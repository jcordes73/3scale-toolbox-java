package com.redhat.threescale.toolbox.commands.provider.authentication;

import picocli.CommandLine.Command;
import picocli.CommandLine.Spec;
import picocli.CommandLine.Model.CommandSpec;
import picocli.CommandLine.ParameterException;


@Command(name="admin-portal", mixinStandardHelpOptions = true, 
         subcommands = {AuthenticationAdminPortalProviderListCommand.class,
                        AuthenticationAdminPortalProviderGetCommand.class,
                        AuthenticationAdminPortalProviderCreateCommand.class,
                        AuthenticationAdminPortalProviderUpdateCommand.class
                       }, 
        synopsisSubcommandLabel = "COMMAND")
public class AuthenticationAdminPortalCommand implements Runnable {
    @Spec CommandSpec spec;

    @Override
    public void run() {
        throw new ParameterException(spec.commandLine(), "Missing required subcommand");
    }
}
