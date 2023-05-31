package com.redhat.threescale.toolbox.commands.services;

import picocli.CommandLine.Command;
import picocli.CommandLine.Spec;
import picocli.CommandLine.Model.CommandSpec;
import picocli.CommandLine.ParameterException;


@Command(name="mapping-rules", mixinStandardHelpOptions = true, 
         subcommands = {ServiceProxyMappingRulesListCommand.class,
                        ServiceProxyMappingRuleGetCommand.class,
                        ServiceProxyMappingRuleCreateCommand.class,
                        ServiceProxyMappingRuleUpdateCommand.class,
                        ServiceProxyMappingRuleDeleteCommand.class
                        }, 
        synopsisSubcommandLabel = "COMMAND")
public class ServiceProxyMappingRulesCommand implements Runnable {
    @Spec CommandSpec spec;

    @Override
    public void run() {
        throw new ParameterException(spec.commandLine(), "Missing required subcommand");
    }
}
