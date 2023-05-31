package com.redhat.threescale.toolbox.commands.provider;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;

import com.redhat.threescale.toolbox.rest.client.service.MasterService;

import jakarta.inject.Inject;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

@Command(name="update", mixinStandardHelpOptions = true)
public class ProviderTenantUpdateCommand implements Runnable {

    private static final Logger LOG = Logger.getLogger(ProviderTenantUpdateCommand.class);

    @Inject
    @RestClient
    MasterService masterService;

    @ConfigProperty(name="access_token")
    private String accessToken;

    @Parameters(index="0", description="Tenant ID", arity="1")
    public int tenantId;

    @Option(names = {"from-email"}, description="New outgoing email")
    public String fromEmail;

    @Option(names = {"support-email"}, description="New support email")
    public String supportEmail;

    @Option(names = {"finance-support-email"}, description="New finance support email")
    public String financeSupportEmail;

    @Option(names = {"site-access-code"}, description="Developer Portal Access Code.")
    public String siteAccessCode;

    @Option(names = {"state-event"}, description="Change the state of the tenant. Valid values: ${COMPLETION-CANDIDATES}", defaultValue = Option.NULL_VALUE)
    public MasterService.State stateEvent;
    
    @Override
    public void run() {
        try {
            masterService.updateTenant(tenantId, accessToken, fromEmail, supportEmail, financeSupportEmail, siteAccessCode, stateEvent);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }
}