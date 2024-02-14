package com.redhat.threescale.toolbox.commands.config.tenants;

import java.util.Iterator;

import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.spi.ConfigSource;
import org.jboss.logging.Logger;

import com.redhat.threescale.toolbox.config.ThreescaleConfigSource;

import jakarta.inject.Inject;
import picocli.CommandLine.Command;
import picocli.CommandLine.Model.CommandSpec;
import picocli.CommandLine.Parameters;
import picocli.CommandLine.Spec;


@Command(name="delete", mixinStandardHelpOptions = true)
public class TenantDeleteCommand implements Runnable {

    private static final Logger LOG = Logger.getLogger(TenantDeleteCommand.class);

    @Inject
    private Config config;
    
    @Spec
    CommandSpec spec;
    
    @Parameters(index = "0", description = "Tenant Name", arity = "1")
    public String tenantName;

    @Override
    public void run() {
        try {

            for (Iterator<ConfigSource> it = config.getConfigSources().iterator(); it.hasNext();){
                ConfigSource configSource = it.next();

                if (configSource.getName() == ThreescaleConfigSource.NAME){
                    configSource.getProperties().remove("threescale.tenant",tenantName);
                    configSource.getProperties().remove("threescale.tenant."+ tenantName + ".url");
                    configSource.getProperties().remove("threescale.tenant."+ tenantName + ".access_token");
                    configSource.getProperties().remove("threescale.tenant."+ tenantName + ".provider_key");
                }
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }
}
