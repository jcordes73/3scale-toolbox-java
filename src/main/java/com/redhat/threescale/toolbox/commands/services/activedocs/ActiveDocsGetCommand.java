package com.redhat.threescale.toolbox.commands.services.activedocs;

import com.redhat.threescale.toolbox.rest.client.service.AccountManagementServiceFactory;

import jakarta.inject.Inject;
import picocli.CommandLine.Command;
import picocli.CommandLine.Model.CommandSpec;
import picocli.CommandLine.Parameters;
import picocli.CommandLine.Spec;

@Command(name="get", mixinStandardHelpOptions = true)
public class ActiveDocsGetCommand implements Runnable { 
    
    @Spec
    CommandSpec spec;
    
    @Inject
    AccountManagementServiceFactory accountManagementServiceFactory;
    
    @Parameters(index = "0", description = "Activedoc ID", arity = "1")
    public int activeDocId;

    @Override
    public void run() {
        try {
            String response = accountManagementServiceFactory.getAccountManagementService().getActiveDoc(activeDocId);

            spec.commandLine().getOut().println(response);
        } catch (Exception e) {
            spec.commandLine().getOut().println(e.getMessage());
        }
    }
}