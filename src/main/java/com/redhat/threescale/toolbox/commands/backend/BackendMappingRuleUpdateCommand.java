package com.redhat.threescale.toolbox.commands.backend;

import com.redhat.threescale.toolbox.rest.client.service.AccountManagementService;

import jakarta.inject.Inject;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;
import picocli.CommandLine.Option;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;


@Command(name="update", mixinStandardHelpOptions = true)
public class BackendMappingRuleUpdateCommand implements Runnable {

    private static final Logger LOG = Logger.getLogger(BackendMappingRuleUpdateCommand.class);

    @Inject
    @RestClient
    AccountManagementService accountManagementService;

    @ConfigProperty(name="access_token")
    private String accessToken;

    @Parameters(index = "0", description = "Backend ID", arity = "1")
    public int backendId;

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
            accountManagementService.updateBackendMappingRule(backendId, mappingRuleId, accessToken, httpMethod, pattern, delta, metricId, position, last);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }
}