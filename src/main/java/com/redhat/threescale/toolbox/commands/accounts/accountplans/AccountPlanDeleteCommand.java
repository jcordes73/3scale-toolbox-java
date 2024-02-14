package com.redhat.threescale.toolbox.commands.accounts.accountplans;

import com.redhat.threescale.toolbox.rest.client.service.AccountManagementServiceFactory;

import jakarta.inject.Inject;
import picocli.CommandLine.Command;
import picocli.CommandLine.Model.CommandSpec;
import picocli.CommandLine.Parameters;
import picocli.CommandLine.Spec;


@Command(name="delete", mixinStandardHelpOptions = true)
public class AccountPlanDeleteCommand implements Runnable {

    @Spec
    CommandSpec spec;
    
    @Inject
    AccountManagementServiceFactory accountManagementServiceFactory;

    @Parameters(index = "0", description = "Account Plan ID", arity = "1")
    public int accountPlanId;
    
    @Override
    public void run() {

        try {
            accountManagementServiceFactory.getAccountManagementService().deleteAccountPlan(accountPlanId);
        } catch (Exception e) {
            spec.commandLine().getOut().println(e.getMessage());
        }
        
    }
}