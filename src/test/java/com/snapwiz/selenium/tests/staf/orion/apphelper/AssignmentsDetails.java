package com.snapwiz.selenium.tests.staf.orion.apphelper;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.snapwiz.selenium.tests.staf.orion.Driver;
import com.snapwiz.selenium.tests.staf.orion.ReadTestData;

public class AssignmentsDetails
{

	public void assignmentsdetails()
	{
		try
		{
			String assignmentstext=Driver.driver.findElement(By.className("ls-assignment-item-wrapper")).getText();//fetch all text of assignment details
			String assignmentname=ReadTestData.readDataByTagName("","assessmentname", "2352");//fetch assignment name from textdata
			String gradedetails=ReadTestData.readDataByTagName("","gradingpolicy", "2352");//fetch assignment name from textdata	
			boolean text1=assignmentstext.contains("posted an assignment.");
			boolean text2=assignmentstext.contains("GRADED");
			boolean text3=assignmentstext.contains("Like");
			boolean text4=assignmentstext.contains("Comments");
			boolean text5=assignmentstext.contains("ago");
			boolean text6=assignmentstext.contains("Due Date");
			boolean text7=assignmentstext.contains("givenname family");
			boolean text8=assignmentstext.contains(gradedetails);
			boolean text9=assignmentstext.contains(assignmentname);
			boolean text10=Driver.driver.findElement(By.className("prof-icon-image")).isDisplayed();//Verify instructor image
			if(text1==false)
				Assert.fail("-posted an assignments -text not shown in Assignments details");
			if(text2==false)
				Assert.fail("-GRADED -text not shown in Assignments details");
			if(text3==false)
				Assert.fail("-like -text not shown in Assignments details");
			if(text4==false)
				Assert.fail("-comment -text not shown in Assignments details");
			if(text5==false)
				Assert.fail("-time not shown-text not shown in Assignments details");
			if(text6==false)
				Assert.fail("-Due date-text not shown in Assignments details");
			if(text7==false)
				Assert.fail("-instructor name -text not shown in Assignments details");
			if(text8==false)
				Assert.fail("-graded details -text not shown in Assignments details");
			if(text9==false)
				Assert.fail("-assignments name-text not shown in Assignments details");	
			if(text10==false)
				Assert.fail("Image of instructor not shown in Assignments details");
		}
		catch(Exception e)
		{
			Assert.fail("Exception in apphelpper assignmentsdetails in class AssignmentDetails.",e);
		}
	}
	
	public void bookMarks()
	{
		try
		{
			boolean bookmarkicon=Driver.driver.findElement(By.cssSelector("span[class='ls-assignment-bookmark not-bookmarked']")).isDisplayed();//verify bookmark icon
			
			if(bookmarkicon==false)
				Assert.fail("bookmark icon not shown ");
			Driver.driver.findElement(By.cssSelector("span[class='ls-assignment-bookmark not-bookmarked']")).click();
			Thread.sleep(3000);
			boolean bookmarkiconafterclick=Driver.driver.findElement(By.cssSelector("span[class='ls-assignment-bookmark bookmarked']")).isDisplayed();//bookmark icon after click 1st time on bookmark icon
			
			if(bookmarkiconafterclick==false)
				Assert.fail("bookmark icon not clicked");
		}
		catch(Exception e)
		{
			Assert.fail("Exception in apphelpper bookMarks in class AssignmentDetails.",e);
			
		}
	}
	public void assignmentName()
	{
		try
		{
			Driver.driver.findElement(By.cssSelector("span[class='ls-assignment-name instructor-assessment-review']")).click();//click on assignment name
			int helppage = Driver.driver.findElements(By.className("close-help-page")).size(); 
		     if(helppage == 1)
		    	 Driver.driver.findElement(By.className("close-help-page")).click();
		     Thread.sleep(3000);
			boolean timer=Driver.driver.findElement(By.id("assessmentTimer")).isDisplayed();//verify timer after click on assignment name
			if(timer==false)
				Assert.fail("assignment name not clickable");
			new Navigator().NavigateTo("Assignments");
			Driver.driver.findElement(By.cssSelector("span[class='ls-assignment-name instructor-assessment-review']")).click();//click on assignment name
			Thread.sleep(3000);
			List<WebElement> allelement=Driver.driver.findElements(By.className("qtn-label"));//select answer
			for(WebElement element:allelement)
			{
				element.click();
				break;
			}
			Driver.driver.findElement(By.cssSelector("a[class='btn btn--primary btn--large btn--submit']")).click();//click on submit button
			new Navigator().NavigateTo("Assignments");
			Driver.driver.findElement(By.cssSelector("span[class='ls-assignment-name instructor-assessment-review']")).click();
			Thread.sleep(3000);
			String performancetext=Driver.driver.findElement(By.className("report-chart-title")).getText();//fetch text from performance page
			if(!performancetext.trim().equals("Performance Summary"))
				Assert.fail("performance summary page not shown");
		}
		catch(Exception e)
		{
			Assert.fail("Exception in apphelpper assignmentName in class AssignmentDetails.",e);
			
		}
	}
	public void gradedAssignments()
	{
		try
		{
			boolean gradedtext=Driver.driver.findElement(By.cssSelector("span[class='ls-assignment-graded-quiz-icon assessment-block-icon']")).isSelected();//verify graed text is clickable
			if(gradedtext==true)
				Assert.fail("Graded text clickable");
			
		}
		catch(Exception e)
		{
			Assert.fail("Exception in apphelpper gradedAssignments in class AssignmentDetails.",e);
			
		}
	}
	public void gradepolicywheninstructornotprovidepolicyornorgraedassignments()
	{
		try
		{
			
			List<WebElement> gradepolicytextifintructornotprovidetext=Driver.driver.findElements(By.className("ls-assignment-grading-title"));
			for(WebElement gradepolicytext : gradepolicytextifintructornotprovidetext)
			{
				if(!gradepolicytext.getText().equals(""))
					Assert.fail("graded policy discription text shown without instructor provided graded policy");
			}
				
		}
		catch(Exception e)
		{
			Assert.fail("Exception in apphelpper gradepolicywheninstructornotprovidepolicyornorgraedassignments in class AssignmentDetails.",e);
			
		}
	}
	
	public void countoflike(int expectedLikeCount)
	{
		try
		{
			//String numberofliketext=Driver.driver.findElement(By.className("ls-post-like-count")).getText();//get nubmer of count of like
			//if(numberofliketext.trim().equals("0"))
				Driver.driver.findElement(By.className("ls-post-like-link")).click();//click on like link
			String numberofliketext1=Driver.driver.findElement(By.className("ls-post-like-count")).getText();//get nubmer of count of like
			if(Integer.parseInt(numberofliketext1)!=expectedLikeCount)
				Assert.fail("Number of like count not increased after clicking on like");
		}
		catch(Exception e)
		{
			Assert.fail("Exception in apphelpper countoflike in class AssignmentDetails.",e);
			
		}
	}
	
	public void countofcomments(int expectedCommentCount)
	{
		try
		{
			//String intialcommentcount = (Driver.driver.findElement(By.className("ls-stream-post-comment-count")).getText().trim());
				Driver.driver.findElement(By.cssSelector("a[class='ls-stream-post__footer-comment-link js-toggle-comments']")).click();//click on  comment link
			String randomtext=new RandomString().randomstring(4);//generate random string
			boolean commentpost=new CommentOnPost().commentOnPost(randomtext, 1);//comment post on assignments
			if(commentpost==false)
				Assert.fail("comment not posted.");
			String numberofcommentstext=Driver.driver.findElement(By.className("ls-stream-post-comment-count")).getText().trim();//get nubmer of count of comments
			
			if(Integer.parseInt(numberofcommentstext)!= expectedCommentCount)
				Assert.fail("Comment count not increased after posting a comment");
		}
		catch(Exception e)
		{
			Assert.fail("Exception in apphelpper countofcomments in class AssignmentDetails.",e);
			
		}
	}
	public void assignmentrightsidetabs()
	{
		try
		{
			Driver.driver.findElement(By.cssSelector("span[title='Stream']")).click();//click on stream tab
			Thread.sleep(3000);
			boolean stremcontentpost=Driver.driver.findElement(By.className("ls-new-btn-section")).isDisplayed();
			if(stremcontentpost==false)
				Assert.fail("After clicking on stream tab detals not shown.");
			Driver.driver.findElement(By.cssSelector("span[title='Assignments']")).click();//click on Assignments tab
			Thread.sleep(3000);
			String assignmenttext=ReadTestData.readDataByTagName("", "assessmentname", "2478"); 
			String assignmentname=Driver.driver.findElement(By.cssSelector("a[class='ls_assessment_link']")).getText();//fetch assignments name
			if(!assignmentname.trim().contains(assignmenttext))
				Assert.fail("After click on Assignment tab last open assignment not shown in right side panel");			
			Driver.driver.findElement(By.cssSelector("span[title='About']")).click();//click on about tab
			Thread.sleep(3000);
			boolean abouttext=Driver.driver.findElement(By.className("al-content-box")).isDisplayed();
			if(abouttext==false)
				Assert.fail("After click on About tab point not shown");
			//boolean objectivetext=Driver.driver.findElement(By.className("al-content-box-body")).isDisplayed();
			//if(objectivetext==false)
				//Assert.fail("After click on About tab learing objective not shown");
		}
		catch(Exception e)
		{
			Assert.fail("Exception in apphelpper assignmentrightsidetabs in class AssignmentDetails.",e);
			
		}
	}
	
	public void AssignmentTimervalidate()
	{
		try
		{
			boolean timer=Driver.driver.findElement(By.id("assessmentTimer")).isDisplayed();//verify timer is present or not
			if(timer==false)
				Assert.fail("Timer not shown on question page");
			String timetaken=Driver.driver.findElement(By.className("timevalue")).getAttribute("timetaken");//time taken at 1st time
			int timetaken1=Integer.parseInt(timetaken);//convert into integer of above time
			Thread.sleep(3000);
			String timetaken2=Driver.driver.findElement(By.className("timevalue")).getAttribute("timetaken");//time taken after few second later
			int timetaken3=Integer.parseInt(timetaken2);//convert into integer
			if(timetaken1>timetaken3)
				Assert.fail("timer stop means time taken value not increase");
			
			
		}
		catch(Exception e)
		{
			Assert.fail("Exception in apphelpper AssignmentTimervalidate in class AssignmentDetails.",e);
			
		}
		
	}
	public void assignmentsummarypopup()
	{
		try
		{
			Driver.driver.findElement(By.className("arrow-top")).click();
			Thread.sleep(3000);
			String summarypopupheader=Driver.driver.findElement(By.className("question-summary-header")).getText();
			if(!summarypopupheader.contains("Question"))
				Assert.fail("In header question not shown");
			if(!summarypopupheader.contains("Attempted"))
				Assert.fail("In header Attempted not shown");
			if(!summarypopupheader.contains("For Review"))
				Assert.fail("In header For Review not shown");
		}
		catch(Exception e)
		{
			Assert.fail("Exception in apphelpper assignmentsummarypopup in class AssignmentDetails.",e);
			
		}
	}

}
