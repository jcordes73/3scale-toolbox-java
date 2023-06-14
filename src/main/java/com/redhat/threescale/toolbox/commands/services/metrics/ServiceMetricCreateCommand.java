package com.redhat.threescale.toolbox.commands.services.metrics;

import org.jboss.logging.Logger;

import com.redhat.threescale.toolbox.picocli.QuotedStringConverter;
import com.redhat.threescale.toolbox.rest.client.service.AccountManagementServiceFactory;

import jakarta.inject.Inject;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

@Command(name="create", mixinStandardHelpOptions = true)
public class ServiceMetricCreateCommand implements Runnable {

    private static final Logger LOG = Logger.getLogger(ServiceMetricCreateCommand.class);
    
    @Inject
    AccountManagementServiceFactory accountManagementServiceFactory;

    @Parameters(index = "0", description = "Service ID", arity = "1")
    public int serviceId;

    @Parameters(index = "1", description = "Friendly name", arity = "1")
    public String friendlyName;

    @Option(names = {"--name",}, description = "Name")
    public String name;

    @Option(names = {"--unit",}, description = "Unit")
    public String unit;

    @Option(names = {"--system-name",}, description = "System name")
    public String systemName;

    @Option(names = {"--description",}, description = "Description", converter = QuotedStringConverter.class)
    public String description;
    

    @Override
    public void run() {
        try {
            accountManagementServiceFactory.getAccountManagementService().createServiceMetric(serviceId, systemName, friendlyName, unit, description);

        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }
}
