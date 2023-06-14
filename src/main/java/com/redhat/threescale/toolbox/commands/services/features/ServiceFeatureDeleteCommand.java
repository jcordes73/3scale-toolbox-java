package com.redhat.threescale.toolbox.commands.services.features;

import org.jboss.logging.Logger;

import com.redhat.threescale.toolbox.rest.client.service.AccountManagementServiceFactory;

import jakarta.inject.Inject;
import picocli.CommandLine.Command;
import picocli.CommandLine.Model.CommandSpec;
import picocli.CommandLine.Parameters;
import picocli.CommandLine.Spec;

@Command(name="delete", mixinStandardHelpOptions = true)
public class ServiceFeatureDeleteCommand implements Runnable {

    private static final Logger LOG = Logger.getLogger(ServiceFeatureDeleteCommand.class);
 
    @Spec
    CommandSpec spec;
    
    @Inject
    AccountManagementServiceFactory accountManagementServiceFactory;
    
    @Parameters(index = "0", description = "Service ID", arity = "1")
    public int serviceId;

    @Parameters(index = "1", description = "Service Feature ID", arity = "1")
    public int serviceFeatureId;

    @Override
    public void run() {
        try {
            accountManagementServiceFactory.getAccountManagementService().deleteServiceFeature(serviceId, serviceFeatureId);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }
}