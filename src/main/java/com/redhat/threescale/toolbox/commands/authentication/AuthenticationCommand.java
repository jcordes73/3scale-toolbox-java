package com.redhat.threescale.toolbox.commands.authentication;

import picocli.CommandLine.Command;
import picocli.CommandLine.Spec;
import picocli.CommandLine.Model.CommandSpec;
import picocli.CommandLine.ParameterException;


@Command(name="authentication", mixinStandardHelpOptions = true, 
         subcommands = {AuthenticationAdminPortalCommand.class,
                        AuthenticationDeveloperPortalCommand.class}, 
        synopsisSubcommandLabel = "COMMAND")
public class AuthenticationCommand implements Runnable {
    @Spec CommandSpec spec;

    @Override
    public void run() {
        throw new ParameterException(spec.commandLine(), "Missing required subcommand");
    }
}
