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

    @POST
    @Path("/providers.xml")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void createTenant(
        @FormParam("access_token") String accessToken,
        @FormParam("org_name") String orgName,
        @FormParam("username") String userName,
        @FormParam("email") String email,
        @FormParam("password") String password
    );

    @GET
    @Path("/providers/{tenantId}.xml")
    @Produces(MediaType.APPLICATION_XML)
    public String getTenant(
        @PathParam("tenantId") int tenantId,
        @QueryParam("access_token") String accessToken
    );

    @PUT
    @Path("/providers/{tenantId}.xml")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void updateTenant(
        @FormParam("tenantId") int tenantId,
        @FormParam("access_token") String accessToken,
        @FormParam("from_email") String fromEmail,
        @FormParam("support_email") String supportEmail,
        @FormParam("finance_support_email") String financeSupportEmail,
        @FormParam("site_access_code") String siteAccessCode,
        @FormParam("state_event") State stateEvent
    );

    @DELETE
    @Path("/providers/{tenantId}.xml")
    public void deleteTenant(
        @PathParam("tenantId") int tenantId,
        @QueryParam("access_token") String accessToken
    );

    @POST
    @Path("/providers/{tenantId}/billing_jobs.xml")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void triggerTenantBillingAll(
        @PathParam("tenantId") int tenantId,
        @FormParam("access_token") String accessToken,
        @FormParam("date") String date
    );

    @POST
    @Path("/providers/{tenantId}/accounts/{accountId}/billing_jobs.xml")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void triggerTenantBillingAccount(
        @PathParam("tenantId") int tenantId,
        @PathParam("accountId") int accountId,
        @FormParam("access_token") String accessToken,
        @FormParam("date") String date
    );
}