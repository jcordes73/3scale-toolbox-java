package com.redhat.threescale.toolbox.commands.account;

import org.jboss.logging.Logger;

import com.redhat.threescale.toolbox.rest.client.service.AccountManagementServiceFactory;

import jakarta.inject.Inject;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

@Command(name="delete", mixinStandardHelpOptions = true)
public class AccountServiceContractsDeleteCommand implements Runnable {

    private static final Logger LOG = Logger.getLogger(AccountServiceContractsDeleteCommand.class);

    @Inject
    AccountManagementServiceFactory accountManagementServiceFactory;

    @Parameters(index = "0", description = "Account ID", arity = "1")
    public int accountId;

    @Parameters(index = "1", description = "Service Contract ID", arity = "1")
    public int serviceContractId;

    @Override
    public void run() {
        try {
            accountManagementServiceFactory.getAccountManagementService().deleteAccountServiceContract(accountId, serviceContractId);
         } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }
}