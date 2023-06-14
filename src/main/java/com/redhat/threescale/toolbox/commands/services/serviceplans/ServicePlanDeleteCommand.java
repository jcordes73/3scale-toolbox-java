package com.redhat.threescale.toolbox.commands.services.serviceplans;

import org.jboss.logging.Logger;

import com.redhat.threescale.toolbox.rest.client.service.AccountManagementServiceFactory;

import jakarta.inject.Inject;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

@Command(name="delete", mixinStandardHelpOptions = true)
public class ServicePlanDeleteCommand implements Runnable {

    private static final Logger LOG = Logger.getLogger(ServicePlanDeleteCommand.class);
     
    @Inject
    AccountManagementServiceFactory accountManagementServiceFactory;

    @Parameters(index = "0", description = "Service ID", arity = "1")
    public int serviceId;

    @Parameters(index = "1", description = "Service Plan ID", arity = "1")
    public int servicePlanId;

    @Override
    public void run() {
        try {
            accountManagementServiceFactory.getAccountManagementService().deleteServicePlan(serviceId, servicePlanId);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }
}