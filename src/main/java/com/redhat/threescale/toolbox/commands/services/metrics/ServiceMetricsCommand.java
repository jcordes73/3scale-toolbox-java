package com.redhat.threescale.toolbox.commands.services.metrics;

import picocli.CommandLine.Command;
import picocli.CommandLine.Spec;
import picocli.CommandLine.Model.CommandSpec;
import picocli.CommandLine.ParameterException;


@Command(name="metrics", mixinStandardHelpOptions = true, 
         subcommands = {ServiceMetricsListCommand.class,
                        ServiceMetricGetCommand.class,
                        ServiceMetricCreateCommand.class,
                        ServiceMetricUpdateCommand.class,
                        ServiceMetricDeleteCommand.class,
                        ServiceMetricMethodsCommand.class
                        }, 
        synopsisSubcommandLabel = "COMMAND")
public class ServiceMetricsCommand implements Runnable {
    @Spec CommandSpec spec;

    @Override
    public void run() {
        throw new ParameterException(spec.commandLine(), "Missing required subcommand");
    }
}
