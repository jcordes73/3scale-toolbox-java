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
public class BackendUsageUpdateCommand implements Runnable {

    private static final Logger LOG = Logger.getLogger(BackendUsageUpdateCommand.class);

    @Inject
    @RestClient
    AccountManagementService accountManagementService;

    @ConfigProperty(name="access_token")
    private String accessToken;

    @Parameters(index = "0", description = "System ID", arity = "1")
    public int systemId;

    @Parameters(index = "1", description = "Backend Usage ID", arity = "1")
    public int backendUsageId;

    @Option(names = {"--path",}, description = "Path")
    public String path;

    @Override
    public void run() {
        try {
            accountManagementService.updateBackendUsage(systemId, backendUsageId, accessToken, path);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }        
    }
}