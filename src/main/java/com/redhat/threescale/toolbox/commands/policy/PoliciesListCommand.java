package com.redhat.threescale.toolbox.commands.policy;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;

import com.redhat.threescale.toolbox.helpers.XPathExecution;
import com.redhat.threescale.toolbox.rest.client.service.PolicyRegistryService;

import jakarta.inject.Inject;
import picocli.CommandLine.ArgGroup;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Spec;
import picocli.CommandLine.Model.CommandSpec;

@Command(name="list", mixinStandardHelpOptions = true)
public class PoliciesListCommand implements Runnable {

    private static final Logger LOG = Logger.getLogger(PoliciesListCommand.class);

    @Spec
    CommandSpec spec;
    
    @Inject
    XPathExecution xPathExecution;
    
    @Inject
    @RestClient
    PolicyRegistryService policyRegistryService;

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

    @Override
    public void run() {
        try {
            String response = policyRegistryService.getPolicies(accessToken);

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
