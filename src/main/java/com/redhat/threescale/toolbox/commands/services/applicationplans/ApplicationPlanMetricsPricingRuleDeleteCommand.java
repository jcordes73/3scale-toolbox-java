package com.redhat.threescale.toolbox.commands.services.applicationplans;

import org.jboss.logging.Logger;

import com.redhat.threescale.toolbox.rest.client.service.AccountManagementServiceFactory;

import jakarta.inject.Inject;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

@Command(name="delete", mixinStandardHelpOptions = true)
public class ApplicationPlanMetricsPricingRuleDeleteCommand implements Runnable {

    private static final Logger LOG = Logger.getLogger(ApplicationPlanMetricsPricingRuleDeleteCommand.class);

    @Inject
    AccountManagementServiceFactory accountManagementServiceFactory;

    @Parameters(index = "0", description = "Application Plan ID", arity = "1")
    public int applicationPlanId;

    @Parameters(index = "1", description = "Metrics ID", arity = "1")
    public int metricId;

    @Parameters(index = "2", description = "Pricing Rule ID", arity = "1")
    public int pricingRuleId;


    @Override
    public void run() {
        try {
            accountManagementServiceFactory.getAccountManagementService().deleteApplicationPlanMetricPricingRule(applicationPlanId, metricId, pricingRuleId);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }
}