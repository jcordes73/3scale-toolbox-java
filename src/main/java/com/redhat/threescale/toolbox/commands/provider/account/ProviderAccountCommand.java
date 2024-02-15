package com.redhat.threescale.toolbox.commands.provider.account;

import com.redhat.threescale.toolbox.commands.provider.account.personal.ProviderAccountPersonalCommand;
import com.redhat.threescale.toolbox.commands.provider.account.users.UsersCommand;
import com.redhat.threescale.toolbox.commands.provider.proxies.ProxiesCommand;

import picocli.CommandLine.Command;
import picocli.CommandLine.Spec;
import picocli.CommandLine.Model.CommandSpec;
import picocli.CommandLine.ParameterException;


@Command(name="account", mixinStandardHelpOptions = true, 
         subcommands = {ProviderAccountGetCommand.class,
                        ProviderAccountUpdateCommand.class,
                        ProviderAccountPersonalCommand.class,
                        UsersCommand.class,
                        ProxiesCommand.class
                        }, 
        synopsisSubcommandLabel = "COMMAND")
public class ProviderAccountCommand implements Runnable {
    @Spec CommandSpec spec;

    @Override
    public void run() {
        throw new ParameterException(spec.commandLine(), "Missing required subcommand");
    }
}
