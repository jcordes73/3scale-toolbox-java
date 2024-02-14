package com.redhat.threescale.toolbox.commands.config;

import picocli.CommandLine.Command;
import picocli.CommandLine.Spec;
import picocli.CommandLine.Model.CommandSpec;
import picocli.CommandLine.ParameterException;

@Command(name="settings", mixinStandardHelpOptions = true, 
         subcommands = {SettingsGetCommand.class,
                        SettingsUpdateCommand.class
                       }, 
synopsisSubcommandLabel = "COMMAND")
public class SettingsCommand implements Runnable {

    @Spec CommandSpec spec;

    @Override
    public void run() {
        throw new ParameterException(spec.commandLine(), "Missing required subcommand");
    }
}