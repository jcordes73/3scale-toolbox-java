package com.redhat.threescale.toolbox.commands.provider.account.users;

import picocli.CommandLine.Command;
import picocli.CommandLine.Spec;
import picocli.CommandLine.Model.CommandSpec;
import picocli.CommandLine.ParameterException;


@Command(name="users", mixinStandardHelpOptions = true, 
         subcommands = {UserListCommand.class,
                        UserGetCommand.class,
                        UserCreateCommand.class,
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
public class UsersCommand implements Runnable {
    @Spec CommandSpec spec;

    @Override
    public void run() {
        throw new ParameterException(spec.commandLine(), "Missing required subcommand");
    }
}
