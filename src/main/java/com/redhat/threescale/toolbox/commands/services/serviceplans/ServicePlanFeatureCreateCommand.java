package com.redhat.threescale.toolbox.commands.services.serviceplans;

import org.jboss.logging.Logger;

import com.redhat.threescale.toolbox.rest.client.service.AccountManagementServiceFactory;

import jakarta.inject.Inject;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

@Command(name="create", mixinStandardHelpOptions = true)
public class ServicePlanFeatureCreateCommand implements Runnable {

    private static final Logger LOG = Logger.getLogger(ServicePlanFeatureCreateCommand.class);
    
    @Inject
    AccountManagementServiceFactory accountManagementServiceFactory;

    @Parameters(index = "0", description = "Service Plan ID", arity = "1")
    private int servicePlanId;

    @Parameters(index = "1", description = "Feature ID", arity = "1")
    private int featureId;

    @Override
    public void run() {
        try {
            accountManagementServiceFactory.getAccountManagementService().createServicePlanFeature(servicePlanId, featureId);
        } catch (Exception e){
            LOG.error(e.getMessage(), e);
        }
    }
}