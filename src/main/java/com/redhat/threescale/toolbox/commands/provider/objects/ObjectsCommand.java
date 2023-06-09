package com.redhat.threescale.toolbox.commands.provider.objects;

import picocli.CommandLine.Command;
import picocli.CommandLine.Spec;
import picocli.CommandLine.Model.CommandSpec;
import picocli.CommandLine.ParameterException;


@Command(name="objects", mixinStandardHelpOptions = true, 
         subcommands = {ObjectStatusCommand.class,
                        }, 
        synopsisSubcommandLabel = "COMMAND")
public class ObjectsCommand implements Runnable {
    @Spec CommandSpec spec;

    @Override
    public void run() {
        throw new ParameterException(spec.commandLine(), "Missing required subcommand");
    }
}
