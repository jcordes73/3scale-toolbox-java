package com.redhat.threescale.toolbox.commands.services.proxy;

import org.jboss.logging.Logger;

import com.redhat.threescale.toolbox.rest.client.service.AccountManagementServiceFactory;

import jakarta.inject.Inject;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

@Command(name="deploy", mixinStandardHelpOptions = true)
public class ServiceProxyDeployCommand implements Runnable {

    private static final Logger LOG = Logger.getLogger(ServiceProxyDeployCommand.class);

    @Inject
    AccountManagementServiceFactory accountManagementServiceFactory;

    @Parameters(index = "0", description = "Service ID", arity = "1")
    public int serviceId;

    @Override
    public void run() {
        try {
            accountManagementServiceFactory.getAccountManagementService().deployServiceProxy(serviceId);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }
}