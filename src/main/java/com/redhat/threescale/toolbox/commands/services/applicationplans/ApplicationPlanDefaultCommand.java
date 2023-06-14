package com.redhat.threescale.toolbox.commands.services.applicationplans;

import org.jboss.logging.Logger;

import com.redhat.threescale.toolbox.rest.client.service.AccountManagementServiceFactory;

import jakarta.inject.Inject;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

@Command(name="default", mixinStandardHelpOptions = true)
public class ApplicationPlanDefaultCommand implements Runnable {

    private static final Logger LOG = Logger.getLogger(ApplicationPlanDefaultCommand.class);

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
            LOG.error(e.getMessage(), e);
        }
    }
}