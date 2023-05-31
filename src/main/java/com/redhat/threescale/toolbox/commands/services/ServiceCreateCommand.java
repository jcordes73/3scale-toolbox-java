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
public class ServiceCreateCommand implements Runnable {

    private static final Logger LOG = Logger.getLogger(ServiceCreateCommand.class);
    
    @Inject
    @RestClient
    AccountManagementService accountManagementService;

    @ConfigProperty(name="access_token")
    private String accessToken;

    @Parameters(index = "0", description = "Name", arity = "1")
    public String name;

    @Option(names = {"--description",}, description = "Description")
    public String description;

    @Option(names = {"--deployment-option",}, description = "Deployment option")
    public AccountManagementService.DeploymentOption deploymentOption;

    @Option(names = {"--backend-version",}, description = "Backend version")
    public String authenticationMode;

    @Option(names = {"--system-name",}, description = "System name")
    public String systemName;

    @Override
    public void run() {
        try {
            accountManagementService.createService(accessToken, name, description, deploymentOption, authenticationMode, systemName);

        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }
}
