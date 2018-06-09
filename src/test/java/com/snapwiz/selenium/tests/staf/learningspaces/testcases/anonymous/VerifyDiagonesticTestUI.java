package com.snapwiz.selenium.tests.staf.learningspaces.testcases.anonymous;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.DiagnosticTest;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.ExpandFirstChapter;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.SelecteTextBook;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.TopicOpen;

public class VerifyDiagonesticTestUI extends Driver{
	private static Logger logger=Logger.getLogger("com.snapwiz.selenium.tests.staf.learningspaces.testcases.anonymous.VerifyStudentGetNotificationWhenQuitTheDaigonesticTest");
	/*
	 * 1079-1080
	 */
	@Test
	public void verifydiagonestictestui()
	{
		try
		{
			driver.manage().deleteAllCookies();
			new LoginUsingLTI().ltiLogin("1079");
			new SelecteTextBook().etextselector();
			new ExpandFirstChapter().expandFirstChapter();
			new DiagnosticTest().startTest(2);
			String linktext=new ReadTestData().readDataByTagName("VerifyDiagonesticTestUI", "linktext", "1079");
			String chapternumber=new ReadTestData().readDataByTagName("VerifyDiagonesticTestUI", "chapternumber", "1079");
			String chaptername=new ReadTestData().readDataByTagName("VerifyDiagonesticTestUI", "chptername", "1079");
			String questiontext=driver.findElement(By.className("al-diag-test-title")).getAttribute("title");
			System.out.println(questiontext);
			int headerpostion=questiontext.indexOf(linktext);
			int chaptrnopostion=questiontext.indexOf(chapternumber);
			int chapternamepostion=questiontext.indexOf(chaptername);
			//System.out.println(headerpostion+"---"+chapternamepostion+"---0"+chaptrnopostion);
			boolean timervalue=driver.findElement(By.className("al-diag-test-timer")).isDisplayed();
			boolean questiondescription=driver.findElement(By.className("al-about-this-question-title")).isDisplayed();
			boolean close=driver.findElement(By.className("al-quit-diag-test-icon")).isDisplayed();
			boolean performanceframe=driver.findElement(By.className("difficulty-level-block-wrapper")).isDisplayed();
			boolean deficultylevel=driver.findElement(By.className("al-content-box")).isDisplayed();
			boolean objective=driver.findElement(By.className("al-question-objective-content-wrapper")).isDisplayed();
			boolean markforreview=driver.findElement(By.className("al-customize-checkbox-small")).isDisplayed();
			boolean confidencelevel=driver.findElement(By.className("al-self-rating")).isDisplayed();
			boolean Submitbutton=driver.findElement(By.className("al-diag-test-submit-button")).isDisplayed();
			//System.out.println(timervalue+"--"+questiondescription+"--"+close+"--"+performanceframe+"-"+deficultylevel+"--"+markforreview+"--"+objective+"--"+Submitbutton+"--"+performanceframe+"-"+confidencelevel);
			if(headerpostion < chapternamepostion && timervalue==true && questiondescription==true && close==true && performanceframe==true && deficultylevel==true && objective==true && markforreview==true && confidencelevel==true && Submitbutton==true)
			{
				logger.log(Level.INFO,"Adaptive practice UI contains all the UI");
	        }
			else
			{
				logger.log(Level.INFO,"Adaptive practice UI NOT  contains all the UI");
				Assert.fail("Adaptive practice UI NOT  contains all the UI");
			}
			
			

			
			
		}
		catch(Exception e)
	    {
				  logger.log(Level.SEVERE,"Exception in LoginUsingLTI Application Helper",e);				 
				  Assert.fail();
				  
		}
	}



}
