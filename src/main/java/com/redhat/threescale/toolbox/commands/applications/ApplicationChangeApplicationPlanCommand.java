package com.redhat.threescale.toolbox.commands.applications;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;

import com.redhat.threescale.toolbox.rest.client.service.AccountManagementService;

import jakarta.inject.Inject;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

@Command(name="change", mixinStandardHelpOptions = true)
public class ApplicationChangeApplicationPlanCommand implements Runnable {

    private static final Logger LOG = Logger.getLogger(ApplicationChangeApplicationPlanCommand.class);

    @Inject
    @RestClient
    AccountManagementService accountManagementService;

    @ConfigProperty(name="access_token")
    private String accessToken;

    @Parameters(index = "0", description = "Account ID", arity = "1")
    private int accountId;

    @Parameters(index = "1", description = "Application ID", arity = "1")
    private int applicationId;

    @Parameters(index = "2", description = "Application Plan ID", arity = "1")
    private int applicationPlanId;
    
    @Override
    public void run() {
        try {
            accountManagementService.changeApplicationPlan(accountId, applicationId, accessToken, applicationPlanId);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }
}
