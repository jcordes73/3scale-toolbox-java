package com.redhat.threescale.toolbox.commands.backend;

import org.jboss.logging.Logger;

import com.redhat.threescale.toolbox.rest.client.service.AccountManagementServiceFactory;

import jakarta.inject.Inject;
import picocli.CommandLine.Command;
import picocli.CommandLine.Model.CommandSpec;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;
import picocli.CommandLine.Spec;

@Command(name="list", mixinStandardHelpOptions = true)
public class BackendMappingRulesListCommand implements Runnable {

    private static final Logger LOG = Logger.getLogger(BackendMappingRulesListCommand.class);

    @Spec
    CommandSpec spec;
    
    @Inject
    AccountManagementServiceFactory accountManagementServiceFactory;

    @Parameters(index = "0", description = "Backend ID", arity = "1")
    public int backendId;

    @Option(names = {"--page",}, description = "Page in the paginates list. Defaults to 1.", defaultValue = "1")
    public int page;

    @Option(names = {"--per-page",}, description = "Number of results per page. Default and max is 500.", defaultValue = "500")
    public int perPage;

    @Override
    public void run() {
        try {
            String response = accountManagementServiceFactory.getAccountManagementService().getBackendMappingRules(backendId);

            spec.commandLine().getOut().println(response);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }

}