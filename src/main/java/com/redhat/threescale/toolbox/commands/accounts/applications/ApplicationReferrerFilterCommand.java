package com.redhat.threescale.toolbox.commands.accounts.applications;

import picocli.CommandLine.Command;
import picocli.CommandLine.Spec;
import picocli.CommandLine.Model.CommandSpec;
import picocli.CommandLine.ParameterException;


@Command(name="referrer-filter", 
        mixinStandardHelpOptions = true, 
        subcommands = {ApplicationReferrerFilterListCommand.class,
                       ApplicationReferrerFilterCreateCommand.class,
                       ApplicationReferrerFilterDeleteCommand.class
                      }, 
        synopsisSubcommandLabel = "COMMAND")
public class ApplicationReferrerFilterCommand implements Runnable {
    @Spec CommandSpec spec;

    @Override
    public void run() {
        throw new ParameterException(spec.commandLine(), "Missing required subcommand");
    }
}
