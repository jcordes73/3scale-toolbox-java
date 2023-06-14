package com.redhat.threescale.toolbox.commands.accounts.invoices;

import org.jboss.logging.Logger;

import com.redhat.threescale.toolbox.rest.client.service.BillingService;
import com.redhat.threescale.toolbox.rest.client.service.BillingServiceFactory;

import jakarta.inject.Inject;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

@Command(name="state", mixinStandardHelpOptions = true)
public class InvoiceStateCommand implements Runnable {

    private static final Logger LOG = Logger.getLogger(InvoiceStateCommand.class);

    @Inject
    BillingServiceFactory billingServiceFactory;

    @Parameters(index = "0", description = "Invoice ID", arity = "1")
    private int invoiceId;

    @Parameters(index = "1", description = "State. Valid values: ${COMPLETION-CANDIDATES}", arity = "1")
    private BillingService.InvoiceState invoiceState;
    
    @Override
    public void run() {
        try {
            billingServiceFactory.getBillingService().updateInvoiceState(invoiceId, invoiceState);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }
}