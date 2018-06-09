package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT17.R171;

import com.snapwiz.selenium.tests.staf.learningspaces.Config;
import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.DBConnect;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.OpenSearchPage;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.Click;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.TextFetch;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mukesh on 12/2/14.
 */
public class CopyAssessmentFromBrowser extends Driver {

    @Test(priority = 1, enabled = true)
    public void checkCopyAssessmentUIElements()
    {
        try
        {
            //Tc row no 50
            //Driver.startDriver();
            new OpenSearchPage().openSearchPage();//open search page
            new Click().clickByid("tab-browse");//click on browse tab
            new Click().clickbylinkText("Bulk Operations");//click on Bulk Operations dropdown
            new Click().clickbylinkText("Copy Assessments");//click on Copy Assessments option
            String text;
            text = new TextFetch().textfetchbyclass("cms-copy-assessments-from");
            System.out.println("text: " + text);
            Assert.assertEquals(text, "Copy Assessments From", "On clicking Copy Assessment the \"Copy Assessments From\" label is absent in the pop-up.");
            text = new TextFetch().textfetchbyclass("cms-copy-assessments-to");
            System.out.println("text: "+text);
            Assert.assertEquals(text, "Copy Assessments To", "On clicking Copy Assessment the \"Copy Assessments To\" label is absent in the pop-up.");
            List<WebElement> allFilters = driver.findElements(By.xpath("/*[starts-with(@id, 'sbHolder_')]"));
            for(WebElement filter : allFilters)
            {
                System.out.println("filter: "+filter.getText());
            }
            new Click().clickbylinkText(Config.course);//click on course
            new Click().clickbylinkText("Select an option");//click on Select a chapter
            new Click().clickbypartiallinkText("Ch 1:");//click on Ch 1:
            new Click().clickbylinkText("Select a course");//click on Select a course
            new Click().clickbylinkText("Select an option");//click on Select a chapter

            //String names = new TextFetch().textfetchbyclass("cms-assessment-checkbox-row");
            String names = new TextFetch().textfetchbyxpath("//div[@class='overview']//li");

            System.out.println("names: "+names);
            if(names == null)
                Assert.fail("After selecting a chapter the assessment names are not displayed.");
            String copyRef = new TextFetch().textfetchbycssselector("label[for='copy-as-ref']");
            System.out.println("copyRef: "+copyRef);
            if(!copyRef.equals("Copy as reference only"))
                Assert.fail("Copy as reference only checkbox is absent in Copy Assessment pop-up.");
            String copyButton = new TextFetch().textfetchbyclass("cms-assessment-footer-copy");
            System.out.println("copyButton: "+copyButton);
            if(!copyButton.equals("Copy"))
                Assert.fail("Copy button is absent in Copy Assessment pop-up.");

        }
        catch (Exception e)
        {
            Assert.fail("Exception in test case checkCopyAssessmentUIElements in class CopyAssessmentFromBrowser.", e);
        }
    }
    @Test(priority = 2, enabled = true)
    public void verifySourcePanel()
    {

        try
        {
            //Tc row no 52 & 53
            //Driver.startDriver();
            DBConnect.Connect();
            System.out.println("Course Id Special : " + Config.courseid);
            ResultSet chapterNames = DBConnect.st.executeQuery(" SELECT name FROM t_chapter where subject_id =(SELECT id FROM t_subject where course_id = (Select id FROM t_course where external_id = '"+Config.courseid+"'));");
            List<String> chapter = new ArrayList<>();
            new OpenSearchPage().openSearchPage();//open search page
            new Click().clickByid("tab-browse");//click on browse tab
            new Click().clickbylinkText("Bulk Operations");//click on Bulk Operations dropdown
            new Click().clickbylinkText("Copy Assessments");//click on Copy Assessments option
            new Click().clickbylinkText(Config.course);//click on Biology course
            new Click().clickbyxpath("(//a[text()='Select an option'])[4]");//click on Select a chapter

            //Tc row no 54
            //Values in chapter filter should be based on default value in course filter

            List<String> allChapters =new TextFetch().fetchhiddentextbyxpath("(//a[text()='Select an option'])[3]/../ul/div[2]//li");//fetch Chapter Name from UI
            Thread.sleep(2000);
            for(int a =0 ;a<allChapters.size();a++){
                System.out.println("allChapters :  " + allChapters.get(a));
            }
            System.out.println("UI Size:" + allChapters.size());
            System.out.println("Chapter From UI:"+allChapters);
            DBConnect.Connect();
            System.out.println("Course Id Special : " + Config.courseid);
            //ResultSet chapterNames = DBConnect.st.executeQuery(" SELECT name FROM t_chapter where subject_id =(SELECT id FROM t_subject where course_id = (Select id FROM t_course where external_id = '"+Config.courseid+"'));");
            //List<String> chapter = new ArrayList<>();
            while(chapterNames.next())
            {
                chapter.add(chapterNames.getString(1));
                System.out.println("DB Size:"+chapter.size());
                System.out.println("Db Chapter Name:"+chapterNames.getString(1));
            }
            for(int i=0;i<chapter.size();i++)
            {
                System.out.println("allChapters.get(i+2) : " + allChapters.get(i+2));
                System.out.println("chapter.get(i) : " + chapter.get(i));

                if(!allChapters.get(i+2).contains(chapter.get(i)))
                    Assert.fail("Values in chapter filter is not based on default value in course filter");
            }

        }
        catch (Exception e)
        {
            Assert.fail("Exception in test case verifySourcePanel in class CopyAssessmentFromBrowser.", e);
        }
    }

    /*@AfterMethod
    public void tearDown() throws Exception
    {
        driver.quit();
    }*/
}
