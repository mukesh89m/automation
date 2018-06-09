package com.snapwiz.selenium.tests.staf.orion;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

public  class ReadTestData {
	private static Logger logger = Logger.getLogger("com.snapwiz.selenium.tests.staf.orion.ReadTestData");
	public static String [] readData(String className, String tcIndex) {
		  String [] testdata = null;
	    try 
	    {
	    	if(className.equals(""))
	    		className = new Exception().getStackTrace()[2].getClassName();
	    	
	    	if(className.equals("sun.reflect.NativeMethodAccessorImpl"))
	    		className = new Exception().getStackTrace()[1].getClassName();
	    String workingDir = System.getProperty("user.dir");
		File fXmlFile = new File(workingDir+"/src/test/java/com/snapwiz/selenium/tests/staf/orion/testdata/testdata.xml");
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(fXmlFile);
	 
		doc.getDocumentElement().normalize();
	 
		
	 
		NodeList nList = doc.getElementsByTagName("testcase");	
	 
		for (int temp = 0; temp < nList.getLength(); temp++) {
	 
			Node nNode = nList.item(temp);
	 
		//	System.out.println("\nCurrent Element :" + nNode.getNodeName());
	 
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
	 
				Element eElement = (Element) nNode;
	 String id = eElement.getAttribute("id");
	 String index = eElement.getAttribute("index");

	 String text="";
	 String eol = System.getProperty("line.separator");

	 if(id.equals(className) && index.equals(tcIndex))
	 {
		NodeList childnodes =  eElement.getChildNodes();
		
		for(int k=0;k<childnodes.getLength();k++)
		{
			Node innerNode = childnodes.item(k);
			if (innerNode.getNodeType() == Node.ELEMENT_NODE) {
			Element innerElement = (Element)innerNode;		
			text += innerElement.getTextContent()+eol;		
			}
		}
		testdata = text.split("\\n");
		
		for(int len = 0;len<testdata.length;len++)
		{
			
			testdata[len] = testdata[len].replaceAll("(\\r|\\n)", "");
						
		}
		
			
	 }
			}
		}
	    } catch (Exception e) {
		e.printStackTrace();
		logger.log(Level.INFO,"Exception while reading test data. Please check Tag Id and Index value",e);
		
	    }
	    return testdata;
	  }
	 
	public static String readDataByTagName(String className, String tagName, String tcIndex) {
		  String  testdata = null;
		    try
		    {
		    	String passedClassName = className;
		    	String classNametag = "";
		    		className = new Exception().getStackTrace()[2].getClassName();
		    	    classNametag = className;
		    	if(className.equals("sun.reflect.NativeMethodAccessorImpl"))
		    	{
		    	className = new Exception().getStackTrace()[1].getClassName();
		    	classNametag = className;
		    	}
		    	className = className.substring(48);
		        className = className.replaceAll("\\.", "_");
		    	String classNameTrimmed= "";
		      
	    	int cnt = 0;
	    	for(int i=0;i<className.length();i++)
	    	{	    		
	    		if(className.charAt(i)== '_')
	    		{
	    			if(cnt == 1)
	    			{
	    				classNameTrimmed = className.substring(0,i);
	    			}
	    			cnt++;
	    		}
	    	}
		   	String workingDir = System.getProperty("user.dir");
                File fXmlFile;
                if(classNameTrimmed.equals(""))
			        fXmlFile = new File(workingDir+"/src/test/java/com/snapwiz/selenium/tests/staf/orion/testdata/testdata.xml");
                else
                    fXmlFile = new File(workingDir+"/src/test/java/com/snapwiz/selenium/tests/staf/orion/testdata/testdata_"+classNameTrimmed+".xml");

                if(passedClassName.contains("tlos"))
				    classNametag = "tlos";
                else if(passedClassName.contains("tloids"))
                    classNametag = "tloids";
                else if(passedClassName.contains("chapternames"))
                    classNametag = "chapternames";

			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
		 
			doc.getDocumentElement().normalize();			
		 
			NodeList nList = doc.getElementsByTagName("testcase");	
		 
			for (int temp = 0; temp < nList.getLength(); temp++) {
		 
				Node nNode = nList.item(temp);
		 
		 if (nNode.getNodeType() == Node.ELEMENT_NODE) {
		 
					Element eElement = (Element) nNode;
		 String id = eElement.getAttribute("id");
		 String index = eElement.getAttribute("index");
		
		 if(id.equals(classNametag) && index.equals(tcIndex))
		 {
			NodeList childnodes =  eElement.getChildNodes();
			
			for(int k=0;k<childnodes.getLength();k++)
			{
				
				Node innerNode = childnodes.item(k);
				if(innerNode.getNodeName() == tagName)
				{
					testdata = innerNode.getTextContent();
				}
				
			}				
				
		 }
				}
			}
		    } catch (Exception e) {
			e.printStackTrace();
			logger.log(Level.INFO,"Exception while reading test data. Please check Tag Id and Index value",e);
			
		    }
		    return testdata;
		
	}
}


