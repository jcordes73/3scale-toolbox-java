package com.redhat.threescale.toolbox.commands.services.applicationplans;

import com.redhat.threescale.toolbox.rest.client.service.AccountManagementServiceFactory;

import jakarta.inject.Inject;
import picocli.CommandLine.Command;
import picocli.CommandLine.Model.CommandSpec;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;
import picocli.CommandLine.Spec;

@Command(name="create", mixinStandardHelpOptions = true)
public class ApplicationPlanMetricsPricingRuleCreateCommand implements Runnable {

    @Spec
    CommandSpec spec;
    
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
            spec.commandLine().getOut().println(e.getMessage());
        }
    }
}