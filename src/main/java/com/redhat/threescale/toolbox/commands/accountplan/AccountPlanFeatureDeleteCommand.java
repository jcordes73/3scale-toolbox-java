package com.redhat.threescale.toolbox.commands.accountplan;

import com.redhat.threescale.toolbox.rest.client.service.AccountManagementService;

import jakarta.inject.Inject;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;


@Command(name="delete", mixinStandardHelpOptions = true)
public class AccountPlanFeatureDeleteCommand implements Runnable {

    private static final Logger LOG = Logger.getLogger(AccountPlanFeatureDeleteCommand.class);

    @Inject
    @RestClient
    AccountManagementService accountManagementService;

    @ConfigProperty(name="access_token")
    private String accessToken;

    @Parameters(index = "0", description = "Account Plan ID", arity = "1")
    public int accountPlanId;

    @Parameters(index = "1", description = "Account Plan Feature ID", arity = "1")
    public int accountPlanFeatureId;
    
    @Override
    public void run() {

        try {
            accountManagementService.deleteAccountPlanFeature(accountPlanId, accountPlanFeatureId, accessToken);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }

}