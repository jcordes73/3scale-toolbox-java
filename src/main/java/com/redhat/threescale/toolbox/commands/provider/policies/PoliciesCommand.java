package com.redhat.threescale.toolbox.commands.provider.policies;

import picocli.CommandLine.Command;
import picocli.CommandLine.Spec;
import picocli.CommandLine.Model.CommandSpec;
import picocli.CommandLine.ParameterException;


@Command(name="policies", mixinStandardHelpOptions = true, 
         subcommands = {PoliciesListCommand.class,
                        PolicyGetCommand.class,
                        PolicyCreateCommand.class,
                        PolicyUpdateCommand.class,
                        PolicyDeleteCommand.class
                        }, 
        synopsisSubcommandLabel = "COMMAND")
public class PoliciesCommand implements Runnable {
    @Spec CommandSpec spec;

    @Override
    public void run() {
        throw new ParameterException(spec.commandLine(), "Missing required subcommand");
    }
}
