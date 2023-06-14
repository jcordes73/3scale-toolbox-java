package com.redhat.threescale.toolbox.commands.accounts.accountplans;

import org.jboss.logging.Logger;

import com.redhat.threescale.toolbox.rest.client.service.AccountManagementServiceFactory;

import jakarta.inject.Inject;
import picocli.CommandLine.Command;
import picocli.CommandLine.Model.CommandSpec;
import picocli.CommandLine.Parameters;
import picocli.CommandLine.Spec;

@Command(name="list", mixinStandardHelpOptions = true)
public class AccountPlanFeatureListCommand implements Runnable {

    private static final Logger LOG = Logger.getLogger(AccountPlanFeatureListCommand.class);

    @Spec
    CommandSpec spec;
    
    @Inject
    AccountManagementServiceFactory accountManagementServiceFactory;

    @Parameters(index = "0", description = "Account Plan ID", arity = "1")
    public int accountPlanId;

    @Override
    public void run() {
        try {
            String response = accountManagementServiceFactory.getAccountManagementService().getAccountPlanFeatures(accountPlanId);

            spec.commandLine().getOut().println(response);
        } catch (Exception e ){
            LOG.error(e.getMessage(), e);
        }
    }
}