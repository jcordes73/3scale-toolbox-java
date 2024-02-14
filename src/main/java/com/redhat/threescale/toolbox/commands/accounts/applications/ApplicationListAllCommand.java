package com.redhat.threescale.toolbox.commands.accounts.applications;

import com.redhat.threescale.toolbox.rest.client.service.AccountManagementServiceFactory;

import jakarta.inject.Inject;
import picocli.CommandLine.Command;
import picocli.CommandLine.Model.CommandSpec;
import picocli.CommandLine.Option;
import picocli.CommandLine.Spec;

@Command(name="all", mixinStandardHelpOptions = true)
public class ApplicationListAllCommand implements Runnable {
 
    @Spec
    CommandSpec spec;
    
    @Inject
    AccountManagementServiceFactory accountManagementServiceFactory;

    @Option(names = {"--page",}, description = "Page in the paginates list. Defaults to 1.", defaultValue = "1")
    public int page;

    @Option(names = {"--per-page",}, description = "Number of results per page. Default and max is 500.", defaultValue = "500")
    public int perPage;

    @Option(names = {"--active-since",}, description = "Filter date")
    public String activeSince;

    @Option(names = {"--inactive-since",}, description = "Filter date")
    public String inActiveSince;

    @Option(names = {"--service-id",}, description = "Filter by service")
    public String serviceId;

    @Option(names = {"--plan-id",}, description = "Filter by plan")
    public String planId;

    @Option(names = {"--plan-type",}, description = "Filter by plan type")
    public String planType;

    @Override
    public void run() {
        try {
            String response = accountManagementServiceFactory.getAccountManagementService().getServicesApplications(page, perPage, activeSince, inActiveSince, serviceId, planId, planType);

            spec.commandLine().getOut().println(response);
        } catch (Exception e) {
            spec.commandLine().getOut().println(e.getMessage());
        }
    }
}