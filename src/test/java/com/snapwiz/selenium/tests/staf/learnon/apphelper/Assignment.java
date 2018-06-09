package com.snapwiz.selenium.tests.staf.learnon.apphelper;

import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;
import com.snapwiz.selenium.tests.staf.learnon.apphelper.KeysSend;
import com.snapwiz.selenium.tests.staf.learnon.pageFactory.assignments.CurrentAssignments;
import com.snapwiz.selenium.tests.staf.learnon.uihelper.TextSend;
import com.snapwiz.selenium.tests.staf.learnon.uihelper.UIElement;
import com.snapwiz.selenium.tests.staf.learnon.Config;
import com.snapwiz.selenium.tests.staf.learnon.Driver;
import com.snapwiz.selenium.tests.staf.learnon.ReadTestData;
import com.snapwiz.selenium.tests.staf.learnon.apphelper.*;
import com.snapwiz.selenium.tests.staf.learnon.apphelper.DirectLogin;
import com.snapwiz.selenium.tests.staf.learnon.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learnon.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.learnon.apphelper.RandomString;
import com.snapwiz.selenium.tests.staf.learnon.apphelper.TOCShow;
import com.snapwiz.selenium.tests.staf.learnon.uihelper.Click;
import com.snapwiz.selenium.tests.staf.learnon.uihelper.ComboBox;
import com.snapwiz.selenium.tests.staf.learnon.uihelper.ShareWith;
import com.snapwiz.selenium.tests.staf.learnon.uihelper.TextFetch;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.HasInputDevices;
import org.openqa.selenium.interactions.Mouse;
import org.openqa.selenium.internal.Locatable;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Assignment {
	
	public void create(int dataIndex)
	{
		try
		{

        String course = "";
        String course_type = ReadTestData.readDataByTagName("", "course_type", Integer.toString(dataIndex));
        String course_name = ReadTestData.readDataByTagName("", "course_name", Integer.toString(dataIndex));
        String practice_type = ReadTestData.readDataByTagName("", "practice_type", Integer.toString(dataIndex));
            course = course_name;
            if(course_name == null) {
                if (course_type == null || course_type.equals(""))
                    course = Config.course;

                else if (course_type.equals("ls"))
                    course = Config.lscourse;
            }
		String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
		String questionset = ReadTestData.readDataByTagName("", "questionset", Integer.toString(dataIndex));
		String questiontype = ReadTestData.readDataByTagName("", "questiontype", Integer.toString(dataIndex));
		String chapterName = ReadTestData.readDataByTagName("", "chapterName", Integer.toString(dataIndex));
		String courselevel = ReadTestData.readDataByTagName("", "courselevel", Integer.toString(dataIndex));
		String reservforassignment = ReadTestData.readDataByTagName("", "reservforassignment", Integer.toString(dataIndex));
		new com.snapwiz.selenium.tests.staf.learnon.apphelper.DirectLogin().CMSLogin();
		String title=Driver.driver.getTitle();
		if(title.equals("Course Content Details"))
			{
			 Driver.driver.findElement(By.cssSelector("img[alt=\""+course+"\"]")).click();
			 if(chapterName == null)
			 {
				 new Click().clickBycssselector("div.course-chapter-label.node");
			 }
			 else if(courselevel!=null)
			 {
				 new Click().clickBycssselector("div[class='course-label node']");
			 }
			 else
			 {
				 List <WebElement> allChapters = Driver.driver.findElements(By.xpath("//*[starts-with(@class, 'course-chapter-label')]"));
				 for(WebElement chapters: allChapters)
				 {
					 if(chapters.getText().contains(chapterName))
					 {
						 ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", chapters);
						 Thread.sleep(4000);
						 break;
					 }
					 
				 }
				 
			 }
			 Driver.driver.findElement(By.cssSelector("div.create-practice")).click();
                if (practice_type == null) {
                   new Click().clickByclassname("create-regular-assessment-popup-item"); //click on the create regular assessment
                } else {
                   new Click().clickByclassname("create-file-assessment-popup-item");//click on the create file assessment
                }
                new Click().clickbylinkText("Adaptive Component Diagnostic");
                new Click().clickbylinkText(questiontype);
                int popup = 1;
                try {
                    WebDriverWait wait = new WebDriverWait(Driver.driver, 3);
                    wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div[class='cms-notification-message-body']")));

                } catch (Exception e) {
                    popup = 0;
                }
                //int popup = Driver.driver.findElements(By.cssSelector("div[class='cms-notification-message-body']")).size();
                if (popup == 0) {
                    Driver.driver.findElement(By.id("assessmentName")).click();
                    Driver.driver.findElement(By.id("assessmentName")).clear();
                    Driver.driver.findElement(By.id("assessmentName")).sendKeys(assessmentname);
                    Driver.driver.findElement(By.id("questionSetName")).clear();
                    Driver.driver.findElement(By.id("questionSetName")).sendKeys(questionset);

                    if (reservforassignment == null) {
                        Driver.driver.findElement(By.xpath("//*[@id='instructor-only-check-box-div']/label")).click(); //checking 'Enable for intructor only'
                    }

                    new QuestionCreate().trueFalseQuestions(dataIndex);

			 
			/* 
			 
			
			 Driver.driver.findElement(By.id("qtn-type-true-false-img")).click();
			 
			
			 Driver.driver.findElement(By.id("question-raw-content")).click();		 
		
			 
			 Driver.driver.switchTo().frame("iframe-question-edit").findElement(By.xpath("/html/body")).sendKeys(questiontext);
			 Driver.driver.switchTo().defaultContent();
			 Actions action = new Actions(Driver.driver);
		        WebElement we = Driver.driver.findElement(By.id("choice1"));
		        action.moveToElement(we).build().perform();	
		       ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.xpath("//img[@title='Set it as Correct Answer']")));
			 //Driver.driver.findElement(By.xpath("//img[@title='Set it as Correct Answer']")).click();
			 Thread.sleep(3000);
			 new ComboBox().selectValue(3, questiontype);
			 Thread.sleep(3000);
			 int popup = Driver.driver.findElements(By.cssSelector("div[class='cms-notification-message-body']")).size();
			 if(popup == 0)
			 {
			 if(useWriteBoard != null)
			 {
				 Driver.driver.findElement(By.id("display-write-board-checkbox")).click();//check the use writeboard check box
				 Thread.sleep(2000);
			 }
			 if(difficultylevel != null)
			 {
				 new ComboBox().selectValue(7, difficultylevel);
			 }
			/* if(learningobjective != null)
			 {
				 Driver.driver.findElement(By.id("learing-objectives-span")).click();
				 new Click().clickByid("link-add-learning-objectives");
				 Driver.driver.findElement(By.xpath("html/body/div[8]/div/div[2]/div[1]/div[3]/div[2]/div/div[1]/div[2]/div/div/div[1]/div/div[3]/div[1]/div/div[1]/label")).click();
				 Driver.driver.findElement(By.cssSelector("span.add-collection-to-subtopic")).click();
				 //Driver.driver.findElement(By.cssSelector("span.cancel-collection")).click();
				
			 }
			 //enter Hint
			 if(hint != null)
			 {
				 Driver.driver.findElement(By.cssSelector("#hint > #question-raw-content")).click();
				 Thread.sleep(3000);	
				 Driver.driver.switchTo().frame("iframe-hint").findElement(By.xpath("/html/body")).sendKeys(hint);//enter hint text
				 Driver.driver.switchTo().defaultContent();
				 Thread.sleep(3000);
			 }
			 //enter solution text
			 if(solution != null)
			 {
			 Driver.driver.findElement(By.cssSelector("#explanation > #question-raw-content")).click();
			 Driver.driver.switchTo().frame("iframe-explanation").findElement(By.xpath("/html/body")).sendKeys(solution);			 	
			 Driver.driver.switchTo().defaultContent();
			 Thread.sleep(3000);	
			 }*/
			 
			 /*new ComboBox().selectValue(4, "Publish");
			 
			 Driver.driver.findElement(By.id("saveQuestionDetails1")).click();
			 }//if condition for popup ends here
			 Driver.driver.quit();
			 Driver.startDriver();*/
                }
			 }
			 
		else
		{
			Assert.fail("CMS login failed");
		}
		}
		catch(Exception e)
		{
			Assert.fail("Exception in create in Apphelper create in class AssignmentCreate",e);
		}
	}
	
	//assignment create for LS course only
	
	public void createassignmentLS(int dataIndex)
	{
		try
		{
		String course = Config.lscourse;
		String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
		String questionset = ReadTestData.readDataByTagName("", "questionset", Integer.toString(dataIndex));
		String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));
		String questiontype = ReadTestData.readDataByTagName("", "questiontype", Integer.toString(dataIndex));
		String chapterName = ReadTestData.readDataByTagName("", "chapterName", Integer.toString(dataIndex));
		String difficultylevel = ReadTestData.readDataByTagName("", "difficultylevel", Integer.toString(dataIndex));
		String learningobjective = ReadTestData.readDataByTagName("", "learningobjective", Integer.toString(dataIndex));
		new com.snapwiz.selenium.tests.staf.learnon.apphelper.DirectLogin().CMSLogin();
		String title=Driver.driver.getTitle();
		if(title.equals("Course Content Details"))
			{
			 Driver.driver.findElement(By.cssSelector("img[alt=\""+course+"\"]")).click();
			 if(chapterName == null)
			 {
			 Driver.driver.findElement(By.cssSelector("div.course-chapter-label.node")).click();
			 }
			 else
			 {
				 List <WebElement> allChapters = Driver.driver.findElements(By.xpath("//*[starts-with(@class, 'course-chapter-label')]"));
				 for(WebElement chapters: allChapters)
				 {
					 if(chapters.getText().equals(chapterName))
					 {
						 chapters.click();
						 break;
					 }
					 
				 }
				 
			 }
			 Driver.driver.findElement(By.cssSelector("div.create-practice")).click();
			 Driver.driver.findElement(By.id("assessmentName")).click();
			 Driver.driver.findElement(By.id("assessmentName")).clear();
			 Driver.driver.findElement(By.id("assessmentName")).sendKeys(assessmentname);
			 Driver.driver.findElement(By.id("questionSetName")).clear();
			 Driver.driver.findElement(By.id("questionSetName")).sendKeys(questionset);
			
			 Driver.driver.findElement(By.id("qtn-type-true-false-img")).click();
			 
			
			 Driver.driver.findElement(By.id("question-raw-content")).click();		 
		
			 
			 Driver.driver.switchTo().frame("iframe-question-edit").findElement(By.xpath("/html/body")).sendKeys(questiontext);
			 Driver.driver.switchTo().defaultContent();
			 Actions action = new Actions(Driver.driver);
		        WebElement we = Driver.driver.findElement(By.id("choice1"));
		        action.moveToElement(we).build().perform();	
		       ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.xpath("//img[@title='Set it as Correct Answer']")));
			 //Driver.driver.findElement(By.xpath("//img[@title='Set it as Correct Answer']")).click();
			 Thread.sleep(3000);
			 new ComboBox().selectValue(3, questiontype);
			 int popup = Driver.driver.findElements(By.cssSelector("div[class='cms-notification-message-body']")).size();
			 if(popup == 0)
			 {
			 if(difficultylevel != null)
			 {
				 new ComboBox().selectValue(7, difficultylevel);
			 }
			 if(learningobjective != null)
			 {
				 Driver.driver.findElement(By.id("learing-objectives-span")).click();
				 Driver.driver.findElement(By.xpath("html/body/div[8]/div/div[2]/div/div[1]/div[3]/div/div/div[4]/div/div[3]/div[1]/div/div[1]/label")).click();
				 Driver.driver.findElement(By.cssSelector("span.add-collection-to-subtopic")).click();
				 Driver.driver.findElement(By.cssSelector("span.cancel-collection")).click();
				
			 }
			 Driver.driver.findElement(By.xpath("//*[@id='instructor-only-check-box-div']/label")).click(); //checking 'Enable for intructor only'
			 new ComboBox().selectValue(4, "Publish");
			 
			 Driver.driver.findElement(By.id("saveQuestionDetails1")).click();
			 }//if condition for popup ends here
			 Driver.driver.quit();
			 Driver.startDriver();
			
			}
		else
		{
			Assert.fail("CMS login failed");
		}
		}
		catch(Exception e)
		{
			Assert.fail("Exception in create in Apphelper create in class AssignmentCreate",e);
		}
	}
	//create a assignment at chapter level where instructor false
	
	//create assignment at topic level instructor value false
	public void createassignmentontopiclevel(int dataIndex,int topic,int chapter)
	{
		try
		{
		String course = Config.course;
		String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
		String questionset = ReadTestData.readDataByTagName("", "questionset", Integer.toString(dataIndex));
		String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));
		String questiontype = ReadTestData.readDataByTagName("", "questiontype", Integer.toString(dataIndex));
		String chapterName = ReadTestData.readDataByTagName("", "chapterName", Integer.toString(dataIndex));
		String difficultylevel = ReadTestData.readDataByTagName("", "difficultylevel", Integer.toString(dataIndex));
		String learningobjective = ReadTestData.readDataByTagName("", "learningobjective", Integer.toString(dataIndex));
		new com.snapwiz.selenium.tests.staf.learnon.apphelper.DirectLogin().CMSLogin();
		String title=Driver.driver.getTitle();
		if(title.equals("Course Content Details"))
			{
			 Driver.driver.findElement(By.cssSelector("img[alt=\""+course+"\"]")).click();
			 List<WebElement> allchapter=(List<WebElement>) ((JavascriptExecutor)Driver.driver).executeScript("return arguments[0]",Driver.driver.findElements(By.cssSelector("div[class='expand-chapter-tree expand']")));
			 int index1=0;
			 for(WebElement elements:allchapter)
			 {
				 
				 if(index1==chapter)
				 {
					 elements.click();
					 break;
				 }
				 index1++;
			 }
			
			 List<WebElement> alltopic=(List<WebElement>) ((JavascriptExecutor)Driver.driver).executeScript("return arguments[0]",Driver.driver.findElements(By.cssSelector("div[class='course-topic-label node']")));
			 int index=0;
			 for(WebElement element:alltopic)
			 {
				
				 if(index==topic)
				 {
					 element.click();
					 break;
				 }
				 index++;
			 }
			 Thread.sleep(3000);
			 Driver.driver.findElement(By.cssSelector("div.create-practice")).click();
			 Driver.driver.findElement(By.id("assessmentName")).click();
			 Driver.driver.findElement(By.id("assessmentName")).clear();
			 Driver.driver.findElement(By.id("assessmentName")).sendKeys(assessmentname);
			 Driver.driver.findElement(By.id("questionSetName")).clear();
			 Driver.driver.findElement(By.id("questionSetName")).sendKeys(questionset);
			
			 Driver.driver.findElement(By.id("qtn-type-true-false-img")).click();
			 
			
			 Driver.driver.findElement(By.id("question-raw-content")).click();		 
		
			 
			 Driver.driver.switchTo().frame("iframe-question-edit").findElement(By.xpath("/html/body")).sendKeys(questiontext);
			 Driver.driver.switchTo().defaultContent();
			 
			 Actions action = new Actions(Driver.driver);
		        WebElement we = Driver.driver.findElement(By.id("choice1"));
		        action.moveToElement(we).build().perform();			 
			 Driver.driver.findElement(By.xpath("//img[@title='Set it as Correct Answer']")).click();
			 Thread.sleep(3000);
			 new ComboBox().selectValue(3, questiontype);
			 int popup = Driver.driver.findElements(By.cssSelector("div[class='cms-notification-message-body']")).size();
			 if(popup == 0)
			 {
			 if(difficultylevel != null)
			 {
				 new ComboBox().selectValue(7, difficultylevel);
			 }
			 if(learningobjective != null)
			 {
				 Driver.driver.findElement(By.id("learing-objectives-span")).click();
				 Driver.driver.findElement(By.xpath("html/body/div[8]/div/div[2]/div/div[1]/div[3]/div/div/div[4]/div/div[3]/div[1]/div/div[1]/label")).click();
				 Driver.driver.findElement(By.cssSelector("span.add-collection-to-subtopic")).click();
				 Driver.driver.findElement(By.cssSelector("span.cancel-collection")).click();
				
			 }
			 Driver.driver.findElement(By.xpath("//*[@id='instructor-only-check-box-div']/label")).click(); //checking 'Enable for intructor only'
			 new ComboBox().selectValue(4, "Publish");
			 
			 Driver.driver.findElement(By.id("saveQuestionDetails1")).click();
			 }//if condition for popup ends here
			 Driver.driver.quit();
			 Driver.startDriver();
			
			}
		}
		catch(Exception e)
		{
			Assert.fail("Exception in create in Apphelper createassignmentontopiclevel in class AssignmentCreate",e);
		}
		
	}
	//create assignment at subtopic level instructor value false
	public void createresourcesatsubtopiclevel(int dataIndex,int topictoexpand,int chapter,int subtopic)
	{
		try
		{
		String course = Config.course;
		String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
		String questionset = ReadTestData.readDataByTagName("", "questionset", Integer.toString(dataIndex));
		String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));
		String questiontype = ReadTestData.readDataByTagName("", "questiontype", Integer.toString(dataIndex));
		String chapterName = ReadTestData.readDataByTagName("", "chapterName", Integer.toString(dataIndex));
		String difficultylevel = ReadTestData.readDataByTagName("", "difficultylevel", Integer.toString(dataIndex));
		String learningobjective = ReadTestData.readDataByTagName("", "learningobjective", Integer.toString(dataIndex));
		new com.snapwiz.selenium.tests.staf.learnon.apphelper.DirectLogin().CMSLogin();
		String title=Driver.driver.getTitle();
		if(title.equals("Course Content Details"))
			{
			 Driver.driver.findElement(By.cssSelector("img[alt=\""+course+"\"]")).click();
			 List<WebElement> allchapter=(List<WebElement>) ((JavascriptExecutor)Driver.driver).executeScript("return arguments[0]",Driver.driver.findElements(By.cssSelector("div[class='expand-chapter-tree expand']")));
			 int index1=0;
			 for(WebElement elements:allchapter)
			 {
				 if(index1==chapter)
				 {
					 elements.click();
					 break;
				 }
				 index1++;
			 }
			 List<WebElement> alltopic=(List<WebElement>) ((JavascriptExecutor)Driver.driver).executeScript("return arguments[0]",Driver.driver.findElements(By.cssSelector("div[class='expand-topic-tree expand']")));
			 int index=0;
			 for(WebElement element:alltopic)
			 {
				
				 if(index==topictoexpand)
				 {
					 element.click();
					 break;
				 }
				 index++;
			 }
			 List<WebElement> allsubtopictopic=(List<WebElement>) ((JavascriptExecutor)Driver.driver).executeScript("return arguments[0]",Driver.driver.findElements(By.cssSelector("div[class='course-subtopic-label node']")));
			 int index2=0;
			 for(WebElement element1:allsubtopictopic)
			 {
			
				 if(index2==subtopic)
				 {
					 element1.click();
					 break;
				 }
				 index2++;
			 }
			 Thread.sleep(3000);
			 Driver.driver.findElement(By.cssSelector("div.create-practice")).click();
			 Driver.driver.findElement(By.id("assessmentName")).click();
			 Driver.driver.findElement(By.id("assessmentName")).clear();
			 Driver.driver.findElement(By.id("assessmentName")).sendKeys(assessmentname);
			 Driver.driver.findElement(By.id("questionSetName")).clear();
			 Driver.driver.findElement(By.id("questionSetName")).sendKeys(questionset);
			
			 Driver.driver.findElement(By.id("qtn-type-true-false-img")).click();
			 
			
			 Driver.driver.findElement(By.id("question-raw-content")).click();		 
		
			 
			 Driver.driver.switchTo().frame("iframe-question-edit").findElement(By.xpath("/html/body")).sendKeys(questiontext);
			 Driver.driver.switchTo().defaultContent();
			 
			 Actions action = new Actions(Driver.driver);
		        WebElement we = Driver.driver.findElement(By.id("choice1"));
		        action.moveToElement(we).build().perform();			 
			 Driver.driver.findElement(By.xpath("//img[@title='Set it as Correct Answer']")).click();
			 Thread.sleep(3000);
			 new ComboBox().selectValue(3, questiontype);
			 int popup = Driver.driver.findElements(By.cssSelector("div[class='cms-notification-message-body']")).size();
			 if(popup == 0)
			 {
			 if(difficultylevel != null)
			 {
				 new ComboBox().selectValue(7, difficultylevel);
			 }
			 if(learningobjective != null)
			 {
				 Driver.driver.findElement(By.id("learing-objectives-span")).click();
				 Driver.driver.findElement(By.xpath("html/body/div[8]/div/div[2]/div/div[1]/div[3]/div/div/div[4]/div/div[3]/div[1]/div/div[1]/label")).click();
				 Driver.driver.findElement(By.cssSelector("span.add-collection-to-subtopic")).click();
				 Driver.driver.findElement(By.cssSelector("span.cancel-collection")).click();
				
			 }
			 Driver.driver.findElement(By.xpath("//*[@id='instructor-only-check-box-div']/label")).click(); //checking 'Enable for intructor only'
			 new ComboBox().selectValue(4, "Publish");
			 
			 Driver.driver.findElement(By.id("saveQuestionDetails1")).click();
			 }//if condition for popup ends here
			 Driver.driver.quit();
			 Driver.startDriver();
			
			}
		}
		catch(Exception e)
		{
			Assert.fail("Exception in create in Apphelper createresourcesatsubtopiclevel in class AssignmentCreate",e);
		}
		
	}
	
	public void addQuestions(int dataIndex,String questionType,String assignmentname)
	{
		try
		{
        assignmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
		String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));
		String optiontext = ReadTestData.readDataByTagName("", "optiontext", Integer.toString(dataIndex));
		String answertext = ReadTestData.readDataByTagName("", "answertext", Integer.toString(dataIndex));
		String numerictext = ReadTestData.readDataByTagName("", "numerictext", Integer.toString(dataIndex));
		String unitoption = ReadTestData.readDataByTagName("", "unitoption", Integer.toString(dataIndex));
		String tolrence = ReadTestData.readDataByTagName("", "tolrence", Integer.toString(dataIndex));
		String maplenumeric = ReadTestData.readDataByTagName("", "maplenumeric", Integer.toString(dataIndex));
		String useWriteBoard = ReadTestData.readDataByTagName("", "useWriteBoard", Integer.toString(dataIndex));
		String chapterName = ReadTestData.readDataByTagName("", "chapterName", Integer.toString(dataIndex));
		String courselevel = ReadTestData.readDataByTagName("", "courselevel", Integer.toString(dataIndex));
		String learningobjective=ReadTestData.readDataByTagName("", "learningobjective", Integer.toString(dataIndex));
		String tlo=ReadTestData.readDataByTagName("", "tlo", Integer.toString(dataIndex));
		String course = "";
            String course_type = ReadTestData.readDataByTagName("", "course_type", Integer.toString(dataIndex));
            if(course_type == null || course_type.equals(""))
                course = Config.course;
            else if(course_type.equals("ls"))
                course = Config.lscourse;
		new com.snapwiz.selenium.tests.staf.learnon.apphelper.DirectLogin().CMSLogin();
		String title=Driver.driver.getTitle();
		if(title.equals("Course Content Details"))
			{
			Driver.driver.findElement(By.cssSelector("img[alt=\""+course+"\"]")).click();
			if(chapterName == null)			 
				new Click().clickBycssselector("div.course-chapter-label.node");
			 
			 else if(courselevel!=null)			 
				 new Click().clickBycssselector("div[class='course-label node']");
			 
			 else
			 {
				 List <WebElement> allChapters = Driver.driver.findElements(By.xpath("//*[starts-with(@class, 'course-chapter-label')]"));
				 for(WebElement chapters: allChapters)
				 {
					 if(chapters.getText().contains(chapterName))
					 {
						 ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", chapters);
						 Thread.sleep(4000);
						 break;
					 }

				 }

			 }
			boolean assignmentExists = false;
			List<WebElement> elements = Driver.driver.findElements(By.className("collection-assessment-name"));
			for(WebElement content : elements)
				{
					if(content.getText().trim().equals(assignmentname))
						{

						   Thread.sleep(3000);
						   ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", content);
						   assignmentExists = true;
						   break;
						}
				}
			if(assignmentExists == true) {
				new Click().clickByid("questionOptions");
				new Click().clickByid("addQuestion");
			}

			if (questionType.equals("truefalse")|| questionType.equals("qtn-type-true-false-img"))
                new QuestionCreate().trueFalseQuestions(dataIndex);
			
			if (questionType.equals("multiplechoice") || questionType.equals("qtn-multiple-choice-img"))
                new QuestionCreate().multipleChoice(dataIndex);

            if(questionType.equals("multipleselection")|| questionType.equals("qtn-multiple-selection-img"))
                    new QuestionCreate().multipleSelection(dataIndex);

            if(questionType.equals("textentry")||questionType.equals("qtn-text-entry-img"))
                    new QuestionCreate().textEntry(dataIndex);

            if(questionType.equals("textdropdown") || questionType.equals("qtn-text-entry-drop-down-img"))
                    new QuestionCreate().textDropDown(dataIndex);

            if(questionType.equals("numericentrywithunits")|| questionType.equals("qtn-text-entry-numeric-units-img"))
                    new QuestionCreate().numericEntryWithUnits(dataIndex);

            if(questionType.equals("advancednumeric"))
                    new QuestionCreate().advancedNumeric(dataIndex);

            if(questionType.equals("expressionevaluator"))
                    new QuestionCreate().expressionEvaluator(dataIndex);

            if(questionType.equals("essay")|| questionType.equals("qtn-essay-type"))
                    new QuestionCreate().essay(dataIndex);

            if(questionType.equals("writeboard")|| questionType.equals("qtn-writeboard-type-new"))
                    new QuestionCreate().writeBoard(dataIndex);

               /* //	Driver.driver.findElement(By.id("addQuestion")).click();
                Thread.sleep(2000);
                Driver.driver.findElement(By.id(questionType)).click();
                Thread.sleep(2000);


                if (questionType.equals("qtn-type-true-false-img")) //1.For true-false type question
                {
                    Driver.driver.findElement(By.id("question-raw-content")).click();
                    Driver.driver.switchTo().frame("iframe-question-edit").findElement(By.xpath("/html/body")).sendKeys(questiontext);
                    Driver.driver.switchTo().defaultContent();
                    //Setting choice 1 as correct answer
                    Actions action = new Actions(Driver.driver);
                    WebElement we = Driver.driver.findElement(By.id("choice1"));
                    action.moveToElement(we).build().perform();
                    ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.xpath("//img[@title='Set it as Correct Answer']")));
                }
                if (questionType.equals("qtn-multiple-choice-img")) //2. For multiple choice question.
                {
                    Driver.driver.findElement(By.id("question-raw-content")).click();
                    Driver.driver.switchTo().frame("iframe-question-edit").findElement(By.xpath("/html/body")).sendKeys(questiontext);
                    Driver.driver.switchTo().defaultContent();
                    //Adding choice 1
                    Driver.driver.findElement(By.id("choice1")).click();
                    Driver.driver.switchTo().frame("iframe-choice1").findElement(By.xpath("/html/body")).sendKeys(answertext);
                    Driver.driver.switchTo().defaultContent();
                    //Adding choice 2
                    Driver.driver.findElement(By.id("choice2")).click();
                    Driver.driver.switchTo().frame("iframe-choice2").findElement(By.xpath("/html/body")).sendKeys(answertext);
                    Driver.driver.switchTo().defaultContent();
                    //Adding choice 3
                    Driver.driver.findElement(By.id("choice3")).click();
                    Driver.driver.switchTo().frame("iframe-choice3").findElement(By.xpath("/html/body")).sendKeys(answertext);
                    Driver.driver.switchTo().defaultContent();
                    //Adding choice 4
                    Driver.driver.findElement(By.id("choice4")).click();
                    Driver.driver.switchTo().frame("iframe-choice4").findElement(By.xpath("/html/body")).sendKeys(answertext);
                    Driver.driver.switchTo().defaultContent();
                    //Setting choice 2 as correct answer
                    Actions action = new Actions(Driver.driver);
                    WebElement we = Driver.driver.findElement(By.id("choice2"));
                    action.moveToElement(we).build().perform();
                    Driver.driver.findElement(By.xpath("//img[@title='Set it as Correct Answer']")).click();
                }
                if (questionType.equals("qtn-multiple-selection-img"))  //3. For multiple sections
                {
                    Driver.driver.findElement(By.id("question-raw-content")).click();
                    Driver.driver.switchTo().frame("iframe-question-edit").findElement(By.xpath("/html/body")).sendKeys(questiontext);
                    Driver.driver.switchTo().defaultContent();
                    //Adding choice 1
                    Driver.driver.findElement(By.id("choice1")).click();
                    Driver.driver.switchTo().frame("iframe-choice1").findElement(By.xpath("/html/body")).sendKeys(answertext);
                    Driver.driver.switchTo().defaultContent();
                    //Adding choice 2
                    Driver.driver.findElement(By.id("choice2")).click();
                    Driver.driver.switchTo().frame("iframe-choice2").findElement(By.xpath("/html/body")).sendKeys(answertext);
                    Driver.driver.switchTo().defaultContent();
                    //Adding choice 3
                    Driver.driver.findElement(By.id("choice3")).click();
                    Driver.driver.switchTo().frame("iframe-choice3").findElement(By.xpath("/html/body")).sendKeys(answertext);
                    Driver.driver.switchTo().defaultContent();
                    //Adding choice 4
                    Driver.driver.findElement(By.id("choice4")).click();
                    Driver.driver.switchTo().frame("iframe-choice4").findElement(By.xpath("/html/body")).sendKeys(answertext);
                    Driver.driver.switchTo().defaultContent();
                    //Setting choice 2 as correct answer
                    Actions action1 = new Actions(Driver.driver);
                    WebElement we1 = Driver.driver.findElement(By.id("choice2"));
                    action1.moveToElement(we1).build().perform();
                    Driver.driver.findElement(By.xpath("//img[@title='Set it as Correct Answer']")).click();
                    //Setting choice 4 as correct answer
                    Actions action2 = new Actions(Driver.driver);
                    WebElement we2 = Driver.driver.findElement(By.id("choice4"));
                    action2.moveToElement(we2).build().perform();
                    Driver.driver.findElement(By.xpath("//img[@title='Set it as Correct Answer']")).click();
                }
                if (questionType.equals("qtn-text-entry-img")) // 4.For Text Entry type.
                {
                    Driver.driver.findElement(By.id("question-raw-content")).click();
                    Driver.driver.switchTo().frame("iframe-question-edit").findElement(By.xpath("/html/body")).sendKeys(questiontext);
                    Driver.driver.switchTo().defaultContent();
                    // Adding correct answer choice
                    Driver.driver.findElement(By.cssSelector("input[id='correct-ans-text']")).click();
                    Driver.driver.findElement(By.cssSelector("input[id='correct-ans-text']")).clear();
                    Driver.driver.findElement(By.cssSelector("input[id='correct-ans-text']")).sendKeys(answertext);
                    Thread.sleep(3000);
                    // Rest two boxes are filled automatically.
                    //Adding alternate answer choice.
                    Driver.driver.findElement(By.id("right-alt-container-1")).click();
                    Driver.driver.findElement(By.cssSelector("input[id='alternate-answer-text']")).click();
                    Driver.driver.findElement(By.cssSelector("input[id='alternate-answer-text']")).clear();
                    Driver.driver.findElement(By.cssSelector("input[id='alternate-answer-text']")).sendKeys(optiontext);

                    Driver.driver.findElement(By.id("done-button")).click(); // Accept answer
                    Thread.sleep(2000);
                }
                if (questionType.equals("qtn-text-entry-drop-down-img")) //5. For text entry drop down
                {
                    Driver.driver.findElement(By.id("question-raw-content")).click();
                    Driver.driver.switchTo().frame("iframe-question-edit").findElement(By.xpath("/html/body")).sendKeys(questiontext);
                    Driver.driver.switchTo().defaultContent();
                    // Adding Entry 1
                    Driver.driver.findElement(By.id("entry-0")).click();
                    Driver.driver.findElement(By.id("unit-name-edit-entry-0")).clear();
                    Driver.driver.findElement(By.id("unit-name-edit-entry-0")).sendKeys(optiontext);

                    // Accepting answer
                    WebElement menuitem = Driver.driver.findElement(By.id("entry-1"));
                    Locatable hoverItem = (Locatable) menuitem;
                    Mouse mouse = ((HasInputDevices) Driver.driver).getMouse();
                    mouse.mouseMove(hoverItem.getCoordinates());
                    List<WebElement> selectanswerticks = Driver.driver.findElements(By.className("mark-selected"));
                    selectanswerticks.get(1).click(); //select second option as correct answer


                    Thread.sleep(2000);
                    // Adding Entry 2
                    Actions action = new Actions(Driver.driver);
                    action.doubleClick(Driver.driver.findElement(By.id("entry-1")));
                    action.perform();
                    Driver.driver.findElement(By.id("unit-name-edit-entry-1")).clear();
                    Driver.driver.findElement(By.id("unit-name-edit-entry-1")).sendKeys(optiontext);


                    // Adding Entry 3
                    action.doubleClick(Driver.driver.findElement(By.id("entry-2")));
                    action.perform();
                    Driver.driver.findElement(By.id("unit-name-edit-entry-2")).clear();
                    Driver.driver.findElement(By.id("unit-name-edit-entry-2")).sendKeys(optiontext);

                    //clicking on add more entry
                    Driver.driver.findElement(By.id("add-new-entry")).click();
                    //Adding entry 4
                    action.doubleClick(Driver.driver.findElement(By.id("entry-3")));
                    Driver.driver.findElement(By.id("unit-name-edit-entry-3")).clear();
                    Driver.driver.findElement(By.id("unit-name-edit-entry-3")).sendKeys(optiontext);

                    // Accepting answer
                    Driver.driver.findElement(By.id("done-button")).click();
                }

                if (questionType.equals("qtn-text-entry-numeric-img")) // 6. For numeric text entry
                {
                    Driver.driver.findElement(By.id("question-raw-content")).click();
                    Driver.driver.switchTo().frame("iframe-question-edit").findElement(By.xpath("/html/body")).sendKeys(questiontext);
                    Driver.driver.switchTo().defaultContent();

                    // Adding correct answer choice
                    Driver.driver.findElement(By.cssSelector("input[id='correct-ans-text']")).click();
                    Driver.driver.findElement(By.cssSelector("input[id='correct-ans-text']")).clear();
                    Driver.driver.findElement(By.cssSelector("input[id='correct-ans-text']")).sendKeys(numerictext);

                    Thread.sleep(3000);
                    // Rest two boxes are filled automatically.
                    //Adding alternate answer choice.
                    Driver.driver.findElement(By.id("right-alt-container-1")).click();
                    Driver.driver.findElement(By.cssSelector("input[id='alternate-answer-text']")).click();
                    Driver.driver.findElement(By.cssSelector("input[id='alternate-answer-text']")).clear();
                    Driver.driver.findElement(By.cssSelector("input[id='alternate-answer-text']")).sendKeys(optiontext);
                    // Adding tolerance
                    Driver.driver.findElement(By.id("tolerance-ans-text")).click();
                    Driver.driver.findElement(By.id("tolerance-ans-text")).sendKeys(tolrence);
                    Thread.sleep(2000);
                    Driver.driver.findElement(By.id("done-button")).click(); // Accept answer
                    Thread.sleep(2000);

                }
                if (questionType.equals("qtn-text-entry-numeric-units-img")) //7. Adding text entry numeric with units.
                {
                    Driver.driver.findElement(By.id("question-raw-content")).click();
                    Driver.driver.switchTo().frame("iframe-question-edit").findElement(By.xpath("/html/body")).sendKeys(questiontext);
                    Driver.driver.switchTo().defaultContent();
                    // Adding correct answer choice
                    Driver.driver.findElement(By.cssSelector("input[id='correct-ans-text']")).click();
                    Driver.driver.findElement(By.cssSelector("input[id='correct-ans-text']")).clear();
                    Driver.driver.findElement(By.cssSelector("input[id='correct-ans-text']")).sendKeys(numerictext);

                    Thread.sleep(3000);
                    // Rest two boxes are filled automatically.
                    Driver.driver.findElement(By.id("add-more-entry")).click();
                    Thread.sleep(3000);
                    // Selecting particular unit
                    List<WebElement> unitvalues = Driver.driver.findElements(By.tagName("li"));
                    for (WebElement units : unitvalues) {
                        if (units.getText().equals(unitoption)) {
                            units.click();
                            break;
                        }
                    }
                    Driver.driver.findElement(By.id("done-button")).click(); // Accept answer
                    Thread.sleep(2000);

                }

                if (questionType.equals("qtn-numeric-maple-img")) //8. Adding maple numeric question
                {
                    Driver.driver.findElement(By.id("question-raw-content")).click();
                    Driver.driver.switchTo().frame("iframe-question-edit").findElement(By.xpath("/html/body")).sendKeys(questiontext);
                    Driver.driver.switchTo().defaultContent();
                    // Adding correct answer choice
                    Driver.driver.findElement(By.cssSelector("input[id='correct-ans-text']")).click();
                    Driver.driver.findElement(By.cssSelector("input[id='correct-ans-text']")).clear();
                    Driver.driver.findElement(By.cssSelector("input[id='correct-ans-text']")).sendKeys(maplenumeric);

                    Thread.sleep(3000);
                    // Rest two boxes are filled automatically.
                    //Adding alternate answer choice.
                    Driver.driver.findElement(By.id("right-alt-container-1")).click();
                    Driver.driver.findElement(By.cssSelector("input[id='alternate-answer-text']")).click();
                    Driver.driver.findElement(By.cssSelector("input[id='alternate-answer-text']")).clear();
                    Driver.driver.findElement(By.cssSelector("input[id='alternate-answer-text']")).sendKeys(optiontext);
                    // Adding tolerance
                    Driver.driver.findElement(By.id("tolerance-ans-text")).click();
                    Driver.driver.findElement(By.id("tolerance-ans-text")).sendKeys(tolrence);
                    Thread.sleep(2000);
                    // Accept answer
                    Driver.driver.findElement(By.id("done-button")).click();
                    Thread.sleep(2000);

                }

                if (questionType.equals("qtn-math-symbolic-notation-img")) // 9. Adding maple symbolic question
                {
                    Driver.driver.findElement(By.id("question-raw-content")).click();
                    Driver.driver.switchTo().frame("iframe-question-edit").findElement(By.xpath("/html/body")).sendKeys(questiontext);
                    Driver.driver.switchTo().defaultContent();
                    Thread.sleep(3000);
                    // Adding Correct answer
                    Driver.driver.findElement(By.cssSelector("div[class='right-container math-correct-answer-input-container']")).click();
                    Thread.sleep(5000);
                    Driver.driver.findElement(By.cssSelector("button[title='Square root']")).click();
                    Driver.driver.findElement(By.cssSelector("input[class='wrs_focusElement']")).sendKeys(numerictext);
                    Driver.driver.findElement(By.id("wiris-container-save-formaulaeditor")).click();

                    // Adding Alternate answer
                    Driver.driver.findElement(By.id("right-alt-container-1")).click();
                    Thread.sleep(3000);
                    //Driver.driver.findElement(By.id("alt1")).click();
                    List<WebElement> allAnswer = Driver.driver.findElements(By.className("display-correct-answer-math-editor-wrapper"));
                    allAnswer.get(1).click();
                    Thread.sleep(5000);
                    Driver.driver.findElement(By.cssSelector("button[title='Square root']")).click();
                    Driver.driver.findElement(By.cssSelector("input[class='wrs_focusElement']")).sendKeys(numerictext);
                    Driver.driver.findElement(By.id("wiris-container-save-formaulaeditor")).click();
                    // Accept answer
                    Driver.driver.findElement(By.id("done-button")).click();
                    Thread.sleep(2000);

                }

                if (questionType.equals("qtn-essay-type")) // 10. Adding Essay type question
                {
                    Driver.driver.findElement(By.id("question-raw-content")).click();
                    Driver.driver.switchTo().frame("iframe-question-edit").findElement(By.xpath("/html/body")).sendKeys(questiontext);
                    Driver.driver.switchTo().defaultContent();
                    //Adding height for text entry.
                    Driver.driver.findElement(By.id("essay-question-height")).click();
                    Driver.driver.findElement(By.id("essay-question-height")).sendKeys(numerictext);

                }


                if (useWriteBoard != null) {
                    Driver.driver.findElement(By.id("display-write-board-checkbox")).click();//check the use writeboard check box
                    Thread.sleep(2000);
                }
                if (learningobjective != null) {
                    Driver.driver.findElement(By.id("learing-objectives-span")).click();
                    new Click().clickByid("link-add-learning-objectives");
                    if (tlo.equals("1")) {
                        Driver.driver.findElement(By.xpath("html/body/div[8]/div/div[2]/div[1]/div[3]/div[2]/div/div[1]/div[2]/div/div/div[1]/div/div[3]/div[1]/div/div[1]/label")).click();
                    } else {
                        Driver.driver.findElement(By.xpath("html/body/div[8]/div/div[2]/div[1]/div[3]/div[2]/div/div[1]/div[2]/div/div/div[1]/div/div[3]/div[1]/div/div[2]/label")).click();
                    }
                    Driver.driver.findElement(By.cssSelector("span.add-collection-to-subtopic")).click();
                }
                new ComboBox().selectValue(3, "Publish");
                Driver.driver.findElement(By.id("saveQuestionDetails1")).click();*/
           // }
		} //if condition for assingnmentExists ends here
		 	Driver.driver.quit();
		 	Driver.startDriver();
		}
		
		catch(Exception e)
		{
			
			Assert.fail("Exception in create in Apphelper addQuestions in class AssignmentCreate",e);
		}
	}
	
	
	public void addPassagetypequestion(int dataIndex,String passageType,String assignmentname,String questionType)
	{
		try
		{
		String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));
		String optiontext = ReadTestData.readDataByTagName("", "optiontext", Integer.toString(dataIndex));
		String answertext = ReadTestData.readDataByTagName("", "answertext", Integer.toString(dataIndex));
		String numerictext = ReadTestData.readDataByTagName("", "numerictext", Integer.toString(dataIndex));
		String unitoption = ReadTestData.readDataByTagName("", "unitoption", Integer.toString(dataIndex));
		String tolrence = ReadTestData.readDataByTagName("", "tolrence", Integer.toString(dataIndex));
		String maplenumeric = ReadTestData.readDataByTagName("", "maplenumeric", Integer.toString(dataIndex));
		String setname = ReadTestData.readDataByTagName("", "setname", Integer.toString(dataIndex));
		String passage = ReadTestData.readDataByTagName("", "passage", Integer.toString(dataIndex));
		String useWriteBoard = ReadTestData.readDataByTagName("", "useWriteBoard", Integer.toString(dataIndex));
		String chapterName = ReadTestData.readDataByTagName("", "chapterName", Integer.toString(dataIndex));
		String course = Config.course;
		new com.snapwiz.selenium.tests.staf.learnon.apphelper.DirectLogin().CMSLogin();
		String title=Driver.driver.getTitle();
		if(title.equals("Course Content Details"))	
			{
			Driver.driver.findElement(By.cssSelector("img[alt=\""+course+"\"]")).click();
			if(chapterName == null)
			 {
			 Driver.driver.findElement(By.cssSelector("div.course-chapter-label.node")).click();
			 }
			 else
			 {
				 List <WebElement> allChapters = Driver.driver.findElements(By.xpath("//*[starts-with(@class, 'course-chapter-label')]"));
				 for(WebElement chapters: allChapters)
				 {
					 if(chapters.getText().contains(chapterName))
					 {
						 ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", chapters);
						 Thread.sleep(4000);
						 break;
					 }
					 
				 }
				 
			 }
			List<WebElement> elements = Driver.driver.findElements(By.className("collection-assessment-name"));
			for(WebElement content : elements)
				{
				if(content.getText().trim().equals(assignmentname))
				{
					Thread.sleep(3000);
                    ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", content);
                    Thread.sleep(3000);
            		break;
				}
				}
			
			 	Driver.driver.findElement(By.id("questionOptions")).click();
			 	Thread.sleep(2000);
			 	Driver.driver.findElement(By.id("addQuestion")).click();
			 	Thread.sleep(2000);
			 	Driver.driver.findElement(By.id(passageType)).click();
			 	Thread.sleep(2000);
			 	
			 	if(passageType.equals("qtn-passage-based-img"))
			 	{
			 		//Adding Question set name
			 		Driver.driver.findElement(By.id("edit-question-set-name")).click();
			 		Driver.driver.findElement(By.id("edit-question-set-name")).sendKeys(setname);
				 	Driver.driver.findElement(By.id("assessment-edit-save-btn")).click();
				 	Thread.sleep(2000);
				 	// Adding Passage title
				 	Driver.driver.findElement(By.id("passage-directions-content")).click();				 	
				 	Driver.driver.switchTo().frame("iframe-question-passage-direction").findElement(By.xpath("/html/body")).sendKeys(questiontext);
				 	Driver.driver.switchTo().defaultContent();
				 	// Adding a passage
				 	Driver.driver.findElement(By.id("passage-raw-content")).click();
				 	Driver.driver.switchTo().frame("iframe-question-edit-passage-text").findElement(By.xpath("/html/body")).sendKeys(passage);
				 	Driver.driver.switchTo().defaultContent();
				 	Driver.driver.findElement(By.id("question-editor-outer-wrapper")).click();
				 	Thread.sleep(2000);
				 	// Adding different types of questions
				 	
				 	 ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.id(questionType)));
				 //		Driver.driver.findElement(By.id(questionType)).click();
				 		Thread.sleep(2000);
				 		if(questionType.equals("qtn-type-true-false-img")) //1.For true-false type question
				 		{
				 			Driver.driver.findElement(By.id("question-raw-content")).click();
				 			Driver.driver.switchTo().frame("iframe-question-edit").findElement(By.xpath("/html/body")).sendKeys(questiontext);
				 			Driver.driver.switchTo().defaultContent();
				 			//Setting choice 1 as correct answer
				 			Actions action = new Actions(Driver.driver);
				 			WebElement we = Driver.driver.findElement(By.id("choice1"));
				 			action.moveToElement(we).build().perform();			 
				 			Driver.driver.findElement(By.xpath("//img[@title='Set it as Correct Answer']")).click();
				 		} 
				        if(questionType.equals("qtn-multiple-choice-img")) //2. For multiple choice question.
				        {
				        	Driver.driver.findElement(By.id("question-raw-content")).click();
				        	Driver.driver.switchTo().frame("iframe-question-edit").findElement(By.xpath("/html/body")).sendKeys(questiontext);
				        	Driver.driver.switchTo().defaultContent();
				        	//Adding choice 1
				        	Driver.driver.findElement(By.id("choice1")).click();
				        	Driver.driver.switchTo().frame("iframe-choice1").findElement(By.xpath("/html/body")).sendKeys(answertext);			 	
				        	Driver.driver.switchTo().defaultContent();
				        	//Adding choice 2
				        	Driver.driver.findElement(By.id("choice2")).click();
				        	Driver.driver.switchTo().frame("iframe-choice2").findElement(By.xpath("/html/body")).sendKeys(answertext);			 	
				        	Driver.driver.switchTo().defaultContent();
				        	//Adding choice 3
				        	Driver.driver.findElement(By.id("choice3")).click();
				        	Driver.driver.switchTo().frame("iframe-choice3").findElement(By.xpath("/html/body")).sendKeys(answertext);			 	
				        	Driver.driver.switchTo().defaultContent();
				        	//Adding choice 4
				        	Driver.driver.findElement(By.id("choice4")).click();
				        	Driver.driver.switchTo().frame("iframe-choice4").findElement(By.xpath("/html/body")).sendKeys(answertext);			 	
				        	Driver.driver.switchTo().defaultContent();
				        	//Setting choice 2 as correct answer
				        	Actions action = new Actions(Driver.driver);
				        	WebElement we = Driver.driver.findElement(By.id("choice2"));
				        	action.moveToElement(we).build().perform();			 
				        	Driver.driver.findElement(By.xpath("//img[@title='Set it as Correct Answer']")).click();
				        }
				        if(questionType.equals("qtn-multiple-selection-img"))  //3. For multiple sections
				        {
				        	Driver.driver.findElement(By.id("question-raw-content")).click();
				        	Driver.driver.switchTo().frame("iframe-question-edit").findElement(By.xpath("/html/body")).sendKeys(questiontext);
				        	Driver.driver.switchTo().defaultContent();
				        	//Adding choice 1
				        	Driver.driver.findElement(By.id("choice1")).click();
				        	Driver.driver.switchTo().frame("iframe-choice1").findElement(By.xpath("/html/body")).sendKeys(answertext);			 	
				        	Driver.driver.switchTo().defaultContent();
				        	//Adding choice 2
				        	Driver.driver.findElement(By.id("choice2")).click();
				        	Driver.driver.switchTo().frame("iframe-choice2").findElement(By.xpath("/html/body")).sendKeys(answertext);			 	
				        	Driver.driver.switchTo().defaultContent();
				        	//Adding choice 3
				        	Driver.driver.findElement(By.id("choice3")).click();
				        	Driver.driver.switchTo().frame("iframe-choice3").findElement(By.xpath("/html/body")).sendKeys(answertext);			 	
				        	Driver.driver.switchTo().defaultContent();
				        	//Adding choice 4
				        	Driver.driver.findElement(By.id("choice4")).click();
				        	Driver.driver.switchTo().frame("iframe-choice4").findElement(By.xpath("/html/body")).sendKeys(answertext);			 	
				        	Driver.driver.switchTo().defaultContent();
				        	//Setting choice 2 as correct answer
				        	Actions action1 = new Actions(Driver.driver);
				        	WebElement we1 = Driver.driver.findElement(By.id("choice2"));
				        	action1.moveToElement(we1).build().perform();			 
				        	Driver.driver.findElement(By.xpath("//img[@title='Set it as Correct Answer']")).click();
				        	//Setting choice 4 as correct answer
				        	Actions action2 = new Actions(Driver.driver);
				        	WebElement we2 = Driver.driver.findElement(By.id("choice4"));
				        	action2.moveToElement(we2).build().perform();			 
				        	Driver.driver.findElement(By.xpath("//img[@title='Set it as Correct Answer']")).click();
				        }
				        if(questionType.equals("qtn-text-entry-img")) // 4.For Text Entry type.
				        {
				        	Driver.driver.findElement(By.id("question-raw-content")).click();
				        	Driver.driver.switchTo().frame("iframe-question-edit").findElement(By.xpath("/html/body")).sendKeys(questiontext);
				        	Driver.driver.switchTo().defaultContent();
				        	// Adding correct answer choice
				        	Driver.driver.findElement(By.cssSelector("input[id='correct-ans-text']")).click();
				        	Driver.driver.findElement(By.cssSelector("input[id='correct-ans-text']")).clear();
				        	Driver.driver.findElement(By.cssSelector("input[id='correct-ans-text']")).sendKeys(answertext);	
				        	Thread.sleep(3000);
				        	// Rest two boxes are filled automatically.
				        	//Adding alternate answer choice.
				        	Driver.driver.findElement(By.id("right-alt-container-1")).click();
				        	Driver.driver.findElement(By.cssSelector("input[id='alternate-answer-text']")).click();
				        	Driver.driver.findElement(By.cssSelector("input[id='alternate-answer-text']")).clear();
				        	Driver.driver.findElement(By.cssSelector("input[id='alternate-answer-text']")).sendKeys(optiontext);	
				 
				        	Driver.driver.findElement(By.id("done-button")).click(); // Accept answer
				        	Thread.sleep(2000);
				        }
				        if(questionType.equals("qtn-text-entry-drop-down-img")) //5. For text entry drop down
				        {
				        	Driver.driver.findElement(By.id("question-raw-content")).click();
				        	Driver.driver.switchTo().frame("iframe-question-edit").findElement(By.xpath("/html/body")).sendKeys(questiontext);
				        	Driver.driver.switchTo().defaultContent();
				        	// Adding Entry 1
				        	Driver.driver.findElement(By.id("entry-0")).click();
				        	Driver.driver.findElement(By.id("unit-name-edit-entry-0")).clear();
				        	Driver.driver.findElement(By.id("unit-name-edit-entry-0")).sendKeys(optiontext);
				        	Thread.sleep(2000);
				        	  // Accepting answer
						 	WebElement menuitem = Driver.driver.findElement(By.id("entry-1")); 
				        	Locatable hoverItem = (Locatable) menuitem;
				        	Mouse mouse = ((HasInputDevices) Driver.driver).getMouse();
				        	mouse.mouseMove(hoverItem.getCoordinates());
				        	List<WebElement> selectanswerticks = Driver.driver.findElements(By.className("mark-selected"));        	
				        	selectanswerticks.get(1).click(); //select second option as correct answer
				        	
				        	// Adding Entry 2
				        	Actions action = new Actions(Driver.driver);
				        	action.doubleClick(Driver.driver.findElement(By.id("entry-1")));
				        	action.perform();
				        	Driver.driver.findElement(By.id("unit-name-edit-entry-1")).clear();
				        	Driver.driver.findElement(By.id("unit-name-edit-entry-1")).sendKeys(optiontext);
				        	Thread.sleep(2000);
	
				        	// Adding Entry 3
				        	action = new Actions(Driver.driver);
				        	action.doubleClick(Driver.driver.findElement(By.id("entry-2")));
						 	action.perform();
						 	Thread.sleep(2000);
						 	Driver.driver.findElement(By.id("unit-name-edit-entry-2")).clear();
						 	Driver.driver.findElement(By.id("unit-name-edit-entry-2")).sendKeys(optiontext);
						 	Thread.sleep(2000);
				 			//clicking on add more entry
				 			Driver.driver.findElement(By.id("add-new-entry")).click();
				 			//Adding entry 4
				 			action.doubleClick(Driver.driver.findElement(By.id("entry-3")));
				 			Driver.driver.findElement(By.id("unit-name-edit-entry-3")).clear();
				 			Driver.driver.findElement(By.id("unit-name-edit-entry-3")).sendKeys(optiontext);
				 			// Accepting answer
				 			Driver.driver.findElement(By.id("done-button")).click(); 
				         }
				
					        if(questionType.equals("qtn-text-entry-numeric-img")) // 6. For numeric text entry
					        {
					        	Driver.driver.findElement(By.id("question-raw-content")).click();
						 	Driver.driver.switchTo().frame("iframe-question-edit").findElement(By.xpath("/html/body")).sendKeys(questiontext);
						 	Driver.driver.switchTo().defaultContent();
						 	// Adding correct answer choice
						 	Driver.driver.findElement(By.cssSelector("input[id='correct-ans-text']")).click();
						 	Driver.driver.findElement(By.cssSelector("input[id='correct-ans-text']")).clear();
						    Driver.driver.findElement(By.cssSelector("input[id='correct-ans-text']")).sendKeys(numerictext);	
						    Driver.driver.switchTo().defaultContent();
						    Thread.sleep(3000);
						 	// Rest two boxes are filled automatically.
						  //Adding alternate answer choice.
						    Driver.driver.findElement(By.id("right-alt-container-1")).click();
						    Driver.driver.findElement(By.cssSelector("input[id='alternate-answer-text']")).click();
						 	Driver.driver.findElement(By.cssSelector("input[id='alternate-answer-text']")).clear();
						    Driver.driver.findElement(By.cssSelector("input[id='alternate-answer-text']")).sendKeys(optiontext);
						    // Adding tolerance
						    Driver.driver.findElement(By.id("tolerance-ans-text")).click();
						 	Driver.driver.findElement(By.id("tolerance-ans-text")).sendKeys(tolrence);
						 	Thread.sleep(2000);
						    Driver.driver.findElement(By.id("done-button")).click(); // Accept answer
						 	  Thread.sleep(2000);
						}
						if(questionType.equals("qtn-text-entry-numeric-units-img")) //7. Adding text entry numeric with units.
						{
							Driver.driver.findElement(By.id("question-raw-content")).click();
						 	Driver.driver.switchTo().frame("iframe-question-edit").findElement(By.xpath("/html/body")).sendKeys(questiontext);
						 	Driver.driver.switchTo().defaultContent();
						 	// Adding correct answer choice
						 	Driver.driver.findElement(By.cssSelector("input[id='correct-ans-text']")).click();
						 	Driver.driver.findElement(By.cssSelector("input[id='correct-ans-text']")).clear();
						    Driver.driver.findElement(By.cssSelector("input[id='correct-ans-text']")).sendKeys(numerictext);	
						    
						    Thread.sleep(3000);
						 	// Rest two boxes are filled automatically.
						    Driver.driver.findElement(By.id("add-more-entry")).click();
						    Thread.sleep(3000);
						    // Selecting particular unit
						    List<WebElement> unitvalues = Driver.driver.findElements(By.tagName("li"));
						    for(WebElement units : unitvalues)
						    {
						    	//System.out.println(units.getText());
						    	if(units.getText().equals(unitoption))
						    	{
						    		units.click();
						    		break;
						    	}
						    }
						    Driver.driver.findElement(By.id("done-button")).click(); // Accept answer
						 	  Thread.sleep(2000);
						}
						
						if(questionType.equals("qtn-numeric-maple-img")) //8. Adding maple numeric question
						{
							Driver.driver.findElement(By.id("question-raw-content")).click();
						 	Driver.driver.switchTo().frame("iframe-question-edit").findElement(By.xpath("/html/body")).sendKeys(questiontext);
						 	Driver.driver.switchTo().defaultContent();
						 	// Adding correct answer choice
						 	Driver.driver.findElement(By.cssSelector("input[id='correct-ans-text']")).click();
						 	Driver.driver.findElement(By.cssSelector("input[id='correct-ans-text']")).clear();
						    Driver.driver.findElement(By.cssSelector("input[id='correct-ans-text']")).sendKeys(maplenumeric);	
						 
						    Thread.sleep(3000);
						 	// Rest two boxes are filled automatically.
						    //Adding alternate answer choice.
						    Driver.driver.findElement(By.id("right-alt-container-1")).click();
						    Driver.driver.findElement(By.cssSelector("input[id='alternate-answer-text']")).click();
						 	Driver.driver.findElement(By.cssSelector("input[id='alternate-answer-text']")).clear();
						    Driver.driver.findElement(By.cssSelector("input[id='alternate-answer-text']")).sendKeys(optiontext);
						    // Adding tolerance
						    Driver.driver.findElement(By.id("tolerance-ans-text")).click();
						 	Driver.driver.findElement(By.id("tolerance-ans-text")).sendKeys(tolrence);
						 	Thread.sleep(2000);
						 	 // Accept answer
						    Driver.driver.findElement(By.id("done-button")).click();
						 	  Thread.sleep(2000);
						 	
						}
				
						if(questionType.equals("qtn-math-symbolic-notation-img")) // 9. Adding maple symbolic question
						{
							Driver.driver.findElement(By.id("question-raw-content")).click();
						 	Driver.driver.switchTo().frame("iframe-question-edit").findElement(By.xpath("/html/body")).sendKeys(questiontext);
						 	Driver.driver.switchTo().defaultContent();
						 	Thread.sleep(3000);
						 	// Adding Correct answer
						    Driver.driver.findElement(By.cssSelector("div[class='right-container math-correct-answer-input-container']")).click();
						    Thread.sleep(5000);
						    Driver.driver.findElement(By.cssSelector("button[title='Square root']")).click();  
					        Driver.driver.findElement(By.cssSelector("input[class='wrs_focusElement']")).sendKeys(numerictext);
					        Driver.driver.findElement(By.id("wiris-container-save-formaulaeditor")).click();
					        
						 	// Adding Alternate answer
						 	Driver.driver.findElement(By.id("right-alt-container-1")).click();
						 	List<WebElement> allAnswer = Driver.driver.findElements(By.className("display-correct-answer-math-editor-wrapper"));
						 	allAnswer.get(1).click();
						    Thread.sleep(5000);
						 	Driver.driver.findElement(By.cssSelector("button[title='Square root']")).click();  
					        Driver.driver.findElement(By.cssSelector("input[class='wrs_focusElement']")).sendKeys(numerictext);
					        Driver.driver.findElement(By.id("wiris-container-save-formaulaeditor")).click();
						 	 // Accept answer
						    Driver.driver.findElement(By.id("done-button")).click();
						 	  Thread.sleep(2000);
				
						}
						
						//((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();",Driver.driver.findElement(By.cssSelector("div[id='qtn-essay-type']")));	
					
						if(questionType.equals("qtn-essay-type")) // 10. Adding Essay type question
						{
							
							Driver.driver.findElement(By.id("question-raw-content")).click();
						 	Driver.driver.switchTo().frame("iframe-question-edit").findElement(By.xpath("/html/body")).sendKeys(questiontext);
						 	Driver.driver.switchTo().defaultContent();
						 	//Adding height for text entry.
						 	Driver.driver.findElement(By.id("essay-question-height")).click();
						 	Driver.driver.findElement(By.id("essay-question-height")).sendKeys(numerictext);
						
						}
						
			 	}
			 	if(useWriteBoard != null)
				 {
					 Driver.driver.findElement(By.id("display-write-board-checkbox")).click();//check the use writeboard check box
					 Thread.sleep(2000);
				 }
			 
			 		new ComboBox().selectValue(3, "Publish");
					Driver.driver.findElement(By.id("saveQuestionDetails1")).click();
					Driver.driver.quit();
					Driver.startDriver();
			 	
					 	
			 		
			}
		}
			 catch(Exception e)
			{
				
				Assert.fail("Exception in create in Apphelper addQuestions in class AssignmentCreate",e);
			}
	
	}
	
	public void assignToStudent(int dataIndex)
	{	
		try
		{
        String context_title = ReadTestData.readDataByTagName("", "context_title", Integer.toString(dataIndex));
		String  shareWithInitialString = ReadTestData.readDataByTagName("", "shareWithInitialString", Integer.toString(dataIndex));
		String shareName = ReadTestData.readDataByTagName("", "shareName", Integer.toString(dataIndex));
		String shareWithClass = ReadTestData.readDataByTagName("", "shareWithClass", Integer.toString(dataIndex));
		String duedate = ReadTestData.readDataByTagName("", "duedate", Integer.toString(dataIndex));
		String duetime = ReadTestData.readDataByTagName("", "duetime", Integer.toString(dataIndex));
		String accessibleafter = ReadTestData.readDataByTagName("", "accessibleafter", Integer.toString(dataIndex));
		String additionalnote = ReadTestData.readDataByTagName("", "additionalnote", Integer.toString(dataIndex));
		String gradeable = ReadTestData.readDataByTagName("", "gradeable", Integer.toString(dataIndex)); 
		String gradingpolicy = ReadTestData.readDataByTagName("", "gradingpolicy", Integer.toString(dataIndex));
		String assignmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
		String  assignmentpolicy = ReadTestData.readDataByTagName("", "assignmentpolicy", Integer.toString(dataIndex));
		String  gradeBookWeighting = ReadTestData.readDataByTagName("", "gradeBookWeighting", Integer.toString(dataIndex));

		new Navigator().NavigateTo("Assignments");
		Driver.driver.findElement(By.cssSelector("span.ins-assignment-button-sprite.instructor-assignment-new")).click();
		Thread.sleep(3000);
		if(Driver.driver.findElement(By.cssSelector("div[class='tab active']")).getText().equals("My Resources"))
		{//Opening All Resources tab if not opened after clicking on New Assignment button

			Driver.driver.findElement(By.cssSelector("span[title='All Resources']")).click();
		}

            Driver.driver.findElement(By.cssSelector("div[class='ls-inst-dashboard-assignment-popup-button ls--question-banks-view']")).click();
		//Adding assignment to search
		Driver.driver.findElement(By.id("all-resource-search-textarea")).clear();
		Driver.driver.findElement(By.id("all-resource-search-textarea")).sendKeys("\"" + assignmentname + "\"");
		Driver.driver.findElement(By.cssSelector("#all-resource-search-button > span.ins-resource-search-sprite.instructor-resource-search-img")).click();
		Thread.sleep(5000);
        new UIElement().waitAndFindElement(By.cssSelector("i[class='assign-this resource-assign-this-image ls-assign-this-sprite']"));
        List<WebElement> elementList = Driver.driver.findElements(By.cssSelector("i[class='assign-this resource-assign-this-image ls-assign-this-sprite']"));
        for(int a= 0;a<elementList.size();a++){
            if(elementList.get(a).isDisplayed()){
                elementList.get(a).click();
                break;
            }
        }

        new ShareWith().share(shareWithInitialString, shareName, shareWithClass, context_title, true);
		Thread.sleep(3000);
		new UIElement().waitAndFindElement(By.id("due-time"));
		Driver.driver.findElement(By.id("due-time")).click();
		Driver.driver.findElement(By.xpath("//li[text()='07:30 PM']")).click();
		Driver.driver.findElement(By.id("due-date")).click();
		new UIElement().waitAndFindElement(By.linkText(duedate));
		Driver.driver.findElement(By.linkText(duedate)).click();

		Driver.driver.findElement(By.id("due-date")).click();
		Thread.sleep(2000);
		Driver.driver.findElement(By.cssSelector("span[class='ui-icon ui-icon-circle-triangle-e']")).click();
		Thread.sleep(2000);
		Driver.driver.findElement(By.linkText(duedate)).click();
		Thread.sleep(2000);
		if(gradeable.equals("true"))
		{
			((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.className("ir-ls-assign-dialog-gradable-label-check")));
			Thread.sleep(2000);
			Driver.driver.findElement(By.id("grading-policy")).sendKeys(gradingpolicy);
		}
		if(gradeable.equals("true") && assignmentpolicy!= null)
		{

			//click on  Choose your assignment policy dropdown
			((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.linkText("Select an assignment policy")));
			Thread.sleep(2000);
			Driver.driver.findElement(By.linkText(assignmentpolicy)).click();//select a policy
			Thread.sleep(2000);
		}
		if(accessibleafter != null)
		{
			Driver.driver.findElement(By.id("accessible-date")).click();
			Driver.driver.findElement(By.linkText(accessibleafter)).click();
		}



		Driver.driver.findElement(By.className("input-filed")).sendKeys("shor"); //short label
		Thread.sleep(2000);
		Driver.driver.findElement(By.id("additional-notes")).clear();
	    Driver.driver.findElement(By.id("additional-notes")).sendKeys(additionalnote);


			if(gradeBookWeighting!=null){
				if(gradeBookWeighting.equals("true")){
					new Click().clickbylinkText("Uncategorized");
					new UIElement().waitAndFindElement(By.linkText("No Assignment Category"));
					new Click().clickbylinkText("Practice");
				}
			}


	    Driver.driver.findElement(By.cssSelector("span[class='btn sty-green submit-assign']")).click();
	    Thread.sleep(5000);

		}
		catch(Exception e)
		{
			Assert.fail("Exception in  assignToStudent in AppHelper class Assignment",e);
		}
	}

	public void AssignAssessmentwithgradingPolicy(int dataIndex,boolean sharewithstudent)
	{
		try
		{
        String context_title = ReadTestData.readDataByTagName("", "context_title", Integer.toString(dataIndex));
		String  shareWithInitialString = ReadTestData.readDataByTagName("", "shareWithInitialString", Integer.toString(dataIndex));
		String shareName = ReadTestData.readDataByTagName("", "shareName", Integer.toString(dataIndex));
		String shareWithClass = ReadTestData.readDataByTagName("", "shareWithClass", Integer.toString(dataIndex));
		String duedate = ReadTestData.readDataByTagName("", "duedate", Integer.toString(dataIndex));
		String duetime = ReadTestData.readDataByTagName("", "duetime", Integer.toString(dataIndex));
		String accessibleafter = ReadTestData.readDataByTagName("", "accessibleafter", Integer.toString(dataIndex));
		String additionalnote = ReadTestData.readDataByTagName("", "additionalnote", Integer.toString(dataIndex));
		String gradeable = ReadTestData.readDataByTagName("", "gradeable", Integer.toString(dataIndex));
		String gradingpolicy = ReadTestData.readDataByTagName("", "gradingpolicy", Integer.toString(dataIndex));
		String assignmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
		String  assignmentpolicy = ReadTestData.readDataByTagName("", "assignmentpolicy", Integer.toString(dataIndex));
		String policyname=ReadTestData.readDataByTagName("", "resourcesname", Integer.toString(dataIndex));
		/*new Navigator().NavigateTo("Assignments");

		Driver.driver.findElement(By.cssSelector("span.ins-assignment-button-sprite.instructor-assignment-new")).click();

		Thread.sleep(3000);
		if(Driver.driver.findElement(By.cssSelector("div[class='tab active']")).getText().equals("My Resources"))
		{//Opening All Resources tab if not opened after clicking on New Assignment button

			Driver.driver.findElement(By.cssSelector("span[title='All Resources']")).click();
		}*/
		new Navigator().NavigateTo("Question Banks");
		//Adding assignment to search
		Driver.driver.findElement(By.id("all-resource-search-textarea")).clear();
		Driver.driver.findElement(By.id("all-resource-search-textarea")).sendKeys("\""+assignmentname+"\"");
		Driver.driver.findElement(By.cssSelector("#all-resource-search-button > span.ins-resource-search-sprite.instructor-resource-search-img")).click();
		Thread.sleep(3000);
        Driver.driver.findElement(By.cssSelector("span[title='Assign This']")).click();
		/*List<WebElement> assign = Driver.driver.findElements(By.className("assign-this"));

		for(WebElement assignment: assign)
		{

			if(assignment.getText().trim().equals("Assign This"))
			{

				assignment.click();
				break;
			}

		}*/
		Driver.driver.findElement(By.className("input-filed")).sendKeys("shor"); //short label
		Thread.sleep(2000);
		if(sharewithstudent==true)
		{

			new ShareWith().share(shareWithInitialString, shareName, shareWithClass,context_title,false);
			Thread.sleep(3000);
		}

		Driver.driver.findElement(By.id("due-time")).click();
		List<WebElement> elements = Driver.driver.findElements(By.xpath("//li"));
		for(WebElement time : elements)
		{
			if(time.getText().equals(duetime))
			{
				time.click();
				break;
			}
		}


		Driver.driver.findElement(By.id("due-date")).click();
		Thread.sleep(2000);
		Driver.driver.findElement(By.cssSelector("span[class='ui-icon ui-icon-circle-triangle-e']")).click();
		Thread.sleep(2000);
		Driver.driver.findElement(By.linkText(duedate)).click();
		Thread.sleep(2000);
		if(gradeable.equals("true"))
		{
			((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.className("ir-ls-assign-dialog-gradable-label-check")));
			Thread.sleep(2000);
			Driver.driver.findElement(By.id("grading-policy")).sendKeys(gradingpolicy);
		}
		if(gradeable.equals("true") && assignmentpolicy!= null)
		{

			//click on  Choose your assignment policy dropdown
			((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.linkText("Choose your assignment policy")));
			Thread.sleep(2000);
			Driver.driver.findElement(By.linkText(assignmentpolicy)).click();//select a policy
			Thread.sleep(2000);
		}
		if(accessibleafter != null)
		{
			Driver.driver.findElement(By.id("accessible-date")).click();
			Driver.driver.findElement(By.linkText(accessibleafter)).click();
		}
		Driver.driver.findElement(By.cssSelector("div[class='ir-ls-grading-policy-filter-drop-down-wrapper ir-ls-assign-dialog-field scrollbar-wrapper']")).click();
		//new Click().clickbyxpath("html/body/div[6]/div[1]/div/div[2]/div[2]/div[2]/div[1]/div[6]/div[4]/div[4]/div[2]/div/a[2]");//click on grading policy
		Thread.sleep(2000);
		new Click().clickBycssselector("a[title='" + policyname + "']");
		Thread.sleep(2000);


		Driver.driver.findElement(By.id("additional-notes")).clear();
	    Driver.driver.findElement(By.id("additional-notes")).sendKeys(additionalnote);

	    Driver.driver.findElement(By.cssSelector("span[class='btn sty-green submit-assign']")).click();
	    Thread.sleep(5000);

		}
		catch(Exception e)
		{
			Assert.fail("Exception in  assignToStudent in AppHelper class Assignment",e);
		}
	}
	public void updateAssignment(int dataIndex,boolean addShareWith)
	{
		try
		{
            String context_title = ReadTestData.readDataByTagName("", "context_title", Integer.toString(dataIndex));
            String shareWithInitialString = ReadTestData.readDataByTagName("", "shareWithInitialString", Integer.toString(dataIndex));
            String shareName = ReadTestData.readDataByTagName("", "shareName", Integer.toString(dataIndex));
            String shareWithClass = ReadTestData.readDataByTagName("", "shareWithClass", Integer.toString(dataIndex));
            String assignmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
            String gradeable = ReadTestData.readDataByTagName("", "gradeable", Integer.toString(dataIndex));
            new Navigator().NavigateTo("Summary");
            //Selecting third and fourth filter values
            Driver.driver.findElement(By.linkText("Assignment Status")).click();
            Thread.sleep(2000);
            Driver.driver.findElement(By.linkText("Available for Students")).click();
            Thread.sleep(2000);

            Driver.driver.findElement(By.linkText("All Activities")).click();
            Thread.sleep(2000);
            if (gradeable == null)
                Driver.driver.findElement(By.linkText("Question Practice")).click();
            else if (gradeable.equals("false"))
                Driver.driver.findElement(By.linkText("Question Practice")).click();
            else
                Driver.driver.findElement(By.linkText("Question Assignment")).click();

            Thread.sleep(2000);

            List<WebElement> assignmentNames = Driver.driver.findElements(By.cssSelector("span[class='ls-assignment-name instructor-assessment-review']"));
            int index = 0;
            for (int i = 0; i < assignmentNames.size(); i++) {
                if (assignmentNames.get(i).getText().substring(7).equals(assignmentname)) {
                    break;
                }
                index++;
            }
            Driver.driver.findElements(By.cssSelector("span[class='assign-more']")).get(index).click(); //click on Update Assignment in Assignments page only
            Thread.sleep(2000);
            if (addShareWith == true)
                new ShareWith().share(shareWithInitialString, shareName, shareWithClass, context_title, false);
            Driver.driver.findElement(By.cssSelector("span[class='btn sty-green submit-assign']")).click();

        //no update link in  My Resource page
		/*new Navigator().NavigateTo("Assignments");
		//Clicking on New Assignment Button
		Driver.driver.findElement(By.cssSelector("span.ins-assignment-button-sprite.instructor-assignment-new")).click();
		Thread.sleep(3000);
		if(Driver.driver.findElement(By.cssSelector("div[class='tab active']")).getText().equals("My Resources"))
		{//Opening All Resources tab if not opened after clicking on New Assignment button

			Driver.driver.findElement(By.cssSelector("span[title='All Resources']")).click();
		}
		//Adding assignment to search
		Driver.driver.findElement(By.id("all-resource-search-textarea")).clear();
	    Driver.driver.findElement(By.id("all-resource-search-textarea")).sendKeys("\""+assignmentname+"\"");
	    Driver.driver.findElement(By.cssSelector("#all-resource-search-button > span.ins-resource-search-sprite.instructor-resource-search-img")).click();
	    Thread.sleep(2000);
	    Driver.driver.findElement(By.cssSelector("span[class='assign-this update-assignment']")).click();

	    //Driver.driver.findElement(By.cssSelector("span[class='assign-this update-assignment']")).click();

	    if(addShareWith == true)
		new ShareWith().share(shareWithInitialString, shareName, shareWithClass,context_title,false);

	    Driver.driver.findElement(By.cssSelector("span[class='btn sty-green submit-assign']")).click();
	    */
		}
		catch(Exception e)
		{
			Assert.fail("Exception in app helper updateAssignment in class Assignment",e);
		}

	}

	public void submitAssignmentAsStudent(int dataIndex)
	{
        try {
            String submitassignment = ReadTestData.readDataByTagName("", "submitassignment", Integer.toString(dataIndex));
            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
            String usehint = ReadTestData.readDataByTagName("", "usehint", Integer.toString(dataIndex));
            String answerchoice = ReadTestData.readDataByTagName("", "answerchoice", Integer.toString(dataIndex));
            new Navigator().NavigateTo("Course Stream");
            int helppage = Driver.driver.findElements(By.className("close-help-page")).size();
            if (helppage == 1)
                Driver.driver.findElement(By.className("close-help-page")).click();
            int index = 0;
            List<WebElement> allAssignment = Driver.driver.findElements(By.cssSelector("span[class='ls-stream-assignment-title']"));
            for(WebElement assignment:allAssignment)
            {
                if (assignment.getText().substring(7).equals(assessmentname)) {
                    break;
                } else
                    index++;
            }
            new Click().clickbylistcssselector("span[class='ls-stream-assignment-title']", index);//click on Assignment
            boolean useHint = false;//by default useHint is set to false
            if(usehint != null)//read the tag from testdata
                useHint = true;//set useHint to true

            if(answerchoice == null)
                answerchoice = "correct";
            int timer = 1;
            while (timer != 0) {

            String questionText = new TextFetch().textfetchbyid("question-edit");
            if(questionText.contains("True False"))
                new AttemptQuestion().trueFalse(useHint, answerchoice, dataIndex);

            if(questionText.contains("Multiple Choice"))
               new AttemptQuestion().multipleChoice(useHint, answerchoice, dataIndex);

            if(questionText.contains("Multi Selection"))
               new AttemptQuestion().multipleSelection(useHint, answerchoice, dataIndex);

            if(questionText.contains("Text Entry"))
                    new AttemptQuestion().textEntry(useHint, answerchoice, dataIndex);

            if(questionText.contains("Text Drop Down"))
                    new AttemptQuestion().textDropDown(useHint, answerchoice, dataIndex);

            if(questionText.contains("Numeric Entry With Units"))
                new AttemptQuestion().numericEntryWithUnits(useHint, answerchoice, dataIndex);

            if(questionText.contains("Advanced Numeric"))
               new AttemptQuestion().advancedNumeric(useHint, answerchoice, dataIndex);

            if(questionText.contains("Expression Evaluator"))
               new AttemptQuestion().expressionEvaluator(useHint, answerchoice, dataIndex);

            if(questionText.contains("Essay"))
                    new AttemptQuestion().essay(useHint, answerchoice, dataIndex);

            if(questionText.contains("Write Board"))
                    new AttemptQuestion().writeBoard(useHint, answerchoice, dataIndex);

          /* if(questionText.contains("Match the Following"))
               new AttemptQuestion().matchTheFollowing(useHint, answerchoice, dataIndex);

           if(questionText.contains("Drag and Drop"))
               new AttemptQuestion().dragAndDrop(useHint, answerchoice, dataIndex);

           if(questionText.contains("Cloze Formula"))
               new AttemptQuestion().attemptClozeFormula(useHint, answerchoice, dataIndex);*/
           if (submitassignment == null || submitassignment.equalsIgnoreCase("true"))
                {
                    if(Driver.driver.findElements(By.cssSelector("a[class='btn btn--primary btn--large btn--submit long-text-button']")).size() > 0) //Finish Assignment
                    {
                        ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("a[class='btn btn--primary btn--large btn--submit long-text-button']")));
                    }
                    else if(Driver.driver.findElements(By.cssSelector("a[class='btn btn--primary btn--large btn--submit']")).size()>0)//click on Finish Assignment
                    {
                        ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("a[class='btn btn--primary btn--large btn--submit']")));
                    }
                    Thread.sleep(2000);//submitting the assignment as student 1
                 }


           if(Driver.driver.findElements(By.cssSelector("a[class='btn btn--primary btn--large btn--next long-text-button']")).size() > 0) //next
                        ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("a[class='btn btn--primary btn--large btn--next long-text-button']")));

           if(Driver.driver.findElements(By.cssSelector("a[class='btn btn--primary btn--large btn--next']")).size() > 0) //next
                        ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("a[class='btn btn--primary btn--large btn--next']")));

           Thread.sleep(2000);

           timer = Driver.driver.findElements(By.id("assessmentTimer")).size();
        }
        }
		catch(Exception e)
		{
			Assert.fail("Exception in app helper submitAssignmentAsStudent in class Assignment",e);
		}
	}

	public void attemptingQuestionsAsStudent()
	{
		try{
			int timer=1;

			while(timer==1)
			{
				 timer=Driver.driver.findElements(By.id("assessmentTimer")).size();
				 if(timer == 0)
						break;
				int valueofoption=Driver.driver.findElements(By.className("qtn-label")).size();
				if(valueofoption>=1)
					Driver.driver.findElement(By.className("qtn-label")).click();

			/*	List<WebElement> textbox = Driver.driver.findElements(By.tagName("input"));
				forloop:
				for(WebElement box : textbox)
				{
					if(box.isDisplayed() == true)
					{
						box.sendKeys("abcd");
						break forloop;
					}
				}*/
				int submitbutton= Driver.driver.findElements(By.cssSelector("a[class='btn btn--primary btn--large btn--submit']")).size();
				 if(submitbutton>=1)
					 Driver.driver.findElement(By.linkText("Submit")).click(); //submitting the assignment as student
				 else
				 Driver.driver.findElement(By.cssSelector("a[class='btn btn--primary btn--large btn--next']")).click();//click on next button
				 Thread.sleep(3000);

			}


		}
		catch(Exception e)
		{
			Assert.fail("Exception in app helper attemptingQuestionAsStudent in class Assignment",e);
		}
	}
	public void statusValidate(int dataIndex,String expectedStatus)
	{
		String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
		String statuscolor =  ReadTestData.readDataByTagName("", "statuscolor", Integer.toString(dataIndex));
		new Navigator().NavigateTo("Assignments");
		int index = 0;
		List<WebElement> assignments =  Driver.driver.findElements(By.cssSelector("span[class='ls-assignment-name instructor-assessment-review']"));
		 for(WebElement element : assignments)
		 {
			 if(element.getText().contains(assessmentname))
					 {
				 		break;
					 }
			 index++;
		 }
		 List<WebElement> status = Driver.driver.findElements(By.className("ls-assignment-status"));

		if(!status.get(index).getText().equals(expectedStatus))
			Assert.fail("Status of the asignment in Instructor Dashboard is "+status.get(index).getText()+" which is not equal to expected status: "+expectedStatus);
		if(statuscolor!= null)
		{
			if(expectedStatus.equals("Status:  Grading in Progress"))
			{
			WebElement statuscolors = Driver.driver.findElement(By.cssSelector("span[title='Grading in Progress']"));
			if(!statuscolors.getCssValue("color").equals(statuscolor))
				Assert.fail("Status color is "+statuscolors.getCssValue("color")+" which is not equal to expected color "+statuscolor);
			}
			if(expectedStatus.equals("Status:  Review in Progress"))
			{
			WebElement statuscolors = Driver.driver.findElement(By.cssSelector("span[title='Review in Progress']"));
			if(!statuscolors.getCssValue("color").equals(statuscolor))
				Assert.fail("Status color is "+statuscolors.getCssValue("color")+" which is not equal to expected color "+statuscolor);
			}
			if(expectedStatus.equals("Status:  Graded") || expectedStatus.equals("Status:  Reviewed"))
			{
			WebElement statuscolors = Driver.driver.findElement(By.cssSelector("span[class='ls-assignment-status-grades-released']"));
			if(!statuscolors.getCssValue("color").equals(statuscolor))
				Assert.fail("Status color is "+statuscolors.getCssValue("color")+" which is not equal to expected color "+statuscolor);
			}
		}
		//List<WebElement> firstblock =  Driver.driver.findElements(By.cssSelector("div[class='ls-instructor-activity-cards ls-instructor-cards-first not-started']"));
		//System.out.println(firstblock.get(index).getText());
	}




	public int statusBoxCount(int dataIndex,String boxName)
	{
		String [] cntarray = {""};
		String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
		new Navigator().NavigateTo("Assignments");
		int index = 0;
		List<WebElement> assignments =  Driver.driver.findElements(By.cssSelector("span[class='ls-assignment-name instructor-assessment-review']"));
		 for(WebElement element : assignments)
		 {
			 if(element.getText().contains(assessmentname))
					 {
				 		break;
					 }
			 index++;
		 }
		 if(boxName.equals("Not Started"))
		 {
		 List<WebElement> notstartedbox = Driver.driver.findElements(By.cssSelector("div[class='ls-instructor-activity-cards ls-instructor-cards-first not-started']"));

		 String cnt = notstartedbox.get(index).getText();
		 cntarray = cnt.split("\\n");
		 }
		 if(boxName.equals("In Progress"))
		 {
		 List<WebElement> inprogress = Driver.driver.findElements(By.cssSelector("div[class='ls-instructor-activity-cards ls-instructor-cards-second in-progress']"));

		 String cnt = inprogress.get(index).getText();
		 cntarray = cnt.split("\\n");
		 }
		 if(boxName.equals("Submitted"))
		 {
		 List<WebElement> submitted = Driver.driver.findElements(By.cssSelector("div[class='ls-instructor-activity-cards ls-instructor-cards-third submitted']"));

		 String cnt = submitted.get(index).getText();
		 cntarray = cnt.split("\\n");
		 }
		 if(boxName.equals("Graded"))
		 {
		 List<WebElement> graded = Driver.driver.findElements(By.cssSelector("div[class='ls-instructor-activity-cards ls-instructor-cards-fourth submitted']"));

		 String cnt = graded.get(index).getText();
		 cntarray = cnt.split("\\n");
		 }
		 if(boxName.equals("Reviewed"))
		 {
		 List<WebElement> graded = Driver.driver.findElements(By.cssSelector("div[class='ls-instructor-activity-cards ls-instructor-cards-fourth submitted']"));

		 String cnt = graded.get(index).getText();
		 cntarray = cnt.split("\\n");
		 }
		 return Integer.parseInt(cntarray[0]);

	}


	public void statusBoxCheckInInstructorDashBoard(int dataIndex)
	{
		try
		{
			String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
			String notStartedCount = ReadTestData.readDataByTagName("", "notstarted", Integer.toString(dataIndex));
			String inProgressCount = ReadTestData.readDataByTagName("", "inprogress", Integer.toString(dataIndex));
			String submittedCount = ReadTestData.readDataByTagName("", "submitted", Integer.toString(dataIndex));
			String reviewedCount = ReadTestData.readDataByTagName("", "reviewed", Integer.toString(dataIndex));
			String gradedCount = ReadTestData.readDataByTagName("", "graded", Integer.toString(dataIndex));
			String gradeable = ReadTestData.readDataByTagName("", "gradeable", Integer.toString(dataIndex));
			new Navigator().NavigateTo("Assignments");
			int index = 0;
			List<WebElement> assignments =  Driver.driver.findElements(By.cssSelector("span[class='ls-assignment-name instructor-assessment-review']"));
			 for(WebElement element : assignments)
			 {
				 if(element.getText().contains(assessmentname))
						 {
					 		break;
						 }
				 index++;
			 }

			 List<WebElement> notstarted = Driver.driver.findElements(By.cssSelector("div[class='ls-instructor-activity-cards ls-instructor-cards-first not-started']"));

			 List<WebElement> inprogress = Driver.driver.findElements(By.cssSelector("div[class='ls-instructor-activity-cards ls-instructor-cards-second in-progress']"));

			 List<WebElement> submitted = Driver.driver.findElements(By.cssSelector("div[class='ls-instructor-activity-cards ls-instructor-cards-third submitted']"));

			 List<WebElement> reviewed = Driver.driver.findElements(By.cssSelector("div[class='ls-instructor-activity-cards ls-instructor-cards-fourth submitted']"));

			 List<WebElement> graded = Driver.driver.findElements(By.cssSelector("div[class='ls-instructor-activity-cards ls-instructor-cards-fourth submitted']"));

			 if(!notstarted.get(index).getText().replaceAll("[\n\r]", "").equals(notStartedCount+"Not Started"))
				{
				 Assert.fail("Not Started box doesn't display the count of students who have not yet started the assignment");
				}
			 if(!inprogress.get(index).getText().replaceAll("[\n\r]", "").contains(inProgressCount+"In Progress"))
			 {
				 Assert.fail("In Progress box doesnt display the count of students who are currently taking the assignment");
			 }
			 if(!submitted.get(index).getText().replaceAll("[\n\r]", "").contains(submittedCount+"Submitted"))
			 {
				 Assert.fail("Submitted box doesnt display the count of Students who have submitted the assignments");
			 }
			 if(gradeable.equals("false"))
			 {
				 if(!reviewed.get(index).getText().replaceAll("[\n\r]", "").contains(reviewedCount+"Reviewed"))
				 {
					 Assert.fail("Reviewed box doesnt display the count of student for whom non gradable assessment has been reviewed");
			 	 }
			 }
			 else
			 {
				 if(!graded.get(index).getText().replaceAll("[\n\r]", "").contains(gradedCount+"Graded"))
				 {
					 Assert.fail("Graded box doesn't display the count of student for whom assessment has been graded");
				 }
			 }

		}
		catch(Exception e)
		{
			Assert.fail("Exception in app helper statusBoxCheckInInstructorDashBoard in class Assignment",e);
		}
	}

	public void releaseGrades(int dataIndex,String releaseAction)
	{
		try
		{
		String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
		new Navigator().NavigateTo("Assignments");
		int index = 0;
		List<WebElement> assignments =  Driver.driver.findElements(By.cssSelector("span[class='ls-assignment-name instructor-assessment-review']"));
		 for(WebElement element : assignments)
		 {
			 if(element.getText().contains(assessmentname))
					 {
				 		break;
					 }
			 index++;
		 }
		 List<WebElement> viewresponseslink = Driver.driver.findElements(By.cssSelector("span[class='ls-grade-book-assessment']"));
		   ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", viewresponseslink.get(index));
		 //viewresponseslink.get(2).click();

		 Driver.driver.findElement(By.cssSelector("div[title='"+releaseAction+"']")).click();
		}
		catch(Exception e)
		{
			Assert.fail("Exception in app helper releaseGrades in class Assignment",e);
		}
	}

	public void gradesValidation(int dataIndex)
	{
		try
		{
		String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
		new Navigator().NavigateTo("Assignments");
		new ComboBox().selectValue(3, "Graded");
		Thread.sleep(3000);
		int index = 0;
		List<WebElement> assignments =  Driver.driver.findElements(By.cssSelector("span[class='ls-assignment-name instructor-assessment-review']"));
		 for(WebElement element : assignments)
		 {
			 if(element.getText().contains(assessmentname))
					 {
				 		break;
					 }
			 index++;
		 }
		 List<WebElement> viewgradeslink = Driver.driver.findElements(By.cssSelector("span[class='ls-grade-book-assessment']"));
		 if( !viewgradeslink.get(index).isDisplayed())
			 Assert.fail("View Grades link not displayed");
		 if(!viewgradeslink.get(index).getText().equals("View Grades"))
			 Assert.fail("View Grades link text is not as expected");

		 List<WebElement> graphs = Driver.driver.findElements(By.className("ls-assignment-performance-graph")); //validating the grade graph by clicking it
		 ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", graphs.get(index));

		 List<WebElement> xaxis = Driver.driver.findElements(By.id("idb-stud-score-bar-label-left"));
		 if(!xaxis.get(index).getAttribute("title").equals("Number of Students"))
			 Assert.fail("x-axis of grade graph not shown as 'Number of Students'");

		 List<WebElement> yaxis = Driver.driver.findElements(By.id("idb-stud-score-bar-chart-footer-label"));
		 if(!yaxis.get(index).getAttribute("title").equals("Percentage Score Range"))
			 Assert.fail("y-axis of grade graph not shown as 'Score Range'");
		   ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", viewgradeslink.get(index));
		   Thread.sleep(3000);
		   if(!Driver.driver.findElement(By.className("idb-gradebook-header-div")).getText().equals("Assignment Responses"))
			   Assert.fail("The Assignment Responses tab not opened after clicking on the view responses link in the assignments page");
		 //viewresponseslink.get(2).click();


		}
		catch(Exception e)
		{
			Assert.fail("Exception in app helper gradesValidation in class Assignment",e);
		}
	}

	public void assignmentValidate(int dataIndex)
	{
	try
		{
			String fullName = ReadTestData.readDataByTagName("", "fullName", Integer.toString(dataIndex));
			String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
			List<String> stringarray = new ArrayList<String>();
			List<WebElement> allInstructorName = Driver.driver.findElements(By.className("ls-assignment-item-author-name"));
			for (WebElement name: allInstructorName)
				stringarray.add(name.getText());

			if(!stringarray.contains(fullName))
				Assert.fail("Instructor Name is not present after posting");


			stringarray.clear();

			List<WebElement> allAssesmentName = Driver.driver.findElements(By.cssSelector("span[class='ls-assignment-name instructor-assessment-review']"));

			for (WebElement name: allAssesmentName)
		    	stringarray.add(name.getText());
			String [] allNames = stringarray.toArray(new String[stringarray.size()]);
			boolean contains = false;
			for(int i =0 ; i<allNames.length; i++)
			{
				String str = allNames[i];
				if (str.contains(assessmentname)) {
			        contains = true;
			        break;
			    }
			}
			if(contains == false)
				Assert.fail("Assesment name is not present in Current Assignment page.");


		}
	catch(Exception e)
		{
		Assert.fail("Exception in  assignmentValidate in AppHelper class Assignment",e);
		}
	}

	public void provideGRadeToStudent(int dataIndex)
	{
		try
		{
			String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
			String studentname = ReadTestData.readDataByTagName("", "studentname", Integer.toString(dataIndex));


			//String gradeLinkIndex = ReadTestData.readDataByTagName("", "gradeLinkIndex", Integer.toString(dataIndex));
			new Navigator().NavigateTo("Assignments");

			int index = 0;
			List<WebElement> assignments =  Driver.driver.findElements(By.cssSelector("span[class='ls-assignment-name instructor-assessment-review']"));
			 for(WebElement element : assignments)
			 {
				 if(element.getText().contains(assessmentname))
						 {
					 		break;
						 }
				 index++;
			 }
			 List<WebElement> viewresponseslink = Driver.driver.findElements(By.cssSelector("span[class='ls-grade-book-assessment']"));
			   ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", viewresponseslink.get(index));
			   List<WebElement> menuitem = Driver.driver.findElements(By.cssSelector("span[class='idb-gradebook-content-label-text idb-gradebook-username']"));
			   for(WebElement user : menuitem)
			   {

				   if(user.getText().equals(studentname))
				   {
					   Locatable hoverItem = (Locatable) user;
						  Mouse mouse = ((HasInputDevices) Driver.driver).getMouse();
						   mouse.mouseMove(hoverItem.getCoordinates());
					   Driver.driver.findElement(By.id("idb-grade-now-link")).click();
					   Thread.sleep(2000);
					   Driver.driver.findElement(By.cssSelector("input[class='idb-grade-points']")).clear();
					   Thread.sleep(2000);
					   Driver.driver.findElement(By.cssSelector("input[class='idb-grade-points']")).sendKeys("0.6");
                       Thread.sleep(2000);
                       Driver.driver.findElement(By.cssSelector("input[class='idb-grade-points']")).sendKeys(Keys.TAB);
					   Thread.sleep(2000);
                       Driver.driver.findElement(By.id("ls-assignment-not-started-count")).click(); //clicking on not started box to save the marks
				   }
			   }


		}
		catch(Exception e)
		{
			Assert.fail("Exception in apphelper provideGRadeToStudent in class Assignment",e);
		}
	}

	public void likeAssignment(int dataIndex)
	{
		try
		{
			String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
			new Navigator().NavigateTo("Assignments");
			int index = 0;
			List<WebElement> assignments =  Driver.driver.findElements(By.cssSelector("span[class='ls-assignment-name instructor-assessment-review']"));
			 for(WebElement element : assignments)
			 {
				 if(element.getText().contains(assessmentname))
						 {
					 		break;
						 }
				 index++;
			 }
			List<WebElement> likelinks =  Driver.driver.findElements(By.className("ls-post-like-link"));
			if(!likelinks.get(index).getText().equals("Like")) //Validating if assignment is liked to unlike it
				Assert.fail("The assignment is already liked so can not like it again.");
			 ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", likelinks.get(index)); //Clicking on like

			 List<WebElement> likecounts = Driver.driver.findElements(By.className("ls-post-like-count"));
			 String likecount = likecounts.get(index).getText(); //Validating link count
				if(!likecount.equals("1"))
					Assert.fail("Like count of the assignment not equal to 1");

			List<WebElement> likelinksafterclicking = Driver.driver.findElements(By.className("ls-post-like-link"));
			String likelinkafterclicking = likelinksafterclicking.get(index).getText(); //Validating like changed to unlike
				if(!likelinkafterclicking.equals("Unlike"))
					Assert.fail("Like link not converted to unlike after clicking on it");

		}
		catch(Exception e)
		{
			Assert.fail("Exception in apphelper likeAssignment in class Assignment",e);
		}
	}

	public void unlikeAssignment(int dataIndex)
	{
		try
		{
			String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
			new Navigator().NavigateTo("Assignments");
			int index = 0;
			List<WebElement> assignments =  Driver.driver.findElements(By.cssSelector("span[class='ls-assignment-name instructor-assessment-review']"));
			 for(WebElement element : assignments)
			 {
				 if(element.getText().contains(assessmentname))
						 {
					 		break;
						 }
				 index++;
			 }
			List<WebElement> likelinks =  Driver.driver.findElements(By.className("ls-post-like-link"));
			if(!likelinks.get(index).getText().equals("Unlike")) //Validating if assignment is liked to unlike it
				Assert.fail("The assignment is not liked so can not unlike it.");
			 ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", likelinks.get(index)); //Clicking on unlike

			 List<WebElement> likecounts = Driver.driver.findElements(By.className("ls-post-like-count"));
			 String likecount = likecounts.get(index).getText(); //Validating link count
				if(!likecount.equals("0"))
					Assert.fail("Like count of the assignment not equal to 1");

			List<WebElement> likelinksafterclicking = Driver.driver.findElements(By.className("ls-post-like-link"));
			String likelinkafterclicking = likelinksafterclicking.get(index).getText(); //Validating like changed to unlike
				if(!likelinkafterclicking.equals("Like"))
					Assert.fail("Unlike link not converted to Like after clicking on it");

		}
		catch(Exception e)
		{
			Assert.fail("Exception in apphelper likeAssignment in class Assignment",e);
		}
	}


	public void commentAssignment(int dataIndex)
	{
		try
		{
			String random = new RandomString().randomstring(5);
			String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
			new Navigator().NavigateTo("Assignments");
			int index = 0;
			List<WebElement> assignments =  Driver.driver.findElements(By.cssSelector("span[class='ls-assignment-name instructor-assessment-review']"));
			 for(WebElement element : assignments)
			 {
				 if(element.getText().contains(assessmentname))
						 {
					 		break;
						 }
				 index++;
			 }
			 List<WebElement> commentarea = Driver.driver.findElements(By.className("ls-textarea-focus"));
			 commentarea.get(index).sendKeys(random+Keys.ENTER);
			 Thread.sleep(3000);
			//Searching for comment posted
			 boolean commentfound = false;
			 List<WebElement> comments = Driver.driver.findElements(By.className("ls-stream-post__comment-text"));
			 for(WebElement comment : comments)
			 {
				 if(comment.getText().contains(random))  //using contains because gettext is returning the given name along with text posted
				 {
					 commentfound = true;
					 break;
				 }
			 }
			 if(commentfound == false)
				 Assert.fail("Comment posted on assignment by instructor not found");


		}
		catch(Exception e)
		{
			Assert.fail("Exception in apphelper likeAssignment in class Assignment",e);
		}
	}

	public int commentCount(int dataIndex)
	{
		String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
		new Navigator().NavigateTo("Assignments");
		int index = 0;
		List<WebElement> assignments =  Driver.driver.findElements(By.cssSelector("span[class='ls-assignment-name instructor-assessment-review']"));
		 for(WebElement element : assignments)
		 {
			 if(element.getText().contains(assessmentname))
					 {
				 		break;
					 }
			 index++;
		 }
		 List<WebElement> commentscount = Driver.driver.findElements(By.className("ls-stream-post-comment-count"));
		return Integer.parseInt(commentscount.get(index).getText());
	}

	public void assignFormValidate(int dataIndex)
	{
		try
		{
		String assignmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
		Driver.driver.findElement(By.cssSelector("span.ins-assignment-button-sprite.instructor-assignment-new")).click();
		Thread.sleep(3000);
		if(Driver.driver.findElement(By.cssSelector("div[class='tab active']")).getText().equals("My Resources"))	//Opening All Resources tab if not opened after clicking on New Assignment button
			Driver.driver.findElement(By.className("tab")).click();

		//Adding assignment to search
		Driver.driver.findElement(By.id("all-resource-search-textarea")).clear();
		Driver.driver.findElement(By.id("all-resource-search-textarea")).sendKeys("\"" + assignmentname + "\"");
		Driver.driver.findElement(By.cssSelector("#all-resource-search-button > span.ins-resource-search-sprite.instructor-resource-search-img")).click();
		Thread.sleep(3000);
        Driver.driver.findElement(By.cssSelector("span[title='Assign This']")).click();
		/*List<WebElement> assign = Driver.driver.findElements(By.className("assign-this"));

		for(WebElement assignment: assign)
		{

			if(assignment.getText().trim().equals("Assign This"))
			{

				assignment.click();
				break;
			}

		}*/
		}
		catch(Exception e)
		{
			Assert.fail("Exception in app helper assignFormValidate in class Assignment ",e);
		}
	}

	public void provideFeedback(int dataIndex)
	{
		try
		{
			String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
			String studentname = ReadTestData.readDataByTagName("", "shareName", Integer.toString(dataIndex));
			new Navigator().NavigateTo("Assignments");
			int index = 0;
			//finding the index of the current assessment
			List<WebElement> assignments =  Driver.driver.findElements(By.cssSelector("span[class='ls-assignment-name instructor-assessment-review']"));
			 for(WebElement element : assignments)
			 {
				 if(element.getText().contains(assessmentname))
						 {
					 		break;
						 }
				 index++;
			 }
			 List<WebElement> viewresponseslink = Driver.driver.findElements(By.cssSelector("span[class='ls-grade-book-assessment']"));
			   ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", viewresponseslink.get(index));
			   Thread.sleep(3000);

			   //finding the index of the particular student
			 int index1 = 0;
			 List<WebElement> usernames =  Driver.driver.findElements(By.cssSelector("span[class='idb-gradebook-content-label-text idb-gradebook-username']"));
				 for(WebElement element : usernames)
				 {
					 if(element.getText().equals(studentname))
							 {

						 		break;
							 }
					 index1++;
				 }


		    List<WebElement> gradebook = Driver.driver.findElements(By.className("idb-question-score-wrapper"));

					 Locatable hoverItem = (Locatable) gradebook.get(index1);
					 Mouse mouse = ((HasInputDevices) Driver.driver).getMouse();
			mouse.mouseMove(hoverItem.getCoordinates());
		  ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.className("ls-view-response-link")));

			Driver.driver.findElement(By.id("view-user-question-performance-feedback-box")).sendKeys("This is a FeedbackText");

			Driver.driver.findElement(By.className("view-user-question-performance-save-btn")).click();

		}
		catch(Exception e)
		{
			Assert.fail("Exception in  provideFeedback in AppHelper class Assignment",e);
		}
	}

	public void submitAssignmentAsStudentFromAssignmentsnavigation(int dataIndex)
	{
		try
		{
		String submitassignment = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
		new Navigator().NavigateTo("Assignments");
		List<WebElement> allElements=Driver.driver.findElements(By.cssSelector("span[class='ls-assignment-name instructor-assessment-review']"));
		for(WebElement element: allElements)
		{
			if(element.getText().trim().contains(submitassignment))
			{
				element.click();
				int helppage = Driver.driver.findElements(By.className("close-help-page")).size();
				if(helppage == 1)
		    	Driver.driver.findElement(By.className("close-help-page")).click();
				Thread.sleep(3000);
				Driver.driver.findElement(By.className("qtn-label")).click();
				Thread.sleep(3000);
				Driver.driver.findElement(By.linkText("Submit")).click(); //submitting the assignment as student 1
				Thread.sleep(3000);
				break;
			}
		}
		}
		catch(Exception e)
		{
			Assert.fail("Exception in app helper submitAssignmentAsStudent in class Assignment",e);
		}
	}

	public void clickonAssignment(String text)
	{
		try
		{
			List<WebElement> allelements=Driver.driver.findElements(By.cssSelector("span[class='ls-assignment-name instructor-assessment-review']"));
			for(WebElement elements:allelements)
			{
				String assigntext=elements.getText();

				if(assigntext.contains(text))
				{

					((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", elements);
					//elements.click();
					int helppage = Driver.driver.findElements(By.className("close-help-page")).size();
				     if(helppage!=0)
				    	 Driver.driver.findElement(By.className("close-help-page")).click();
				     break;
				}
			}
		}
		catch(Exception e)
		{
			Assert.fail("Exception in apphelper clickonAssignment in class Assignments",e);
		}
	}
	public void createnumberofAssignments(String instructor1dataindex,String instructor2dataindex,String instructor3dataindex,int numberofassignment,String studentid)
	{
		try
		{
			if(numberofassignment==1)
			{
				new Assignment().create(Integer.parseInt(instructor1dataindex));
				new com.snapwiz.selenium.tests.staf.learnon.apphelper.LoginUsingLTI().ltiLogin(studentid);
				new com.snapwiz.selenium.tests.staf.learnon.apphelper.LoginUsingLTI().ltiLogin(instructor1dataindex);
				new Assignment().assignToStudent(Integer.parseInt(instructor1dataindex));

			}
			if(numberofassignment==2)
			{
				new Assignment().create(Integer.parseInt(instructor1dataindex));
				new Assignment().create(Integer.parseInt(instructor2dataindex));
				new com.snapwiz.selenium.tests.staf.learnon.apphelper.LoginUsingLTI().ltiLogin(studentid);
				new com.snapwiz.selenium.tests.staf.learnon.apphelper.LoginUsingLTI().ltiLogin(instructor1dataindex);
				new Assignment().assignToStudent(Integer.parseInt(instructor1dataindex));
				new com.snapwiz.selenium.tests.staf.learnon.apphelper.LoginUsingLTI().ltiLogin(instructor2dataindex);
				new Assignment().assignToStudent(Integer.parseInt(instructor2dataindex));

			}
			if(numberofassignment==3)
			{
				new Assignment().create(Integer.parseInt(instructor1dataindex));
				new Assignment().create(Integer.parseInt(instructor2dataindex));
				new Assignment().create(Integer.parseInt(instructor3dataindex));
				new com.snapwiz.selenium.tests.staf.learnon.apphelper.LoginUsingLTI().ltiLogin(studentid);
				new com.snapwiz.selenium.tests.staf.learnon.apphelper.LoginUsingLTI().ltiLogin(instructor1dataindex);
				new Assignment().assignToStudent(Integer.parseInt(instructor1dataindex));
				new com.snapwiz.selenium.tests.staf.learnon.apphelper.LoginUsingLTI().ltiLogin(instructor2dataindex);
				new Assignment().assignToStudent(Integer.parseInt(instructor3dataindex));
				new LoginUsingLTI().ltiLogin(instructor2dataindex);
				new Assignment().assignToStudent(Integer.parseInt(instructor3dataindex));


			}

		}
		catch(Exception e)
		{
			Assert.fail("Exception in apphelper createnumberofAssignments in class Assignments");
		}
	}

	public void createAssignmentAtTopicLevel( int dataIndex)
	{
			try
			{
			String course = Config.course;
			String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
			String questionset = ReadTestData.readDataByTagName("", "questionset", Integer.toString(dataIndex));
			String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));
			String questiontype = ReadTestData.readDataByTagName("", "questiontype", Integer.toString(dataIndex));
			String chapterName = ReadTestData.readDataByTagName("", "chapterName", Integer.toString(dataIndex));
			String topicname = ReadTestData.readDataByTagName("", "topicname", Integer.toString(dataIndex));
			String difficultylevel = ReadTestData.readDataByTagName("", "difficultylevel", Integer.toString(dataIndex));
			String learningobjective = ReadTestData.readDataByTagName("", "learningobjective", Integer.toString(dataIndex));
			new com.snapwiz.selenium.tests.staf.learnon.apphelper.DirectLogin().CMSLogin();
			String title=Driver.driver.getTitle();
			if(title.equals("Course Content Details"))
				{
				 Driver.driver.findElement(By.cssSelector("img[alt=\""+course+"\"]")).click();
				 int index = 0;
				 //Find the chapter index
				 List <WebElement> allChapters = Driver.driver.findElements(By.xpath("//*[starts-with(@class, 'course-chapter-label')]"));
				 if(chapterName != null)
				 {
				 for(WebElement element : allChapters)
				 {
					 if(element.getText().equals(chapterName))
							 {
						 		break;
							 }
					 index++;
				 }
				 }
				 else
				 {
					 allChapters.get(0).click();
					 index = 0;
				 }
					 //Find the topic under a chapter and click on it
				 List <WebElement> expansionSymbol = Driver.driver.findElements(By.xpath("//*[starts-with(@class, 'expand-chapter-tree expand')]"));
				 expansionSymbol.get(index).click();
				 Thread.sleep(3000);
				 //List the topic and click on a topic
				 List <WebElement> allTopic = Driver.driver.findElements(By.xpath("//*[starts-with(@class, 'course-topic-label node')]"));
				 if(topicname != null)
				 {
				 for(WebElement topic: allTopic)
				 {
					 if(topic.getText().equals(topicname))
					 {
						 topic.click();
						 Thread.sleep(3000);
						 break;
					 }

				 }
				 }
				 else
					 allTopic.get(0).click();
				 Driver.driver.findElement(By.cssSelector("div.create-practice")).click();
				 Driver.driver.findElement(By.id("assessmentName")).click();
				 Driver.driver.findElement(By.id("assessmentName")).clear();
				 Driver.driver.findElement(By.id("assessmentName")).sendKeys(assessmentname);
				 Driver.driver.findElement(By.id("questionSetName")).clear();
				 Driver.driver.findElement(By.id("questionSetName")).sendKeys(questionset);

				 Driver.driver.findElement(By.id("qtn-type-true-false-img")).click();


				 Driver.driver.findElement(By.id("question-raw-content")).click();


				 Driver.driver.switchTo().frame("iframe-question-edit").findElement(By.xpath("/html/body")).sendKeys(questiontext);
				 Driver.driver.switchTo().defaultContent();

				 Actions action = new Actions(Driver.driver);
			        WebElement we = Driver.driver.findElement(By.id("choice1"));
			        action.moveToElement(we).build().perform();
				 Driver.driver.findElement(By.xpath("//img[@title='Set it as Correct Answer']")).click();
				 Thread.sleep(3000);
				 new ComboBox().selectValue(3, questiontype);
				 int popup = Driver.driver.findElements(By.cssSelector("div[class='cms-notification-message-body']")).size();
				 if(popup == 0)
				 {
				 if(difficultylevel != null)
				 {
					 new ComboBox().selectValue(7, difficultylevel);
				 }
				 if(learningobjective != null)
				 {
					 Driver.driver.findElement(By.id("learing-objectives-span")).click();
					 Driver.driver.findElement(By.xpath("html/body/div[8]/div/div[2]/div/div[1]/div[3]/div/div/div[4]/div/div[3]/div[1]/div/div[1]/label")).click();
					 Driver.driver.findElement(By.cssSelector("span.add-collection-to-subtopic")).click();
					 Driver.driver.findElement(By.cssSelector("span.cancel-collection")).click();

				 }
				 Driver.driver.findElement(By.xpath("//*[@id='instructor-only-check-box-div']/label")).click(); //checking 'Enable for intructor only'
				 new ComboBox().selectValue(4, "Publish");

				 Driver.driver.findElement(By.id("saveQuestionDetails1")).click();
				 }//if condition for popup ends here

				}

			}
			catch(Exception e)
			{
				Assert.fail("Exception in  createAssignmentAtTopicLevel in AppHelper class Assignment",e);
			}
	}


	public void clickViewResponse(String assessmentname)
	{
		try
		{
			new Navigator().NavigateTo("Assignments");
			int index = 0;
			List<WebElement> assignments =  Driver.driver.findElements(By.cssSelector("span[class='ls-assignment-name instructor-assessment-review']"));
			 for(WebElement element : assignments)
			 {
				 if(element.getText().contains(assessmentname))
						 {
					 		break;
						 }
				 index++;
			 }
			 //click on 'View Responses link'
			 List<WebElement> viewresponseslink = Driver.driver.findElements(By.cssSelector("span[class='ls-grade-book-assessment']"));
			   ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", viewresponseslink.get(index));
			   Thread.sleep(3000);
		}
		catch(Exception e)
		{
			Assert.fail("Exception in  clickViewResponse in AppHelper class Assignment",e);
		}
	}
	public void postCommentInAssessmentResponseTab()
	{
		try
		{
			List<WebElement> comments = Driver.driver.findElements(By.cssSelector("a[class='ls-stream-post__footer-comment-link js-toggle-comments']"));
			 comments.get(1).click();
			 String random = new RandomString().randomstring(5);
			//Post a comment
			 List<WebElement> commentarea = Driver.driver.findElements(By.className("ls-textarea-focus"));
			 commentarea.get(1).sendKeys(random + Keys.ENTER);
			//Searching for comment posted
			 boolean commentfound = false;
			 List<WebElement> postedcomments = Driver.driver.findElements(By.className("ls-stream-post__comment-text"));
			 for(WebElement comment : postedcomments)
			 {
				 if(comment.getText().contains(random))  //using contains because gettext is returning the given name along with text posted
				 {
					 commentfound = true;
					 break;
				 }
			 }
			 if(commentfound == false)
				 Assert.fail("Comment posted on assignment by instructor not found");
		}
		catch(Exception e)
		{
			Assert.fail("Exception in  postCommentInAssessmentResponseTab in AppHelper class Assignment",e);
		}
	}

	public void provideGradeToStudentForMultipleQuestions(int dataIndex)
	{
		try
		{
			String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
			String studentname = ReadTestData.readDataByTagName("", "studentname", Integer.toString(dataIndex));
			String grade = ReadTestData.readDataByTagName("", "grade", Integer.toString(dataIndex));
			new Navigator().NavigateTo("Assignments");
			int index = 0;
			List<WebElement> assignments =  Driver.driver.findElements(By.cssSelector("span[class='ls-assignment-name instructor-assessment-review']"));
			 for(WebElement element : assignments)
			 {
				 if(element.getText().contains(assessmentname))
						 {
					 		break;
						 }
				 index++;
			 }
			 List<WebElement> viewresponseslink = Driver.driver.findElements(By.cssSelector("span[class='ls-grade-book-assessment']"));
			   ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", viewresponseslink.get(index));
			   List<WebElement> menuitem = Driver.driver.findElements(By.cssSelector("span[class='idb-gradebook-content-label-text idb-gradebook-username']"));
			   int gradeLinkIndex = 0;
			   for(WebElement user : menuitem)
			   {
				   if(user.getText().equals(studentname))
				   {
					   Locatable hoverItem = (Locatable) user;
						  Mouse mouse = ((HasInputDevices) Driver.driver).getMouse();
						   mouse.mouseMove(hoverItem.getCoordinates());
						   List<WebElement> gradenowlinks = Driver.driver.findElements(By.id("idb-grade-now-link"));
						   gradenowlinks.get(gradeLinkIndex).click();
					   Thread.sleep(3000);
					   List<WebElement> allGradeBox = Driver.driver.findElements(By.cssSelector("input[class='idb-grade-points']"));
					   for(WebElement gradeBox: allGradeBox)
					   {
						   Thread.sleep(3000);
						   gradeBox.clear();
						   Thread.sleep(3000);
						   if(grade == null)
						   {
						   gradeBox.sendKeys("0.7");
						   Thread.sleep(3000);
						   gradeBox.sendKeys(Keys.TAB);
						   }
						   else
						   {
							   Thread.sleep(3000);
							   gradeBox.sendKeys(""+grade+""+Keys.TAB);
						   }
						   Thread.sleep(3000);

					   }
					   Driver.driver.findElement(By.xpath("/html/body")).click();
					   Thread.sleep(3000);

				   }
				   gradeLinkIndex++;
			   }

		}
		catch(Exception e)
		{
			Assert.fail("Exception in apphelper provideGradeToStudentForMultipleQuestions in class Assignment",e);
		}
	}
	public void addQuestionsWithCustomizedQuestionText(int dataIndex,String questionType,String assignmentname, int noOfQuestions)
	{
		try
		{
		String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));
		String optiontext = ReadTestData.readDataByTagName("", "optiontext", Integer.toString(dataIndex));
		String answertext = ReadTestData.readDataByTagName("", "answertext", Integer.toString(dataIndex));
		String numerictext = ReadTestData.readDataByTagName("", "numerictext", Integer.toString(dataIndex));
		String unitoption = ReadTestData.readDataByTagName("", "unitoption", Integer.toString(dataIndex));
		String tolrence = ReadTestData.readDataByTagName("", "tolrence", Integer.toString(dataIndex));
		String maplenumeric = ReadTestData.readDataByTagName("", "maplenumeric", Integer.toString(dataIndex));
		String hint = ReadTestData.readDataByTagName("", "hint", Integer.toString(dataIndex));
		String solution = ReadTestData.readDataByTagName("", "solution", Integer.toString(dataIndex));
		String course = Config.course;
		for(int i = 2; i < noOfQuestions+2; i++){
		new com.snapwiz.selenium.tests.staf.learnon.apphelper.DirectLogin().CMSLogin();
		String title=Driver.driver.getTitle();
		if(title.equals("Course Content Details"))
			{
			Driver.driver.findElement(By.cssSelector("img[alt=\""+course+"\"]")).click();
			Driver.driver.findElement(By.cssSelector("div.course-chapter-label.node")).click();
			boolean assignmentExists = false;
			List<WebElement> elements = Driver.driver.findElements(By.className("collection-assessment-name"));
			for(WebElement content : elements)
				{
				if(content.getText().trim().equals(assignmentname))
				{

					Thread.sleep(3000);
					((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", content);
					assignmentExists = true;
            		break;
				}
				}
			if(assignmentExists == true)
			{
			 	Driver.driver.findElement(By.id("questionOptions")).click();
			 	Thread.sleep(2000);
			 	((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.id("addQuestion")));
			 //	Driver.driver.findElement(By.id("addQuestion")).click();
			 	Thread.sleep(2000);
			 	Driver.driver.findElement(By.id(questionType)).click();
			 	Thread.sleep(2000);


		if(questionType.equals("qtn-type-true-false-img")) //1.For true-false type question
				{
			 	Driver.driver.findElement(By.id("question-raw-content")).click();
			 	Driver.driver.switchTo().frame("iframe-question-edit").findElement(By.xpath("/html/body")).sendKeys(questiontext+" "+i);
			 	Driver.driver.findElement(By.cssSelector("body")).click();	//click outside the textbox
			 	Driver.driver.switchTo().defaultContent();
			 	//Setting choice 1 as correct answer
			 	Actions action = new Actions(Driver.driver);
		        WebElement we = Driver.driver.findElement(By.id("choice1"));
		        action.moveToElement(we).build().perform();
		        ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.xpath("//img[@title='Set it as Correct Answer']")));
				}
		if(questionType.equals("qtn-multiple-choice-img")) //2. For multiple choice question.
		{
			Driver.driver.findElement(By.id("question-raw-content")).click();
		 	Driver.driver.switchTo().frame("iframe-question-edit").findElement(By.xpath("/html/body")).sendKeys(questiontext);
		 	Driver.driver.switchTo().defaultContent();
		 	//Adding choice 1
		 	Driver.driver.findElement(By.id("choice1")).click();
		 	Driver.driver.switchTo().frame("iframe-choice1").findElement(By.xpath("/html/body")).sendKeys(answertext);
		 	Driver.driver.switchTo().defaultContent();
		 	//Adding choice 2
		 	Driver.driver.findElement(By.id("choice2")).click();
		 	Driver.driver.switchTo().frame("iframe-choice2").findElement(By.xpath("/html/body")).sendKeys(answertext);
		 	Driver.driver.switchTo().defaultContent();
		 	//Adding choice 3
		 	Driver.driver.findElement(By.id("choice3")).click();
		 	Driver.driver.switchTo().frame("iframe-choice3").findElement(By.xpath("/html/body")).sendKeys(answertext);
		 	Driver.driver.switchTo().defaultContent();
		 	//Adding choice 4
		 	Driver.driver.findElement(By.id("choice4")).click();
		 	Driver.driver.switchTo().frame("iframe-choice4").findElement(By.xpath("/html/body")).sendKeys(answertext);
		 	Driver.driver.switchTo().defaultContent();
		 	//Setting choice 2 as correct answer
		 	Actions action = new Actions(Driver.driver);
	        WebElement we = Driver.driver.findElement(By.id("choice2"));
	        action.moveToElement(we).build().perform();
		    Driver.driver.findElement(By.xpath("//img[@title='Set it as Correct Answer']")).click();
			}
		if(questionType.equals("qtn-multiple-selection-img"))  //3. For multiple sections
		{
			Driver.driver.findElement(By.id("question-raw-content")).click();
		 	Driver.driver.switchTo().frame("iframe-question-edit").findElement(By.xpath("/html/body")).sendKeys(questiontext);
		 	Driver.driver.switchTo().defaultContent();
		 	//Adding choice 1
		 	Driver.driver.findElement(By.id("choice1")).click();
		 	Driver.driver.switchTo().frame("iframe-choice1").findElement(By.xpath("/html/body")).sendKeys(answertext);
		 	Driver.driver.switchTo().defaultContent();
		 	//Adding choice 2
		 	Driver.driver.findElement(By.id("choice2")).click();
		 	Driver.driver.switchTo().frame("iframe-choice2").findElement(By.xpath("/html/body")).sendKeys(answertext);
		 	Driver.driver.switchTo().defaultContent();
		 	//Adding choice 3
		 	Driver.driver.findElement(By.id("choice3")).click();
		 	Driver.driver.switchTo().frame("iframe-choice3").findElement(By.xpath("/html/body")).sendKeys(answertext);
		 	Driver.driver.switchTo().defaultContent();
		 	//Adding choice 4
		 	Driver.driver.findElement(By.id("choice4")).click();
		 	Driver.driver.switchTo().frame("iframe-choice4").findElement(By.xpath("/html/body")).sendKeys(answertext);
		 	Driver.driver.switchTo().defaultContent();
		 	//Setting choice 2 as correct answer
		 	Actions action1 = new Actions(Driver.driver);
	        WebElement we1 = Driver.driver.findElement(By.id("choice2"));
	        action1.moveToElement(we1).build().perform();
		    Driver.driver.findElement(By.xpath("//img[@title='Set it as Correct Answer']")).click();
		   //Setting choice 4 as correct answer
		 	Actions action2 = new Actions(Driver.driver);
	        WebElement we2 = Driver.driver.findElement(By.id("choice4"));
	        action2.moveToElement(we2).build().perform();
		    Driver.driver.findElement(By.xpath("//img[@title='Set it as Correct Answer']")).click();
			}
		if(questionType.equals("qtn-text-entry-img")) // 4.For Text Entry type.
		{
			Driver.driver.findElement(By.id("question-raw-content")).click();
		 	Driver.driver.switchTo().frame("iframe-question-edit").findElement(By.xpath("/html/body")).sendKeys(questiontext);
		 	Driver.driver.switchTo().defaultContent();
		 	// Adding correct answer choice
		 	Driver.driver.findElement(By.cssSelector("input[id='correct-ans-text']")).click();
		 	Driver.driver.findElement(By.cssSelector("input[id='correct-ans-text']")).clear();
		    Driver.driver.findElement(By.cssSelector("input[id='correct-ans-text']")).sendKeys(answertext);
		    Thread.sleep(3000);
			// Rest two boxes are filled automatically.
		    //Adding alternate answer choice.
		    Driver.driver.findElement(By.id("right-alt-container-1")).click();
		    Driver.driver.findElement(By.cssSelector("input[id='alternate-answer-text']")).click();
		 	Driver.driver.findElement(By.cssSelector("input[id='alternate-answer-text']")).clear();
		    Driver.driver.findElement(By.cssSelector("input[id='alternate-answer-text']")).sendKeys(optiontext);

		    Driver.driver.findElement(By.id("done-button")).click(); // Accept answer
		 	  Thread.sleep(2000);
		}
		if(questionType.equals("qtn-text-entry-drop-down-img")) //5. For text entry drop down
		{
			Driver.driver.findElement(By.id("question-raw-content")).click();
		 	Driver.driver.switchTo().frame("iframe-question-edit").findElement(By.xpath("/html/body")).sendKeys(questiontext);
		 	Driver.driver.switchTo().defaultContent();
		 	// Adding Entry 1
		 	Driver.driver.findElement(By.id("entry-0")).click();
		 	Driver.driver.findElement(By.id("unit-name-edit-entry-0")).clear();
		 	Driver.driver.findElement(By.id("unit-name-edit-entry-0")).sendKeys(optiontext);

		 	  // Accepting answer
		 	WebElement menuitem = Driver.driver.findElement(By.id("entry-1"));
        	Locatable hoverItem = (Locatable) menuitem;
        	Mouse mouse = ((HasInputDevices) Driver.driver).getMouse();
        	mouse.mouseMove(hoverItem.getCoordinates());
        	List<WebElement> selectanswerticks = Driver.driver.findElements(By.className("mark-selected"));
        	selectanswerticks.get(1).click(); //select second option as correct answer


		 	Thread.sleep(2000);
		   // Adding Entry 2
		 	Actions action = new Actions(Driver.driver);
		 	action.doubleClick(Driver.driver.findElement(By.id("entry-1")));
		 	action.perform();
		 	Driver.driver.findElement(By.id("unit-name-edit-entry-1")).clear();
		 	Driver.driver.findElement(By.id("unit-name-edit-entry-1")).sendKeys(optiontext);



		   // Adding Entry 3
		 	action.doubleClick(Driver.driver.findElement(By.id("entry-2")));
		 	action.perform();
		 	Driver.driver.findElement(By.id("unit-name-edit-entry-2")).clear();
		 	Driver.driver.findElement(By.id("unit-name-edit-entry-2")).sendKeys(optiontext);

		 	//clicking on add more entry
		 	Driver.driver.findElement(By.id("add-new-entry")).click();
		 	//Adding entry 4
		 	action.doubleClick(Driver.driver.findElement(By.id("entry-3")));
		 	Driver.driver.findElement(By.id("unit-name-edit-entry-3")).clear();
		 	Driver.driver.findElement(By.id("unit-name-edit-entry-3")).sendKeys(optiontext);

		 	// Accepting answer
		 	 Driver.driver.findElement(By.id("done-button")).click();
		}

		if(questionType.equals("qtn-text-entry-numeric-img")) // 6. For numeric text entry
		{
			Driver.driver.findElement(By.id("question-raw-content")).click();
		 	Driver.driver.switchTo().frame("iframe-question-edit").findElement(By.xpath("/html/body")).sendKeys(questiontext);
		 	Driver.driver.switchTo().defaultContent();

		 	// Adding correct answer choice
		 	Driver.driver.findElement(By.cssSelector("input[id='correct-ans-text']")).click();
		 	Driver.driver.findElement(By.cssSelector("input[id='correct-ans-text']")).clear();
		    Driver.driver.findElement(By.cssSelector("input[id='correct-ans-text']")).sendKeys(numerictext);

		    Thread.sleep(3000);
		 	// Rest two boxes are filled automatically.
		  //Adding alternate answer choice.
		    Driver.driver.findElement(By.id("right-alt-container-1")).click();
		    Driver.driver.findElement(By.cssSelector("input[id='alternate-answer-text']")).click();
		 	Driver.driver.findElement(By.cssSelector("input[id='alternate-answer-text']")).clear();
		    Driver.driver.findElement(By.cssSelector("input[id='alternate-answer-text']")).sendKeys(optiontext);
		    // Adding tolerance
		    Driver.driver.findElement(By.id("tolerance-ans-text")).click();
		 	Driver.driver.findElement(By.id("tolerance-ans-text")).sendKeys(tolrence);
		 	Thread.sleep(2000);
		    Driver.driver.findElement(By.id("done-button")).click(); // Accept answer
		 	  Thread.sleep(2000);
		 	Driver.driver.findElement(By.id("display-write-board-checkbox")).click(); // Allow for using white board.
		}
		if(questionType.equals("qtn-text-entry-numeric-units-img")) //7. Adding text entry numeric with units.
		{
			Driver.driver.findElement(By.id("question-raw-content")).click();
		 	Driver.driver.switchTo().frame("iframe-question-edit").findElement(By.xpath("/html/body")).sendKeys(questiontext);
		 	Driver.driver.switchTo().defaultContent();
		 	// Adding correct answer choice
		 	Driver.driver.findElement(By.cssSelector("input[id='correct-ans-text']")).click();
		 	Driver.driver.findElement(By.cssSelector("input[id='correct-ans-text']")).clear();
		    Driver.driver.findElement(By.cssSelector("input[id='correct-ans-text']")).sendKeys(numerictext);

		    Thread.sleep(3000);
		 	// Rest two boxes are filled automatically.
		    Driver.driver.findElement(By.id("add-more-entry")).click();
		    Thread.sleep(3000);
		    // Selecting particular unit
		    List<WebElement> unitvalues = Driver.driver.findElements(By.tagName("li"));
		    for(WebElement units : unitvalues)
		    {
		    	if(units.getText().equals(unitoption))
		    	{
		    		units.click();
		    		break;
		    	}
		    }
		    Driver.driver.findElement(By.id("done-button")).click(); // Accept answer
		 	  Thread.sleep(2000);
		 	Driver.driver.findElement(By.id("display-write-board-checkbox")).click(); // Allow for using white board.
		}

		if(questionType.equals("qtn-numeric-maple-img")) //8. Adding maple numeric question
		{
			Driver.driver.findElement(By.id("question-raw-content")).click();
		 	Driver.driver.switchTo().frame("iframe-question-edit").findElement(By.xpath("/html/body")).sendKeys(questiontext);
		 	Driver.driver.switchTo().defaultContent();
		 	// Adding correct answer choice
		 	Driver.driver.findElement(By.cssSelector("input[id='correct-ans-text']")).click();
		 	Driver.driver.findElement(By.cssSelector("input[id='correct-ans-text']")).clear();
		    Driver.driver.findElement(By.cssSelector("input[id='correct-ans-text']")).sendKeys(maplenumeric);

		    Thread.sleep(3000);
		 	// Rest two boxes are filled automatically.
		    //Adding alternate answer choice.
		    Driver.driver.findElement(By.id("right-alt-container-1")).click();
		    Driver.driver.findElement(By.cssSelector("input[id='alternate-answer-text']")).click();
		 	Driver.driver.findElement(By.cssSelector("input[id='alternate-answer-text']")).clear();
		    Driver.driver.findElement(By.cssSelector("input[id='alternate-answer-text']")).sendKeys(optiontext);
		    // Adding tolerance
		    Driver.driver.findElement(By.id("tolerance-ans-text")).click();
		 	Driver.driver.findElement(By.id("tolerance-ans-text")).sendKeys(tolrence);
		 	Thread.sleep(2000);
		 	 // Accept answer
		    Driver.driver.findElement(By.id("done-button")).click();
		 	  Thread.sleep(2000);
		 	// Allow for using white board.
		 	Driver.driver.findElement(By.id("display-write-board-checkbox")).click();
		}

		if(questionType.equals("qtn-math-symbolic-notation-img")) // 9. Adding maple symbolic question
		{
			Driver.driver.findElement(By.id("question-raw-content")).click();
		 	Driver.driver.switchTo().frame("iframe-question-edit").findElement(By.xpath("/html/body")).sendKeys(questiontext);
		 	Driver.driver.switchTo().defaultContent();
		 	Thread.sleep(3000);
		 	// Adding Correct answer
		    Driver.driver.findElement(By.cssSelector("div[class='right-container math-correct-answer-input-container']")).click();
		    Thread.sleep(5000);
		    Driver.driver.findElement(By.cssSelector("button[title='Square root']")).click();
	        Driver.driver.findElement(By.cssSelector("input[class='wrs_focusElement']")).sendKeys(numerictext);
	        Driver.driver.findElement(By.id("wiris-container-save-formaulaeditor")).click();

		 	// Adding Alternate answer
		 	Driver.driver.findElement(By.id("right-alt-container-1")).click();
		 	Driver.driver.findElement(By.id("alt1")).click();
		    Thread.sleep(5000);
		 	Driver.driver.findElement(By.cssSelector("button[title='Square root']")).click();
	        Driver.driver.findElement(By.cssSelector("input[class='wrs_focusElement']")).sendKeys(numerictext);
	        Driver.driver.findElement(By.id("wiris-container-save-formaulaeditor")).click();
		 	 // Accept answer
		    Driver.driver.findElement(By.id("done-button")).click();
		 	  Thread.sleep(2000);
		 	// Allow for using white board.
		 	Driver.driver.findElement(By.id("display-write-board-checkbox")).click();
		 	Thread.sleep(5000);
		}

		if(questionType.equals("qtn-essay-type")) // 10. Adding Essay type question
		{
			Driver.driver.findElement(By.id("question-raw-content")).click();
		 	Driver.driver.switchTo().frame("iframe-question-edit").findElement(By.xpath("/html/body")).sendKeys(questiontext);
		 	Driver.driver.switchTo().defaultContent();
		 	//Adding height for text entry.
		 	Driver.driver.findElement(By.id("essay-question-height")).click();
		 	Driver.driver.findElement(By.id("essay-question-height")).sendKeys(numerictext);
		 	// Allow for using white board.
		 	Driver.driver.findElement(By.id("display-write-board-checkbox")).click();
		}

		}
		    //enter Hint
		 	if(hint != null)
		 		{
                    Driver.driver.findElement(By.cssSelector("#hint > #question-raw-content")).click();
                    Thread.sleep(3000);
                    Driver.driver.switchTo().frame("iframe-hint").findElement(By.xpath("/html/body")).sendKeys(hint);//enter hint text
                    Driver.driver.switchTo().defaultContent();
                    Thread.sleep(3000);
		 		}
		 	//enter solution text
			if(solution != null)
			{
			 Driver.driver.findElement(By.cssSelector("#explanation > #question-raw-content")).click();
			 Driver.driver.switchTo().frame("iframe-explanation").findElement(By.xpath("/html/body")).sendKeys(solution);
			 Driver.driver.switchTo().defaultContent();
			 Thread.sleep(3000);
			}
		    new ComboBox().selectValue(3, "Publish");
		    Driver.driver.findElement(By.id("saveQuestionDetails1")).click();
			}//if condition for the AssignmentExists ends here

		 	Driver.driver.quit();
		 	Driver.startDriver();

		}
		}
		catch(Exception e)
		{

			Assert.fail("Exception in create in Apphelper addQuestions in class AssignmentCreate",e);
		}
	}

	/*
	 * @Author Sumit
	 * Method will create a chapter and will publish the chapter
	 */
	public void createChapter(int dataIndex, int ...tloid )
	{
		try
		{
			String newChapterName = ReadTestData.readDataByTagName("", "newChapterName", Integer.toString(dataIndex));
			String course = Config.course;
			new com.snapwiz.selenium.tests.staf.learnon.apphelper.DirectLogin().CMSLogin();
			Driver.driver.findElement(By.cssSelector("img[alt=\""+course+"\"]")).click();
			//Driver.driver.findElement(By.id("manage-toc")).click();
			((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.id("manage-toc")));
			Thread.sleep(3000);
			((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("div.add-new.add-new-chapter > span.f1")));
			Thread.sleep(3000);
			Driver.driver.switchTo().activeElement().sendKeys(newChapterName);
			Thread.sleep(3000);
			Driver.driver.findElement(By.cssSelector("div[id='body-content-wrapper']")).click();
			Thread.sleep(3000);
			List <WebElement> allChapters = Driver.driver.findElements(By.xpath("//*[starts-with(@class, 'course-chapter-label node course-chapter-label-unpublished')]"));
			 for(WebElement chapters: allChapters)
			 {
				 if(chapters.getText().contains(newChapterName))
				 {
					 Locatable hoverItem = (Locatable) chapters;
					 Mouse mouse = ((HasInputDevices) Driver.driver).getMouse();
					 mouse.mouseMove(hoverItem.getCoordinates());
				 }
			 }
			//associate TLO
			 if(tloid.length > 0)
			 {
			 Driver.driver.findElement(By.id("learing-objectives-span")).click();
			 Thread.sleep(2000);
			 Driver.driver.findElement(By.cssSelector("span[title='Add Learning Objective']")).click();

			 int [] tlosids = tloid;
			 for(int tlo_id : tlosids)
			 {
			 WebElement element =Driver.driver.findElement(By.xpath("//label[@id='"+Integer.toString(tlo_id)+"']"));
			 ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", element);
			 Thread.sleep(3000);
			 }
			 Driver.driver.findElement(By.cssSelector("span.add-collection-to-subtopic")).click();
			 //Driver.driver.findElement(By.cssSelector("span.cancel-collection")).click();
			 }
			((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.className("tree-node-edit-icon")));
			Thread.sleep(3000);
			Driver.driver.findElement(By.cssSelector("label[id='']")).click();
			Thread.sleep(3000);
			Driver.driver.findElement(By.id("cms-course-tree-edit-save-btn")).click();
			Thread.sleep(3000);
			Driver.driver.findElement(By.cssSelector("span.cms-notification-message-ignore-changes.cms-notification-message-save-chater-details > span")).click();
			Thread.sleep(3000);

		}
		catch(Exception e)
		{
			Assert.fail("Exception in create in Apphelper createChapter in class AssignmentCreate",e);
		}
	}
	public void OpenAssignment(String assignmentname,int dataIndex)
	{
		try
		{
			String chapterName = ReadTestData.readDataByTagName("", "chapterName", Integer.toString(dataIndex));

			String course = Config.course;
			new DirectLogin().CMSLogin();
			String title=Driver.driver.getTitle();
			if(title.equals("Course Content Details"))
				{
				Driver.driver.findElement(By.cssSelector("img[alt=\""+course+"\"]")).click();
				if(chapterName == null)
				 {
				 Driver.driver.findElement(By.cssSelector("div.course-chapter-label.node")).click();
				 }
				 else
				 {
					 List <WebElement> allChapters = Driver.driver.findElements(By.xpath("//*[starts-with(@class, 'course-chapter-label')]"));
					 for(WebElement chapters: allChapters)
					 {
						 if(chapters.getText().contains(chapterName))
						 {
							 ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", chapters);
							 Thread.sleep(4000);
							 break;
						 }

					 }

				 }
				//Driver.driver.findElement(By.cssSelector("div.course-chapter-label.node")).click();
				List<WebElement> elements = Driver.driver.findElements(By.className("collection-assessment-name"));
				for(WebElement content : elements)
					{
						if(content.getText().trim().equals(assignmentname))
							{

							   Thread.sleep(3000);
							   ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", content);
							   break;
							}
					}
		}
		}
		catch (Exception e)
		{
			Assert.fail("Exception in create in Apphelper OpenAssignment in class AssignmentCreate",e);
		}
	}

	public void duplicatQuestion()
	{
		try
		{
			new Click().clickByid("questionOptions");
			new Click().clickByid("copyQuestion");
			new ComboBox().selectValue(3, "Publish");
			Driver.driver.findElement(By.id("saveQuestionDetails1")).click();
			Thread.sleep(3000);
		}
		catch (Exception e)
		{
			Assert.fail("Exception in create in Apphelper duplicatQuestion in class AssignmentCreate",e);
		}
	}

    //assign assignment from Assignemnt tab of eTextbook
    public void assignAssignmentFromEtextBook(String dataIndex, int assignThisIndex )
    {
        try
        {
            String context_title = ReadTestData.readDataByTagName("", "context_title",dataIndex);
            String duedate = ReadTestData.readDataByTagName("", "duedate", dataIndex);
            String duetime = ReadTestData.readDataByTagName("", "duetime", dataIndex);
            String additionalnote = ReadTestData.readDataByTagName("", "additionalnote", dataIndex);
            String  shareWithInitialString = ReadTestData.readDataByTagName("", "shareWithInitialString", dataIndex);
            String shareName = ReadTestData.readDataByTagName("", "shareName", dataIndex);
            String shareWithClass = ReadTestData.readDataByTagName("", "shareWithClass", dataIndex);
            String gradeable = ReadTestData.readDataByTagName("", "gradeable", dataIndex);
            String gradingpolicy = ReadTestData.readDataByTagName("", "gradingpolicy", dataIndex);
            new Navigator().NavigateTo("e-Textbook");//go to e-Textbook
            new TOCShow().tocHide(); //close the TOC
            new Navigator().navigateToTab("Assignments");   //go to Assignments tab
            List<WebElement> allAssignThisButton = Driver.driver.findElements(By.cssSelector("span[class='ls-assign-this-sprite-right-tab assign-resource-bg']"));
            ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", allAssignThisButton.get(assignThisIndex));//click on AssignThis button
            Thread.sleep(2000);
            new ShareWith().share(shareWithInitialString, shareName, shareWithClass,context_title,true);
            Driver.driver.findElement(By.className("input-filed")).sendKeys("shor"); //short label
            Thread.sleep(2000);

            Driver.driver.findElement(By.id("due-time")).click();//click on dur time
            Thread.sleep(2000);
            List<WebElement> elements = Driver.driver.findElements(By.xpath("//li"));//fetch all time section
            for(WebElement time : elements)
            {
                if(time.getText().equals(duetime))
                {
                    time.click();
                    break;
                }
            }
            Driver.driver.findElement(By.id("due-date")).click();//click on due date
            Thread.sleep(2000);
            Driver.driver.findElement(By.cssSelector("a[title='Next']")).click();
            Thread.sleep(2000);
            Driver.driver.findElement(By.linkText(duedate)).click();//click on specified date
            Thread.sleep(2000);
            if(gradeable !=null) {
                if (gradeable.equals("true")) {
                    ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.className("ir-ls-assign-dialog-gradable-label-check")));
                    Thread.sleep(2000);
                    Driver.driver.findElement(By.id("grading-policy")).sendKeys(gradingpolicy);
                }
            }
            Driver.driver.findElement(By.id("additional-notes")).clear();
            Driver.driver.findElement(By.id("additional-notes")).sendKeys(additionalnote);//add additional note
            new Click().clickBycssselector("span[class='btn sty-green submit-assign']");
            Thread.sleep(5000);
        }
        catch (Exception e)
        {
            Assert.fail("Exception in create in Apphelper assignAssignmentFromEtextBook in class Assignment",e);
        }
    }

    //open assignment from Assignment tan from eTextBook
    public void openAssignmentFromAssignmentTab( int openLinkIndex)
    {
        try
        {
            new Navigator().NavigateTo("e-Textbook");//go to e-Textbook
            new TOCShow().tocHide(); //close the TOC
            new Navigator().navigateToTab("Assignments");   //go to Assignments tab
            List<WebElement> allOpenLink = Driver.driver.findElements(By.cssSelector("span[class='ls-right-tab-hover-sprite folder-cycle-bg']"));
            ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", allOpenLink.get(openLinkIndex));//click on open link
            Thread.sleep(2000);
        }
        catch (Exception e)
        {
            Assert.fail("Exception in create in Apphelper openAssignmentFromAssignmentTab in class Assignment",e);
        }
    }

    //open the 1st assignment from Course stream
    public void openAssignmentFromCourseStream(String dataIndex) {
        try {
            new Navigator().NavigateTo("Course Stream");
            Thread.sleep(2000);
            ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("span[class='ls-stream-assignment-title']")));//click on Assignment
            Thread.sleep(3000);
        } catch (Exception e) {
            Assert.fail("Exception in create in Apphelper openAssignmentFromCourseStream in class Assignment", e);
        }
    }
    //Author Sumit On 13/08/2014
    //submit the assignment with immediate feedback enabled using Assignment Policy.
    public void submitAssignmentWithImmediateFeedBack(int dataIndex)
    {
        try {
            String submitassignment = ReadTestData.readDataByTagName("", "submitassignment", Integer.toString(dataIndex));
            new Navigator().NavigateTo("Course Stream");
            Thread.sleep(2000);
            ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("span[class='ls-stream-assignment-title']")));//click on Assignment
            Thread.sleep(3000);
            int helppage = Driver.driver.findElements(By.className("close-help-page")).size();
            if (helppage == 1)
                Driver.driver.findElement(By.className("close-help-page")).click();

            int timer = 1;
            while (timer != 0) {
                if (Driver.driver.findElements(By.className("qtn-label")).size() > 0)
                    Driver.driver.findElement(By.className("qtn-label")).click();

                if (submitassignment == null || submitassignment.equalsIgnoreCase("true"))
                {
                    if(Driver.driver.findElements(By.cssSelector("a[class='btn btn--primary btn--large btn--submit long-text-button']")).size() > 0) //Finish Assignment
                    {
                        ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("a[class='btn btn--primary btn--large btn--submit long-text-button']")));
                        ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.className("next-or-submit-link")));//click on Finish link on pop-up

                    }
                    else if(Driver.driver.findElements(By.cssSelector("a[class='btn btn--primary btn--large btn--submit']")).size()>0)//click on Finish Assignment
                    {
                        ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("a[class='btn btn--primary btn--large btn--submit']")));
                        ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.className("next-or-submit-link")));//click on Finish link on pop-up
                    }
                    Thread.sleep(2000);//submitting the assignment as student 1
                }


                if(Driver.driver.findElements(By.cssSelector("a[class='btn btn--primary btn--large btn--next long-text-button']")).size() > 0) //next
                {
                    ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("a[class='btn btn--primary btn--large btn--next long-text-button']")));
                    ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.className("next-or-submit-link")));//click on Next link on pop-up
                }

                if(Driver.driver.findElements(By.cssSelector("a[class='btn btn--primary btn--large btn--next']")).size() > 0) //next
                {
                    ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("a[class='btn btn--primary btn--large btn--next']")));
                    ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.className("next-or-submit-link")));//click on Next link on pop-up

                }Thread.sleep(2000);

                timer = Driver.driver.findElements(By.id("assessmentTimer")).size();
            }
        }
        catch(Exception e)
        {
            Assert.fail("Exception in app helper submitAssignmentAsStudent in class Assignment",e);
        }
    }

    public void deleteAssignment()
    {
        try
        {
            Driver.driver.findElement(By.cssSelector("span[title='Delete Assignment']")).click(); //clicking on delete link for the first assignment in summary page
            Driver.driver.findElement(By.cssSelector("div[class='as-modal-yes-btn delete-button']")).click(); //click on yes button in the alert which comes
        }
        catch (Exception e)
        {
            Assert.fail("Exception in app helper deleteAssignment in class Assignment",e);
        }
    }

	public void createCustomeAssignment(String dataIndex)
	{
		try
		{
			String questiontext = ReadTestData.readDataByTagName("", "questiontext",dataIndex);
			String customAssignmentName = ReadTestData.readDataByTagName("", "customAssignmentName",dataIndex);
			System.out.println("customAssignmentName::"+customAssignmentName);
			new Navigator().NavigateTo("New Assignment"); //Navigae to assignment page
			Driver.driver.findElement(By.cssSelector("div[class='ls-inst-dashboard-assignment-popup-button ls--create-custom-assignment-view']")).click();
			Thread.sleep(1000);
			Driver.driver.findElement(By.className("ls-ins-search-text")).click();
			Driver.driver.findElement(By.cssSelector("input[class='ls-ins-search-question-content ls-ins-search-question-content-selected']")).sendKeys(questiontext);
			Thread.sleep(1000);
			Driver.driver.findElement(By.cssSelector("i[class='ls-ins-custom-image ls-ins-search-whiteImg']")).click();
			boolean questionResult = Driver.driver.findElement(By.className("ls-ins-customize-checkbox-small")).isDisplayed();
			if(questionResult==false){
				Assert.fail("No Questions Found in New Assignment Page");
			}
			List<WebElement> elementToClick = Driver.driver.findElements(By.xpath(".//div[@class='ls-ins-customize-checkbox-small']/label"));
			elementToClick.get(0).click();
			Thread.sleep(1000);
			Driver.driver.findElement(By.id("ls-ins-assignment-name")).click();
			Thread.sleep(1000);
			Driver.driver.findElement(By.id("ls-ins-edit-assignment")).sendKeys(customAssignmentName);
			Driver.driver.findElement(By.id("ls-ins-save-assigment-btn")).click();
			Thread.sleep(2000);

		}
		catch (Exception e)
		{
			Assert.fail("Exception in app helper createCustomeAssignment in class Assignment",e);
		}
	}


    public void createFileBasedAssessment(int dataIndex) {
        try {
            String course = "";
            String overrideDefaultLogin = ReadTestData.readDataByTagName("", "overrideDefaultLogin", Integer.toString(dataIndex));
            String course_type = ReadTestData.readDataByTagName("", "course_type", Integer.toString(dataIndex));
            String course_name = ReadTestData.readDataByTagName("", "course_name", Integer.toString(dataIndex));
            String filename = ReadTestData.readDataByTagName("", "filename", Integer.toString(dataIndex));
            course = course_name;
            if (course_name == null) {
                if (course_type == null || course_type.equals(""))
                    course = Config.course;

                else if (course_type.equals("ls"))
                    course = Config.lscourse;
            }
            String assessmentname =ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
            String promptdetails = ReadTestData.readDataByTagName("", "promptdetails", Integer.toString(dataIndex));
            String chapterName = ReadTestData.readDataByTagName("", "chapterName", Integer.toString(dataIndex));
            String courselevel = ReadTestData.readDataByTagName("", "courselevel", Integer.toString(dataIndex));
            if (overrideDefaultLogin == null)
               new DirectLogin().CMSLogin();
            else {
                String role = ReadTestData.readDataByTagName("", "role", Integer.toString(dataIndex));
                new DBConnect().Connect();
                ResultSet rs = DBConnect.st.executeQuery("select id,username from t_user where id in (SELECT user_id FROM t_course_user where course_id = 253) and id in(select user_id from t_user_role where type = '" + role + "') limit 1,1;");
                String username = "";
                while (rs.next()) {
                    username = rs.getString("username");
                }
                new DirectLogin().directLoginWithCreditial(username, "snapwiz", 0);
                new Click().clickbyxpath("(//select[@name='product-drop-down']/following-sibling::div/a)[1]");
                new Click().clickbylinkText("Learning Space + Adaptive Component");
            }

            String title = Driver.driver.getTitle();
            if (title.equals("Course Content Details")) {
                Driver.driver.findElement(By.cssSelector("img[alt=\"" + course + "\"]")).click();
                if (chapterName == null) {
                    new Click().clickBycssselector("div.course-chapter-label.node");
                } else if (courselevel != null) {
                    new Click().clickBycssselector("div[class='course-label node']");
                } else {
                    List<WebElement> allChapters = Driver.driver.findElements(By.xpath("//*[starts-with(@class, 'course-chapter-label')]"));
                    for (WebElement chapters : allChapters) {
                        if (chapters.getText().contains(chapterName)) {
                            ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", chapters);

                            break;
                        }

                    }

                }
                new UIElement().waitAndFindElement(By.cssSelector("div.create-practice"));
                Driver.driver.findElement(By.cssSelector("div.create-practice")).click();
                new Click().clickByclassname("create-file-assessment-popup-item");//click on the create file assessment
                WebDriverWait wait = new WebDriverWait(Driver.driver, 2000);
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='File Based Assessment']")));
                new Click().clickbyxpath("//a[@id='uploadFile']");
                new KeysSend().sendKeyBoardKeys("$" + Config.fileUploadPath + filename + "^");
                Driver.driver.findElement(By.xpath("//*[@id='cms-file-assessment-popup-title']")).sendKeys(assessmentname); // give assessment name
                Thread.sleep(2000);
                new UIElement().waitAndFindElement(By.id("question-prompt-raw-content"));
                new TextSend().textsendbyid(""+promptdetails+"","question-prompt-raw-content");

                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='ls-delete-file-icon']")));
                new Click().clickbyxpath("//span[@class='cms-file-assessment-popup-save']"); //click on save button

            }

        } catch (Exception e) {
            Assert.fail("Exception in method createFileBasedAssessment of appHelper Assignment",e);
        }
    }


    public void assignFileBasedAssignmentToStudent(int dataIndex) {
        try {
            CurrentAssignments currentAssignments= PageFactory.initElements(Driver.driver, CurrentAssignments.class);
            String duedate = ReadTestData.readDataByTagName("", "duedate", Integer.toString(dataIndex));
            String accessibleafter = ReadTestData.readDataByTagName("", "accessibleafter", Integer.toString(dataIndex));
            String additionalnote = ReadTestData.readDataByTagName("", "additionalnote", Integer.toString(dataIndex));
            String gradable = ReadTestData.readDataByTagName("", "gradable", Integer.toString(dataIndex));
            String gradingpolicy = ReadTestData.readDataByTagName("", "gradingpolicy", Integer.toString(dataIndex));
            String assignmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
            String assignmentpolicy = ReadTestData.readDataByTagName("", "assignmentpolicy", Integer.toString(dataIndex));
            String totalpoints = ReadTestData.readDataByTagName("", "totalpoints", Integer.toString(dataIndex));
            String questionBank = ReadTestData.readDataByTagName("", "questionBank", Integer.toString(dataIndex));
            String shareWithInitialString = ReadTestData.readDataByTagName("", "shareWithInitialString", Integer.toString(dataIndex));
            String shareName = ReadTestData.readDataByTagName("", "shareName", Integer.toString(dataIndex));
            String shareWithClass = ReadTestData.readDataByTagName("", "shareWithClass", Integer.toString(dataIndex));
            String context_title = ReadTestData.readDataByTagName("", "context_title", Integer.toString(dataIndex));

            if(questionBank!=null)
            {
                new Navigator().NavigateTo("Assignments");
                Driver.driver.findElement(By.cssSelector("span.ins-assignment-button-sprite.instructor-assignment-new")).click();

                new UIElement().waitAndFindElement(By.cssSelector("div[class='tab active']"));
                if (Driver.driver.findElement(By.cssSelector("div[class='tab active']")).getText().equals("My Resources")) {//Opening All Resources tab if not opened after clicking on New Assignment button

                    Driver.driver.findElement(By.cssSelector("span[title='All Resources']")).click();
                }
                currentAssignments.getUsePreCreatedAssignment_button().click();//click on Use Pre-created Assignment on Create New assignment pop-up
                new UIElement().waitAndFindElement(By.id("all-resource-search-textarea"));
                //Adding assignment to search
                Driver.driver.findElement(By.id("all-resource-search-textarea")).clear();
                Driver.driver.findElement(By.id("all-resource-search-textarea")).sendKeys("\"" + assignmentname + "\"");
                Driver.driver.findElement(By.cssSelector("#all-resource-search-button > span.ins-resource-search-sprite.instructor-resource-search-img")).click();
            }

            String currentUrl= Driver.driver.getCurrentUrl();
            Thread.sleep(3000);
            if(currentUrl.contains("assignment")) {
                Driver.driver.findElement(By.id("ls-assign-now-assigment-btn")).click();
            }
            else {
                //List<WebElement> assignThis = Driver.driver.findElements(By.cssSelector("span[class='assign-this']"));
                //assignThis.get(0).click();
                Driver.driver.findElement(By.cssSelector("span[class='assign-this']")).click();
            }
            new ShareWith().share(shareWithInitialString, shareName, shareWithClass,context_title,true);

            Driver.driver.findElement(By.className("input-filed")).sendKeys("shor"); //short label
            Thread.sleep(2000);
            new UIElement().waitAndFindElement(By.id("due-time"));
            Driver.driver.findElement(By.id("due-time")).click();
            Driver.driver.findElement(By.xpath("//li[text()='12:00 AM']")).click();
            Driver.driver.findElement(By.id("due-date")).click();
            new UIElement().waitAndFindElement(By.cssSelector("span[class='ui-icon ui-icon-circle-triangle-e']"));
            Driver.driver.findElement(By.cssSelector("span[class='ui-icon ui-icon-circle-triangle-e']")).click();
            new UIElement().waitAndFindElement(By.linkText(duedate));
            Driver.driver.findElement(By.linkText(duedate)).click();

            if(gradable !=null) {
                if (gradable.equals("true")) {
                    ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.className("ir-ls-assign-dialog-gradable-label-check")));    //check gradable chekbox
                    new UIElement().waitAndFindElement(By.id("grading-policy"));
                    Driver.driver.findElement(By.id("grading-policy")).click();
                    new UIElement().waitAndFindElement(By.id("grading-policy"));
                    Driver.driver.findElement(By.id("grading-policy")).sendKeys(gradingpolicy);
                    Driver.driver.findElement(By.id("total-points")).sendKeys(totalpoints);  //enter Total Points
                }
            }
            if (gradable.equals("true") && assignmentpolicy != null) {

                //click on  Choose your assignment policy dropdown
                ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.linkText("Select an assignment policy")));
                new UIElement().waitAndFindElement(By.linkText(assignmentpolicy));
                Driver.driver.findElement(By.linkText(assignmentpolicy)).click();//select a policy
            }


            if (accessibleafter != null) {
                Driver.driver.findElement(By.id("accessible-date")).click();
                Driver.driver.findElement(By.linkText(accessibleafter)).click();
            }


            Driver.driver.findElement(By.className("input-filed")).sendKeys("shor"); //short label
            new UIElement().waitAndFindElement(By.id("additional-notes"));
            Driver.driver.findElement(By.id("additional-notes")).clear();
            Driver.driver.findElement(By.id("additional-notes")).sendKeys(additionalnote);
            ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.xpath("//span[text()='Assign']")));

        } catch (Exception e) {
            Assert.fail("Exception in  assignFileBasedAssignmentToStudent in AppHelper class Assignment", e);
        }
    }

	public void createFileBasedAssignmentInstructor(String dataIndex)
	{
		try
		{
			String assignmentname = ReadTestData.readDataByTagName("", "assignmentname", dataIndex);
			String filename = ReadTestData.readDataByTagName("", "filename", dataIndex);
			new Navigator().NavigateTo("New Assignment");
			Driver.driver.findElement(By.cssSelector("div[class='ls-inst-dashboard-assignment-popup-button ls--custom-file-assignment-view']")).click();
			Thread.sleep(1000);
			Driver.driver.findElement(By.id("ls-ins-assignment-name")).click();
			Thread.sleep(1000);
			Driver.driver.findElement(By.id("ls-ins-edit-assignment")).sendKeys(assignmentname);
			Thread.sleep(2000);
			Driver.driver.findElement(By.className("file-upload-button")).click();
			new KeysSend().sendKeyBoardKeys("$" + Config.fileUploadPath + filename + "^");
			Thread.sleep(2000);
			Driver.driver.findElement(By.className("save-for-later-text")).click();
		}
		catch (Exception e)
		{
			Assert.fail("Exception in  createFileBasedAssignmentInstructor in AppHelper class Assignment", e);
		}
	}

	public void assignmentScrollDownAndClickAssign (String customAssignmentName)
	{
		try
		{

			int pos = 0;
			List<WebElement> allOpenLink = Driver.driver.findElements(By.className("ls_assessment_link"));
			for(int a=0;a<allOpenLink.size();a++){
				((JavascriptExecutor)Driver.driver).executeScript("arguments[0].scrollIntoView(true);", allOpenLink.get(a));
				if((allOpenLink.get(a).getText().equals(customAssignmentName))){
					System.out.println("customAssignmentName:"+customAssignmentName);
					break;
				}
				pos++;
			}
			Thread.sleep(1000);
			List<WebElement> rightArrowElementsList = Driver.driver.findElements(By.className("ls-assignment-show-assign-this-block"));
			((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", rightArrowElementsList.get(pos));//click on open link
			Thread.sleep(1000);
			List<WebElement> assignThisELementsList = Driver.driver.findElements(By.cssSelector("span[class='ls-assign-this-sprite-right-tab assign-resource-bg']"));
			((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", assignThisELementsList.get(pos));//click on open link
		}
		catch (Exception e)
		{
			Assert.fail("Exception in  assignmentScrollDown in AppHelper class Assignment");
		}
	}

	public void assignCustomeOrFileBasedAssignment(int dataIndex)
	{
		try
		{
			String context_title = ReadTestData.readDataByTagName("", "context_title", Integer.toString(dataIndex));
			String  shareWithInitialString = ReadTestData.readDataByTagName("", "shareWithInitialString", Integer.toString(dataIndex));
			String shareName = ReadTestData.readDataByTagName("", "shareName", Integer.toString(dataIndex));
			String shareWithClass = ReadTestData.readDataByTagName("", "shareWithClass", Integer.toString(dataIndex));
			String duedate = ReadTestData.readDataByTagName("", "duedate", Integer.toString(dataIndex));
			String duetime = ReadTestData.readDataByTagName("", "duetime", Integer.toString(dataIndex));
			String accessibleafter = ReadTestData.readDataByTagName("", "accessibleafter", Integer.toString(dataIndex));
			String additionalnote = ReadTestData.readDataByTagName("", "additionalnote", Integer.toString(dataIndex));
			String gradeable = ReadTestData.readDataByTagName("", "gradeable", Integer.toString(dataIndex));
			String gradingpolicy = ReadTestData.readDataByTagName("", "gradingpolicy", Integer.toString(dataIndex));
			String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
			String  assignmentpolicy = ReadTestData.readDataByTagName("", "assignmentpolicy", Integer.toString(dataIndex));
			String  customAssignmentName = ReadTestData.readDataByTagName("", "customAssignmentName", Integer.toString(dataIndex));
			String totalpoints = ReadTestData.readDataByTagName("", "totalpoints", Integer.toString(dataIndex));
			new Navigator().NavigateTo("My Question Bank");

			new Click().clickByid("my-resource-search-textarea");
			Driver.driver.findElement(By.id("my-resource-search-textarea")).sendKeys(assessmentname);
			Driver.driver.findElement(By.id("my-resource-search-button")).click();
			Thread.sleep(1000);
			List<WebElement> assignThis = Driver.driver.findElements(By.className("assign-this"));
			assignThis.get(0).click();
			Thread.sleep(1000);

			new ShareWith().share(shareWithInitialString, shareName, shareWithClass,context_title,true);
			Thread.sleep(3000);

			Driver.driver.findElement(By.id("due-time")).click();
			List<WebElement> elements = Driver.driver.findElements(By.xpath("//li"));
			for(WebElement time : elements)
			{
				if(time.getText().equals(duetime))
				{
					time.click();
					break;
				}
			}


			Driver.driver.findElement(By.id("due-date")).click();
			Thread.sleep(2000);
			Driver.driver.findElement(By.cssSelector("span[class='ui-icon ui-icon-circle-triangle-e']")).click();
			Thread.sleep(2000);
			Driver.driver.findElement(By.linkText(duedate)).click();
			Thread.sleep(2000);
			if(gradeable !=null) {
				if (gradeable.equals("true")) {
					((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.className("ir-ls-assign-dialog-gradable-label-check")));    //check gradable chekbox
					new UIElement().waitAndFindElement(By.id("grading-policy"));
					Driver.driver.findElement(By.id("grading-policy")).click();
					new UIElement().waitAndFindElement(By.id("grading-policy"));
					Driver.driver.findElement(By.id("grading-policy")).sendKeys(gradingpolicy);
					List<WebElement> totalMarksPresent = Driver.driver.findElements(By.xpath(".//*[@class='ir-ls-assign-dialog-assign-to-label ir-ls-assign-dialog-label']/span"));
					for(int i = 0; i<totalMarksPresent.size();i++)
					{
						String totalMarks = totalMarksPresent.get(i).getText();
						if(totalMarks.contains("Marks"))
						{
							Driver.driver.findElement(By.id("total-points")).sendKeys(totalpoints);  //enter Total Points
							break;
						}
					}
				}
			}
			if(gradeable.equals("true") && assignmentpolicy!= null)
			{

				//click on  Choose your assignment policy dropdown
				((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.linkText("Select an assignment policy")));
				Thread.sleep(2000);
				Driver.driver.findElement(By.linkText(assignmentpolicy)).click();//select a policy
				Thread.sleep(2000);
			}
			if(accessibleafter != null)
			{
				Driver.driver.findElement(By.id("accessible-date")).click();
				Driver.driver.findElement(By.linkText(accessibleafter)).click();
			}



			Driver.driver.findElement(By.className("input-filed")).sendKeys("shor"); //short label
			Thread.sleep(2000);
			Driver.driver.findElement(By.id("additional-notes")).clear();
			Driver.driver.findElement(By.id("additional-notes")).sendKeys(additionalnote);

			Driver.driver.findElement(By.cssSelector("span[class='btn sty-green submit-assign']")).click();
			Thread.sleep(5000);

		}
		catch(Exception e)
		{
			Assert.fail("Exception in assignCustomeOrFileBasedAssignment in AppHelper class Assignment",e);
		}
	}
	public void assignAssignmentFromAssignmentTab(int assignLink, String assessmentName) {
		try {
			new Navigator().NavigateTo("e-Textbook");//go to e-Textbook
			new TOCShow().tocHide(); //close the TOC
			new Navigator().navigateToTab("Assignments");   //go to Assignments tab
			int pos = 0;
			List<WebElement> allOpenLink = Driver.driver.findElements(By.className("ls_assessment_link"));
			for (int a = 0; a < allOpenLink.size(); a++) {
				((JavascriptExecutor) Driver.driver).executeScript("arguments[0].scrollIntoView(true);", allOpenLink.get(a));
				if ((allOpenLink.get(a).getText().equals(assessmentName))) {
					System.out.println("assessmentName:" + assessmentName);
					break;
				}
				pos++;
				List<WebElement> rightArrowElementsList = Driver.driver.findElements(By.className("ls-assignment-show-assign-this-block"));
				((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", rightArrowElementsList.get(pos));//click on open link
				List<WebElement> assignThisELementsList = Driver.driver.findElements(By.cssSelector("span[class='ls-assign-this-sprite-right-tab assign-resource-bg']"));
				((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", assignThisELementsList.get(pos));//click on open link
			}
		} catch (Exception e) {
			Assert.fail("Exception in create in Apphelper assignAssignmentFromAssignmentTab in class Assignment", e);
		}
	}

}

