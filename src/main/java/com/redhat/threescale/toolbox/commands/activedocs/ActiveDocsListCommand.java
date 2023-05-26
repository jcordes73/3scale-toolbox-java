package com.redhat.threescale.toolbox.commands.activedocs;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;

import com.redhat.threescale.toolbox.rest.client.service.AccountManagementService;

import jakarta.inject.Inject;
import picocli.CommandLine.Command;
import picocli.CommandLine.Spec;
import picocli.CommandLine.Model.CommandSpec;

@Command(name="list", mixinStandardHelpOptions = true)
public class ActiveDocsListCommand implements Runnable {

    private static final Logger LOG = Logger.getLogger(ActiveDocsListCommand.class);

    @Spec
    CommandSpec spec;
    
    @Inject
    @RestClient
    AccountManagementService accountManagementService;

    @ConfigProperty(name="access_token")
    private String accessToken;

    @Override
    public void run() {
        try {
            String response = accountManagementService.getActiveDocs(accessToken);

            spec.commandLine().getOut().println(response);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }
}