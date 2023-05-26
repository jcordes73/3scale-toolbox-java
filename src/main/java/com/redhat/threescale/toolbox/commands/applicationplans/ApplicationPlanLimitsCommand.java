package com.redhat.threescale.toolbox.commands.applicationplans;

import picocli.CommandLine.Command;
import picocli.CommandLine.Spec;
import picocli.CommandLine.Model.CommandSpec;
import picocli.CommandLine.ParameterException;


@Command(name="limits", mixinStandardHelpOptions = true, 
         subcommands = {ApplicationPlanLimitsListCommand.class
                       }, 
        synopsisSubcommandLabel = "COMMAND")
public class ApplicationPlanLimitsCommand implements Runnable {
    @Spec CommandSpec spec;

    @Override
    public void run() {
        throw new ParameterException(spec.commandLine(), "Missing required subcommand");
    }
}
