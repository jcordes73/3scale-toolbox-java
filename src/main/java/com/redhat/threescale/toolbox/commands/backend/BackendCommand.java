package com.redhat.threescale.toolbox.commands.backend;

import picocli.CommandLine.Command;
import picocli.CommandLine.Spec;
import picocli.CommandLine.Model.CommandSpec;
import picocli.CommandLine.ParameterException;


@Command(name="backend", mixinStandardHelpOptions = true, 
         subcommands = {BackendListCommand.class,
                        BackendGetCommand.class,
                        BackendCreateCommand.class,
                        BackendUpdateCommand.class,
                        BackendDeleteCommand.class,
                        BackendMappingRulesCommand.class,
                        BackendMetricsCommand.class,
                        BackendUsagesCommand.class
                        }, 
        synopsisSubcommandLabel = "COMMAND")
public class BackendCommand implements Runnable {
    @Spec CommandSpec spec;

    @Override
    public void run() {
        throw new ParameterException(spec.commandLine(), "Missing required subcommand");
    }
}
