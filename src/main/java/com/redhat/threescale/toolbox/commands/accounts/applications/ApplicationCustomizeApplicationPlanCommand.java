package com.redhat.threescale.toolbox.commands.accounts.applications;

import org.jboss.logging.Logger;

import com.redhat.threescale.toolbox.rest.client.service.AccountManagementServiceFactory;

import jakarta.inject.Inject;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

@Command(name="customize", mixinStandardHelpOptions = true)
public class ApplicationCustomizeApplicationPlanCommand implements Runnable {

    private static final Logger LOG = Logger.getLogger(ApplicationCustomizeApplicationPlanCommand.class);

    @Inject
    AccountManagementServiceFactory accountManagementServiceFactory;

    @Parameters(index = "0", description = "Account ID", arity = "1")
    private int accountId;

    @Parameters(index = "1", description = "Application ID", arity = "1")
    private int applicationId;
    
    @Override
    public void run() {
        try {
            accountManagementServiceFactory.getAccountManagementService().customizeApplicationPlan(accountId, applicationId);
        } catch (Exception e) {
            LOG.error(e.getMessage(),e);
        }
    }
}