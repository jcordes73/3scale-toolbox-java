package com.redhat.threescale.toolbox.commands.accounts.applications.referrerfilters;

import org.jboss.logging.Logger;

import com.redhat.threescale.toolbox.rest.client.service.AccountManagementServiceFactory;

import jakarta.inject.Inject;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

@Command(name="delete", mixinStandardHelpOptions = true)
public class ApplicationReferrerFilterDeleteCommand implements Runnable {

    private static final Logger LOG = Logger.getLogger(ApplicationReferrerFilterDeleteCommand.class);

    @Inject
    AccountManagementServiceFactory accountManagementServiceFactory;

    @Parameters(index = "0", description = "Account ID", arity = "1")
    public int accountId;

    @Parameters(index = "1", description = "Application ID", arity = "1")
    public int applicationId;

    @Parameters(index = "2", description = "Referrer Filter ID", arity = "1")
    public String referrerFilter;

    @Override
    public void run() {
        try {
            accountManagementServiceFactory.getAccountManagementService().deleteApplicationReferrerFilter(accountId, applicationId, referrerFilter);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }
}