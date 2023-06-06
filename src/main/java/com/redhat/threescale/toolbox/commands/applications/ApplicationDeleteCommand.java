package com.redhat.threescale.toolbox.commands.applications;

import org.jboss.logging.Logger;

import com.redhat.threescale.toolbox.rest.client.service.AccountManagementServiceFactory;

import jakarta.inject.Inject;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

@Command(name="delete", mixinStandardHelpOptions = true)
public class ApplicationDeleteCommand implements Runnable {

    private static final Logger LOG = Logger.getLogger(ApplicationDeleteCommand.class);

    @Inject
    AccountManagementServiceFactory accountManagementServiceFactory;

    @Parameters(index = "0", description = "Account ID", arity = "1")
    public int accountId;

    @Parameters(index = "1", description = "Application ID", arity = "1")
    public int applicationId;

    @Override
    public void run() {
        try {
            accountManagementServiceFactory.getAccountManagementService().deleteApplication(accountId, applicationId);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }
}