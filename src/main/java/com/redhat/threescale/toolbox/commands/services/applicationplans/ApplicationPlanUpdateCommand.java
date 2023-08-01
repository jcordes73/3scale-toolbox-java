package com.redhat.threescale.toolbox.commands.services.applicationplans;

import org.jboss.logging.Logger;

import com.redhat.threescale.toolbox.rest.client.service.AccountManagementService;
import com.redhat.threescale.toolbox.rest.client.service.AccountManagementServiceFactory;

import jakarta.inject.Inject;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;


@Command(name="update", mixinStandardHelpOptions = true)
public class ApplicationPlanUpdateCommand implements Runnable {

    private static final Logger LOG = Logger.getLogger(ApplicationPlanUpdateCommand.class);

    @Inject
    AccountManagementServiceFactory accountManagementServiceFactory;

    @Parameters(index = "0", description = "Service ID", arity = "1")
    public int serviceId;

    @Parameters(index = "0", description = "Application Plan ID", arity = "1")
    public int applicationPlanId;

    @Parameters(index = "1", description = "Application Plan Name", arity = "1")
    public String applicationPlanName;
    
    @Option(names = {"--approval-required",}, description = "Approval required")
    public Boolean approvalRequired;

    @Option(names = {"--cost-per-month",}, description = "Cost per month")
    public Float costPerMonth;

    @Option(names = {"--setup-fee",}, description = "Setup fee")
    public Float setupFee;

    @Option(names = {"--trial-period-days",}, description = "Trial period in days.")
    public Integer trialPeriodDays;

    @Option(names = {"--state-event",}, description = "State event. Valid values: ${COMPLETION-CANDIDATES}")
    public AccountManagementService.StateEvent stateEvent;

    @Option(names = {"--cancellation-period",}, description = "Cancellation period in days.")
    public Integer cancellationPeriod;

    @Override
    public void run() {
        try {
            accountManagementServiceFactory.getAccountManagementService().updateServiceApplicationPlan(serviceId, applicationPlanId, applicationPlanName, approvalRequired, costPerMonth, setupFee, applicationPlanId, stateEvent, cancellationPeriod);
         } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }
}