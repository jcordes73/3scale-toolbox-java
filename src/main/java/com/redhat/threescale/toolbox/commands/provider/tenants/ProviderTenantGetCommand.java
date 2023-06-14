package com.redhat.threescale.toolbox.commands.provider.tenants;

import org.jboss.logging.Logger;

import com.redhat.threescale.toolbox.rest.client.service.MasterServiceFactory;

import jakarta.inject.Inject;
import picocli.CommandLine.Command;
import picocli.CommandLine.Model.CommandSpec;
import picocli.CommandLine.Parameters;
import picocli.CommandLine.Spec;

@Command(name="get", mixinStandardHelpOptions = true)
public class ProviderTenantGetCommand implements Runnable {

    private static final Logger LOG = Logger.getLogger(ProviderTenantGetCommand.class);

    @Spec
    CommandSpec spec;
        
    @Inject
    MasterServiceFactory masterServiceFactory;

    @Parameters(index="0", description="Tenant ID", arity="1")
    public int tenantId;

    @Override
    public void run() {
        try {
            String response = masterServiceFactory.getMasterService().getTenant(tenantId);

            spec.commandLine().getOut().println(response);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }
}
