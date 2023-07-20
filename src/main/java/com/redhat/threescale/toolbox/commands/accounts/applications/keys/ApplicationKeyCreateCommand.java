package com.redhat.threescale.toolbox.commands.accounts.applications.keys;

import org.jboss.logging.Logger;

import com.redhat.threescale.toolbox.rest.client.service.AccountManagementServiceFactory;

import jakarta.inject.Inject;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

@Command(name="create", mixinStandardHelpOptions = true)
public class ApplicationKeyCreateCommand implements Runnable {

    private static final Logger LOG = Logger.getLogger(ApplicationKeyCreateCommand.class);

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
            LOG.error(e.getMessage(), e);
        }
    }
}