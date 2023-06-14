package com.redhat.threescale.toolbox.commands.accounts.features;

import org.jboss.logging.Logger;

import com.redhat.threescale.toolbox.rest.client.service.AccountManagementServiceFactory;

import jakarta.inject.Inject;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;


@Command(name="create", mixinStandardHelpOptions = true)
public class AccountFeatureCreateCommand implements Runnable {

    private static final Logger LOG = Logger.getLogger(AccountFeatureCreateCommand.class);

    @Inject
    AccountManagementServiceFactory accountManagementServiceFactory;

    @Parameters(index = "0", description = "name", arity = "1")
    public String name;

    @Option(names = {"--system-name",}, description = "System name")
    public String systemName;

    @Override
    public void run() {

        try {
            accountManagementServiceFactory.getAccountManagementService().createAccountFeature(name, systemName);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }        
    }
}
