package com.redhat.threescale.toolbox.commands.accounts;

import com.redhat.threescale.toolbox.commands.accounts.accountplans.AccountPlanCommand;
import com.redhat.threescale.toolbox.commands.accounts.applications.ApplicationsCommand;
import com.redhat.threescale.toolbox.commands.accounts.creditcards.AccountCreditCardCommand;
import com.redhat.threescale.toolbox.commands.accounts.users.AccountUsersCommand;
import com.redhat.threescale.toolbox.commands.accounts.servicecontracts.AccountServiceContractsCommand;
import com.redhat.threescale.toolbox.commands.accounts.features.AccountFeaturesCommand;
import com.redhat.threescale.toolbox.commands.accounts.invoices.InvoicesCommand;
import com.redhat.threescale.toolbox.commands.accounts.message.AccountMessageCommand;

import picocli.CommandLine.Command;
import picocli.CommandLine.Spec;
import picocli.CommandLine.Model.CommandSpec;
import picocli.CommandLine.ParameterException;


@Command(name="accounts", mixinStandardHelpOptions = true, 
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
                        ApplicationsCommand.class,
                        AccountPlanCommand.class,
                        AccountServiceContractsCommand.class,
                        AccountCreditCardCommand.class,
                        AccountUsersCommand.class,
                        AccountFeaturesCommand.class,
                        InvoicesCommand.class
                        }, 
        synopsisSubcommandLabel = "COMMAND")
public class AccountsCommand implements Runnable {
    @Spec CommandSpec spec;

    @Override
    public void run() {
        throw new ParameterException(spec.commandLine(), "Missing required subcommand");
    }
}
