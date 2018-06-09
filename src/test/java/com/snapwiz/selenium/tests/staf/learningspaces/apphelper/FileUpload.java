package com.snapwiz.selenium.tests.staf.learningspaces.apphelper;
import java.awt.Robot;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


import com.snapwiz.selenium.tests.staf.framework.utility.WebDriverUtil;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.Click;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.UIElement;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.TextSend;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.snapwiz.selenium.tests.staf.learningspaces.Config;
import com.snapwiz.selenium.tests.staf.framework.controller.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.ShareWith;

public class FileUpload extends Driver {

    private static Logger logger = Logger.getLogger("com.snapwiz.selenium.tests.staf.learningspaces.apphelper.FileUpload");
    public void fileUpload(String dataIndex,boolean upload)
    {
        WebDriver driver=Driver.getWebDriver();
        try
        {
            String filename =  ReadTestData.readDataByTagName("", "filename", dataIndex);
            logger.log(Level.INFO,"Starting File Upload");
            WebDriverUtil.waitTillVisibilityOfElement(driver.findElement(By.cssSelector("a[data-type='file']")), 500);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("a[data-type='file']")));	            //((JavascriptExecutor) driver).executeScript("arguments[0].click();",driver.findElement(By.xpath("/html/body/div[3]/div/div/div/div/ul/li[3]/a")));
            Thread.sleep(2000);
            if(Config.browser.equals("ie") || Config.browser.equals("chrome"))
                driver.findElement(By.xpath("//*[@id='file-upload-button']")).click();
            else
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();",driver.findElement(By.xpath("//*[@id='file-upload-button']")));
            Thread.sleep(2000);
            new KeysSend().sendKeyBoardKeys("$"+Config.fileUploadPath+filename+"^");
            Thread.sleep(20000);
            if(upload == true)
            {
                driver.findElement(By.id("post-submit-button")).click();
                Thread.sleep(10000);
            }
            logger.log(Level.INFO,"File Upload Completed");
            Thread.sleep(50000);

        }
        catch(Exception e)
        {
            e.printStackTrace();
            Assert.fail("Exception in app helper fileUpload in class FileUpload",e);
        }
    }




    public void fileUploadFromNote(String dataIndex,boolean upload)
    {
        WebDriver driver=Driver.getWebDriver();
        try
        {
            String filename =  ReadTestData.readDataByTagName("", "filename", dataIndex);
            logger.log(Level.INFO,"Starting File Upload");
            new UIElement().waitAndFindElement(By.className("add-note-button"));
            driver.findElement(By.className("add-note-button")).click();
            new UIElement().waitAndFindElement(By.id("uploadFile"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();",driver.findElement(By.id("uploadFile")));
            new KeysSend().sendKeyBoardKeys("$"+Config.fileUploadPath+filename+"^");
            Thread.sleep(20000);
            new TextSend().textsendbyclass("This is a Note","ins-uploadResource-textbox");
            List<WebElement> divElementsList = driver.findElements(By.cssSelector("div[class = 'course-chapter-label node']"));
            for(int g=0;g<divElementsList.size();g++){
                if(divElementsList.get(g).getText().contains("The Study of Life")){
                    new Click().clickByElement(divElementsList.get(g));
                    Thread.sleep(2000);
                }
            }
            if(upload == true)
            {
                driver.findElement(By.xpath("//span[@title = 'Save']")).click();
                Thread.sleep(10000);
            }
            logger.log(Level.INFO,"File Upload Completed");
        }
        catch(Exception e)
        {
            e.printStackTrace();
            Assert.fail("Exception in app helper fileUploadFromNote in class FileUpload",e);
        }
    }

    public boolean fileUploadAndValidate(String dataIndex)
    {
        boolean uploaded = true;
        WebDriver driver=Driver.getWebDriver();
        try
        {
            String filename = ReadTestData.readDataByTagName("", "filename", dataIndex);
            driver.findElement(By.linkText("File")).click();
            Thread.sleep(2000);
            if(Config.browser.equals("ie") || Config.browser.equals("chrome"))
                driver.findElement(By.xpath("//*[@id='file-upload-button']")).click();
            else
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();",driver.findElement(By.xpath("//*[@id='file-upload-button']")));
            Thread.sleep(5000);
            Robot robot = new Robot();
            new KeysSend().sendKeyBoardKeys("$"+Config.fileUploadPath+filename+"^");
            Thread.sleep(2000);
            int errmsg=driver.findElements(By.className("notification-message-body")).size();
            if(errmsg>0)
            {

                String notificationtext = driver.findElement(By.className("notification-message-body")).getText();
                if(!notificationtext.equals("Your file upload request is being processed...."))
                    uploaded = false;

            }
            if(uploaded == true)
            {
                System.out.println("Clicking on submit");
                Thread.sleep(15000);
                driver.findElement(By.id("post-submit-button")).click();
                Thread.sleep(50000);
                String fileuploadedname = (driver.findElement(By.cssSelector("div[class='ls-media__body media_file_link']")).getText());
                if(!fileuploadedname.equals(filename))
                    uploaded=false;
            }
            if(uploaded == true)
            {
                System.out.println("Verifying filename");
                String shrvalue=driver.findElement(By.className("ls-stream-post__action")).getText();
                if(!shrvalue.trim().equals("shared a file"))
                {
                    uploaded=false;
                }
            }

        }
        catch(Exception e)
        {
            Assert.fail("Exception in app helper fileUploadValidate in class FileUpload",e);
        }
        return uploaded;
    }

    public boolean fileUploadNotificationMessageValidate(String dataIndex,String notificationMessage,boolean uploadFile)
    {
        boolean uploaded = true;
        WebDriver driver=Driver.getWebDriver();
        try
        {
            String filename = ReadTestData.readDataByTagName("", "filename", dataIndex);
            driver.findElement(By.linkText("File")).click();

            if(Config.browser.equals("ie") || Config.browser.equals("chrome"))
                driver.findElement(By.xpath("//*[@id='file-upload-button']")).click();
            else
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();",driver.findElement(By.xpath("//*[@id='file-upload-button']")));
            Thread.sleep(3000);
            Robot robot = new Robot();
            new KeysSend().sendKeyBoardKeys("$"+Config.fileUploadPath+filename+"^");
            Thread.sleep(2000);
            int errmsg=driver.findElements(By.className("notification-message-body")).size();
            if(errmsg>0)
            {
                String notificationtext = driver.findElement(By.className("notification-message-body")).getText();
                System.out.println(notificationtext);
                if(!notificationtext.equals(notificationMessage))
                    return false;
            }
            else
            {
                Assert.fail("Notification Message during file upload did not appear.");
            }
            if(uploadFile == true)
            {
                Thread.sleep(15000);
                driver.findElement(By.id("post-submit-button")).click();
                Thread.sleep(15000);
                String fileuploadedname = (driver.findElement(By.cssSelector("div[class='ls-media__body media_file_link']")).getText());
                if(!fileuploadedname.equals(filename))
                    uploaded=false;
            }

        }
        catch(Exception e)
        {
            Assert.fail("Exception in app helper fileUploadValidate in class FileUpload",e);
        }
        return uploaded;
    }

    public boolean validateFilePosted(String fileNameToValidate)
    {
        boolean filefound = false;
        WebDriver driver=Driver.getWebDriver();
        try
        {
            Thread.sleep(5000);
            List<WebElement> links = driver.findElements(By.className("ls-stream-post__attachments"));
            for(WebElement linktext : links)
            {
                if(linktext.getText().trim().equals(fileNameToValidate))
                {
                    System.out.println("File Found in using attachements classname");
                    filefound = true;
                    break;
                }

            }
            if(filefound == false)
            {
                links = driver.findElements(By.cssSelector("div[class='ls-media__body media_file_link']"));
                for(WebElement linktext : links)
                {
                    if(linktext.getText().trim().equals(fileNameToValidate))
                    {
                        System.out.println("File Found in using media_file_link classname");
                        filefound = true;
                        break;
                    }

                }
            }

            if(filefound == true)
            {
                System.out.println("Validating the shared a file string");
                WebElement posttext = driver.findElement(By.className("ls-stream-post__action"));

                if(!posttext.getText().trim().equals("shared a file"))
                {
                    filefound = false;
                }
            }

        }
        catch(Exception e)
        {
            Assert.fail("Exception in app helper validateLink in class PostMessageValidate",e);
        }
        return filefound;
    }

    public void enterFileUploadText()
    {
        try
        {
            String str = new RandomString().randomstring(20);
            WebDriver driver=Driver.getWebDriver();

            if(Config.browser.equals("ie"))
            {
                new KeysSend().sendKeyBoardKeys(str);
            }
            else
            {
                WebElement t=driver.findElement(By.id("iframe-user-file-input-div"));
                driver.switchTo().frame(t) ;
                driver.findElement(By.xpath("/html/body/font")).click();
                driver.findElement(By.xpath("/html/body")).sendKeys(str);
                driver.switchTo().defaultContent();
            }
        }
        catch(Exception e)
        {
            Assert.fail("Exception in app helper postTextWithUpload in class PostMessageValidate",e);
        }
    }

    public void fileUploadAndShare(String shareWithInitialString, String studentnametag, String shareWithClass, String dataIndex)
    {
        WebDriver driver=Driver.getWebDriver();
        try
        {
            String context_title = ReadTestData.readDataByTagName("", "context_title",dataIndex);
            String filename =  ReadTestData.readDataByTagName("", "filename", dataIndex);
            driver.findElement(By.linkText("File")).click();
            Thread.sleep(2000);
            if(Config.browser.equals("ie") || Config.browser.equals("chrome"))
                driver.findElement(By.xpath("//*[@id='file-upload-button']")).click();
            else
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();",driver.findElement(By.xpath("//*[@id='file-upload-button']")));
            Thread.sleep(2000);
            new KeysSend().sendKeyBoardKeys("$"+Config.fileUploadPath+filename+"^");
            Thread.sleep(20000);
            String shareName=ReadTestData.readDataByTagName("", studentnametag, dataIndex);
            new ShareWith().shareInCS(shareWithInitialString, shareName, shareWithClass,context_title,true);
            driver.findElement(By.id("post-submit-button")).click();
            Thread.sleep(10000);
        }
        catch(Exception e)
        {

            Assert.fail("Exception in app helper fileUploadAndShare in class FileUpload",e);
        }
    }

    public void videoUpload(String dataIndex){
        try{
            WebDriver driver=Driver.getWebDriver();
            String filename =  ReadTestData.readDataByTagName("", "filename", dataIndex);
            if(filename!=null) {
                if (filename.contains(".jpg" +
                        "") || filename.contains(".JPG") || filename.contains(".jpeg") || filename.contains(".JPEG") || filename.contains(".mp3") || filename.contains(".MP3")) {
                    String filenameTokens[] = filename.split("[.]");
                    filename = filenameTokens[0] + ".mp4";
                }
            }
            new Click().clickBycssselector("a[class='re-icon re-embedvideo redactor-btn-image']");//click on video icon
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();",driver.findElement(By.id("cms-embed-media-upload-tab")));
            new KeysSend().sendKeyBoardKeys("$"+Config.fileUploadPath+filename+"^");
            driver.findElement(By.id("widget-createimage_start_queue")).click();
            WebDriverWait wait = new WebDriverWait(driver,180);
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("widget-createimage_start_queue")));
        }catch(Exception e){
            Assert.fail("Exception in app helper videoUpload in class FileUpload",e);

        }

    }



}
