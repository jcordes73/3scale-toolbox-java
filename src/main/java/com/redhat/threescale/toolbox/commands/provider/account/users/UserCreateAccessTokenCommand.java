package com.redhat.threescale.toolbox.commands.provider.account.users;

import java.util.Arrays;

import com.redhat.threescale.toolbox.rest.client.service.AccountManagementServiceFactory;

import jakarta.inject.Inject;
import picocli.CommandLine.Command;
import picocli.CommandLine.Model.CommandSpec;
import picocli.CommandLine.Parameters;
import picocli.CommandLine.Spec;


@Command(name="access-token", mixinStandardHelpOptions = true)
public class UserCreateAccessTokenCommand implements Runnable {

    @Spec
    CommandSpec spec;
    
    @Inject
    AccountManagementServiceFactory accountManagementServiceFactory;

    @Parameters(index = "0", description = "User ID", arity = "1")
    private int userId;

    @Parameters(index = "1", description = "Name", arity = "1")
    private String name;

    @Parameters(index = "2", description = "Permission", arity = "1")
    private String permission;

    @Parameters(index = "3", description = "Scopes", arity = "1")
    private String scopes;
        
    @Override
    public void run() {
        try {
            accountManagementServiceFactory.getAccountManagementService().createAccessToken(scopes, name, permission, Arrays.asList(scopes.split(",")));
        } catch (Exception e) {
            spec.commandLine().getOut().println(e.getMessage());
        }
    }
}