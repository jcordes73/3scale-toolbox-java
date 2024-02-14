package com.redhat.threescale.toolbox.commands.services.backends;

import com.redhat.threescale.toolbox.picocli.QuotedStringConverter;
import com.redhat.threescale.toolbox.rest.client.service.AccountManagementServiceFactory;

import jakarta.inject.Inject;
import picocli.CommandLine.Command;
import picocli.CommandLine.Model.CommandSpec;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;
import picocli.CommandLine.Spec;


@Command(name="create", mixinStandardHelpOptions = true)
public class BackendMetricCreateCommand implements Runnable {

    @Spec
    CommandSpec spec;
    
    @Inject
    AccountManagementServiceFactory accountManagementServiceFactory;

    @Parameters(index = "0", description = "Backend ID", arity = "1")
    public int backendId;

    @Parameters(index = "1", description = "Friendly name", arity = "1")
    public String friendlyName;

    @Parameters(index = "2", description = "unit", arity = "1")
    public String unit;

    @Option(names = {"--system-name",}, description = "System name")
    public String systemName;

    @Option(names = {"--description",}, description = "Description", converter = QuotedStringConverter.class)
    public String description;

    @Override
    public void run() {
        try {
            accountManagementServiceFactory.getAccountManagementService().createBackendMetric(backendId, friendlyName, systemName, unit, description);
        } catch (Exception e) {
            spec.commandLine().getOut().println(e.getMessage());
        }
    }
}