package com.redhat.threescale.toolbox.commands.services;

import com.redhat.threescale.toolbox.rest.client.service.AccountManagementService;

import jakarta.inject.Inject;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;


@Command(name="delete", mixinStandardHelpOptions = true)
public class ServiceApplicationPlanDeleteCommand implements Runnable {

    private static final Logger LOG = Logger.getLogger(ServiceApplicationPlanDeleteCommand.class);

    @Inject
    @RestClient
    AccountManagementService accountManagementService;

    @ConfigProperty(name="access_token")
    private String accessToken;

    @Parameters(index = "0", description = "Service ID", arity = "1")
    public int serviceId;

    @Parameters(index = "1", description = "Application Plan ID", arity = "1")
    public int applicationPlanId;
    
    @Override
    public void run() {
        try {
            accountManagementService.deleteServiceApplicationPlan(serviceId, applicationPlanId, accessToken);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }
}