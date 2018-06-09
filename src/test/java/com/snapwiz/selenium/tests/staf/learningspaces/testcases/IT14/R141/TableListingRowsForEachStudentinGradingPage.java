package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT14.R141;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.DiscussionWidget;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.RandomString;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Screenshot;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.TopicOpen;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.ComboBox;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.MouseHover;

public class TableListingRowsForEachStudentinGradingPage extends Driver{
	
	@Test
	public void tableListingRowsForEachStudentinGradingPage()
	{
		try
		{
            String shareWithInitialString = ReadTestData.readDataByTagName("", "shareWithInitialString", "148_2");
			String shareName = ReadTestData.readDataByTagName("", "shareName", "148_2");
            String y[]=shareName.split(" ");
            shareName = y[1] + ", " + y[0];//reverse the name with comma in between
			new LoginUsingLTI().ltiLogin("148_1");		//create a student
			new LoginUsingLTI().ltiLogin("148_2");		//create a student
			new LoginUsingLTI().ltiLogin("148");		//login as instructor
			new Navigator().NavigateTo("eTextbook");	//navigate to etextbook
			new TopicOpen().openLessonWithDiscussionWidget();
			new DiscussionWidget().assignDiscussionWidgetWithDefaultClassSection(148);

			new LoginUsingLTI().ltiLogin("148_1");		//login as student
			new Navigator().NavigateTo("Assignments");	//navigate to Assignments
			driver.findElement(By.className("ls-stream-learing-activity-level")).click();	//click on DW assignment
			new WebDriverWait(driver, 2000).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("a[class='ls-content-post__footer-perspective-link js-toggle-comments']")));
			String perspective = new RandomString().randomstring(5);
			new DiscussionWidget().addPerspectiveForDWAssignment(perspective);

			new LoginUsingLTI().ltiLogin("148");		//login as instructor
			new Navigator().NavigateTo("Assignments");	//navigate to Assignments
			driver.findElement(By.cssSelector("span[title='View Student Responses']")).click();	//click on View Student Responses
			String d1 = driver.findElement(By.cssSelector("div[class='idb-gradebook-content-coloumn idb-gradebook-question-coloumn ls-learning-activity-label']")).getText();
			if(!d1.contains("D1"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("D1 column is missing in the table present at the bottom of grading page for a DW assignment.");
			}
			List<WebElement> allElements = driver.findElements(By.xpath("/*//*[starts-with(@id, 'sbSelector_')]"));
			Thread.sleep(2000);
			allElements.get(4).click();	//click on checkbox to select student from the table
			int all = driver.findElements(By.linkText("All")).size();
			//TC row no. 148
			int none = driver.findElements(By.linkText("None")).size();
			if(all == 0 || none == 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Select checkbox(Option to select all/none) is missing in the table present at the bottom of grading page for a DW assignment.");
			}
			List<WebElement> allRows = driver.findElements(By.xpath("/*//*[starts-with(@class, 'idb-grader-content-body-row idb-grader-content-body-view-row idb-grader-content-')]"));
			//TC row no. 151
			if(allRows.size() != 2)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Rows specific to each student is not displayed in the table present at the bottom of grading page for a DW assignment.");
			}
			driver.findElement(By.cssSelector("a[title='All']")).click();//select all values
			List<WebElement> allCheckbox = driver.findElements(By.xpath("/*//*[starts-with(@class, 'idb-Reviewed-icon-checkbox idb-Reviewed-')]"));
			//TC row no. 149
			if(!allCheckbox.get(0).getAttribute("innerHTML").contains("check-box-selected.png") || !allCheckbox.get(1).getAttribute("innerHTML").contains("check-box-selected.png"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Selecting all option doesnt check box for all student in the table below Grading page.");
			}
			new ComboBox().selectValue(4, "None"); //select none
			List<WebElement> deselectCheckbox = driver.findElements(By.xpath("/*//*[starts-with(@class, 'idb-Reviewed-icon-checkbox idb-Reviewed-')]"));
			//TC row no. 150
			if(!deselectCheckbox.get(0).getAttribute("innerHTML").contains("check-box.png") || !deselectCheckbox.get(1).getAttribute("innerHTML").contains("check-box.png"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Selecting None option doesnt uncheck box for all student in the table below Grading page.");
			}
			//mouse hover over D1 column
			Actions action = new Actions(driver);
			WebElement we = driver.findElement(By.cssSelector("div[class='idb-gradebook-content-coloumn idb-gradebook-question-coloumn ls-learning-activity-label']"));
			action.moveToElement(we).build().perform();
			//TC row no. 152
			String text = driver.findElement(By.cssSelector("div[class='idb-gradebook-content-coloumn idb-gradebook-question-coloumn ls-learning-activity-label']")).getAttribute("resourcetitle");
			if(text == null)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("On hovering over D1 column header the DW question text is not displayed as tooltip.");
			}
			//TC row no. 156
			int checkMark = driver.findElements(By.className("idb-question-manually-graded")).size();
			if(checkMark == 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("\"Checkmark\" is not displayed under D1 when the user has submitted the discussion widget but the instructor has not marked the discussion widget.");
			}
			//TC row no. 153
			new MouseHover().mouserhover("idb-gradebook-assignment-username"); //mouse hover over Student name
			String enterGrade = driver.findElement(By.id("idb-grade-now-link")).getAttribute("title");
			if(!enterGrade.contains("Enter Grade"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("On hovering student name in the table present at the bottom of grading page the Enter Grade link is not displayed as tooltip.");
			}
			//TC row no. 154 and 155
			new DiscussionWidget().provideGradeToStudent(0, "1.6");
			String score = driver.findElement(By.cssSelector("span[class='idb-gradebook-question-content idb-question-partial-scored']")).getText();
			if(!score.contains("1.6"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("The instructor is unable to add the marks of that particular student.");
			}
			//TC row no. 160
			int dashMark = driver.findElements(By.className("idb-question-skipped")).size();
			if(dashMark == 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("\"_\" is not displayed under D1 when the student has not started the DW assignment.");
			}

			//verify Post Message button
			driver.findElement(By.className("ls-post-a-message")).click();
			Thread.sleep(2000);
			//TC row no. 164
			boolean popup = driver.findElement(By.cssSelector("div[id='sendMailDialog']")).isDisplayed();
			if(popup != false)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Post a message button is not disabled by default in Grading page for a DW assignment.");
			}
			driver.findElement(By.xpath(".//*[@id='idb-gradeBook-overview-test-title-wrapper']/div[8]/div[1]/span[2]/span[1]/img")).click();	//checkbox click
			Thread.sleep(2000);
			driver.findElement(By.className("ls-post-a-message")).click();	//click on post message
			Thread.sleep(2000);
			//TC row no. 165
			List<WebElement> popup1 =  driver.findElements(By.cssSelector("div[id='sendMailDialog']"));

			if(popup1.get(0).isDisplayed() == false)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Post a message button is not enabled if we Select checkbox for any student in Grading page for a DW assignment.");
			}
			//TC row no. 166
			if(popup1.get(0).isDisplayed() == false)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Post a message pop up is not displayed if we click on Post a Message button.");
			}
			//TC row no. 167
			String messageHeader = driver.findElement(By.className("ls-ins-send-mail-header-label")).getText();
			if(!messageHeader.contains("Post a message"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Post a message header is absent in the popup to send message to a student for a DW assignment.");
			}
			String toField = driver.findElement(By.className("user-names-block-wrapper")).getText();
			if(!toField.contains("To"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("To field is absent in the popup to send message to a student for a DW assignment.");
			}
			String messageField = driver.findElement(By.className("message-block-wrapper")).getText();
			if(!messageField.contains("Message"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Message field is absent in the popup to send message to a student for a DW assignment.");
			}
			int sendButton =  driver.findElements(By.cssSelector("div[id='send-mail']")).size();
			if(sendButton == 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Send button is absent in the popup to send message to a student for a DW assignment.");
			}
			int closeIcon =  driver.findElements(By.id("dialog-close")).size();
			if(closeIcon == 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Close icon is absent in the popup to send message to a student for a DW assignment.");
			}
			//TC row no. 168
			String studentName = driver.findElement(By.className("item-text")).getText();
			if(studentName == null)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("To field is not pre populated with selected student in the popup to send message to a student for a DW assignment.");
			}
			
			
			driver.findElement(By.className("email-message-content")).click();	//click on Message text field
			Thread.sleep(2000);
			String message = new RandomString().randomstring(5);
			driver.findElement(By.className("email-message-content")).sendKeys(message);
			driver.findElement(By.id("dialog-close")).click();	//click on cancel
			Thread.sleep(2000);
			//TC row no. 172
			List<WebElement> popup2 =  driver.findElements(By.cssSelector("div[id='sendMailDialog']"));
			if(popup2.size()!=0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Pop up for sending message to a student doesnt closes after we click on Cancel.");
			}
			
			
			new LoginUsingLTI().ltiLogin("148_1");//login as student
			new Navigator().NavigateTo("Course Stream");
			//TC row no. 173
			int messageDisplayed = driver.findElements(By.className("ls-link-span")).size();
			if(messageDisplayed != 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Message gets posted on corresponding student course stream after we click on cancel icon Send message pop-up.");
			}
			
			new LoginUsingLTI().ltiLogin("148");		//login as instructor
			new Navigator().NavigateTo("Assignments");	//navigate to Assignments
			driver.findElement(By.cssSelector("span[title='View Student Responses']")).click();	//click on View Student Responses
			driver.findElement(By.xpath(".//*[@id='idb-gradeBook-overview-test-title-wrapper']/div[8]/div[1]/span[2]/span[1]/img")).click();	//checkbox click
			Thread.sleep(2000);
			driver.findElement(By.className("ls-post-a-message")).click();	//click on post message
			Thread.sleep(2000);
			driver.findElement(By.className("closebutton")).click();
			Thread.sleep(2000);
			//TC row no. 169
			int studentName1 = driver.findElements(By.className("item-text")).size();
			if(studentName1 != 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Unable to remove the selected student from To field in the popup to send message to a student for a DW assignment.");
			}
			driver.findElement(By.className("maininput")).sendKeys(shareWithInitialString);
			Thread.sleep(3000);
			boolean sharefound = false;
			List<WebElement> suggestname;
				 suggestname = driver.findElements(By.xpath("//*[starts-with(@rel, 'uid_')]"));
			   for (WebElement answerchoice: suggestname)
			   {
			    if(answerchoice.getText().trim().equals(shareName))
			    {
			    	((JavascriptExecutor) driver).executeScript("arguments[0].click();", answerchoice);
			        answerchoice.click();	
			        sharefound = true;
			        break;
			    }
			   }
			 //TC row no. 170
			if(sharefound == false)
				Assert.fail("User is unable to select other student name in to field");
			driver.findElement(By.className("email-message-content")).click();	//click on Message text field
			Thread.sleep(2000);
			String message1 = new RandomString().randomstring(5);
			driver.findElement(By.className("email-message-content")).sendKeys(message1);
			driver.findElement(By.id("send-mail")).click();	//click on cancel
			Thread.sleep(2000);
			List<WebElement> popup3 =  driver.findElements(By.cssSelector("div[id='sendMailDialog']"));
			//TC row no. 174
			if(popup3.size() != 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Pop up for sending message to a student doesnt closes after we click on Cancel.");
			}
			
			new LoginUsingLTI().ltiLogin("148_2");//login as student
			new Navigator().NavigateTo("Course Stream");
			//TC row no. 175
			String messageDisplayed1 = driver.findElement(By.className("ls-link-span")).getText();
			if(!messageDisplayed1.contains(message1))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Message is not posted on corresponding student course stream after we click on send button of Send message pop-up.");
			}
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase tableListingRowsForEachStudentinGradingPage in class TableListingRowsForEachStudentinGradingPage.",e);
		}
	}
}
