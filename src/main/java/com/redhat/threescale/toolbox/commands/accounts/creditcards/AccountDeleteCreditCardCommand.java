package com.redhat.threescale.toolbox.commands.accounts.creditcards;

import org.jboss.logging.Logger;

import com.redhat.threescale.toolbox.rest.client.service.AccountManagementServiceFactory;

import jakarta.inject.Inject;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

@Command(name="delete", mixinStandardHelpOptions = true)
public class AccountDeleteCreditCardCommand implements Runnable {

    private static final Logger LOG = Logger.getLogger(AccountDeleteCreditCardCommand.class);

    @Inject
    AccountManagementServiceFactory accountManagementServiceFactory;

    @Parameters(index = "0", description = "Account ID", arity = "1")
    public int accountId;

    @Override
    public void run() {
        try {
            accountManagementServiceFactory.getAccountManagementService().accountDeleteCreditCard(accountId);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }
}