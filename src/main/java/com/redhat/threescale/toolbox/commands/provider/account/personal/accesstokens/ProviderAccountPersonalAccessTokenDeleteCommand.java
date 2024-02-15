package com.redhat.threescale.toolbox.commands.provider.account.personal.accesstokens;

import com.redhat.threescale.toolbox.rest.client.service.AccountManagementServiceFactory;

import jakarta.inject.Inject;
import picocli.CommandLine.Command;
import picocli.CommandLine.Model.CommandSpec;
import picocli.CommandLine.Parameters;
import picocli.CommandLine.Spec;

@Command(name="delete", mixinStandardHelpOptions = true)
public class ProviderAccountPersonalAccessTokenDeleteCommand implements Runnable {

    @Spec
    CommandSpec spec;
        
    @Inject
    AccountManagementServiceFactory accountManagementServiceFactory;

    @Parameters(index = "0", description = "ID of the access token.", arity = "1")
    private String id;

    @Override
    public void run() {
        try {
            accountManagementServiceFactory.getAccountManagementService().deletePersonalAccessToken(id);
        } catch (Exception e) {
            spec.commandLine().getOut().println(e.getMessage());
        }
    }
}