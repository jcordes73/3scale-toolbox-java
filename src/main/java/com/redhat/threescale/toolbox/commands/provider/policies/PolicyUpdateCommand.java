package com.redhat.threescale.toolbox.commands.provider.policies;

import java.nio.file.Files;
import java.nio.file.Paths;

import com.redhat.threescale.toolbox.rest.client.service.PolicyRegistryServiceFactory;

import jakarta.inject.Inject;
import picocli.CommandLine.Command;
import picocli.CommandLine.Model.CommandSpec;
import picocli.CommandLine.Parameters;
import picocli.CommandLine.Spec;
import picocli.CommandLine.Option;

@Command(name="update", mixinStandardHelpOptions = true)
public class PolicyUpdateCommand implements Runnable {

    @Spec
    CommandSpec spec;
    
    @Inject
    PolicyRegistryServiceFactory policyRegistryServiceFactory;

    @Parameters(index="0", description = "Policy ID", arity = "1")
    private int policyId;


    @Option(names ={"--name"}, description = "Name", arity = "1")
    private String name;

    @Option(names ={"--version"}, description = "Version", arity = "1")
    private String version;

    @Option(names ={"--schema-file"}, description = "Schema File", arity = "1")
    private String schemaFile;

    @Override
    public void run() {
        try {
            String schema = null;
            
            if (schemaFile != null)
                schema = Files.readString(Paths.get(schemaFile));

            policyRegistryServiceFactory.getPolicyRegistryService().updatePolicy(policyId, schema, schema, schema, schema);
        } catch (Exception e) {
            spec.commandLine().getOut().println(e.getMessage());
        }
    }
}
