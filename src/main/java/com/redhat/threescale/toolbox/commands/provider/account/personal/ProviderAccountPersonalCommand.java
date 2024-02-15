package com.redhat.threescale.toolbox.commands.provider.account.personal;

import com.redhat.threescale.toolbox.commands.provider.account.personal.accesstokens.ProviderAccountPersonalAccessTokensCommand;

import picocli.CommandLine.Command;
import picocli.CommandLine.Spec;
import picocli.CommandLine.Model.CommandSpec;
import picocli.CommandLine.ParameterException;


@Command(name="personal", mixinStandardHelpOptions = true, 
         subcommands = {ProviderAccountPersonalAccessTokensCommand.class
                        }, 
        synopsisSubcommandLabel = "COMMAND")
public class ProviderAccountPersonalCommand implements Runnable {
    @Spec CommandSpec spec;

    @Override
    public void run() {
        throw new ParameterException(spec.commandLine(), "Missing required subcommand");
    }
}
