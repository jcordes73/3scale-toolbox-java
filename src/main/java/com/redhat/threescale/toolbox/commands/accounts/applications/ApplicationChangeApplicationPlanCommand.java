package com.redhat.threescale.toolbox.commands.accounts.applications;

import com.redhat.threescale.toolbox.rest.client.service.AccountManagementServiceFactory;

import jakarta.inject.Inject;
import picocli.CommandLine.Command;
import picocli.CommandLine.Model.CommandSpec;
import picocli.CommandLine.Parameters;
import picocli.CommandLine.Spec;

@Command(name="change", mixinStandardHelpOptions = true)
public class ApplicationChangeApplicationPlanCommand implements Runnable {

    @Spec
    CommandSpec spec;
    
    @Inject
    AccountManagementServiceFactory accountManagementServiceFactory;

    @Parameters(index = "0", description = "Account ID", arity = "1")
    private int accountId;

    @Parameters(index = "1", description = "Application ID", arity = "1")
    private int applicationId;

    @Parameters(index = "2", description = "Application Plan ID", arity = "1")
    private int applicationPlanId;
    
    @Override
    public void run() {
        try {
            accountManagementServiceFactory.getAccountManagementService().changeApplicationPlan(accountId, applicationId, applicationPlanId);
        } catch (Exception e) {
            spec.commandLine().getOut().println(e.getMessage());
        }
    }
}
