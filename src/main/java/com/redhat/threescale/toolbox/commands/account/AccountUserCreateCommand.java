package com.redhat.threescale.toolbox.commands.account;

import org.jboss.logging.Logger;

import com.redhat.threescale.toolbox.rest.client.service.AccountManagementServiceFactory;

import jakarta.inject.Inject;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;


@Command(name="create", mixinStandardHelpOptions = true)
public class AccountUserCreateCommand implements Runnable {

    private static final Logger LOG = Logger.getLogger(AccountUserCreateCommand.class);

    @Inject
    AccountManagementServiceFactory accountManagementServiceFactory;

    @Parameters(index = "0", description = "Account ID", arity = "1")
    public int accountId;

    @Parameters(index = "1", description = "username", arity = "1")
    public String userName;

    @Parameters(index = "2", description = "email", arity = "1")
    public String email;

    @Parameters(index = "3", description = "password", arity = "1")
    public String password;
    
    @Override
    public void run() {

        try {
            accountManagementServiceFactory.getAccountManagementService().createAccountUser(accountId, userName, email, password);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        
    }
}
