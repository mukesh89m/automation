package com.snapwiz.selenium.tests.staf.learningspaces.testcases.smoke.courseContentSummary;

import com.snapwiz.selenium.tests.staf.framework.controller.Driver;
import com.snapwiz.selenium.tests.staf.framework.utility.CustomAssert;
import com.snapwiz.selenium.tests.staf.framework.utility.ReportUtil;
import com.snapwiz.selenium.tests.staf.framework.utility.WebDriverUtil;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Assignment;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.DirectLogin;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Screenshot;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.SelectCourse;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.CMS.SearchPage;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.CMS.Summary;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.Click;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 4/14/16.
 */
public class CourseContentSummary extends Driver{

    @Test(priority = 1,enabled = true)
    public void validateDifficultLevelCount(){
    try {
        WebDriver driver = Driver.getWebDriver();

        ReportUtil.log("Validate Difficult Level Count","Validating for different difficult level for questions","info");
        SearchPage searchPage = PageFactory.initElements(driver, SearchPage.class);
        Summary summary = PageFactory.initElements(driver, Summary.class);

        //Get the different difficult level before questions are being created
        new DirectLogin().CMSLogin();//CMS Login
        new SelectCourse().selectcourse();//Select 'Biology' course
        new WebDriverUtil().waitTillVisibilityOfElement(searchPage.icon_search,60);
        new Click().clickByid("deliver-course");
        Thread.sleep(5000);
        String totalNoOfQuestionsBefore = driver.findElement(By.className("highcharts-title")).getText();
        totalNoOfQuestionsBefore = totalNoOfQuestionsBefore.substring(0,totalNoOfQuestionsBefore.length()-9);
        List<Integer> beforeCountList = getBeforeCount();
        System.out.println("1 : " + beforeCountList);

        //1.'Difficult Level Count' should be selected by default
        summary.difficultyLevelCountDropdown.click();
        new WebDriverUtil().waitTillVisibilityOfElement(summary.questionTypeCountDropdown,60);
        summary.difficultyLevelCountDropdown.click();


        //Get the different difficult level after questions are being created
        new Assignment().create(3);
        new Assignment().create(4);
        new Assignment().create(5);

        new DirectLogin().CMSLogin();//CMS Login
        new SelectCourse().selectcourse();//Select Biology Course
        searchPage = PageFactory.initElements(driver, SearchPage.class);
        summary = PageFactory.initElements(driver, Summary.class);
        new WebDriverUtil().waitTillVisibilityOfElement(searchPage.icon_search,60);
        new Click().clickByid("deliver-course");
        Thread.sleep(5000);
        summary.refreshSummaryCount.click();
        Thread.sleep(90000);
        new Click().clickByid("deliver-course");
        List<Integer> afterCountList = getAfterCount();
        Thread.sleep(9000);

        //2. Total no of questions as per expected
        String totalNoOfQuestionsAfter = driver.findElement(By.className("highcharts-title")).getText();
        totalNoOfQuestionsAfter = totalNoOfQuestionsAfter.substring(0,totalNoOfQuestionsAfter.length()-9);
        if(!(Integer.parseInt(totalNoOfQuestionsAfter)>=Integer.parseInt(totalNoOfQuestionsBefore))){
            CustomAssert.fail("Total Number of Questions Count","Total no of questions is not as per expected");
        }

        //3. Total no of Medium Questions should be as per expected
        System.out.println("afterCountList : " + afterCountList);
        System.out.println("beforeCountList : " + beforeCountList);
        if(!(afterCountList.get(2)>=beforeCountList.get(2))){
            CustomAssert.fail("Medium Questions Count","Total no of 'Medium' Questions is not as per expected");
        }

        //4. Total no of Hard Questions should be as per expected
        if(!(afterCountList.get(3)>=beforeCountList.get(3))){
            CustomAssert.fail("Hard Questions Count","Total no of 'Hard' Questions is not as per expected");
        }


        //5. Total no of 'No Difficulty' Questions should be as per expected
            if(!(afterCountList.get(0)>=beforeCountList.get(0))){
                CustomAssert.fail("No Difficulty Questions Count","Total no of 'No Difficulty' Questions is not as per expected");
            }

        //6. Total no of 'Easy' Questions should be as per expected
        if(!(afterCountList.get(1)>=beforeCountList.get(1))){
            CustomAssert.fail("Easy Questions Count","Total no of 'Easy' Questions is not as per expected");
        }

    }catch(Exception e){
        Assert.fail("Exception in the test method 'validateDifficultLevelCount' in the class 'CourseContentSummary'", e);
    }

}

    @Test(priority = 2,enabled = true)
    public void validateBloomsTaxonomyCount(){
        try {
            ReportUtil.log("Validate Blooms Taxonomy Count","Validating Blooms Taxonomy Count for the questions","info");
            WebDriver driver = Driver.getWebDriver();
            //Get the Blooms Taxonomy count before questions are being created
            new DirectLogin().CMSLogin();//CMS Login
            new SelectCourse().selectcourse();//Select Biology Course
            SearchPage searchPage = PageFactory.initElements(driver, SearchPage.class);
            Summary summary = PageFactory.initElements(driver, Summary.class);

            new WebDriverUtil().waitTillVisibilityOfElement(searchPage.icon_search,60);
            new Click().clickByid("deliver-course");
            Thread.sleep(5000);
            summary.difficultyLevelCountDropdown.click();//Click on 'difficulty level' dropdown
            new WebDriverUtil().waitTillVisibilityOfElement(summary.questionTypeCountDropdown,60);
            summary.bloomsTaxonomyCountDropdown.click();//Select 'Blooms Taxonomy' count

            String totalNoOfQuestionsBefore = driver.findElement(By.className("highcharts-title")).getText();
            totalNoOfQuestionsBefore = totalNoOfQuestionsBefore.substring(0,totalNoOfQuestionsBefore.length()-9);
            List<Integer> beforeCountList = getBeforeCountOfBloomsTaxonomyCount();


            //Get the Blooms Taxonomy count after questions are being created
            new Assignment().create(9);
            new Assignment().create(10);
            new Assignment().create(11);
            new Assignment().create(12);
            new Assignment().create(13);
            new Assignment().create(14);

            new DirectLogin().CMSLogin();//CMS Login
            new SelectCourse().selectcourse();//Select Biology Course
            searchPage = PageFactory.initElements(driver, SearchPage.class);
            summary = PageFactory.initElements(driver, Summary.class);
            new WebDriverUtil().waitTillVisibilityOfElement(searchPage.icon_search,60);
            new Click().clickByid("deliver-course");
            Thread.sleep(5000);
            summary.refreshSummaryCount.click();
            Thread.sleep(90000);
            new Click().clickByid("deliver-course");
            Thread.sleep(5000);
            //summary.difficultyLevelCountDropdown.click();//Click on 'Difficulty level' dropdown
            new Click().clickByElement(summary.difficultyLevelCountDropdown);
            new WebDriverUtil().waitTillVisibilityOfElement(summary.questionTypeCountDropdown,60);
            summary.bloomsTaxonomyCountDropdown.click();//Click on 'blooms Taxonomy Count' dropdown
            List<Integer> afterCountList = getAfterCountOfBloomsTaxonomyCount();
            Thread.sleep(9000);

            //1. Total no of questions should be as per expected
            String totalNoOfQuestionsAfter = driver.findElement(By.className("highcharts-title")).getText();
            totalNoOfQuestionsAfter = totalNoOfQuestionsAfter.substring(0,totalNoOfQuestionsAfter.length()-9);

            if(!(Integer.parseInt(totalNoOfQuestionsAfter)>=Integer.parseInt(totalNoOfQuestionsBefore))){
                CustomAssert.fail("Total Number of Questions Count","Total no of questions is not as per expected");
            }

            //2. Total no of questions of type 'Application' should be as per expected
            System.out.println("afterCountList: " +afterCountList.get(3) );
            System.out.println("beforeCountList: " +beforeCountList.get(3));

            if(!(afterCountList.get(3)>=beforeCountList.get(3))){
                CustomAssert.fail("Application Questions Count","Total no of questions of type 'Application' is not as per expected");
            }


           //3. Total no of questions of type 'Analysis' should be as per expected
            if(!(afterCountList.get(4)>=beforeCountList.get(4))){
                CustomAssert.fail("Analysis Questions Count","Total no of questions of type 'Analysis' is not as per expected");
            }


            //4. Total no of questions of type 'Synthesis' should be as per expected
            if(!(afterCountList.get(5)>=beforeCountList.get(5))){
                CustomAssert.fail("Synthesis Questions Count","Total no of questions of type 'Synthesis' is not as per expected");
            }



            //5. Total no of questions of type 'Evaluation' should be as per expected
            if(!(afterCountList.get(6)>=beforeCountList.get(6))){
                CustomAssert.fail("Evaluation Questions Count","Total no of questions of type 'Evaluation' is not as per expected");
            }


            //6. Total no of questions of type 'No Bloomcode' should be as per expected
            if(!(afterCountList.get(0)>=beforeCountList.get(0))){
                CustomAssert.fail("No Bloomcode Questions Count","Total no of questions of type 'No Bloomcode' is not as per expected");
            }


            //7. Total no of questions of type 'Knowledge' should be as per expected
            if(!(afterCountList.get(1)>=beforeCountList.get(1))){
                CustomAssert.fail("Knowledge Questions Count","Total no of questions of type 'Knowledge' is not as per expected");
            }


            //8. Total no of questions of type 'Comprehension' should be as per expected
            if(!(afterCountList.get(2)>=beforeCountList.get(2))){
                CustomAssert.fail("Comprehension Questions Count","Total no of questions of type 'Comprehension' is not as per expected");
            }

        }catch(Exception e){
            Assert.fail("Exception in the test method 'validateBloomsTaxonomyCount' in the class 'CourseContentSummary'", e);
        }
    }

    @Test(priority = 3,enabled = true)
    public void validateQuestionStatusCount(){
        try {
            ReportUtil.log("Validate Question Status Count","validating for different Statuses","info");
            WebDriver driver = Driver.getWebDriver();
            //Get the different Statuses before questions are being created
            new DirectLogin().CMSLogin();//CMS Login
            new SelectCourse().selectcourse();//Select Biology course
            SearchPage searchPage = PageFactory.initElements(driver, SearchPage.class);
            Summary summary = PageFactory.initElements(driver, Summary.class);

            new WebDriverUtil().waitTillVisibilityOfElement(searchPage.icon_search,60);
            new Click().clickByid("deliver-course");
            Thread.sleep(5000);
            summary.difficultyLevelCountDropdown.click();//Select difficulty level count dropdown
            new WebDriverUtil().waitTillVisibilityOfElement(summary.questionTypeCountDropdown,60);
            summary.questionStatusCountDropdown.click();//select question status count dropdown
            String totalNoOfQuestionsBefore = driver.findElement(By.className("highcharts-title")).getText();
            totalNoOfQuestionsBefore = totalNoOfQuestionsBefore.substring(0,totalNoOfQuestionsBefore.length()-9);
            List<Integer> beforeCountList = getBeforeQuestionStatusCount();
            new Assignment().create(37);
            new Assignment().create(38);
            new Assignment().create(39);
            new Assignment().create(40);
            new Assignment().create(41);

            //Get the different Statuses after questions are being created
            new DirectLogin().CMSLogin();//CMS Login
            new SelectCourse().selectcourse();//Select Biology course
            searchPage = PageFactory.initElements(driver, SearchPage.class);
            summary = PageFactory.initElements(driver, Summary.class);
            new WebDriverUtil().waitTillVisibilityOfElement(searchPage.icon_search,60);
            new Click().clickByid("deliver-course");
            Thread.sleep(5000);
            summary.refreshSummaryCount.click();
            Thread.sleep(90000);
            new Click().clickByid("deliver-course");
            Thread.sleep(5000);
            summary.difficultyLevelCountDropdown.click();//Select difficulty level count dropdown
            new WebDriverUtil().waitTillVisibilityOfElement(summary.questionTypeCountDropdown,60);
            summary.questionStatusCountDropdown.click();//select question status count dropdown
            List<Integer> afterCountList = getAfterQuestionStatusCount();
            Thread.sleep(9000);
            System.out.println("beforeCountList : " + beforeCountList);
            System.out.println("afterCountList : " + afterCountList);
            if(afterCountList.equals(beforeCountList)){
                System.out.println("Two Lists are equal");
            }



            //1. Total no of questions should be as per expected
            String totalNoOfQuestionsAfter = driver.findElement(By.className("highcharts-title")).getText();
            totalNoOfQuestionsAfter = totalNoOfQuestionsAfter.substring(0,totalNoOfQuestionsAfter.length()-9);

            if(Integer.parseInt(totalNoOfQuestionsBefore)>=Integer.parseInt(totalNoOfQuestionsAfter)){
                CustomAssert.fail("Total Number of Questions Count","Total no of questions is not as per expected");
            }

            //1.Total no of questions of status 'Drafts' should be as per expected
            if(!(afterCountList.get(0)>=beforeCountList.get(0))){
                CustomAssert.fail("Drafts Questions Count","Total no of questions of type 'Drafts' is not as per expected");
            }



            //2.Total no of questions of status 'Needs Revision' should be as per expected



            //3.Total no of questions of status 'Approve' should be as per expected


            //4.Total no of questions of status 'Ready to Publish' should be as per expected


            //5.Total no of questions of status 'Publish' should be as per expected
            if(!(afterCountList.get(4)>=beforeCountList.get(4))){
                CustomAssert.fail("Publish Questions Count","Total no of questions of type 'Publish' is not as per expected");
            }

        }catch(Exception e){
            Assert.fail("Exception in the test method 'validateQuestionStatusCount' in the class 'CourseContentSummary'", e);
        }
    }



    @Test(priority = 4,enabled = true)
    public void validateQuestionTypeCount(){
        try {
            WebDriver driver = Driver.getWebDriver();
            ReportUtil.log("Validate Question Type Count","validating count of all Question Types","info");
            SearchPage searchPage = PageFactory.initElements(driver, SearchPage.class);
            Summary summary = PageFactory.initElements(driver, Summary.class);
            int dataIndex = 17;

            //Get the Count of all question types before questions are being created
            new DirectLogin().CMSLogin();//cms Login
            new SelectCourse().selectcourse();//Select 'Biology' Course
            new WebDriverUtil().waitTillVisibilityOfElement(searchPage.icon_search,60);
            new Click().clickByid("deliver-course");
            Thread.sleep(5000);
            summary.difficultyLevelCountDropdown.click();//Select Difficulty Level
            new WebDriverUtil().waitTillVisibilityOfElement(summary.questionTypeCountDropdown,60);
            summary.questionTypeCountDropdown.click();//Select Question Type Count
            new Screenshot().captureScreenshotFromTestCase();
            List<Integer> beforeCountList = getBeforeQuestionTypeCount();

            //Create all types of questions
            new Assignment().create(dataIndex);
            new Assignment().addQuestions(dataIndex, "essay", "");
            //new Assignment().addQuestions(dataIndex, "multipart", "");
            new Assignment().addQuestions(dataIndex, "multipleselection", "");
            new Assignment().addQuestions(dataIndex, "textdropdown", "");
            new Assignment().addQuestions(dataIndex, "multiplechoice", "");
            new Assignment().addQuestions(dataIndex, "numericentrywithunits", "");
            new Assignment().addQuestions(dataIndex, "textentry", "");
            new Assignment().addQuestions(dataIndex, "writeboard", "");

            //Get the Count of all question types after questions are being created
            new DirectLogin().CMSLogin();//CMS Login
            new SelectCourse().selectcourse();//Select Course
            searchPage = PageFactory.initElements(driver, SearchPage.class);
            summary = PageFactory.initElements(driver, Summary.class);
            new WebDriverUtil().waitTillVisibilityOfElement(searchPage.icon_search,60);
            new Click().clickByid("deliver-course");
            Thread.sleep(5000);
            summary.refreshSummaryCount.click();
            Thread.sleep(90000);
            new Click().clickByid("deliver-course");
            Thread.sleep(5000);
            WebDriverUtil.clickOnElementUsingJavascript(summary.difficultyLevelCountDropdown);//Click on difficulty level dropdown
            new WebDriverUtil().waitTillVisibilityOfElement(summary.questionTypeCountDropdown,60);//click on question type count dropdown
            List<Integer> afterCountList = getAfterQuestionTypeCount();
            Thread.sleep(9000);
            if(afterCountList.equals(beforeCountList)){
                System.out.println("Two Lists are equal");
            }

            //1.Total no of questions of type 'Essay Type' should be as per expected
            if(!(afterCountList.get(0)>=beforeCountList.get(0))){
                CustomAssert.fail("Drafts Questions Count","Total no of questions of type 'Essay Type' is not as per expected");
            }


            //1.Total no of questi  ons of type 'Expression Evaluator' should be as per expected
            if(!(afterCountList.get(1)>=beforeCountList.get(1))){
                CustomAssert.fail("Drafts Questions Count","Total no of questions of type 'Expression' is not as per expected");
            }


            //2.Total no of questions of type 'Multi Part' should be as per expected
            if(!(afterCountList.get(2)>=beforeCountList.get(2))){
                CustomAssert.fail("Drafts Questions Count","Total no of questions of type 'Multi Part' is not as per expected");
            }

            //9.Total no of questions of type 'Multiple Choice' should be as per expected
            if(!(afterCountList.get(3)>=beforeCountList.get(3))){
                CustomAssert.fail("Drafts Questions Count","Total no of questions of type 'Multiple Choice' is not as per expected");
            }

            //3.Total no of questions of type 'Multiple Selection' should be as per expected
            if(!(afterCountList.get(4)>=beforeCountList.get(4))){
                CustomAssert.fail("Drafts Questions Count","Total no of questions of type 'Multiple Selection' is not as per expected");
            }

            //10.Total no of questions of type 'Numeric Entry' should be as per expected
            if(!(afterCountList.get(5)>=beforeCountList.get(5))){
                CustomAssert.fail("Drafts Questions Count","Total no of questions of type 'Numeric Entry' is not as per expected");
            }

            //4.Total no of questions of type 'Numeric Entry w/units' should be as per expected
            if(!(afterCountList.get(6)>=beforeCountList.get(6))){
                CustomAssert.fail("Drafts Questions Count","Total no of questions of type 'Numeric Entry w/units' is not as per expected");
            }

            //11.Total no of questions of type 'Text Drop Down' should be as per expected
            if(!(afterCountList.get(7)>=beforeCountList.get(7))){
                CustomAssert.fail("Drafts Questions Count","Total no of questions of type 'Text Drop Down' is not as per expected");
            }


            //5.Total no of questions of type 'Text Entry' should be as per expected
            if(!(afterCountList.get(8)>=beforeCountList.get(8))){
                CustomAssert.fail("Drafts Questions Count","Total no of questions of type 'Text Entry' is not as per expected");
            }


            //12.Total no of questions of type 'True / False' should be as per expected
            if(!(afterCountList.get(9)>=beforeCountList.get(9))){
                CustomAssert.fail("Drafts Questions Count","Total no of questions of type 'True / False' is not as per expected");
            }


            //6.Total no of questions of type 'Workboard' should be as per expected
            if(!(afterCountList.get(10)>=beforeCountList.get(10))){
                CustomAssert.fail("Drafts Questions Count","Total no of questions of type 'Workboard' is not as per expected");
            }


        }catch(Exception e){
            Assert.fail("Exception in the test method 'validateQuestionTypeCount' in the class 'CourseContentSummary'", e);
        }
    }

    public ArrayList getBeforeCount(){
        ArrayList<Integer> aList = new ArrayList<>();
        WebDriver driver = Driver.getWebDriver();
        try{
            List<WebElement> gElementsList = driver.findElement(By.className("highcharts-data-labels")).findElements(By.tagName("g"));
            Thread.sleep(2000);
            String noDifficulty  = gElementsList.get(0).getText();
            String easy  = gElementsList.get(1).getText();
            String medium  = gElementsList.get(2).getText();
            String hard  = gElementsList.get(3).getText();

            noDifficulty = noDifficulty.substring(0,noDifficulty.length()-13);
            easy = easy.substring(0, easy.length() - 4);
            medium = medium.substring(0,medium.length()-6);
            hard = hard.substring(0,hard.length()-4);
            Thread.sleep(5000);
            aList.add(Integer.parseInt(noDifficulty));
            aList.add(Integer.parseInt(easy) + 1);
            aList.add(Integer.parseInt(medium)+1);
            aList.add(Integer.parseInt(hard)+1);
        }catch(Exception e){
            Assert.fail("Exception in the method 'getBeforeCount' in the class 'CourseContentSummary'", e);
        }
        return  aList;
    }


    public ArrayList getAfterCount(){
        ArrayList<Integer> aList = new ArrayList<>();
        WebDriver driver = Driver.getWebDriver();
        try{

            List<WebElement> gElementsList = driver.findElement(By.className("highcharts-data-labels")).findElements(By.tagName("g"));
            Thread.sleep(2000);
            String noDifficulty  = gElementsList.get(0).getText();
            String easy  = gElementsList.get(1).getText();
            String medium  = gElementsList.get(2).getText();
            String hard  = gElementsList.get(3).getText();

            noDifficulty = noDifficulty.substring(0,noDifficulty.length()-13);
            easy = easy.substring(0, easy.length() - 4);
            medium = medium.substring(0,medium.length()-6);
            hard = hard.substring(0,hard.length()-4);
            Thread.sleep(5000);

            aList.add(Integer.parseInt(noDifficulty));
            aList.add(Integer.parseInt(easy));
            aList.add(Integer.parseInt(medium));
            aList.add(Integer.parseInt(hard));
        }catch(Exception e){
            Assert.fail("Exception in the method 'getAfterCount' in the class 'CourseContentSummary'", e);
        }
        return aList;
    }


    public ArrayList getBeforeCountOfBloomsTaxonomyCount(){
        ArrayList<Integer> aList = new ArrayList<>();
        WebDriver driver = Driver.getWebDriver();
        try{
            List<WebElement> gElementsList = driver.findElement(By.className("highcharts-data-labels")).findElements(By.tagName("g"));
            Thread.sleep(2000);
            String noBloomCode  = gElementsList.get(0).getText();
            String knowledge  = gElementsList.get(1).getText();
            String comprehension  = gElementsList.get(2).getText();
            String application  = gElementsList.get(3).getText();
            String analysis  = gElementsList.get(4).getText();
            String synthesis  = gElementsList.get(5).getText();
            String evaluation  = gElementsList.get(6).getText();

            noBloomCode = noBloomCode.substring(0,noBloomCode.length()-12);
            knowledge = knowledge.substring(0, knowledge.length() - 9);
            comprehension = comprehension.substring(0,comprehension.length()-13);
            application = application.substring(0,application.length()-11);
            analysis = analysis.substring(0,analysis.length()-8);
            synthesis = synthesis.substring(0,synthesis.length()-9);
            evaluation = evaluation.substring(0,evaluation.length()-10);
            Thread.sleep(5000);

            aList.add(Integer.parseInt(noBloomCode));
            aList.add(Integer.parseInt(knowledge) );
            aList.add(Integer.parseInt(comprehension));
            aList.add(Integer.parseInt(application));
            aList.add(Integer.parseInt(analysis));
            aList.add(Integer.parseInt(synthesis));
            aList.add(Integer.parseInt(evaluation));
        }catch(Exception e){
            Assert.fail("Exception in the method 'getBeforeCountOfBloomsTaxonomyCount' in the class 'CourseContentSummary'", e);
        }
        return  aList;
    }



    public ArrayList getBeforeQuestionStatusCount(){
        ArrayList<Integer> aList = new ArrayList<>();
        WebDriver driver = Driver.getWebDriver();
        try{
            List<WebElement> gElementsList = driver.findElement(By.className("highcharts-data-labels")).findElements(By.tagName("g"));
            Thread.sleep(2000);
            for (int a=0;a<gElementsList.size();a++){
                System.out.println("gElementsList : " + gElementsList.get(a).getText());
                /*if(!(gElementsList.get(a).getText().contains(" ") || gElementsList.get(a).getText()==null)){
                    gElementsList1.add(gElementsList.get(a));
                }*/
            }

            String drafts  = gElementsList.get(0).getText();
            String needsRevision  = gElementsList.get(4).getText();
            String approve  = gElementsList.get(5).getText();
            String readyToPublish  = gElementsList.get(6).getText();
            String publish  = gElementsList.get(7).getText();

            drafts = drafts.substring(0,drafts.length()-6);
            System.out.println("needsRevision : " + needsRevision);
            needsRevision = needsRevision.substring(0, needsRevision.length() - 14);
            approve = approve.substring(0,approve.length()-7);
            readyToPublish = readyToPublish.substring(0,readyToPublish.length()-16);
            publish = publish.substring(0,publish.length()-7);
            Thread.sleep(5000);

            aList.add(Integer.parseInt(drafts)+1);
            aList.add(Integer.parseInt(needsRevision) + 1);
            aList.add(Integer.parseInt(approve) + 1);
            aList.add(Integer.parseInt(readyToPublish) + 1);
            aList.add(Integer.parseInt(publish) + 1);
        }catch(Exception e){
            Assert.fail("Exception in the method 'getBeforeQuestionStatusCount' in the class 'CourseContentSummary'", e);
        }
        return  aList;

    }



    public ArrayList getAfterQuestionStatusCount(){
        ArrayList<Integer> aList = new ArrayList<>();
        WebDriver driver = Driver.getWebDriver();
        try{
            List<WebElement> gElementsList = driver.findElement(By.className("highcharts-data-labels")).findElements(By.tagName("g"));
            Thread.sleep(2000);
            String drafts  = gElementsList.get(0).getText();
            String needsRevision  = gElementsList.get(4).getText();
            String approve  = gElementsList.get(5).getText();
            String readyToPublish  = gElementsList.get(6).getText();
            String publish  = gElementsList.get(7).getText();

            drafts = drafts.substring(0,drafts.length()-6);
            System.out.println("needsRevision : " + needsRevision);
            needsRevision = needsRevision.substring(0, needsRevision.length() - 14);
            approve = approve.substring(0,approve.length()-7);
            readyToPublish = readyToPublish.substring(0,readyToPublish.length()-16);
            publish = publish.substring(0,publish.length()-7);
            Thread.sleep(5000);
            Thread.sleep(5000);

            aList.add(Integer.parseInt(drafts));
            aList.add(Integer.parseInt(needsRevision));
            aList.add(Integer.parseInt(approve));
            aList.add(Integer.parseInt(readyToPublish));
            aList.add(Integer.parseInt(publish));
        }catch(Exception e){
            Assert.fail("Exception in the method 'getAfterQuestionStatusCount' in the class 'CourseContentSummary'", e);
        }
        return  aList;

    }


    public ArrayList getBeforeQuestionTypeCount(){
        ArrayList<Integer> aList = new ArrayList<>();
        WebDriver driver = Driver.getWebDriver();
        try{
            List<WebElement> gElementsList = driver.findElements(By.className("cms-QuestionsTotal-count"));
            Thread.sleep(2000);
            String essayType  = gElementsList.get(0).getText();
            String expressionEvaluator  = gElementsList.get(1).getText();
            String multiPart  = gElementsList.get(2).getText();
            String multipleChoice  = gElementsList.get(3).getText();
            String multipleSelection  = gElementsList.get(4).getText();
            String numericEntry  = gElementsList.get(5).getText();
            String numericEntryWithUnits  = gElementsList.get(6).getText();
            String textDropdown  = gElementsList.get(7).getText();
            String textentry  = gElementsList.get(8).getText();
            String trueFalse  = gElementsList.get(9).getText();
            String workboard  = gElementsList.get(10).getText();

            aList.add(Integer.parseInt(essayType));
            aList.add(Integer.parseInt(expressionEvaluator));
            aList.add(Integer.parseInt(multiPart));
            aList.add(Integer.parseInt(multipleChoice));
            aList.add(Integer.parseInt(multipleSelection));
            aList.add(Integer.parseInt(numericEntry));
            aList.add(Integer.parseInt(numericEntryWithUnits));
            aList.add(Integer.parseInt(textDropdown));
            aList.add(Integer.parseInt(textentry));
            aList.add(Integer.parseInt(trueFalse));
            aList.add(Integer.parseInt(workboard));
        }catch(Exception e){
            Assert.fail("Exception in the method 'getBeforeQuestionTypeCount' in the class 'CourseContentSummary'", e);
        }
        return  aList;

    }

    public ArrayList getAfterQuestionTypeCount(){
        ArrayList<Integer> aList = new ArrayList<>();
        WebDriver driver = Driver.getWebDriver();

        try{
            List<WebElement> gElementsList = driver.findElements(By.className("cms-QuestionsTotal-count"));

            Thread.sleep(2000);
            String essayType  = gElementsList.get(0).getText();
            String expressionEvaluator  = gElementsList.get(1).getText();
            String multiPart  = gElementsList.get(2).getText();
            String multipleChoice  = gElementsList.get(3).getText();
            String multipleSelection  = gElementsList.get(4).getText();
            String numericEntry  = gElementsList.get(5).getText();
            String numericEntryWithUnits  = gElementsList.get(6).getText();
            String textDropdown  = gElementsList.get(7).getText();
            String textentry  = gElementsList.get(8).getText();
            String trueFalse  = gElementsList.get(9).getText();
            String workboard  = gElementsList.get(10).getText();

            aList.add(Integer.parseInt(essayType));
            aList.add(Integer.parseInt(expressionEvaluator));
            aList.add(Integer.parseInt(multiPart));
            aList.add(Integer.parseInt(multipleChoice));
            aList.add(Integer.parseInt(multipleSelection));
            aList.add(Integer.parseInt(numericEntry));
            aList.add(Integer.parseInt(numericEntryWithUnits));
            aList.add(Integer.parseInt(textDropdown));
            aList.add(Integer.parseInt(textentry));
            aList.add(Integer.parseInt(trueFalse));
            aList.add(Integer.parseInt(workboard));

        }catch(Exception e){
            Assert.fail("Exception in the method 'getAfterQuestionTypeCount' in the class 'CourseContentSummary'", e);
        }
        return  aList;

    }


    public ArrayList getAfterCountOfBloomsTaxonomyCount(){
        ArrayList<Integer> aList = new ArrayList<>();
        WebDriver driver = Driver.getWebDriver();

        try{
            List<WebElement> gElementsList = driver.findElement(By.className("highcharts-data-labels")).findElements(By.tagName("g"));
            Thread.sleep(2000);
            String noBloomCode  = gElementsList.get(0).getText();
            String knowledge  = gElementsList.get(1).getText();
            String comprehension  = gElementsList.get(2).getText();
            String application  = gElementsList.get(3).getText();
            String analysis  = gElementsList.get(4).getText();
            String synthesis  = gElementsList.get(5).getText();
            String evaluation  = gElementsList.get(6).getText();

            noBloomCode = noBloomCode.substring(0,noBloomCode.length()-12);
            knowledge = knowledge.substring(0, knowledge.length() - 9);
            comprehension = comprehension.substring(0,comprehension.length()-13);
            application = application.substring(0,application.length()-11);
            analysis = analysis.substring(0,analysis.length()-8);
            synthesis = synthesis.substring(0,synthesis.length()-9);
            evaluation = evaluation.substring(0,evaluation.length()-10);
            Thread.sleep(5000);

            aList.add(Integer.parseInt(noBloomCode));
            aList.add(Integer.parseInt(knowledge)+1);
            aList.add(Integer.parseInt(comprehension)+1);
            aList.add(Integer.parseInt(application)+1);
            aList.add(Integer.parseInt(analysis)+1);
            aList.add(Integer.parseInt(synthesis)+1);
            aList.add(Integer.parseInt(evaluation)+1);
        }catch(Exception e){
            Assert.fail("Exception in the method 'getAfterCountOfBloomsTaxonomyCount' in the class 'CourseContentSummary'", e);
        }
        return  aList;

    }
}
