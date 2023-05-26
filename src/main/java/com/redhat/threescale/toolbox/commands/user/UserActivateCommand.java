package com.redhat.threescale.toolbox.commands.user;

import com.redhat.threescale.toolbox.rest.client.service.AccountManagementService;

import jakarta.inject.Inject;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;


@Command(name="activate", mixinStandardHelpOptions = true)
public class UserActivateCommand implements Runnable {

    private static final Logger LOG = Logger.getLogger(UserActivateCommand.class);

    @Inject
    @RestClient
    AccountManagementService accountManagementService;

    @ConfigProperty(name="access_token")
    private String accessToken;

    @Parameters(index = "0", description = "User ID", arity = "1")
    private int userId;
        
    @Override
    public void run() {
        try {
            accountManagementService.activateUser(userId, accessToken);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        
    }
}