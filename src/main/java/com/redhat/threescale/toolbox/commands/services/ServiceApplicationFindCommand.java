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

@Command(name="find", mixinStandardHelpOptions = true)
public class ServiceApplicationFindCommand implements Runnable {

    private static final Logger LOG = Logger.getLogger(ServiceApplicationFindCommand.class);
    
    @Spec
    CommandSpec spec;
    
    @Inject
    @RestClient
    AccountManagementService accountManagementService;

    @ConfigProperty(name="access_token")
    private String accessToken;
    
    @Option(names = {"--application-id",}, description = "Application ID")
    public String applicationId;

    @Option(names = {"--user-key",}, description = "User key")
    public String userKey;

    @Option(names = {"--app-id",}, description = "app_id of the application (for app_id/app_key and oauth authentication modes)")
    public String appId;

    @Option(names = {"--service-id",}, description = "Service ID")
    public String serviceId;

    @Override
    public void run() {
        try {
            String response = accountManagementService.findServicesApplications(accessToken, applicationId, userKey, appId, serviceId);

            spec.commandLine().getOut().println(response);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }
}