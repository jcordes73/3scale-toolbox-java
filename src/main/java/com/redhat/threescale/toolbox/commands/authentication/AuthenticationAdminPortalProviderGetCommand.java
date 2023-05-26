package com.redhat.threescale.toolbox.commands.authentication;

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
public class AuthenticationAdminPortalProviderGetCommand implements Runnable {

    private static final Logger LOG = Logger.getLogger(AuthenticationAdminPortalProviderGetCommand.class);

    @Spec
    CommandSpec spec;
    
    @Inject
    @RestClient
    AccountManagementService accountManagementService;

    @ConfigProperty(name="access_token")
    private String accessToken;

    @Parameters(index = "0", description = "Authentication Provider ID", arity = "1")
    public int authenticationProviderId;

    @Override
    public void run() {
        try {
            String response = accountManagementService.getAdminPortalAuthenticationProvider(authenticationProviderId, accessToken);

            spec.commandLine().getOut().println(response);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }
}