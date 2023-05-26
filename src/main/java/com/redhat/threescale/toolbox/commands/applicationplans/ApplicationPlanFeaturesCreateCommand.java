package com.redhat.threescale.toolbox.commands.applicationplans;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;

import com.redhat.threescale.toolbox.rest.client.service.AccountManagementService;

import jakarta.inject.Inject;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

@Command(name="create", mixinStandardHelpOptions = true)
public class ApplicationPlanFeaturesCreateCommand implements Runnable {

    private static final Logger LOG = Logger.getLogger(ApplicationPlanFeaturesCreateCommand.class);
    
    @Inject
    @RestClient
    AccountManagementService accountManagementService;

    @ConfigProperty(name="access_token")
    private String accessToken;

    @Parameters(index = "0", description = "Application Plan ID", arity = "1")
    public int applicationPlanId;

    @Parameters(index = "1", description = "Feature ID", arity = "1")
    public int featureId;

    @Override
    public void run() {
        try {
            accountManagementService.createApplicationPlanFeature(applicationPlanId, accessToken, featureId);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }
}