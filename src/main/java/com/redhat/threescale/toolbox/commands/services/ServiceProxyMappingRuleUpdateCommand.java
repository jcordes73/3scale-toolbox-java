package com.redhat.threescale.toolbox.commands.services;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;

import com.redhat.threescale.toolbox.rest.client.service.AccountManagementService;

import jakarta.inject.Inject;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

@Command(name="update", mixinStandardHelpOptions = true)
public class ServiceProxyMappingRuleUpdateCommand implements Runnable {

    private static final Logger LOG = Logger.getLogger(ServiceProxyMappingRuleUpdateCommand.class);
    
    @Inject
    @RestClient
    AccountManagementService accountManagementService;

    @ConfigProperty(name="access_token")
    private String accessToken;

    @Parameters(index = "0", description = "Service ID", arity = "1")
    public int serviceId;

    @Parameters(index = "1", description = "Mapping Rule ID", arity = "1")
    public int mappingRuleId;

    @Option(names = {"--http-method",}, description = "HTTP Method")
    public String httpMethod;

    @Option(names = {"--pattern",}, description = "Pattern")
    public String pattern;

    @Option(names = {"--delta",}, description = "Delta")
    public Integer delta;

    @Option(names = {"--metric-id",}, description = "Metric ID")
    public Integer metricId;

    @Option(names = {"--position",}, description = "Position")
    public Integer position;

    @Option(names = {"--last",}, description = "Last matched Mapping Rule to process")
    public Boolean last;

    @Override
    public void run() {
        try {
            accountManagementService.updateServiceProxyMappingRule(serviceId, mappingRuleId, accessToken, httpMethod, pattern, serviceId, mappingRuleId, position, last);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }
}