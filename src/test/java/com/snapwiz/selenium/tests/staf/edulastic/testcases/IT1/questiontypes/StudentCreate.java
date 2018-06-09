package com.snapwiz.selenium.tests.staf.edulastic.testcases.IT1.questiontypes;

import com.snapwiz.selenium.tests.staf.edulastic.Driver;
import com.snapwiz.selenium.tests.staf.edulastic.ReadTestData;
import com.snapwiz.selenium.tests.staf.edulastic.apphelpers.Navigator;
import com.snapwiz.selenium.tests.staf.edulastic.uihelper.Click;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

/*
 * Created by sumit on 24/11/14.
 */
public class StudentCreate extends Driver{

    @Test
    public void studentCreate()
    {
        try
        {
            driver.get("http://app.edulastic.in");
            for(int i = 123; i < 163; i++) {

                String firstname = ReadTestData.readDataByTagName("", "firstname", Integer.toString(i));
                String lastname = ReadTestData.readDataByTagName("", "lastname", Integer.toString(i));
                String email = ReadTestData.readDataByTagName("", "email", Integer.toString(i));
                String classcode = ReadTestData.readDataByTagName("", "classcode", Integer.toString(i));
                driver.findElement(By.linkText("Student")).click();//click on student
                driver.findElement(By.id("class-code")).clear();
                driver.findElement(By.id("class-code")).sendKeys(classcode);
                driver.findElement(By.id("first-name")).clear();
                driver.findElement(By.id("first-name")).sendKeys(firstname);
                driver.findElement(By.id("last-name")).clear();
                driver.findElement(By.id("last-name")).sendKeys(lastname);
                driver.findElement(By.id("user-email")).clear();
                driver.findElement(By.id("user-email")).sendKeys(email);
                driver.findElement(By.id("user-password")).clear();
                driver.findElement(By.id("user-password")).sendKeys("snapwiz");
                new Click().clickByclassname("as-terms-condition-checkbox");//clicking on terms and conditions checkbox
                new Click().clickBycssselector("input[type='submit']");//clicking on submit button present on student sign up page
                Thread.sleep(1000);
                String url = driver.getCurrentUrl();
                System.out.println("url: "+url);
                if (!url.contains("dashboard"))
                    Assert.fail("Student did not land to Dashboard after getting registered in the row number: "+ i+"with student first name: "+firstname);
                new Click().clickByclassname("swhelp-button-next");
                Thread.sleep(6000);
                new Navigator().logout();
            }
        }
        catch (Exception e)
        {
            Assert.fail("Exception in testcase studentCreate in class StudentCreate.", e);
        }
    }


}
