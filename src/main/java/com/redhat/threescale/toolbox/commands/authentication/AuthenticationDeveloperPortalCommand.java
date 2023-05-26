package com.redhat.threescale.toolbox.commands.authentication;

import picocli.CommandLine.Command;
import picocli.CommandLine.Spec;
import picocli.CommandLine.Model.CommandSpec;
import picocli.CommandLine.ParameterException;


@Command(name="developer-portal", mixinStandardHelpOptions = true, 
         subcommands = {AuthenticationDeveloperPortalProviderListCommand.class,
                        AuthenticationDeveloperPortalProviderGetCommand.class,
                        AuthenticationDeveloperPortalProviderCreateCommand.class,
                        AuthenticationDeveloperPortalProviderUpdateCommand.class
                       }, 
        synopsisSubcommandLabel = "COMMAND")
public class AuthenticationDeveloperPortalCommand implements Runnable {
    @Spec CommandSpec spec;

    @Override
    public void run() {
        throw new ParameterException(spec.commandLine(), "Missing required subcommand");
    }
}
