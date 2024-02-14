package com.redhat.threescale.toolbox.commands.services.serviceplans;

import com.redhat.threescale.toolbox.rest.client.service.AccountManagementServiceFactory;

import jakarta.inject.Inject;
import picocli.CommandLine.Command;
import picocli.CommandLine.Model.CommandSpec;
import picocli.CommandLine.Parameters;
import picocli.CommandLine.Spec;

@Command(name="default", mixinStandardHelpOptions = true)
public class ServicePlanDefaultCommand implements Runnable {

    
    
    @Spec
    CommandSpec spec;
    
    @Inject
    AccountManagementServiceFactory accountManagementServiceFactory;

    @Parameters(index = "0", description = "Service ID", arity = "1")
    public int serviceId;

    @Parameters(index = "1", description = "Name", arity = "1")
    public String name;
    
    @Override
    public void run() {
        try {
            accountManagementServiceFactory.getAccountManagementService().defaultServicePlan(serviceId, serviceId);
        } catch (Exception e) {
            spec.commandLine().getOut().println(e.getMessage());
        }
    }
}