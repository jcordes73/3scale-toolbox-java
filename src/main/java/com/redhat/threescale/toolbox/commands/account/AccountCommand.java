package com.redhat.threescale.toolbox.commands.account;

import picocli.CommandLine.Command;
import picocli.CommandLine.Spec;
import picocli.CommandLine.Model.CommandSpec;
import picocli.CommandLine.ParameterException;


@Command(name="account", mixinStandardHelpOptions = true, 
         subcommands = {AccountListCommand.class,
                        AccountGetCommand.class,
                        AccountFindCommand.class,
                        AccountCreateCommand.class,
                        AccountUpdateCommand.class,
                        AccountDeleteCommand.class,
                        AccountApproveCommand.class,
                        AccountMakePendingCommand.class,
                        AccountRejectCommand.class,
                        AccountMessageCommand.class,
                        AccountPlanCommand.class,
                        AccountServiceContractsCommand.class,
                        AccountPlanChangeCommand.class,
                        AccountCreditCardCommand.class,
                        AccountUserCommand.class,
                        AccountFeatureCommand.class
                        }, 
        synopsisSubcommandLabel = "COMMAND")
public class AccountCommand implements Runnable {
    @Spec CommandSpec spec;

    @Override
    public void run() {
        throw new ParameterException(spec.commandLine(), "Missing required subcommand");
    }
}
