package com.redhat.threescale.toolbox.commands.provider.policies;

import java.nio.file.Files;
import java.nio.file.Paths;

import org.jboss.logging.Logger;

import com.redhat.threescale.toolbox.rest.client.service.PolicyRegistryServiceFactory;

import jakarta.inject.Inject;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

@Command(name="create", mixinStandardHelpOptions = true)
public class PolicyCreateCommand implements Runnable {

    private static final Logger LOG = Logger.getLogger(PolicyCreateCommand.class);

    @Inject
    PolicyRegistryServiceFactory policyRegistryServiceFactory;

    @Parameters(index="0", description = "Name", arity = "1")
    private String name;

    @Parameters(index="1", description = "Version", arity = "1")
    private String version;

    @Parameters(index="2", description = "Schema File", arity = "1")
    private String schemaFile;

    @Override
    public void run() {
        try {
            String schema = Files.readString(Paths.get(schemaFile));

            policyRegistryServiceFactory.getPolicyRegistryService().createPolicy(name, version, schema);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }
}
