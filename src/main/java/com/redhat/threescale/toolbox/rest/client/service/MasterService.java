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

@Path("/master/api")
@RegisterRestClient(configKey = "threescale")
public interface MasterService {

    enum State {
        make_pending,
        approve,
        reject,
        suspend,
        resume
    }

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

    @POST
    @Path("/providers.xml")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void createTenant(
        @FormParam("org_name") String orgName,
        @FormParam("username") String userName,
        @FormParam("email") String email,
        @FormParam("password") String password
    );

    @GET
    @Path("/providers/{tenantId}.xml")
    @Produces(MediaType.APPLICATION_XML)
    public String getTenant(
        @PathParam("tenantId") int tenantId
    );

    @PUT
    @Path("/providers/{tenantId}.xml")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void updateTenant(
        @FormParam("tenantId") int tenantId,
        @FormParam("from_email") String fromEmail,
        @FormParam("support_email") String supportEmail,
        @FormParam("finance_support_email") String financeSupportEmail,
        @FormParam("site_access_code") String siteAccessCode,
        @FormParam("state_event") State stateEvent
    );

    @DELETE
    @Path("/providers/{tenantId}.xml")
    public void deleteTenant(
        @PathParam("tenantId") int tenantId
    );

    @POST
    @Path("/providers/{tenantId}/billing_jobs.xml")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void triggerTenantBillingAll(
        @PathParam("tenantId") int tenantId,
        @FormParam("date") String date
    );

    @POST
    @Path("/providers/{tenantId}/accounts/{accountId}/billing_jobs.xml")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void triggerTenantBillingAccount(
        @PathParam("tenantId") int tenantId,
        @PathParam("accountId") int accountId,
        @FormParam("date") String date
    );
}