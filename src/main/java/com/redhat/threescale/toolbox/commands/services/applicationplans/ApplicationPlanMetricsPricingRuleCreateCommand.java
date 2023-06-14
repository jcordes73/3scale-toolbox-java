package com.redhat.threescale.toolbox.commands.services.applicationplans;

import org.jboss.logging.Logger;

import com.redhat.threescale.toolbox.rest.client.service.AccountManagementServiceFactory;

import jakarta.inject.Inject;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

@Command(name="create", mixinStandardHelpOptions = true)
public class ApplicationPlanMetricsPricingRuleCreateCommand implements Runnable {

    private static final Logger LOG = Logger.getLogger(ApplicationPlanMetricsPricingRuleCreateCommand.class);

    @Inject
    AccountManagementServiceFactory accountManagementServiceFactory;

    @Parameters(index = "0", description = "Application Plan ID", arity = "1")
    public int applicationPlanId;

    @Parameters(index = "1", description = "Metrics ID", arity = "1")
    public int metricId;

    @Option(names = {"--min"}, description = "From (min) hit", arity = "1", defaultValue = Option.NULL_VALUE)
    public Integer min;

    @Option(names = {"--max"}, description = "To (max) hit", arity = "1", defaultValue = Option.NULL_VALUE)
    public Integer max;

    @Option(names = {"--cost-per-unit"}, description = "Cost per unit", arity = "1", defaultValue = Option.NULL_VALUE)
    public Float costPerUnit;


    @Override
    public void run() {
        try {
            accountManagementServiceFactory.getAccountManagementService().createApplicationPlanMetricPricingRule(applicationPlanId, metricId, min, max, costPerUnit);
         } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }
}