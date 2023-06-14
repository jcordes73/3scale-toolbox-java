package com.redhat.threescale.toolbox.commands.accounts.applications;

import picocli.CommandLine.Command;
import picocli.CommandLine.Spec;
import picocli.CommandLine.Model.CommandSpec;
import picocli.CommandLine.ParameterException;


@Command(name="key", 
        mixinStandardHelpOptions = true, 
        subcommands = {ApplicationKeyListCommand.class,
                       ApplicationKeyCreateCommand.class,
                       ApplicationKeyDeleteCommand.class
                      }, 
        synopsisSubcommandLabel = "COMMAND")
public class ApplicationKeyCommand implements Runnable {
    @Spec CommandSpec spec;

    @Override
    public void run() {
        throw new ParameterException(spec.commandLine(), "Missing required subcommand");
    }
}
