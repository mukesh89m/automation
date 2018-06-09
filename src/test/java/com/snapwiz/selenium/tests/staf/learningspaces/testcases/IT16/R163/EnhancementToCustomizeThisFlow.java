package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT16.R163;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.Click;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.TextFetch;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.util.List;

/*
 * Created by Sumit on 8/28/2014.
 */
public class EnhancementToCustomizeThisFlow extends Driver{

    @Test
    public void enhancementToCustomizeThisFlow()
    {
        try
        {
            String name = ReadTestData.readDataByTagName("", "name", "69");
            String customassignmentname = ReadTestData.readDataByTagName("", "customassignmentname", "69");
            String name2 = ReadTestData.readDataByTagName("", "name2", "69");
            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", "69");
            String questiontext = ReadTestData.readDataByTagName("", "questiontext", "69");
            String searchquestion = ReadTestData.readDataByTagName("", "searchquestion", "69");

            new Assignment().create(69);//create assignment
            new Assignment().addQuestionsWithCustomizedQuestionText(69, "qtn-type-true-false-img", assessmentname, 3);

            new LoginUsingLTI().ltiLogin("69");    //login as instructor
            new Navigator().NavigateTo("Question Banks");

             //TC row no. 69...3. Click on "Customize This" link, present below any assessment...Instructor should navigate to New Assignment tab where the questions of existing assignment should be present
            new Click().clickByclassname("customize-this");
            Thread.sleep(2000);
            String tab = driver.findElement(By.cssSelector("div[class='tab active']")).getText();
            if(!tab.equals("New Assignment"))
                Assert.fail("Click on \"Customize This\" link, Instructor does not navigate to New Assignment tab.");

            String saveButton = new TextFetch().textfetchbyid("ls-ins-save-assigment-btn");
            if(!saveButton.contains("SAVE FOR LATER"))
                Assert.fail("\"SAVE FOR LATER\" button is absent in New Assignment tab.");

            String assignButton = new TextFetch().textfetchbyid("ls-assign-now-assigment-btn");
            if(!assignButton.contains("ASSIGN NOW"))
                Assert.fail("\"ASSIGN NOW\" button is absent in New Assignment tab.");

            new Click().clickByid("ls-ins-assignment-name");//click on Name field
            driver.findElement(By.id("ls-ins-edit-assignment")).sendKeys(name);
            driver.findElement(By.cssSelector("span.save-for-later-text")).click();//click on Save For Later
            String notice = new TextFetch().textfetchbyclass("ls-notification-text-span");
            if(!notice.equals("You have not added any questions for this custom assessment. Please add questions before saving the assessment"))
                Assert.fail("No notification appear on clicking SAVE FOR LATER button without selecting question from New Assignment tab.");
            Thread.sleep(5000);
            new SearchQuestionInCustomCourseAssignemnt().searchQuestionInCustomCourseAssignemnt(searchquestion);
            new AssignLesson().selectQuestionForCustomAssignment("69");
            Thread.sleep(3000);
            driver.findElement(By.cssSelector("span.save-for-later-text")).click();//click on Save For Later
            String notice1 = new TextFetch().textfetchbyclass("ls-notification-text-span");
            System.out.println("notice1: "+notice1);
            if(!notice1.equals("Saved Custom Assessment Successfully."))
                Assert.fail("No notification appear on clicking SAVE FOR LATER button after selecting a question from New Assignment tab.");

            new Click().clickBycssselector("span[title='My Question Bank']");//go to My Library
            String assignmentName = new TextFetch().textfetchbyclass("resource-title");
            if(!assignmentName.equals(name))
                Assert.fail("Custom Assignment is not present in My Question Bank");

            //TC row no. 77..."Assign This" and "Delete This" links should be present
            driver.findElement(By.className("assign-this")).click();//click on Assign This link
            int assignThis = driver.findElements(By.className("ir-ls-assign-dialog-content-wrapper")).size();
            if(assignThis == 0)
                Assert.fail("\"Assign This\" links is not present for Custom Assignment under My Library.");

            int deleteLink = driver.findElements(By.cssSelector("span[title='Delete This']")).size();
            if(deleteLink == 0)
                Assert.fail("\"Delete This\" links is not present for Custom Assignment under My Library.");

            //TC row no. 79...10. Click on 'Assign This' link and assign the assignment...Instructor should navigate to Assignment Summary page
            new AssignLesson().Assigncustomeassignemnt(69);
            String url = driver.getCurrentUrl();
            if(!url.contains("assignment"))
                Assert.fail("After assigning a custom assignment the instructor doesn't navigate to Assignment page.");

            //TC row no. 80...11. Go to Question Banks page 12. Go to My Library tab"..."Assign This" link should be present for the same Custom assignment
            new Navigator().NavigateTo("Question Banks");
            new Click().clickBycssselector("span[title='My Question Bank']");//go to My Question Bank
            new Click().clickByclassname("assign-this");//click on assign this link
            int assignThis1 = driver.findElements(By.className("ir-ls-assign-dialog-content-wrapper")).size();
            if(assignThis1 == 0)
                Assert.fail("\"Assign This\" links is not present for Custom Assignment under My Library once the assignment is assigned.");

            //TC row no. 82...13. Click on Assign This Link for the same resource 14. Fill the details with new entries and assign it"....New entry should be created for the assignment in Assignment Summary page
            new AssignLesson().Assigncustomeassignemnt(69);
            int noOfAssignments = driver.findElements(By.cssSelector("span[class='ls-assignment-name instructor-assessment-review']")).size();
            if(noOfAssignments != 2)//there will be two assignments
                Assert.fail("On re-assigning the custom assignment new entry is not created for the assignment in Assignment Summary page.");

           /* "15. Go to Question Banks page
            16. Click on Customize This link present below any assessment
            17. Select some question and enter the assignment name
            18. Click on ""ASSIGN NOW"" button"*/


            //TC row no. 83..."Assign This" pop-up should appear
            new Navigator().NavigateTo("Question Banks");
            Thread.sleep(2000);
            new Click().clickByclassname("customize-this");//click on Customize this link
            // driver.findElement(By.className("Customize This")).click();//click on Customize this link
            Thread.sleep(2000);
            new AssignLesson().selectQuestionForCustomAssignment("69");//select two question
            new Click().clickByid("ls-ins-assignment-name");//click on Name field
            driver.findElement(By.id("ls-ins-edit-assignment")).sendKeys(customassignmentname);//enter name of assignment
            driver.findElement(By.cssSelector("body")).click();//click out side
            new Click().clickByid("ls-assign-now-assigment-btn");//click on ASSIGN NOW button
            int assignThisPopUp = driver.findElements(By.id("ir-ls-assign-dialog")).size();
            if(assignThisPopUp != 1)
                Assert.fail("\"Assign This\" pop-up does not appear on clicking ASSIGN NOW button from custom assignment page.");
            driver.findElement(By.cssSelector("body")).click();//click out side

            //TC row no. 84...19. Fill the entries and click on assign button...Customize tab should be closed as soon as instructor clicks on assign button
            new AssignLesson().assignCustomAssignment(69);
            String url1 = driver.getCurrentUrl();
            if(!url1.contains("assignment"))
                Assert.fail("After assigning a custom assignment the Customize tab does not closes and instructor doesn't navigate to Assignment page.");

            //TC row no. 85...Custom assignment should be present in My library tab
            new Navigator().NavigateTo("Question Banks");
            new Click().clickBycssselector("span[title='My Question Bank']");//go to My Question Bank
            String assignmentName1 = new TextFetch().textfetchbyclass("resource-title");
            if(!assignmentName1.equals(customassignmentname))
                Assert.fail("Custom Assignment is not present in My library");

            //TC row no. 86...20. Click on "+Create Custom Assignment" button..."New Assignment" tab should opened
            new Click().clickByid("customAssignment");//Click on "+Create Custom Assignment" button
            String newTab = new TextFetch().textfetchbycssselector("div[class='tab active']");
            if(!newTab.equals("New Assignment"))
                Assert.fail("Click on \"+Create Custom Assignment\" button from My library the New Assignment tab does not open.");

            String saveButton1 = new TextFetch().textfetchbyid("ls-ins-save-assigment-btn");
            if(!saveButton1.contains("SAVE FOR LATER"))
                Assert.fail("\"SAVE FOR LATER\" button is absent in New Assignment tab.");

            /*TC row no. 87...
             "New Assignment"" tab should have two buttons:
            i) SAVE FOR LATER (Add to My library)
            ii) ASSIGN NOW (Assign to the Class)
            */
            String assignButton1 = new TextFetch().textfetchbyid("ls-assign-now-assigment-btn");
            if(!assignButton1.contains("ASSIGN NOW"))
                Assert.fail("\"ASSIGN NOW\" button is absent in New Assignment tab.");


            /*TC row no. 91
            "23. Enter assignment name
            24. Click on ""SAVE FOR LATER"""
            Following notification should displayed at top of the screen: "You have not added any question for this custom assessment.Please add question before saving the assessment".
             */
            new Click().clickByid("ls-ins-assignment-name");//click on Name field
            driver.findElement(By.id("ls-ins-edit-assignment")).sendKeys(name2);
            driver.findElement(By.cssSelector("span.save-for-later-text")).click();//click on Save For Later
            String notice2 = new TextFetch().textfetchbyclass("ls-notification-text-span");
            if(!notice2.equals("You have not added any questions for this custom assessment. Please add questions before saving the assessment"))
                Assert.fail("No notification appear on clicking SAVE FOR LATER button without selecting question from New Assignment tab.");


            /*TC row no. 92
            "25. Search some questions and select them
            Click on ""SAVE FOR LATER"""
            "Saved Custom Assignment Successfully"  notification should appear at the top of the screen
             */
            new CreateCustomAssignment().createCustomAssignment(questiontext, "69");
            driver.findElement(By.cssSelector("span.save-for-later-text")).click();//click on Save For Later
            String notice3 = new TextFetch().textfetchbyclass("ls-notification-text-span");
            if(!notice3.equals("Saved Custom Assessment Successfully."))
                Assert.fail("No notification appear on clicking SAVE FOR LATER button after selecting a question from New Assignment tab.");
            /*
            TC row no. 93
            26. Go to My Library tab
            Custom Assignment should be present
             */
            new Click().clickBycssselector("span[title='My Question Bank']");//go to My Question Bank
            String assignmentName2 = new TextFetch().textfetchbyclass("resource-title");
            if(!assignmentName2.equals(customassignmentname))
                Assert.fail("Custom Assignment is not present in My library");

            /*
            TC row no. 94
            "Assign This" and "Delete This" links should be present
             */

            Thread.sleep(2000);
            int index = 0;
            List<WebElement> deleteLink2 = driver.findElements(By.cssSelector("span[title='Delete This']"));
            for(WebElement link : deleteLink2) {
                System.out.println("deleteLink2: " + link.getText());
                if(link.getText().contains("Delete")){
                    index++;
                }
            }
            if(index != 1)//there will three assignments and one Delete link will be present
                Assert.fail("\"Delete This\" links is not present for Custom Assignment under My Library.");

            new Click().clickByclassname("assign-this");//click on assign this link
            int assignThis2 = driver.findElements(By.className("ir-ls-assign-dialog-content-wrapper")).size();
            if(assignThis2 != 1)//there will three assignments and one Assign this link will be present
                Assert.fail("\"Assign This\" links is not present for Custom Assignment under My Library.");

            new Navigator().NavigateTo("Question Banks");
            new Click().clickBycssselector("span[title='My Question Bank']");//go to My Question Bank
            //TC row no. 96...27. Click on 'Assign This' link and assign the assignment...Instructor should navigate to Assignment Summary page
            new AssignLesson().Assigncustomeassignemnt(69);
            String url2 = driver.getCurrentUrl();
            if(!url2.contains("assignment"))
                Assert.fail("After assigning a custom assignment the instructor doesn't navigate to Assignment page.");

            /*
            TC row no. 97...
            "28. Go to Question Banks page
            29. Go to My Library tab"
            "Assign This" link should be present for the same Custom assignment
             */
            new Navigator().NavigateTo("Question Banks");
            new Click().clickBycssselector("span[title='My Question Bank']");//go to My Question Bank
            new Click().clickByclassname("assign-this");//click on assign this link
            String assignThis3 = driver.findElement(By.className("ir-ls-assign-dialog-header")).getText();
            if(!assignThis3.contains(customassignmentname))
                Assert.fail("\"Assign This\" links is not present for Custom Assignment under My Library once the assignment is assigned.");

            /*
            TC row no. 99
            "30. Click on Assign This Link for the same resource
            31. Fill the details with new entries and assign it"
            New entry should be created for the assignment in Assignment Summary page

             */
            new AssignLesson().Assigncustomeassignemnt(69);
            Thread.sleep(3000);
            int assignments = driver.findElements(By.cssSelector("span[class='ls-assignment-name instructor-assessment-review']")).size();
            if(assignments!= 5)//there will be 5 assignment in summary page
                Assert.fail("New entry is not created for the assignment in Assignment Summary page.");

            /*
            TC row no. 100
            "32. Go to Question Banks page
            33. Click on Customize This link present below any assessment
            34. Select some question and enter the assignment name
            35. Click on ""ASSIGN NOW"" button"
            "Assign This" pop-up should appear
             */
            new Navigator().NavigateTo("Question Banks");
            new Click().clickByclassname("customize-this");//click on Customize this link
            Thread.sleep(2000);
            new AssignLesson().selectQuestionForCustomAssignment("69");//select two question
            new Click().clickByid("ls-ins-assignment-name");//click on Name field
            driver.findElement(By.id("ls-ins-edit-assignment")).sendKeys(customassignmentname);//enter name of assignment
            driver.findElement(By.cssSelector("body")).click();//click out side
            new Click().clickByid("ls-assign-now-assigment-btn");//click on ASSIGN NOW button
            int assignThisPopUp1 = driver.findElements(By.id("ir-ls-assign-dialog")).size();
            if(assignThisPopUp1 != 1)
                Assert.fail("\"Assign This\" pop-up does not appear on clicking ASSIGN NOW button from custom assignment page.");

            //TC row no. 101...19. Fill the entries and click on assign button...Customize tab should be closed as soon as instructor clicks on assign button
            new AssignLesson().assignCustomAssignment(69);
            String url3 = driver.getCurrentUrl();
            if(!url3.contains("assignment"))
                Assert.fail("After assigning a custom assignment the Customize tab does not closes and instructor doesn't navigate to Assignment page.");

            //TC row no. 102...Custom assignment should be present in My library tab
            new Navigator().NavigateTo("Question Banks");
            new Click().clickBycssselector("span[title='My Question Bank']");//go to My Question Bank
            String assignmentName3 = driver.findElement(By.className("resource-title")).getText();
            if(!assignmentName3.contains(customassignmentname))
                Assert.fail("Custom Assignment is not present in My library");


        }
        catch (Exception e)
        {
            Assert.fail("Exception in test case enhancementToCustomizeThisFlow in class EnhancementToCustomizeThisFlow.", e);
        }
    }

}
