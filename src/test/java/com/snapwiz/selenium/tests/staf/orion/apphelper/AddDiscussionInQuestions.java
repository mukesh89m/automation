package com.snapwiz.selenium.tests.staf.orion.apphelper;
import java.util.List;
import com.snapwiz.selenium.tests.staf.framework.controller.Driver;
import com.snapwiz.selenium.tests.staf.framework.utility.ReportUtil;

import com.snapwiz.selenium.tests.staf.orion.pagefactory.Dashboard.DashBoard;
import com.snapwiz.selenium.tests.staf.orion.pagefactory.Preview.Preview;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class AddDiscussionInQuestions extends Driver

{

	/*
	 * Add discussion in question
	 * Brajesh-23-01-14
	 */
	public void AddDiscussion(String discussiontext)
	{
        WebDriver driver=Driver.getWebDriver();
		try
		{
			driver.findElement(By.className("al-question-notes-discussion-text")).click();
			Thread.sleep(1000);
			driver.findElement(By.className("al-question-discussion-input-section")).click();
			Actions ac=new Actions(driver);
			driver.findElement(By.className("al-question-discussion-input-section")).sendKeys(discussiontext);
			ac.sendKeys(Keys.RETURN).build().perform();
			Thread.sleep(2000);
			boolean discussionpostblock=driver.findElement(By.id("al-user-discussion-question-content")).isDisplayed();
			if(discussionpostblock==false)
			{
				new Screenshot().captureScreenshotFromAppHelper();
				Assert.fail("discussion text not posted");
			}
            ReportUtil.log("Adding Discussion", "Added Discussion by student", "info");
        }
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromAppHelper();
			Assert.fail("Exception in app helper AddDiscussion in class AddDiscussionInQuestions",e);
		}
	}
	
	/*
	 * reply on discussion
	 */
	public void ReplyToDiscussion(int index,String replytext)
	{
        WebDriver driver=Driver.getWebDriver();
		try
		{
			List<WebElement> allreply=driver.findElements(By.id("al-user-discussion-question-replay"));
			allreply.get(index).click();
			Thread.sleep(1000);
			driver.findElement(By.className("al-discussion-reply-text-section")).click();
			driver.findElement(By.className("al-discussion-reply-text-section")).sendKeys(replytext+Keys.ENTER);
			Thread.sleep(2000);
			
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromAppHelper();
			Assert.fail("Exception in app helper ReplyToDiscussion in class AddDiscussionInQuestions",e);
		}
		
	}

    public void addDiscussionAndNotesOnPractice(int confidencelevel,int numberofquestionattempt,String answeroption,String discussiontext,int discussionNo,String replytext,String notes,int notesNo) throws InterruptedException {
        WebDriver driver=Driver.getWebDriver();
        try {
            DashBoard dashBoard = PageFactory.initElements(driver, DashBoard.class);
            Preview preview = PageFactory.initElements(driver,Preview.class);

            if (answeroption.equalsIgnoreCase("correct")) {
                for (int i = 0; i < numberofquestionattempt; i++) {
                    String confidence = Integer.toString(confidencelevel);

                    if (driver.findElements(By.className("multiple-selection-answer-choice-label-wrapper")).size() > 0) {
                        String corranswer = driver.findElement(By.id("show-debug-correct-answer-block")).getText();
                        char correctanswer;
                        if (corranswer.charAt(17) == '(')
                            correctanswer = corranswer.charAt(18);
                        else
                            correctanswer = corranswer.charAt(17);
                        String correctchoice = Character.toString(correctanswer);

                        String corranswer1 = corranswer;
                        char correctanswer1 = corranswer1.charAt(22);
                        String correctchoice1 = Character.toString(correctanswer1);
                        List<WebElement> answer = driver.findElements(By.className("qtn-label"));
                        for (WebElement answerchoice : answer) {

                            if (answerchoice.getText().trim().equals(correctchoice)) {
                                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", answerchoice);
                                break;
                            }
                        }
                        List<WebElement> answer1 = driver.findElements(By.className("qtn-label"));
                        for (WebElement answerchoice1 : answer1) {

                            if (answerchoice1.getText().trim().equals(correctchoice1)) {
                                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", answerchoice1);
                                break;
                            }
                        }

                    } else if (driver.findElements(By.className("answer-choice-label-wrapper")).size() > 0) {
                        String corranswer = driver.findElement(By.id("show-debug-correct-answer-block")).getText();
                        char correctanswer = corranswer.charAt(17);
                        String correctchoice = Character.toString(correctanswer);
                        List<WebElement> answer = driver.findElements(By.className("qtn-label"));
                        for (WebElement answerchoice : answer) {

                            if (answerchoice.getText().trim().equals(correctchoice)) {
                                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", answerchoice);
                                break;
                            }
                        }
                    } else if (driver.findElements(By.cssSelector("span[class='input-tag-wrapper question-element-wrapper']")).size() > 0) {
                        String corranswertext = driver.findElement(By.id("show-debug-correct-answer-block")).getText();
                        int lastindex = corranswertext.length();
                        String corrcttextanswer = corranswertext.substring(16, lastindex);
                        driver.findElement(By.cssSelector("input[id='1']")).sendKeys(corrcttextanswer);
                    } else if (driver.findElements(By.className("sbHolder")).size() > 0) {
                        String corranswertext = driver.findElement(By.id("show-debug-correct-answer-block")).getText();
                        int lastindex = corranswertext.length();
                        String corrcttextanswer = corranswertext.substring(16, lastindex);
                        driver.findElement(By.className("sbToggle")).click();
                        Thread.sleep(2000);
                        driver.findElement(By.linkText(corrcttextanswer)).click();
                        Thread.sleep(5000);
                    }

                    Thread.sleep(2000);
                    if (confidencelevel != 0) {
                        driver.findElement(By.cssSelector("a[id='" + confidence + "']")).click();//click on confidence level
                        Thread.sleep(2000);
                    }
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("input[title=\"Submit\"]")));
                    Thread.sleep(2000);
                    //add discussion to question

                    for (int j = 0; j < discussionNo; j++) {
                        driver.findElement(By.id("al-practice-question-discussion-icon-section")).click();
                        Thread.sleep(2000);
                        driver.findElement(By.className("al-question-discussion-input-section")).click();
                        Actions ac = new Actions(driver);
                        driver.findElement(By.className("al-question-discussion-input-section")).sendKeys(discussiontext);
                        ac.sendKeys(Keys.RETURN).build().perform();
                        Thread.sleep(2000);
                        boolean discussionpostblock = driver.findElement(By.id("al-user-discussion-question-content")).isDisplayed();
                        if (discussionpostblock == false) {
                            Assert.fail("discussion text not posted");
                        }

                    }
                    //give reply to discussion
                    List<WebElement> allreply = driver.findElements(By.id("al-user-discussion-question-replay"));
                    allreply.get(0).click();
                    Thread.sleep(1000);
                    driver.findElement(By.className("al-discussion-reply-text-section")).click();
                    driver.findElement(By.className("al-discussion-reply-text-section")).sendKeys(replytext + Keys.ENTER);
                    Thread.sleep(2000);
                    driver.findElement(By.id("al-close-question-discussion-wrapper")).click();
                    // add notes to question
                    for (int k = 0; k < notesNo; k++) {
                        driver.findElement(By.className("al-question-notes-icon-section")).click();
                        Thread.sleep(2000);
                        driver.findElement(By.className("al-notes-input-text-section")).click();
                        Thread.sleep(2000);
                        Actions ac = new Actions(driver);
                        driver.findElement(By.className("al-notes-input-text-section")).sendKeys(notes);
                        Thread.sleep(2000);
                        ac.sendKeys(Keys.RETURN).build().perform();
                        Thread.sleep(1000);
                        boolean addednotes = driver.findElement(By.id("al-user-notes-content")).isDisplayed();
                        if (addednotes == false) {
                            Assert.fail("notes not post");
                        }
                    }


                } //delete added notes
                Thread.sleep(2000);
                Actions action = new Actions(driver);
                List<WebElement> we = driver.findElements(By.xpath("//div[@id='al-user-notes-icon-block']"));
                action.moveToElement(we.get(we.size()-1)).build().perform();
                Thread.sleep(2000);
                preview.closeIconOnNotesBlock.get(preview.closeIconOnNotesBlock.size()-1).click();
                Thread.sleep(2000);
                preview.closeIconNotePopUp.click();

            }

        }  catch(Exception e)
        {
            new Screenshot().captureScreenshotFromAppHelper();
            Assert.fail("Exception in app helper AddDiscussion in class AddDiscussionInQuestions",e);
        }
    }


}
