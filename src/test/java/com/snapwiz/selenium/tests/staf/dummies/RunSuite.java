package com.snapwiz.selenium.tests.staf.dummies;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import junit.framework.JUnit4TestAdapter;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.junit.Test;

import org.jopendocument.dom.spreadsheet.MutableCell;
import org.jopendocument.dom.spreadsheet.Sheet;
import org.jopendocument.dom.spreadsheet.SpreadSheet;


public final class RunSuite extends TestCase {	
	
	@Test
	public static TestSuite suite() {
	    TestSuite suite = new TestSuite();    
	    
	   		
		 List<String> data = new ArrayList<String>();
		 //File file = new File("/opt/automation-test-framework/selenium/dummies/testsuite.ods");
		 File file = new File("/home/akansh/testsuite.ods");
	        Sheet sheet;
	        try {
	             //Getting the 0th sheet for manipulation| pass sheet name as string
	             sheet = SpreadSheet.createFromFile(file).getSheet(0);
	              
	             //Get row count and column count
	             int nColCount = sheet.getColumnCount();
	             int nRowCount = sheet.getRowCount();

	             
	             //Iterating through each row of the selected sheet
	             MutableCell cell = null;
	            
	             Object  testdata;
	             for(int nRowIndex = 0; nRowIndex < nRowCount; nRowIndex++)
	             {      	 
	               //Iterating through each column
	            	 
	               int nColIndex = 0;
	               for( ;nColIndex < nColCount; nColIndex++)
	               {
	                 cell = sheet.getCellAt(nColIndex, nRowIndex);
	                 testdata = cell.getValue();
	                 data.add(testdata.toString());
	               
	                }
	               if(data.contains(""))
	               	   break;
	                           
	              	                
	              }
	             
	             

	            } catch (Exception e) {
	              e.printStackTrace();
	            }
	        
	       for(int i=0;i<data.size();i++)
	       {
	    	  String testcase = data.get(i);
	    	  
	    	   try
	    	   {
	    		   testcase = "com.snapwiz.selenium.tests.staf.dummies.testcases."+testcase;
	    		   System.out.println(testcase);
	    	       Class c = Class.forName(testcase);
	    	       suite.addTest(new JUnit4TestAdapter(c));
	    	   }
	    	   catch(Exception e)
	    	   {
	    		   e.printStackTrace();
	    	   }
	        
	       }
	
	return suite;
	}
}