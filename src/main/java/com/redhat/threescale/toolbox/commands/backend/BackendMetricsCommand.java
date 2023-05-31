package com.redhat.threescale.toolbox.commands.backend;

import picocli.CommandLine.Command;
import picocli.CommandLine.Spec;
import picocli.CommandLine.Model.CommandSpec;
import picocli.CommandLine.ParameterException;


@Command(name="metrics", mixinStandardHelpOptions = true, 
         subcommands = {BackendMetricsListCommand.class,
                        BackendMetricGetCommand.class,
                        BackendMetricCreateCommand.class,
                        BackendMetricUpdateCommand.class,
                        BackendMetricDeleteCommand.class,
                        BackendMetricMethodsCommand.class
                        }, 
        synopsisSubcommandLabel = "COMMAND")
public class BackendMetricsCommand implements Runnable {
    @Spec CommandSpec spec;

    @Override
    public void run() {
        throw new ParameterException(spec.commandLine(), "Missing required subcommand");
    }
}
