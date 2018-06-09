package com.snapwiz.selenium.tests.staf.dummies.apphelper;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.snapwiz.selenium.tests.staf.dummies.Driver;
import com.snapwiz.selenium.tests.staf.dummies.ReadTestData;
import com.snapwiz.selenium.tests.staf.dummies.uihelper.Click;

public class PracticeTest {
	
	public void startTest()
	{
		try
		{
			new Navigator().NavigateTo("Start Practice");
			new Click().clickByclassname("start-adaptive-practice");
		}
		catch(Exception e)
		{
			
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
	

	
	public void AttemptCorrectAnswer(int confidencelevel)
	{
		try
		{
		String corranswer=Driver.driver.findElement(By.id("show-debug-correct-answer-block")).getText();
		char correctanswer=corranswer.charAt(17);
		String correctchoice=Character.toString(correctanswer);
		String corranswertext=Driver.driver.findElement(By.id("show-debug-correct-answer-block")).getText();
		int lastindex=corranswertext.length();
		String correcttextanswer=corranswertext.substring(16, lastindex);
		
		 if(Driver.driver.findElements(By.className("multiple-selection-answer-choice-label-wrapper")).size()>0)
			{
				String corranswer1=Driver.driver.findElement(By.id("show-debug-correct-answer-block")).getText();
				char correctanswer1=corranswer1.charAt(22);
				
				String correctchoice1=Character.toString(correctanswer1);
				List<WebElement> answer = Driver.driver.findElements(By.className("qtn-label"));
				for (WebElement answerchoice: answer)
				{
					
					if(answerchoice.getText().trim().equals(correctchoice))
					{
						((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();",answerchoice);					
						break;
					}
				}
				List<WebElement> answer1 = Driver.driver.findElements(By.className("qtn-label"));
				for (WebElement answerchoice1: answer1)
				{
					
					if(answerchoice1.getText().trim().equals(correctchoice1))
					{
						((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();",answerchoice1);					
						break;
					}
				}
				
			}
		
		else if(Driver.driver.findElements(By.className("answer-choice-label-wrapper")).size() > 0) //single select and true/false question type
		{	
		List<WebElement> answer = Driver.driver.findElements(By.className("qtn-label"));
		for (WebElement answerchoice: answer)
			{			
				if(answerchoice.getText().trim().equals(correctchoice))
				{
				((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();",answerchoice);					
				break;
				}
			}		
		}
		
		else if(Driver.driver.findElements(By.cssSelector("span[class='input-tag-wrapper question-element-wrapper']")).size() > 0)
		{			
		Driver.driver.findElement(By.cssSelector("input[id='1']")).sendKeys(correcttextanswer);
		}
		else if(Driver.driver.findElements(By.className("sbHolder")).size() > 0)
		{
			try
			{
			Driver.driver.findElement(By.className("sbToggle")).click();
			//Thread.sleep(2000);
			Driver.driver.findElement(By.linkText(correcttextanswer)).click();
			//Thread.sleep(5000);
			}
			catch(Exception e)
			{
				Assert.fail("Exception in App helper attemptQuestion in class PracticeTest",e);
			}
		}
		 if(confidencelevel !=0)
		Driver.driver.findElement(By.cssSelector("a[id='"+confidencelevel+"']")).click();//click on confidence level
		
		((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.id("submit-practice-question-button")));
		
		/*int noticesize = Driver.driver.findElements(By.className("al-notification-message-body")).size();
		if(noticesize>=1)
		
		{
			String notice =  Driver.driver.findElement(By.className("al-notification-message-body")).getText();			
			//if(notice.equals("You will build your proficiency faster if you say how confident you feel about the question before answering."))
			//{
				Thread.sleep(3000);
				Driver.driver.findElement(By.cssSelector("div[id='close-al-notification-dialog']")).click();
				((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.id("submit-practice-question-button")));
				Thread.sleep(2000);				
			//}
			
		}*/
		Thread.sleep(2000);	
		((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.id("next-practice-question-button")));
		Thread.sleep(2000);   
	
	} 
	catch(Exception e)
	{
		//new com.snapwiz.selenium.tests.staf.dummies.apphelper.Screenshot().captureScreenshotFromAppHelper();
		Assert.fail("Exception in App helper AttemptCorrectAnswer in class PracticeTest",e);
	}
}
	
	public void attemptStaticPracticeTest(int confidencelevel)
	{
		try
		{
			int timer=Driver.driver.findElements(By.cssSelector("span[id='timer-label']")).size();
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
			timer=Driver.driver.findElements(By.cssSelector("span[id='timer-label']")).size();
		}
		}
		catch(Exception e)
		{
			new com.snapwiz.selenium.tests.staf.dummies.apphelper.Screenshot().captureScreenshotFromAppHelper();
			Assert.fail("Exception in App helper AttemptCorrectAnswer in class PracticeTest",e);
		}
		
	}
	
	public void quitTestAndGoToDashboard()
	{
		try
		{
			Driver.driver.findElement(By.className("al-quit-diag-test-icon")).click();
			Thread.sleep(1000);
			Driver.driver.findElement(By.className("ls-practice-test-view-report")).click();
			Thread.sleep(2000);
		}
		catch(Exception e)
		{
			//new Screenshot().captureScreenshotFromAppHelper();
			Assert.fail("Exception in quitting the practice test",e);
		}
	}
	
	public void continueLaterPracticeTestAndGoToDashboard()
	{
		try
		{
			Driver.driver.findElement(By.className("al-quit-diag-test-icon")).click();
			Thread.sleep(1000);
			Driver.driver.findElement(By.linkText("Continue Later")).click();
			Thread.sleep(2000);
		}
		catch(Exception e)
		{
			//new Screenshot().captureScreenshotFromAppHelper();
			Assert.fail("Exception in app helper method continueLaterPracticeTestAndGoToDashboard",e);
		}
	}
	  public void attemptQuestion(String answeroption,int confidencelevel, boolean useHints,boolean useSolutionText)
	   {
	    try
	    {
	    	if(answeroption.equalsIgnoreCase("skip"))
			{				
					
				if(useHints == true)					
					Driver.driver.findElement(By.cssSelector("img[title=\"Hint\"]")).click();
					
				((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.id("submit-practice-question-button")));
				Thread.sleep(2000);	
				((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.id("next-practice-question-button")));
				Thread.sleep(2000); 
				
			} //logic for skipping questions ends
			
			else if(answeroption.equalsIgnoreCase("correct"))
			{					
			String confidence=Integer.toString(confidencelevel);
			
			if(useHints == true)					
			Driver.driver.findElement(By.cssSelector("img[title=\"Hint\"]")).click();
			
			if(Driver.driver.findElements(By.className("multiple-selection-answer-choice-label-wrapper")).size()>0)
			{
				String corranswer=Driver.driver.findElement(By.id("show-debug-correct-answer-block")).getText();			
				char correctanswer;							  
				  if(corranswer.charAt(17) == '(')
					  correctanswer=corranswer.charAt(18);
				  else
					  correctanswer=corranswer.charAt(17);
				String correctchoice=Character.toString(correctanswer);
				
				String corranswer1=corranswer;
				char correctanswer1=corranswer1.charAt(22);
				String correctchoice1=Character.toString(correctanswer1);
				List<WebElement> answer = Driver.driver.findElements(By.className("qtn-label"));
				for (WebElement answerchoice: answer)
				{
					
					if(answerchoice.getText().trim().equals(correctchoice))
					{
						((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();",answerchoice);					
						break;
					}
				}
				List<WebElement> answer1 = Driver.driver.findElements(By.className("qtn-label"));
				for (WebElement answerchoice1: answer1)
				{
					
					if(answerchoice1.getText().trim().equals(correctchoice1))
					{
						((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();",answerchoice1);					
						break;
					}
				}
				
			}
			
			else if(Driver.driver.findElements(By.className("answer-choice-label-wrapper")).size() > 0)
			{
				String corranswer=Driver.driver.findElement(By.id("show-debug-correct-answer-block")).getText();			
				char correctanswer=corranswer.charAt(17);
				String correctchoice=Character.toString(correctanswer);	
			List<WebElement> answer = Driver.driver.findElements(By.className("qtn-label"));
			for (WebElement answerchoice: answer)
			{
				
				if(answerchoice.getText().trim().equals(correctchoice))
				{
					((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();",answerchoice);					
					break;
				}
			}				
				}
			
			
			else if(Driver.driver.findElements(By.cssSelector("span[class='input-tag-wrapper question-element-wrapper']")).size() > 0)
			{
				String corranswertext=Driver.driver.findElement(By.id("show-debug-correct-answer-block")).getText();
				int lastindex=corranswertext.length();
				String corrcttextanswer=corranswertext.substring(16, lastindex);
			Driver.driver.findElement(By.cssSelector("input[id='1']")).sendKeys(corrcttextanswer);
			}
			else if(Driver.driver.findElements(By.className("sbHolder")).size() > 0)
			{
				String corranswertext=Driver.driver.findElement(By.id("show-debug-correct-answer-block")).getText();
				int lastindex=corranswertext.length();
				String corrcttextanswer=corranswertext.substring(16, lastindex);
				Driver.driver.findElement(By.className("sbToggle")).click();
				Thread.sleep(2000);
				Driver.driver.findElement(By.linkText(corrcttextanswer)).click();
				Thread.sleep(5000);
			}
		
			Thread.sleep(2000);
			if(confidencelevel!=0)
			{
				Driver.driver.findElement(By.cssSelector("a[id='"+confidence+"']")).click();//click on confidence level
				Thread.sleep(2000);
			}
			((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.id("submit-practice-question-button")));
			Thread.sleep(2000);	
			((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.id("next-practice-question-button")));
			Thread.sleep(2000); 
		
			
			
		} //attepting correct answer ends
			else
			{
				int recommendation = Driver.driver.findElements(By.className("al-notification-message-wrapper")).size();
				if(recommendation > 0)
				{
					String notice =  Driver.driver.findElement(By.className("al-notification-message-body")).getText();
					if(notice.contains("Looks like you might want to study this objective in WileyPLUS. Go to WileyPLUS for"))
					{	
						Thread.sleep(3000);
						((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();",Driver.driver.findElement(By.cssSelector("span[id='exit-practice-test-block']")));
						//Driver.driver.findElement(By.cssSelector("span[id='exit-practice-test-block']")).click();
						
					}							
				}
					if(useHints == true)
					{
						
						Driver.driver.findElement(By.cssSelector("img[title=\"Hint\"]")).click();
					Thread.sleep(2000);
					int hintpopup=Driver.driver.findElements(By.className("al-notification-message-header")).size();
					if(hintpopup>0)
					{
						Driver.driver.findElement(By.xpath("/html/body")).click();
						Thread.sleep(2000);
					}
					}
					String confidence=Integer.toString(confidencelevel);
									
					String corranswertext=Driver.driver.findElement(By.id("show-debug-correct-answer-block")).getText();
					int lastindex=corranswertext.length();
					String corrcttextanswer=corranswertext.substring(16, lastindex);
					
					if(Driver.driver.findElements(By.className("multiple-selection-answer-choice-label-wrapper")).size()>0)
					{
						String corranswer=Driver.driver.findElement(By.id("show-debug-correct-answer-block")).getText();					
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
						List<WebElement> answer = Driver.driver.findElements(By.className("qtn-label"));
						for (WebElement answerchoice: answer)
						{
							
							if(answerchoice.getText().trim().equals(correctchoice))
							{
								((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();",answerchoice);					
								break;
							}
						}
						List<WebElement> answer1 = Driver.driver.findElements(By.className("qtn-label"));
						for (WebElement answerchoice1: answer1)
						{
							
							if(answerchoice1.getText().trim().equals(correctchoice1))
							{
								((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();",answerchoice1);					
								break;
							}
						}
						
					}
					
					else if(Driver.driver.findElements(By.className("answer-choice-label-wrapper")).size() > 0)
						{
						String corranswer=Driver.driver.findElement(By.id("show-debug-correct-answer-block")).getText();					
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
						List<WebElement> answer = Driver.driver.findElements(By.className("qtn-label"));
						for (WebElement answerchoice: answer)
						{
							
							if(answerchoice.getText().trim().equals(correctchoice))
							{
								((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();",answerchoice);					
								break;
							}
						}
						
						}
					
					else if(Driver.driver.findElements(By.cssSelector("span[class='input-tag-wrapper question-element-wrapper']")).size() > 0)
					{
						
					Driver.driver.findElement(By.cssSelector("input[id='1']")).sendKeys(corrcttextanswer+"bh");
					}
					else if(Driver.driver.findElements(By.className("sbHolder")).size() > 0)
					{
						Driver.driver.findElement(By.className("sbToggle")).click();
						Thread.sleep(2000);
						
						String values = Driver.driver.findElement(By.cssSelector("ul[class='sbOptions']")).getText();
						//values = values.replaceAll("\n", " ");
						String [] val = values.split("\n"); 
						
						
						for(String element:val)
						{
							if(!element.equals(corrcttextanswer))
							{
								Driver.driver.findElement(By.linkText(element)).click();
								break;
							}
						}
					}
				//	((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.xpath("/html/body/div[3]/div/div[2]/div[2]/div[2]/div/div/div[2]/div[2]/div/div/label")));
				//	Thread.sleep(2000);
					Thread.sleep(2000);				
					
					if(confidencelevel!=0)
					{						
						Driver.driver.findElement(By.cssSelector("a[id='"+confidence+"']")).click();//click on confidence level
						Thread.sleep(2000);
					}
					
					((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.id("submit-practice-question-button")));
					Thread.sleep(2000);	
					((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.id("next-practice-question-button")));
					Thread.sleep(2000); 
			}
	    }
	    catch(Exception e)
	    {
	    	//new Screenshot().captureScreenshotFromAppHelper();
	    	Assert.fail("Exception in App helper attemptQuestion in class PracticeTest",e);
	    }
	   }
	
	
	}




