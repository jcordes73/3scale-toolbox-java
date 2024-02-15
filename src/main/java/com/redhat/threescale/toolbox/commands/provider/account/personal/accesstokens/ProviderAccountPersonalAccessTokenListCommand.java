package com.redhat.threescale.toolbox.commands.provider.account.personal.accesstokens;

import com.redhat.threescale.toolbox.rest.client.service.AccountManagementServiceFactory;

import jakarta.inject.Inject;
import picocli.CommandLine.Command;
import picocli.CommandLine.Model.CommandSpec;
import picocli.CommandLine.Option;
import picocli.CommandLine.Spec;

@Command(name="list", mixinStandardHelpOptions = true)
public class ProviderAccountPersonalAccessTokenListCommand implements Runnable {

    @Spec
    CommandSpec spec;
        
    @Inject
    AccountManagementServiceFactory accountManagementServiceFactory;

    @Option(names = {"--name",}, description = "Part of the name of the access token")
    public String userName;

    @Override
    public void run() {
        try {
            String response = accountManagementServiceFactory.getAccountManagementService().getPersonalAccessTokens(userName);

            spec.commandLine().getOut().println(response);
        } catch (Exception e) {
            spec.commandLine().getOut().println(e.getMessage());
        }
    }
}
