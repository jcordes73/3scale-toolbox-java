package com.redhat.threescale.toolbox.commands.services;

import org.jboss.logging.Logger;

import com.redhat.threescale.toolbox.rest.client.service.AccountManagementServiceFactory;

import jakarta.inject.Inject;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

@Command(name="delete", mixinStandardHelpOptions = true)
public class ServiceMetricMethodDeleteCommand implements Runnable {

    private static final Logger LOG = Logger.getLogger(ServiceMetricMethodDeleteCommand.class);
     
    @Inject
    AccountManagementServiceFactory accountManagementServiceFactory;

    @Parameters(index = "0", description = "Service ID", arity = "1")
    public int serviceId;

    @Parameters(index = "1", description = "Metric ID", arity = "1")
    public int metricId;

    @Parameters(index = "2", description = "Method ID", arity = "1")
    public int methodId;

    @Override
    public void run() {
        try {
            accountManagementServiceFactory.getAccountManagementService().deleteServiceMetricMethod(serviceId, metricId, methodId);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }
}