package com.redhat.threescale.toolbox.commands.accounts.features;

import picocli.CommandLine.Command;
import picocli.CommandLine.Spec;
import picocli.CommandLine.Model.CommandSpec;
import picocli.CommandLine.ParameterException;


@Command(name="features", mixinStandardHelpOptions = true, 
         subcommands = {AccountFeatureListCommand.class,
                        AccountFeatureGetCommand.class,
                        AccountFeatureCreateCommand.class,
                        AccountFeatureDeleteCommand.class
                        }, 
        synopsisSubcommandLabel = "COMMAND")
public class AccountFeaturesCommand implements Runnable {
    @Spec CommandSpec spec;

    @Override
    public void run() {
        throw new ParameterException(spec.commandLine(), "Missing required subcommand");
    }
}
