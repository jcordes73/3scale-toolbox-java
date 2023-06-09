package com.redhat.threescale.toolbox.commands.services.proxy;

import picocli.CommandLine.Command;
import picocli.CommandLine.Spec;
import picocli.CommandLine.Model.CommandSpec;
import picocli.CommandLine.ParameterException;


@Command(name="policies", mixinStandardHelpOptions = true, 
         subcommands = {ServiceProxyPoliciesListCommand.class,
                        ServiceProxyPoliciesUpdateCommand.class,
                        }, 
        synopsisSubcommandLabel = "COMMAND")
public class ServiceProxyPoliciesCommand implements Runnable {
    @Spec CommandSpec spec;

    @Override
    public void run() {
        throw new ParameterException(spec.commandLine(), "Missing required subcommand");
    }
}
