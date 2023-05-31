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

@Command(name="version", mixinStandardHelpOptions = true)
public class ServiceProxyConfigVersionCommand implements Runnable {

    private static final Logger LOG = Logger.getLogger(ServiceProxyConfigVersionCommand.class);

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

    @Parameters(index = "2", description = "Version", arity = "1")
    public String version;

    @Override
    public void run() {
        try {
            String response = accountManagementService.getServiceProxyConfigVersion(serviceId, environment, version, accessToken);

            spec.commandLine().getOut().println(response);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }
}