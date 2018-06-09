package com.snapwiz.selenium.tests.staf.edulastic.apphelpers;

import com.snapwiz.selenium.tests.staf.framework.controller.Driver;
import com.snapwiz.selenium.tests.staf.edulastic.uihelper.Click;
import com.snapwiz.selenium.tests.staf.edulastic.uihelper.TextSend;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

/*
 * Created by sumit on 17/12/14.
 */
public class WriteBoard extends Driver{

    public void enterTextInWriteBoard(String text, int dataIndex)
    {
        WebDriver driver=Driver.getWebDriver();
        try{
            new Click().clickByid("show-your-work-label");// Click on "Show your Work"
            driver.switchTo().frame(driver.findElement(By.xpath("//*[starts-with(@id,'whiteBoard_iframe_kedukWBSTUDENT__whiteBoard_JSONData')]")));
            new Click().clickByid("text-btn");//click on text button
            new WebDriverWait(driver,60).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='zwibbler']/canvas[1]")));
            new Click().clickByXpath("//div[@id='zwibbler']/canvas[1]");
            new WebDriverWait(driver,60).until(ExpectedConditions.presenceOfElementLocated(By.id("textEditor")));
            new TextSend().textsendbyid(text, "textEditor");
            new Click().clickbylistcssselector("button[type='button']", 2);
            driver.switchTo().defaultContent();
            new Click().clickByXpath("//span[starts-with(@class,'close-iframe-question-content')]");

        }
        catch (Exception e){
            Assert.fail("Exception in apphelper enterTextInWriteBoard in class WriteBoard.", e);
        }
    }

    public void instructorEnterTextInWriteBoard(String text, int dataIndex)
    {
        WebDriver driver=Driver.getWebDriver();
        try{
            new Click().clickByid("show-your-work-label");// Click on "Show your Work"
            Thread.sleep(2000);
            driver.switchTo().frame(driver.findElement(By.id("whiteBoard_iframe_kedukWBTEACHER__whiteBoard_JSONData__false")));
            new Click().clickByid("text-btn");//click on text button
            new Click().clickBycssselector("canvas[height='395']");

            new TextSend().textsendbyid(text, "textEditor");
            new Click().clickbylistcssselector("button[type='button']", 2);
            driver.switchTo().defaultContent();

        }
        catch (Exception e){
            Assert.fail("Exception in apphelper enterTextInWriteBoard in class WriteBoard.", e);
        }
    }

    public boolean verifyWriteBoardDataIsSaved(String text)
    {
        boolean writeBoardData = false;
        WebDriver driver=Driver.getWebDriver();
        try
        {
            new Click().clickByid("show-your-work-label");//Click on "Show your link"
            String str = driver.findElement(By.id("saved_whiteBoard_JSONData")).getAttribute("innerHTML");
            System.out.println(str);
            /*if(str.length() > 50)
                writeBoardData = true;*/
            if(str.contains(text)){
                writeBoardData = true;
            }
        }
        catch (Exception e){
            Assert.fail("Exception in apphelper verifyWriteBoardDataIsSaved in class WriteBoard.", e);
        }
        return writeBoardData;
    }

    public void deleteWriteBoardData()
    {
        try{
            WebDriver driver=Driver.getWebDriver();
            new Click().clickByid("show-your-work-label");//Click on "Show your link"
            driver.switchTo().frame(driver.findElement(By.xpath("//*[starts-with(@id,'whiteBoard_iframe_kedukWBSTUDENT__whiteBoard_JSONData')]")));
            new Click().clickByid("eraser-btn");//click on delete button
            new Click().clickByXpath("//div[@id='zwibbler']/canvas[1]");


           /* Alert a = driver.switchTo().alert();
            a.accept();*/
            driver.switchTo().defaultContent();

        }
        catch (Exception e){
            Assert.fail("Exception in apphelper deleteWriteBoardData in class WriteBoard.", e);
        }
    }

    public boolean verifyWriteBoardDataIsDeleted(String text)
    {
        boolean writeBoardData = false;
        WebDriver driver=Driver.getWebDriver();
        try
        {
            new Click().clickByid("show-your-work-label");//Click on "Show your link"
            String str = driver.findElement(By.id("saved_whiteBoard_JSONData")).getAttribute("innerHTML");
            /*if(str.length() > 50)
                writeBoardData = true;*/
            if(str.contains(text)){
                writeBoardData = true;
            }
        }
        catch (Exception e){
            Assert.fail("Exception in apphelper verifyWriteBoardDataIsDeleted in class WriteBoard.", e);
        }
        return writeBoardData;
    }

    public boolean verifyInstructorWriteBoardDataIsSavedInStudentSide(String text)
    {
        boolean writeBoardData = false;
        WebDriver driver=Driver.getWebDriver();
        try
        {
            new Click().clickByid("show-your-work-label");//Click on "Show your link"
            String str = driver.findElement(By.id("whiteBoard_feedback_JSONData")).getAttribute("innerHTML");
            System.out.println(str);
            /*if(str.length() > 50)
                writeBoardData = true;*/
            if(str.contains(text)){
                writeBoardData = true;
            }
        }
        catch (Exception e){
            Assert.fail("Exception in apphelper verifyInstructorWriteBoardDataIsSavedInStudentSide in class WriteBoard.", e);
        }
        return writeBoardData;
    }

    public boolean verifyWriteBoardDataIsSavedForNumberLine(String text)
    {
        boolean writeBoardData = false;
        WebDriver driver=Driver.getWebDriver();
        try
        {
            new Click().clickByid("show-your-work-label");//Click on "Show your link"
            driver.switchTo().frame(driver.findElement(By.xpath("//*[starts-with(@id,'whiteBoard_iframe_kedukWBSTUDENT__whiteBoard_JSONData')]")));
            String str = driver.findElement(By.id("zwibbler")).getAttribute("innerHTML");
            System.out.println(str);
            /*if(str.length() > 50)
                writeBoardData = true;*/
            if(str.contains(text)){
                writeBoardData = true;
            }
        }
        catch (Exception e){
            Assert.fail("Exception in apphelper verifyWriteBoardDataIsSaved in class WriteBoard.", e);
        }
        return writeBoardData;
    }

    public boolean verifyWriteBoardDataIsDeletedForNumberLine(String text)
    {
        boolean writeBoardData = false;
        WebDriver driver=Driver.getWebDriver();
        try
        {
            new Click().clickByid("show-your-work-label");//Click on "Show your link"
            String str = driver.findElement(By.id("zwibbler")).getAttribute("innerHTML");
            /*if(str.length() > 50)
                writeBoardData = true;*/
            if(str.contains(text)){
                writeBoardData = true;
            }
        }
        catch (Exception e){
            Assert.fail("Exception in apphelper verifyWriteBoardDataIsDeleted in class WriteBoard.", e);
        }
        return writeBoardData;
    }
}
