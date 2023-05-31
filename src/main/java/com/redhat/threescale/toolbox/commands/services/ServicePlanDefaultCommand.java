package com.redhat.threescale.toolbox.commands.services;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;

import com.redhat.threescale.toolbox.rest.client.service.AccountManagementService;

import jakarta.inject.Inject;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

@Command(name="default", mixinStandardHelpOptions = true)
public class ServicePlanDefaultCommand implements Runnable {

    private static final Logger LOG = Logger.getLogger(ServicePlanDefaultCommand.class);
    
    @Inject
    @RestClient
    AccountManagementService accountManagementService;

    @ConfigProperty(name="access_token")
    private String accessToken;

    @Parameters(index = "0", description = "Service ID", arity = "1")
    public int serviceId;

    @Parameters(index = "1", description = "Name", arity = "1")
    public String name;
    
    @Override
    public void run() {
        try {
            accountManagementService.defaultServicePlan(serviceId, serviceId, accessToken);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }
}