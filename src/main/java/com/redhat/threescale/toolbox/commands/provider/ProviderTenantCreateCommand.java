package com.redhat.threescale.toolbox.commands.provider;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;

import com.redhat.threescale.toolbox.rest.client.service.MasterService;

import jakarta.inject.Inject;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

@Command(name="create", mixinStandardHelpOptions = true)
public class ProviderTenantCreateCommand implements Runnable {

    private static final Logger LOG = Logger.getLogger(ProviderTenantCreateCommand.class);

    @Inject
    @RestClient
    MasterService masterService;

    @ConfigProperty(name="access_token")
    private String accessToken;

    @Parameters(index="0", description = "Organization name.", arity="1")
    public String orgName;

    @Parameters(index="1", description = "Username.", arity="1")
    public String userName;

    @Parameters(index="2", description = "Email.", arity="1")
    public String email;

    @Parameters(index="3", description = "Password.", arity="1")
    public String password;
    
    @Override
    public void run() {
        try {
            masterService.createTenant(accessToken, orgName, userName, email, password);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }
}