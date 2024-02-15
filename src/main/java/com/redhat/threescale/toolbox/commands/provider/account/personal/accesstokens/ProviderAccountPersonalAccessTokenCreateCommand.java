package com.redhat.threescale.toolbox.commands.provider.account.personal.accesstokens;

import java.util.Arrays;

import com.redhat.threescale.toolbox.rest.client.service.AccountManagementServiceFactory;

import jakarta.inject.Inject;
import jakarta.ws.rs.core.MultivaluedHashMap;
import picocli.CommandLine.Command;
import picocli.CommandLine.Model.CommandSpec;
import picocli.CommandLine.Parameters;
import picocli.CommandLine.Spec;

@Command(name="create", mixinStandardHelpOptions = true)
public class ProviderAccountPersonalAccessTokenCreateCommand implements Runnable {

    @Spec
    CommandSpec spec;
        
    @Inject
    AccountManagementServiceFactory accountManagementServiceFactory;

    @Parameters(index = "0", description = "Name of the access token.", arity = "1")
    private String name;

    @Parameters(index = "1", description = "Permission of the access token. It must be either 'ro' (read only) or 'rw' (read and write).", arity = "1")
    private String permission;

    @Parameters(index = "2", description = "List of comma-seperated scopes. Valid values: account_management,stats,finance,cms,policy_registry", arity = "1")
    private String scopes;

    @Override
    public void run() {
        try {
            MultivaluedHashMap<String,String> request = new MultivaluedHashMap<>();
            request.addFirst("name", name);
            request.addFirst("permission", permission);

            if (scopes != null)
                request.put("scopes[]", Arrays.asList(scopes.split(",")));

            String response = accountManagementServiceFactory.getAccountManagementService().createPersonalAccessToken(request);

            spec.commandLine().getOut().println(response);
        } catch (Exception e) {
            spec.commandLine().getOut().println(e.getMessage());
        }
    }
}
