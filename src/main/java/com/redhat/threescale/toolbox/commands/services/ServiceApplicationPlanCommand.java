package com.redhat.threescale.toolbox.commands.services;

import picocli.CommandLine.Command;
import picocli.CommandLine.Spec;
import picocli.CommandLine.Model.CommandSpec;
import picocli.CommandLine.ParameterException;


@Command(name="application-plan", mixinStandardHelpOptions = true, 
         subcommands = {ServiceApplicationPlanListCommand.class,
                        ServiceApplicationPlanGetCommand.class,
                        ServiceApplicationPlanCreateCommand.class,
                        ServiceApplicationPlanUpdateCommand.class,
                        ServiceApplicationPlanDeleteCommand.class,
                        ServiceApplicationPlanDefaultCommand.class
                       }, 
        synopsisSubcommandLabel = "COMMAND")
public class ServiceApplicationPlanCommand implements Runnable {
    @Spec CommandSpec spec;

    @Override
    public void run() {
        throw new ParameterException(spec.commandLine(), "Missing required subcommand");
    }
}
