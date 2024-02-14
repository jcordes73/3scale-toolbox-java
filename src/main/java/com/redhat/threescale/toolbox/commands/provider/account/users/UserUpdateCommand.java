package com.redhat.threescale.toolbox.commands.provider.account.users;

import com.redhat.threescale.toolbox.rest.client.service.AccountManagementServiceFactory;

import jakarta.inject.Inject;
import picocli.CommandLine.Command;
import picocli.CommandLine.Model.CommandSpec;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;
import picocli.CommandLine.Spec;

@Command(name="update", mixinStandardHelpOptions = true)
public class UserUpdateCommand implements Runnable {

    @Spec
    CommandSpec spec;
    
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
            spec.commandLine().getOut().println(e.getMessage());
        }
    }
}