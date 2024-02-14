package com.redhat.threescale.toolbox.commands.accounts.features;

import com.redhat.threescale.toolbox.rest.client.service.AccountManagementServiceFactory;

import jakarta.inject.Inject;
import picocli.CommandLine.Command;
import picocli.CommandLine.Model.CommandSpec;
import picocli.CommandLine.Parameters;
import picocli.CommandLine.Spec;

@Command(name="delete", mixinStandardHelpOptions = true)
public class AccountFeatureDeleteCommand implements Runnable {
    
    @Spec
    CommandSpec spec;
    
    @Inject
    AccountManagementServiceFactory accountManagementServiceFactory;

    @Parameters(index = "0", description = "Feature ID", arity = "1")
    public int featureId;

    @Override
    public void run() {
        try {
            accountManagementServiceFactory.getAccountManagementService().deleteAccountFeature(featureId);
        } catch (Exception e){
            spec.commandLine().getOut().println(e.getMessage());
        }
    }
}
