package com.redhat.threescale.toolbox.commands.provider.account;

import org.jboss.logging.Logger;

import com.redhat.threescale.toolbox.rest.client.service.AccountManagementServiceFactory;

import jakarta.inject.Inject;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

@Command(name="update", mixinStandardHelpOptions = true)
public class ProviderAccountUpdateCommand implements Runnable {

    private static final Logger LOG = Logger.getLogger(ProviderAccountUpdateCommand.class);

    @Inject
    AccountManagementServiceFactory accountManagementServiceFactory;

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
            accountManagementServiceFactory.getAccountManagementService().updateProvider(fromEmail, supportEmail, financeSupportEmail, siteAccessCode);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }
}