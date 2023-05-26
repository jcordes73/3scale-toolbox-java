package com.redhat.threescale.toolbox.commands.invoice;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;

import com.redhat.threescale.toolbox.rest.client.service.BillingService;

import jakarta.inject.Inject;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

@Command(name="charge", mixinStandardHelpOptions = true)
public class InvoiceChargeCommand implements Runnable {

    private static final Logger LOG = Logger.getLogger(InvoiceChargeCommand.class);
    
    @Inject
    @RestClient
    BillingService billingService;

    @ConfigProperty(name="access_token")
    private String accessToken;

    @Parameters(index = "0", description = "Invoice ID", arity = "1")
    private int invoiceId;

    @Override
    public void run() {
        try {
            billingService.chargeInvoice(invoiceId, accessToken);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }
}
