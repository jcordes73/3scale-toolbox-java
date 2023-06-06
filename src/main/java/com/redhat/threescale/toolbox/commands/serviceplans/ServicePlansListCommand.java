package com.redhat.threescale.toolbox.commands.serviceplans;

import org.jboss.logging.Logger;

import com.redhat.threescale.toolbox.rest.client.service.AccountManagementServiceFactory;

import jakarta.inject.Inject;
import picocli.CommandLine.Command;
import picocli.CommandLine.Model.CommandSpec;
import picocli.CommandLine.Spec;

@Command(name="list", mixinStandardHelpOptions = true)
public class ServicePlansListCommand implements Runnable {

    private static final Logger LOG = Logger.getLogger(ServicePlansListCommand.class);

    @Spec
    CommandSpec spec;
    
    @Inject
    AccountManagementServiceFactory accountManagementServiceFactory;

    @Override
    public void run() {
        try {
            String response = accountManagementServiceFactory.getAccountManagementService().getServicePlans();

            spec.commandLine().getOut().println(response);
        } catch (Exception e){
            LOG.error(e.getMessage(), e);
        }
    }
}