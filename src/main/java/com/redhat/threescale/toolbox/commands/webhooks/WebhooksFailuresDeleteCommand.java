package com.redhat.threescale.toolbox.commands.webhooks;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;

import com.redhat.threescale.toolbox.rest.client.service.AccountManagementService;

import jakarta.inject.Inject;
import picocli.CommandLine.Command;

@Command(name="delete", mixinStandardHelpOptions = true)
public class WebhooksFailuresDeleteCommand implements Runnable {

    private static final Logger LOG = Logger.getLogger(WebhooksFailuresDeleteCommand.class);
        
    @Inject
    @RestClient
    AccountManagementService accountManagementService;

    @ConfigProperty(name="access_token")
    private String accessToken;

    @Override
    public void run() {
        try {
            accountManagementService.deleteWebhooksFailures(accessToken);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }
}