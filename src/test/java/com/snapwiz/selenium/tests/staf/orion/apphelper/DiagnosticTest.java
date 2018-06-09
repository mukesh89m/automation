package com.snapwiz.selenium.tests.staf.orion.apphelper;

//import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.snapwiz.selenium.tests.staf.framework.utility.ReportUtil;
import com.snapwiz.selenium.tests.staf.framework.utility.WebDriverUtil;
import com.snapwiz.selenium.tests.staf.orion.Config;
import com.snapwiz.selenium.tests.staf.orion.pagefactory.CMS.Preview;
import com.snapwiz.selenium.tests.staf.orion.uihelper.Click;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.snapwiz.selenium.tests.staf.framework.controller.Driver;
import com.snapwiz.selenium.tests.staf.orion.uihelper.RandomNumber;


public class DiagnosticTest extends  Driver{

	public void startTest(int index,int confidencelevel)
	{
		WebDriver driver=Driver.getWebDriver();
		try
		{
			String confidence=Integer.toString(confidencelevel);
			List<WebElement> allbegin=driver.findElements(By.cssSelector("img[title=\"Begin Diagnostic\"]"));
			((JavascriptExecutor)driver).executeScript("arguments[0].click();",allbegin.get(index));//click on test ,thats we want to start
			Thread.sleep(2000);
			driver.findElement(By.id(confidence.trim())).click();//click on confidence level
			Thread.sleep(2000);
			driver.findElement(By.className("al-continue-to-diagnostic")).click();//click on continue button to start test
			Thread.sleep(2000);
            ReportUtil.log("Starting Diagnostic Test", "Diagnostic test is started", "info");
        }
		catch(Exception e)
		{
			Assert.fail("Exception in app helper startTest in class DiagnosticTest",e);
		}
	}



    public void continueDiagnosticTest(int index,int confidencelevel)
    {
		WebDriver driver=Driver.getWebDriver();
        try
        {
            String confidence=Integer.toString(confidencelevel);
            List<WebElement> allbegin=driver.findElements(By.cssSelector("img[title=\"Continue Diagnostic\"]"));
            ((JavascriptExecutor)driver).executeScript("arguments[0].click();",allbegin.get(index));//click on test ,thats we want to start
            Thread.sleep(2000);
            driver.findElement(By.cssSelector("a[id='"+confidence+"']")).click();//click on confidence level
            Thread.sleep(2000);
        }
        catch(Exception e)
        {
            Assert.fail("Exception in app helper continueDiagnosticTest in class DiagnosticTest",e);
        }
    }

	public void openLastDiagnosticTest()
	{
		WebDriver driver=Driver.getWebDriver();
		try
		{
			//open last chapter diag test
			List<WebElement> allbegin=driver.findElements(By.cssSelector("img[title='Begin Diagnostic']"));
			((JavascriptExecutor)driver).executeScript("arguments[0].click();",allbegin.get(allbegin.size()-1));//click on latest created test 
			Thread.sleep(2000);
			driver.findElement(By.cssSelector("a[id='2']")).click();//click on confidence level
			Thread.sleep(2000);
			driver.findElement(By.className("al-continue-to-diagnostic")).click();//click on continue button to start test
			Thread.sleep(2000);
		}
		catch(Exception e)
		{
			Assert.fail("Exception in app helper openLastDiagnosticTest in class DiagnosticTest",e);
		}
	}

	public void quitTestAndGoToDashboard()
	{
		WebDriver driver=Driver.getWebDriver();
		try
		{
			Thread.sleep(2000);
			driver.findElement(By.className("al-quit-diag-test-icon")).click();
			Thread.sleep(2000);
			driver.findElement(By.className("al-quit-diag-test")).click();
			Thread.sleep(2000);
			driver.findElement(By.className("al-summary-performance-link")).click();
			Thread.sleep(2000);
            ReportUtil.log("Quitting Diagnostic Test & Go to Dashboard", "Diagnostic test is quit & navigated to dashboard", "info");
        }
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromAppHelper();
			Assert.fail("Exception in quitting the diagnostic test",e);
		}
	}

	public void lessThanThirtyOnePercentCorrect()
	{
		WebDriver driver=Driver.getWebDriver();
		try
		{			
			int timer=driver.findElements(By.cssSelector("span[id='cntdwn']")).size();
			boolean attempted = false;
			List<String> tlosattempted = new ArrayList<String>();
			while(timer!=0)
			{
				Thread.sleep(2000);
				String tlonamefound = driver.findElement(By.className("al-diagtest-skills-evaluted-ul")).getText().substring(2);
				for(String tlo : tlosattempted)
				{
					if(tlo.equals(tlonamefound))
					{
						attempted = true;
					}
				}
				if(attempted == false)
				{
					tlosattempted.add(tlonamefound);
					new DiagnosticTest().attemptCorrect(0);
				}
				else
				{
					new DiagnosticTest().attemptIncorrect(0);
				}
				attempted = false;
				timer=driver.findElements(By.cssSelector("span[id='cntdwn']")).size();
			}

		}
		catch(Exception e)
		{
			Assert.fail("Exception in app helper lessThanThirtyOnePercentCorrect in class DiagnosticTest",e);
		}
	}

	public void thirtyOneToSixtyPercentCorrect()
	{
		WebDriver driver=Driver.getWebDriver();
		try
		{	
			int timer=driver.findElements(By.cssSelector("span[id='cntdwn']")).size();
			boolean attempted = false;
			List<String> tlosattempted = new ArrayList<String>();
			int tlopresencecount = 0;
			while(timer!=0)
			{
				String tlonamefound = driver.findElement(By.className("al-diagtest-skills-evaluted-ul")).getText().substring(2);
				for(String tlo : tlosattempted)
				{
					if(tlo.equals(tlonamefound))
					{
						tlopresencecount++;
					}
				}
				if(tlopresencecount == 2)
					attempted = true;

				if(attempted == false)
				{
					tlosattempted.add(tlonamefound);
					new DiagnosticTest().attemptCorrect(0);
				}
				else
				{
					new DiagnosticTest().attemptIncorrect(0);
				}
				attempted = false;
				tlopresencecount = 0;
				timer=driver.findElements(By.cssSelector("span[id='cntdwn']")).size();
			}

		}
		catch(Exception e)
		{
			Assert.fail("Exception in app helper thirtyOneToSixtyPercentCorrect in class DiagnosticTest",e);
		}
	}
	public void moreThanSixtyPercentCorrect()
	{
		WebDriver driver=Driver.getWebDriver();
		try
		{	
			int timer=driver.findElements(By.cssSelector("span[id='cntdwn']")).size();
			boolean attempted = false;
			List<String> tlosattempted = new ArrayList<String>();
			int tlopresencecount = 0;
			while(timer!=0)
			{
				String tlonamefound = driver.findElement(By.className("al-diagtest-skills-evaluted-ul")).getText().substring(2);
				for(String tlo : tlosattempted)
				{
					if(tlo.equals(tlonamefound))
					{
						tlopresencecount++;
					}
				}
				if(tlopresencecount == 3)
					attempted = true;

				if(attempted == false)
				{
					tlosattempted.add(tlonamefound);
					new DiagnosticTest().attemptCorrect(0);
				}
				else
				{
					new DiagnosticTest().attemptIncorrect(0);
				}
				attempted = false;
				tlopresencecount = 0;
				timer=driver.findElements(By.cssSelector("span[id='cntdwn']")).size();
			}

		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromAppHelper();
			Assert.fail("Exception in app helper thirtyOneToSixtyPercentCorrect in class DiagnosticTest",e);
		}
	}

	public void attemptRandomly(int confidencelevel)
	{
		WebDriver driver=Driver.getWebDriver();
		try
		{
			int timer=driver.findElements(By.cssSelector("span[id='timer-label']")).size();
			while(timer!=0)
			{		
				int random = new RandomNumber().generateRandomNumber(0, 100);
				if(random > 50)
					attemptCorrect(confidencelevel);
				else
					attemptIncorrect(confidencelevel);
				timer=driver.findElements(By.cssSelector("span[id='timer-label']")).size();
			}
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromAppHelper();
			Assert.fail("Exception in app helper attemptRandomly in class DiagnosticTest",e);
		}
	}

	public void attemptCorrect(int confidencelevel)
	{
		WebDriver driver=Driver.getWebDriver();
		try
		{
			String confidence=Integer.toString(confidencelevel);

			String innerHtml=driver.findElement(By.className("al-diag-test-question-raw-content")).getAttribute("innerHTML");

			if(innerHtml.contains("input-tag-wrapper")){
				if(driver.findElements(By.cssSelector("span[class='input-tag-wrapper question-element-wrapper']")).size() > 0)
				{
					String corranswertext=driver.findElement(By.id("show-debug-correct-answer-block")).getText();
					int lastindex=corranswertext.length();
					String corrcttextanswer=corranswertext.substring(16, lastindex);
					driver.findElement(By.cssSelector("input[id='1']")).sendKeys(corrcttextanswer);
				}
			}
			else if(innerHtml.contains("select-box-tag-wrapper")){
//				if(driver.findElements(By.className("sbHolder")).size() > 0)
//				{
					String corranswertext=driver.findElement(By.id("show-debug-correct-answer-block")).getText();
					int lastindex=corranswertext.length();
					String corrcttextanswer=corranswertext.substring(16, lastindex);
				    WebDriverUtil.clickOnElementUsingJavascript(driver.findElement(By.className("sbToggle")));
					WebDriverUtil.waitTillVisibilityOfElement(driver.findElement(By.className("sbOptions")),10);
					driver.findElement(By.linkText(corrcttextanswer)).click();
//				}
			}
			else if(driver.findElements(By.className("answer-choice-label-wrapper")).size() > 0)
			{
				String corranswer=driver.findElement(By.id("show-debug-correct-answer-block")).getText();
				char correctanswer=corranswer.charAt(17);
				String correctchoice=Character.toString(correctanswer);
				WebDriverUtil.clickOnElementUsingJavascript(driver.findElement(By.xpath(".//*[@class='qtn-label' and contains(.,\""+correctchoice+"\")]")));
			}

			else if(driver.findElements(By.className("multiple-selection-answer-choice-label-wrapper")).size()>0)
			{
				String corranswer=driver.findElement(By.id("show-debug-correct-answer-block")).getText();
				String[] arrCorrAns=corranswer.split(":")[1].trim().replaceAll("\\(","").replaceAll("\\)","").replaceAll(" ","").split("");
				for(String corrChoice:arrCorrAns) {
					if(!corrChoice.equals(""))
						driver.findElement(By.xpath(".//*[contains(@class,'qtn-label')][contains(.,'" + corrChoice + "')]")).click();
				}

			}

			Thread.sleep(2000);
			if(confidencelevel!=0)
			{
				driver.findElement(By.cssSelector("a[id='"+confidence+"']")).click();//click on confidence level
				Thread.sleep(2000);
			}
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("al-diag-test-submit")));

			try {
				(new WebDriverWait(driver, 2))
                        .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("span[class='al-summary-performance-link']")));
				WebDriverUtil.clickOnElementUsingJavascript(driver.findElement(By.cssSelector("span[class='al-summary-performance-link']")));
			} catch (Exception e) {
			}
			Thread.sleep(2000);
            if(driver.findElement(By.cssSelector(".robo-icon>img")).getAttribute("src").contains("robo-icon-on")) {
                int noticesize = driver.findElements(By.className("al-notification-message-body")).size();
                if (noticesize == 1) {
                    String notice = driver.findElement(By.className("al-notification-message-body")).getText();
                    if (notice.equals("You will build your proficiency faster if you say how confident you feel about the question before answering.")) {
                        driver.findElement(By.cssSelector("div[id='close-al-notification-dialog']")).click();
                        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("al-diag-test-submit")));
						try {
							(new WebDriverWait(driver, 2))
                                    .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("span[class='al-summary-performance-link']")));
							WebDriverUtil.clickOnElementUsingJavascript(driver.findElement(By.cssSelector("span[class='al-summary-performance-link']")));

						} catch (Exception e) {
						}
					}

                }
            }
		}
		catch(Exception e)
		{
			Assert.fail("Exception in app helper attemptCorrect in class DiagnosticTest",e);
		}
	}

	public void attemptIncorrect(int confidencelevel)
	{
		WebDriver driver=Driver.getWebDriver();
		try
		{
			String confidence=Integer.toString(confidencelevel);	

			if(driver.findElements(By.className("multiple-selection-answer-choice-label-wrapper")).size()>0)
			{
				String corranswer=driver.findElement(By.id("show-debug-correct-answer-block")).getText();
				char correctanswer;
				if(corranswer.charAt(17) == '(')
					correctanswer=corranswer.charAt(18);
				else
					correctanswer=corranswer.charAt(17);

				String correctchoice=Character.toString(correctanswer); //first correct choice

				char correctanswer1=corranswer.charAt(22);		
				String correctchoice1=Character.toString(correctanswer1); //second correct choice			  

				if((correctchoice.equals("A") && correctchoice1.equals("B")) || (correctchoice.equals("B") && correctchoice1.equals("A")) )
				{	correctchoice="C"; correctchoice1 = "D";} 
				else if(correctchoice.equals("A") && correctchoice1.equals("C") || (correctchoice.equals("C") && correctchoice1.equals("A")))
				{	correctchoice="B"; correctchoice1 = "D"; }
				else if(correctchoice.equals("A") && correctchoice1.equals("D") || (correctchoice.equals("D") && correctchoice1.equals("A")))
				{ correctchoice="B"; correctchoice1 = "C";}
				else if(correctchoice.equals("A") && correctchoice1.equals("E") || (correctchoice.equals("E") && correctchoice1.equals("A")))
				{ correctchoice="B"; correctchoice1 = "C";}
				else if(correctchoice.equals("A") && correctchoice1.equals("F") || (correctchoice.equals("F") && correctchoice1.equals("A")))
				{ correctchoice="B"; correctchoice1 = "C";}


				else if((correctchoice.equals("B") && correctchoice1.equals("C")) || (correctchoice.equals("C") && correctchoice1.equals("B")) )
				{	correctchoice="A"; correctchoice1 = "D";} 
				else if(correctchoice.equals("B") && correctchoice1.equals("D") || (correctchoice.equals("D") && correctchoice1.equals("B")))
				{	correctchoice="A"; correctchoice1 = "C"; }
				else if(correctchoice.equals("B") && correctchoice1.equals("E") || (correctchoice.equals("E") && correctchoice1.equals("B")))
				{ correctchoice="A"; correctchoice1 = "C";}
				else if(correctchoice.equals("B") && correctchoice1.equals("F") || (correctchoice.equals("F") && correctchoice1.equals("B")))
				{ correctchoice="A"; correctchoice1 = "C";}

				else if((correctchoice.equals("C") && correctchoice1.equals("D")) || (correctchoice.equals("D") && correctchoice1.equals("C")) )
				{	correctchoice="A"; correctchoice1 = "B";} 
				else if(correctchoice.equals("C") && correctchoice1.equals("E") || (correctchoice.equals("E") && correctchoice1.equals("C")))
				{	correctchoice="A"; correctchoice1 = "B"; }
				else if(correctchoice.equals("C") && correctchoice1.equals("F") || (correctchoice.equals("F") && correctchoice1.equals("C")))
				{ correctchoice="A"; correctchoice1 = "B";}


				else if((correctchoice.equals("D") && correctchoice1.equals("E")) || (correctchoice.equals("E") && correctchoice1.equals("D")) )
				{	correctchoice="A"; correctchoice1 = "B";} 
				else if(correctchoice.equals("D") && correctchoice1.equals("F") || (correctchoice.equals("F") && correctchoice1.equals("D")))
				{	correctchoice="A"; correctchoice1 = "B"; }

				else if((correctchoice.equals("E") && correctchoice1.equals("F")) || (correctchoice.equals("F") && correctchoice1.equals("E")) )
				{	correctchoice="A"; correctchoice1 = "B";}

				//String corranswer1=driver.findElement(By.id("show-debug-correct-answer-block")).getText();

				List<WebElement> answer = driver.findElements(By.className("qtn-label"));
				for (WebElement answerchoice: answer)
				{

					if(answerchoice.getText().trim().equals(correctchoice))
					{
						((JavascriptExecutor) driver).executeScript("arguments[0].click();",answerchoice);					
						break;
					}
				}
				List<WebElement> answer1 = driver.findElements(By.className("qtn-label"));
				for (WebElement answerchoice1: answer1)
				{

					if(answerchoice1.getText().trim().equals(correctchoice1))
					{
						((JavascriptExecutor) driver).executeScript("arguments[0].click();",answerchoice1);					
						break;
					}
				}

			}

			else if(driver.findElements(By.className("answer-choice-label-wrapper")).size() > 0)
			{

				String corranswer=driver.findElement(By.id("show-debug-correct-answer-block")).getText();
				char correctanswer = corranswer.charAt(17);		
				String correctchoice=Character.toString(correctanswer);
				if(correctchoice.equals("A"))
					correctchoice="B";
				else if(correctchoice.equals("B"))
					correctchoice="A";
				else if(correctchoice.equals("C"))
					correctchoice="D";
				else if(correctchoice.equals("D"))
					correctchoice="C";		


				List<WebElement> answer = driver.findElements(By.className("qtn-label"));
				for (WebElement answerchoice: answer)
				{

					if(answerchoice.getText().trim().equals(correctchoice))
					{
						((JavascriptExecutor) driver).executeScript("arguments[0].click();",answerchoice);					
						break;
					}
				}			
			}
			else if(driver.findElements(By.cssSelector("span[class='input-tag-wrapper question-element-wrapper']")).size() > 0)	
			{
				String corranswertext=driver.findElement(By.id("show-debug-correct-answer-block")).getText();
				int lastindex=corranswertext.length();
				String corrcttextanswer=corranswertext.substring(16, lastindex);
				driver.findElement(By.cssSelector("input[id='1']")).sendKeys(corrcttextanswer+"bh");
			}
			else if(driver.findElements(By.className("sbHolder")).size() > 0)
			{			
				String corranswertext=driver.findElement(By.id("show-debug-correct-answer-block")).getText();
				int lastindex=corranswertext.length();
				String corrcttextanswer=corranswertext.substring(16, lastindex);
				driver.findElement(By.className("sbToggle")).click();
				Thread.sleep(2000);

				String values = driver.findElement(By.cssSelector("ul[class='sbOptions']")).getText();
				//values = values.replaceAll("\n", " ");
				String [] val = values.split("\n"); 


				for(String element:val)
				{

					if(!element.equals(corrcttextanswer))
					{

						driver.findElement(By.linkText(element)).click();
						break;
					}
				}
			}	
			Thread.sleep(2000);
			if(confidencelevel!=0)
			{
				driver.findElement(By.cssSelector("a[id='"+confidence+"']")).click();//click on confidence level
				Thread.sleep(2000);
			}
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("al-diag-test-submit-button")));
			Thread.sleep(2000);
			int noticesize = driver.findElements(By.className("al-notification-message-body")).size();
			if(noticesize==1)

			{
				String notice =  driver.findElement(By.className("al-notification-message-body")).getText();
				if(notice.equals("You will build your proficiency faster if you say how confident you feel about the question before answering."))
				{
					driver.findElement(By.cssSelector("div[id='close-al-notification-dialog']")).click();
					((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("al-diag-test-submit-button")));
				}

			}


		}
		catch(Exception e)
		{
			Assert.fail("Exception in app helper attemptIncorrect in class DiagnosticTest",e);
		}
	}

	public void attemptSkip(int confidencelevel)
	{
		WebDriver driver=Driver.getWebDriver();
		try
		{
			String confidence=Integer.toString(confidencelevel);
			if(confidencelevel!=0)
			{
				driver.findElement(By.cssSelector("a[id='"+confidence+"']")).click();//click on confidence level
				Thread.sleep(2000);
			}
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("al-diag-test-submit-button")));
			Thread.sleep(2000);
			int noticesize = driver.findElements(By.className("al-notification-message-body")).size();
			if(noticesize==1)

			{
				String notice =  driver.findElement(By.className("al-notification-message-body")).getText();
				if(notice.equals("You will build your proficiency faster if you say how confident you feel about the question before answering."))
				{
					driver.findElement(By.cssSelector("div[id='close-al-notification-dialog']")).click();
					((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("al-diag-test-submit-button")));
				}

			}
		}
		catch(Exception e)
		{
			Assert.fail("Exception in app helper attemptSkip in class DiagnosticTest",e);
		}
	}

	public void attemptPartialCorrect(int confidencelevel,boolean useHints)
	{
		WebDriver driver=Driver.getWebDriver();
		try
		{
			String confidence=Integer.toString(confidencelevel);	
			if(useHints == true)					
				driver.findElement(By.cssSelector("img[title=\"Hint\"]")).click();
			if(driver.findElements(By.className("multiple-selection-answer-choice-label-wrapper")).size()>0)
			{
				String corranswer=driver.findElement(By.id("show-debug-correct-answer-block")).getText();
				char correctanswer;							  
				if(corranswer.charAt(17) == '(')
					correctanswer=corranswer.charAt(18);
				else
					correctanswer=corranswer.charAt(17);

				String correctchoice=Character.toString(correctanswer);

				List<WebElement> answer = driver.findElements(By.className("qtn-label"));
				for (WebElement answerchoice: answer)
				{

					if(answerchoice.getText().trim().equals(correctchoice))
					{
						((JavascriptExecutor) driver).executeScript("arguments[0].click();",answerchoice);					
						break;
					}
				}		

			}


			Thread.sleep(2000);
			if(confidencelevel!=0)
			{
				driver.findElement(By.cssSelector("a[id='"+confidence+"']")).click();//click on confidence level
				Thread.sleep(2000);
			}
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("al-diag-test-submit-button")));
			Thread.sleep(2000);
			int noticesize = driver.findElements(By.className("al-notification-message-body")).size();
			if(noticesize==1)

			{
				String notice =  driver.findElement(By.className("al-notification-message-body")).getText();
				if(notice.equals("You will build your proficiency faster if you say how confident you feel about the question before answering."))
				{
					driver.findElement(By.cssSelector("div[id='close-al-notification-dialog']")).click();
					((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("al-diag-test-submit-button")));
				}

			}
		}
		catch(Exception e)
		{
			Assert.fail("Exception in app helper attemptCorrect in class DiagnosticTest",e);
		}
	}

	public void attemptAllCorrect(int confidencelevel,boolean useHints,boolean useSolutionText)
	{
		WebDriver driver=Driver.getWebDriver();
        try {
            int timer=1;
            int questionCount = 0;
            while(timer!=0) {
				boolean attempted = false;
				if (useHints == true) {
					driver.findElement(By.cssSelector("img[title=\"Hint\"]")).click();
				}
				//True False and  multiple choice Single Select Question Type
				Thread.sleep(1000);
				String correctAns = driver.findElement(By.id("show-debug-correct-answer-block")).getText().trim();
				if (!correctAns.isEmpty()) {
					try {

						char correctAnswerOption = correctAns.charAt(17);
						String correctChoice = Character.toString(correctAnswerOption);
						System.out.println("correctChoice:" + correctChoice);
						driver.findElement(By.xpath("//label[@class='qtn-label']"));
						List<WebElement> answerIndex = driver.findElements(By.xpath("//label[@class='qtn-label']"));

						if (correctChoice.equals("A")) {

							answerIndex.get(0).click();
						} else if (correctChoice.equals("B")) {
							answerIndex.get(1).click();

						} else if (correctChoice.equals("C")) {
							answerIndex.get(2).click();

						} else if (correctChoice.equals("D")) {
							answerIndex.get(3).click();
						}
						attempted = true;

					} catch (Exception e) {

					}
					//Multiple Selection Question Type
					if (attempted == false) {
						try {
							System.out.println("attempting multiple selection");
							Thread.sleep(3000);
							driver.findElement(By.className("multiple-selection-answer-choice-label-wrapper"));
							String correctAnswer1 = driver.findElement(By.id("show-debug-correct-answer-block")).getText();
							char correctOption1;
							if (correctAnswer1.charAt(17) == '(') {
								correctOption1 = correctAnswer1.charAt(18);
							} else {
								correctOption1 = correctAnswer1.charAt(17);
							}

							String correctChoice1 = Character.toString(correctOption1); //Answer 1

							String correctAnswer2 = driver.findElement(By.id("show-debug-correct-answer-block")).getText();
							char correctOption2 = correctAnswer2.charAt(22);

							String correctChoice2 = Character.toString(correctOption2); //Answer 2
							List<WebElement> answerIndex = driver.findElements(By.xpath("//div[@class='multiple-selection-answer-choice-label-wrapper']/label"));

							//String answerIndex = "0";
							if (correctChoice1.equals("A")) {
								answerIndex.get(0).click();
							} else if (correctChoice1.equals("B")) {
								answerIndex.get(1).click();
							} else if (correctChoice1.equals("C")) {
								answerIndex.get(2).click();
							} else if (correctChoice1.equals("D")) {
								answerIndex.get(3).click();
							} else if (correctChoice1.equals("E")) {
								answerIndex.get(4).click();
							} else if (correctChoice1.equals("F")) {
								answerIndex.get(5).click();
							}


							if (correctChoice2.equals("A")) {
								answerIndex.get(0).click();
							} else if (correctChoice2.equals("B")) {
								answerIndex.get(1).click();
							} else if (correctChoice2.equals("C")) {
								answerIndex.get(2).click();
							} else if (correctChoice2.equals("D")) {
								answerIndex.get(3).click();
							} else if (correctChoice2.equals("E")) {
								answerIndex.get(4).click();
							} else if (correctChoice2.equals("F")) {
								answerIndex.get(5).click();
							}
							attempted = true;

						} catch (Exception e) {

						}
					}
					//Drop Down Question Type
					if (attempted == false) {
						try {
							Thread.sleep(4000);
							System.out.println("Attempting text dropdown");
							driver.findElement(By.className("sbHolder"));
							String correctAnswerText = driver.findElement(By.id("show-debug-correct-answer-block")).getText();
							String correctAnswer = correctAnswerText.substring(16, correctAnswerText.length());
							driver.findElement(By.className("sbToggle")).click();
							Thread.sleep(1000);
							driver.findElement(By.linkText(correctAnswer)).click();

							//String corrcttextanswer = "ghi";
							//new Select(driver.findElement(By.className("question-raw-content-dropdown"))).selectByIndex(1);
							attempted = true;
						} catch (Exception e) {

						}
					}
					//Advanced Numeric Question Type
					if (attempted == false) {
						try {
							Thread.sleep(3000);
							System.out.println("Attempting Advanced Numeric");
							String correctAnswerText = driver.findElement(By.id("show-debug-correct-answer-block")).getText();
							String correctAnswer = correctAnswerText.substring(16, correctAnswerText.length());
							driver.findElement(By.cssSelector("span[class='input-tag-wrapper question-element-wrapper cde']>input")).sendKeys(correctAnswer);
							attempted = true;
						} catch (Exception e) {

						}
					}
					//NumericEntryWithUnits Question Type
					if (attempted == false) {
						try {
							Thread.sleep(3000);
							System.out.println("Attempting numeric entry with units");
							Preview preview = PageFactory.initElements(driver, Preview.class);
							String correctAnswerText = driver.findElement(By.id("show-debug-correct-answer-block")).getText();
							String correctAnswer = correctAnswerText.substring(16, correctAnswerText.length());
							preview.textEntry_textBox.sendKeys(correctAnswer);
							preview.selectUnits_dropdown.click();
							preview.selectUnits.get(1).click();//select feet
							attempted = true;
						} catch (Exception e) {

						}
					}
					//Expressin Evaluater Question Type
					if (attempted == false) {
						try {
							Thread.sleep(3000);
							System.out.println("Attempting Expressin Evaluater");
							String correctAnswerText = driver.findElement(By.id("show-debug-correct-answer-block")).getText();
							String correctAnswer = correctAnswerText.substring(16, correctAnswerText.length());
							driver.findElement(By.cssSelector("div[class='display-correct-answer-math-editor btn sty-green']")).click();
							driver.findElement(By.cssSelector("button[title='Square root']")).click();
							driver.findElement(By.cssSelector("input[class='wrs_focusElement']")).sendKeys(correctAnswer);
							driver.findElement(By.id("wiris-container-save-formaulaeditor")).click();
							attempted = true;
						} catch (Exception e) {

						}
					}
					//Text Entry Question Type
					if (attempted == false) {
						try {
							driver.findElement(By.cssSelector("span[class='input-tag-wrapper question-element-wrapper']"));
							String correctAnswerText = driver.findElement(By.id("show-debug-correct-answer-block")).getText();
							String correctAnswer = correctAnswerText.substring(16, correctAnswerText.length());
							driver.findElement(By.cssSelector("input[id='1']")).sendKeys(correctAnswer);
							attempted = true;
						} catch (Exception e) {

						}
					}
					try {
						Thread.sleep(500);
						if (confidencelevel != 0) {
							driver.findElement(By.cssSelector("a[id='" + Integer.toString(confidencelevel) + "']")).click();//click on confidence level
							Thread.sleep(2000);
						}

					} catch (Exception e) {

					}

					try {
						(new WebDriverWait(driver, 2))
								.until(ExpectedConditions.presenceOfElementLocated(By.className("al-diag-test-submit-button"))).click();
						(new WebDriverWait(driver, 2))
								.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("span[class='al-summary-performance-link']")));
						 WebDriverUtil.clickOnElementUsingJavascript(driver.findElement(By.cssSelector("span[class='al-summary-performance-link']")));
					} catch (Exception e) {

					}

					Thread.sleep(1000);
					questionCount++;
					if (questionCount == 1) {
						int noticesize = driver.findElements(By.className("al-notification-message-body")).size();
						if (noticesize == 1)

						{
							String notice = driver.findElement(By.className("al-notification-message-body")).getText();
							if (notice.equals("You will build your proficiency faster if you say how confident you feel about the question before answering.")) {
								driver.findElement(By.cssSelector("div[id='close-al-notification-dialog']")).click();
								((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("al-diag-test-submit-button")));
								try{
									if(driver.findElement(By.cssSelector("span[class='al-summary-performance-link']")).isDisplayed()){
										System.out.println("inside display");
										WebDriverUtil.clickOnElementUsingJavascript(driver.findElement(By.cssSelector("span[class='al-summary-performance-link']")));
									}
								}
								catch (Exception e){

								}
							}

						}
					}

					try {
						driver.findElement(By.cssSelector("span[id='timer-label']"));
					} catch (Exception e) {
						timer = 0;
					}
				}
			}
        }
        catch (Exception e) {
            Assert.fail("Exception while attempting the diagnostic question as correct",e);
        }

	}

	public void attemptAllIncorrect(int confidencelevel,boolean useHints,boolean showSolutionText)
	{
		WebDriver driver=Driver.getWebDriver();
		try
		{
			int timer=driver.findElements(By.cssSelector("span[id='timer-label']")).size();
			while(timer!=0)
			{
				if(useHints == true)					
					driver.findElement(By.cssSelector("img[title=\"Hint\"]")).click();
				String confidence=Integer.toString(confidencelevel);

				String corranswertext=driver.findElement(By.id("show-debug-correct-answer-block")).getText();
				int lastindex=corranswertext.length();
				String corrcttextanswer=corranswertext.substring(16, lastindex);

				if(driver.findElements(By.className("multiple-selection-answer-choice-label-wrapper")).size()>0)
				{
					String corranswer=driver.findElement(By.id("show-debug-correct-answer-block")).getText();					
					char correctanswer;							  
					if(corranswer.charAt(17) == '(')
						correctanswer=corranswer.charAt(18);
					else
						correctanswer=corranswer.charAt(17);

					String  correctchoice=Character.toString(correctanswer); //first correct choice

					char correctanswer1=corranswer.charAt(22);		
					String correctchoice1=Character.toString(correctanswer1); //second correct choice				  

					if((correctchoice.equals("A") && correctchoice1.equals("B")) || (correctchoice.equals("B") && correctchoice1.equals("A")) )
					{	correctchoice="C"; correctchoice1 = "D";} 
					else if(correctchoice.equals("A") && correctchoice1.equals("C") || (correctchoice.equals("C") && correctchoice1.equals("A")))
					{	correctchoice="B"; correctchoice1 = "D"; }
					else if(correctchoice.equals("A") && correctchoice1.equals("D") || (correctchoice.equals("D") && correctchoice1.equals("A")))
					{ correctchoice="B"; correctchoice1 = "C";}
					else if(correctchoice.equals("A") && correctchoice1.equals("E") || (correctchoice.equals("E") && correctchoice1.equals("A")))
					{ correctchoice="B"; correctchoice1 = "C";}
					else if(correctchoice.equals("A") && correctchoice1.equals("F") || (correctchoice.equals("F") && correctchoice1.equals("A")))
					{ correctchoice="B"; correctchoice1 = "C";}


					else if((correctchoice.equals("B") && correctchoice1.equals("C")) || (correctchoice.equals("C") && correctchoice1.equals("B")) )
					{	correctchoice="A"; correctchoice1 = "D";} 
					else if(correctchoice.equals("B") && correctchoice1.equals("D") || (correctchoice.equals("D") && correctchoice1.equals("B")))
					{	correctchoice="A"; correctchoice1 = "C"; }
					else if(correctchoice.equals("B") && correctchoice1.equals("E") || (correctchoice.equals("E") && correctchoice1.equals("B")))
					{ correctchoice="A"; correctchoice1 = "C";}
					else if(correctchoice.equals("B") && correctchoice1.equals("F") || (correctchoice.equals("F") && correctchoice1.equals("B")))
					{ correctchoice="A"; correctchoice1 = "C";}

					else if((correctchoice.equals("C") && correctchoice1.equals("D")) || (correctchoice.equals("D") && correctchoice1.equals("C")) )
					{	correctchoice="A"; correctchoice1 = "B";} 
					else if(correctchoice.equals("C") && correctchoice1.equals("E") || (correctchoice.equals("E") && correctchoice1.equals("C")))
					{	correctchoice="A"; correctchoice1 = "B"; }
					else if(correctchoice.equals("C") && correctchoice1.equals("F") || (correctchoice.equals("F") && correctchoice1.equals("C")))
					{ correctchoice="A"; correctchoice1 = "B";}


					else if((correctchoice.equals("D") && correctchoice1.equals("E")) || (correctchoice.equals("E") && correctchoice1.equals("D")) )
					{	correctchoice="A"; correctchoice1 = "B";} 
					else if(correctchoice.equals("D") && correctchoice1.equals("F") || (correctchoice.equals("F") && correctchoice1.equals("D")))
					{	correctchoice="A"; correctchoice1 = "B"; }

					else if((correctchoice.equals("E") && correctchoice1.equals("F")) || (correctchoice.equals("F") && correctchoice1.equals("E")) )
					{	correctchoice="A"; correctchoice1 = "B";}
					List<WebElement> answer = driver.findElements(By.className("qtn-label"));
					for (WebElement answerchoice: answer)
					{

						if(answerchoice.getText().trim().equals(correctchoice))
						{
							((JavascriptExecutor) driver).executeScript("arguments[0].click();",answerchoice);					
							break;
						}
					}
					List<WebElement> answer1 = driver.findElements(By.className("qtn-label"));
					for (WebElement answerchoice1: answer1)
					{

						if(answerchoice1.getText().trim().equals(correctchoice1))
						{
							((JavascriptExecutor) driver).executeScript("arguments[0].click();",answerchoice1);					
							break;
						}
					}

				}

				else if(driver.findElements(By.className("answer-choice-label-wrapper")).size() > 0)
				{
					String corranswer=driver.findElement(By.id("show-debug-correct-answer-block")).getText();					
					char correctanswer=corranswer.charAt(17);
					String correctchoice=Character.toString(correctanswer);
					if(correctchoice.equals("A"))
						correctchoice="B";
					else if(correctchoice.equals("B"))
						correctchoice="A";
					else if(correctchoice.equals("C"))
						correctchoice="D";
					else if(correctchoice.equals("D"))
						correctchoice="C";
					List<WebElement> answer = driver.findElements(By.className("qtn-label"));
					for (WebElement answerchoice: answer)
					{

						if(answerchoice.getText().trim().equals(correctchoice))
						{
							((JavascriptExecutor) driver).executeScript("arguments[0].click();",answerchoice);					
							break;
						}
					}

				}

				else if(driver.findElements(By.cssSelector("span[class='input-tag-wrapper question-element-wrapper']")).size() > 0)
				{

					driver.findElement(By.cssSelector("input[id='1']")).sendKeys(corrcttextanswer+"bh");
				}
				else if(driver.findElements(By.className("sbHolder")).size() > 0)
				{
					driver.findElement(By.className("sbToggle")).click();
					Thread.sleep(2000);

					String values = driver.findElement(By.cssSelector("ul[class='sbOptions']")).getText();
					//values = values.replaceAll("\n", " ");
					String [] val = values.split("\n"); 


					for(String element:val)
					{
						if(!element.equals(corrcttextanswer))
						{
							driver.findElement(By.linkText(element)).click();
							break;
						}
					}
				}			
				Thread.sleep(2000);
				if(confidencelevel!=0)
				{
					driver.findElement(By.cssSelector("a[id='"+confidence+"']")).click();//click on confidence level
					Thread.sleep(2000);
				}
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("img[title=\"Submit\"]")));
				Thread.sleep(2000);
				int noticesize = driver.findElements(By.className("al-notification-message-body")).size();
				if(noticesize==1)

				{
					String notice =  driver.findElement(By.className("al-notification-message-body")).getText();
					if(notice.equals("You will build your proficiency faster if you say how confident you feel about the question before answering."))
					{
						driver.findElement(By.cssSelector("div[id='close-al-notification-dialog']")).click();
						((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("img[title=\"Submit\"]")));
						Thread.sleep(2000);

					}

				}
				//((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("img[title=\"Submit\"]")));
				//Thread.sleep(2000);
				timer=driver.findElements(By.cssSelector("span[id='timer-label']")).size();
			}

		}
		catch(Exception e)
		{

			Assert.fail("Exception in App helper method  attemptAllIncorrect in class Diagnostic Test",e);
		}
	}

	//select correct or incorrect option upto which Question number u want
	public void DiagonesticTestQuitBetween(int confidencelevel,int numberofquestionattempt,String answeroption,boolean useHints,boolean useSolutionText,boolean quitorcontinue)
	{
		WebDriver driver=Driver.getWebDriver();
		try
		{
			if(answeroption.equals("correct"))
			{				
				for(int i=1;i<=numberofquestionattempt;i++)
				{
					String confidence=Integer.toString(confidencelevel);
					if(useHints == true)					
						driver.findElement(By.cssSelector("img[title=\"Hint\"]")).click();
                        attemptCorrect(confidencelevel);
//					if(driver.findElements(By.className("multiple-selection-answer-choice-label-wrapper")).size()>0)
//					{
//						String corranswer=driver.findElement(By.id("show-debug-correct-answer-block")).getText();
//						char correctanswer;
//						if(corranswer.charAt(17) == '(')
//							correctanswer=corranswer.charAt(18);
//						else
//							correctanswer=corranswer.charAt(17);
//						String correctchoice=Character.toString(correctanswer);
//
//						String corranswer1=driver.findElement(By.id("show-debug-correct-answer-block")).getText();
//						char correctanswer1=corranswer1.charAt(22);
//
//						String correctchoice1=Character.toString(correctanswer1);
//
//						List<WebElement> answer = driver.findElements(By.className("qtn-label"));
//						for (WebElement answerchoice: answer)
//						{
//
//							if(answerchoice.getText().trim().equals(correctchoice))
//							{
//								((JavascriptExecutor) driver).executeScript("arguments[0].click();",answerchoice);
//								break;
//							}
//						}
//						List<WebElement> answer1 = driver.findElements(By.className("qtn-label"));
//						for (WebElement answerchoice1: answer1)
//						{
//
//							if(answerchoice1.getText().trim().equals(correctchoice1))
//							{
//								((JavascriptExecutor) driver).executeScript("arguments[0].click();",answerchoice1);
//								break;
//							}
//						}
//
//					}
//					else if(driver.findElements(By.className("answer-choice-label-wrapper")).size() > 0)
//					{
//						String corranswer=driver.findElement(By.id("show-debug-correct-answer-block")).getText();
//						char correctanswer=corranswer.charAt(17);
//						String correctchoice=Character.toString(correctanswer);
//						List<WebElement> answer = driver.findElements(By.className("qtn-label"));
//						for (WebElement answerchoice: answer)
//						{
//							if(answerchoice.getText().trim().equals(correctchoice))
//							{
//								((JavascriptExecutor) driver).executeScript("arguments[0].click();",answerchoice);
//								break;
//							}
//						}
//					}
//					else if(driver.findElements(By.cssSelector("span[class='input-tag-wrapper question-element-wrapper']")).size() > 0)
//					{
//						String corranswertext=driver.findElement(By.id("show-debug-correct-answer-block")).getText();
//						int lastindex=corranswertext.length();
//						String corrcttextanswer=corranswertext.substring(16, lastindex);
//						driver.findElement(By.cssSelector("input[id='1']")).sendKeys(corrcttextanswer);
//					}
//					else if(driver.findElements(By.className("sbHolder")).size() > 0)
//					{
//						String corranswertext=driver.findElement(By.id("show-debug-correct-answer-block")).getText();
//						int lastindex=corranswertext.length();
//						String corrcttextanswer=corranswertext.substring(16, lastindex);
//						driver.findElement(By.className("sbToggle")).click();
//						Thread.sleep(2000);
//						driver.findElement(By.linkText(corrcttextanswer)).click();
//					}
//					Thread.sleep(2000);
//					if(confidencelevel!=0)
//					{
//						driver.findElement(By.cssSelector("a[id='"+confidence+"']")).click();//click on confidence level
//						Thread.sleep(2000);
//					}
//					((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("al-diag-test-submit-button")));
//					Thread.sleep(2000);
//					int noticesize = driver.findElements(By.className("al-notification-message-body")).size();
//					if(noticesize==1)
//
//					{
//						String notice =  driver.findElement(By.className("al-notification-message-body")).getText();
//						if(notice.equals("You will build your proficiency faster if you say how confident you feel about the question before answering."))
//						{
//							driver.findElement(By.cssSelector("div[id='close-al-notification-dialog']")).click();
//							((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("al-diag-test-submit-button")));
//						}
//
//					}

				} //for loop for correct answer submit ends

			} //if condition for correct answer submit ends

			else //Attempting all questions as incorrect
			{
				for(int j=1;j<=numberofquestionattempt;j++)
				{
					if(useHints == true)					
						driver.findElement(By.cssSelector("img[title=\"Hint\"]")).click();
					String confidence=Integer.toString(confidencelevel);

					String corranswertext=driver.findElement(By.id("show-debug-correct-answer-block")).getText();
					int lastindex=corranswertext.length();
					String corrcttextanswer=corranswertext.substring(16, lastindex);

					if(driver.findElements(By.className("multiple-selection-answer-choice-label-wrapper")).size()>0)
					{
						String corranswer=driver.findElement(By.id("show-debug-correct-answer-block")).getText();					
						char correctanswer;							  
						if(corranswer.charAt(17) == '(')
							correctanswer=corranswer.charAt(18);
						else
							correctanswer=corranswer.charAt(17);

						String  correctchoice=Character.toString(correctanswer); //first correct choice

						char correctanswer1=corranswer.charAt(22);		
						String correctchoice1=Character.toString(correctanswer1); //second correct choice					  

						if((correctchoice.equals("A") && correctchoice1.equals("B")) || (correctchoice.equals("B") && correctchoice1.equals("A")) )
						{	correctchoice="C"; correctchoice1 = "D";} 
						else if(correctchoice.equals("A") && correctchoice1.equals("C") || (correctchoice.equals("C") && correctchoice1.equals("A")))
						{	correctchoice="B"; correctchoice1 = "D"; }
						else if(correctchoice.equals("A") && correctchoice1.equals("D") || (correctchoice.equals("D") && correctchoice1.equals("A")))
						{ correctchoice="B"; correctchoice1 = "C";}
						else if(correctchoice.equals("A") && correctchoice1.equals("E") || (correctchoice.equals("E") && correctchoice1.equals("A")))
						{ correctchoice="B"; correctchoice1 = "C";}
						else if(correctchoice.equals("A") && correctchoice1.equals("F") || (correctchoice.equals("F") && correctchoice1.equals("A")))
						{ correctchoice="B"; correctchoice1 = "C";}


						else if((correctchoice.equals("B") && correctchoice1.equals("C")) || (correctchoice.equals("C") && correctchoice1.equals("B")) )
						{	correctchoice="A"; correctchoice1 = "D";} 
						else if(correctchoice.equals("B") && correctchoice1.equals("D") || (correctchoice.equals("D") && correctchoice1.equals("B")))
						{	correctchoice="A"; correctchoice1 = "C"; }
						else if(correctchoice.equals("B") && correctchoice1.equals("E") || (correctchoice.equals("E") && correctchoice1.equals("B")))
						{ correctchoice="A"; correctchoice1 = "C";}
						else if(correctchoice.equals("B") && correctchoice1.equals("F") || (correctchoice.equals("F") && correctchoice1.equals("B")))
						{ correctchoice="A"; correctchoice1 = "C";}

						else if((correctchoice.equals("C") && correctchoice1.equals("D")) || (correctchoice.equals("D") && correctchoice1.equals("C")) )
						{	correctchoice="A"; correctchoice1 = "B";} 
						else if(correctchoice.equals("C") && correctchoice1.equals("E") || (correctchoice.equals("E") && correctchoice1.equals("C")))
						{	correctchoice="A"; correctchoice1 = "B"; }
						else if(correctchoice.equals("C") && correctchoice1.equals("F") || (correctchoice.equals("F") && correctchoice1.equals("C")))
						{ correctchoice="A"; correctchoice1 = "B";}


						else if((correctchoice.equals("D") && correctchoice1.equals("E")) || (correctchoice.equals("E") && correctchoice1.equals("D")) )
						{	correctchoice="A"; correctchoice1 = "B";} 
						else if(correctchoice.equals("D") && correctchoice1.equals("F") || (correctchoice.equals("F") && correctchoice1.equals("D")))
						{	correctchoice="A"; correctchoice1 = "B"; }

						else if((correctchoice.equals("E") && correctchoice1.equals("F")) || (correctchoice.equals("F") && correctchoice1.equals("E")) )
						{	correctchoice="A"; correctchoice1 = "B";}
						List<WebElement> answer = driver.findElements(By.className("qtn-label"));
						for (WebElement answerchoice: answer)
						{

							if(answerchoice.getText().trim().equals(correctchoice))
							{
								((JavascriptExecutor) driver).executeScript("arguments[0].click();",answerchoice);					
								break;
							}
						}
						List<WebElement> answer1 = driver.findElements(By.className("qtn-label"));
						for (WebElement answerchoice1: answer1)
						{

							if(answerchoice1.getText().trim().equals(correctchoice1))
							{
								((JavascriptExecutor) driver).executeScript("arguments[0].click();",answerchoice1);					
								break;
							}
						}

					}

					else if(driver.findElements(By.className("answer-choice-label-wrapper")).size() > 0)
					{
						String corranswer=driver.findElement(By.id("show-debug-correct-answer-block")).getText();					
						char correctanswer=corranswer.charAt(17);
						String correctchoice=Character.toString(correctanswer);
						if(correctchoice.equals("A"))
							correctchoice="B";
						else if(correctchoice.equals("B"))
							correctchoice="A";
						else if(correctchoice.equals("C"))
							correctchoice="D";
						else if(correctchoice.equals("D"))
							correctchoice="C";
						List<WebElement> answer = driver.findElements(By.className("qtn-label"));
						for (WebElement answerchoice: answer)
						{

							if(answerchoice.getText().trim().equals(correctchoice))
							{
								((JavascriptExecutor) driver).executeScript("arguments[0].click();",answerchoice);					
								break;
							}
						}

					}

					else if(driver.findElements(By.cssSelector("span[class='input-tag-wrapper question-element-wrapper']")).size() > 0)
					{

						driver.findElement(By.cssSelector("input[id='1']")).sendKeys(corrcttextanswer+"bh");
					}
					else if(driver.findElements(By.className("sbHolder")).size() > 0)
					{
						driver.findElement(By.className("sbToggle")).click();
						Thread.sleep(2000);

						String values = driver.findElement(By.cssSelector("ul[class='sbOptions']")).getText();
						//values = values.replaceAll("\n", " ");
						String [] val = values.split("\n"); 


						for(String element:val)
						{
							if(!element.equals(corrcttextanswer))
							{
								driver.findElement(By.linkText(element)).click();
								break;
							}
						}
					}			
					Thread.sleep(2000);

					if(confidencelevel<5)
					{
						driver.findElement(By.cssSelector("a[id='"+confidence+"']")).click();//click on confidence level
						Thread.sleep(2000);
					}
					try {
						(new WebDriverWait(driver, 2))
								.until(ExpectedConditions.presenceOfElementLocated(By.className("al-diag-test-submit-button"))).click();
						(new WebDriverWait(driver, 2))
								.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("span[class='al-summary-performance-link']"))).click();
					} catch (Exception e) {

					}

					Thread.sleep(2000);
					int noticesize = driver.findElements(By.className("al-notification-message-body")).size();
					if(noticesize==1)

					{
						String notice =  driver.findElement(By.className("al-notification-message-body")).getText();
						if(notice.equals("You will build your proficiency faster if you say how confident you feel about the question before answering."))
						{
							driver.findElement(By.cssSelector("div[id='close-al-notification-dialog']")).click();
							((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("al-diag-test-submit-button")));
							try{
								if(driver.findElement(By.cssSelector("span[class='al-summary-performance-link']")).isDisplayed()){
									System.out.println("inside display");
									driver.findElement(By.cssSelector("span[class='al-summary-performance-link']")).click();
								}
							}
							catch (Exception e){

							}
						}

					}

				} //for loop for attempting all questions incorrectly ends
			} //else condition for attempting all questions incorrectly ends

			driver.findElement(By.className("al-quit-diag-test-icon")).click();
			Thread.sleep(1000);
			if(quitorcontinue==true)
			{
				driver.findElement(By.className("al-quit-diag-test")).click();
				(new WebDriverWait(driver, 2))
						.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("span[class='al-summary-performance-link']"))).click();
				Thread.sleep(2000);
			}
			else
			{
				driver.findElement(By.className("al-diag-test-continue-later")).click();
				Thread.sleep(2000);
			}


		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromAppHelper();
			Assert.fail("Exception in App helper method  DiagonesticTestQuitBetween",e);
		}
	}

	public void attemptFromParticularTLO(int noOfQuestions, int tloIndex,String answerChoice,int confidence)
	{
		WebDriver driver=Driver.getWebDriver();
		try
		{
			List<String> tlonames = new PracticeTest().tloNames();
			int tlocnt = 0;
			while(tlocnt != noOfQuestions)
			{
				String tlonamefound = driver.findElement(By.className("al-diagtest-skills-evaluted-ul")).getText().substring(2); //TLO from Question Page
				if(tlonamefound.equals(tlonames.get(tloIndex)))
					tlocnt++;
				if(answerChoice.equals("correct"))
					attemptCorrect(confidence);
				else if(answerChoice.equals("incorrect"))
					attemptIncorrect(confidence);
				else
					attemptSkip(confidence);
				System.out.println(tlocnt);
			}
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromAppHelper();
			Assert.fail("Exception in App helper method  attemptFromParticularTLO",e);
		}
	}

	public void attemptDiagnosticTestRandomly()
	{
		WebDriver driver=Driver.getWebDriver();
		try
		{
			int diagQuit = driver.findElements(By.className("al-quit-diag-tag")).size();
			while (diagQuit > 0)
			{
				List<WebElement> multipleChoice = driver.findElements(By.className("qtn-label"));
				if(multipleChoice.size() > 2)
				{
					int randNumber = new RandomNumber().generateRandomNumber(0,3);
					multipleChoice.get(randNumber).click();
				}
				else if(multipleChoice.size() > 0 && multipleChoice.size() <= 2)
				{
					int randNumber = new RandomNumber().generateRandomNumber(0,1);
					multipleChoice.get(randNumber).click();
				}
				new Click().clickByclassname("al-diag-test-submit-button");
				diagQuit = driver.findElements(By.className("al-quit-diag-tag")).size();
			}
		}
		catch (Exception e)
		{
			Assert.fail("Exception in App helper method  attemptDiagnosticTestRandomly",e);
		}
	}



    public void attemptDiagnosticTestRandomly(int noOfQuestions)
    {
		WebDriver driver=Driver.getWebDriver();
        try
        {
            for(int a=0;a<noOfQuestions;a++){
                List<WebElement> multipleChoice = driver.findElements(By.className("qtn-label"));
                if (multipleChoice.size() > 2) {
                    int randNumber = new RandomNumber().generateRandomNumber(0, 3);
                    multipleChoice.get(randNumber).click();
                } else if (multipleChoice.size() > 0 && multipleChoice.size() <= 2) {
                    int randNumber = new RandomNumber().generateRandomNumber(0, 1);
                    multipleChoice.get(randNumber).click();
                }
                new Click().clickByclassname("al-diag-test-submit-button");


            }
            ReportUtil.log("Attempting Diagnostic Test Randomly", "Diagnostic test is attempted randomly", "info");
        }
        catch (Exception e)
        {
            Assert.fail("Exception in App helper method  attemptDiagnosticTestRandomly",e);
        }
    }

	public void attemptCorrect(int noOfQuestions, String testType,int userNo)
	{
		WebDriver driver=Driver.getWebDriver();
		try {
			int timer=1;
			int questionCount = 0;
			while(timer!=0) {
				boolean attempted = false;
				//True False and Single Select Question Type
				try {
					driver.findElement(By.className("qtn-label"));
					String correctAnswer=driver.findElement(By.id("show-debug-correct-answer-block")).getText();
					char correctAnswerOption=correctAnswer.charAt(17);
					String correctChoice=Character.toString(correctAnswerOption);
					String answerIndex="0";
					if(correctChoice.equals("A")) {
						answerIndex = "1";
					}
					else if (correctChoice.equals("B")) {
						answerIndex = "2";
					}
					else if (correctChoice.equals("C")) {
						answerIndex = "3";
					}
					else if (correctChoice.equals("D")) {
						answerIndex = "4";
					}
					driver.findElement(By.xpath("//div[@class='al-diag-test-answerChoices-content-wrapper']/div["+answerIndex+"]/div[1]/label")).click();
					attempted = true;

				}
				catch (Exception e) {

				}
				//Multiple Selection Question Type
				if(attempted ==false) {
					try {
						driver.findElement(By.className("multiple-selection-answer-choice-label-wrapper"));
						String correctAnswer1 = driver.findElement(By.id("show-debug-correct-answer-block")).getText();
						char correctOption1;
						if (correctAnswer1.charAt(17) == '(') {
							correctOption1 = correctAnswer1.charAt(18);
						} else {
							correctOption1 = correctAnswer1.charAt(17);
						}

						String correctChoice1 = Character.toString(correctOption1); //Answer 1

						String correctAnswer2 = driver.findElement(By.id("show-debug-correct-answer-block")).getText();
						char correctOption2 = correctAnswer2.charAt(22);

						String correctChoice2 = Character.toString(correctOption2); //Answer 2

						String answerIndex = "0";
						if (correctChoice1.equals("A")) {
							answerIndex = "1";
						} else if (correctChoice1.equals("B")) {
							answerIndex = "2";
						} else if (correctChoice1.equals("C")) {
							answerIndex = "3";
						} else if (correctChoice1.equals("D")) {
							answerIndex = "4";
						} else if (correctChoice1.equals("E")) {
							answerIndex = "5";
						} else if (correctChoice1.equals("F")) {
							answerIndex = "6";
						}
						driver.findElement(By.xpath("//div[@class='al-diag-test-answerChoices-content-wrapper']/div[" + answerIndex + "]/div[1]/label")).click();

						answerIndex = "0";

						if (correctChoice2.equals("A")) {
							answerIndex = "1";
						} else if (correctChoice2.equals("B")) {
							answerIndex = "2";
						} else if (correctChoice2.equals("C")) {
							answerIndex = "3";
						} else if (correctChoice2.equals("D")) {
							answerIndex = "4";
						} else if (correctChoice2.equals("E")) {
							answerIndex = "5";
						} else if (correctChoice2.equals("F")) {
							answerIndex = "6";
						}
						driver.findElement(By.xpath("//div[@class='al-diag-test-answerChoices-content-wrapper']/div[" + answerIndex + "]/div[1]/label")).click();
						attempted = true;

					} catch (Exception e) {

					}
				}
				//Drop Down Question Type
				if(attempted == false) {
					try {
						driver.findElement(By.className("sbHolder"));
						String correctAnswerText=driver.findElement(By.id("show-debug-correct-answer-block")).getText();
						String correctAnswer=correctAnswerText.substring(16, correctAnswerText.length());
						driver.findElement(By.className("sbToggle")).click();
						Thread.sleep(1000);
						driver.findElement(By.linkText(correctAnswer)).click();
						attempted = true;
					} catch (Exception e) {

					}
				}
				//Text Entry Question Type
				if(attempted == false) {
					try {
						driver.findElement(By.cssSelector("span[class='input-tag-wrapper question-element-wrapper']"));
						String correctAnswerText=driver.findElement(By.id("show-debug-correct-answer-block")).getText();
						String correctAnswer=correctAnswerText.substring(16, correctAnswerText.length());
						driver.findElement(By.cssSelector("input[id='1']")).sendKeys(correctAnswer);
						attempted = true;
					}
					catch (Exception e) {

					}
				}
				try {
					//  driver.findElement(By.cssSelector("a[id='" + new RandomNumber().generateRandomNumber(1, 4) + "']")).click();
					if (userNo % 2 == 0) {
						driver.findElement(By.cssSelector("a[id='3']")).click();
					} else {
						driver.findElement(By.cssSelector("a[id='4']")).click();
					}
					//  driver.findElement(By.cssSelector("a[id='" + new RandomNumber().generateRandomNumber(1, 4) + "']")).click();
                   /* if(userNo <= 60 || userNo > 70) {
                        if (userNo % 2 == 0) {
                            driver.findElement(By.cssSelector("a[id='3']")).click();
                        } else {
                            driver.findElement(By.cssSelector("a[id='4']")).click();
                        }
                    }
                    else {
                        driver.findElement(By.cssSelector("a[id='" + new RandomNumber().generateRandomNumber(1, 4) + "']")).click();
                    }
*/
				}
				catch (Exception e) {

				}
				if(testType.equals("Diagnostic")) {
					try {
						(new WebDriverWait(driver, 2))
								.until(ExpectedConditions.presenceOfElementLocated(By.className("al-diag-test-submit-button"))).click();
					}
					catch (Exception e) {

					}
				}
				else {
					((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("img[title=\"Submit\"]")));
					try {
						driver.findElement(By.className("al-notification-message-body"));

						String notice = driver.findElement(By.className("al-notification-message-body")).getText();
						if (notice.equals("You will build your proficiency faster if you say how confident you feel about the question before answering.")) {
							driver.findElement(By.cssSelector("div[id='close-al-notification-dialog']")).click();
							driver.findElement(By.className("al-practice-test-submit-button")).click();
							Thread.sleep(1000);
						}


					}
					catch (Exception e) {

					}
					Thread.sleep(500);
					try {
						((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("img[title=\"Next\"]")));
					}
					catch (Exception e) {

					}

				}
				Thread.sleep(1000);
				questionCount++;
				if(noOfQuestions == 0) {
					try {
						driver.findElement(By.cssSelector("span[id='timer-label']"));
					} catch (Exception e) {
						timer = 0;
					}
				}
				else {
					if(noOfQuestions == questionCount) {
						timer = 0;
					}
				}



			}
		}
		catch (Exception e) {
			Assert.fail("Exception while attempting the diagnostic question as correct",e);
		}
	}

}
