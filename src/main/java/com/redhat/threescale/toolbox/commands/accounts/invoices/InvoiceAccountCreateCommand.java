package com.redhat.threescale.toolbox.commands.accounts.invoices;

import org.jboss.logging.Logger;

import com.redhat.threescale.toolbox.rest.client.service.BillingServiceFactory;

import jakarta.inject.Inject;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

@Command(name="create", mixinStandardHelpOptions = true)
public class InvoiceAccountCreateCommand implements Runnable {

    private static final Logger LOG = Logger.getLogger(InvoiceAccountCreateCommand.class);
    
    @Inject
    BillingServiceFactory billingServiceFactory;

    @Parameters(index = "0", description = "Account ID", arity = "1")
    private int accountId;

    @Option(names = {"--period"}, description = "Period", arity = "1")
    private String period;

    @Override
    public void run() {
        try {
            billingServiceFactory.getBillingService().createInvoice(accountId, period);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }
}
