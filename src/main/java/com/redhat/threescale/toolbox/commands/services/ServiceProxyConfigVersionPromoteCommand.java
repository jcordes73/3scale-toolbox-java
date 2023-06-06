package com.redhat.threescale.toolbox.commands.services;

import org.jboss.logging.Logger;

import com.redhat.threescale.toolbox.rest.client.service.AccountManagementService;
import com.redhat.threescale.toolbox.rest.client.service.AccountManagementServiceFactory;

import jakarta.inject.Inject;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

@Command(name="promote", mixinStandardHelpOptions = true)
public class ServiceProxyConfigVersionPromoteCommand implements Runnable {

    private static final Logger LOG = Logger.getLogger(ServiceProxyConfigVersionPromoteCommand.class);

    @Inject
    AccountManagementServiceFactory accountManagementServiceFactory;

    @Parameters(index = "0", description = "Service ID", arity = "1")
    public int serviceId;

    @Parameters(index = "1", description = "Environment. Valid values: ${COMPLETION-CANDIDATES}", arity = "1")
    public AccountManagementService.Environment environment;

    @Parameters(index = "2", description = "Environment to promote to. Valid values: ${COMPLETION-CANDIDATES}", arity = "1")
    public AccountManagementService.Environment toEnvironment;

    @Parameters(index = "3", description = "Version", arity = "1")
    public String version;

    @Override
    public void run() {
        try {
            accountManagementServiceFactory.getAccountManagementService().promoteServiceProxyConfigVersion(serviceId, environment, version, toEnvironment);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }
}