package com.redhat.threescale.toolbox.commands.accounts.creditcards;

import picocli.CommandLine.Command;
import picocli.CommandLine.Spec;
import picocli.CommandLine.Model.CommandSpec;
import picocli.CommandLine.ParameterException;


@Command(name="credit-card", mixinStandardHelpOptions = true, 
         subcommands = {AccountSetCreditCardCommand.class,
                        AccountDeleteCreditCardCommand.class
                        }, 
        synopsisSubcommandLabel = "COMMAND")
public class AccountCreditCardCommand implements Runnable {
    @Spec CommandSpec spec;

    @Override
    public void run() {
        throw new ParameterException(spec.commandLine(), "Missing required subcommand");
    }
}
