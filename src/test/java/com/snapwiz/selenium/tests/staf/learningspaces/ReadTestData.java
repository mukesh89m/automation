package com.snapwiz.selenium.tests.staf.learningspaces;

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
	private static Logger logger = Logger.getLogger("com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData");
	public static String [] readData(String className, String tcIndex) {
		  String [] testdata = null;
	    try 
	    {
	    	//if(className.equals(""))
	    		className = new Exception().getStackTrace()[2].getClassName();
	    		String classNametag = className;
	    	if(className.equals("sun.reflect.NativeMethodAccessorImpl"))
	    		className = new Exception().getStackTrace()[1].getClassName();
	    	
	    	className = className.substring(57);
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
    				break;
    			}
    			cnt++;
    		}
    	}
	    	
	    String workingDir = System.getProperty("user.dir");
		File fXmlFile = new File(workingDir+"/src/test/java/com/snapwiz/selenium/tests/staf/learningspaces/testdata_"+classNameTrimmed+".xml");

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

	 if(id.equals(classNametag) && index.equals(tcIndex))
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
		    	if(className.contains("com.snapwiz.selenium.tests.staf.learningspaces.apphelper"))
                {
                    className = new Exception().getStackTrace()[3].getClassName();
                    classNametag = className;
                }
                if(className.equals("com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Assignment"))
                {
                    className = new Exception().getStackTrace()[4].getClassName();
                    classNametag = className;
                }

                className = className.substring(57);
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
			File fXmlFile = new File(workingDir+"/src/test/java/com/snapwiz/selenium/tests/staf/learningspaces/testdata/testdata_"+classNameTrimmed+".xml");
			if(passedClassName.contains("tocdata"))
				classNametag = "tocdata";
	
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


