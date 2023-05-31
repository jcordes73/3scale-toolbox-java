package com.redhat.threescale.toolbox.commands.account;

import picocli.CommandLine.Command;
import picocli.CommandLine.Spec;
import picocli.CommandLine.Model.CommandSpec;
import picocli.CommandLine.ParameterException;


@Command(name="feature", mixinStandardHelpOptions = true, 
         subcommands = {AccountFeatureListCommand.class,
                        AccountFeatureGetCommand.class,
                        AccountFeatureCreateCommand.class,
                        AccountFeatureDeleteCommand.class
                        }, 
        synopsisSubcommandLabel = "COMMAND")
public class AccountFeatureCommand implements Runnable {
    @Spec CommandSpec spec;

    @Override
    public void run() {
        throw new ParameterException(spec.commandLine(), "Missing required subcommand");
    }
}
