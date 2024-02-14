package com.redhat.threescale.toolbox.commands.provider.tenants;

import com.redhat.threescale.toolbox.rest.client.service.MasterServiceFactory;

import jakarta.inject.Inject;
import picocli.CommandLine.Command;
import picocli.CommandLine.Model.CommandSpec;
import picocli.CommandLine.Parameters;
import picocli.CommandLine.Spec;

@Command(name="all", mixinStandardHelpOptions = true)
public class ProviderTenantBillingAllCommand implements Runnable {

    @Spec
    CommandSpec spec;
    
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
            spec.commandLine().getOut().println(e.getMessage());
        }
    }
}