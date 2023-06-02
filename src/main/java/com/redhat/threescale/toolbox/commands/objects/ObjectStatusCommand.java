package com.redhat.threescale.toolbox.commands.objects;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;

import com.redhat.threescale.toolbox.rest.client.service.AccountManagementService;

import jakarta.inject.Inject;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;
import picocli.CommandLine.Spec;
import picocli.CommandLine.Model.CommandSpec;

@Command(name="status", mixinStandardHelpOptions = true)
public class ObjectStatusCommand implements Runnable {

    private static final Logger LOG = Logger.getLogger(ObjectStatusCommand.class);

    @Spec
    CommandSpec spec;
    
    @Inject
    @RestClient
    AccountManagementService accountManagementService;

    @ConfigProperty(name="access_token")
    private String accessToken;

    @Parameters(index = "0", description = "Object Type. Valid values: ${COMPLETION-CANDIDATES}", arity = "1")
    public AccountManagementService.ObjectType objectType;

    @Parameters(index = "1", description = "Object ID", arity = "1")
    public String objectId;

    @Override
    public void run() {
        try {
            String response = accountManagementService.getObjectStatus(accessToken, objectType, objectId);

            spec.commandLine().getOut().println(response);
        } catch (Exception e){
            LOG.error(e.getMessage(), e);
        }
    }
}