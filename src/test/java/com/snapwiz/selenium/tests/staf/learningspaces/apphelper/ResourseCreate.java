package com.snapwiz.selenium.tests.staf.learningspaces.apphelper;

import java.util.List;

import com.snapwiz.selenium.tests.staf.framework.utility.WebDriverUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.snapwiz.selenium.tests.staf.learningspaces.Config;
import com.snapwiz.selenium.tests.staf.framework.controller.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.Click;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.ComboBox;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.ShareWith;

public class ResourseCreate extends Driver{

    public  ResourseCreate(){
        Config.readconfiguration();
    }
    //create resource at chapter level
    public void resourseCreate(int dataIndex,int chapternumber)
    {
        try
        {
            WebDriver driver=Driver.getWebDriver();
            String resoursename = ReadTestData.readDataByTagName("", "resoursename", Integer.toString(dataIndex));
            String type = ReadTestData.readDataByTagName("", "type", Integer.toString(dataIndex));
            String description = ReadTestData.readDataByTagName("", "description", Integer.toString(dataIndex));
            String instructoronlyflag = ReadTestData.readDataByTagName("", "instructoronlyflag", Integer.toString(dataIndex));
            String filename = ReadTestData.readDataByTagName("", "filename", Integer.toString(dataIndex));
            driver.get(Config.baseURL);
            new DirectLogin().CMSLogin();
            String title=driver.getTitle();
            if(title.equals("Course Content Details"))
            {
                driver.findElement(By.cssSelector("img[alt=\""+Config.course+"\"]")).click();
                List<WebElement> allchapter=driver.findElements(By.cssSelector("div[class='course-chapter-label node']"));
                int index=0;
                for(WebElement chapter:allchapter)
                {
                    if(index==chapternumber)
                    {
                        chapter.click();
                        break;
                    }
                    index++;
                }
                // driver.findElement(By.cssSelector("div.course-chapter-label.node")).click();
                driver.findElement(By.cssSelector("div.associate-resource")).click();
                WebElement resname = driver.findElement(By.className("associate-resource-field-text"));
                Actions action = new Actions(driver);
                action.doubleClick(resname);
                action.perform();
                driver.findElement(By.id("resource-name-field")).clear();
                driver.findElement(By.id("resource-name-field")).sendKeys(resoursename);

                new ComboBox().selectValue(3, type);

                Thread.sleep(5000);
                WebElement desc = driver.findElement(By.id("associate-resource-details-field-text-content"));
                action.moveToElement(desc).doubleClick().build().perform();

                driver.findElement(By.id("resource-description")).clear();
                driver.findElement(By.id("resource-description")).sendKeys(description);
                new ComboBox().selectValue(4, instructoronlyflag);

                ((JavascriptExecutor) driver).executeScript("arguments[0].click();",driver.findElement(By.id("browseResource")));
                new KeysSend().sendKeyBoardKeys("$"+Config.fileUploadPath+filename+"^");
                Thread.sleep(2000);
                driver.findElement(By.id("start_queue")).click();
                Thread.sleep(30000);
                driver.findElement(By.id("associateResourceToNode")).click();
                Thread.sleep(30000);
            }
            else
            {
                Assert.fail("Course Content page not opened");
            }
        }
        catch(Exception e)
        {
            Assert.fail("Exception in App helper ResourseCreate",e);
        }
    }

    //create resources as topic level
    public void creatresourcesattopiclevel(int dataIndex,int topic,int chapter)
    {
        try
        {
            WebDriver driver=Driver.getWebDriver();
            String course = ReadTestData.readDataByTagName("", "course", Integer.toString(dataIndex));
            String resoursename = ReadTestData.readDataByTagName("", "resoursename", Integer.toString(dataIndex));
            String type = ReadTestData.readDataByTagName("", "type", Integer.toString(dataIndex));
            String description = ReadTestData.readDataByTagName("", "description", Integer.toString(dataIndex));
            String instructoronlyflag = ReadTestData.readDataByTagName("", "instructoronlyflag", Integer.toString(dataIndex));
            String filename = ReadTestData.readDataByTagName("", "filename", Integer.toString(dataIndex));
            new DirectLogin().CMSLogin();
            String title=driver.getTitle();
            if(title.equals("Course Content Details"))
            {
                driver.findElement(By.cssSelector("img[alt=\""+Config.course+"\"]")).click();
                List<WebElement> allchapter=(List<WebElement>) ((JavascriptExecutor)driver).executeScript("return arguments[0]",driver.findElements(By.cssSelector("div[class='expand-chapter-tree expand']")));
                int index1=0;
                for(WebElement elements:allchapter)
                {

                    if(index1==chapter)
                    {
                        elements.click();
                        break;
                    }
                    index1++;
                }

                List<WebElement> alltopic=(List<WebElement>) ((JavascriptExecutor)driver).executeScript("return arguments[0]",driver.findElements(By.cssSelector("div[class='course-topic-label node']")));
                int index=0;
                for(WebElement element:alltopic)
                {

                    if(index==topic)
                    {
                        element.click();
                        break;
                    }
                    index++;
                }
                driver.findElement(By.cssSelector("div.associate-resource")).click();
                WebElement resname = driver.findElement(By.className("associate-resource-field-text"));
                Actions action = new Actions(driver);
                action.doubleClick(resname);
                action.perform();
                driver.findElement(By.id("resource-name-field")).clear();
                driver.findElement(By.id("resource-name-field")).sendKeys(resoursename);

                new ComboBox().selectValue(3, type);


                WebElement desc = driver.findElement(By.id("associate-resource-details-field-text-content"));
                action.doubleClick(desc);
                action.perform();

                driver.findElement(By.id("resource-description")).clear();
                driver.findElement(By.id("resource-description")).sendKeys(description);
                new ComboBox().selectValue(4, instructoronlyflag);

                ((JavascriptExecutor) driver).executeScript("arguments[0].click();",driver.findElement(By.id("browseResource")));
                new KeysSend().sendKeyBoardKeys("$"+Config.fileUploadPath+filename+"^");
                new WebDriverWait(driver, 1200).until(ExpectedConditions.presenceOfElementLocated(By.id("start_queue")));
                driver.findElement(By.id("start_queue")).click();
                Thread.sleep(15000);
                driver.findElement(By.id("associateResourceToNode")).click();
                Thread.sleep(30000);
                driver.quit();
                new ReInitDriver().startDriver("firefox");

            }
        }
        catch(Exception e)
        {
            Assert.fail("Exception in App helper method creatresourcesattopiclevel in class  ResourseCreate",e);
        }
    }

    //add resources at subtopic level
    public void createresourcesatsubtopiclevel(int dataIndex,int topictoexpand,int chapter,int subtopic)
    {
        try
        {
            WebDriver driver=Driver.getWebDriver();
            String course = ReadTestData.readDataByTagName("", "course", Integer.toString(dataIndex));
            String resoursename = ReadTestData.readDataByTagName("", "resoursename", Integer.toString(dataIndex));
            String type = ReadTestData.readDataByTagName("", "type", Integer.toString(dataIndex));
            String description = ReadTestData.readDataByTagName("", "description", Integer.toString(dataIndex));
            String instructoronlyflag = ReadTestData.readDataByTagName("", "instructoronlyflag", Integer.toString(dataIndex));
            String filename = ReadTestData.readDataByTagName("", "filename", Integer.toString(dataIndex));
            new DirectLogin().CMSLogin();
            String title=driver.getTitle();
            if(title.equals("Course Content Details"))
            {
                driver.findElement(By.cssSelector("img[alt=\""+Config.course+"\"]")).click();
                List<WebElement> allchapter=(List<WebElement>) ((JavascriptExecutor)driver).executeScript("return arguments[0]",driver.findElements(By.cssSelector("div[class='expand-chapter-tree expand']")));
                int index1=0;
                for(WebElement elements:allchapter)
                {
                    if(index1==chapter)
                    {
                        elements.click();
                        break;
                    }
                    index1++;
                }
                List<WebElement> alltopic=(List<WebElement>) ((JavascriptExecutor)driver).executeScript("return arguments[0]",driver.findElements(By.cssSelector("div[class='expand-topic-tree expand']")));
                int index=0;
                for(WebElement element:alltopic)
                {

                    if(index==topictoexpand)
                    {
                        element.click();
                        break;
                    }
                    index++;
                }
                List<WebElement> allsubtopictopic=(List<WebElement>) ((JavascriptExecutor)driver).executeScript("return arguments[0]",driver.findElements(By.cssSelector("div[class='course-subtopic-label node']")));
                int index2=0;
                for(WebElement element1:allsubtopictopic)
                {

                    if(index2==subtopic)
                    {
                        element1.click();
                        break;
                    }
                    index2++;
                }
                Thread.sleep(3000);
                driver.findElement(By.cssSelector("div.associate-resource")).click();
                WebElement resname = driver.findElement(By.className("associate-resource-field-text"));
                Actions action = new Actions(driver);
                action.doubleClick(resname);
                action.perform();
                driver.findElement(By.id("resource-name-field")).clear();
                driver.findElement(By.id("resource-name-field")).sendKeys(resoursename);
                new ComboBox().selectValue(3, type);
                WebElement desc = driver.findElement(By.id("associate-resource-details-field-text-content"));
                action.doubleClick(desc);
                action.perform();

                driver.findElement(By.id("resource-description")).clear();
                driver.findElement(By.id("resource-description")).sendKeys(description);
                new ComboBox().selectValue(4, instructoronlyflag);
                //    driver.findElement(By.linkText("image")).click();
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();",driver.findElement(By.id("browseResource")));
                new KeysSend().sendKeyBoardKeys(Config.fileUploadPath+filename+"^");
                Thread.sleep(2000);
                driver.findElement(By.id("start_queue")).click();
                Thread.sleep(15000);
                driver.findElement(By.id("associateResourceToNode")).click();
                Thread.sleep(30000);
                driver.quit();
                new ReInitDriver().startDriver("firefox");
            }
        }
        catch(Exception e)
        {
            Assert.fail("Exception in App helper method addresourcesatsubtopiclevel in class  ResourseCreate",e);
        }
    }

    public void URLBasedResources(int dataIndex,int chapter)
    {
        WebDriver driver=Driver.getWebDriver();
        try
        {
            String course = ReadTestData.readDataByTagName("", "course", Integer.toString(dataIndex));
            String resoursename = ReadTestData.readDataByTagName("", "resoursename", Integer.toString(dataIndex));
            String type = ReadTestData.readDataByTagName("", "type", Integer.toString(dataIndex));
            String description = ReadTestData.readDataByTagName("", "description", Integer.toString(dataIndex));
            String instructoronlyflag = ReadTestData.readDataByTagName("", "instructoronlyflag", Integer.toString(dataIndex));
            String urlname = ReadTestData.readDataByTagName("", "urlname", Integer.toString(dataIndex));
            driver.get(Config.baseURL);
            new DirectLogin().CMSLogin();
            String title=driver.getTitle();
            if(title.equals("Course Content Details"))
            {
                driver.findElement(By.cssSelector("img[alt=\""+Config.course+"\"]")).click();
                List<WebElement> allchapter=(List<WebElement>) ((JavascriptExecutor)driver).executeScript("return arguments[0]",driver.findElements(By.cssSelector("div[class='course-chapter-label node']")));
                int index1=0;
                for(WebElement elements:allchapter)
                {
                    if(index1==chapter)
                    {
                        elements.click();
                        break;
                    }
                    index1++;
                }
                driver.findElement(By.cssSelector("div.associate-resource")).click();
                WebElement resname = driver.findElement(By.className("associate-resource-field-text"));
                Actions action = new Actions(driver);
                action.doubleClick(resname);
                action.perform();
                driver.findElement(By.id("resource-name-field")).clear();
                driver.findElement(By.id("resource-name-field")).sendKeys(resoursename);
                new ComboBox().selectValue(3, type);
                WebElement desc = driver.findElement(By.id("associate-resource-details-field-text-content"));
                action.doubleClick(desc);
                action.perform();
                driver.findElement(By.id("resource-description")).clear();
                driver.findElement(By.id("resource-description")).sendKeys(description);
                new ComboBox().selectValue(4, instructoronlyflag);
                //driver.findElement(By.linkText("image")).click();
                WebElement url = driver.findElement(By.cssSelector("div[fieldname='resource-url']"));
                action.doubleClick(url);
                action.perform();
                driver.findElement(By.cssSelector("input[id='resource-url-field']")).clear();
                driver.findElement(By.cssSelector("input[id='resource-url-field']")).sendKeys(urlname);
                driver.findElement(By.id("associateResourceToNode")).click();
                Thread.sleep(30000);
            }
        }
        catch(Exception e)
        {
            Assert.fail("Exception in App helper method URLBasedResources in class  ResourseCreate",e);
        }
    }

    public void assignResources(int dataIndex)
    {
        WebDriver driver=Driver.getWebDriver();
        try
        {
            String context_title = ReadTestData.readDataByTagName("", "context_title", Integer.toString(dataIndex));
            String  resourceName = ReadTestData.readDataByTagName("", "resoursename", Integer.toString(dataIndex));
            String  shareWithInitialString = ReadTestData.readDataByTagName("", "shareWithInitialString", Integer.toString(dataIndex));
            String shareName = ReadTestData.readDataByTagName("", "shareName", Integer.toString(dataIndex));
            String shareWithClass = ReadTestData.readDataByTagName("", "shareWithClass", Integer.toString(dataIndex));
            String duedate = ReadTestData.readDataByTagName("", "duedate", Integer.toString(dataIndex));
            String duetime = ReadTestData.readDataByTagName("", "duetime", Integer.toString(dataIndex));
            String accessibleafter = ReadTestData.readDataByTagName("", "accessibleafter", Integer.toString(dataIndex));
            String additionalnote = ReadTestData.readDataByTagName("", "additionalnote", Integer.toString(dataIndex));
            new Navigator().NavigateTo("Resources");

            //Adding assignment to search
            driver.findElement(By.id("all-resource-search-textarea")).clear();
            driver.findElement(By.id("all-resource-search-textarea")).sendKeys("\""+resourceName+"\"");
            driver.findElement(By.cssSelector("#all-resource-search-button > span.ins-resource-search-sprite.instructor-resource-search-img")).click();
            Thread.sleep(3000);

            new Click().clickbylist("assign-this", 0);
            new ShareWith().share(shareWithInitialString, shareName, shareWithClass,context_title,true);
            Thread.sleep(3000);

            driver.findElement(By.id("due-time")).click();
            List<WebElement> elements = driver.findElements(By.xpath("//li"));
            for(WebElement time : elements)
            {
                if(time.getText().equals(duetime))
                {
                    time.click();
                    break;
                }
            }


            driver.findElement(By.id("due-date")).click();
            Thread.sleep(2000);
            driver.findElement(By.cssSelector("span[class='ui-icon ui-icon-circle-triangle-e']")).click();
            Thread.sleep(2000);
            driver.findElement(By.linkText(duedate)).click();
            Thread.sleep(2000);
            if(accessibleafter != null)
            {
                driver.findElement(By.id("accessible-date")).click();
                driver.findElement(By.linkText(accessibleafter)).click();
            }
            Thread.sleep(2000);
            driver.findElement(By.id("additional-notes")).clear();
            driver.findElement(By.id("additional-notes")).sendKeys(additionalnote);

            driver.findElement(By.cssSelector("span[class='btn sty-green submit-assign']")).click();
            Thread.sleep(5000);

        }
        catch(Exception e)
        {
            Assert.fail("Exception in App helper method assignResources in class  ResourseCreate",e);
        }
    }
    //     Created by priyanka on 1/2/2015.
    public void createResourceAtCourseLevel(int dataIndex,String coursename)
    {
        WebDriver driver=Driver.getWebDriver();
        try {
            String resoursename = ReadTestData.readDataByTagName("", "resoursename", Integer.toString(dataIndex));
            String type = ReadTestData.readDataByTagName("", "type", Integer.toString(dataIndex));
            String description = ReadTestData.readDataByTagName("", "description", Integer.toString(dataIndex));
            String instructoronlyflag = ReadTestData.readDataByTagName("", "instructoronlyflag", Integer.toString(dataIndex));
            String filename = ReadTestData.readDataByTagName("", "filename", Integer.toString(dataIndex));
            driver.get(Config.baseURL);
            new DirectLogin().CMSLogin();

            driver.findElement(By.cssSelector("img[alt='"+coursename+"']")).click();
            driver.findElement(By.cssSelector("div[class='course-label node']")).click();
            driver.findElement(By.cssSelector("div.associate-resource")).click();
            WebElement resname = driver.findElement(By.className("associate-resource-field-text"));
            Actions action = new Actions(driver);
            action.doubleClick(resname);
            action.perform();
            driver.findElement(By.id("resource-name-field")).clear();
            driver.findElement(By.id("resource-name-field")).sendKeys(resoursename);

            new ComboBox().selectValue(3, type);

            WebElement desc = driver.findElement(By.id("associate-resource-details-field-text-content"));
            action.doubleClick(desc);
            action.perform();

            driver.findElement(By.id("resource-description")).clear();
            driver.findElement(By.id("resource-description")).sendKeys(description);
            new ComboBox().selectValue(4, instructoronlyflag);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();",driver.findElement(By.id("browseResource")));
            new KeysSend().sendKeyBoardKeys("$"+Config.fileUploadPath+filename+"^");
            Thread.sleep(2000);
            driver.findElement(By.id("start_queue")).click();
            Thread.sleep(30000);
            driver.findElement(By.id("associateResourceToNode")).click();
            Thread.sleep(30000);


        }

        catch(Exception e)
        {
            Assert.fail("Exception in App helper method createResourceAtCourseLevel in class  ResourseCreate",e);
        }
    }


    public void openResourcesTab(String chapterName,String subChapterName,String dataIndex){
        try{
            WebDriver driver = Driver.getWebDriver();
            new Navigator().NavigateTo("eTextBook");
            if(!chapterName.equals("")){
                driver.findElement(By.xpath("//h3[text() = '"+chapterName+"']")).click();
            }
            WebDriverUtil.clickOnElementUsingJavascript(driver.findElement(By.linkText(subChapterName)));
            WebDriverUtil.clickOnElementUsingJavascript(driver.findElement(By.cssSelector("span[title = 'Resources']")));
        }catch(Exception e){
            Assert.fail("Exception in the test method 'openResourcesTab' in the class 'ResourceCreate'",e);
        }
    }
}
