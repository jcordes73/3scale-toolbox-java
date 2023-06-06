package com.redhat.threescale.toolbox.commands.invoice;

import org.jboss.logging.Logger;

import com.redhat.threescale.toolbox.rest.client.service.BillingServiceFactory;

import jakarta.inject.Inject;
import picocli.CommandLine.Command;
import picocli.CommandLine.Model.CommandSpec;
import picocli.CommandLine.Parameters;
import picocli.CommandLine.Spec;

@Command(name="payment-transactions", mixinStandardHelpOptions = true)
public class InvoicePaymentTransactionsCommand implements Runnable {

    private static final Logger LOG = Logger.getLogger(InvoicePaymentTransactionsCommand.class);

    @Spec
    CommandSpec spec;
        
    @Inject
    BillingServiceFactory billingServiceFactory;

    @Parameters(index = "0", description = "Invoice ID", arity = "1")
    private int invoiceId;
    
    @Override
    public void run() {
        try {
            String response = billingServiceFactory.getBillingService().getInvoicePaymentTransactions(invoiceId);

            spec.commandLine().getOut().println(response);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }
}
