package com.redhat.threescale.toolbox.commands.applicationplans;

import org.jboss.logging.Logger;

import com.redhat.threescale.toolbox.rest.client.service.AccountManagementServiceFactory;

import jakarta.inject.Inject;
import picocli.CommandLine.Command;
import picocli.CommandLine.Model.CommandSpec;
import picocli.CommandLine.Parameters;
import picocli.CommandLine.Spec;

@Command(name="listParameters", mixinStandardHelpOptions = true)
public class ApplicationPlanMetricsLimitsListCommand implements Runnable {

    private static final Logger LOG = Logger.getLogger(ApplicationPlanMetricsLimitsListCommand.class);

    @Spec
    CommandSpec spec;
    
    @Inject
    AccountManagementServiceFactory accountManagementServiceFactory;

    @Parameters(index = "0", description = "Application Plan ID", arity = "1")
    public int applicationPlanId;

    @Parameters(index = "1", description = "Metric ID", arity = "1")
    public int metricId;

    @Override
    public void run() {
        try {
            String response = accountManagementServiceFactory.getAccountManagementService().getApplicationPlanMetricLimits(applicationPlanId, metricId);

            spec.commandLine().getOut().println(response);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }
}