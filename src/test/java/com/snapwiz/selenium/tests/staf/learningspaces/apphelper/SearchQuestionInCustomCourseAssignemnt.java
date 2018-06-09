package com.snapwiz.selenium.tests.staf.learningspaces.apphelper;

import com.snapwiz.selenium.tests.staf.framework.controller.Driver;
import com.snapwiz.selenium.tests.staf.framework.utility.WebDriverUtil;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.MyQuestionBank;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.NewAssignment;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.ScrollElement;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.UIElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.Click;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.TextSend;

public class SearchQuestionInCustomCourseAssignemnt extends Driver
{

	public void searchQuestionInCustomCourseAssignemnt(String serachtext)
	{
		try
		{
			WebDriver driver=Driver.getWebDriver();
            new UIElement().waitAndFindElement(By.className("ls-ins-search-icon"));
            driver.findElement(By.className("ls-ins-search-icon")).click();
            new UIElement().waitAndFindElement(By.cssSelector("input[class='ls-ins-search-question-content ls-ins-search-question-content-selected']"));
            new TextSend().textsendbycssSelector("" + serachtext + "", "input[class='ls-ins-search-question-content ls-ins-search-question-content-selected']");// text entry for search
			new Click().clickByclassname("ls-ins-search-icon");//click on search button
		}
		catch(Exception e)
		{
			Assert.fail("Exception in apphelper SearchQuestionInCustomCourseAssignemnt in method SearchQuestionInCustomCourseAssignemnt ",e);
		}
	}

	public  void selectQuestionFromDifferentChapter(int chapterIndex,String dataIndex){
		WebDriver driver=Driver.getWebDriver();
		MyQuestionBank myQuestionBank = PageFactory.initElements(driver, MyQuestionBank.class);
		NewAssignment newAssignment=PageFactory.initElements(driver, NewAssignment.class);

		try {

			WebDriverUtil.clickOnElementUsingJavascript(newAssignment.browse_link); //click on the browse link
			WebDriverUtil.clickOnElementUsingJavascript(newAssignment.allChapter_link); //click on the all chapter dropdown
			new ScrollElement().scrollToElementUsingCoordinates(newAssignment.chapterName_list.get(chapterIndex));
			WebDriverUtil.clickOnElementUsingJavascript(newAssignment.chapterName_list.get(chapterIndex)); //click on the all chapter dropdown
			WebDriverUtil.clickOnElementUsingJavascript(newAssignment.go_button); //click on the go button
			Thread.sleep(2000);
			new AssignLesson().selectQuestionForCustomAssignment(dataIndex);//select question

		}

		catch(Exception e)
		{
			Assert.fail("Exception in Method selectQuestionFromDifferentChapter of class SearchQuestionInCustomCourseAssignemnt", e);
		}
	}
}
