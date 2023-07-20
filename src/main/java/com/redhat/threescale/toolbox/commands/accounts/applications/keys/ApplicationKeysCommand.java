package com.redhat.threescale.toolbox.commands.accounts.applications.keys;

import picocli.CommandLine.Command;
import picocli.CommandLine.Spec;
import picocli.CommandLine.Model.CommandSpec;
import picocli.CommandLine.ParameterException;


@Command(name="keys", 
        mixinStandardHelpOptions = true, 
        subcommands = {ApplicationKeyListCommand.class,
                       ApplicationKeyCreateCommand.class,
                       ApplicationKeyDeleteCommand.class
                      }, 
        synopsisSubcommandLabel = "COMMAND")
public class ApplicationKeysCommand implements Runnable {
    @Spec CommandSpec spec;

    @Override
    public void run() {
        throw new ParameterException(spec.commandLine(), "Missing required subcommand");
    }
}
