package com.redhat.threescale.toolbox.commands.backend;

import org.jboss.logging.Logger;

import com.redhat.threescale.toolbox.picocli.QuotedStringConverter;
import com.redhat.threescale.toolbox.rest.client.service.AccountManagementServiceFactory;

import jakarta.inject.Inject;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

@Command(name="update", mixinStandardHelpOptions = true)
public class BackendUpdateCommand implements Runnable {

    private static final Logger LOG = Logger.getLogger(BackendUpdateCommand.class);

    @Inject
    AccountManagementServiceFactory accountManagementServiceFactory;

    @Parameters(index = "0", description = "Backend ID", arity = "1")
    public int backendId;

    @Option(names = {"--name",}, description = "Backend name")
    public String name;

    @Option(names = {"--system-name",}, description = "System name")
    public String systemName;

    @Option(names = {"--description",}, description = "Description", converter = QuotedStringConverter.class)
    public String description;

    @Option(names = {"--private-endpoint",}, description = "Private Base URL (your API)")
    public String privateEndpoint;

    @Override
    public void run() {
        try {
            accountManagementServiceFactory.getAccountManagementService().updateBackend(backendId, name, systemName, description, privateEndpoint);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }        
    }
}