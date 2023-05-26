package com.redhat.threescale.toolbox.commands.authentication;

import com.redhat.threescale.toolbox.rest.client.service.AccountManagementService;

import jakarta.inject.Inject;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;
import picocli.CommandLine.Option;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;


@Command(name="update", mixinStandardHelpOptions = true)
public class AuthenticationAdminPortalProviderUpdateCommand implements Runnable {

    private static final Logger LOG = Logger.getLogger(AuthenticationAdminPortalProviderUpdateCommand.class);

    @Inject
    @RestClient
    AccountManagementService accountManagementService;

    @ConfigProperty(name="access_token")
    private String accessToken;

    @Parameters(index = "0", description = "Authentication Provider ID", arity = "1")
    public int authenticationProviderId;

    @Option(names = {"--client-id",}, description = "Client ID")
    public String clientId;

    @Option(names = {"--client-secret",}, description = "Client Secret")
    public String clientSecret;

    @Option(names = {"--site",}, description = "Site or Realm of the authentication provider")
    public String site;

    @Option(names = {"--skip-ssl-certificate-verification",}, description = "Skip SSL certificate verification. False by default")
    public Boolean skipSslCertificateVerification;

    @Option(names = {"--published",}, description = "Published authentication provider. False by default")
    public Boolean published;

    @Override
    public void run() {
        try {
            accountManagementService.updateAdminPortalAuthenticationProvider(authenticationProviderId, accessToken, clientId, clientSecret, site, skipSslCertificateVerification, published);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }

}