package com.redhat.threescale.toolbox.helpers;


import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Expression;
import org.apache.camel.builder.ExchangeBuilder;
import org.apache.camel.jsonpath.JsonPathLanguage;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class JsonPathExecution {
    @Inject
    CamelContext context;

    public String execute(String content, String expression) throws Exception {
        Exchange exchange = new ExchangeBuilder(context).build();
        exchange.getMessage().setBody(content);

        expression = expression.trim();

        if (expression.isEmpty()){
            expression = "$";
        }

        JsonPathLanguage jsonPathLanguage= new JsonPathLanguage();
        jsonPathLanguage.setWriteAsString(true);
        jsonPathLanguage.setUnpackArray(true);
        Expression expr = jsonPathLanguage.createExpression(expression);

        return expr.evaluate(exchange, String.class);
    }

    public String prettyPrint(String content) throws Exception {
        return execute(content,"$");
    }
}
