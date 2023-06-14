package com.redhat.threescale.toolbox.commands.services.features;

import picocli.CommandLine.Command;
import picocli.CommandLine.Spec;
import picocli.CommandLine.Model.CommandSpec;
import picocli.CommandLine.ParameterException;


@Command(name="features", mixinStandardHelpOptions = true, 
         subcommands = {ServiceFeaturesListCommand.class,
                        ServiceFeatureGetCommand.class,
                        ServiceFeatureCreateCommand.class,
                        ServiceFeatureUpdateCommand.class,
                        ServiceFeatureDeleteCommand.class
                        }, 
        synopsisSubcommandLabel = "COMMAND")
public class ServiceFeaturesCommand implements Runnable {
    @Spec CommandSpec spec;

    @Override
    public void run() {
        throw new ParameterException(spec.commandLine(), "Missing required subcommand");
    }
}
