package com.redhat.threescale.toolbox.commands.accounts;

import org.jboss.logging.Logger;

import com.redhat.threescale.toolbox.rest.client.service.AccountManagementServiceFactory;

import jakarta.inject.Inject;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

@Command(name="reject", mixinStandardHelpOptions = true)
public class AccountRejectCommand implements Runnable {

    private static final Logger LOG = Logger.getLogger(AccountRejectCommand.class);
    
    @Inject
    AccountManagementServiceFactory accountManagementServiceFactory;
    
    @Parameters(index = "0", description = "Account ID", arity = "1")
    public int accountId;

    @Override
    public void run() {
        try {
            accountManagementServiceFactory.getAccountManagementService().accountReject(accountId);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }
}
