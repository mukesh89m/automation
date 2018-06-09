package com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelper;

import java.util.List;

import com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.AppiumLearningspaces.uihelper.Click;
import com.snapwiz.selenium.tests.staf.AppiumLearningspaces.uihelper.MouseHover;
import com.snapwiz.selenium.tests.staf.AppiumLearningspaces.uihelper.UIElement;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.snapwiz.selenium.tests.staf.AppiumLearningspaces.Driver;
import com.snapwiz.selenium.tests.staf.AppiumLearningspaces.ReadTestData;

public class PracticeTest{
	
	public void startTest()
	{
		try
		{
			new Click().clickBycssselector("div[data-id='orion-adaptive-practice-tab']");
            List<WebElement> startButtons =  Driver.driver.findElements(By.id("practice-test-button"));
            for(WebElement startButton : startButtons) {
                if(startButton.isDisplayed()) {
                    startButton.click();
                    break;
                }
            }
			Thread.sleep(3000);
		}
		catch(Exception e)
		{
			new com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelper.Screenshot().captureScreenshotFromAppHelper();
			Assert.fail("Exception in starting the practice test",e);
		}
	}
	
	public void startStaticPracticeTest(String testName)   // added for static Practice
	{
		try
		{
			Thread.sleep(3000);
            String card1topic1 = ReadTestData.readDataByTagName("tocdata", testName, "1");
			((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("a[title='"+card1topic1+"']")));
		
		}
		catch(Exception e)
		{
			Assert.fail("Exception in app helper startTest in class PracticeTest",e);
		}
	}
	
	public void startAdaptivePracticeTest(String testName)            // Added for Adaptive Practice
	{
		try
		{
			String card1topic1 = ReadTestData.readDataByTagName("tocdata", testName, "1");   // This <adaptivepracticetest> is passed in Testdata.
			((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("a[title='"+card1topic1+"']")));
		
		}
		catch(Exception e)
		{
			Assert.fail("Exception in app helper startTest in class PracticeTest",e);
		}
	}


    public void startTest(int confidenceLevelIndex, int dataIndex)
    {
        try
        {
            String chapterName = ReadTestData.readDataByTagName("", "chapterName", ""+dataIndex);//fetch assignment name from textdata
            WebDriverWait wait  = new WebDriverWait(Driver.driver,60);
            wait.until(ExpectedConditions.textToBePresentInElementLocated(By.className("selected-chapter-name"),"Ch"));
            if(chapterName!=null){
                List<WebElement> chapterHeadingList = Driver.driver.findElements(By.cssSelector("div[class='chapter-heading']"));
                System.out.println("chapterHeadingList : " + chapterHeadingList.size());
                for(int a=0;a<chapterHeadingList.size();a++){
                    System.out.println("chapterHeadingList content : " + chapterHeadingList.get(a).getText());
                    ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].scrollIntoView(true);", chapterHeadingList.get(a));
                    if(chapterHeadingList.get(a).getText().contains(chapterName)){
                        System.out.println("Yes");
                        List<WebElement> duplicateChaptersElementsList = Driver.driver.findElements(By.xpath(".//*[@title='" + chapterName + "']"));
                        System.out.println("duplicateChaptersElementsList ::" + duplicateChaptersElementsList.size());
                        if(duplicateChaptersElementsList.get(duplicateChaptersElementsList.size() - 1).isDisplayed()) {
                            ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", duplicateChaptersElementsList.get(duplicateChaptersElementsList.size() - 1));
                            System.out.println("2222");
                        }
                        wait  = new WebDriverWait(Driver.driver,60);
                        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.className("selected-chapter-name"),chapterName));
                        break;
                    }
                }
            }
            System.out.println("1111");
            new Click().clickBycssselector("div[data-id='orion-adaptive-practice-tab']");
            List<WebElement> conf =  Driver.driver.findElements(By.id(Integer.toString(confidenceLevelIndex)));
            for(WebElement c : conf) {
                if(c.isDisplayed()) {
                    c.click();
                    break;
                }
            }
            List<WebElement> startButtons =  Driver.driver.findElements(By.xpath("//input[@id='practice-test-button']"));
            for(WebElement startButton : startButtons) {
                if(startButton.isDisplayed()) {
                    startButton.click();
                    break;
                }
            }
        }
        catch(Exception e)
        {
            Assert.fail("Exception in app helper startTest in class DiagnosticTest",e);
        }
    }


    public void startTest(int dataIndex)
    {
        try
        {
            String chapterName = ReadTestData.readDataByTagName("", "chapterName", ""+dataIndex);//fetch assignment name from textdata
            WebDriverWait wait  = new WebDriverWait(Driver.driver,60);
            wait.until(ExpectedConditions.textToBePresentInElementLocated(By.className("selected-chapter-name"),"Ch 1:"));
            if(chapterName!=null){
                List<WebElement> chapterHeadingList = Driver.driver.findElements(By.cssSelector("div[class='chapter-heading']"));
                for(int a=0;a<chapterHeadingList.size();a++){
                    ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].scrollIntoView(true);", chapterHeadingList.get(a));
                    if(chapterHeadingList.get(a).getText().contains(chapterName)){
                        List<WebElement> duplicateChaptersElementsList = Driver.driver.findElements(By.xpath(".//*[@title='" + chapterName + "']"));
                        ((JavascriptExecutor)Driver.driver).executeScript("arguments[0].click();", duplicateChaptersElementsList.get(duplicateChaptersElementsList.size() - 1));
                        wait  = new WebDriverWait(Driver.driver,60);
                        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.className("selected-chapter-name"),chapterName));
                        break;
                    }
                }
            }
            new Click().clickBycssselector("div[data-id='orion-adaptive-practice-tab']");
            List<WebElement> startButtons =  Driver.driver.findElements(By.id("practice-test-button"));
            for(WebElement startButton : startButtons) {
                if(startButton.isDisplayed()) {
                    startButton.click();
                    break;
                }
            }
        }
        catch(Exception e)
        {
            Assert.fail("Exception in app helper startTest in class PracticeTest",e);
        }
    }
	
	public void AttemptCorrectAnswer(int confidencelevel, String tcIndex)
	{
		try
		{

            new UIElement().waitAndFindElement(By.className("question-label"));
			String clickNextButton = new ReadTestData().readDataByTagName("","clickNextButton",tcIndex);
            String confidence=Integer.toString(confidencelevel);
            if(Driver.driver.findElements(By.className("preview-multiple-select-answer-choice")).size()>0)
                new AttemptDiagnosticQuestion().attemptMultipleSelection("correct", false, false, confidence, "");

            else if(Driver.driver.findElements(By.className("preview-single-selection-answer-choices")).size() > 0)
                new AttemptDiagnosticQuestion().attemptMultipleChoice("correct", false, false, confidence, "");

            else if(Driver.driver.findElements(By.className("true-false-answer-choices")).size() > 0)
                new AttemptDiagnosticQuestion().attemptTrueFalse("correct", false, false, confidence, "");

            else if(Driver.driver.findElements(By.cssSelector("div[class='text-entry-input-wrapper visible_redactor_input_wrapper']")).size() > 0)
                new AttemptDiagnosticQuestion().attemptTextEntry("correct", false, false, confidence, "");

            else if(Driver.driver.findElements(By.cssSelector("div[class='text-entry-dropdown-wrapper visible_redactor_input_wrapper']")).size() > 0)
                new AttemptDiagnosticQuestion().attemptTextDropDown("correct", false, false, confidence, "");

            else if(Driver.driver.findElements(By.className("numeric_entry_student_title")).size() > 0)
                new AttemptDiagnosticQuestion().attemptNumericEntryWithUnits("correct", false, false, confidence, "");

            else if(Driver.driver.findElements(By.cssSelector("div[class='text-entry-input-wrapper visible_redactor_input_wrapper']")).size() > 0)
                new AttemptDiagnosticQuestion().attemptAdvancedNumeric("correct", false, false, confidence, "");

            else if(Driver.driver.findElements(By.cssSelector("div[class='symb-notation-math-edit-icon-preview']")).size() > 0)
                new AttemptDiagnosticQuestion().attemptExpressionEvaluator("correct", false, false, confidence, "");

            else if(Driver.driver.findElements(By.cssSelector("div[id='html-editor-non-draggable']")).size() > 0)
                new AttemptDiagnosticQuestion().attemptEssayType("correct", false, false, confidence, "");

		((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.id("submit-practice-question-button")));
            int noticesize = Driver.driver.findElements(By.className("al-notification-message-body")).size();
		if(noticesize==1)
		{
			String notice =  Driver.driver.findElement(By.className("al-notification-message-body")).getText();			
			//if(notice.equals("You will build your proficiency faster if you say how confident you feel about the question before answering."))
			//{
				Thread.sleep(3000);
				Driver.driver.findElement(By.cssSelector("div[id='close-al-notification-dialog']")).click();
            if(Driver.driver.findElement(By.id("next-practice-question-button")).isDisplayed() == true)
                ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.id("next-practice-question-button")));

            else {

                ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.id("submit-practice-question-button")));
                Thread.sleep(2000);

            }
		}
		Thread.sleep(2000);
			if(clickNextButton == null || clickNextButton.equals("true")) {
				((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.id("next-practice-question-button")));
			}
		Thread.sleep(2000);

        }
	catch(Exception e)
	{
		//new Screenshot().captureScreenshotFromAppHelper();
		Assert.fail("Exception in App helper AttemptCorrectAnswer in class PracticeTest",e);
	}
}
	
	public void attemptStaticPracticeTest(int confidencelevel)
	{
		try
		{
			int timer=Driver.driver.findElements(By.cssSelector("span[id='timer-label']")).size();
			System.out.println("timer:"+timer);
		while(timer!=0)
		{
			if(Driver.driver.findElements(By.className("answer-choice-label-wrapper")).size() > 0)
			{						
			((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();",Driver.driver.findElement(By.className("qtn-label")));							
		
			}
			
			if(confidencelevel!=0)
			{
				Driver.driver.findElement(By.cssSelector("a[id='"+confidencelevel+"']")).click();//click on confidence level
				Thread.sleep(2000);
			}
			((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("input[type='button']")));
			Thread.sleep(2000);
			int noticesize = Driver.driver.findElements(By.className("al-notification-message-body")).size();
			if(noticesize==1)

			{
				String notice =  Driver.driver.findElement(By.className("al-notification-message-body")).getText();
				if(notice.equals("You will build your proficiency faster if you say how confident you feel about the question before answering."))
				{
					Driver.driver.findElement(By.cssSelector("div[id='close-al-notification-dialog']")).click();
					((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.className("submit-practice-question-button")));
				}

			}
			((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("input[type='button']")));
			Thread.sleep(2000);
			timer=Driver.driver.findElements(By.cssSelector("span[id='timer-label']")).size();
		}
		}
		catch(Exception e)
		{
			new com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelper.Screenshot().captureScreenshotFromAppHelper();
			Assert.fail("Exception in App helper AttemptCorrectAnswer in class PracticeTest",e);
		}
		
	}
	
	public void quitThePracticeTest()
	{
		try
		{
			Driver.driver.findElement(By.className("al-quit-diag-test-icon")).click();//click on cross icon
			Thread.sleep(2000);
			Driver.driver.findElement(By.className("ls-practice-test-view-report")).click();//click on view report
			Thread.sleep(3000);
		}
		catch(Exception e)
		{
			Assert.fail("Exception in App helper quitThePracticeTest in class PracticeTest",e);
		}
		
	}
    //start first chapters TLO level practice test
    public void startTloLevelPracticeTest(int dataIndex)
    {
        try
        {
			List<WebElement> totaltlo = Driver.driver.findElements(By.cssSelector("a[data-type='tlo_adaptive_assessment']"));
            ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", totaltlo.get(dataIndex));
            Thread.sleep(3000);
        }
        catch(Exception e)
        {
            new com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelper.Screenshot().captureScreenshotFromAppHelper();
            Assert.fail("Exception in starting the Tlo practice test",e);
        }
    }

    public void startPracticeTest(String chapterName)
    {
        try
        {
            new Click().clickbyxpath("//a[@title='"+chapterName+"']");

        }
        catch(Exception e)
        {
            Assert.fail("Exception in app helper startPracticeTest in class DiagnosticTest",e);
        }
    }

	public void startPracticeTestByInstructor()
	{
		try
		{
			new Click().clickBycssselector("div[data-id='orion-adaptive-practice-tab']");
			List<WebElement> startButtons =  Driver.driver.findElements(By.id("preview-test-button"));
			for(WebElement startButton : startButtons) {
				if(startButton.isDisplayed()) {
					startButton.click();
					break;
				}
			}
			Thread.sleep(3000);
		}
		catch(Exception e)
		{
			new com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelper.Screenshot().captureScreenshotFromAppHelper();
			Assert.fail("Exception in startPracticeTestByInstructor the practice test",e);
		}
	}

    public void attemptPracticeTestAfterAssign(int confidencelevel)
    {
        try
        {
            new UIElement().waitAndFindElement(By.className("question-label"));
            String confidence=Integer.toString(confidencelevel);
            if(Driver.driver.findElements(By.className("preview-multiple-select-answer-choice")).size()>0)
                new AttemptDiagnosticQuestion().attemptMultipleSelection("correct", false, false, confidence, "");

            else if(Driver.driver.findElements(By.className("preview-single-selection-answer-choices")).size() > 0)
                new AttemptDiagnosticQuestion().attemptMultipleChoice("correct", false, false, confidence, "");

            else if(Driver.driver.findElements(By.className("true-false-answer-choices")).size() > 0)
                new AttemptDiagnosticQuestion().attemptTrueFalse("correct", false, false, confidence, "");

            else if(Driver.driver.findElements(By.cssSelector("div[class='text-entry-input-wrapper visible_redactor_input_wrapper']")).size() > 0)
                new AttemptDiagnosticQuestion().attemptTextEntry("correct", false, false, confidence, "");

            else if(Driver.driver.findElements(By.cssSelector("div[class='text-entry-dropdown-wrapper visible_redactor_input_wrapper']")).size() > 0)
                new AttemptDiagnosticQuestion().attemptTextDropDown("correct", false, false, confidence, "");

            else if(Driver.driver.findElements(By.className("numeric_entry_student_title")).size() > 0)
                new AttemptDiagnosticQuestion().attemptNumericEntryWithUnits("correct", false, false, confidence, "");

            else if(Driver.driver.findElements(By.cssSelector("div[class='text-entry-input-wrapper visible_redactor_input_wrapper']")).size() > 0)
                new AttemptDiagnosticQuestion().attemptAdvancedNumeric("correct", false, false, confidence, "");

            else if(Driver.driver.findElements(By.cssSelector("div[class='symb-notation-math-edit-icon-preview']")).size() > 0)
                new AttemptDiagnosticQuestion().attemptExpressionEvaluator("correct", false, false, confidence, "");

            else if(Driver.driver.findElements(By.cssSelector("div[id='html-editor-non-draggable']")).size() > 0)
                new AttemptDiagnosticQuestion().attemptEssayType("correct", false, false, confidence, "");

            Thread.sleep(3000);
            if(Driver.driver.findElement(By.cssSelector("a[class='btn btn--primary btn--large btn--next long-text-button']")).isDisplayed() == true) {
                ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("a[class='btn btn--primary btn--large btn--next long-text-button']")));
            }

            else {
                new WebDriverWait(Driver.driver,120).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("a[class='btn btn--primary btn--large btn--submit long-text-button adaptive-assignment-finish-button']")));
                ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("a[class='btn btn--primary btn--large btn--submit long-text-button adaptive-assignment-finish-button']")));
            }
        }
        catch(Exception e)
        {
            Assert.fail("Exception in App helper attemptPracticeTestAfterAssign in class PracticeTest",e);
        }
    }

    public void openPracticeTestThroughMetaCognitiveReport(int dataIndex){
        try{
            new Navigator().NavigateTo("Metacognitive Report");
            Thread.sleep(2000);
            new MouseHover().mouserhoverbyCss("g.highcharts-markers.highcharts-tracker > path");
            Thread.sleep(2000);
            new Click().clickByid("ls-metacognitive-chapter-start-practice");
            new UIElement().waitAndFindElement(By.className("al-quit-diag-test-icon"));

        }catch(Exception e){
            Assert.fail("Exception in method 'openPracticeTestThroughMetaCognitiveReport' in the class 'navigateToPerformanceReportPage'",e);
        }

    }



    public void openPracticeTestThroughProductivityReport(int dataIndex){
        try{
            new Navigator().NavigateTo("Productivity Report");
            Thread.sleep(2000);
            new MouseHover().mouserhoverbyCss("g.highcharts-markers.highcharts-tracker > path");
            Thread.sleep(2000);
            new Click().clickByid("ls-tlo-start-practice");
            new UIElement().waitAndFindElement(By.className("al-quit-diag-test-icon"));

        }catch(Exception e){
            Assert.fail("Exception in method 'openPracticeTestThroughMetaCognitiveReport' in the class 'navigateToPerformanceReportPage'",e);
        }

    }

    public void openPracticeTestThroughMostChallengingActivitiesReport(int dataIndex){
        try{
            new Navigator().NavigateTo("Most Challenging Activities Report");
            Thread.sleep(2000);
            new Click().clickBycssselector("div.ls-report-row-right-arrow");
            Thread.sleep(2000);
            new Click().clickByclassname("ls-option ls-start-practice-button");
            new UIElement().waitAndFindElement(By.className("al-quit-diag-test-icon"));

        }catch(Exception e){
            Assert.fail("Exception in method 'openPracticeTestThroughMetaCognitiveReport' in the class 'navigateToPerformanceReportPage'",e);
        }

    }


	}




