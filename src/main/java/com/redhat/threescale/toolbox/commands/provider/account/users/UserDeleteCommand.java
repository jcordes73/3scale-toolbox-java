package com.redhat.threescale.toolbox.commands.provider.account.users;

import com.redhat.threescale.toolbox.rest.client.service.AccountManagementServiceFactory;

import jakarta.inject.Inject;
import picocli.CommandLine.Command;
import picocli.CommandLine.Model.CommandSpec;
import picocli.CommandLine.Parameters;
import picocli.CommandLine.Spec;

@Command(name="delete", mixinStandardHelpOptions = true)
public class UserDeleteCommand implements Runnable {

    @Spec
    CommandSpec spec;
    
    @Inject
    AccountManagementServiceFactory accountManagementServiceFactory;

    @Parameters(index = "0", description = "User ID", arity = "1")
    public int userId;

    @Override
    public void run() {
        try {
            accountManagementServiceFactory.getAccountManagementService().deleteUser(userId);
        } catch (Exception e) {
            spec.commandLine().getOut().println(e.getMessage());
        }
    }
}