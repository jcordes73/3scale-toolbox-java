package com.redhat.threescale.toolbox.commands.config;

import org.jboss.logging.Logger;

import com.redhat.threescale.toolbox.rest.client.service.AccountManagementServiceFactory;

import jakarta.inject.Inject;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

@Command(name="update", mixinStandardHelpOptions = true)
public class SettingsUpdateCommand implements Runnable {

    private static final Logger LOG = Logger.getLogger(SettingsUpdateCommand.class);
        
    @Inject
    AccountManagementServiceFactory accountManagementServiceFactory;

    @Option(names = {"--user-account-area-enabled",}, description = "Allow the user to edit their submitted details, change passwords, etc", defaultValue = Option.NULL_VALUE, negatable = true)
    public Boolean userAccountAreaEnabled;

    @Option(names = {"--hide-service",}, description = "Used a default service plan", defaultValue = Option.NULL_VALUE, negatable = true)
    public Boolean hideService;

    @Option(names = {"--signups-enabled",}, description = "Developers are allowed sign up themselves.", defaultValue = Option.NULL_VALUE, negatable = true)
    public Boolean signupEnabled;

    @Option(names = {"--account-approval-required",}, description = "Approval is required by you before developer accounts are activated.", defaultValue = Option.NULL_VALUE, negatable = true)
    public Boolean accountApprovalRequired;

    @Option(names = {"--strong-passwords-enabled",}, description = "Require strong passwords from your users: Password must be at least 8 characters long, and contain both upper and lowercase letters, a digit and one special character of -+=><_$#.:;!?@&*()~][}{|. Existing passwords will still work.", defaultValue = Option.NULL_VALUE, negatable = true)
    public Boolean strongPasswordsEnabled;

    @Option(names = {"--public-search",}, description = "Enables public search on Developer Portal", defaultValue = Option.NULL_VALUE, negatable = true)
    public Boolean publicSearch;

    @Option(names = {"--account-plans-ui-visible",}, description = "Enables visibility of Account Plans", defaultValue = Option.NULL_VALUE, negatable = true)
    public Boolean accountPlansUiVisible;

    @Option(names = {"--change-account-plan-permission",}, description = "Enables visibility of Service Plans", defaultValue = Option.NULL_VALUE, negatable = true)
    public Boolean changeAccountPlanPermission;

    @Option(names = {"--change-service-plan-permission",}, description = "Service Plans changing")
    public String changeServicePlanPermission;

    @Override
    public void run() {
        try {
            accountManagementServiceFactory.getAccountManagementService().updateSettings(userAccountAreaEnabled, hideService, signupEnabled, accountApprovalRequired, strongPasswordsEnabled, publicSearch, accountPlansUiVisible, changeAccountPlanPermission, changeServicePlanPermission);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }
}
