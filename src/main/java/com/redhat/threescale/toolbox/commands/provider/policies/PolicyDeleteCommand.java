package com.redhat.threescale.toolbox.commands.provider.policies;

import org.jboss.logging.Logger;

import com.redhat.threescale.toolbox.rest.client.service.PolicyRegistryServiceFactory;

import jakarta.inject.Inject;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

@Command(name="delete", mixinStandardHelpOptions = true)
public class PolicyDeleteCommand implements Runnable {

    private static final Logger LOG = Logger.getLogger(PolicyDeleteCommand.class);

    @Inject
    PolicyRegistryServiceFactory policyRegistryServiceFactory;

    @Parameters(index="0", description = "Policy ID", arity = "1")
    private int policyId;

    @Override
    public void run() {
        try {
            policyRegistryServiceFactory.getPolicyRegistryService().deletePolicy(policyId);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }
}
