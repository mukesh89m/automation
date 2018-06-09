package com.snapwiz.selenium.tests.staf.framework.utility;

import org.testng.annotations.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

/**
 * Created by Mukesh_PC on 5/23/2017.
 */
public class Demo {

    @Test
    public  void read() throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document dom = db.parse("D:\\projects\\staf\\src\\test\\java\\com\\snapwiz\\selenium\\tests\\staf\\framework\\bank.xml");
        Element docEle = dom.getDocumentElement();
        NodeList nl = docEle.getChildNodes();
        if (nl != null) {
            int length = nl.getLength();
            for (int i = 0; i < length; i++) {
                if (nl.item(i).getNodeType() == Node.ELEMENT_NODE) {
                    Element el = (Element) nl.item(i);
                    if (el.getNodeName().contains("staff")) {
                        String name = el.getElementsByTagName("name").item(0).getTextContent();
                        String phone = el.getElementsByTagName("phone").item(0).getTextContent();
                        String email = el.getElementsByTagName("email").item(0).getTextContent();
                        String area = el.getElementsByTagName("area").item(0).getTextContent();
                        String city = el.getElementsByTagName("city").item(0).getTextContent();
                    }
                }
            }
        }
    }
}
