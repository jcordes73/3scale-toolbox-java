package com.redhat.threescale.toolbox.commands.provider.authentication;

import com.redhat.threescale.toolbox.rest.client.service.AccountManagementServiceFactory;

import jakarta.inject.Inject;
import picocli.CommandLine.Command;
import picocli.CommandLine.Model.CommandSpec;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;
import picocli.CommandLine.Spec;


@Command(name="update", mixinStandardHelpOptions = true)
public class AuthenticationDeveloperPortalProviderUpdateCommand implements Runnable {

    @Spec
    CommandSpec spec;
    
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
            spec.commandLine().getOut().println(e.getMessage());
        }
    }
}