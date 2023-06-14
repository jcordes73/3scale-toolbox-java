package com.redhat.threescale.toolbox.commands.accounts.applications;

import org.jboss.logging.Logger;

import com.redhat.threescale.toolbox.rest.client.service.AccountManagementServiceFactory;

import jakarta.inject.Inject;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

@Command(name="delete", mixinStandardHelpOptions = true)
public class ApplicationKeyDeleteCommand implements Runnable {

    private static final Logger LOG = Logger.getLogger(ApplicationKeyDeleteCommand.class);

    @Inject
    AccountManagementServiceFactory accountManagementServiceFactory;

    @Parameters(index = "0", description = "Account ID", arity = "1")
    public int accountId;

    @Parameters(index = "1", description = "Application ID", arity = "1")
    public int applicationId;

    @Parameters(index = "2", description = "Key", arity = "1")
    public String key;

    @Override
    public void run() {
        try {
            accountManagementServiceFactory.getAccountManagementService().deleteApplicationKey(accountId, applicationId, key);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }
}