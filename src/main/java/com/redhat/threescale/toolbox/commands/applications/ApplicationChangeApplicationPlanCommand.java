package com.redhat.threescale.toolbox.commands.applications;

import org.jboss.logging.Logger;

import com.redhat.threescale.toolbox.rest.client.service.AccountManagementServiceFactory;

import jakarta.inject.Inject;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

@Command(name="change", mixinStandardHelpOptions = true)
public class ApplicationChangeApplicationPlanCommand implements Runnable {

    private static final Logger LOG = Logger.getLogger(ApplicationChangeApplicationPlanCommand.class);

    @Inject
    AccountManagementServiceFactory accountManagementServiceFactory;

    @Parameters(index = "0", description = "Account ID", arity = "1")
    private int accountId;

    @Parameters(index = "1", description = "Application ID", arity = "1")
    private int applicationId;

    @Parameters(index = "2", description = "Application Plan ID", arity = "1")
    private int applicationPlanId;
    
    @Override
    public void run() {
        try {
            accountManagementServiceFactory.getAccountManagementService().changeApplicationPlan(accountId, applicationId, applicationPlanId);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }
}
