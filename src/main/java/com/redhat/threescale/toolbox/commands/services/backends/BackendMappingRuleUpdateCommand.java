package com.redhat.threescale.toolbox.commands.services.backends;

import com.redhat.threescale.toolbox.rest.client.service.AccountManagementServiceFactory;

import jakarta.inject.Inject;
import picocli.CommandLine.Command;
import picocli.CommandLine.Model.CommandSpec;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;
import picocli.CommandLine.Spec;


@Command(name="update", mixinStandardHelpOptions = true)
public class BackendMappingRuleUpdateCommand implements Runnable {    @Spec
    CommandSpec spec;
    
    @Inject
    AccountManagementServiceFactory accountManagementServiceFactory;

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
            accountManagementServiceFactory.getAccountManagementService().updateBackendMappingRule(backendId, mappingRuleId, httpMethod, pattern, delta, metricId, position, last);
        } catch (Exception e) {
            spec.commandLine().getOut().println(e.getMessage());
        }
    }
}