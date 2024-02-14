package com.redhat.threescale.toolbox.commands.provider.tenants;

import com.redhat.threescale.toolbox.rest.client.service.MasterService;
import com.redhat.threescale.toolbox.rest.client.service.MasterServiceFactory;

import jakarta.inject.Inject;
import picocli.CommandLine.Command;
import picocli.CommandLine.Model.CommandSpec;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;
import picocli.CommandLine.Spec;

@Command(name="update", mixinStandardHelpOptions = true)
public class ProviderTenantUpdateCommand implements Runnable {

    @Spec
    CommandSpec spec;
    
    @Inject
    MasterServiceFactory masterServiceFactory;

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
            masterServiceFactory.getMasterService().updateTenant(tenantId, fromEmail, supportEmail, financeSupportEmail, siteAccessCode, stateEvent);
        } catch (Exception e) {
            spec.commandLine().getOut().println(e.getMessage());
        }
    }
}