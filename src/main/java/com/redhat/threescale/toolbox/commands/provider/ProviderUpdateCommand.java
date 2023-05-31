package com.redhat.threescale.toolbox.commands.provider;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;

import com.redhat.threescale.toolbox.rest.client.service.AccountManagementService;

import jakarta.inject.Inject;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

@Command(name="update", mixinStandardHelpOptions = true)
public class ProviderUpdateCommand implements Runnable {

    private static final Logger LOG = Logger.getLogger(ProviderUpdateCommand.class);

    @Inject
    @RestClient
    AccountManagementService accountManagementService;

    @ConfigProperty(name="access_token")
    private String accessToken;

    @Option(names = {"--from-email"}, description = "From email")
    public String fromEmail;

    @Option(names = {"--support-email"}, description = "Support email")
    public String supportEmail;

    @Option(names = {"--finance-support-email"}, description = "Finance support email")
    public String financeSupportEmail;

    @Option(names = {"--site-access-code"}, description = "Site access code")
    public String siteAccessCode;

    @Override
    public void run() {
        try {
            accountManagementService.updateProvider(accessToken, fromEmail, supportEmail, financeSupportEmail, siteAccessCode);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }
}