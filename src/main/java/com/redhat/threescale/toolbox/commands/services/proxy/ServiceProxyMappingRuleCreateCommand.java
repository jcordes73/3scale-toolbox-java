package com.redhat.threescale.toolbox.commands.services.proxy;

import com.redhat.threescale.toolbox.rest.client.service.AccountManagementServiceFactory;

import jakarta.inject.Inject;
import picocli.CommandLine.Command;
import picocli.CommandLine.Model.CommandSpec;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;
import picocli.CommandLine.Spec;

@Command(name="create", mixinStandardHelpOptions = true)
public class ServiceProxyMappingRuleCreateCommand implements Runnable {

    @Spec
    CommandSpec spec;
    
    @Inject
    AccountManagementServiceFactory accountManagementServiceFactory;

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
            accountManagementServiceFactory.getAccountManagementService().createServiceProxyMappingRule(serviceId, httpMethod, pattern, serviceId, metricId, position, last);
        } catch (Exception e) {
            spec.commandLine().getOut().println(e.getMessage());
        }
    }
}