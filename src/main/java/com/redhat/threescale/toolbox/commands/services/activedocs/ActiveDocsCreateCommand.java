package com.redhat.threescale.toolbox.commands.services.activedocs;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.jboss.logging.Logger;

import com.redhat.threescale.toolbox.picocli.QuotedStringConverter;
import com.redhat.threescale.toolbox.rest.client.service.AccountManagementServiceFactory;

import jakarta.inject.Inject;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;


@Command(name="create", mixinStandardHelpOptions = true)
public class ActiveDocsCreateCommand implements Runnable {

    private static final Logger LOG = Logger.getLogger(ActiveDocsCreateCommand.class);

    @Inject
    AccountManagementServiceFactory accountManagementServiceFactory;

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

        try {
            String body = null;
            
            if (swaggerFile.startsWith("http")){
                HttpClient client = HttpClient.newHttpClient();
                HttpRequest request = HttpRequest.newBuilder(URI.create(swaggerFile)).header("content-type", "application/json").GET().build();
                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
                body = response.body();
            } else {
                body = Files.readString(Paths.get(swaggerFile));
            }
 
            accountManagementServiceFactory.getAccountManagementService().createActiveDocs(name, systemName, serviceId, body, description, published, skipSwaggerValidation);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        
    }
}