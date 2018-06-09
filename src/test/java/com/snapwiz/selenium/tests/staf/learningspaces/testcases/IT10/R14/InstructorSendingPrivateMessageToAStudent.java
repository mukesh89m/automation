package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT10.R14;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Assignment;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.RandomString;

public class InstructorSendingPrivateMessageToAStudent extends Driver{
	@Test
	public void instructorSendingPrivateMessageToAStudent()
	{
		try
		{
			String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", "2011");
			String studentname1 = ReadTestData.readDataByTagName("", "studentname", "20111");
			String shareWithInitialString = ReadTestData.readDataByTagName("", "shareWithInitialString", "20111");
			String shareName = ReadTestData.readDataByTagName("", "shareName", "20112");
			String shareWithEnterFullString = ReadTestData.readDataByTagName("", "shareWithEnterFullString", "20111");
			String y[]=shareWithEnterFullString.split(" ");
			shareWithEnterFullString = y[1] + ", " + y[0];//reverse the name with comma in between
			new Assignment().create(2011);
			new LoginUsingLTI().ltiLogin("20111");//creating student with id 20111student
			new LoginUsingLTI().ltiLogin("20112");//creating student with id 20111student
			new LoginUsingLTI().ltiLogin("2011");
			new Assignment().assignToStudent(20111);
			new LoginUsingLTI().ltiLogin("2011");
			new Assignment().updateAssignment(20112, true);
			new LoginUsingLTI().ltiLogin("20111");
			new Assignment().submitAssignmentAsStudent(20111);
			new LoginUsingLTI().ltiLogin("20112");
			new Assignment().submitAssignmentAsStudent(20112);
			new LoginUsingLTI().ltiLogin("2011");
			new Assignment().clickViewResponse(assessmentname);
			int index = 0;
			List<WebElement> assignments =  driver.findElements(By.cssSelector("span[class='idb-gradebook-content-label-text idb-gradebook-username']"));
			 for(WebElement element : assignments)
			 {
				 if(element.getText().equals(studentname1))
						 {
					 		break;
						 }
				 index++;
			 }
			 //tick the checkbox
			List <WebElement> allCheckBox = driver.findElements(By.xpath("/*//*[starts-with(@class, 'idb-Reviewed-icon-checkbox')]"));
			allCheckBox.get(0).click();
			Thread.sleep(3000);
			
			//click on 'Post a message' button
			driver.findElement(By.cssSelector("div.ls-post-a-message.ls-enable-post-a-message")).click();
			Thread.sleep(3000);
			
			//checking for 'Post a message' label in 'Post a message' form.
			String postMessageLabel = driver.findElement(By.className("ls-ins-send-mail-header-label")).getText();
			if(!postMessageLabel.equals("Post a message"))
				Assert.fail("'Post a message' label is absent in 'Post a message' form");
			
			//checking for 'To' and 'Message' labels in 'Post a message' form.
			List <WebElement> messageText = driver.findElements(By.xpath("/*//*[starts-with(@class, 'message-text')]"));
			if(!messageText.get(0).getText().equals("To") || !messageText.get(1).getText().equals("Message"))
				Assert.fail("'To' and 'Message' labels are not present in 'Post a message' form.");
			
			//checking for 'Send' button in 'Post a message' form.
			int sendButton = driver.findElements(By.cssSelector("div[id='send-mail']")).size();
			if(sendButton != 1)
				Assert.fail("Send button  is not present in 'Post a message' form.");
			
			//checking for selected student names is getting  prepopulated in the to list.
			String selectedName = driver.findElement(By.className("item-text")).getText();
			System.out.println("selectedname:"+selectedName);
			System.out.println("student:"+studentname1);
			if(!selectedName.equals(studentname1))
				Assert.fail("The selected student names is not getting  prepopulated in the to list.");
			
			//Remove the student name from To list
			driver.findElement(By.cssSelector("a.closebutton")).click();
			Thread.sleep(3000);
			String removedName = driver.findElement(By.className("holder")).getText();
			if(removedName.equals(null))
				Assert.fail("Names are not getting removed from the To list");
			Thread.sleep(5000);
			//Enter a initial string and select student name
			driver.findElement(By.className("maininput")).sendKeys(shareWithInitialString);
			boolean sharefound = false;
			List<WebElement> suggestname = driver.findElements(By.xpath("/*//*[starts-with(@rel, 'uid_')]"));
			   for (WebElement answerchoice: suggestname)
			   {    		   
			    if(answerchoice.getText().trim().equals(shareWithEnterFullString))
			    {
			     answerchoice.click();	
			     sharefound = true;
			     break;
			    }
			   }
			if(sharefound == false)
				Assert.fail("No value selected from the autosuggestion");
			
			//Enter message text
			String randomText = new RandomString().randomstring(5);
			driver.findElement(By.id("email-message-content")).clear();
			Thread.sleep(3000);
			driver.findElement(By.id("email-message-content")).sendKeys(randomText);
			driver.findElement(By.id("send-mail")).click();
			
			//Login as student to whom message has been sent and check the message is visible to him or not
			new LoginUsingLTI().ltiLogin("20111");
			new Navigator().NavigateTo("Course Stream");
			List<WebElement> allMessage = driver.findElements(By.className("ls-link-span"));
			String message = allMessage.get(0).getText();
			System.out.println("message:"+allMessage.get(0).getText());
			System.out.println(randomText);
			if(!message.contains(randomText))
				Assert.fail("Message has not been posted as it is not visible");

			//Login as student to whom message has not been sent and check the message is visible to him or not
			new LoginUsingLTI().ltiLogin("20112");
			new Navigator().NavigateTo("Course Stream");
			int allMessage1 = driver.findElements(By.className("ls-link-span")).size();
			if(allMessage1 != 0)
				Assert.fail("Message is visible to student with whom instructor has not shared the message");
			
		}
		catch(Exception e)
		{
			Assert.fail("Exception in instructorSendingPrivateMessageToAStudent in class InstructorSendingPrivateMessageToAStudent", e);
		}
	}

}
