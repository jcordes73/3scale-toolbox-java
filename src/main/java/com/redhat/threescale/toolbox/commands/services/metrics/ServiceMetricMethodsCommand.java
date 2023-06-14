package com.redhat.threescale.toolbox.commands.services.metrics;

import picocli.CommandLine.Command;
import picocli.CommandLine.Spec;
import picocli.CommandLine.Model.CommandSpec;
import picocli.CommandLine.ParameterException;


@Command(name="methods", mixinStandardHelpOptions = true, 
         subcommands = {ServiceMetricMethodsListCommand.class,
                        ServiceMetricMethodGetCommand.class,
                        ServiceMetricMethodCreateCommand.class,
                        ServiceMetricMethodUpdateCommand.class,
                        ServiceMetricMethodDeleteCommand.class
                        }, 
        synopsisSubcommandLabel = "COMMAND")
public class ServiceMetricMethodsCommand implements Runnable {
    @Spec CommandSpec spec;

    @Override
    public void run() {
        throw new ParameterException(spec.commandLine(), "Missing required subcommand");
    }
}
