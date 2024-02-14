package com.redhat.threescale.toolbox.commands.services.applicationplans;


import com.redhat.threescale.toolbox.rest.client.service.AccountManagementServiceFactory;

import jakarta.inject.Inject;
import picocli.CommandLine.Command;
import picocli.CommandLine.Model.CommandSpec;
import picocli.CommandLine.Parameters;
import picocli.CommandLine.Spec;

@Command(name="default", mixinStandardHelpOptions = true)
public class ApplicationPlanDefaultCommand implements Runnable {

    @Spec
    CommandSpec spec;

    @Inject
    AccountManagementServiceFactory accountManagementServiceFactory;

    @Parameters(index = "0", description = "Service ID", arity = "1")
    public int serviceId;

    @Parameters(index = "0", description = "Application Plan ID", arity = "1")
    public int applicationPlanId;

    @Parameters(index = "1", description = "Application Plan Name", arity = "1")
    public String applicationPlanName;
    
    @Override
    public void run() {
        try {
            accountManagementServiceFactory.getAccountManagementService().defaultApplicationPlan(serviceId, applicationPlanId);
        } catch (Exception e) {
            spec.commandLine().getOut().println(e.getMessage());
        }
    }
}