package com.redhat.threescale.toolbox.commands.services;

import com.redhat.threescale.toolbox.commands.services.activedocs.ActiveDocsCommand;
import com.redhat.threescale.toolbox.commands.services.applicationplans.ApplicationPlansCommand;
import com.redhat.threescale.toolbox.commands.services.backends.BackendsCommand;
import com.redhat.threescale.toolbox.commands.services.features.ServiceFeaturesCommand;
import com.redhat.threescale.toolbox.commands.services.metrics.ServiceMetricsCommand;
import com.redhat.threescale.toolbox.commands.services.proxy.ServiceProxyCommand;
import com.redhat.threescale.toolbox.commands.services.serviceplans.ServicePlansCommand;

import picocli.CommandLine.Command;
import picocli.CommandLine.Spec;
import picocli.CommandLine.Model.CommandSpec;
import picocli.CommandLine.ParameterException;


@Command(name="services", mixinStandardHelpOptions = true, 
         subcommands = {ServicesListCommand.class,
                        ServiceGetCommand.class,
                        ServiceCreateCommand.class,
                        ServiceUpdateCommand.class,
                        ServiceDeleteCommand.class,
                        ServicePlansCommand.class,
                        ActiveDocsCommand.class,
                        BackendsCommand.class,
                        ApplicationPlansCommand.class,
                        ServiceFeaturesCommand.class,
                        ServiceMetricsCommand.class,
                        ServiceProxyCommand.class
                       }, 
        synopsisSubcommandLabel = "COMMAND")
public class ServicesCommand implements Runnable {
    @Spec CommandSpec spec;

    @Override
    public void run() {
        throw new ParameterException(spec.commandLine(), "Missing required subcommand");
    }
}