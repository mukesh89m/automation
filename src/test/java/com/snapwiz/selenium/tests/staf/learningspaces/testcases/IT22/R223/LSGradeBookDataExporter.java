package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT22.R223;

import au.com.bytecode.opencsv.CSVReader;
import com.snapwiz.selenium.tests.staf.learningspaces.Config;
import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.CurrentAssignments;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Gradebook.Gradebook;
import com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT18.R1816.ReleaseGradeForAll;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.Click;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.UIElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by root on 10/26/15.
 */
public class LSGradeBookDataExporter extends Driver{

    @Test(priority = 1,enabled = true)
    public void exportGradeBookGrade(){
        try{
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
            Gradebook gradebook = PageFactory.initElements(driver, Gradebook.class);
            int dataIndex  = 58;
            new Assignment().create(dataIndex);
            new LoginUsingLTI().ltiLogin("59");
            new LoginUsingLTI().ltiLogin("58");
            new Navigator().NavigateTo("Gradebook");
            gradebook.getGradebookWeighting().click();
            gradebook.getEnterGradebookWeighting().get(0).clear();//clear practice textField
            gradebook.getEnterGradebookWeighting().get(0).sendKeys("60");//type in practice textField
            gradebook.getEnterGradebookWeighting().get(5).clear();//clear uncategorized
            gradebook.getEnterGradebookWeighting().get(5).sendKeys("40");//type in uncategorized
            gradebook.getgradebookWeightingSaveButton().click();//click on save button
            driver.navigate().refresh();//page refresh
            new Assignment().assignToStudent(dataIndex);

            new LoginUsingLTI().ltiLogin("59");
            new Navigator().NavigateTo("Assignments");
            currentAssignments.getAssessmentName().click();//open lesson assignment
            Thread.sleep(10000);
            new AttemptQuestion().trueFalse(false,"incorrect",63);
            new Click().clickbylinkText("Finish Assignment");
            new UIElement().waitAndFindElement(By.className("next-or-submit-link"));
            new Click().clickByclassname("next-or-submit-link");
            new LoginUsingLTI().ltiLogin("58");
            new Assignment().releaseGrades(dataIndex, "Release Grade for All");
            new Navigator().NavigateTo("Gradebook");

            //Expected - 1."Gradebook" Page should be displayed.
            Assert.assertEquals(gradebook.getGradeBookHeader().getText(),"Gradebook","Gradebook Page should be displayed.");

            //Expected - 2."Export to CSV" button should be displayed.
            //Row No - 108 : 4.Click on "Export to CSV" button.
            //Expected - 3.The instructor/mentor should be able to click on the “Export to CSV” button
            Thread.sleep(1000);
            new ExportToCSVGradebookPage().exportCSVFile();
            Thread.sleep(4000);
            String context_title = ReadTestData.readDataByTagName("", "context_title", "59");
            System.out.println("context_title 1 : " + context_title);
            CSVReader path = new ReadFile().readCSVFilePath("59",context_title);
            validateCSVFileContents("59",path);


            new Assignment().create(60);
            new DirectLogin().directLoginWithCreditial("instructor2.geography@epub.com","snapwiz",dataIndex);
            new Navigator().NavigateTo("Gradebook");
            gradebook.getGradebookWeighting().click();
            gradebook.getEnterGradebookWeighting().get(0).clear();//clear practice textField
            gradebook.getEnterGradebookWeighting().get(0).sendKeys("60");//type in practice textField
            gradebook.getEnterGradebookWeighting().get(5).clear();//clear uncategorized
            gradebook.getEnterGradebookWeighting().get(5).sendKeys("40");//type in uncategorized
            gradebook.getgradebookWeightingSaveButton().click();//click on save button
            driver.navigate().refresh();//page refresh
            new Assignment().assignToStudent(60);
            new DirectLogin().directLoginWithCreditial("student4.geography@epub.com","snapwiz",dataIndex);
            new Navigator().NavigateTo("Assignments");
            currentAssignments.getAssessmentName().click();//open lesson assignment
            Thread.sleep(10000);
            new AttemptQuestion().trueFalse(false,"incorrect",61);
            new Click().clickbylinkText("Finish Assignment");
            new LoginUsingLTI().ltiLogin("58");
            new Assignment().releaseGrades(dataIndex, "Release Grade for All");
            new Navigator().NavigateTo("Gradebook");

            //Expected - 1."Gradebook" Page should be displayed.
            Assert.assertEquals(gradebook.getGradeBookHeader().getText(),"Gradebook","Gradebook Page should be displayed.");

            //Expected - 2."Export to CSV" button should be displayed.
            //Row No - 108 : 4.Click on "Export to CSV" button.
            //Expected - 3.The instructor/mentor should be able to click on the “Export to CSV” button
            Thread.sleep(1000);
            new ExportToCSVGradebookPage().exportCSVFile();
            Thread.sleep(4000);
            context_title = ReadTestData.readDataByTagName("", "context_title", "61");
            System.out.println("context_title 1 : " + context_title);
            path = new ReadFile().readCSVFilePath("60",context_title);
            validateCSVFileContentsFOrExistingUsers("60",path);



        }catch(Exception e){
            Assert.fail("Exception in test method 'exportGradeBookGrade' in class 'GradeBookDataExporter' ", e);
        }
    }


    public void validateCSVFileContents(String dataIndex,CSVReader path){
        try{

            String assessmentName =  ReadTestData.readDataByTagName("", "assessmentname", dataIndex);
            String familyname =  ReadTestData.readDataByTagName("", "familyname", dataIndex);
            String givenname = ReadTestData.readDataByTagName("", "givenname", dataIndex);
            String context_title = ReadTestData.readDataByTagName("", "context_title", dataIndex);

            String [] nextLine;
            ArrayList<String>  cellsContent = new ArrayList<>();
            while((nextLine=path.readNext())!=null)
            {
                for(int a=0;a<7;a++) {
                    String assignmentStatus = nextLine[a];
                    cellsContent.add(assignmentStatus);
                    if(nextLine.length==1){
                        break;
                    }
                }
            }

            for(int a=0;a<cellsContent.size();a++){
                System.out.println("assignmentstatus :  " + cellsContent.get(a));

            }

            //Expected  - 4.First row should have "First name" text in first columns.
            if(!cellsContent.get(0).equals("First Name"))
                Assert.fail("First row should have \"First name\" text in first columns.");

            //Expected - 5.In second row and first column should be contain Student First name.
            System.out.println("givenname : " + givenname);
            System.out.println("cellsContent.get(7) : " + cellsContent.get(7));

            if(!cellsContent.get(7).trim().equals(givenname))
                Assert.fail("In second row and first column should be contain Student First name.");


            //Expected - 6.First row should have "Last name" text in second columns.
            if(!cellsContent.get(1).equals("Last Name"))
                Assert.fail("First row should have \"Last name\" text in second columns.");


            //Expected - 7.In second row and second column should be contain Student Last name.
            if(!cellsContent.get(8).equals(familyname))
                Assert.fail("In second row and second column should be contain Student Last name.");


            //Expected - 8.First row should have "course name" text in third column.
            if(!cellsContent.get(2).equals("Course name"))
                Assert.fail("First row should have \"course name\" text in third column.");


            //Expected - 9.In second row and third column should be contain course name.
            if(!cellsContent.get(9).equals(Config.lscourse))
                Assert.fail("In second row and third column should be contain course name.");


            //Expected - 10.First row should have "class Section name" text in fourth column.
            if(!cellsContent.get(3).equals("Class-section name"))
                Assert.fail("First row should have \"class Section name\" text in fourth column.");


            //Expected - 11.In second row and fourth column should be contain class section name.
            if(!cellsContent.get(10).equals(context_title))
                Assert.fail("In second row and fourth column should be contain class section name.");



            //Expected - 12.First row should have "Date"  text in fifth column.
            if(!cellsContent.get(4).equals("Date"))
                Assert.fail("First row should have \"Date\"  text in fifth column.");


            //13.In second row and fifth column should be contain current Date in (MM-DD-YYYY) format.
            SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
            String today = sdf.format(new Date());
            if(!cellsContent.get(11).equals(today))
                Assert.fail("In second row and fifth column should be contain current Date in (MM-DD-YYYY) format.");


            //Expected - 14.First row should have "Overall Score(%)"  text in sixthcolumn.
            if(!cellsContent.get(5).equals("Overall Score (%)"))
                Assert.fail("First row should have \"Overall Score(%)\"  text in sixthcolumn");


            //Expected - 15.In second row and sixth column should be contain Overall score.
            //Expected - 16.Overall score should be displayed in  "<#score>%" format.
            if(!cellsContent.get(12).equals("0%"))
                Assert.fail("In second row and sixth column should be contain Overall score. && 16.Overall score should be displayed in  \"<#score>%\" format.");


            //Expected - 17.First row should have "<Assignment_name>"  text in after sixth column.
            if(!cellsContent.get(6).equals(assessmentName))
                Assert.fail("First row should have \"Overall Score(%)\"  text in sixthcolumn");



            //Expected - 18.Below assignment name grades obtained by the student should be displayed.
            if(!cellsContent.get(13).equals("0"))
                Assert.fail("Below assignment name grades obtained by the student should be displayed.");


            //Expected -  21.The first column should have “Total Points Available” as text.
            if(!cellsContent.get(15).equals("Total Points Available"))
                Assert.fail("The first column should have “Total Points Available” as text.");


            //Expected - 22.After Sixth column should have "#total point" corresponding to assignments defined in row 1.
            if(!cellsContent.get(21).equals("2.0"))
                Assert.fail("After Sixth column should have \"#total point\" corresponding to assignments defined in row 1");


        }catch(Exception e){
            Assert.fail("Exception in test method 'validateCSVFileContents' in class 'WPLSGradeBookDataExporter'",e);
        }
    }




    public void validateCSVFileContentsFOrExistingUsers(String dataIndex,CSVReader path){
        try{

            String givenname = "June";
            String familyname =  "Chadway";
            String assessmentName =  "20 Assessment";

            String [] nextLine;
            ArrayList<String>  cellsContent = new ArrayList<>();
            while((nextLine=path.readNext())!=null)
            {
                for(int a=0;a<7;a++) {
                    String assignmentStatus = nextLine[a];
                    cellsContent.add(assignmentStatus);
                    if(nextLine.length==1){
                        break;
                    }
                }
            }

            for(int a=0;a<cellsContent.size();a++){
                System.out.println("assignmentstatus :  " + cellsContent.get(a));

            }

            //Expected  - 4.First row should have "First name" text in first columns.
            if(!cellsContent.get(0).equals("First Name"))
                Assert.fail("First row should have \"First name\" text in first columns.");

            //Expected - 5.In second row and first column should be contain Student First name.
            System.out.println("givenname : " + givenname);
            System.out.println("cellsContent.get(7) : " + cellsContent.get(7));

            if(!cellsContent.get(7).trim().equals(givenname))
                Assert.fail("In second row and first column should be contain Student First name.");


            //Expected - 6.First row should have "Last name" text in second columns.
            if(!cellsContent.get(1).equals("Last Name"))
                Assert.fail("First row should have \"Last name\" text in second columns.");


            //Expected - 7.In second row and second column should be contain Student Last name.
            if(!cellsContent.get(8).equals(familyname))
                Assert.fail("In second row and second column should be contain Student Last name.");


            //Expected - 8.First row should have "course name" text in third column.
            if(!cellsContent.get(2).equals("Course name"))
                Assert.fail("First row should have \"course name\" text in third column.");


            //Expected - 9.In second row and third column should be contain course name.
            if(!cellsContent.get(9).equals(Config.course))
                Assert.fail("In second row and third column should be contain course name.");


            //Expected - 10.First row should have "class Section name" text in fourth column.
            if(!cellsContent.get(3).equals("Class-section name"))
                Assert.fail("First row should have \"class Section name\" text in fourth column.");


            //Expected - 11.In second row and fourth column should be contain class section name.
            if(!cellsContent.get(10).equals(Config.course))
                Assert.fail("In second row and fourth column should be contain class section name.");



            //Expected - 12.First row should have "Date"  text in fifth column.
            if(!cellsContent.get(4).equals("Date"))
                Assert.fail("First row should have \"Date\"  text in fifth column.");


            //13.In second row and fifth column should be contain current Date in (MM-DD-YYYY) format.
            SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
            String today = sdf.format(new Date());
            if(!cellsContent.get(11).equals(today))
                Assert.fail("In second row and fifth column should be contain current Date in (MM-DD-YYYY) format.");


            //Expected - 14.First row should have "Overall Score(%)"  text in sixthcolumn.
            if(!cellsContent.get(5).equals("Overall Score (%)"))
                Assert.fail("First row should have \"Overall Score(%)\"  text in sixthcolumn");


            //Expected - 15.In second row and sixth column should be contain Overall score.
            //Expected - 16.Overall score should be displayed in  "<#score>%" format.
            if(!cellsContent.get(12).equals("NA"))
                Assert.fail("In second row and sixth column should be contain Overall score. && 16.Overall score should be displayed in  \"<#score>%\" format.");


            //Expected - 17.First row should have "<Assignment_name>"  text in after sixth column.
            if(!cellsContent.get(6).equals(assessmentName))
                Assert.fail("First row should have \"Overall Score(%)\"  text in sixthcolumn");



            //Expected - 18.Below assignment name grades obtained by the student should be displayed.
            if(!cellsContent.get(13).equals("Grades Not Released"))
                Assert.fail("Below assignment name grades obtained by the student should be displayed.");


            //Expected -  21.The first column should have “Total Points Available” as text.
            if(!cellsContent.get(29).equals("Total Points Available"))
                Assert.fail("The first column should have “Total Points Available” as text.");


            //Expected - 22.After Sixth column should have "#total point" corresponding to assignments defined in row 1.
            if(!cellsContent.get(35).equals("1.0"))
                Assert.fail("After Sixth column should have \"#total point\" corresponding to assignments defined in row 1");


        }catch(Exception e){
            Assert.fail("Exception in test method 'validateCSVFileContents' in class 'WPLSGradeBookDataExporter'",e);
        }
    }


    public void deleteFile(Path path){
        try{
            Files.delete(path);
        }catch(Exception e){
            Assert.fail("Exception in test method 'deleteFile' in class 'WPLSGradeBookDataExporter'",e);
        }
    }
}
