package com.redhat.threescale.toolbox.commands.services.proxy;

import java.nio.file.Files;
import java.nio.file.Paths;


import com.redhat.threescale.toolbox.rest.client.service.AccountManagementServiceFactory;

import jakarta.inject.Inject;
import picocli.CommandLine.Command;
import picocli.CommandLine.Model.CommandSpec;
import picocli.CommandLine.Parameters;
import picocli.CommandLine.Spec;

@Command(name="update", mixinStandardHelpOptions = true)
public class ServiceProxyPoliciesUpdateCommand implements Runnable {

    @Spec
    CommandSpec spec;
    
    @Inject
    AccountManagementServiceFactory accountManagementServiceFactory;

    @Parameters(index = "0", description = "Service ID", arity = "1")
    public int serviceId;

    @Parameters(index = "1", description = "Policies Config File", arity = "1")
    public String policiesConfigFile;

    @Override
    public void run() {
        try {
            String policiesConfig = Files.readString(Paths.get(policiesConfigFile));

            accountManagementServiceFactory.getAccountManagementService().updateServiceProxyPolicies(serviceId, policiesConfig);
        } catch (Exception e) {
            spec.commandLine().getOut().println(e.getMessage());
        }
    }
}