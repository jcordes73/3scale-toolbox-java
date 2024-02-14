package com.redhat.threescale.toolbox.commands.accounts.invoices;

import com.redhat.threescale.toolbox.rest.client.service.BillingService;
import com.redhat.threescale.toolbox.rest.client.service.BillingServiceFactory;

import jakarta.inject.Inject;
import picocli.CommandLine.Command;
import picocli.CommandLine.Model.CommandSpec;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;
import picocli.CommandLine.Spec;

@Command(name="list", mixinStandardHelpOptions = true)
public class InvoiceAccountListCommand implements Runnable {    @Spec
    CommandSpec spec;
        
    @Inject
    BillingServiceFactory billingServiceFactory;

    @Parameters(index = "0", description = "Account ID", arity = "1")
    private int accountId;

    @Option(names = {"--month",}, description = "Month.")
    public String month;

    @Option(names = {"--state",}, description = "State. Valid values: ${COMPLETION-CANDIDATES}")
    public BillingService.InvoiceState state;

    @Option(names = {"--page",}, description = "Page in the paginates list. Defaults to 1.", defaultValue = "1")
    public int page;

    @Option(names = {"--per-page",}, description = "Number of results per page. Default and max is 500.", defaultValue = "500")
    public int perPage;

    @Override
    public void run() {
        try {
            String response = billingServiceFactory.getBillingService().getInvoicesByAccount(accountId, state, month, page, perPage);

            spec.commandLine().getOut().println(response);
        } catch (Exception e) {
            spec.commandLine().getOut().println(e.getMessage());
        }
    }
}
