package com.redhat.threescale.toolbox.commands.services.backends;


import com.redhat.threescale.toolbox.rest.client.service.AccountManagementServiceFactory;

import jakarta.inject.Inject;
import picocli.CommandLine.Command;
import picocli.CommandLine.Model.CommandSpec;
import picocli.CommandLine.Parameters;
import picocli.CommandLine.Spec;

@Command(name="delete", mixinStandardHelpOptions = true)
public class BackendMetricMethodDeleteCommand implements Runnable {

    
    
    @Spec
    CommandSpec spec;
    
    @Inject
    AccountManagementServiceFactory accountManagementServiceFactory;

    @Parameters(index = "0", description = "Backend ID", arity = "1")
    public int backendId;

    @Parameters(index = "1", description = "Metric ID", arity = "1")
    public int metricId;

    @Parameters(index = "2", description = "Method ID", arity = "1")
    public int methodId;

    @Override
    public void run() {
        try {
            accountManagementServiceFactory.getAccountManagementService().deleteBackendMetricMethod(backendId, metricId, methodId);
        } catch (Exception e) {
            spec.commandLine().getOut().println(e.getMessage());
        }
    }
}