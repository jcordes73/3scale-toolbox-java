package com.redhat.threescale.toolbox.commands.account;

import org.jboss.logging.Logger;

import com.redhat.threescale.toolbox.rest.client.service.AccountManagementServiceFactory;

import jakarta.inject.Inject;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;


@Command(name="admin", mixinStandardHelpOptions = true)
public class AccountUserAdminCommand implements Runnable {

    private static final Logger LOG = Logger.getLogger(AccountUserAdminCommand.class);

    @Inject
    AccountManagementServiceFactory accountManagementServiceFactory;

    @Parameters(index = "0", description = "Account ID", arity = "1")
    public int accountId;

    @Parameters(index = "1", description = "User ID", arity = "1")
    public int  userId;
    
    @Override
    public void run() {

        try {
            accountManagementServiceFactory.getAccountManagementService().changeAccountUserRoleToAdmin(accountId, userId);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        
    }
}
