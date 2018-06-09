package com.snapwiz.selenium.tests.staf.learningspaces.apphelper;

import java.awt.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.snapwiz.selenium.tests.staf.framework.utility.WebDriverUtil;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.CurrentAssignments;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.NewAssignment;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.eTextbook.LessonPage;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.eTextbook.TocSearch;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.*;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import com.snapwiz.selenium.tests.staf.framework.controller.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;

public class AssignLesson extends Driver
{
	
	/*
	 * @Brajesh
	 * Assign the lesson to  default class section name
	 */

    //assign a lesson from e-Textbook
    public void assignLesson(String dataIndex)
    {

        WebDriver driver=Driver.getWebDriver();
        try
        {
            String context_title = ReadTestData.readDataByTagName("", "context_title", dataIndex);
            String shareWithInitialString = ReadTestData.readDataByTagName("", "shareWithInitialString", dataIndex);
            String shareName = ReadTestData.readDataByTagName("", "shareName", dataIndex);
            String shareWithClass = ReadTestData.readDataByTagName("", "shareWithClass", dataIndex);
            String duedate = ReadTestData.readDataByTagName("", "duedate", dataIndex);
            String duetime = ReadTestData.readDataByTagName("", "duetime", dataIndex);
            String additionalnote = ReadTestData.readDataByTagName("", "additionalnote", dataIndex);
            driver.findElement(By.cssSelector("div[class='ls-assign-this-sprite assign-this-lesson-bg']")).click();//click on assign this tab
            new ShareWith().share(shareWithInitialString, shareName, shareWithClass,context_title,true);
            Thread.sleep(2000);

            driver.findElement(By.id("due-time")).click();//click on dur time
            driver.findElement(By.xpath("//li[text()='12:00 AM']")).click();
            /*List<WebElement> elements = driver.findElements(By.xpath("//li"));//fetch all time section
            for(WebElement time : elements)
            {
                if(time.getText().equals(duetime))
                {
                    time.click();
                    break;
                }
            }*/
            driver.findElement(By.id("due-date")).click();//click on due date
            Thread.sleep(2000);
            driver.findElement(By.cssSelector("a[title='Next']")).click();
            Thread.sleep(2000);
            driver.findElement(By.className("ui-state-default")).click();//click on specified date
            Thread.sleep(2000);
            driver.findElement(By.id("additional-notes")).clear();
            driver.findElement(By.id("additional-notes")).sendKeys(additionalnote);//add additional note
            driver.findElement(By.cssSelector("span[class='btn sty-green submit-assign']")).click();//click on assign  button
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
        WebDriver driver=Driver.getWebDriver();try
    {
        String context_title = ReadTestData.readDataByTagName("", "context_title", dataIndex);
        String shareWithInitialString = ReadTestData.readDataByTagName("", "shareWithInitialString", dataIndex);
        String shareName = ReadTestData.readDataByTagName("", "shareName", dataIndex);
        String shareWithClass = ReadTestData.readDataByTagName("", "shareWithClass", dataIndex);
        String duedate = ReadTestData.readDataByTagName("", "duedate", dataIndex);
        String duetime = ReadTestData.readDataByTagName("", "duetime", dataIndex);
        String additionalnote = ReadTestData.readDataByTagName("", "additionalnote", dataIndex);

        ((JavascriptExecutor)driver).executeScript("arguments[0].click();", driver.findElement(By.className("assign-all-lessons")));
        new Click().clickbyxpath(".//*[@class='submit-assignment-content']/span");//click on Assign button
        Thread.sleep(3000);
        new ShareWith().share(shareWithInitialString, shareName, shareWithClass,context_title,true);
        Thread.sleep(2000);
        driver.findElement(By.id("due-time")).click();//click on due time
        driver.findElement(By.xpath("//li[text()='12:00 AM']")).click();
        driver.findElement(By.id("due-date")).click();//click on due date
        Thread.sleep(2000);
        driver.findElement(By.cssSelector("a[title='Next']")).click();
        Thread.sleep(2000);
        driver.findElement(By.className("ui-state-default")).click();//click on specified date
        Thread.sleep(2000);
        driver.findElement(By.id("additional-notes")).clear();
        driver.findElement(By.id("additional-notes")).sendKeys(additionalnote);//add additional note
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
        WebDriver driver=Driver.getWebDriver();try
    {
        CurrentAssignments courseOutline = PageFactory.initElements(driver, CurrentAssignments.class);
        String context_title = ReadTestData.readDataByTagName("", "context_title", dataIndex);
        String shareWithInitialString = ReadTestData.readDataByTagName("", "shareWithInitialString", dataIndex);
        String shareName = ReadTestData.readDataByTagName("", "shareName", dataIndex);
        String shareWithClass = ReadTestData.readDataByTagName("", "shareWithClass", dataIndex);
        String duedate = ReadTestData.readDataByTagName("", "duedate", dataIndex);
        String duetime = ReadTestData.readDataByTagName("", "duetime", dataIndex);
        String additionalnote = ReadTestData.readDataByTagName("", "additionalnote", dataIndex);
        new Navigator().NavigateTo("Current Assignments");  //navigate to Current Assignments
        courseOutline.getUpdateAssignment_button().click(); //click on Update Assignment link
        new ShareWith().share(shareWithInitialString, shareName, shareWithClass, context_title, false);
        driver.findElement(By.id("due-time")).click();//click on due time
        List<WebElement> elements = driver.findElements(By.xpath("//li"));//fetch all time section
        for(WebElement time : elements)
        {
            if(time.getText().equals(duetime))
            {
                time.click();
                break;
            }
        }
        driver.findElement(By.id("due-date")).click();//click on due date
        Thread.sleep(2000);
        driver.findElement(By.cssSelector("a[title='Next']")).click();
        Thread.sleep(2000);
        driver.findElement(By.linkText(duedate)).click();//click on specified date
        Thread.sleep(2000);
        driver.findElement(By.id("additional-notes")).clear();
        driver.findElement(By.id("additional-notes")).sendKeys(additionalnote);//add additional note
        driver.findElement(By.cssSelector("span[class='btn sty-green submit-assign tab-view']")).click();//click on assign  button
        driver.findElement(By.cssSelector("span[class='confirm-submit-yes submit-assign']")).click();//click on yes
        Thread.sleep(5000);

    }
    catch(Exception e)
    {
        Assert.fail("Exception in apphelper method assignLessonWithDefaultClassSection in class AssignLesson",e);
    }
    }
    public void assignLessonWithDefaultClassSection(String dataIndex)
    {
        WebDriver driver=Driver.getWebDriver();try
    {
        String duedate = ReadTestData.readDataByTagName("", "duedate", dataIndex);
        String duetime = ReadTestData.readDataByTagName("", "duetime", dataIndex);
        String additionalnote = ReadTestData.readDataByTagName("", "additionalnote", dataIndex);
        //driver.findElement(By.cssSelector("div[class='ls-assign-this-sprite assign-this-lesson-bg']")).click();//click on assign this tab
        //driver.findElement(By.cssSelector("span[class='btn sty-green submit-assign']")).click();//click on assign  butto
        Thread.sleep(2000);

        WebDriverUtil.clickOnElementUsingJavascript(driver.findElement(By.id("due-time")));//click on dur time
        List<WebElement> elements = driver.findElements(By.xpath("//li"));//fetch all time section
        for(WebElement time : elements)
        {
            if(time.getText().equals(duetime))
            {
                WebDriverUtil.clickOnElementUsingJavascript(time);
                break;
            }
        }
        WebDriverUtil.waitForAjax(driver,60);
        try {
            WebDriverUtil.executeJavascript("$('#due-date').focus()");
        }
        catch (Exception e){
            WebDriverUtil.executeJavascript("$('#due-date').focus()");
        }

        WebDriverUtil.waitForAjax(driver,60);
        Thread.sleep(2000);
        WebDriverUtil.clickOnElementUsingJavascript(driver.findElement(By.cssSelector("a[title='Next']")));
        Thread.sleep(2000);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.xpath("//a[text()='" + duedate + "']")));
        Thread.sleep(2000);
        driver.findElement(By.id("additional-notes")).clear();
        driver.findElement(By.id("additional-notes")).sendKeys(additionalnote);//add additional note
        new Click().clickbyxpath("//span[contains(@class,'btn sty-green submit-assign')]"); //click on the assign this button

        Thread.sleep(5000);

    }
    catch(Exception e)
    {
        Assert.fail("Exception in apphelper method assignLessonWithDefaultClassSection in class AssignLesson",e);
    }
    }

    public void assigncart(int dataIndex)
    {
        WebDriver driver=Driver.getWebDriver();try
    {
        String duedate = ReadTestData.readDataByTagName("", "duedate", Integer.toString(dataIndex));
        String duetime = ReadTestData.readDataByTagName("", "duetime", Integer.toString(dataIndex));
        String additionalnote = ReadTestData.readDataByTagName("", "additionalnote", Integer.toString(dataIndex));
        new Click().clickByclassname("submit-assignment-content");//click on assign on the cart
        Thread.sleep(2000);

        driver.findElement(By.id("due-time")).click();//click on dur time
        driver.findElement(By.xpath("//li[text()='12:00 AM']")).click();
			/*List<WebElement> elements = driver.findElements(By.xpath("//li"));//fetch all time section
			for(WebElement time : elements)
			{
				if(time.getText().equals(duetime))
				{
					time.click();
					break;
				}
			}*/
        driver.findElement(By.id("due-date")).click();//click on due date
        Thread.sleep(2000);
        driver.findElement(By.cssSelector("a[title='Next']")).click();
        Thread.sleep(2000);
        driver.findElement(By.className("ui-state-default")).click();//click on specified date
        Thread.sleep(2000);
			/*driver.findElement(By.linkText(duedate)).click();//click on specified date
			Thread.sleep(2000);*/
        driver.findElement(By.id("additional-notes")).clear();
        driver.findElement(By.id("additional-notes")).sendKeys(additionalnote);//add additional note
        driver.findElement(By.cssSelector("span[class='btn sty-green submit-assign']")).click();//click on assign  button
        Thread.sleep(5000);

    }
    catch(Exception e)
    {
        Assert.fail("Exceptiion in apphelper method assigncart in class  AssignLesson",e);
    }
    }

    public void assigncartwithclasssection(int dataIndex)
    {
        WebDriver driver=Driver.getWebDriver();try
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
        new ShareWith().share(shareWithInitialString, shareName, shareWithClass, context_title, true);
        Thread.sleep(3000);
        driver.findElement(By.id("due-time")).click();//click on dur time
        driver.findElement(By.xpath("//li[text()='12:00 AM']")).click();

			/*List<WebElement> elements = driver.findElements(By.xpath("//li"));//fetch all time section
			for(WebElement time : elements)
			{
				if(time.getText().equals(duetime))
				{
					time.click();
					break;
				}
			}*/
        driver.findElement(By.id("due-date")).click();//click on due date
        Thread.sleep(2000);
        driver.findElement(By.cssSelector("a[title='Next']")).click();
        Thread.sleep(2000);
        driver.findElement(By.className("ui-state-default")).click();//click on specified date
        Thread.sleep(2000);
			/*driver.findElement(By.linkText(duedate)).click();//click on specified date
			Thread.sleep(2000);*/
        driver.findElement(By.id("additional-notes")).clear();
        driver.findElement(By.id("additional-notes")).sendKeys(additionalnote);//add additional note
        driver.findElement(By.cssSelector("span[class='btn sty-green submit-assign']")).click();//click on assign  button
        Thread.sleep(5000);

    }
    catch(Exception e)
    {
        Assert.fail("Exceptiion in apphelper method assigncart in class  AssignLesson",e);
    }
    }

    public void assigncartwithclasssectionwithoutshorandwithdefaultclass(int dataIndex)
    {
        WebDriver driver=Driver.getWebDriver();try
    {
        String context_title = ReadTestData.readDataByTagName("", "context_title", Integer.toString(dataIndex));
        String duedate = ReadTestData.readDataByTagName("", "duedate", Integer.toString(dataIndex));
        String duetime = ReadTestData.readDataByTagName("", "duetime", Integer.toString(dataIndex));
        String  shareWithInitialString = ReadTestData.readDataByTagName("", "shareWithInitialString", Integer.toString(dataIndex));
        String shareName = ReadTestData.readDataByTagName("", "shareName", Integer.toString(dataIndex));
        String shareWithClass = ReadTestData.readDataByTagName("", "shareWithClass", Integer.toString(dataIndex));
        String additionalnote = ReadTestData.readDataByTagName("", "additionalnote", Integer.toString(dataIndex));
        //new Click().clickByclassname("submit-assignment-content");//click on assign on the cart
        Thread.sleep(2000);
        new ShareWith().share(shareWithInitialString, shareName, shareWithClass,context_title,false);
        Thread.sleep(3000);
        driver.findElement(By.id("due-time")).click();//click on dur time
        Thread.sleep(2000);
        List<WebElement> elements = driver.findElements(By.xpath("//li"));//fetch all time section
        for(WebElement time : elements)
        {
            if(time.getText().equals(duetime))
            {
                time.click();
                break;
            }
        }
        Thread.sleep(2000);
        driver.findElement(By.id("due-date")).click();//click on due date
        Thread.sleep(2000);
        driver.findElement(By.cssSelector("a[title='Next']")).click();
        Thread.sleep(2000);
        driver.findElement(By.linkText(duedate)).click();//click on specified date
        Thread.sleep(2000);
        driver.findElement(By.id("additional-notes")).clear();
        driver.findElement(By.id("additional-notes")).sendKeys(additionalnote);//add additional note
        driver.findElement(By.cssSelector("span[class='btn sty-green submit-assign']")).click();//click on assign  button
        Thread.sleep(5000);

    }
    catch(Exception e)
    {
        Assert.fail("Exceptiion in apphelper method assigncart in class  AssignLesson",e);
    }
    }

    public void Assigncustomeassignemnt(int dataIndex)
    {
        WebDriver driver=Driver.getWebDriver();try
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
        String policyName = ReadTestData.readDataByTagName("", "assignmentpolicyname", Integer.toString(dataIndex));
        String policyDescription = ReadTestData.readDataByTagName("", "policyDescription", Integer.toString(dataIndex));
        String scorePerQuestion = ReadTestData.readDataByTagName("", "scorePerQuestion", Integer.toString(dataIndex));
        String ordering = ReadTestData.readDataByTagName("", "ordering", Integer.toString(dataIndex));
        String immediateFeedBack = ReadTestData.readDataByTagName("", "immediateFeedBack", Integer.toString(dataIndex));
        String numberOfAttempt = ReadTestData.readDataByTagName("", "numberOfAttempt", Integer.toString(dataIndex));
        String showAnswerAtAttemptNumber = ReadTestData.readDataByTagName("", "showAnswerAtAttemptNumber", Integer.toString(dataIndex));
        String gradeReleaseOption = ReadTestData.readDataByTagName("", "gradeReleaseOption", Integer.toString(dataIndex));
        String showHintsAtAttemptNumber = ReadTestData.readDataByTagName("", "showHintsAtAttemptNumber", Integer.toString(dataIndex));
        String showReadingContentLinkAtAttemptNumber = ReadTestData.readDataByTagName("", "showReadingContentLinkAtAttemptNumber", Integer.toString(dataIndex));
        String showSolutionAtAttemptNumber = ReadTestData.readDataByTagName("", "showSolutionAtAttemptNumber", Integer.toString(dataIndex));
        String extendDueTime = ReadTestData.readDataByTagName("", "extendDueTime", Integer.toString(dataIndex));


        if(policyName==null){
            policyName = "ByDefault Policy Name";
        }

        if(policyDescription==null){
            policyDescription = "Policy Description text";
        }
        if(scorePerQuestion==null){
            scorePerQuestion = "1";
        }

        if(immediateFeedBack==null){
            immediateFeedBack = "false";
        }


        if(numberOfAttempt==null){
            numberOfAttempt = "1";
        }
        if(showAnswerAtAttemptNumber==null){
            showAnswerAtAttemptNumber = "";
        }
        if(gradeReleaseOption==null){
            gradeReleaseOption = "Release explicitly by the instructor";
        }
        if(showHintsAtAttemptNumber==null){
            showHintsAtAttemptNumber = "";
        }
        if(showReadingContentLinkAtAttemptNumber==null){
            showReadingContentLinkAtAttemptNumber = "";
        }
        if(showSolutionAtAttemptNumber==null){
            showSolutionAtAttemptNumber = "";
        }


        new Click().clickByclassname("assign-this");//click on assign this link
        new ShareWith().share(shareWithInitialString, shareName, shareWithClass,context_title,true);
        Thread.sleep(2000);

        if(extendDueTime!=null){
            if(extendDueTime.equals(duetime)){
                new UIElement().waitAndFindElement(By.id("due-time"));
                DateFormat dateFormat = new SimpleDateFormat("hh:mm a");
                Calendar now = Calendar.getInstance();
                //add minutes to current date using Calendar.add method
                now.add(Calendar.MINUTE, Integer.parseInt(extendDueTime));
                String calenderFormat=dateFormat.format(now.getTime());
                System.out.println("calenderFormat:" + calenderFormat);

                driver.findElement(By.id("due-time")).click();//click on dur time
                driver.findElement(By.id("due-time")).sendKeys(calenderFormat);//click on dur time

                driver.findElement(By.id("due-date")).click();//click on due date
                Thread.sleep(5000);
                List<WebElement> defaultsDate=driver.findElements(By.cssSelector("a[class='ui-state-default ui-state-highlight ui-state-hover']"));
                for (WebElement defaultdate:defaultsDate){
                    if (defaultdate.isDisplayed()){
                        ((JavascriptExecutor) driver).executeScript("arguments[0].click();",defaultdate);
                        break;
                    }
                }

            }
        }

        else {
            driver.findElement(By.id("due-time")).click();//click on dur time
            driver.findElement(By.xpath("//li[text()='12:00 AM']")).click();
            driver.findElement(By.id("due-date")).click();//click on due date
            Thread.sleep(2000);
            driver.findElement(By.cssSelector("a[title='Next']")).click();
            Thread.sleep(2000);
            driver.findElement(By.linkText(duedate)).click();//click on specified date
            Thread.sleep(2000);
        }
            /*if (gradeable != null) {
                    if (gradeable.equals("true")) {
                        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("ir-ls-assign-dialog-gradable-label-check")));
                        Thread.sleep(2000);
                    }
                }
*/
        if (gradeable.equals("false")) {
            new UIElement().waitAndFindElement(By.cssSelector("div[class='ir-ls-assign-dialog-gradable-label-check selected']"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("div[class='ir-ls-assign-dialog-gradable-label-check selected']")));
        }

        if (gradeable.equals("true") && assignmentpolicy != null) {

            //click on  Choose your assignment policy dropdown
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.linkText("Select an Assignment Policy")));
            Thread.sleep(2000);
            driver.findElement(By.linkText(assignmentpolicy)).click();//select a policy
            Thread.sleep(2000);
        }

        if(gradeable.equals("true")) {
            if(policyName.equals("ByDefault Policy Name")) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.xpath("//a[@title='Select an Assignment Policy']")));
                new Click().clickbyxpath("//a[@rel='addNewAssignmentPolicy']");
                new AssignmentPolicy().createAssignmentPolicyWhileAssigning(policyName, policyDescription, scorePerQuestion, ordering, Boolean.parseBoolean(immediateFeedBack), numberOfAttempt, showAnswerAtAttemptNumber, gradeReleaseOption, showHintsAtAttemptNumber, showReadingContentLinkAtAttemptNumber, showSolutionAtAttemptNumber);
            }

        }
        if (assignmentReference != null) {

            if (System.getProperty("UCHAR") == null) {
                assignmentReference = assignmentReference + LoginUsingLTI.appendChar;
            } else {
                assignmentReference = assignmentReference + System.getProperty("UCHAR");
            }
            //click on  Choose your Select your Assignment Reference dropdown
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.linkText("Select your Assignment Reference")));
            Thread.sleep(2000);
            driver.findElement(By.linkText(assignmentReference)).click();//select a policy
            Thread.sleep(2000);
        }

        driver.findElement(By.id("additional-notes")).clear();
        driver.findElement(By.id("additional-notes")).sendKeys(additionalnote);//add additional note
        //driver.findElement(By.cssSelector("span[class='btn sty-green submit-assign']")).click();//click on assign  button
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
        WebDriver driver=Driver.getWebDriver();try
    {

        String duedate = ReadTestData.readDataByTagName("", "duedate", Integer.toString(dataIndex));
        String duetime = ReadTestData.readDataByTagName("", "duetime", Integer.toString(dataIndex));
        String additionalnote = ReadTestData.readDataByTagName("", "additionalnote", Integer.toString(dataIndex));
/*			String  shareWithInitialString = ReadTestData.readDataByTagName("", "shareWithInitialString", Integer.toString(dataIndex));
			String shareName = ReadTestData.readDataByTagName("", "shareName", Integer.toString(dataIndex));
			String shareWithClass = ReadTestData.readDataByTagName("", "shareWithClass", Integer.toString(dataIndex));
			new ShareWith().share(shareWithInitialString, shareName, shareWithClass,true);
*/
        Thread.sleep(2000);
        driver.findElement(By.id("due-time")).click();//click on dur time
        Thread.sleep(2000);
        List<WebElement> elements = driver.findElements(By.xpath("//li"));//fetch all time section
        for(WebElement time : elements)
        {
            if(time.getText().equals(duetime))
            {
                time.click();
                break;
            }
        }
        driver.findElement(By.id("due-date")).click();//click on due date
        Thread.sleep(2000);
        driver.findElement(By.cssSelector("a[title='Next']")).click();
        Thread.sleep(2000);
        driver.findElement(By.linkText(duedate)).click();//click on specified date
        Thread.sleep(2000);
        driver.findElement(By.id("additional-notes")).clear();
        driver.findElement(By.id("additional-notes")).sendKeys(additionalnote);//add additional note
        driver.findElement(By.cssSelector("span[class='btn sty-green submit-assign']")).click();//click on assign  button
        Thread.sleep(5000);

    }
    catch(Exception e)
    {
        Assert.fail("Exceptiion in apphelper method assignResourcesFormrightTab in class  AssignLesson",e);
    }

    }

    public void assignResourceFromMyResources(int dataIndex)
    {
        WebDriver driver=Driver.getWebDriver();try
    {
        new Navigator().NavigateTo("Resources");
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

        driver.findElement(By.cssSelector("div[data-id='my-resources']")).click();
        //Adding assignment to search
        Thread.sleep(3000);
        driver.findElement(By.id("my-resource-search-textarea")).clear();
        driver.findElement(By.id("my-resource-search-textarea")).sendKeys("\""+resourcesname+"\"");
        driver.findElement(By.id("my-resource-search-button")).click();
        Thread.sleep(3000);
        driver.findElement(By.className("assign-this")).click(); //click on assign this button
        Thread.sleep(2000);
        driver.findElement(By.id("due-time")).click();//click on dur time
        Thread.sleep(2000);
        List<WebElement> elements = driver.findElements(By.xpath("//li"));//fetch all time section
        for(WebElement time : elements)
        {
            if(time.getText().equals(duetime))
            {
                time.click();
                break;
            }
        }
        driver.findElement(By.id("due-date")).click();//click on due date
        Thread.sleep(2000);
        driver.findElement(By.cssSelector("a[title='Next']")).click();
        Thread.sleep(2000);
        driver.findElement(By.linkText(duedate)).click();//click on specified date
        Thread.sleep(2000);
        driver.findElement(By.id("additional-notes")).clear();
        driver.findElement(By.id("additional-notes")).sendKeys(additionalnote);//add additional note
        driver.findElement(By.cssSelector("span[class='btn sty-green submit-assign']")).click();//click on assign  button
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
        WebDriver driver=Driver.getWebDriver();try
    {
        String noOfQuestions = ReadTestData.readDataByTagName("", "noOfQuestions", dataIndex);
        List<WebElement> allCheckbox =  driver.findElements(By.className("ls-ins-question-wrapper"));       // list all the question check box
        ArrayList<String> allCheckboxId = new ArrayList<>();
        for(WebElement checkbox: allCheckbox)
        {
            String checkBox = checkbox.getAttribute("questionid");
            allCheckboxId.add(checkBox);
        }
        for(int i = 0; i < Integer.parseInt(noOfQuestions); i++)
        {
            driver.findElement(By.xpath("//label[@id='"+allCheckboxId.get(i)+"']")).click();
        }
    }
    catch (Exception e)
    {
        Assert.fail("Exception in apphelper method selectQuestionForCustomAssignment in class  AssignLesson",e);
    }
    }

    public void selectParticularQuestionForCustomAssignment(String dataIndex,int questionNo)
    {
        WebDriver driver=Driver.getWebDriver();try
    {
        String particularQuestionNo = ReadTestData.readDataByTagName("", "particularQuestionNo", dataIndex);
        List<WebElement> allCheckbox =  driver.findElements(By.className("ls-ins-question-wrapper"));       // list all the question check box
        ArrayList<String> allCheckboxId = new ArrayList<>();
        for(WebElement checkbox: allCheckbox)
        {
            String checkBox = checkbox.getAttribute("questionid");
            allCheckboxId.add(checkBox);
        }
        for(int i = questionNo; i <= Integer.parseInt(particularQuestionNo); i++)
        {
            new ScrollElement().scrollBottomOfPage();
            driver.findElement(By.xpath("//label[@id='"+allCheckboxId.get(i)+"']")).click();
        }
    }
    catch (Exception e)
    {
        Assert.fail("Exception in apphelper method selectParticularQuestionForCustomAssignment in class  AssignLesson",e);
    }
    }

    /*
    Author : Sumit
    assign Custom Assignment from New Assignment tab
     */
    public void assignCustomAssignment(int dataIndex)
    {
        WebDriver driver=Driver.getWebDriver();try
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

        String policyName = ReadTestData.readDataByTagName("", "assignmentpolicyname", Integer.toString(dataIndex));
        String policyDescription = ReadTestData.readDataByTagName("", "policyDescription", Integer.toString(dataIndex));
        String scorePerQuestion = ReadTestData.readDataByTagName("", "scorePerQuestion", Integer.toString(dataIndex));
        String ordering = ReadTestData.readDataByTagName("", "ordering", Integer.toString(dataIndex));
        String immediateFeedBack = ReadTestData.readDataByTagName("", "immediateFeedBack", Integer.toString(dataIndex));
        String numberOfAttempt = ReadTestData.readDataByTagName("", "numberOfAttempt", Integer.toString(dataIndex));
        String showAnswerAtAttemptNumber = ReadTestData.readDataByTagName("", "showAnswerAtAttemptNumber", Integer.toString(dataIndex));
        String gradeReleaseOption = ReadTestData.readDataByTagName("", "gradeReleaseOption", Integer.toString(dataIndex));
        String showHintsAtAttemptNumber = ReadTestData.readDataByTagName("", "showHintsAtAttemptNumber", Integer.toString(dataIndex));
        String showReadingContentLinkAtAttemptNumber = ReadTestData.readDataByTagName("", "showReadingContentLinkAtAttemptNumber", Integer.toString(dataIndex));
        String showSolutionAtAttemptNumber = ReadTestData.readDataByTagName("", "showSolutionAtAttemptNumber", Integer.toString(dataIndex));


        System.out.println("policyName:"+policyName);
        if(policyName==null){
            System.out.println("Inside Policy Name");
            policyName = "ByDefault Policy Name";
        }

        if(policyDescription==null){
            policyDescription = "Policy Description text";
        }
        if(scorePerQuestion==null){
            scorePerQuestion = "1";
        }

        if(immediateFeedBack==null){
            immediateFeedBack = "false";
        }


        if(numberOfAttempt==null){
            numberOfAttempt = "1";
        }
        if(showAnswerAtAttemptNumber==null){
            showAnswerAtAttemptNumber = "";
        }
        if(gradeReleaseOption==null){
            gradeReleaseOption = "Release explicitly by the instructor";
        }
        if(showHintsAtAttemptNumber==null){
            showHintsAtAttemptNumber = "";
        }
        if(showReadingContentLinkAtAttemptNumber==null){
            showReadingContentLinkAtAttemptNumber = "";
        }
        if(showSolutionAtAttemptNumber==null){
            showSolutionAtAttemptNumber = "";
        }
        new Navigator().NavigateTo("Question Banks");
        driver.findElement(By.id("all-resource-search-textarea")).clear();
        driver.findElement(By.id("all-resource-search-textarea")).sendKeys("\""+assessmentname+"\"");
        driver.findElement(By.cssSelector("#all-resource-search-button > span.ins-resource-search-sprite.instructor-resource-search-img")).click();
        Thread.sleep(3000);
        new Click().clickByclassname("customize-this");//click on Customize this link
        // driver.findElement(By.cssSelector("span[title='Customize This']")).click();//click on Customize this link
        Thread.sleep(2000);
        //select question
        List<WebElement> allCheckbox =  driver.findElements(By.className("ls-ins-question-wrapper"));       // list all the question check box
        ArrayList<String> allCheckboxId = new ArrayList<>();
        for(WebElement checkbox: allCheckbox)
        {
            String checkBox = checkbox.getAttribute("questionid");
            allCheckboxId.add(checkBox);
        }
        for(int i = 0; i < Integer.parseInt(noOfQuestions); i++)
        {
            driver.findElement(By.xpath("//label[@id='"+allCheckboxId.get(i)+"']")).click();
        }
        new Click().clickByid("ls-ins-assignment-name");//click on Name field
        driver.findElement(By.id("ls-ins-edit-assignment")).sendKeys(customassignmentname);//enter name of assignment
        driver.findElement(By.cssSelector("body")).click();//click out side
        Thread.sleep(1000);
        driver.findElement(By.cssSelector("span.assign-now-text")).click();//click on ASSIGN NOW button
        new ShareWith().share(shareWithInitialString, shareName, shareWithClass,context_title,true);
        Thread.sleep(2000);

        driver.findElement(By.id("due-time")).click();//click on dur time
        Thread.sleep(2000);
        List<WebElement> elements = driver.findElements(By.xpath("//li"));//fetch all time section
        for(WebElement time : elements)
        {
            if(time.getText().equals(duetime))
            {
                time.click();
                break;
            }
        }
        driver.findElement(By.id("due-date")).click();//click on due date
        Thread.sleep(2000);
        driver.findElement(By.cssSelector("a[title='Next']")).click();
        Thread.sleep(2000);
        driver.findElement(By.linkText(duedate)).click();//click on specified date
        Thread.sleep(2000);
        if(gradeable !=null) {
            if (gradeable.equals("true")) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("ir-ls-assign-dialog-gradable-label-check")));
                Thread.sleep(2000);
            }
        }

        if(gradeable.equals("true")) {
            if(policyName.equals("ByDefault Policy Name")) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.xpath("//a[@title='Select an Assignment Policy']")));
                new Click().clickbyxpath("//a[@rel='addNewAssignmentPolicy']");
                new AssignmentPolicy().createAssignmentPolicyWhileAssigning(policyName, policyDescription, scorePerQuestion, ordering, Boolean.parseBoolean(immediateFeedBack), numberOfAttempt, showAnswerAtAttemptNumber, gradeReleaseOption, showHintsAtAttemptNumber, showReadingContentLinkAtAttemptNumber, showSolutionAtAttemptNumber);
            }

        }
        driver.findElement(By.id("additional-notes")).clear();
        driver.findElement(By.id("additional-notes")).sendKeys(additionalnote);//add additional note
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
        WebDriver driver=Driver.getWebDriver();try
    {
        LessonPage lessonPage = PageFactory.initElements(driver, LessonPage.class);
        String context_title = ReadTestData.readDataByTagName("", "context_title", dataIndex);
        String duedate = ReadTestData.readDataByTagName("", "duedate", dataIndex);
        String duetime = ReadTestData.readDataByTagName("", "duetime", dataIndex);
        String shareWithInitialString = ReadTestData.readDataByTagName("", "shareWithInitialString", dataIndex);
        String shareName = ReadTestData.readDataByTagName("", "shareName", dataIndex);
        String shareWithClass = ReadTestData.readDataByTagName("", "shareWithClass", dataIndex);
        String additionalnote = ReadTestData.readDataByTagName("", "additionalnote", dataIndex);

        WebDriverUtil.clickOnElementUsingJavascript(lessonPage.getAssignForImage());//click on Assign icon for image widget
        WebDriverUtil.clickOnElementUsingJavascript(lessonPage.getAssignThisLinkForImage());//click on Assign This link for image widget

        new ShareWith().share(shareWithInitialString, shareName, shareWithClass,context_title,true);
        WebDriverUtil.clickOnElementUsingJavascript(driver.findElement(By.id("due-time")));//click on dur time
        driver.findElement(By.xpath("//li[text()='12:00 AM']")).click();
        Thread.sleep(5000);
        WebElement elementToClick = driver.findElement(By.id("due-date"));
        elementToClick.click();
        WebDriverUtil.clickOnElementUsingJavascript( driver.findElement(By.cssSelector("a[title='Next']")));
        WebDriverUtil.clickOnElementUsingJavascript( driver.findElement(By.linkText(duedate)));
        driver.findElement(By.id("additional-notes")).clear();
        driver.findElement(By.id("additional-notes")).sendKeys(additionalnote);//add additional note
        driver.findElement(By.cssSelector("span[class='btn sty-green submit-assign']")).click();//click on assign  button
        Thread.sleep(2000);

    }
    catch(Exception e)
    {
        Assert.fail("Exception in apphelper method assignImageWidget in class  AssignLesson",e);
    }
    }
    public void assignTOCWithDefaultClassSection(int dataIndex)
    {
        WebDriver driver=Driver.getWebDriver();try
    {

        String duedate = ReadTestData.readDataByTagName("", "duedate", Integer.toString(dataIndex));
        String additionalnote = ReadTestData.readDataByTagName("", "additionalnote", Integer.toString(dataIndex));
        String gradeable = ReadTestData.readDataByTagName("", "gradeable", Integer.toString(dataIndex));
        String gradingpolicy = ReadTestData.readDataByTagName("", "gradingpolicy", Integer.toString(dataIndex));
        String assignmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
        String assignmentpolicy = ReadTestData.readDataByTagName("", "assignmentpolicy", Integer.toString(dataIndex));
        String numberofquestions = ReadTestData.readDataByTagName("", "numberofquestions", Integer.toString(dataIndex));
        String assignmentReference = ReadTestData.readDataByTagName("", "assignmentReference", Integer.toString(dataIndex));
        String policyName = ReadTestData.readDataByTagName("", "assignmentpolicyname", Integer.toString(dataIndex));
        String policyDescription = ReadTestData.readDataByTagName("", "policyDescription", Integer.toString(dataIndex));
        String scorePerQuestion = ReadTestData.readDataByTagName("", "scorePerQuestion", Integer.toString(dataIndex));
        String ordering = ReadTestData.readDataByTagName("", "ordering", Integer.toString(dataIndex));
        String immediateFeedBack = ReadTestData.readDataByTagName("", "immediateFeedBack", Integer.toString(dataIndex));
        String numberOfAttempt = ReadTestData.readDataByTagName("", "numberOfAttempt", Integer.toString(dataIndex));
        String showAnswerAtAttemptNumber = ReadTestData.readDataByTagName("", "showAnswerAtAttemptNumber", Integer.toString(dataIndex));
        String gradeReleaseOption = ReadTestData.readDataByTagName("", "gradeReleaseOption", Integer.toString(dataIndex));
        String showHintsAtAttemptNumber = ReadTestData.readDataByTagName("", "showHintsAtAttemptNumber", Integer.toString(dataIndex));
        String showReadingContentLinkAtAttemptNumber = ReadTestData.readDataByTagName("", "showReadingContentLinkAtAttemptNumber", Integer.toString(dataIndex));
        String showSolutionAtAttemptNumber = ReadTestData.readDataByTagName("", "showSolutionAtAttemptNumber", Integer.toString(dataIndex));
        String accessibleafter = ReadTestData.readDataByTagName("", "accessibleafter", Integer.toString(dataIndex));


        System.out.println("policyName:"+policyName);
        if(policyName==null){
            System.out.println("Inside Policy Name");
            policyName = "ByDefault Policy Name";
        }

        if(policyDescription==null){
            policyDescription = "Policy Description text";
        }
        if(scorePerQuestion==null){
            scorePerQuestion = "1";
        }

        if(immediateFeedBack==null){
            immediateFeedBack = "false";
        }


        if(numberOfAttempt==null){
            numberOfAttempt = "1";
        }
        if(showAnswerAtAttemptNumber==null){
            showAnswerAtAttemptNumber = "";
        }
        if(gradeReleaseOption==null){
            gradeReleaseOption = "Release explicitly by the instructor";
        }
        if(showHintsAtAttemptNumber==null){
            showHintsAtAttemptNumber = "";
        }
        if(showReadingContentLinkAtAttemptNumber==null){
            showReadingContentLinkAtAttemptNumber = "";
        }
        if(showSolutionAtAttemptNumber==null){
            showSolutionAtAttemptNumber = "";
        }


        WebElement html = driver.findElement(By.tagName("html"));
           /* html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
            html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
            html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
*/
        Thread.sleep(9000);
        driver.findElement(By.id("additional-notes")).clear();
        driver.findElement(By.id("additional-notes")).sendKeys(additionalnote);
        driver.findElement(By.id("due-time")).click();//click on dur time
        driver.findElement(By.xpath("//li[text()='12:00 AM']")).click();
        driver.findElement(By.id("due-date")).click();//click on due date
        Thread.sleep(2000);
        // driver.findElement(By.cssSelector("a[title='Next']")).click();
        driver.findElement(By.cssSelector("a[class='ui-datepicker-next ui-corner-all']")).click();

        Thread.sleep(2000);
        driver.findElement(By.className("ui-state-default")).click();//click on specified date
        Thread.sleep(2000);
        if (gradeable.equals("true")) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("ir-ls-assign-dialog-gradable-label-check")));
            Thread.sleep(2000);
        }

        if (accessibleafter != null) {
            driver.findElement(By.id("accessible-date")).click();
            driver.findElement(By.linkText(accessibleafter)).click();
        }

        if(numberofquestions!=null)
        {
            driver.findElement(By.xpath("//input[@maxlength='3']")).clear();
            driver.findElement(By.xpath("//input[@maxlength='3']")).sendKeys(numberofquestions);
        }
        Thread.sleep(2000);

        if (gradeable.equals("true") && assignmentpolicy != null) {

            //click on  Choose your assignment policy dropdown
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.xpath("//a[@title='Select an Assignment Policy']")));
            Thread.sleep(2000);
            driver.findElement(By.linkText(assignmentpolicy)).click();//select a policy
            Thread.sleep(2000);
        }

           /* if(gradeable.equals("true")) {
                if(policyName.equals("ByDefault Policy Name")) {
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.xpath("//a[@title='Select an Assignment Policy']")));
                    new Click().clickbyxpath("//a[@rel='addNewAssignmentPolicy']");
                    new AssignmentPolicy().createAssignmentPolicyWhileAssigning(policyName, policyDescription, scorePerQuestion, ordering, Boolean.parseBoolean(immediateFeedBack), numberOfAttempt, showAnswerAtAttemptNumber, gradeReleaseOption, showHintsAtAttemptNumber, showReadingContentLinkAtAttemptNumber, showSolutionAtAttemptNumber);
                }

            }*/

        if (assignmentReference != null) {
            if (System.getProperty("UCHAR") == null) {
                assignmentReference = assignmentReference + LoginUsingLTI.appendChar;
            } else {
                assignmentReference = assignmentReference + System.getProperty("UCHAR");
            }
            //click on  Choose your Select your Assignment Reference dropdown
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.linkText("Select your Assignment Reference")));
            Thread.sleep(2000);
            driver.findElement(By.linkText(assignmentReference)).click();//select a Assignment Reference
            Thread.sleep(2000);
        }
        Thread.sleep(2000);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.xpath("//span[text()='Assign']")));
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
        WebDriver driver=Driver.getWebDriver();try
    {

        String duedate = ReadTestData.readDataByTagName("", "duedate", Integer.toString(dataIndex));
        String additionalnote = ReadTestData.readDataByTagName("", "additionalnote", Integer.toString(dataIndex));
        String gradeable = ReadTestData.readDataByTagName("", "gradeable", Integer.toString(dataIndex));
        String gradingpolicy = ReadTestData.readDataByTagName("", "gradingpolicy", Integer.toString(dataIndex));
        String assignmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
        String assignmentpolicy = ReadTestData.readDataByTagName("", "assignmentpolicy", Integer.toString(dataIndex));
        String numberofquestions = ReadTestData.readDataByTagName("", "numberofquestions", Integer.toString(dataIndex));
        NewAssignment newAssignment = PageFactory.initElements(driver, NewAssignment.class);
        String shareWithInitialString = ReadTestData.readDataByTagName("", "shareWithInitialString", Integer.toString(dataIndex));
        String shareName = ReadTestData.readDataByTagName("", "shareName", Integer.toString(dataIndex));
        String shareWithClass = ReadTestData.readDataByTagName("", "shareWithClass", Integer.toString(dataIndex));
        String context_title = ReadTestData.readDataByTagName("", "context_title", Integer.toString(dataIndex));
        String policyName = ReadTestData.readDataByTagName("", "assignmentpolicyname", Integer.toString(dataIndex));
        String policyDescription = ReadTestData.readDataByTagName("", "policyDescription", Integer.toString(dataIndex));
        String scorePerQuestion = ReadTestData.readDataByTagName("", "scorePerQuestion", Integer.toString(dataIndex));
        String ordering = ReadTestData.readDataByTagName("", "ordering", Integer.toString(dataIndex));
        String immediateFeedBack = ReadTestData.readDataByTagName("", "immediateFeedBack", Integer.toString(dataIndex));
        String numberOfAttempt = ReadTestData.readDataByTagName("", "numberOfAttempt", Integer.toString(dataIndex));
        String showAnswerAtAttemptNumber = ReadTestData.readDataByTagName("", "showAnswerAtAttemptNumber", Integer.toString(dataIndex));
        String gradeReleaseOption = ReadTestData.readDataByTagName("", "gradeReleaseOption", Integer.toString(dataIndex));
        String showHintsAtAttemptNumber = ReadTestData.readDataByTagName("", "showHintsAtAttemptNumber", Integer.toString(dataIndex));
        String showReadingContentLinkAtAttemptNumber = ReadTestData.readDataByTagName("", "showReadingContentLinkAtAttemptNumber", Integer.toString(dataIndex));
        String showSolutionAtAttemptNumber = ReadTestData.readDataByTagName("", "showSolutionAtAttemptNumber", Integer.toString(dataIndex));


        System.out.println("policyName:"+policyName);
        if(policyName==null){
            System.out.println("Inside Policy Name");
            policyName = "ByDefault Policy Name";
        }

        if(policyDescription==null){
            policyDescription = "Policy Description text";
        }
        if(scorePerQuestion==null){
            scorePerQuestion = "1";
        }

        if(immediateFeedBack==null){
            immediateFeedBack = "false";
        }


        if(numberOfAttempt==null){
            numberOfAttempt = "1";
        }
        if(showAnswerAtAttemptNumber==null){
            showAnswerAtAttemptNumber = "";
        }
        if(gradeReleaseOption==null){
            gradeReleaseOption = "Release explicitly by the instructor";
        }
        if(showHintsAtAttemptNumber==null){
            showHintsAtAttemptNumber = "";
        }
        if(showReadingContentLinkAtAttemptNumber==null){
            showReadingContentLinkAtAttemptNumber = "";
        }
        if(showSolutionAtAttemptNumber==null){
            showSolutionAtAttemptNumber = "";
        }
        WebElement html = driver.findElement(By.tagName("html"));
        html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
        html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
        html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));

        Thread.sleep(9000);
        newAssignment.assignNowButton.click();//click on assign now
        new ShareWith().share(shareWithInitialString, shareName, shareWithClass,context_title,true);
        driver.findElement(By.id("additional-notes")).clear();
        driver.findElement(By.id("additional-notes")).sendKeys(additionalnote);
        driver.findElement(By.id("due-time")).click();//click on dur time
        driver.findElement(By.xpath("//li[text()='12:00 AM']")).click();
        driver.findElement(By.id("due-date")).click();//click on due date
        Thread.sleep(2000);
        driver.findElement(By.cssSelector("a[title='Next']")).click();
        Thread.sleep(2000);
        driver.findElement(By.className("ui-state-default")).click();//click on specified date
        Thread.sleep(2000);
        if (gradeable.equals("true")) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("ir-ls-assign-dialog-gradable-label-check")));
            Thread.sleep(2000);
        }

        if(numberofquestions!=null)
        {
            driver.findElement(By.xpath("//input[@maxlength='3']")).clear();
            driver.findElement(By.xpath("//input[@maxlength='3']")).sendKeys(numberofquestions);
        }
        Thread.sleep(2000);

        if (gradeable.equals("true") && assignmentpolicy != null) {

            //click on  Choose your assignment policy dropdown
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.linkText("Select an assignment policy")));
            Thread.sleep(2000);
            driver.findElement(By.linkText(assignmentpolicy)).click();//select a policy
        }

        if(gradeable.equals("true")) {
            if(policyName.equals("ByDefault Policy Name")) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.xpath("//a[@title='Select an Assignment Policy']")));
                new Click().clickbyxpath("//a[@rel='addNewAssignmentPolicy']");
                new AssignmentPolicy().createAssignmentPolicyWhileAssigning(policyName, policyDescription, scorePerQuestion, ordering, Boolean.parseBoolean(immediateFeedBack), numberOfAttempt, showAnswerAtAttemptNumber, gradeReleaseOption, showHintsAtAttemptNumber, showReadingContentLinkAtAttemptNumber, showSolutionAtAttemptNumber);
            }

        }

        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.xpath("//span[text()='Assign']")));
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
        WebDriver driver=Driver.getWebDriver();try
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

        String accessibleafter = ReadTestData.readDataByTagName("", "accessibleafter", Integer.toString(dataIndex));
        String gradingpolicy = ReadTestData.readDataByTagName("", "gradingpolicy", Integer.toString(dataIndex));
        String assignmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
        String gradeBookWeighting = ReadTestData.readDataByTagName("", "gradeBookWeighting", Integer.toString(dataIndex));
        String policyName = ReadTestData.readDataByTagName("", "assignmentpolicyname", Integer.toString(dataIndex));
        String policyDescription = ReadTestData.readDataByTagName("", "policyDescription", Integer.toString(dataIndex));
        String scorePerQuestion = ReadTestData.readDataByTagName("", "scorePerQuestion", Integer.toString(dataIndex));
        String ordering = ReadTestData.readDataByTagName("", "ordering", Integer.toString(dataIndex));
        String immediateFeedBack = ReadTestData.readDataByTagName("", "immediateFeedBack", Integer.toString(dataIndex));
        String numberOfAttempt = ReadTestData.readDataByTagName("", "numberOfAttempt", Integer.toString(dataIndex));
        String showAnswerAtAttemptNumber = ReadTestData.readDataByTagName("", "showAnswerAtAttemptNumber", Integer.toString(dataIndex));
        String gradeReleaseOption = ReadTestData.readDataByTagName("", "gradeReleaseOption", Integer.toString(dataIndex));
        String showHintsAtAttemptNumber = ReadTestData.readDataByTagName("", "showHintsAtAttemptNumber", Integer.toString(dataIndex));
        String showReadingContentLinkAtAttemptNumber = ReadTestData.readDataByTagName("", "showReadingContentLinkAtAttemptNumber", Integer.toString(dataIndex));
        String showSolutionAtAttemptNumber = ReadTestData.readDataByTagName("", "showSolutionAtAttemptNumber", Integer.toString(dataIndex));


        if(policyName==null){
            System.out.println("Inside Policy Name");
            policyName = "ByDefault Policy Name";
        }

        if(policyDescription==null){
            policyDescription = "Policy Description text";
        }
        if(scorePerQuestion==null){
            scorePerQuestion = "1";
        }

        if(immediateFeedBack==null){
            immediateFeedBack = "false";
        }


        if(numberOfAttempt==null){
            numberOfAttempt = "1";
        }
        if(showAnswerAtAttemptNumber==null){
            showAnswerAtAttemptNumber = "";
        }
        if(gradeReleaseOption==null){
            gradeReleaseOption = "Release explicitly by the instructor";
        }
        if(showHintsAtAttemptNumber==null){
            showHintsAtAttemptNumber = "";
        }
        if(showReadingContentLinkAtAttemptNumber==null){
            showReadingContentLinkAtAttemptNumber = "";
        }
        if(showSolutionAtAttemptNumber==null){
            showSolutionAtAttemptNumber = "";
        }

        WebElement html = driver.findElement(By.tagName("html"));
        html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
        html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
        html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));

        new ShareWith().share(shareWithInitialString, shareName, shareWithClass,context_title,true);
        Thread.sleep(2000);

        driver.findElement(By.id("due-time")).click();//click on dur time
        driver.findElement(By.xpath("//li[text()='12:00 AM']")).click();
        driver.findElement(By.id("due-date")).click();//click on due date
        Thread.sleep(2000);
        driver.findElement(By.cssSelector("a[title='Next']")).click();
        Thread.sleep(2000);
        driver.findElement(By.linkText(duedate)).click();//click on specified date
        Thread.sleep(2000);

        if (gradeable.equals("true")) {
            new UIElement().waitAndFindElement(By.className("ir-ls-assign-dialog-gradable-label-check"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("ir-ls-assign-dialog-gradable-label-check")));
        }
        if (gradeable.equals("true") && assignmentpolicy != null) {

            //click on  Choose your assignment policy dropdown
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.xpath("//a[@title='Select an Assignment Policy']")));
            new UIElement().waitAndFindElement(By.linkText(assignmentpolicy));
            driver.findElement(By.linkText(assignmentpolicy)).click();//select a policy
        }
        if(gradeable.equals("true")) {
            if(policyName.equals("ByDefault Policy Name")) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.xpath("//a[@title='Select an Assignment Policy']")));
                new Click().clickbyxpath("//a[@rel='addNewAssignmentPolicy']");
                new AssignmentPolicy().createAssignmentPolicyWhileAssigning(policyName, policyDescription, scorePerQuestion, ordering, Boolean.parseBoolean(immediateFeedBack), numberOfAttempt, showAnswerAtAttemptNumber, gradeReleaseOption, showHintsAtAttemptNumber, showReadingContentLinkAtAttemptNumber, showSolutionAtAttemptNumber);
            }

        }
        driver.findElement(By.id("additional-notes")).clear();
        driver.findElement(By.id("additional-notes")).sendKeys(additionalnote);//add additional note
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.xpath("//span[text()='Assign']")));

        html.sendKeys(Keys.chord(Keys.CONTROL, "0"));
    }
    catch(Exception e)
    {
        Assert.fail("Exception in apphelper method assignTOCToSpecificStudent in class AssignLesson",e);
    }
    }


    public void assignTOCFromOrionAdaptivePracticeWithDueDate(int dataIndex)
    {
        WebDriver driver=Driver.getWebDriver();try
    {

        TocSearch tocSearch= PageFactory.initElements(driver, TocSearch.class);
        String additionalnote = ReadTestData.readDataByTagName("", "additionalnote", Integer.toString(dataIndex));
        String gradeable = ReadTestData.readDataByTagName("", "gradeable", Integer.toString(dataIndex));
        String assignThis = ReadTestData.readDataByTagName("", "assignThis", Integer.toString(dataIndex));
        String numberofquestions = ReadTestData.readDataByTagName("", "numberofquestions", Integer.toString(dataIndex));
        String assignmentReference = ReadTestData.readDataByTagName("", "assignmentReference", Integer.toString(dataIndex));
        String threshold = ReadTestData.readDataByTagName("", "threshold", Integer.toString(dataIndex));
        String thresholdRangeOne = ReadTestData.readDataByTagName("", "thresholdRangeOne", Integer.toString(dataIndex));
        String thresholdRangeTwo = ReadTestData.readDataByTagName("", "thresholdRangeTwo", Integer.toString(dataIndex));
        String thresholdRangeThree = ReadTestData.readDataByTagName("", "thresholdRangeThree", Integer.toString(dataIndex));
        String thresholdRangeFour = ReadTestData.readDataByTagName("", "thresholdRangeFour", Integer.toString(dataIndex));
        String thresholdRangeFive = ReadTestData.readDataByTagName("", "thresholdRangeFive", Integer.toString(dataIndex));


        driver.findElement(By.id("additional-notes")).clear();
        driver.findElement(By.id("additional-notes")).sendKeys(additionalnote);

        DateFormat dateFormat = new SimpleDateFormat("hh:mm a");
        Calendar now = Calendar.getInstance();
        //add minutes to current date using Calendar.add method
        now.add(Calendar.MINUTE,7);
        String calenderFormat=dateFormat.format(now.getTime());
        System.out.println("calenderFormat:"+calenderFormat);

        driver.findElement(By.id("due-time")).click();//click on dur time
        driver.findElement(By.id("due-time")).sendKeys(calenderFormat);//click on dur time

        driver.findElement(By.id("due-date")).click();//click on due date
        Thread.sleep(5000);
        List<WebElement> defaultsDate=driver.findElements(By.cssSelector("a[class='ui-state-default ui-state-highlight ui-state-hover']"));
        for (WebElement defaultdate:defaultsDate){
            if (defaultdate.isDisplayed()){
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();",defaultdate);
                break;
            }
        }

        Thread.sleep(2000);
        if (gradeable.equals("true")) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("ir-ls-assign-dialog-gradable-label-check")));
        }
        if (gradeable.equals("true") && threshold!=null){
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", tocSearch.gradableTreshold_checkbox);//click on thr threshold checkbox
            Thread.sleep(2000);
            if (thresholdRangeOne!=null){
                tocSearch.threshold_val_one.sendKeys(thresholdRangeOne);
            }
            else{
                tocSearch.threshold_val_one.sendKeys("20");
            }

            if (thresholdRangeTwo!=null){
                tocSearch.threshold_val_two.sendKeys(thresholdRangeTwo);
            }
            else{
                tocSearch.threshold_val_two.sendKeys("40");
            }

            if (thresholdRangeThree!=null){
                tocSearch.threshold_val_three.sendKeys(thresholdRangeThree);
            }
            else{
                tocSearch.threshold_val_three.sendKeys("60");
            }

            if (thresholdRangeFour!=null){
                tocSearch.threshold_val_four.sendKeys(thresholdRangeFour);
            }
            else{
                tocSearch.threshold_val_four.sendKeys("80");
            }

            if (thresholdRangeFive!=null){
                tocSearch.threshold_val_five.sendKeys(thresholdRangeFive);
            }
            else{
                tocSearch.threshold_val_five.sendKeys("100");

            }

        }

        if(numberofquestions!=null)
        {
            driver.findElement(By.xpath("//input[@maxlength='3']")).clear();
            driver.findElement(By.xpath("//input[@maxlength='3']")).sendKeys(numberofquestions);
        }
        Thread.sleep(2000);
        if (assignmentReference != null) {
            if (System.getProperty("UCHAR") == null) {
                assignmentReference = assignmentReference + LoginUsingLTI.appendChar;
            } else {
                assignmentReference = assignmentReference + System.getProperty("UCHAR");
            }
            //click on  Choose your Choose your Assignment Reference dropdown
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.linkText("Choose your Assignment Reference")));
            Thread.sleep(2000);
            driver.findElement(By.linkText(assignmentReference)).click();//select a Assignment Reference
            Thread.sleep(2000);
        }
        Thread.sleep(2000);
        if(assignThis==null){
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.xpath("//span[text()='Assign']")));
        }


    }
    catch(Exception e)
    {
        Assert.fail("Exception in apphelper method assignTOCFromOrionAdaptivePracticeWithDueDate in class AssignLesson",e);
    }
    }
    public void assignTOCFromOrionAdaptivePractice(int dataIndex)
    {
        WebDriver driver=Driver.getWebDriver();
        try
        {

            TocSearch tocSearch= PageFactory.initElements(driver,TocSearch.class);
            String additionalnote = ReadTestData.readDataByTagName("", "additionalnote", Integer.toString(dataIndex));
            String gradeable = ReadTestData.readDataByTagName("", "gradeable", Integer.toString(dataIndex));
            String assignThis = ReadTestData.readDataByTagName("", "assignThis", Integer.toString(dataIndex));
            String numberofquestions = ReadTestData.readDataByTagName("", "numberofquestions", Integer.toString(dataIndex));
            String assignmentReference = ReadTestData.readDataByTagName("", "assignmentReference", Integer.toString(dataIndex));
            String duedate = ReadTestData.readDataByTagName("", "duedate", Integer.toString(dataIndex));
            String threshold = ReadTestData.readDataByTagName("", "threshold", Integer.toString(dataIndex));
            String thresholdRangeOne = ReadTestData.readDataByTagName("", "thresholdRangeOne", Integer.toString(dataIndex));
            String thresholdRangeTwo = ReadTestData.readDataByTagName("", "thresholdRangeTwo", Integer.toString(dataIndex));
            String thresholdRangeThree = ReadTestData.readDataByTagName("", "thresholdRangeThree", Integer.toString(dataIndex));
            String thresholdRangeFour = ReadTestData.readDataByTagName("", "thresholdRangeFour", Integer.toString(dataIndex));
            String thresholdRangeFive = ReadTestData.readDataByTagName("", "thresholdRangeFive", Integer.toString(dataIndex));
            String defaultDuetime = ReadTestData.readDataByTagName("", "defaultDuetime", Integer.toString(dataIndex));
            String duetime = ReadTestData.readDataByTagName("", "duetime", Integer.toString(dataIndex));
            Thread.sleep(4000);
            new MouseHover().mouserhover("ir-ls-assign-dialog-header-wrapper");
            Thread.sleep(4000);
            Robot r = new Robot();
            r.mouseWheel(7);

            if (defaultDuetime != null) {
                if (defaultDuetime.equals("true")) {
                    new UIElement().waitAndFindElement(By.id("due-time"));
                    DateFormat dateFormat = new SimpleDateFormat("hh:mm a");
                    Calendar now = Calendar.getInstance();
                    //add minutes to current date using Calendar.add method
                    now.add(Calendar.MINUTE, Integer.parseInt(duetime));
                    String calenderFormat = dateFormat.format(now.getTime());
                    System.out.println("calenderFormat:" + calenderFormat);

                    driver.findElement(By.id("due-time")).click();//click on dur time
                    driver.findElement(By.id("due-time")).sendKeys(calenderFormat);//click on dur time

                    driver.findElement(By.id("due-date")).click();//click on due date
                    Thread.sleep(5000);
                    List<WebElement> defaultsDate = driver.findElements(By.cssSelector("a[class='ui-state-default ui-state-highlight ui-state-hover']"));
                    for (WebElement defaultDate : defaultsDate) {
                        if (defaultDate.isDisplayed()) {
                            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", defaultDate);
                            break;
                        }
                    }
                }
            }
            else {
                WebDriverUtil.clickOnElementUsingJavascript(driver.findElement(By.id("due-time")));//click on dur time
                WebDriverUtil.clickOnElementUsingJavascript(driver.findElement(By.xpath("//li[text()='12:00 AM']")));
                Thread.sleep(2000);
                new ScrollElement().scrollToViewOfElement(driver.findElement(By.id("due-date")));
                try {
                    Actions action = new Actions(driver);
                    action.moveToElement(driver.findElement(By.id("due-date"))).click().perform();//click on due date
                } catch (Exception e) {
                    driver.findElement(By.id("due-date")).click();//click on due date
                }
                driver.findElement(By.cssSelector("a[class='ui-datepicker-next ui-corner-all']")).click();
                if (duedate != null) {
                    Thread.sleep(5000);
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.linkText(duedate)));
                } else {
                    List<WebElement> defaultsDate = driver.findElements(By.className("ui-state-default"));
                    for (WebElement defaultdate : defaultsDate) {
                        if (defaultdate.isDisplayed()) {
                            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", defaultdate);
                            break;
                        }
                    }

                }
            }
            if (gradeable.equals("false")) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("div[class='ir-ls-assign-dialog-gradable-label-check selected']")));
            }

            if (gradeable.equals("true") && threshold!=null) {
                if (threshold.equals("false")) {
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", tocSearch.gradableTreshold_checkbox);//click on  selected threshold checkbox
                }
                if(threshold.equals("true")) {
                    if (thresholdRangeOne != null) {
                        tocSearch.threshold_val_one.clear();
                        tocSearch.threshold_val_one.sendKeys(thresholdRangeOne);
                    } else {
                        tocSearch.threshold_val_one.clear();
                        tocSearch.threshold_val_one.sendKeys("20");
                    }

                    if (thresholdRangeTwo != null) {
                        tocSearch.threshold_val_two.clear();
                        tocSearch.threshold_val_two.sendKeys(thresholdRangeTwo);
                    } else {
                        tocSearch.threshold_val_two.clear();
                        tocSearch.threshold_val_two.sendKeys("40");
                    }

                    if (thresholdRangeThree != null) {
                        tocSearch.threshold_val_three.clear();
                        tocSearch.threshold_val_three.sendKeys(thresholdRangeThree);
                    } else {
                        tocSearch.threshold_val_three.clear();
                        tocSearch.threshold_val_three.sendKeys("60");
                    }

                    if (thresholdRangeFour != null) {
                        tocSearch.threshold_val_four.clear();
                        tocSearch.threshold_val_four.sendKeys(thresholdRangeFour);
                    } else {
                        tocSearch.threshold_val_four.clear();
                        tocSearch.threshold_val_four.sendKeys("80");
                    }

                    if (thresholdRangeFive != null) {
                        tocSearch.threshold_val_five.clear();
                        tocSearch.threshold_val_five.sendKeys(thresholdRangeFive);
                    } else {
                        tocSearch.threshold_val_five.clear();
                        tocSearch.threshold_val_five.sendKeys("100");

                    }
                }
            }


            if(numberofquestions!=null)
            {
                driver.findElement(By.xpath("//input[@maxlength='3']")).clear();
                driver.findElement(By.xpath("//input[@maxlength='3']")).sendKeys(numberofquestions);
            }
            Thread.sleep(2000);
            if (assignmentReference != null) {
                if (System.getProperty("UCHAR") == null) {
                    assignmentReference = assignmentReference + LoginUsingLTI.appendChar;
                } else {
                    assignmentReference = assignmentReference + System.getProperty("UCHAR");
                }
                //click on  Choose your Choose your Assignment Reference dropdown
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.linkText("Select your Assignment Reference")));
                Thread.sleep(2000);
                driver.findElement(By.linkText(assignmentReference)).click();//select a Assignment Reference
                Thread.sleep(2000);
            }

            driver.findElement(By.id("additional-notes")).clear();
            driver.findElement(By.id("additional-notes")).sendKeys(additionalnote);
            Thread.sleep(2000);
            if(assignThis==null){
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.xpath("//span[text()='Assign']")));
            }


        }
        catch(Exception e)
        {
            Assert.fail("Exception in apphelper method assignTOCWithDefaultClassSection in class AssignLesson",e);
        }
    }



}
