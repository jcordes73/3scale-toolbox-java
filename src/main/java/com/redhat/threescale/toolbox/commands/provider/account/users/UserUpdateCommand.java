package com.redhat.threescale.toolbox.commands.provider.account.users;

import org.jboss.logging.Logger;

import com.redhat.threescale.toolbox.rest.client.service.AccountManagementServiceFactory;

import jakarta.inject.Inject;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

@Command(name="update", mixinStandardHelpOptions = true)
public class UserUpdateCommand implements Runnable {

    private static final Logger LOG = Logger.getLogger(UserUpdateCommand.class);

    @Inject
    AccountManagementServiceFactory accountManagementServiceFactory;

    @Parameters(index = "0", description = "User ID", arity = "1")
    private int userId;

    @Option(names = {"--username",}, description = "Username")
    public String userName;

    @Option(names = {"--email",}, description = "Email")
    public String email;

    @Option(names = {"--password",}, description = "Password")
    public String password;
        
    @Override
    public void run() {
        try {
            accountManagementServiceFactory.getAccountManagementService().updateUser(userName, userName, email, password);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }
}