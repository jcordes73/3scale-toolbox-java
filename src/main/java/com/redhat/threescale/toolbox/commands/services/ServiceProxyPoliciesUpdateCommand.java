package com.redhat.threescale.toolbox.commands.services;

import java.nio.file.Files;
import java.nio.file.Paths;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;

import com.redhat.threescale.toolbox.rest.client.service.AccountManagementService;

import jakarta.inject.Inject;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;
import picocli.CommandLine.Spec;
import picocli.CommandLine.Model.CommandSpec;

@Command(name="update", mixinStandardHelpOptions = true)
public class ServiceProxyPoliciesUpdateCommand implements Runnable {

    private static final Logger LOG = Logger.getLogger(ServiceProxyPoliciesUpdateCommand.class);

    @Spec
    CommandSpec spec;
    
    @Inject
    @RestClient
    AccountManagementService accountManagementService;

    @ConfigProperty(name="access_token")
    private String accessToken;

    @Parameters(index = "0", description = "Service ID", arity = "1")
    public int serviceId;

    @Parameters(index = "1", description = "Policies Config File", arity = "1")
    public String policiesConfigFile;

    @Override
    public void run() {
        try {
            String policiesConfig = Files.readString(Paths.get(policiesConfigFile));

            accountManagementService.updateServiceProxyPolicies(serviceId, accessToken, policiesConfig);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }
}