package com.redhat.threescale.toolbox.commands.provider.account.users;

import java.util.Arrays;
import java.util.List;

import com.redhat.threescale.toolbox.rest.client.service.AccountManagementServiceFactory;

import jakarta.inject.Inject;
import jakarta.ws.rs.core.MultivaluedHashMap;
import picocli.CommandLine.Command;
import picocli.CommandLine.Model.CommandSpec;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;
import picocli.CommandLine.Spec;

@Command(name="update", mixinStandardHelpOptions = true)
public class UserPermissionsUpdateCommand implements Runnable { 

    @Spec
    CommandSpec spec;
    
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
            MultivaluedHashMap<String,String> request = new MultivaluedHashMap<>();
            
            if (allowedServiceIds != null)
                request.put("allowed_service_ids", Arrays.asList(allowedServiceIds.split(",")));

            if (allowedSections != null)
                request.put("allowed_sections", Arrays.asList(allowedSections.split(",")));
                
            accountManagementServiceFactory.getAccountManagementService().updateUserPermissions(userId, request);
        } catch (Exception e) {
            spec.commandLine().getOut().println(e.getMessage());
        }   
    }
}