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
public class ServicePlanCreateCommand implements Runnable {

    private static final Logger LOG = Logger.getLogger(ServicePlanCreateCommand.class);
    
    @Inject
    @RestClient
    AccountManagementService accountManagementService;

    @ConfigProperty(name="access_token")
    private String accessToken;

    @Parameters(index = "0", description = "Service ID", arity = "1")
    public int serviceId;

    @Parameters(index = "1", description = "Name", arity = "1")
    public String name;

    @Option(names = {"--approval-required",}, description = "Approval required")
    public boolean approvalRequired;

    @Option(names = {"--system-name",}, description = "System name")
    public String systemName;

    @Option(names = {"--state-even",}, description = "State event. Valid values: ${COMPLETION-CANDIDATES}")
    public AccountManagementService.StateEvent stateEvent;
    
    @Override
    public void run() {
        try {
            accountManagementService.createServicePlan(serviceId, accessToken, systemName, null, name, stateEvent);

        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }
}
