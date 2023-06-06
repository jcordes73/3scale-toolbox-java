package com.redhat.threescale.toolbox.commands.applicationplans;

import org.jboss.logging.Logger;

import com.redhat.threescale.toolbox.rest.client.service.AccountManagementServiceFactory;

import jakarta.inject.Inject;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

@Command(name="delete", mixinStandardHelpOptions = true)
public class ApplicationPlanFeaturesDeleteCommand implements Runnable {

    private static final Logger LOG = Logger.getLogger(ApplicationPlanFeaturesDeleteCommand.class);

    @Inject
    AccountManagementServiceFactory accountManagementServiceFactory;

    @Parameters(index = "0", description = "Account Plan ID", arity = "1")
    public int applicationPlanId;

    @Parameters(index = "1", description = "Feature ID", arity = "1")
    public int featureId;

    @Override
    public void run() {
        try {
            accountManagementServiceFactory.getAccountManagementService().deleteApplicationPlanFeature(applicationPlanId, featureId);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }
}
