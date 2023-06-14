package com.redhat.threescale.toolbox.commands.provider;

import com.redhat.threescale.toolbox.commands.provider.tenants.ProviderTenantsCommand;
import com.redhat.threescale.toolbox.commands.provider.webhooks.WebhooksCommand;
import com.redhat.threescale.toolbox.commands.provider.objects.ObjectsCommand;
import com.redhat.threescale.toolbox.commands.provider.account.ProviderAccountCommand;
import com.redhat.threescale.toolbox.commands.provider.authentication.AuthenticationCommand;
import com.redhat.threescale.toolbox.commands.provider.fielddefinitions.FieldDefinitionsCommand;
import com.redhat.threescale.toolbox.commands.provider.policies.PoliciesCommand;
import com.redhat.threescale.toolbox.commands.provider.proxies.ProxiesCommand;

import picocli.CommandLine.Command;
import picocli.CommandLine.Spec;
import picocli.CommandLine.Model.CommandSpec;
import picocli.CommandLine.ParameterException;


@Command(name="provider", mixinStandardHelpOptions = true, 
         subcommands = {ProviderAccountCommand.class,
                        AuthenticationCommand.class,
                        PoliciesCommand.class,
                        ProxiesCommand.class,
                        FieldDefinitionsCommand.class,
                        ProviderTenantsCommand.class,
                        WebhooksCommand.class,
                        ObjectsCommand.class
                        }, 
        synopsisSubcommandLabel = "COMMAND")
public class ProviderCommand implements Runnable {
    @Spec CommandSpec spec;

    @Override
    public void run() {
        throw new ParameterException(spec.commandLine(), "Missing required subcommand");
    }
}
