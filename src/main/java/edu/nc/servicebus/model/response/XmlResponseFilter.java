package edu.nc.servicebus.model.response;


import org.apache.commons.lang3.StringEscapeUtils;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.ByteArrayInputStream;
import java.io.IOException;

public class XmlResponseFilter implements ResponseFilter{

    private String expression;

    public XmlResponseFilter(String expression){
        this.expression = expression;
    }

    @Override
    public Response filter(Response sourceResult) {
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        builderFactory.setCoalescing(true);
        DocumentBuilder builder = null;
        String response = "";
        try {
            builder = builderFactory.newDocumentBuilder();

            InputSource input = new InputSource(
                    new ByteArrayInputStream(StringEscapeUtils.unescapeXml(sourceResult.getRawData()).getBytes("UTF-8")));
            Document document = builder.parse(input);

            XPathFactory xPathFactory = XPathFactory.newInstance();
            XPath xPath = xPathFactory.newXPath();
            NodeList nodeList = (NodeList) xPath.compile(expression).evaluate(document, XPathConstants.NODESET);

            for (int i = 0; i < nodeList.getLength(); i++) {
                response += nodeList.item(i).getFirstChild().getNodeValue() + " ";
            }
        } catch (ParserConfigurationException |
                SAXException |
                XPathExpressionException |
                IOException e){
            e.printStackTrace();
        }
        sourceResult.setRawData(response);
        return sourceResult;
    }
}
