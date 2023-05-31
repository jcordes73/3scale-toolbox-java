package com.redhat.threescale.toolbox.commands.backend;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;

import com.redhat.threescale.toolbox.rest.client.service.AccountManagementService;

import jakarta.inject.Inject;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

@Command(name="delete", mixinStandardHelpOptions = true)
public class BackendUsageDeleteCommand implements Runnable {

    private static final Logger LOG = Logger.getLogger(BackendUsageDeleteCommand.class);
    
    @Inject
    @RestClient
    AccountManagementService accountManagementService;

    @ConfigProperty(name="access_token")
    private String accessToken;

    @Parameters(index = "0", description = "Service ID", arity = "1")
    public int serviceId;

    @Parameters(index = "1", description = "Backend Usage ID", arity = "1")
    public int backendUsageId;

    @Override
    public void run() {
        try {
            accountManagementService.deleteBackendUsage(serviceId, backendUsageId, accessToken);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }
}