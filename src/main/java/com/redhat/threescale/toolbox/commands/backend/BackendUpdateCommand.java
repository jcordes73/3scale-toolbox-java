package com.redhat.threescale.toolbox.commands.backend;

import com.redhat.threescale.toolbox.rest.client.service.AccountManagementService;

import jakarta.inject.Inject;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;
import picocli.CommandLine.Option;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;

@Command(name="update", mixinStandardHelpOptions = true)
public class BackendUpdateCommand implements Runnable {

    private static final Logger LOG = Logger.getLogger(BackendUpdateCommand.class);

    @Inject
    @RestClient
    AccountManagementService accountManagementService;

    @ConfigProperty(name="access_token")
    private String accessToken;

    @Parameters(index = "0", description = "Backend ID", arity = "1")
    public int backendId;

    @Option(names = {"--name",}, description = "Backend name")
    public String name;

    @Option(names = {"--system-name",}, description = "System name")
    public String systemName;

    @Option(names = {"--description",}, description = "Description")
    public String description;

    @Option(names = {"--private-endpoint",}, description = "Private Base URL (your API)")
    public String privateEndpoint;

    @Override
    public void run() {
        try {
            accountManagementService.updateBackend(backendId, accessToken, name, systemName, description, privateEndpoint);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }        
    }
}