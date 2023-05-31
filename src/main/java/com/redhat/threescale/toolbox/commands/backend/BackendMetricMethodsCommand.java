package com.redhat.threescale.toolbox.commands.backend;

import picocli.CommandLine.Command;
import picocli.CommandLine.Spec;
import picocli.CommandLine.Model.CommandSpec;
import picocli.CommandLine.ParameterException;


@Command(name="methods", mixinStandardHelpOptions = true, 
         subcommands = {BackendMetricMethodsListCommand.class,
                        BackendMetricMethodGetCommand.class,
                        BackendMetricMethodCreateCommand.class,
                        BackendMetricMethodUpdateCommand.class
                        }, 
        synopsisSubcommandLabel = "COMMAND")
public class BackendMetricMethodsCommand implements Runnable {
    @Spec CommandSpec spec;

    @Override
    public void run() {
        throw new ParameterException(spec.commandLine(), "Missing required subcommand");
    }
}
