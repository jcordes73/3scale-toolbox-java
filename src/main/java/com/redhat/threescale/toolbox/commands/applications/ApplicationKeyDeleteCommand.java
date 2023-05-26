package com.redhat.threescale.toolbox.commands.applications;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;

import com.redhat.threescale.toolbox.rest.client.service.AccountManagementService;

import jakarta.inject.Inject;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

@Command(name="delete", mixinStandardHelpOptions = true)
public class ApplicationKeyDeleteCommand implements Runnable {

    private static final Logger LOG = Logger.getLogger(ApplicationKeyDeleteCommand.class);

    @Inject
    @RestClient
    AccountManagementService accountManagementService;

    @ConfigProperty(name="access_token")
    private String accessToken;

    @Parameters(index = "0", description = "Account ID", arity = "1")
    public int accountId;

    @Parameters(index = "1", description = "Application ID", arity = "1")
    public int applicationId;

    @Parameters(index = "2", description = "Key", arity = "1")
    public String key;

    @Override
    public void run() {
        try {
            accountManagementService.deleteApplicationKey(accountId, applicationId, key, accessToken);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }
}