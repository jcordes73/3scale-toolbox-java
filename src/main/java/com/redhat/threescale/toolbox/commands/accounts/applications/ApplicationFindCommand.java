package com.redhat.threescale.toolbox.commands.accounts.applications;

import com.redhat.threescale.toolbox.rest.client.service.AccountManagementServiceFactory;

import jakarta.inject.Inject;
import picocli.CommandLine.Command;
import picocli.CommandLine.Model.CommandSpec;
import picocli.CommandLine.Option;
import picocli.CommandLine.Spec;

@Command(name="find", mixinStandardHelpOptions = true)
public class ApplicationFindCommand implements Runnable {

    @Spec
    CommandSpec spec;
    
    @Inject
    AccountManagementServiceFactory accountManagementServiceFactory;

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
            String response = accountManagementServiceFactory.getAccountManagementService().findServicesApplications(applicationId, userKey, appId, serviceId);

            spec.commandLine().getOut().println(response);
        } catch (Exception e) {
            spec.commandLine().getOut().println(e.getMessage());
        }
    }
}