package com.redhat.threescale.toolbox.commands.services.proxy;

import picocli.CommandLine.Command;
import picocli.CommandLine.Spec;
import picocli.CommandLine.Model.CommandSpec;
import picocli.CommandLine.ParameterException;


@Command(name="config", mixinStandardHelpOptions = true, 
         subcommands = {ServiceProxyConfigListCommand.class,
                        ServiceProxyConfigLatestCommand.class,
                        ServiceProxyConfigVersionCommand.class,
                        ServiceProxyConfigVersionPromoteCommand.class
                        }, 
        synopsisSubcommandLabel = "COMMAND")
public class ServiceProxyConfigCommand implements Runnable {
    @Spec CommandSpec spec;

    @Override
    public void run() {
        throw new ParameterException(spec.commandLine(), "Missing required subcommand");
    }
}
