package com.redhat.threescale.toolbox.commands.user;

import java.util.Arrays;

import com.redhat.threescale.toolbox.rest.client.service.AccountManagementService;

import jakarta.inject.Inject;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;
import picocli.CommandLine.Option;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;

@Command(name="update", mixinStandardHelpOptions = true)
public class UserPermissionsUpdateCommand implements Runnable {

    private static final Logger LOG = Logger.getLogger(UserPermissionsUpdateCommand.class);

    @Inject
    @RestClient
    AccountManagementService accountManagementService;

    @ConfigProperty(name="access_token")
    private String accessToken;

    @Parameters(index = "0", description = "User ID", arity = "1")
    private int userId;

    @Option(names = {"--service-ids",}, description = "Allowed Service IDs")
    public String allowedServiceIds;

    @Option(names = {"--sections",}, description = "Allowed Sections")
    public String allowedSections;
        
    @Override
    public void run() {
        try {
            accountManagementService.updateUserPermissions(userId, accessToken, Arrays.asList(allowedServiceIds.split(",")), Arrays.asList(allowedSections.split(",")));
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }   
    }
}