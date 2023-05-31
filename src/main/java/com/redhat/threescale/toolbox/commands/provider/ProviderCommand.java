package com.redhat.threescale.toolbox.commands.provider;

import picocli.CommandLine.Command;
import picocli.CommandLine.Spec;
import picocli.CommandLine.Model.CommandSpec;
import picocli.CommandLine.ParameterException;


@Command(name="provider", mixinStandardHelpOptions = true, 
         subcommands = {ProviderGetCommand.class,
                        ProviderTenantCommand.class
                        }, 
        synopsisSubcommandLabel = "COMMAND")
public class ProviderCommand implements Runnable {
    @Spec CommandSpec spec;

    @Override
    public void run() {
        throw new ParameterException(spec.commandLine(), "Missing required subcommand");
    }
}
