package com.redhat.threescale.toolbox.commands.applicationplans;

import picocli.CommandLine.Command;
import picocli.CommandLine.Spec;
import picocli.CommandLine.Model.CommandSpec;
import picocli.CommandLine.ParameterException;


@Command(name="features", mixinStandardHelpOptions = true, 
         subcommands = {ApplicationPlanFeaturesListCommand.class,
                        ApplicationPlanFeaturesCreateCommand.class,
                        ApplicationPlanFeaturesDeleteCommand.class
                       }, 
        synopsisSubcommandLabel = "COMMAND")
public class ApplicationPlanFeaturesCommand implements Runnable {
    @Spec CommandSpec spec;

    @Override
    public void run() {
        throw new ParameterException(spec.commandLine(), "Missing required subcommand");
    }
}
