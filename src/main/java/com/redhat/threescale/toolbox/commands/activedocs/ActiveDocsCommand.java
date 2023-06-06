package com.redhat.threescale.toolbox.commands.activedocs;

import picocli.CommandLine.Command;
import picocli.CommandLine.Spec;
import picocli.CommandLine.Model.CommandSpec;
import picocli.CommandLine.ParameterException;


@Command(name="activedocs", mixinStandardHelpOptions = true, 
         subcommands = {ActiveDocsListCommand.class,
                        ActiveDocsGetCommand.class,
                        ActiveDocsCreateCommand.class,
                        ActiveDocsUpdateCommand.class,
                        ActiveDocsDeleteCommand.class
                       }, 
        synopsisSubcommandLabel = "COMMAND")
public class ActiveDocsCommand implements Runnable {
    @Spec CommandSpec spec;

    @Override
    public void run() {
        throw new ParameterException(spec.commandLine(), "Missing required subcommand");
    }
}
