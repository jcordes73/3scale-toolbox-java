package com.redhat.threescale.toolbox.commands.accounts.servicecontracts;

import picocli.CommandLine.Command;
import picocli.CommandLine.Spec;
import picocli.CommandLine.Model.CommandSpec;
import picocli.CommandLine.ParameterException;


@Command(name="service-contracts", 
        mixinStandardHelpOptions = true, 
        subcommands = {AccountServiceContractsListCommand.class,
                       AccountServiceContractsDeleteCommand.class
                    }, 
        synopsisSubcommandLabel = "COMMAND")
public class AccountServiceContractsCommand implements Runnable {
    @Spec CommandSpec spec;

    @Override
    public void run() {
        throw new ParameterException(spec.commandLine(), "Missing required subcommand");
    }
}
