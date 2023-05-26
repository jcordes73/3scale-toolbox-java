package com.redhat.threescale.toolbox.commands.account;

import picocli.CommandLine.Command;
import picocli.CommandLine.Spec;
import picocli.CommandLine.Model.CommandSpec;
import picocli.CommandLine.ParameterException;


@Command(name="user", mixinStandardHelpOptions = true, 
         subcommands = {AccountUserListCommand.class,
                        AccountUserCreateCommand.class
                        }, 
        synopsisSubcommandLabel = "COMMAND")
public class AccountUserCommand implements Runnable {
    @Spec CommandSpec spec;

    @Override
    public void run() {
        throw new ParameterException(spec.commandLine(), "Missing required subcommand");
    }
}
