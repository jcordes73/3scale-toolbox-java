package com.redhat.threescale.toolbox.commands.services.backends;

import org.jboss.logging.Logger;

import com.redhat.threescale.toolbox.rest.client.service.AccountManagementServiceFactory;

import jakarta.inject.Inject;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;


@Command(name="update", mixinStandardHelpOptions = true)
public class BackendUsageUpdateCommand implements Runnable {

    private static final Logger LOG = Logger.getLogger(BackendUsageUpdateCommand.class);

    @Inject
    AccountManagementServiceFactory accountManagementServiceFactory;

    @Parameters(index = "0", description = "System ID", arity = "1")
    public int systemId;

    @Parameters(index = "1", description = "Backend Usage ID", arity = "1")
    public int backendUsageId;

    @Option(names = {"--path",}, description = "Path")
    public String path;

    @Override
    public void run() {
        try {
            accountManagementServiceFactory.getAccountManagementService().updateBackendUsage(systemId, backendUsageId, path);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }        
    }
}