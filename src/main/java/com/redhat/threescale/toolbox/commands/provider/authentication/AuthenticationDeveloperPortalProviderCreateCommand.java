package com.redhat.threescale.toolbox.commands.provider.authentication;

import com.redhat.threescale.toolbox.rest.client.service.AccountManagementServiceFactory;

import jakarta.inject.Inject;
import picocli.CommandLine.Command;
import picocli.CommandLine.Model.CommandSpec;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;
import picocli.CommandLine.Spec;


@Command(name="create", mixinStandardHelpOptions = true)
public class AuthenticationDeveloperPortalProviderCreateCommand implements Runnable {

    @Spec
    CommandSpec spec;
    
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

    @Option(names = {"--token-url",}, description = "Token URL of the authentication provider")
    public String tokenUrl;

    @Option(names = {"--user-info-url",}, description = "User info URL of the authentication provider")
    public String userInfoUrl;

    @Option(names = {"--authorize-url",}, description = "Authorize URL of the authentication provider")
    public String authorizeUrl;

    @Option(names = {"--identifier-key",}, description = "Identifier key. 'id' by default.")
    public String identifierKey;

    @Option(names = {"--username-key",}, description = "Username key. 'login' by default.")
    public String userNameKey;

    @Option(names = {"--trust-email",}, description = "Trust emails automatically. False by default")
    public boolean trustEmail;

    @Option(names = {"--published",}, description = "Published authentication provider. False by default")
    public boolean published;

    @Option(names = {"--branding-state-event",}, description = "Branding state event of the authentication provider. Only available for Github. It can be either 'brand_as_threescale' (the default one) or 'custom_brand'")
    public String brandingStateEvent;

    @Option(names = {"--skip-ssl-certificate-verification",}, description = "Skip SSL certificate verification. False by default")
    public boolean skipSslCertificateVerification;

    @Option(names = {"--automatically-approve-accounts",}, description = "Automatically approve accounts. False by default.")
    public boolean automaticallApproveAccounts;

    @Override
    public void run() {
        try {
            accountManagementServiceFactory.getAccountManagementService().createDeveloperPortalAuthenticationProvider(kind, name, systemName, clientId, clientSecret, site, tokenUrl, userInfoUrl, authorizeUrl, identifierKey, userNameKey, trustEmail, published, brandingStateEvent, skipSslCertificateVerification, automaticallApproveAccounts);
        } catch (Exception e) {
            spec.commandLine().getOut().println(e.getMessage());
        }        
    }
}