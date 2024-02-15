package com.redhat.threescale.toolbox.commands.provider.account.personal.accesstokens;

import picocli.CommandLine.Command;
import picocli.CommandLine.Spec;
import picocli.CommandLine.Model.CommandSpec;
import picocli.CommandLine.ParameterException;


@Command(name="access-tokens", mixinStandardHelpOptions = true, 
         subcommands = {ProviderAccountPersonalAccessTokenListCommand.class,
                        ProviderAccountPersonalAccessTokenGetCommand.class,
                        ProviderAccountPersonalAccessTokenCreateCommand.class,
                        ProviderAccountPersonalAccessTokenDeleteCommand.class
                        }, 
        synopsisSubcommandLabel = "COMMAND")
public class ProviderAccountPersonalAccessTokensCommand implements Runnable {
    @Spec CommandSpec spec;

    @Override
    public void run() {
        throw new ParameterException(spec.commandLine(), "Missing required subcommand");
    }
}
