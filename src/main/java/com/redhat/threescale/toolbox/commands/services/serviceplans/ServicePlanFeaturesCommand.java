package com.redhat.threescale.toolbox.commands.services.serviceplans;

import picocli.CommandLine.Command;
import picocli.CommandLine.Spec;
import picocli.CommandLine.Model.CommandSpec;
import picocli.CommandLine.ParameterException;


@Command(name="feature", mixinStandardHelpOptions = true, 
         subcommands = {ServicePlanFeaturesListCommand.class,
                        ServicePlanFeatureCreateCommand.class,
                        ServicePlanFeatureDeleteCommand.class
                        }, 
        synopsisSubcommandLabel = "COMMAND")
public class ServicePlanFeaturesCommand implements Runnable {
    @Spec CommandSpec spec;

    @Override
    public void run() {
        throw new ParameterException(spec.commandLine(), "Missing required subcommand");
    }
}
