package com.redhat.threescale.toolbox.commands.authentication;

import org.jboss.logging.Logger;

import com.redhat.threescale.toolbox.rest.client.service.AccountManagementServiceFactory;

import jakarta.inject.Inject;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;


@Command(name="create", mixinStandardHelpOptions = true)
public class AuthenticationAdminPortalProviderCreateCommand implements Runnable {

    private static final Logger LOG = Logger.getLogger(AuthenticationAdminPortalProviderCreateCommand.class);

    @Inject
    AccountManagementServiceFactory accountManagementServiceFactory;

    @Parameters(index = "0", description = "kind", arity = "1")
    public String kind;

    @Option(names = {"--name",}, description = "Name")
    public String name;

    @Option(names = {"--system-name",}, description = "System Name")
    public String systemName;

    @Option(names = {"--client-id",}, description = "Client ID")
    public String clientId;

    @Option(names = {"--client-secret",}, description = "Client Secret")
    public String clientSecret;

    @Option(names = {"--site",}, description = "Site or Realm of the authentication provider")
    public String site;

    @Option(names = {"--skip-ssl-certificate-verification",}, description = "Skip SSL certificate verification. False by default")
    public boolean skipSslCertificateVerification;

    @Option(names = {"--published",}, description = "Published authentication provider. False by default")
    public boolean published;

    @Override
    public void run() {
        try {
            accountManagementServiceFactory.getAccountManagementService().createAdminPortalAuthenticationProvider(kind, name, systemName, clientId, clientSecret, site, skipSslCertificateVerification, published);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }
}