package com.redhat.threescale.toolbox.commands.accounts.invoices;

import com.redhat.threescale.toolbox.rest.client.service.BillingServiceFactory;

import jakarta.inject.Inject;
import picocli.CommandLine.Command;
import picocli.CommandLine.Model.CommandSpec;
import picocli.CommandLine.Parameters;
import picocli.CommandLine.Spec;

@Command(name="charge", mixinStandardHelpOptions = true)
public class InvoiceChargeCommand implements Runnable {
    
    @Spec
    CommandSpec spec;
    
    @Inject
    BillingServiceFactory billingServiceFactory;

    @Parameters(index = "0", description = "Invoice ID", arity = "1")
    private int invoiceId;

    @Override
    public void run() {
        try {
            billingServiceFactory.getBillingService().chargeInvoice(invoiceId);
        } catch (Exception e) {
            spec.commandLine().getOut().println(e.getMessage());
        }
    }
}
