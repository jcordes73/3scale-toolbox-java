package com.redhat.threescale.toolbox.commands.activedocs;

import com.redhat.threescale.toolbox.rest.client.service.AccountManagementService;

import jakarta.inject.Inject;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;
import picocli.CommandLine.Option;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;


@Command(name="update", mixinStandardHelpOptions = true)
public class ActiveDocsUpdateCommand implements Runnable {

    private static final Logger LOG = Logger.getLogger(ActiveDocsUpdateCommand.class);

    @Inject
    @RestClient
    AccountManagementService accountManagementService;

    @ConfigProperty(name="access_token")
    private String accessToken;

    @Parameters(index = "0", description = "Activedoc ID", arity = "1")
    public int activeDocId;

    @Option(names = {"--name"}, description = "Name", arity = "1")
    public String name;

    @Option(names = {"-f"}, description = "Swagger file")
    public String swaggerFile;
    
    @Option(names = {"--system-name",}, description = "System name")
    public String systemName;

    @Option(names = {"--service-id",}, description = "Service ID")
    public Integer serviceId;

    @Option(names = {"--description",}, description = "Description")
    public String description;
    
    @Option(names = {"--published",}, description = "Published")
    public Boolean published;

    @Option(names = {"--skip-swagger-validation",}, description = "Skip swagger validation")
    public Boolean skipSwaggerValidation;

    @Override
    public void run() {

        try {
            accountManagementService.updateActiveDocs(accessToken, name, serviceId, accessToken, description, published, skipSwaggerValidation);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }
}