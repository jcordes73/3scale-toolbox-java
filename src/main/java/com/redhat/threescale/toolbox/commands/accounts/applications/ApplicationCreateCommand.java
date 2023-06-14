package com.redhat.threescale.toolbox.commands.accounts.applications;

import org.jboss.logging.Logger;

import com.redhat.threescale.toolbox.picocli.QuotedStringConverter;
import com.redhat.threescale.toolbox.rest.client.service.AccountManagementServiceFactory;

import jakarta.inject.Inject;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;


@Command(name="create", mixinStandardHelpOptions = true)
public class ApplicationCreateCommand implements Runnable {

    private static final Logger LOG = Logger.getLogger(ApplicationCreateCommand.class);

    @Inject
    AccountManagementServiceFactory accountManagementServiceFactory;

    @Parameters(index = "0", description = "Plan ID", arity = "1")
    public int planId;

    @Parameters(index = "1", description = "Name", arity = "1")
    public String name;

    @Parameters(index = "2", description = "Description", arity = "1", converter = QuotedStringConverter.class)
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
            accountManagementServiceFactory.getAccountManagementService().createApplication(planId, planId, name, description, userKey, applicationId, applicationKey, redirectUrl, firstTrafficAt, firstDailyTrafficAt);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }       
    }
}