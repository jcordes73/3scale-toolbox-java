package com.redhat.threescale.toolbox.commands.accounts.accountplans;

import org.jboss.logging.Logger;

import com.redhat.threescale.toolbox.rest.client.service.AccountManagementServiceFactory;

import jakarta.inject.Inject;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Model.CommandSpec;
import picocli.CommandLine.Spec;

@Command(name="get", mixinStandardHelpOptions = true)
public class AccountPlanGetCommand implements Runnable {

    private static final Logger LOG = Logger.getLogger(AccountPlanGetCommand.class);

    @Spec
    CommandSpec spec;
    
    @Inject
    AccountManagementServiceFactory accountManagementServiceFactory;

    @Option(names= {"--account-id"}, description = "Account ID", arity = "1", defaultValue = Option.NULL_VALUE)
    private Integer accountId;

    @Option(names= {"--account-plan-id"}, description = "Account Plan ID", arity = "1", defaultValue = Option.NULL_VALUE)
    private Integer accountPlanId;

    @Override
    public void run() {
        try {
            String response = null;
            
            if (accountId != null)
                response = accountManagementServiceFactory.getAccountManagementService().getAccountPlanByAccount(accountId.intValue());
            else if (accountPlanId != null)
                response = accountManagementServiceFactory.getAccountManagementService().getAccountPlan(accountPlanId.intValue());
            
            spec.commandLine().getOut().println(response);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }
}