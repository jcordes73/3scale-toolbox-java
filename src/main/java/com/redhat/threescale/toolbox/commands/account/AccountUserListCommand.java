package com.redhat.threescale.toolbox.commands.account;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;

import com.redhat.threescale.toolbox.rest.client.service.AccountManagementService;

import jakarta.inject.Inject;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;
import picocli.CommandLine.Spec;
import picocli.CommandLine.Model.CommandSpec;

@Command(name="list", mixinStandardHelpOptions = true)
public class AccountUserListCommand implements Runnable {

    private static final Logger LOG = Logger.getLogger(AccountUserListCommand.class);

    @Spec
    CommandSpec spec;
    
    @Inject
    @RestClient
    AccountManagementService accountManagementService;

    @ConfigProperty(name="access_token")
    private String accessToken;

    @Parameters(index = "0", description = "Account ID", arity = "1")
    public int accountId;

    @Option(names = {"--state",}, description = "State. Valid values: ${COMPLETION-CANDIDATES}")
    public AccountManagementService.State state;

    @Option(names = {"--role",}, description = "Role of the user. Valid values: ${COMPLETION-CANDIDATES}")
    public AccountManagementService.Role role;

    @Override
    public void run() {
        try {
            String response = accountManagementService.getAccountUsers(accountId, accessToken, state, role);

            spec.commandLine().getOut().println(response);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }
}
