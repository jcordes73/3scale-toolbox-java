package com.redhat.threescale.toolbox.commands.services;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;

import com.redhat.threescale.toolbox.rest.client.service.AccountManagementService;

import jakarta.inject.Inject;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;
import picocli.CommandLine.Spec;
import picocli.CommandLine.Model.CommandSpec;

@Command(name="latest", mixinStandardHelpOptions = true)
public class ServiceProxyConfigLatestCommand implements Runnable {

    private static final Logger LOG = Logger.getLogger(ServiceProxyConfigLatestCommand.class);

    @Spec
    CommandSpec spec;
    
    @Inject
    @RestClient
    AccountManagementService accountManagementService;

    @ConfigProperty(name="access_token")
    private String accessToken;

    @Parameters(index = "0", description = "Service ID", arity = "1")
    public int serviceId;

    @Parameters(index = "1", description = "Environment. Valid values: ${COMPLETION-CANDIDATES}", arity = "1")
    public AccountManagementService.Environment environment;

    @Override
    public void run() {
        try {
            String response = accountManagementService.getServiceProxyConfigLatest(serviceId, environment, accessToken);

            spec.commandLine().getOut().println(response);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }
}