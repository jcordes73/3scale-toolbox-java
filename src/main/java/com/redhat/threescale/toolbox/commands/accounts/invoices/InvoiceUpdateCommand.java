package com.redhat.threescale.toolbox.commands.accounts.invoices;

import com.redhat.threescale.toolbox.rest.client.service.BillingServiceFactory;

import jakarta.inject.Inject;
import picocli.CommandLine.Command;
import picocli.CommandLine.Model.CommandSpec;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;
import picocli.CommandLine.Spec;

@Command(name="update", mixinStandardHelpOptions = true)
public class InvoiceUpdateCommand implements Runnable {    @Spec
    CommandSpec spec;
    
    @Inject
    BillingServiceFactory billingServiceFactory;

    @Parameters(index = "0", description = "Invoice ID", arity = "1")
    private int invoiceId;

    @Option(names = {"--period"}, description = "Period", arity = "1")
    private String period;

    @Option(names = {"--friendly-id"}, description = "Friendly-ID", arity = "1")
    private String friendlyId;
    
    @Override
    public void run() {
        try {
            billingServiceFactory.getBillingService().updateInvoice(invoiceId, period, friendlyId);
        } catch (Exception e) {
            spec.commandLine().getOut().println(e.getMessage());
        }
    }
}