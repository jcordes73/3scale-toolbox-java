package com.redhat.threescale.toolbox.commands.services;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;

import com.redhat.threescale.toolbox.rest.client.service.AccountManagementService;

import jakarta.inject.Inject;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

@Command(name="create", mixinStandardHelpOptions = true)
public class ServiceProxyMappingRuleCreateCommand implements Runnable {

    private static final Logger LOG = Logger.getLogger(ServiceProxyMappingRuleCreateCommand.class);
    
    @Inject
    @RestClient
    AccountManagementService accountManagementService;

    @ConfigProperty(name="access_token")
    private String accessToken;

    @Parameters(index = "0", description = "Service ID", arity = "1")
    public int serviceId;

    @Parameters(index = "1", description = "HTTP Method", arity = "1")
    public String httpMethod;

    @Parameters(index = "2", description = "Pattern", arity = "1")
    public String pattern;

    @Parameters(index = "3", description = "Delta", arity = "1")
    public Integer delta;

    @Parameters(index = "4", description = "Metric ID", arity = "1")
    public int metricId;

    @Option(names = {"--position",}, description = "Position")
    public Integer position;

    @Option(names = {"--last",}, description = "Last matched Mapping Rule to process")
    public Boolean last;

    @Override
    public void run() {
        try {
            accountManagementService.createServiceProxyMappingRule(serviceId, accessToken, httpMethod, pattern, serviceId, metricId, position, last);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }
}