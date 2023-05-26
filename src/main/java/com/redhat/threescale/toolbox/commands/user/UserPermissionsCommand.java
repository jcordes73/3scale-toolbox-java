package com.redhat.threescale.toolbox.commands.user;

import picocli.CommandLine.Command;
import picocli.CommandLine.Spec;
import picocli.CommandLine.Model.CommandSpec;
import picocli.CommandLine.ParameterException;


@Command(name="permissions", mixinStandardHelpOptions = true, 
         subcommands = {UserPermissionsGetCommand.class,
                        UserPermissionsUpdateCommand.class
                    }, 
        synopsisSubcommandLabel = "COMMAND")
public class UserPermissionsCommand implements Runnable {
    @Spec CommandSpec spec;

    @Override
    public void run() {
        throw new ParameterException(spec.commandLine(), "Missing required subcommand");
    }
}
