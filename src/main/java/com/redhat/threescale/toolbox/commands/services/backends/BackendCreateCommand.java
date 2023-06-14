package com.redhat.threescale.toolbox.commands.services.backends;

import org.jboss.logging.Logger;

import com.redhat.threescale.toolbox.picocli.QuotedStringConverter;
import com.redhat.threescale.toolbox.rest.client.service.AccountManagementServiceFactory;

import jakarta.inject.Inject;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;


@Command(name="create", mixinStandardHelpOptions = true)
public class BackendCreateCommand implements Runnable {

    private static final Logger LOG = Logger.getLogger(BackendCreateCommand.class);

    @Inject
    AccountManagementServiceFactory accountManagementServiceFactory;

    @Parameters(index = "0", description = "name", arity = "1", converter = QuotedStringConverter.class)
    public String name;

    @Parameters(index = "1", description = "Private Base URL (your API)", arity = "1")
    public String privateEndpoint;

    @Option(names = {"--system-name",}, description = "System name")
    public String systemName;

    @Option(names = {"--description",}, description = "Description", converter = QuotedStringConverter.class)
    public String description;

    @Override
    public void run() {
        try {
            accountManagementServiceFactory.getAccountManagementService().createBackend(name, systemName, description, privateEndpoint);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }        
    }
}