package com.redhat.threescale.toolbox.commands.provider.account.users;

import org.jboss.logging.Logger;

import com.redhat.threescale.toolbox.rest.client.service.AccountManagementServiceFactory;

import jakarta.inject.Inject;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

@Command(name="member", mixinStandardHelpOptions = true)
public class UserMemberCommand implements Runnable {

    private static final Logger LOG = Logger.getLogger(UserMemberCommand.class);

    @Inject
    AccountManagementServiceFactory accountManagementServiceFactory;

    @Parameters(index = "0", description = "User ID", arity = "1")
    private int userId;
        
    @Override
    public void run() {
        try {
            accountManagementServiceFactory.getAccountManagementService().changeUserRoleToAdmin(userId);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }
}