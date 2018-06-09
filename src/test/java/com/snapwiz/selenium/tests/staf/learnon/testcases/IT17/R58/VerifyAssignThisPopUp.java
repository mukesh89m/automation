package com.snapwiz.selenium.tests.staf.learnon.testcases.IT17.R58;
/*
 * Created by Sumit on 9/28/2014.
 */

import com.snapwiz.selenium.tests.staf.learnon.Driver;
import com.snapwiz.selenium.tests.staf.learnon.ReadTestData;
import com.snapwiz.selenium.tests.staf.learnon.apphelper.Assignment;
import com.snapwiz.selenium.tests.staf.learnon.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learnon.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.learnon.apphelper.QuestionCard;
import com.snapwiz.selenium.tests.staf.learnon.uihelper.TextFetch;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class VerifyAssignThisPopUp {

    @Test
    public void verifyAssignThisPopUp()
    {
        try
        {
            Driver.startDriver();
            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", "28");
            String text;
            new Assignment().create(28);
            new Assignment().addQuestions(28, "multiplechoice", "");
            new Assignment().addQuestions(28, "multipleselection", "");

            new LoginUsingLTI().ltiLogin("30"); //log in as student
            new LoginUsingLTI().ltiLogin("28"); //log in as instructor
            new Navigator().NavigateTo("Assignments");

            Driver.driver.findElement(By.cssSelector("span.ins-assignment-button-sprite.instructor-assignment-new")).click();

            Thread.sleep(3000);
            if(Driver.driver.findElement(By.cssSelector("div[class='tab active']")).getText().equals("My Resources"))
            {//Opening All Resources tab if not opened after clicking on New Assignment button

                Driver.driver.findElement(By.cssSelector("span[title='All Resources']")).click();
            }
            //Adding assignment to search
            Driver.driver.findElement(By.id("all-resource-search-textarea")).clear();
            Driver.driver.findElement(By.id("all-resource-search-textarea")).sendKeys("\""+assessmentname+"\"");
            Driver.driver.findElement(By.cssSelector("#all-resource-search-button > span.ins-resource-search-sprite.instructor-resource-search-img")).click();
            Thread.sleep(3000);
            Driver.driver.findElement(By.cssSelector("span[title='Assign This']")).click();
            text = new TextFetch().textfetchbycssselector("div[class='ir-ls-assign-dialog-gradable-label ir-ls-assign-dialog-label']");
            Assert.assertEquals(text, "Assessment Task", "\"Assessment Task\" label is absent in Assign THis pop-up.");
            text = new TextFetch().textfetchbylistcssselector("div[class='ir-ls-assign-dialog-assign-to-label ir-ls-assign-dialog-label']", 2);
            Assert.assertEquals(text, "Marking Policy:", "\"Marking Policy:\" label is absent in Assign THis pop-up.");
            text = new TextFetch().textfetchbycssselector("div[class='ir-ls-grading-policy-filter-drop-down-wrapper ir-ls-assign-dialog-field scrollbar-wrapper']");
            Assert.assertEquals(text, "Choose your marking policy", "\"Choose your marking policy\" dropdown label is absent in Assign This pop-up.");
            text = new TextFetch().textfetchbylistcssselector("div[class='ir-ls-assign-dialog-assign-to-label ir-ls-assign-dialog-label']", 3);
            Assert.assertEquals(text, "Marking Policy Description:", "\"Marking Policy Description:\" dropdown label is absent in Assign This pop-up.");

            new Assignment().assignToStudent(28);//assign to student
            new Assignment().clickViewResponse(assessmentname);
            text = new TextFetch().textfetchbycssselector("span[class='ls-assignment-grading-title ls-assignment-total-points']").trim();
            if(!text.contains("Total Marks"))
                Assert.fail("\"Total Marks\" label is absent in assignment response page.");

            new LoginUsingLTI().ltiLogin("30"); //log in as student
            new Assignment().openAssignmentFromCourseStream("28");
            text = new TextFetch().textfetchbycssselector("div[class='static-assessment-point-content-box']").trim();
            if(!text.contains("Marks Available"))
                Assert.fail("\"Marks Available\" label is absent in assignment tab while student is attempting assignment.");
            new Assignment().submitAssignmentAsStudent(28);
            new QuestionCard().clickOnCard("", 0);//click on question card
            text = new TextFetch().textfetchbycssselector("div[class='question-card-content']").trim();
            if(!text.contains("Marks"))
                Assert.fail("\"Marks\" label is absent in question preview page.");

        }
        catch (Exception e)
        {
            Assert.fail("Exception in TC verifyAssignThisPopUp in class VerifyAssignThisPopUp.", e);
        }
    }
    @AfterMethod
    public void tearDown() throws Exception
    {
        Driver.driver.quit();
    }
}
