package com.snapwiz.selenium.tests.staf.learnon.apphelper;

import com.snapwiz.selenium.tests.staf.learnon.pageFactory.assignments.NewAssignment;
import com.snapwiz.selenium.tests.staf.learnon.uihelper.Click;
import com.snapwiz.selenium.tests.staf.learnon.uihelper.ShareWith;
import com.snapwiz.selenium.tests.staf.learnon.Driver;
import com.snapwiz.selenium.tests.staf.learnon.ReadTestData;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 8/12/15.
 */
public class AssignLesson {
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
            Assert.fail("Exception in apphelper method selectQuestionForCustomAssignment in class  AssignLesson", e);
        }
    }


    public void Assigncustomeassignemnt(int dataIndex)
    {
        try
        {
            String context_title =ReadTestData.readDataByTagName("", "context_title", Integer.toString(dataIndex));
            String duedate =ReadTestData.readDataByTagName("", "duedate", Integer.toString(dataIndex));
            String duetime =ReadTestData.readDataByTagName("", "duetime", Integer.toString(dataIndex));
            String additionalnote =ReadTestData.readDataByTagName("", "additionalnote", Integer.toString(dataIndex));
            String shareWithInitialString =ReadTestData.readDataByTagName("", "shareWithInitialString", Integer.toString(dataIndex));
            String shareName =ReadTestData.readDataByTagName("", "shareName", Integer.toString(dataIndex));
            String shareWithClass =ReadTestData.readDataByTagName("", "shareWithClass", Integer.toString(dataIndex));
            String gradeable =ReadTestData.readDataByTagName("", "gradeable", Integer.toString(dataIndex));
            String assignmentpolicy =ReadTestData.readDataByTagName("", "assignmentpolicy", Integer.toString(dataIndex));
            String assignmentReference =ReadTestData.readDataByTagName("", "assignmentReference", Integer.toString(dataIndex));


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
                    ((JavascriptExecutor)Driver.driver).executeScript("arguments[0].click();",Driver.driver.findElement(By.className("ir-ls-assign-dialog-gradable-label-check")));
                    Thread.sleep(2000);
                    Driver.driver.findElement(By.id("grading-policy")).sendKeys("this is grading policy");
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
                    assignmentReference = assignmentReference +LoginUsingLTI.appendChar;
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


    public void assignTOCWithDefaultClassSection(int dataIndex)
    {
        try
        {

            String duedate = ReadTestData.readDataByTagName("", "duedate", Integer.toString(dataIndex));
            String additionalnote = ReadTestData.readDataByTagName("", "additionalnote", Integer.toString(dataIndex));
            String gradeable = ReadTestData.readDataByTagName("", "gradeable", Integer.toString(dataIndex));
            String gradingpolicy =ReadTestData.readDataByTagName("", "gradingpolicy", Integer.toString(dataIndex));
            String assignmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
            String assignmentpolicy =ReadTestData.readDataByTagName("", "assignmentpolicy", Integer.toString(dataIndex));
            String numberofquestions =ReadTestData.readDataByTagName("", "numberofquestions", Integer.toString(dataIndex));
            String assignmentReference =ReadTestData.readDataByTagName("", "assignmentReference", Integer.toString(dataIndex));



            WebElement html = Driver.driver.findElement(By.tagName("html"));
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
            if(gradeable.equals("true"))
            {
                ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.className("ir-ls-assign-dialog-gradable-label-check")));
                Thread.sleep(2000);
                Driver.driver.findElement(By.id("grading-policy")).sendKeys(gradingpolicy);
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
                //click on  Choose your Choose your Marking Policy  dropdown
                ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();",Driver.driver.findElement(By.linkText("Choose your Marking Policy")));
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
                    Driver.driver.findElement(By.id("grading-policy")).sendKeys("this is grading policy");
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
                ((JavascriptExecutor)Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.className("ir-ls-assign-dialog-gradable-label-check")));
                Thread.sleep(2000);
                Driver.driver.findElement(By.id("grading-policy")).sendKeys(gradingpolicy);
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
}



