package com.redhat.threescale.toolbox.commands.applicationplans;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;

import com.redhat.threescale.toolbox.rest.client.service.AccountManagementService;

import jakarta.inject.Inject;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;
import picocli.CommandLine.Spec;
import picocli.CommandLine.Model.CommandSpec;

@Command(name="list", mixinStandardHelpOptions = true)
public class ApplicationPlanPricingRulesListCommand implements Runnable {

    private static final Logger LOG = Logger.getLogger(ApplicationPlanPricingRulesListCommand.class);

    @Spec
    CommandSpec spec;
    
    @Inject
    @RestClient
    AccountManagementService accountManagementService;

    @ConfigProperty(name="access_token")
    private String accessToken;

    @Parameters(index = "0", description = "Application Plan ID", arity = "1")
    public int applicationPlanId;

    @Override
    public void run() {
        try {
            String response = accountManagementService.getApplicationPlanPricingRules(applicationPlanId, accessToken);

            spec.commandLine().getOut().println(response);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }
}