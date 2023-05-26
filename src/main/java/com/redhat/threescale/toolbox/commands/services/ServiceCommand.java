package com.redhat.threescale.toolbox.commands.services;

import picocli.CommandLine.Command;
import picocli.CommandLine.Spec;
import picocli.CommandLine.Model.CommandSpec;
import picocli.CommandLine.ParameterException;


@Command(name="service", mixinStandardHelpOptions = true, 
         subcommands = {ServiceApplicationPlanCommand.class,
                        ServiceApplicationCommand.class
                       }, 
        synopsisSubcommandLabel = "COMMAND")
public class ServiceCommand implements Runnable {
    @Spec CommandSpec spec;

    @Override
    public void run() {
        throw new ParameterException(spec.commandLine(), "Missing required subcommand");
    }
}
