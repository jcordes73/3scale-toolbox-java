package com.redhat.threescale.toolbox.commands.fielddefinitions;

import picocli.CommandLine.Command;
import picocli.CommandLine.Spec;
import picocli.CommandLine.Model.CommandSpec;
import picocli.CommandLine.ParameterException;


@Command(name="field-definitions", mixinStandardHelpOptions = true, 
         subcommands = {FieldDefinitionsListCommand.class,
                        FieldDefinitionGetCommand.class,
                        FieldDefinitionCreateCommand.class,
                        FieldDefinitionUpdateCommand.class
                        }, 
        synopsisSubcommandLabel = "COMMAND")
public class FieldDefinitionsCommand implements Runnable {
    @Spec CommandSpec spec;

    @Override
    public void run() {
        throw new ParameterException(spec.commandLine(), "Missing required subcommand");
    }
}
