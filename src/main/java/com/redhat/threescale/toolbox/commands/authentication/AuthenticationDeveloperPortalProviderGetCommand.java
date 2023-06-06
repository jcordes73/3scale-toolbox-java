package com.redhat.threescale.toolbox.commands.authentication;

import org.jboss.logging.Logger;

import com.redhat.threescale.toolbox.rest.client.service.AccountManagementServiceFactory;

import jakarta.inject.Inject;
import picocli.CommandLine.Command;
import picocli.CommandLine.Model.CommandSpec;
import picocli.CommandLine.Parameters;
import picocli.CommandLine.Spec;

@Command(name="get", mixinStandardHelpOptions = true)
public class AuthenticationDeveloperPortalProviderGetCommand implements Runnable {

    private static final Logger LOG = Logger.getLogger(AuthenticationDeveloperPortalProviderGetCommand.class);

    @Spec
    CommandSpec spec;
    
    @Inject
    AccountManagementServiceFactory accountManagementServiceFactory;

    @Parameters(index = "0", description = "Authentication Provider ID", arity = "1")
    public String authenticationProviderId;

    @Override
    public void run() {
        try {
            String response = accountManagementServiceFactory.getAccountManagementService().getDeveloperPortalAuthenticationProvider(authenticationProviderId);

            spec.commandLine().getOut().println(response);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }
}