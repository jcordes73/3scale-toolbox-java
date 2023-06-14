package com.redhat.threescale.toolbox.commands.accounts;

import org.jboss.logging.Logger;

import com.redhat.threescale.toolbox.rest.client.service.AccountManagementServiceFactory;

import jakarta.inject.Inject;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

@Command(name="update", mixinStandardHelpOptions = true)
public class AccountUpdateCommand implements Runnable {

    private static final Logger LOG = Logger.getLogger(AccountUpdateCommand.class);
    
    @Inject
    AccountManagementServiceFactory accountManagementServiceFactory;

    @Parameters(index = "0", description = "Account ID", arity = "1")
    public int accountId;

    @Parameters(index = "1", description = "org_name", arity = "0..1")
    public String orgName;

    @Option(names = {"--monthly-billing",}, description = "Monthly Billing", defaultValue = Option.NULL_VALUE, negatable = true)
    public Boolean monthlyBillingEnabled;

    @Option(names = {"--monthly-charging",}, description = "Monthly Charging", defaultValue = Option.NULL_VALUE, negatable = true)
    public Boolean monthlyChargingEnabled;

    @Option(names = {"--additional-properties",}, description = "Additional Properties")
    public String additionalProperties;

    @Override
    public void run() {
        try {
            accountManagementServiceFactory.getAccountManagementService().updateAccount(accountId, orgName, monthlyBillingEnabled, monthlyChargingEnabled, additionalProperties);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }
}