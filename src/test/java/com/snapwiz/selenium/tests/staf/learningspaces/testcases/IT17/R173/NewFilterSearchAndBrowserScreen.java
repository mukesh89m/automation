package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT17.R173;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Assignment;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.DirectLogin;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.SelectCourse;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Created by Mukesh on 15-Oct-14.
 */
public class NewFilterSearchAndBrowserScreen extends Driver {

    @Test(priority = 1,enabled = true)
    public void newFilterSearchAndBrowserScreen() {
        try {
            //Row No 2:"1. Login as a CMS user
            //Driver.startDriver();
            new DirectLogin().CMSLogin();
            //2. Select a course and go to Search page
            new SelectCourse().selectcourse(); //select a course
            Thread.sleep(3000);
            //3. Click on Search Filters"
            new Click().clickByid("content-search-icon"); //click on the search Filter
            new Click().clickByid("search-filter-link"); //clcik on the seach filter link

            //Expected: New filter "All Questions" should appear to the 'Search Filters' screen along with the existing filters
            String AllQuestionText = new TextFetch().textfetchbyxpath("(//a[@title='All Questions'])[1]");
            Assert.assertEquals("All Questions", AllQuestionText, "All Questions is not appear to the 'Search Filters' screen");

            //4. Click on All Questions Filter
            new Click().clickbyxpath("(//a[@title='All Questions'])[1]");//click on the All questions Filter

            //Expected: "On click of ""All Questions” dropdown the author should get the following two options" 1.Questions with media 2.Questions without media"
            String questionWithMedia = new TextFetch().textfetchbylinktext("Questions with Media");
            Assert.assertEquals("Questions with Media", questionWithMedia, "Questions with Media is not displayed");
            String questionWithOutMedia = new TextFetch().textfetchbylinktext("Questions without Media");
            Assert.assertEquals("Questions without Media", questionWithOutMedia, "Questions without Media is not displayed");

            //"5. Click on Browse tab
            new Click().clickByid("tab-browse"); //click on the Browser Tab
            //6. Select 'Search Questions' in select content type filter"

            new Click().clickbyxpath("(//a[@title='Select Content Type'])[1]");
            new Click().clickbylinkText("Search Questions");

            //Expected 1: New filter "All Questions" should appear to the Browse screen along with the existing filters
            String AllQuestionTextOfBrowserScreen = new TextFetch().textfetchbyxpath("(//a[@title='All Questions'])[3]");
            System.out.println("AllQuestionTextOfBrowserScreen" + AllQuestionTextOfBrowserScreen);
            Assert.assertEquals("All Questions", AllQuestionTextOfBrowserScreen, "All Questions is not appear to the 'Browser Filters' screen");

            //Expected 2: "On click of ""All Questions” dropdown the author should get the following two options:  1.Questions with media 2.Questions without media"
            new Click().clickbyxpath("(//a[@title='All Questions'])[3]");//click on the All questions Filter
            String questionWithMedia1 = new TextFetch().textfetchbylinktext("Questions with Media");
            Assert.assertEquals("Questions with Media", questionWithMedia1, "Questions with Media is not displayed");
            String questionWithOutMedia1 = new TextFetch().textfetchbylinktext("Questions without Media");
            Assert.assertEquals("Questions without Media", questionWithOutMedia1, "Questions without Media is not displayed");



        } catch (Exception e) {
            Assert.fail("Exception in testcase newFilterSearchAndBrowserScreen for the class NewFilterSearchAndBrowserScreene" + e);
        }
    }
    @Test(priority = 2,enabled = true)
    public void searchQuestionMediaType() {
        try {
            // Row no 6:"1. Login as a CMS user
            //Driver.startDriver();
            new Assignment().create(1);
            //2. Select a course and go to Search page
             //new SelectCourse().selectcourse(); //select a course
            new Click().clickByid("content-search-icon"); //click on the search Filter
            //3. Enter the text related to media(image/audio/video) file in Search question content and click on Go button"
            String imageText = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(1));
            new TextSend().textsendbyid(imageText, "content-search-field");
            new Click().clickByid("search-question-contents-icon");//click on Go button

            //Expected1 :All the questions with related text in search question content should appear as per the filtered search

            List<String> relatedText = new TextFetch().textfetchbylistxpathwithoutindex("//div[contains(@class,'search-question-rawcontent')]");
            for(String eachText:relatedText)
            {
                if(!eachText.contains("MyImage"))
                {
                    Assert.fail("All the questions with related text in search question content is not appear as per the filtered search");
                }
            }
            //Expected2 :Generic Icon(media type) should be present for media type files only
            String imageIcon = driver.findElement(By.cssSelector("div[class='cms-result-media-icon']")).getCssValue("background-image");
            System.out.println("Image:" + imageIcon);
            if (!imageIcon.contains("webresources/images/cms/multi-media.png"))
                Assert.fail("Generic Icon(media type) is not present for media type files ");

            //Expected3: Chapter No, Assessment Name and Learning Objective should appear below each question
            List<String> chapterNo = new TextFetch().textfetchbylistxpathwithoutindex("//span[contains(text(),'Ch')]");
            for(String chapNo:chapterNo)
            {
                if(!chapNo.contains("Ch 1:"))
                {
                    Assert.fail("Chapter No is not Appeared");
                }
            }
            //Assessment Name
            List<String> assessmentName=new TextFetch().textfetchbylistxpathwithoutindex("//div[contains(text(),'Assessment Name :')]");
            for(String assessName:assessmentName)
            {
                if(!assessName.contains("Assessment Name :"))
                {
                    Assert.fail("AssessmentName is not Appeared");
                }
            }
            //Leaning Objective
           /* List<String>  learningObjective=new TextFetch().textfetchbylistxpathwithoutindex("//span[contains(text(),'Learning Objective')]");
            for(String objective:learningObjective)
            {
                System.out.println("Learning Objective:"+objective);
                if(!objective.contains("Learning Objective :"))
                {
                    Assert.fail("LearningObjective is not Appeared");
                }
            }*/
            String learningObjective1=new TextFetch().textfetchbylistid("search-learning-objective-blk",0);
            if(!learningObjective1.contains("Learning Objective :"))
                Assert.fail("LearningObjective is not Appeared");
            // 4. Select "Questions with media" in "All Questions" filter
            new Click().clickByid("search-filter-link"); //clcik on the seach filter link
            new Click().clickbyxpath("(//a[@title='All Questions'])[1]");//click on the All questions Filter
            new Click().clickbylinkText("Questions with Media");//click on the Question with media
            new Click().clickbyxpath("//span[text()='GO']");//click on Go Button
            Thread.sleep(5000);
            //Expected 1: Questions that have media(image/audio/video) in the question content should appear

            //Expected 2: Any media in the answer choice,solution,hint etc. should not appear using "Questions with media" filter
           /* if(driver.getPageSource().contains("Solution")||driver.getPageSource().contains("Hint")) {

            }*/
            if(driver.getPageSource().contains("Hint")) {

            }

            //Expected 3:"Quick Preview","Full Preview" and "Edit" options should appear for questions with media type

            new MouseHover().mouserhover("content-search-title-section");
            Thread.sleep(2000);
            String quickPreview=driver.findElement(By.className("expand-question-content")).getAttribute("title");
            //System.out.println("quickPreview:"+quickPreview);
            Assert.assertEquals(quickPreview,"Quick Preview","QuickPreview is not appear for the question Type media");
            String fullPreview=driver.findElement(By.className("preview-question-content")).getAttribute("title");
            //System.out.println("fullPreview:"+fullPreview);
            Assert.assertEquals(fullPreview,"Full Preview","fullPreview is not appear for the question Type media");
            String edit=driver.findElement(By.className("edit-question-content")).getAttribute("title");
            //System.out.println("edit:"+edit);
            Assert.assertEquals(edit,"Edit","edit is not appear for the question Type media");

            //Expected 3:Author should be able to perform copy, move, delete or Launch review action as per the existing functionality
            List<WebElement> alllist=driver.findElements(By.xpath("//div[@id = 'question-check-box-div']//label"));
            Thread.sleep(2000);
            alllist.get(0).click();
            Thread.sleep(2000);
            new Click().clickbyxpath("//div[@id = 'content-search-copy-btn']//img[@id='content-search-icon']");//Click on Copy Button
            String 	CopySelectedQuestionsTo =new TextFetch().textfetchbyxpath("//div[@id='contentSearchSelectChapterSlider']/div[1]");

            if(!CopySelectedQuestionsTo.contains("Copy Selected Questions To"))
            {
                Assert.fail("user is not able to perform copy operation");
            }
            new Click().clickByid("cancel-select-assement");//click on the cancel button

            new Click().clickbyxpath("//div[@id = 'content-search-move-btn']//img[@id='content-search-icon']");//Click on Move Button
            String 	MoveSelectedQuestionsTo  =new TextFetch().textfetchbyxpath("//div[@id='contentSearchSelectChapterSlider']/div[1]");

            if(!MoveSelectedQuestionsTo.contains("Move Selected Questions To"))
            {
                Assert.fail("user is not able to perform move operation");
            }
            new Click().clickByid("cancel-select-assement");//click on the cancel button

            // new Click().clickbyxpath("//div[@id = 'content-search-delete-btn']//img[@id='content-search-icon']");//Click on Delete Button

            new Click().clickbyxpath("//div[@id = 'content-search-review-btn']//img[@id='content-search-icon']");//Click on Review Launch
            String reviewLaunch=new TextFetch().textfetchbyclass("cms-content-question-review-list-label");
            Assert.assertEquals(reviewLaunch,"Review Content","user is not able to perform review launch");
            Thread.sleep(7000);
            //5. Select "Questions without media" in "All Questions" Filter

            new Click().clickByid("content-search-icon"); //click on the search Filter
            Thread.sleep(5000);
            new Click().clickByid("search-filter-link"); //clcik on the seach filter link
            new Click().clickbylinkText("Questions with Media");//click on the All questions Filter
            new Click().clickbylinkText("Questions without Media");//click on the Question Without Media
            new Click().clickbyxpath("//span[text()='GO']");//click on Go Button

            WebDriverWait wait = new WebDriverWait(driver,30);
            wait.until(ExpectedConditions.presenceOfElementLocated(By.className("notify-text")));
            //Expected Questions that have media in the question content should not appear
            String noQuestionFound=new TextFetch().textfetchbyclass("notify-text");
            Assert.assertEquals(noQuestionFound,"No Results Found"," Questions that have media in the question content is appeared");

            //All other questions (except questions with media in the question content) should appear as per the filtered search
            new Assignment().create(14);
            new Click().clickByid("content-search-icon"); //click on the search Filter
            Thread.sleep(5000);
            String imageText1 = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(14));
            new TextSend().textsendbyid(imageText1, "content-search-field");
            new Click().clickByid("search-filter-link"); //clcik on the seach filter link
            new Click().clickbyxpath("(//a[@title='All Questions'])[1]");//click on the All questions Filter
            new Click().clickbylinkText("Questions without Media");//click on the Question Without Media
            new Click().clickbyxpath("//span[text()='GO']");//click on Go Button
            int i= driver.findElements(By.className("cms-result-media-icon")).size();
            if(i!=0)
            {
                Assert.fail("All other question are present except media");
            }

            List<WebElement> alllist2=driver.findElements(By.xpath("//div[@id = 'question-check-box-div']//label"));
            Thread.sleep(2000);
            alllist2.get(0).click();
            Thread.sleep(2000);
            new Click().clickbyxpath("//div[@id = 'content-search-delete-btn']//img[@id='content-search-icon']");//Click on Delete Button


            //Questions with media in the answer choice,solution,hint etc. should also appear
            new Assignment().create(15);
            //new SelectCourse().selectcourse(); //select a course
            new Click().clickByid("content-search-icon"); //click on the search Filter
            String imageText2 = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(15));
            new TextSend().textsendbyid(imageText2, "content-search-field");
            new Click().clickByid("search-filter-link"); //clcik on the seach filter link
            new Click().clickbyxpath("(//a[@title='All Questions'])[1]");//click on the All questions Filter
            new Click().clickbylinkText("Questions with Media");//click on the Question with media
            new Click().clickbyxpath("//span[text()='GO']");//click on Go Button
            Thread.sleep(5000);
            Actions action2 = new Actions(driver);
            List<WebElement> we2 = driver.findElements(By.xpath("//div[@id = 'question-check-box-div']//label"));
            action2.moveToElement(we2.get(we2.size()-1)).build().perform();// Mouse Over on Edit icon on 2nd question
            Thread.sleep(2000);
            List<WebElement> alllist1=driver.findElements(By.xpath("//div[@class='edit-question-content']"));
            Thread.sleep(2000);
            alllist1.get(alllist1.size()-1).click();//click on the edit Button
            Thread.sleep(5000);
            // new Click().clickbylistxpath("//div[@class='edit-question-content']", 1);// Click on 'Edit' Button on 2nd Question
            String innerHtmlForSolution = driver.findElement(By.id("content-solution")).getAttribute("innerHTML");
            System.out.println("innerHtml: "+innerHtmlForSolution);
            if(!innerHtmlForSolution.contains("src=\"https://s3.amazonaws.com/"))//verifying the presence of image for hint
                Assert.fail("Questions with media in the  solution is not appear");


            String innerHtmlForHint = driver.findElement(By.id("content-hint")).getAttribute("innerHTML");
            System.out.println("innerHtml: "+innerHtmlForHint);
            if(!innerHtmlForHint.contains("src=\"https://s3.amazonaws.com/"))//verifying the presence of image for hint
                Assert.fail("Questions with media in the hint is not appear");
            //6. Select "Questions with media" and set the filter in such a way that does not include media file
            new Click().clickByid("content-search-icon"); //click on the search Filter
            Thread.sleep(5000);
            new Click().clickByid("search-filter-link"); //clcik on the seach filter link
            new Click().clickbylinkText("All Difficulty Level");
            new Click().clickbylinkText("Hard");
            new Click().clickbyxpath("//span[text()='GO']");//click on Go Button

            //Expected "No Results Found" should appear
            String noQuestionFound1=new TextFetch().textfetchbyclass("notify-text");
            System.out.println("noQuestionFound1"+noQuestionFound1);
            Assert.assertEquals(noQuestionFound1,"No Results Found"," Questions that have media in the question content is appeared");
        }
        catch(Exception e){
            Assert.fail("Exception in testcase searchQuestionMediaType for the class NewFilterSearchAndBrowserScreene", e);
        }
    }

    @Test(priority = 3,enabled = true)
    public void searchQuestionMediaTypeInBrowserTab() {
        try {
            //Row No 17:"1. Login as a CMS user
            //2. Select a course and go to Search page
            //3. Select Browse tab and Select 'Search Questions' in select content type filter"
            //Driver.startDriver();
            new Assignment().create(17);
            //2. Select a course and go to Search page
            //new SelectCourse().selectcourse(); //select a course
            new Click().clickByid("content-search-icon"); //click on the search Filter
            new Click().clickByid("tab-browse"); //click on the Browser Tab
            //6. Select 'Search Questions' in select content type filter"
            new Click().clickbyxpath("(//a[@title='Select Content Type'])[1]"); //click on the search content type
            new Click().clickbylinkText("Search Questions"); //select search question
            new Click().clickbylinkText("Select an option");
            //Thread.sleep(2000);
            new Click().clickbylinkText("Ch 1: The Study of Life");
            Thread.sleep(5000);
            //Expected 1 All the questions with Filtered chapter should appear as per the search filter
            List<String>searchedChapter= new TextFetch().textfetchbylistxpathwithoutindex("//div[@id='search-chapter-blk']/div");
            System.out.println("searchedChapter"+searchedChapter);
            for(String text:searchedChapter)
            {
                if(!text.contains("The Study of Life"))
                {
                    Assert.fail("All the questions with Filtered chapter ia not appear as per the search fi");
                }
            }
            //Expected2 :Generic Icon(media type) should be present for media type files only
            new Click().clickbylinkText("All Questions");
            new Click().clickbylinkText("Questions with Media");
            String imageIcon = driver.findElement(By.cssSelector("div[class='cms-result-media-icon']")).getCssValue("background-image");
            System.out.println("Image:" + imageIcon);
            if (!imageIcon.contains("webresources/images/cms/multi-media.png"))
                Assert.fail("Generic Icon(media type) is not present for media type files ");

            //Chapter No, Assessment Name and Learning Objective should appear below each question

            List<WebElement> chapterNo = driver.findElements(By.id("search-chapter-blk"));
            for(WebElement chapNo:chapterNo)
            {
                if(!chapNo.getText().contains("Ch 1:"))
                {

                    Assert.fail("Chapter No is not Appeared");
                }
            }
            //Assessment Name
            List<String> assessmentName=new TextFetch().textfetchbylistxpathwithoutindex("//div[contains(text(),'Assessment Name :')]");
            System.out.println("assessmentName"+assessmentName);
            for(String assessName:assessmentName)
            {
                if(!assessName.contains("Assessment Name :"))
                {
                    Assert.fail("AssessmentName is not Appeared");
                }
            }
            //Leaning Objective
            List<String>  learningObjective=new TextFetch().textfetchbylistxpathwithoutindex("//span[contains(text(),'Learning Objective')]");
            for(String objective:learningObjective)
            {
                if(!objective.contains("Learning Objective :"))
                {
                    Assert.fail("LearningObjective is not Appeared");
                }
            }

            //4. Select "Questions with media" in "All Questions" filter
            new Assignment().create(21);
            //new SelectCourse().selectcourse(); //select a course
            new Click().clickByid("content-search-icon"); //click on the search Filter
            new Click().clickByid("tab-browse"); //click on the Browser Tab
            new Click().clickbyxpath("(//a[@title='Select Content Type'])[1]"); //click on the search content type
            new Click().clickbylinkText("Search Questions"); //select search question
            new Click().clickbylinkText("Select an option");
            new Click().clickbylinkText("Ch 1: The Study of Life");
            new Click().clickbylinkText("All Questions");
            new Click().clickbylinkText("Questions with Media");
            new Click().clickbylinkText("Select Question Type");
            new Click().clickbylinkText("True / False");
            //Expected 1:Questions that have media(image/audio/video) in the question content should appear
            String imageIcon1 = driver.findElement(By.cssSelector("div[class='cms-result-media-icon']")).getCssValue("background-image");
            System.out.println("Image:" + imageIcon1);
            if (!imageIcon1.contains("webresources/images/cms/multi-media.png"))
                Assert.fail("Questions that have media(image/audio/video) in the question content is not appear ");

            //Expected 2:Any media in the answer choice,solution,hint etc. should not appear using "Questions with media" filter
            List<WebElement> solution = driver.findElements(By.xpath("//div[@id='content-solution']/img"));
            Assert.assertTrue(solution.isEmpty(),"media is not present in solution hint");

            //Expected 3:"Quick Preview","Full Preview" and "Edit" options should appear for questions with media type
            new MouseHover().mouserhover("content-search-title-section");
            Thread.sleep(2000);

            String quickPreview=driver.findElement(By.className("expand-question-content")).getAttribute("title");
            Assert.assertEquals(quickPreview,"Quick Preview","QuickPreview is not appear for the question Type media");
            String fullPreview=driver.findElement(By.className("preview-question-content")).getAttribute("title");
            Assert.assertEquals(fullPreview,"Full Preview","fullPreview is not appear for the question Type media");
            String edit=driver.findElement(By.className("edit-question-content")).getAttribute("title");
            Assert.assertEquals(edit,"Edit","edit is not appear for the question Type media");
            //Expected 4:Author should be able to perform copy, move, delete or Launch review action as per the existing functionality
            List<WebElement> alllist=driver.findElements(By.xpath("//div[@id = 'question-check-box-div']//label"));
            Thread.sleep(2000);
            new Click().clickByElement(alllist.get(alllist.size()-1));
            Thread.sleep(2000);
            new Click().clickbyxpath("//div[@id = 'content-search-copy-btn']//img[@id='content-search-icon']");//Click on Copy Button
            String 	CopySelectedQuestionsTo =new TextFetch().textfetchbyxpath("//div[@id='contentSearchSelectChapterSlider']/div[1]");

            if(!CopySelectedQuestionsTo.contains("Copy Selected Questions To"))
            {
                Assert.fail("user is not able to perform copy operation");
            }
            new Click().clickByid("cancel-select-assement");//click on the cancel button

            new Click().clickbyxpath("//div[@id = 'content-search-move-btn']//img[@id='content-search-icon']");//Click on Move Button
            String 	MoveSelectedQuestionsTo  =new TextFetch().textfetchbyxpath("//div[@id='contentSearchSelectChapterSlider']/div[1]");

            if(!MoveSelectedQuestionsTo.contains("Move Selected Questions To"))
            {
                Assert.fail("user is not able to perform move operation");
            }
            new Click().clickByid("cancel-select-assement");//click on the cancel button

            new Click().clickbyxpath("//div[@id = 'content-search-review-btn']//img[@id='content-search-icon']");//Click on Review Launch
            String reviewLaunch=new TextFetch().textfetchbyclass("cms-content-question-review-list-label");
            Assert.assertEquals(reviewLaunch,"Review Content","user is not able to perform review launch");

            //new Click().clickbyxpath("//div[@id = 'content-search-delete-btn']//img[@id='content-search-icon']");//Click on Delete Button
            //5. Select "Questions without media" in "All Questions" Filter
            new Click().clickByid("content-search-icon"); //click on the search Filter
            new Click().clickbylinkText("Questions with Media");
            new Click().clickbylinkText("Questions without Media");
            new Click().clickbylinkText("Select Bloom's Taxonomy");
            new Click().clickbylinkText("Analysis");


            //Expected 1:Questions that have media in the question content should not appear
            String noQuestionFound=new TextFetch().textfetchbyclass("notify-text");
            Assert.assertEquals(noQuestionFound,"No Results Found"," Questions that have media in the question content is appeared");

            //Expected All other questions (except questions with media in the question content) should appear as per the filtered se

            new Assignment().create(25);
            new Click().clickByid("content-search-icon"); //click on the search Filter
            new Click().clickByid("tab-browse"); //click on the Browser Tab
            //6. Select 'Search Questions' in select content type filter"

            new Click().clickbyxpath("(//a[@title='Select Content Type'])[1]"); //click on the search content type
            new Click().clickbylinkText("Search Questions"); //select search question
            new Click().clickbylinkText("Select an option");
            new Click().clickbylinkText("Ch 1: The Study of Life");
            new Click().clickbylinkText("All Questions");
            new Click().clickbylinkText("Questions without Media");
            new Click().clickbylinkText("Select Difficulty Level");
            new Click().clickbylinkText("Hard");
            new Click().clickbylinkText("Select Question Type");
            new Click().clickbylinkText("True / False");
            Thread.sleep(5000);
            int i= driver.findElements(By.className("cms-result-media-icon")).size();
            if(i!=0)
            {
                Assert.fail("All other question are present except media");
            }

            //Expected 3:Questions with media in the answer choice,solution,hint etc. should also appear
            new Assignment().create(24);
            //new SelectCourse().selectcourse(); //select a course
            new Click().clickByid("content-search-icon"); //click on the search Filter
            new Click().clickByid("tab-browse"); //click on the Browser Tab
            new Click().clickbyxpath("(//a[@title='Select Content Type'])[1]"); //click on the search content type
            new Click().clickbylinkText("Search Questions"); //select search question
            new Click().clickbylinkText("Select an option");
            new Click().clickbylinkText("Ch 1: The Study of Life");
            new Click().clickbylinkText("All Questions");
            new Click().clickbylinkText("Questions with Media");//click on the Question with media
            new Click().clickbylinkText("Select Difficulty Level");
            new Click().clickbylinkText("Hard");
            new Click().clickbylinkText("Select Question Type");
            new Click().clickbylinkText("True / False");
            Actions action = new Actions(driver);
            List<WebElement> we = driver.findElements(By.xpath("//div[@id = 'question-check-box-div']//label"));
            action.moveToElement(we.get(0)).build().perform();// Mouse Over on Edit icon on 2nd question
            Thread.sleep(2000);
            List<WebElement> alllist3=driver.findElements(By.xpath("//div[@class='edit-question-content']"));
            Thread.sleep(2000);
            alllist3.get(0).click();//click on the edit Button
            Thread.sleep(2000);
            // new Click().clickbylistxpath("//div[@class='edit-question-content']", 1);// Click on 'Edit' Button on 2nd Question
            String innerHtmlForSolution = driver.findElement(By.id("content-solution")).getAttribute("innerHTML");
            System.out.println("innerHtml: "+innerHtmlForSolution);
            if(!innerHtmlForSolution.contains("src=\"https://s3.amazonaws.com/"))//verifying the presence of image for hint
                Assert.fail("Questions with media in the  solution is not appear");

            String innerHtmlForHint = driver.findElement(By.id("content-hint")).getAttribute("innerHTML");
            System.out.println("innerHtml: "+innerHtmlForHint);
            if(!innerHtmlForHint.contains("src=\"https://s3.amazonaws.com/"))//verifying the presence of image for hint
                Assert.fail("Questions with media in the hint is not appear");

            //6. Select "Questions with media" and set the filter in such a way that does not include media file
            new Click().clickByid("content-search-icon"); //click on the search Filter
            new Click().clickbylinkText("Select Bloom's Taxonomy");
            new Click().clickbylinkText("Evaluation");

            // Expected "No Results Found" should appear
            String noQuestionFound1=new TextFetch().textfetchbyclass("notify-text");
            Assert.assertEquals(noQuestionFound1,"No Results Found"," Questions that have media in the question content is appeared");


        }
        catch (Exception e)
        {
            Assert.fail("Exception in testcase searchQuestionMediaTypeInBrowserTab for the class NewFilterSearchAndBrowserScreene" + e);
        }
    }
    /*@AfterMethod
    public void tearDown() throws Exception
    {
        driver.quit();
    }*/
}



