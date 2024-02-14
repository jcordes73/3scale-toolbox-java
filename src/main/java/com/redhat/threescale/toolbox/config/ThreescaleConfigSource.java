package com.redhat.threescale.toolbox.config;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.eclipse.microprofile.config.spi.ConfigSource;

import io.quarkus.runtime.annotations.StaticInitSafe;

@StaticInitSafe
public class ThreescaleConfigSource implements ConfigSource {

    public static final String NAME = "ThreescaleConfigSource";
    private static final Map<String,String> PROPERTIES = new HashMap<>();

    @Override
    public int getOrdinal() {
        return 900;
    }

    @Override
    public Map<String, String> getProperties() {
        return PROPERTIES;
    }

    @Override
    public String getValue(String key) {
        if (key.startsWith("threescale.tenant")){
            if ("threescale.tenant.url".equals(key)){
                key = "threescale.tenant." + getValue("threescale.tenant") + ".url";
            } else if ("threescale.tenant.access_token".equals(key)){
                key = "threescale.tenant." + getValue("threescale.tenant") + ".access_token";
            } else if ("threescale.tenant.provider_key".equals(key)){
                key = "threescale.tenant." + getValue("threescale.tenant") + ".provider_key";
            }

            if(PROPERTIES.containsKey(key)){
                return PROPERTIES.get(key);
            }

            return "undefined";
        }

        return null;
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public Set<String> getPropertyNames() {
        return PROPERTIES.keySet();
    }
}
