package com.redhat.threescale.toolbox.commands.applications;

import org.jboss.logging.Logger;

import com.redhat.threescale.toolbox.rest.client.service.AccountManagementServiceFactory;

import jakarta.inject.Inject;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

@Command(name="create", mixinStandardHelpOptions = true)
public class ApplicationReferrerFilterCreateCommand implements Runnable {

    private static final Logger LOG = Logger.getLogger(ApplicationReferrerFilterCreateCommand.class);

    @Inject
    AccountManagementServiceFactory accountManagementServiceFactory;

    @Parameters(index = "0", description = "Account ID", arity = "1")
    private int accountId;

    @Parameters(index = "1", description = "Application ID", arity = "1")
    private int applicationId;

    @Parameters(index = "2", description = "Referrer Filter", arity = "1")
    private String referrerFilter;
    
    @Override
    public void run() {
        try {
            accountManagementServiceFactory.getAccountManagementService().createApplicationReferrerFilter(accountId, applicationId, referrerFilter);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }
}