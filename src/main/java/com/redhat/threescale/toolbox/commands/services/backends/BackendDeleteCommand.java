package com.redhat.threescale.toolbox.commands.services.backends;

import org.jboss.logging.Logger;

import com.redhat.threescale.toolbox.rest.client.service.AccountManagementServiceFactory;

import jakarta.inject.Inject;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

@Command(name="delete", mixinStandardHelpOptions = true)
public class BackendDeleteCommand implements Runnable {

    private static final Logger LOG = Logger.getLogger(BackendDeleteCommand.class);

    @Inject
    AccountManagementServiceFactory accountManagementServiceFactory;

    @Parameters(index = "0", description = "Backend ID", arity = "1")
    public int backendId;

    @Override
    public void run() {
        try {
            accountManagementServiceFactory.getAccountManagementService().deleteBackend(backendId);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }
}