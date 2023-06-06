package com.redhat.threescale.toolbox.commands.applicationplans;

import org.jboss.logging.Logger;

import com.redhat.threescale.toolbox.rest.client.service.AccountManagementService;
import com.redhat.threescale.toolbox.rest.client.service.AccountManagementServiceFactory;

import jakarta.inject.Inject;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

@Command(name="create", mixinStandardHelpOptions = true)
public class ApplicationPlanMetricsLimitsCreateCommand implements Runnable {

    private static final Logger LOG = Logger.getLogger(ApplicationPlanMetricsLimitsCreateCommand.class);

    @Inject
    AccountManagementServiceFactory accountManagementServiceFactory;

    @Parameters(index = "0", description = "Application Plan ID", arity = "1")
    public int applicationPlanId;

    @Parameters(index = "1", description = "Metrics ID", arity = "1")
    public int metricId;

    @Parameters(index = "2", description = "Value", arity = "1")
    public int value;

    @Parameters(index = "3", description = "Period. Valid values: ${COMPLETION-CANDIDATES}. Default value ${DEFAULT-VALUE}", arity = "1", defaultValue = "minute")
    public AccountManagementService.LimitPeriod period = AccountManagementService.LimitPeriod.minute;

    @Override
    public void run() {
        try {
            accountManagementServiceFactory.getAccountManagementService().createApplicationPlanMetricLimit(applicationPlanId, metricId, value, period);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }
}