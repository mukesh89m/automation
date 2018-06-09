package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT13.R55;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Assignment;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.Click;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.ComboBox;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.TextSend;

public class UseEnterToEvaluationInPreviewPage extends Driver
{
	@Test
	public void useEnterToEvaluationInPreviewPage()
	{
		try
		{
			String assessmentname=ReadTestData.readDataByTagName("", "assessmentname", "81");
			String text="You got it right. You got it wrong. You got it Partially Correct.";
			String [] questiontype={"qtn-type-true-false-img","qtn-multiple-choice-img","qtn-multiple-selection-img","qtn-text-entry-img","qtn-text-entry-numeric-img","qtn-text-entry-numeric-units-img","qtn-text-entry-drop-down-img","qtn-numeric-maple-img","qtn-math-symbolic-notation-img","qtn-essay-type"};
			new Assignment().create(81);
			for(int i=7;i<=9;i++)
			{
				new Assignment().addQuestions(81, questiontype[i], assessmentname);
			}
			new Assignment().OpenAssignment(assessmentname, 81);
			//81,90
			for(int i=2;i<=9;i++)
			{
				new Click().clickByid("preview-the-image-quiz");
				String winHandleBefore = driver.getWindowHandle();
				for(String winHandle : driver.getWindowHandles()){
					driver.switchTo().window(winHandle);
				}
				if(driver.findElements(By.className("qtn-label")).size()>=1)
					new Click().clickByclassname("qtn-label");
				else if(driver.findElements(By.cssSelector("input[type='textbox']")).size()>=1)
					new TextSend().textsendbycssSelector("text", "input[type='textbox']");
				else if(driver.findElements(By.className("math-correct-answer-container")).size()>=1)
				{
					new Click().clickBycssselector("div[class='display-correct-answer-math-editor btn sty-green']");
					new Click().clickByclassname("wrs_container");
					new TextSend().textsendbyclass("344", "wrs_container");
					new Click().clickByid("wiris-container-save-formaulaeditor");
				}
				else if(driver.findElements(By.className("sbHolder")).size()>=1)
					new ComboBox().selectValue(0, "option");
				else if(driver.findElements(By.id("html-editor-non-draggable")).size()>=1)
				{
					continue;
				}
				Actions ac=new Actions(driver);
				ac.sendKeys(Keys.ENTER).build().perform();
				Thread.sleep(2000);
				String answercheck=driver.findElement(By.xpath("html/body/div[2]/div[1]/div[5]/div/div/div/div[4]")).getText();
				System.out.println(answercheck);
				if(!text.contains(answercheck))
				{
					Assert.fail("message not display");
				}
				driver.close();
				driver.switchTo().window(winHandleBefore);
				new Click().clickbyxpath("//label[text()='"+Integer.toString(i)+"']");
			}
			
		}
		catch(Exception e)
		{
			Assert.fail("Exception in testcase class useEnterToEvaluationInPreviewPage in class UseEnterToEvaluationInPreviewPage",e);
		}
	}


}
