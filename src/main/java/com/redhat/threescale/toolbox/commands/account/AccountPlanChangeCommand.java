package com.redhat.threescale.toolbox.commands.account;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;

import com.redhat.threescale.toolbox.rest.client.service.AccountManagementService;

import jakarta.inject.Inject;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

@Command(name="change", mixinStandardHelpOptions = true)
public class AccountPlanChangeCommand implements Runnable {
    private static final Logger LOG = Logger.getLogger(AccountCreateCommand.class);
    
    @Inject
    @RestClient
    AccountManagementService accountManagementService;

    @ConfigProperty(name="access_token")
    private String accessToken;

    @Parameters(index = "0", description = "Account ID", arity = "1")
    public int accountId;

    @Parameters(index = "1", description = "Plan ID", arity = "0..1")
    public int planId;

    @Override
    public void run() {
        try {
            accountManagementService.accountChangePlan(accountId, accessToken, planId);
        } catch (Exception e){
            LOG.error(e.getMessage(), e);
        }
    }
}
