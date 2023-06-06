package com.redhat.threescale.toolbox.commands.user;

import java.util.Arrays;
import java.util.List;

import org.jboss.logging.Logger;

import com.redhat.threescale.toolbox.rest.client.service.AccountManagementServiceFactory;

import jakarta.inject.Inject;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

@Command(name="update", mixinStandardHelpOptions = true)
public class UserPermissionsUpdateCommand implements Runnable {

    private static final Logger LOG = Logger.getLogger(UserPermissionsUpdateCommand.class);

    @Inject
    AccountManagementServiceFactory accountManagementServiceFactory;

    @Parameters(index = "0", description = "User ID", arity = "1")
    private int userId;

    @Option(names = {"--service-ids",}, description = "Allowed Service IDs")
    public String allowedServiceIds;

    @Option(names = {"--sections",}, description = "Allowed Sections")
    public String allowedSections;
        
    @Override
    public void run() {
        try {
            List<String> allowedServiceIdsList = null;
            if (allowedServiceIds != null)
                allowedServiceIdsList= Arrays.asList(allowedServiceIds.split(","));

            List<String> allowedSectionsList = null;
            if (allowedSections != null)
                allowedSectionsList = Arrays.asList(allowedSections.split(","));
                
            accountManagementServiceFactory.getAccountManagementService().updateUserPermissions(userId, allowedServiceIdsList, allowedSectionsList);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }   
    }
}