package com.redhat.threescale.toolbox.commands.services.applicationplans;


import com.redhat.threescale.toolbox.rest.client.service.AccountManagementService;
import com.redhat.threescale.toolbox.rest.client.service.AccountManagementServiceFactory;

import jakarta.inject.Inject;
import picocli.CommandLine.Command;
import picocli.CommandLine.Model.CommandSpec;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;
import picocli.CommandLine.Spec;


@Command(name="create", mixinStandardHelpOptions = true)
public class ApplicationPlanCreateCommand implements Runnable {

    @Spec
    CommandSpec spec;
    
    @Inject
    AccountManagementServiceFactory accountManagementServiceFactory;

    @Parameters(index = "0", description = "Service ID", arity = "1")
    public int serviceId;

    @Parameters(index = "1", description = "Application Plan Name", arity = "1")
    public String applicationPlanName;
    
    @Option(names = {"--approval-required",}, description = "Approval required")
    public boolean approvalRequired;

    @Option(names = {"--cost-per-month",}, description = "Cost per month")
    public Float costPerMonth;

    @Option(names = {"--setup-fee",}, description = "Setup fee")
    public Float setupFee;

    @Option(names = {"--trial-period-days",}, description = "Trial period in days.")
    public Integer trialPeriodDays;

    @Option(names = {"--cancellation-period",}, description = "Cancellation period in days.")
    public Integer cancellationPeriod;


    @Option(names = {"--state-event",}, description = "State event. Valid values: ${COMPLETION-CANDIDATES}")
    public AccountManagementService.StateEvent stateEvent;

    @Override
    public void run() {
        try {
            accountManagementServiceFactory.getAccountManagementService().createServiceApplicationPlan(serviceId, applicationPlanName, approvalRequired, costPerMonth, setupFee, trialPeriodDays, stateEvent, cancellationPeriod);
        } catch (Exception e) {
            spec.commandLine().getOut().println(e.getMessage());
        }        
    }
}