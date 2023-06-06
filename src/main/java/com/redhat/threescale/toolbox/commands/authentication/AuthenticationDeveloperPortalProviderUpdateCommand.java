package com.redhat.threescale.toolbox.commands.authentication;

import org.jboss.logging.Logger;

import com.redhat.threescale.toolbox.rest.client.service.AccountManagementServiceFactory;

import jakarta.inject.Inject;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;


@Command(name="update", mixinStandardHelpOptions = true)
public class AuthenticationDeveloperPortalProviderUpdateCommand implements Runnable {

    private static final Logger LOG = Logger.getLogger(AuthenticationDeveloperPortalProviderUpdateCommand.class);

    @Inject
    AccountManagementServiceFactory accountManagementServiceFactory;

    @Parameters(index = "0", description = "Authentication Provider ID", arity = "1")
    public int authenticationProviderId;

    @Option(names = {"--client-id",}, description = "Client ID")
    public String clientId;

    @Option(names = {"--client-secret",}, description = "Client Secret")
    public String clientSecret;

    @Option(names = {"--site",}, description = "Site or Realm of the authentication provider")
    public String site;

    @Option(names = {"--published",}, description = "Published authentication provider. False by default")
    public Boolean published;

    @Option(names = {"--skip-ssl-certificate-verification",}, description = "Skip SSL certificate verification. False by default")
    public Boolean skipSslCertificateVerification;

    @Option(names = {"--automatically-approve-accounts",}, description = "Automatically approve accounts. False by default.")
    public Boolean automaticallyApproveAccounts;

    @Override
    public void run() {
        try {
            accountManagementServiceFactory.getAccountManagementService().updateDeveloperPortalAuthenticationProvider(authenticationProviderId, clientId, clientSecret, site, published, skipSslCertificateVerification, automaticallyApproveAccounts);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }
}