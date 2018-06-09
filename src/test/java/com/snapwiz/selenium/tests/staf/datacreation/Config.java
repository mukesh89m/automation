package com.snapwiz.selenium.tests.staf.datacreation;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class Config {
	public static String baseURL;
	public static String course;
	public static String baseLTIURL;
	public static String fileUploadPath;
	public static String launchURL;
	public static String customerkey;
	public static String secretkey;
	public static String resourselinkid;
	public static String familyname;
	public static String givenname;
	public static String email;
	public static String instance_guid;
	public static String instance_name;
	public static String custom_destination;
	public static String custom_domainid;
	public static String custom_course_number;
	public static String courseid;
	public static String context_id;
	public static String context_title;
	public static String lis_outcome_service_url;
	public static String browser;
	public static String dbconnecturl;
	public static String dbName;
	public static String dbdriver;
	public static String dbuserName;
	public static String dbPassword;
	public static String custom_domain_name;
	public static String custom_instructor_classlist;
	public static String mongoHost;
	public static String mongoPort;
	public static String cmsUserName;
	public static String cmsPassword;
    public static String orionCourseId;
    public static String lsCourseId;
    public static String csid,cslsid,csorionid;
	public static void readconfiguration()
	{
		 try {
			String workingDir = System.getProperty("user.dir");
			File fXmlFile = new File(workingDir+"/src/test/java/com/snapwiz/selenium/tests/staf/datacreation/config.xml");
			
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
					baseLTIURL = eElement.getElementsByTagName("LTIURL").item(0).getTextContent();
					fileUploadPath = eElement.getElementsByTagName("FileUploadPath").item(0).getTextContent();
					launchURL = eElement.getElementsByTagName("LaunchURL").item(0).getTextContent();
					customerkey = eElement.getElementsByTagName("customerkey").item(0).getTextContent();
					secretkey = eElement.getElementsByTagName("secretkey").item(0).getTextContent();
					resourselinkid = eElement.getElementsByTagName("resourselinkid").item(0).getTextContent();
					familyname = eElement.getElementsByTagName("familyname").item(0).getTextContent();
					givenname = eElement.getElementsByTagName("givenname").item(0).getTextContent();
					email = eElement.getElementsByTagName("email").item(0).getTextContent();
					instance_guid = eElement.getElementsByTagName("instance_guid").item(0).getTextContent();
					instance_name = eElement.getElementsByTagName("instance_name").item(0).getTextContent();
					custom_destination = eElement.getElementsByTagName("custom_destination").item(0).getTextContent();
					custom_domainid = eElement.getElementsByTagName("custom_domainid").item(0).getTextContent();
					custom_course_number = eElement.getElementsByTagName("custom_course_number").item(0).getTextContent();
					courseid = eElement.getElementsByTagName("courseID").item(0).getTextContent();
					context_id = eElement.getElementsByTagName("context_id").item(0).getTextContent();
					context_title = eElement.getElementsByTagName("context_title").item(0).getTextContent();
					lis_outcome_service_url = eElement.getElementsByTagName("lis_outcome_service_url").item(0).getTextContent();
					browser = eElement.getElementsByTagName("browser").item(0).getTextContent();
					dbconnecturl = eElement.getElementsByTagName("dbconnecturl").item(0).getTextContent();
					dbName = eElement.getElementsByTagName("dbName").item(0).getTextContent();
					dbdriver = eElement.getElementsByTagName("dbdriver").item(0).getTextContent();
					dbuserName = eElement.getElementsByTagName("dbuserName").item(0).getTextContent();
					dbPassword = eElement.getElementsByTagName("dbPassword").item(0).getTextContent();
					custom_domain_name = eElement.getElementsByTagName("custom_domain_name").item(0).getTextContent();
					custom_instructor_classlist = eElement.getElementsByTagName("custom_instructor_classlist").item(0).getTextContent();
					mongoHost = eElement.getElementsByTagName("mongoHost").item(0).getTextContent();
					mongoPort = eElement.getElementsByTagName("mongoPort").item(0).getTextContent();
					cmsUserName = eElement.getElementsByTagName("cmsUserName").item(0).getTextContent();
					cmsPassword = eElement.getElementsByTagName("cmsPassword").item(0).getTextContent();
                    orionCourseId = eElement.getElementsByTagName("orionCourseId").item(0).getTextContent();
                    lsCourseId = eElement.getElementsByTagName("lsCourseId").item(0).getTextContent();
                    csid=eElement.getElementsByTagName("csid").item(0).getTextContent();
                    csorionid=eElement.getElementsByTagName("csorionid").item(0).getTextContent();
                    cslsid=eElement.getElementsByTagName("cslsid").item(0).getTextContent();
				}
		   
		 }}
		 catch(Exception e)
		 {
			 e.printStackTrace();
		 }
		   
		 }

	} 
