package com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelper;

import java.util.ArrayList;
import java.util.List;

import com.snapwiz.selenium.tests.staf.AppiumLearningspaces.pagefactory.Assignments.CurrentAssignments;
import com.snapwiz.selenium.tests.staf.AppiumLearningspaces.pagefactory.Assignments.NewAssignment;
import com.snapwiz.selenium.tests.staf.AppiumLearningspaces.pagefactory.eTextbook.LessonPage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import com.snapwiz.selenium.tests.staf.AppiumLearningspaces.Driver;
import com.snapwiz.selenium.tests.staf.AppiumLearningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.AppiumLearningspaces.uihelper.Click;
import com.snapwiz.selenium.tests.staf.AppiumLearningspaces.uihelper.ShareWith;

public class AssignLesson
{
	
	/*
	 * @Brajesh
	 * Assign the lesson to  default class section name
	 */

    //assign a lesson from e-Textbook
    public void assignLesson(String dataIndex)
    {
        try
        {
            String context_title = ReadTestData.readDataByTagName("", "context_title", dataIndex);
            String shareWithInitialString = ReadTestData.readDataByTagName("", "shareWithInitialString", dataIndex);
            String shareName = ReadTestData.readDataByTagName("", "shareName", dataIndex);
            String shareWithClass = ReadTestData.readDataByTagName("", "shareWithClass", dataIndex);
            String duedate = ReadTestData.readDataByTagName("", "duedate", dataIndex);
            String duetime = ReadTestData.readDataByTagName("", "duetime", dataIndex);
            String additionalnote = ReadTestData.readDataByTagName("", "additionalnote", dataIndex);
            Driver.driver.findElement(By.cssSelector("div[class='ls-assign-this-sprite assign-this-lesson-bg']")).click();//click on assign this tab
            new ShareWith().share(shareWithInitialString, shareName, shareWithClass,context_title,true);
            Driver.driver.findElement(By.className("input-filed")).sendKeys("shor"); //short label
            Thread.sleep(2000);

            Driver.driver.findElement(By.id("due-time")).click();//click on dur time
            Driver.driver.findElement(By.xpath("//li[text()='12:00 AM']")).click();
            /*List<WebElement> elements = Driver.driver.findElements(By.xpath("//li"));//fetch all time section
            for(WebElement time : elements)
            {
                if(time.getText().equals(duetime))
                {
                    time.click();
                    break;
                }
            }*/
            Driver.driver.findElement(By.id("due-date")).click();//click on due date
            Thread.sleep(2000);
            Driver.driver.findElement(By.cssSelector("a[title='Next']")).click();
            Thread.sleep(2000);
            Driver.driver.findElement(By.className("ui-state-default")).click();//click on specified date
            Thread.sleep(2000);
            Driver.driver.findElement(By.id("additional-notes")).clear();
            Driver.driver.findElement(By.id("additional-notes")).sendKeys(additionalnote);//add additional note
            Driver.driver.findElement(By.cssSelector("span[class='btn sty-green submit-assign']")).click();//click on assign  button
            Thread.sleep(5000);

        }
        catch(Exception e)
        {
            Assert.fail("Exception in apphelper method assignLessonWithDefaultClassSection in class AssignLesson",e);
        }
    }
    //assign all lesson from TOC of e-Textbook
    public void assignAllLesson(String dataIndex)
    {
        try
        {
            String context_title = ReadTestData.readDataByTagName("", "context_title", dataIndex);
            String shareWithInitialString = ReadTestData.readDataByTagName("", "shareWithInitialString", dataIndex);
            String shareName = ReadTestData.readDataByTagName("", "shareName", dataIndex);
            String shareWithClass = ReadTestData.readDataByTagName("", "shareWithClass", dataIndex);
            String duedate = ReadTestData.readDataByTagName("", "duedate", dataIndex);
            String duetime = ReadTestData.readDataByTagName("", "duetime", dataIndex);
            String additionalnote = ReadTestData.readDataByTagName("", "additionalnote", dataIndex);
            Driver.driver.findElement(By.className("assign-all-lessons")).click();//click on assign icon
            new Click().clickbyxpath(".//*[@class='submit-assignment-content']/span");//click on Assign button
            new ShareWith().share(shareWithInitialString, shareName, shareWithClass,context_title,true);
            Driver.driver.findElement(By.className("input-filed")).sendKeys("shor"); //short label
            Thread.sleep(2000);
            Driver.driver.findElement(By.id("due-time")).click();//click on due time
            Driver.driver.findElement(By.xpath("//li[text()='12:00 AM']")).click();
            Driver.driver.findElement(By.id("due-date")).click();//click on due date
            Thread.sleep(2000);
            Driver.driver.findElement(By.cssSelector("a[title='Next']")).click();
            Thread.sleep(2000);
            Driver.driver.findElement(By.className("ui-state-default")).click();//click on specified date
            Thread.sleep(2000);
            Driver.driver.findElement(By.id("additional-notes")).clear();
            Driver.driver.findElement(By.id("additional-notes")).sendKeys(additionalnote);//add additional note
            new Click().clickbyxpath(".//*[@class='submit-assignment-content']/span");//click on Assign button
            Thread.sleep(5000);

        }
        catch(Exception e)
        {
            Assert.fail("Exception in apphelper method assignLessonWithDefaultClassSection in class AssignLesson",e);
        }
    }
    //update Lesson Assignment from Current Assignments page
    public void updateAssignment(String dataIndex)
    {
        try
        {
            CurrentAssignments courseOutline = PageFactory.initElements(Driver.driver, CurrentAssignments.class);
            String context_title = ReadTestData.readDataByTagName("", "context_title", dataIndex);
            String shareWithInitialString = ReadTestData.readDataByTagName("", "shareWithInitialString", dataIndex);
            String shareName = ReadTestData.readDataByTagName("", "shareName", dataIndex);
            String shareWithClass = ReadTestData.readDataByTagName("", "shareWithClass", dataIndex);
            String duedate = ReadTestData.readDataByTagName("", "duedate", dataIndex);
            String duetime = ReadTestData.readDataByTagName("", "duetime", dataIndex);
            String additionalnote = ReadTestData.readDataByTagName("", "additionalnote", dataIndex);
            new com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelper.Navigator().NavigateTo("Current Assignments");  //navigate to Current Assignments
            courseOutline.getUpdateAssignment_button().click(); //click on Update Assignment link
            new ShareWith().share(shareWithInitialString, shareName, shareWithClass,context_title,false);
            Driver.driver.findElement(By.id("due-time")).click();//click on due time
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
            Driver.driver.findElement(By.id("additional-notes")).clear();
            Driver.driver.findElement(By.id("additional-notes")).sendKeys(additionalnote);//add additional note
            Driver.driver.findElement(By.cssSelector("span[class='btn sty-green submit-assign']")).click();//click on assign  button
            Thread.sleep(5000);

        }
        catch(Exception e)
        {
            Assert.fail("Exception in apphelper method assignLessonWithDefaultClassSection in class AssignLesson",e);
        }
    }
    public void assignLessonWithDefaultClassSection(String dataIndex)
    {
        try
        {
            String duedate = ReadTestData.readDataByTagName("", "duedate", dataIndex);
            String duetime = ReadTestData.readDataByTagName("", "duetime", dataIndex);
            String additionalnote = ReadTestData.readDataByTagName("", "additionalnote", dataIndex);
            //Driver.driver.findElement(By.cssSelector("div[class='ls-assign-this-sprite assign-this-lesson-bg']")).click();//click on assign this tab
            //Driver.driver.findElement(By.cssSelector("span[class='btn sty-green submit-assign']")).click();//click on assign  butto
            Driver.driver.findElement(By.className("input-filed")).sendKeys("shor"); //short label
            Thread.sleep(2000);

            Driver.driver.findElement(By.id("due-time")).click();//click on dur time
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
            Driver.driver.findElement(By.id("additional-notes")).clear();
            Driver.driver.findElement(By.id("additional-notes")).sendKeys(additionalnote);//add additional note
            new Click().clickBycssselector("span[class='btn sty-green submit-assign toc-assignment-cart']"); //click on the assign this button
            Thread.sleep(5000);

        }
        catch(Exception e)
        {
            Assert.fail("Exception in apphelper method assignLessonWithDefaultClassSection in class AssignLesson",e);
        }
    }

    public void assigncart(int dataIndex)
    {
        try
        {
            String duedate = ReadTestData.readDataByTagName("", "duedate", Integer.toString(dataIndex));
            String duetime = ReadTestData.readDataByTagName("", "duetime", Integer.toString(dataIndex));
            String additionalnote = ReadTestData.readDataByTagName("", "additionalnote", Integer.toString(dataIndex));
            new Click().clickByclassname("submit-assignment-content");//click on assign on the cart
            Driver.driver.findElement(By.className("input-filed")).sendKeys("shor"); //short label
            Thread.sleep(2000);

            Driver.driver.findElement(By.id("due-time")).click();//click on dur time
            Driver.driver.findElement(By.xpath("//li[text()='12:00 AM']")).click();
			/*List<WebElement> elements = Driver.driver.findElements(By.xpath("//li"));//fetch all time section
			for(WebElement time : elements)
			{
				if(time.getText().equals(duetime))
				{
					time.click();
					break;
				}
			}*/
            Driver.driver.findElement(By.id("due-date")).click();//click on due date
            Thread.sleep(2000);
            Driver.driver.findElement(By.cssSelector("a[title='Next']")).click();
            Thread.sleep(2000);
            Driver.driver.findElement(By.className("ui-state-default")).click();//click on specified date
            Thread.sleep(2000);
			/*Driver.driver.findElement(By.linkText(duedate)).click();//click on specified date
			Thread.sleep(2000);*/
            Driver.driver.findElement(By.id("additional-notes")).clear();
            Driver.driver.findElement(By.id("additional-notes")).sendKeys(additionalnote);//add additional note
            Driver.driver.findElement(By.cssSelector("span[class='btn sty-green submit-assign']")).click();//click on assign  button
            Thread.sleep(5000);

        }
        catch(Exception e)
        {
            Assert.fail("Exceptiion in apphelper method assigncart in class  AssignLesson",e);
        }
    }

    public void assigncartwithclasssection(int dataIndex)
    {
        try
        {
            String context_title = ReadTestData.readDataByTagName("", "context_title", Integer.toString(dataIndex));
            String duedate = ReadTestData.readDataByTagName("", "duedate", Integer.toString(dataIndex));
            String duetime = ReadTestData.readDataByTagName("", "duetime", Integer.toString(dataIndex));
            String  shareWithInitialString = ReadTestData.readDataByTagName("", "shareWithInitialString", Integer.toString(dataIndex));
            String shareName = ReadTestData.readDataByTagName("", "shareName", Integer.toString(dataIndex));
            String shareWithClass = ReadTestData.readDataByTagName("", "shareWithClass", Integer.toString(dataIndex));
            String additionalnote = ReadTestData.readDataByTagName("", "additionalnote", Integer.toString(dataIndex));
            new Click().clickBycssselector("span[class='btn sty-green submit-assign']");//click on assign on the cart
            Thread.sleep(2000);
            Driver.driver.findElement(By.className("input-filed")).sendKeys("shor"); //short label
            Thread.sleep(2000);
            new ShareWith().share(shareWithInitialString, shareName, shareWithClass, context_title, true);
            Thread.sleep(3000);
            Driver.driver.findElement(By.id("due-time")).click();//click on dur time
            Driver.driver.findElement(By.xpath("//li[text()='12:00 AM']")).click();

			/*List<WebElement> elements = Driver.driver.findElements(By.xpath("//li"));//fetch all time section
			for(WebElement time : elements)
			{
				if(time.getText().equals(duetime))
				{
					time.click();
					break;
				}
			}*/
            Driver.driver.findElement(By.id("due-date")).click();//click on due date
            Thread.sleep(2000);
            Driver.driver.findElement(By.cssSelector("a[title='Next']")).click();
            Thread.sleep(2000);
            Driver.driver.findElement(By.className("ui-state-default")).click();//click on specified date
            Thread.sleep(2000);
			/*Driver.driver.findElement(By.linkText(duedate)).click();//click on specified date
			Thread.sleep(2000);*/
            Driver.driver.findElement(By.id("additional-notes")).clear();
            Driver.driver.findElement(By.id("additional-notes")).sendKeys(additionalnote);//add additional note
            Driver.driver.findElement(By.cssSelector("span[class='btn sty-green submit-assign']")).click();//click on assign  button
            Thread.sleep(5000);

        }
        catch(Exception e)
        {
            Assert.fail("Exceptiion in apphelper method assigncart in class  AssignLesson",e);
        }
    }

    public void assigncartwithclasssectionwithoutshorandwithdefaultclass(int dataIndex)
    {
        try
        {
            String context_title = ReadTestData.readDataByTagName("", "context_title", Integer.toString(dataIndex));
            String duedate = ReadTestData.readDataByTagName("", "duedate", Integer.toString(dataIndex));
            String duetime = ReadTestData.readDataByTagName("", "duetime", Integer.toString(dataIndex));
            String  shareWithInitialString = ReadTestData.readDataByTagName("", "shareWithInitialString", Integer.toString(dataIndex));
            String shareName = ReadTestData.readDataByTagName("", "shareName", Integer.toString(dataIndex));
            String shareWithClass = ReadTestData.readDataByTagName("", "shareWithClass", Integer.toString(dataIndex));
            String additionalnote = ReadTestData.readDataByTagName("", "additionalnote", Integer.toString(dataIndex));
            //new Click().clickByclassname("submit-assignment-content");//click on assign on the cart
            //Driver.driver.findElement(By.className("input-filed")).sendKeys("shor"); //short label
            Thread.sleep(2000);
            new ShareWith().share(shareWithInitialString, shareName, shareWithClass,context_title,false);
            Thread.sleep(3000);
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
            Thread.sleep(2000);
            Driver.driver.findElement(By.id("due-date")).click();//click on due date
            Thread.sleep(2000);
            Driver.driver.findElement(By.cssSelector("a[title='Next']")).click();
            Thread.sleep(2000);
            Driver.driver.findElement(By.linkText(duedate)).click();//click on specified date
            Thread.sleep(2000);
            Driver.driver.findElement(By.id("additional-notes")).clear();
            Driver.driver.findElement(By.id("additional-notes")).sendKeys(additionalnote);//add additional note
            Driver.driver.findElement(By.cssSelector("span[class='btn sty-green submit-assign']")).click();//click on assign  button
            Thread.sleep(5000);

        }
        catch(Exception e)
        {
            Assert.fail("Exceptiion in apphelper method assigncart in class  AssignLesson",e);
        }
    }

    public void Assigncustomeassignemnt(int dataIndex)
    {
        try
        {
            String context_title = ReadTestData.readDataByTagName("", "context_title", Integer.toString(dataIndex));
            String duedate = ReadTestData.readDataByTagName("", "duedate", Integer.toString(dataIndex));
            String duetime = ReadTestData.readDataByTagName("", "duetime", Integer.toString(dataIndex));
            String additionalnote = ReadTestData.readDataByTagName("", "additionalnote", Integer.toString(dataIndex));
            String shareWithInitialString = ReadTestData.readDataByTagName("", "shareWithInitialString", Integer.toString(dataIndex));
            String shareName = ReadTestData.readDataByTagName("", "shareName", Integer.toString(dataIndex));
            String shareWithClass = ReadTestData.readDataByTagName("", "shareWithClass", Integer.toString(dataIndex));
            String gradeable = ReadTestData.readDataByTagName("", "gradeable", Integer.toString(dataIndex));
            String assignmentpolicy = ReadTestData.readDataByTagName("", "assignmentpolicy", Integer.toString(dataIndex));
            String assignmentReference = ReadTestData.readDataByTagName("", "assignmentReference", Integer.toString(dataIndex));


            new Click().clickByclassname("assign-this");//click on assign this link
            new ShareWith().share(shareWithInitialString, shareName, shareWithClass,context_title,true);
            Driver.driver.findElement(By.className("input-filed")).sendKeys("shor"); //short label
            Thread.sleep(2000);

            Driver.driver.findElement(By.id("due-time")).click();//click on dur time
            Driver.driver.findElement(By.xpath("//li[text()='12:00 AM']")).click();
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
                }
            }
            if (gradeable.equals("true") && assignmentpolicy != null) {

                //click on  Choose your assignment policy dropdown
                ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.linkText("Select an Assignment Policy")));
                Thread.sleep(2000);
                Driver.driver.findElement(By.linkText(assignmentpolicy)).click();//select a policy
                Thread.sleep(2000);
            }
            if (assignmentReference != null) {

                if (System.getProperty("UCHAR") == null) {
                    assignmentReference = assignmentReference + LoginUsingLTI.appendChar;
                } else {
                    assignmentReference = assignmentReference + System.getProperty("UCHAR");
                }
                //click on  Choose your Choose your Assignment Reference dropdown
                ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.linkText("Choose your Assignment Reference")));
                Thread.sleep(2000);
                Driver.driver.findElement(By.linkText(assignmentReference)).click();//select a policy
                Thread.sleep(2000);
            }

            Driver.driver.findElement(By.id("additional-notes")).clear();
            Driver.driver.findElement(By.id("additional-notes")).sendKeys(additionalnote);//add additional note
            //Driver.driver.findElement(By.cssSelector("span[class='btn sty-green submit-assign']")).click();//click on assign  button
            new Click().clickBycssselector("span[class='btn sty-green submit-assign']");
            Thread.sleep(5000);

        }
        catch(Exception e)
        {
            Assert.fail("Exceptiion in apphelper method Assigncustomeassignemnt in class  AssignLesson",e);
        }
    }
    public void assignResourcesFormrightTab(int dataIndex)
    {
        try
        {

            String duedate = ReadTestData.readDataByTagName("", "duedate", Integer.toString(dataIndex));
            String duetime = ReadTestData.readDataByTagName("", "duetime", Integer.toString(dataIndex));
            String additionalnote = ReadTestData.readDataByTagName("", "additionalnote", Integer.toString(dataIndex));
/*			String  shareWithInitialString = ReadTestData.readDataByTagName("", "shareWithInitialString", Integer.toString(dataIndex));
			String shareName = ReadTestData.readDataByTagName("", "shareName", Integer.toString(dataIndex));
			String shareWithClass = ReadTestData.readDataByTagName("", "shareWithClass", Integer.toString(dataIndex));
			new ShareWith().share(shareWithInitialString, shareName, shareWithClass,true);
*/			Driver.driver.findElement(By.className("input-filed")).sendKeys("shor"); //short label
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
            Driver.driver.findElement(By.id("additional-notes")).clear();
            Driver.driver.findElement(By.id("additional-notes")).sendKeys(additionalnote);//add additional note
            Driver.driver.findElement(By.cssSelector("span[class='btn sty-green submit-assign']")).click();//click on assign  button
            Thread.sleep(5000);

        }
        catch(Exception e)
        {
            Assert.fail("Exceptiion in apphelper method assignResourcesFormrightTab in class  AssignLesson",e);
        }

    }

    public void assignResourceFromMyResources(int dataIndex)
    {
        try
        {
            new com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelper.Navigator().NavigateTo("Resources");
            String resourcesname=ReadTestData.readDataByTagName("", "resourcesname",  Integer.toString(dataIndex));
            String duedate = ReadTestData.readDataByTagName("", "duedate", Integer.toString(dataIndex));
            String duetime = ReadTestData.readDataByTagName("", "duetime", Integer.toString(dataIndex));
            String additionalnote = ReadTestData.readDataByTagName("", "additionalnote", Integer.toString(dataIndex));

            if(System.getProperty("UCHAR") == null) {
                resourcesname = resourcesname + LoginUsingLTI.appendChar;
            }
            else {
                resourcesname = resourcesname + System.getProperty("UCHAR");
            }

            Driver.driver.findElement(By.cssSelector("div[data-id='my-resources']")).click();
            //Adding assignment to search
            Thread.sleep(3000);
            Driver.driver.findElement(By.id("my-resource-search-textarea")).clear();
            Driver.driver.findElement(By.id("my-resource-search-textarea")).sendKeys("\""+resourcesname+"\"");
            Driver.driver.findElement(By.id("my-resource-search-button")).click();
            Thread.sleep(3000);
            Driver.driver.findElement(By.className("assign-this")).click(); //click on assign this button

            //Driver.driver.findElement(By.cssSelector("span[class='assign-this resource-assign-this-image ls-assign-this-sprite']")).click(); //click on assign this button
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
            Driver.driver.findElement(By.id("additional-notes")).clear();
            Driver.driver.findElement(By.id("additional-notes")).sendKeys(additionalnote);//add additional note
            Driver.driver.findElement(By.cssSelector("span[class='btn sty-green submit-assign']")).click();//click on assign  button
            Thread.sleep(5000);
        }
        catch(Exception e)
        {
            Assert.fail("Exception in apphelper method assignResourceFromMyResources in class  AssignLesson",e);
        }

    }

    /*
    Author : Sumit
    select questions for Custom Assignment from New Assignment tab
     */
    public void selectQuestionForCustomAssignment(String dataIndex)
    {
        try
        {
            String noOfQuestions = ReadTestData.readDataByTagName("", "noOfQuestions", dataIndex);
            List<WebElement> allCheckbox =  Driver.driver.findElements(By.className("ls-ins-question-wrapper"));       // list all the question check box
            ArrayList<String> allCheckboxId = new ArrayList<>();
            for(WebElement checkbox: allCheckbox)
            {
                String checkBox = checkbox.getAttribute("questionid");
                allCheckboxId.add(checkBox);
            }
            for(int i = 0; i < Integer.parseInt(noOfQuestions); i++)
            {
                Driver.driver.findElement(By.xpath("//label[@id='"+allCheckboxId.get(i)+"']")).click();
            }
        }
        catch (Exception e)
        {
            Assert.fail("Exception in apphelper method selectQuestionForCustomAssignment in class  AssignLesson",e);
        }
    }

    /*
    Author : Sumit
    assign Custom Assignment from New Assignment tab
     */
    public void assignCustomAssignment(int dataIndex)
    {
        try
        {
            String context_title = ReadTestData.readDataByTagName("", "context_title", Integer.toString(dataIndex));
            String duedate = ReadTestData.readDataByTagName("", "duedate", Integer.toString(dataIndex));
            String duetime = ReadTestData.readDataByTagName("", "duetime", Integer.toString(dataIndex));
            String additionalnote = ReadTestData.readDataByTagName("", "additionalnote", Integer.toString(dataIndex));
            String  shareWithInitialString = ReadTestData.readDataByTagName("", "shareWithInitialString", Integer.toString(dataIndex));
            String shareName = ReadTestData.readDataByTagName("", "shareName", Integer.toString(dataIndex));
            String shareWithClass = ReadTestData.readDataByTagName("", "shareWithClass", Integer.toString(dataIndex));
            String gradeable = ReadTestData.readDataByTagName("", "gradeable", Integer.toString(dataIndex));
            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
            String customassignmentname = ReadTestData.readDataByTagName("", "customassignmentname", Integer.toString(dataIndex));
            String noOfQuestions = ReadTestData.readDataByTagName("", "noOfQuestions", Integer.toString(dataIndex));
            new com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelper.Navigator().NavigateTo("Question Banks");
            Driver.driver.findElement(By.id("all-resource-search-textarea")).clear();
            Driver.driver.findElement(By.id("all-resource-search-textarea")).sendKeys("\""+assessmentname+"\"");
            Driver.driver.findElement(By.cssSelector("#all-resource-search-button > span.ins-resource-search-sprite.instructor-resource-search-img")).click();
            Thread.sleep(3000);
            Driver.driver.findElement(By.cssSelector("span[title='Customize This']")).click();//click on Customize this link
            Thread.sleep(2000);
            //select question
            List<WebElement> allCheckbox =  Driver.driver.findElements(By.className("ls-ins-question-wrapper"));       // list all the question check box
            ArrayList<String> allCheckboxId = new ArrayList<>();
            for(WebElement checkbox: allCheckbox)
            {
                String checkBox = checkbox.getAttribute("questionid");
                allCheckboxId.add(checkBox);
            }
            for(int i = 0; i < Integer.parseInt(noOfQuestions); i++)
            {
                Driver.driver.findElement(By.xpath("//label[@id='"+allCheckboxId.get(i)+"']")).click();
            }
            new Click().clickByid("ls-ins-assignment-name");//click on Name field
            Driver.driver.findElement(By.id("ls-ins-edit-assignment")).sendKeys(customassignmentname);//enter name of assignment
            Driver.driver.findElement(By.cssSelector("body")).click();//click out side
            Thread.sleep(1000);
            Driver.driver.findElement(By.cssSelector("span.assign-now-text")).click();//click on ASSIGN NOW button
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
                }
            }
            Driver.driver.findElement(By.id("additional-notes")).clear();
            Driver.driver.findElement(By.id("additional-notes")).sendKeys(additionalnote);//add additional note
            new Click().clickBycssselector("span[class='btn sty-green submit-assign assign-custom-assignment']");
            Thread.sleep(5000);

        }
        catch(Exception e)
        {
            Assert.fail("Exception in apphelper method assignCustomAssignment in class  AssignLesson",e);
        }
    }

    public void assignImageWidget(String dataIndex)
    {
        try
        {
            LessonPage lessonPage = PageFactory.initElements(Driver.driver, LessonPage.class);
            String context_title = ReadTestData.readDataByTagName("", "context_title", dataIndex);
            String duedate = ReadTestData.readDataByTagName("", "duedate", dataIndex);
            String duetime = ReadTestData.readDataByTagName("", "duetime", dataIndex);
            String shareWithInitialString = ReadTestData.readDataByTagName("", "shareWithInitialString", dataIndex);
            String shareName = ReadTestData.readDataByTagName("", "shareName", dataIndex);
            String shareWithClass = ReadTestData.readDataByTagName("", "shareWithClass", dataIndex);
            String additionalnote = ReadTestData.readDataByTagName("", "additionalnote", dataIndex);
            lessonPage.getAssignForImage().click();     //click on Assign icon for image widget
            lessonPage.getAssignThisLinkForImage().click(); //click on Assign This link for image widget
            Driver.driver.findElement(By.className("input-filed")).sendKeys("shor"); //short label
            new ShareWith().share(shareWithInitialString, shareName, shareWithClass,context_title,true);
            Driver.driver.findElement(By.id("due-time")).click();//click on dur time
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
            Driver.driver.findElement(By.cssSelector("a[title='Next']")).click();
            Driver.driver.findElement(By.linkText(duedate)).click();//click on specified date
            Driver.driver.findElement(By.id("additional-notes")).clear();
            Driver.driver.findElement(By.id("additional-notes")).sendKeys(additionalnote);//add additional note
            Driver.driver.findElement(By.cssSelector("span[class='btn sty-green submit-assign']")).click();//click on assign  button
            Thread.sleep(2000);

        }
        catch(Exception e)
        {
            Assert.fail("Exception in apphelper method assignImageWidget in class  AssignLesson",e);
        }
    }
    public void assignTOCWithDefaultClassSection(int dataIndex)
    {
        try
        {

            String duedate = ReadTestData.readDataByTagName("", "duedate", Integer.toString(dataIndex));
            String additionalnote = ReadTestData.readDataByTagName("", "additionalnote", Integer.toString(dataIndex));
            String gradeable = ReadTestData.readDataByTagName("", "gradeable", Integer.toString(dataIndex));
            String gradingpolicy = ReadTestData.readDataByTagName("", "gradingpolicy", Integer.toString(dataIndex));
            String assignmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
            String assignmentpolicy = ReadTestData.readDataByTagName("", "assignmentpolicy", Integer.toString(dataIndex));
            String numberofquestions = ReadTestData.readDataByTagName("", "numberofquestions", Integer.toString(dataIndex));
            String assignmentReference = ReadTestData.readDataByTagName("", "assignmentReference", Integer.toString(dataIndex));



            WebElement html = Driver.driver.findElement(By.tagName("html"));
            html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
            html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
            html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));

            Thread.sleep(9000);
            Driver.driver.findElement(By.id("additional-notes")).clear();
            Driver.driver.findElement(By.id("additional-notes")).sendKeys(additionalnote);
            Driver.driver.findElement(By.id("due-time")).click();//click on dur time
            Driver.driver.findElement(By.xpath("//li[text()='12:00 AM']")).click();
            Driver.driver.findElement(By.id("due-date")).click();//click on due date
            Thread.sleep(2000);
            Driver.driver.findElement(By.cssSelector("a[title='Next']")).click();
            Thread.sleep(2000);
            Driver.driver.findElement(By.className("ui-state-default")).click();//click on specified date
            Thread.sleep(2000);
            Driver.driver.findElement(By.className("input-filed")).sendKeys("shor"); //short label
            Thread.sleep(2000);
            Thread.sleep(2000);
            if (gradeable.equals("true")) {
                ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.className("ir-ls-assign-dialog-gradable-label-check")));
                Thread.sleep(2000);
            }

            if(numberofquestions!=null)
            {
                Driver.driver.findElement(By.xpath("//input[@maxlength='3']")).clear();
                Driver.driver.findElement(By.xpath("//input[@maxlength='3']")).sendKeys(numberofquestions);
            }
            Thread.sleep(2000);

            if (gradeable.equals("true") && assignmentpolicy != null) {

                //click on  Choose your assignment policy dropdown
                ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.linkText("Select an assignment policy")));
                Thread.sleep(2000);
                Driver.driver.findElement(By.linkText(assignmentpolicy)).click();//select a policy
                Thread.sleep(2000);
            }

            if (assignmentReference != null) {
                if (System.getProperty("UCHAR") == null) {
                    assignmentReference = assignmentReference + LoginUsingLTI.appendChar;
                } else {
                    assignmentReference = assignmentReference + System.getProperty("UCHAR");
                }
                //click on  Choose your Choose your Assignment Reference dropdown
                ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.linkText("Choose your Assignment Reference")));
                Thread.sleep(2000);
                Driver.driver.findElement(By.linkText(assignmentReference)).click();//select a Assignment Reference
                Thread.sleep(2000);
            }
            Thread.sleep(2000);
            ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.xpath("//span[text()='Assign']")));
            Thread.sleep(5000);

            html.sendKeys(Keys.chord(Keys.CONTROL, "0"));
        }
        catch(Exception e)
        {
            Assert.fail("Exception in apphelper method assignTOCWithDefaultClassSection in class AssignLesson",e);
        }
    }

    public void assignCustomAssignmentFromCustomAssignmentPage(int dataIndex)
    {
        try
        {

            String duedate = ReadTestData.readDataByTagName("", "duedate", Integer.toString(dataIndex));
            String additionalnote = ReadTestData.readDataByTagName("", "additionalnote", Integer.toString(dataIndex));
            String gradeable = ReadTestData.readDataByTagName("", "gradeable", Integer.toString(dataIndex));
            String gradingpolicy = ReadTestData.readDataByTagName("", "gradingpolicy", Integer.toString(dataIndex));
            String assignmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
            String assignmentpolicy = ReadTestData.readDataByTagName("", "assignmentpolicy", Integer.toString(dataIndex));
            String numberofquestions = ReadTestData.readDataByTagName("", "numberofquestions", Integer.toString(dataIndex));
            NewAssignment newAssignment = PageFactory.initElements(Driver.driver, NewAssignment.class);
            String shareWithInitialString = ReadTestData.readDataByTagName("", "shareWithInitialString", Integer.toString(dataIndex));
            String shareName = ReadTestData.readDataByTagName("", "shareName", Integer.toString(dataIndex));
            String shareWithClass = ReadTestData.readDataByTagName("", "shareWithClass", Integer.toString(dataIndex));
            String context_title = ReadTestData.readDataByTagName("", "context_title", Integer.toString(dataIndex));

            WebElement html = Driver.driver.findElement(By.tagName("html"));
            html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
            html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
            html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));

            Thread.sleep(9000);
            newAssignment.assignNowButton.click();//click on assign now
            new ShareWith().share(shareWithInitialString, shareName, shareWithClass,context_title,true);
            Driver.driver.findElement(By.id("additional-notes")).clear();
            Driver.driver.findElement(By.id("additional-notes")).sendKeys(additionalnote);
            Driver.driver.findElement(By.id("due-time")).click();//click on dur time
            Driver.driver.findElement(By.xpath("//li[text()='12:00 AM']")).click();
            Driver.driver.findElement(By.id("due-date")).click();//click on due date
            Thread.sleep(2000);
            Driver.driver.findElement(By.cssSelector("a[title='Next']")).click();
            Thread.sleep(2000);
            Driver.driver.findElement(By.className("ui-state-default")).click();//click on specified date
            Thread.sleep(2000);
            Driver.driver.findElement(By.className("input-filed")).sendKeys("shor"); //short label
            Thread.sleep(2000);
            Thread.sleep(2000);
            if (gradeable.equals("true")) {
                ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.className("ir-ls-assign-dialog-gradable-label-check")));
                Thread.sleep(2000);
            }

            if(numberofquestions!=null)
            {
                Driver.driver.findElement(By.xpath("//input[@maxlength='3']")).clear();
                Driver.driver.findElement(By.xpath("//input[@maxlength='3']")).sendKeys(numberofquestions);
            }
            Thread.sleep(2000);

            if (gradeable.equals("true") && assignmentpolicy != null) {

                //click on  Choose your assignment policy dropdown
                ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.linkText("Select an assignment policy")));
                Thread.sleep(2000);
                Driver.driver.findElement(By.linkText(assignmentpolicy)).click();//select a policy
            }

            ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.xpath("//span[text()='Assign']")));
            Thread.sleep(5000);

            html.sendKeys(Keys.chord(Keys.CONTROL, "0"));
        }
        catch(Exception e)
        {
            Assert.fail("Exception in apphelper method assignCustomAssignmentFromCustomAssignmentPage in class AssignLesson",e);
        }
    }
 public void assignTOCToSpecificStudent(int dataIndex)
    {
        try
        {

            String context_title = ReadTestData.readDataByTagName("", "context_title", Integer.toString(dataIndex));
            String duedate = ReadTestData.readDataByTagName("", "duedate", Integer.toString(dataIndex));
            String duetime = ReadTestData.readDataByTagName("", "duetime", Integer.toString(dataIndex));
            String additionalnote = ReadTestData.readDataByTagName("", "additionalnote", Integer.toString(dataIndex));
            String shareWithInitialString = ReadTestData.readDataByTagName("", "shareWithInitialString", Integer.toString(dataIndex));
            String shareName = ReadTestData.readDataByTagName("", "shareName", Integer.toString(dataIndex));
            String shareWithClass = ReadTestData.readDataByTagName("", "shareWithClass", Integer.toString(dataIndex));
            String gradeable = ReadTestData.readDataByTagName("", "gradeable", Integer.toString(dataIndex));
            String assignmentpolicy = ReadTestData.readDataByTagName("", "assignmentpolicy", Integer.toString(dataIndex));

            WebElement html = Driver.driver.findElement(By.tagName("html"));
            html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
            html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
            html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));

            new ShareWith().share(shareWithInitialString, shareName, shareWithClass,context_title,true);
            Driver.driver.findElement(By.className("input-filed")).sendKeys("shor"); //short label
            Thread.sleep(2000);

            Driver.driver.findElement(By.id("due-time")).click();//click on dur time
            Driver.driver.findElement(By.xpath("//li[text()='12:00 AM']")).click();
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
                }
            }
            if (gradeable.equals("true") && assignmentpolicy != null) {

                //click on  Choose your assignment policy dropdown
                ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.linkText("Select an assignment policy")));
                Thread.sleep(2000);
                Driver.driver.findElement(By.linkText(assignmentpolicy)).click();//select a policy
                Thread.sleep(2000);
            }

            Driver.driver.findElement(By.id("additional-notes")).clear();
            Driver.driver.findElement(By.id("additional-notes")).sendKeys(additionalnote);//add additional note
            ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.xpath("//span[text()='Assign']")));

            html.sendKeys(Keys.chord(Keys.CONTROL, "0"));
        }
        catch(Exception e)
        {
            Assert.fail("Exception in apphelper method assignTOCToSpecificStudent in class AssignLesson",e);
        }
    }
}
