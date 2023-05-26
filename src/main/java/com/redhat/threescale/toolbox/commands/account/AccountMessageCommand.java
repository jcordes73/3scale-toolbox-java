package com.redhat.threescale.toolbox.commands.account;

import com.redhat.threescale.toolbox.rest.client.service.AccountManagementService;

import jakarta.inject.Inject;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;


@Command(name="message", mixinStandardHelpOptions = true)
public class AccountMessageCommand implements Runnable {

    private static final Logger LOG = Logger.getLogger(AccountMessageCommand.class);

    @Inject
    @RestClient
    AccountManagementService accountManagementService;

    @ConfigProperty(name="access_token")
    private String accessToken;

    @Parameters(index = "0", description = "Account ID", arity = "1")
    public int accountId;

    @Parameters(index = "1", description = "Subject", arity = "1")
    public String subject;

    @Parameters(index = "2", description = "Body", arity = "1")
    public String body;

    @Override
    public void run() {

        try {
            accountManagementService.createAccountMessage(accountId, accessToken, subject, body);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        
    }
}