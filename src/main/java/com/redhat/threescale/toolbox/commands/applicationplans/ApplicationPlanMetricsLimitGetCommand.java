package com.redhat.threescale.toolbox.commands.applicationplans;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;

import com.redhat.threescale.toolbox.rest.client.service.AccountManagementService;

import jakarta.inject.Inject;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;
import picocli.CommandLine.Spec;
import picocli.CommandLine.Model.CommandSpec;

@Command(name="get", mixinStandardHelpOptions = true)
public class ApplicationPlanMetricsLimitGetCommand implements Runnable {

    private static final Logger LOG = Logger.getLogger(ApplicationPlanMetricsLimitGetCommand.class);

    @Spec
    CommandSpec spec;
    
    @Inject
    @RestClient
    AccountManagementService accountManagementService;

    @ConfigProperty(name="access_token")
    private String accessToken;

    @Parameters(index = "0", description = "Application Plan ID", arity = "1")
    public int applicationPlanId;

    @Parameters(index = "1", description = "Metric ID", arity = "1")
    public int metricId;

    @Parameters(index = "2", description = "Limit ID", arity = "1")
    public int limitId;

    @Override
    public void run() {
        try {
            String response = accountManagementService.getApplicationPlanMetricLimit(applicationPlanId, metricId, limitId, accessToken);

            spec.commandLine().getOut().println(response);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }
}