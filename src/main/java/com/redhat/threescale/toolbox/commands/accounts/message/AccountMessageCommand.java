package com.redhat.threescale.toolbox.commands.accounts.message;

import com.redhat.threescale.toolbox.rest.client.service.AccountManagementServiceFactory;

import jakarta.inject.Inject;
import picocli.CommandLine.Command;
import picocli.CommandLine.Model.CommandSpec;
import picocli.CommandLine.Parameters;
import picocli.CommandLine.Spec;


@Command(name="message", mixinStandardHelpOptions = true)
public class AccountMessageCommand implements Runnable {

    @Spec
    CommandSpec spec;
    
    @Inject
    AccountManagementServiceFactory accountManagementServiceFactory;

    @Parameters(index = "0", description = "Account ID", arity = "1")
    public int accountId;

    @Parameters(index = "1", description = "Subject", arity = "1")
    public String subject;

    @Parameters(index = "2", description = "Body", arity = "1")
    public String body;

    @Override
    public void run() {

        try {
            accountManagementServiceFactory.getAccountManagementService().createAccountMessage(accountId, subject, body);
        } catch (Exception e) {
            spec.commandLine().getOut().println(e.getMessage());
        }
        
    }
}
