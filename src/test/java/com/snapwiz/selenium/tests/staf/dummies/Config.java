package com.snapwiz.selenium.tests.staf.dummies;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;


import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

import java.io.File;

public class Config {
	public static String baseURL;
	public static String course;
	public static String institutionURL;
	public static String institutionAdminUser;
	public static String institutionAdminPassword;
	public static String fileUploadPath;
	public static String browser;
	public static String dbconnecturl;
	public static String dbName;
	public static String dbdriver;
	public static String dbuserName;
	public static String dbPassword;
	public static String cmsUserName;
	public static String cmsPassword;
	public static String directloginurl;
	public static String homeurl;
    public static String familyname;
    public static String givenname;
	public static void readconfiguration()
	{
		 try {
			String workingDir = System.getProperty("user.dir");
			File fXmlFile = new File(workingDir+"/src/test/java/com/snapwiz/selenium/tests/staf/dummies/config.xml");
			
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
		 
			//optional, but recommended
			//read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
			doc.getDocumentElement().normalize();	 
			
		 
			NodeList nList = doc.getElementsByTagName("config");		
		 
			for (int temp = 0; temp < nList.getLength(); temp++) {
		 
				Node nNode = nList.item(temp);
		 
				
		 
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
		 
					Element eElement = (Element) nNode;	 
					course = eElement.getElementsByTagName("course").item(0).getTextContent();
					baseURL =	 eElement.getElementsByTagName("baseURL").item(0).getTextContent();
                    institutionAdminPassword = eElement.getElementsByTagName("institutionAdminPassword").item(0).getTextContent();
					fileUploadPath = eElement.getElementsByTagName("FileUploadPath").item(0).getTextContent();			
					browser = eElement.getElementsByTagName("browser").item(0).getTextContent();
					dbconnecturl = eElement.getElementsByTagName("dbconnecturl").item(0).getTextContent();
					dbName = eElement.getElementsByTagName("dbName").item(0).getTextContent();
					dbdriver = eElement.getElementsByTagName("dbdriver").item(0).getTextContent();
					dbuserName = eElement.getElementsByTagName("dbuserName").item(0).getTextContent();
					dbPassword = eElement.getElementsByTagName("dbPassword").item(0).getTextContent();
					cmsUserName = eElement.getElementsByTagName("cmsUserName").item(0).getTextContent();
					cmsPassword = eElement.getElementsByTagName("cmsPassword").item(0).getTextContent();
					directloginurl = eElement.getElementsByTagName("directloginurl").item(0).getTextContent();
					homeurl = eElement.getElementsByTagName("homeurl").item(0).getTextContent();
                    familyname = eElement.getElementsByTagName("familyname").item(0).getTextContent();
                    givenname = eElement.getElementsByTagName("givenname").item(0).getTextContent();
				}
		   
		 }}
		 catch(Exception e)
		 {
			 e.printStackTrace();
		 }
		   
		 }

	} 
