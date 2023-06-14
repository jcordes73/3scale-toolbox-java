package com.redhat.threescale.toolbox.commands.accounts.applications;

import picocli.CommandLine.Command;
import picocli.CommandLine.Spec;
import picocli.CommandLine.Model.CommandSpec;
import picocli.CommandLine.ParameterException;


@Command(name="applications", 
        mixinStandardHelpOptions = true, 
        subcommands = { ApplicationFindCommand.class,
                        ApplicationListAllCommand.class, 
                        ApplicationListCommand.class, 
                        ApplicationGetCommand.class,
                        ApplicationCreateCommand.class,
                        ApplicationUpdateCommand.class, 
                        ApplicationAcceptCommand.class,
                        ApplicationChangeApplicationPlanCommand.class, 
                        ApplicationCustomizeApplicationPlanCommand.class,
                        ApplicationDecustomizeApplicationPlanCommand.class,
                        ApplicationResumeCommand.class,
                        ApplicationSuspendCommand.class,
                        ApplicationDeleteCommand.class,
                        ApplicationKeyCommand.class,
                        ApplicationReferrerFilterCommand.class,
                        ApplicationAnalyticsCommand.class
                    }, 
        synopsisSubcommandLabel = "COMMAND")
public class ApplicationsCommand implements Runnable {
    @Spec CommandSpec spec;

    @Override
    public void run() {
        throw new ParameterException(spec.commandLine(), "Missing required subcommand");
    }
}
