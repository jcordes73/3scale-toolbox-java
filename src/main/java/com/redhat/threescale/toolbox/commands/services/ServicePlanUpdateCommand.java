package com.redhat.threescale.toolbox.commands.services;

import org.jboss.logging.Logger;

import com.redhat.threescale.toolbox.rest.client.service.AccountManagementService;
import com.redhat.threescale.toolbox.rest.client.service.AccountManagementServiceFactory;

import jakarta.inject.Inject;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

@Command(name="update", mixinStandardHelpOptions = true)
public class ServicePlanUpdateCommand implements Runnable {

    private static final Logger LOG = Logger.getLogger(ServicePlanUpdateCommand.class);
    
    @Inject
    AccountManagementServiceFactory accountManagementServiceFactory;

    @Parameters(index = "0", description = "Service ID", arity = "1")
    public int serviceId;

    @Parameters(index = "1", description = "Name", arity = "1")
    public String name;

    @Option(names = {"--approval-required",}, description = "Approval required", defaultValue = Option.NULL_VALUE)
    public Boolean approvalRequired;

    @Option(names = {"--state-even",}, description = "State event. Valid values: ${COMPLETION-CANDIDATES}", defaultValue = Option.NULL_VALUE)
    public AccountManagementService.StateEvent stateEvent;
    
    @Override
    public void run() {
        try {
            accountManagementServiceFactory.getAccountManagementService().updateServicePlan(serviceId, serviceId, name, approvalRequired, stateEvent);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }
}
