package com.redhat.threescale.toolbox.commands.services.proxy;

import com.redhat.threescale.toolbox.rest.client.service.AccountManagementService;
import com.redhat.threescale.toolbox.rest.client.service.AccountManagementServiceFactory;

import jakarta.inject.Inject;
import picocli.CommandLine.Command;
import picocli.CommandLine.Model.CommandSpec;
import picocli.CommandLine.Parameters;
import picocli.CommandLine.Spec;

@Command(name="promote", mixinStandardHelpOptions = true)
public class ServiceProxyConfigVersionPromoteCommand implements Runnable {

    @Spec
    CommandSpec spec;
    
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
            spec.commandLine().getOut().println(e.getMessage());
        }
    }
}