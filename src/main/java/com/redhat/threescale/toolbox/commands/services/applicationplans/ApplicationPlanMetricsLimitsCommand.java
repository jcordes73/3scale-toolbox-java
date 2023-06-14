package com.redhat.threescale.toolbox.commands.services.applicationplans;

import picocli.CommandLine.Command;
import picocli.CommandLine.Spec;
import picocli.CommandLine.Model.CommandSpec;
import picocli.CommandLine.ParameterException;


@Command(name="limits", mixinStandardHelpOptions = true, 
         subcommands = {ApplicationPlanMetricsLimitsListCommand.class,
                        ApplicationPlanMetricsLimitsCreateCommand.class,
                        ApplicationPlanMetricsLimitGetCommand.class,
                        ApplicationPlanMetricsLimitsUpdateCommand.class,
                        ApplicationPlanMetricsLimitsDeleteCommand.class
                       }, 
        synopsisSubcommandLabel = "COMMAND")
public class ApplicationPlanMetricsLimitsCommand implements Runnable {
    @Spec CommandSpec spec;

    @Override
    public void run() {
        throw new ParameterException(spec.commandLine(), "Missing required subcommand");
    }
}
