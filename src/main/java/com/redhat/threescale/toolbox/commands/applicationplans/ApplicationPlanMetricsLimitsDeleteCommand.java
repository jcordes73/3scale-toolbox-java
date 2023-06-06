package com.redhat.threescale.toolbox.commands.applicationplans;

import org.jboss.logging.Logger;

import com.redhat.threescale.toolbox.rest.client.service.AccountManagementServiceFactory;

import jakarta.inject.Inject;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

@Command(name="delete", mixinStandardHelpOptions = true)
public class ApplicationPlanMetricsLimitsDeleteCommand implements Runnable {

    private static final Logger LOG = Logger.getLogger(ApplicationPlanMetricsLimitsDeleteCommand.class);

    @Inject
    AccountManagementServiceFactory accountManagementServiceFactory;

    @Parameters(index = "0", description = "Application Plan ID", arity = "1")
    public int applicationPlanId;

    @Parameters(index = "1", description = "Metrics ID", arity = "1")
    public int metricId;

    @Parameters(index = "2", description = "Limit ID", arity = "1")
    public int limitId;


    @Override
    public void run() {
        try {
            accountManagementServiceFactory.getAccountManagementService().deleteApplicationPlanMetricLimit(applicationPlanId, metricId, limitId);        
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }
}
