package com.redhat.threescale.toolbox.commands.accountplan;

import com.redhat.threescale.toolbox.rest.client.service.AccountManagementService;

import jakarta.inject.Inject;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;


@Command(name="create", mixinStandardHelpOptions = true)
public class AccountPlanCreateCommand implements Runnable {

    private static final Logger LOG = Logger.getLogger(AccountPlanCreateCommand.class);

    @Inject
    @RestClient
    AccountManagementService accountManagementService;

    @ConfigProperty(name="access_token")
    private String accessToken;

    @Parameters(index = "0", description = "name", arity = "1")
    public String name;

    @Option(names = {"--approval-required",}, description = "Approval required")
    public boolean approvalRequired;

    @Option(names = {"--system-name",}, description = "System name")
    public String systemName;

    @Option(names = {"--state-event",}, description = "State event")
    public AccountManagementService.StateEvent stateEvent;
    
    @Override
    public void run() {

        try {
            accountManagementService.createAccountPlan(accessToken, name, approvalRequired, systemName, stateEvent);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        
    }
}