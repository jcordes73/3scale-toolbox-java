package com.redhat.threescale.toolbox.helpers;

import java.io.StringWriter;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.camel.CamelContext;
import org.apache.camel.language.xpath.XPathBuilder;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class XPathExecution {
    
    @Inject
    CamelContext context;

    private Transformer transformer = null;

    
    public XPathExecution() throws Exception {
        transformer = TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
    }

    public String execute(String content, String xpathExpression) throws Exception {
        NodeList nodeList = XPathBuilder.xpath(xpathExpression).evaluate(context, content, NodeList.class);

        StringBuffer result = new StringBuffer();

        for (int i=0; i < nodeList.getLength(); i++){
            Node node = nodeList.item(i);
            StringWriter sw = new StringWriter();
            transformer.transform(new DOMSource(node), new StreamResult(sw));

            result.append(sw.toString());
            if (node.getNodeType() == Node.TEXT_NODE){
                if (i < nodeList.getLength()-1)
                    result.append(" ");
                else
                    result.append("\n");
            }
        }

        return result.toString();
    }

    public String prettyPrint(String content) throws Exception {
        return execute(content,"/*");
    }
}