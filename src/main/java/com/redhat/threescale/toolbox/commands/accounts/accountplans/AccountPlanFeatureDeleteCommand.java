package com.redhat.threescale.toolbox.commands.accounts.accountplans;

import org.jboss.logging.Logger;

import com.redhat.threescale.toolbox.rest.client.service.AccountManagementServiceFactory;

import jakarta.inject.Inject;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;


@Command(name="delete", mixinStandardHelpOptions = true)
public class AccountPlanFeatureDeleteCommand implements Runnable {

    private static final Logger LOG = Logger.getLogger(AccountPlanFeatureDeleteCommand.class);

    @Inject
    AccountManagementServiceFactory accountManagementServiceFactory;

    @Parameters(index = "0", description = "Account Plan ID", arity = "1")
    public int accountPlanId;

    @Parameters(index = "1", description = "Account Plan Feature ID", arity = "1")
    public int accountPlanFeatureId;
    
    @Override
    public void run() {

        try {
            accountManagementServiceFactory.getAccountManagementService().deleteAccountPlanFeature(accountPlanId, accountPlanFeatureId);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }

}