package com.redhat.threescale.toolbox.commands.invoice;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;

import com.redhat.threescale.toolbox.rest.client.service.BillingService;

import jakarta.inject.Inject;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

@Command(name="update", mixinStandardHelpOptions = true)
public class InvoiceUpdateCommand implements Runnable {

    private static final Logger LOG = Logger.getLogger(InvoiceUpdateCommand.class);

    @Inject
    @RestClient
    BillingService billingService;

    @ConfigProperty(name="access_token")
    private String accessToken;

    @Parameters(index = "0", description = "Invoice ID", arity = "1")
    private int invoiceId;

    @Option(names = {"--period"}, description = "Period", arity = "1")
    private String period;

    @Option(names = {"--friendly-id"}, description = "Friendly-ID", arity = "1")
    private String friendlyId;
    
    @Override
    public void run() {
        try {
            billingService.updateInvoice(invoiceId, accessToken, period, friendlyId);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }
}