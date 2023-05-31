package com.redhat.threescale.toolbox.commands.account;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;

import com.redhat.threescale.toolbox.rest.client.service.AccountManagementService;

import jakarta.inject.Inject;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;
import picocli.CommandLine.Spec;
import picocli.CommandLine.Model.CommandSpec;

@Command(name="get", mixinStandardHelpOptions = true)
public class AccountFeatureGetCommand implements Runnable {

    private static final Logger LOG = Logger.getLogger(AccountFeatureGetCommand.class);

    @Spec
    CommandSpec spec;
    
    @Inject
    @RestClient
    AccountManagementService accountManagementService;

    @ConfigProperty(name="access_token")
    private String accessToken;

    @Parameters(index = "0", description = "Feature ID", arity = "1")
    public int featureId;

    @Override
    public void run() {
        try {
            String response = accountManagementService.getAccountFeature(featureId, accessToken);

            spec.commandLine().getOut().println(response);
        } catch (Exception e){
            LOG.error(e.getMessage(), e);
        }
    }
}
