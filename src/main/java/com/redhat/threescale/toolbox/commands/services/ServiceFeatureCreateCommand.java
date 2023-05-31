package com.redhat.threescale.toolbox.commands.services;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;

import com.redhat.threescale.toolbox.rest.client.service.AccountManagementService;

import jakarta.inject.Inject;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

@Command(name="create", mixinStandardHelpOptions = true)
public class ServiceFeatureCreateCommand implements Runnable {

    private static final Logger LOG = Logger.getLogger(ServiceFeatureCreateCommand.class);
    
    @Inject
    @RestClient
    AccountManagementService accountManagementService;

    @ConfigProperty(name="access_token")
    private String accessToken;

    @Parameters(index = "0", description = "Service ID", arity = "1")
    public int serviceId;

    @Parameters(index = "1", description = "Name", arity = "1")
    public String name;

    @Option(names = {"--system-name",}, description = "System name")
    public String systemName;

    @Option(names = {"--description",}, description = "Description")
    public String description;
    
    @Option(names = {"--scope",}, description = "Scope. Valid values: ${COMPLETION-CANDIDATES}")
    public AccountManagementService.FeatureScope featureScope;

    @Override
    public void run() {
        try {
            accountManagementService.createServiceFeature(serviceId, accessToken, name, systemName, description, featureScope);

        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }
}
