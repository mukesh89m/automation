package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT17.R171;

import com.snapwiz.selenium.tests.staf.learningspaces.Config;
import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.DBConnect;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.OpenSearchPage;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.Click;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.*;

/*
 * Created by Sumit on 13/10/14.
 */
public class BulkOperationsDropDown extends Driver {

    @Test(priority = 1, enabled = true)
    public void bulkOperationsDropDown()
    {
        try
        {
            new OpenSearchPage().openSearchPage();//open search page
            new Click().clickbylinkText("Bulk Operations");//click on Bulk Operations dropdown
            List<WebElement> allOptions = driver.findElements(By.xpath("//*[starts-with(@id, 'sbOptions_')]"));
            if(!allOptions.get(3).getText().contains("Copy Assessments"))
                Assert.fail("Copy Assessments option is not present under Bulk Operations dropdown in search tab.");
            if(!allOptions.get(3).getText().contains("Move Assessments"))
                Assert.fail("Move Assessments option is not present under Bulk Operations dropdown in search tab.");
            new Click().clickByid("tab-browse");//click on browse tab
            List<WebElement> allOptions1 = driver.findElements(By.xpath("//*[starts-with(@id, 'sbOptions_')]"));
            if(!allOptions1.get(3).getText().contains("Copy Assessments"))
                Assert.fail("Copy Assessments option is not present under Bulk Operations dropdown in browse tab.");
            if(!allOptions1.get(3).getText().contains("Move Assessments"))
                Assert.fail("Move Assessments option is not present under Bulk Operations dropdown in browse tab.");
            new Click().clickByid("tab-browse");//click on browse tab

        }
        catch (Exception e)
        {
            Assert.fail("Exception in test case bulkOperationsDropDown in class BulkOperationsDropDown.", e);
        }
    }

    @Test(priority = 2, enabled = true, groups = {"bugsLogged"})
    public void copyAssessmentOption()
    {
        try
        {
            //Driver.startDriver();
            new OpenSearchPage().openSearchPage();//open search page
            new Click().clickbylinkText("Bulk Operations");//click on Bulk Operations dropdown
            new Click().clickbylinkText("Copy Assessments");//click on Copy Assessments option
            String text;
            text = new TextFetch().textfetchbyclass("cms-copy-assessments-from");
            Assert.assertEquals(text, "Copy Assessments From", "On clicking Copy Assessment the \"Copy Assessments From\" label is absent in the pop-up.");
            text = new TextFetch().textfetchbyclass("cms-copy-assessments-to");
            Assert.assertEquals(text, "Copy Assessments To", "On clicking Copy Assessment the \"Copy Assessments To\" label is absent in the pop-up.");
            List<WebElement> allFilters = driver.findElements(By.xpath("/*[starts-with(@id, 'sbHolder_')]"));
            new Click().clickbylinkText(Config.course);//click on course
            new Click().clickbylinkText("Select an option");//click on Select a course
            new Click().clickbypartiallinkText("Ch 1:");//click on Ch 1:
            new Click().clickbylinkText("Select a course");//click on Select a course
            new Click().clickbylinkText("Select an option");//click on Select a course
            Thread.sleep(3000);
            String names = new TextFetch().textfetchbyclass("cms-assessment-checkbox-row");
            if(names == null)
                Assert.fail("After selecting a chapter the assessment names are not displayed.");
            String copyRef = new TextFetch().textfetchbycssselector("label[for='copy-as-ref']");
            if(!copyRef.equals("Copy as reference only"))
                Assert.fail("Copy as reference only checkbox is absent in Copy Assessment pop-up.");
            String copyButton = new TextFetch().textfetchbyclass("cms-assessment-footer-copy");
            if(!copyButton.equals("Copy"))
                Assert.fail("Copy button is absent in Copy Assessment pop-up.");

            //5. Verify source panel at left...  The course should show the current course by default.
            new Click().clickbylinkText(Config.course);//click on Course

            //The chapter drop down should have "Select a course" as default
            new Click().clickbylinkText("Select a course");//click on Select a course

            //TC row no. 20
            //6. Verify course filter ...//All the courses available for the CMS user should show up in course filter
            new Click().clickbylinkText("Select a course");//click on Course
            boolean courseFound = false;
            List<WebElement> allCourses = driver.findElements(By.tagName("li"));
            for(WebElement course: allCourses)
            {
                if(course.getText().equals(Config.lscourse))//search for ls course
                    courseFound = true;
            }

            Assert.assertEquals(courseFound, true, "All the courses available for the CMS user is not shown in course filter");

            //TC row no. 21...User should be able to select any course from the filter
            new Click().clickbylinkText(Config.lscourse);//select a course

            //TC row no. 22
            //7. Verify chapter filter....List of chapters should be based on selected course
            new Click().clickbylinkText(Config.lscourse);//click on course
            new Click().clickbylinkText(Config.course);//select previous course
            Thread.sleep(3000);
            new Click().clickbylinkText("Select an option");//click on Select a course
            List<WebElement> allChapters = driver.findElements(By.xpath("/*[starts-with(@id, 'sbOptions_')]"));
            DBConnect.Connect();
            ResultSet chapterNames = DBConnect.st.executeQuery(" SELECT name FROM t_chapter where subject_id =(SELECT id FROM t_subject where course_id = (Select id FROM t_course where external_id = 'ISBNEPROF12517'));");
            String chapter;
            while(chapterNames.next())
            {
                chapter = chapterNames.getString(1);
                System.out.println("chapters DB: "+chapter);
            }
            //TC row no. 24 ...All chapter option should not be available
            List<WebElement> allElements = driver.findElements(By.xpath("//*[starts-with(@id, 'sbOptions_')]"));
            for(WebElement element: allElements)
            {
                if(element.getText().contains("All Chapters"))
                    Assert.fail("All chapter option is available under select chapter menu.");
            }

            //TC row no. 23
            //User should be able to select any chapter from the filter
            new Click().clickbypartiallinkText("Ch 1:");//click on Ch 1:

            //TC row no. 25
            //List of assessments should be per course and chapter filter
            DBConnect.Connect();

            ResultSet result = DBConnect.st.executeQuery("select ta.name\n" +
                    "from t_assessment ta, t_category_assessment_map tcam, t_category tca,\n" +
                    "t_course tc,t_chapter tch\n" +
                    "where tc.id = 253 and tch.position =0 and ta.id=tcam.assessment_id\n" +
                    "and tcam.category_id=tca.id\n" +
                    "and tca.course_id=tc.id\n" +
                    "and tca.chapter_id = tch.id\n" +
                    "and ta.assessment_type != 'PRACTICE' and ta.assessment_type != 'CUSTOM_ASSESSMENT'\n" +
                    "group by ta.id order by tc.id,tch.position,ta.id;");



            List<String> stringarray = new ArrayList<>();
            String result1;
            while(result.next())
            {
                result1 = result.getString(1);
                stringarray.add(result1);
            }


            WebElement element = driver.findElement(By.cssSelector("i[title='"+stringarray.get(stringarray.size()-1)+"']"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",element);
            Thread.sleep(5000);
            List<String> allAssessments = new TextFetch().textfetchbylistclasswithoutindex("cms-assessment-checkbox-row");
            for (int i = 0; i < stringarray.size(); i++) {
                if (!stringarray.get(i).equals(allAssessments.get(i))) {
                    Assert.fail("List of assessments is not as per course and chapter filter.in Copy assessment popup. Mismatch at index "+i);
                }
            }
            //TC row no. 26
            //Assessments should appear with checkbox
            List<WebElement> checkBoxType=driver.findElements(By.xpath("//div[@class='cms-assessment-checkbox-row']/input"));
            for(WebElement eachAssessment:checkBoxType) {
                if(!eachAssessment.getAttribute("type").equals("checkbox"))
                    Assert.fail("All Assessments is not appear with checkbox");
            }

            //TC row no 27
            //The author should be able to select one/more assessments checkboxes
            List<WebElement> selectTwoCheckBox=driver.findElements(By.xpath("//input[@type='checkbox']"));
            for(int i=0;i<3;i++){
                //selectTwoCheckBox.get(i).click();
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();",selectTwoCheckBox.get(i));
            }
            //TC row no 28
            //8. Select filter returning no assessment
            //Expected " Message saying ""No assessments available for selected choices"" should be displayed along with icon"
            new Assignment().createChapter(28);//create a chapter
            new Click().clickByid("content-search-icon");
            new Click().clickbylinkText("Bulk Operations");//click on Bulk Operations dropdown
            new Click().clickbylinkText("Copy Assessments");//click on Copy Assessments option
            new Click().clickbylinkText("Select an option");//click on Select a course
           /* WebElement element1=driver.findElement(By.xpath("(//a[contains(text(),'java')])[3]"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",element1);
            Thread.sleep(500);*/

           allChapters = driver.findElements(By.xpath("//div[@class='overview']//a"));//select all chapter under biology course
            for(int a= 1;a<allChapters.size();a++){
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",allChapters.get(a));
                if(allChapters.get(a).getText().contains("java")){
                    allChapters.get(a).click();
                    break;
                }
            }
            //new Click().clickbyxpath("(//a[contains(text(),'java')])[3]");
            WebDriverWait wait = new WebDriverWait(driver,30);
            wait.until(ExpectedConditions.presenceOfElementLocated(By.className("no-assessment-found-text")));
            String noAssessmentMsg=new TextFetch().textfetchbyclass("no-assessment-found-text");
            String expected="No assessments available for selected choices";
            if(!noAssessmentMsg.contains(expected)){
                Assert.fail("No assessments available for selected choices is not  displayed along with icon");
            }

        }
        catch (Exception e)
        {
            Assert.fail("Exception in test case bulkOperationsDropDown in class BulkOperationsDropDown.", e);
        }
    }
    @Test(priority = 3,enabled = true, groups = {"bugsLogged"})
    public void verifyDestinationPanel()
    {
        try {
            //Driver.startDriver();
            //TC row no 29
            //9. Verify destination panel at right
            //Course filter should say "Select a course" by default
            new OpenSearchPage().openSearchPage();//open search page
            new Click().clickbylinkText("Bulk Operations");//click on Bulk Operations dropdown
            new Click().clickbylinkText("Copy Assessments");//click on Copy Assessments option
            new Click().clickbylinkText("Select a course");//click on Select a course


            //TC row no 30
            //Course dropdown should display all courses available for that publisher


            //TC row no 31
            //Chapter filter should say "Select a course" and remain disabled
            new Click().clickbyxpath("(//a[text()='Select an option'])[4]");//select a destination chapter
            if(driver.findElements(By.partialLinkText("Ch 1")).size()!=0)
                Assert.fail("Chapter filter should say Select a course and it is not remain disabled");
            // TC row no 32
            //Chapter filter should get enabled
            new Click().clickbylinkText("Select a course");//click on Select a course
            new Click().clickbyxpath("(//a[text()='"+Config.course+"'])[3]"); //click on the destination biology
            new Click().clickbyxpath("(//a[text()='Select an option'])[3]");
            if(driver.findElements(By.partialLinkText("Ch 1")).size()==0)
                Assert.fail("Chapter filter should say Select a course and it is not remain disabled");

            //TC row no 33
            //List of all assessments available in course should be displayed
            new Click().clickbypartiallinkText("Ch 1");//click on the chapter ch 1
            DBConnect.Connect();
            ResultSet result = DBConnect.st.executeQuery("select ta.name\n" +
                    "from t_assessment ta, t_category_assessment_map tcam, t_category tca,\n" +
                    "t_course tc,t_chapter tch\n" +
                    "where tc.id = 253 and tch.position =0 and ta.id=tcam.assessment_id\n" +
                    "and tcam.category_id=tca.id\n" +
                    "and tca.course_id=tc.id\n" +
                    "and tca.chapter_id = tch.id\n" +
                    "and ta.assessment_type != 'PRACTICE' and ta.assessment_type != 'CUSTOM_ASSESSMENT'\n" +
                    "group by ta.id order by tc.id,tch.position,ta.id;");

            List<String> stringarray = new ArrayList<>();
            String result1;
            while(result.next())
            {
                result1 = result.getString(1);
                stringarray.add(result1);

            }
            Collections.sort(stringarray);
            Thread.sleep(9000);
            List<String> allAssessments = new TextFetch().textfetchbylistclasswithoutindex("cms-assessment-checkbox-row");
            Collections.sort(allAssessments);
            for (int i = 0; i < stringarray.size(); i++) {
                System.out.println("allAssessments:"+allAssessments.get(i));
                WebElement element = driver.findElements(By.className("cms-assessment-checkbox-row")).get(i);
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",element);
                if (!stringarray.get(i).equals(allAssessments.get(i))) {
                    Assert.fail("List of assessments is not as per course and chapter filter.in Copy assessment popup. Mismatch at index "+i);
                }
            }

            //Tc row no 35
            //Chapters in the filter dropdown should be based on selected course
            new Click().clickbyxpath("(//select[@id='content-search-select-chapter-dropdown'])[2]/following-sibling::div[starts-with(@id,'sbHolder')]/a[1]");//click on the Select a course dropdown
            new Click().clickbyxpath("(//a[text()='Select an option'])[3]");//select destination chapter
            new Click().clickbyxpath("(//select[@id='content-search-select-chapter-dropdown'])[2]/following-sibling::div[starts-with(@id,'sbHolder')]/a[1]");//click on the Select a course dropdown
            List<WebElement> allChapters = driver.findElements(By.xpath("//div[@class='overview']//a"));//select all chapter under biology course

            DBConnect.Connect();
            ResultSet chapterNames = DBConnect.st.executeQuery(" SELECT name FROM t_chapter where subject_id =(SELECT id FROM t_subject where course_id = (Select id FROM t_course where external_id = 'ISBNEPROF12517'));");
            List<String> chapter = new ArrayList<>();
            while(chapterNames.next())
            {
                chapter.add(chapterNames.getString(1));
            }

            for(int i=0;i<chapter.size();i++)
            {
                if(allChapters.get(i+2).equals(chapter.get(i)))
                    Assert.fail("Chapters in the filter dropdown is not based on selected course");
            }

            //Tc row no 36
            //All chapter option should not be available
            int count =0;
            List<WebElement> allElements = driver.findElements(By.xpath("//*[starts-with(@id, 'sbSelector_')]"));
            for(WebElement option: allElements)
            {
                if(option.getText().contains("Select an option")) {
                    count++;
                    option.click();
                    break;
                }

            }
            if(count==2) //first time Select a course is available
                Assert.fail("Select a course option is available");
            //Tc row no 37
            //User should be able to select any chapter from the filter

            Thread.sleep(3000);
            new Click().clickbypartiallinkText("Ch 2");//click on chapter 2
            new Click().clickbyxpath("(//select[@id='content-search-select-chapter-dropdown'])[2]/following-sibling::div[starts-with(@id,'sbHolder')]/a[1]");//click on the Select a course dropdown
            new Click().clickbypartiallinkText("Ch 3");//click on chapter 3
            new Click().clickbyxpath("(//select[@id='content-search-select-chapter-dropdown'])[2]/following-sibling::div[starts-with(@id,'sbHolder')]/a[1]");//click on the Select a course dropdown
            new Click().clickbypartiallinkText("Ch 4");//click on chapter 3

            //Tc row no 39
            //Assessments should appear with radio button
            List<WebElement> checkBoxType=driver.findElements(By.xpath("//div[@class='cms-assessment-radiobtn-row']/input"));
            for(WebElement eachAssessment:checkBoxType) {
                if(!eachAssessment.getAttribute("type").equals("radio"))
                    Assert.fail("All Assessments is not appear with radio button");
            }

            //Tc row no 40
            //The user should be able to select ONE destination assessment only
            List<WebElement> selectTwoRadio=driver.findElements(By.xpath("//input[@type='radio']"));
            for(int i=0;i<3;i++){

                ((JavascriptExecutor) driver).executeScript("arguments[0].click();",selectTwoRadio.get(i));
            }

            //Tc row no 41
            //If destination assessment has AT LEAST one published question AND the chapter is in “Published” state, a validation message should appear - “The destination assessment has questions in published state. Please choose a different assessment.”

            new Click().clickbylinkText(Config.course);//click on Course (Biology)
            new Click().clickbylinkText(Config.lscourse);//click on course(Geography)
            new Click().clickbylinkText("Select an option");//click on Select a course
            new Click().clickbypartiallinkText("Ch 1");//click on chapter 1
            new Click().clickbyxpath("//div[@class='cms-assessment-checkbox-row']/input");//click on the checkbox

            new Click().clickByclassname("cms-assessment-footer-copy");//click on copy button
            String validationMSg=new TextFetch().textfetchbyclass("validation-message-block");
            String actual="The destination assessment has questions in published state. Please choose a different assessment.";
            Assert.assertEquals(validationMSg,actual,"The destination assessment has questions in published state. Please choose a different assessment Message is not displaying");

          //Tc row no 44
          //Copy as reference only checkbox should appear checked and enabled when source course is different from destination
           boolean copyAsReferenceIsSelected=driver.findElement(By.id("copy-as-ref")).isSelected();
            System.out.println("CopyAsReferenceIsSelected:"+copyAsReferenceIsSelected);
            if(copyAsReferenceIsSelected==false)
                Assert.fail("Copy as reference only checkbox is not appear checked and enabled when source course is different from destination");

          //Tc row no 45
          //Copy as reference only checkbox should appear unchecked and disabled when source course is same as destination
            new Click().clickbyxpath("(//a[text()='Biology'])[2]");//click on the destination biology
            new Click().clickbyxpath("(//a[contains(text(),'Geography: Realms, Regions')])[3]"); //click on the destination geography
            boolean copyAsReferenceIsNotSelected=driver.findElement(By.id("copy-as-ref")).isSelected();
            System.out.println("CopyAsReferenceIsNotSelected:"+copyAsReferenceIsNotSelected);
            if(copyAsReferenceIsNotSelected==true)
                Assert.fail("Copy as reference only checkbox is not appear checked and enabled when source course is different from destination");

          //Tc row no 46
          //11. Select filter returning no assessment
         //" Message saying ""No assessments available for selected choices"" should be displayed along with icon"
          new Click().clickbyxpath("(//select[@id='content-search-select-course-dropdown']/following-sibling::div/a[1])[2]");//click on the toggle of destination course
          new Click().clickbyxpath("(//a[text()='Biology'])[2]"); //click on the destination biology
          new Click().clickbypartiallinkText("Geography");//click on Select a course
          List<WebElement> sourceCourseList = driver.findElements(By.xpath("//div[@class = 'overview']//a"));
          for(int a=1;a<sourceCourseList.size();a++){
              ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",sourceCourseList.get(a));
              if(sourceCourseList.get(a).getText().trim().equals("STEM Calculus 1e")){
                  sourceCourseList.get(a).click();
                  break;
              }
          }
            Thread.sleep(1000);
            new Click().clickbylinkText("Select an option");//click on Select a course
            Thread.sleep(1000);
            new Click().clickbylinkText("Course Level Assessment");//click on Select a course
            String noAssessmentMsg=new TextFetch().textfetchbyclass("no-assessment-found-text");
            String expected="No assessments available for selected choices";
            if(!noAssessmentMsg.contains(expected)){
                Assert.fail("No assessments available for selected choices is not  displayed along with icon");
            }

            //Tc row no 47
            //12. Click on copy without selecting source/destination assessment
            //Warning message saying " You need to select at least one source/destination assessment to copy ”should be displayed
            new Click().clickByclassname("cms-assessment-footer-copy");//click on copy button
            String validationMSgWithoutSelectingAssessment=new TextFetch().textfetchbyclass("validation-message-block");
            String actualWithoutSelectingAssessment="You need to select at least one source/destination assessment";
            Assert.assertEquals(validationMSgWithoutSelectingAssessment,actualWithoutSelectingAssessment,"You need to select at least one source/destination assessment Validation message is not appearing");

            //Tc row no 48
            //13. Click on x icon
            //Copy pop up should get closed taking user to the search screen.
            new Click().clickByclassname("cms-assessments-dialog-close");//click on the x icon
            new Click().clickByid("content-search-icon");//click on search button
            String summary=new TextFetch().textfetchbyid("deliver-course");
            Assert.assertEquals(summary,"Summary","users is not landing to the search screen");

        } catch (Exception e) {
            Assert.fail("Exception in test case verifyDestinationPanel in class BulkOperationsDropDown",e);
        }
    }
    @Test(priority = 4, enabled = true)
    public void searchScreenAfterClosing()
    {
        try
        {
            //Tc row no 49
            //Search results displayed before starting bulk operation should continue getting displayed on pop up closure
            //Driver.startDriver();
            new OpenSearchPage().openSearchPage();//open search page
            new Click().clickbylinkText("Bulk Operations");//click on Bulk Operations dropdown
            new TextSend().textsendbyid(new RandomString().randomstring(20),"content-search-field");
            new Click().clickByid("search-question-contents-icon");//click on go button
            new UIElement().waitAndFindElement(By.className("notify-text"));
            Assert.assertEquals(new TextFetch().textfetchbyclass("notify-text"),"No Results Found","No Results Found is not visible");

            new Click().clickbylinkText("Copy Assessments");//click on Copy Assessments option
            new Click().clickByclassname("cms-assessments-dialog-close");//click on the x icon
            Assert.assertEquals(new TextFetch().textfetchbyclass("notify-text"),"No Results Found","No Results Found is not visible");

        }
        catch (Exception e)
        {
            Assert.fail("Exception in test case searchScreenAfterClosing in class BulkOperationsDropDown.", e);
        }
    }
    /*@AfterMethod
    public void tearDown() throws Exception
    {
        driver.quit();
    }*/
}
