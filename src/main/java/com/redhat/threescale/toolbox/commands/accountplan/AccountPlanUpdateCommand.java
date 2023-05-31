package com.redhat.threescale.toolbox.commands.accountplan;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;

import com.redhat.threescale.toolbox.rest.client.service.AccountManagementService;

import jakarta.inject.Inject;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

@Command(name="update", mixinStandardHelpOptions = true)
public class AccountPlanUpdateCommand implements Runnable {

    private static final Logger LOG = Logger.getLogger(AccountPlanUpdateCommand.class);
    
    @Inject
    @RestClient
    AccountManagementService accountManagementService;

    @ConfigProperty(name="access_token")
    private String accessToken;
    
    @Parameters(index = "0", description = "Account Plan ID", arity = "1")
    public int accountPlanId;

    @Option(names = {"--name"}, description = "Name")
    public String name;

    @Option(names = {"--approval-required"}, description = "Approval required")
    public boolean approvalRequired;

    @Option(names = {"--state-event"}, description = "State event. Valid values: ${COMPLETION-CANDIDATES}")
    public AccountManagementService.StateEvent stateEvent;

    @Override
    public void run() {
        try {
            accountManagementService.updateAccountPlan(accountPlanId, accessToken, name, approvalRequired, accessToken);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }
}