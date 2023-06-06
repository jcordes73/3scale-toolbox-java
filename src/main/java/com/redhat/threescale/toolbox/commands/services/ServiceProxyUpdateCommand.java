package com.redhat.threescale.toolbox.commands.services;

import org.jboss.logging.Logger;

import com.redhat.threescale.toolbox.rest.client.service.AccountManagementService;
import com.redhat.threescale.toolbox.rest.client.service.AccountManagementServiceFactory;

import jakarta.inject.Inject;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

@Command(name="update", mixinStandardHelpOptions = true)
public class ServiceProxyUpdateCommand implements Runnable {

    private static final Logger LOG = Logger.getLogger(ServiceProxyUpdateCommand.class);
    
    @Inject
    AccountManagementServiceFactory accountManagementServiceFactory;

    @Parameters(index = "0", description = "Service ID", arity = "1")
    public int serviceId;

    @Option(names = {"--endpoint"}, description="Endpoint", arity = "1")
    public String endpoint;
    
    @Option(names = {"--api-backend"}, description="Private Base URL. Caution! Do not use it if the account has API as a Product enabled. It may cause API backend usages to be removed from the Product.", arity = "1")
    public String apiBackend;

    @Option(names = {"--credentials-location"}, description="Credentials location. Valid values: ${COMPLETION-CANDIDATES}", arity = "1")
    public AccountManagementService.CredentialsLocation credentialsLocation;

    @Option(names = {"--auth-app-key"}, description="Parameter/Header where App Key is expected.", arity = "1")
    public String authAppKey;

    @Option(names = {"--auth-app-id"}, description="Parameter/Header where App ID is expected.", arity = "1")
    public String authAppId;

    @Option(names = {"--auth-user-key"}, description="Parameter/Header where User Key is expected.", arity = "1")
    public String authUserKey;

    @Option(names = {"--error-auth-failed"}, description="Error message on failed authentication.", arity = "1")
    public String errorAuthFailed;

    @Option(names = {"--error-status-auth-failed"}, description="Status code on failed authentication.", arity = "1")
    public Integer errorStatusAuthFailed;

    @Option(names = {"--error-headers-auth-failed"}, description="Content-Type header on failed authentication.", arity = "1")
    public String errorHeadersAuthFailed;

    @Option(names = {"--error-auth-missing"}, description="Error message on missing authentication.", arity = "1")
    public String errorAuthMissing;

    @Option(names = {"--error-status-auth-missing"}, description="Status code on missing authentication.", arity = "1")
    public Integer errorStatusAuthMissing;

    @Option(names = {"--error-headers-auth-missing"}, description="Content-Type header on missing authentication.", arity = "1")
    public String errorHeadersAuthMissing;

    @Option(names = {"--error-no-match"}, description="Error message when no mapping rule is matched.", arity = "1")
    public String errorNoMatch;

    @Option(names = {"--error-status-no-match"}, description="Content-Type header when no mapping rule is matched.", arity = "1")
    public String errorStatusNoMatch;
    
    @Option(names = {"--error-status-limits-exceeded"}, description="Status code when usage limit exceeded.", arity = "1")
    public Integer errorStatusLimitsExceeded;

    @Option(names = {"--error-headers-limits-exceeded"}, description="Content-Type header when usage limit exceeded.", arity = "1")
    public String errorHeadersLimitsExceeded;

    @Option(names = {"--error-limits-exceeded"}, description="Error message on usage limit exceeded.", arity = "1")
    public String errorLimitsExceeded;

    @Option(names = {"--oidc-issuer-endpoint"}, description="Location of your OpenID Provider.", arity = "1")
    public String oidcIssuerEndpoint;

    @Option(names = {"--oidc-issuer-type"}, description="Type of your OpenID Provider.", arity = "1")
    public String oidcIssuerType;

    @Option(names = {"--sandbox-endpoint"}, description="Sandbox endpoint.", arity = "1")
    public String sandboxEndpoint;
    
    @Option(names = {"--jwt-claim-with-client-id"}, description="JWT Claim With ClientId Location.", arity = "1")
    public String jwtClaimWithClientId;

    @Option(names = {"--jwt-claim-with-client-id-type"}, description="JWT Claim With ClientId type.Valid values: ${COMPLETION-CANDIDATES}", arity = "1")
    public AccountManagementService.ClientIdType jwtClaimWithClientIdType;

    @Override
    public void run() {
        try {
            accountManagementServiceFactory.getAccountManagementService().updateServiceProxy(serviceId, endpoint, apiBackend, credentialsLocation, authAppKey, authAppId, authUserKey, errorAuthFailed, errorStatusAuthFailed, errorHeadersAuthFailed, errorAuthMissing, errorStatusAuthMissing, errorHeadersAuthMissing, errorNoMatch, errorStatusNoMatch, errorStatusLimitsExceeded, errorHeadersLimitsExceeded, errorLimitsExceeded, oidcIssuerEndpoint, oidcIssuerType, sandboxEndpoint, jwtClaimWithClientId, jwtClaimWithClientIdType);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }
}