package com.snapwiz.selenium.tests.staf.learningspaces.apphelper;

import org.testng.Assert;

import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.Click;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.TextFetch;

public class HelpText
{
	public String helpText(int index)
	{
		String text=null;
		try
		{
			new Click().clickbylist("ins-resourceUpload-help-img", index);
			text=new TextFetch().textfetchbyclass("help-data-container");
		}
		catch(Exception e)
		{
			Assert.fail("Exception in app helper helptext");
		}
		return text;
	}
	
	public String helpTextForGradingPolicy(int index)
	{
		String text=null;
		try
		{
			new Click().clickbylist("policy-question-help-icon", index);
			text=new TextFetch().textfetchbyclass("policy-help-data-container");
		}
		catch(Exception e)
		{
			Assert.fail("Exception in app helper helpTextForGradingPolicy");
		}
		return text;
	}
	
	

}
