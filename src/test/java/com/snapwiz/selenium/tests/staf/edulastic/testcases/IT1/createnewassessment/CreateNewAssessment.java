package com.snapwiz.selenium.tests.staf.edulastic.testcases.IT1.createnewassessment;

import com.snapwiz.selenium.tests.staf.edulastic.Driver;
import com.snapwiz.selenium.tests.staf.edulastic.ReadTestData;
import com.snapwiz.selenium.tests.staf.edulastic.apphelpers.Login;
import com.snapwiz.selenium.tests.staf.edulastic.apphelpers.Navigator;
import com.snapwiz.selenium.tests.staf.edulastic.uihelper.Click;
import com.snapwiz.selenium.tests.staf.edulastic.uihelper.TextFetch;
import com.snapwiz.selenium.tests.staf.edulastic.uihelper.TextSend;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Created by priyanka on 12/16/2014.
 */
public class CreateNewAssessment extends Driver{
    @Test(priority = 1,enabled = true)
    public void createNewAssessment() {
        try {
            //tc row no 109
            //"1. Login as DA  2.Select 'Assessments' from the navigator dropdown 3.Click on '+New assessment' 4.Click on ""Create new assessment"" "
            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", "110");

            String email = "daadmin@snapwiz.com";
            int index = 110;
            new Login().loginAsDA(index, email);//login as DA
            new Navigator().navigateTo("districtAssessments");//select assessment
            new Click().clickBycssselector("a[class='btn btn-blue btn-rounded']");//Click on Create New Assignment
            new TextSend().textsendbyid(assessmentname,"new-assessment-name");//Enter the assessment name
            new Click().clickByid("create-assessment-with-val");//Click on create
            String str = driver.findElement(By.cssSelector("span[class='lsm-add-questions-title step-labels']")).getText();
            System.out.println("str" +str);
            if (!str.contains("Step 1 of 3Select or Create questions")) {
                Assert.fail("Step 1 of 3:Select questions is not visible");
            }
            new Click().clickBycssselector("span[class='lsm-create-btn lsm-elo-create-btn btn sp-link']");//click on create
            new Click().clickByclassname("create-question-back-button");//click on back
            Select selectGrade=new Select(driver.findElement(By.cssSelector("div#lsm-createAssignment-grade-dropDown >select")));
            selectGrade.selectByVisibleText("Grade 9");//Select grade 9
            Thread.sleep(2000);

            new Click().clickByXpath("(//span[@class='lsm-auto-assign-btn btn btn-blue btn-outline btn-rounded'])[1]");//click on auto select
            new Click().clickBycssselector("span[class='as-modal-sprite-img as-close-modal close-question-notification']");//click on the x icon
            Select select = new Select(driver.findElement(By.xpath("./*//*[@id='lsm-createAssignment-grade-dropDown']/select")));
            select.selectByIndex(1); //select grade1
            new Click().clickByXpath("//input[@id='three']");
            new Click().clickByXpath("//span[@class='lsm-select-btn lsm-select-tlo-questions-btn btn btn-blue btn-outline btn-rounded']");//click on select
            driver.findElement(By.xpath("//input[@class='lsm-createAssignment-input-name form-control']")).clear();
            driver.switchTo().activeElement().sendKeys("New Assessment");
            select.selectByIndex(1); //select grade1
            new Click().clickByXpath("//*[@id='one']");//click on owner
            new Click().clickByXpath("//*[@id='lsm-createAssignment-grade-dropDown']");//click on choose standards
        } catch (Exception e) {
            Assert.fail("Exception in test case createNewAssessment of class createNewAssessment",e);
        }
    }
    @Test(priority = 2,enabled = true)
         public void createBesideElo(){
            try {
                //tc row no 115,124
                //"1. Login as DA  2.Select 'Assessments' from the navigator dropdown 3.Click on '+New assessment' 4.Click on ""Create new assessment"" 5.Click on ""create"" beside any ELO"
                String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", "115");

                String email = "daadmin@snapwiz.com";
                int index = 115;
                new Login().loginAsDA(index, email);//log in as DA
                new Navigator().navigateTo("districtAssessments");//select assessment
                new Click().clickBycssselector("a[class='btn btn-blue btn-rounded']");//Click on Create New Assignment
                new TextSend().textsendbyid(assessmentname,"new-assessment-name");//Enter the assessment name
                new Click().clickByid("create-assessment-with-val");//Click on create
                new Click().clickBycssselector("span[class='lsm-create-btn lsm-elo-create-btn btn sp-link']");//Click on create
                new Click().clickByid("qtn-multiple-choice-type");//click on multiple choice question
                new Click().clickByclassname("as-question-editor-back");//click on back
                new Click().clickByid("qtn-multiple-selection-type");//click on multiple selection question
                new Click().clickByclassname("as-question-editor-back");//click on back
                new Click().clickByid("qtn-text-drop-down-type");//click on text drop down question
                new Click().clickByclassname("as-question-editor-back");//click on back
                new Click().clickByid("qtn-essay-type");//click on essay type question
                new Click().clickByclassname("as-question-editor-back");//click on back
                new Click().clickByid("qtn-text-entry-type");//click on text dropdown
                new Click().clickByclassname("as-question-editor-back");//click on back
                new Click().clickByid("qtn-true-false-type");//click on true false question type
                new Click().clickByclassname("as-question-editor-back");//click on back

                new Click().clickbylinkText("Math Tech Enhanced");//click on Math Tech Enhanced tab
                new Click().clickByid("qtn-maple-numeric-type");//click on numeric entry question type
                new Click().clickByclassname("as-question-editor-back");//click on back
                new Click().clickbylinkText("Math Tech Enhanced");//click on Math Tech Enhanced tab
                new Click().clickByid("qtn-math-symbolic-notation-type");//click on expression evaluator question type
                new Click().clickByclassname("as-question-editor-back");//click on back
                new Click().clickbylinkText("Math Tech Enhanced");//click on Math Tech Enhanced tab
                new Click().clickByid("qtn-fraction-editor-type");//Click on fraction editor question type
                new Click().clickByclassname("as-question-editor-back");//click on back
                new Click().clickbylinkText("Math Tech Enhanced");//click on Math Tech Enhanced tab
                new Click().clickByid("qtn-classification-type");//Click on classification question type
                new Click().clickByclassname("as-question-editor-back");//click on back
                new Click().clickbylinkText("Math Tech Enhanced");//click on Math Tech Enhanced tab
                new Click().clickByid("qtn-graphing-type");//Click on graphing question type
                new Click().clickByclassname("as-question-editor-back");//click on back
                new Click().clickbylinkText("Math Tech Enhanced");//click on Math Tech Enhanced tab
                new Click().clickByid("qtn-graph-placement-type");//Click on graph placement question type
                new Click().clickByclassname("as-question-editor-back");//click on back
                new Click().clickbylinkText("Math Tech Enhanced");//click on Math Tech Enhanced tab
                new Click().clickByid("qtn-matching-tables-type");//Click on matching tables question type
                new Click().clickByclassname("as-question-editor-back");//click on back
                new Click().clickbylinkText("Math Tech Enhanced");//click on Math Tech Enhanced tab
                new Click().clickByid("qtn-pictograph-type");//Click on pictograph question type
                new Click().clickByclassname("as-question-editor-back");//click on back
                new Click().clickbylinkText("Math Tech Enhanced");//click on Math Tech Enhanced tab
                new Click().clickByid("qtn-dnd-type");//Click on drag and drop question type
                new Click().clickByclassname("as-question-editor-back");//click on back
                new Click().clickbylinkText("Math Tech Enhanced");//click on Math Tech Enhanced tab
                new Click().clickByid("qtn-hybrid-type");//Click on multipart question type
                new Click().clickByclassname("as-question-editor-back");//click on back
                new Click().clickbylinkText("Math Tech Enhanced");//click on Math Tech Enhanced tab
                new Click().clickByid("qtn-number-line-type");//Click on Numberline question type
                new Click().clickByclassname("as-question-editor-back");//click on back
                new Click().clickbylinkText("Math Tech Enhanced");//click on Math Tech Enhanced tab
                new Click().clickByid("qtn-number-line-range");//Click on range plotter question type
                new Click().clickByclassname("as-question-editor-back");//click on back
                new Click().clickbylinkText("Math Tech Enhanced");//click on Math Tech Enhanced tab new Click().clickByid("qtn-number-line-type");//Click on Numberline question type
                new Click().clickByid("qtn-line-plot-type");//Click on line plot question type
                new Click().clickByclassname("as-question-editor-back");//click on back
                new Click().clickbylinkText("Math Tech Enhanced");//click on Math Tech Enhanced tab
                new Click().clickByid("qtn-passage-type");//Click on passage based question type
                new Click().clickByclassname("as-question-editor-back");//click on back
                new Click().clickbylinkText("Math Tech Enhanced");//click on Math Tech Enhanced tab
                new Click().clickByid("qtn-number-line-type");//Click on label an image(text) question type
                new Click().clickByclassname("as-question-editor-back");//click on back
                new Click().clickbylinkText("Math Tech Enhanced");//click on Math Tech Enhanced tab
                new Click().clickByid("qtn-lbl-dropdown-type");//Click on label an image(dropdown) question type
                new Click().clickByclassname("as-question-editor-back");//click on back
                new Click().clickbylinkText("Math Tech Enhanced");//click on Math Tech Enhanced tab
                new Click().clickByid("qtn-resequence-type");//Click on resequence question type
                new Click().clickByclassname("as-question-editor-back");//click on back
                new Click().clickbylinkText("Math Tech Enhanced");//click on Math Tech Enhanced tab
                new Click().clickByid("qtn-numeric-entry-units-type");//Click on Numeric entry with units question type
                new Click().clickByclassname("as-question-editor-back");//click on back
                new Click().clickbylinkText("Math Tech Enhanced");//click on Math Tech Enhanced tab
                new Click().clickByid("qtn-cf-type");//Click on cloze(formula)question type
                new Click().clickByclassname("as-question-editor-back");//click on back
                new Click().clickbylinkText("Math Tech Enhanced");//click on Math Tech Enhanced tab
                new Click().clickByid("qtn-cloze-matrix-type");//Click on cloze Matrix question type
                new Click().clickByclassname("as-question-editor-back");//click on back
                new Click().clickbylinkText("ELA Tech Enhanced");//Click on ELA Tech Enhanced tab
                new Click().clickByXpath("(//div[@id='qtn-passage-type'])[2]");//Click on passage based question type
                new Click().clickByclassname("as-question-editor-back");//click on back
                new Click().clickbylinkText("ELA Tech Enhanced");//Click on ELA Tech Enhanced tab
                new Click().clickByid("qtn-sentence-type");//Click on Sentence Response question type
                new Click().clickByclassname("as-question-editor-back");//click on back
                new Click().clickbylinkText("ELA Tech Enhanced");//Click on ELA Tech Enhanced tab
                new Click().clickByid("qtn-sentence-correction-type");//Click on Sentence Correction question type
                new Click().clickByclassname("as-question-editor-back");//click on back
                new Click().clickbylinkText("ELA Tech Enhanced");//Click on ELA Tech Enhanced tab
                new Click().clickByXpath("(//div[@id='qtn-dnd-type'])[2]");//Click on drag and drop question type
                new Click().clickByclassname("as-question-editor-back");//click on back
                new Click().clickbylinkText("ELA Tech Enhanced");//Click on ELA Tech Enhanced tab
                new Click().clickByXpath("(//div[@id='qtn-matching-tables-type'])[2]");//Click on Matching Tables question type
                new Click().clickByclassname("as-question-editor-back");//click on back
                new Click().clickbylinkText("ELA Tech Enhanced");//Click on ELA Tech Enhanced tab
                new Click().clickByXpath("(//div[@id='qtn-classification-type'])[2]");//Click on Classification question type
                new Click().clickByclassname("as-question-editor-back");//click on back
                new Click().clickbylinkText("ELA Tech Enhanced");//Click on ELA Tech Enhanced tab
                new Click().clickByid("qtn-mtf-type");//Click on match the following question type
                new Click().clickByclassname("as-question-editor-back");//click on back
                new Click().clickbylinkText("ELA Tech Enhanced");//Click on ELA Tech Enhanced tab
                new Click().clickByXpath("(//div[@id='qtn-resequence-type'])[2]");//Click on Resequence question type
                new Click().clickByclassname("as-question-editor-back");//click on back

                new Click().clickByid("qtn-true-false-type");//click on truefalse
                new TextSend().textsendbyid("truefalse","question-raw-content");
                new Click().clickByclassname("true-false-answer-label");
                new TextSend().textsendbyid("solution","content-solution");
                new TextSend().textsendbyid("text","content-hint");
                new Click().clickByid("saveQuestionDetails1");//click on save
                new WebDriverWait(driver,10).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()='Saved.']")));
                String reviewText=driver.findElement(By.cssSelector("small[class='lsm-createAssignment-total-questions count-badge']")).getText();
                if(!reviewText.equals("1")){
                    Assert.fail("1 is not visible");
                }

                new Click().clickBycssselector("button[class='btn btn-rounded lsm-createAssignment-done selected btn-blue']");//click on review
                String saveForLater=new TextFetch().textfetchbyid("assessments-save-later-button");//verify save for later
                if (!saveForLater.contains("Save for later")){
                    Assert.fail("Save for later is not visible");
                }
                String addMore=new TextFetch().textfetchbyid("assessments-back-button");//verify add more
                if (!addMore.contains("Add more")){
                     Assert.fail("Add more is not visible");
                }
                String next=new TextFetch().textfetchbyid("assessments-use-button");//verify next
                if(!next.contains("Next")){
                    Assert.fail("Next is not visible");
                }
                new Click().clickByid("assessments-use-button");//click on next

                String share=new TextFetch().textfetchbyid("share-assessment-button");//verify share
                if (!share.contains("Share")){
                    Assert.fail("Share is not visible");
                }
                String shortLabel=new TextFetch().textfetchbyclass("lsm-short-label");
                Assert.assertEquals(shortLabel,"Short Label","Short Label field is not present");
                new TextSend().textsendbycssSelector("shor1","input[class='lsm-assignment-input total_point width50 lsm-assignment-short-label-input']");
                List<WebElement> totalPoint=driver.findElements(By.cssSelector("label[class='lsm-new-assignment-content-row width50']"));
                if(!totalPoint.get(0).getText().contains("Total points"))
                    Assert.fail("Total points Text is not present");
                String point=new TextFetch().textfetchbycssselector("span[class='lsm-assignment-total-points total_point width50']");
                if(!point.equals("1"))
                    Assert.fail("Total points is not present");
                new Click().clickBycssselector("i[class='lsm-new-assignment-addDetails-toggle lsm-new-assignment-expand-icon']");//click on the +icon
                String descriptionLabel=new TextFetch().textfetchbylistcssselector("span[class='width25 f-left']",0);
                if(!descriptionLabel.contains("Description"))
                    Assert.fail("Description (optional) tag is not visible");
                String tag=new TextFetch().textfetchbylistcssselector("span[class='width25 f-left']",1);
                if(!tag.contains("Tag"))
                    Assert.fail("tag (Optional) tag is not visible");
                boolean districtByDefault=driver.findElement(By.id("district")).isSelected();
                System.out.println("DistrictByDefault:"+districtByDefault);
                if(districtByDefault==false)
                    Assert.fail("By Default District radio button is not selected");

                ((JavascriptExecutor)driver).executeScript("arguments[0].click();",driver.findElement(By.id("private")));//click on the private radio button
                Thread.sleep(2000);
                ((JavascriptExecutor)driver).executeScript("arguments[0].click();",driver.findElement(By.id("public")));//click on the public radio button
                new Click().clickByid("share-assessment-button");//click on share
                String assessmentname1=new TextFetch().textFetchByXpath("(//div[contains(text(),'New Assessment')])[1]");
                if (!assessmentname1.contains("New Assessment")){
                    Assert.fail("New Assessment name is not visible");
                }
            }
            catch (Exception e){
                Assert.fail("Exception in test case createbesideelo of class createNewAssessment",e);
            }}

    @Test(priority = 3,enabled = true)
            public void clickingOnSaveForLater(){
                try {
                    //tc row no 130
                    //"1. Login as DA 2.Click on assessment on the Side navigator (navigator drop down) 3.Click on ""+New assessment"" 4.Click on ""Create new""
                    //5.Click ""Create"" 6.Select any question type, 7.Enter assessment name 8. Enter all the valid fields
                    //9.Select difficulty level 10.Click ""Save""11.Click ""Review"" 12.Click ""Save for later"""
                    String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", "130");


                    String email = "daadmin@snapwiz.com";
                    int index = 130;
                    new Login().loginAsDA(index, email);//log in as DA
                    new Navigator().navigateTo("districtAssessments");//select assessment
                    new Click().clickBycssselector("a[class='btn btn-blue btn-rounded']");//Click on Create New Assignment
                    new TextSend().textsendbyid(assessmentname,"new-assessment-name");//Enter the assessment name
                    new Click().clickByid("create-assessment-with-val");//Click on create
                    new Click().clickBycssselector("span[class='lsm-create-btn lsm-elo-create-btn btn sp-link']");//Click on create link
                    new Click().clickByid("qtn-true-false-type");//click on truefalse
                    new TextSend().textsendbyid("truefalse1","question-raw-content");//type on truefalse field
                    new Click().clickByclassname("true-false-answer-label");//
                    new TextSend().textsendbyid("solution1","content-solution");//type on solution field
                    new TextSend().textsendbyid("text1","content-hint");//type on content field
                    new Click().clickByid("saveQuestionDetails1");//click on save
                    new Click().clickBycssselector("button[class='btn btn-rounded lsm-createAssignment-done selected btn-blue']");//click on review
                    new Click().clickByid("assessments-save-later-button");//click on save for later
                    String viewDraftStatus=new TextFetch().textFetchByXpath("//span[@class='as-assignment-draft-count']");
                    String str=viewDraftStatus.substring(1,2);
                    int count=Integer.parseInt(str);
                    Thread.sleep(2000);
                    new Click().clickBycssselector("a[class='btn btn-blue btn-rounded']");//Click on Create New Assignment
                    new TextSend().textsendbyid(assessmentname+"2","new-assessment-name");//Enter the assessment name
                    new Click().clickByid("create-assessment-with-val");//Click on create
                    new Click().clickBycssselector("span[class='lsm-create-btn lsm-elo-create-btn btn sp-link']");//Click on create link
                    new Click().clickByid("qtn-true-false-type");//click on truefalse
                    new TextSend().textsendbyclass("New Assessment2","lsm-createAssignment-input-name");
                    new TextSend().textsendbyid("truefalse2","question-raw-content");//type on truefalse field
                    new Click().clickByclassname("true-false-answer-label");//
                    new TextSend().textsendbyid("solution2","content-solution");//type on solution field
                    new TextSend().textsendbyid("text2","content-hint");//type on content field
                    new Click().clickByid("saveQuestionDetails1");//click on save
                    new Click().clickBycssselector("button[class='btn btn-rounded lsm-createAssignment-done selected btn-blue']");//click on review
                    new Click().clickByid("assessments-save-later-button");//click on save for later
                    String str1=viewDraftStatus.substring(1,2);
                    System.out.println(str1);
                    int count1=Integer.parseInt(str1);
                    if(count < count1){
                        Assert.fail("count is not less than count1 ");
                    }
                }
                catch (Exception e){
                    Assert.fail("Exception in test case clickingOnSaveForLater of class createNewAssessment",e);
                }
    }
     @Test(priority = 4 ,enabled = true)
            public void deletingAnAssessment(){
            try {
                //tc row no 131
                //"1. Login as DA 2.Click on assessment on the Side navigator (navigator drop down) 3.Select a Grade and subject in which draft assessments are there 4.Click on assessment from the side navigator"
                String email = "daadmin@snapwiz.com";
                int index = 131;
                new Login().loginAsDA(index, email);//log in as DA
                new Navigator().navigateTo("districtAssessments");//select assessment
                new Navigator().selectGradeAndSubject(index);
                new Click().clickByclassname("as-assignment-draftTxt");//click on view draft status
                new Click().clickBycssselector("div[class='as-label ellipsis']");//click on assessment
                new Click().clickByid("assessments-back-link");//click on back
                Thread.sleep(5000);
                String viewDraftStatus=new TextFetch().textFetchByXpath("//span[@class='as-assignment-draft-count']");
                String str=viewDraftStatus.substring(1,2);
                int count=Integer.parseInt(str);
                new Click().clickByclassname("as-assignment-draftTxt");//click on view draft status
                new Click().clickByclassname("as-delete-assessment");//click on delete assessment
                String popUp=new TextFetch().textfetchbyclass("delete-notification");
                //System.out.println(popUp);
                if(!popUp.contains("You are about to delete the assignment.")){
                    Assert.fail("popup message is not displaying");
                }
                new Click().clickByclassname("delete-draft-assessment");//click on yes
                String str1=viewDraftStatus.substring(1,2);
                int count1=Integer.parseInt(str1);
                if(count > count1){
                    Assert.fail("view status count is not decrementing after deletion");
                }
            }
            catch (Exception e){
                Assert.fail("Exception in test case deletingAnAssessment of class createNewAssessment",e);
            }
    }
    @Test(priority = 5  ,enabled = true)
    public void useTheAssessmentListedInDraft(){
        try{
            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", "134");

            //tc row no 134
            String email = "daadmin@snapwiz.com";
            String teacherEmail="da1autoteacher@snapwiz.com";
            int index = 134;
            new Login().loginAsDA(index, email);//log in as DA
            new Navigator().navigateTo("districtAssessments");//select assessment
            new Click().clickBycssselector("a[class='btn btn-blue btn-rounded']");//Click on Create New Assignment
            new TextSend().textsendbyid(assessmentname,"new-assessment-name");//Enter the assessment name
            new Click().clickByid("create-assessment-with-val");//Click on create
            new Click().clickBycssselector("span[class='lsm-create-btn lsm-elo-create-btn btn sp-link']");//Click on create link
            new Click().clickByid("qtn-true-false-type");//click on truefalse
            new TextSend().textsendbyclass("New Assessment3", "lsm-createAssignment-input-name");
            new TextSend().textsendbyid("truefalse3", "question-raw-content");//type on truefalse field
            new Click().clickByclassname("true-false-answer-label");//
            new TextSend().textsendbyid("solution3", "content-solution");//type on solution field
            new TextSend().textsendbyid("text2","content-hint");//type on content field
            new Click().clickByid("saveQuestionDetails1");//click on save
            new Click().clickBycssselector("button[class='btn btn-rounded lsm-createAssignment-done selected btn-blue']");//click on review
            new Click().clickByid("assessments-save-later-button");//click on save for later
            Thread.sleep(1000);


            new Navigator().navigateTo("districtAssessments");//select assessment
            new Click().clickByclassname("as-assignment-draft");//click on view draft status
            String clickOnViewdraftStatus=new TextFetch().textfetchbycssselector("div[class='as-label ellipsis']");
            if (!clickOnViewdraftStatus.contains("New Assessment3")) {
                Assert.fail("New Assessment3 is not displaying");
            }
            new Click().clickByclassname("as-edit");//click on edit
            String saveForLater=new TextFetch().textfetchbyid("assessments-save-later-button");//verify save for later
            if (!saveForLater.contains("Save for later")){
                Assert.fail("Save for later is not visible");
            }
            String addMore=new TextFetch().textfetchbyid("assessments-back-button");//verify addmore
            if (!addMore.contains("Add more")){
                Assert.fail("Add more is not visible");
            }
            String next=new TextFetch().textfetchbyid("assessments-use-button");//verify next
            if(!next.contains("Next")){
                Assert.fail("Next is not visible");
            }
            new Click().clickByid("assessments-use-button");//click on next
            String shortLabel=new TextFetch().textfetchbyclass("lsm-short-label");
            Assert.assertEquals(shortLabel,"Short Label","Short Label field is not present");
            new TextSend().textsendbycssSelector("shor1","input[class='lsm-assignment-input total_point width50 lsm-assignment-short-label-input']");
            List<WebElement> totalPoint=driver.findElements(By.cssSelector("label[class='lsm-new-assignment-content-row width50']"));
            if(!totalPoint.get(0).getText().contains("Total points"))
                Assert.fail("Total points Text is not present");
            String point=new TextFetch().textfetchbycssselector("span[class='lsm-assignment-total-points total_point width50']");
            if(!point.equals("1"))
                Assert.fail("Total points is not present");
            new Click().clickBycssselector("i[class='lsm-new-assignment-addDetails-toggle lsm-new-assignment-expand-icon']");//click on the +icon
            String descriptionLabel=new TextFetch().textfetchbylistcssselector("span[class='width25 f-left']",0);
            if(!descriptionLabel.contains("Description"))
                Assert.fail("Description (optional) tag is not visible");
            String tag=new TextFetch().textfetchbylistcssselector("span[class='width25 f-left']",1);
            if(!tag.contains("Tag"))
                Assert.fail("tag (Optional) tag is not visible");
            boolean districtByDefault=driver.findElement(By.id("district")).isSelected();
            System.out.println("DistrictByDefault:"+districtByDefault);
            if(districtByDefault==false)
                Assert.fail("By Default District radio button is not selected");

            ((JavascriptExecutor)driver).executeScript("arguments[0].click();",driver.findElement(By.id("private")));//click on the private radio button
            Thread.sleep(2000);
            ((JavascriptExecutor)driver).executeScript("arguments[0].click();",driver.findElement(By.id("public")));//click on the public radio button
            new Click().clickByid("share-assessment-button");//click on share
            String clickOnViewdraftStatus1=new TextFetch().textfetchbycssselector("div[class='as-label ellipsis']");
            if (!clickOnViewdraftStatus1.contains("New Assessment3")) {
                Assert.fail("New Assessment3 is not displaying");
            }
            new Navigator().logout();
            new Login().loginAsDA(index,teacherEmail);//login as teacher
            new Navigator().navigateTo("assignment");//click on assignment
            new Click().clickByclassname("instructor-assignment-new-txt");//click on +new assignment
            new Click().clickByclassname("as-assignment-flow-link-title");//click on existing
             Select sel = new Select(driver.findElement(By.xpath(".//*[@id='lsm-createAssignment-grade-dropDown']/select")));
            sel.selectByVisibleText("Kindergarten");
            new Click().clickByid("two");
            String assessmentName=new TextFetch().textFetchByXpath("//div[text()='New Assessment3']");
            if(!assessmentName.contains("New Assessment3")){
                Assert.fail("New Assessment3 is not displaying");
            }

            /*String assessmentname=new TextFetch().textfetchbycssselector("div[class='as-label ellipsis']");
            if(assessmentname.contains(("New Assessment3"))){
                Assert.fail("New Assessment3 is not displaying");
            }*/

        }
        catch (Exception e){
            Assert.fail("Exception in test case useTheAssessmentListedInDraft of class createNewAssessment",e);
        }
    }
    @Test(priority = 6,enabled = true)
    public void creatingPrivateAssessment(){
        try {
            //tc row no 139
            String email = "daadmin@snapwiz.com";
            String district = "daadmin1@snapwiz.com";
            String teacherEmail = "da1autoteacher@snapwiz.com";
            String teacherEmail1 = "da2autoteacher@snapwiz.com";
            int index = 139;
            new Login().loginAsDA(index, email);//log in as DA
            new Navigator().navigateTo("districtAssessments");//select assessment
            new Click().clickByclassname("instructor-assignment-new-txt");//click on +new assessment
            new Click().clickByXpath("//*[@id='create-new-assignment']/div[2]/div[1]");//click on create new assessment
            new Click().clickByXpath("//*[@id='objectives-details-view-wrapper']/div[2]/div/div[1]/span[3]");//click on create
            new Click().clickByid("qtn-true-false-type");//click on truefalse
            new TextSend().textsendbyclass("New Assessment4", "lsm-createAssignment-input-name");
            new TextSend().textsendbyid("truefalse4", "question-raw-content");//type on truefalse field
            new Click().clickByclassname("true-false-answer-label");//
            Select select = new Select(driver.findElement(By.id("difficulty-level-drop-down")));
            select.selectByIndex(1); //select easy
            new Click().clickByid("saveQuestionDetails1");//click on save
            new Click().clickBycssselector("div[class='lsm-createAssignment-done selected']");//click on review
            new Click().clickByid("assessments-use-button");//click on next
            String shortLabel=new TextFetch().textfetchbyclass("lsm-short-label");
            Assert.assertEquals(shortLabel,"Short Label","Short Label field is not present");
            new TextSend().textsendbycssSelector("shor1","input[class='lsm-assignment-input total_point width50 lsm-assignment-short-label-input']");
            List<WebElement> totalPoint=driver.findElements(By.cssSelector("label[class='lsm-new-assignment-content-row width50']"));
            if(!totalPoint.get(0).getText().contains("Total points"))
                Assert.fail("Total points Text is not present");
            String point=new TextFetch().textfetchbycssselector("span[class='lsm-assignment-total-points total_point width50']");
            if(!point.equals("1"))
                Assert.fail("Total points is not present");
            new Click().clickBycssselector("i[class='lsm-new-assignment-addDetails-toggle lsm-new-assignment-expand-icon']");//click on the +icon
            String descriptionLabel=new TextFetch().textfetchbylistcssselector("span[class='width25 f-left']",0);
            if(!descriptionLabel.contains("Description"))
                Assert.fail("Description (optional) tag is not visible");
            String tag=new TextFetch().textfetchbylistcssselector("span[class='width25 f-left']",1);
            if(!tag.contains("Tag"))
                Assert.fail("tag (Optional) tag is not visible");
            boolean districtByDefault=driver.findElement(By.id("district")).isSelected();
            System.out.println("DistrictByDefault:"+districtByDefault);
            if(districtByDefault==false)
                Assert.fail("By Default District radio button is not selected");

            ((JavascriptExecutor)driver).executeScript("arguments[0].click();",driver.findElement(By.id("private")));//click on the private radio button
            Thread.sleep(2000);
            ((JavascriptExecutor)driver).executeScript("arguments[0].click();",driver.findElement(By.id("public")));//click on the public radio button
            new Click().clickByid("share-assessment-button");//click on share
            String clickOnViewdraftStatus=new TextFetch().textfetchbycssselector("div[class='as-label ellipsis']");
            if (!clickOnViewdraftStatus.contains("New Assessment4")) {
                Assert.fail("New Assessment4 is not displaying");
            }
            new Navigator().logout();//click on logout
            new Login().loginAsDA(index,teacherEmail);//log in as instructor
            new Navigator().navigateTo("assignment");//click on assignment
            new Click().clickByclassname("instructor-assignment-new-txt");//click on +new assignment
            new Click().clickByclassname("as-assignment-flow-link-title");//click on existing
            Select selectSubject = new Select(driver.findElement(By.xpath("//*[@id='lsm-createAssignment-subject-dropDown']/select")));
            selectSubject.selectByIndex(0); //select subject
            Select selectGrade = new Select(driver.findElement(By.xpath("//*[@id='lsm-createAssignment-grade-dropDown']/select")));
            selectGrade.selectByIndex(0); //select grade
            new Click().clickByid("two");//click on community
            String afterClickingCommunity=new TextFetch().textFetchByXpath("//div[text()='New Assessment4']");
            if (!afterClickingCommunity.contains("New Assessment4")) {
                Assert.fail("New Assessment4 is not displaying");
            }
            String owner=new TextFetch().textFetchByXpath("//span[text()='daadmin daadmin  ']");
            //System.out.println(">>>>>>>>>>"+owner);
            if(!owner.contains("daadmin daadmin")){
                Assert.fail("owner name is not displying");
            }
            String standard=new TextFetch().textFetchByXpath("//span[text()='K.CC']");
            if(!standard.contains("K.CC")){
                Assert.fail("TLO nmae is not displaying");
            }
            //tc row no 151
            new Navigator().logout();//click on logout
            new Login().loginAsDA(index,district);//login as district1
            new Login().loginAsDA(index,teacherEmail1);//login as teacher2
            new Navigator().navigateTo("assignment");//click on assignment
            new Click().clickByclassname("instructor-assignment-new-txt");//click on +new assignment
            new Click().clickByclassname("as-assignment-flow-link-title");//click on existing
            Select selectSubject1 = new Select(driver.findElement(By.xpath("//*[@id='lsm-createAssignment-subject-dropDown']/select")));
            selectSubject1.selectByIndex(0); //select subject
            Select selectGrade1 = new Select(driver.findElement(By.xpath("//*[@id='lsm-createAssignment-grade-dropDown']/select")));
            selectGrade1.selectByIndex(0); //select grade
            new Click().clickByid("two");//click on community
            String afterClickingCommunity1=new TextFetch().textFetchByXpath("//div[text()='New Assessment4']");
            if (!afterClickingCommunity1.contains("New Assessment4")) {
                Assert.fail("New Assessment4 is not displaying");
            }
        }
        catch (Exception e){
            Assert.fail("Exception in test case creatingPrivateAssessment of class createNewAssessment",e);
        }}
    @Test(priority = 7,enabled = true)
    public void previewingTheQuestions(){
        try{
            //tc row no 152
            String email = "daadmin@snapwiz.com";
            int index = 152;
            new Login().loginAsDA(index, email);//login as DA
            new Navigator().navigateTo("districtAssessments");//select assessment
            new Click().clickByclassname("instructor-assignment-new-txt");//click on +new assessment
            new Click().clickByXpath("//*[@id='create-new-assignment']/div[2]/div[1]");//click on create new assessment
            new Click().clickByXpath("//*[@id='objectives-details-view-wrapper']/div[1]/div/div[1]/span[3]");//click on create
            new Click().clickByid("qtn-true-false-type");//click on truefalse
            new TextSend().textsendbyclass("New Assessment5", "lsm-createAssignment-input-name");
            new TextSend().textsendbyid("truefalse5", "question-raw-content");//type on truefalse field
            new Click().clickByclassname("true-false-answer-label");//
            new Click().clickByXpath("//label[@class='writeboardchkbox as-writeboard-checkbox-unchecked']");//click on use sratchpad
            new Click().clickByid("saveQuestionDetails1");//click on save
            Thread.sleep(2000);
            String reviewText=driver.findElement(By.className("lsm-createAssignment-total-questions")).getText();
            if(!reviewText.equals("1")){
                Assert.fail("1 is not visible");
            }
            new Click().clickByclassname("as-question-teacher-preview-button");//click on preview
            /*String question=new TextFetch().textFetchByXpath("//label[@for='name']");
            System.out.println(">>>>>>>>>"+question);
            if(!question.contains("truefalse5")){
                Assert.fail("Question is not displaying");
            }*/
           for(String handle:driver.getWindowHandles())
           {
               driver.switchTo().window(handle);
           }
            new Click().clickByid("show-your-work-label");// Click on "Show your Work"
            driver.switchTo().frame(driver.findElement(By.id("whiteBoard_iframe_kedukWBSTUDENT__whiteBoard_JSONData__false")));
            new Click().clickByid("text-btn");//click on text button
            new Click().clickBycssselector("canvas[height='395']");
            new TextSend().textsendbyid("texttt", "textEditor");
            new Click().clickbylistcssselector("button[type='button']", 2);
            driver.switchTo().defaultContent();
            new Click().clickByid("question-reveview-submit");//click on save
            Thread.sleep(5000);
            String str=new TextFetch().textFetchByXpath("//*[@id='show-your-work-label']").trim();
            System.out.println("StrStrStrStrStr: " +str);
            if(!str.contains("View your work")){
                Assert.fail("View your work link is not vissible");
            }

            String image=driver.findElement(By.cssSelector("div[class='true-false-student-tick true-false-student-right']")).getCssValue("background-image");
            System.out.println(">>>>>>>>"+image);
            new Click().clickByid("show-your-work-label");// Click on "Show your Work"
        }
            catch (Exception e){
                Assert.fail("Exception in test case previewingTheQuestions of class createNewAssessment",e);
            }
    }
    @Test(priority = 8,enabled = true)
    public void editPrivateAssessment(){
        try{
            //tc row no 156
            String email = "daadmin@snapwiz.com";
            int index = 156;
            new Login().loginAsDA(index, email);//login as DA
            new Navigator().navigateTo("districtAssessments");//select assessment
            new Click().clickByclassname("instructor-assignment-new-txt");//click on +new assessment
            new Click().clickByXpath("//*[@id='create-new-assignment']/div[2]/div[1]");//click on create new assessment
            new Click().clickByXpath("//*[@id='objectives-details-view-wrapper']/div[1]/div/div[1]/span[3]");//click on create
            new Click().clickByid("qtn-true-false-type");//click on truefalse
            new TextSend().textsendbyclass("New Assessment6", "lsm-createAssignment-input-name");
            new TextSend().textsendbyid("truefalse6", "question-raw-content");//type on truefalse field
            new Click().clickByclassname("true-false-answer-label");//
            new Click().clickByid("saveQuestionDetails1");//click on save
            new Click().clickBycssselector("div[class='lsm-createAssignment-done selected']");//click on review
            new Click().clickByid("assessments-use-button");//click on next
            new TextSend().textsendbycssSelector("shor1","input[class='lsm-assignment-input total_point width50 lsm-assignment-short-label-input']");
           // new Click().clickBycssselector("i[class='lsm-new-assignment-addDetails-toggle lsm-new-assignment-expand-icon']");//click on the +icon
            ((JavascriptExecutor)driver).executeScript("arguments[0].click();",driver.findElement(By.id("private")));//click on the private radio button
            Thread.sleep(2000);
            new Click().clickByid("share-assessment-button");//click on share
            String edit=new TextFetch().textfetchbyclass("as-edit");
            if(!edit.contains("Edit Assignment")){
                Assert.fail("Edit Assignment is not available");
            }
            Thread.sleep(2000);
            String delete=new TextFetch().textFetchByXpath("//span[text()=' Delete Assignment ']");
            System.out.println(">>>>>>>>>>>"+delete);
            if(!delete.contains("Delete Assignment")){
                Assert.fail("Delete Assignment is not available");
            }
            String beforeEdit=new TextFetch().textfetchbycssselector("div[class='as-label ellipsis']");
            new Click().clickByclassname("as-edit");//click on edit
            driver.findElement(By.className("lsm-createAssignment-input-name")).clear();
            driver.findElement(By.className("lsm-createAssignment-input-name")).sendKeys("NewAssessment");
            new Click().clickByid("assessments-save-later-button");
            String afterEdit=new TextFetch().textfetchbycssselector("div[class='as-label ellipsis']");
            if(afterEdit==beforeEdit){
                Assert.fail("changes are not reflected");
            }
        }
        catch (Exception e){
            Assert.fail("Exception in test case editPrivateAssessment of class createNewAssessment",e);
        }
    }
    @Test(priority = 9,enabled = true)
    public  void DaEditTheAssessment(){
        try{
            //tc row no 159
            //"1. Login as a DA 2.Click on ""Assessment"" from the navigator drop down 3.Click on ""+New assessment"" 4.Create a ""District assessment""
            //5.Click on 'Save for later' "
            String email = "daadmin@snapwiz.com";
            int index = 159;
            new Login().loginAsDA(index, email);//login as DA
            new Navigator().navigateTo("districtAssessments");//select assessment
            new Click().clickByclassname("instructor-assignment-new-txt");//click on +new assessment
            new Click().clickByXpath("//*[@id='create-new-assignment']/div[2]/div[1]");//click on create new assessment
            new Click().clickByXpath("//*[@id='objectives-details-view-wrapper']/div[1]/div/div[1]/span[3]");//click on create
            new Click().clickByid("qtn-true-false-type");//click on truefalse
            new TextSend().textsendbyclass("New Assessment7", "lsm-createAssignment-input-name");
            new TextSend().textsendbyid("truefalse7", "question-raw-content");//type on truefalse field
            new Click().clickByclassname("true-false-answer-label");//
            new Click().clickByid("saveQuestionDetails1");//click on save
            new Click().clickBycssselector("div[class='lsm-createAssignment-done selected']");//click on review
            new Click().clickByid("assessments-save-later-button");//click on save for later
            String afterShare=new TextFetch().textfetchbycssselector("div[class='as-label ellipsis']");
            if (!afterShare.contains("New Assessment7")) {
                Assert.fail("New Assessment7 is not displaying");
            }
            String edit=new TextFetch().textfetchbyclass("as-edit");
            if(!edit.contains("Edit Assignment")){
                Assert.fail("Edit Assignment is not available");
            }
            Thread.sleep(2000);
            String delete=new TextFetch().textFetchByXpath("//span[text()=' Delete Assignment ']");
            //System.out.println(">>>>>>>>>>>"+delete);
            if(!delete.contains("Delete Assignment")){
                Assert.fail("Delete Assignment is not available");
            }
            String beforeEdit=new TextFetch().textfetchbycssselector("div[class='as-label ellipsis']");
            new Click().clickByclassname("as-edit");//click on edit
            driver.findElement(By.className("lsm-createAssignment-input-name")).clear();
            driver.findElement(By.className("lsm-createAssignment-input-name")).sendKeys("NewAssessment1");
            new Click().clickByid("assessments-save-later-button");
            String afterEdit=new TextFetch().textfetchbycssselector("div[class='as-label ellipsis']");
            if(afterEdit==beforeEdit){
                Assert.fail("changes are not reflected");
            }
        }
        catch (Exception e){
            Assert.fail("Exception in test case DaEditTheAssessment of class createNewAssessment");
        }
    }
    @Test(priority = 10,enabled = true)
    public void daShouldNotEdit(){
        try{
            //tc row no 162
            //"1. Login as a DA 2.Click on ""Assessment"" from the navigator drop down 3.Click on ""+New assessment"" 4.Create a ""District assessment"""
            String email = "daadmin@snapwiz.com";
            int index = 162;
            new Login().loginAsDA(index, email);//login as DA
            new Navigator().navigateTo("districtAssessments");//select assessment
            new Click().clickByclassname("instructor-assignment-new-txt");//click on +new assessment
            new Click().clickByXpath("//*[@id='create-new-assignment']/div[2]/div[1]");//click on create new assessment
            new Click().clickByXpath("//*[@id='objectives-details-view-wrapper']/div[1]/div/div[1]/span[3]");//click on create
            new Click().clickByid("qtn-true-false-type");//click on truefalse
            new TextSend().textsendbyclass("New Assessment8", "lsm-createAssignment-input-name");
            new TextSend().textsendbyid("truefalse8", "question-raw-content");//type on truefalse field
            new Click().clickByclassname("true-false-answer-label");//
            new Click().clickByid("saveQuestionDetails1");//click on save
            new Click().clickBycssselector("div[class='lsm-createAssignment-done selected']");//click on review
            new Click().clickByid("assessments-use-button");//click on next
            new TextSend().textsendbycssSelector("shor1","input[class='lsm-assignment-input total_point width50 lsm-assignment-short-label-input']");
            new Click().clickByid("share-assessment-button");//click on share
            String afterShare=new TextFetch().textfetchbycssselector("div[class='as-label ellipsis']");
            if (!afterShare.contains("New Assessment8")) {
                Assert.fail("New Assessment8 is not displaying");
            }
            String edit=new TextFetch().textFetchByXpath("//div[@class='as-card-wrapper']");
            if(edit.contains("Edit Assignment")){
                Assert.fail("edit option is not available");
            }
        }
        catch (Exception e){
            Assert.fail("Exception in test case DaEditTheAssessment of class createNewAssessment",e);
        }
    }
    @Test(priority = 11,enabled = true)
    public void daShouldNotDelete(){
        try {
            //tc row no 163
            //"1. Login as a DA 2.Click on ""Assessment"" from the navigator drop down 3.Click on ""+New assessment"" 4.Create a ""District assessment""
            //5.Click ""share"" to share the assessment to share it with the district"
            String email = "daadmin@snapwiz.com";
            int index = 163;
            new Login().loginAsDA(index, email);//login as DA
            new Navigator().navigateTo("districtAssessments");//select assessment
            new Click().clickByclassname("instructor-assignment-new-txt");//click on +new assessment
            new Click().clickByXpath("//*[@id='create-new-assignment']/div[2]/div[1]");//click on create new assessment
            new Click().clickByXpath("//*[@id='objectives-details-view-wrapper']/div[1]/div/div[1]/span[3]");//click on create
            new Click().clickByid("qtn-true-false-type");//click on truefalse
            new TextSend().textsendbyclass("New Assessment9", "lsm-createAssignment-input-name");
            new TextSend().textsendbyid("truefalse9", "question-raw-content");//type on truefalse field
            new Click().clickByclassname("true-false-answer-label");//
            new Click().clickByid("saveQuestionDetails1");//click on save
            new Click().clickBycssselector("div[class='lsm-createAssignment-done selected']");//click on review
            new Click().clickByid("assessments-use-button");//click on next
            new TextSend().textsendbycssSelector("shor1","input[class='lsm-assignment-input total_point width50 lsm-assignment-short-label-input']");
            new Click().clickByid("share-assessment-button");//click on share
            String afterShare=new TextFetch().textfetchbycssselector("div[class='as-label ellipsis']");
            if (!afterShare.contains("New Assessment9")) {
                Assert.fail("New Assessment9 is not displaying");
            }
            Thread.sleep(3000);
            String shared=new TextFetch().textFetchByXpath("//span[text()='Shared ']");
            System.out.println(">>>>>>>>"+shared);
            if(!shared.contains("Shared")){
                Assert.fail("shared is not displaying");
            }
            String delete=new TextFetch().textFetchByXpath("//div[@class='as-card-wrapper']");
            if(delete.contains("Delete Assignment")){
                Assert.fail("delete option is not available");
            }
        }
        catch (Exception e){
            Assert.fail("Exception in test case daShouldNotDelete of class createNewAssessment",e);
        }
    }
    @Test(priority = 12,enabled = true)
    public void assessmentShouldVisibleToTeacher(){
        try{
            //tc row no 165
            //"1. Login as DA 2.Select 'Assessments' from the navigator dropdown "
            String email = "daadmin@snapwiz.com";
            String teacherEmail = "da1autoteacher@snapwiz.com";
            int index = 165;
            new Login().loginAsDA(index, email);//login as DA
            new Navigator().navigateTo("districtAssessments");//select assessment
            new Click().clickByclassname("instructor-assignment-new-txt");//click on +new assessment
            Thread.sleep(2000);
            /*String createAssessment=new TextFetch().textFetchByXpath("//div[text()='Create Assessment']");
            if(!createAssessment.contains("Create Assessment")){
                Assert.fail("create assessment is not displaying");
            }*/
            String modifyExistingAssessment=new TextFetch().textFetchByXpath("//div[text()='Modify Existing Assessment']");
            if(!modifyExistingAssessment.contains("Modify Existing Assessment")){
                Assert.fail("Modify Existing Assessment is not displaying");
            }
            String createNewAssessment=new TextFetch().textFetchByXpath("//div[text()='Create New Assessment']");
            if(!createNewAssessment.contains("Create New Assessmen")){
                Assert.fail("Create New Assessmen is not displaying");
            }
            new Click().clickByXpath("//*[@id='create-new-assignment']/div[2]/div[1]");//click on create new assessment
            Select selectSubject1 = new Select(driver.findElement(By.xpath("//*[@id='lsm-createAssignment-subject-dropDown']/select")));
            selectSubject1.selectByIndex(0); //select subject
            Select selectGrade1 = new Select(driver.findElement(By.xpath("//*[@id='lsm-createAssignment-grade-dropDown']/select")));
            selectGrade1.selectByIndex(0); //select grade
            Thread.sleep(2000);
            new Click().clickBycssselector("input[class='lsm-createAssignment-owner-checked owner']");//click on me
            new Click().clickBycssselector("input[class='lsm-createAssignment-owner-checked community']");//click on community
            new Click().clickByXpath("//li[@class='selected-tlo']");//click on operation and algebric thinking
            new Click().clickByXpath("//*[@id='objectives-details-view-wrapper']/div[2]/div/div[1]/span[3]");//click on create
            new Click().clickByid("qtn-true-false-type");//click on truefalse
            new TextSend().textsendbyclass("New Assessment10","lsm-createAssignment-input-name");
            new TextSend().textsendbyid("truefalse10","question-raw-content");//type on truefalse field
            new Click().clickByclassname("true-false-answer-label");//
            new TextSend().textsendbyid("solution10","content-solution");//type on solution field
            new TextSend().textsendbyid("text10","content-hint");//type on content field
            new Click().clickByid("saveQuestionDetails1");//click on save
            new Click().clickBycssselector("div[class='lsm-createAssignment-done selected']");//click on review
            new Click().clickByid("assessments-use-button");//click on next
            String shortLabel=new TextFetch().textfetchbyclass("lsm-short-label");
            Assert.assertEquals(shortLabel,"Short Label","Short Label field is not present");
            new TextSend().textsendbycssSelector("shor1","input[class='lsm-assignment-input total_point width50 lsm-assignment-short-label-input']");
            List<WebElement> totalPoint=driver.findElements(By.cssSelector("label[class='lsm-new-assignment-content-row width50']"));
            if(!totalPoint.get(0).getText().contains("Total points"))
                Assert.fail("Total points Text is not present");
            String point=new TextFetch().textfetchbycssselector("span[class='lsm-assignment-total-points total_point width50']");
            if(!point.equals("1"))
                Assert.fail("Total points is not present");
            new Click().clickBycssselector("i[class='lsm-new-assignment-addDetails-toggle lsm-new-assignment-expand-icon']");//click on the +icon
            String descriptionLabel=new TextFetch().textfetchbylistcssselector("span[class='width25 f-left']",0);
            if(!descriptionLabel.contains("Description"))
                Assert.fail("Description (optional) tag is not visible");
            String tag=new TextFetch().textfetchbylistcssselector("span[class='width25 f-left']",1);
            if(!tag.contains("Tag"))
                Assert.fail("tag (Optional) tag is not visible");
            boolean districtByDefault=driver.findElement(By.id("district")).isSelected();
            System.out.println("DistrictByDefault:"+districtByDefault);
            if(districtByDefault==false)
                Assert.fail("By Default District radio button is not selected");
            new Click().clickByid("share-assessment-button");//click on share
            String assessneme=new TextFetch().textfetchbycssselector("div[class='as-label ellipsis']");
            if (!assessneme.contains("New Assessment10")) {
                Assert.fail("New Assessment10 is not displaying");
            }
            //tc row no 177
            new Navigator().logout();
            new Login().loginAsDA(index,teacherEmail);//login as teacher
            new Navigator().navigateTo("assignment");//click on assignment
            new Click().clickByclassname("instructor-assignment-new-txt");//click on +new assignment
            new Click().clickByclassname("as-assignment-flow-link-title");//click on existing
            Thread.sleep(2000);
            Select sel = new Select(driver.findElement(By.xpath(".//*[@id='lsm-createAssignment-grade-dropDown']/select")));
            sel.selectByVisibleText("Kindergarten");
            new Click().clickByid("three");
            String assessmentName=new TextFetch().textFetchByXpath("//div[text()='New Assessment10']");
            if(!assessmentName.contains("New Assessment10")){
                Assert.fail("New Assessment10 is not displaying");
            }
            String numberOfQuestion=new TextFetch().textfetchbyclass("as-assignments-question-count");
            String owner=new TextFetch().textfetchbycssselector("span[class='as-tags-list ellipsis']");
            String standard=new TextFetch().textFetchByXpath("//span[@class='as-tags-list']");
            Assert.assertEquals("(1 questions)",numberOfQuestion,"Number of question is not displaying");
            Assert.assertEquals("daadmin daadmin",owner,"owner name is not displaying");
            Assert.assertEquals("K.CC", standard ,"standard is not displaying");
        }
        catch (Exception e){
            Assert.fail("Exception in test case assessmentShouldVisibleToTeacher of class createNewAssessment",e);
        }
    }
    }



