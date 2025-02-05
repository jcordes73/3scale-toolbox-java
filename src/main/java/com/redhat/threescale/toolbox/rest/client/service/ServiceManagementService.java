package com.redhat.threescale.toolbox.rest.client.service;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import io.quarkus.rest.client.reactive.ClientExceptionMapper;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.MultivaluedHashMap;
import jakarta.ws.rs.core.Response;

@Path("/transactions")
@RegisterRestClient(configKey = "threescale")
public interface ServiceManagementService {

    @ClientExceptionMapper
    static RuntimeException toException(Response response) {
        if (response.getStatus() == 401) {
            return new RuntimeException("Unauthorized");
        } else if (response.getStatus() == 403) {
            return new RuntimeException("Access denied");
        } else if (response.getStatus() == 404) {
            return new RuntimeException("Entry not found");
        } else if (response.getStatus() == 500) {
            return new RuntimeException("The remote service responded with HTTP 500");
        }
        
        return null;
    }
    
    @GET
    @Path("/authorize.xml")
    public void authorize(
        @QueryParam("user_key") String userKey,
        @QueryParam("service_token") String serviceToken,
        @QueryParam("service_id") String serviceId,
        @QueryParam("app_id") String AppId,
        @QueryParam("app_key") String appKey,
        @QueryParam("referrer") String referrer,
        @QueryParam("usage") String usage
    );

    @POST
    @Path("/authrep.xml")
    public void authRep(
        @QueryParam("user_key") String userKey,
        @QueryParam("service_token") String serviceToken,
        @QueryParam("service_id") String serviceId,
        @QueryParam("app_id") String AppId,
        @QueryParam("app_key") String appKey,
        @QueryParam("referrer") String referrer,
        @QueryParam("usage") String usage
    );
    
    @GET
    @Path("/oauth_authorize.xml")
    public String oauthAuthorize(
        @QueryParam("service_token") String serviceToken,
        @QueryParam("service_id") String serviceId,
        @QueryParam("app_id") String AppId,
        @QueryParam("app_key") String appKey,
        @QueryParam("referrer") String referrer,
        @QueryParam("usage") String usage,
        @QueryParam("redirect_url") String redirectUrl,
        @QueryParam("redirect_uri") String redirectUri
    );

    @GET
    @Path("/oauth_authrep.xml")
    public String oauthAuthrep(
        @QueryParam("service_token") String serviceToken,
        @QueryParam("service_id") String serviceId,
        @QueryParam("app_id") String AppId,
        @QueryParam("app_key") String appKey,
        @QueryParam("referrer") String referrer,
        @QueryParam("usage") String usage,
        @QueryParam("redirect_url") String redirectUrl,
        @QueryParam("redirect_uri") String redirectUri
    );

    @POST
    @Path("/transactions.xml")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void reportTransactions(
        MultivaluedHashMap<String,String> request
    );
}
