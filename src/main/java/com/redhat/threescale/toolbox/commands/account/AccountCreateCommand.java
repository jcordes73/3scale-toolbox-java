package com.redhat.threescale.toolbox.commands.account;

import com.redhat.threescale.toolbox.rest.client.service.AccountManagementService;

import jakarta.inject.Inject;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;
import picocli.CommandLine.Option;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;


@Command(name="create", mixinStandardHelpOptions = true)
public class AccountCreateCommand implements Runnable {

    private static final Logger LOG = Logger.getLogger(AccountCreateCommand.class);

    @Inject
    @RestClient
    AccountManagementService accountManagementService;

    @ConfigProperty(name="access_token")
    private String accessToken;

    @Parameters(index = "0", description = "org_name", arity = "1")
    public String orgName;

    @Parameters(index = "1", description = "username", arity = "1")
    public String userName;

    @Parameters(index = "2", description = "email", arity = "1")
    public String email;

    @Parameters(index = "3", description = "password", arity = "1")
    public String password;
    
    @Option(names = {"--account-plan-id",}, description = "Account Plan ID")
    public Integer accountPlanId;

    @Option(names = {"--service-plan-id",}, description = "Service Plan ID")
    public Integer servicePlanId;

    @Option(names = {"--application-plan-id",}, description = "Application Plan ID")
    public Integer applicationPlanId;
    
    @Option(names = {"--additional-properties",}, description = "Additional Properties")
    public String additionalProperties;

    @Override
    public void run() {

        try {
            accountManagementService.createAccount(accessToken, orgName, userName, email, password, accountPlanId, servicePlanId, applicationPlanId, additionalProperties);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }        
    }
}
