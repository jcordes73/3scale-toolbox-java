package com.redhat.threescale.toolbox.commands.provider.account.personal.accesstokens;

import com.redhat.threescale.toolbox.rest.client.service.AccountManagementServiceFactory;

import jakarta.inject.Inject;
import picocli.CommandLine.Command;
import picocli.CommandLine.Model.CommandSpec;
import picocli.CommandLine.Parameters;
import picocli.CommandLine.Spec;

@Command(name="get", mixinStandardHelpOptions = true)
public class ProviderAccountPersonalAccessTokenGetCommand implements Runnable {

    @Spec
    CommandSpec spec;
        
    @Inject
    AccountManagementServiceFactory accountManagementServiceFactory;

    @Parameters(index = "0", description = "ID the access token.", arity = "1")
    private String id;

    @Override
    public void run() {
        try {
            String response = accountManagementServiceFactory.getAccountManagementService().getPersonalAccessToken(id);

            spec.commandLine().getOut().println(response);
        } catch (Exception e) {
            spec.commandLine().getOut().println(e.getMessage());
        }
    }
}
