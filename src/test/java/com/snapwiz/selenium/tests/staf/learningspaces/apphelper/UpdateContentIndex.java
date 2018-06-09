package com.snapwiz.selenium.tests.staf.learningspaces.apphelper;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.snapwiz.selenium.tests.staf.framework.utility.ReportUtil;
import com.snapwiz.selenium.tests.staf.framework.utility.WebDriverUtil;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.UIElement;
import org.apache.commons.lang3.time.StopWatch;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.snapwiz.selenium.tests.staf.learningspaces.Config;
import com.snapwiz.selenium.tests.staf.framework.controller.Driver;

public class UpdateContentIndex extends Driver{
    private static Logger logger=Logger.getLogger("com.snapwiz.selenium.tests.staf.learningspaces.apphelper.UpdateContentIndex");

  public UpdateContentIndex(){
         Config.readconfiguration();
    }
    //Its update the course update Content index
    public void updatecontentindex(String dataIndex)
    {
        WebDriver driver=Driver.getWebDriver();
        try
        {
            String course =Config.course;
            new DirectLogin().CMSLogin();
            String title=driver.getTitle();
            if(title.equals("Course Content Details"))
            {
                logger.log(Level.INFO,"CMSHome  open");
                driver.get(Config.baseURL+"/secure/updateContentIndex");
                driver.findElement(By.xpath("//*[@id='course-drop-down-selectbox']/div/a")).click();

                WebElement element=driver.findElement(By.cssSelector("a[rel='253']"));
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",element);
                WebDriverUtil.clickOnElementUsingJavascript(element);

                Thread.sleep(3000);
                driver.findElement(By.id("content-type-radio-button")).click();
                driver.findElement(By.id("remove-from-solr-index")).click();
                WebElement myDynamicElement = (new WebDriverWait(driver,3600000))
                        .until(ExpectedConditions.presenceOfElementLocated(By.className("cms-notification-message-wrapper")));
                Thread.sleep(3000);
                System.out.println(myDynamicElement.getText());
                if(!myDynamicElement.getText().equals("Successfully removed from Solr index.")) Assert.fail("Solr index not removed successfully");
                driver.findElement(By.id("add-to-solr-index")).click();
                myDynamicElement = (new WebDriverWait(driver,3600000))
                        .until(ExpectedConditions.presenceOfElementLocated(By.className("cms-notification-message-wrapper")));
                Thread.sleep(3000);
                System.out.println(myDynamicElement.getText());
                if(!myDynamicElement.getText().equals("Successfully added to Solr index.")) Assert.fail("Solr index not added successfully");
                driver.findElement(By.id("entity-radio-button")).click();
                driver.findElement(By.id("remove-from-solr-index")).click();
                myDynamicElement = (new WebDriverWait(driver,3600000))
                        .until(ExpectedConditions.presenceOfElementLocated(By.className("cms-notification-message-wrapper")));
                Thread.sleep(3000);
                System.out.println(myDynamicElement.getText());
                if(!myDynamicElement.getText().equals("Successfully removed from Solr index.")) Assert.fail("Solr index not removed successfully");
                driver.findElement(By.id("add-to-solr-index")).click();
                myDynamicElement = (new WebDriverWait(driver,3600000))
                        .until(ExpectedConditions.presenceOfElementLocated(By.className("cms-notification-message-wrapper")));
                Thread.sleep(3000);
                System.out.println(myDynamicElement.getText());
                if(!myDynamicElement.getText().equals("Successfully added to Solr index.")) Assert.fail("Solr index not added successfully");
            }
            else
            {
                logger.log(Level.INFO,"CMSHome  NOT open");
                Assert.fail("CMS home not opened");
            }
        }
        catch(Exception e)
        {
            Assert.fail("Exception in App Helper UpdateContentIndex",e);
        }
    }



    public void updateContentIndexForAllCourses(String dataIndex)
    {
        WebDriver driver=Driver.getWebDriver();
        try
        {
            String course =Config.course;
            new DirectLogin().CMSLogin();
            String title=driver.getTitle();
            if(title.equals("Course Content Details"))
            {
                logger.log(Level.INFO,"CMSHome  open");
                driver.get(Config.baseURL+"/secure/updateContentIndex");
                List<WebElement> courseList=driver.findElements(By.cssSelector("div[class='overview'] >li>a"));
                StopWatch stopWatch = new StopWatch();
                StopWatch stopWatchForAll = new StopWatch();
                stopWatchForAll.start();
                for(int i=0;i<courseList.size();i++)
                {
                    stopWatch.start();

                    Thread.sleep(1000);
                    if(courseList.get(i).getText()!="" ) {
                        System.out.println(courseList.get(i).getText());
                        WebElement element = driver.findElement(By.cssSelector("a[title='" + courseList.get(i).getAttribute("title") + "']"));
                        System.out.println("Title "+i+"  "+element.getAttribute("title"));
                        driver.findElement(By.xpath("//*[@id='course-drop-down-selectbox']/div/a")).click();
                        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",element);
                        element.click();
                        WebElement myDynamicElement = (new WebDriverWait(driver,3600000))
                                .until(ExpectedConditions.presenceOfElementLocated(By.id("content-type-radio-button")));
                        Thread.sleep(1000);

                        driver.findElement(By.id("content-type-radio-button")).click();
                        myDynamicElement = (new WebDriverWait(driver,3600000))
                                .until(ExpectedConditions.presenceOfElementLocated(By.id("remove-from-solr-index")));
                        Thread.sleep(1000);

                        driver.findElement(By.id("remove-from-solr-index")).click();
                        myDynamicElement = (new WebDriverWait(driver,3600000))
                                .until(ExpectedConditions.presenceOfElementLocated(By.className("cms-notification-message-wrapper")));
                        Thread.sleep(1000);
                        System.out.println(i+":"+myDynamicElement.getText());

                        //if(!myDynamicElement.getText().equals("Successfully removed from Solr index.")) Assert.fail("Solr index not removed successfully");
                        driver.findElement(By.id("add-to-solr-index")).click();
                        myDynamicElement = (new WebDriverWait(driver,3600000))
                                .until(ExpectedConditions.presenceOfElementLocated(By.className("cms-notification-message-wrapper")));
                        Thread.sleep(1000);
                        System.out.println(i+":"+myDynamicElement.getText());
                        if(!(myDynamicElement.getText().equals("Successfully added to Solr index.") || myDynamicElement.getText().equals("Publish unsuccessful.")))
                            System.out.println(myDynamicElement.getText());

                        driver.findElement(By.id("entity-radio-button")).click();
                        (new WebDriverWait(driver,3600000))
                                .until(ExpectedConditions.presenceOfElementLocated(By.id("remove-from-solr-index")));
                        Thread.sleep(1000);

                        driver.findElement(By.id("remove-from-solr-index")).click();
                        myDynamicElement = (new WebDriverWait(driver,3600000))
                                .until(ExpectedConditions.presenceOfElementLocated(By.className("cms-notification-message-wrapper")));
                        Thread.sleep(1000);
                        if(!myDynamicElement.getText().equals("Successfully removed from Solr index.")) Assert.fail("Solr index not removed successfully");
                        driver.findElement(By.id("add-to-solr-index")).click();
                        myDynamicElement = (new WebDriverWait(driver,3600000))
                                .until(ExpectedConditions.presenceOfElementLocated(By.className("cms-notification-message-wrapper")));
                        Thread.sleep(1000);
                        if(myDynamicElement.getText().equals("Successfully added to Solr index.")) {
                            System.out.println(myDynamicElement.getText());
                        }

                        System.out.println("Time:"+stopWatch);
                        stopWatch.stop();
                        stopWatch.reset();
                        System.out.println("Time taken for all course "+stopWatchForAll);

                    }
                }
                Thread.sleep(5000);
                System.out.println("Time taken for all course "+stopWatchForAll);

            }
            else
            {
                logger.log(Level.INFO,"CMSHome  NOT open");
                Assert.fail("CMS home not opened");
            }
        }
        catch(Exception e)
        {
            Assert.fail("Exception in App Helper UpdateContentIndex",e);
        }
    }


    public void updateContentIndexForCoursesBothContentAndEntityType(String dataIndex,String arr[])
    {
        WebDriver driver=Driver.getWebDriver();
        try
        {
            String url = System.getProperty("URL");
            String contentType = System.getProperty("ContentType");

            WebElement myDynamicElement ;
            CMSLogin();
            String title=driver.getTitle();
            if(title.equals("Course Content Details"))
            {
                logger.log(Level.INFO,"CMSHome  open");
                driver.get(url + "/secure/updateContentIndex");
                Thread.sleep(5000);
                List<WebElement> courseList=driver.findElements(By.cssSelector("div[class='overview'] >li>a"));
                for(int j=0;j<arr.length;j++)
                {
                    int count=0;
                    for(int i=0;i<courseList.size();i++)
                    {
                        Thread.sleep(1000);
                        if(courseList.get(i).getText()!="" ) {
                            if(courseList.get(i).getAttribute("title").contains(arr[j]))
                            {
                                driver.findElement(By.xpath("//*[@id='course-drop-down-selectbox']/div/a")).click(); //click on the course dropdown
                                WebElement element = driver.findElement(By.cssSelector("a[title='" + courseList.get(i).getAttribute("title") + "']"));
                                System.out.println("Title " + i + "  " + element.getAttribute("title"));
                                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
                                WebDriverUtil.clickOnElementUsingJavascript(element); //select the course
                                WebDriverUtil.clickOnElementUsingJavascript(driver.findElement(By.id("content-type-radio-button"))); //click on the content-type-radio-button
                                (new WebDriverWait(driver,360))
                                        .until(ExpectedConditions.presenceOfElementLocated(By.id("remove-from-solr-index")));
                                Thread.sleep(1000);
                                WebDriverUtil.clickOnElementUsingJavascript(driver.findElement(By.xpath("//div[@id='content-drop-down-wrapper']/div/a"))); //click on the content type dropdown
                                List<WebElement> allContentType=driver.findElements(By.xpath("//div[@id='content-drop-down-wrapper']/div/ul/li/a"));
                                for(WebElement contentTypes:allContentType){
                                    if(contentTypes.isDisplayed()){
                                        if(contentTypes.getAttribute("rel").equalsIgnoreCase(contentType)) { //here you can pass content type
                                            WebDriverUtil.clickOnElementUsingJavascript(contentTypes);
                                            break;
                                        }
                                    }
                                }

                                /*//verify whether resource is selcted as default or not
                                if(!driver.findElement(By.xpath("(/*//*[@id='content-drop-down-wrapper']/div/a)[2]")).getAttribute("selectedid").equals("RESOURCE"))
                                {
                                    //click on content type
                                    driver.findElement(By.xpath("/*//*[@id='content-drop-down-wrapper']/div/a")).click();
                                    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",driver.findElement(By.cssSelector("a[title='RESOURCE']")));
                                    driver.findElement(By.cssSelector("a[title='RESOURCE']")).click();

                                }
*/
                                        (new WebDriverWait(driver, 360000))
                                                .until(ExpectedConditions.presenceOfElementLocated(By.id("remove-from-solr-index")));
                                Thread.sleep(1000);
                                driver.findElement(By.id("remove-from-solr-index")).click(); //click on the remove from index
                                myDynamicElement = (new WebDriverWait(driver,3600000))
                                        .until(ExpectedConditions.presenceOfElementLocated(By.className("cms-notification-message-wrapper")));
                                Thread.sleep(1000);
                                System.out.println(i+":"+myDynamicElement.getText());
                                if(driver.findElements(By.id("close-cms-notification-dialog")).size()>0) {
                                    driver.findElement(By.id("close-cms-notification-dialog")).click();
                                }



                                WebDriverUtil.clickOnElementUsingJavascript(driver.findElement(By.id("add-to-solr-index"))); //click on the add to index
                                myDynamicElement = (new WebDriverWait(driver,3600000))
                                        .until(ExpectedConditions.presenceOfElementLocated(By.className("cms-notification-message-wrapper")));
                                Thread.sleep(1000);
                                System.out.println(i+":"+myDynamicElement.getText());

                                if(!(myDynamicElement.getText().equals("Successfully added to Solr index.") || myDynamicElement.getText().equals("Publish unsuccessful."))) {
                                    System.out.println(myDynamicElement.getText());
                                    ReportUtil.log("Update solar index", courseList.get(i).getAttribute("title") + " solar index not updated", "fail");
                                }
                                else {
                                    ReportUtil.log("Update solar index", courseList.get(i).getAttribute("title") + " solar index updated successfully updated", "pass");
                                }

                                driver.findElement(By.id("entity-radio-button")).click(); //click on the entity radio button
                                driver.findElement(By.id("remove-from-solr-index")).click();
                                myDynamicElement = (new WebDriverWait(driver,3600000))
                                        .until(ExpectedConditions.presenceOfElementLocated(By.className("cms-notification-message-wrapper")));
                                Thread.sleep(3000);
                                System.out.println(myDynamicElement.getText());
                                if(!myDynamicElement.getText().equals("Successfully removed from Solr index.")) Assert.fail("Solr index not removed successfully");
                                driver.findElement(By.id("add-to-solr-index")).click();
                                myDynamicElement = (new WebDriverWait(driver,3600000))
                                        .until(ExpectedConditions.presenceOfElementLocated(By.className("cms-notification-message-wrapper")));
                                Thread.sleep(3000);
                                System.out.println(myDynamicElement.getText());
                                if(!myDynamicElement.getText().equals("Successfully added to Solr index.")) Assert.fail("Solr index not added successfully");
                                count++;
                                break;
                            }
                        }
                    }

                    if (count==0)
                    {
                        ReportUtil.log("Update solar index", arr[j] + " course ID not present", "fail");
                    }

                }
                Thread.sleep(5000);
            } else
            {
                logger.log(Level.INFO,"CMSHome  NOT open");
                Assert.fail("CMS home not opened");
            }
        }
        catch(Exception e)
        {
            Assert.fail("Exception in App Helper UpdateContentIndex",e);
        }
    }

    public void updateContentIndexForCoursesForContentType(String dataIndex,String arr[])
    {
        WebDriver driver=Driver.getWebDriver();
        try
        {
            String url = System.getProperty("URL");
            String contentType = System.getProperty("ContentType");

            WebElement myDynamicElement ;
            CMSLogin();
            String title=driver.getTitle();
            if(title.equals("Course Content Details"))
            {
                logger.log(Level.INFO,"CMSHome  open");
                driver.get(url + "/secure/updateContentIndex");
                Thread.sleep(5000);
                List<WebElement> courseList=driver.findElements(By.cssSelector("div[class='overview'] >li>a"));
                for(int j=0;j<arr.length;j++)
                {
                    int count=0;
                    for(int i=0;i<courseList.size();i++)
                    {
                        Thread.sleep(1000);
                        if(courseList.get(i).getText()!="" ) {
                            if(courseList.get(i).getAttribute("title").contains(arr[j]))
                            {
                                driver.findElement(By.xpath("//*[@id='course-drop-down-selectbox']/div/a")).click(); //click on the course dropdown
                                WebElement element = driver.findElement(By.cssSelector("a[title='" + courseList.get(i).getAttribute("title") + "']"));
                                System.out.println("Title " + i + "  " + element.getAttribute("title"));
                                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
                                WebDriverUtil.clickOnElementUsingJavascript(element); //select the course
                                WebDriverUtil.clickOnElementUsingJavascript(driver.findElement(By.id("content-type-radio-button"))); //click on the content-type-radio-button
                                (new WebDriverWait(driver,360000))
                                        .until(ExpectedConditions.presenceOfElementLocated(By.id("remove-from-solr-index")));
                                Thread.sleep(1000);
                                WebDriverUtil.clickOnElementUsingJavascript(driver.findElement(By.xpath("//div[@id='content-drop-down-wrapper']/div/a"))); //click on the content type dropdown
                                List<WebElement> allContentType=driver.findElements(By.xpath("//div[@id='content-drop-down-wrapper']/div/ul/li/a"));
                                for(WebElement contentTypes:allContentType){
                                    if(contentTypes.isDisplayed()){
                                        if(contentTypes.getAttribute("rel").equalsIgnoreCase(contentType)) { //here you can pass content type
                                            WebDriverUtil.clickOnElementUsingJavascript(contentTypes);
                                            break;
                                        }
                                    }
                                }


                                (new WebDriverWait(driver, 3600000))
                                        .until(ExpectedConditions.presenceOfElementLocated(By.id("remove-from-solr-index")));
                                Thread.sleep(1000);
                                driver.findElement(By.id("remove-from-solr-index")).click(); //click on the remove from index
                                myDynamicElement = (new WebDriverWait(driver,3600000))
                                        .until(ExpectedConditions.presenceOfElementLocated(By.className("cms-notification-message-wrapper")));
                                Thread.sleep(1000);
                                System.out.println(i+":"+myDynamicElement.getText());
                                if(driver.findElements(By.id("close-cms-notification-dialog")).size()>0) {
                                    driver.findElement(By.id("close-cms-notification-dialog")).click();
                                }


                                WebDriverUtil.clickOnElementUsingJavascript(driver.findElement(By.id("add-to-solr-index"))); //click on the add to index
                                myDynamicElement = (new WebDriverWait(driver,3600000))
                                        .until(ExpectedConditions.presenceOfElementLocated(By.className("cms-notification-message-wrapper")));
                                Thread.sleep(1000);
                                System.out.println(i+":"+myDynamicElement.getText());

                                if(!(myDynamicElement.getText().equals("Successfully added to Solr index.") || myDynamicElement.getText().equals("Publish unsuccessful."))) {
                                    System.out.println(myDynamicElement.getText());
                                    ReportUtil.log("Update solar index", courseList.get(i).getAttribute("title") + " solar index not updated", "fail");
                                }
                                else {
                                    ReportUtil.log("Update solar index", courseList.get(i).getAttribute("title") + " solar index updated successfully updated", "pass");
                                }
                                count++;
                                break;
                            }
                        }
                    }

                    if (count==0)
                    {
                        ReportUtil.log("Update solar index", arr[j] + " course ID not present", "fail");
                    }
                }
                Thread.sleep(5000);
            } else
            {
                logger.log(Level.INFO,"CMSHome  NOT open");
                Assert.fail("CMS home not opened");
            }
        }
        catch(Exception e)
        {
            Assert.fail("Exception in App Helper UpdateContentIndex",e);
        }
    }

    public void updateContentIndexForCoursesForAllContentAndEntityType(String dataIndex,String arr[])
    {
        WebDriver driver=Driver.getWebDriver();
        try
        {
            String url = System.getProperty("URL");
            WebElement myDynamicElement ;
            CMSLogin();
            String title=driver.getTitle();
            if(title.equals("Course Content Details"))
            {
                logger.log(Level.INFO,"CMSHome  open");
                driver.get(url + "/secure/updateContentIndex");
                Thread.sleep(5000);
                List<WebElement> courseList=driver.findElements(By.cssSelector("div[class='overview'] >li>a"));
                for(int j=0;j<arr.length;j++)
                {
                    int count=0;
                    for(int i=0;i<courseList.size();i++)
                    {
                        Thread.sleep(1000);
                        if(courseList.get(i).getText()!="" ) {
                            if(courseList.get(i).getAttribute("title").contains(arr[j]))
                            {
                                driver.findElement(By.xpath("//*[@id='course-drop-down-selectbox']/div/a")).click(); //click on the course dropdown
                                WebElement element = driver.findElement(By.cssSelector("a[title='" + courseList.get(i).getAttribute("title") + "']"));
                                System.out.println("Title " + i + "  " + element.getAttribute("title"));
                                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
                                WebDriverUtil.clickOnElementUsingJavascript(element); //select the course
                                WebDriverUtil.clickOnElementUsingJavascript(driver.findElement(By.id("content-type-radio-button"))); //click on the content-type-radio-button
                                (new WebDriverWait(driver,360000))
                                        .until(ExpectedConditions.presenceOfElementLocated(By.id("remove-from-solr-index")));
                                Thread.sleep(1000);
                                driver.findElement(By.id("remove-from-solr-index")).click(); //click on the remove from index
                                myDynamicElement = (new WebDriverWait(driver,3600000))
                                        .until(ExpectedConditions.presenceOfElementLocated(By.className("cms-notification-message-wrapper")));
                                Thread.sleep(1000);
                                System.out.println(i+":"+myDynamicElement.getText());
                                if(driver.findElements(By.id("close-cms-notification-dialog")).size()>0) {
                                    driver.findElement(By.id("close-cms-notification-dialog")).click();
                                }


                                WebDriverUtil.clickOnElementUsingJavascript(driver.findElement(By.id("add-to-solr-index"))); //click on the add to index
                                myDynamicElement = (new WebDriverWait(driver,3600000))
                                        .until(ExpectedConditions.presenceOfElementLocated(By.className("cms-notification-message-wrapper")));
                                Thread.sleep(1000);
                                System.out.println(i+":"+myDynamicElement.getText());

                                if(!(myDynamicElement.getText().equals("Successfully added to Solr index.") || myDynamicElement.getText().equals("Publish unsuccessful."))) {
                                    System.out.println(myDynamicElement.getText());
                                    ReportUtil.log("Update solar index", courseList.get(i).getAttribute("title") + " solar index not updated", "fail");
                                }
                                else {
                                    ReportUtil.log("Update solar index", courseList.get(i).getAttribute("title") + " solar index updated successfully updated", "pass");
                                }

                                driver.findElement(By.id("entity-radio-button")).click(); //click on the entity radio button
                                driver.findElement(By.id("remove-from-solr-index")).click();//click on the remove from index
                                myDynamicElement = (new WebDriverWait(driver,3600000))
                                        .until(ExpectedConditions.presenceOfElementLocated(By.className("cms-notification-message-wrapper")));
                                Thread.sleep(3000);
                                System.out.println(myDynamicElement.getText());
                                if(!myDynamicElement.getText().equals("Successfully removed from Solr index.")) Assert.fail("Solr index not removed successfully");

                                driver.findElement(By.id("add-to-solr-index")).click();//click on the add to solr index
                                myDynamicElement = (new WebDriverWait(driver,3600000))
                                        .until(ExpectedConditions.presenceOfElementLocated(By.className("cms-notification-message-wrapper")));
                                Thread.sleep(3000);
                                System.out.println(myDynamicElement.getText());
                                if(!myDynamicElement.getText().equals("Successfully added to Solr index.")) Assert.fail("Solr index not added successfully");

                                count++;
                                break;

                            }
                        }
                    }

                    if (count==0)
                    {
                        ReportUtil.log("Update solar index", arr[j] + " course ID not present", "fail");
                    }
                }
                Thread.sleep(5000);
            } else
            {
                logger.log(Level.INFO,"CMSHome  NOT open");
                Assert.fail("CMS home not opened");
            }
        }
        catch(Exception e)
        {
            Assert.fail("Exception in App Helper UpdateContentIndex",e);
        }
    }

    public void updateContentIndexForContentType(String contentType,String Course )
    {
        WebDriver driver=Driver.getWebDriver();
        try {
            new DirectLogin().CMSLogin();
            String title=driver.getTitle();
            if(title.equals("Course Content Details")) {
                logger.log(Level.INFO, "CMSHome  open");
                driver.get(Config.baseURL + "/secure/updateContentIndex");
                driver.findElement(By.xpath("//*[@id='course-drop-down-selectbox']/div/a")).click(); //click on the course toggle
                WebElement element = driver.findElement(By.cssSelector("a[title='" + Course + "']"));
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
                driver.findElement(By.cssSelector("a[title='" + Course + "']")).click();


                new UIElement().waitAndFindElement(By.id("content-type-radio-button"));
                driver.findElement(By.id("content-type-radio-button")).click(); //click on the contentType Radio button
                Thread.sleep(3000);
                ((JavascriptExecutor)driver).executeScript("arguments[0].click();", driver.findElement(By.xpath("//div[@id='content-drop-down-wrapper']/div/a[1]")));
                Thread.sleep(2000);
                driver.findElement(By.xpath("//a[@rel='" + contentType + "']")).click(); //click on the particular contentType
                Thread.sleep(3000);
                driver.findElement(By.id("remove-from-solr-index")).click();
                WebElement myDynamicElement = (new WebDriverWait(driver, 3600000))
                        .until(ExpectedConditions.presenceOfElementLocated(By.className("cms-notification-message-wrapper")));
                Thread.sleep(3000);
                if (!myDynamicElement.getText().equals("Successfully removed from Solr index.")) {
                    Assert.fail("Solr index not removed successfully");
                }
                driver.findElement(By.id("add-to-solr-index")).click(); //click on the add-to-solr-index

                myDynamicElement = (new WebDriverWait(driver, 3600000))
                        .until(ExpectedConditions.presenceOfElementLocated(By.className("cms-notification-message-wrapper")));
                Thread.sleep(3000);
                if (!myDynamicElement.getText().equals("Successfully added to Solr index."))
                    Assert.fail("Solr index not added successfully");

            }
            else {
                logger.log(Level.INFO, "CMSHome  NOT open");
                Assert.fail("CMS home not opened");
            }

        }
        catch(Exception e)
        {
            Assert.fail("Exception in App Helper UpdateContentIndex",e);
        }
    }


    public void CMSLogin()
    {
        WebDriver driver=Driver.getWebDriver();
        try
        {
            String username = System.getProperty("Username");
            String password = System.getProperty("Password");
            String url = System.getProperty("URL");
            driver.get(url);
            new UIElement().waitAndFindElement(By.id("username"));
            driver.findElement(By.id("username")).sendKeys(username);
            driver.findElement(By.id("password")).sendKeys(password);
            driver.findElement(By.id("loginSubmitBtn")).click();
        }
        catch(Exception e)
        {
            Assert.fail("Exception in CMSLogin in class DirectLogin",e);
        }
    }
}
	
