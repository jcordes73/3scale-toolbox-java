package com.redhat.threescale.toolbox.commands.services.features;


import com.redhat.threescale.toolbox.picocli.QuotedStringConverter;
import com.redhat.threescale.toolbox.rest.client.service.AccountManagementService;
import com.redhat.threescale.toolbox.rest.client.service.AccountManagementServiceFactory;

import jakarta.inject.Inject;
import picocli.CommandLine.Command;
import picocli.CommandLine.Model.CommandSpec;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;
import picocli.CommandLine.Spec;

@Command(name="create", mixinStandardHelpOptions = true)
public class ServiceFeatureCreateCommand implements Runnable {

    
    
    @Spec
    CommandSpec spec;
    
    @Inject
    AccountManagementServiceFactory accountManagementServiceFactory;

    @Parameters(index = "0", description = "Service ID", arity = "1")
    public int serviceId;

    @Parameters(index = "1", description = "Name", arity = "1")
    public String name;

    @Option(names = {"--system-name",}, description = "System name")
    public String systemName;

    @Option(names = {"--description",}, description = "Description", converter = QuotedStringConverter.class)
    public String description;
    
    @Option(names = {"--scope",}, description = "Scope. Valid values: ${COMPLETION-CANDIDATES}")
    public AccountManagementService.FeatureScope featureScope;

    @Override
    public void run() {
        try {
            accountManagementServiceFactory.getAccountManagementService().createServiceFeature(serviceId, name, systemName, description, featureScope);

        } catch (Exception e) {
            spec.commandLine().getOut().println(e.getMessage());
        }
    }
}
