package com.redhat.threescale.toolbox.commands.serviceplans;

import org.jboss.logging.Logger;

import com.redhat.threescale.toolbox.rest.client.service.AccountManagementServiceFactory;

import jakarta.inject.Inject;
import picocli.CommandLine.Command;
import picocli.CommandLine.Model.CommandSpec;
import picocli.CommandLine.Parameters;
import picocli.CommandLine.Spec;

@Command(name="list", mixinStandardHelpOptions = true)
public class ServicePlanFeaturesListCommand implements Runnable {

    private static final Logger LOG = Logger.getLogger(ServicePlansListCommand.class);

    @Spec
    CommandSpec spec;
    
    @Inject
    AccountManagementServiceFactory accountManagementServiceFactory;

    @Parameters(index = "0", description = "Service Plan ID", arity = "1")
    private int servicePlanId;

    @Override
    public void run() {
        try {
            String response = accountManagementServiceFactory.getAccountManagementService().getServicePlanFeatures(servicePlanId);

            spec.commandLine().getOut().println(response);
        } catch (Exception e){
            LOG.error(e.getMessage(), e);
        }
    }
}