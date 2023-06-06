package com.redhat.threescale.toolbox.commands.account;

import org.jboss.logging.Logger;

import com.redhat.threescale.toolbox.rest.client.service.AccountManagementServiceFactory;

import jakarta.inject.Inject;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;


@Command(name="message", mixinStandardHelpOptions = true)
public class AccountMessageCommand implements Runnable {

    private static final Logger LOG = Logger.getLogger(AccountMessageCommand.class);

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
            LOG.error(e.getMessage(), e);
        }
        
    }
}
