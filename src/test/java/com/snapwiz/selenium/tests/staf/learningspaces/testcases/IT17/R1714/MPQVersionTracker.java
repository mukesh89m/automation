package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT17.R1714;
import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.Assignments;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.CMS.NewQuestionDataEntry;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.*;
import org.apache.commons.collections.CollectionUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.text.SimpleDateFormat;
import java.util.*;

/*
 * Created by Dharaneesha on 4/20/15.
 */
public class MPQVersionTracker extends Driver{
    String actual = null;
    String expected = null;


    @Test(priority=1,enabled = true)
    public void mpqToBeCreatedWithMultipleQuestionParts(){
        try{
            int dataIndex = 179;
            String assessmentName = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
            new Assignment().create(dataIndex);
            new Assignment().OpenAssignment(assessmentName,dataIndex);
            validateNewLinkOptions();
        }catch(Exception e){
            Assert.fail("Exception in the testcase 'mpqToBeCreatedWithMultipleQuestionParts' in the class 'MPQVersionTracker'",e);
        }

    }


    @Test(priority=2,enabled = true)
    public void VerifyUIForDropdownForQuestionStatus(){
        try{
            int dataIndex = 180;
            String assessmentName = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
            new Assignment().create(dataIndex);
            new Assignment().OpenAssignment(assessmentName,dataIndex);
            validateQuestionStatusComboBOx();
        }catch(Exception e){
            Assert.fail("Exception in the testcase 'VerifyUIForDropdownForQuestionStatus' in the class 'MPQVersionTracker'",e);
        }

    }


    @Test(priority=3,enabled = true)
    public void verifyRevisionsSidebar(){
        try{
            int dataIndex = 181;
            String assessmentName = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
            new Assignment().create(dataIndex);
            new Assignment().OpenAssignment(assessmentName,dataIndex);
            validateRevisionsOption();
        }catch(Exception e){
            Assert.fail("Exception in the testcase 'verifyRevisionsSidebar' in the class 'MPQVersionTracker'",e);
        }
    }

    @Test(priority=4,enabled = true)
    public void verifyUIInRevisionsSidebar(){
        try{

            /*Row No - 182 : "1. Login to CMS
            2. Open an assessment using the ""Manage Content"" tab.
            3. Click on the ""+"" button for ""Question ID"" in the QE of multipart question type
            4. Click on the option ""Revisions""."*/

            //Expected - 1.Revisions side-bar have the heading "Question and Revision History".
            int dataIndex = 181;
            Assignments assignments= PageFactory.initElements(driver, Assignments.class);
            String assessmentName = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
            new Assignment().create(dataIndex);
            new Assignment().OpenAssignment(assessmentName, dataIndex);
            new UIElement().waitAndFindElement(By.className("question-set-add-text"));
            Thread.sleep(1000);
            assignments.new_Link.click();
            Thread.sleep(3000);
            assignments.option_Revisions.click();
            new UIElement().waitAndFindElement(assignments.button_Preview);
            //Expected -1 : 1. Revisions side-bar have the heading "Question and Revision History".
            Assert.assertEquals(assignments.label_QuestionAndRevisionHistory.getText().trim(),"Question and Revision History","Revisions side-bar have the heading \"Question and Revision History\".");

            //Expected - 2 : A text in red displaying the latest published version having the format "Published -version_number" on the top-left corner of the cell
            if(!(assignments.label_PublishedV1.getText().trim().contains("Published - V1"))){
                Assert.fail("A text in red displaying the latest published version having the format \"Published -version_number\" on the top-left corner of the cell");
            }

            //Expected - 3 : A "+" button on the left bottom of the cell having the text "Create New Version".
            Assert.assertEquals(assignments.link_CreateNewVersion.getText().trim(),"+ Create New Version"," A \"+\" button on the left bottom of the cell having the text 'Create New Version'");

            //Expected - 4 : A button having display text "Get Diff".
            Assert.assertEquals(assignments.button_GetDiff.getText().trim(),"Get Diff","A button having display text \"Get Diff\".");


            //Expected - 5 : All other cells below should have a check-box, a timestamp, first name of the author who made the change and a version name alongwith the status of the question's version.
            if(driver.findElements(By.id("cms-question-revision-checkbox")).size()!=0) {
                assignments.getCheckBox_TimeStampList().get(0).click();
                Thread.sleep(3000);
            }else{
                Assert.fail("The Cell should have a checkbox");
            }

            String month =  new SimpleDateFormat("MMMM").format(new Date());
            String currentDate =  new SimpleDateFormat("dd").format(new Date());
            String todaysDate = month+" "+currentDate+", ";
            System.out.println("todaysDate : " + todaysDate);
            if(!(assignments.questionNewVersions_timeStamp.getText().contains(todaysDate))){
                Assert.fail("The Cell should have a timestamp");
            }

            Assert.assertEquals(assignments.questionNewVersions_firstName.getText().trim(),"lspaces2","The Cell should have a first name of the author who made the change");

            Assert.assertEquals(assignments.questionNewVersions_status.getText().trim(),"V1 - Publish","The Cell should have a status of the question's version.");


            //Expected - 6 : For versions with status "Published" or "Expired", a "Preview" button should be available.
            Assert.assertEquals(assignments.button_Preview.getText().trim(),"Preview","The Cell should have a Preview button");


        }catch(Exception e){
            Assert.fail("Exception in the testcase 'verifyNotAbleTOCreateForUnpublishedMPQ' in the class 'MPQVersionTracker'",e);
        }
    }



    @Test(priority=5,enabled = true)
    public void verifyNotAbleTOCreateForUnpublishedMPQ(){
        try{
            /*Row No - 183 : "1. Login to CMS.
            2. Open an assessment using the ""Manage Content"" tab.
            3. Navigate to a multipart question which doesn't have a ""Published"" version (Verify the versions available for the question using the
            Revisions sidebar.)."*/

            //Expected - 1. Link "Create New Version" should not be available.
            int dataIndex = 183;
            Assignments assignments= PageFactory.initElements(driver, Assignments.class);
            String assessmentName = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
            new Assignment().create(dataIndex);
            new Assignment().OpenAssignment(assessmentName, dataIndex);
            Thread.sleep(3000);
            new UIElement().waitAndFindElement(assignments.new_Link);
            assignments.new_Link.click();
            Thread.sleep(3000);
            assignments.option_Revisions.click();
            if(driver.findElements(By.id("cms-question-revision-new-version-link")).size()==0){
                Assert.fail("Link \"Create New Version\" should not be available.");
            }
        }catch(Exception e){
            Assert.fail("Exception in the testcase 'verifyNotAbleTOCreateForUnpublishedMPQ' in the class 'MPQVersionTracker'",e);
        }
    }


    @Test(priority=6,enabled = true)
    public void verifyAuthorAbleToCreatePublishedMPQ(){
        try{
            /*Row No - 184 : "1. Login to CMS.
            2. Open an assessment using the ""Manage Content"" tab.
            3. Navigate to a multipart question which doesn't have a ""Published"" version (Verify the versions available for the question using the
            Revisions sidebar.).
            4. Select the option ""Published"" from the Question-Status drop-down and save the question.
            5. Click on the button ""Create New Version"" available in the topmost cell of the Revisions sidebar."*/

            //Expected - 1. New version for the question should get created.
            int dataIndex = 184;
            Assignments assignments= PageFactory.initElements(driver, Assignments.class);
            String assessmentName = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
            String questionText = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));

            new Assignment().create(dataIndex);
            new Assignment().addQuestions(dataIndex, "multipart", assessmentName);
            new Assignment().addQuestions(dataIndex, "multipart", assessmentName);
            new Assignment().OpenAssignment(assessmentName, dataIndex);
            new UIElement().waitAndFindElement(By.className("question-set-add-text"));
            Thread.sleep(2000);
            assignments.new_Link.click();
            Thread.sleep(2000);
            assignments.option_Revisions.click();
            assignments.link_CreateNewVersion.click();
            WebDriverWait wait = new WebDriverWait(driver,10);
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("cms-question-revision-preview-button")));

            String expected = "Q3:\n" + questionText;
            System.out.println("assignments.questionText : "  + assignments.questionText.getText());
            Assert.assertEquals(assignments.questionText.getText(),expected,"New version for the question should get created.");


            //Expected - 2 : 2. The Question-Id in the ID drop-down should get incremented by 1.




            //Expected - 3 : 3. Total number of questions in the assessment should remain the same.
            assignments.combBox_jumpToQ.click();
            Assert.assertEquals(assignments.getQuestionsList_JumpToQ().size(),3,"Total number of questions in the assessment should remain the same.");


            //Expected - 4 : 4. The newly added version should appear in the Revisions side-bar with Status having the value "New" and version number having a value 1 more than the current highest version.



        }catch(Exception e){
            Assert.fail("Exception in the testcase 'verifyNotAbleTOCreateForUnpublishedMPQ' in the class 'MPQVersionTracker'",e);
        }
    }





    @Test(priority=7,enabled = true)
    public void AbleToCreateNewVersionIfPublishedMPQisEdited(){
        try{
           /* Row No - 185: "1. Login to CMS.
            2. Open an assessment using the ""Manage Content"" tab.
            3. Navigate to a multipart question which has a ""Published"" version (Verify the versions available for the question using the
            Revisions sidebar.).
            4. Edit the question and save the changes."*/

            int dataIndex = 185;
            Assignments assignments= PageFactory.initElements(driver, Assignments.class);
            NewQuestionDataEntry newQuestionDataEntry= PageFactory.initElements(driver, NewQuestionDataEntry.class);

            String assessmentName = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
            String questionText = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));

            new Assignment().create(dataIndex);
            new Assignment().addQuestions(dataIndex,"multipart",assessmentName);
            new Assignment().addQuestions(dataIndex, "multipart", assessmentName);
            new Assignment().OpenAssignment(assessmentName, dataIndex);
            Thread.sleep(5000);
            assignments.new_Link.click();
            assignments.option_Revisions.click();
            assignments.link_CreateNewVersion.click();
            new UIElement().waitAndFindElement(assignments.questionsEditBox);
            Thread.sleep(3000);
            assignments.questionsEditBox.click();
            assignments.questionsEditBox.sendKeys(" Updated");
            newQuestionDataEntry.save_button.click();

            //Expected - 1. The value in the ID drop-down should get incremented by 1.




            //Expected - 2. A new version of the question should get created and a cell for the new revision should appear on the Revisions sidebar.
            new UIElement().waitAndFindElement(assignments.new_Link);
            Thread.sleep(2000);
            assignments.new_Link.click();
            assignments.option_Revisions.click();
            String expected = "Q3:\n" + " Updated"+questionText;
            System.out.println("assignments.questionText : "  + assignments.questionText.getText());
            Assert.assertEquals(assignments.questionText.getText(),expected,"A new version of the question should get created");

            //Expected - 3. The status for the new version should be "New".

        }catch(Exception e){
            Assert.fail("Exception in the testcase 'verifyNotAbleTOCreateForUnpublishedMPQ' in the class 'MPQVersionTracker'",e);
        }
    }





    @Test(priority=8,enabled = true)
    public void VerifyDiffUIWhenOneCheckBoxIsSelected(){
        try{
            /*Row No - 190 : "1. Login to CMS.
            2. Open an assessment using the ""Manage Content"" tab.
            3. For multipart question in the assessment, click on the ""+"" button in the ID drop-down.
            4. Select the option ""Versions"".
            5. On the right sidebar, check the check-box for only one cell.
            6. Click on the button ""Diff"" on the top of the sidebar."*/

            //Expected  - 1. Diff UI should not get displayed.

            int dataIndex = 190;
            Assignments assignments= PageFactory.initElements(driver, Assignments.class);
            NewQuestionDataEntry newQuestionDataEntry= PageFactory.initElements(driver, NewQuestionDataEntry.class);
            String assessmentName = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
            new Assignment().create(dataIndex);
            new Assignment().openRevisionSideBar(dataIndex, 0);
            assignments.getCheckBox_TimeStampList().get(0).click();
            if(assignments.button_GetDiff.getAttribute("style").equals("opacity: 1; cursor: pointer;") && assignments.button_GetDiff.getAttribute("style").equals("opacity: 0.3; cursor: auto;")){
                Assert.fail("Diff UI should not get displayed.\n");
            }
        }catch(Exception e){
            Assert.fail("Exception in the testcase 'verifyNotAbleTOCreateForUnpublishedMPQ' in the class 'MPQVersionTracker'",e);
        }
    }




    @Test(priority=9,enabled = true)
    public void VerifyDiffUIWheTwoCheckBoxIsSelected(){
        try{
            /*Row No - 191 : "1. Login to CMS.
            2. Open an assessment using the ""Manage Content"" tab.
            3. For multipart question in the assessment having 2 or more versions, click on the ""+"" button in the ID drop-down.
            4. Select the option ""Versions"".
            5. On the right sidebar, check the check-boxes for 2 cells.
            6. Click on the button ""Diff"" on the top of the sidebar."*/

            //Expected  - 1. A pop-up should appear having the two selected versions displayed parallel to each other.

            int dataIndex = 191;
            Assignments assignments= PageFactory.initElements(driver, Assignments.class);
            NewQuestionDataEntry newQuestionDataEntry= PageFactory.initElements(driver, NewQuestionDataEntry.class);
            String assessmentName = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
            String questionText = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));

            new Assignment().create(dataIndex);
            new Assignment().openRevisionSideBar(dataIndex, 0);
            assignments.getCheckBox_TimeStampList().get(0).click();//Click on checkbox for first cell
            assignments.getCheckBox_TimeStampList().get(1).click();//Click on checkbox for Second cell
            assignments.button_GetDiff.click();//Click on button 'Get Diff'
            if(driver.findElements(By.id("diffoutput")).size()==0){
                Assert.fail("A pop-up should appear having the two selected versions displayed parallel to each other.\n");
            }

            if(!(assignments.getQuestionTextList_GetDiffPopup().get(0).getText().trim().equals(questionText)&& assignments.getQuestionTextList_GetDiffPopup().get(1).getText().trim().equals(questionText))){
                Assert.fail("A pop-up should appear having the two selected versions displayed parallel to each other.\n");
            }

        }catch(Exception e){
            Assert.fail("Exception in the testcase 'verifyNotAbleTOCreateForUnpublishedMPQ' in the class 'MPQVersionTracker'",e);
        }
    }



    @Test(priority=10,enabled = false)
    public void VerifyDiffUIWhenComparesWithLastPublishedVersion(){
        try{
            /*Row No - 192 : "1. Login to CMS.
            2. Open an assessment using the ""Manage Content"" tab.
            3. For any question in the assessment having 2 or more versions one of which is a Published version, click on the ""+"" button in the ID drop-down.
            4. Select the option ""Versions"".
            5. Create a new edited version of the question.
            6. Select the check-box for the cell for the new version and the Published version."*/

            //Expected  - 1. A pop-up should appear having the two selected versions displayed parallel to each other.

            int dataIndex = 192;
            Assignments assignments= PageFactory.initElements(driver, Assignments.class);
            NewQuestionDataEntry newQuestionDataEntry= PageFactory.initElements(driver, NewQuestionDataEntry.class);
            String assessmentName = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
            String questionText = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));

            new Assignment().create(dataIndex);
            new Assignment().create(1921);
            new Assignment().create(1922);

            new Assignment().createNewerVersionQuestion(dataIndex, 1);
            new Assignment().createNewerVersionQuestion(1921, 2);


            assignments.getCheckBox_TimeStampList().get(0).click();//Click on checkbox for first cell
            assignments.getCheckBox_TimeStampList().get(1).click();//Click on checkbox for Second cell
            assignments.button_GetDiff.click();//Click on button 'Get Diff'
            if(driver.findElements(By.id("diffoutput")).size()==0){
                Assert.fail("A pop-up should appear having the two selected versions displayed parallel to each other.\n");
            }

            if(!(assignments.getQuestionTextList_GetDiffPopup().get(0).getText().trim().equals(questionText)&& assignments.getQuestionTextList_GetDiffPopup().get(1).getText().trim().equals(questionText))){
                Assert.fail("A pop-up should appear having the two selected versions displayed parallel to each other.\n");
            }

        }catch(Exception e){
            Assert.fail("Exception in the testcase 'verifyNotAbleTOCreateForUnpublishedMPQ' in the class 'MPQVersionTracker'",e);
        }
    }


    @Test(priority=11,enabled = true)
    public void VerifyDiffUIWhenThirdCheckBoxIsSelected(){
        try{


            /*Row No - 193  : "1. Login to CMS.
            2. Open an assessment using the ""Manage Content"" tab.
            3. For multipart question in the assessment having 3 or more versions, click on the ""+"" button in the ID drop-down.
            4. Select the option ""Versions"".
            5. On the right sidebar, check the check-boxes for 3 cells."*/

            //Expected - 1. 3rd check-box should not get checked.
            int dataIndex = 193;
            Assignments assignments= PageFactory.initElements(driver, Assignments.class);
            NewQuestionDataEntry newQuestionDataEntry= PageFactory.initElements(driver, NewQuestionDataEntry.class);
            String assessmentName = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
            String questionText = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));

            new Assignment().create(dataIndex);
            new Assignment().openRevisionSideBar(dataIndex, 0);
            assignments.getCheckBox_TimeStampList().get(0).click();//Click on checkbox for first cell
            assignments.getCheckBox_TimeStampList().get(1).click();//Click on checkbox for Second cell
            assignments.getCheckBox_TimeStampList().get(2).click();//Click on checkbox for third cell
            if(assignments.getCheckBox_TimeStampList().get(2).isSelected()){
                Assert.fail("3rd check-box should not get checked.");
            }
        }catch(Exception e){
            Assert.fail("Exception in the testcase 'verifyNotAbleTOCreateForUnpublishedMPQ' in the class 'MPQVersionTracker'",e);
        }
    }




    @Test(priority=12,enabled = true)
    public void verifyStatusOfPreviousPublishedVersion(){
        try{


           /*Row No - 194 : "1. Login to CMS.
            2. Open an assessment using the ""Manage Content"" tab.
            3. Navigate to a question which has a ""Published"" version.
            4. Click on the ""+"" button for the ID drop-down.
            5. Select the option ""Versions"".
            6. Click on ""Create New Version""."*/
            int dataIndex = 194;
            String questionIdBeforeNewerVersion = null;
            String questionIdAfterNewerVersion = null;

            Assignments assignments= PageFactory.initElements(driver, Assignments.class);
            NewQuestionDataEntry newQuestionDataEntry= PageFactory.initElements(driver, NewQuestionDataEntry.class);
            String assessmentName = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
            String questionText = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));
            new Assignment().create(dataIndex);
            questionIdBeforeNewerVersion = new Assignment().createNewerVersionQuestion(dataIndex,1);

            //Expected - 1. A cell having the entry for the newly created version should appear in the Revisions sidebar.
            new UIElement().waitAndFindElement(assignments.new_Link);
            assignments.new_Link.click();
            Thread.sleep(2000);
            assignments.option_Revisions.click();
            assignments.getCheckBox_TimeStampList().get(0).click();//Click on checkbox for first cell
            assignments.getCheckBox_TimeStampList().get(1).click();//Click on checkbox for Second cell
            assignments.button_GetDiff.click();//Click on button 'Get Diff'
            Thread.sleep(5000);
            if(!(assignments.getQuestionTextList_GetDiffPopup().get(0).getText().trim().equals(questionText)&& assignments.getQuestionTextList_GetDiffPopup().get(1).getText().trim().equals("Updated"+questionText))){
                Assert.fail("A pop-up should appear having the two selected versions displayed parallel to each other.\n");
            }


            //Expected - 2. Value for the id in the ID drop-down should get incremented.
            assignments.button_Close_GetDiffPopUp.click();
            questionIdAfterNewerVersion = assignments.label_questionID.getText();
            Assert.assertNotEquals(questionIdBeforeNewerVersion,questionIdAfterNewerVersion,"Value for the id in the ID drop-down should get incremented.");

        }catch(Exception e){
            Assert.fail("Exception in the testcase 'verifyNotAbleTOCreateForUnpublishedMPQ' in the class 'MPQVersionTracker'",e);
        }
    }






    @Test(priority=13,enabled = true)
    public void verifyNewVersionsThatAreNotPublished(){
        try{


            /*Row No - 195 : "1. Login to CMS.
            2. Open an assessment using the ""Manage Content"" tab.
            3. Navigate to a multipart question which has a ""Published"" version.
            4. Click on the ""+"" button for the ID drop-down.
            5. Select the option ""Versions"".
            6. Click on ""Create New Version"".
            7. Edit the newly created version and make a note of the edit changes. Also, note the question-id of the previous and the newly created versions.
            8. Login to the student application and take the test."*/


            //Expected - 1. Question having the question-id of the published version of the question should get displayed in the test.
            int dataIndex = 195;
            String questionIdBeforeNewerVersion = null;
            String questionIdAfterNewerVersion = null;

            Assignments assignments= PageFactory.initElements(driver, Assignments.class);
            NewQuestionDataEntry newQuestionDataEntry= PageFactory.initElements(driver, NewQuestionDataEntry.class);
            String assessmentName = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
            String questionText = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));
            new Assignment().create(dataIndex);
            new Assignment().addQuestions(1951,"multipart",assessmentName);
            new Assignment().addQuestions(1952, "multipart", assessmentName);
            new Assignment().createNewerVersionQuestion(dataIndex,1);//Create New Version for a question
            new LoginUsingLTI().ltiLogin("195");//Login as an instructor
            new Assignment().assignToStudent(195);
            new LoginUsingLTI().ltiLogin("195_1");//Login as a student
            boolean isThereOldVersionQuestion = false;
            List<String> questionTextList  = getQuestionTextWhileAttempting(3,195);
            for(int a= 0;a<questionTextList.size();a++){
                if(questionTextList.get(a).equals(questionText)){
                    isThereOldVersionQuestion = true;
                    break;
                }
            }
            if(isThereOldVersionQuestion ==false){
                Assert.fail("Question having the question-id of the published version of the question should get displayed in the test.");
            }



            //Expected - 2 : Question having the question-id of newly created version of the question should not get displayed in the test.

            boolean isThereNewVersionQuestion = false;
            for(int a= 0;a<questionTextList.size();a++){
                if(questionTextList.get(a).equals("Updated"+questionText)){
                    isThereNewVersionQuestion = true;
                    break;
                }
            }
            if(isThereNewVersionQuestion ==true){
                Assert.fail("Question having the question-id of newly created version of the question should not get displayed in the test.");
            }


            //new Assignment().submitAssignmentAsStudent();
        }catch(Exception e){
            Assert.fail("Exception in the testcase 'verifyNotAbleTOCreateForUnpublishedMPQ' in the class 'MPQVersionTracker'",e);
        }
    }




    @Test(priority=14,enabled = true)
    public void verifyNewVersionsThatArePublished(){
        try{


            /*Row No - 196 : "1. Login to CMS.
            2. Open an assessment using the ""Manage Content"" tab.
            3. Navigate to a multipart question which has a ""Published"" version.
            4. Click on the ""+"" button for the ID drop-down.
            5. Select the option ""Versions"".
            6. Click on ""Create New Version"".
            7. Edit the newly created version and make a note of the edit changes. Also, note the question-id of the previous and the newly created versions.
            8. Publish the newly created version.
            9. Login to the student application and take the test."*/


            //Expected - 1. Question having the question-id of the newly published version of the question should get displayed in the test.
            int dataIndex = 196;
            String questionIdBeforeNewerVersion = null;
            String questionIdAfterNewerVersion = null;

            Assignments assignments= PageFactory.initElements(driver, Assignments.class);
            NewQuestionDataEntry newQuestionDataEntry= PageFactory.initElements(driver, NewQuestionDataEntry.class);
            String assessmentName = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
            String questionText = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));
            new Assignment().create(dataIndex);
            new Assignment().addQuestions(196,"multipart",assessmentName);
            new Assignment().addQuestions(196, "multipart", assessmentName);
            questionIdBeforeNewerVersion = new Assignment().createNewerVersionQuestion(dataIndex,1);//Create New Version for a question
            new LoginUsingLTI().ltiLogin("196");//Login as an instructor
            new Assignment().assignToStudent(196);
            new LoginUsingLTI().ltiLogin("196_1");//Login as a student
            boolean isThereOldVersionQuestion = false;
            List<String> questionTextList  = getQuestionTextWhileAttempting(3,196);
            for(int a= 0;a<questionTextList.size();a++){
                System.out.println("questionTextListtext : " + questionTextList.size());
                if(questionTextList.get(a).trim().equals("Updated"+questionText.trim())){
                    isThereOldVersionQuestion = true;
                    break;
                }
            }
            if(isThereOldVersionQuestion ==false){
                Assert.fail("Question having the question-id of the newly published version of the question should get displayed in the test.");
            }



            //Expected - 2. Question having the question-id of previous published version of the question should not get displayed in the test.

            boolean isThereNewVersionQuestion = false;
            for(int a= 0;a<questionTextList.size();a++){
                if(questionTextList.get(a).equals(questionText)){
                    isThereNewVersionQuestion = false;
                    break;
                }
            }
            if(isThereNewVersionQuestion ==true){
                Assert.fail("Question having the question-id of previous published version of the question should not get displayed in the test.");
            }


            //new Assignment().submitAssignmentAsStudent();
        }catch(Exception e){
            Assert.fail("Exception in the testcase 'verifyNotAbleTOCreateForUnpublishedMPQ' in the class 'MPQVersionTracker'",e);
        }
    }



    @Test(priority=15,enabled = true)
    public void checkVendorQABeAbleToViewUnpublishedContent(){
        try{


            /*ROw No - 204 : "1. Login to LS as a QA-Vendor-Student
            2. Go to eTextbook
            3. Start the static assignment"*/


            //Expected - 1. Student should be able to view the multipart question which is in QA status in static assessment
            //Expected - 2 : Student should be able to view the multipart question which is in Ready to Publish status in static assessment
            //Expected - 3 : Student should be able to view the multipart question which is in Published status in static assessment
            //Expected - 4 : Student should not be able to view multipart question other than QA, Ready to publish or Published status in static assignment
            //Expected - 5 :  Question content displaying, should be of latest version.

            int dataIndex = 2041;
            String questionIdBeforeNewerVersion = null;
            String questionIdAfterNewerVersion = null;

            Assignments assignments= PageFactory.initElements(driver, Assignments.class);
            NewQuestionDataEntry newQuestionDataEntry= PageFactory.initElements(driver, NewQuestionDataEntry.class);
            String assessmentName = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
            String questionText = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));
            String userId = ReadTestData.readDataByTagName("", "user_id", Integer.toString(204));

            new Assignment().create(dataIndex);
            new Assignment().addQuestions(2042,"multipart",assessmentName);
            new Assignment().addQuestions(2043, "multipart", assessmentName);
            new LoginUsingLTI().ltiLogin("204");//  Login as a student
            new DBConnect().Connect();
            DBConnect.st.executeUpdate("update t_user_role set permission = 'OP_QA' where user_id = (select id from t_user where username ='" + userId + LoginUsingLTI.appendChar + "');");
            DBConnect.st.executeUpdate("update t_user set password = '6ab14d3be1858b6e8f9f223b6980c7a6dc087a1d4871255f7fc366535762f2a90213d242f2eaa0ab' where username ='" + userId + LoginUsingLTI.appendChar + "';");
            new DirectLogin().directLoginWithCreditial(userId + LoginUsingLTI.appendChar, "snapwiz", dataIndex);
            Thread.sleep(9000);
            new Assignment().startStaticAssignmentFromeTextbook(2041, 1);
            List<String> questionStatusList = new Assignment().getMPQQuestionsStatus(204);
            for(int a=0;a<questionStatusList.size();a++){
                System.out.println("questionList : " + questionStatusList.get(a));
            }
            ArrayList<String> expextedquestionStatusList = new ArrayList<>(Arrays.asList("Status: QA", "Status: Ready to Publish"));


            if(!(CollectionUtils.isEqualCollection(expextedquestionStatusList,questionStatusList))){
                Assert.fail("Question Status is not same as per expected");
            }



        }catch(Exception e){
            Assert.fail("Exception in the testcase 'verifyNotAbleTOCreateForUnpublishedMPQ' in the class 'MPQVersionTracker'",e);
        }
    }



    @Test(priority=16,enabled = true)
    public void checkVendorQABeAbleToViewUnpublishedContentAfterAssigningToClassSections(){
        try{

            /*Pre Conditions: "1. Course content should have assignment with questions in QA, Ready to Publish, Published and other states
            2. Instructor has assigned the assignment to class section"*/



            /*Row No - 209 : "1. Login to LS as a QA-Vendor-Student
            2. Start the assignment"*/


            //Expected - 1. Student should be able to view the multipart question which is in QA status in static assessment
            //Expected - 2 : Student should be able to view the multipart question which is in Ready to Publish status in static assessment
            //Expected - 3 : Student should be able to view the multipart question which is in Published status in static assessment
            //Expected - 4 : Student should not be able to view multipart question other than QA, Ready to publish or Published status in static assignment
            //Expected - 5 :  Question content displaying, should be of latest version.

            int dataIndex = 2091;
            String questionIdBeforeNewerVersion = null;
            String questionIdAfterNewerVersion = null;

            Assignments assignments= PageFactory.initElements(driver, Assignments.class);
            NewQuestionDataEntry newQuestionDataEntry= PageFactory.initElements(driver, NewQuestionDataEntry.class);
            String assessmentName = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
            String questionText = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));
            String userId = ReadTestData.readDataByTagName("", "user_id", Integer.toString(209));


            new Assignment().create(dataIndex);
            new Assignment().addQuestions(2092,"multipart",assessmentName);
            new Assignment().addQuestions(2093, "multipart", assessmentName);
            new DirectLogin().loginAsQAVendorRole("209");//Log in as QA Vendor instructor role
            new Assignment().assignToStudent(209);//Assign tit to class sections
            new DirectLogin().loginAsQAVendorRole("209_1");//Log in as QA Vendor student role
            new Assignment().openAssignmentFromCourseStream("209");
            List<String> questionStatusList = getMPQQuestionsStatusFromStudentSide(209);
            for(int a=0;a<questionStatusList.size();a++){
                System.out.println("questionList : " + questionStatusList.get(a));
            }
            ArrayList<String> expextedquestionStatusList = new ArrayList<>(Arrays.asList("Status: QA", "Status: Ready to Publish"));
            if(!(CollectionUtils.isEqualCollection(expextedquestionStatusList,questionStatusList))){
                Assert.fail("Question Status is not same as per expected");
            }
        }catch(Exception e){
            Assert.fail("Exception in the testcase 'checkVendorQABeAbleToViewUnpublishedContentAfterAssigningToClassSections' in the class 'MPQVersionTracker'",e);
        }
    }



    @Test(priority=17,enabled = true)
    public void checkVendorQABeAbleToViewUnpublishedContentOnInstructorRole(){
        try{


            /*Row No - 214 : "1. Login as QA-Vendor-instructor to LS course
            2. Go to eTextbook and click on static assessment"*/


            //Expected - 1. Questions of status QA,Ready to Publish or Published should only display
            //Expected - 2 : Multipart Questions of status QA,Ready to Publish or Published should only display
            //Expected - 3 :  Question content displaying, should be of latest version.

            int dataIndex = 2141;
            String questionIdBeforeNewerVersion = null;
            String questionIdAfterNewerVersion = null;

            Assignments assignments= PageFactory.initElements(driver, Assignments.class);
            NewQuestionDataEntry newQuestionDataEntry= PageFactory.initElements(driver, NewQuestionDataEntry.class);
            String assessmentName = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
            String questionText = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));
            String userId = ReadTestData.readDataByTagName("", "user_id", Integer.toString(214));

            new Assignment().create(dataIndex);
            new Assignment().addQuestions(2142,"multipart",assessmentName);
            new Assignment().addQuestions(2143, "multipart", assessmentName);
            new LoginUsingLTI().ltiLogin("214");//  Login as an instructoe
            new DBConnect().Connect();
            DBConnect.st.executeUpdate("update t_user_role set permission = 'OP_QA' where user_id = (select id from t_user where username ='" + userId + LoginUsingLTI.appendChar + "');");
            DBConnect.st.executeUpdate("update t_user set password = '6ab14d3be1858b6e8f9f223b6980c7a6dc087a1d4871255f7fc366535762f2a90213d242f2eaa0ab' where username ='" + userId + LoginUsingLTI.appendChar + "';");
            new DirectLogin().directLoginWithCreditial(userId + LoginUsingLTI.appendChar, "snapwiz", dataIndex);
            Thread.sleep(9000);
            List<String> questionStatusList = new ArrayList<>();
            new Assignment().startStaticAssignmentFromeTextbook(2141, 1);
            Thread.sleep(3000);
            List<WebElement> questionStatusElementsList = driver.findElements(By.className("ls-question-status-indicator-wrapper"));
            System.out.println("questionStatusElementsList : " + questionStatusElementsList.size());
            for(int a= 0;a<questionStatusElementsList.size();a++){
                questionStatusList.add(questionStatusElementsList.get(a).getText());
                questionStatusElementsList = driver.findElements(By.className("ls-question-status-indicator-wrapper"));
                ((JavascriptExecutor) driver).executeScript("scroll(0, 250)");
            }

            for(int a=0;a<questionStatusList.size();a++){
                System.out.println("questionList : " + questionStatusList.get(a));
            }
            ArrayList<String> expextedquestionStatusList = new ArrayList<>(Arrays.asList("Status: QA",""));


            if(!(CollectionUtils.isEqualCollection(expextedquestionStatusList,questionStatusList))){
                Assert.fail("Question Status is not same as per expected");
            }

        }catch(Exception e){
            Assert.fail("Exception in the testcase 'verifyNotAbleTOCreateForUnpublishedMPQ' in the class 'MPQVersionTracker'",e);
        }
    }



    @Test(priority=18,enabled = true)
    public void checkVendorQABeAbleToViewUnpublishedChapter(){
        try{


            /*Row No - 217 : "1. Login to LS as a QA-Vendor-Student
            2. Go to eTextbook
            3. Start the static assignment"*/

             //Expected - 1 : Student should be able to view the multipart question which is in QA status in static assessment
            //Expected -2 : Student should be able to view the multipart question which is in Ready to Publish status in static assessment
            //Expected - 3 : Student should be able to view the multipart question which is in Published status in static assessment
            //Expected - 4 : Student should not be able to view multipart question other than QA, Ready to publish or Published status in static assignment
            //Expected - 5:  Question content displaying, should be of latest version.

            int dataIndex = 2171;
            String questionIdBeforeNewerVersion = null;
            String questionIdAfterNewerVersion = null;

            Assignments assignments= PageFactory.initElements(driver, Assignments.class);
            NewQuestionDataEntry newQuestionDataEntry= PageFactory.initElements(driver, NewQuestionDataEntry.class);
            String assessmentName = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
            String questionText = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));
            String userId = ReadTestData.readDataByTagName("", "user_id", Integer.toString(217));

            new Assignment().createChapter(217);
            new Assignment().create(dataIndex);
            new Assignment().addQuestions(2172,"multipart",assessmentName);
            new Assignment().addQuestions(2173, "multipart", assessmentName);
            new LoginUsingLTI().ltiLogin("217");//Login as an instructor
            new DBConnect().Connect();
            DBConnect.st.executeUpdate("update t_user_role set permission = 'OP_QA' where user_id = (select id from t_user where username ='"+userId+LoginUsingLTI.appendChar+"');");
            DBConnect.st.executeUpdate("update t_user set password = '6ab14d3be1858b6e8f9f223b6980c7a6dc087a1d4871255f7fc366535762f2a90213d242f2eaa0ab' where username ='"+userId+LoginUsingLTI.appendChar+"';");
            new DirectLogin().directLoginWithCreditial(userId+LoginUsingLTI.appendChar,"snapwiz",dataIndex);
            new Navigator().NavigateTo("e-Textbook");
            new Assignment().startStaticAssignmentFromeTextbook(2171, 1);
            List<String> questionStatusList = new Assignment().getMPQQuestionsStatus(217);
            for(int a=0;a<questionStatusList.size();a++){
                System.out.println("questionList : " + questionStatusList.get(a));
            }
            ArrayList<String> expextedquestionStatusList = new ArrayList<>(Arrays.asList("Status: QA", "Status: Ready to Publish"));
            if(!(CollectionUtils.isEqualCollection(expextedquestionStatusList,questionStatusList))){
                Assert.fail("Question Status is not same as per expected");
            }
        }catch(Exception e){
            Assert.fail("Exception in the testcase 'verifyNotAbleTOCreateForUnpublishedMPQ' in the class 'MPQVersionTracker'",e);
        }
    }





    @Test(priority=19,enabled = true)
    public void checkVendorQABeAbleToViewUnpublishedChapterAfterAssigningToClassSections(){
        try{

            /*Pre Conditions: "1. Course content should have assignment with questions in QA, Ready to Publish, Published and other states
            2. Instructor has assigned the assignment to class section"*/



            /*Row No - 222 : "1. Login to LS as a QA-Vendor-Student
            2. Start the assignment"*/


            //Expected - 1. Student should be able to view the multipart question which is in QA status in static assessment
            //Expected - 2 : Student should be able to view the multipart question which is in Ready to Publish status in static assessment
            //Expected - 3 : Student should be able to view the multipart question which is in Published status in static assessment
            //Expected - 4 : Student should not be able to view multipart question other than QA, Ready to publish or Published status in static assignment
            //Expected - 5 :  Question content displaying, should be of latest version.

            int dataIndex = 2221;
            String questionIdBeforeNewerVersion = null;
            String questionIdAfterNewerVersion = null;

            Assignments assignments= PageFactory.initElements(driver, Assignments.class);
            NewQuestionDataEntry newQuestionDataEntry= PageFactory.initElements(driver, NewQuestionDataEntry.class);
            String assessmentName = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
            String questionText = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));
            String userId = ReadTestData.readDataByTagName("", "user_id", Integer.toString(222));

            new Assignment().createChapter(222);
            new Assignment().create(dataIndex);
            new Assignment().addQuestions(2222, "multipart", assessmentName);
            new Assignment().addQuestions(2223, "multipart", assessmentName);
            new DirectLogin().loginAsQAVendorRole("222");//Log in as QA Vendor instructor role
            new Assignment().assignToStudent(222);//Assign tit to class sections*/
            new DirectLogin().loginAsQAVendorRole("222_1");//Log in as QA Vendor student role
            new Assignment().openAssignmentFromCourseStream("222");
            Thread.sleep(3000);
            List<String> questionStatusList = getMPQQuestionsStatusFromStudentSide(222);
            for(int a=0;a<questionStatusList.size();a++){
                System.out.println("questionList : " + questionStatusList.get(a));
            }
            ArrayList<String> expextedquestionStatusList = new ArrayList<>(Arrays.asList("Status: QA", "Status: Ready to Publish"));
            if(!(CollectionUtils.isEqualCollection(expextedquestionStatusList,questionStatusList))){
                Assert.fail("Question Status is not same as per expected");
            }
        }catch(Exception e){
            Assert.fail("Exception in the testcase 'checkVendorQABeAbleToViewUnpublishedContentAfterAssigningToClassSections' in the class 'MPQVersionTracker'",e);
        }
    }


    @Test(priority=20,enabled = true)
    public void deactivateMultiPartQuestionInAssessment(){
        try{

            /*Row No - 231 :  "1. Login to CMS.
            2. Open an assessment using the ""Manage Content"" tab.
            3. Navigate to a question which doesn't have a ""Published"" version
            4. Select the option ""Published"" from the Question-Status drop-down and save the question.
            5. Click on the ""+"" button for ""Question ID"" in the QE of multipart question type and click on Revision
            6. Click on the Deactivate button for version V1"*/


            //Expected - 1:  Multipart question should get deactivated and should get label "Deactivated" near Question status dropdown
            int dataIndex = 231;

            Assignments assignments= PageFactory.initElements(driver, Assignments.class);
            NewQuestionDataEntry newQuestionDataEntry= PageFactory.initElements(driver, NewQuestionDataEntry.class);
            String assessmentName = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
            String questionText = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(2332));
            String userId = ReadTestData.readDataByTagName("", "user_id", Integer.toString(222));


            new Assignment().create(2331);
            new Assignment().addQuestions(2332, "multipart", assessmentName);
            new Assignment().addQuestions(2333, "multipart", assessmentName);
            new OpenSearchPage().openSearchPage();
            new OpenSearchPage().searchquestion(questionText);
            deActivateStaticAssessment(2332);//deactivate a question
            Assert.assertEquals("Deactivated",assignments.label_deactivated.getText(),"Multipart question should get deactivated and should get label \"Deactivated\" near Question status dropdown");

            new LoginUsingLTI().ltiLogin("233_1");//login as an instructor
            new Navigator().NavigateTo("e-Textbook");
            new Assignment().startStaticAssignmentFromeTextbook(2331,0);
            List<WebElement> questionELementsList = driver.findElements(By.cssSelector("label[class = 'control-label redactor_editor']"));
            System.out.println("questionELementsList size : " + questionELementsList.size());
            for(int a=0;a<questionELementsList.size();a++){
                System.out.println("Question : " + questionELementsList.get(a).getText());
                if(questionELementsList.get(a).getText().equals(questionText)){
                    Assert.fail("Instructor should not be able to view the multipart question");
                    break;
                }
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",questionELementsList.get(a));
                Thread.sleep(3000);
            }



            /*Row No - 234 : "9. Login as student
            10. Go to eTextbook and attempt the static assessment for which author has deactivated the multipart question"*/

            //Expected - Student should not get the multipart question while attempting the static assessment
            new LoginUsingLTI().ltiLogin("233_2");//Login as a student
            new Assignment().startStaticAssignmentFromeTextbook(2331,0);
            List<String> mpqquestionsList = attemptMPQAssessmentAndGetQuestions(2);
            if(mpqquestionsList.contains(questionText)){
                Assert.fail("Student should not get the multipart question while attempting the static assessment");
            }

        }catch(Exception e){
            Assert.fail("Exception in the testcase 'checkVendorQABeAbleToViewUnpublishedContentAfterAssigningToClassSections' in the class 'MPQVersionTracker'",e);
        }
    }


    @Test(priority=21,enabled = true)
    public void checkIfStudentHasAlreadyAttemptedMPQAssessment(){
        try{

            /*Row No - 235 : "1. Login as student
            2. Go to Performance report page
            3. Click on the question part card of mutipart question"*/

            //Expected -  Message should appear on the top card view"This question has been deactivated"

            int dataIndex = 2351;
            Assignments assignments= PageFactory.initElements(driver, Assignments.class);
            NewQuestionDataEntry newQuestionDataEntry= PageFactory.initElements(driver, NewQuestionDataEntry.class);
            String assessmentName = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
            String questionText = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(2352));
            String userId = ReadTestData.readDataByTagName("", "user_id", Integer.toString(222));

            new Assignment().create(2351);
            new Assignment().addQuestions(2352, "multipart", assessmentName);
            new Assignment().addQuestions(2353, "multipart", assessmentName);
            new Assignment().addQuestions(2353, "multipart", assessmentName);
            new Assignment().addQuestions(2353, "multipart", assessmentName);
            new Assignment().addQuestions(2353, "multipart", assessmentName);
            new Assignment().addQuestions(2353, "multipart", assessmentName);
            new Assignment().addQuestions(2353, "multipart", assessmentName);
            new Assignment().addQuestions(2353, "multipart", assessmentName);
            new Assignment().addQuestions(2353, "multipart", assessmentName);
            new Assignment().addQuestions(2353, "multipart", assessmentName);
            new Assignment().addQuestions(2353, "multipart", assessmentName);

            new LoginUsingLTI().ltiLogin("235_2");//Login as a student
            new Assignment().startStaticAssignmentFromeTextbook(2351,0);
            List<String> mpqquestionsList = attemptMPQAssessmentAndGetQuestions(12);
            for(int a=0;a<mpqquestionsList.size();a++){
                System.out.println("mpqquestionsList : " + mpqquestionsList.get(a));

            }
            new OpenSearchPage().openSearchPage();
            new OpenSearchPage().searchquestion(questionText);
            deActivateStaticAssessment(2352);//deactivate a question
            new LoginUsingLTI().ltiLogin("235_2");//Login as a student
            new Assignment().startStaticAssignmentFromeTextbook(2351,0);
            new Click().clickBycssselector("div[class='next-chart']");
            Thread.sleep(3000);
            new Click().clickBycssselector("div[class='next-chart']");
            Thread.sleep(9000);
            driver.findElement(By.cssSelector("g.highcharts-series.highcharts-tracker > rect")).click();
            Thread.sleep(3000);
            actual = assignments.getNotificationMessage().getText();
            expected = "This question is deactivated and is no longer part of this assignment/practice.";
            Assert.assertEquals(actual,expected,"Message should appear on the top card view\"This question has been deactivated\"");
        }catch(Exception e){
            Assert.fail("Exception in the testcase 'checkVendorQABeAbleToViewUnpublishedContentAfterAssigningToClassSections' in the class 'MPQVersionTracker'",e);
        }
    }




    @Test(priority=22,enabled = true)
    public void deactivateMultiPartQuestionInAssignment(){
        try{

           /*Row No - 236 : "1. Login to CMS.
            2. Open an static assessment reserved for assignment
            3. Navigate to a question which doesn't have a ""Published"" version
            4. Select the option ""Published"" from the Question-Status drop-down and save the question.
            5. Click on the ""+"" button for ""Question ID"" in the QE of multipart question type and click on Revision
            6. Click on the Deactivate button for version V1"*/

            //Expected - 1 : Multipart question should get deactivated and should get label "Deactivated" near Question status dropdown
            int dataIndex = 236;

            Assignments assignments= PageFactory.initElements(driver, Assignments.class);
            NewQuestionDataEntry newQuestionDataEntry= PageFactory.initElements(driver, NewQuestionDataEntry.class);
            String assessmentName = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
            String questionText = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(2362));


            new Assignment().create(2361);
            new Assignment().addQuestions(2362, "multipart", assessmentName);
            new Assignment().addQuestions(2363, "multipart", assessmentName);
            new OpenSearchPage().openSearchPage();
            new OpenSearchPage().searchquestion(questionText);
            deActivateStaticAssessment(2362);//deactivate a question
            Assert.assertEquals("Deactivated",assignments.label_deactivated.getText(),"Multipart question should get deactivated and should get label \"Deactivated\" near Question status dropdown");



            /*Row No - 237 : "7. Login as instructor
            8. Go to Question Banks page and select the static assessment for which author has deactivated the multipart question"*/

            //Expected - Instructor should not be able to view the multipart question

            new LoginUsingLTI().ltiLogin("236_1");//login as an instructor
            new Navigator().NavigateTo("Question Banks");
            new Assignment().searchAssessmentInQuestionBanks(2361);
            new Click().clickBycssselector("span[class='ls-preview-wrapper action-links']");
            Thread.sleep(5000);
            List<WebElement> questionELementsList = driver.findElements(By.cssSelector("label[class = 'control-label redactor_editor']"));
            System.out.println("questionELementsList size : " + questionELementsList.size());
            for(int a=0;a<questionELementsList.size();a++){
                System.out.println("Question : " + questionELementsList.get(a).getText());
                if(questionELementsList.get(a).getText().equals(questionText)){
                    Assert.fail("Instructor should not be able to view the multipart question");
                    break;
                }
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",questionELementsList.get(a));
                Thread.sleep(3000);
            }



        }catch(Exception e){
            Assert.fail("Exception in the testcase 'checkVendorQABeAbleToViewUnpublishedContentAfterAssigningToClassSections' in the class 'MPQVersionTracker'",e);
        }

    }



    @Test(priority=23,enabled = true, groups = {"bugsLogged"})
    public void checkIfStudentHasAlreadyAttemptedMPQAssignment(){
        try{

            /*Row No - 238: "1. Login as student
            2. Go to Performance summary page
            3. Click on the question part card of mutipart question"*/

            //Expected -  Message should appear on the top card view"This question has been deactivated"

            int dataIndex = 2381;
            Assignments assignments= PageFactory.initElements(driver, Assignments.class);
            NewQuestionDataEntry newQuestionDataEntry= PageFactory.initElements(driver, NewQuestionDataEntry.class);
            String assessmentName = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
            String questionText = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(2382));
            String userId = ReadTestData.readDataByTagName("", "user_id", Integer.toString(222));

            new Assignment().create(2381);
            new Assignment().addQuestions(2382, "multipart", assessmentName);
            new Assignment().addQuestions(2383, "multipart", assessmentName);
            new Assignment().addQuestions(2383, "multipart", assessmentName);
            new Assignment().addQuestions(2383, "multipart", assessmentName);
            new Assignment().addQuestions(2383, "multipart", assessmentName);
            new Assignment().addQuestions(2383, "multipart", assessmentName);
            new Assignment().addQuestions(2383, "multipart", assessmentName);
            new Assignment().addQuestions(2383, "multipart", assessmentName);
            new Assignment().addQuestions(2383, "multipart", assessmentName);
            new Assignment().addQuestions(2383, "multipart", assessmentName);
            new Assignment().addQuestions(2383, "multipart", assessmentName);


            new LoginUsingLTI().ltiLogin("238_1");//Login as an instructor
            new Assignment().assignToStudent(2381);//Assign it to a class section



            new LoginUsingLTI().ltiLogin("238_2");//Login as a student
            new Assignment().openAssignmentFromCourseStream("2381");
            List<String> mpqquestionsList = attemptMPQAssignmentAndGetQuestionsFrom(12);
            for(int a=0;a<mpqquestionsList.size();a++){
                System.out.println("mpqquestionsList : " + mpqquestionsList.get(a));
            }
            new OpenSearchPage().openSearchPage();
            new OpenSearchPage().searchquestion(questionText);
            deActivateStaticAssessment(2382);//deactivate a question
            new LoginUsingLTI().ltiLogin("238_2");//Login as a student
            //new Assignment().startStaticAssignmentFromeTextbook(2381,0);
            new Assignment().openAssignmentFromCourseStream("2381");
            Thread.sleep(9000);
            new UIElement().waitAndFindElement(driver.findElements(By.xpath("//div[contains(@id,'question_card_id_')]")).get(1));
            new Click().clickByElement(driver.findElements(By.xpath("//div[contains(@id,'question_card_id_')]")).get(1));
            Thread.sleep(3000);
            actual = assignments.getNotificationMessage().getText();
            expected = "This question is deactivated and is no longer part of this assignment/practice.";
            Assert.assertEquals(actual,expected,"Message should appear on the top card view\"This question has been deactivated\"");
            new LoginUsingLTI().ltiLogin("238_1");//Login as an instructor
            new Navigator().NavigateTo("Question Banks");
            new Assignment().searchAssessmentInQuestionBanks(2381);
            new Click().clickBycssselector("span[class='ls-preview-wrapper action-links']");
            Thread.sleep(5000);
            List<WebElement> questionELementsList = driver.findElements(By.cssSelector("label[class = 'control-label redactor_editor']"));
            System.out.println("questionELementsList size : " + questionELementsList.size());
            for(int a=0;a<questionELementsList.size();a++){
                System.out.println("Question : " + questionELementsList.get(a).getText());
                if(questionELementsList.get(a).getText().equals(questionText)){
                    Assert.fail("Instructor should not be able to view the multipart question");
                    break;
                }
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",questionELementsList.get(a));
                Thread.sleep(3000);
            }

        }catch(Exception e){
            Assert.fail("Exception in the testcase 'checkVendorQABeAbleToViewUnpublishedContentAfterAssigningToClassSections' in the class 'MPQVersionTracker'",e);
        }
    }



    public void validateNewLinkOptions(){
        try{
            Assignments assignments= PageFactory.initElements(driver, Assignments.class);
            new UIElement().waitAndFindElement(assignments.new_Link);
            Thread.sleep(2000);
            assignments.new_Link.click();
            Thread.sleep(3000);
            List<WebElement> linkOptionsElementsList = driver.findElements(By.className("question-options-text"));
            System.out.println("linkOptionsElementsList.size : " + linkOptionsElementsList.size());
            for(int a=0;a<linkOptionsElementsList.size();a++){
                String options = linkOptionsElementsList.get(a).getText();
                System.out.println("options : " + options);
            }
            if(!(linkOptionsElementsList.get(0).getText().equals("New") && linkOptionsElementsList.get(1).getText().equals("Duplicate") && linkOptionsElementsList.get(2).getText().equals("Revisions"))){
                Assert.fail("New, Duplicate, Revisions options are not available in dropdown");
            }
        }catch (Exception e){
            Assert.fail("Exception in the method 'validateNewLinkOptions' in the class 'MPQVersionTracker'",e);
        }

    }



    public void validateQuestionStatusComboBOx(){
        try{
            Assignments assignments= PageFactory.initElements(driver, Assignments.class);
            assignments.combBox_publish.click();
            Thread.sleep(3000);
            List<WebElement> linkOptionsElementsList = driver.findElements(By.xpath("(//ul[@class='sbOptions'])[6]//li"));
            System.out.println("linkOptionsElementsList.size : " + linkOptionsElementsList.size());
            for(int a=0;a<linkOptionsElementsList.size();a++){
                String options = linkOptionsElementsList.get(a).getText();
                System.out.println("options : " + options);
            }
            if(!(linkOptionsElementsList.get(0).getText().equals("Draft") && linkOptionsElementsList.get(1).getText().equals("Draft - Pending Images") && linkOptionsElementsList.get(2).getText().equals("Accuracy Check") && linkOptionsElementsList.get(3).getText().equals("QA") && linkOptionsElementsList.get(4).getText().equals("Need Revision") && linkOptionsElementsList.get(5).getText().equals("Approve") && linkOptionsElementsList.get(6).getText().equals("Ready to Publish"))){
                Assert.fail("New, Duplicate, Revisions options are not available in dropdown");
            }
        }catch (Exception e){
            Assert.fail("Exception in the method 'validateNewLinkOptions' in the class 'MPQVersionTracker'",e);
        }

    }


    public void validateRevisionsOption(){
        try{
            Assignments assignments= PageFactory.initElements(driver, Assignments.class);
            new UIElement().waitAndFindElement(By.className("question-set-add-text"));
            Thread.sleep(2000);
            assignments.new_Link.click();
            assignments.option_Revisions.click();

            if(driver.findElements(By.className("cms-question-revision-wrapper")).size()==0){
                Assert.fail("Revisions side-bar should appear on the right");
            }

            if(!(driver.findElement(By.className("cms-question-revision-wrapper")).isDisplayed())){
                Assert.fail("Revisions side-bar should appear on the right");
            }
        }catch (Exception e){
            Assert.fail("Exception in the method 'validateRevisionsOption' in the class 'MPQVersionTracker'",e);
        }

    }


    public List<String> getQuestionTextWhileAttempting(int totalNumberOfQuestions, int dataIndex){
        List<String> questionTextList = new ArrayList<String>();
        try{
            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));

            new Navigator().NavigateTo("Course Stream");
            int helppage = driver.findElements(By.className("close-help-page")).size();
            if (helppage == 1)
                driver.findElement(By.className("close-help-page")).click();
            //new Click().clickbyxpath("//span[contains(@title,'"+assessmentname+"')]");
            new Click().clickBycssselector("span[assignmentname='" + assessmentname + "']");//click on Assignment
            Thread.sleep(5000);
            Assignments assignments= PageFactory.initElements(driver, Assignments.class);

            for(int a= 0;a<totalNumberOfQuestions;a++){
                questionTextList.add(assignments.MPQquestionText.getText());
                if(!(a == totalNumberOfQuestions-1)) {
                    assignments.button_NextQuestion.click();
                    Thread.sleep(3000);
                }
            }


        }catch (Exception e){
            Assert.fail("Exception in the method 'validateRevisionsOption' in the class 'MPQVersionTracker'",e);
        }

        return questionTextList;

    }

    public List<String> getMPQQuestionsStatusFromStudentSide(int dataIndex)
    {
        List<String> statusList = new ArrayList<String>();
        Assignments assignments= PageFactory.initElements(driver, Assignments.class);
        try
        {

            for (int a=0;a<2;a++){
                statusList.add(assignments.label_status.getText());
                new Click().clickbylinkText("Next Question");
                Thread.sleep(3000);
            }
        }
        catch(Exception e)
        {
            new Screenshot().captureScreenshotFromAppHelper();
            Assert.fail("Exception in create in Apphelper getMPQQuestionStatus in class AssignmentCreate",e);
        }
        Collections.sort(statusList);
        return statusList;
    }


    public List<String> attemptMPQAssessmentAndGetQuestions(int numberOfQuestionsToBeAttempted)
    {
        List<String> statusList = new ArrayList<String>();
        Assignments assignments= PageFactory.initElements(driver, Assignments.class);
        try
        {
            Thread.sleep(3000);
            for (int a=0;a<numberOfQuestionsToBeAttempted;a++){
                statusList.add(new TextFetch().textfetchbycssselector("label[class='control-label redactor_editor']"));
                new Click().clickBycssselector("input[value = 'Submit Answer']");
                if(!(a==numberOfQuestionsToBeAttempted-1)){
                    new UIElement().waitAndFindElement(By.cssSelector("input[value = 'Next Question']"));
                    new Click().clickBycssselector("input[value = 'Next Question']");
                }else{
                    new Click().clickBycssselector("input[value = 'Finish']");

                }
            }
        }
        catch(Exception e)
        {
            new Screenshot().captureScreenshotFromAppHelper();
            Assert.fail("Exception in create in Apphelper getMPQQuestionStatus in class AssignmentCreate",e);
        }
        Collections.sort(statusList);
        return statusList;
    }


    public List<String> attemptMPQAssignmentAndGetQuestionsFrom(int numberOfQuestionsToBeAttempted)
    {
        List<String> statusList = new ArrayList<String>();
        Assignments assignments= PageFactory.initElements(driver, Assignments.class);
        try
        {
            Thread.sleep(3000);
            for (int a=0;a<numberOfQuestionsToBeAttempted;a++){
                statusList.add(new TextFetch().textfetchbycssselector("label[class='control-label redactor_editor']"));
                //new Click().clickBycssselector("input[value = 'Submit Answer']");
                if(!(a==numberOfQuestionsToBeAttempted-1)){
                    new UIElement().waitAndFindElement(By.linkText("Next Question"));
                    new Click().clickbylinkText("Next Question");
                    Thread.sleep(1000);
                }else{
                    new UIElement().waitAndFindElement(By.linkText("Finish Assignment"));
                    new Click().clickbylinkText("Finish Assignment");
                    Thread.sleep(1000);
                }
            }
        }
        catch(Exception e)
        {
            new Screenshot().captureScreenshotFromAppHelper();
            Assert.fail("Exception in create in Apphelper getMPQQuestionStatus in class AssignmentCreate",e);
        }
        Collections.sort(statusList);
        return statusList;
    }


    public List<String> getQuestionsinAssignmentFromInstructorSide(int numberOfQuestionsToBeAttempted)
    {
        List<String> statusList = new ArrayList<String>();
        Assignments assignments= PageFactory.initElements(driver, Assignments.class);
        try
        {
            Thread.sleep(3000);
            for (int a=0;a<numberOfQuestionsToBeAttempted;a++){
                statusList.add(new TextFetch().textfetchbycssselector("label[class='control-label redactor_editor']"));
                new Click().clickBycssselector("input[value = 'Submit Answer']");
                if(!(a==numberOfQuestionsToBeAttempted-1)){
                    new UIElement().waitAndFindElement(By.cssSelector("input[value = 'Next Question']"));
                    new Click().clickBycssselector("input[value = 'Next Question']");
                }else{
                    new Click().clickBycssselector("input[value = 'Finish']");

                }
            }
        }
        catch(Exception e)
        {
            new Screenshot().captureScreenshotFromAppHelper();
            Assert.fail("Exception in create in Apphelper getMPQQuestionStatus in class AssignmentCreate",e);
        }
        Collections.sort(statusList);
        return statusList;
    }

    public void deActivateStaticAssessment(int dataIndex)
    {
        try {
            Actions action2 = new Actions(driver);
            List<WebElement> we2 = driver.findElements(By.xpath("//div[@id = 'question-check-box-div']//label"));
            action2.moveToElement(we2.get(0)).build().perform();// Mouse Over on Edit icon on first question
            Thread.sleep(2000);
            action2.moveToElement( driver.findElement(By.xpath("//div[@class='edit-question-content']"))).click().build().perform();
            Thread.sleep(2000);
            new Click().clickByid("questionOptions"); //click on question option
            Thread.sleep(2000);
            new Click().clickByid("questionRevisions"); // click on revisions
            Thread.sleep(2000);
            new Click().clickByid("cms-question-revision-deactivate-button");//click on the deactivate button
        } catch (InterruptedException e) {
            Assert.fail("Exception in method deActivateStaticAssessment of class StudentSubmittedQs",e);
        }
    }


}
