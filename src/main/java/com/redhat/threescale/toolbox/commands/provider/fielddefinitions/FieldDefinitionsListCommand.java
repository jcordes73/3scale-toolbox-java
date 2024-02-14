package com.redhat.threescale.toolbox.commands.provider.fielddefinitions;

import com.redhat.threescale.toolbox.rest.client.service.AccountManagementServiceFactory;

import jakarta.inject.Inject;
import picocli.CommandLine.Command;
import picocli.CommandLine.Model.CommandSpec;
import picocli.CommandLine.Spec;

@Command(name="list", mixinStandardHelpOptions = true)
public class FieldDefinitionsListCommand implements Runnable {

    @Spec
    CommandSpec spec;
    
    @Inject
    AccountManagementServiceFactory accountManagementServiceFactory;

    @Override
    public void run() {
        try {
            String response = accountManagementServiceFactory.getAccountManagementService().getFieldDefinitions();

            spec.commandLine().getOut().println(response);
        } catch (Exception e){
            spec.commandLine().getOut().println(e.getMessage());
        }
    }
}
