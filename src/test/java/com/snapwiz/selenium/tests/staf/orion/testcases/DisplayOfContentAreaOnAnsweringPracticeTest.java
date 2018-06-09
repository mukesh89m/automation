package com.snapwiz.selenium.tests.staf.orion.testcases;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.HasInputDevices;
import org.openqa.selenium.interactions.Mouse;
import org.openqa.selenium.internal.Locatable;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.orion.Driver;
import com.snapwiz.selenium.tests.staf.orion.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.orion.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.orion.apphelper.Screenshot;
import com.snapwiz.selenium.tests.staf.orion.apphelper.SelectAnswerAndSubmit;
import com.snapwiz.selenium.tests.staf.orion.apphelper.SelectChapterForTest;

public class DisplayOfContentAreaOnAnsweringPracticeTest {
	@Test
	public void displayOfContentAreaOnAnsweringPracticeTest()
	{
		try
		{
			Driver.startDriver();	
			new LoginUsingLTI().ltiLogin("169"); //  Logging in as student to orion
			new SelectChapterForTest().selectchapterfortest(0, 2);		//open diag test for 1st chapter
			new SelectAnswerAndSubmit().DiagTestWithCorrectAnswer(3);
			new Navigator().orionDashboard();
			WebElement menuitem = Driver.driver.findElement(By.className("al-preformance-text")); 
			Locatable hoverItem = (Locatable) menuitem;
			Mouse mouse = ((HasInputDevices) Driver.driver).getMouse();
			mouse.mouseMove(hoverItem.getCoordinates());
			Driver.driver.findElement(By.cssSelector("div[title='Practice']")).click();		//click on 1st TLO of 1st chapter
			Thread.sleep(3000);
			
			//attempt 1st question correct
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
			
				Driver.driver.findElement(By.cssSelector("a[id='2']")).click();//click on confidence level
				Thread.sleep(2000);
			 ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("img[title=\"Submit\"]")));
		    Thread.sleep(2000);
			int noticesize = Driver.driver.findElements(By.className("al-notification-message-body")).size();
			if(noticesize==1)
			
			{
				String notice =  Driver.driver.findElement(By.className("al-notification-message-body")).getText();
				if(notice.equals("You will build your proficiency faster if you say how confident you feel about the question before answering."))
				{
					Driver.driver.findElement(By.cssSelector("div[id='close-al-notification-dialog']")).click();
					Thread.sleep(2000);
					((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("img[title=\"Submit\"]")));
					Thread.sleep(2000);						
				}
			}
			
			//verify the correct icon
			 List<String> stringarray = new ArrayList<String>();
			 List<WebElement> WE = Driver.driver.findElements(By.className("user-response-data"));
			 for (WebElement element: WE) 
		     {          
				//stringarray.add(element.getText());
				stringarray.add((String) ((JavascriptExecutor)Driver.driver).executeScript("return arguments[0].innerHTML",element));
		     }
			 String [] icons = stringarray.toArray(new String[stringarray.size()]);
			 boolean found = false;
			 for(int i = 0; i<stringarray.size(); i++)
			 {
				 if(icons[i].contains("correct-icon.png"))
				 {
					 found = true;
				 	 break;
				 }
			 }
			 //String correctAnswer =  (String) ((JavascriptExecutor)Driver.driver).executeScript("return arguments[0].innerHTML",WE.get(0));
			 if(found == false)
			 {
				 new Screenshot().captureScreenshotFromTestCase();
				 Assert.fail("Correct answer is not displayed in practice question after attempting it correctly.");
			 }
			 
			 Driver.driver.findElement(By.cssSelector("img[title=\"Next\"]")).click();
			 Thread.sleep(3000);
			//attempt second question incorrect
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
		
			Thread.sleep(2000);
			Driver.driver.findElement(By.cssSelector("a[id='2']")).click();//click on confidence level
				Thread.sleep(2000);
			
			((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("img[title=\"Submit\"]")));
		    Thread.sleep(2000);
			int noticesize1 = Driver.driver.findElements(By.className("al-notification-message-body")).size();
			if(noticesize1==1)
			
			{
				String notice =  Driver.driver.findElement(By.className("al-notification-message-body")).getText();
				if(notice.equals("You will build your proficiency faster if you say how confident you feel about the question before answering."))
				{
					Driver.driver.findElement(By.cssSelector("div[id='close-al-notification-dialog']")).click();
					((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("img[title=\"Submit\"]")));
					Thread.sleep(2000);
					
				}
				
			}
			
			//verify the incorrect icon
			/*stringarray.clear();
			List<WebElement> WE1 = Driver.driver.findElements(By.className("user-response-data"));
			for (WebElement element: WE1) 
		     {          
				//stringarray.add(element.getText());
				stringarray.add((String) ((JavascriptExecutor)Driver.driver).executeScript("return arguments[0].innerHTML",element));
		     }
			String [] icons1 = stringarray.toArray(new String[stringarray.size()]);
			 boolean found1 = false;
			 for(int i = 0; i<stringarray.size(); i++)
			 {
				 System.out.println("-->"+icons1[i]);
				 if(icons1[i].contains("wrong-icon.png"))
				 {
					 found1 = true;
				 	 break;
				 }
			 }
			 
			 if(found1 == false)
			 {
				 new Screenshot().captureScreenshotFromTestCase();
				 Assert.fail("Wrong  symbol is not displayed in practice question after attempting it incorrectly.");
			 }*/
			//skip the 3rd question
			 Driver.driver.findElement(By.cssSelector("img[title=\"Next\"]")).click();
			 Thread.sleep(3000);
			 Driver.driver.findElement(By.cssSelector("img[title=\"Submit\"]")).click();
			 Thread.sleep(3000);
			 stringarray.clear();
			 List<WebElement> WE2 = Driver.driver.findElements(By.className("user-response-data"));
			 for (WebElement element: WE2) 
			     {          
					stringarray.add((String) ((JavascriptExecutor)Driver.driver).executeScript("return arguments[0].innerHTML",element));
			     }
			 String [] icons2 = stringarray.toArray(new String[stringarray.size()]);
			 boolean found2 = false;
			 for(int i = 0; i<stringarray.size(); i++)
				 {
					 if(icons2[i].contains("wrong-icon.png"))
					 {
						 found2 = true;
					 	 break;
					 }
				 }
				 
			 if(found2 == true)
				 {
					 new Screenshot().captureScreenshotFromTestCase();
					 Assert.fail("In content area chnages are displayed in practice question after skipping it.");
				 }
			 
		}
		catch(Exception e)
		{
			Assert.fail("Exception in displayOfContentAreaOnAnsweringPracticeTest in class DisplayOfContentAreaOnAnsweringPracticeTest",e);
		}
	}
	@AfterMethod
	public void tearDown() throws Exception
	{
	//	Driver.driver.quit();
	}
}
