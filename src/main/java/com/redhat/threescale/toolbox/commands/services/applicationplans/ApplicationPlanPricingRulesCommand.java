package com.redhat.threescale.toolbox.commands.services.applicationplans;

import picocli.CommandLine.Command;
import picocli.CommandLine.Spec;
import picocli.CommandLine.Model.CommandSpec;
import picocli.CommandLine.ParameterException;


@Command(name="pricing-rules", mixinStandardHelpOptions = true, 
         subcommands = {ApplicationPlanPricingRulesListCommand.class}, 
        synopsisSubcommandLabel = "COMMAND")
public class ApplicationPlanPricingRulesCommand implements Runnable {
    @Spec CommandSpec spec;

    @Override
    public void run() {
        throw new ParameterException(spec.commandLine(), "Missing required subcommand");
    }
}
