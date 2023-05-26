package com.redhat.threescale.toolbox.rest.client.service;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;

@Path("/admin/api/registry")
@RegisterRestClient(configKey = "threescale")
public interface PolicyRegistryService {

    @GET
    @Path("/policies.json")
    @Produces(MediaType.APPLICATION_JSON)
    public String getPolicies(
        @QueryParam("access_token") String accessToken
    );

    @POST
    @Path("/policies.json")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void createPolicy(
        @FormParam("access_token") String accessToken,
        @FormParam("name") String name,
        @FormParam("version") String version,
        @FormParam("schema") String schema
    );

    @GET
    @Path("/policies/{policyId}.json")
    @Produces(MediaType.APPLICATION_JSON)
    public String getPolicy(
        @PathParam("policyId") int policyId,
        @QueryParam("access_token") String accessToken
    );

    @PUT
    @Path("/policies/{policyId}.json")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void updatePolicy(
        @PathParam("policyId") int policyId,
        @QueryParam("access_token") String accessToken,
        @FormParam("name") String name,
        @FormParam("version") String version,
        @FormParam("schema") String schema
    );
      
    @DELETE
    @Path("/policies/{policyId}.json")
    public void deletePolicy(
        @PathParam("policyId") int policyId,
        @QueryParam("access_token") String accessToken
    );  
}