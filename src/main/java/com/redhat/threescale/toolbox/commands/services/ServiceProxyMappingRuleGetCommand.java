package com.redhat.threescale.toolbox.commands.services;

import org.jboss.logging.Logger;

import com.redhat.threescale.toolbox.rest.client.service.AccountManagementServiceFactory;

import jakarta.inject.Inject;
import picocli.CommandLine.Command;
import picocli.CommandLine.Model.CommandSpec;
import picocli.CommandLine.Parameters;
import picocli.CommandLine.Spec;

@Command(name="get", mixinStandardHelpOptions = true)
public class ServiceProxyMappingRuleGetCommand implements Runnable {

    private static final Logger LOG = Logger.getLogger(ServiceProxyMappingRuleGetCommand.class);

    @Spec
    CommandSpec spec;
    
    @Inject
    AccountManagementServiceFactory accountManagementServiceFactory;

    @Parameters(index = "0", description = "Service ID", arity = "1")
    public int serviceId;

    @Parameters(index = "1", description = "Mapping Rule ID", arity = "1")
    public int mappingRuleId;

    @Override
    public void run() {
        try {
            String response = accountManagementServiceFactory.getAccountManagementService().getServiceProxyMappingRule(serviceId, mappingRuleId);

            spec.commandLine().getOut().println(response);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }
}