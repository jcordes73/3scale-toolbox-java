package com.redhat.threescale.toolbox.commands.provider;

import org.jboss.logging.Logger;

import com.redhat.threescale.toolbox.rest.client.service.MasterServiceFactory;

import jakarta.inject.Inject;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

@Command(name="delete", mixinStandardHelpOptions = true)
public class ProviderTenantDeleteCommand implements Runnable {

    private static final Logger LOG = Logger.getLogger(ProviderTenantDeleteCommand.class);
        
    @Inject
    MasterServiceFactory masterServiceFactory;

    @Parameters(index="0", description="Tenant ID", arity="1")
    public int tenantId;

    @Override
    public void run() {
        try {
            masterServiceFactory.getMasterService().deleteTenant(tenantId);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }
}