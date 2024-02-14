package com.redhat.threescale.toolbox.commands.services.backends;


import com.redhat.threescale.toolbox.picocli.QuotedStringConverter;
import com.redhat.threescale.toolbox.rest.client.service.AccountManagementServiceFactory;

import jakarta.inject.Inject;
import picocli.CommandLine.Command;
import picocli.CommandLine.Model.CommandSpec;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;
import picocli.CommandLine.Spec;

@Command(name="update", mixinStandardHelpOptions = true)
public class BackendUpdateCommand implements Runnable {

    @Spec
    CommandSpec spec;
    
    @Inject
    AccountManagementServiceFactory accountManagementServiceFactory;

    @Parameters(index = "0", description = "Backend ID", arity = "1")
    public int backendId;

    @Option(names = {"--name",}, description = "Backend name")
    public String name;

    @Option(names = {"--system-name",}, description = "System name")
    public String systemName;

    @Option(names = {"--description",}, description = "Description", converter = QuotedStringConverter.class)
    public String description;

    @Option(names = {"--private-endpoint",}, description = "Private Base URL (your API)")
    public String privateEndpoint;

    @Override
    public void run() {
        try {
            accountManagementServiceFactory.getAccountManagementService().updateBackend(backendId, name, systemName, description, privateEndpoint);
        } catch (Exception e) {
            spec.commandLine().getOut().println(e.getMessage());
        }        
    }
}