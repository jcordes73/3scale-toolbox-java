package com.redhat.threescale.toolbox.commands.accounts.applications;

import org.jboss.logging.Logger;

import com.redhat.threescale.toolbox.picocli.QuotedStringConverter;
import com.redhat.threescale.toolbox.rest.client.service.AccountManagementServiceFactory;

import jakarta.inject.Inject;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

@Command(name="update", mixinStandardHelpOptions = true)
public class ApplicationUpdateCommand implements Runnable {

    private static final Logger LOG = Logger.getLogger(ApplicationUpdateCommand.class);

    @Inject
    AccountManagementServiceFactory accountManagementServiceFactory;

    @Parameters(index = "0", description = "Account ID", arity = "1")
    private int accountId;

    @Parameters(index = "1", description = "Application ID", arity = "1")
    private int applicationId;

    @Parameters(index = "2", description = "Application Name", arity = "1")
    private String applicationName;

    @Option(names = {"--description"}, description = "Description", converter = QuotedStringConverter.class)
    public String description;

    @Option(names = {"--redirect-url"}, description = "Redirect-URL")
    public String redirectUrl;

    @Option(names = {"--first-traffic-at",}, description = "Timestamp of the first call made by the application")
    public String firstTrafficAt;

    @Option(names = {"--first-daily-traffic-at",}, description = "Timestamp of the first call on the last day when traffic was registered for the application ('Traffic On')")
    public String firstDailyTrafficAt;
    
    @Override
    public void run() {
        try {
            accountManagementServiceFactory.getAccountManagementService().updateApplication(accountId, applicationId, applicationName, description, redirectUrl, firstTrafficAt, firstDailyTrafficAt);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }
}