package com.redhat.threescale.toolbox.commands.services;

import org.jboss.logging.Logger;

import com.redhat.threescale.toolbox.rest.client.service.AccountManagementServiceFactory;

import jakarta.inject.Inject;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;


@Command(name="delete", mixinStandardHelpOptions = true)
public class ServiceApplicationPlanDeleteCommand implements Runnable {

    private static final Logger LOG = Logger.getLogger(ServiceApplicationPlanDeleteCommand.class);

    @Inject
    AccountManagementServiceFactory accountManagementServiceFactory;

    @Parameters(index = "0", description = "Service ID", arity = "1")
    public int serviceId;

    @Parameters(index = "1", description = "Application Plan ID", arity = "1")
    public int applicationPlanId;
    
    @Override
    public void run() {
        try {
            accountManagementServiceFactory.getAccountManagementService().deleteServiceApplicationPlan(serviceId, applicationPlanId);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }
}