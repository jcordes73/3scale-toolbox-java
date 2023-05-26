package com.redhat.threescale.toolbox.commands.applicationplans;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;

import com.redhat.threescale.toolbox.rest.client.service.AccountManagementService;

import jakarta.inject.Inject;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

@Command(name="delete", mixinStandardHelpOptions = true)
public class ApplicationPlanMetricsLimitsDeleteCommand implements Runnable {

    private static final Logger LOG = Logger.getLogger(ApplicationPlanMetricsLimitsDeleteCommand.class);

    @Inject
    @RestClient
    AccountManagementService accountManagementService;

    @ConfigProperty(name="access_token")
    private String accessToken;

    @Parameters(index = "0", description = "Application Plan ID", arity = "1")
    public int applicationPlanId;

    @Parameters(index = "1", description = "Metrics ID", arity = "1")
    public int metricId;

    @Parameters(index = "2", description = "Limit ID", arity = "1")
    public int limitId;


    @Override
    public void run() {
        try {
            accountManagementService.deleteApplicationPlanMetricLimit(applicationPlanId, metricId, limitId, accessToken);        
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }
}
