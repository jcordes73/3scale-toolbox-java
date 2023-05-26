package com.redhat.threescale.toolbox.commands.account;

import picocli.CommandLine.Command;
import picocli.CommandLine.Spec;
import picocli.CommandLine.Model.CommandSpec;
import picocli.CommandLine.ParameterException;


@Command(name="plan", mixinStandardHelpOptions = true, 
         subcommands = {AccountPlanGetCommand.class,
                        AccountPlanChangeCommand.class,
                        }, 
        synopsisSubcommandLabel = "COMMAND")
public class AccountPlanCommand implements Runnable {
    @Spec CommandSpec spec;

    @Override
    public void run() {
        throw new ParameterException(spec.commandLine(), "Missing required subcommand");
    }
}
