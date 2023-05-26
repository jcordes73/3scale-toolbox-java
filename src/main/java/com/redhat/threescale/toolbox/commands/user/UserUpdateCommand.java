package com.redhat.threescale.toolbox.commands.user;

import com.redhat.threescale.toolbox.rest.client.service.AccountManagementService;

import jakarta.inject.Inject;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;
import picocli.CommandLine.Option;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;

@Command(name="update", mixinStandardHelpOptions = true)
public class UserUpdateCommand implements Runnable {

    private static final Logger LOG = Logger.getLogger(UserUpdateCommand.class);

    @Inject
    @RestClient
    AccountManagementService accountManagementService;

    @ConfigProperty(name="access_token")
    private String accessToken;

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
            accountManagementService.updateUser(userName, accessToken, userName, email, password);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }
}