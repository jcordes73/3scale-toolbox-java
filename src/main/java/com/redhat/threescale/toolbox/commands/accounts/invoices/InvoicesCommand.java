package com.redhat.threescale.toolbox.commands.accounts.invoices;

import picocli.CommandLine.Command;
import picocli.CommandLine.Spec;
import picocli.CommandLine.Model.CommandSpec;
import picocli.CommandLine.ParameterException;


@Command(name="invoices", mixinStandardHelpOptions = true, 
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
public class InvoicesCommand implements Runnable {
    @Spec CommandSpec spec;

    @Override
    public void run() {
        throw new ParameterException(spec.commandLine(), "Missing required subcommand");
    }
}
