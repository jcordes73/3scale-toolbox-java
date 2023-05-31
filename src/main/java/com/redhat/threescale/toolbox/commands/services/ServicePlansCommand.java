package com.redhat.threescale.toolbox.commands.services;

import picocli.CommandLine.Command;
import picocli.CommandLine.Spec;
import picocli.CommandLine.Model.CommandSpec;
import picocli.CommandLine.ParameterException;


@Command(name="service-plans", mixinStandardHelpOptions = true, 
         subcommands = {ServicePlansListCommand.class,
                        ServicePlanGetCommand.class,
                        ServicePlanCreateCommand.class,
                        ServicePlanUpdateCommand.class,
                        ServicePlanDeleteCommand.class,
                        ServicePlanDefaultCommand.class
                        }, 
        synopsisSubcommandLabel = "COMMAND")
public class ServicePlansCommand implements Runnable {
    @Spec CommandSpec spec;

    @Override
    public void run() {
        throw new ParameterException(spec.commandLine(), "Missing required subcommand");
    }
}
