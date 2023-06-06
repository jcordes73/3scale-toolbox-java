package com.redhat.threescale.toolbox.commands.backend;

import org.jboss.logging.Logger;

import com.redhat.threescale.toolbox.rest.client.service.AccountManagementServiceFactory;

import jakarta.inject.Inject;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

@Command(name="delete", mixinStandardHelpOptions = true)
public class BackendMetricDeleteCommand implements Runnable {

    private static final Logger LOG = Logger.getLogger(BackendMetricDeleteCommand.class);
    
    @Inject
    AccountManagementServiceFactory accountManagementServiceFactory;

    @Parameters(index = "0", description = "Backend ID", arity = "1")
    public int backendId;

    @Parameters(index = "1", description = "Metric ID", arity = "1")
    public int metricId;


    @Override
    public void run() {
        try {
            accountManagementServiceFactory.getAccountManagementService().deleteBackendMetric(backendId, metricId);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }
}