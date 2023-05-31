package com.redhat.threescale.toolbox.commands.webhooks;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;

import com.redhat.threescale.toolbox.rest.client.service.AccountManagementService;

import jakarta.inject.Inject;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

@Command(name="update", mixinStandardHelpOptions = true)
public class WebhooksUpdateCommand implements Runnable {

    private static final Logger LOG = Logger.getLogger(WebhooksUpdateCommand.class);
    
    @ConfigProperty(name="access_token")
    private String accessToken;
    
    @Inject
    @RestClient
    AccountManagementService accountManagementService;

    @Option(names = {"--url",}, description = "URL that will be notified about all the events")
    public String url;

    @Option(names = {"--active",}, description = "Activate/Disable WebHoks", defaultValue = Option.NULL_VALUE, negatable = true)
    public Boolean active;

    @Option(names = {"--provider-actions",}, description = "Dashboard actions fire webhooks. If false, only user actions in the portal trigger events.", defaultValue = Option.NULL_VALUE, negatable = true)
    public Boolean providerActions;

    @Option(names = {"--account-created-on",}, description = "Account created on", defaultValue = Option.NULL_VALUE, negatable = true)
    public Boolean accountCreatedOn;

    @Option(names = {"--account-updated-on",}, description = "Account updated on", defaultValue = Option.NULL_VALUE, negatable = true)
    public Boolean accountUpdatedOn;

    @Option(names = {"--account-deleted-on",}, description = "Account deleted on", defaultValue = Option.NULL_VALUE, negatable = true)
    public Boolean accountDeletedOn;

    @Option(names = {"--user-created-on",}, description = "User created on", defaultValue = Option.NULL_VALUE, negatable = true)
    public Boolean userCreatedOn;

    @Option(names = {"--user-deleted-on",}, description = "User deleted on", defaultValue = Option.NULL_VALUE, negatable = true)
    public Boolean userDeletedOn;

    @Option(names = {"--user-updated-on",}, description = "User updated on", defaultValue = Option.NULL_VALUE, negatable = true)
    public Boolean userUpdatedOn;

    @Option(names = {"--application-created-on",}, description = "Application created on", defaultValue = Option.NULL_VALUE, negatable = true)
    public Boolean applicationCreatedOn;

    @Option(names = {"--application-updated-on",}, description = "Application updated on", defaultValue = Option.NULL_VALUE, negatable = true)
    public Boolean applicationUpdatedOn;

    @Option(names = {"--application-deleted-on",}, description = "Application deleted on", defaultValue = Option.NULL_VALUE, negatable = true)
    public Boolean applicationDeletedOn;

    @Option(names = {"--account-plan-changed-on",}, description = "Account plan changed on", defaultValue = Option.NULL_VALUE, negatable = true)
    public Boolean accountPlanChangedOn;

    @Option(names = {"--application-plan-changed-on",}, description = "Application plan changed on", defaultValue = Option.NULL_VALUE, negatable = true)
    public Boolean applicationPlanChangedOn;

    @Option(names = {"--application-user-key-updated-on",}, description = "Application user key updated changed", defaultValue = Option.NULL_VALUE, negatable = true)
    public Boolean applicationUserKeyUpdatedOn;

    @Option(names = {"--application-key-created-on",}, description = "Application key created on", defaultValue = Option.NULL_VALUE, negatable = true)
    public Boolean applicationKeyCreatedOn;

    @Option(names = {"--application-key-deleted-on",}, description = "Application key deleted on", defaultValue = Option.NULL_VALUE, negatable = true)
    public Boolean applicationKeyDeletedOn;

    @Option(names = {"--application-suspended-on",}, description = "Application suspended on", defaultValue = Option.NULL_VALUE, negatable = true)
    public Boolean applicationSuspendedOn;

    @Option(names = {"--application-key-updated-on",}, description = "Application key updated on", defaultValue = Option.NULL_VALUE, negatable = true)
    public Boolean applicationKeyUpdatedOn;

    @Override
    public void run() {
        try {
            accountManagementService.updateWebhooks(accessToken, url, active, providerActions, accountCreatedOn, accountUpdatedOn, accountDeletedOn, userCreatedOn, userUpdatedOn, userDeletedOn, applicationCreatedOn, applicationUpdatedOn, applicationDeletedOn, accountPlanChangedOn, applicationPlanChangedOn, applicationUserKeyUpdatedOn, applicationKeyCreatedOn, applicationKeyDeletedOn, applicationSuspendedOn, applicationKeyUpdatedOn);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }
}