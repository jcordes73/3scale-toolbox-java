package com.redhat.threescale.toolbox.commands.config;

import com.redhat.threescale.toolbox.commands.config.tenants.TenantsCommand;

import picocli.CommandLine.Command;
import picocli.CommandLine.Spec;
import picocli.CommandLine.Model.CommandSpec;
import picocli.CommandLine.ParameterException;

@Command(name="config", mixinStandardHelpOptions = true, 
         subcommands = {TenantsCommand.class,
                        SettingsCommand.class
                       }, 
synopsisSubcommandLabel = "COMMAND")
public class ConfigCommand implements Runnable {

    @Spec CommandSpec spec;

    @Override
    public void run() {
        throw new ParameterException(spec.commandLine(), "Missing required subcommand");
    }
}