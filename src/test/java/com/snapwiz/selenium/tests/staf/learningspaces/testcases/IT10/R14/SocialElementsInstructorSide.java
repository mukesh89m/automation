package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT10.R14;

import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Assignment;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;

import java.util.List;

public class SocialElementsInstructorSide extends Driver {
	
	@Test(priority=1,enabled=true)
	public void socialElementsInstructorSide()
	{
		try
		{
        String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", "1800");
		new Assignment().create(1800);
		new LoginUsingLTI().ltiLogin("18001");  //Creating student with ID 18001student
		new LoginUsingLTI().ltiLogin("1800"); //  Logging in as instructor
		new Assignment().assignToStudent(1800);  //Assigning assignment to student 18001
		new Assignment().likeAssignment(1800);
		new Assignment().unlikeAssignment(1800);
		//Put first comment
		new Assignment().commentAssignment(1800);
		int commentcount = new Assignment().commentCount(1800);
		if(commentcount!= 1) Assert.fail("Comment count not got updated to 1 after commenting on the assignment");
		//Put second comment
		new Assignment().commentAssignment(1800);
		commentcount = new Assignment().commentCount(1800);
		if(commentcount!= 2) Assert.fail("Comment count not got updated to 2 after commenting on the assignment");
		//Put third comment
		new Assignment().commentAssignment(1800);
		commentcount = new Assignment().commentCount(1800);
		if(commentcount!= 3) Assert.fail("Comment count not got updated to 2 after commenting on the assignment");
		new Navigator().NavigateTo("Assignments");
		driver.navigate().refresh();
        int index = 0;
            List<WebElement> assignments =  driver.findElements(By.cssSelector("span[class='ls-assignment-name instructor-assessment-review']"));
            for(WebElement element : assignments)
            {
                if(element.getText().contains(assessmentname))
                {
                    break;
                }
                index++;
            }
            List<WebElement> allComments = driver.findElements(By.cssSelector("a[title='Comments']"));
            allComments.get(index).click();//click on Comment Link
		Thread.sleep(3000);
		 String viewallcommentslinktext = driver.findElement(By.className("ls-stream-post__view-comments")).getText();
			if(!viewallcommentslinktext.equals("View all comments"))
				Assert.fail("A link 'View all comments' not appearing if there are more than 2 comments.");
		
			((JavascriptExecutor) driver).executeScript("arguments[0].click();",driver.findElement(By.partialLinkText("View all comments")));
			Thread.sleep(2000);
		String hideallcommentslinktext = driver.findElement(By.className("ls-stream-post__view-comments")).getText();
			if(!hideallcommentslinktext.equals("Hide all comments"))
				Assert.fail("View All Comments Link not getting changed to Hide All Comments after clicking on View All Comments");	
			 
		}
		catch(Exception e)
		{
			Assert.fail("Exception in testcase socialElementsInstructorSide in class SocialElementsInstructorSide",e);
		}
	}

}
