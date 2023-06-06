package com.redhat.threescale.toolbox.commands.backend;

import org.jboss.logging.Logger;

import com.redhat.threescale.toolbox.rest.client.service.AccountManagementServiceFactory;

import jakarta.inject.Inject;
import picocli.CommandLine.Command;
import picocli.CommandLine.Model.CommandSpec;
import picocli.CommandLine.Option;
import picocli.CommandLine.Spec;

@Command(name="list", mixinStandardHelpOptions = true)
public class BackendListCommand implements Runnable {

    private static final Logger LOG = Logger.getLogger(BackendListCommand.class);

    @Spec
    CommandSpec spec;
    
    @Inject
    AccountManagementServiceFactory accountManagementServiceFactory;

    @Option(names = {"--page",}, description = "Page in the paginates list. Defaults to 1.", defaultValue = "1")
    public int page;

    @Option(names = {"--per-page",}, description = "Number of results per page. Default and max is 500.", defaultValue = "500")
    public int perPage;

    @Override
    public void run() {
        try {
            String response = accountManagementServiceFactory.getAccountManagementService().getBackends(page, perPage);

            spec.commandLine().getOut().println(response);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }
}