package com.redhat.threescale.toolbox.commands.user;

import java.util.Arrays;

import com.redhat.threescale.toolbox.rest.client.service.AccountManagementService;

import jakarta.inject.Inject;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;


@Command(name="access-token", mixinStandardHelpOptions = true)
public class UserCreateAccessTokenCommand implements Runnable {

    private static final Logger LOG = Logger.getLogger(UserCreateAccessTokenCommand.class);

    @Inject
    @RestClient
    AccountManagementService accountManagementService;

    @ConfigProperty(name="access_token")
    private String accessToken;

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
            accountManagementService.createAccessToken(scopes, accessToken, name, permission, Arrays.asList(scopes.split(",")));
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }
}