package com.redhat.threescale.toolbox.commands.invoice;

import picocli.CommandLine.Command;
import picocli.CommandLine.Spec;
import picocli.CommandLine.Model.CommandSpec;
import picocli.CommandLine.ParameterException;


@Command(name="invoice", mixinStandardHelpOptions = true, 
         subcommands = {InvoicesListCommand.class,
                        InvoiceGetCommand.class,
                        InvoiceUpdateCommand.class,
                        InvoiceStateCommand.class,
                        InvoiceChargeCommand.class,
                        InvoicePaymentTransactionsCommand.class,
                        InvoiceLineItemsListCommand.class,
                        InvoiceAccountCommand.class
                        }, 
        synopsisSubcommandLabel = "COMMAND")
public class InvoiceCommand implements Runnable {
    @Spec CommandSpec spec;

    @Override
    public void run() {
        throw new ParameterException(spec.commandLine(), "Missing required subcommand");
    }
}
