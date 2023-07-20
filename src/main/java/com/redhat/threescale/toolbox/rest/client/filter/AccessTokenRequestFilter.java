package com.redhat.threescale.toolbox.rest.client.filter;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.client.ClientRequestContext;
import jakarta.ws.rs.client.ClientRequestFilter;
import jakarta.ws.rs.core.MultivaluedHashMap;
import jakarta.ws.rs.core.UriBuilder;
import jakarta.ws.rs.ext.Provider;

@Provider
@ApplicationScoped
public class AccessTokenRequestFilter implements ClientRequestFilter {

    @Inject @ConfigProperty(name="threescale.tenant.access_token")
    jakarta.inject.Provider<String> accessToken;

    @Inject @ConfigProperty(name="threescale.tenant.provider_key")
    jakarta.inject.Provider<String> providerKey;
    
    @Override
    public void filter(ClientRequestContext requestContext) {
        String method = requestContext.getMethod();

        if ("post".equalsIgnoreCase(method)||"put".equalsIgnoreCase(method)||"patch".equalsIgnoreCase(method)){
            MultivaluedHashMap form = (MultivaluedHashMap)requestContext.getEntity();

            if (form != null){
                if (!"undefined".equals(accessToken.get()))
                    form.add("access_token", accessToken.get());
                else if (!"undefined".equals(providerKey.get()))
                    form.add("provider_key", providerKey.get());
            } else {
                if (!"undefined".equals(accessToken.get()))
                    requestContext.setUri(UriBuilder.fromUri(requestContext.getUri()).queryParam("access_token", accessToken.get()).build());
                else if (!"undefined".equals(providerKey.get()))
                    requestContext.setUri(UriBuilder.fromUri(requestContext.getUri()).queryParam("provider_key", providerKey.get()).build());    
            }
        } else {
            if (!"undefined".equals(accessToken.get()))
                requestContext.setUri(UriBuilder.fromUri(requestContext.getUri()).queryParam("access_token", accessToken.get()).build());
            else if (!"undefined".equals(providerKey.get()))
                requestContext.setUri(UriBuilder.fromUri(requestContext.getUri()).queryParam("provider_key", providerKey.get()).build());
        }
    }
}