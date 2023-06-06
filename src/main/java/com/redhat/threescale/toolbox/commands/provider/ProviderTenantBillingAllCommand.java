package com.redhat.threescale.toolbox.commands.provider;

import org.jboss.logging.Logger;

import com.redhat.threescale.toolbox.rest.client.service.MasterServiceFactory;

import jakarta.inject.Inject;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

@Command(name="all", mixinStandardHelpOptions = true)
public class ProviderTenantBillingAllCommand implements Runnable {

    private static final Logger LOG = Logger.getLogger(ProviderTenantCreateCommand.class);

    @Inject
    MasterServiceFactory masterServiceFactory;

    @Parameters(index="0", description = "Tenant ID.", arity="1")
    public int tenantId;

    @Parameters(index="1", description = "Base date for the billing process. Format YYYY-MM-DD (UTC).", arity="1")
    public String date;
    
    @Override
    public void run() {
        try {
            masterServiceFactory.getMasterService().triggerTenantBillingAll(tenantId, date);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }
}