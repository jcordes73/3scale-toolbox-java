package com.redhat.threescale.toolbox.commands.applications;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;

import com.redhat.threescale.toolbox.rest.client.service.AccountManagementService;

import jakarta.inject.Inject;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

@Command(name="create", mixinStandardHelpOptions = true)
public class ApplicationReferrerFilterCreateCommand implements Runnable {

    private static final Logger LOG = Logger.getLogger(ApplicationReferrerFilterCreateCommand.class);

    @Inject
    @RestClient
    AccountManagementService accountManagementService;

    @ConfigProperty(name="access_token")
    private String accessToken;

    @Parameters(index = "0", description = "Account ID", arity = "1")
    private int accountId;

    @Parameters(index = "1", description = "Application ID", arity = "1")
    private int applicationId;

    @Parameters(index = "2", description = "Referrer Filter", arity = "1")
    private String referrerFilter;
    
    @Override
    public void run() {
        try {
            accountManagementService.createApplicationReferrerFilter(accountId, applicationId, accessToken, referrerFilter);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }
}