package com.redhat.threescale.toolbox.commands.services;

import org.jboss.logging.Logger;

import com.redhat.threescale.toolbox.rest.client.service.AccountManagementServiceFactory;

import jakarta.inject.Inject;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

@Command(name="create", mixinStandardHelpOptions = true)
public class ServiceMetricMethodCreateCommand implements Runnable {

    private static final Logger LOG = Logger.getLogger(ServiceMetricMethodCreateCommand.class);
    
    @Inject
    AccountManagementServiceFactory accountManagementServiceFactory;

    @Parameters(index = "0", description = "Service ID", arity = "1")
    public int serviceId;

    @Parameters(index = "1", description = "Metric ID", arity = "1")
    public int metricId;

    @Parameters(index = "2", description = "Friendly name", arity = "1")
    public String friendlyName;

    @Parameters(index = "3", description = "Unit", arity = "1")
    public String unit;

    @Option(names = {"--system-name",}, description = "System name")
    public String systemName;

    @Option(names = {"--description",}, description = "Description")
    public String description;
    

    @Override
    public void run() {
        try {
            accountManagementServiceFactory.getAccountManagementService().createServiceMetricMethod(serviceId, metricId, friendlyName, systemName, unit, description);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }
}
