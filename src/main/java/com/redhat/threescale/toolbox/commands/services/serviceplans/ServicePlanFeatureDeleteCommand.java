package com.redhat.threescale.toolbox.commands.services.serviceplans;

import com.redhat.threescale.toolbox.rest.client.service.AccountManagementServiceFactory;

import jakarta.inject.Inject;
import picocli.CommandLine.Command;
import picocli.CommandLine.Model.CommandSpec;
import picocli.CommandLine.Parameters;
import picocli.CommandLine.Spec;

@Command(name="delete", mixinStandardHelpOptions = true)
public class ServicePlanFeatureDeleteCommand implements Runnable {

    
    
    @Spec
    CommandSpec spec;
    
    @Inject
    AccountManagementServiceFactory accountManagementServiceFactory;

    @Parameters(index = "0", description = "Service Plan ID", arity = "1")
    private int servicePlanId;

    @Parameters(index = "1", description = "Feature ID", arity = "1")
    private int featureId;

    @Override
    public void run() {
        try {
            accountManagementServiceFactory.getAccountManagementService().deleteServicePlanFeature(servicePlanId, featureId);
        } catch (Exception e){
            spec.commandLine().getOut().println(e.getMessage());
        }
    }
}