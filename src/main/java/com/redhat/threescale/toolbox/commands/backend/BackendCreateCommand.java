package com.redhat.threescale.toolbox.commands.backend;

import com.redhat.threescale.toolbox.rest.client.service.AccountManagementService;

import jakarta.inject.Inject;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;
import picocli.CommandLine.Option;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;


@Command(name="create", mixinStandardHelpOptions = true)
public class BackendCreateCommand implements Runnable {

    private static final Logger LOG = Logger.getLogger(BackendCreateCommand.class);

    @Inject
    @RestClient
    AccountManagementService accountManagementService;

    @ConfigProperty(name="access_token")
    private String accessToken;

    @Parameters(index = "0", description = "name", arity = "1")
    public String name;

    @Parameters(index = "1", description = "Private Base URL (your API)", arity = "1")
    public String privateEndpoint;

    @Option(names = {"--system-name",}, description = "System name")
    public String systemName;

    @Option(names = {"--description",}, description = "Description")
    public String description;

    @Override
    public void run() {
        try {
            accountManagementService.createBackend(accessToken, name, systemName, description, privateEndpoint);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }        
    }
}