package com.redhat.threescale.toolbox.commands.account;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;

import com.redhat.threescale.toolbox.rest.client.service.AccountManagementService;

import jakarta.inject.Inject;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

@Command(name="delete", mixinStandardHelpOptions = true)
public class AccountDeleteCreditCardCommand implements Runnable {

    private static final Logger LOG = Logger.getLogger(AccountDeleteCreditCardCommand.class);

    @Inject
    @RestClient
    AccountManagementService accountManagementService;

    @ConfigProperty(name="access_token")
    private String accessToken;


    @Parameters(index = "0", description = "Account ID", arity = "1")
    public int accountId;

    @Override
    public void run() {
        try {
            accountManagementService.accountDeleteCreditCard(accountId, accessToken);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }
}