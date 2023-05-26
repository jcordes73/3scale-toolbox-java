package com.redhat.threescale.toolbox.commands.invoice;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;

import com.redhat.threescale.toolbox.helpers.XPathExecution;
import com.redhat.threescale.toolbox.rest.client.service.BillingService;

import jakarta.inject.Inject;
import picocli.CommandLine.ArgGroup;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Spec;
import picocli.CommandLine.Model.CommandSpec;

@Command(name="list", mixinStandardHelpOptions = true)
public class InvoicesListCommand implements Runnable {

    private static final Logger LOG = Logger.getLogger(InvoiceAccountListCommand.class);

    @Spec
    CommandSpec spec;
    
    @Inject
    XPathExecution xPathExecution;
    
    @Inject
    @RestClient
    BillingService billingService;

    @ArgGroup(exclusive = true, multiplicity = "0..1")
    FormattingOptions formattingOptions;

    static class FormattingOptions {
        @Option(names = {"--xpath","-x"}, description = "XPath expression", arity = "0..1")
        String xpathExpression;

        @Option(names = {"--prettyprint","-p"}, description = "Format output", arity = "0..1")
        boolean prettyPrint;
    }

    @ConfigProperty(name="access_token")
    private String accessToken;

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
            String response = billingService.getInvoices(accessToken, state, month, page, perPage);

            if (formattingOptions !=null){
                if (formattingOptions.prettyPrint)
                    response = xPathExecution.prettyPrint(response);

                if (formattingOptions.xpathExpression != null)
                    response = xPathExecution.execute(response, formattingOptions.xpathExpression);
            }

            spec.commandLine().getOut().println(response);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }
}
