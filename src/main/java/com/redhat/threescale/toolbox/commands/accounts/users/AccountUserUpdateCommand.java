package com.redhat.threescale.toolbox.commands.accounts.users;

import org.jboss.logging.Logger;

import com.redhat.threescale.toolbox.rest.client.service.AccountManagementServiceFactory;

import jakarta.inject.Inject;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;


@Command(name="update", mixinStandardHelpOptions = true)
public class AccountUserUpdateCommand implements Runnable {

    private static final Logger LOG = Logger.getLogger(AccountUserUpdateCommand.class);

    @Inject
    AccountManagementServiceFactory accountManagementServiceFactory;

    @Parameters(index = "0", description = "Account ID", arity = "1")
    public int accountId;

    @Parameters(index = "0", description = "User ID", arity = "1")
    public int userId;

    @Option(names = {"--username"}, description = "Username", arity = "1")
    public String userName;

    @Option(names = {"--email"}, description = "Email", arity = "1")
    public String email;

    @Option(names = {"--password"}, description = "Password", arity = "1")
    public String password;
    
    @Override
    public void run() {

        try {
            accountManagementServiceFactory.getAccountManagementService().updateAccountUser(accountId, userId, userName, email, password);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        
    }
}
