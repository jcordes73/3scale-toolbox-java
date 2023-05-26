package com.redhat.threescale.toolbox.commands.accountplan;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;

import com.redhat.threescale.toolbox.rest.client.service.AccountManagementService;

import jakarta.inject.Inject;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

@Command(name="default", mixinStandardHelpOptions = true)
public class AccountPlanDefaultCommand implements Runnable {

    private static final Logger LOG = Logger.getLogger(AccountPlanDefaultCommand.class);
    
    @Inject
    @RestClient
    AccountManagementService accountManagementService;

    @ConfigProperty(name="access_token")
    private String accessToken;
    
    @Parameters(index = "0", description = "Account Plan ID", arity = "1")
    public int accountPlanId;

    @Override
    public void run() {
        try {
            accountManagementService.accountPlanDefault(accountPlanId, accessToken);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }
}