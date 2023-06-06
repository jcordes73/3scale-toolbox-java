package com.redhat.threescale.toolbox.commands.invoice;

import org.jboss.logging.Logger;

import com.redhat.threescale.toolbox.rest.client.service.BillingServiceFactory;

import jakarta.inject.Inject;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

@Command(name="charge", mixinStandardHelpOptions = true)
public class InvoiceChargeCommand implements Runnable {

    private static final Logger LOG = Logger.getLogger(InvoiceChargeCommand.class);
    
    @Inject
    BillingServiceFactory billingServiceFactory;

    @Parameters(index = "0", description = "Invoice ID", arity = "1")
    private int invoiceId;

    @Override
    public void run() {
        try {
            billingServiceFactory.getBillingService().chargeInvoice(invoiceId);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }
}
