package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT10.R14;

import java.util.List;

import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.TabClose;

public class InstructorAbleToGradeManuallyGradableQuestions extends Driver{
	@Test(priority = 1, enabled = true)	
public void instructorAbleToGradeManuallyGradableQuestions()
{
	try
	{
		String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", "2050");
		String grade1 = ReadTestData.readDataByTagName("", "grade", "20504");
		String grade2 = ReadTestData.readDataByTagName("", "grade", "20505");
		new Assignment().create(2050);
		new Assignment().addQuestions(2050, "qtn-type-true-false-img", assessmentname);
		new Assignment().addQuestions(2050, "qtn-type-true-false-img", assessmentname);
		new Assignment().addQuestions(2050, "qtn-type-true-false-img", assessmentname);
		new Assignment().addQuestions(2050, "essay", assessmentname);
		/*DBConnect.Connect();
		DBConnect.st.executeUpdate("UPDATE t_question_set_item set auto_grade = 0 where question_set_id in (select id from t_question_set where name = '"+questionset+"') and position = 0 ;");
*/
		new LoginUsingLTI().ltiLogin("20501"); //create student with id 20501student
		new LoginUsingLTI().ltiLogin("20502"); //create student with id 20502student
		new LoginUsingLTI().ltiLogin("20503"); //create student with id 20503student
		new LoginUsingLTI().ltiLogin("2050"); //login as instructor
		new Assignment().assignToStudent(20501);
		new LoginUsingLTI().ltiLogin("2050");  //login as instructor
		new Assignment().updateAssignment(20502, true);
		new LoginUsingLTI().ltiLogin("2050"); //login as instructor
		new Assignment().updateAssignment(20503, true);
		new LoginUsingLTI().ltiLogin("20501"); //login as student with id 20501student
		new Assignment().submitAssignmentAsStudent(20501);
		new LoginUsingLTI().ltiLogin("20502");  //login as student with id 20502student
		new Assignment().submitAssignmentAsStudent(20502);

		new LoginUsingLTI().ltiLogin("2050");  //login as instructor
		new Assignment().clickViewResponse(assessmentname);
		String totalmarks_firststudent = driver.findElement(By.cssSelector("div[class='idb-gradebook-content-coloumn-total-score idb-gradebook-content-total']")).getText();
        if(!totalmarks_firststudent.equals("4.0")) Assert.fail("Total marks for first student after attempting 5 questions is not 4, out of which one question is manually gradable.");

		int tickMark = driver.findElements(By.className("idb-question-manually-graded")).size();
		if(tickMark == 0)
			Assert.fail("Checkmark is not displayed where user has attempted a manually gradable question but the instructor has not marked the question");

		//checking full mark for auto graded questions
		String fullMark = driver.findElement(By.cssSelector("span[class='idb-gradebook-question-content idb-question-fully-scored ']")).getText();
        if(!fullMark.equals("1.0"))
			Assert.fail("The marks is not displayed for auto graded question just after submission in Student Response page.");
		
		new LoginUsingLTI().ltiLogin("2050");
		new Assignment().provideGradeToStudentForMultipleQuestions(20501);
		new LoginUsingLTI().ltiLogin("2050");
		new Assignment().provideGradeToStudentForMultipleQuestions(20502); 
		
		//check for tick mark for manually graded questions
		int tickMark1 = driver.findElements(By.className("idb-question-manually-graded")).size();
		if(tickMark1 != 0)
			Assert.fail("Checkmark is not displayed where user has attempted a manually gradable question but the instructor has not marked the question");
		
		//Check the entered mark's color
		String color = driver.findElement(By.cssSelector("span[class='idb-gradebook-question-content idb-question-partial-scored']")).getCssValue("color");
		String markcolor = null;
		if(color.length()>10)
		     {
		       String colorx= color.substring(5, 8);
	           String colory= color.substring(10,13);
	           String colorz = color.substring(15, 17);
		       markcolor = String.format("#%02x%02x%02x", Integer.parseInt(colorx), Integer.parseInt(colory), Integer.parseInt(colorz));
		     }
		if(!markcolor.equalsIgnoreCase("#EA9B3D"))
				Assert.fail("Entered Mark is not in supporting color");

		//check for '-' symbol for not attempted questions
		List<WebElement> allSkippedQs = driver.findElements(By.cssSelector("span[class='idb-question-skipped']"));
		//if(!allSkippedQs.get(0).equals("rgba(133, 133, 133, 1)"))
		if(!allSkippedQs.get(0).getCssValue("background-color").equals("rgba(133, 133, 133, 1)"))
				Assert.fail("'-' symbol is not displayed for not attempted questions");
		
		//check for '-' symbol for the questions that the student has not viewed at all.(as the element @ index 2 & 3 are not viewed
		if(!allSkippedQs.get(1).getCssValue("background-color").equals("rgba(133, 133, 133, 1)"))
			Assert.fail("'-' symbol is not displayed for the questions that the student has not viewed at all.");
		
		//enter invalid grade(negative value)
		new LoginUsingLTI().ltiLogin("2050");
		new Assignment().provideGradeToStudentForMultipleQuestions(20504);
		int invalidGrade = driver.findElements(By.cssSelector("input[class='idb-grade-points invalid-value']")).size();
		if(invalidGrade == 0)
			Assert.fail("The instructor is able to enter negative values for a particular question.");
		
		//enter invalid grade(greater value)
		new LoginUsingLTI().ltiLogin("2050");
		new Assignment().provideGradeToStudentForMultipleQuestions(20505);
		int invalidGrade1 = driver.findElements(By.cssSelector("input[class='idb-grade-points invalid-value']")).size();
		if(invalidGrade1 == 0)
			Assert.fail("The instructor is able to enter marks greater than the marks allowed .");
		//check the 'View Response' Link on a question
		new LoginUsingLTI().ltiLogin("2050");
		new Assignment().clickViewResponse(assessmentname);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("div[class='idb-gradebook-content-coloumn']")));

		//Verify response page
		String assignmentName = driver.findElement(By.id("assignment-name")).getText();
		if(!assignmentName.equals(assessmentname))
			Assert.fail("Assignment name is not present in the Header of the Response page.");
		
		String studentDetails = driver.findElement(By.id("student-details")).getText();
		if(!studentDetails.contains("Student : "))
			Assert.fail("Student name is not present in the Response page.");
		
		String questionDetails = driver.findElement(By.id("question-raw-content-preview")).getText();
		if(questionDetails.length() == 0)
			Assert.fail("Question is not present in the Response page.");
		
		String score = driver.findElement(By.id("view-user-question-performance-score-box")).getAttribute("value");
        if(!score.equals("0.7"))
			Assert.fail("Grades given is not shown in the corner of the tab.");
		
		
		String teacherFeedback = driver.findElement(By.className("response-and-feedback-block-header")).getText();
		if(!teacherFeedback.equals("Teacher Feedback"))
			Assert.fail("'Teacher Feedback' label is absent in the resonse page.");
		
		int size1 = driver.findElements(By.id("view-user-question-performance-feedback-box")).size();
		if(size1 == 0)
			Assert.fail("Text box for giving feedback is present.");
		
		//Try entering negative numbers for marks  in the responses page.
		driver.findElement(By.id("view-user-question-performance-score-box")).click();
		driver.findElement(By.id("view-user-question-performance-score-box")).clear();
		driver.findElement(By.id("view-user-question-performance-score-box")).sendKeys(""+grade1+"");
		driver.findElement(By.id("view-user-question-performance")).click();
		Thread.sleep(3000);
		String wrongGrade = driver.findElement(By.id("view-user-question-performance-score-box")).getAttribute("style");
		if(!wrongGrade.equals("border: 1px solid red;"))
			Assert.fail("The instructor is able to enter negative values for a particular question in the response page.");
		//Try entering grater numbers for marks  in the responses page.
		driver.findElement(By.id("view-user-question-performance-score-box")).click();
		driver.findElement(By.id("view-user-question-performance-score-box")).clear();
		driver.findElement(By.id("view-user-question-performance-score-box")).sendKeys(""+grade2+"");
		String wrongGrade1 = driver.findElement(By.id("view-user-question-performance-score-box")).getAttribute("style");
		driver.findElement(By.id("view-user-question-performance")).click();
		Thread.sleep(3000);
		if(!wrongGrade1.equals("border: 1px solid red;"))
			Assert.fail("The instructor is able to enter numbers greater than the marks allowed for that particular question in the response page.");
		
		//Edit the mark in the responses page(for 1st Question)
		driver.findElement(By.id("view-user-question-performance-score-box")).click();
		driver.findElement(By.id("view-user-question-performance-score-box")).clear();
		driver.findElement(By.id("view-user-question-performance-score-box")).sendKeys("0.1");
		driver.findElement(By.cssSelector("span.view-user-question-performance-save-btn")).click();
		Thread.sleep(3000);
		
		//click on > button
		driver.findElement(By.id("next-question-performance-view")).click();
		Thread.sleep(3000);
		String questionIndex = driver.findElement(By.id("current-question-Index")).getText();
		if(!questionIndex.contains("2 of"))
				Assert.fail("Next question response page is not opened on clicking > button.");
		
		//click on < button
		driver.findElement(By.id("prev-question-performance-view")).click();
		Thread.sleep(3000);
		String questionIndex1 = driver.findElement(By.id("current-question-Index")).getText();
		if(!questionIndex1.contains("1 of"))
				Assert.fail("Previous question response page is not opened on clicking < button.");
		
		//check whether the edited mark is present or not
		String marks = driver.findElement(By.id("view-user-question-performance-score-box")).getAttribute("value");
		if(!marks.equals("0.1"))
				Assert.fail("The entered marks is not present in the marks edit box.");

		new TabClose().tabClose(2); //close the response page
		//refresh the page
		driver.findElement(By.cssSelector("#ls-gradebook-refresh-icon > span.ins-assignment-button-sprite.instructor-assignment-refresh")).click();
		Thread.sleep(3000);
		List<WebElement> allEditedGrade = driver.findElements(By.cssSelector("span[class='idb-gradebook-question-content idb-question-partial-scored ']"));
		String editedGrade = allEditedGrade.get(0).getText();
		if(!editedGrade.equals("0.1"))
			Assert.fail("The edited marks is not displayed for that particular question in the assignment responses page.");
		
		//again click on 'View Response Link'
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("div[class='idb-gradebook-content-coloumn']")));
		
		//Enter feedback
	    String feedbacktext = new RandomString().randomstring(5);
	    driver.findElement(By.id("view-user-question-performance-feedback-box")).clear();
		driver.findElement(By.id("view-user-question-performance-feedback-box")).sendKeys(feedbacktext);
		driver.findElement(By.cssSelector("span.view-user-question-performance-save-btn")).click();
		Thread.sleep(3000);
		new TabClose().tabClose(2); //close the response page
		driver.findElement(By.cssSelector("#ls-gradebook-refresh-icon > span.ins-assignment-button-sprite.instructor-assignment-refresh")).click();
		//check for comment icon
		WebElement WE = driver.findElement(By.cssSelector("span[class='idb-question-feedback-icon']"));
		String feedbackIcon = (String) ((JavascriptExecutor)driver).executeScript("return arguments[0].innerHTML",WE);
		if(!feedbackIcon.contains("feedback-notification-icon.png"))
			Assert.fail("The 'Comments' icon is not added beside the grades.");
		
		//click on Comment icon
		//driver.findElement(By.cssSelector("span[class='idb-question-feedback-icon']")).click();
		
		//again click on 'View Response Link'
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("div[class='idb-gradebook-content-coloumn']")));

		String textPresent = driver.findElement(By.id("view-user-question-performance-feedback-box")).getText();
		if(!textPresent.equals(feedbacktext))
			Assert.fail("The feedback entered earlier is not present.");
		
		//click > button then < button
		driver.findElement(By.id("next-question-performance-view")).click();
		Thread.sleep(3000);
	    driver.findElement(By.id("prev-question-performance-view")).click();
	    Thread.sleep(3000);
	    String textPresent1 = driver.findElement(By.id("view-user-question-performance-feedback-box")).getText();
		if(!textPresent1.equals(feedbacktext))
			Assert.fail("The feedback entered earlier is not present when we return back on clicking < icon.");
		
		new TabClose().tabClose(2); //close the response page
		//Check the comment icon again 
		WebElement WE1 = driver.findElement(By.cssSelector("span[class='idb-question-feedback-icon']"));
		String commentIcon = (String) ((JavascriptExecutor)driver).executeScript("return arguments[0].innerHTML",WE1);
		if(!commentIcon.contains("feedback-notification-icon.png"))
			Assert.fail("The 'Comments' icon is not added beside the grades.");
		
		//click on comment icon again
		//driver.findElement(By.className("idb-question-feedback-icon")).click();
		
		//again click on 'View Response Link'
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("div[class='idb-gradebook-content-coloumn']")));
		String textPresent2 = driver.findElement(By.id("view-user-question-performance-feedback-box")).getText();
		if(!textPresent2.equals(feedbacktext))
			Assert.fail("The feedback entered earlier is not present.");
		
	}
	catch(Exception e)
	{
		Assert.fail("Exception in instructorAbleToGradeManuallyGradableQuestions in class InstructorAbleToGradeManuallyGradableQuestions", e);
	}
}
	@Test(priority = 2, enabled = true)	
public void verifyReleaseGradesButton()
{
	try
	{
		String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", "2113");
		new Assignment().create(2113);
		new Assignment().addQuestions(2113, "qtn-type-true-false-img", assessmentname);

		new LoginUsingLTI().ltiLogin("21131");  //create student with id 21131student
		new LoginUsingLTI().ltiLogin("2113"); //login as instructor
		new Assignment().assignToStudent(21131);

		new LoginUsingLTI().ltiLogin("21131"); //login as student with id 21131student
		new Assignment().submitAssignmentAsStudent(21131);

		new LoginUsingLTI().ltiLogin("2113");
		new Assignment().provideGradeToStudentForMultipleQuestions(21131);
		
		String releaseGradeColor = driver.findElement(By.cssSelector("div[class='idb-gradeBook-grades-section-release idb-gradeBook-release-grades-section idb-gradeBook-grades-release']")).getCssValue("background-image");
		if(!releaseGradeColor.equals("linear-gradient(rgb(129, 194, 98), rgb(98, 175, 75))"))
			Assert.fail("The color of the 'Release Grade For all' button is not green.");
		
		//click on 'Release Grade For all'
		driver.findElement(By.cssSelector("div[class='idb-gradeBook-grades-section-release idb-gradeBook-release-grades-section idb-gradeBook-grades-release']")).click();
		Thread.sleep(3000);
	
		String gradeReleasedText = driver.findElement(By.cssSelector("div[class='idb-gradeBook-grades-section-release idb-gradeBook-release-grades-section idb-gradeBook-grades-released']")).getText();
		if(!gradeReleasedText.equals("Grades Released"))
			Assert.fail("On clicking 'Release Grade For all' button doesnt change to 'Grades Released' button.");
		
		//count the 'Graded' status box count
		int count = new Assignment().statusBoxCount(2113, "Graded");
		if(count != 1)  //here only 1 student is present
			Assert.fail("The total student count present in the 'Graded' tile is not correct.");
		
		
		new Assignment().clickViewResponse(assessmentname);
		//check for bar graph in assignment response page
		int graph = driver.findElements(By.className("ls-assignment-performance-graph")).size();
		if(graph == 0)
			Assert.fail("The grades bar graph is not displayed assignment responses page.");
	
		new LoginUsingLTI().ltiLogin("21131"); //login as student with id 21131student
		new Navigator().NavigateTo("Assignments"); 
		new Assignment().clickonAssignment(assessmentname);
		
	}
	catch(Exception e)
	{
		Assert.fail("Exception in verifyReleaseGradesButton in class InstructorAbleToGradeManuallyGradableQuestions", e);
	}
}

}
