package com.snapwiz.selenium.tests.staf.orion.apphelper;

import java.util.ArrayList;
import java.util.List;

import com.snapwiz.selenium.tests.staf.framework.controller.Driver;
import com.snapwiz.selenium.tests.staf.framework.utility.ReportUtil;
import com.snapwiz.selenium.tests.staf.framework.utility.WebDriverUtil;
import com.snapwiz.selenium.tests.staf.orion.pagefactory.CMS.ManageContent;
import com.snapwiz.selenium.tests.staf.orion.uihelper.*;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.HasInputDevices;
import org.openqa.selenium.interactions.Mouse;
import org.openqa.selenium.internal.Locatable;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.snapwiz.selenium.tests.staf.orion.Config;
import com.snapwiz.selenium.tests.staf.orion.ReadTestData;

public class Assignment extends Driver {
    public WebDriver driver=Driver.getWebDriver();
    public Assignment(){

        Config.readconfiguration();
    }
	public void create(int dataIndex)
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
		String hint = ReadTestData.readDataByTagName("", "hint", Integer.toString(dataIndex));
			String courselevel = ReadTestData.readDataByTagName("", "courselevel", Integer.toString(dataIndex));
			String practice_type = ReadTestData.readDataByTagName("", "practice_type", Integer.toString(dataIndex));
			new DirectLogin().CMSLogin();
		String title=driver.getTitle();
		if(title.equals("Course Content Details"))
			{
			 driver.findElement(By.cssSelector("img[alt=\""+course+"\"]")).click();
			 if(chapterName == null)
			 {
			 driver.findElement(By.cssSelector("div.course-chapter-label.node")).click();
			 } else if (courselevel != null) {
				 new Click().clickBycssselector("div[class='course-label node']");
			 } else {
				 Thread.sleep(10000);
				 if (driver.findElements(By.xpath("//div[contains(@title,'" + chapterName + "')]")).size() > 1) {
					 System.out.println("inside if:" + chapterName);
					 new TopicOpen().openCMSLastChapter();
				 } else {
					 System.out.println("inside else:" + chapterName);
					 List<WebElement> allChapters = driver.findElements(By.xpath("//*[starts-with(@class, 'course-chapter-label')]"));
					 for (WebElement chapters : allChapters) {
						 if (chapters.getText().contains(chapterName)) {
							 ((JavascriptExecutor) driver).executeScript("arguments[0].click();", chapters);
							 Thread.sleep(4000);
							 break;
						 }

					 }
				 }

			 }
			 driver.findElement(By.cssSelector("div.create-practice")).click();
                if (practice_type == null) {
                    driver.findElement(By.className("create-regular-assessment-popup-item")).click();//click on the create regular assessment
                } else {
                    driver.findElement(By.className("create-regular-assessment-popup-item")).click();//click on the create file assessment
                }
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
                 driver.findElement(By.xpath("html/body/div[8]/div/div[2]/div/div[1]/div[3]/div/div/div[4]/div/div[3]/div[1]/div/div[1]/label")).click();
                 driver.findElement(By.cssSelector("span.add-collection-to-subtopic")).click();
                 driver.findElement(By.cssSelector("span.cancel-collection")).click();
				
			 }
			 driver.findElement(By.xpath("//*[@id='instructor-only-check-box-div']/label")).click(); //checking 'Enable for intructor only'

			 //enter Hint
			 if(hint != null)
			 {
                 driver.findElement(By.cssSelector("#hint > #question-raw-content")).click();
                 Thread.sleep(3000);
                 driver.switchTo().frame("iframe-hint").findElement(By.xpath("/html/body")).sendKeys(hint);//enter hint text
                 driver.switchTo().defaultContent();
                 Thread.sleep(3000);
			 }

			 new ComboBox().selectValue(4, "Publish");
			 
			 driver.findElement(By.id("saveQuestionDetails1")).click();
			}
		}
		catch(Exception e)
		{
			Assert.fail("Exception in create in Apphelper create in class AssignmentCreate.",e);
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
			int [] tlosids = tloid;
			String newChapterName = ReadTestData.readDataByTagName("", "newChapterName", Integer.toString(dataIndex));
			String course = Config.course;
			new DirectLogin().CMSLogin();
			driver.findElement(By.cssSelector("img[alt=\""+course+"\"]")).click();
			Thread.sleep(3000);
			driver.findElement(By.id("manage-toc")).click();
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("div.add-new.add-new-chapter > span.f1")));
			driver.switchTo().activeElement().sendKeys(newChapterName);
			driver.findElement(By.cssSelector("div[id='body-content-wrapper']")).click();
			Thread.sleep(3000);
			List <WebElement> allChapters = driver.findElements(By.xpath("//*[starts-with(@class, 'course-chapter-label node course-chapter-label-unpublished')]"));
			 for(WebElement chapters: allChapters)
			 {
				 if(chapters.getText().contains(newChapterName))
				 {
					 Locatable hoverItem = (Locatable) chapters;
					 Mouse mouse = ((HasInputDevices) driver).getMouse();
					 mouse.mouseMove(hoverItem.getCoordinates());
				 } 
			 }
			//associate TLO
			if(tloid.length!=0) {
				driver.findElement(By.id("tlo-count")).click();
				for (int tlo : tlosids) {
					WebElement element = driver.findElement(By.xpath("//label[@id='" + Integer.toString(tlo) + "']"));
					((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
					Thread.sleep(3000);
				}
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("span.add-collection-to-subtopic")));
				Thread.sleep(3000);
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.id("link-add-learn-objectives")));
				Thread.sleep(3000);
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("span.cancel-collection")));
				Thread.sleep(3000);
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("tree-node-edit-icon")));
				Thread.sleep(3000);
				driver.findElement(By.cssSelector("label[id='']")).click();
				Thread.sleep(3000);
				driver.findElement(By.id("cms-course-tree-edit-save-btn")).click();
				Thread.sleep(3000);
				driver.findElement(By.cssSelector("span.cms-notification-message-ignore-changes.cms-notification-message-save-chater-details > span")).click();
				Thread.sleep(3000);
			}
		}
		catch(Exception e)
		{
			Assert.fail("Exception in create in Apphelper createChapter in class AssignmentCreate",e);
		}
	}
	/*
	 * @Author Sumit
	 * method will create a assessment at chapter level with single multiple choice question and will publish it
	 */
	public void createPracticeAtChapterLevel(int dataIndex, String typeOfPractice, boolean enterSolutionText, boolean enterHint, boolean publish,int ...tloid)
	{
		try
		{			
			String course = Config.course;
			String diagassessmentname = ReadTestData.readDataByTagName("", "diagassessmentname", Integer.toString(dataIndex));
			String practiceassessmentname = ReadTestData.readDataByTagName("", "practiceassessmentname", Integer.toString(dataIndex));
			String questionset = ReadTestData.readDataByTagName("", "questionset", Integer.toString(dataIndex));
			String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));
			String chapterName = ReadTestData.readDataByTagName("", "chapterName", Integer.toString(dataIndex));
			String templatename = ReadTestData.readDataByTagName("", "templatename", Integer.toString(dataIndex));
			String variationlevel = ReadTestData.readDataByTagName("", "variationlevel", Integer.toString(dataIndex));
			String difficultylevel = ReadTestData.readDataByTagName("", "difficultylevel", Integer.toString(dataIndex));
			new DirectLogin().CMSLogin();
			String title=driver.getTitle();
			if(title.equals("Course Content Details"))
				{
				 driver.findElement(By.cssSelector("img[alt=\""+course+"\"]")).click();
				 if(chapterName == null)
				 {
				 driver.findElement(By.cssSelector("div.course-chapter-label.node")).click();
				 }
				 else
				 {
					 List <WebElement> allChapters = driver.findElements(By.xpath("//*[starts-with(@class, 'course-chapter-label')]"));
					 for(WebElement chapters: allChapters)
					 {
						 if(chapters.getText().contains(chapterName))
						 {
							 //chapters.click();
							 ((JavascriptExecutor) driver).executeScript("arguments[0].click();", chapters); 
							 break;
						 }
						 
					 }
					 
				 }
				 driver.findElement(By.cssSelector("div.create-practice")).click();
				 driver.findElement(By.id("assessmentName")).click();
				 driver.findElement(By.id("assessmentName")).clear();
				 if(typeOfPractice.equals("Adaptive Component Diagnostic"))
					 driver.findElement(By.id("assessmentName")).sendKeys(diagassessmentname);
				 if(typeOfPractice.equals("Adaptive Component Practice"))
					 driver.findElement(By.id("assessmentName")).sendKeys(practiceassessmentname);
				 driver.findElement(By.id("questionSetName")).clear();
				 driver.findElement(By.id("questionSetName")).sendKeys(questionset);
				
				 driver.findElement(By.id("qtn-multiple-choice-img")).click();
				 
				
				 driver.findElement(By.id("question-raw-content")).click();		 
			
				 
				 driver.switchTo().frame("iframe-question-edit").findElement(By.xpath("/html/body")).sendKeys(questiontext);
				 driver.switchTo().defaultContent();
				 
				//Adding choice 1
				 driver.findElement(By.id("choice1")).click();
				 driver.switchTo().frame("iframe-choice1").findElement(By.xpath("/html/body")).sendKeys("Choice 1");			 	
				 driver.switchTo().defaultContent();
                    Thread.sleep(2000);
				 //Adding choice 2
				 driver.findElement(By.id("choice2")).click();
				 driver.switchTo().frame("iframe-choice2").findElement(By.xpath("/html/body")).sendKeys("Choice 2");			 	
				 driver.switchTo().defaultContent();
                    Thread.sleep(2000);
				 //Adding choice 3
				 driver.findElement(By.id("choice3")).click();
				 driver.switchTo().frame("iframe-choice3").findElement(By.xpath("/html/body")).sendKeys("Choice 3");			 	
				 driver.switchTo().defaultContent();
                    Thread.sleep(2000);
				 //Adding choice 4
				 driver.findElement(By.id("choice4")).click();
				 driver.switchTo().frame("iframe-choice4").findElement(By.xpath("/html/body")).sendKeys("Choice 4");			 	
				 driver.switchTo().defaultContent();
                    Thread.sleep(2000);
				 //Setting choice 2 as correct answer
				 Actions action = new Actions(driver);
			     WebElement we = driver.findElement(By.id("choice2"));
			     action.moveToElement(we).build().perform();
			     ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.xpath("//img[@title='Set it as Correct Answer']"))); 
				// driver.findElement(By.xpath("//img[@title='Set it as Correct Answer']")).click();
				 Thread.sleep(3000);
				 //enter solution text
				 if(enterSolutionText == true)
				 {
				 driver.findElement(By.cssSelector("#explanation > #question-raw-content")).click();
				 driver.switchTo().frame("iframe-explanation").findElement(By.xpath("/html/body")).sendKeys("This is solution text.");			 	
				 driver.switchTo().defaultContent();
				 Thread.sleep(3000);	
				 }
				 
				 //enter template
				 if(templatename != null)
					 {
				     driver.findElement(By.cssSelector("div.template-data-wrapper > #template-name")).click();
				     driver.switchTo().activeElement().sendKeys(templatename);			 	
					 driver.switchTo().defaultContent();
					 driver.findElement(By.className("template-data-wrapper")).click();
					 Thread.sleep(3000);	
					 }
				    //select variation level
				 if(variationlevel != null)
				    {
				    	
				    	System.out.println("Variation "+variationlevel);
				    	driver.findElement(By.linkText("Select Variation Level")).click();		    	
						Thread.sleep(4000);
						driver.findElement(By.linkText("Select Variation Level")).click();
					    driver.findElement(By.cssSelector("a[title='"+variationlevel+"']")).click();
				    }
				 
				 //enter Hint
				 if(enterHint == true)
				 {
                     driver.findElement(By.cssSelector("#hint > #question-raw-content")).click();
                     Thread.sleep(3000);
                     driver.switchTo().frame("iframe-hint").findElement(By.xpath("/html/body")).sendKeys("This is hint text.");//enter hint text
                     driver.switchTo().defaultContent();
                     Thread.sleep(3000);
				 }
				 //Select Difficulty Level
				 if(difficultylevel != null)
				 {
					 driver.findElement(By.linkText("Difficulty Level")).click();
					 Thread.sleep(3000);
					 driver.findElement(By.linkText(difficultylevel)).click();
				 }
                    //associate TLO
                    if(tloid.length > 0)
                    {
                        driver.findElement(By.id("learing-objectives-span")).click();
                        Thread.sleep(2000);
                        driver.findElement(By.cssSelector("span[title='Add Learning Objective']")).click();

                        int [] tlosids = tloid;
                        for(int tlo_id : tlosids)
                        {
                            WebElement element =driver.findElement(By.xpath("//label[@id='"+Integer.toString(tlo_id)+"']"));
                            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
                            Thread.sleep(3000);
                        }
                        driver.findElement(By.cssSelector("span.add-collection-to-subtopic")).click();
                        //driver.findElement(By.cssSelector("span.cancel-collection")).click();
                    }
				 new ComboBox().selectValue(3, typeOfPractice);		//select adaptive practice
				 if(publish == true)
					 new ComboBox().selectValue(4, "Publish");		//publish question
				 
				 driver.findElement(By.id("saveQuestionDetails1")).click();
				 Thread.sleep(5000);
				 
				}
		}
		catch(Exception e)
		{
			Assert.fail("Exception in create in Apphelper createPracticeAtChapterLevel in class Assignment.",e);
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
		new DirectLogin().CMSLogin();
		String title=driver.getTitle();
		if(title.equals("Course Content Details"))
			{
			 driver.findElement(By.cssSelector("img[alt=\""+course+"\"]")).click();
			 List<WebElement> allchapter=(List<WebElement>) ((JavascriptExecutor)driver).executeScript("return arguments[0]",driver.findElements(By.cssSelector("div[class='expand-chapter-tree expand']")));
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
			
			 List<WebElement> alltopic=(List<WebElement>) ((JavascriptExecutor)driver).executeScript("return arguments[0]",driver.findElements(By.cssSelector("div[class='course-topic-label node']")));
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
                 driver.findElement(By.xpath("html/body/div[8]/div/div[2]/div/div[1]/div[3]/div/div/div[4]/div/div[3]/div[1]/div/div[1]/label")).click();
                 driver.findElement(By.cssSelector("span.add-collection-to-subtopic")).click();
                 driver.findElement(By.cssSelector("span.cancel-collection")).click();
				
			 }
			 driver.findElement(By.xpath("//*[@id='instructor-only-check-box-div']/label")).click(); //checking 'Enable for intructor only'
			 new ComboBox().selectValue(4, "Publish");			 
			 driver.findElement(By.id("saveQuestionDetails1")).click();
			
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
		new DirectLogin().CMSLogin();
		String title=driver.getTitle();
		if(title.equals("Course Content Details"))
			{
			 driver.findElement(By.cssSelector("img[alt=\""+course+"\"]")).click();
			 List<WebElement> allchapter=(List<WebElement>) ((JavascriptExecutor)driver).executeScript("return arguments[0]",driver.findElements(By.cssSelector("div[class='expand-chapter-tree expand']")));
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
			 List<WebElement> alltopic=(List<WebElement>) ((JavascriptExecutor)driver).executeScript("return arguments[0]",driver.findElements(By.cssSelector("div[class='expand-topic-tree expand']")));
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
			 List<WebElement> allsubtopictopic=(List<WebElement>) ((JavascriptExecutor)driver).executeScript("return arguments[0]",driver.findElements(By.cssSelector("div[class='course-subtopic-label node']")));
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
                 driver.findElement(By.xpath("html/body/div[8]/div/div[2]/div/div[1]/div[3]/div/div/div[4]/div/div[3]/div[1]/div/div[1]/label")).click();
                 driver.findElement(By.cssSelector("span.add-collection-to-subtopic")).click();
                 driver.findElement(By.cssSelector("span.cancel-collection")).click();
				
			 }
			 driver.findElement(By.xpath("//*[@id='instructor-only-check-box-div']/label")).click(); //checking 'Enable for intructor only'
			 new ComboBox().selectValue(4, "Publish");			 
			 driver.findElement(By.id("saveQuestionDetails1")).click();
			
			}
		}
		catch(Exception e)
		{
			Assert.fail("Exception in create in Apphelper createresourcesatsubtopiclevel in class AssignmentCreate",e);
		}
		
	}
	
	public void addQuestions(int dataIndex,String questionType,String assignmentname, boolean enterSolutionText, boolean enterHint,String difficultylevel, boolean publish,int ...tloids)
	{
		try
		{
	    int tloid [] = tloids;
		String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));
		String optiontext = ReadTestData.readDataByTagName("", "optiontext", Integer.toString(dataIndex));
		String answertext = ReadTestData.readDataByTagName("", "answertext", Integer.toString(dataIndex));
		String numerictext = ReadTestData.readDataByTagName("", "numerictext", Integer.toString(dataIndex));
		String unitoption = ReadTestData.readDataByTagName("", "unitoption", Integer.toString(dataIndex));
		String tolrence = ReadTestData.readDataByTagName("", "tolrence", Integer.toString(dataIndex));
		String maplenumeric = ReadTestData.readDataByTagName("", "maplenumeric", Integer.toString(dataIndex));
		String chapterName = ReadTestData.readDataByTagName("", "chapterName", Integer.toString(dataIndex));
		String passagetext = ReadTestData.readDataByTagName("", "passagetext", Integer.toString(dataIndex));
		String passagedetails = ReadTestData.readDataByTagName("", "passagedetails", Integer.toString(dataIndex));
		String questionset = ReadTestData.readDataByTagName("", "questionset", Integer.toString(dataIndex));
		String course = Config.course;
		new DirectLogin().CMSLogin();
		String title=driver.getTitle();
		if(title.equals("Course Content Details"))	
			{
			driver.findElement(By.cssSelector("img[alt=\""+course+"\"]")).click();
			if(chapterName == null)
			 {
			 driver.findElement(By.cssSelector("div.course-chapter-label.node")).click();
			 }
			 else
			 {
				 List <WebElement> allChapters = driver.findElements(By.xpath("//*[starts-with(@class, 'course-chapter-label')]"));
				 for(WebElement chapters: allChapters)
				 {
					 if(chapters.getText().contains(chapterName))
					 {
						 //chapters.click();
						 ((JavascriptExecutor) driver).executeScript("arguments[0].click();", chapters); 
						 break;
					 }
					 
				 }
				 
			 }
			
			List<WebElement> elements = driver.findElements(By.className("collection-assessment-name"));
			for(WebElement content : elements)
				{
		
				if(content.getText().trim().equals(assignmentname))
				{
					Thread.sleep(3000);
				   // content.click();
					((JavascriptExecutor) driver).executeScript("arguments[0].click();", content); 
				    Thread.sleep(3000);
            		break;
				}
				}
			
			 	driver.findElement(By.id("questionOptions")).click();
			 	Thread.sleep(2000);
			 	//driver.findElement(By.id("addQuestion")).click();
			 	((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.id("addQuestion")));
			 	Thread.sleep(2000);
			 	driver.findElement(By.id(questionType)).click();
			 	Thread.sleep(2000);
			 	
				
		if(questionType.equals("qtn-type-true-false-img")) //1.For true-false type question
				{
			 	driver.findElement(By.id("question-raw-content")).click();
			 	driver.switchTo().frame("iframe-question-edit").findElement(By.xpath("/html/body")).sendKeys(questiontext);
			 	driver.switchTo().defaultContent();
			 	//Setting choice 1 as correct answer
			 	Actions action = new Actions(driver);
		        WebElement we = driver.findElement(By.id("choice1"));
		        action.moveToElement(we).build().perform();			 
			    driver.findElement(By.xpath("//img[@title='Set it as Correct Answer']")).click();
				} 
		if(questionType.equals("qtn-multiple-choice-img")) //2. For multiple choice question.
		{
			driver.findElement(By.id("question-raw-content")).click();
		 	driver.switchTo().frame("iframe-question-edit").findElement(By.xpath("/html/body")).sendKeys(questiontext);
		 	driver.switchTo().defaultContent();
		 	//Adding choice 1
		 	driver.findElement(By.id("choice1")).click();
		 	driver.switchTo().frame("iframe-choice1").findElement(By.xpath("/html/body")).sendKeys(answertext);			 	
		 	driver.switchTo().defaultContent();
		 	//Adding choice 2
		 	driver.findElement(By.id("choice2")).click();
		 	driver.switchTo().frame("iframe-choice2").findElement(By.xpath("/html/body")).sendKeys(answertext);			 	
		 	driver.switchTo().defaultContent();
		 	//Adding choice 3
		 	driver.findElement(By.id("choice3")).click();
		 	driver.switchTo().frame("iframe-choice3").findElement(By.xpath("/html/body")).sendKeys(answertext);			 	
		 	driver.switchTo().defaultContent();
		 	//Adding choice 4
		 	driver.findElement(By.id("choice4")).click();
		 	driver.switchTo().frame("iframe-choice4").findElement(By.xpath("/html/body")).sendKeys(answertext);			 	
		 	driver.switchTo().defaultContent();
		 	//Setting choice 2 as correct answer
		 	Actions action = new Actions(driver);
	        WebElement we = driver.findElement(By.id("choice2"));
	        action.moveToElement(we).build().perform();			 
		    driver.findElement(By.xpath("//img[@title='Set it as Correct Answer']")).click();
		    //enter solution text
		    if(enterSolutionText == true)
			 {
			 driver.findElement(By.cssSelector("#explanation > #question-raw-content")).click();
			 driver.switchTo().frame("iframe-explanation").findElement(By.xpath("/html/body")).sendKeys("This is solution text.");			 	
			 driver.switchTo().defaultContent();
			 Thread.sleep(3000);	
			 }
		    
		 
		}
		
			
		if(questionType.equals("qtn-multiple-selection-img"))  //3. For multiple sections
		{
			driver.findElement(By.id("question-raw-content")).click();
		 	driver.switchTo().frame("iframe-question-edit").findElement(By.xpath("/html/body")).sendKeys(questiontext);
		 	driver.switchTo().defaultContent();
		 	//Adding choice 1
		 	driver.findElement(By.id("choice1")).click();
		 	driver.switchTo().frame("iframe-choice1").findElement(By.xpath("/html/body")).sendKeys(answertext);			 	
		 	driver.switchTo().defaultContent();
		 	//Adding choice 2
		 	driver.findElement(By.id("choice2")).click();
		 	driver.switchTo().frame("iframe-choice2").findElement(By.xpath("/html/body")).sendKeys(answertext);			 	
		 	driver.switchTo().defaultContent();
		 	//Adding choice 3
		 	driver.findElement(By.id("choice3")).click();
		 	driver.switchTo().frame("iframe-choice3").findElement(By.xpath("/html/body")).sendKeys(answertext);			 	
		 	driver.switchTo().defaultContent();
		 	//Adding choice 4
		 	driver.findElement(By.id("choice4")).click();
		 	driver.switchTo().frame("iframe-choice4").findElement(By.xpath("/html/body")).sendKeys(answertext);			 	
		 	driver.switchTo().defaultContent();
		 	//Setting choice 2 as correct answer
		 	Actions action1 = new Actions(driver);
	        WebElement we1 = driver.findElement(By.id("choice2"));
	        action1.moveToElement(we1).build().perform();			 
		    driver.findElement(By.xpath("//img[@title='Set it as Correct Answer']")).click();
		   //Setting choice 4 as correct answer
		 	Actions action2 = new Actions(driver);
	        WebElement we2 = driver.findElement(By.id("choice4"));
	        action2.moveToElement(we2).build().perform();			 
		    driver.findElement(By.xpath("//img[@title='Set it as Correct Answer']")).click();
			}
		if(questionType.equals("qtn-text-entry-img")) // 4.For Text Entry type.
		{
			driver.findElement(By.id("question-raw-content")).click();
		 	driver.switchTo().frame("iframe-question-edit").findElement(By.xpath("/html/body")).sendKeys(questiontext);
		 	driver.switchTo().defaultContent();
		 	// Adding correct answer choice
		 	driver.findElement(By.cssSelector("input[id='correct-ans-text']")).click();
		 	driver.findElement(By.cssSelector("input[id='correct-ans-text']")).clear();
		    driver.findElement(By.cssSelector("input[id='correct-ans-text']")).sendKeys(answertext);	
		    Thread.sleep(3000);
			// Rest two boxes are filled automatically.
		    //Adding alternate answer choice.
		    driver.findElement(By.id("right-alt-container-1")).click();
		    driver.findElement(By.cssSelector("input[id='alternate-answer-text']")).click();
		 	driver.findElement(By.cssSelector("input[id='alternate-answer-text']")).clear();
		    driver.findElement(By.cssSelector("input[id='alternate-answer-text']")).sendKeys(optiontext);	
		 
		    driver.findElement(By.id("done-button")).click(); // Accept answer
		 	  Thread.sleep(2000);
		}
		if(questionType.equals("qtn-text-entry-drop-down-img")) //5. For text entry drop down
		{
			driver.findElement(By.id("question-raw-content")).click();
		 	driver.switchTo().frame("iframe-question-edit").findElement(By.xpath("/html/body")).sendKeys(questiontext);
		 	driver.switchTo().defaultContent();
		 	// Adding Entry 1
		 	driver.findElement(By.id("entry-0")).click();
		 	driver.findElement(By.id("unit-name-edit-entry-0")).clear();
		 	driver.findElement(By.id("unit-name-edit-entry-0")).sendKeys(optiontext);
		 	
		 	  // Accepting answer
		 	WebElement menuitem = driver.findElement(By.id("entry-1")); 
        	Locatable hoverItem = (Locatable) menuitem;
        	Mouse mouse = ((HasInputDevices) driver).getMouse();
        	mouse.mouseMove(hoverItem.getCoordinates());
        	List<WebElement> selectanswerticks = driver.findElements(By.className("mark-selected"));        	
        	selectanswerticks.get(1).click(); //select second option as correct answer
        	
		 	
		 	Thread.sleep(2000);
		   // Adding Entry 2
		 	Actions action = new Actions(driver);
		 	action.doubleClick(driver.findElement(By.id("entry-1")));
		 	action.perform();
		 	driver.findElement(By.id("unit-name-edit-entry-1")).clear();
		 	driver.findElement(By.id("unit-name-edit-entry-1")).sendKeys(optiontext);
		 	
		
        	
		   // Adding Entry 3
		 	action.doubleClick(driver.findElement(By.id("entry-2")));
		 	action.perform();
		 	driver.findElement(By.id("unit-name-edit-entry-2")).clear();
		 	driver.findElement(By.id("unit-name-edit-entry-2")).sendKeys(optiontext);
		 	
		 	//clicking on add more entry
		 	driver.findElement(By.id("add-new-entry")).click();
		 	//Adding entry 4
		 	action.doubleClick(driver.findElement(By.id("entry-3")));
		 	driver.findElement(By.id("unit-name-edit-entry-3")).clear();
		 	driver.findElement(By.id("unit-name-edit-entry-3")).sendKeys(optiontext);
		 
		 	// Accepting answer
		 	 driver.findElement(By.id("done-button")).click(); 
		}
		
		if(questionType.equals("qtn-text-entry-numeric-img")) // 6. For numeric text entry
		{
			driver.findElement(By.id("question-raw-content")).click();
		 	driver.switchTo().frame("iframe-question-edit").findElement(By.xpath("/html/body")).sendKeys(questiontext);
		 	driver.switchTo().defaultContent();
		 	
		 	// Adding correct answer choice
		 	driver.findElement(By.cssSelector("input[id='correct-ans-text']")).click();
		 	driver.findElement(By.cssSelector("input[id='correct-ans-text']")).clear();
		    driver.findElement(By.cssSelector("input[id='correct-ans-text']")).sendKeys(numerictext);	
		   
		    Thread.sleep(3000);
		 	// Rest two boxes are filled automatically.
		  //Adding alternate answer choice.
		    driver.findElement(By.id("right-alt-container-1")).click();
		    driver.findElement(By.cssSelector("input[id='alternate-answer-text']")).click();
		 	driver.findElement(By.cssSelector("input[id='alternate-answer-text']")).clear();
		    driver.findElement(By.cssSelector("input[id='alternate-answer-text']")).sendKeys(optiontext);
		    // Adding tolerance
		    driver.findElement(By.id("tolerance-ans-text")).click();
		    driver.findElement(By.id("tolerance-ans-text")).clear();
		 	driver.findElement(By.id("tolerance-ans-text")).sendKeys(tolrence);
		 	Thread.sleep(2000);
		    driver.findElement(By.id("done-button")).click(); // Accept answer
		 	  Thread.sleep(2000);
		 	driver.findElement(By.id("display-write-board-checkbox")).click(); // Allow for using white board.
		}
		if(questionType.equals("qtn-text-entry-numeric-units-img")) //7. Adding text entry numeric with units.
		{
			driver.findElement(By.id("question-raw-content")).click();
		 	driver.switchTo().frame("iframe-question-edit").findElement(By.xpath("/html/body")).sendKeys(questiontext);
		 	driver.switchTo().defaultContent();
		 	// Adding correct answer choice
		 	driver.findElement(By.cssSelector("input[id='correct-ans-text']")).click();
		 	driver.findElement(By.cssSelector("input[id='correct-ans-text']")).clear();
		    driver.findElement(By.cssSelector("input[id='correct-ans-text']")).sendKeys(numerictext);	
		 
		    Thread.sleep(3000);
		 	// Rest two boxes are filled automatically.
		    driver.findElement(By.id("add-more-entry")).click();
		    Thread.sleep(3000);
		    // Selecting particular unit
		    List<WebElement> unitvalues = driver.findElements(By.tagName("li"));
		    for(WebElement units : unitvalues)
		    {
		    	if(units.getText().equals(unitoption))
		    	{
		    		units.click();
		    		break;
		    	}
		    }
		    driver.findElement(By.id("done-button")).click(); // Accept answer
		 	  Thread.sleep(2000);
		 	driver.findElement(By.id("display-write-board-checkbox")).click(); // Allow for using white board.
		}
		
		if(questionType.equals("qtn-numeric-maple-img")) //8. Adding maple numeric question
		{
			driver.findElement(By.id("question-raw-content")).click();
		 	driver.switchTo().frame("iframe-question-edit").findElement(By.xpath("/html/body")).sendKeys(questiontext);
		 	driver.switchTo().defaultContent();
		 	// Adding correct answer choice
		 	driver.findElement(By.cssSelector("input[id='correct-ans-text']")).click();
		 	driver.findElement(By.cssSelector("input[id='correct-ans-text']")).clear();
		    driver.findElement(By.cssSelector("input[id='correct-ans-text']")).sendKeys(maplenumeric);	
		
		    Thread.sleep(3000);
		 	// Rest two boxes are filled automatically.
		    //Adding alternate answer choice.
		    driver.findElement(By.id("right-alt-container-1")).click();
		    driver.findElement(By.cssSelector("input[id='alternate-answer-text']")).click();
		 	driver.findElement(By.cssSelector("input[id='alternate-answer-text']")).clear();
		    driver.findElement(By.cssSelector("input[id='alternate-answer-text']")).sendKeys(optiontext);
		    // Adding tolerance
		    driver.findElement(By.id("tolerance-ans-text")).click();
		    driver.findElement(By.id("tolerance-ans-text")).clear();
		 	driver.findElement(By.id("tolerance-ans-text")).sendKeys(tolrence);
		 	Thread.sleep(2000);
		 	 // Accept answer
		    driver.findElement(By.id("done-button")).click();
		 	  Thread.sleep(2000);
		 	// Allow for using white board.
		 	driver.findElement(By.id("display-write-board-checkbox")).click(); 
		}
		
		if(questionType.equals("qtn-math-symbolic-notation-img")) // 9. Adding maple symbolic question
		{
			driver.findElement(By.id("question-raw-content")).click();
		 	driver.switchTo().frame("iframe-question-edit").findElement(By.xpath("/html/body")).sendKeys(questiontext);
		 	driver.switchTo().defaultContent();
		 	Thread.sleep(3000);
		 	// Adding Correct answer
		    driver.findElement(By.cssSelector("div[class='right-container math-correct-answer-input-container']")).click();
		    Thread.sleep(5000);
		    driver.findElement(By.cssSelector("button[title='Square root']")).click();  
	        driver.findElement(By.cssSelector("input[class='wrs_focusElement']")).sendKeys(numerictext);
	        driver.findElement(By.id("wiris-container-save-formaulaeditor")).click();
	        
		 	// Adding Alternate answer
		 	driver.findElement(By.id("right-alt-container-1")).click();
		 	driver.findElement(By.id("alt1")).click();
		    Thread.sleep(5000);
		 	driver.findElement(By.cssSelector("button[title='Square root']")).click();  
	        driver.findElement(By.cssSelector("input[class='wrs_focusElement']")).sendKeys(numerictext);
	        driver.findElement(By.id("wiris-container-save-formaulaeditor")).click();
		 	 // Accept answer
		    driver.findElement(By.id("done-button")).click();
		 	  Thread.sleep(2000);
		 	// Allow for using white board.
		 	driver.findElement(By.id("display-write-board-checkbox")).click(); 
		 	Thread.sleep(5000);
		}
		
		if(questionType.equals("qtn-essay-type")) // 10. Adding Essay type question
		{
			driver.findElement(By.id("question-raw-content")).click();
		 	driver.switchTo().frame("iframe-question-edit").findElement(By.xpath("/html/body")).sendKeys(questiontext);
		 	driver.switchTo().defaultContent();
		 	//Adding height for text entry.
		 	driver.findElement(By.id("essay-question-height")).click();
		 	driver.findElement(By.id("essay-question-height")).sendKeys(numerictext);
		 	// Allow for using white board.
		 	driver.findElement(By.id("display-write-board-checkbox")).click(); 
		}
		if(questionType.equals("qtn-passage-based-img")) // 10. adding passage type question
		{
			driver.findElement(By.id("passage-directions-content")).click();
			driver.findElement(By.id("edit-question-set-name")).click();
			driver.findElement(By.id("edit-question-set-name")).sendKeys(questionset);
			driver.findElement(By.id("assessment-edit-save-btn")).click();
			Thread.sleep(2000);
			driver.findElement(By.id("passage-directions-content")).click();
			driver.switchTo().frame("iframe-question-passage-direction").findElement(By.xpath("/html/body")).sendKeys(passagetext);
		 	driver.switchTo().defaultContent();
		 	driver.findElement(By.id("passage-raw-content")).click();
		 	driver.switchTo().frame("iframe-question-edit-passage-text").findElement(By.xpath("/html/body")).sendKeys(passagedetails);
		 	driver.switchTo().defaultContent();
		 	driver.findElement(By.id("qtn-type-true-false-img")).click();
		 	driver.findElement(By.id("question-raw-content")).click();
		 	driver.switchTo().frame("iframe-question-edit").findElement(By.xpath("/html/body")).sendKeys(questiontext);
		 	driver.switchTo().defaultContent();
		 	//Setting choice 1 as correct answer
		 	Actions action = new Actions(driver);
	        WebElement we = driver.findElement(By.id("choice1"));
	        action.moveToElement(we).build().perform();			 
		    driver.findElement(By.xpath("//img[@title='Set it as Correct Answer']")).click();
		}
		
			//enter Hint
			if(enterHint == true)
			{
                driver.findElement(By.cssSelector("#hint > #question-raw-content")).click();
                Thread.sleep(3000);
                driver.switchTo().frame("iframe-hint").findElement(By.xpath("/html/body")).sendKeys("This is hint text.");//enter hint text
                driver.switchTo().defaultContent();
                Thread.sleep(3000);
			}
		 
		 	//Select Difficulty Level
		 	if(difficultylevel != null)
		 	{
			 driver.findElement(By.linkText("Difficulty Level")).click();
			 Thread.sleep(3000);
			 driver.findElement(By.linkText(difficultylevel)).click();
		 	}
			//associate TLO 
			 if(tloid.length > 0)
			 {
			 driver.findElement(By.id("learing-objectives-span")).click();
			 Thread.sleep(2000);
			 driver.findElement(By.cssSelector("span[title='Add Learning Objective']")).click();
			 
			 int [] tlosids = tloid;
			 for(int tlo_id : tlosids)
			 {
			 WebElement element =driver.findElement(By.xpath("//label[@id='"+Integer.toString(tlo_id)+"']"));
			 ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
			 Thread.sleep(3000);
			 }
			 driver.findElement(By.cssSelector("span.add-collection-to-subtopic")).click();
			 //driver.findElement(By.cssSelector("span.cancel-collection")).click();
			 }
		
		}
		if(publish == true)
		    new ComboBox().selectValue(3, "Publish");
		    driver.findElement(By.id("saveQuestionDetails1")).click();
		 	Thread.sleep(3000);	
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
		String course = Config.course;
		new DirectLogin().CMSLogin();
		String title=driver.getTitle();
		if(title.equals("Course Content Details"))	
			{
			driver.findElement(By.cssSelector("img[alt=\""+course+"\"]")).click();
			driver.findElement(By.cssSelector("div.course-chapter-label.node")).click();
			List<WebElement> elements = driver.findElements(By.className("collection-assessment-name"));
			for(WebElement content : elements)
				{
				if(content.getText().trim().equals(assignmentname))
				{
					
					Thread.sleep(3000);
				    content.click();
            		break;
				}
				}
			
			 	driver.findElement(By.id("questionOptions")).click();
			 	Thread.sleep(2000);
			 	driver.findElement(By.id("addQuestion")).click();
			 	Thread.sleep(2000);
			 	driver.findElement(By.id(passageType)).click();
			 	Thread.sleep(2000);
			 	
			 	if(passageType.equals("qtn-passage-based-img"))
			 	{
			 		//Adding Question set name
			 		driver.findElement(By.id("edit-question-set-name")).click();
			 		driver.findElement(By.id("edit-question-set-name")).sendKeys(setname);
				 	driver.findElement(By.id("assessment-edit-save-btn")).click();
				 	Thread.sleep(2000);
				 	// Adding Passage title
				 	driver.findElement(By.id("passage-directions-content")).click();				 	
				 	driver.switchTo().frame("iframe-question-passage-direction").findElement(By.xpath("/html/body")).sendKeys(questiontext);
				 	driver.switchTo().defaultContent();
				 	// Adding a passage
				 	driver.findElement(By.id("passage-raw-content")).click();
				 	driver.switchTo().frame("iframe-question-edit-passage-text").findElement(By.xpath("/html/body")).sendKeys(passage);
				 	driver.switchTo().defaultContent();
				 	driver.findElement(By.id("question-editor-outer-wrapper")).click();
				 	Thread.sleep(2000);
				 	// Adding different types of questions
				 	
				 	 ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.id(questionType)));
				 //		driver.findElement(By.id(questionType)).click();
				 		Thread.sleep(2000);
				 		if(questionType.equals("qtn-type-true-false-img")) //1.For true-false type question
				 		{
				 			driver.findElement(By.id("question-raw-content")).click();
				 			driver.switchTo().frame("iframe-question-edit").findElement(By.xpath("/html/body")).sendKeys(questiontext);
				 			driver.switchTo().defaultContent();
				 			//Setting choice 1 as correct answer
				 			Actions action = new Actions(driver);
				 			WebElement we = driver.findElement(By.id("choice1"));
				 			action.moveToElement(we).build().perform();			 
				 			driver.findElement(By.xpath("//img[@title='Set it as Correct Answer']")).click();
				 		} 
				        if(questionType.equals("qtn-multiple-choice-img")) //2. For multiple choice question.
				        {
				        	driver.findElement(By.id("question-raw-content")).click();
				        	driver.switchTo().frame("iframe-question-edit").findElement(By.xpath("/html/body")).sendKeys(questiontext);
				        	driver.switchTo().defaultContent();
				        	//Adding choice 1
				        	driver.findElement(By.id("choice1")).click();
				        	driver.switchTo().frame("iframe-choice1").findElement(By.xpath("/html/body")).sendKeys(answertext);			 	
				        	driver.switchTo().defaultContent();
				        	//Adding choice 2
				        	driver.findElement(By.id("choice2")).click();
				        	driver.switchTo().frame("iframe-choice2").findElement(By.xpath("/html/body")).sendKeys(answertext);			 	
				        	driver.switchTo().defaultContent();
				        	//Adding choice 3
				        	driver.findElement(By.id("choice3")).click();
				        	driver.switchTo().frame("iframe-choice3").findElement(By.xpath("/html/body")).sendKeys(answertext);			 	
				        	driver.switchTo().defaultContent();
				        	//Adding choice 4
				        	driver.findElement(By.id("choice4")).click();
				        	driver.switchTo().frame("iframe-choice4").findElement(By.xpath("/html/body")).sendKeys(answertext);			 	
				        	driver.switchTo().defaultContent();
				        	//Setting choice 2 as correct answer
				        	Actions action = new Actions(driver);
				        	WebElement we = driver.findElement(By.id("choice2"));
				        	action.moveToElement(we).build().perform();			 
				        	driver.findElement(By.xpath("//img[@title='Set it as Correct Answer']")).click();
				        }
				        if(questionType.equals("qtn-multiple-selection-img"))  //3. For multiple sections
				        {
				        	driver.findElement(By.id("question-raw-content")).click();
				        	driver.switchTo().frame("iframe-question-edit").findElement(By.xpath("/html/body")).sendKeys(questiontext);
				        	driver.switchTo().defaultContent();
				        	//Adding choice 1
				        	driver.findElement(By.id("choice1")).click();
				        	driver.switchTo().frame("iframe-choice1").findElement(By.xpath("/html/body")).sendKeys(answertext);			 	
				        	driver.switchTo().defaultContent();
				        	//Adding choice 2
				        	driver.findElement(By.id("choice2")).click();
				        	driver.switchTo().frame("iframe-choice2").findElement(By.xpath("/html/body")).sendKeys(answertext);			 	
				        	driver.switchTo().defaultContent();
				        	//Adding choice 3
				        	driver.findElement(By.id("choice3")).click();
				        	driver.switchTo().frame("iframe-choice3").findElement(By.xpath("/html/body")).sendKeys(answertext);			 	
				        	driver.switchTo().defaultContent();
				        	//Adding choice 4
				        	driver.findElement(By.id("choice4")).click();
				        	driver.switchTo().frame("iframe-choice4").findElement(By.xpath("/html/body")).sendKeys(answertext);			 	
				        	driver.switchTo().defaultContent();
				        	//Setting choice 2 as correct answer
				        	Actions action1 = new Actions(driver);
				        	WebElement we1 = driver.findElement(By.id("choice2"));
				        	action1.moveToElement(we1).build().perform();			 
				        	driver.findElement(By.xpath("//img[@title='Set it as Correct Answer']")).click();
				        	//Setting choice 4 as correct answer
				        	Actions action2 = new Actions(driver);
				        	WebElement we2 = driver.findElement(By.id("choice4"));
				        	action2.moveToElement(we2).build().perform();			 
				        	driver.findElement(By.xpath("//img[@title='Set it as Correct Answer']")).click();
				        }
				        if(questionType.equals("qtn-text-entry-img")) // 4.For Text Entry type.
				        {
				        	driver.findElement(By.id("question-raw-content")).click();
				        	driver.switchTo().frame("iframe-question-edit").findElement(By.xpath("/html/body")).sendKeys(questiontext);
				        	driver.switchTo().defaultContent();
				        	// Adding correct answer choice
				        	driver.findElement(By.cssSelector("input[id='correct-ans-text']")).click();
				        	driver.findElement(By.cssSelector("input[id='correct-ans-text']")).clear();
				        	driver.findElement(By.cssSelector("input[id='correct-ans-text']")).sendKeys(answertext);	
				        	Thread.sleep(3000);
				        	// Rest two boxes are filled automatically.
				        	//Adding alternate answer choice.
				        	driver.findElement(By.id("right-alt-container-1")).click();
				        	driver.findElement(By.cssSelector("input[id='alternate-answer-text']")).click();
				        	driver.findElement(By.cssSelector("input[id='alternate-answer-text']")).clear();
				        	driver.findElement(By.cssSelector("input[id='alternate-answer-text']")).sendKeys(optiontext);	
				 
				        	driver.findElement(By.id("done-button")).click(); // Accept answer
				        	Thread.sleep(2000);
				        }
				        if(questionType.equals("qtn-text-entry-drop-down-img")) //5. For text entry drop down
				        {
				        	driver.findElement(By.id("question-raw-content")).click();
				        	driver.switchTo().frame("iframe-question-edit").findElement(By.xpath("/html/body")).sendKeys(questiontext);
				        	driver.switchTo().defaultContent();
				        	// Adding Entry 1
				        	driver.findElement(By.id("entry-0")).click();
				        	driver.findElement(By.id("unit-name-edit-entry-0")).clear();
				        	driver.findElement(By.id("unit-name-edit-entry-0")).sendKeys(optiontext);
				        	Thread.sleep(2000);
				        	  // Accepting answer
						 	WebElement menuitem = driver.findElement(By.id("entry-1")); 
				        	Locatable hoverItem = (Locatable) menuitem;
				        	Mouse mouse = ((HasInputDevices) driver).getMouse();
				        	mouse.mouseMove(hoverItem.getCoordinates());
				        	List<WebElement> selectanswerticks = driver.findElements(By.className("mark-selected"));        	
				        	selectanswerticks.get(1).click(); //select second option as correct answer
				        	
				        	// Adding Entry 2
				        	Actions action = new Actions(driver);
				        	action.doubleClick(driver.findElement(By.id("entry-1")));
				        	action.perform();
				        	driver.findElement(By.id("unit-name-edit-entry-1")).clear();
				        	driver.findElement(By.id("unit-name-edit-entry-1")).sendKeys(optiontext);
				        	Thread.sleep(2000);
	
				        	// Adding Entry 3
				        	action = new Actions(driver);
				        	action.doubleClick(driver.findElement(By.id("entry-2")));
						 	action.perform();
						 	Thread.sleep(2000);
						 	driver.findElement(By.id("unit-name-edit-entry-2")).clear();
						 	driver.findElement(By.id("unit-name-edit-entry-2")).sendKeys(optiontext);
						 	Thread.sleep(2000);
				 			//clicking on add more entry
				 			driver.findElement(By.id("add-new-entry")).click();
				 			//Adding entry 4
				 			action.doubleClick(driver.findElement(By.id("entry-3")));
				 			driver.findElement(By.id("unit-name-edit-entry-3")).clear();
				 			driver.findElement(By.id("unit-name-edit-entry-3")).sendKeys(optiontext);
				 			// Accepting answer
				 			driver.findElement(By.id("done-button")).click(); 
				         }
				
					        if(questionType.equals("qtn-text-entry-numeric-img")) // 6. For numeric text entry
					        {
					        	driver.findElement(By.id("question-raw-content")).click();
						 	driver.switchTo().frame("iframe-question-edit").findElement(By.xpath("/html/body")).sendKeys(questiontext);
						 	driver.switchTo().defaultContent();
						 	// Adding correct answer choice
						 	driver.findElement(By.cssSelector("input[id='correct-ans-text']")).click();
						 	driver.findElement(By.cssSelector("input[id='correct-ans-text']")).clear();
						    driver.findElement(By.cssSelector("input[id='correct-ans-text']")).sendKeys(numerictext);	
						    driver.switchTo().defaultContent();
						    Thread.sleep(3000);
						 	// Rest two boxes are filled automatically.
						  //Adding alternate answer choice.
						    driver.findElement(By.id("right-alt-container-1")).click();
						    driver.findElement(By.cssSelector("input[id='alternate-answer-text']")).click();
						 	driver.findElement(By.cssSelector("input[id='alternate-answer-text']")).clear();
						    driver.findElement(By.cssSelector("input[id='alternate-answer-text']")).sendKeys(optiontext);
						    // Adding tolerance
						    driver.findElement(By.id("tolerance-ans-text")).click();
						 	driver.findElement(By.id("tolerance-ans-text")).sendKeys(tolrence);
						 	Thread.sleep(2000);
						    driver.findElement(By.id("done-button")).click(); // Accept answer
						 	  Thread.sleep(2000);
						 	driver.findElement(By.id("display-write-board-checkbox")).click(); // Allow for using white board.
						}
						if(questionType.equals("qtn-text-entry-numeric-units-img")) //7. Adding text entry numeric with units.
						{
							driver.findElement(By.id("question-raw-content")).click();
						 	driver.switchTo().frame("iframe-question-edit").findElement(By.xpath("/html/body")).sendKeys(questiontext);
						 	driver.switchTo().defaultContent();
						 	// Adding correct answer choice
						 	driver.findElement(By.cssSelector("input[id='correct-ans-text']")).click();
						 	driver.findElement(By.cssSelector("input[id='correct-ans-text']")).clear();
						    driver.findElement(By.cssSelector("input[id='correct-ans-text']")).sendKeys(numerictext);	
						    
						    Thread.sleep(3000);
						 	// Rest two boxes are filled automatically.
						    driver.findElement(By.id("add-more-entry")).click();
						    Thread.sleep(3000);
						    // Selecting particular unit
						    List<WebElement> unitvalues = driver.findElements(By.tagName("li"));
						    for(WebElement units : unitvalues)
						    {
						    	//System.out.println(units.getText());
						    	if(units.getText().equals(unitoption))
						    	{
						    		units.click();
						    		break;
						    	}
						    }
						    driver.findElement(By.id("done-button")).click(); // Accept answer
						 	  Thread.sleep(2000);
						 	driver.findElement(By.id("display-write-board-checkbox")).click(); // Allow for using white board.
						}
						
						if(questionType.equals("qtn-numeric-maple-img")) //8. Adding maple numeric question
						{
							driver.findElement(By.id("question-raw-content")).click();
						 	driver.switchTo().frame("iframe-question-edit").findElement(By.xpath("/html/body")).sendKeys(questiontext);
						 	driver.switchTo().defaultContent();
						 	// Adding correct answer choice
						 	driver.findElement(By.cssSelector("input[id='correct-ans-text']")).click();
						 	driver.findElement(By.cssSelector("input[id='correct-ans-text']")).clear();
						    driver.findElement(By.cssSelector("input[id='correct-ans-text']")).sendKeys(maplenumeric);	
						 
						    Thread.sleep(3000);
						 	// Rest two boxes are filled automatically.
						    //Adding alternate answer choice.
						    driver.findElement(By.id("right-alt-container-1")).click();
						    driver.findElement(By.cssSelector("input[id='alternate-answer-text']")).click();
						 	driver.findElement(By.cssSelector("input[id='alternate-answer-text']")).clear();
						    driver.findElement(By.cssSelector("input[id='alternate-answer-text']")).sendKeys(optiontext);
						    // Adding tolerance
						    driver.findElement(By.id("tolerance-ans-text")).click();
						 	driver.findElement(By.id("tolerance-ans-text")).sendKeys(tolrence);
						 	Thread.sleep(2000);
						 	 // Accept answer
						    driver.findElement(By.id("done-button")).click();
						 	  Thread.sleep(2000);
						 	// Allow for using white board.
						 	driver.findElement(By.id("display-write-board-checkbox")).click(); 
						}
				
						if(questionType.equals("qtn-math-symbolic-notation-img")) // 9. Adding expressoin evaluater
						{
							driver.findElement(By.id("question-raw-content")).click();
						 	driver.switchTo().frame("iframe-question-edit").findElement(By.xpath("/html/body")).sendKeys(questiontext);
						 	driver.switchTo().defaultContent();
						 	Thread.sleep(3000);
						 	// Adding Correct answer
						    driver.findElement(By.cssSelector("div[class='right-container math-correct-answer-input-container']")).click();
						    Thread.sleep(5000);
						    driver.findElement(By.cssSelector("button[title='Square root']")).click();  
					        driver.findElement(By.cssSelector("input[class='wrs_focusElement']")).sendKeys(numerictext);
					        driver.findElement(By.id("wiris-container-save-formaulaeditor")).click();
					        
						 	// Adding Alternate answer
						 	driver.findElement(By.id("right-alt-container-1")).click();
						 	driver.findElement(By.id("alt1")).click();
						    Thread.sleep(5000);
						 	driver.findElement(By.cssSelector("button[title='Square root']")).click();  
					        driver.findElement(By.cssSelector("input[class='wrs_focusElement']")).sendKeys(numerictext);
					        driver.findElement(By.id("wiris-container-save-formaulaeditor")).click();
						 	 // Accept answer
						    driver.findElement(By.id("done-button")).click();
						 	  Thread.sleep(2000);
						 	// Allow for using white board.
						 	driver.findElement(By.id("display-write-board-checkbox")).click(); 
						 	Thread.sleep(2000);
						}
						
						//((JavascriptExecutor) driver).executeScript("arguments[0].click();",driver.findElement(By.cssSelector("div[id='qtn-essay-type']")));	
					
						if(questionType.equals("qtn-essay-type")) // 10. Adding Essay type question
						{
							
							driver.findElement(By.id("question-raw-content")).click();
						 	driver.switchTo().frame("iframe-question-edit").findElement(By.xpath("/html/body")).sendKeys(questiontext);
						 	driver.switchTo().defaultContent();
						 	//Adding height for text entry.
						 	driver.findElement(By.id("essay-question-height")).click();
						 	driver.findElement(By.id("essay-question-height")).sendKeys(numerictext);
						 	// Allow for using white board.
						 	driver.findElement(By.id("display-write-board-checkbox")).click(); 
						}
						
			 	}
			 
			 		new ComboBox().selectValue(3, "Publish");
					driver.findElement(By.id("saveQuestionDetails1")).click();
					Thread.sleep(3000);	
			 	
					 	
			 		
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
		new Navigator().NavigateTo("Assignments");

		driver.findElement(By.cssSelector("span.ins-assignment-button-sprite.instructor-assignment-new")).click();

		Thread.sleep(3000);
		if(driver.findElement(By.cssSelector("div[class='tab active']")).getText().equals("My Resources"))
		{//Opening All Resources tab if not opened after clicking on New Assignment button

			driver.findElement(By.cssSelector("span[title='All Resources']")).click();
		}
		//Adding assignment to search
		driver.findElement(By.id("all-resource-search-textarea")).clear();
		driver.findElement(By.id("all-resource-search-textarea")).sendKeys("\""+assignmentname+"\"");
		driver.findElement(By.cssSelector("#all-resource-search-button > span.ins-resource-search-sprite.instructor-resource-search-img")).click();
		Thread.sleep(3000);
		List<WebElement> assign = driver.findElements(By.className("assign-this"));
		
		for(WebElement assignment: assign)
		{
			
			if(assignment.getText().trim().equals("Assign This"))
			{
				
				assignment.click();
				break;
			}
			
		}
		new ShareWith().share(shareWithInitialString, shareName, shareWithClass,true);
		Thread.sleep(3000);
		driver.findElement(By.id("due-date")).click();
		driver.findElement(By.linkText(duedate)).click();
		if(gradeable.equals("true"))
		{
		driver.findElement(By.className("ir-ls-assign-dialog-gradable-label-check")).click();
		driver.findElement(By.id("grading-policy")).sendKeys(gradingpolicy);
		}
		if(accessibleafter != null)
		{
			driver.findElement(By.id("accessible-date")).click();
			driver.findElement(By.linkText(accessibleafter)).click();
		}
		
		
		driver.findElement(By.id("due-time")).click();
		List<WebElement> elements = driver.findElements(By.xpath("//li"));
		for(WebElement time : elements)
		{
			if(time.getText().equals(duetime))
			{
				time.click();
				break;
			}
		}
		driver.findElement(By.className("input-filed")).sendKeys("shor"); //short label
		Thread.sleep(2000);
		driver.findElement(By.id("additional-notes")).clear();
	    driver.findElement(By.id("additional-notes")).sendKeys(additionalnote);
	    
	    driver.findElement(By.cssSelector("span[class='btn sty-green submit-assign']")).click();
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
		String  shareWithInitialString = ReadTestData.readDataByTagName("", "shareWithInitialString", Integer.toString(dataIndex));
		String shareName = ReadTestData.readDataByTagName("", "shareName", Integer.toString(dataIndex));
		String shareWithClass = ReadTestData.readDataByTagName("", "shareWithClass", Integer.toString(dataIndex));
		String assignmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
		new Navigator().NavigateTo("Assignments");
		//Clicking on New Assignment Button
		driver.findElement(By.cssSelector("span.ins-assignment-button-sprite.instructor-assignment-new")).click();
		Thread.sleep(3000);
		if(driver.findElement(By.cssSelector("div[class='tab active']")).getText().equals("My Resources"))
		{//Opening All Resources tab if not opened after clicking on New Assignment button

			driver.findElement(By.cssSelector("span[title='All Resources']")).click();
		}
		//Adding assignment to search
		driver.findElement(By.id("all-resource-search-textarea")).clear();
	    driver.findElement(By.id("all-resource-search-textarea")).sendKeys("\""+assignmentname+"\"");
	    driver.findElement(By.cssSelector("#all-resource-search-button > span.ins-resource-search-sprite.instructor-resource-search-img")).click();
	    Thread.sleep(2000);
	    driver.findElement(By.cssSelector("span[class='assign-this update-assignment']")).click();
	    
	    //driver.findElement(By.cssSelector("span[class='assign-this update-assignment']")).click();
	    
	    if(addShareWith == true)
		new ShareWith().share(shareWithInitialString, shareName, shareWithClass,false);
		
	    driver.findElement(By.cssSelector("span[class='btn sty-green submit-assign']")).click();
	    
		}
		catch(Exception e)
		{
			Assert.fail("Exception in app helper updateAssignment in class Assignment",e);
		}
		
	}
	
	public void submitAssignmentAsStudent(int dataIndex)
	{
		try
		{
		String submitassignment = ReadTestData.readDataByTagName("", "submitassignment", Integer.toString(dataIndex));
		new Navigator().NavigateTo("Course Stream");
		driver.findElement(By.cssSelector("span[class='ls-stream-assignment-title']")).click();
		Thread.sleep(3000);
		int helppage = driver.findElements(By.className("close-help-page")).size(); 
		     if(helppage == 1)
		    	 driver.findElement(By.className("close-help-page")).click();
	
	   int timer = 1;
	   while(timer != 0)
	   {
		   if(driver.findElements(By.className("qtn-label")).size() > 0)
		driver.findElement(By.className("qtn-label")).click();
		int submitbutton= driver.findElements(By.cssSelector("a[class='btn btn--primary btn--large btn--submit']")).size();
		if(submitbutton>=1)
		{
			
			if(submitassignment == null){
				driver.findElement(By.linkText("Submit")).click();
				Thread.sleep(3000);//submitting the assignment as student 1 
			}
			else
				break;
		}
		else
		{
		  driver.findElement(By.cssSelector("a[class='btn btn--primary btn--large btn--next']")).click();//click on next button
		  Thread.sleep(3000);
		}
		 timer=driver.findElements(By.id("assessmentTimer")).size();
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
				 timer=driver.findElements(By.id("assessmentTimer")).size();
				 if(timer == 0)
						break;
				int valueofoption=driver.findElements(By.className("qtn-label")).size();
				if(valueofoption>=1)
					driver.findElement(By.className("qtn-label")).click();
		
			/*	List<WebElement> textbox = driver.findElements(By.tagName("input"));
				forloop:
				for(WebElement box : textbox)
				{				
					if(box.isDisplayed() == true)
					{
						box.sendKeys("abcd");
						break forloop;
					}
				}*/
				int submitbutton= driver.findElements(By.cssSelector("a[class='btn btn--primary btn--large btn--submit']")).size();
				 if(submitbutton>=1)
					 driver.findElement(By.linkText("Submit")).click(); //submitting the assignment as student  
				 else
				 driver.findElement(By.cssSelector("a[class='btn btn--primary btn--large btn--next']")).click();//click on next button
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
		List<WebElement> assignments =  driver.findElements(By.cssSelector("span[class='ls-assignment-name instructor-assessment-review']")); 
		 for(WebElement element : assignments)
		 {
			 if(element.getText().contains(assessmentname))
					 {
				 		break;
					 }
			 index++;
		 }
		 List<WebElement> status = driver.findElements(By.className("ls-assignment-status"));
		 
		if(!status.get(index).getText().equals(expectedStatus))
			Assert.fail("Status of the asignment in Instructor Dashboard is "+status.get(index).getText()+" which is not equal to expected status: "+expectedStatus);
		if(statuscolor!= null)
		{
			if(expectedStatus.equals("Status:  Grading in Progress"))
			{
			WebElement statuscolors = driver.findElement(By.cssSelector("span[class='ls-assignment-status-grading-in-progress']"));
			if(!statuscolors.getCssValue("color").equals(statuscolor))
				Assert.fail("Status color is "+statuscolors.getCssValue("color")+" which is not equal to expected color "+statuscolor);
			}
			if(expectedStatus.equals("Status:  Graded") || expectedStatus.equals("Status:  Reviewed"))
			{
			WebElement statuscolors = driver.findElement(By.cssSelector("span[class='ls-assignment-status-grades-released']"));
			if(!statuscolors.getCssValue("color").equals(statuscolor))
				Assert.fail("Status color is "+statuscolors.getCssValue("color")+" which is not equal to expected color "+statuscolor);
			}
		}
		//List<WebElement> firstblock =  driver.findElements(By.cssSelector("div[class='ls-instructor-activity-cards ls-instructor-cards-first not-started']")); 
		//System.out.println(firstblock.get(index).getText());
	}

	
	

	public int statusBoxCount(int dataIndex,String boxName)
	{
		String [] cntarray = {""};
		String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
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
		 if(boxName.equals("Not Started"))
		 {
		 List<WebElement> notstartedbox = driver.findElements(By.cssSelector("div[class='ls-instructor-activity-cards ls-instructor-cards-first not-started']"));
		 
		 String cnt = notstartedbox.get(index).getText();
		 cntarray = cnt.split("\\n");		
		 }
		 if(boxName.equals("In Progress"))
		 {
		 List<WebElement> inprogress = driver.findElements(By.cssSelector("div[class='ls-instructor-activity-cards ls-instructor-cards-second in-progress']"));
		 
		 String cnt = inprogress.get(index).getText();
		 cntarray = cnt.split("\\n");		
		 }
		 if(boxName.equals("Submitted"))
		 {
		 List<WebElement> submitted = driver.findElements(By.cssSelector("div[class='ls-instructor-activity-cards ls-instructor-cards-third submitted']"));
		 
		 String cnt = submitted.get(index).getText();
		 cntarray = cnt.split("\\n");		
		 }
		 if(boxName.equals("Graded"))
		 {
		 List<WebElement> graded = driver.findElements(By.cssSelector("div[class='ls-instructor-activity-cards ls-instructor-cards-fourth submitted']"));
		 
		 String cnt = graded.get(index).getText();
		 cntarray = cnt.split("\\n");		
		 }
		 if(boxName.equals("Reviewed"))
		 {
		 List<WebElement> graded = driver.findElements(By.cssSelector("div[class='ls-instructor-activity-cards ls-instructor-cards-fourth submitted']"));
		 
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
			List<WebElement> assignments =  driver.findElements(By.cssSelector("span[class='ls-assignment-name instructor-assessment-review']")); 
			 for(WebElement element : assignments)
			 {
				 if(element.getText().contains(assessmentname))
						 {
					 		break;
						 }
				 index++;
			 }
			 
			 List<WebElement> notstarted = driver.findElements(By.cssSelector("div[class='ls-instructor-activity-cards ls-instructor-cards-first not-started']"));
			 
			 List<WebElement> inprogress = driver.findElements(By.cssSelector("div[class='ls-instructor-activity-cards ls-instructor-cards-second in-progress']"));
			 
			 List<WebElement> submitted = driver.findElements(By.cssSelector("div[class='ls-instructor-activity-cards ls-instructor-cards-third submitted']"));
			 
			 List<WebElement> reviewed = driver.findElements(By.cssSelector("div[class='ls-instructor-activity-cards ls-instructor-cards-fourth submitted']"));
			 
			 List<WebElement> graded = driver.findElements(By.cssSelector("div[class='ls-instructor-activity-cards ls-instructor-cards-fourth submitted']"));
			
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
		List<WebElement> assignments =  driver.findElements(By.cssSelector("span[class='ls-assignment-name instructor-assessment-review']")); 
		 for(WebElement element : assignments)
		 {
			 if(element.getText().contains(assessmentname))
					 {
				 		break;
					 }
			 index++;
		 }
		 List<WebElement> viewresponseslink = driver.findElements(By.cssSelector("span[class='ls-grade-book-assessment']"));
		   ((JavascriptExecutor) driver).executeScript("arguments[0].click();", viewresponseslink.get(index));
		 //viewresponseslink.get(2).click();
		 
		 driver.findElement(By.cssSelector("div[title='"+releaseAction+"']")).click();
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
		List<WebElement> assignments =  driver.findElements(By.cssSelector("span[class='ls-assignment-name instructor-assessment-review']")); 
		 for(WebElement element : assignments)
		 {
			 if(element.getText().contains(assessmentname))
					 {
				 		break;
					 }
			 index++;
		 }
		 List<WebElement> viewgradeslink = driver.findElements(By.cssSelector("span[class='ls-grade-book-assessment']"));
		 if( !viewgradeslink.get(index).isDisplayed())
			 Assert.fail("View Grades link not displayed");
		 if(!viewgradeslink.get(index).getText().equals("View Grades"))
			 Assert.fail("View Grades link text is not as expected");
		 
		 List<WebElement> graphs = driver.findElements(By.className("ls-assignment-performance-graph")); //validating the grade graph by clicking it
		 ((JavascriptExecutor) driver).executeScript("arguments[0].click();", graphs.get(index));
		 
		 List<WebElement> xaxis = driver.findElements(By.id("idb-stud-score-bar-label-left"));
		 if(!xaxis.get(index).getAttribute("title").equals("Number of Students"))
			 Assert.fail("x-axis of grade graph not shown as 'Number of Students'");
		 
		 List<WebElement> yaxis = driver.findElements(By.id("idb-stud-score-bar-chart-footer-label"));
		 if(!yaxis.get(index).getAttribute("title").equals("Percentage Score Range"))
			 Assert.fail("y-axis of grade graph not shown as 'Score Range'");
		   ((JavascriptExecutor) driver).executeScript("arguments[0].click();", viewgradeslink.get(index));
		   Thread.sleep(3000);
		   if(!driver.findElement(By.className("idb-gradebook-header-div")).getText().equals("Assignment Responses"))
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
			List<WebElement> allInstructorName = driver.findElements(By.className("ls-assignment-item-author-name"));
			for (WebElement name: allInstructorName) 		 
				stringarray.add(name.getText());
		 
			if(!stringarray.contains(fullName))
				Assert.fail("Instructor Name is not present after posting");
			
			
			stringarray.clear();
			
			List<WebElement> allAssesmentName = driver.findElements(By.cssSelector("span[class='ls-assignment-name instructor-assessment-review']"));
			
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
			List<WebElement> assignments =  driver.findElements(By.cssSelector("span[class='ls-assignment-name instructor-assessment-review']")); 
			 for(WebElement element : assignments)
			 {
				 if(element.getText().contains(assessmentname))
						 {
					 		break;
						 }
				 index++;
			 }
			 List<WebElement> viewresponseslink = driver.findElements(By.cssSelector("span[class='ls-grade-book-assessment']"));
			   ((JavascriptExecutor) driver).executeScript("arguments[0].click();", viewresponseslink.get(index));
			   List<WebElement> menuitem = driver.findElements(By.cssSelector("span[class='idb-gradebook-content-label-text idb-gradebook-username']"));
			   for(WebElement user : menuitem)
			   {
				   
				   if(user.getText().equals(studentname))
				   {
					   Locatable hoverItem = (Locatable) user;
						  Mouse mouse = ((HasInputDevices) driver).getMouse();
						   mouse.mouseMove(hoverItem.getCoordinates());
					   driver.findElement(By.id("idb-grade-now-link")).click();   
					   driver.findElement(By.cssSelector("input[class='idb-grade-points']")).clear();

					   driver.findElement(By.cssSelector("input[class='idb-grade-points']")).sendKeys("0.6");

					  // driver.findElement(By.cssSelector("input[class='idb-grade-points']")).sendKeys("0.4");
					   driver.findElement(By.className("idb-grader-content-header-row")).click();
					   Thread.sleep(3000);

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
			List<WebElement> assignments =  driver.findElements(By.cssSelector("span[class='ls-assignment-name instructor-assessment-review']")); 
			 for(WebElement element : assignments)
			 {
				 if(element.getText().contains(assessmentname))
						 {
					 		break;
						 }
				 index++;
			 }
			List<WebElement> likelinks =  driver.findElements(By.className("ls-post-like-link"));
			if(!likelinks.get(index).getText().equals("Like")) //Validating if assignment is liked to unlike it
				Assert.fail("The assignment is already liked so can not like it again.");
			 ((JavascriptExecutor) driver).executeScript("arguments[0].click();", likelinks.get(index)); //Clicking on like
			 
			 List<WebElement> likecounts = driver.findElements(By.className("ls-post-like-count"));
			 String likecount = likecounts.get(index).getText(); //Validating link count
				if(!likecount.equals("1"))
					Assert.fail("Like count of the assignment not equal to 1");
				
			List<WebElement> likelinksafterclicking = driver.findElements(By.className("ls-post-like-link"));
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
			List<WebElement> assignments =  driver.findElements(By.cssSelector("span[class='ls-assignment-name instructor-assessment-review']")); 
			 for(WebElement element : assignments)
			 {
				 if(element.getText().contains(assessmentname))
						 {
					 		break;
						 }
				 index++;
			 }
			List<WebElement> likelinks =  driver.findElements(By.className("ls-post-like-link"));
			if(!likelinks.get(index).getText().equals("Unlike")) //Validating if assignment is liked to unlike it
				Assert.fail("The assignment is not liked so can not unlike it.");
			 ((JavascriptExecutor) driver).executeScript("arguments[0].click();", likelinks.get(index)); //Clicking on unlike
			 
			 List<WebElement> likecounts = driver.findElements(By.className("ls-post-like-count"));
			 String likecount = likecounts.get(index).getText(); //Validating link count
				if(!likecount.equals("0"))
					Assert.fail("Like count of the assignment not equal to 1");
				
			List<WebElement> likelinksafterclicking = driver.findElements(By.className("ls-post-like-link"));
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
			List<WebElement> assignments =  driver.findElements(By.cssSelector("span[class='ls-assignment-name instructor-assessment-review']")); 
			 for(WebElement element : assignments)
			 {
				 if(element.getText().contains(assessmentname))
						 {
					 		break;
						 }
				 index++;
			 }
			 List<WebElement> commentarea = driver.findElements(By.className("ls-textarea-focus"));
			 commentarea.get(index).sendKeys(random+Keys.ENTER);
			 Thread.sleep(3000);
			//Searching for comment posted
			 boolean commentfound = false;
			 List<WebElement> comments = driver.findElements(By.className("ls-stream-post__comment-text"));
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
		List<WebElement> assignments =  driver.findElements(By.cssSelector("span[class='ls-assignment-name instructor-assessment-review']")); 
		 for(WebElement element : assignments)
		 {
			 if(element.getText().contains(assessmentname))
					 {
				 		break;
					 }
			 index++;
		 }
		 List<WebElement> commentscount = driver.findElements(By.className("ls-stream-post-comment-count"));
		return Integer.parseInt(commentscount.get(index).getText());
	}
	
	public void assignFormValidate(int dataIndex)
	{
		try
		{
		String assignmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
		driver.findElement(By.cssSelector("span.ins-assignment-button-sprite.instructor-assignment-new")).click();
		Thread.sleep(3000);
		if(driver.findElement(By.cssSelector("div[class='tab active']")).getText().equals("My Resources"))	//Opening All Resources tab if not opened after clicking on New Assignment button
			driver.findElement(By.className("tab")).click();
		
		//Adding assignment to search
		driver.findElement(By.id("all-resource-search-textarea")).clear();
		driver.findElement(By.id("all-resource-search-textarea")).sendKeys("\""+assignmentname+"\"");
		driver.findElement(By.cssSelector("#all-resource-search-button > span.ins-resource-search-sprite.instructor-resource-search-img")).click();
		Thread.sleep(3000);
		List<WebElement> assign = driver.findElements(By.className("assign-this"));
		
		for(WebElement assignment: assign)
		{
			
			if(assignment.getText().trim().equals("Assign This"))
			{
				
				assignment.click();
				break;
			}
			
		}
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
			List<WebElement> assignments =  driver.findElements(By.cssSelector("span[class='ls-assignment-name instructor-assessment-review']")); 
			 for(WebElement element : assignments)
			 {
				 if(element.getText().contains(assessmentname))
						 {
					 		break;
						 }
				 index++;
			 }
			 List<WebElement> viewresponseslink = driver.findElements(By.cssSelector("span[class='ls-grade-book-assessment']"));
			   ((JavascriptExecutor) driver).executeScript("arguments[0].click();", viewresponseslink.get(index));
			   Thread.sleep(3000);
			   
			   //finding the index of the particular student
			 int index1 = 0;
			 List<WebElement> usernames =  driver.findElements(By.cssSelector("span[class='idb-gradebook-content-label-text idb-gradebook-username']"));
				 for(WebElement element : usernames)
				 {
					 if(element.getText().equals(studentname))
							 {
						 
						 		break;
							 }
					 index1++;
				 }				  
				
				 
		    List<WebElement> gradebook = driver.findElements(By.className("idb-question-score-wrapper"));
				  
					 Locatable hoverItem = (Locatable) gradebook.get(index1);
					 Mouse mouse = ((HasInputDevices) driver).getMouse();
			mouse.mouseMove(hoverItem.getCoordinates());
		  ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("ls-view-response-link")));

			driver.findElement(By.id("view-user-question-performance-feedback-box")).sendKeys("This is a FeedbackText");
					  
			driver.findElement(By.className("view-user-question-performance-save-btn")).click();   
				
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
		List<WebElement> allElements=driver.findElements(By.cssSelector("span[class='ls-assignment-name instructor-assessment-review']"));
		for(WebElement element: allElements)
		{				
			if(element.getText().trim().contains(submitassignment))
			{
				element.click();			
				int helppage = driver.findElements(By.className("close-help-page")).size(); 
				if(helppage == 1)
		    	driver.findElement(By.className("close-help-page")).click();
				Thread.sleep(3000);
				driver.findElement(By.className("qtn-label")).click();
				Thread.sleep(3000);
				driver.findElement(By.linkText("Submit")).click(); //submitting the assignment as student 1 
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
			List<WebElement> allelements=driver.findElements(By.cssSelector("span[class='ls-assignment-name instructor-assessment-review']"));
			for(WebElement elements:allelements)
			{
				String assigntext=elements.getText();

				if(assigntext.contains(text))
				{
					
					((JavascriptExecutor) driver).executeScript("arguments[0].click();",elements);
					//elements.click();
					int helppage = driver.findElements(By.className("close-help-page")).size(); 
				     if(helppage!=0)
				    	 driver.findElement(By.className("close-help-page")).click();
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
				new LoginUsingLTI().ltiLogin(studentid);
				new UpdateContentIndex().updatecontentindex(instructor1dataindex);
				new LoginUsingLTI().ltiLogin(instructor1dataindex);
				new Assignment().assignToStudent(Integer.parseInt(instructor1dataindex));
				
			}
			if(numberofassignment==2)
			{
				new Assignment().create(Integer.parseInt(instructor1dataindex));
				new Assignment().create(Integer.parseInt(instructor2dataindex));				
				new LoginUsingLTI().ltiLogin(studentid);
				new UpdateContentIndex().updatecontentindex(instructor1dataindex);
				new LoginUsingLTI().ltiLogin(instructor1dataindex);
				new Assignment().assignToStudent(Integer.parseInt(instructor1dataindex));
				new LoginUsingLTI().ltiLogin(instructor2dataindex);
				new Assignment().assignToStudent(Integer.parseInt(instructor2dataindex));				
				
			}
			if(numberofassignment==3)
			{
				new Assignment().create(Integer.parseInt(instructor1dataindex));
				new Assignment().create(Integer.parseInt(instructor2dataindex));
				new Assignment().create(Integer.parseInt(instructor3dataindex));
				new LoginUsingLTI().ltiLogin(studentid);
				new UpdateContentIndex().updatecontentindex(instructor1dataindex);
				new LoginUsingLTI().ltiLogin(instructor1dataindex);
				new Assignment().assignToStudent(Integer.parseInt(instructor1dataindex));
				new LoginUsingLTI().ltiLogin(instructor2dataindex);
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
                     driver.findElement(By.xpath("html/body/div[8]/div/div[2]/div/div[1]/div[3]/div/div/div[4]/div/div[3]/div[1]/div/div[1]/label")).click();
                     driver.findElement(By.cssSelector("span.add-collection-to-subtopic")).click();
                     driver.findElement(By.cssSelector("span.cancel-collection")).click();
					
				 }
				 driver.findElement(By.xpath("//*[@id='instructor-only-check-box-div']/label")).click(); //checking 'Enable for intructor only'
				 new ComboBox().selectValue(4, "Publish");				 
				 driver.findElement(By.id("saveQuestionDetails1")).click();				
				
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
			List<WebElement> assignments =  driver.findElements(By.cssSelector("span[class='ls-assignment-name instructor-assessment-review']")); 
			 for(WebElement element : assignments)
			 {
				 if(element.getText().contains(assessmentname))
						 {
					 		break;
						 }
				 index++;
			 }
			 //click on 'View Responses link'
			 List<WebElement> viewresponseslink = driver.findElements(By.cssSelector("span[class='ls-grade-book-assessment']"));
			   ((JavascriptExecutor) driver).executeScript("arguments[0].click();", viewresponseslink.get(index));
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
			List<WebElement> comments = driver.findElements(By.cssSelector("a[class='ls-stream-post__footer-comment-link js-toggle-comments']"));
			 comments.get(1).click();
			 String random = new RandomString().randomstring(5);
			//Post a comment
			 List<WebElement> commentarea = driver.findElements(By.className("ls-textarea-focus"));
			 commentarea.get(1).sendKeys(random+Keys.ENTER);
			//Searching for comment posted
			 boolean commentfound = false;
			 List<WebElement> postedcomments = driver.findElements(By.className("ls-stream-post__comment-text"));
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
			List<WebElement> assignments =  driver.findElements(By.cssSelector("span[class='ls-assignment-name instructor-assessment-review']")); 
			 for(WebElement element : assignments)
			 {
				 if(element.getText().contains(assessmentname))
						 {
					 		break;
						 }
				 index++;
			 }
			 List<WebElement> viewresponseslink = driver.findElements(By.cssSelector("span[class='ls-grade-book-assessment']"));
			   ((JavascriptExecutor) driver).executeScript("arguments[0].click();", viewresponseslink.get(index));
			   List<WebElement> menuitem = driver.findElements(By.cssSelector("span[class='idb-gradebook-content-label-text idb-gradebook-username']"));
			   int gradeLinkIndex = 0;
			   for(WebElement user : menuitem)
			   {
				   if(user.getText().equals(studentname))
				   {
					   Locatable hoverItem = (Locatable) user;
						  Mouse mouse = ((HasInputDevices) driver).getMouse();
						   mouse.mouseMove(hoverItem.getCoordinates());
						   List<WebElement> gradenowlinks = driver.findElements(By.id("idb-grade-now-link"));
						   gradenowlinks.get(gradeLinkIndex).click();
					   Thread.sleep(3000);
					   List<WebElement> allGradeBox = driver.findElements(By.cssSelector("input[class='idb-grade-points']"));
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
					   driver.findElement(By.className("idb-grader-content-header-row")).click();
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

	public void addMultipleQuestions(int dataIndex,String questionType,String assignmentname, int noOfQuestions, boolean enterSolutionText, boolean enterHint, boolean publish, int ...tloid)
	{
		try
		{
		String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));
		String answertext = ReadTestData.readDataByTagName("", "answertext", Integer.toString(dataIndex));
		String chapterName = ReadTestData.readDataByTagName("", "chapterName", Integer.toString(dataIndex));
		String templatename = ReadTestData.readDataByTagName("", "templatename", Integer.toString(dataIndex));
		String variationlevel = ReadTestData.readDataByTagName("", "variationlevel", Integer.toString(dataIndex));
		String course = Config.course;
		new DirectLogin().CMSLogin();
		String title=driver.getTitle();
		if(title.equals("Course Content Details"))	
			{
			driver.findElement(By.cssSelector("img[alt=\""+course+"\"]")).click();
			if(chapterName == null)
			 {
			 driver.findElement(By.cssSelector("div.course-chapter-label.node")).click();
			 }
			 else
			 {
				 List <WebElement> allChapters = driver.findElements(By.xpath("//*[starts-with(@class, 'course-chapter-label')]"));
				 for(WebElement chapters: allChapters)
				 {
					 if(chapters.getText().contains(chapterName))
					 {
						 //chapters.click();
						 ((JavascriptExecutor) driver).executeScript("arguments[0].click();", chapters); 
						 break;
					 }
					 
				 }
				 
			 }
			
			List<WebElement> elements = driver.findElements(By.className("collection-assessment-name"));
			for(WebElement content : elements)
				{
		
				if(content.getText().trim().equals(assignmentname))
				{
					Thread.sleep(3000);
				   // content.click();
					((JavascriptExecutor) driver).executeScript("arguments[0].click();", content); 
				    Thread.sleep(3000);
            		break;
				}
				}
			
			 	
			 	
		for(int i =0; i< noOfQuestions; i++)
		{
			driver.findElement(By.id("questionOptions")).click();
		 	Thread.sleep(2000);
		 	driver.findElement(By.id("addQuestion")).click();
		 	Thread.sleep(2000);
		 	driver.findElement(By.id(questionType)).click();
		 	Thread.sleep(2000);
		
		//if(questionType.equals("qtn-multiple-choice-img")) //2. For multiple choice question.
		//{
			driver.findElement(By.id("question-raw-content")).click();
		 	driver.switchTo().frame("iframe-question-edit").findElement(By.xpath("/html/body")).sendKeys((i+2)+" "+questiontext);
		 	driver.switchTo().defaultContent();
		 	//Adding choice 1
		 	driver.findElement(By.id("choice1")).click();
		 	driver.switchTo().frame("iframe-choice1").findElement(By.xpath("/html/body")).sendKeys(answertext+" 1");			 	
		 	driver.switchTo().defaultContent();
		 	//Adding choice 2
		 	driver.findElement(By.id("choice2")).click();
		 	driver.switchTo().frame("iframe-choice2").findElement(By.xpath("/html/body")).sendKeys(answertext+" 2");			 	
		 	driver.switchTo().defaultContent();
		 	//Adding choice 3
		 	driver.findElement(By.id("choice3")).click();
		 	driver.switchTo().frame("iframe-choice3").findElement(By.xpath("/html/body")).sendKeys(answertext+" 3");			 	
		 	driver.switchTo().defaultContent();
		 	//Adding choice 4
		 	driver.findElement(By.id("choice4")).click();
		 	driver.switchTo().frame("iframe-choice4").findElement(By.xpath("/html/body")).sendKeys(answertext+" 4");			 	
		 	driver.switchTo().defaultContent();
	//	}
		if(questionType.equals("qtn-multiple-choice-img"))
		{
		 	//Setting choice 2 as correct answer
		 	Actions action = new Actions(driver);
	        WebElement we = driver.findElement(By.id("choice2"));
	        action.moveToElement(we).build().perform();			 
		    driver.findElement(By.xpath("//img[@title='Set it as Correct Answer']")).click();
		}
		else if(questionType.equals("qtn-multiple-selection-img"))
		{
			Actions action = new Actions(driver);
	        WebElement we = driver.findElement(By.id("choice2"));
	        action.moveToElement(we).build().perform();			 
		    driver.findElement(By.xpath("//img[@title='Set it as Correct Answer']")).click();
		    
		    we = driver.findElement(By.id("choice3"));
	        action.moveToElement(we).build().perform();			 
		    driver.findElement(By.xpath("//img[@title='Set it as Correct Answer']")).click();
		    
		}
		    //enter solution text
		    if(enterSolutionText == true)
			 {
			 driver.findElement(By.cssSelector("#explanation > #question-raw-content")).click();
			 driver.switchTo().frame("iframe-explanation").findElement(By.xpath("/html/body")).sendKeys("This is solution text.");			 	
			 driver.switchTo().defaultContent();
			 Thread.sleep(3000);	
			 }
		    //enter template
		    if(templatename != null)
			 {
		     driver.findElement(By.cssSelector("div.template-data-wrapper > #template-name")).click();
		     driver.switchTo().activeElement().sendKeys(templatename);			 	
			 driver.switchTo().defaultContent();
			 driver.findElement(By.className("template-data-wrapper")).click();
			 Thread.sleep(3000);	
			 }
		    //select variation level
		    if(variationlevel != null)
		    {
		    	
		    	System.out.println("Variation "+variationlevel);
		    	driver.findElement(By.linkText("Select Variation Level")).click();		    	
				Thread.sleep(4000);
				driver.findElement(By.linkText("Select Variation Level")).click();
			    driver.findElement(By.cssSelector("a[title='"+variationlevel+"']")).click();
		    }
		    
		  //enter Hint
			 if(enterHint == true)
			 {
                 driver.findElement(By.cssSelector("#hint > #question-raw-content")).click();
                 Thread.sleep(3000);
                 driver.switchTo().frame("iframe-hint").findElement(By.xpath("/html/body")).sendKeys("This is hint text.");//enter hint text
                 driver.switchTo().defaultContent();
                 Thread.sleep(3000);
			 }
            //associate TLO
            if(tloid.length > 0)
            {
                driver.findElement(By.id("learing-objectives-span")).click();
                Thread.sleep(2000);
                driver.findElement(By.cssSelector("span[title='Add Learning Objective']")).click();

                int [] tlosids = tloid;
                for(int tlo_id : tlosids)
                {
                    WebElement element =driver.findElement(By.xpath("//label[@id='"+Integer.toString(tlo_id)+"']"));
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
                    Thread.sleep(3000);
                }
                driver.findElement(By.cssSelector("span.add-collection-to-subtopic")).click();
                //driver.findElement(By.cssSelector("span.cancel-collection")).click();
            }
			 
			 
			 if(publish == true)
				    new ComboBox().selectValue(3, "Publish");
				    driver.findElement(By.id("saveQuestionDetails1")).click();
				 	Thread.sleep(3000);	
			}//for loop ends
	 	
		}				
		}//try ends
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromAppHelper();
			Assert.fail("Exception in create in Apphelper addMultipleQuestions in class AssignmentCreate",e);
		}
	}
	
	public void deactivateQuestion(int dataIndex)
	{
		try
		{
			String chapterName = ReadTestData.readDataByTagName("", "chapterName", Integer.toString(dataIndex));
			String assignmentname = ReadTestData.readDataByTagName("", "practiceassessmentname", Integer.toString(dataIndex));
			new DirectLogin().CMSLogin();
			driver.findElement(By.cssSelector("img[alt=\""+Config.course+"\"]")).click();
			Thread.sleep(2000);			
			
			if(chapterName == null)
			 {
			 driver.findElement(By.cssSelector("div.course-chapter-label.node")).click();
			 }
			 else
			 {
				 List <WebElement> allChapters = driver.findElements(By.xpath("//*[starts-with(@class, 'course-chapter-label')]"));
				 for(WebElement chapters: allChapters)
				 {
					 if(chapters.getText().contains(chapterName))
					 {
						 //chapters.click();
						 ((JavascriptExecutor) driver).executeScript("arguments[0].click();", chapters); 
						 break;
					 }
					 
				 }
				 
			 }
			
			List<WebElement> elements = driver.findElements(By.className("collection-assessment-name"));
			for(WebElement content : elements)
			{
		
				if(content.getText().trim().equals(assignmentname))
				{
					Thread.sleep(3000);
					((JavascriptExecutor) driver).executeScript("arguments[0].click();", content); 
				    Thread.sleep(3000);
            		break;
				}
			}
			
			driver.findElement(By.id("questionOptions")).click();
		 	Thread.sleep(2000);
		 	driver.findElement(By.id("questionRevisions")).click();
		 	Thread.sleep(2000);
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromAppHelper();
			Assert.fail("Exception in create in Apphelper deactivateQuestion in class AssignmentCreate",e);
		}
	}

	public void selectParticularQuestionInCMS(String questionType, int dataIndex) {
		try {
			String reservforassignment = ReadTestData.readDataByTagName("", "reservforassignment", Integer.toString(dataIndex));
			String questiontype = ReadTestData.readDataByTagName("", "questiontype", Integer.toString(dataIndex));
			String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
			String questionset = ReadTestData.readDataByTagName("", "questionset", Integer.toString(dataIndex));
			ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
			manageContent.createPractice.click();//click on create practice
			manageContent.createRegularAssessment.click();//click on regular assessment
			new Click().clickBycssselector("a[selectedid='Adaptive Component Diagnostic']");
			new Click().clickbylinkText(questiontype);
			driver.findElement(By.id("assessmentName")).click();
			driver.findElement(By.id("assessmentName")).clear();
			driver.findElement(By.id("assessmentName")).sendKeys(assessmentname);
			driver.findElement(By.id("questionSetName")).clear();
			driver.findElement(By.id("questionSetName")).sendKeys(questionset);
			if (reservforassignment == null) {
				driver.findElement(By.xpath("//*[@id='instructor-only-check-box-div']/label")).click(); //checking 'Enable for intructor only'
			}
			WebElement question = driver.findElement(By.xpath("//div[@title='" + questionType + "']"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", question);
		} catch (Exception e) {
			Assert.fail("Exception in app helper Assignment in method selectParticularQuestionInCMS.", e);
		}
	}
	public void publishChapter(int dataIndex) {
		try {
			List<WebElement> editElementsList =driver.findElements(By.xpath("//img[starts-with(@id,'tree-node-edit-icon-')]"));
			for (int a = 0; a < editElementsList.size(); a++) {
				if (editElementsList.get(a).isDisplayed()) {
					editElementsList.get(a).click();
					Thread.sleep(2000);
					break;
				}
			}
			Thread.sleep(3000);
			new Click().clickByxpath("//div[normalize-space(@class)='cms-course-tree-edit-publish-content-wrapper cms-course-tree-edit-line-wrapper']//label");
			new Click().clickByid("cms-course-tree-edit-save-btn");
			new Click().clickBycssselector("span.cms-notification-message-ignore-changes.cms-notification-message-save-chater-details > span");
		} catch (Exception e) {
			Assert.fail("Exception in create in Apphelper publishChapter in class AssignmentCreate", e);
		}
	}

	public  String  create(int dataIndex,String questionType) throws InterruptedException {
		String questionID=null;
		try {
			ManageContent manageContent=PageFactory.initElements(driver,ManageContent.class);
			String course = Config.course;
			String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
			String questionset = ReadTestData.readDataByTagName("", "questionset", Integer.toString(dataIndex));
			String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));
			String questiontype = ReadTestData.readDataByTagName("", "questiontype", Integer.toString(dataIndex));
			String chapterName = ReadTestData.readDataByTagName("", "chapterName", Integer.toString(dataIndex));
			String difficultylevel = ReadTestData.readDataByTagName("", "difficultylevel", Integer.toString(dataIndex));
			String learningobjective = ReadTestData.readDataByTagName("", "learningobjective", Integer.toString(dataIndex));
			String hint = ReadTestData.readDataByTagName("", "hint", Integer.toString(dataIndex));
			String courselevel = ReadTestData.readDataByTagName("", "courselevel", Integer.toString(dataIndex));
			String practice_type = ReadTestData.readDataByTagName("", "practice_type", Integer.toString(dataIndex));
			String solutionText = ReadTestData.readDataByTagName("", "solutionText", Integer.toString(dataIndex));
			String variationLevel = ReadTestData.readDataByTagName("", "variationLevel", Integer.toString(dataIndex));
			String useWriteBoard = ReadTestData.readDataByTagName("", "useWriteBoard", Integer.toString(dataIndex));
			String score = ReadTestData.readDataByTagName("", "score", Integer.toString(dataIndex));
			String selectLanguagePalette = ReadTestData.readDataByTagName("", "selectLanguagePalette", Integer.toString(dataIndex));
			String algorithmic = ReadTestData.readDataByTagName("", "algorithmic", Integer.toString(dataIndex));

			System.out.println("algorithmic:"+algorithmic );
			System.out.println("assessmentname:"+assessmentname);
			new DirectLogin().CMSLogin();
			String title = driver.getTitle();
			if (title.equals("Course Content Details")) {
				driver.findElement(By.cssSelector("img[alt=\"" + course + "\"]")).click();
				if (chapterName == null) {
					driver.findElement(By.cssSelector("div.course-chapter-label.node")).click();
				} else if (courselevel != null) {
					new Click().clickBycssselector("div[class='course-label node']");
				} else {
					new UIElement().waitAndFindElement(By.xpath("//*[starts-with(@class, 'course-chapter-label')]"));
					if (driver.findElements(By.xpath("//div[contains(@title,'" + chapterName + "')]")).size() > 1) {
						System.out.println("inside if:" + chapterName);
						new TopicOpen().openCMSLastChapter();
					} else {
						System.out.println("inside else:" + chapterName);
						List<WebElement> allChapters = driver.findElements(By.xpath("//*[starts-with(@class, 'course-chapter-label')]"));
						for (WebElement chapters : allChapters) {
							if (chapters.getText().contains(chapterName)) {
								((JavascriptExecutor) driver).executeScript("arguments[0].click();", chapters);
								Thread.sleep(4000);
								break;
							}
						}
					}

				}
				List<WebElement> elements = driver.findElements(By.xpath("//div[@title='" + assessmentname + "']"));
				if(elements.size()==0) {
					driver.findElement(By.cssSelector("div.create-practice")).click();
					if (practice_type == null) {
						driver.findElement(By.className("create-regular-assessment-popup-item")).click();//click on the create regular assessment
						new UIElement().waitAndFindElement(By.cssSelector("a[selectedid='Adaptive Component Diagnostic']"));
						new Click().clickBycssselector("a[selectedid='Adaptive Component Diagnostic']");
						new Click().clickbylinkText(questiontype);
						driver.findElement(By.id("assessmentName")).clear();
						driver.findElement(By.id("assessmentName")).sendKeys(assessmentname);
						driver.findElement(By.id("questionSetName")).clear();
						driver.findElement(By.id("questionSetName")).sendKeys(questionset);
					}
					else {
						driver.findElement(By.className("create-regular-assessment-popup-item")).click();//click on the create file assessment
					}
				}
				else{
					elements.get(elements.size() - 1).click();//click on the assessment name
					Thread.sleep(9000);
					new UIElement().waitAndFindElement(By.id("questionOptions"));
					new Click().clickByid("questionOptions");
					new UIElement().waitTillVisibleElement(By.id("addQuestion"));
					new Click().clickByid("addQuestion");
				}

				driver.findElement(By.id(questionType)).click();

				if (questionType.equals("qtn-type-true-false-img")) //1.For true-false type question
				{
					new Click().clickByid("question-raw-content");//click on Question
					if(selectLanguagePalette!=null){
						if(selectLanguagePalette.equals("spanish")){
							((JavascriptExecutor)driver).executeScript("arguments[0].click();", manageContent.langaugePalette_icon);
							for(WebElement element:manageContent.langauge_input){
								element.click();
							}
							manageContent.langaugePalette_saveButton.click(); //click on the save button
						}
						driver.switchTo().frame("iframe-question-edit").findElement(By.xpath("/html/body")).sendKeys(questiontext);
						driver.switchTo().defaultContent();                    }

					else{
						driver.switchTo().frame("iframe-question-edit").findElement(By.xpath("/html/body")).sendKeys(questiontext);
						driver.switchTo().defaultContent();
					}

					Thread.sleep(1000);
					driver.findElement(By.id("choice1")).click();
					//Setting choice 1 as correct answer
					Actions action = new Actions(driver);
					WebElement we = driver.findElement(By.id("0"));
					action.moveToElement(we).build().perform();
//					Thread.sleep(2000);
					WebElement webElement = driver.findElement(By.xpath("//img[@title='Set it as Correct Answer']"));
					((JavascriptExecutor) driver).executeScript("arguments[0].click();", webElement);
//					action.moveToElement(we).click(we.findElement(By.xpath("//img[@title='Set it as Correct Answer']"))).build().perform();

				}

				if (questionType.equals("qtn-multiple-choice-img")) //2. For multiple choice question.
				{
					driver.findElement(By.id("question-raw-content")).click();
					driver.switchTo().frame("iframe-question-edit").findElement(By.xpath("/html/body")).sendKeys(questiontext);
					driver.switchTo().defaultContent();
					//Adding choice 1
					driver.findElement(By.id("choice1")).click();
					driver.switchTo().frame("iframe-choice1").findElement(By.xpath("/html/body")).sendKeys("Multiple Choice1");
					driver.switchTo().defaultContent();
					//Adding choice 2
					driver.findElement(By.id("choice2")).click();
					driver.switchTo().frame("iframe-choice2").findElement(By.xpath("/html/body")).sendKeys("Multiple Choice2");
					driver.switchTo().defaultContent();
					//Adding choice 3
					driver.findElement(By.id("choice3")).click();
					driver.switchTo().frame("iframe-choice3").findElement(By.xpath("/html/body")).sendKeys("Multiple Choice3");
					driver.switchTo().defaultContent();
					//Adding choice 4
					driver.findElement(By.id("choice4")).click();
					driver.switchTo().frame("iframe-choice4").findElement(By.xpath("/html/body")).sendKeys("Multiple Choice4");
					driver.switchTo().defaultContent();
					//Setting choice 2 as correct answer
					Actions action = new Actions(driver);
					WebElement we1 = driver.findElement(By.id("choice1"));
					action.moveToElement(we1).build().perform();
					WebElement webElement = driver.findElement(By.xpath("//img[@title='Set it as Correct Answer']"));
					((JavascriptExecutor) driver).executeScript("arguments[0].click();", webElement);

					Actions action1 = new Actions(driver);
					WebElement we = driver.findElement(By.id("choice2"));
					action1.moveToElement(we).build().perform();
					WebElement webElement1 = driver.findElement(By.xpath("//img[@title='Set it as Correct Answer']"));
					((JavascriptExecutor) driver).executeScript("arguments[0].click();", webElement1);


					driver.findElement(By.id("shuffle-answer-choices-checkbox")).click();

				}
				if(questionType.equals("qtn-multiple-selection-img"))  //3. For multiple sections
				{
					driver.findElement(By.id("question-raw-content")).click();
					driver.switchTo().frame("iframe-question-edit").findElement(By.xpath("/html/body")).sendKeys(questiontext);
					driver.switchTo().defaultContent();
					//Adding choice 1
					driver.findElement(By.id("choice1")).click();
					driver.switchTo().frame("iframe-choice1").findElement(By.xpath("/html/body")).sendKeys("Multiple Selection1");
					driver.switchTo().defaultContent();
					//Adding choice 2
					driver.findElement(By.id("choice2")).click();
					driver.switchTo().frame("iframe-choice2").findElement(By.xpath("/html/body")).sendKeys("Multiple Selection2");
					driver.switchTo().defaultContent();
					//Adding choice 3
					driver.findElement(By.id("choice3")).click();
					driver.switchTo().frame("iframe-choice3").findElement(By.xpath("/html/body")).sendKeys("Multiple Selection3");
					driver.switchTo().defaultContent();
					//Adding choice 4
					driver.findElement(By.id("choice4")).click();
					driver.switchTo().frame("iframe-choice4").findElement(By.xpath("/html/body")).sendKeys("Multiple Selection4");
					driver.switchTo().defaultContent();
					//Setting choice 2 as correct answer
					Actions action1 = new Actions(driver);
					WebElement we1 = driver.findElement(By.id("choice2"));
					action1.moveToElement(we1).build().perform();
					WebElement webElement = driver.findElement(By.xpath("//img[@title='Set it as Correct Answer']"));
					((JavascriptExecutor) driver).executeScript("arguments[0].click();", webElement);
					driver.findElement(By.id("shuffle-answer-choices-checkbox")).click();

				}
				if(questionType.equals("qtn-text-entry-img")) // 4.For Text Entry type.
				{
					driver.findElement(By.id("question-raw-content")).click();
					new Click().clickByid("addtextbox"); //click on the add textbox
					driver.switchTo().frame("iframe-question-edit").findElement(By.xpath("/html/body")).sendKeys(questiontext);
					driver.switchTo().defaultContent();
					// Adding correct answer choice
					driver.findElement(By.cssSelector("input[id='correct-ans-text']")).click();
					driver.findElement(By.cssSelector("input[id='correct-ans-text']")).clear();
					driver.findElement(By.cssSelector("input[id='correct-ans-text']")).sendKeys("TextEntryQuestion");
					Thread.sleep(3000);

					List<WebElement> elements1=driver.findElements(By.cssSelector("input[id='correct-ans-text']"));
					elements1.get(1).click();
					elements1.get(1).clear();
					elements1.get(1).sendKeys("TextEntryQuestion1");
					driver.findElement(By.id("done-button")).click(); // Accept answer
					Thread.sleep(2000);
				}

				if(questionType.equals("qtn-numeric-maple-img")) //5. Adding Advanced numeric question
				{
					driver.findElement(By.id("question-raw-content")).click();
					driver.findElement(By.id("addmaplenumeric")).click();
					driver.switchTo().frame("iframe-question-edit").findElement(By.xpath("/html/body")).sendKeys(questiontext);
					driver.switchTo().defaultContent();
					// Adding correct answer choice
					driver.findElement(By.cssSelector("input[id='correct-ans-text']")).click();
					driver.findElement(By.cssSelector("input[id='correct-ans-text']")).clear();
					driver.findElement(By.cssSelector("input[id='correct-ans-text']")).sendKeys("advancednumeric");


					for (int i = 1; i <4; i++) {
						try {
							List<WebElement> correctAnswers = driver.findElements(By.cssSelector("input[id='correct-ans-text']"));
							correctAnswers.get(1).click();
							correctAnswers.get(1).clear();
							correctAnswers.get(1).sendKeys("advancednumeric");
							break;
						} catch (Exception e) {
						}
					}



					// Accept answer
					driver.findElement(By.id("done-button")).click();
					Thread.sleep(2000);
					// Allow for using white board.
					driver.findElement(By.id("display-write-board-checkbox")).click();
				}
				if(questionType.equals("qtn-text-entry-numeric-units-img")) //6. Adding text entry numeric with units.
				{
					driver.findElement(By.id("question-raw-content")).click();
					driver.switchTo().frame("iframe-question-edit").findElement(By.xpath("/html/body")).sendKeys(questiontext);
					driver.switchTo().defaultContent();
					// Adding correct answer choice
					driver.findElement(By.cssSelector("input[id='correct-ans-text']")).click();
					driver.findElement(By.cssSelector("input[id='correct-ans-text']")).clear();
					driver.findElement(By.cssSelector("input[id='correct-ans-text']")).sendKeys("10");

					Thread.sleep(3000);
					// Rest two boxes are filled automatically.
					driver.findElement(By.id("add-more-entry")).click();
					Thread.sleep(3000);
					// Selecting particular unit
					List<WebElement> unitvalues = driver.findElements(By.tagName("li"));
					for(WebElement units : unitvalues)
					{
//						System.out.println(units.getText());
						if(units.getText().equals("feet"))
						{
							units.click();
							break;
						}
					}
					driver.findElement(By.id("done-button")).click(); // Accept answer
					Thread.sleep(2000);
					driver.findElement(By.id("display-write-board-checkbox")).click(); // Allow for using white board.
				}

				if(questionType.equals("qtn-math-symbolic-notation-img")) // 7. Adding expressoin evaluater
				{
					driver.findElement(By.id("question-raw-content")).click();
                    new WebDriverWait(driver,90).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("iframe-question-edit"));
					driver.findElement(By.xpath("/html/body")).sendKeys(questiontext);
					driver.switchTo().defaultContent();
					Thread.sleep(3000);
					// Adding Correct answer
					driver.findElement(By.cssSelector("div[class='right-container math-correct-answer-input-container']")).click();
					Thread.sleep(5000);
					driver.findElement(By.cssSelector("button[title='Square root']")).click();
					driver.findElement(By.cssSelector("input[class='wrs_focusElement']")).sendKeys("5");
					driver.findElement(By.id("wiris-container-save-formaulaeditor")).click();

					// Accept answer
					driver.findElement(By.id("done-button")).click();
					Thread.sleep(2000);
					// Allow for using white board.
					driver.findElement(By.id("display-write-board-checkbox")).click();
					Thread.sleep(2000);
				}
				if(questionType.equals("qtn-essay-type")) // 8. Adding Essay type question
				{

					driver.findElement(By.id("question-raw-content")).click();
					driver.switchTo().frame("iframe-question-edit").findElement(By.xpath("/html/body")).sendKeys(questiontext);
					driver.switchTo().defaultContent();
					//Adding height for text entry.
					driver.findElement(By.id("essay-question-height")).click();
					driver.findElement(By.id("essay-question-height")).sendKeys("2");
					// Allow for using white board.
					driver.findElement(By.id("display-write-board-checkbox")).click();
				}
				if(questionType.equals("qtn-text-entry-drop-down-img")) //9. For text entry drop down
				{
					driver.findElement(By.id("question-raw-content")).click();
					driver.switchTo().frame("iframe-question-edit").findElement(By.xpath("/html/body")).sendKeys(questiontext);
					driver.switchTo().defaultContent();
					// Adding Entry 1
					driver.findElement(By.id("entry-0")).click();
					driver.findElement(By.id("unit-name-edit-entry-0")).clear();
					driver.findElement(By.id("unit-name-edit-entry-0")).sendKeys("Answer");
					Thread.sleep(2000);
					// Accepting answer
					WebElement menuitem = driver.findElement(By.id("entry-1"));
					Locatable hoverItem = (Locatable) menuitem;
					Mouse mouse = ((HasInputDevices) driver).getMouse();
					mouse.mouseMove(hoverItem.getCoordinates());
					List<WebElement> selectanswerticks = driver.findElements(By.className("mark-selected"));
					selectanswerticks.get(1).click(); //select second option as correct answer

					// Adding Entry 2
					Actions action = new Actions(driver);
					action.doubleClick(driver.findElement(By.id("entry-1")));
					action.perform();
					driver.findElement(By.id("unit-name-edit-entry-1")).clear();
					driver.findElement(By.id("unit-name-edit-entry-1")).sendKeys("Answer1");
					Thread.sleep(2000);

					// Adding Entry 3
					action = new Actions(driver);
					action.doubleClick(driver.findElement(By.id("entry-2")));
					action.perform();
					Thread.sleep(2000);
					driver.findElement(By.id("unit-name-edit-entry-2")).clear();
					driver.findElement(By.id("unit-name-edit-entry-2")).sendKeys("Answer2");
					Thread.sleep(2000);
					//clicking on add more entry
					driver.findElement(By.id("add-new-entry")).click();
					//Adding entry 4
					action.doubleClick(driver.findElement(By.id("entry-3")));
					driver.findElement(By.id("unit-name-edit-entry-3")).clear();
					driver.findElement(By.id("unit-name-edit-entry-3")).sendKeys("Answer3"+Keys.ENTER);
					// Accepting answer
					driver.findElement(By.id("done-button")).click();
					driver.findElement(By.id("done-button")).click();
				}

				saveQuestion(learningobjective, solutionText, hint, useWriteBoard, difficultylevel, variationLevel, dataIndex, algorithmic);
				ReportUtil.log("Question Creation", questionType+" type question created successfully", "pass");

                questionID=driver.findElement(By.id("question-id-label")).getText().trim();

			}

		} catch (InterruptedException e) {
			Assert.fail("Exception while creating question", e);
		}
		System.out.println("questionID:"+questionID);
		return questionID;

	}


	public  void saveQuestion(String learningobjective, String solutionText, String hint, String useWriteBoard, String difficultylevel, String variationLevel, int dataIndex, String algorithmic) {
		try {

			if (useWriteBoard != null) {
				new Click().clickBycssselector("#content-writeboard > #writeboard");//check the use writeboard check box
			}
			if(difficultylevel != null)
			{
				new ComboBox().selectValue(7, difficultylevel);
			}
			if (learningobjective != null)
				associateTlo(dataIndex, learningobjective);//add TLO

			if(driver.findElements(By.xpath("//*[@id='instructor-only-check-box-div']/label")).size()==1){
				driver.findElement(By.xpath("//*[@id='instructor-only-check-box-div']/label")).click();
			} //checking 'Enable for intructor only'

			if (solutionText != null || solutionText.equals("true")) {
				driver.findElement(By.cssSelector("#explanation > #question-raw-content")).click();
				Thread.sleep(1000);
				driver.switchTo().frame("iframe-explanation").findElement(By.xpath("/html/body")).sendKeys(solutionText);//enter solution text
				driver.switchTo().defaultContent();
			}

			if(hint != null)
			{
				WebElement webEle=driver.findElement(By.cssSelector("#hint > #question-raw-content"));
				WebDriverUtil.clickOnElementUsingJavascript(webEle);
				Thread.sleep(1000);
				driver.switchTo().frame("iframe-hint").findElement(By.xpath("/html/body")).sendKeys(hint);//enter hint text
				driver.switchTo().defaultContent();
//				Thread.sleep(3000);
			}

			System.out.println("algorithmic:"+algorithmic);
			if (algorithmic != null) {
				if (algorithmic.equals("true")) {
					System.out.println("hi m here ");
					WebElement element = driver.findElement(By.id("question-parameters"));
					//driver.findElement(By.xpath("//html/body")).click();
					new ScrollElement().scrollToViewOfElement(element);
					new Click().clickByxpath("//*[@id='question-parameters']/div[1]"); //click on the + icon to expan
					driver.findElement(By.className("ace_text-input")).sendKeys("var " + Keys.chord(Keys.SHIFT, Keys.NUMPAD4) + "a = 5;\n" +
							"var " + Keys.chord(Keys.SHIFT, Keys.NUMPAD4) + "b = rint" + Keys.chord(Keys.SHIFT, Keys.NUMPAD9) + "11,20);\n" +
							"var " + Keys.chord(Keys.SHIFT, Keys.NUMPAD4) + "c = " + Keys.chord(Keys.SHIFT, Keys.NUMPAD4) + "a+" + Keys.chord(Keys.SHIFT, Keys.NUMPAD4) + "b;");

				}
			}
			if (variationLevel != null) {
				new Click().clickByxpath("//div[@class='footer-bloomcode']/div/a");
				new Click().clickByxpath("//div[@class='footer-bloomcode']//ul/li/a[@rel='" + variationLevel + "']");
			}

           /* List<WebElement>elements= driver.findElements(By.xpath("//a[@title='Draft']"));
            for(WebElement ele:elements){
                if(ele.isDisplayed()){
                    ele.click();//click on the draft
                    break;
                }
            }*/

			new Click().clickbylinkText("Draft"); //click on Draft option
			new Click().clickByxpath("//a[@rel='80']");    //click on Publish
			new WebDriverWait(driver, 600).until(ExpectedConditions.presenceOfElementLocated(By.id("saveQuestionDetails1")));
			new Click().clickByid("saveQuestionDetails1");//click on save button
		}

		catch (Exception e)
		{
			Assert.fail("Exception while saving a question",e);
		}
	}
	public  void associateTlo(int dataIndex, String learningobjective)
	{
		try
		{
			driver.findElement(By.id("learing-objectives-span")).click(); //Clicking on Learning objective drop-down from footer
			new WebDriverWait(driver,30).until(ExpectedConditions.presenceOfElementLocated(By.id("link-add-learning-objectives")));
			driver.findElement(By.id("link-add-learning-objectives")).click(); //clicking on 'Add Learning Objective' link
			Thread.sleep(2000);
			if(learningobjective.equals("true"))
				driver.findElement(By.xpath("//div[@class='taxonomyStructureContent']/div[3]/label")).click();
			else {
				WebElement element = driver.findElement(By.xpath("//div[@class='taxonomyStructureContent']/div[" + learningobjective + "]/label"));
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
				driver.findElement(By.xpath("//div[@class='taxonomyStructureContent']/div[" + learningobjective + "]/label")).click();
				Thread.sleep(3000);
			}
			new Click().clickBycssselector("span.add-collection-to-subtopic");//click on Associate
		}
		catch (Exception e)
		{
			Assert.fail("Exception in app helper QuestionCreate in method associateTlo.", e);
		}
	}

	public static void updateQuestionStatus(String qIds,int qStatus )
	{
		try
		{
			new DBConnect().Connect(); //Connect to database
			String updateQuery = "update t_questions set status = "+qStatus+" where id in ("+qIds+");";
			Thread.sleep(3000);
			System.out.println("updateQuery::" + updateQuery);
			DBConnect.st.executeUpdate(updateQuery);
		}
		catch (Exception e)
		{
			Assert.fail("Exception in updateQsStatus Application Helper",e);
		}
	}

}

