package com.redhat.threescale.toolbox.commands.provider.tenants;

import picocli.CommandLine.Command;
import picocli.CommandLine.Spec;
import picocli.CommandLine.Model.CommandSpec;
import picocli.CommandLine.ParameterException;


@Command(name="tenants", mixinStandardHelpOptions = true, 
         subcommands = {ProviderTenantGetCommand.class,
                        ProviderTenantCreateCommand.class,
                        ProviderTenantDeleteCommand.class,
                        ProviderTenantBillingCommand.class
                        }, 
        synopsisSubcommandLabel = "COMMAND")
public class ProviderTenantsCommand implements Runnable {
    @Spec CommandSpec spec;

    @Override
    public void run() {
        throw new ParameterException(spec.commandLine(), "Missing required subcommand");
    }
}
