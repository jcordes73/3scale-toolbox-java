package com.redhat.threescale.toolbox.commands.accounts.applications;

import picocli.CommandLine.Command;
import picocli.CommandLine.Spec;
import picocli.CommandLine.Model.CommandSpec;
import picocli.CommandLine.ParameterException;


@Command(name="analytics", 
        mixinStandardHelpOptions = true, 
        subcommands = {ApplicationAnalyticsUsageCommand.class, 
                    }, 
        synopsisSubcommandLabel = "COMMAND")
public class ApplicationAnalyticsCommand implements Runnable {
    @Spec CommandSpec spec;

    @Override
    public void run() {
        throw new ParameterException(spec.commandLine(), "Missing required subcommand");
    }
}
