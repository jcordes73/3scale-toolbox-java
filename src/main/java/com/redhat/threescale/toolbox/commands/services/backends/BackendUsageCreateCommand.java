package com.redhat.threescale.toolbox.commands.services.backends;


import com.redhat.threescale.toolbox.rest.client.service.AccountManagementServiceFactory;

import jakarta.inject.Inject;
import picocli.CommandLine.Command;
import picocli.CommandLine.Model.CommandSpec;
import picocli.CommandLine.Parameters;
import picocli.CommandLine.Spec;


@Command(name="create", mixinStandardHelpOptions = true)
public class BackendUsageCreateCommand implements Runnable {    @Spec
    CommandSpec spec;

    @Inject
    AccountManagementServiceFactory accountManagementServiceFactory;

    @Parameters(index = "0", description = "Service ID", arity = "1")
    public int serviceId;

    @Parameters(index = "1", description = "Backend ID", arity = "1")
    public int backendId;

    @Parameters(index = "2", description = "Path", arity = "1")
    public String path;

    @Override
    public void run() {
        try {
            accountManagementServiceFactory.getAccountManagementService().createBackendUsage(serviceId, backendId, path);
        } catch (Exception e) {
            spec.commandLine().getOut().println(e.getMessage());
        }        
    }
}