package com.snapwiz.selenium.tests.staf.datacreation.testcases;

import com.snapwiz.selenium.tests.staf.datacreation.Config;
import com.snapwiz.selenium.tests.staf.datacreation.Driver;
import com.snapwiz.selenium.tests.staf.datacreation.apphelper.DirectLogin;
import com.snapwiz.selenium.tests.staf.datacreation.uihelper.ComboBox;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by root on 14/1/15.
 */
public class UpdateContentIndexEdulastic extends Driver {

    private static Logger logger=Logger.getLogger("com.snapwiz.selenium.tests.staf.datacreation.apphelper.UpdateContentIndex");
    //Its update the course update Content index
    @Test
    public void updatecontentindex()
    {
        String currentCourse="";
        try
        {
            String msg1,msg2,msg3,msg4;
            List<String> courses = new ArrayList<>();
            courses.add("312"); courses.add("300");
            //LS
            new DirectLogin().CMSLogin();
            String title= Driver.driver.getTitle();
            if(title.equals("Course Content Details"))
            {
                logger.log(Level.INFO,"CMSHome  open");
                Driver.driver.get(Config.baseURL+"/secure/updateContentIndex");
                Thread.sleep(10000);



                for(String course : courses) {
                    currentCourse = course;
                   // Driver.driver.findElement(By.xpath("//*[@id='course-drop-down-selectbox']/div/a")).click();
                    new ComboBox().selectValue(1, course);

                    driver.findElement(By.id("content-type-radio-button")).click();
                    driver.findElement(By.id("remove-from-solr-index")).click();
                    WebElement myDynamicElement = (new WebDriverWait(driver,3600000))
                            .until(ExpectedConditions.presenceOfElementLocated(By.className("cms-notification-message-wrapper")));
                    Thread.sleep(3000);
                    msg1 = (myDynamicElement.getText());

                    Driver.driver.findElement(By.id("add-to-solr-index")).click();
                    myDynamicElement = (new WebDriverWait(driver,3600000))
                            .until(ExpectedConditions.presenceOfElementLocated(By.className("cms-notification-message-wrapper")));
                    Thread.sleep(3000);
                    System.out.println(myDynamicElement.getText());
                    msg2 = (myDynamicElement.getText());

                    driver.findElement(By.id("entity-radio-button")).click();
                    driver.findElement(By.id("remove-from-solr-index")).click();
                    myDynamicElement = (new WebDriverWait(Driver.driver,3600000))
                            .until(ExpectedConditions.presenceOfElementLocated(By.className("cms-notification-message-wrapper")));
                    Thread.sleep(3000);
                    msg3 = (myDynamicElement.getText());

                    Driver.driver.findElement(By.id("add-to-solr-index")).click();
                    myDynamicElement = (new WebDriverWait(driver,3600000))
                            .until(ExpectedConditions.presenceOfElementLocated(By.className("cms-notification-message-wrapper")));
                    Thread.sleep(3000);
                    msg4 = (myDynamicElement.getText());

                    writeToFile(msg1,msg2,msg3,msg4,course);

                }
            }
            else
            {
                logger.log(Level.INFO,"CMSHome  NOT open");
                Assert.fail("CMS home not opened");
            }
        }
        catch(Exception e)
        {
            writeToFile("Error in course","","","",currentCourse);
            System.out.println("Exception in App Helper UpdateContentIndex "+e);
        }


    }


    @Test
    public void updatecontentindex2()
    {
        try
        {
            String msg1,msg2,msg3,msg4;
            List<String> courses = new ArrayList<>();

            //LS

            new DirectLogin().CMSLogin();
            String title= Driver.driver.getTitle();
            if(title.equals("Course Content Details"))
            {
                logger.log(Level.INFO,"CMSHome  open");
                Driver.driver.get(Config.baseURL+"/secure/updateContentIndex");
                Thread.sleep(10000);
                driver.findElement(By.className("home-course-input-checkbox")).click();
                driver.findElements(By.linkText("ALL")).get(1).click();
                Thread.sleep(2000);
                driver.findElement(By.linkText("USER")).click();

                for(String course : courses) {

                    new ComboBox().selectValue(1, course);


                    Driver.driver.findElement(By.id("content-type-radio-button")).click();
                    /*Driver.driver.findElement(By.id("remove-from-solr-index")).click();
                    WebElement myDynamicElement = (new WebDriverWait(Driver.driver, 3600000))
                            .until(ExpectedConditions.presenceOfElementLocated(By.className("cms-notification-message-wrapper")));
                    Thread.sleep(500);
                    msg1 = (myDynamicElement.getText());*/
                    /*if (!myDynamicElement.getText().equals("Successfully removed from Solr index."))
                        Assert.fail("Solr index not removed successfully");*/
                    Driver.driver.findElement(By.id("add-to-solr-index")).click();
                    WebElement myDynamicElement = (new WebDriverWait(Driver.driver, 3600000))
                            .until(ExpectedConditions.presenceOfElementLocated(By.className("cms-notification-message-wrapper")));
                    Thread.sleep(500);
                    msg2 = (myDynamicElement.getText());
                    System.out.println("completed "+course);
                    //writeToFile(msg2,course);
                   /* if (!myDynamicElement.getText().equals("Successfully added to Solr index."))
                        Assert.fail("Solr index not added successfully");*/
                    //Driver.driver.findElement(By.id("entity-radio-button")).click();
                    /*Driver.driver.findElement(By.id("remove-from-solr-index")).click();
                    myDynamicElement = (new WebDriverWait(Driver.driver, 3600000))
                            .until(ExpectedConditions.presenceOfElementLocated(By.className("cms-notification-message-wrapper")));
                    Thread.sleep(500);
                    msg3 = (myDynamicElement.getText());*/
                    /*if (!myDynamicElement.getText().equals("Successfully removed from Solr index."))
                        Assert.fail("Solr index not removed successfully");*/
                  /*  Driver.driver.findElement(By.id("add-to-solr-index")).click();
                    myDynamicElement = (new WebDriverWait(Driver.driver, 3600000))
                            .until(ExpectedConditions.presenceOfElementLocated(By.className("cms-notification-message-wrapper")));
                    Thread.sleep(500);
                    msg4 = (myDynamicElement.getText());*/

                    /*if (!myDynamicElement.getText().equals("Successfully added to Solr index."))
                        Assert.fail("Solr index not added successfully");*/
                }
            }
            else
            {
                logger.log(Level.INFO,"CMSHome  NOT open");
                Assert.fail("CMS home not opened");
            }
        }
        catch(Exception e)
        {
            System.out.println("Exception in App Helper UpdateContentIndex "+e);
        }


    }

    @Test
    public void updatecontentindex4()
    {
        try
        {
            String msg1,msg2,msg3,msg4;
            List<String> courses = new ArrayList<>();

            //LS

            new DirectLogin().CMSLogin();
            String title= Driver.driver.getTitle();
            if(title.equals("Course Content Details"))
            {
                logger.log(Level.INFO,"CMSHome  open");
                Driver.driver.get(Config.baseURL+"/secure/updateContentIndex");
                Thread.sleep(10000);
                driver.findElement(By.className("home-course-input-checkbox")).click();
                driver.findElements(By.linkText("ALL")).get(1).click();
                Thread.sleep(2000);
                driver.findElement(By.linkText("USER")).click();

                for(String course : courses) {

                    new ComboBox().selectValue(1, course);


                    Driver.driver.findElement(By.id("content-type-radio-button")).click();
                    /*Driver.driver.findElement(By.id("remove-from-solr-index")).click();
                    WebElement myDynamicElement = (new WebDriverWait(Driver.driver, 3600000))
                            .until(ExpectedConditions.presenceOfElementLocated(By.className("cms-notification-message-wrapper")));
                    Thread.sleep(500);
                    msg1 = (myDynamicElement.getText());*/
                    /*if (!myDynamicElement.getText().equals("Successfully removed from Solr index."))
                        Assert.fail("Solr index not removed successfully");*/
                    Driver.driver.findElement(By.id("add-to-solr-index")).click();
                    WebElement myDynamicElement = (new WebDriverWait(Driver.driver, 3600000))
                            .until(ExpectedConditions.presenceOfElementLocated(By.className("cms-notification-message-wrapper")));
                    Thread.sleep(500);
                    msg2 = (myDynamicElement.getText());
                    System.out.println("completed "+course);
                    //writeToFile(msg2,course);
                   /* if (!myDynamicElement.getText().equals("Successfully added to Solr index."))
                        Assert.fail("Solr index not added successfully");*/
                    //Driver.driver.findElement(By.id("entity-radio-button")).click();
                    /*Driver.driver.findElement(By.id("remove-from-solr-index")).click();
                    myDynamicElement = (new WebDriverWait(Driver.driver, 3600000))
                            .until(ExpectedConditions.presenceOfElementLocated(By.className("cms-notification-message-wrapper")));
                    Thread.sleep(500);
                    msg3 = (myDynamicElement.getText());*/
                    /*if (!myDynamicElement.getText().equals("Successfully removed from Solr index."))
                        Assert.fail("Solr index not removed successfully");*/
                  /*  Driver.driver.findElement(By.id("add-to-solr-index")).click();
                    myDynamicElement = (new WebDriverWait(Driver.driver, 3600000))
                            .until(ExpectedConditions.presenceOfElementLocated(By.className("cms-notification-message-wrapper")));
                    Thread.sleep(500);
                    msg4 = (myDynamicElement.getText());*/

                    /*if (!myDynamicElement.getText().equals("Successfully added to Solr index."))
                        Assert.fail("Solr index not added successfully");*/
                }
            }
            else
            {
                logger.log(Level.INFO,"CMSHome  NOT open");
                Assert.fail("CMS home not opened");
            }
        }
        catch(Exception e)
        {
            System.out.println("Exception in App Helper UpdateContentIndex "+e);
        }


    }

    public void writeToFile(String msg1,String msg2,String msg3,String msg4,String course) {
        try {
            File file = new File("updatecontentindexLS.txt");

            // if file doesnt exists, then create it
            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter fw = new FileWriter(file.getAbsoluteFile(),true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write("---------------------------------------------------------------------------------------------"); bw.newLine();
            //bw.write("Course: "+course); bw.newLine();
            bw.write(msg1+" " + course);bw.newLine();
            bw.write(msg2+" " + course);bw.newLine();
            bw.write(msg3+" " + course);bw.newLine();
            bw.write(msg4+" " + course);bw.newLine();
            // bw.write("Entities "+msg3); bw.newLine();

            bw.newLine();
            bw.close();

        }
        catch (Exception e) {

        }
    }
}
