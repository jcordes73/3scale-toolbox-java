package com.redhat.threescale.toolbox.commands.services.proxy;

import org.jboss.logging.Logger;

import com.redhat.threescale.toolbox.rest.client.service.AccountManagementServiceFactory;

import jakarta.inject.Inject;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

@Command(name="update", mixinStandardHelpOptions = true)
public class ServiceProxyOidcUpdateCommand implements Runnable {

    private static final Logger LOG = Logger.getLogger(ServiceProxyOidcUpdateCommand.class);
    
    @Inject
    AccountManagementServiceFactory accountManagementServiceFactory;

    @Parameters(index = "0", description = "Service ID", arity = "1")
    public int serviceId;

    @Option(names = {"--standard-flow-enabled",}, description = "Enable Authorization Code Flow (Standard Flow)", defaultValue = Option.NULL_VALUE, negatable = true)
    public Boolean standardFlowEnabled;

    @Option(names = {"--implicit-flow-enabled",}, description = "Enable Implicit Flow", defaultValue = Option.NULL_VALUE, negatable = true)
    public Boolean implicitFlowEnabled;

    @Option(names = {"--service-accounts-enabled",}, description = "Enable Service Account Flow (Standard Flow)", defaultValue = Option.NULL_VALUE, negatable = true)
    public Boolean serviceAccountsEnabled;

    @Option(names = {"--direct-access-grants-enabled",}, description = "Enable Direct Access Grant Flow", defaultValue = Option.NULL_VALUE, negatable = true)
    public Boolean directAccessGrantsEnabled;

    @Override
    public void run() {
        try {
            accountManagementServiceFactory.getAccountManagementService().updateServiceProxyOidc(serviceId, standardFlowEnabled, implicitFlowEnabled, serviceAccountsEnabled, directAccessGrantsEnabled);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }
}