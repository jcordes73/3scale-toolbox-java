package com.redhat.threescale.toolbox.commands.provider.objects;

import org.jboss.logging.Logger;

import com.redhat.threescale.toolbox.rest.client.service.AccountManagementService;
import com.redhat.threescale.toolbox.rest.client.service.AccountManagementServiceFactory;

import jakarta.inject.Inject;
import picocli.CommandLine.Command;
import picocli.CommandLine.Model.CommandSpec;
import picocli.CommandLine.Parameters;
import picocli.CommandLine.Spec;

@Command(name="status", mixinStandardHelpOptions = true)
public class ObjectStatusCommand implements Runnable {

    private static final Logger LOG = Logger.getLogger(ObjectStatusCommand.class);

    @Spec
    CommandSpec spec;
    
    @Inject
    AccountManagementServiceFactory accountManagementServiceFactory;

    @Parameters(index = "0", description = "Object Type. Valid values: ${COMPLETION-CANDIDATES}", arity = "1")
    public AccountManagementService.ObjectType objectType;

    @Parameters(index = "1", description = "Object ID", arity = "1")
    public String objectId;

    @Override
    public void run() {
        try {
            String response = accountManagementServiceFactory.getAccountManagementService().getObjectStatus(objectType, objectId);

            spec.commandLine().getOut().println(response);
        } catch (Exception e){
            LOG.error(e.getMessage(), e);
        }
    }
}