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

@Path("/api")
@RegisterRestClient(configKey = "threescale")
public interface BillingService {

    enum InvoiceState {
        open,
        pending,
        paid,
        unpaid,
        cancelled
    }

    enum LineItemType {
        PlanCost("LineItem::PlanCost"),
        VariableCost("LineItem::VariableCost");

        private String type;

        LineItemType(String type){
            this.type = type;
        }

        public String getType(){
            return type;
        }

        public String toString() {
            return type;
        }
    }

    @GET
    @Path("/accounts/{accountId}/invoices/{invoiceId}.xml")
    @Produces(MediaType.APPLICATION_XML)
    public String getInvoiceByAccount(
        @PathParam("accountId") int accoundId,
        @PathParam("invoiceId") int invoiceId,
        @QueryParam("access_token") String accessToken
    );

    @GET
    @Path("/accounts/{accountId}/invoices.xml")
    @Produces(MediaType.APPLICATION_XML)
    public String getInvoicesByAccount(
        @PathParam("accountId") int accoundId,
        @QueryParam("access_token") String accessToken,
        @QueryParam("state") InvoiceState state,
        @QueryParam("month") String month,
        @QueryParam("page") int page,
        @QueryParam("per_page") int perPage
    );

    @GET
    @Path("/invoices.xml")
    @Produces(MediaType.APPLICATION_XML)
    public String getInvoices(
        @QueryParam("access_token") String accessToken,
        @QueryParam("state") InvoiceState state,
        @QueryParam("month") String month,
        @QueryParam("page") int page,
        @QueryParam("per_page") int perPage
    );

    @POST
    @Path("/invoices.xml")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void createInvoice(
        @FormParam("access_token") String accessToken,
        @FormParam("account_id") int accountId,
        @FormParam("period") String period
    );

    @GET
    @Path("/invoices/{invoiceId}.xml")
    @Produces(MediaType.APPLICATION_XML)
    public String getInvoice(
        @PathParam("invoiceId") int invoiceId,
        @QueryParam("access_token") String access_token
    );

    @PUT
    @Path("/invoices/{invoiceId}.xml")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void updateInvoice(
        @PathParam("invoiceId") int invoiceId,
        @FormParam("access_token") String access_token,
        @FormParam("period") String period,
        @FormParam("friendly_id") String friendlyId
    );

    @PUT
    @Path("/invoices/{invoiceId}/state.xml")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void updateInvoiceState(
        @PathParam("invoiceId") int invoiceId,
        @FormParam("access_token") String access_token,
        @FormParam("state") InvoiceState state
    );
    
    @POST
    @Path("/invoices/{invoiceId}/charge.xml")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void chargeInvoice(
        @PathParam("invoiceId") int invoiceId,
        @FormParam("access_token") String accessToken
    );

    @GET
    @Path("/invoices/{invoiceId}/payment_transactions.xml")
    @Produces(MediaType.APPLICATION_XML)
    public String getInvoicePaymentTransactions(
        @PathParam("invoiceId") int invoiceId,
        @QueryParam("access_token") String access_token
    );

    @GET
    @Path("/invoices/{invoiceId}/line_items.xml")
    @Produces(MediaType.APPLICATION_XML)
    public String getInvoiceLineItems(
        @PathParam("invoiceId") int invoiceId,
        @QueryParam("access_token") String access_token
    );

    @PUT
    @Path("/invoices/{invoiceId}/line_items.xml")
    @Produces(MediaType.APPLICATION_XML)
    public void createInvoiceLineItem(
        @PathParam("invoiceId") int invoiceId,
        @FormParam("access_token") String access_token,
        @FormParam("name") String name,
        @FormParam("description") String description,
        @FormParam("quantity") String quantity,
        @FormParam("cost") String cost,
        @FormParam("metric_id") String metricId,
        @FormParam("contract_id") String contractId,
        @FormParam("type") LineItemType lineItemType,
        @FormParam("plan_id") String planId
    );

    @DELETE
    @Path("/invoices/{invoiceId}/line_items/{lineItemId}.xml")
    public void deleteInvoiceLineItem(
        @PathParam("invoiceId") int invoiceId,
        @PathParam("lineItemId") int lineItemId
    );
}