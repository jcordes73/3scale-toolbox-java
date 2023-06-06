package com.redhat.threescale.toolbox.rest.client.service;

import java.net.URI;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.RestClientBuilder;

import com.redhat.threescale.toolbox.rest.client.filter.AccessTokenRequestFilter;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Provider;

@ApplicationScoped
public class AccountManagementServiceFactory {

    @Inject @ConfigProperty(name="threescale.tenant.url")
    Provider<String> baseUri;

    @Inject
    private AccessTokenRequestFilter accessTokenRequestFilter;

    public AccountManagementService getAccountManagementService() throws Exception {
        return RestClientBuilder.newBuilder().register(accessTokenRequestFilter).baseUri(new URI(baseUri.get())).build(AccountManagementService.class);
    }
}