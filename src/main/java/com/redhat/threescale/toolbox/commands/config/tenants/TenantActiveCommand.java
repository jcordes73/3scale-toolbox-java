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


@Command(name="active", mixinStandardHelpOptions = true)
public class TenantActiveCommand implements Runnable {

    private static final Logger LOG = Logger.getLogger(TenantActiveCommand.class);

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
                    configSource.getProperties().put("threescale.tenant",tenantName);
                }
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }
}
