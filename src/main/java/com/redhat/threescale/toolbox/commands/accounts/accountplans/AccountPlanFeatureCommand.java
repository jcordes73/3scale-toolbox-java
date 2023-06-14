package com.redhat.threescale.toolbox.commands.accounts.accountplans;

import picocli.CommandLine.Command;
import picocli.CommandLine.Spec;
import picocli.CommandLine.Model.CommandSpec;
import picocli.CommandLine.ParameterException;


@Command(name="feature", mixinStandardHelpOptions = true, 
         subcommands = {AccountPlanFeatureListCommand.class,
                        AccountPlanFeatureCreateCommand.class,
                        AccountPlanFeatureDeleteCommand.class,
                    }, 
        synopsisSubcommandLabel = "COMMAND")
public class AccountPlanFeatureCommand implements Runnable {
    @Spec CommandSpec spec;

    @Override
    public void run() {
        throw new ParameterException(spec.commandLine(), "Missing required subcommand");
    }
}
