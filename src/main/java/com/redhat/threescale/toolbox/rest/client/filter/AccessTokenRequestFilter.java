package com.redhat.threescale.toolbox.rest.client.filter;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.client.ClientRequestContext;
import jakarta.ws.rs.client.ClientRequestFilter;
import jakarta.ws.rs.core.Form;
import jakarta.ws.rs.core.UriBuilder;
import jakarta.ws.rs.ext.Provider;

@Provider
@ApplicationScoped
public class AccessTokenRequestFilter implements ClientRequestFilter {

    @Inject @ConfigProperty(name="threescale.tenant.access_token")
    jakarta.inject.Provider<String> accessToken;
    
    @Override
    public void filter(ClientRequestContext requestContext) {
        String method = requestContext.getMethod();

        if ("post".equalsIgnoreCase(method)||"put".equalsIgnoreCase(method)||"patch".equalsIgnoreCase(method)){
            Form form = (Form)requestContext.getEntity();
            form = form.param("access_token", accessToken.get());
        } else {
            requestContext.setUri(UriBuilder.fromUri(requestContext.getUri()).queryParam("access_token", accessToken.get()).build());
        }
    }
}