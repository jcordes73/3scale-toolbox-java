package com.redhat.threescale.toolbox.commands.services.backends;

import picocli.CommandLine.Command;
import picocli.CommandLine.Spec;
import picocli.CommandLine.Model.CommandSpec;
import picocli.CommandLine.ParameterException;


@Command(name="usages", mixinStandardHelpOptions = true, 
         subcommands = {BackendUsageListCommand.class,
                        BackendUsageGetCommand.class,
                        BackendUsageCreateCommand.class,
                        BackendUsageUpdateCommand.class,
                        BackendUsageDeleteCommand.class
                        }, 
        synopsisSubcommandLabel = "COMMAND")
public class BackendUsagesCommand implements Runnable {
    @Spec CommandSpec spec;

    @Override
    public void run() {
        throw new ParameterException(spec.commandLine(), "Missing required subcommand");
    }
}
