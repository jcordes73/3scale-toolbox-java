package com.redhat.threescale.toolbox.commands.services;

import picocli.CommandLine.Command;
import picocli.CommandLine.Spec;
import picocli.CommandLine.Model.CommandSpec;
import picocli.CommandLine.ParameterException;


@Command(name="proxy", mixinStandardHelpOptions = true, 
         subcommands = {ServiceProxyGetCommand.class,
                        ServiceProxyUpdateCommand.class,
                        ServiceProxyDeployCommand.class,
                        ServiceProxyMappingRulesCommand.class,
                        ServiceProxyConfigCommand.class,
                        ServiceProxyOidcCommand.class,
                        ServiceProxyPoliciesCommand.class
                        }, 
        synopsisSubcommandLabel = "COMMAND")
public class ServiceProxyCommand implements Runnable {
    @Spec CommandSpec spec;

    @Override
    public void run() {
        throw new ParameterException(spec.commandLine(), "Missing required subcommand");
    }
}
