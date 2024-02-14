package com.redhat.threescale.toolbox.commands.accounts.features;

import com.redhat.threescale.toolbox.rest.client.service.AccountManagementServiceFactory;

import jakarta.inject.Inject;
import picocli.CommandLine.Command;
import picocli.CommandLine.Model.CommandSpec;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;
import picocli.CommandLine.Spec;


@Command(name="create", mixinStandardHelpOptions = true)
public class AccountFeatureCreateCommand implements Runnable {

    @Spec
    CommandSpec spec;
    
    @Inject
    AccountManagementServiceFactory accountManagementServiceFactory;

    @Parameters(index = "0", description = "name", arity = "1")
    public String name;

    @Option(names = {"--system-name",}, description = "System name")
    public String systemName;

    @Override
    public void run() {

        try {
            accountManagementServiceFactory.getAccountManagementService().createAccountFeature(name, systemName);
        } catch (Exception e) {
            spec.commandLine().getOut().println(e.getMessage());
        }        
    }
}
