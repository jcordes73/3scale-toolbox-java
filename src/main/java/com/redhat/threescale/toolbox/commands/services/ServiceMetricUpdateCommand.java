package com.redhat.threescale.toolbox.commands.services;

import org.jboss.logging.Logger;

import com.redhat.threescale.toolbox.picocli.QuotedStringConverter;
import com.redhat.threescale.toolbox.rest.client.service.AccountManagementServiceFactory;

import jakarta.inject.Inject;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

@Command(name="update", mixinStandardHelpOptions = true)
public class ServiceMetricUpdateCommand implements Runnable {

    private static final Logger LOG = Logger.getLogger(ServiceMetricUpdateCommand.class);
    
    @Inject
    AccountManagementServiceFactory accountManagementServiceFactory;

    @Parameters(index = "0", description = "Service ID", arity = "1")
    public int serviceId;

    @Parameters(index = "1", description = "Service Metric ID", arity = "1")
    public int metricId;

    @Option(names = {"--friendly-name"}, description = "Friendly name", arity = "1", converter = QuotedStringConverter.class)
    public String friendlyName;

    @Option(names = {"--unit",}, description = "Unit")
    public String unit;

    @Option(names = {"--description",}, description = "Description", converter = QuotedStringConverter.class)
    public String description;
    

    @Override
    public void run() {
        try {
            accountManagementServiceFactory.getAccountManagementService().updateServiceMetric(serviceId, metricId, friendlyName, unit, description);

        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }
}
