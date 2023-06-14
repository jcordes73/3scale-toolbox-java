package com.redhat.threescale.toolbox.commands.accounts.accountplans;

import org.jboss.logging.Logger;

import com.redhat.threescale.toolbox.commands.accounts.AccountCreateCommand;
import com.redhat.threescale.toolbox.rest.client.service.AccountManagementServiceFactory;

import jakarta.inject.Inject;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

@Command(name="change", mixinStandardHelpOptions = true)
public class AccountPlanChangeCommand implements Runnable {
    private static final Logger LOG = Logger.getLogger(AccountCreateCommand.class);
    
    @Inject
    AccountManagementServiceFactory accountManagementServiceFactory;

    @Parameters(index = "0", description = "Account ID", arity = "1")
    public int accountId;

    @Parameters(index = "1", description = "Plan ID", arity = "0..1")
    public int planId;

    @Override
    public void run() {
        try {
            accountManagementServiceFactory.getAccountManagementService().accountChangePlan(accountId, planId);
        } catch (Exception e){
            LOG.error(e.getMessage(), e);
        }
    }
}
