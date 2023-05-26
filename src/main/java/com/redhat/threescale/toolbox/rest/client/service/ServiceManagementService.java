package com.redhat.threescale.toolbox.rest.client.service;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;

@Path("/transactions")
@RegisterRestClient(configKey = "threescale")
public interface ServiceManagementService {
    @GET
    @Path("/authorize.xml")
    void authorize(
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
    void authRep(
        @QueryParam("user_key") String userKey,
        @QueryParam("service_token") String serviceToken,
        @QueryParam("service_id") String serviceId,
        @QueryParam("app_id") String AppId,
        @QueryParam("app_key") String appKey,
        @QueryParam("referrer") String referrer,
        @QueryParam("usage") String usage
    );    
}
