package com.redhat.threescale.toolbox.commands.services.serviceplans;

import org.jboss.logging.Logger;

import com.redhat.threescale.toolbox.rest.client.service.AccountManagementServiceFactory;

import jakarta.inject.Inject;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

@Command(name="default", mixinStandardHelpOptions = true)
public class ServicePlanDefaultCommand implements Runnable {

    private static final Logger LOG = Logger.getLogger(ServicePlanDefaultCommand.class);
    
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
            LOG.error(e.getMessage(), e);
        }
    }
}