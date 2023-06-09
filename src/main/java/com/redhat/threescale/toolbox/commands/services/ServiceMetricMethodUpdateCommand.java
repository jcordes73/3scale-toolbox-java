package com.redhat.threescale.toolbox.commands.services;

import org.jboss.logging.Logger;

import com.redhat.threescale.toolbox.picocli.QuotedStringConverter;
import com.redhat.threescale.toolbox.rest.client.service.AccountManagementServiceFactory;

import jakarta.inject.Inject;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

@Command(name="update", mixinStandardHelpOptions = true)
public class ServiceMetricMethodUpdateCommand implements Runnable {

    private static final Logger LOG = Logger.getLogger(ServiceMetricMethodUpdateCommand.class);
    
    @Inject
    AccountManagementServiceFactory accountManagementServiceFactory;

    @Parameters(index = "0", description = "Service ID", arity = "1")
    public int serviceId;

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
            accountManagementServiceFactory.getAccountManagementService().updateServiceMetricMethod(serviceId, metricId, methodId, friendlyName, unit, description);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }
}
