package com.redhat.threescale.toolbox.commands.services.activedocs;

import org.jboss.logging.Logger;

import com.redhat.threescale.toolbox.rest.client.service.AccountManagementServiceFactory;

import jakarta.inject.Inject;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

@Command(name="delete", mixinStandardHelpOptions = true)
public class ActiveDocsDeleteCommand implements Runnable {

    private static final Logger LOG = Logger.getLogger(ActiveDocsDeleteCommand.class);

    @Inject
    AccountManagementServiceFactory accountManagementServiceFactory;

    @Parameters(index = "0", description = "Activedoc ID", arity = "1")
    public int activeDocId;

    @Override
    public void run() {
        try {
            accountManagementServiceFactory.getAccountManagementService().deleteActiveDoc(activeDocId);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }
}