package com.redhat.threescale.toolbox.helpers;

import org.json.JSONObject;
import org.json.XML;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class JsonToXmlConverter {
    
    public String convert(String json) throws Exception {
        JSONObject jsonObject = new JSONObject(json);

        return XML.toString(jsonObject,"result");
    }
}