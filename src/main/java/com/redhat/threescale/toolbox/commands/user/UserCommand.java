package com.redhat.threescale.toolbox.commands.user;

import picocli.CommandLine.Command;
import picocli.CommandLine.Spec;
import picocli.CommandLine.Model.CommandSpec;
import picocli.CommandLine.ParameterException;


@Command(name="user", mixinStandardHelpOptions = true, 
         subcommands = {UserGetCommand.class,
                        UserUpdateCommand.class,
                        UserDeleteCommand.class,
                        UserActivateCommand.class,
                        UserAdminCommand.class,
                        UserMemberCommand.class,
                        UserSuspendCommand.class,
                        UserUnsuspendCommand.class,
                        UserPermissionsCommand.class,
                        UserCreateAccessTokenCommand.class
                    }, 
        synopsisSubcommandLabel = "COMMAND")
public class UserCommand implements Runnable {
    @Spec CommandSpec spec;

    @Override
    public void run() {
        throw new ParameterException(spec.commandLine(), "Missing required subcommand");
    }
}
