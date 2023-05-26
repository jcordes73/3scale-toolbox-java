package com.redhat.threescale.toolbox.commands.applicationplans;

import picocli.CommandLine.Command;
import picocli.CommandLine.Spec;
import picocli.CommandLine.Model.CommandSpec;
import picocli.CommandLine.ParameterException;


@Command(name="application-plans", mixinStandardHelpOptions = true, 
         subcommands = {ApplicationPlanListCommand.class,
                        ApplicationPlanFeaturesCommand.class,
                        ApplicationPlanLimitsCommand.class,
                        ApplicationPlanMetricsCommand.class,
                        ApplicationPlanPricingRulesCommand.class
                       }, 
        synopsisSubcommandLabel = "COMMAND")
public class ApplicationPlanCommand implements Runnable {
    @Spec CommandSpec spec;

    @Override
    public void run() {
        throw new ParameterException(spec.commandLine(), "Missing required subcommand");
    }
}
