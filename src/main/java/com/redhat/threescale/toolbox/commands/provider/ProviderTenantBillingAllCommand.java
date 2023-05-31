package com.redhat.threescale.toolbox.commands.provider;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;

import com.redhat.threescale.toolbox.rest.client.service.MasterService;

import jakarta.inject.Inject;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

@Command(name="all", mixinStandardHelpOptions = true)
public class ProviderTenantBillingAllCommand implements Runnable {

    private static final Logger LOG = Logger.getLogger(ProviderTenantCreateCommand.class);

    @Inject
    @RestClient
    MasterService masterService;

    @ConfigProperty(name="access_token")
    private String accessToken;

    @Parameters(index="0", description = "Tenant ID.", arity="1")
    public int tenantId;

    @Parameters(index="1", description = "Base date for the billing process. Format YYYY-MM-DD (UTC).", arity="1")
    public String date;
    
    @Override
    public void run() {
        try {
            masterService.triggerTenantBillingAll(tenantId, accessToken, date);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }
}