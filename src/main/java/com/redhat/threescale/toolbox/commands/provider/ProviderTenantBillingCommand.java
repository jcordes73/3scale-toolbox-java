package com.redhat.threescale.toolbox.commands.provider;

import picocli.CommandLine.Command;
import picocli.CommandLine.Spec;
import picocli.CommandLine.Model.CommandSpec;
import picocli.CommandLine.ParameterException;


@Command(name="billing", mixinStandardHelpOptions = true, 
         subcommands = {ProviderTenantBillingAllCommand.class,
                        }, 
        synopsisSubcommandLabel = "COMMAND")
public class ProviderTenantBillingCommand implements Runnable {
    @Spec CommandSpec spec;

    @Override
    public void run() {
        throw new ParameterException(spec.commandLine(), "Missing required subcommand");
    }
}
