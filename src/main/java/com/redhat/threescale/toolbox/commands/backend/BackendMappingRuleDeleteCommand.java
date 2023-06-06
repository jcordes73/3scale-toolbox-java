package com.redhat.threescale.toolbox.commands.backend;

import org.jboss.logging.Logger;

import com.redhat.threescale.toolbox.rest.client.service.AccountManagementServiceFactory;

import jakarta.inject.Inject;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

@Command(name="delete", mixinStandardHelpOptions = true)
public class BackendMappingRuleDeleteCommand implements Runnable {

    private static final Logger LOG = Logger.getLogger(BackendMappingRuleDeleteCommand.class);

    @Inject
    AccountManagementServiceFactory accountManagementServiceFactory;

    @Parameters(index = "0", description = "Backend ID", arity = "1")
    public int backendId;

    @Parameters(index = "1", description = "Mapping Rule ID", arity = "1")
    public int mappingRuleId;


    @Override
    public void run() {
        try {
            accountManagementServiceFactory.getAccountManagementService().deleteBackendMappingRule(backendId, mappingRuleId);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }
}