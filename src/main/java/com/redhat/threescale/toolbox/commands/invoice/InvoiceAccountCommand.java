package com.redhat.threescale.toolbox.commands.invoice;

import picocli.CommandLine.Command;
import picocli.CommandLine.Spec;
import picocli.CommandLine.Model.CommandSpec;
import picocli.CommandLine.ParameterException;


@Command(name="account", mixinStandardHelpOptions = true, 
         subcommands = {InvoiceAccountGetCommand.class,
                        InvoiceAccountListCommand.class,
                        InvoiceAccountCreateCommand.class,
                        }, 
        synopsisSubcommandLabel = "COMMAND")
public class InvoiceAccountCommand implements Runnable {
    @Spec CommandSpec spec;

    @Override
    public void run() {
        throw new ParameterException(spec.commandLine(), "Missing required subcommand");
    }
}
