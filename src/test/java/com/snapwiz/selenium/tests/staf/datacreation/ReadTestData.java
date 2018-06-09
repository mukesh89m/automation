package com.snapwiz.selenium.tests.staf.datacreation;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

public  class ReadTestData {
	private static Logger logger = Logger.getLogger("com.snapwiz.selenium.tests.staf.orion.ReadTestData");
	 
	public static String readDataByTagName(String className, String tagName, String tcIndex) {
		  String  testdata = null;
		    try
		    {
		    	/*String passedClassName = className;
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
	    	}*/
		   	String workingDir = System.getProperty("user.dir");
                File fXmlFile;
               // if(classNameTrimmed.equals(""))
			        fXmlFile = new File(workingDir+"/src/test/java/com/snapwiz/selenium/tests/staf/datacreation/testdata/testdata.xml");
                /*else
                    fXmlFile = new File(workingDir+"/src/test/java/com/snapwiz/selenium/tests/staf/datacreation/testdata/testdata.xml");*/

              /*  if(passedClassName.contains("tlos"))
				    classNametag = "tlos";
                else if(passedClassName.contains("tloids"))
                    classNametag = "tloids";
                else if(passedClassName.contains("chapternames"))
                    classNametag = "chapternames";*/

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
		
		 if(id.equals(className) && index.equals(tcIndex))
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


