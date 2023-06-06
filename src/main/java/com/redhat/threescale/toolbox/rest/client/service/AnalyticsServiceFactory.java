package com.redhat.threescale.toolbox.rest.client.service;

import java.net.URI;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.RestClientBuilder;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Provider;

@ApplicationScoped
public class AnalyticsServiceFactory {

    @Inject @ConfigProperty(name="threescale.tenant.url")
    Provider<String> baseUri;

    public AnalyticsService getAnalyticsService() throws Exception {
        return RestClientBuilder.newBuilder().baseUri(new URI(baseUri.get())).build(AnalyticsService.class);
    }
}