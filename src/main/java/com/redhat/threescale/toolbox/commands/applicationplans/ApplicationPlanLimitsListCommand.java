package com.redhat.threescale.toolbox.commands.applicationplans;

import org.jboss.logging.Logger;

import com.redhat.threescale.toolbox.rest.client.service.AccountManagementServiceFactory;

import jakarta.inject.Inject;
import picocli.CommandLine.Command;
import picocli.CommandLine.Model.CommandSpec;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;
import picocli.CommandLine.Spec;

@Command(name="list", mixinStandardHelpOptions = true)
public class ApplicationPlanLimitsListCommand implements Runnable {

    private static final Logger LOG = Logger.getLogger(ApplicationPlanLimitsListCommand.class);

    @Spec
    CommandSpec spec;
    
    @Inject
    AccountManagementServiceFactory accountManagementServiceFactory;

    @Parameters(index = "0", description = "Application Plan ID", arity = "1")
    public int applicationPlanId;

    @Option(names = {"--page",}, description = "Page in the paginates list. Defaults to 1.", defaultValue = "1")
    public int page;

    @Option(names = {"--per-page",}, description = "Number of results per page. Default and max is 50.", defaultValue = "50")
    public int perPage;


    @Override
    public void run() {
        try {
            String response = accountManagementServiceFactory.getAccountManagementService().getApplicationPlanLimits(applicationPlanId, page, perPage);

            spec.commandLine().getOut().println(response);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }
}