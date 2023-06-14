package com.redhat.threescale.toolbox.commands.provider.webhooks;

import org.jboss.logging.Logger;

import com.redhat.threescale.toolbox.rest.client.service.AccountManagementServiceFactory;

import jakarta.inject.Inject;
import picocli.CommandLine.Command;

@Command(name="delete", mixinStandardHelpOptions = true)
public class WebhooksFailuresDeleteCommand implements Runnable {

    private static final Logger LOG = Logger.getLogger(WebhooksFailuresDeleteCommand.class);
        
    @Inject
    AccountManagementServiceFactory accountManagementServiceFactory;

    @Override
    public void run() {
        try {
            accountManagementServiceFactory.getAccountManagementService().deleteWebhooksFailures();
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }
}