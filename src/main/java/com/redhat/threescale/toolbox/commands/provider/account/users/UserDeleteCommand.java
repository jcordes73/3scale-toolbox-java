package com.redhat.threescale.toolbox.commands.provider.account.users;

import org.jboss.logging.Logger;

import com.redhat.threescale.toolbox.rest.client.service.AccountManagementServiceFactory;

import jakarta.inject.Inject;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

@Command(name="delete", mixinStandardHelpOptions = true)
public class UserDeleteCommand implements Runnable {

    private static final Logger LOG = Logger.getLogger(UserDeleteCommand.class);

    @Inject
    AccountManagementServiceFactory accountManagementServiceFactory;

    @Parameters(index = "0", description = "User ID", arity = "1")
    public int userId;

    @Override
    public void run() {
        try {
            accountManagementServiceFactory.getAccountManagementService().deleteUser(userId);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }
}