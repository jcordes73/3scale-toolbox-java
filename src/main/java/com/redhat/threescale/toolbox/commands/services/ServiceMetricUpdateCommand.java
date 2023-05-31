package com.redhat.threescale.toolbox.commands.services;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;

import com.redhat.threescale.toolbox.rest.client.service.AccountManagementService;

import jakarta.inject.Inject;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

@Command(name="update", mixinStandardHelpOptions = true)
public class ServiceMetricUpdateCommand implements Runnable {

    private static final Logger LOG = Logger.getLogger(ServiceMetricUpdateCommand.class);
    
    @Inject
    @RestClient
    AccountManagementService accountManagementService;

    @ConfigProperty(name="access_token")
    private String accessToken;

    @Parameters(index = "0", description = "Service ID", arity = "1")
    public int serviceId;

    @Parameters(index = "1", description = "Service Metric ID", arity = "1")
    public int metricId;

    @Option(names = {"--friendly-name"}, description = "Friendly name", arity = "1")
    public String friendlyName;

    @Option(names = {"--unit",}, description = "Unit")
    public String unit;

    @Option(names = {"--description",}, description = "Description")
    public String description;
    

    @Override
    public void run() {
        try {
            accountManagementService.updateServiceMetric(serviceId, metricId, accessToken, friendlyName, unit, description);

        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }
}
