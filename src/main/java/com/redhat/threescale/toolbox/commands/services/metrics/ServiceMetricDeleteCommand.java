package com.redhat.threescale.toolbox.commands.services.metrics;

import org.jboss.logging.Logger;

import com.redhat.threescale.toolbox.rest.client.service.AccountManagementServiceFactory;

import jakarta.inject.Inject;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

@Command(name="delete", mixinStandardHelpOptions = true)
public class ServiceMetricDeleteCommand implements Runnable {

    private static final Logger LOG = Logger.getLogger(ServiceMetricDeleteCommand.class);
     
    @Inject
    AccountManagementServiceFactory accountManagementServiceFactory;

    @Parameters(index = "0", description = "Service ID", arity = "1")
    public int serviceId;

    @Parameters(index = "1", description = "Metric ID", arity = "1")
    public int metricId;

    @Override
    public void run() {
        try {
            accountManagementServiceFactory.getAccountManagementService().deleteServiceMetric(serviceId, metricId);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }
}