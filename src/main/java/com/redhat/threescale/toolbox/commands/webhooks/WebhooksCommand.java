package com.redhat.threescale.toolbox.commands.webhooks;

import picocli.CommandLine.Command;
import picocli.CommandLine.Spec;
import picocli.CommandLine.Model.CommandSpec;
import picocli.CommandLine.ParameterException;


@Command(name="webhooks", mixinStandardHelpOptions = true, 
         subcommands = {WebhooksUpdateCommand.class,
                        WebhooksFailuresCommand.class
                        }, 
        synopsisSubcommandLabel = "COMMAND")
public class WebhooksCommand implements Runnable {
    @Spec CommandSpec spec;

    @Override
    public void run() {
        throw new ParameterException(spec.commandLine(), "Missing required subcommand");
    }
}
