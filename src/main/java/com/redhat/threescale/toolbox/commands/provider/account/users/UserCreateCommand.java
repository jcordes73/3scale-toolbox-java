package com.redhat.threescale.toolbox.commands.provider.account.users;

import com.redhat.threescale.toolbox.rest.client.service.AccountManagementServiceFactory;

import jakarta.inject.Inject;
import picocli.CommandLine.Command;
import picocli.CommandLine.Model.CommandSpec;
import picocli.CommandLine.Parameters;
import picocli.CommandLine.Spec;

@Command(name="create", mixinStandardHelpOptions = true)
public class UserCreateCommand implements Runnable {

    @Spec
    CommandSpec spec;
    
    @Inject
    AccountManagementServiceFactory accountManagementServiceFactory;

    @Parameters(index = "0", description = "Username", arity = "1")
    private String userName;

    @Parameters(index = "1", description = "Email", arity = "1")
    private String email;

    @Parameters(index = "2", description = "Password", arity = "1")
    private String password;

    @Override
    public void run() {
        try {
            accountManagementServiceFactory.getAccountManagementService().createUser(userName, email, password);
        } catch (Exception e) {
            spec.commandLine().getOut().println(e.getMessage());
        }
    }
}