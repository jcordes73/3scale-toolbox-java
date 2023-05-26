package com.redhat.threescale.toolbox;

import com.redhat.threescale.toolbox.commands.account.AccountCommand;
import com.redhat.threescale.toolbox.commands.accountplan.AccountPlanCommand;
import com.redhat.threescale.toolbox.commands.activedocs.ActiveDocsCommand;
import com.redhat.threescale.toolbox.commands.applicationplans.ApplicationPlanCommand;
import com.redhat.threescale.toolbox.commands.applications.ApplicationCommand;
import com.redhat.threescale.toolbox.commands.authentication.AuthenticationCommand;
import com.redhat.threescale.toolbox.commands.backend.BackendCommand;
import com.redhat.threescale.toolbox.commands.config.ConfigCommand;
import com.redhat.threescale.toolbox.commands.invoice.InvoiceCommand;
import com.redhat.threescale.toolbox.commands.policy.PolicyCommand;
import com.redhat.threescale.toolbox.commands.services.ServiceCommand;
import com.redhat.threescale.toolbox.commands.user.UserCommand;

import io.quarkus.picocli.runtime.annotations.TopCommand;
import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;
import jakarta.inject.Inject;
import picocli.CommandLine;
import picocli.CommandLine.Command;

@QuarkusMain
@TopCommand
@Command(name="",mixinStandardHelpOptions = true,
         subcommands = {ConfigCommand.class,
                        AccountCommand.class,
                        AccountPlanCommand.class,
                        UserCommand.class,
                        ApplicationPlanCommand.class,
                        ApplicationCommand.class,
                        ServiceCommand.class,
                        BackendCommand.class,
                        ActiveDocsCommand.class,
                        AuthenticationCommand.class,
                        InvoiceCommand.class,
                        PolicyCommand.class
                        },
                        synopsisSubcommandLabel = "COMMAND")
public class Toolbox implements Runnable, QuarkusApplication {

    @Inject
    CommandLine.IFactory factory;

    @Override
    public void run() {
        CommandLine commandLine = new CommandLine(this, factory);
        commandLine.usage(System.out);
    }

    @Override
    public int run(String... args) throws Exception {
        int exitCode = new CommandLine(this, factory).execute(args);

        return exitCode;
    }
}