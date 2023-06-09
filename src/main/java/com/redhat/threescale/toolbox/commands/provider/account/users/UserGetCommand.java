package com.redhat.threescale.toolbox.commands.provider.account.users;

import org.jboss.logging.Logger;

import com.redhat.threescale.toolbox.rest.client.service.AccountManagementServiceFactory;

import jakarta.inject.Inject;
import picocli.CommandLine.Command;
import picocli.CommandLine.Model.CommandSpec;
import picocli.CommandLine.Parameters;
import picocli.CommandLine.Spec;

@Command(name="get", mixinStandardHelpOptions = true)
public class UserGetCommand implements Runnable {

    private static final Logger LOG = Logger.getLogger(UserGetCommand.class);

    @Spec
    CommandSpec spec;
    
    @Inject
    AccountManagementServiceFactory accountManagementServiceFactory;

    @Parameters(index = "0", description = "User ID", arity = "1")
    private int userId;

    @Override
    public void run() {
        try {
            String response = accountManagementServiceFactory.getAccountManagementService().getUser(userId);

            spec.commandLine().getOut().println(response);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }
}