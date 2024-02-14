package com.redhat.threescale.toolbox.commands.config.tenants;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.spi.ConfigSource;
import org.jboss.logging.Logger;

import com.redhat.threescale.toolbox.config.ThreescaleConfigSource;

import jakarta.inject.Inject;
import picocli.CommandLine.Command;
import picocli.CommandLine.Model.CommandSpec;
import picocli.CommandLine.Spec;


@Command(name="list", mixinStandardHelpOptions = true)
public class TenantListCommand implements Runnable {

    private static final Logger LOG = Logger.getLogger(TenantListCommand.class);

    @Inject
    private Config config;
    
    @Spec
    CommandSpec spec;
    
    @Override
    public void run() {
        try {

            for (Iterator<ConfigSource> it = config.getConfigSources().iterator(); it.hasNext();){
                ConfigSource configSource = it.next();

                if (configSource.getName() == ThreescaleConfigSource.NAME){
                    String activeTenant = configSource.getProperties().get("threescale.tenant");

                    HashMap<String,String> tenants = new HashMap<String,String>();
                    
                    for (Iterator<Entry<String,String>> entryIt = configSource.getProperties().entrySet().iterator(); entryIt.hasNext();){
                        Entry<String,String> entry = entryIt.next();

                        if (!entry.getKey().equals("threescale.tenant")){
                            String tenantName=entry.getKey().split("\\.")[2];
                            tenants.put(tenantName, tenantName);
                        }
                    }

                    for (Iterator<String> tenantIt = tenants.keySet().iterator(); tenantIt.hasNext();){
                        StringBuffer sb = new StringBuffer();

                        String tenantName = tenantIt.next();
                        String url = configSource.getProperties().get("threescale.tenant."+ tenantName + ".url");
                        String accessToken = configSource.getProperties().get("threescale.tenant."+ tenantName + ".access_token");
                        String providerKey = configSource.getProperties().get("threescale.tenant."+ tenantName + ".provider_key");

                        sb.append("Name: ").append(tenantName).append(" ")
                        .append("Admin-URL: ").append(url).append(" ");

                        if (accessToken != null)
                            sb.append("Access-Token: ").append(accessToken);

                        if (providerKey != null)
                            sb.append("Provider-Key: ").append(providerKey);

                        if (tenantName.equals(activeTenant))
                            sb.append(" (active)");

                        spec.commandLine().getOut().println(sb.toString());
                    }
                }
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }
}
