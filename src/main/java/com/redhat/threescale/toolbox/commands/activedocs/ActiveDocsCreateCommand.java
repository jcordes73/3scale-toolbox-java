package com.redhat.threescale.toolbox.commands.activedocs;

import com.redhat.threescale.toolbox.rest.client.service.AccountManagementService;

import jakarta.inject.Inject;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;
import picocli.CommandLine.Option;

import java.nio.file.Files;
import java.nio.file.Paths;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;


@Command(name="create", mixinStandardHelpOptions = true)
public class ActiveDocsCreateCommand implements Runnable {

    private static final Logger LOG = Logger.getLogger(ActiveDocsCreateCommand.class);

    @Inject
    @RestClient
    AccountManagementService accountManagementService;

    @ConfigProperty(name="access_token")
    private String accessToken;

    @Parameters(index = "0", description = "name", arity = "1")
    public String name;

    @Parameters(index = "1", description = "Swagger File", arity = "1")
    public String swaggerFile;
    
    @Option(names = {"--system-name",}, description = "System name")
    public String systemName;

    @Option(names = {"--service-id",}, description = "Service ID", defaultValue = Option.NULL_VALUE)
    public Integer serviceId;

    @Option(names = {"--description",}, description = "Description")
    public String description;
    
    @Option(names = {"--published",}, description = "Published", defaultValue = Option.NULL_VALUE)
    public Boolean published;

    @Option(names = {"--skip-swagger-validation",}, description = "Skip swagger validation", defaultValue = Option.NULL_VALUE)
    public Boolean skipSwaggerValidation;

    @Override
    public void run() {

        try {
            String body = Files.readString(Paths.get(swaggerFile));
 
            accountManagementService.createActiveDocs(accessToken, name, systemName, serviceId, body, description, published, skipSwaggerValidation);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        
    }
}