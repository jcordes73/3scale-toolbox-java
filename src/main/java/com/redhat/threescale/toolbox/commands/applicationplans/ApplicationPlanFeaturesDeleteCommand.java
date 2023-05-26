package com.redhat.threescale.toolbox.commands.applicationplans;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;

import com.redhat.threescale.toolbox.rest.client.service.AccountManagementService;

import jakarta.inject.Inject;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

@Command(name="delete", mixinStandardHelpOptions = true)
public class ApplicationPlanFeaturesDeleteCommand implements Runnable {

    private static final Logger LOG = Logger.getLogger(ApplicationPlanFeaturesDeleteCommand.class);

    @Inject
    @RestClient
    AccountManagementService accountManagementService;

    @ConfigProperty(name="access_token")
    private String accessToken;

    @Parameters(index = "0", description = "Account Plan ID", arity = "1")
    public int applicationPlanId;

    @Parameters(index = "1", description = "Feature ID", arity = "1")
    public int featureId;

    @Override
    public void run() {
        try {
            accountManagementService.deleteApplicationPlanFeature(applicationPlanId, featureId, accessToken);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }
}
