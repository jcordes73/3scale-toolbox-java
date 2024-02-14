package com.redhat.threescale.toolbox.commands.accounts.accountplans;

import com.redhat.threescale.toolbox.rest.client.service.AccountManagementServiceFactory;

import jakarta.inject.Inject;
import picocli.CommandLine.Command;
import picocli.CommandLine.Model.CommandSpec;
import picocli.CommandLine.Parameters;
import picocli.CommandLine.Spec;


@Command(name="create", mixinStandardHelpOptions = true)
public class AccountPlanFeatureCreateCommand implements Runnable {    @Spec
    CommandSpec spec;
    
    @Inject
    AccountManagementServiceFactory accountManagementServiceFactory;

    @Parameters(index = "0", description = "Account Plan ID", arity = "1")
    public int accountPlanId;

    @Parameters(index = "1", description = "Account Plan Feature ID", arity = "1")
    public int accountPlanFeatureId;
    
    @Override
    public void run() {

        try {
            accountManagementServiceFactory.getAccountManagementService().createAccountPlanFeature(accountPlanId, accountPlanFeatureId);
        } catch (Exception e) {
            spec.commandLine().getOut().println(e.getMessage());
        }
    }
}