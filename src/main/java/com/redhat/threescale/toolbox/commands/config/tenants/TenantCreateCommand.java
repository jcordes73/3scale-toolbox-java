package com.redhat.threescale.toolbox.commands.config.tenants;

import java.util.Iterator;

import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.spi.ConfigSource;
import org.jboss.logging.Logger;

import com.redhat.threescale.toolbox.config.ThreescaleConfigSource;

import jakarta.inject.Inject;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Model.CommandSpec;
import picocli.CommandLine.Parameters;
import picocli.CommandLine.Spec;


@Command(name="create", mixinStandardHelpOptions = true)
public class TenantCreateCommand implements Runnable {

    private static final Logger LOG = Logger.getLogger(TenantCreateCommand.class);

    @Inject
    private Config config;
    
    @Spec
    CommandSpec spec;
    
    @Parameters(index = "0", description = "Tenant Name", arity = "1")
    public String tenantName;

    @Parameters(index = "1", description = "URL", arity = "1")
    public String url;

    @Option(names={"--access-token"}, description = "Access Token", arity = "1")
    public String accessToken;

    @Option(names={"--provider-key"}, description = "Provider Key", arity = "1")
    public String providerKey;

    @Override
    public void run() {
        try {

            for (Iterator<ConfigSource> it = config.getConfigSources().iterator(); it.hasNext();){
                ConfigSource configSource = it.next();

                if (configSource.getName() == ThreescaleConfigSource.NAME){
                    configSource.getProperties().put("threescale.tenant",tenantName);
                    configSource.getProperties().put("threescale.tenant."+ tenantName + ".url", url);
                    
                    if (accessToken != null)
                        configSource.getProperties().put("threescale.tenant."+ tenantName + ".access_token",accessToken);
                    if (providerKey != null)
                        configSource.getProperties().put("threescale.tenant."+ tenantName + ".provider_key",providerKey);
                }
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }
}
