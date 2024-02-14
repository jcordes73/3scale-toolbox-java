package com.redhat.threescale.toolbox.commands.provider.tenants;

import com.redhat.threescale.toolbox.rest.client.service.MasterServiceFactory;

import jakarta.inject.Inject;
import jakarta.validation.constraints.Email;
import picocli.CommandLine.Command;
import picocli.CommandLine.Model.CommandSpec;
import picocli.CommandLine.Parameters;
import picocli.CommandLine.Spec;

@Command(name="create", mixinStandardHelpOptions = true)
public class ProviderTenantCreateCommand implements Runnable {

    @Spec
    CommandSpec spec;
    
    @Inject
    MasterServiceFactory masterServiceFactory;

    @Parameters(index="0", description = "Organization name.", arity="1")
    public String orgName;

    @Parameters(index="1", description = "Username.", arity="1")
    public String userName;

    @Parameters(index="2", description = "Email.", arity="1")
    @Email
    public String email;

    @Parameters(index="3", description = "Password.", arity="1")
    public String password;
    
    @Override
    public void run() {
        try {
            masterServiceFactory.getMasterService().createTenant(orgName, userName, email, password);
        } catch (Exception e) {
            spec.commandLine().getOut().println(e.getMessage());
        }
    }
}