package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT17.R1721KMZ;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

/*
 * Created by snapwiz on 03-Dec-14.
 */
public class KMZFileTypeResourceUploader_Mentor_DIfferentUserId extends Driver{

    String actual = null;
    String expected = null;
    @Test(priority =1,enabled = true)
    public void reservedForAssignmentOptionAsUncheckedInUploadResourcePopUp(){

        /*Row No - 3 : "1. Login as an instructor to LS course
        2. Access navigator dropdown and select resources
        3. Select My Library tab on Resources page
        4. Click on upload resource button and upload file with .kmz extension"*/
        //Expected - File should be uploaded successfully with filename.kmz extension in the Upload resource pop-up

        try{
            String resourceName =  ReadTestData.readDataByTagName("", "resourcesname", "3");
            System.out.println("Resource Name : " + resourceName);
            new LoginUsingLTI().ltiLogin("3");// Login as an 'Instructor' with AJ_1 sections
            new UploadResources().uploadResources("3",true,true,true);
            actual = new TextFetch().textfetchbyxpath("//div[@id = 'top-notification-Bar']//div[@class='ls-notification-text-span']");
            expected = "The resource is successfully created.";
            Assert.assertEquals(actual,expected,"File is not uploaded successfully with filename.kmz extension in the Upload resource pop-up");



            //Expected: KMZ file with more than 25MB size should not get uploaded and show Robo-notification saying "There was a problem while uploading a file.Please try again later"


            //Row No - 5 : 5. Do not select the checkbox of “Reserved for assignment” and do all the mandatory actions and click on Save.
            //Expected - Instructor should be able to save the resource.
            new UploadResources().uploadResources("3",false,true,true);
            actual = new TextFetch().textfetchbyxpath("//div[@id = 'top-notification-Bar']//div[@class='ls-notification-text-span']");
            expected = "The resource is successfully created.";
            Assert.assertEquals(actual,expected,"File is not uploaded successfully with filename.kmz extension in the Upload resource pop-up");
            if(new BooleanValue().verifyNotElementPresentByXpath("//div[normalize-space(@class) ='ls-assessment-item-sectn-right ls-my-resource-item-section']//div[@title = '"+resourceName+LoginUsingLTI.appendChar+"']")){
                Assert.fail("File is not saved successfully with filename.kmz extension in the 'My Resources' Tab");
            }




            //Expected - Uploaded Resource with .kmz extension should show unique icon in My Library tab
            if(new BooleanValue().verifyNotElementPresentByXpath("//div[normalize-space(@class)='ls-assessment-item-title ls-assesment-title-text-ellipsis']//div[normalize-space(@class)='ls-resource-doctypes ls-resource-kml']//..//div[@title = '"+resourceName+LoginUsingLTI.appendChar+"']")){
                Assert.fail("Uploaded Resource with .kmz extension should show unique icon in My Library tab");

            }

            //Row No - 7 : 6. Select eTextbook in navigation drop-down and Navigate to  lesson from the level where resource is added.
            //Expected  - Instructor should be able to view the resource under Resource tab at right side of the lesson.
            new Navigator().NavigateTo("e-Textbook");// Navigate to 'e-Textbook'
            new Click().clickbyxpath("//a[contains(text(),'The Science of Biology')]");//Clck on the chapter
            new Navigator().navigateToTab("Resources");//Navigate to 'Resources'
            if(new BooleanValue().verifyNotElementPresentByXpath("//ul[@class='resource-content-posts']//a[@title = '"+resourceName+LoginUsingLTI.appendChar+"']")){
                Assert.fail("Instructor should be able to view the resource under Resource tab at right side of the lesson.");
            }


            //Expected  - Instructor' Label should be displayed for the resource posted.
            if(new BooleanValue().verifyNotElementPresentByXpath("//div[@class='ls-right-user-content']//a[@title = '"+resourceName+LoginUsingLTI.appendChar+"']//..//..//..//span[text() = 'Instructor']")){
                Assert.fail("Instructor' Label should be displayed for the resource posted.");
            }



            //Expected - Uploaded Resource with .kmz extension should show unique icon
            if(new BooleanValue().verifyNotElementPresentByXpath("//i[normalize-space(@class) = 'ls-fileicon-img ls-right-assignment-kmz']//..//a[@title = '"+resourceName+LoginUsingLTI.appendChar+"']")){
                Assert.fail("Uploaded Resource with .kmz extension should show unique icon in 'Resources' tab");
            }



            /*Row No - 10 : "7. Click on the right side arrow of Resource under Resources tab
            8. Click on Open button and click on the resource link"*/
            //Expected  - Clicking on the open button should open in new tab
            new Click().clickbyxpath("//li[@class='resource-content-posts-list']//a[@title ='"+resourceName+LoginUsingLTI.appendChar+"']//..//..//..//..//..//..//a[@class='ls-resource-show-assign-this-block']");//Click on > arrow
            new Click().clickbyxpath("//li[@class='resource-content-posts-list']//a[@title ='"+resourceName+LoginUsingLTI.appendChar+"']//..//..//..//..//..//..//a[@title = 'Open']//span");//Click on 'open' icon
            if(new BooleanValue().verifyNotElementPresentByXpath("//div[normalize-space(@class)='tab active']//span[@title = '"+resourceName+LoginUsingLTI.appendChar+"']")){
                Assert.fail("Clicking on the open button is not opened in new tab");
            }




            //Row No - 11 : 9. Click on the resource file link
            //Expected - 9. Resource file should get opened in new browser tab using google maps
            new Click().clickbyxpath("//div[@class='ls-media-link']//a[text() = '"+resourceName+LoginUsingLTI.appendChar+"']");// CLick on resource link
            checkBrowserNewTabOpenedInGoogleMaps();





            //Row No - 12 : 10. Bookmark the Resource and Go to Star tab
            //Expected- Bookmarked resource should appear in Star tab
            new Click().clickbyxpath("//i[normalize-space(@class) = 'ls-right-section-sprites ls--right-star-icon-resource']");//Click on 'Bookmark' icon
            new Click().clickbyxpath("//span[starts-with(@id,'close-resource-RESOURCE--')]");//Click on 'x' icon to close resource separate tab
            new Click().clickbyxpath("//span[@title = 'Fav']");//Click on 'Star(*)' Tab
            if(new BooleanValue().verifyNotElementPresentByXpath("//div[@class='ls-right-user-content']//a[@title= '"+resourceName+LoginUsingLTI.appendChar+"']//..//..//..//..//..//span[text() = ' bookmarked a Resource']")){
                Assert.fail("Bookmarked resource has not appeared in Star tab");

            }





            /*Row No - 13 : "11. Close the Resource tab
            12. Again click on the right side arrow and Click on New tab button
            13. Unbookmark the resource and Go to Star tab"*/
            //Expected - Resource should not appear in Star tab
            new Navigator().navigateToTab("Resources");//Click on 'Resources' Tab
            new Click().clickbyxpath("//li[@class='resource-content-posts-list']//a[@title ='"+resourceName+LoginUsingLTI.appendChar+"']//..//..//..//..//..//..//a[@class='ls-resource-show-assign-this-block']");//Click on 'Arrow' button
            new Click().clickbyxpath("//li[@class='resource-content-posts-list']//a[@title ='"+resourceName+LoginUsingLTI.appendChar+"']//..//..//..//..//..//..//a[@title = 'New tab']//span");//Click on 'New Tab' button
            new Click().clickbyxpath("//i[normalize-space(@class) = 'ls-right-section-sprites ls--right-star-icon-resource']");//Click on 'Remove Bookmark' icon
            new Click().clickbyxpath("//span[starts-with(@id,'close-resource-RESOURCE--')]");//Click on 'x' icon to close resource separate tab
            new Click().clickbyxpath("//span[@title = 'Fav']");//Click on 'Star(*)' Tab
            if(!(new BooleanValue().verifyNotElementPresentByXpath("//div[@class='ls-right-user-content']//a[@title= '"+resourceName+LoginUsingLTI.appendChar+"']//..//..//..//..//..//span[text() = ' bookmarked a Resource']"))){
                Assert.fail("Bookmarked resource should not be appeared in Star tab");
            }




            //Row No - 14 : 14. Again click on the right side arrow and Click on Add to Activity button
            //Expected  - Resource should get added to the Cart and should show at the top header
            new Navigator().navigateToTab("Resources");//Click on 'Resources' Tab
            new Click().clickbyxpath("//li[@class='resource-content-posts-list']//a[@title ='"+resourceName+LoginUsingLTI.appendChar+"']//..//..//..//..//..//..//a[@class='ls-resource-show-assign-this-block']");//Click on 'Arrow' button
            new Click().clickbyxpath("//li[@class='resource-content-posts-list']//a[@title ='"+resourceName+LoginUsingLTI.appendChar+"']//..//..//..//..//..//..//a[@title = 'Add to Activity']//span");//Click on 'Add to Activity' button


            /*Row No - 15 : "15. Login as a student linked to the class to which the instructor is associated with.
            16.Go to textbook page and validate if the uploaded resource is available under resources tab at the assigned node."*/

            new LoginUsingLTI().ltiLogin("15");// Login as an 'Instructor' with CS1 sections
            new Navigator().NavigateTo("e-Textbook");// Navigate to 'e-Textbook'
            new Click().clickbyxpath("//a[contains(text(),'The Science of Biology')]");//Clck on the chapter
            new Navigator().navigateToTab("Resources");//Navigate to 'Resources'
            if(new BooleanValue().verifyNotElementPresentByXpath("//ul[@class='resource-content-posts']//a[@title = '"+resourceName+LoginUsingLTI.appendChar+"']")){
                Assert.fail("Student should be able to view the added resource under Resource tab.");
            }


            //Expected - Two buttons should appear over the Resources (Open and new tab)
            new Click().clickbyxpath("//li[@class='resource-content-posts-list']//a[@title ='"+resourceName+LoginUsingLTI.appendChar+"']//..//..//..//..//..//..//a[@class='ls-resource-show-assign-this-block']");//Click on > arrow
            if(new BooleanValue().verifyNotElementPresentByXpath("//li[@class='resource-content-posts-list']//a[@title ='"+resourceName+LoginUsingLTI.appendChar+"']//..//..//..//..//..//..//a[@title = 'Open']//span")){
                Assert.fail("Open button is not appeared over the Resources");
            }
            if(new BooleanValue().verifyNotElementPresentByXpath("//li[@class='resource-content-posts-list']//a[@title ='"+resourceName+LoginUsingLTI.appendChar+"']//..//..//..//..//..//..//a[@title = 'New tab']//span")){
                Assert.fail("New Tab button is not appeared over the Resources");
            }

            //Expected - Student should be able to open the resource using both buttons (Open and new tab) as per the button functionalities
            new Click().clickbyxpath("//li[@class='resource-content-posts-list']//a[@title ='"+resourceName+LoginUsingLTI.appendChar+"']//..//..//..//..//..//..//a[@class='ls-resource-show-assign-this-block']");//Click on > arrow
            new Click().clickbyxpath("//li[@class='resource-content-posts-list']//a[@title ='"+resourceName+LoginUsingLTI.appendChar+"']//..//..//..//..//..//..//a[@title = 'Open']//span");//Click on 'open' icon
            if(new BooleanValue().verifyNotElementPresentByXpath("//div[normalize-space(@class)='tab active']//span[@title = '"+resourceName+LoginUsingLTI.appendChar+"']")){
                Assert.fail("Clicking on the open button is not opened in new tab");
            }

            new Click().clickbyxpath("//div[@class='ls-media-link']//a[text() = '"+resourceName+LoginUsingLTI.appendChar+"']");// CLick on resource link
            checkBrowserNewTabOpenedInGoogleMaps();
            Thread.sleep(5000);
            new Click().clickbyxpath("//i[normalize-space(@class) = 'ls-right-section-sprites ls--right-star-icon-resource']");//Click on 'Bookmark' icon
            new Click().clickbyxpath("//span[starts-with(@id,'close-resource-RESOURCE--')]");//Click on 'x' icon to close resource separate tab
            new Click().clickbyxpath("//span[@title = 'Add to My Notes']");//Click on 'Star(*)' Tab
            String resName = driver.findElement(By.xpath("//div[@class='ls-right-user-content']//a[@title= '"+resourceName+LoginUsingLTI.appendChar+"']//..//..//..//..//..//span[text() = ' bookmarked a Resource']")).getText();
            System.out.println("resName : " + resName);
            if(new BooleanValue().verifyNotElementPresentByXpath("//div[@class='ls-right-user-content']//a[@title= '"+resourceName+LoginUsingLTI.appendChar+"']//..//..//..//..//..//span[text() = ' bookmarked a Resource']")){
                Assert.fail("Bookmarked resource has not appeared in Star tab");

            }
            new Navigator().navigateToTab("Resources");//Click on 'Resources' Tab
            new Click().clickbyxpath("//li[@class='resource-content-posts-list']//a[@title ='"+resourceName+LoginUsingLTI.appendChar+"']//..//..//..//..//..//..//a[@class='ls-resource-show-assign-this-block']");//Click on 'Arrow' button
            new Click().clickbyxpath("//li[@class='resource-content-posts-list']//a[@title ='"+resourceName+LoginUsingLTI.appendChar+"']//..//..//..//..//..//..//a[@title = 'New tab']//span");//Click on 'New Tab' button
            new Click().clickbyxpath("//i[normalize-space(@class) = 'ls-right-section-sprites ls--right-star-icon-resource']");//Click on 'Remove Bookmark' icon
            new Click().clickbyxpath("//span[starts-with(@id,'close-resource-RESOURCE--')]");//Click on 'x' icon to close resource separate tab
            new Click().clickbyxpath("//span[@title = 'Add to My Notes']");//Click on 'Star(*)' Tab
            if(!(new BooleanValue().verifyNotElementPresentByXpath("//div[@class='ls-right-user-content']//a[@title= '"+resourceName+LoginUsingLTI.appendChar+"']//..//..//..//..//..//span[text() = ' bookmarked a Resource']"))){
                Assert.fail("Bookmarked resource should not be appeared in Star tab");

            }


            /*Row No - 18 : "17. Click on the Like option and add a comment on the Resource
            18. Go to Course Stream page"*/
            //Expected  - Resource entry should be present in the Course Stream page
            new Navigator().navigateToTab("Resources");//Click on 'Resources' Tab
            new Click().clickbyxpath("//li[@class='resource-content-posts-list']//a[@title ='" +resourceName+LoginUsingLTI.appendChar+"']//..//..//..//..//..//..//a[@class='ls-resource-show-assign-this-block']");//Click on 'Arrow' button
            new Click().clickbyxpath("//li[@class='resource-content-posts-list']//a[@title ='" +resourceName+LoginUsingLTI.appendChar+"']//..//..//..//..//..//..//a[@title = 'New tab']//span");//Click on 'New Tab' button
            new Click().clickbyxpath("//div[@class='resource-tab-content-section']//div[text() = '"+resourceName+LoginUsingLTI.appendChar+"']//..//..//a[@class = 'right-post-like']");
            new Click().clickbyxpath("//div[@class='resource-tab-content-section']//div[text() = '"+resourceName+LoginUsingLTI.appendChar+"']//..//..//a[@title = 'Comments']");

            driver.findElement(By.cssSelector("textarea[class='ls-textarea-focus resource-post-comment']")).sendKeys("I liked the resource");//Type comment
            new Click().clickBycssselector("div[class='post-comment resource-post-comment']");//Click on Post button
            new Navigator().NavigateTo("Course Stream");
            actual = new TextFetch().textfetchbyclass("ls-stream-post__body");
            System.out.println("Actual Before : " + actual);
            String actualTokens[] = actual.split("/n");
            actual = actualTokens[0].trim();
            expected = resourceName+LoginUsingLTI.appendChar;
            if(!(actual.contains(expected))){
                Assert.fail("Resource entry is not present in the Course Stream page");
            }


            //Row No - 19 : 19. Click on the jump-out icon for resource entry
            //Expected - It should redirect to eTextbook and should open the resource in new tab
            new Click().clickbyxpath("//div[@class='ls-stream-post__body']//span");
            if(!(driver.getCurrentUrl().contains("/eTextBook"))){
                Assert.fail("Jump-out icon is not jumping to e-Textbook");
            }
            if(new BooleanValue().verifyNotElementPresentByXpath("//div[normalize-space(@class)='tab active']//span[@title = '"+resourceName+LoginUsingLTI.appendChar+"']")){
                Assert.fail("New tab with Resource has not been opened");
            }
            if(new BooleanValue().verifyNotElementPresentByXpath("  //div[@class='ls-media-link']//a[text() = '"+resourceName+LoginUsingLTI.appendChar+"']")){
                Assert.fail("Resource is not appered in new tab resource");
            }
        }catch(Exception e){
            Assert.fail("Exception in the testcase 'reservedForAssignmentOptionAsUncheckedInUploadResourcePopUp' in the class 'KMZFileTypeResourceUploader_Mentor_DifferentUserId'", e);
        }
    }



    @Test(priority =2,enabled = true)
    public void reservedForAssignmentOptionAsCheckedInUploadResourcePopUp(){

        /*Row No -  20 : "1. Login as an instructor to LS course
        2. Access navigator dropdown and select resources
        3. Select My Library tab on Resources page
        4. Click on upload resource button and upload file with .kmz extension"*/
        //Expected - File should be uploaded successfully with filename.kmz extension in the Upload resource pop-up

        try{
            String resourceName =  ReadTestData.readDataByTagName("", "resourcesname", "20");
            //new Driver().startDriver();
            new LoginUsingLTI().ltiLogin("20");// Login as an 'Instructor' with AJ_1 sections
            new UploadResources().uploadResources("20",true,true,true);
            actual = new TextFetch().textfetchbyxpath("//div[@id = 'top-notification-Bar']//div[@class='ls-notification-text-span']");
            expected = "The resource is successfully created.";
            Assert.assertEquals(actual,expected,"File is not uploaded successfully with filename.kmz extension in the Upload resource pop-up");


            //Expected: KMZ file with more than 25MB size should not get uploaded and show Robo-notification saying "There was a problem while uploading a file.Please try again later"







            //Row No - 21 : 5. Select the checkbox of “Reserved for assignment” and do all the mandatory actions and click on Save.
            //Expected - Instructor should be able to save the resource.
            new UploadResources().uploadResources("20",true,true,true);
            actual = new TextFetch().textfetchbyxpath("//div[@id = 'top-notification-Bar']//div[@class='ls-notification-text-span']");
            expected = "The resource is successfully created.";
            Assert.assertEquals(actual,expected,"File is not uploaded successfully with filename.kmz extension in the Upload resource pop-up");
            if(new BooleanValue().verifyNotElementPresentByXpath("//div[normalize-space(@class) ='ls-assessment-item-sectn-right ls-my-resource-item-section']//div[@title = '"+resourceName+LoginUsingLTI.appendChar+"']")){
                Assert.fail("File is not saved successfully with filename.kmz extension in the 'My Resources' Tab");
            }




            //Expected - Uploaded Resource with .kmz extension should show unique icon in My Library tab
            if(new BooleanValue().verifyNotElementPresentByXpath("//div[normalize-space(@class)='ls-assessment-item-title ls-assesment-title-text-ellipsis']//div[normalize-space(@class)='ls-resource-doctypes ls-resource-kml']//..//div[@title = '"+resourceName+LoginUsingLTI.appendChar+"']")){
                Assert.fail("Uploaded Resource with .kmz extension should show unique icon in My Library tab");

            }

            //Row No - 23 : 6. Select eTextbook in navigation drop-down and Navigate to  lesson from the level where resource is added.
            //Expected  - Instructor should be able to view the resource under Resource tab at right side of the lesson.
            new Navigator().NavigateTo("e-Textbook");// Navigate to 'e-Textbook'
            new Click().clickbyxpath("//a[contains(text(),'The Science of Biology')]");//Clck on the chapter
            new Navigator().navigateToTab("Resources");//Navigate to 'Resources'
            if(new BooleanValue().verifyNotElementPresentByXpath("//ul[@class='resource-content-posts']//a[@title = '"+resourceName+LoginUsingLTI.appendChar+"']")){
                Assert.fail("Instructor should be able to view the resource under Resource tab at right side of the lesson.");
            }


            //Expected  - Instructor' Label should be displayed for the resource posted.
            if(new BooleanValue().verifyNotElementPresentByXpath("//div[@class='ls-right-user-content']//a[@title = '"+resourceName+LoginUsingLTI.appendChar+"']//..//..//..//span[text() = 'Instructor']")){
                Assert.fail("Instructor' Label should be displayed for the resource posted.");
            }



            //Expected - Uploaded Resource with .kmz extension should show unique icon
            if(new BooleanValue().verifyNotElementPresentByXpath("//i[normalize-space(@class) = 'ls-fileicon-img ls-right-assignment-kmz']//..//a[@title = '"+resourceName+LoginUsingLTI.appendChar+"']")){
                Assert.fail("Uploaded Resource with .kmz extension should show unique icon in 'Resources' tab");
            }





            /*Row No - 26 : "15. Login as a student linked to the class to which the instructor is associated with.
            16.Go to textbook page and validate if the uploaded resource is available under resources tab at the assigned node."*/
            //Expected - Student should not be able to view the added resource under Resource tab.
            new LoginUsingLTI().ltiLogin("26");// Login as an 'Instructor' with CS1 sections
            new Navigator().NavigateTo("e-Textbook");// Navigate to 'e-Textbook'
            new Click().clickbyxpath("//a[contains(text(),'The Science of Biology')]");//Clck on the chapter
            new Navigator().navigateToTab("Resources");//Navigate to 'Resources'
            if(!(new BooleanValue().verifyNotElementPresentByXpath("//ul[@class='resource-content-posts']//a[@title = '"+resourceName+LoginUsingLTI.appendChar+"']"))){
                Assert.fail("Student should not be able to view the added resource under Resource tab.");
            }

        }catch(Exception e){
            Assert.fail("Exception in the testcase 'reservedForAssignmentOptionAsCheckedInUploadResourcePopUp' in the class 'KMZFileTypeResourceUploader_Mentor_DifferentUserId'", e);
        }
    }







    @Test(priority =3,enabled = true)
    public void assignKMZTypeResourceStudents(){

        /*Row No - 27 : "1. Login as a instructor again
        2. Go to eTextbook page and go to the Resources tab
        3. Click on the right side arrow of KMZ file type Resource
        4. Click on Assign this option and assign the resource"*/
        //Expected -1 : Resource (KMZ file type) should be assigned successfully to students
        try{
            String resourceName =  ReadTestData.readDataByTagName("", "resourcesname", "27");
            System.out.println("resource Name : " + resourceName);
            new LoginUsingLTI().ltiLogin("27");// Login as an 'Instructor' with AJ_1 sections
            new UploadResources().uploadResources("27",true,true,true);
            new Navigator().NavigateTo("e-Textbook");// Navigate to 'e-Textbook'
            new Click().clickBycssselector("a[title = 'Introduction']");//Click on 'Introduction' chapter
            new Navigator().navigateToTab("Resources");//Navigate to 'Resources'
            new Click().clickbyxpath("//li[@class='resource-content-posts-list']//a[@title ='"+resourceName+LoginUsingLTI.appendChar+"']//..//..//..//..//..//..//a[@class='ls-resource-show-assign-this-block']");//Click on > arrow
            //new Click().clickbyxpath("//li[@class='resource-content-posts-list']//a[@title ='"+resourceName+LoginUsingLTI.appendChar+"']//..//..//..//..//..//..//a[text() = 'Assign this']//span");//Click on 'open' icon
            List<WebElement> assignThisElementsList = driver.findElements(By.cssSelector("a[title = 'Assign this']"));
            for(int a=0;a<assignThisElementsList.size();a++){
                if(assignThisElementsList.get(a).isDisplayed()){
                    assignThisElementsList.get(a).click();
                    break;
                }
            }
            new Assignment().assignThisFormFillOtherDetails("27");




            //Expected- 2 : Assigned resource should appear under Assignment tab
            if(new BooleanValue().verifyNotElementPresentByXpath("//div[@class='ls-right-user-content']//a[normalize-space(@title)= '(Shor) "+resourceName+LoginUsingLTI.appendChar+"']//..//..//..//..//..//span[text() = 'posted an assignment']")){
                Assert.fail("Resource (KMZ file type) should be assigned successfully to students");
            }


            //Row No - 29 : 5. Go to Assinments->Current Assignments page
            //Expected - Assignnend resource should appear in assignment->Current Assignments page
            new Navigator().NavigateTo("Current Assignments");
            if(new BooleanValue().verifyNotElementPresentByXpath("//div[@class='ls-assignment-item-detail-section']//a[contains(text(),'"+resourceName+LoginUsingLTI.appendChar+"')]")){
                Assert.fail("Assigned resource has not been appeared in assignment->Current Assignments page");
            }



           /* Row No - 30 : "6. Login as student
            7. Go to Assignments page"*/
            //Expected - Assigned resource should appear in Assignments page
            new LoginUsingLTI().ltiLogin("30");// Login as an 'Student' with CS1 sections
            new Navigator().NavigateTo("Assignments");// Navigate to 'Assignments'
            if(new BooleanValue().verifyNotElementPresentByXpath("//div[@class='ls-assignment-item-detail-section']//span[contains(text(),'"+resourceName+LoginUsingLTI.appendChar+"')]")){
                Assert.fail("Assigned resource should appear in Assignments page");
            }





            //Row No - 31 : 8. Click on Assignment filter and filter the assignment by selecting Non-gradable Learning actiity
            //Expected - Assigned resource should appear under Non-gradable Learning Activity filter only
            new Click().clickbyxpath("//a[contains(@id,'sbSelector_')]");
            new Click().clickbypartiallinkText("Non Gradable Learning Activity");
            if(new BooleanValue().verifyNotElementPresentByXpath("//div[@class='ls-assignment-item-detail-section']//span[contains(text(),'"+resourceName+LoginUsingLTI.appendChar+"')]")){
                Assert.fail("Assigned resource should appear under Non-gradable Learning Activity filter only");
            }




            //Row No - 32 : 9. Click on the assignment
            //Expected - Student should redirect to eTextbook and assigned resource should open in new tab
            //new Click().clickbyxpath("//span[contains(text(),'"+resourceName+LoginUsingLTI.appendChar+"')]");
            if(driver.findElement(By.className("learning-activity-title")).getText().contains(resourceName)){
                new Click().clickByclassname("learning-activity-title");
            }
            List<WebElement> assignmentNamesElementsList = driver.findElements(By.className("ls-assignment-item-wrapper"));
            for(int a=0;a<assignmentNamesElementsList.size();a++){
                System.out.println("assignmentNamesElementsList : " + assignmentNamesElementsList.get(a).getText());
                if(assignmentNamesElementsList.get(a).getText().contains(resourceName)){
                    assignmentNamesElementsList.get(a).click();
                    break;
                }
            }

            Thread.sleep(2000);
            if(!(driver.getCurrentUrl().contains("/eTextBook"))){
                Assert.fail("Student is not able to redirect to eTextbook ");
            }

            if(new BooleanValue().verifyNotElementPresentByXpath("//div[normalize-space(@class)='tab active']//span[contains(@title,'"+resourceName+LoginUsingLTI.appendChar+"')]")){
                Assert.fail("Assigned resource is not opened in new tab");
            }

            if(new BooleanValue().verifyNotElementPresentByXpath("//div[@class='ls-media-link']//a[contains(text(),'"+resourceName+LoginUsingLTI.appendChar+"')]")){
                Assert.fail("Resource is not appered in new tab resource");
            }



            //Row No - 33  : 10. Click on the resource file
            //Expected - Resource file should get opened in new browser tab using google maps
            new Click().clickbyxpath("//div[@class='ls-media-link']//a[contains(text(),'"+resourceName+LoginUsingLTI.appendChar+"')]");// CLick on resource link
            checkBrowserNewTabOpenedInGoogleMaps();


            //Row No - 34 : 11. Go to Assignments page
            //Expected 1- Assignment should show the status as "Submitted" for the assigned learning activity
            new Navigator().NavigateTo("Assignments");
            List<String> assignmentNamesList= new TextFetch().textfetchbylistclasswithoutindex("ls-assignment-item-wrapper");
            List<String> assignmentStatusList= new TextFetch().textfetchbylistclasswithoutindex("ls-assignment-status");
            for(int a=0;a<assignmentNamesList.size();a++){
                if(assignmentNamesList.get(a).contains(resourceName)){
                    System.out.println("Actual : " + actual);
                    actual = assignmentStatusList.get(a).trim();
                    expected = "Your Status: Submitted";
                    Assert.assertEquals(actual,expected,"Assignment is not showing the status as \"Submitted\" for the assigned learning activity");
                }
            }


            //Expected - 2 : Assignment Status box at the top of the assignment page should get updated with Submitted as 1
            actual = new TextFetch().textfetchbyxpath("//div[@id='ls-submitted-assignment']//div[@class='ls-assignment-data-count']");
            expected = "1";
            Assert.assertEquals(actual,expected,"Assignment Status box at the top of the assignment page is not getting updated with Sumbitted as 1");




            //Row No - 36 : 2. Go to eTextbook and go the Assignments tab where the learning activity is associated with
            //Expected - Status should show as Sumbitted in Assignments tab
            new Navigator().NavigateTo("e-Textbook");
            new Click().clickbyxpath("//a[contains(text(),'The Science of Biology')]");//Clck on the chapter
            new Navigator().navigateToTab("Assignments");//Navigate to 'Assignments'
            if(new BooleanValue().verifyNotElementPresentByXpath("//a[contains(text(),'"+resourceName+LoginUsingLTI.appendChar+"')]//..//..//..//span[text() = 'Submitted']")){
                Assert.fail("Status is not showing as Sumbitted in Assignments tab");
            }

        }catch(Exception e){
            Assert.fail("Exception in the testcase 'reservedForAssignmentOptionAsCheckedInUploadResourcePopUp' in the class 'KMZFileTypeResourceUploader_Mentor_DifferentUserId'", e);
        }
    }



    @Test(priority =4,enabled = true)
    public void reservedForGradingPolicyOptionAsCheckedInUploadResourcePopUp(){

       /* Row No - 37 : "1. Login as an instructor to LS course
        2. Access navigator dropdown and select resources
        3. Select My Library tab on Resources page
        4. Click on upload resource button and upload file with .kmz extension"*/
        //Expected - File should be uploaded successfully with filename.kmz extension in the Upload resource pop-up
        try{
            String assessmentName =  ReadTestData.readDataByTagName("", "assessmentname", "42");
            new Assignment().create(42);// Create an assignment with true false question creation
            new Assignment().addQuestions(42, "multipleselection", "");// Create Multiple Selection Question
            new LoginUsingLTI().ltiLogin("37");// Login as an 'Instructor' with AJ_1 sections
            new UploadResources().uploadResources("37",true,true,true);
            actual = new TextFetch().textfetchbyxpath("//div[@id = 'top-notification-Bar']//div[@class='ls-notification-text-span']");
            expected = "The resource is successfully created.";
            Assert.assertEquals(actual,expected,"File is not uploaded successfully with filename.kmz extension in the Upload resource pop-up");


            //Row No - 39 : 5. Select the checkbox of “Reserved for Assignment Reference” and do all the mandatory actions and click on Save.
            //Expected  - Instructor should be able to upload the file as Assignment Reference.
            new UploadResources().uploadResources("39",false,true,true);
            actual = new TextFetch().textfetchbyxpath("//div[@id = 'top-notification-Bar']//div[@class='ls-notification-text-span']");
            expected = "The Assignment Reference has been successfully created.";
            Assert.assertEquals(actual,expected,"File is not uploaded successfully with filename.kmz extension in the Upload resource with Assignment Reference popup");
            String resourceName =  ReadTestData.readDataByTagName("", "resourcesname", "39");
            if(new BooleanValue().verifyNotElementPresentByXpath("//div[normalize-space(@class) ='ls-assessment-item-sectn-right ls-my-resource-item-section']//span[text() = 'Assignment Reference']//..//div[@title = '"+resourceName+LoginUsingLTI.appendChar+"']")){
                Assert.fail("Instructor is not able to upload the file as Assignment Reference.");
            }


            //Row No - 41 : 6. Click on the Assignment Reference name(Click on Preview button)
            //Expected - Instructor should be able to open the KMZ file in new browser tab using google maps
            new Click().clickbylinkText("Preview");
            checkBrowserNewTabOpenedInGoogleMaps();


            /*Row No - 42 : "7. Go to Question Banks page and assign the assessment with Assignment Reference(having .Kmz file)
            8. Go to Assignment-> Summary Page*/
            //Assignment Reference should be shown in the assignment along with unique icon of KMZ file
            new Navigator().NavigateTo("Question Banks");
            new Assignment().searchAssessmentInQuestionBanks(42);

            new Click().clickBycssselector("span[class = 'assign-this']");
            fillOtherDetailsInAssignThisForm(resourceName+LoginUsingLTI.appendChar,"42");
            new Navigator().NavigateTo("Current Assignments");
            Thread.sleep(2000);
            new Navigator().NavigateTo("Current Assignments");
            Thread.sleep(5000);
            if(driver.findElements(By.xpath("//span[@title = '(Shor) "+assessmentName+"']//following-sibling::div//span")).size()==0){
                Assert.fail("Assignment Reference is not showing in the assignment along with unique icon of KMZ file 1");
            }
        }catch(Exception e){
            Assert.fail("Exception in the testcase 'reservedForGradingPolicyOptionAsCheckedInUploadResourcePopUp' in the class 'KMZFileTypeResourceUploader_Mentor_DifferentUserId'", e);
        }
    }




    @Test(priority =5,enabled = true)
    public void uploadKMZFileInCourseStream(){

        /*Row No - 45 : "1. Login as instructor
        2. Go to Course Stream and click on ""Share a new"" pop-up
        3. Click on File and upload KMZ file"*/
        //Expected - File should be uploaded successfully with filename.kmz extension in the Upload resource pop-up
        try{

            String filename =  ReadTestData.readDataByTagName("", "filename", "45");

            new LoginUsingLTI().ltiLogin("45");// Login as an 'Instructor' with AJ_1 sections
            new Navigator().NavigateTo("Course Stream");
            new Click().clickByclassname("share-to-ls-label");
            new FileUpload().fileUpload("45",false);
            if(driver.findElements(By.id("file-thumbnail-img")).size()==0){
                Assert.fail("File has not been uploaded successfully");
            }







            //Expected - KMZ file with more than 25MB size should not get uploaded and show Robo-notification saying "There was a problem while uploading a file.Please try again later"








            //Row No - 47 : 4. Click on the KMZ file which is present in the Course Stream
            //Expected - KMZ file should get opened in new browser tab using google maps
            System.out.println("File Name : " + filename);
            driver.findElement(By.id("post-submit-button")).click();
            Thread.sleep(10000);
            new Click().clickbylinkText(filename);
            checkBrowserNewTabOpenedInGoogleMaps();



            /*Row No - 48 : "5. Login as student
            6. Go to Course Stream and click on ""Share a new"" pop-up
            7. Click on File and upload KMZ file"*/
            //Expecetd  - File should be uploaded successfully with filename.kmz extension in the Upload resource pop-up
            new LoginUsingLTI().ltiLogin("48");// Login as a 'Student' with AJ_1 sections
            new Navigator().NavigateTo("Course Stream");
            new Click().clickByclassname("share-to-ls-label");
            new FileUpload().fileUpload("45", false);
            if(driver.findElements(By.id("file-thumbnail-img")).size()==0){
                Assert.fail("File has not been uploaded successfully");
            }







            //Expected - KMZ file with more than 10MB size should not get uploaded and show Robo-notification saying "There was a problem while uploading a file.Please try again later"





            //Row No - 50 : 8. Click on the KMZ file which is present in the Course Stream
            //Expected - KMZ file should get opened in new browser tab using google maps
            System.out.println("File Name : " + filename);
            driver.findElement(By.id("post-submit-button")).click();
            Thread.sleep(10000);
            new Click().clickbylinkText(filename);
            checkBrowserNewTabOpenedInGoogleMaps();

        }catch(Exception e){
            Assert.fail("Exception in the testcase 'uploadKMZFileInCourseStream' in the class 'KMZFileTypeResourceUploader_Mentor_DifferentUserId'", e);
        }
    }



    @Test(priority =6,enabled = true)
    public void uploadKMZFileInMyNotesPage(){

        /*Row No - 51 : "1. Login as student
        2. Go to My Notes page
        3. Click on ""+New Note"" button
        4. Upload KMZ file and do all the mandatory actions and click on Save"*/
        //Expected  - File should be uploaded successfully with filename.kmz extension in the My notes page

        try{
            String filename =  ReadTestData.readDataByTagName("", "filename", "51");
            System.out.println("filename : " + filename);
            //new Driver().startDriver();
            new LoginUsingLTI().ltiLogin("51");// Login as a 'Student' with AJ_1 sections
            new Navigator().NavigateTo("My Notes");
            new FileUpload().fileUploadFromNote("51",true);
            if(new BooleanValue().verifyNotElementPresentByXpath("//a[text() = '"+filename+"']")){
                Assert.fail("File should has not been uploaded successfully with filename.kmz extension in the My notes page");
            }





            //Expected - KMZ file with more than 10MB size should not get uploaded and show Robo-notification saying "There was a problem while uploading a file.Please try again later"






            //Expected - Uploaded file should be shown in the my notes page along with unique icon of KMZ file
            if(new BooleanValue().verifyNotElementPresentByXpath("//a[text() = '"+filename+"']//span")){
                Assert.fail("Uploaded file is not shown in the my notes page along with unique icon of KMZ file");
            }


            //Row N0 - 54 : 5. Click on the uploaded KMZ file
            //Expected- KMZ file should get opened in new browser tab using google maps
            new Click().clickbylinkText(filename);
            checkBrowserNewTabOpenedInGoogleMaps();



        }catch(Exception e){
            Assert.fail("Exception in the testcase 'uploadKMZFileInCourseStream' in the class 'KMZFileTypeResourceUploader_Mentor_DifferentUserId'", e);
        }
    }





    @Test(priority =7,enabled = true)
    public void reservedForAssignmentOptionAsUncheckedInUploadResourcePopUpKML(){

        /*Row No - 55 : "1. Login as an instructor to LS course
        2. Access navigator dropdown and select resources
        3. Select My Library tab on Resources page
        4. Click on upload resource button and upload file with .kml extension"*/
        //Expected - File should be uploaded successfully with filename.kmz extension in the Upload resource pop-up

        try{
            String resourceName =  ReadTestData.readDataByTagName("", "resourcesname", "55");
            new LoginUsingLTI().ltiLogin("55");// Login as an 'Instructor' with AJ_1 sections
            new UploadResources().uploadResources("55",true,true,true);
            actual = new TextFetch().textfetchbyxpath("//div[@id = 'top-notification-Bar']//div[@class='ls-notification-text-span']");
            expected = "The resource is successfully created.";
            Assert.assertEquals(actual,expected,"File is not uploaded successfully with filename.kmz extension in the Upload resource pop-up");



            //Row No - 56 : 5. Do not select the checkbox of “Reserved for assignment” and do all the mandatory actions and click on Save.
            //Expected - Instructor should be able to save the resource.
            new UploadResources().uploadResources("55",false,true,true);
            actual = new TextFetch().textfetchbyxpath("//div[@id = 'top-notification-Bar']//div[@class='ls-notification-text-span']");
            expected = "The resource is successfully created.";
            Assert.assertEquals(actual,expected,"File is not uploaded successfully with filename.kmz extension in the Upload resource pop-up");
            if(new BooleanValue().verifyNotElementPresentByXpath("//div[normalize-space(@class) ='ls-assessment-item-sectn-right ls-my-resource-item-section']//div[@title = '"+resourceName+LoginUsingLTI.appendChar+"']")){
                Assert.fail("File is not saved successfully with filename.kmz extension in the 'My Resources' Tab");
            }





            //Expected - Uploaded Resource with .kmz extension should show unique icon in My Library tab
            if(new BooleanValue().verifyNotElementPresentByXpath("//div[normalize-space(@class)='ls-assessment-item-title ls-assesment-title-text-ellipsis']//div[normalize-space(@class)='ls-resource-doctypes ls-resource-kml']//..//div[@title = '"+resourceName+LoginUsingLTI.appendChar+"']")){
                Assert.fail("Uploaded Resource with .kmz extension should show unique icon in My Library tab");

            }

            //Row No - 58 : 6. Select eTextbook in navigation drop-down and Navigate to  lesson from the level where resource is added.
            //Expected  - Instructor should be able to view the resource under Resource tab at right side of the lesson.
            new Navigator().NavigateTo("e-Textbook");// Navigate to 'e-Textbook'
            new Click().clickbyxpath("//a[contains(text(),'The Science of Biology')]");//Clck on the chapter
            new Navigator().navigateToTab("Resources");//Navigate to 'Resources'
            if(new BooleanValue().verifyNotElementPresentByXpath("//ul[@class='resource-content-posts']//a[@title = '"+resourceName+LoginUsingLTI.appendChar+"']")){
                Assert.fail("Instructor should be able to view the resource under Resource tab at right side of the lesson.");
            }


            //Expected  - Instructor' Label should be displayed for the resource posted.
            if(new BooleanValue().verifyNotElementPresentByXpath("//div[@class='ls-right-user-content']//a[@title = '"+resourceName+LoginUsingLTI.appendChar+"']//..//..//..//span[text() = 'Instructor']")){
                Assert.fail("Instructor' Label should be displayed for the resource posted.");
            }



            //Expected - Uploaded Resource with .kmz extension should show unique icon
            if(new BooleanValue().verifyNotElementPresentByXpath("//i[normalize-space(@class) = 'ls-fileicon-img ls-right-assignment-kml']//..//a[@title = '"+resourceName+LoginUsingLTI.appendChar+"']")){
                Assert.fail("Uploaded Resource with .kmz extension should show unique icon in 'Resources' tab");
            }



            /*Row No - 61 : "7. Click on the right side arrow of Resource under Resources tab
            8. Click on Open button and click on the resource link"*/
            //Expected  - Clicking on the open button should open in new tab
            new Click().clickbyxpath("//li[@class='resource-content-posts-list']//a[@title ='"+resourceName+LoginUsingLTI.appendChar+"']//..//..//..//..//..//..//a[@class='ls-resource-show-assign-this-block']");//Click on > arrow
            new Click().clickbyxpath("//li[@class='resource-content-posts-list']//a[@title ='"+resourceName+LoginUsingLTI.appendChar+"']//..//..//..//..//..//..//a[@title = 'Open']//span");//Click on 'open' icon
            if(new BooleanValue().verifyNotElementPresentByXpath("//div[normalize-space(@class)='tab active']//span[@title = '"+resourceName+LoginUsingLTI.appendChar+"']")){
                Assert.fail("Clicking on the open button is not opened in new tab");
            }




            //Row No - 62 : 9. Click on the resource file link
            //Expected - 9. Resource file should get opened in new browser tab using google maps
            new Click().clickbyxpath("//div[@class='ls-media-link']//a[text() = '"+resourceName+LoginUsingLTI.appendChar+"']");// CLick on resource link
            checkBrowserNewTabOpenedInGoogleMaps();



            //Row No - 63 : 10. Bookmark the Resource and Go to Star tab
            //Expected- Bookmarked resource should appear in Star tab
            new Click().clickbyxpath("//i[normalize-space(@class) = 'ls-right-section-sprites ls--right-star-icon-resource']");//Click on 'Bookmark' icon
            new Click().clickbyxpath("//span[starts-with(@id,'close-resource-RESOURCE--')]");//Click on 'x' icon to close resource separate tab
            new Click().clickbyxpath("//span[@title = 'Fav']");//Click on 'Star(*)' Tab
            if(new BooleanValue().verifyNotElementPresentByXpath("//div[@class='ls-right-user-content']//a[@title= '"+resourceName+LoginUsingLTI.appendChar+"']//..//..//..//..//..//span[text() = ' bookmarked a Resource']")){
                Assert.fail("Bookmarked resource has not appeared in Star tab");

            }





            /*Row No - 64 : "11. Close the Resource tab
            12. Again click on the right side arrow and Click on New tab button
            13. Unbookmark the resource and Go to Star tab"*/
            //Expected - Resource should not appear in Star tab
            new Navigator().navigateToTab("Resources");//Click on 'Resources' Tab
            new Click().clickbyxpath("//li[@class='resource-content-posts-list']//a[@title ='"+resourceName+LoginUsingLTI.appendChar+"']//..//..//..//..//..//..//a[@class='ls-resource-show-assign-this-block']");//Click on 'Arrow' button
            new Click().clickbyxpath("//li[@class='resource-content-posts-list']//a[@title ='"+resourceName+LoginUsingLTI.appendChar+"']//..//..//..//..//..//..//a[@title = 'New tab']//span");//Click on 'New Tab' button
            new Click().clickbyxpath("//i[normalize-space(@class) = 'ls-right-section-sprites ls--right-star-icon-resource']");//Click on 'Remove Bookmark' icon
            new Click().clickbyxpath("//span[starts-with(@id,'close-resource-RESOURCE--')]");//Click on 'x' icon to close resource separate tab
            new Click().clickbyxpath("//span[@title = 'Fav']");//Click on 'Star(*)' Tab
            if(!(new BooleanValue().verifyNotElementPresentByXpath("//div[@class='ls-right-user-content']//a[@title= '"+resourceName+LoginUsingLTI.appendChar+"']//..//..//..//..//..//span[text() = ' bookmarked a Resource']"))){
                Assert.fail("Bookmarked resource should not be appeared in Star tab");
            }
        



            //Row No - 65 : 14. Again click on the right side arrow and Click on Add to Activity button
            //Expected  - Resource should get added to the Cart and should show at the top header
            new Navigator().navigateToTab("Resources");//Click on 'Resources' Tab
            new Click().clickbyxpath("//li[@class='resource-content-posts-list']//a[@title ='"+resourceName+LoginUsingLTI.appendChar+"']//..//..//..//..//..//..//a[@class='ls-resource-show-assign-this-block']");//Click on 'Arrow' button
            new Click().clickbyxpath("//li[@class='resource-content-posts-list']//a[@title ='"+resourceName+LoginUsingLTI.appendChar+"']//..//..//..//..//..//..//a[@title = 'Add to Activity']//span");//Click on 'Add to Activity' button




            /*Row No - 66 : "15. Login as a student linked to the class to which the instructor is associated with.
            16.Go to textbook page and validate if the uploaded resource is available under resources tab at the assigned node."*/
            new LoginUsingLTI().ltiLogin("66");// Login as an 'Instructor' with CS1 sections
            new Navigator().NavigateTo("e-Textbook");// Navigate to 'e-Textbook'
            new Click().clickbyxpath("//a[contains(text(),'The Science of Biology')]");//Clck on the chapter
            new Navigator().navigateToTab("Resources");//Navigate to 'Resources'
            if(new BooleanValue().verifyNotElementPresentByXpath("//ul[@class='resource-content-posts']//a[@title = '"+resourceName+LoginUsingLTI.appendChar+"']")){
                Assert.fail("Student should be able to view the added resource under Resource tab.");
            }




            //Expected - Two buttons should appear over the Resources (Open and new tab)
            new Click().clickbyxpath("//li[@class='resource-content-posts-list']//a[@title ='"+resourceName+LoginUsingLTI.appendChar+"']//..//..//..//..//..//..//a[@class='ls-resource-show-assign-this-block']");//Click on > arrow
            if(new BooleanValue().verifyNotElementPresentByXpath("//li[@class='resource-content-posts-list']//a[@title ='"+resourceName+LoginUsingLTI.appendChar+"']//..//..//..//..//..//..//a[@title = 'Open']//span")){
                Assert.fail("Open button is not appeared over the Resources");
            }
            if(new BooleanValue().verifyNotElementPresentByXpath("//li[@class='resource-content-posts-list']//a[@title ='"+resourceName+LoginUsingLTI.appendChar+"']//..//..//..//..//..//..//a[@title = 'New tab']//span")){
                Assert.fail("New Tab button is not appeared over the Resources");
            }


            //Expected - Student should be able to open the resource using both buttons (Open and new tab) as per the button functionalities
            new Click().clickbyxpath("//li[@class='resource-content-posts-list']//a[@title ='"+resourceName+LoginUsingLTI.appendChar+"']//..//..//..//..//..//..//a[@class='ls-resource-show-assign-this-block']");//Click on > arrow
            new Click().clickbyxpath("//li[@class='resource-content-posts-list']//a[@title ='"+resourceName+LoginUsingLTI.appendChar+"']//..//..//..//..//..//..//a[@title = 'Open']//span");//Click on 'open' icon
            if(new BooleanValue().verifyNotElementPresentByXpath("//div[normalize-space(@class)='tab active']//span[@title = '"+resourceName+LoginUsingLTI.appendChar+"']")){
                Assert.fail("Clicking on the open button is not opened in new tab");
            }
            new Click().clickbyxpath("//div[@class='ls-media-link']//a[text() = '"+resourceName+LoginUsingLTI.appendChar+"']");// CLick on resource link
            checkBrowserNewTabOpenedInGoogleMaps();
            Thread.sleep(5000);
            new Click().clickbyxpath("//i[normalize-space(@class) = 'ls-right-section-sprites ls--right-star-icon-resource']");//Click on 'Bookmark' icon
            new Click().clickbyxpath("//span[starts-with(@id,'close-resource-RESOURCE--')]");//Click on 'x' icon to close resource separate tab
            new Click().clickbyxpath("//span[@title = 'Add to My Notes']");//Click on 'Star(*)' Tab
            String resName = driver.findElement(By.xpath("//div[@class='ls-right-user-content']//a[@title= '"+resourceName+LoginUsingLTI.appendChar+"']//..//..//..//..//..//span[text() = ' bookmarked a Resource']")).getText();
            System.out.println("resName : " + resName);
            if(new BooleanValue().verifyNotElementPresentByXpath("//div[@class='ls-right-user-content']//a[@title= '"+resourceName+LoginUsingLTI.appendChar+"']//..//..//..//..//..//span[text() = ' bookmarked a Resource']")){
                Assert.fail("Bookmarked resource has not appeared in Star tab");

            }
            new Navigator().navigateToTab("Resources");//Click on 'Resources' Tab
            new Click().clickbyxpath("//li[@class='resource-content-posts-list']//a[@title ='"+resourceName+LoginUsingLTI.appendChar+"']//..//..//..//..//..//..//a[@class='ls-resource-show-assign-this-block']");//Click on 'Arrow' button
            new Click().clickbyxpath("//li[@class='resource-content-posts-list']//a[@title ='"+resourceName+LoginUsingLTI.appendChar+"']//..//..//..//..//..//..//a[@title = 'New tab']//span");//Click on 'New Tab' button
            new Click().clickbyxpath("//i[normalize-space(@class) = 'ls-right-section-sprites ls--right-star-icon-resource']");//Click on 'Remove Bookmark' icon
            new Click().clickbyxpath("//span[starts-with(@id,'close-resource-RESOURCE--')]");//Click on 'x' icon to close resource separate tab
            new Click().clickbyxpath("//span[@title = 'Add to My Notes']");//Click on 'Star(*)' Tab
            if(!(new BooleanValue().verifyNotElementPresentByXpath("//div[@class='ls-right-user-content']//a[@title= '"+resourceName+LoginUsingLTI.appendChar+"']//..//..//..//..//..//span[text() = ' bookmarked a Resource']"))){
                Assert.fail("Bookmarked resource should not be appeared in Star tab");

            }




            /*Row No - 69 : "17. Click on the Like option and add a comment on the Resource
            18. Go to Course Stream page"*/
            //Expected  - Resource entry should be present in the Course Stream page
            new Navigator().navigateToTab("Resources");//Click on 'Resources' Tab
            new Click().clickbyxpath("//li[@class='resource-content-posts-list']//a[@title ='" +resourceName+LoginUsingLTI.appendChar+"']//..//..//..//..//..//..//a[@class='ls-resource-show-assign-this-block']");//Click on 'Arrow' button
            new Click().clickbyxpath("//li[@class='resource-content-posts-list']//a[@title ='" +resourceName+LoginUsingLTI.appendChar+"']//..//..//..//..//..//..//a[@title = 'New tab']//span");//Click on 'New Tab' button
            new Click().clickbyxpath("//div[@class='resource-tab-content-section']//div[text() = '"+resourceName+LoginUsingLTI.appendChar+"']//..//..//a[@class = 'right-post-like']");
            new Click().clickbyxpath("//div[@class='resource-tab-content-section']//div[text() = '"+resourceName+LoginUsingLTI.appendChar+"']//..//..//a[@title = 'Comments']");
            /*List<WebElement> commentElements = driver.findElements(By.xpath("//div[@class='ls-media__body']//div[@class='post-comment resource-post-comment']//preceding-sibling::textarea[@name = 'comment']"));
            List<WebElement> postElements = driver.findElements(By.xpath("//div[@class='ls-media__body']//div[@class='post-comment resource-post-comment']"));
            commentElements.get(commentElements.size()-1).sendKeys("I liked the resource");
            postElements.get(commentElements.size()-1).click();*/

            driver.findElement(By.cssSelector("textarea[class='ls-textarea-focus resource-post-comment']")).sendKeys("I liked the resource");//Type comment
            new Click().clickBycssselector("div[class='post-comment resource-post-comment']");//Click on Post button
            new Navigator().NavigateTo("Course Stream");
            actual = new TextFetch().textfetchbyclass("ls-stream-post__body");
            System.out.println("Actual Before : " + actual);
            String actualTokens[] = actual.split("/n");
            actual = actualTokens[0].trim();
            expected = resourceName+LoginUsingLTI.appendChar;
            if(!(actual.contains(expected))){
                Assert.fail("Resource entry is not present in the Course Stream page");
            }



            //Row No - 70 : 19. Click on the jump-out icon for resource entry
            //Expected - It should redirect to eTextbook and should open the resource in new tab
            new Click().clickbyxpath("//div[@class='ls-stream-post__body']//span");
            if(!(driver.getCurrentUrl().contains("/eTextBook"))){
                Assert.fail("Jump-out icon is not jumping to e-Textbook");
            }
            if(new BooleanValue().verifyNotElementPresentByXpath("//div[normalize-space(@class)='tab active']//span[@title = '"+resourceName+LoginUsingLTI.appendChar+"']")){
                Assert.fail("New tab with Resource has not been opened");
            }
            if(new BooleanValue().verifyNotElementPresentByXpath("  //div[@class='ls-media-link']//a[text() = '"+resourceName+LoginUsingLTI.appendChar+"']")){
                Assert.fail("Resource is not appered in new tab resource");
            }
        }catch(Exception e){
            Assert.fail("Exception in the testcase 'reservedForAssignmentOptionAsUncheckedInUploadResourcePopUpKML' in the class 'KMZFileTypeResourceUploader_Mentor_DifferentUserId'", e);
        }
    }





    @Test(priority =8,enabled = true)
    public void reservedForAssignmentOptionAsCheckedInUploadResourcePopUpKML(){

        /*Row No -  71 : "1. Login as an instructor to LS course
        2. Access navigator dropdown and select resources
        3. Select My Library tab on Resources page
        4. Click on upload resource button and upload file with .kmz extension"*/
        //Expected - File should be uploaded successfully with filename.kml extension in the Upload resource pop-up

        try{
            String resourceName =  ReadTestData.readDataByTagName("", "resourcesname", "71");
            //new Driver().startDriver();
            new LoginUsingLTI().ltiLogin("71");// Login as an 'Instructor' with AJ_1 sections
            new UploadResources().uploadResources("71",true,true,true);
            actual = new TextFetch().textfetchbyxpath("//div[@id = 'top-notification-Bar']//div[@class='ls-notification-text-span']");
            expected = "The resource is successfully created.";
            Assert.assertEquals(actual,expected,"File is not uploaded successfully with filename.kmz extension in the Upload resource pop-up");



            //Row No - 72 : 5. Select the checkbox of “Reserved for assignment” and do all the mandatory actions and click on Save.
            //Expected - Instructor should be able to save the resource.
            new UploadResources().uploadResources("71",true,true,true);
            actual = new TextFetch().textfetchbyxpath("//div[@id = 'top-notification-Bar']//div[@class='ls-notification-text-span']");
            expected = "The resource is successfully created.";
            Assert.assertEquals(actual,expected,"File is not uploaded successfully with filename.kmz extension in the Upload resource pop-up");
            if(new BooleanValue().verifyNotElementPresentByXpath("//div[normalize-space(@class) ='ls-assessment-item-sectn-right ls-my-resource-item-section']//div[@title = '"+resourceName+LoginUsingLTI.appendChar+"']")){
                Assert.fail("File is not saved successfully with filename.kmz extension in the 'My Resources' Tab");
            }




            //Expected - Uploaded Resource with .kmz extension should show unique icon in My Library tab
            if(new BooleanValue().verifyNotElementPresentByXpath("//div[normalize-space(@class)='ls-assessment-item-title ls-assesment-title-text-ellipsis']//div[normalize-space(@class)='ls-resource-doctypes ls-resource-kml']//..//div[@title = '"+resourceName+LoginUsingLTI.appendChar+"']")){
                Assert.fail("Uploaded Resource with .kmz extension should show unique icon in My Library tab");

            }

            //Row No - 74 : 6. Select eTextbook in navigation drop-down and Navigate to  lesson from the level where resource is added.
            //Expected  - Instructor should be able to view the resource under Resource tab at right side of the lesson.
            new Navigator().NavigateTo("e-Textbook");// Navigate to 'e-Textbook'
            new Click().clickbyxpath("//a[contains(text(),'The Science of Biology')]");//Clck on the chapter
            new Navigator().navigateToTab("Resources");//Navigate to 'Resources'
            if(new BooleanValue().verifyNotElementPresentByXpath("//ul[@class='resource-content-posts']//a[@title = '"+resourceName+LoginUsingLTI.appendChar+"']")){
                Assert.fail("Instructor should be able to view the resource under Resource tab at right side of the lesson.");
            }


            //Expected  - Instructor' Label should be displayed for the resource posted.
            if(new BooleanValue().verifyNotElementPresentByXpath("//div[@class='ls-right-user-content']//a[@title = '"+resourceName+LoginUsingLTI.appendChar+"']//..//..//..//span[text() = 'Instructor']")){
                Assert.fail("Instructor' Label should be displayed for the resource posted.");
            }



            //Expected - Uploaded Resource with .kmz extension should show unique icon
            if(new BooleanValue().verifyNotElementPresentByXpath("//i[normalize-space(@class) = 'ls-fileicon-img ls-right-assignment-kml']//..//a[@title = '"+resourceName+LoginUsingLTI.appendChar+"']")){
                    Assert.fail("Uploaded Resource with .kmz extension should show unique icon in 'Resources' tab");
            }





            /*Row No - 77 : "15. Login as a student linked to the class to which the instructor is associated with.
            16.Go to textbook page and validate if the uploaded resource is available under resources tab at the assigned node."*/
            //Expected - Student should not be able to view the added resource under Resource tab.
            new LoginUsingLTI().ltiLogin("77");// Login as a 'Student' with CS1 sections
            new Navigator().NavigateTo("e-Textbook");// Navigate to 'e-Textbook'
            new Click().clickbyxpath("//a[contains(text(),'The Science of Biology')]");//Clck on the chapter
            new Navigator().navigateToTab("Resources");//Navigate to 'Resources'
            if(!(new BooleanValue().verifyNotElementPresentByXpath("//ul[@class='resource-content-posts']//a[@title = '"+resourceName+LoginUsingLTI.appendChar+"']"))){
                Assert.fail("Student should not be able to view the added resource under Resource tab.");
            }

        }catch(Exception e){
            Assert.fail("Exception in the testcase 'reservedForAssignmentOptionAsCheckedInUploadResourcePopUpKML' in the class 'KMZFileTypeResourceUploader_Mentor_DifferentUserId'", e);
        }
    }



    @Test(priority =9,enabled = true)
    public void assignKMZTypeResourceStudentsKML(){

        /*Row No - 78 : "1. Login as a instructor again
        2. Go to eTextbook page and go to the Resources tab
        3. Click on the right side arrow of KML file type Resource
        4. Click on Assign this option and assign the resource"*/
        //Expected -1 : Resource (KML file type) should be assigned successfully to students
        try{
            String resourceName =  ReadTestData.readDataByTagName("", "resourcesname", "78");
            new LoginUsingLTI().ltiLogin("78");// Login as an 'Instructor' with AJ_1 sections
            new UploadResources().uploadResources("78",true,true,true);
            new Navigator().NavigateTo("e-Textbook");// Navigate to 'e-Textbook'
            new Click().clickbyxpath("//a[contains(text(),'The Science of Biology')]");//Clck on the chapter
            new Navigator().navigateToTab("Resources");//Navigate to 'Resources'
            new Click().clickbyxpath("//li[@class='resource-content-posts-list']//a[@title ='"+resourceName+LoginUsingLTI.appendChar+"']//..//..//..//..//..//..//a[@class='ls-resource-show-assign-this-block']");//Click on > arrow
            List<WebElement> assignThisElementsList = driver.findElements(By.cssSelector("a[title = 'Assign this']"));
            for(int a=0;a<assignThisElementsList.size();a++){
                if(assignThisElementsList.get(a).isDisplayed()){
                    assignThisElementsList.get(a).click();
                    break;
                }
            }
            new Assignment().assignThisFormFillOtherDetails("27");




            //Expected- 2 : Assigned resource should appear under Assignment tab
            if(new BooleanValue().verifyNotElementPresentByXpath("//div[@class='ls-right-user-content']//a[normalize-space(@title)= '(Shor) "+resourceName+LoginUsingLTI.appendChar+"']//..//..//..//..//..//span[text() = 'posted an assignment']")){
                Assert.fail("Resource (KMZ file type) should be assigned successfully to students");
            }


            //Row No - 80 : 5. Go to Assinments->Current Assignments page
            //Expected - Assignnend resource should appear in assignment->Current Assignments page
            new Navigator().NavigateTo("Current Assignments");
            if(new BooleanValue().verifyNotElementPresentByXpath("//div[@class='ls-assignment-item-detail-section']//a[contains(text(),'"+resourceName+LoginUsingLTI.appendChar+"')]")){
                Assert.fail("Assigned resource has not been appeared in assignment->Current Assignments page");
            }



           /* Row No - 81 : "6. Login as student
            7. Go to Assignments page"*/
            //Expected - Assigned resource should appear in Assignments page
            new LoginUsingLTI().ltiLogin("81");// Login as an 'Student' with CS1 sections
            new Navigator().NavigateTo("Assignments");// Navigate to 'Assignments'
            if(new BooleanValue().verifyNotElementPresentByXpath("//div[@class='ls-assignment-item-detail-section']//span[contains(text(),'"+resourceName+LoginUsingLTI.appendChar+"')]")){
                Assert.fail("Assigned resource should appear in Assignments page");
            }





            //Row No - 82 : 8. Click on Assignment filter and filter the assignment by selecting Non-gradable Learning actiity
            //Expected - Assigned resource should appear under Non-gradable Learning Activity filter only
            new Click().clickbyxpath("//a[contains(@id,'sbSelector_')]");
            new Click().clickbypartiallinkText("Non Gradable Learning Activity");
            if(new BooleanValue().verifyNotElementPresentByXpath("//div[@class='ls-assignment-item-detail-section']//span[contains(text(),'"+resourceName+LoginUsingLTI.appendChar+"')]")){
                Assert.fail("Assigned resource should appear under Non-gradable Learning Activity filter only");
            }




            //Row No - 83 : 9. Click on the assignment
            //Expected - Student should redirect to eTextbook and assigned resource should open in new tab
            //new Click().clickbyxpath("//span[contains(text(),'"+resourceName+LoginUsingLTI.appendChar+"')]");
            if(driver.findElement(By.className("learning-activity-title")).getText().contains(resourceName)){
                new Click().clickByclassname("learning-activity-title");
            }
            List<WebElement> assignmentNamesElementsList = driver.findElements(By.className("ls-assignment-item-wrapper"));
            for(int a=0;a<assignmentNamesElementsList.size();a++){
                System.out.println("assignmentNamesElementsList : " + assignmentNamesElementsList.get(a).getText());
                if(assignmentNamesElementsList.get(a).getText().contains(resourceName)){
                    assignmentNamesElementsList.get(a).click();
                    break;
                }
            }
            Thread.sleep(2000);
            if(!(driver.getCurrentUrl().contains("/eTextBook"))){
                Assert.fail("Student is not able to redirect to eTextbook ");
            }
            if(new BooleanValue().verifyNotElementPresentByXpath("//div[normalize-space(@class)='tab active']//span[contains(@title,'"+resourceName+LoginUsingLTI.appendChar+"')]")){
                Assert.fail("Assigned resource is not opened in new tab");
            }
            if(new BooleanValue().verifyNotElementPresentByXpath("//div[@class='ls-media-link']//a[contains(text(),'"+resourceName+LoginUsingLTI.appendChar+"')]")){
                Assert.fail("Resource is not appered in new tab resource");
            }


            //Row No - 84  : 10. Click on the resource file
            //Expected - Resource file should get opened in new browser tab using google maps
            new Click().clickbyxpath("//div[@class='ls-media-link']//a[contains(text(),'"+resourceName+LoginUsingLTI.appendChar+"')]");// CLick on resource link
            checkBrowserNewTabOpenedInGoogleMaps();

            //Row No - 85 : 11. Go to Assignments page
            //Expected 1- Assignment should show the status as "Submitted" for the assigned learning activity
            new Navigator().NavigateTo("Assignments");
            List<String> assignmentNamesList= new TextFetch().textfetchbylistclasswithoutindex("ls-assignment-item-wrapper");
            List<String> assignmentStatusList= new TextFetch().textfetchbylistclasswithoutindex("ls-assignment-status");
            for(int a=0;a<assignmentNamesList.size();a++){
                if(assignmentNamesList.get(a).contains(resourceName)){
                    System.out.println("Actual : " + actual);
                    actual = assignmentStatusList.get(a).trim();
                    expected = "Your Status: Submitted";
                    Assert.assertEquals(actual,expected,"Assignment is not showing the status as \"Submitted\" for the assigned learning activity");
                }
            }


            //Expected - 2 : Assignment Status box at the top of the assignment page should get updated with Submitted as 1
            actual = new TextFetch().textfetchbyxpath("//div[@id='ls-submitted-assignment']//div[@class='ls-assignment-data-count']");
            expected = "1";
            Assert.assertEquals(actual,expected,"Assignment Status box at the top of the assignment page is not getting updated with Sumbitted as 1");




            //Row No - 87 : 2. Go to eTextbook and go the Assignments tab where the learning activity is associated with
            //Expected - Status should show as Sumbitted in Assignments tab
            new Navigator().NavigateTo("e-Textbook");
            new Click().clickbyxpath("//a[contains(text(),'The Science of Biology')]");//Clck on the chapter
            new Navigator().navigateToTab("Assignments");//Navigate to 'Assignments'
            if(new BooleanValue().verifyNotElementPresentByXpath("//a[contains(text(),'"+resourceName+LoginUsingLTI.appendChar+"')]//..//..//..//span[text() = 'Submitted']")){
                Assert.fail("Status is not showing as Sumbitted in Assignments tab");
            }

        }catch(Exception e){
            Assert.fail("Exception in the testcase 'reservedForAssignmentOptionAsCheckedInUploadResourcePopUpKML' in the class 'KMZFileTypeResourceUploader_Mentor_DifferentUserId'", e);
        }
    }





    @Test(priority =10,enabled = true)
    public void reservedForGradingPolicyOptionAsCheckedInUploadResourcePopUpKML(){

       /* Row No - 88 : "1. Login as an instructor to LS course
        2. Access navigator dropdown and select resources
        3. Select My Library tab on Resources page
        4. Click on upload resource button and upload file with .kml extension"*/
        //Expected - File should be uploaded successfully with filename.kmz extension in the Upload resource pop-up
        try{
            String resourceName =  ReadTestData.readDataByTagName("", "resourcesname", "88");
            //new Driver().startDriver();
            new LoginUsingLTI().ltiLogin("88");// Login as an 'Instructor' with AJ_1 sections
            new UploadResources().uploadResources("88",true,true,true);
            actual = new TextFetch().textfetchbyxpath("//div[@id = 'top-notification-Bar']//div[@class='ls-notification-text-span']");
            expected = "The resource is successfully created.";
            Assert.assertEquals(actual,expected,"File is not uploaded successfully with filename.kmz extension in the Upload resource pop-up");


            //Expected: KMZ file with more than 25MB size should not get uploaded and show Robo-notification saying "There was a problem while uploading a file.Please try again later"







            //Row No - 90 : 5. Select the checkbox of “Reserved for assignment” and do all the mandatory actions and click on Save.
            //Expected - Instructor should be able to upload the file as Assignment Reference.
            new UploadResources().uploadResources("90",false,true,true);
            actual = new TextFetch().textfetchbyxpath("//div[@id = 'top-notification-Bar']//div[@class='ls-notification-text-span']");
            expected = "The Assignment Reference has been successfully created.";
            Assert.assertEquals(actual,expected,"File is not uploaded successfully with filename.kmz extension in the Upload resource pop-up");
            if(new BooleanValue().verifyNotElementPresentByXpath("//div[normalize-space(@class) ='ls-assessment-item-sectn-right ls-my-resource-item-section']//div[@title = '"+resourceName+LoginUsingLTI.appendChar+"']")){
                Assert.fail("File is not saved successfully with filename.kmz extension in the 'My Resources' Tab");
            }




            //Expected - Uploaded Assignment Reference with .kmz extension should show unique icon in My Library tab
            if(new BooleanValue().verifyNotElementPresentByXpath("//div[normalize-space(@class)='ls-assessment-item-title ls-assesment-title-text-ellipsis']//div[normalize-space(@class)='ls-resource-doctypes ls-resource-kml']//..//div[@title = '"+resourceName+LoginUsingLTI.appendChar+"']")){
                Assert.fail("Uploaded Resource with .kmz extension should show unique icon in My Library tab");

            }

        }catch(Exception e){
            Assert.fail("Exception in the testcase 'reservedForGradingPolicyOptionAsCheckedInUploadResourcePopUpKML' in the class 'KMZFileTypeResourceUploader_Mentor_DifferentUserId'", e);
        }
    }






    @Test(priority =11,enabled = true)
    public void uploadKMZFileInCourseStreamKML(){

        /*Row No - 96 : "1. Login as instructor
        2. Go to Course Stream and click on ""Share a new"" pop-up
        3. Click on File and upload KML file"*/
        //Expected - File should be uploaded successfully with filename.kmz extension in the Upload resource pop-up
        try{

            String filename =  ReadTestData.readDataByTagName("", "filename", "96");
            //new Driver().startDriver();
            new LoginUsingLTI().ltiLogin("96");// Login as an 'Instructor' with AJ_1 sections
            new Navigator().NavigateTo("Course Stream");
            new Click().clickByclassname("share-to-ls-label");
            new FileUpload().fileUpload("96",false);
            if(driver.findElements(By.id("file-thumbnail-img")).size()==0){
                Assert.fail("File has not been uploaded successfully");
            }







            //Expected - KMZ file with more than 25MB size should not get uploaded and show Robo-notification saying "There was a problem while uploading a file.Please try again later"








            //Row No - 98 : 4. Click on the KML file which is present in the Course Stream
            //Expected - KMZ file should get opened in new browser tab using google maps
            System.out.println("File Name : " + filename);
            driver.findElement(By.id("post-submit-button")).click();
            Thread.sleep(10000);
            new Click().clickbylinkText(filename);
            checkBrowserNewTabOpenedInGoogleMaps();



            /*Row No - 99 : "5. Login as student
            6. Go to Course Stream and click on ""Share a new"" pop-up
            7. Click on File and upload KML file"*/
            //Expecetd  - File should be uploaded successfully with filename.kmz extension in the Upload resource pop-up
            new LoginUsingLTI().ltiLogin("99");// Login as a 'Student' with AJ_1 sections
            new Navigator().NavigateTo("Course Stream");
            new Click().clickByclassname("share-to-ls-label");
            new FileUpload().fileUpload("96", false);
            if(driver.findElements(By.id("file-thumbnail-img")).size()==0){
                Assert.fail("File has not been uploaded successfully");
            }







            //Expected - KMZ file with more than 10MB size should not get uploaded and show Robo-notification saying "There was a problem while uploading a file.Please try again later"





            //Row No - 101 : 8. Click on the KML file which is present in the Course Stream
            //Expected - KMZ file should get opened in new browser tab using google maps
            System.out.println("File Name : " + filename);
            driver.findElement(By.id("post-submit-button")).click();
            Thread.sleep(10000);
            new Click().clickbylinkText(filename);
            checkBrowserNewTabOpenedInGoogleMaps();

        }catch(Exception e){
            Assert.fail("Exception in the testcase 'uploadKMZFileInCourseStreamKML' in the class 'KMZFileTypeResourceUploader_Mentor_DifferentUserId'", e);
        }
    }





    @Test(priority =12,enabled = true)
    public void uploadKMZFileInMyNotesPageKML(){

        /*Row No - 102 : "1. Login as student
        2. Go to My Notes page
        3. Click on ""+New Note"" button
        4. Upload KML file and do all the mandatory actions and click on Save"*/
        //Expected  - File should be uploaded successfully with filename.kml extension in the My notes page

        try{
            String filename =  ReadTestData.readDataByTagName("", "filename", "102");
            //new Driver().startDriver();
            new LoginUsingLTI().ltiLogin("102");// Login as a 'Student' with AJ_1 sections
            new Navigator().NavigateTo("My Notes");
            new FileUpload().fileUploadFromNote("102",true);
            if(new BooleanValue().verifyNotElementPresentByXpath("//a[text() = '"+filename+"']")){
                Assert.fail("File should has not been uploaded successfully with filename.kmz extension in the My notes page");
            }





            //Expected - KML file with more than 10MB size should not get uploaded and show Robo-notification saying "There was a problem while uploading a file.Please try again later"






            //Expected - Uploaded file should be shown in the my notes page along with unique icon of KMZ file
            if(new BooleanValue().verifyNotElementPresentByXpath("//a[text() = '"+filename+"']//span")){
                Assert.fail("Uploaded file is not shown in the my notes page along with unique icon of KMZ file");
            }


            //Row N0 - 105 : 5. Click on the uploaded KML file
            //Expected- KMZ file should get opened in new browser tab using google maps
            new Click().clickbylinkText(filename);
            checkBrowserNewTabOpenedInGoogleMaps();



        }catch(Exception e){
            Assert.fail("Exception in the testcase 'uploadKMZFileInCourseStreamKML' in the class 'KMZFileTypeResourceUploader_Mentor_DifferentUserId'", e);
        }
    }

    public void fillOtherDetailsInAssignThisForm(String gradingPolicyResourceName, String dataIndex){
        try{
            new Click().clickbyxpath("//a[@class= 'sbSelector'  and text() = 'Select your Assignment Reference']");
            new Click().clickbyxpath("//a[text() = '"+gradingPolicyResourceName+"']");
            new TextSend().textsendbyid("Description","additional-notes");
            String duedate = ReadTestData.readDataByTagName("", "duedate", dataIndex);
            String duetime = ReadTestData.readDataByTagName("", "duetime", dataIndex);
            String gradeable = ReadTestData.readDataByTagName("", "gradeable", dataIndex);
            String  assignmentpolicy = ReadTestData.readDataByTagName("", "assignmentpolicy", dataIndex);
            String gradingpolicy = ReadTestData.readDataByTagName("", "gradingpolicy", dataIndex);

            driver.findElement(By.id("due-time")).click();
            List<WebElement> elements = driver.findElements(By.xpath("//li"));
            for(WebElement time : elements)
            {
                System.out.println("time.getText() : " + time.getText());
                if(time.getText().equals(duetime))
                {
                    time.click();
                    System.out.println("Entered & clicked");
                    break;
                }
            }
            driver.findElement(By.id("due-date")).click();
            Thread.sleep(2000);
            driver.findElement(By.cssSelector("span[class='ui-icon ui-icon-circle-triangle-e']")).click();
            Thread.sleep(2000);
            driver.findElement(By.linkText(duedate)).click();
            Thread.sleep(2000);
            if(gradeable.equals("true"))
            {
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("ir-ls-assign-dialog-gradable-label-check")));
                Thread.sleep(2000);
            }
            if(gradeable.equals("true") && assignmentpolicy!= null)
            {

                //click on  Choose your assignment policy dropdown
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.linkText("Select an assignment policy")));
                Thread.sleep(2000);
                driver.findElement(By.linkText(assignmentpolicy)).click();//select a policy
                Thread.sleep(2000);
            }



            //((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("span[class='btn sty-green submit-assign']")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.xpath("//span[starts-with(normalize-space(@class),'btn sty-green submit-assign')]")));
            Thread.sleep(5000);
        }catch(Exception e){
            Assert.fail("Exception in method 'fillOtherDetailsInAssignThisForm' in class 'KMZFileTypeResourceUploader_Mentor'",e);
        }
    }

    public void checkBrowserNewTabOpenedInGoogleMaps(){

        try{
            Thread.sleep(5000);
            List<String> browserTabs = new ArrayList<String>(driver.getWindowHandles());
            driver.switchTo().window(browserTabs .get(1));
            actual = driver.getTitle();
            expected  = "https://s3.amazonaws.com/content-d/253/user/";
            if(!(actual.contains(expected))){
                Assert.fail("Resource file has not opened in new browser tab");
            }
            driver.close();
            driver.switchTo().window(browserTabs .get(0));
        }catch(Exception e){
            Assert.fail("Exception in method 'checkBrowserNewTabOpenedInGoogleMaps' in the class 'KMZFileTypeResourceUploader_Mentor_DifferentUserId'",e);
        }
    }


    public void checkBrowserNewTabOpenedInGoogleMaps(String winHandleBefore){

        try{
            //Switch to new window opened
            for(String winHandle : driver.getWindowHandles()){
                driver.switchTo().window(winHandle);
            }
            // Perform the actions on new window
            new UIElement().waitAndFindElement(By.linkText("Sign in"));
            actual = driver.getCurrentUrl();
            expected  = "https://s3.amazonaws.com/content-d/253/user/";
            if(!(actual.contains(expected))){
                Assert.fail("Resource file has not opened in new browser tab");
            }
            driver.close();
            //Switch back to original browser (first window)
            driver.switchTo().window(winHandleBefore);
           //continue with original browser (first window)
        }catch(Exception e){
            Assert.fail("Exception in method 'checkBrowserNewTabOpenedInGoogleMaps' in the class 'KMZFileTypeResourceUploader_Mentor_DifferentUserId'",e);
        }

    }
}
