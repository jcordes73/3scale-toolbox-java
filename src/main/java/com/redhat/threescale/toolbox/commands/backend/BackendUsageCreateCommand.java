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
public class BackendUsageCreateCommand implements Runnable {

    private static final Logger LOG = Logger.getLogger(BackendUsageCreateCommand.class);

    @Inject
    @RestClient
    AccountManagementService accountManagementService;

    @ConfigProperty(name="access_token")
    private String accessToken;

    @Parameters(index = "0", description = "System ID", arity = "1")
    public int systemId;

    @Parameters(index = "1", description = "Backend ID", arity = "1")
    public int backendId;

    @Option(names = {"--path",}, description = "Path")
    public String path;

    @Override
    public void run() {
        try {
            accountManagementService.createBackendUsage(systemId, accessToken, backendId, path);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }        
    }
}