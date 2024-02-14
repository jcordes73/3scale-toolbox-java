package com.redhat.threescale.toolbox.commands.accounts.applications.keys;

import com.redhat.threescale.toolbox.rest.client.service.AccountManagementServiceFactory;

import jakarta.inject.Inject;
import picocli.CommandLine.Command;
import picocli.CommandLine.Model.CommandSpec;
import picocli.CommandLine.Parameters;
import picocli.CommandLine.Spec;

@Command(name="create", mixinStandardHelpOptions = true)
public class ApplicationKeyCreateCommand implements Runnable {

    @Spec
    CommandSpec spec;
    
    @Inject
    AccountManagementServiceFactory accountManagementServiceFactory;

    @Parameters(index = "0", description = "Account ID", arity = "1")
    private int accountId;

    @Parameters(index = "1", description = "Application ID", arity = "1")
    private int applicationId;

    @Parameters(index = "2", description = "Key", arity = "1")
    private String key;
    
    @Override
    public void run() {
        try {
            accountManagementServiceFactory.getAccountManagementService().createApplicationKey(accountId, applicationId, key);           
        } catch (Exception e) {
            spec.commandLine().getOut().println(e.getMessage());
        }
    }
}