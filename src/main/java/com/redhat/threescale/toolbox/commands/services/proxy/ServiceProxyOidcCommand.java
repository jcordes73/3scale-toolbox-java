package com.redhat.threescale.toolbox.commands.services.proxy;

import picocli.CommandLine.Command;
import picocli.CommandLine.Spec;
import picocli.CommandLine.Model.CommandSpec;
import picocli.CommandLine.ParameterException;


@Command(name="oidc", mixinStandardHelpOptions = true, 
         subcommands = {ServiceProxyOidcGetCommand.class,
                        ServiceProxyOidcUpdateCommand.class
                        }, 
        synopsisSubcommandLabel = "COMMAND")
public class ServiceProxyOidcCommand implements Runnable {
    @Spec CommandSpec spec;

    @Override
    public void run() {
        throw new ParameterException(spec.commandLine(), "Missing required subcommand");
    }
}
