package com.snapwiz.selenium.tests.staf.learningspaces.testcases.anonymous;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.TextValidate;
/*
 * 18
 */
public class LTIAdaptiveLoginCustomDestinationWithSnapwizChapter extends Driver{

	@Test
    public void ltiAdaptiveLoginCustomDestinationWithSnapwizChapter()
    {
    	try
    	{
    		new LoginUsingLTI().ltiLogin("18");
    		String texttoverify=ReadTestData.readDataByTagName("", "expectederror", "18");
    		boolean stringvalue=new TextValidate().IsTextPresent(texttoverify);
   	        if(stringvalue==false)
   	            Assert.fail("The student navigated to the current ORION product with custom-destination field as snapwiz-chapter.");

    	}
    	catch(Exception e)
    	{
			 Assert.fail("Exception  in testcase ltiAdaptiveLoginCustomDestinationWithSnapwizChapter in class LTIAdaptiveLoginCustomDestinationWithSnapwizChapter",e);
    	}
    }


}
