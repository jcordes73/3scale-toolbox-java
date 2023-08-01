package com.redhat.threescale.toolbox.commands.accounts.accountplans;

import org.jboss.logging.Logger;

import com.redhat.threescale.toolbox.rest.client.service.AccountManagementService;
import com.redhat.threescale.toolbox.rest.client.service.AccountManagementServiceFactory;

import jakarta.inject.Inject;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

@Command(name="update", mixinStandardHelpOptions = true)
public class AccountPlanUpdateCommand implements Runnable {

    private static final Logger LOG = Logger.getLogger(AccountPlanUpdateCommand.class);
    
    @Inject
    AccountManagementServiceFactory accountManagementServiceFactory;

    @Parameters(index = "0", description = "Account Plan ID", arity = "1")
    public int accountPlanId;

    @Option(names = {"--name"}, description = "Name")
    public String name;

    @Option(names = {"--approval-required"}, description = "Approval required")
    public boolean approvalRequired;

    @Option(names = {"--state-event"}, description = "State event. Valid values: ${COMPLETION-CANDIDATES}")
    public AccountManagementService.StateEvent stateEvent;

    @Option(names = {"--cost-per-month",}, description = "Cost per month")
    public Float costPerMonth;

    @Option(names = {"--setup-fee",}, description = "Setup fee")
    public Float setupFee;

    @Option(names = {"--trial-period-days",}, description = "Trial period in days.")
    public Integer trialPeriodDays;
    
    @Option(names = {"--cancellation-period",}, description = "Cancellation period in days.")
    public Integer cancellationPeriod;


    @Override
    public void run() {
        try {
            accountManagementServiceFactory.getAccountManagementService().updateAccountPlan(accountPlanId, name, approvalRequired, name, setupFee, costPerMonth, trialPeriodDays, cancellationPeriod);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }
}