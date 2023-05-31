package com.redhat.threescale.toolbox.commands.services;

import com.redhat.threescale.toolbox.rest.client.service.AccountManagementService;

import jakarta.inject.Inject;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;


@Command(name="update", mixinStandardHelpOptions = true)
public class ServiceApplicationPlanUpdateCommand implements Runnable {

    private static final Logger LOG = Logger.getLogger(ServiceApplicationPlanUpdateCommand.class);

    @Inject
    @RestClient
    AccountManagementService accountManagementService;
    
    @ConfigProperty(name="access_token")
    private String accessToken;

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

    @Override
    public void run() {
        try {
            accountManagementService.updateServiceApplicationPlan(serviceId, applicationPlanId, accessToken, applicationPlanName, approvalRequired, costPerMonth, setupFee, applicationPlanId, stateEvent);
         } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }
}