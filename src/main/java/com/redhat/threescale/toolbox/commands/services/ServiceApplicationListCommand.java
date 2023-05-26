package com.redhat.threescale.toolbox.commands.services;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;

import com.redhat.threescale.toolbox.rest.client.service.AccountManagementService;

import jakarta.inject.Inject;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Spec;
import picocli.CommandLine.Model.CommandSpec;

@Command(name="list", mixinStandardHelpOptions = true)
public class ServiceApplicationListCommand implements Runnable {

    private static final Logger LOG = Logger.getLogger(ServiceApplicationListCommand.class);
 
    @Spec
    CommandSpec spec;
    
    @Inject
    @RestClient
    AccountManagementService accountManagementService;
    
    @ConfigProperty(name="access_token")
    private String accessToken;
    
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
            String response = accountManagementService.getServicesApplications(accessToken, page, perPage, activeSince, inActiveSince, serviceId, planId, planType);

            spec.commandLine().getOut().println(response);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }
}