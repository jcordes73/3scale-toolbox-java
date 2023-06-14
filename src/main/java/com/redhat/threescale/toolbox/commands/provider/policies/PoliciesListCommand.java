package com.redhat.threescale.toolbox.commands.provider.policies;

import org.jboss.logging.Logger;

import com.redhat.threescale.toolbox.rest.client.service.PolicyRegistryServiceFactory;

import jakarta.inject.Inject;
import picocli.CommandLine.Command;
import picocli.CommandLine.Model.CommandSpec;
import picocli.CommandLine.Spec;

@Command(name="list", mixinStandardHelpOptions = true)
public class PoliciesListCommand implements Runnable {

    private static final Logger LOG = Logger.getLogger(PoliciesListCommand.class);

    @Spec
    CommandSpec spec;
    
    @Inject
    PolicyRegistryServiceFactory policyRegistryServiceFactory;

    @Override
    public void run() {
        try {
            String response = policyRegistryServiceFactory.getPolicyRegistryService().getPolicies();

            spec.commandLine().getOut().println(response);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }
}
