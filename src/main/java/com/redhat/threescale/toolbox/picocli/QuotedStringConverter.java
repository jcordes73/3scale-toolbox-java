package com.redhat.threescale.toolbox.picocli;

import picocli.CommandLine.ITypeConverter;

public class QuotedStringConverter implements ITypeConverter<String> {

    public String convert(String value) throws Exception {
        String result = null;

        if (value != null)
            result = value.replaceAll("^\"|\"$", "");

        return result;
    }
}