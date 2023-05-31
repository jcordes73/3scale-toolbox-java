package com.redhat.threescale.toolbox.commands.serviceplans;

import picocli.CommandLine.Command;
import picocli.CommandLine.Spec;
import picocli.CommandLine.Model.CommandSpec;
import picocli.CommandLine.ParameterException;


@Command(name="service-plans", mixinStandardHelpOptions = true, 
         subcommands = {ServicePlansListCommand.class,
                        ServicePlanFeaturesCommand.class
                        }, 
        synopsisSubcommandLabel = "COMMAND")
public class ServicePlansCommand implements Runnable {
    @Spec CommandSpec spec;

    @Override
    public void run() {
        throw new ParameterException(spec.commandLine(), "Missing required subcommand");
    }
}
