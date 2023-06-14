package com.redhat.threescale.toolbox.commands.services.applicationplans;

import org.jboss.logging.Logger;

import com.redhat.threescale.toolbox.rest.client.service.AccountManagementService;
import com.redhat.threescale.toolbox.rest.client.service.AccountManagementServiceFactory;

import jakarta.inject.Inject;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

@Command(name="update", mixinStandardHelpOptions = true)
public class ApplicationPlanMetricsLimitsUpdateCommand implements Runnable {

    private static final Logger LOG = Logger.getLogger(ApplicationPlanMetricsLimitsUpdateCommand.class);

    @Inject
    AccountManagementServiceFactory accountManagementServiceFactory;

    @Parameters(index = "0", description = "Application Plan ID", arity = "1")
    public int applicationPlanId;

    @Parameters(index = "1", description = "Metrics ID", arity = "1")
    public int metricId;

    @Parameters(index = "2", description = "Limit ID", arity = "1")
    public int limitId;

    @Parameters(index = "3", description = "Value", arity = "1")
    public int value;

    @Parameters(index = "4", description = "Period. Valid values: ${COMPLETION-CANDIDATES}. Default value ${DEFAULT-VALUE}", defaultValue = "minute", arity = "1")
    public AccountManagementService.LimitPeriod period = AccountManagementService.LimitPeriod.minute;

    @Override
    public void run() {
        try {
            accountManagementServiceFactory.getAccountManagementService().updateApplicationPlanMetricLimit(applicationPlanId, metricId, limitId, value, period);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }
}