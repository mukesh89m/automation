package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT10.R14;

import java.util.List;

import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.Click;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Assignment;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.TabClose;

public class InstructorAbleToGradeAndProvideFeedback extends Driver {
	@Test
	public void instructorAbleToGradeAndProvideFeedback()
	{
		try
		{
			String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", "1983");
			System.out.println(assessmentname);
			String additionalnote = ReadTestData.readDataByTagName("", "additionalnote", "1983");
			new Assignment().create(1983);
			new LoginUsingLTI().ltiLogin("19831");//creating student with id 19831student
			new LoginUsingLTI().ltiLogin("19832");//creating student with id 19832student
			new LoginUsingLTI().ltiLogin("1983");
			new Assignment().assignToStudent(19831);
		
			new LoginUsingLTI().ltiLogin("1983");
			new Assignment().updateAssignment(19832, true);
			new LoginUsingLTI().ltiLogin("19831");
			new Assignment().submitAssignmentAsStudent(19831);
			new LoginUsingLTI().ltiLogin("19832");
			new Assignment().submitAssignmentAsStudent(19832);
			new LoginUsingLTI().ltiLogin("1983");
			new Navigator().NavigateTo("Assignments");
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
			 System.out.println("Index "+index);
			 //click on 'View Responses link'
			 List<WebElement> viewresponseslink = driver.findElements(By.cssSelector("span[class='ls-grade-book-assessment']"));
			   ((JavascriptExecutor) driver).executeScript("arguments[0].click();", viewresponseslink.get(index));
			   Thread.sleep(3000);
			 String tab = driver.findElement(By.cssSelector("div[class='tab active']")).getText();
			 System.out.println("tan "+tab);
			// Verify the assessment tab's name	
			 if(!tab.equals("Response - (shor) "+assessmentname+""))
			 {
				 Assert.fail("The name of the tab is not in the form - “Response - <Assignment Name>”. ");
			 }
			 //Checking for description of the assessment
			 boolean found = false;
			 List<WebElement> assignmentInCourseStream =  driver.findElements(By.cssSelector("div[id='idb-additional-note-section']"));
			 for(WebElement element: assignmentInCourseStream)
			 {
				 System.out.println(element.getText());
				 if(element.getText().contains(additionalnote));
				 {
					found = true;
					break;
				 }
			 }
			 if(found == false)
				 Assert.fail("Description and other details for the assignment is not visisble to the instructor");
			 new TabClose().tabClose(1);
			 new Assignment().provideGradeToStudentForMultipleQuestions(19831);
			 Thread.sleep(3000);
			 new TabClose().tabClose(1);
			 new Assignment().provideGradeToStudentForMultipleQuestions(19832);
			 driver.findElement(By.cssSelector("div[title='Release Grade for All']")).click();

			 //After providing the grade again login as instructor and validate the elements
			 new LoginUsingLTI().ltiLogin("1983");
			 new Navigator().NavigateTo("Assignments");
			driver.findElement(By.className("ls-grade-book-assessment")).click();  //clicking on View Grade link
			Thread.sleep(4000);
			 String str = driver.findElement(By.cssSelector("span[class='ls-assignment-name instructor-assessment-review']")).getAttribute("title");
			 System.out.println(str);
			 if(!str.contains(assessmentname))
			 {
				 Assert.fail("On clicking 'View Grade' the particular assesment does not open");
			 }
			 Thread.sleep(5000);
			 String tab1 = driver.findElement(By.cssSelector("div[class='tab active']")).getText();
			 System.out.println("tab active "+tab1);
			
			 // Verify the assessment tab's name	
			 if(!tab1.equals("Response - (shor) "+assessmentname+""))
			 {
				 Assert.fail("The name of the tab is not in the form - Response - <Assignment Name>. ");
			 }
			 
			 //Checking for description of the assessment
			 boolean found1 = false;
			 List<WebElement> assignmentInCourseStream1 =  driver.findElements(By.cssSelector("div[id='idb-additional-note-section']"));
			 for(WebElement element1: assignmentInCourseStream1)
			 {
				 System.out.println("-->"+element1.getText());
				 if(element1.getText().contains(additionalnote));
				 {
					found1 = true;
					break;
				 }
			 }
			 if(found1 == false)
				 Assert.fail("Description and other details for the assignment is not visisble to the instructor");
			 
			 
			 
			
			 // VERIFYING THE Comment OPTION
			 
			 
			 
			 //storing the initial comment count in an variable
			 List<WebElement> numberofcomments = driver.findElements(By.className("ls-stream-post-comment-count"));
			 int oldcount = Integer.parseInt(numberofcomments.get(1).getText());//Element is visible 1st index
			 System.out.println("old count "+oldcount);
			 
			//post a comment in Assessment response page
			 new Assignment().postCommentInAssessmentResponseTab();
			 
			 //Check if comment count is getting updated or not
			 List<WebElement> numberofcomments1 = driver.findElements(By.className("ls-stream-post-comment-count"));
			 int newcount = Integer.parseInt(numberofcomments1.get(1).getText()); //Element is visible 1st index
			 System.out.println("New count "+newcount);
			 if(newcount == oldcount)
			 {
				 Assert.fail("Comment number is not getting updated");
			 }
			 
			//Posting various instructor comments on view responses page
			Thread.sleep(3000);
			List<WebElement> comments = driver.findElements(By.cssSelector("a[class='ls-stream-post__footer-comment-link js-toggle-comments']"));
			comments.get(1).click();
			new Assignment().postCommentInAssessmentResponseTab();
			comments = driver.findElements(By.cssSelector("a[class='ls-stream-post__footer-comment-link js-toggle-comments']"));
			comments.get(1).click();
			new Assignment().postCommentInAssessmentResponseTab();
			comments = driver.findElements(By.cssSelector("a[class='ls-stream-post__footer-comment-link js-toggle-comments']"));
			comments.get(1).click();
			new Assignment().postCommentInAssessmentResponseTab();
			
			//Navigating to View Responses page again to make the view all comments link visible
			new Navigator().NavigateTo("Assignments");
			 index = 0;
		    assignments =  driver.findElements(By.cssSelector("span[class='ls-assignment-name instructor-assessment-review']"));
			 for(WebElement element : assignments)
			 {
				 if(element.getText().contains(assessmentname))
						 {
					 		break;
						 }
				 index++;
			 }
		
			 //click on 'View Responses link'
			  viewresponseslink = driver.findElements(By.cssSelector("span[class='ls-grade-book-assessment']"));
			   ((JavascriptExecutor) driver).executeScript("arguments[0].click();", viewresponseslink.get(index));
			   
			   //Clicking on comments link after navigating to view responses page
			   comments = driver.findElements(By.cssSelector("a[class='ls-stream-post__footer-comment-link js-toggle-comments']"));
				Thread.sleep(2000);
			   System.out.println("comments::"+comments.get(0));
			   //new Click().clickBycssselector("a[class='ls-stream-post__footer-comment-link js-toggle-comments']");
				comments.get(0).click();
				
			//Checking whether comments are in expanded form or not
			List<WebElement> listviewAllComments = driver.findElements(By.className("ls-stream-post__view-comments"));
			String viewAllComments = listviewAllComments.get(1).getText();//Element is visible 1st index
			if(!viewAllComments.equals("View all comments"))
			{
				Assert.fail("Comments are in expanded format.");
			}
			
			//click on 'View all comments'
			driver.findElement(By.partialLinkText("View all comments")).click();
				Thread.sleep(2000);
				
			//checking if number of displayed comments is equal to the count or not
			 int count = 0;
			 List<WebElement> numberOfDisplayedComments = driver.findElements(By.className("ls-stream-post__comment-text"));
			 for(WebElement element1: numberOfDisplayedComments)
			 {
				if(element1.getText().length() > 0)
				 count++;
			 }
			 if(count == newcount)
			 {
				 Assert.fail("Number displayed comment is not equal to the count beside comment link.");
			 }

			 //Again posting a comment in Assessment response page in expanded form
			 new Assignment().postCommentInAssessmentResponseTab();
			 Thread.sleep(2000);
			 
			 
			 //click on 'Hide all comments '
			 ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.linkText("Hide all comments")));
			 Thread.sleep(2000);
			 
			 //find the number number Of Displayed Comments After Hiding
			 int count1 = 0;
			 List<WebElement> numberOfDisplayedCommentsAfterHiding = driver.findElements(By.className("ls-stream-post__comment-text"));
			 for(WebElement element1: numberOfDisplayedCommentsAfterHiding)
			 {
				if(element1.getText().length() > 0)
					count1++;
			 }
			 
			 if(count1 != 2)//After Hiding there will be two comments visible
			 {
				 Assert.fail("After clicking 'Hide all comments' the comments are not getting hidden.");
			 }
			 
			
			 
			 
			 /*
			  * VERIFYING THE Like OPTION
			  */
			 
			//click on like link
			Thread.sleep(3000);
			String likelinkafterclicking="";
			List<WebElement> likelinks = driver.findElements(By.cssSelector("span[class='ls-post-like-link']"));
			for(WebElement element1: likelinks)
			 {
				if(element1.isDisplayed()==true)
				{
				element1.click();
				 likelinkafterclicking = element1.getText();
				break;
				}
			 }
			Thread.sleep(3000);
			//After liking the Like should become 'Unlike'
			if(!likelinkafterclicking.equals("Unlike"))
				Assert.fail("Like link not converted to unlike after clicking on it");
			
			//Count the like number 
			List<WebElement> newLikeCount = driver.findElements(By.cssSelector("span[class='ls-post-like-count']"));
			String newlikes = newLikeCount.get(1).getText();
			if(!newlikes.equals("1"))
			{
				Assert.fail("Like count  not getting updated after clicking on like");
			}
			
			//Again click on unlike button
			List<WebElement> likelinks2 = driver.findElements(By.cssSelector("span[class='ls-post-like-link']"));
			likelinks2.get(1).click();  //Element is visible 1st index
			Thread.sleep(3000);
			
			//After clicking 'Unlike' it should become Like
			String unlikelinkafterclicking = likelinks2.get(1).getText();
			if(!unlikelinkafterclicking.equals("Like"))
				Assert.fail("Unlike link not converted to Like after clicking on it");
			
			//calculate like count after unliking 
			List<WebElement> newLinkCount1 = driver.findElements(By.cssSelector("span[class='ls-post-like-count']"));
			String newlikes1 = newLinkCount1.get(1).getText();
			if(!newlikes1.equals("0"))
			{
				Assert.fail("Like count not getting updated after clicking on like");
			}

		}
		catch(Exception e)
		{
			Assert.fail("Exception in instructorAbleToGradeAndProvideFeedback in InstructorAbleToGradeAndProvideFeedback class.",e);
		}
	}

}
