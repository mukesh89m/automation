package com.snapwiz.selenium.tests.staf.dummies.apphelper;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.HasInputDevices;
import org.openqa.selenium.interactions.Mouse;
import org.openqa.selenium.internal.Locatable;
import org.testng.Assert;

import com.snapwiz.selenium.tests.staf.dummies.Config;
import com.snapwiz.selenium.tests.staf.dummies.Driver;
import com.snapwiz.selenium.tests.staf.dummies.ReadTestData;
import com.snapwiz.selenium.tests.staf.dummies.uihelper.ComboBox;
import com.snapwiz.selenium.tests.staf.dummies.uihelper.ShareWith;

public class Assignment {

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
			String solution = ReadTestData.readDataByTagName("", "solution", Integer.toString(dataIndex));
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
				if(difficultylevel != null)
				{
					new ComboBox().selectValue(7, difficultylevel);
				}
				if(learningobjective != null)
				{
					Driver.driver.findElement(By.id("learing-objectives-span")).click(); //Clicking on Learning objective drop-down from footer
					Driver.driver.findElement(By.id("link-add-learning-objectives")).click(); //clicking on 'Add Learning Objective' link
					Driver.driver.findElement(By.xpath("html/body/div[8]/div/div[2]/div[1]/div[3]/div[2]/div/div[1]/div[2]/div/div/div[1]/div/div[3]/div[1]/div/div[1]/label")).click();// html/body/div[8]/div/div[2]/div[1]/div[3]/div[2]/div/div[1]/div[2]/div/div/div[1]/div/div[3]/div[1]/div/div[1]/label				
					Driver.driver.findElement(By.cssSelector("span.add-collection-to-subtopic")).click();

				}
				//enter Hint
				if(hint != null)
				{
					Driver.driver.findElement(By.cssSelector("#hint > #question-raw-content")).click();
					Thread.sleep(3000);	
					Driver.driver.switchTo().activeElement().sendKeys(hint);//enter hint text
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
				Driver.driver.findElement(By.xpath("//*[@id='instructor-only-check-box-div']/label")).click(); //checking 'Enable for intructor only'
				new ComboBox().selectValue(4, "Publish");

				Driver.driver.findElement(By.id("saveQuestionDetails1")).click();
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

	//assignment create for LS course only



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
			new DirectLogin().CMSLogin();
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
			String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));
			String optiontext = ReadTestData.readDataByTagName("", "optiontext", Integer.toString(dataIndex));
			String answertext = ReadTestData.readDataByTagName("", "answertext", Integer.toString(dataIndex));
			String numerictext = ReadTestData.readDataByTagName("", "numerictext", Integer.toString(dataIndex));
			String unitoption = ReadTestData.readDataByTagName("", "unitoption", Integer.toString(dataIndex));
			String tolrence = ReadTestData.readDataByTagName("", "tolrence", Integer.toString(dataIndex));
			String maplenumeric = ReadTestData.readDataByTagName("", "maplenumeric", Integer.toString(dataIndex));
			String course = Config.course;
			new DirectLogin().CMSLogin();
			String title=Driver.driver.getTitle();
			if(title.equals("Course Content Details"))	
			{
				Driver.driver.findElement(By.cssSelector("img[alt=\""+course+"\"]")).click();
				Driver.driver.findElement(By.cssSelector("div.course-chapter-label.node")).click();
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
					Driver.driver.switchTo().frame("iframe-question-edit").findElement(By.xpath("/html/body")).sendKeys(questiontext);
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
			new ComboBox().selectValue(3, "Publish");
			Driver.driver.findElement(By.id("saveQuestionDetails1")).click();
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
			String course = Config.course;
			new DirectLogin().CMSLogin();
			String title=Driver.driver.getTitle();
			if(title.equals("Course Content Details"))	
			{
				Driver.driver.findElement(By.cssSelector("img[alt=\""+course+"\"]")).click();
				Driver.driver.findElement(By.cssSelector("div.course-chapter-label.node")).click();
				List<WebElement> elements = Driver.driver.findElements(By.className("collection-assessment-name"));
				for(WebElement content : elements)
				{
					if(content.getText().trim().equals(assignmentname))
					{

						Thread.sleep(3000);
						content.click();
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
							//System.out.println(units.getText());
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
						// Allow for using white board.
						Driver.driver.findElement(By.id("display-write-board-checkbox")).click(); 
					}

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

	public void assignToStudent(int dataIndex, String ...appendCharacter )
	{	
		try
		{
			String shareWithClass = ReadTestData.readDataByTagName("", "shareWithClass", Integer.toString(dataIndex));
			String duedate = ReadTestData.readDataByTagName("", "duedate", Integer.toString(dataIndex));
			String duetime = ReadTestData.readDataByTagName("", "duetime", Integer.toString(dataIndex));
			String accessibleafter = ReadTestData.readDataByTagName("", "accessibleafter", Integer.toString(dataIndex));
			String additionalnote = ReadTestData.readDataByTagName("", "additionalnote", Integer.toString(dataIndex));
			String gradeable = ReadTestData.readDataByTagName("", "gradeable", Integer.toString(dataIndex)); 
			String gradingpolicy = ReadTestData.readDataByTagName("", "gradingpolicy", Integer.toString(dataIndex));
			String assignmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
			String  assignmentpolicy = ReadTestData.readDataByTagName("", "assignmentpolicy", Integer.toString(dataIndex));
			new com.snapwiz.selenium.tests.staf.dummies.apphelper.Navigator().NavigateTo("Assignments");

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
			Thread.sleep(3000);
			List<WebElement> assign = Driver.driver.findElements(By.className("assign-this"));

			for(WebElement assignment: assign)
			{

				if(assignment.getText().trim().equals("Assign This"))
				{

					assignment.click();
					break;
				}

			}
			if(!shareWithClass.toUpperCase().equals("TRUE"))
			{
				String methodName = new Exception().getStackTrace()[1].getMethodName();
				String shareName = methodName+appendCharacter[0];
				Thread.sleep(3000);
				new ShareWith().share(shareName,true);
			}
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



			Driver.driver.findElement(By.className("input-filed")).sendKeys("shor"); //short label
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

	public void updateAssignment(int dataIndex,boolean addShareWith,String ...appendCharacter)
	{
		try
		{
			//	String shareWithClass = ReadTestData.readDataByTagName("", "shareWithClass", Integer.toString(dataIndex));
			String assignmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
			new com.snapwiz.selenium.tests.staf.dummies.apphelper.Navigator().NavigateTo("Assignments");
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
			{	    	
				String methodName = new Exception().getStackTrace()[1].getMethodName();
				String shareName = methodName+appendCharacter[0];
				new ShareWith().share(shareName,false);	           
			}
			Driver.driver.findElement(By.cssSelector("span[class='btn sty-green submit-assign']")).click();

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
			Thread.sleep(2000);
			((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("span[class='ls-stream-assignment-title']")));
			//Driver.driver.findElement(By.cssSelector("span[class='ls-stream-assignment-title']")).click();
			Thread.sleep(3000);
			int helppage = Driver.driver.findElements(By.className("close-help-page")).size(); 
			if(helppage == 1)
				Driver.driver.findElement(By.className("close-help-page")).click();

			int timer = 1;
			while(timer != 0)
			{
				if(Driver.driver.findElements(By.className("qtn-label")).size() > 0)
					((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.className("qtn-label")));
				//Driver.driver.findElement(By.className("qtn-label")).click();
				Thread.sleep(3000);
				int submitbutton= Driver.driver.findElements(By.cssSelector("a[class='btn btn--primary btn--large btn--submit']")).size();
				if(submitbutton>=1)
				{

					if(submitassignment == null || submitassignment.equalsIgnoreCase("true")){
						Driver.driver.findElement(By.linkText("Submit")).click();
						Thread.sleep(3000);//submitting the assignment as student 1 
					}
					else
						break;
				}
				else
				{
					Driver.driver.findElement(By.cssSelector("a[class='btn btn--primary btn--large btn--next']")).click();//click on next button
					Thread.sleep(3000);
				}
				timer=Driver.driver.findElements(By.id("assessmentTimer")).size();
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
		new com.snapwiz.selenium.tests.staf.dummies.apphelper.Navigator().NavigateTo("Assignments");
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
		new com.snapwiz.selenium.tests.staf.dummies.apphelper.Navigator().NavigateTo("Assignments");
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
			new com.snapwiz.selenium.tests.staf.dummies.apphelper.Navigator().NavigateTo("Assignments");
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
			new com.snapwiz.selenium.tests.staf.dummies.apphelper.Navigator().NavigateTo("Assignments");
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
			new com.snapwiz.selenium.tests.staf.dummies.apphelper.Navigator().NavigateTo("Assignments");
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

			List<WebElement> axis = Driver.driver.findElements(By.className("highcharts-container"));

			if(!axis.get(index).getText().contains("Number of Students"))
				Assert.fail("Y-axis of grade graph not shown as 'Number of Students'");

			//List<WebElement> yaxis = Driver.driver.findElements(By.id("idb-stud-score-bar-chart-footer-label"));
			if(!axis.get(index).getText().contains("Score Range"))
				Assert.fail("X-axis of grade graph not shown as 'Score Range'");
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

	public void provideGRadeToStudent(int dataIndex, String ...appendCharacter)
	{
		try
		{
			String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
			String methodName = new Exception().getStackTrace()[1].getMethodName();
			String studentname = methodName+appendCharacter[0];


			//String gradeLinkIndex = ReadTestData.readDataByTagName("", "gradeLinkIndex", Integer.toString(dataIndex));
			new com.snapwiz.selenium.tests.staf.dummies.apphelper.Navigator().NavigateTo("Assignments");

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
					((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.id("idb-grade-now-link")));
					//Driver.driver.findElement(By.id("idb-grade-now-link")).click();
					Thread.sleep(2000);
					Driver.driver.findElement(By.cssSelector("input[class='idb-grade-points']")).clear();
					Thread.sleep(2000);
					Driver.driver.findElement(By.cssSelector("input[class='idb-grade-points']")).sendKeys("0.6");
					Thread.sleep(2000);
					// Driver.driver.findElement(By.cssSelector("input[class='idb-grade-points']")).sendKeys("0.4");
					Driver.driver.findElement(By.id("idb-gradeBook-overview-test-title-wrapper")).click();
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
			new com.snapwiz.selenium.tests.staf.dummies.apphelper.Navigator().NavigateTo("Assignments");
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
			new com.snapwiz.selenium.tests.staf.dummies.apphelper.Navigator().NavigateTo("Assignments");
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
			new com.snapwiz.selenium.tests.staf.dummies.apphelper.Navigator().NavigateTo("Assignments");
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
		new com.snapwiz.selenium.tests.staf.dummies.apphelper.Navigator().NavigateTo("Assignments");
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
			Driver.driver.findElement(By.id("all-resource-search-textarea")).sendKeys("\""+assignmentname+"\"");
			Driver.driver.findElement(By.cssSelector("#all-resource-search-button > span.ins-resource-search-sprite.instructor-resource-search-img")).click();
			Thread.sleep(3000);
			List<WebElement> assign = Driver.driver.findElements(By.className("assign-this"));

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

	public void provideFeedback(int dataIndex,String ...appendCharacter)
	{
		try
		{
			String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
			String methodName = new Exception().getStackTrace()[1].getMethodName();
			String studentname = methodName+appendCharacter[0];
			new com.snapwiz.selenium.tests.staf.dummies.apphelper.Navigator().NavigateTo("Assignments");
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
			new com.snapwiz.selenium.tests.staf.dummies.apphelper.Navigator().NavigateTo("Assignments");
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

					((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();",elements);
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
			new com.snapwiz.selenium.tests.staf.dummies.apphelper.Navigator().NavigateTo("Assignments");
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
			commentarea.get(1).sendKeys(random+Keys.ENTER);
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

	public void provideGradeToStudentForMultipleQuestions(int dataIndex, String ...appendCharacter)
	{
		try
		{
			String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
			String methodName = new Exception().getStackTrace()[1].getMethodName();
			String studentname = methodName+appendCharacter[0];
			String grade = ReadTestData.readDataByTagName("", "grade", Integer.toString(dataIndex));
			new com.snapwiz.selenium.tests.staf.dummies.apphelper.Navigator().NavigateTo("Assignments");
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
					((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", gradenowlinks.get(gradeLinkIndex));
					//gradenowlinks.get(gradeLinkIndex).click();
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
					Driver.driver.findElement(By.className("idb-grader-content-header-row")).click();
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
				new DirectLogin().CMSLogin();
				String title=Driver.driver.getTitle();
				if(title.equals("Course Content Details"))	
				{
					Driver.driver.findElement(By.cssSelector("img[alt=\""+course+"\"]")).click();
					Driver.driver.findElement(By.cssSelector("div.course-chapter-label.node")).click();
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
					Driver.driver.switchTo().activeElement().sendKeys(hint);//enter hint text
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
				Driver.driver.quit();
				Driver.startDriver();

			}
		}
		catch(Exception e)
		{

			Assert.fail("Exception in create in Apphelper addQuestions in class AssignmentCreate",e);
		}
	}
}

