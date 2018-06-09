package com.snapwiz.selenium.tests.staf.learningspaces.apphelper;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.snapwiz.selenium.tests.staf.framework.utility.StringUtil;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.AssignmentResponsesPage;
import com.snapwiz.selenium.tests.staf.framework.utility.WebDriverUtil;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.CMS.ManageContent;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.Click;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.snapwiz.selenium.tests.staf.learningspaces.Config;
import com.snapwiz.selenium.tests.staf.framework.controller.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.ShareWith;

public class PostMessage extends Driver{
    private static Logger logger=Logger.getLogger("com.snapwiz.selenium.tests.staf.learningspaces.apphelper.PostMessage");
    /*
     * post any message in course
     */
    public void postmessage(String randomtext)
    {
        try
        {
            WebDriver driver=Driver.getWebDriver();
            WebDriverUtil.waitTillVisibilityOfElement(driver.findElement(By.cssSelector("a[data-type='note']")), 500);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("a[data-type='note']")));
            if(Config.browser.equals("ie"))
            {
                new KeysSend().sendKeyBoardKeys(randomtext);
            }
            else// if(Config.browser.equals("chrome"))
            {
                WebElement t=driver.findElement(By.id("iframe-user-text-input-div"));
                driver.switchTo().frame(t) ;
                driver.findElement(By.xpath("/html/body/font")).click();
                driver.findElement(By.xpath("/html/body")).sendKeys(randomtext);
                driver.switchTo().defaultContent();
            }

            Thread.sleep(2000);
            ((JavascriptExecutor)driver).executeScript("arguments[0].click();",driver.findElement(By.id("post-submit-button")));
            Thread.sleep(5000);
        }
        catch(Exception e)
        {
            Assert.fail("Message not posted",e);
        }
    }

    public void postMessageWithLangaugePallete(int dataIndex)
    {
        try
        {
            WebDriver driver=Driver.getWebDriver();
            String selectLanguagePalette = ReadTestData.readDataByTagName("", "selectLanguagePalette", Integer.toString(dataIndex));
            ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
            WebDriverUtil.waitTillVisibilityOfElement(driver.findElement(By.cssSelector("a[data-type='note']")), 500);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("a[data-type='note']")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("ls-shareImg")));
            WebDriverUtil.waitTillVisibilityOfElement(driver.findElement(By.cssSelector("img[id='languages']")), 500);


            if(selectLanguagePalette!=null) {
                if (selectLanguagePalette.equals("spanish")) {
                    WebDriverUtil.clickOnElementUsingJavascript(driver.findElement(By.cssSelector("img[id='languages']")));
                    WebDriverUtil.clickOnElementUsingJavascript(driver.findElement(By.cssSelector("#spanish")));
                    for(WebElement element:manageContent.langaugePalette_allinput){
                        element.click();
                    }
                    manageContent.langaugePalette_saveButton.click(); //click on the save button

                }

                if (selectLanguagePalette.equals("italian")) {
                    WebDriverUtil.clickOnElementUsingJavascript(driver.findElement(By.cssSelector("img[id='languages']")));
                    WebDriverUtil.clickOnElementUsingJavascript(driver.findElement(By.cssSelector("#italian")));
                    for(WebElement element:manageContent.langaugePalette_allinput){
                        element.click();
                    }
                    manageContent.langaugePalette_saveButton.click(); //click on the save button

                }

                if (selectLanguagePalette.equals("french")) {
                    WebDriverUtil.clickOnElementUsingJavascript(driver.findElement(By.cssSelector("img[id='languages']")));
                    WebDriverUtil.clickOnElementUsingJavascript(driver.findElement(By.cssSelector("#french")));
                    for(WebElement element:manageContent.langaugePalette_allinput){
                        element.click();
                    }
                    manageContent.langaugePalette_saveButton.click(); //click on the save button

                }
                Thread.sleep(2000);
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.id("post-submit-button")));
                Thread.sleep(5000);
            }
        }
        catch(Exception e)
        {
            Assert.fail("Message not posted with language pallete",e);
        }
    }

    public void postMessageWithoutSubmit(String randomtext)
    {
        try
        {
            WebDriver driver=Driver.getWebDriver();
            WebDriverUtil.waitTillVisibilityOfElement(driver.findElement(By.cssSelector("a[data-type='note']")), 500);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("a[data-type='note']")));
            if(Config.browser.equals("ie"))
            {
                new KeysSend().sendKeyBoardKeys(randomtext);
            }
            else //if(Config.browser.equals("chrome"))
            {
                WebElement t=driver.findElement(By.id("iframe-user-text-input-div"));
                driver.switchTo().frame(t) ;
                driver.findElement(By.xpath("/html/body/font")).click();
                driver.findElement(By.xpath("/html/body")).sendKeys(randomtext);
                driver.switchTo().defaultContent();
            }
			/*else
			{
			driver.switchTo().frame("iframe-user-text-input-div").findElement(By.xpath("/html/body/font")).sendKeys(randomtext);
			driver.switchTo().defaultContent();
			}*/
            Thread.sleep(2000);
        }
        catch(Exception e)
        {
            Assert.fail("Message not entered in the share-with box",e);
        }
    }

    public boolean postMessageAndShare(String randomtext,String shareWithInitialString,String studentnametag,String dataIndex,String shareWithClass)
    {
        WebDriver driver=Driver.getWebDriver();
        WebDriverUtil.waitTillVisibilityOfElement(driver.findElement(By.cssSelector("a[data-type='note']")),500);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("a[data-type='note']")));


        boolean sharefound = false;
        try
        {
            if(Config.browser.equals("ie"))
            {

                Thread.sleep(2000);
                new KeysSend().sendKeyBoardKeys(randomtext);
            }
            else
            {

                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("ls-shareImg")));
                WebElement t=driver.findElement(By.id("iframe-user-text-input-div"));
                driver.switchTo().frame(t) ;
                //driver.findElement(By.xpath("/html/body/font")).click();
                driver.findElement(By.xpath("/html/body")).sendKeys(randomtext);
                driver.switchTo().defaultContent();
            }

            String shareName=ReadTestData.readDataByTagName("", studentnametag, dataIndex);
            String context_title = ReadTestData.readDataByTagName("", "context_title", dataIndex);
            new ShareWith().shareInCS(shareWithInitialString, shareName, shareWithClass,context_title,true);
            Thread.sleep(3000);

            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.id("post-submit-button")));
            logger.log(Level.INFO, "Message Posted with Share");
            Thread.sleep(5000);

        }
        catch(Exception e)
        {
            Assert.fail("Message not posted along with sharing it with student/instructor",e);
        }
        sharefound = new PostMessageValidate().postMessageValidate(randomtext);
        if(sharefound == false) Assert.fail("Message posted not visible on Course Stream");
        return sharefound;
    }

    public void postlink(String linktext)
    {
        try
        {
            WebDriver driver=Driver.getWebDriver();
            WebDriverUtil.waitTillVisibilityOfElement(driver.findElement(By.cssSelector("a[data-type='link']")), 500);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("a[data-type='link']")));
            Thread.sleep(2000);
            if(Config.browser.equals("ie"))
            {
                new KeysSend().sendKeyBoardKeys(linktext);
            }
            else
            {
                WebElement t=driver.findElement(By.id("iframe-user-link-input-div"));
                driver.switchTo().frame(t) ;
                driver.findElement(By.xpath("/html/body/font")).click();
                driver.findElement(By.xpath("/html/body")).sendKeys(linktext);
                driver.switchTo().defaultContent();
            }

            Thread.sleep(10000);
            driver.findElement(By.id("post-submit-button")).click();
            logger.log(Level.INFO,"LinkPost Post");
            Thread.sleep(5000);

        }
        catch(Exception e)
        {
            e.printStackTrace();
            Assert.fail("Link not posted",e);
        }
    }

    public void postLinkWithoutSubmit(String linktext)
    {
        try
        {
            WebDriver driver=Driver.getWebDriver();
            WebDriverUtil.waitTillVisibilityOfElement(driver.findElement(By.cssSelector("a[data-type='link']")), 500);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("a[data-type='link']")));				Thread.sleep(2000);
            if(Config.browser.equals("ie"))
            {
                new KeysSend().sendKeyBoardKeys(linktext);
            }
            else //if(Config.browser.equals("chrome"))
            {
                WebElement t=driver.findElement(By.id("iframe-user-link-input-div"));
                driver.switchTo().frame(t) ;
                driver.findElement(By.xpath("/html/body/font")).click();
                driver.findElement(By.xpath("/html/body")).sendKeys(linktext);
                driver.switchTo().defaultContent();
            }

            Thread.sleep(10000);
        }
        catch(Exception e)
        {
            e.printStackTrace();
            Assert.fail("Link not posted",e);
        }
    }

    public boolean postLinkAndShare(String linktext,String shareWithInitialString,String studentnametag,String dataIndex)
    {
        boolean sharefound = false;
        try
        {
            WebDriver driver=Driver.getWebDriver();
            WebDriverUtil.waitTillVisibilityOfElement(driver.findElement(By.cssSelector("a[data-type='link']")), 500);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("a[data-type='link']")));				Thread.sleep(2000);
            if(Config.browser.equals("ie"))
            {
                new KeysSend().sendKeyBoardKeys(linktext);
            }
            else //if(Config.browser.equals("chrome"))
            {
                WebElement t=driver.findElement(By.id("iframe-user-link-input-div"));
                driver.switchTo().frame(t) ;
                driver.findElement(By.xpath("/html/body/font")).click();
                driver.findElement(By.xpath("/html/body")).sendKeys(linktext);
                driver.switchTo().defaultContent();
            }

            String shareName=ReadTestData.readDataByTagName("", studentnametag, dataIndex);
            String context_title = ReadTestData.readDataByTagName("", "context_title", dataIndex);
            new ShareWith().shareInCS(shareWithInitialString, shareName, "false",context_title,true);

            Thread.sleep(3000);
            driver.findElement(By.id("post-submit-button")).click();
            logger.log(Level.INFO,"LinkPost Post");
            Thread.sleep(5000);
            List<WebElement> allSharedElements = driver.findElements(By.className("ls-stream-share__title"));
            if(allSharedElements.get(0).getText().contains(linktext))
                sharefound = true;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            Assert.fail("Link not posted along with sharing it with student/instructor",e);
        }
        return sharefound;
    }

    public void postLinkAndShareWithDefaultClassSection(String linktext )
    {
        try {
            WebDriver driver=Driver.getWebDriver();
            WebDriverUtil.waitTillVisibilityOfElement(driver.findElement(By.cssSelector("a[data-type='link']")), 500);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("a[data-type='link']")));				WebElement t = driver.findElement(By.id("iframe-user-link-input-div"));
            driver.switchTo().frame(t);
            driver.findElement(By.xpath("/html/body/font")).click();
            driver.findElement(By.xpath("/html/body")).sendKeys(linktext);
            driver.switchTo().defaultContent();
            Thread.sleep(5000);
            driver.findElement(By.id("post-submit-button")).click();
            logger.log(Level.INFO, "LinkPost Post");
        }
        catch(Exception e)
        {
            Assert.fail("Link not posted along with sharing it with student/instructor",e);
        }
    }


    public boolean postMessageAndShareToGroup(String randomtext,String shareWithInitialString,String dataIndex,String shareWithClass)
    {
        WebDriver driver=Driver.getWebDriver();
        WebDriverUtil.waitTillVisibilityOfElement(driver.findElement(By.cssSelector("a[data-type='note']")),500);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("a[data-type='note']")));


        boolean sharefound = false;
        try
        {
            if(Config.browser.equals("ie"))
            {

                Thread.sleep(2000);
                new KeysSend().sendKeyBoardKeys(randomtext);
            }
            else
            {

                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("ls-shareImg")));
                WebElement t=driver.findElement(By.id("iframe-user-text-input-div"));
                driver.switchTo().frame(t) ;
                driver.findElement(By.xpath("/html/body")).sendKeys(randomtext);
                driver.switchTo().defaultContent();
            }
            new Navigator().closeHelp();
            String shareName=ReadTestData.readDataByTagName("", "GroupName", dataIndex);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("closebutton")));
            Thread.sleep(5000);
            List<WebElement> classSectionName=driver.findElements(By.className("maininput"));
            for(WebElement ele:classSectionName){
                if(ele.isDisplayed()){
                    ele.sendKeys(shareWithInitialString);
                    Thread.sleep(2000);
                    break;

                }
            }
            new KeysSend().sendKeyBoardKeys("^");
            Thread.sleep(2000);
            new KeysSend().sendKeyBoardKeys("^");

           /* List<WebElement> suggestname = driver.findElements(By.xpath("//*[starts-with(@rel, 'cgrp_')]"));

            for (WebElement answerchoice: suggestname)
            {
                System.out.println("Names "+answerchoice.getText());
                System.out.println("shareName "+shareName);
                if(answerchoice.getText().trim().contains(shareName))
                {
                    System.out.println("Inside");
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", answerchoice);
                    sharefound = true;
                    break;
                }
            }*/

            //WebElement element=driver.findElement(By.xpath("//*[starts-with(@rel, 'cgrp_')]"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.id("post-submit-button")));
            logger.log(Level.INFO, "Message Posted with Share");
            Thread.sleep(5000);

        }
        catch(Exception e)
        {
            Assert.fail("Message not posted along with sharing it with student/instructor",e);
        }
        sharefound = new PostMessageValidate().postMessageValidate(randomtext);
        if(sharefound == false) Assert.fail("Message posted not visible on Course Stream");
        return sharefound;
    }


    public String postMessageFromAssignmentResponsePage()
    {
        try {
            WebDriver driver=Driver.getWebDriver();
            AssignmentResponsesPage assignmentResponsesPage = PageFactory.initElements(driver, AssignmentResponsesPage.class);
            String random= StringUtil.generateRandomString(5, StringUtil.Mode.ALPHA);
            new Click().clickbyxpath("//div[@id='select-all-drop-down-wraper']/descendant::a[1]");
            assignmentResponsesPage.studentDropDown.get(1).click();
            driver.findElement(By.className("gradebook-menu-icon")).click();
            assignmentResponsesPage.postAMessage.click();
            assignmentResponsesPage.messageTextField.sendKeys(random);
            assignmentResponsesPage.sendButton.click();
            return random;
        }


        catch(Exception e)
        {
            Assert.fail("Link not posted along with sharing it with student/instructor",e);
        }
        return null;
    }

    public boolean postMessageAndShareinCs(String randomtext,String dataIndex,String shareWithClass)
    {
        WebDriver driver=Driver.getWebDriver();
        WebDriverUtil.waitTillVisibilityOfElement(driver.findElement(By.cssSelector("a[data-type='note']")),500);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("a[data-type='note']")));


        boolean sharefound = false;
        try {
            if (Config.browser.equals("ie")) {

                Thread.sleep(2000);
                new KeysSend().sendKeyBoardKeys(randomtext);
            } else {

                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("ls-shareImg")));
                WebElement t = driver.findElement(By.id("iframe-user-text-input-div"));
                driver.switchTo().frame(t);
                driver.findElement(By.xpath("/html/body")).sendKeys(randomtext);
                driver.switchTo().defaultContent();
            }
            new Navigator().closeHelp();
            String GroupName = ReadTestData.readDataByTagName("", "GroupName", dataIndex);
            String studentName = ReadTestData.readDataByTagName("", "studentName", dataIndex);
            String classSection = ReadTestData.readDataByTagName("", "classSection", dataIndex);

           /* if (classSection.equals("true")) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.id("post-submit-button")));
            }*/

            if (classSection.equals("true") && GroupName != null) {
                System.out.println("dxfcghjk");
                driver.findElement(By.className("maininput")).sendKeys(GroupName);
                driver.findElement(By.xpath("//*[starts-with(@rel, 'cgrp_')]")).click();

            }


            if (classSection.equals(true) && GroupName != null && studentName != null) {

                Thread.sleep(5000);
                List<WebElement> classSectionName = driver.findElements(By.className("maininput"));
                for (WebElement ele : classSectionName) {
                    if (ele.isDisplayed()) {
                        ele.sendKeys(GroupName);
                        Thread.sleep(2000);
                        break;
                    }
                }

                new KeysSend().sendKeyBoardKeys("^");
                Thread.sleep(2000);
                new KeysSend().sendKeyBoardKeys("^");

                List<WebElement> classSectionName1 = driver.findElements(By.className("maininput"));
                for (WebElement ele : classSectionName1) {
                    if (ele.isDisplayed()) {
                        ele.sendKeys(studentName);
                        Thread.sleep(2000);
                        break;
                    }
                }


                new KeysSend().sendKeyBoardKeys("^");
                Thread.sleep(2000);
                new KeysSend().sendKeyBoardKeys("^");

            }
            if (classSection.equals("true") && studentName != null) {

                List<WebElement> classSectionName2 = driver.findElements(By.className("maininput"));
                for (WebElement ele : classSectionName2) {
                    if (ele.isDisplayed()) {
                        ele.sendKeys(studentName);
                        Thread.sleep(2000);
                        break;
                    }
                }


                new KeysSend().sendKeyBoardKeys("^");
                Thread.sleep(2000);
                new KeysSend().sendKeyBoardKeys("^");

            }
            if (classSection.equals("false") && studentName != null && GroupName != null) {

                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("closebutton")));
                Thread.sleep(5000);
                List<WebElement> classSectionName3 = driver.findElements(By.className("maininput"));
                for (WebElement ele : classSectionName3) {
                    if (ele.isDisplayed()) {
                        ele.sendKeys(GroupName);
                        Thread.sleep(2000);
                        break;
                    }
                }

                new KeysSend().sendKeyBoardKeys("^");
                Thread.sleep(2000);
                new KeysSend().sendKeyBoardKeys("^");

                List<WebElement> classSectionName4 = driver.findElements(By.className("maininput"));
                for (WebElement ele : classSectionName4) {
                    if (ele.isDisplayed()) {
                        ele.sendKeys(studentName);
                        Thread.sleep(2000);
                        break;
                    }
                }


                new KeysSend().sendKeyBoardKeys("^");
                Thread.sleep(2000);
                new KeysSend().sendKeyBoardKeys("^");
            }
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.id("post-submit-button")));
            logger.log(Level.INFO, "Message Posted with Share");

        }



        catch(Exception e)
        {
            Assert.fail("Message not posted along with sharing it with student/instructor",e);
        }

        return sharefound;
    }



}
