package com.redhat.threescale.toolbox.commands.user;

import java.util.Arrays;

import org.jboss.logging.Logger;

import com.redhat.threescale.toolbox.rest.client.service.AccountManagementServiceFactory;

import jakarta.inject.Inject;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;


@Command(name="access-token", mixinStandardHelpOptions = true)
public class UserCreateAccessTokenCommand implements Runnable {

    private static final Logger LOG = Logger.getLogger(UserCreateAccessTokenCommand.class);

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
            LOG.error(e.getMessage(), e);
        }
    }
}