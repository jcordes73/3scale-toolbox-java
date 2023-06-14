package com.redhat.threescale.toolbox.commands.services.backends;

import org.jboss.logging.Logger;

import com.redhat.threescale.toolbox.rest.client.service.AccountManagementServiceFactory;

import jakarta.inject.Inject;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;


@Command(name="create", mixinStandardHelpOptions = true)
public class BackendUsageCreateCommand implements Runnable {

    private static final Logger LOG = Logger.getLogger(BackendUsageCreateCommand.class);

    @Inject
    AccountManagementServiceFactory accountManagementServiceFactory;

    @Parameters(index = "0", description = "Service ID", arity = "1")
    public int serviceId;

    @Parameters(index = "1", description = "Backend ID", arity = "1")
    public int backendId;

    @Parameters(index = "2", description = "Path", arity = "1")
    public String path;

    @Override
    public void run() {
        try {
            accountManagementServiceFactory.getAccountManagementService().createBackendUsage(serviceId, backendId, path);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }        
    }
}