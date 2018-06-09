package com.snapwiz.selenium.tests.staf.learningspaces.testcases.anonymous;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.AttemptTest;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.DiagnosticTest;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.DirectLogin;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.TOCShow;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.TopicOpen;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.UpdateContentIndex;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.ComboBox;

public class NotifiactionId2010Verifivation extends Driver {
@Test
	public void notifiactionId2010Verifivation()
	{
		try
		{
			String course = ReadTestData.readDataByTagName("NotifiactionId2010Verifivation", "course", "2510");
			String assessmentname = ReadTestData.readDataByTagName("NotifiactionId2010Verifivation", "assessmentname", "2510");
			String questionset = ReadTestData.readDataByTagName("NotifiactionId2010Verifivation", "questionset", "2510");
			String questiontext = ReadTestData.readDataByTagName("NotifiactionId2010Verifivation", "questiontext", "2510");
			String questiontype = ReadTestData.readDataByTagName("NotifiactionId2010Verifivation", "questiontype", "2510");
			String chapterName = ReadTestData.readDataByTagName("NotifiactionId2010Verifivation", "chapterName", "2510");
			String topicname = ReadTestData.readDataByTagName("NotifiactionId2010Verifivation", "topicname", "2510");
			String difficultylevel = ReadTestData.readDataByTagName("NotifiactionId2010Verifivation", "difficultylevel", "2510");
			String learningobjective = ReadTestData.readDataByTagName("NotifiactionId2010Verifivation", "learningobjective", "2510");
			new DirectLogin().CMSLogin();
			String title=driver.getTitle();
			if(title.equals("Course Content Details"))
				{
				 driver.findElement(By.cssSelector("img[alt=\""+course+"\"]")).click();
				 int index = 0;
				 //Find the chapter index
				 List <WebElement> allChapters = driver.findElements(By.xpath("//*[starts-with(@class, 'course-chapter-label')]"));
				 for(WebElement element : allChapters)
				 {
					 if(element.getText().equals(chapterName))
							 {
						 		break;
							 }
					 index++;
				 }
				//Find the topic under a chapter and click on it
				 List <WebElement> expansionSymbol = driver.findElements(By.xpath("//*[starts-with(@class, 'expand-chapter-tree expand')]"));
				 expansionSymbol.get(index).click();
				 Thread.sleep(3000);
				 //List the topic and click on a topic
				 List <WebElement> allTopic = driver.findElements(By.xpath("//*[starts-with(@class, 'course-topic-label node')]"));
				 for(WebElement topic: allTopic)
				 {
					 if(topic.getText().equals(topicname))
					 {
						 topic.click();
						 Thread.sleep(3000);
						 break;
					 }
					 
				 }
				 driver.findElement(By.cssSelector("div.create-practice")).click();
				 driver.findElement(By.id("assessmentName")).click();
				 driver.findElement(By.id("assessmentName")).clear();
				 driver.findElement(By.id("assessmentName")).sendKeys(assessmentname);
				 driver.findElement(By.id("questionSetName")).clear();
				 driver.findElement(By.id("questionSetName")).sendKeys(questionset);
				
				 driver.findElement(By.id("qtn-type-true-false-img")).click();
				 
				
				 driver.findElement(By.id("question-raw-content")).click();
			
				 
				 driver.switchTo().frame("iframe-question-edit").findElement(By.xpath("/html/body")).sendKeys(questiontext);
				 driver.switchTo().defaultContent();
				 
				 Actions action = new Actions(driver);
			        WebElement we = driver.findElement(By.id("choice1"));
			        action.moveToElement(we).build().perform();			 
				 driver.findElement(By.xpath("//img[@title='Set it as Correct Answer']")).click();
				 Thread.sleep(3000);
				 new ComboBox().selectValue(3, questiontype);
				 if(difficultylevel != null)
				 {
					 new ComboBox().selectValue(7, difficultylevel);
				 }
				 if(learningobjective != null)
				 {
					 driver.findElement(By.id("learing-objectives-span")).click();
					 driver.findElement(By.xpath("//label[@id='7510']")).click();
					 driver.findElement(By.cssSelector("span.add-collection-to-subtopic")).click();
					 driver.findElement(By.cssSelector("span.cancel-collection")).click();
					
				 }
				 new ComboBox().selectValue(4, "Publish");
				 
				 driver.findElement(By.id("saveQuestionDetails1")).click();
				 Thread.sleep(5000);
				 for(int i = 1; i<=5; i++){
				 driver.findElement(By.id("questionOptions")).click();
				 Thread.sleep(2000);
				 driver.findElement(By.id("addQuestion")).click();
				 Thread.sleep(2000);
				 driver.findElement(By.id("qtn-type-true-false-img")).click();
				 Thread.sleep(2000);
				 driver.findElement(By.id("question-raw-content")).click();
				 driver.switchTo().frame("iframe-question-edit").findElement(By.xpath("/html/body")).sendKeys(questiontext);
				 driver.switchTo().defaultContent();
				 //Setting choice 1 as correct answer
				 Actions action1 = new Actions(driver);
			     WebElement we1 = driver.findElement(By.id("choice1"));
			     action1.moveToElement(we1).build().perform();			 
				 driver.findElement(By.xpath("//img[@title='Set it as Correct Answer']")).click();
				 new ComboBox().selectValue(3, "Publish");
				 driver.findElement(By.id("saveQuestionDetails1")).click();
				 Thread.sleep(3000);
				 }
			}
		
			new UpdateContentIndex().updatecontentindex("2510");
		
			new LoginUsingLTI().ltiLogin("2510");
			new Navigator().NavigateTo("eTextbook");
			new DiagnosticTest().startTest(2);
			new AttemptTest().Diagonestictest();
			new TOCShow().tocShow();
			new TopicOpen().topicOpen("1.1 25101Assessment");
			for(int i = 0; i<=3; i++){
				List<WebElement> allChoice = driver.findElements(By.className("qtn-label"));
				allChoice.get(1).click();  //click on option B which incorrect answer
				Thread.sleep(3000);
				int notificationSize = driver.findElements(By.className("al-notification-message-body")).size();
				if(notificationSize == 1)
				{
					driver.findElement(By.id("submit-practice-question-button")).click();
					driver.findElement(By.id("submit-practice-question-button")).click();
				}
				else
				{
					driver.findElement(By.id("submit-practice-question-button")).click();
					driver.findElement(By.id("next-practice-question-button")).click();
				}
			}
			String notification = driver.findElement(By.className("al-notification-message-body")).getText();
			System.out.println("bksbgdcf"+notification);
		}
		catch(Exception e)
		{
			Assert.fail("Exception in notifiactionId2010Verifivation in class NotifiactionId2010Verifivation",e);
		}
	}
}
