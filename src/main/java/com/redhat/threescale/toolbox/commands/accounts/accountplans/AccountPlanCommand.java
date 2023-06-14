package com.redhat.threescale.toolbox.commands.accounts.accountplans;

import picocli.CommandLine.Command;
import picocli.CommandLine.Spec;
import picocli.CommandLine.Model.CommandSpec;
import picocli.CommandLine.ParameterException;


@Command(name="account-plans", mixinStandardHelpOptions = true, 
         subcommands = {AccountPlanListCommand.class,
                        AccountPlanGetCommand.class,
                        AccountPlanCreateCommand.class,
                        AccountPlanUpdateCommand.class,
                        AccountPlanDeleteCommand.class,
                        AccountPlanChangeCommand.class,
                        AccountPlanDefaultCommand.class,
                        AccountPlanFeatureCommand.class
                        }, 
        synopsisSubcommandLabel = "COMMAND")
public class AccountPlanCommand implements Runnable {
    @Spec CommandSpec spec;

    @Override
    public void run() {
        throw new ParameterException(spec.commandLine(), "Missing required subcommand");
    }
}
