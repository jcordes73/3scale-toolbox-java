package com.redhat.threescale.toolbox.commands.services;


import com.redhat.threescale.toolbox.picocli.QuotedStringConverter;
import com.redhat.threescale.toolbox.rest.client.service.AccountManagementService;
import com.redhat.threescale.toolbox.rest.client.service.AccountManagementServiceFactory;

import jakarta.inject.Inject;
import picocli.CommandLine.Command;
import picocli.CommandLine.Model.CommandSpec;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;
import picocli.CommandLine.Spec;

@Command(name="update", mixinStandardHelpOptions = true)
public class ServiceUpdateCommand implements Runnable {

    
 
    @Spec
    CommandSpec spec;
    
    @Inject
    AccountManagementServiceFactory accountManagementServiceFactory;

    @Parameters(index = "0", description = "Service ID", arity = "1")
    public int serviceId;

    @Option(names = {"--name",}, description = "Name")
    public String name;

    @Option(names = {"--description",}, description = "Description", converter = QuotedStringConverter.class)
    public String description;

    @Option(names = {"--deployment-option",}, description = "Deployment option")
    public AccountManagementService.DeploymentOption deploymentOption;

    @Option(names = {"--backend-version",}, description = "Backend version")
    public String authenticationMode;

    @Option(names = {"--system-name",}, description = "System name")
    public String systemName;

    @Option(names = {"--intentions-required",}, description = "Intentions required", defaultValue = Option.NULL_VALUE, negatable = true)
    public Boolean intentionsRequired;

    @Option(names = {"--buyers-manage-apps",}, description = "Buyers can manage applications", defaultValue = Option.NULL_VALUE, negatable = true)
    public Boolean buyersManageApps;
    
    @Option(names = {"--buyers-manage-keys",}, description = "Buyers can manage key", defaultValue = Option.NULL_VALUE, negatable = true)
    public Boolean buyersManageKeys;
    
    @Option(names = {"--referrer-filters-required",}, description = "Referrer filters required", defaultValue = Option.NULL_VALUE, negatable = true)
    public Boolean referrerFiltersRequired;

    @Option(names = {"--custom-keys-enabled",}, description = "Custom keys enabled", defaultValue = Option.NULL_VALUE, negatable = true)
    public Boolean customKeysEnabled;

    @Option(names = {"--buyer-key-generate-enabled",}, description = "Buyer key generated enabled", defaultValue = Option.NULL_VALUE, negatable = true)
    public Boolean buyerKeyGenerateEnabled;

    @Option(names = {"--mandatory-app-key",}, description = "Mandatory app key", defaultValue = Option.NULL_VALUE, negatable = true)
    public Boolean mandatoryAppKey;

    @Option(names = {"--buyer-can-select-plan",}, description = "Buyer can select plan", defaultValue = Option.NULL_VALUE, negatable = true)
    public Boolean buyerCanSelectPlan;

    @Option(names = {"--buyer-plan-change-permission",}, description = "Buyer plan change permission. Valid values: ${COMPLETION-CANDIDATES}")
    public AccountManagementService.BuyerChangePlanPermission buyerChangePlanPermission;


    @Override
    public void run() {
        try {
            accountManagementServiceFactory.getAccountManagementService().updateService(serviceId, name, description, deploymentOption, authenticationMode, systemName, buyersManageApps, buyersManageKeys, referrerFiltersRequired, customKeysEnabled, buyerKeyGenerateEnabled, mandatoryAppKey, buyerCanSelectPlan, buyerChangePlanPermission);
        } catch (Exception e) {
            spec.commandLine().getOut().println(e.getMessage());
        }
    }
}
