package com.redhat.threescale.toolbox.rest.client.service;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import io.quarkus.rest.client.reactive.ClientExceptionMapper;
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
import jakarta.ws.rs.core.Response;

@Path("/admin/api/registry")
@RegisterRestClient(configKey = "threescale")
public interface PolicyRegistryService {

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
    @Path("/policies.json")
    @Produces(MediaType.APPLICATION_JSON)
    public String getPolicies();

    @POST
    @Path("/policies.json")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void createPolicy(
        @FormParam("name") String name,
        @FormParam("version") String version,
        @FormParam("schema") String schema
    );

    @GET
    @Path("/policies/{policyId}.json")
    @Produces(MediaType.APPLICATION_JSON)
    public String getPolicy(
        @PathParam("policyId") int policyId
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
        @PathParam("policyId") int policyId
    );  
}