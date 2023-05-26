package com.redhat.threescale.toolbox.commands.applications;

import com.redhat.threescale.toolbox.rest.client.service.AccountManagementService;

import jakarta.inject.Inject;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;
import picocli.CommandLine.Option;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;


@Command(name="create", mixinStandardHelpOptions = true)
public class ApplicationCreateCommand implements Runnable {

    private static final Logger LOG = Logger.getLogger(ApplicationCreateCommand.class);

    @Inject
    @RestClient
    AccountManagementService accountManagementService;

    @ConfigProperty(name="access_token")
    private String accessToken;

    @Parameters(index = "0", description = "plan_id", arity = "1")
    public int planId;

    @Parameters(index = "1", description = "name", arity = "1")
    public String name;

    @Parameters(index = "2", description = "description", arity = "1")
    public String description;

    @Option(names = {"--user-key",}, description = "User Key (API Key) of the application to be created")
    public String userKey;

    @Option(names = {"--application-id",}, description = "App ID or Client ID (for OAuth and OpenID Connect authentication modes) of the application to be created")
    public String applicationId;

    @Option(names = {"--application-key",}, description = "App Key or Client Secret (for OAuth and OpenID Connect authentication modes) of the application to be created")
    public String applicationKey;

    @Option(names = {"--redirect-url"}, description = "Redirect-URL")
    public String redirectUrl;

    @Option(names = {"--first-traffic-at",}, description = "Timestamp of the first call made by the application")
    public String firstTrafficAt;

    @Option(names = {"--first-daily-traffic-at",}, description = "Timestamp of the first call on the last day when traffic was registered for the application ('Traffic On')")
    public String firstDailyTrafficAt;
    @Override
    public void run() {
        try {
            accountManagementService.createApplication(planId, accessToken, planId, name, description, userKey, applicationId, applicationKey, redirectUrl, firstTrafficAt, firstDailyTrafficAt);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }       
    }
}