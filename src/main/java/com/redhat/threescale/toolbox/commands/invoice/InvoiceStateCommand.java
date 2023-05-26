package com.redhat.threescale.toolbox.commands.invoice;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;

import com.redhat.threescale.toolbox.rest.client.service.BillingService;

import jakarta.inject.Inject;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

@Command(name="state", mixinStandardHelpOptions = true)
public class InvoiceStateCommand implements Runnable {

    private static final Logger LOG = Logger.getLogger(InvoiceStateCommand.class);

    @Inject
    @RestClient
    BillingService billingService;

    @ConfigProperty(name="access_token")
    private String accessToken;

    @Parameters(index = "0", description = "Invoice ID", arity = "1")
    private int invoiceId;

    @Parameters(index = "1", description = "State. Valid values: ${COMPLETION-CANDIDATES}", arity = "1")
    private BillingService.InvoiceState invoiceState;
    
    @Override
    public void run() {
        try {
            billingService.updateInvoiceState(invoiceId, accessToken, invoiceState);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }
}