package com.redhat.threescale.toolbox.commands.accounts.features;

import org.jboss.logging.Logger;

import com.redhat.threescale.toolbox.rest.client.service.AccountManagementServiceFactory;

import jakarta.inject.Inject;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

@Command(name="delete", mixinStandardHelpOptions = true)
public class AccountFeatureDeleteCommand implements Runnable {

    private static final Logger LOG = Logger.getLogger(AccountFeatureDeleteCommand.class);
    
    @Inject
    AccountManagementServiceFactory accountManagementServiceFactory;

    @Parameters(index = "0", description = "Feature ID", arity = "1")
    public int featureId;

    @Override
    public void run() {
        try {
            accountManagementServiceFactory.getAccountManagementService().deleteAccountFeature(featureId);
        } catch (Exception e){
            LOG.error(e.getMessage(), e);
        }
    }
}
