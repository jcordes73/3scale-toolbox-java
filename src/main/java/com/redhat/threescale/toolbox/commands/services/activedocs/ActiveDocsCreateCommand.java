package com.redhat.threescale.toolbox.commands.services.activedocs;

import java.io.PrintWriter;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import com.redhat.threescale.toolbox.helpers.XPathExecution;
import com.redhat.threescale.toolbox.picocli.QuotedStringConverter;
import com.redhat.threescale.toolbox.rest.client.service.AccountManagementService;
import com.redhat.threescale.toolbox.rest.client.service.AccountManagementServiceFactory;

import io.swagger.parser.OpenAPIParser;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.PathItem;
import io.swagger.v3.oas.models.Paths;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.security.SecurityScheme.Type;
import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.parser.core.models.SwaggerParseResult;

import jakarta.inject.Inject;
import picocli.CommandLine.Command;
import picocli.CommandLine.Model.CommandSpec;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;
import picocli.CommandLine.Spec;


@Command(name="create", mixinStandardHelpOptions = true)
public class ActiveDocsCreateCommand implements Runnable {    
    
    @Spec
    CommandSpec spec;

    @Inject
    AccountManagementServiceFactory accountManagementServiceFactory;

    @Inject
    XPathExecution xPathExecution;

    @Parameters(index = "0", description = "name", arity = "1")
    public String name;

    @Parameters(index = "1", description = "Swagger File. Either local file or http-url.", arity = "1")
    public String swaggerFile;
    
    @Option(names = {"--system-name",}, description = "System name")
    public String systemName;

    @Option(names = {"--service-id",}, description = "Service ID", defaultValue = Option.NULL_VALUE)
    public Integer serviceId;

    @Option(names = {"--description",}, description = "Description", converter = QuotedStringConverter.class)
    public String description;
    
    @Option(names = {"--published",}, description = "Published", defaultValue = Option.NULL_VALUE, negatable = true)
    public Boolean published;

    @Option(names = {"--skip-swagger-validation",}, description = "Skip swagger validation", defaultValue = Option.NULL_VALUE, negatable = true)
    public Boolean skipSwaggerValidation;

    @Override
    public void run() {

        PrintWriter out = spec.commandLine().getOut();
        
        try {
            String body = null;
            
            if (swaggerFile.startsWith("http")){
                HttpClient client = HttpClient.newHttpClient();
                HttpRequest request = HttpRequest.newBuilder(URI.create(swaggerFile)).header("content-type", "application/json").GET().build();
                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
                body = response.body();
            } else {
                body = Files.readString(java.nio.file.Paths.get(swaggerFile));
            }

            SwaggerParseResult result = new OpenAPIParser().readContents(body, null, null);
            OpenAPI openApi = result.getOpenAPI();

            if (result.getMessages() != null) result.getMessages().forEach(out::println);

            accountManagementServiceFactory.getAccountManagementService().createActiveDocs(name, systemName, serviceId, body, description, published, skipSwaggerValidation);

            setSecurity(openApi);

            String metrics = accountManagementServiceFactory.getAccountManagementService().getServiceMetrics(serviceId.intValue());
            int metricId = Integer.parseInt(xPathExecution.execute(metrics, "//metric[./name='hits']/id/text()").trim());    
            String unit = "hit";
        
            Paths paths = openApi.getPaths();

            for (Iterator<Entry<String,PathItem>> itPaths = paths.entrySet().iterator(); itPaths.hasNext();){
                Entry<String,PathItem> entry = itPaths.next();
                String path = entry.getKey();

                createMethodAndMappingRules(path, "GET", entry.getValue().getGet(), serviceId.intValue(), metricId, unit);
                createMethodAndMappingRules(path, "POST", entry.getValue().getPost(), serviceId.intValue(), metricId, unit);
                createMethodAndMappingRules(path, "PUT", entry.getValue().getPut(), serviceId.intValue(), metricId, unit);
                createMethodAndMappingRules(path, "DELETE", entry.getValue().getDelete(), serviceId.intValue(), metricId, unit);
                createMethodAndMappingRules(path, "PATCH", entry.getValue().getPatch(), serviceId.intValue(), metricId, unit);
            }            
        } catch (Exception e) {
            //LOG.error(e.getMessage());
            out.println(e.getMessage());
        } finally {
            if (out != null)
                out.close();
        }      
    }

    public void createMethodAndMappingRules(String path, String httpMethod, Operation operation, int serviceId, int metricId, String unit) throws Exception{
        if (operation != null){
            String operationId = operation.getOperationId();
            String methodSystemName = operationId.toLowerCase();
            String methodDescription = operation.getSummary();

            accountManagementServiceFactory.getAccountManagementService().createServiceMetricMethod(serviceId, metricId, operationId, methodSystemName, unit, methodDescription);
            int methodMetricId = Integer.parseInt(xPathExecution.execute(accountManagementServiceFactory.getAccountManagementService().getServiceMetricMethods(serviceId, metricId),"//methods/method[system_name='"+methodSystemName+"']/id/text()").trim());            
            accountManagementServiceFactory.getAccountManagementService().createServiceProxyMappingRule(serviceId, httpMethod, path, 1, methodMetricId, null, null);
        }
    }

    public void setSecurity(OpenAPI openApi) throws Exception {
        if (openApi.getSecurity() != null){
            for (Iterator<SecurityRequirement> securityIt = openApi.getSecurity().iterator(); securityIt.hasNext();){
                SecurityRequirement securityRequirement = securityIt.next();

                for (Iterator<Entry<String, List<String>>> securityEntryIt = securityRequirement.entrySet().iterator(); securityEntryIt.hasNext();){
                    Entry<String,List<String>> entry = securityEntryIt.next();

                    String componentName = entry.getKey();

                    if (openApi.getComponents().getSecuritySchemes().containsKey(componentName)){
                        SecurityScheme securityScheme = openApi.getComponents().getSecuritySchemes().get(componentName);

                        Type type = securityScheme.getType();
                        String name = securityScheme.getName();

                        if (type.equals(Type.APIKEY)){
                            if (securityScheme.getIn().equals(securityScheme.getIn().HEADER)){
                                accountManagementServiceFactory.getAccountManagementService().updateServiceProxy(serviceId.intValue(), null, null, AccountManagementService.CredentialsLocation.headers, null, null, name, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
                            } else if (securityScheme.getIn().equals(securityScheme.getIn().QUERY)){
                                accountManagementServiceFactory.getAccountManagementService().updateServiceProxy(serviceId.intValue(), null, null, AccountManagementService.CredentialsLocation.query, null, null, name, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
                            }
                        } else if (type.equals(Type.OPENIDCONNECT)){
                            String openIdConnectUrl = securityScheme.getOpenIdConnectUrl();
                            accountManagementServiceFactory.getAccountManagementService().updateServiceProxy(serviceId.intValue(), null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, openIdConnectUrl, "keycloak", null, null, null);
                        }
                    }
                }
            }
        }
    }   
}