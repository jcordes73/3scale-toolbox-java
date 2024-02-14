package com.redhat.threescale.toolbox.commands.config.tenants;

import picocli.CommandLine.Command;
import picocli.CommandLine.Spec;
import picocli.CommandLine.Model.CommandSpec;
import picocli.CommandLine.ParameterException;

@Command(name="tenants", mixinStandardHelpOptions = true, 
         subcommands = {TenantListCommand.class,
                        TenantCreateCommand.class,
                        TenantUpdateCommand.class,
                        TenantActiveCommand.class,
                        TenantDeleteCommand.class                       
                       }, 
synopsisSubcommandLabel = "COMMAND")
public class TenantsCommand implements Runnable {

    @Spec CommandSpec spec;

    @Override
    public void run() {
        throw new ParameterException(spec.commandLine(), "Missing required subcommand");
    }
}