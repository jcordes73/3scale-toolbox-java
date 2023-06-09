package com.redhat.threescale.toolbox.commands.services;

import org.jboss.logging.Logger;

import com.redhat.threescale.toolbox.picocli.QuotedStringConverter;
import com.redhat.threescale.toolbox.rest.client.service.AccountManagementServiceFactory;

import jakarta.inject.Inject;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

@Command(name="update", mixinStandardHelpOptions = true)
public class ServiceFeatureUpdateCommand implements Runnable {

    private static final Logger LOG = Logger.getLogger(ServiceFeatureUpdateCommand.class);
    
    @Inject
    AccountManagementServiceFactory accountManagementServiceFactory;

    @Parameters(index = "0", description = "Service ID", arity = "1")
    public int serviceId;

    @Parameters(index = "1", description = "Service FeatureID", arity = "1")
    public int serviceFeatureId;

    @Option(names = {"--name",}, description = "Name")
    public String name;

    @Option(names = {"--description",}, description = "Description", converter = QuotedStringConverter.class)
    public String description;
    
    @Override
    public void run() {
        try {
            accountManagementServiceFactory.getAccountManagementService().updateServiceFeature(serviceId, serviceFeatureId, name, description);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }
}