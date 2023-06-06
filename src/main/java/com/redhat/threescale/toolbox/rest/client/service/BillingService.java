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
    @Path("/accounts/{accountId}/invoices/{invoiceId}.xml")
    @Produces(MediaType.APPLICATION_XML)
    public String getInvoiceByAccount(
        @PathParam("accountId") int accoundId,
        @PathParam("invoiceId") int invoiceId
    );

    @GET
    @Path("/accounts/{accountId}/invoices.xml")
    @Produces(MediaType.APPLICATION_XML)
    public String getInvoicesByAccount(
        @PathParam("accountId") int accoundId,
        @QueryParam("state") InvoiceState state,
        @QueryParam("month") String month,
        @QueryParam("page") int page,
        @QueryParam("per_page") int perPage
    );

    @GET
    @Path("/invoices.xml")
    @Produces(MediaType.APPLICATION_XML)
    public String getInvoices(
        @QueryParam("state") InvoiceState state,
        @QueryParam("month") String month,
        @QueryParam("page") int page,
        @QueryParam("per_page") int perPage
    );

    @POST
    @Path("/invoices.xml")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void createInvoice(
        @FormParam("account_id") int accountId,
        @FormParam("period") String period
    );

    @GET
    @Path("/invoices/{invoiceId}.xml")
    @Produces(MediaType.APPLICATION_XML)
    public String getInvoice(
        @PathParam("invoiceId") int invoiceId
    );

    @PUT
    @Path("/invoices/{invoiceId}.xml")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void updateInvoice(
        @PathParam("invoiceId") int invoiceId,
        @FormParam("period") String period,
        @FormParam("friendly_id") String friendlyId
    );

    @PUT
    @Path("/invoices/{invoiceId}/state.xml")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void updateInvoiceState(
        @PathParam("invoiceId") int invoiceId,
        @FormParam("state") InvoiceState state
    );
    
    @POST
    @Path("/invoices/{invoiceId}/charge.xml")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void chargeInvoice(
        @PathParam("invoiceId") int invoiceId
    );

    @GET
    @Path("/invoices/{invoiceId}/payment_transactions.xml")
    @Produces(MediaType.APPLICATION_XML)
    public String getInvoicePaymentTransactions(
        @PathParam("invoiceId") int invoiceId
    );

    @GET
    @Path("/invoices/{invoiceId}/line_items.xml")
    @Produces(MediaType.APPLICATION_XML)
    public String getInvoiceLineItems(
        @PathParam("invoiceId") int invoiceId
    );

    @PUT
    @Path("/invoices/{invoiceId}/line_items.xml")
    @Produces(MediaType.APPLICATION_XML)
    public void createInvoiceLineItem(
        @PathParam("invoiceId") int invoiceId,
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