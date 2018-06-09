package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT11.R22;

import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.AssignLesson;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.SearchQuestionInCustomCourseAssignemnt;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.Click;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.TextFetch;

public class ExpandFunctionalityOfSearchedResult extends Driver{
	@Test(priority=1,enabled=true)
	public void expandFunctionalityOfSearchedResult()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("195");// login as instructor
			new Navigator().NavigateTo("Question Banks");// Navigate to Question Banks
			new Click().clickByid("customAssignment");// click on create custom assignment
			Thread.sleep(2000);
			String searchtext=ReadTestData.readDataByTagName("tocdata", "searchtext", "1");
			new SearchQuestionInCustomCourseAssignemnt().searchQuestionInCustomCourseAssignemnt(searchtext);
			Thread.sleep(2000);
			new Click().clickByid("showExpendQuestionIcon");//click on link to expand the search question
			new Click().clickByclassname("expendQuestion");//click on + icon
			String expandedarea=new TextFetch().textfetchbylistclass("ls-ins-question-wrapper", 0);
			if(!expandedarea.contains("Question Set: "))
			{
				Assert.fail("assessment name not shown");
			}
			if(!expandedarea.contains("Ch"))
			{
				Assert.fail("chapter name not shown");
			}
            String question = new TextFetch().textfetchbyid("cms-question-preview-question-content");
            if(question == null)
			{
				Assert.fail("question is not shown");
			}
			new Click().clickByid("showExpendQuestionIcon");//click on other question expand
			new Click().clickByclassname("expendQuestion");
			new Click().clickByclassname("expendQuestionView");//click on expanded question  icon
			new Click().clickbylistid("showExpendQuestionIcon", 1);//click on 2nd expand question icon

		}
		catch (Exception e) {
			Assert.fail("Exception in testcase ExpandFunctionalityOfSearchedResult in method expandFunctionalityOfSearchedResult ",e);
		}
	}

	@Test(priority=2,enabled=true)
	public void expandFunctionalityofyourquestiontab()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("264");// login as instructor
			new Navigator().NavigateTo("Question Banks");// Navigate to Question Banks
			new Click().clickByid("customAssignment");// click on create custom														// assignment
			String searchtext=ReadTestData.readDataByTagName("tocdata", "searchtext", "1");
			new SearchQuestionInCustomCourseAssignemnt().searchQuestionInCustomCourseAssignemnt(searchtext);
            new AssignLesson().selectQuestionForCustomAssignment("264");
			new Click().clickBycssselector("span[title='Selected Questions (2) ']");
			int expandecount=driver.findElements(By.id("showExpendQuestionIcon")).size();
			((JavascriptExecutor) driver).executeScript("arguments[0].click();",driver.findElements(By.className("expendQuestion")).get(expandecount-1));
			String expandedarea=new TextFetch().textfetchbylistclass("ls-ins-question-wrapper", expandecount-1);
            if(!expandedarea.contains("Question Set: "))
            {
                Assert.fail("assessment name not shown");
            }
            if(!expandedarea.contains("Ch"))
            {
                Assert.fail("chapter name not shown");
            }
            if(!expandedarea.contains("Q"))
			{
				Assert.fail("Question is not shown");
			}
		}
		catch (Exception e) {
			Assert.fail("Exception in testcase ExpandFunctionalityOfSearchedResult in method expandFunctionalityofyourquestiontab ",e);
		}
	}


}
