package com.redhat.threescale.toolbox.commands.services.activedocs;

import java.nio.file.Files;
import java.nio.file.Paths;

import org.jboss.logging.Logger;

import com.redhat.threescale.toolbox.picocli.QuotedStringConverter;
import com.redhat.threescale.toolbox.rest.client.service.AccountManagementServiceFactory;

import jakarta.inject.Inject;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;


@Command(name="update", mixinStandardHelpOptions = true)
public class ActiveDocsUpdateCommand implements Runnable {

    private static final Logger LOG = Logger.getLogger(ActiveDocsUpdateCommand.class);

    @Inject
    AccountManagementServiceFactory accountManagementServiceFactory;

    @Parameters(index = "0", description = "Activedoc ID", arity = "1")
    public int activeDocId;

    @Option(names = {"--name"}, description = "Name", arity = "1")
    public String name;

    @Option(names = {"-f"}, description = "Swagger file")
    public String swaggerFile;

    @Option(names = {"--service-id",}, description = "Service ID")
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
            String body = Files.readString(Paths.get(swaggerFile));
            
            accountManagementServiceFactory.getAccountManagementService().updateActiveDocs(name, serviceId, body, description, published, skipSwaggerValidation);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }
}