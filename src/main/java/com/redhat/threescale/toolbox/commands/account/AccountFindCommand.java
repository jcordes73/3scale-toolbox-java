package com.redhat.threescale.toolbox.commands.account;

import org.jboss.logging.Logger;

import com.redhat.threescale.toolbox.rest.client.service.AccountManagementServiceFactory;

import jakarta.inject.Inject;
import jakarta.validation.constraints.Email;
import picocli.CommandLine.Command;
import picocli.CommandLine.Model.CommandSpec;
import picocli.CommandLine.Option;
import picocli.CommandLine.Spec;

@Command(name="find", mixinStandardHelpOptions = true)
public class AccountFindCommand implements Runnable {

    private static final Logger LOG = Logger.getLogger(AccountFindCommand.class);

    @Spec
    CommandSpec spec;
    
    @Inject
    AccountManagementServiceFactory accountManagementServiceFactory;

    @Option(names = {"--username"}, description = "username", arity = "0..1")
    private String userName;

    @Option(names = {"--email"}, description = "email", arity = "0..1")
    private String email;

    @Option(names = {"--user-id"}, description = "user_id", arity = "0..1")
    private String userId;

    @Override
    public void run() {
        try {
            String response = accountManagementServiceFactory.getAccountManagementService().findAccount(userName, email, userId);

            spec.commandLine().getOut().println(response);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }
}