package com.snapwiz.selenium.tests.staf.learningspaces.testcases.anonymous;

import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;

public class AdaptiveTimer extends Driver{
	/*
	 * 1163-1165
	 */
	@Test
	public void adaptivetimer()
	{
		try
		{
            new LoginUsingLTI().ltiLogin("1163");
            new Navigator().NavigateTo("e-Textbook");
            new DiagnosticTest().startTest(4);
            new DiagnosticTest().attemptAllCorrect(0, false, false);
            new Navigator().NavigateTo("e-Textbook");
            new PracticeTest().startTest();
			boolean timevalue=new Timer().timer();
            if(timevalue==false)
                Assert.fail("Timer not shown or timer stops after we click on another tab.");
			
		}
		catch(Exception e)
	    {
				  Assert.fail("Exception in testcase adaptivetimer in class AdaptiveTimer.",e);
		}
	}

}
