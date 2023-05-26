package com.redhat.threescale.toolbox.helpers;

import java.io.ByteArrayInputStream;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class XPathExecution {
    
    private DocumentBuilder builder = null;
    private XPath xPath = null;
    private Transformer transformer = null;

    
    public XPathExecution() throws Exception {
        builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        xPath = XPathFactory.newInstance().newXPath();
        transformer = TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
    }

    public String execute(String content, String xpathExpression) throws Exception {
        
        Document xmlDocument = builder.parse(new ByteArrayInputStream(content.getBytes(StandardCharsets.UTF_8)));

        Node node = (Node)xPath.compile(xpathExpression).evaluate(xmlDocument, XPathConstants.NODE);
        
        StringWriter sw = new StringWriter();
        transformer.transform(new DOMSource(node), new StreamResult(sw));

        return sw.toString();
    }

    public String prettyPrint(String content) throws Exception {
        return execute(content,"/*");
    }
}
