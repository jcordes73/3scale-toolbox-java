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
public class BackendMetricUpdateCommand implements Runnable {

    private static final Logger LOG = Logger.getLogger(BackendMetricUpdateCommand.class);

    @Inject
    @RestClient
    AccountManagementService accountManagementService;

    @ConfigProperty(name="access_token")
    private String accessToken;

    @Parameters(index = "0", description = "Backend ID", arity = "1")
    public int backendId;

    @Parameters(index = "1", description = "Metric ID", arity = "1")
    public int metricId;

    @Option(names = {"--friendly-name",}, description = "Friendly name")
    public String friendlyName;

    @Option(names = {"--unit",}, description = "Unit")
    public String unit;

    @Option(names = {"--description",}, description = "Description")
    public String description;

    @Override
    public void run() {
        try {
            accountManagementService.updateBackendMetric(backendId, accessToken, friendlyName, unit, description);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }        
    }
}