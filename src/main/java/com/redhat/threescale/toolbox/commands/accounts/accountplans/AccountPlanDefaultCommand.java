package com.redhat.threescale.toolbox.commands.accounts.accountplans;

import org.jboss.logging.Logger;

import com.redhat.threescale.toolbox.rest.client.service.AccountManagementServiceFactory;

import jakarta.inject.Inject;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

@Command(name="default", mixinStandardHelpOptions = true)
public class AccountPlanDefaultCommand implements Runnable {

    private static final Logger LOG = Logger.getLogger(AccountPlanDefaultCommand.class);
    
    @Inject
    AccountManagementServiceFactory accountManagementServiceFactory;

    @Parameters(index = "0", description = "Account Plan ID", arity = "1")
    public int accountPlanId;

    @Override
    public void run() {
        try {
            accountManagementServiceFactory.getAccountManagementService().accountPlanDefault(accountPlanId);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }
}