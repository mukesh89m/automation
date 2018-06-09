package com.snapwiz.selenium.tests.staf.edulastic.testcases.IT1.datacreation;

import com.snapwiz.selenium.tests.staf.edulastic.Driver;
import com.snapwiz.selenium.tests.staf.edulastic.Config;
import com.snapwiz.selenium.tests.staf.edulastic.ReadTestData;
import com.snapwiz.selenium.tests.staf.edulastic.apphelpers.Login;
import com.snapwiz.selenium.tests.staf.edulastic.apphelpers.RandomString;
import com.snapwiz.selenium.tests.staf.edulastic.uihelper.Click;
import com.snapwiz.selenium.tests.staf.edulastic.uihelper.DropDown;
import com.snapwiz.selenium.tests.staf.edulastic.uihelper.TextSend;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 29/1/15.
 */
public class EdulasticTeacher extends Driver {

    @Test
    public void edulasticTeacher() {
        try {


            for (int i = 56; i <= 63; i++) {
                String email = new ReadTestData().readDataByTagName("","email",Integer.toString(i));
                String school_name = new ReadTestData().readDataByTagName("","school_name",Integer.toString(i));
                String classroom = new ReadTestData().readDataByTagName("","classroom",Integer.toString(i));
                String grade = new ReadTestData().readDataByTagName("","grade",Integer.toString(i));
                String subject = new ReadTestData().readDataByTagName("","subject",Integer.toString(i));
                String firstname = new ReadTestData().readDataByTagName("","firstname",Integer.toString(i));
                String lastname = new ReadTestData().readDataByTagName("","lastname",Integer.toString(i));

                String subjectArea = "";

                if(subject.equals("MATH")) {
                    subject = "Mathematics";
                    subjectArea ="Math - Common Core";
                }
                else if(subject.equals("ELA")) {
                    subjectArea = "ELA - Common Core";
                }
                else if(subject.equals("SCIENCE")) {
                    subject = "Science";
                    subjectArea = "Science - NGSS";
                }
                else if(subject.equals("ESL")) {
                    subject = "Other Subjects";
                    subjectArea = "ESL";
                }

                driver.get("http://10.0.0.28/#register/close/teacher");//land on home page
          /*  new TextSend().textsendbyid("teachersugu@snapwiz.com","login-email");
            new TextSend().textsendbyid("snapwiz","login-password");
            driver.findElement(By.cssSelector("input[type='submit']")).click();*/
               // driver.findElement(By.linkText("Teacher")).click();//click on teacher
                driver.findElement(By.id("first-name")).clear();//clear teacher 1st name
                driver.findElement(By.id("first-name")).sendKeys(firstname);//give teacher first name
                driver.findElement(By.id("last-name")).clear();//clear teacher last name
                driver.findElement(By.id("last-name")).sendKeys(lastname);//give teacher first name

                driver.findElement(By.id("user-email")).clear();
                driver.findElement(By.id("user-email")).sendKeys(email);//give teacher email

                driver.findElement(By.id("user-password")).clear();
                driver.findElement(By.id("user-password")).sendKeys("snapwiz");//give teacher password
                new Click().clickByclassname("as-terms-condition-checkbox");
                driver.findElement(By.cssSelector("input[type='submit']")).click();//click o0n sign up button

                new TextSend().textsendbyclass(school_name+"S", "maininput"); //adding first three characters to the school name
                Thread.sleep(6000);
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//*[text()=' LAKEWOOD, OH , US']")));// LAKEWOOD, OH , US
                List<WebElement> suggestedNames = driver.findElements(By.xpath("//*[starts-with(@rel, 'inst_')]"));

                for (WebElement suggestedName : suggestedNames) {
                    System.out.println(suggestedName.getText().trim());
                    if (suggestedName.getText().trim().contains("LAKEWOOD")) {
                        suggestedName.click();
                        break;
                    }
                }

                driver.findElement(By.cssSelector("div[class='as-search-blue-btn as-search-next']")).click();

                new TextSend().textsendbyid(classroom, "class-name");//Enter Class name
                new DropDown().selectValue("class", "as-add-grade-dropDown", "Grade "+grade);//Select a grade
                new DropDown().selectValue("class", "as-add-subjectArea-dropDown", subject);//Select subject area
                new DropDown().selectValue("class", "as-add-subject-dropDown", subjectArea);//select subject
                Thread.sleep(2000);
                new Click().clickBycssselector("div[class='as-search-blue-btn as-add-save-btn']"); //Finish button of step 3

                new Click().clickByXpath("//div[@class='as-registration-profile-edit-footer']/div"); //Finish button of step 4
                String classCode = driver.findElement(By.className("as-manageClass-codeValue")).getText();
                List<String> data = new ArrayList<String>();
                data.add(email); data.add(firstname); data.add(lastname); data.add(school_name); data.add(classroom); data.add(classCode); data.add(grade); data.add(subject);
                writeToFile(data);
                //Creating new class again
                int j = i;
                String email_next = new ReadTestData().readDataByTagName("","email",Integer.toString(++j));

                while (email.equals(email_next)) {

                    //Read Data for next row
                    classroom = new ReadTestData().readDataByTagName("","classroom",Integer.toString(j));
                    grade = new ReadTestData().readDataByTagName("","grade",Integer.toString(j));
                    subject = new ReadTestData().readDataByTagName("","subject",Integer.toString(j));

                    if(subject.equals("MATH")) {
                        subject = "Mathematics";
                        subjectArea ="Math - Common Core";
                    }
                    else if(subject.equals("ELA")) {
                        subjectArea = "ELA - Common Core";
                    }
                    else if(subject.equals("SCIENCE")) {
                        subject = "Science";
                        subjectArea = "Science - NGSS";
                    }
                    else if(subject.equals("ESL")) {
                        subject = "Other Subjects";
                        subjectArea = "ESL";
                    }

                    new Click().clickBycssselector("div[class='as-manage-blue-btn as-add-newClass-btn']");//Clicking on new class button
                    new TextSend().textsendbycssSelector(classroom, "input[class='as-manage-class-name class-name-input']");//Enter Class name
                    new DropDown().selectValue("class", "as-add-grade-dropDown", "Grade "+grade);//Select a grade              //Check if next user is same as existing user
                    new DropDown().selectValue("class", "as-add-subjectArea-dropDown", subject);//Select subject area
                    new DropDown().selectValue("class", "as-add-subject-dropDown", subjectArea);//select subject

                    new Click().clickBycssselector("input[class='as-blue-btn as-add-save-btn']");//Save Class
                    Thread.sleep(2000);
                    classCode = driver.findElement(By.className("as-manageClass-codeValue")).getText();
                    data.add(email); data.add(firstname); data.add(lastname); data.add(school_name); data.add(classroom); data.add(classCode); data.add(grade); data.add(subject);
                    writeToFile(data);
                    email_next = new ReadTestData().readDataByTagName("","email",Integer.toString(++j));
                }
                i=--j;
                driver.manage().deleteAllCookies();
            }
        }
        catch (Exception e) {
            Assert.fail("",e);
        }
    }

    @Test
    public void studentRegister() {
        List<String> data = new ArrayList<String>();
        try {
            for(int i=1;i<=2;i++) {
                String classCode = new ReadTestData().readDataByTagName("", "classcode", Integer.toString(i));
                String email = new ReadTestData().readDataByTagName("", "email", Integer.toString(i));
                String firstName = new ReadTestData().readDataByTagName("", "firstname", Integer.toString(i));
                String lastName = new ReadTestData().readDataByTagName("", "lastname", Integer.toString(i));
                data.add(classCode); data.add(email); data.add(firstName); data.add(lastName);
                driver.get("http://idc-content2.snapwiz.net");//land on home page
                driver.findElement(By.linkText("Student")).click();
                driver.findElement(By.id("class-code")).sendKeys(classCode);
                driver.findElement(By.id("user-email")).sendKeys(email);
                driver.findElement(By.id("user-password")).sendKeys("snapwiz");
                driver.findElement(By.id("first-name")).sendKeys(firstName);
                driver.findElement(By.id("last-name")).sendKeys(lastName);
                new Click().clickByclassname("as-terms-condition-checkbox");//clicking on terms and conditions checkbox

                new Click().clickBycssselector("input[type='submit']");//clicking on submit button present on student sign up page
                driver.manage().deleteAllCookies();
            }
        }
        catch (Exception e) {

            writeToFile(data);
            driver.manage().deleteAllCookies();

        }
    }

    public void writeToFile(List<String> data) {
        try {
            File file = new File("edulastic_failed_users.csv");

            // if file doesnt exists, then create it
            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter fw = new FileWriter(file.getAbsoluteFile(),true);
            BufferedWriter bw = new BufferedWriter(fw);

            bw.write(data.get(0)); bw.write(",");
            bw.write(data.get(1));bw.write(",");
            bw.write(data.get(2));bw.write(",");
            bw.write(data.get(3));bw.write(",");
            bw.write(data.get(4));bw.write(",");
            bw.write(data.get(5));bw.write(",");
            bw.write(data.get(6));bw.write(",");
            bw.write(data.get(7));
            bw.newLine();
            bw.close();
            data.clear();
        }
        catch (Exception e) {

        }
    }
}