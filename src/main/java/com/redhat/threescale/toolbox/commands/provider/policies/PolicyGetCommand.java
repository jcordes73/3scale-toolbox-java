package com.redhat.threescale.toolbox.commands.provider.policies;

import org.jboss.logging.Logger;

import com.redhat.threescale.toolbox.rest.client.service.PolicyRegistryServiceFactory;

import jakarta.inject.Inject;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;
import picocli.CommandLine.Model.CommandSpec;
import picocli.CommandLine.Spec;

@Command(name="get", mixinStandardHelpOptions = true)
public class PolicyGetCommand implements Runnable {

    private static final Logger LOG = Logger.getLogger(PoliciesListCommand.class);

    @Spec
    CommandSpec spec;
    
    @Inject
    PolicyRegistryServiceFactory policyRegistryServiceFactory;

    @Parameters(index="0", description = "Policy ID", arity = "1")
    private int policyId;

    @Override
    public void run() {
        try {
            String response = policyRegistryServiceFactory.getPolicyRegistryService().getPolicy(policyId);

            spec.commandLine().getOut().println(response);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }
}
