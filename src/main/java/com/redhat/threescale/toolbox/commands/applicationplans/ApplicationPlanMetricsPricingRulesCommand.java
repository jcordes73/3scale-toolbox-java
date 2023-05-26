package com.redhat.threescale.toolbox.commands.applicationplans;

import picocli.CommandLine.Command;
import picocli.CommandLine.Spec;
import picocli.CommandLine.Model.CommandSpec;
import picocli.CommandLine.ParameterException;


@Command(name="pricing-rules", mixinStandardHelpOptions = true, 
         subcommands = {ApplicationPlanMetricsPricingRulesListCommand.class,
                        ApplicationPlanMetricsPricingRuleCreateCommand.class,
                        ApplicationPlanMetricsPricingRuleDeleteCommand.class
                       }, 
        synopsisSubcommandLabel = "COMMAND")
public class ApplicationPlanMetricsPricingRulesCommand implements Runnable {
    @Spec CommandSpec spec;

    @Override
    public void run() {
        throw new ParameterException(spec.commandLine(), "Missing required subcommand");
    }
}
