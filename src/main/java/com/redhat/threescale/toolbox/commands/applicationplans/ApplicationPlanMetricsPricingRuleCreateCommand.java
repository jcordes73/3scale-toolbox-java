package com.redhat.threescale.toolbox.commands.applicationplans;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;

import com.redhat.threescale.toolbox.rest.client.service.AccountManagementService;

import jakarta.inject.Inject;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

@Command(name="create", mixinStandardHelpOptions = true)
public class ApplicationPlanMetricsPricingRuleCreateCommand implements Runnable {

    private static final Logger LOG = Logger.getLogger(ApplicationPlanMetricsPricingRuleCreateCommand.class);

    @Inject
    @RestClient
    AccountManagementService accountManagementService;

    @ConfigProperty(name="access_token")
    private String accessToken;

    @Parameters(index = "0", description = "Application Plan ID", arity = "1")
    public int applicationPlanId;

    @Parameters(index = "1", description = "Metrics ID", arity = "1")
    public int metricId;

    @Parameters(index = "2", description = "From (min) hit", arity = "1")
    public int min;

    @Parameters(index = "3", description = "To (max) hit", arity = "1")
    public int max;

    @Parameters(index = "4", description = "Cost per unit", arity = "1")
    public float costPerUnit;

    @Override
    public void run() {
        try {
            accountManagementService.createApplicationPlanMetricPricingRule(applicationPlanId, metricId, accessToken, min, max, costPerUnit);
         } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }
}