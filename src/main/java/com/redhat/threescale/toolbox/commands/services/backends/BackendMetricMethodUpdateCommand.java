package com.redhat.threescale.toolbox.commands.services.backends;


import com.redhat.threescale.toolbox.picocli.QuotedStringConverter;
import com.redhat.threescale.toolbox.rest.client.service.AccountManagementServiceFactory;

import jakarta.inject.Inject;
import picocli.CommandLine.Command;
import picocli.CommandLine.Model.CommandSpec;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;
import picocli.CommandLine.Spec;


@Command(name="update", mixinStandardHelpOptions = true)
public class BackendMetricMethodUpdateCommand implements Runnable {

        @Spec
    CommandSpec spec;
    
    @Inject
    AccountManagementServiceFactory accountManagementServiceFactory;

    @Parameters(index = "0", description = "Backend ID", arity = "1")
    public int backendId;

    @Parameters(index = "1", description = "Metric ID", arity = "1")
    public int metricId;

    @Parameters(index = "2", description = "Method ID", arity = "1")
    public int methodId;

    @Option(names = {"--friendly-name",}, description = "Friendly name")
    public String friendlyName;

    @Option(names = {"--unit",}, description = "Unit")
    public String unit;

    @Option(names = {"--description",}, description = "Description", converter = QuotedStringConverter.class)
    public String description;

    @Override
    public void run() {
        try {
            accountManagementServiceFactory.getAccountManagementService().updateBackendMetricMethod(backendId, metricId, methodId, friendlyName, unit, description);
        } catch (Exception e) {
            spec.commandLine().getOut().println(e.getMessage());
        }
    }
}