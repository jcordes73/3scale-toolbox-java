package com.redhat.threescale.toolbox.commands.accountplan;

import picocli.CommandLine.Command;
import picocli.CommandLine.Spec;
import picocli.CommandLine.Model.CommandSpec;
import picocli.CommandLine.ParameterException;


@Command(name="account-plan", mixinStandardHelpOptions = true, 
         subcommands = {AccountPlanListCommand.class,
                        AccountPlanCreateCommand.class,
                        AccountPlanGetCommand.class,
                        AccountPlanUpdateCommand.class,
                        AccountPlanDeleteCommand.class,
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
