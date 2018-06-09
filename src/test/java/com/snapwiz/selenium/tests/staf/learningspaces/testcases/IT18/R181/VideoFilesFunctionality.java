package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT18.R181;
import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Assignment;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.DirectLogin;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.CMS.NewQuestionDataEntry;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.CMSActions;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.BooleanValue;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Created by pragya on 28-01-2015.
 */
public class VideoFilesFunctionality extends Driver{

    @Test(priority = 1, enabled = true)
    //TC row no. - 10 : As an Author, the user should be able to add a video to a Question using Wistia embed Code
    public void embeddingVideoFilesForAllQuestionMP4() {
        try {
            int dataIndex = 18;
            String questionset = ReadTestData.readDataByTagName("", "questionset", Integer.toString(dataIndex));
            NewQuestionDataEntry newQuestionDataEntry = PageFactory.initElements(driver, NewQuestionDataEntry.class);

            new DirectLogin().CMSLogin(); //CMS login as author
            new Assignment().create(dataIndex);//Create an assignment
            new Assignment().addQuestions(18, "all", "");//Create all type of questions

            newQuestionDataEntry.jumpToQuestionSelector.click();//Click on Jump To Q#
            List<WebElement> allQuestions = newQuestionDataEntry.getList_questionsCreated_dropdown();
            newQuestionDataEntry.jumpToQuestionSelector.click();//Click on Jump To Q#

            for (int i = 1; i <= allQuestions.size() - 2; i++) {

                    new CMSActions().navigateToQuestionNo(i);
                    boolean videoFound = new BooleanValue().presenceOfElement(dataIndex,By.xpath("//div[contains(@id,'wistia-video-wrapper')]"));

                  //Expected - The inserted video code should be saved and displayed under the question section
                  Assert.assertEquals(videoFound,true,"Video is not found in question number " + i);

            }

            //Expected - Typed Question set name should be displayed in the textbox
            Assert.assertEquals(newQuestionDataEntry.getSetName_question().getAttribute("title"), questionset, "Typed question set is not displayed");

        } catch (Exception e) {
            Assert.fail("Exception in class 'VideoFilesFunctionality' in method 'embeddingVideoFilesForAllQuestionMP4'", e);
        }
    }


    @Test(priority = 2, enabled = true)
    //TC row no. - 10 : As an Author, the user should be able to add a video to a Question using Wistia embed Code
    public void embeddingVideoFilesForAllQuestionFLV() {
        try {
            int dataIndex = 63;
            String questionset = ReadTestData.readDataByTagName("", "questionset", Integer.toString(dataIndex));
            NewQuestionDataEntry newQuestionDataEntry = PageFactory.initElements(driver, NewQuestionDataEntry.class);

            new DirectLogin().CMSLogin(); //CMS login as author
            new Assignment().create(dataIndex);//Create an assignment
            new Assignment().addQuestions(63, "all", "");//Create all type of questions

            newQuestionDataEntry.jumpToQuestionSelector.click();//Click on Jump To Q#
            List<WebElement> allQuestions = newQuestionDataEntry.getList_questionsCreated_dropdown();
            newQuestionDataEntry.jumpToQuestionSelector.click();//Click on Jump To Q#


            for (int i = 1; i <= allQuestions.size() - 2; i++) {

                new CMSActions().navigateToQuestionNo(i);
                boolean videoFound = new BooleanValue().presenceOfElement(dataIndex,By.xpath("//div[contains(@id,'wistia-video-wrapper')]"));

                //Expected - The inserted video code should be saved and displayed under the question section
                Assert.assertEquals(videoFound,true,"Video is not found in question number " + i);

            }

            //Expected - Typed Question set name should be displayed in the textbox
            Assert.assertEquals(newQuestionDataEntry.getSetName_question().getAttribute("title"), questionset, "Typed question set is not displayed");

        } catch (Exception e) {
            Assert.fail("Exception in class 'VideoFilesFunctionality' in method 'embeddingVideoFilesForAllQuestionFLV'", e);
        }
    }

    @Test(priority = 3, enabled = true)
    //TC row no. - 10 : As an Author, the user should be able to add a video to a Question using Wistia embed Code
    public void embeddingVideoFilesForAllQuestionAVI() {
        try {
            int dataIndex = 99;
            String questionset = ReadTestData.readDataByTagName("", "questionset", Integer.toString(dataIndex));
            NewQuestionDataEntry newQuestionDataEntry = PageFactory.initElements(driver, NewQuestionDataEntry.class);

            new DirectLogin().CMSLogin(); //CMS login as author
            new Assignment().create(dataIndex);//Create an assignment
            new Assignment().addQuestions(99, "all", "");//Create all type of questions

            newQuestionDataEntry.jumpToQuestionSelector.click();//Click on Jump To Q#
            List<WebElement> allQuestions = newQuestionDataEntry.getList_questionsCreated_dropdown();
            newQuestionDataEntry.jumpToQuestionSelector.click();//Click on Jump To Q#


            for (int i = 1; i <= allQuestions.size() - 2; i++) {

                new CMSActions().navigateToQuestionNo(i);
                boolean videoFound = new BooleanValue().presenceOfElement(dataIndex,By.xpath("//div[contains(@id,'wistia-video-wrapper')]"));

                //Expected - The inserted video code should be saved and displayed under the question section
                Assert.assertEquals(videoFound,true,"Video is not found in question number " + i);

            }

            //Expected - Typed Question set name should be displayed in the textbox
            Assert.assertEquals(newQuestionDataEntry.getSetName_question().getAttribute("title"), questionset, "Typed question set is not displayed");

        } catch (Exception e) {
            Assert.fail("Exception in class 'VideoFilesFunctionality' in method 'embeddingVideoFilesForAllQuestionAVI'", e);
        }
    }

    @Test(priority = 4, enabled = true)
    //TC row no. - 10 : As an Author, the user should be able to add a video to a Question using Wistia embed Code
    public void embeddingVideoFilesForAllQuestion3GP() {
        try {
            int dataIndex = 135;
            String questionset = ReadTestData.readDataByTagName("", "questionset", Integer.toString(dataIndex));
            NewQuestionDataEntry newQuestionDataEntry = PageFactory.initElements(driver, NewQuestionDataEntry.class);

            new DirectLogin().CMSLogin(); //CMS login as author
            new Assignment().create(dataIndex);//Create an assignment
            new Assignment().addQuestions(135, "all", "");//Create all type of questions

            newQuestionDataEntry.jumpToQuestionSelector.click();//Click on Jump To Q#
            List<WebElement> allQuestions = newQuestionDataEntry.getList_questionsCreated_dropdown();
            newQuestionDataEntry.jumpToQuestionSelector.click();//Click on Jump To Q#


            for (int i = 1; i <= allQuestions.size() - 2; i++) {

                new CMSActions().navigateToQuestionNo(i);
                boolean videoFound = new BooleanValue().presenceOfElement(dataIndex,By.xpath("//div[contains(@id,'wistia-video-wrapper')]"));

                //Expected - The inserted video code should be saved and displayed under the question section
                Assert.assertEquals(videoFound,true,"Video is not found in question number " + i);

            }

            //Expected - Typed Question set name should be displayed in the textbox
            Assert.assertEquals(newQuestionDataEntry.getSetName_question().getAttribute("title"), questionset, "Typed question set is not displayed");

        } catch (Exception e) {
            Assert.fail("Exception in class 'VideoFilesFunctionality' in method 'embeddingVideoFilesForAllQuestion3GP'", e);
        }
    }

    @Test(priority = 5, enabled = true)
    //TC row no. - 10 : As an Author, the user should be able to add a video to a Question using Wistia embed Code
    public void embeddingVideoFilesForAllQuestionOGV() {
        try {
            int dataIndex = 171;
            String questionset = ReadTestData.readDataByTagName("", "questionset", Integer.toString(dataIndex));
            NewQuestionDataEntry newQuestionDataEntry = PageFactory.initElements(driver, NewQuestionDataEntry.class);

            new DirectLogin().CMSLogin(); //CMS login as author
            new Assignment().create(dataIndex);//Create an assignment
            new Assignment().addQuestions(171, "all", "");//Create all type of questions

            newQuestionDataEntry.jumpToQuestionSelector.click();//Click on Jump To Q#
            List<WebElement> allQuestions = newQuestionDataEntry.getList_questionsCreated_dropdown();
            newQuestionDataEntry.jumpToQuestionSelector.click();//Click on Jump To Q#


            for (int i = 1; i <= allQuestions.size() - 2; i++) {

                new CMSActions().navigateToQuestionNo(i);
                boolean videoFound = new BooleanValue().presenceOfElement(dataIndex,By.xpath("//div[contains(@id,'wistia-video-wrapper')]"));

                //Expected - The inserted video code should be saved and displayed under the question section
                Assert.assertEquals(videoFound,true,"Video is not found in question number " + i);

            }

            //Expected - Typed Question set name should be displayed in the textbox
            Assert.assertEquals(newQuestionDataEntry.getSetName_question().getAttribute("title"), questionset, "Typed question set is not displayed");

        } catch (Exception e) {
            Assert.fail("Exception in class 'VideoFilesFunctionality' in method 'embeddingVideoFilesForAllQuestionOGV'", e);
        }
    }

    @Test(priority = 6, enabled = true)
    //TC row no. - 10 : As an Author, the user should be able to add a video to a Question using Wistia embed Code
    public void embeddingVideoFilesForAllQuestionWMV() {
        try {
            int dataIndex = 207;
            String questionset = ReadTestData.readDataByTagName("", "questionset", Integer.toString(dataIndex));
            NewQuestionDataEntry newQuestionDataEntry = PageFactory.initElements(driver, NewQuestionDataEntry.class);

            new DirectLogin().CMSLogin(); //CMS login as author
            new Assignment().create(dataIndex);//Create an assignment
            new Assignment().addQuestions(207, "all", "");//Create all type of questions

            newQuestionDataEntry.jumpToQuestionSelector.click();//Click on Jump To Q#
            List<WebElement> allQuestions = newQuestionDataEntry.getList_questionsCreated_dropdown();
            newQuestionDataEntry.jumpToQuestionSelector.click();//Click on Jump To Q#

            for (int i = 1; i <= allQuestions.size() - 2; i++) {

                new CMSActions().navigateToQuestionNo(i);
                boolean videoFound = new BooleanValue().presenceOfElement(dataIndex,By.xpath("//div[contains(@id,'wistia-video-wrapper')]"));

                //Expected - The inserted video code should be saved and displayed under the question section
                Assert.assertEquals(videoFound,true,"Video is not found in question number " + i);

            }

            //Expected - Typed Question set name should be displayed in the textbox
            Assert.assertEquals(newQuestionDataEntry.getSetName_question().getAttribute("title"), questionset, "Typed question set is not displayed");

        } catch (Exception e) {
            Assert.fail("Exception in class 'VideoFilesFunctionality' in method 'embeddingVideoFilesForAllQuestionWMV'", e);
        }
    }


    @Test(priority = 7, enabled = true)
    //TC row no. - 10 : As an Author, the user should be able to add a video to a Question using Wistia embed Code
    public void embeddingVideoFilesForAllQuestionM4V() {
        try {
            int dataIndex = 292;
            String questionset = ReadTestData.readDataByTagName("", "questionset", Integer.toString(dataIndex));
            NewQuestionDataEntry newQuestionDataEntry = PageFactory.initElements(driver, NewQuestionDataEntry.class);

            new DirectLogin().CMSLogin(); //CMS login as author
            new Assignment().create(dataIndex);//Create an assignment
            new Assignment().addQuestions(292, "all", "");//Create all type of questions

            newQuestionDataEntry.jumpToQuestionSelector.click();//Click on Jump To Q#
            List<WebElement> allQuestions = newQuestionDataEntry.getList_questionsCreated_dropdown();
            newQuestionDataEntry.jumpToQuestionSelector.click();//Click on Jump To Q#


            for (int i = 1; i <= allQuestions.size() - 2; i++) {

                new CMSActions().navigateToQuestionNo(i);
                boolean videoFound = new BooleanValue().presenceOfElement(dataIndex,By.xpath("//div[contains(@id,'wistia-video-wrapper')]"));

                //Expected - The inserted video code should be saved and displayed under the question section
                Assert.assertEquals(videoFound,true,"Video is not found in question number " + i);

            }

            //Expected - Typed Question set name should be displayed in the textbox
            Assert.assertEquals(newQuestionDataEntry.getSetName_question().getAttribute("title"), questionset, "Typed question set is not displayed");

        } catch (Exception e) {
            Assert.fail("Exception in class 'VideoFilesFunctionality' in method 'embeddingVideoFilesForAllQuestionM4V'", e);
        }
    }

    @Test(priority = 8, enabled = true)
    //TC row no. - 10 : As an Author, the user should be able to add a video to a Question using Wistia embed Code
    public void embeddingVideoFilesForAllQuestionMPG() {
        try {
            int dataIndex = 382;
            String questionset = ReadTestData.readDataByTagName("", "questionset", Integer.toString(dataIndex));
            NewQuestionDataEntry newQuestionDataEntry = PageFactory.initElements(driver, NewQuestionDataEntry.class);

            new DirectLogin().CMSLogin(); //CMS login as author
            new Assignment().create(dataIndex);//Create an assignment
            new Assignment().addQuestions(382, "all", "");//Create all type of questions

            newQuestionDataEntry.jumpToQuestionSelector.click();//Click on Jump To Q#
            List<WebElement> allQuestions = newQuestionDataEntry.getList_questionsCreated_dropdown();
            newQuestionDataEntry.jumpToQuestionSelector.click();//Click on Jump To Q#

            for (int i = 1; i <= allQuestions.size() - 2; i++) {

                new CMSActions().navigateToQuestionNo(i);
                boolean videoFound = new BooleanValue().presenceOfElement(dataIndex,By.xpath("//div[contains(@id,'wistia-video-wrapper')]"));

                //Expected - The inserted video code should be saved and displayed under the question section
                Assert.assertEquals(videoFound,true,"Video is not found in question number " + i);

            }

            //Expected - Typed Question set name should be displayed in the textbox
            Assert.assertEquals(newQuestionDataEntry.getSetName_question().getAttribute("title"), questionset, "Typed question set is not displayed");

        } catch (Exception e) {
            Assert.fail("Exception in class 'VideoFilesFunctionality' in method 'embeddingVideoFilesForAllQuestionMPG'", e);
        }
    }


    @Test(priority = 9, enabled = true)
    //TC row no. - 10 : As an Author, the user should be able to add a video to a Question using Wistia embed Code
    public void embeddingVideoFilesForAllQuestionVOB() {
        try {
            int dataIndex = 425;
            String questionset = ReadTestData.readDataByTagName("", "questionset", Integer.toString(dataIndex));
            NewQuestionDataEntry newQuestionDataEntry = PageFactory.initElements(driver, NewQuestionDataEntry.class);

            new DirectLogin().CMSLogin(); //CMS login as author
            new Assignment().create(dataIndex);//Create an assignment
            new Assignment().addQuestions(425, "all", "");//Create all type of questions

            newQuestionDataEntry.jumpToQuestionSelector.click();//Click on Jump To Q#
            List<WebElement> allQuestions = newQuestionDataEntry.getList_questionsCreated_dropdown();
            newQuestionDataEntry.jumpToQuestionSelector.click();//Click on Jump To Q#

            for (int i = 1; i <= allQuestions.size() - 2; i++) {

                new CMSActions().navigateToQuestionNo(i);
                boolean videoFound = new BooleanValue().presenceOfElement(dataIndex,By.xpath("//div[contains(@id,'wistia-video-wrapper')]"));

                //Expected - The inserted video code should be saved and displayed under the question section
                Assert.assertEquals(videoFound,true,"Video is not found in question number " + i);

            }

            //Expected - Typed Question set name should be displayed in the textbox
            Assert.assertEquals(newQuestionDataEntry.getSetName_question().getAttribute("title"), questionset, "Typed question set is not displayed");

        } catch (Exception e) {
            Assert.fail("Exception in class 'VideoFilesFunctionality' in method 'embeddingVideoFilesForAllQuestionVOB'", e);
        }
    }


    @Test(priority = 10, enabled = true)
    //TC row no. - 10 : As an Author, the user should be able to add a video to a Question using Wistia embed Code
    public void embeddingVideoFilesForAllQuestionMOD() {
        try {
            int dataIndex = 470;
            String questionset = ReadTestData.readDataByTagName("", "questionset", Integer.toString(dataIndex));
            NewQuestionDataEntry newQuestionDataEntry = PageFactory.initElements(driver, NewQuestionDataEntry.class);

            new DirectLogin().CMSLogin(); //CMS login as author
            new Assignment().create(dataIndex);//Create an assignment
            new Assignment().addQuestions(470, "all", "");//Create all type of questions

            newQuestionDataEntry.jumpToQuestionSelector.click();//Click on Jump To Q#
            List<WebElement> allQuestions = newQuestionDataEntry.getList_questionsCreated_dropdown();
            newQuestionDataEntry.jumpToQuestionSelector.click();//Click on Jump To Q#


            for (int i = 1; i <= allQuestions.size() - 2; i++) {

                new CMSActions().navigateToQuestionNo(i);
                boolean videoFound = new BooleanValue().presenceOfElement(dataIndex,By.xpath("//div[contains(@id,'wistia-video-wrapper')]"));

                //Expected - The inserted video code should be saved and displayed under the question section
                Assert.assertEquals(videoFound,true,"Video is not found in question number " + i);

            }

            //Expected - Typed Question set name should be displayed in the textbox
            Assert.assertEquals(newQuestionDataEntry.getSetName_question().getAttribute("title"), questionset, "Typed question set is not displayed");

        } catch (Exception e) {
            Assert.fail("Exception in class 'VideoFilesFunctionality' in method 'embeddingVideoFilesForAllQuestionMOD'", e);
        }
    }


    @Test(priority = 11, enabled = true)
    //TC row no. - 10 : As an Author, the user should be able to add a video to a Question using Wistia embed Code
    public void embeddingVideoFilesForAllQuestion3G2() {
        try {
            int dataIndex = 515;
            String questionset = ReadTestData.readDataByTagName("", "questionset", Integer.toString(dataIndex));
            NewQuestionDataEntry newQuestionDataEntry = PageFactory.initElements(driver, NewQuestionDataEntry.class);

            new DirectLogin().CMSLogin(); //CMS login as author
            new Assignment().create(dataIndex);//Create an assignment
            new Assignment().addQuestions(515, "all", "");//Create all type of questions

            newQuestionDataEntry.jumpToQuestionSelector.click();//Click on Jump To Q#
            List<WebElement> allQuestions = newQuestionDataEntry.getList_questionsCreated_dropdown();
            newQuestionDataEntry.jumpToQuestionSelector.click();//Click on Jump To Q#


            for (int i = 1; i <= allQuestions.size() - 2; i++) {

                new CMSActions().navigateToQuestionNo(i);
                boolean videoFound = new BooleanValue().presenceOfElement(dataIndex,By.xpath("//div[contains(@id,'wistia-video-wrapper')]"));

                //Expected - The inserted video code should be saved and displayed under the question section
                Assert.assertEquals(videoFound,true,"Video is not found in question number " + i);

            }

            //Expected - Typed Question set name should be displayed in the textbox
            Assert.assertEquals(newQuestionDataEntry.getSetName_question().getAttribute("title"), questionset, "Typed question set is not displayed");

        } catch (Exception e) {
            Assert.fail("Exception in class 'VideoFilesFunctionality' in method 'embeddingVideoFilesForAllQuestion3G2'", e);
        }
    }

    @Test(priority = 12, enabled = true)
    //TC row no. - 10 : As an Author, the user should be able to add a video to a Question using Wistia embed Code
    public void embeddingVideoFilesForAllQuestionM2V() {
        try {
            int dataIndex = 559;
            String questionset = ReadTestData.readDataByTagName("", "questionset", Integer.toString(dataIndex));
            NewQuestionDataEntry newQuestionDataEntry = PageFactory.initElements(driver, NewQuestionDataEntry.class);

            new DirectLogin().CMSLogin(); //CMS login as author
            new Assignment().create(dataIndex);//Create an assignment
            new Assignment().addQuestions(559, "all", "");//Create all type of questions

            newQuestionDataEntry.jumpToQuestionSelector.click();//Click on Jump To Q#
            List<WebElement> allQuestions = newQuestionDataEntry.getList_questionsCreated_dropdown();
            newQuestionDataEntry.jumpToQuestionSelector.click();//Click on Jump To Q#


            for (int i = 1; i <= allQuestions.size() - 2; i++) {

                new CMSActions().navigateToQuestionNo(i);
                boolean videoFound = new BooleanValue().presenceOfElement(dataIndex,By.xpath("//div[contains(@id,'wistia-video-wrapper')]"));

                //Expected - The inserted video code should be saved and displayed under the question section
                Assert.assertEquals(videoFound,true,"Video is not found in question number " + i);

            }

            //Expected - Typed Question set name should be displayed in the textbox
            Assert.assertEquals(newQuestionDataEntry.getSetName_question().getAttribute("title"), questionset, "Typed question set is not displayed");

        } catch (Exception e) {
            Assert.fail("Exception in class 'VideoFilesFunctionality' in method 'embeddingVideoFilesForAllQuestionM2V'", e);
        }
    }

    @Test(priority = 13, enabled = true)
    //TC row no. - 10 : As an Author, the user should be able to add a video to a Question using Wistia embed Code
    public void embeddingVideoFilesForAllQuestionWEBM() {
        try {
            int dataIndex = 603;
            String questionset = ReadTestData.readDataByTagName("", "questionset", Integer.toString(dataIndex));
            NewQuestionDataEntry newQuestionDataEntry = PageFactory.initElements(driver, NewQuestionDataEntry.class);

            new DirectLogin().CMSLogin(); //CMS login as author
            new Assignment().create(dataIndex);//Create an assignment
            new Assignment().addQuestions(603, "all", "");//Create all type of questions

            newQuestionDataEntry.jumpToQuestionSelector.click();//Click on Jump To Q#
            List<WebElement> allQuestions = newQuestionDataEntry.getList_questionsCreated_dropdown();
            newQuestionDataEntry.jumpToQuestionSelector.click();//Click on Jump To Q#

            for (int i = 1; i <= allQuestions.size() - 2; i++) {

                new CMSActions().navigateToQuestionNo(i);
                boolean videoFound = new BooleanValue().presenceOfElement(dataIndex,By.xpath("//div[contains(@id,'wistia-video-wrapper')]"));

                //Expected - The inserted video code should be saved and displayed under the question section
                Assert.assertEquals(videoFound,true,"Video is not found in question number " + i);

            }

            //Expected - Typed Question set name should be displayed in the textbox
            Assert.assertEquals(newQuestionDataEntry.getSetName_question().getAttribute("title"), questionset, "Typed question set is not displayed");

        } catch (Exception e) {
            Assert.fail("Exception in class 'VideoFilesFunctionality' in method 'embeddingVideoFilesForAllQuestionWEBM'", e);
        }
    }

        /*@Test(priority = 14,enabled = true)
        //TC row no. - 21 : As an Author, the user should be able to add multiple video and save to a Question using Wistia embed Code
        public void embeddingMultipleVideoFilesAllFormats(){
            try{
                Driver.startDriver();
                int dataIndex = 646;
                String questionset = ReadTestData.readDataByTagName("", "questionset", Integer.toString(dataIndex));
                NewQuestionDataEntry newQuestionDataEntry = PageFactory.initElements(driver,NewQuestionDataEntry.class);

                new DirectLogin().CMSLogin(); //CMS login as author
                new Assignment().create(dataIndex);//Create an assignment
                new Assignment().addQuestions(dataIndex,"all","");//Create all type of questions

                newQuestionDataEntry.jumpToQuestionSelector.click();//Click on Jump To Q#
                List<WebElement> allQuestions = newQuestionDataEntry.getList_questionsCreated_dropdown();
                newQuestionDataEntry.jumpToQuestionSelector.click();//Click on Jump To Q#

                for(int i =1;i<=allQuestions.size()-1;i++) {
                    try {
                        new CMSActions().navigateToQuestionNo(i);
                        Thread.sleep(2000);
                        if(newQuestionDataEntry.getList_videoInQuestion().size()!=13){
                            Assert.fail("Multiple videos are not added in a question");
                        }
                    } catch (Exception e1) {
                        //Expected - The inserted video code should be saved and displayed under the question section
                        Assert.fail("Video is not found in question number " + i, e1);
                    }
                }
            }catch(Exception e){
                Assert.fail("Exception in class 'VideoFilesFunctionality' in method 'embeddingMultipleVideoFilesAllFormats'", e);
            }
        }
*/

    @Test(priority = 15, enabled = true)
    //TC row no. - As an Author, the user should be able to add a video to a Question using video upload functionality
    public void embeddingVideoFilesForAllQuestionUploadMP4() {
        try {
            int dataIndex = 667;
            String questionset = ReadTestData.readDataByTagName("", "questionset", Integer.toString(dataIndex));
            NewQuestionDataEntry newQuestionDataEntry = PageFactory.initElements(driver,NewQuestionDataEntry.class);

            new DirectLogin().CMSLogin(); //CMS login as author
            new Assignment().create(dataIndex);//Create an assignment
            new Assignment().addQuestions(dataIndex,"all","");//Create all type of questions
            newQuestionDataEntry.jumpToQuestionSelector.click();//Click on Jump To Q#
            List<WebElement> allQuestions = newQuestionDataEntry.getList_questionsCreated_dropdown();
            newQuestionDataEntry.jumpToQuestionSelector.click();//Click on Jump To Q#

            for (int i = 1; i <= allQuestions.size() - 2; i++) {

                new CMSActions().navigateToQuestionNo(i);
                boolean videoFound = new BooleanValue().presenceOfElement(dataIndex,By.xpath("//div[contains(@id,'wistia-video-wrapper')]"));

                //Expected - The inserted video code should be saved and displayed under the question section
                Assert.assertEquals(videoFound,true,"Video is not found in question number " + i);

            }

            //Expected - Typed Question set name should be displayed in the textbox
            Assert.assertEquals(newQuestionDataEntry.getSetName_question().getAttribute("title"), questionset, "Typed question set is not displayed");

        } catch (Exception e) {
       Assert.fail("Exception in class 'VideoFilesFunctionality' in method 'embeddingVideoFilesForAllQuestionUploadMP4'", e);
        }

    }



}