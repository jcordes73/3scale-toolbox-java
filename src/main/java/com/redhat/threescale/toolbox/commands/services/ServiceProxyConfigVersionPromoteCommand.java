package com.redhat.threescale.toolbox.commands.services;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;

import com.redhat.threescale.toolbox.rest.client.service.AccountManagementService;

import jakarta.inject.Inject;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

@Command(name="promote", mixinStandardHelpOptions = true)
public class ServiceProxyConfigVersionPromoteCommand implements Runnable {

    private static final Logger LOG = Logger.getLogger(ServiceProxyConfigVersionPromoteCommand.class);

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

    @Parameters(index = "1", description = "Environment to promote to. Valid values: ${COMPLETION-CANDIDATES}", arity = "1")
    public AccountManagementService.Environment toEnvironment;

    @Override
    public void run() {
        try {
            accountManagementService.promoteServiceProxyConfigVersion(serviceId, environment, version, accessToken, toEnvironment);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }
}