package com.redhat.threescale.toolbox.commands.backend;

import org.jboss.logging.Logger;

import com.redhat.threescale.toolbox.picocli.QuotedStringConverter;
import com.redhat.threescale.toolbox.rest.client.service.AccountManagementServiceFactory;

import jakarta.inject.Inject;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;


@Command(name="create", mixinStandardHelpOptions = true)
public class BackendMetricCreateCommand implements Runnable {

    private static final Logger LOG = Logger.getLogger(BackendMetricCreateCommand.class);

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
            LOG.error(e.getMessage(), e);
        }
    }
}