package com.redhat.threescale.toolbox.commands.services;

import org.jboss.logging.Logger;

import com.redhat.threescale.toolbox.rest.client.service.AccountManagementService;
import com.redhat.threescale.toolbox.rest.client.service.AccountManagementServiceFactory;

import jakarta.inject.Inject;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

@Command(name="update", mixinStandardHelpOptions = true)
public class ServiceUpdateCommand implements Runnable {

    private static final Logger LOG = Logger.getLogger(ServiceUpdateCommand.class);
    
    @Inject
    AccountManagementServiceFactory accountManagementServiceFactory;

    @Parameters(index = "0", description = "Service ID", arity = "1")
    public int serviceId;

    @Option(names = {"--name",}, description = "Name")
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
            accountManagementServiceFactory.getAccountManagementService().updateService(serviceId, name, description, deploymentOption, authenticationMode, systemName);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }
}
