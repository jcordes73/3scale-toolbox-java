package com.redhat.threescale.toolbox.commands.provider.proxies;

import com.redhat.threescale.toolbox.rest.client.service.AccountManagementService;
import com.redhat.threescale.toolbox.rest.client.service.AccountManagementServiceFactory;

import jakarta.inject.Inject;
import picocli.CommandLine.Command;
import picocli.CommandLine.Model.CommandSpec;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;
import picocli.CommandLine.Spec;


@Command(name="proxies", mixinStandardHelpOptions = true)
public class ProxiesCommand implements Runnable {

    @Spec CommandSpec spec;

    @Inject
    AccountManagementServiceFactory accountManagementServiceFactory;

    @Parameters(index = "0", description = "Environment. Valid values: ${COMPLETION-CANDIDATES}", arity = "1")
    public AccountManagementService.Environment environment;

    @Option(names={"--host"}, description="Host")
    public String host;

    @Option(names={"--version"}, description="Version")
    public String version;
    
    @Override
    public void run() {
        try {
            String response = accountManagementServiceFactory.getAccountManagementService().getProxyConfig(environment, host, version);

            spec.commandLine().getOut().println(response);
        } catch (Exception e) {
            spec.commandLine().getOut().println(e.getMessage());
        }
    }
}
