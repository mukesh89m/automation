package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT14.R149;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.AddDiscussionAndNotesWithQuestion;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Assignment;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.AttemptTest;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.PerformanceProficencyPercent;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.BooleanValue;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.Click;

public class SupportCourseLevelAssessment extends Driver
{
	@Test(enabled = false) //course level assignments are not supported now
	public void supportCourseLevelAssessment()
	{
		try
		{
			String assignmentname=ReadTestData.readDataByTagName("", "assessmentname", "1");
			new Assignment().create(1);
			for(int i=1;i<=15;i++)
			{
				new Assignment().addQuestions(1, "qtn-type-true-false-img", assignmentname);//create static test
			}
			for(int i=1;i<=10;i++)
			{
				new Assignment().addQuestions(11, "qtn-type-true-false-img", assignmentname);
			}
			new LoginUsingLTI().ltiLogin("1");
			new Navigator().NavigateTo("eTextBook");
			new Click().clickByclassname("assessment_media__body");//open static test at course level
			new AttemptTest().StaticTest();//attempt static test
			new Click().clickbylist("report-sidebar-question-card-left", 0);//click on question card
			new AddDiscussionAndNotesWithQuestion().addDiscussion();//8--add disscussion to question
			new AddDiscussionAndNotesWithQuestion().addNote();//9--add disscussion to question
			new Navigator().NavigateTo("eTextBook");
			new Click().clickByclassname("assessment_media__body");//open complete static assessment
			//16
			boolean performancepage=new BooleanValue().booleanbyclass("performance-summary-titleText");//verify performance page
			if(performancepage==false)
			{
				Assert.fail("After click on course level completed assessment its not goes to performance report page ");
			}
			new Navigator().NavigateTo("Proficiency Report");//navigate to proficency report
			Thread.sleep(3000);
			double proficency=new PerformanceProficencyPercent().performanceProficencyPercent("1");//fetch proficency report of above user from database
            String proficencytext=driver.findElement(By.id("al-cource-proficiency-summary")).getText();//fetch proficency report of user from pro
            double pageproficency=Double.parseDouble(proficencytext.substring(0, 2));//
            int prof=new Double(proficency).compareTo(new Double(pageproficency));
            if(prof!=0)
            	Assert.fail("database proficency not equal to diplayed proficency on proficency report page");
 		}
		catch(Exception e)
		{
			Assert.fail("Exception in testcase class SupportCourseLevelAssessment in method supportCourseLevelAssessment",e);
		}
	}
}
