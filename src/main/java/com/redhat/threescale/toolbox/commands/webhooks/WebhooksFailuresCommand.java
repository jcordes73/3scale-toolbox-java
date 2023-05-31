package com.redhat.threescale.toolbox.commands.webhooks;

import picocli.CommandLine.Command;
import picocli.CommandLine.Spec;
import picocli.CommandLine.Model.CommandSpec;
import picocli.CommandLine.ParameterException;


@Command(name="failures", mixinStandardHelpOptions = true, 
         subcommands = {WebhooksFailuresGetCommand.class,
                        WebhooksFailuresDeleteCommand.class
                        }, 
        synopsisSubcommandLabel = "COMMAND")
public class WebhooksFailuresCommand implements Runnable {
    @Spec CommandSpec spec;

    @Override
    public void run() {
        throw new ParameterException(spec.commandLine(), "Missing required subcommand");
    }
}
