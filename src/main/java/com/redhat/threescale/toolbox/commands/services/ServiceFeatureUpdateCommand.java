package com.redhat.threescale.toolbox.commands.services;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;

import com.redhat.threescale.toolbox.rest.client.service.AccountManagementService;

import jakarta.inject.Inject;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

@Command(name="update", mixinStandardHelpOptions = true)
public class ServiceFeatureUpdateCommand implements Runnable {

    private static final Logger LOG = Logger.getLogger(ServiceFeatureUpdateCommand.class);
    
    @Inject
    @RestClient
    AccountManagementService accountManagementService;

    @ConfigProperty(name="access_token")
    private String accessToken;

    @Parameters(index = "0", description = "Service ID", arity = "1")
    public int serviceId;

    @Parameters(index = "1", description = "Service FeatureID", arity = "1")
    public int serviceFeatureId;

    @Option(names = {"--name",}, description = "Name")
    public String name;

    @Option(names = {"--description",}, description = "Description")
    public String description;
    
    @Override
    public void run() {
        try {
            accountManagementService.updateServiceFeature(serviceId, serviceFeatureId, accessToken, name, description);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }
}