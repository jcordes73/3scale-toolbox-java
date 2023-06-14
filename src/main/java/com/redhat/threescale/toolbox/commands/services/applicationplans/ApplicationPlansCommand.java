package com.redhat.threescale.toolbox.commands.services.applicationplans;

import picocli.CommandLine.Command;
import picocli.CommandLine.Spec;
import picocli.CommandLine.Model.CommandSpec;
import picocli.CommandLine.ParameterException;


@Command(name="application-plans", mixinStandardHelpOptions = true, 
         subcommands = {ApplicationPlanListAllCommand.class,
                        ApplicationPlanListCommand.class,
                        ApplicationPlanGetCommand.class,
                        ApplicationPlanCreateCommand.class,
                        ApplicationPlanUpdateCommand.class,
                        ApplicationPlanDeleteCommand.class,
                        ApplicationPlanDefaultCommand.class,
                        ApplicationPlanFeaturesCommand.class,
                        ApplicationPlanLimitsCommand.class,
                        ApplicationPlanMetricsCommand.class,
                        ApplicationPlanPricingRulesCommand.class
                       }, 
        synopsisSubcommandLabel = "COMMAND")
public class ApplicationPlansCommand implements Runnable {
    @Spec CommandSpec spec;

    @Override
    public void run() {
        throw new ParameterException(spec.commandLine(), "Missing required subcommand");
    }
}
