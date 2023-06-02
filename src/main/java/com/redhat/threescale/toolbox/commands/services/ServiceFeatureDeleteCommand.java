package com.redhat.threescale.toolbox.commands.services;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;

import com.redhat.threescale.toolbox.rest.client.service.AccountManagementService;

import jakarta.inject.Inject;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;
import picocli.CommandLine.Spec;
import picocli.CommandLine.Model.CommandSpec;

@Command(name="delete", mixinStandardHelpOptions = true)
public class ServiceFeatureDeleteCommand implements Runnable {

    private static final Logger LOG = Logger.getLogger(ServiceFeatureDeleteCommand.class);
 
    @Spec
    CommandSpec spec;
    
    @Inject
    @RestClient
    AccountManagementService accountManagementService;
    
    @ConfigProperty(name="access_token")
    private String accessToken;
    
    @Parameters(index = "0", description = "Service ID", arity = "1")
    public int serviceId;

    @Parameters(index = "1", description = "Service Feature ID", arity = "1")
    public int serviceFeatureId;

    @Override
    public void run() {
        try {
            accountManagementService.deleteServiceFeature(serviceId, serviceFeatureId, accessToken);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }
}