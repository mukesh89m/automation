package com.snapwiz.selenium.tests.staf.learningspaces.apphelper;

import com.snapwiz.selenium.tests.staf.framework.controller.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.Click;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.TextSend;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

/*
 * Created by sumit on 17/12/14.
 */
public class WriteBoard extends Driver {

    public void enterTextInWriteBoard(String text, int dataIndex) {
        try {
            WebDriver driver=Driver.getWebDriver();
            new Click().clickByid("show-your-work-label");// Click on "Show your Work"
            driver.switchTo().frame(driver.findElement(By.xpath("//iframe[starts-with(@id,'whiteBoard_iframe_kedukWBSTUDENT')]")));
            new Click().clickByid("text-btn");//click on text button
            String widthvalue= driver.findElement(By.xpath("//canvas")).getAttribute("width");
            driver.findElement(By.cssSelector("canvas[width='"+widthvalue+"']")).click();
            Thread.sleep(3000);
            new TextSend().textsendbyid(text, "textEditor");
            new Click().clickbylistcssselector("button[type='button']", 2);//click on update
            driver.switchTo().defaultContent();

        } catch (Exception e) {
            Assert.fail("Exception in apphelper enterTextInWriteBoard in class WriteBoard.", e);
        }
    }

    public void instructorEnterTextInWriteBoard(String text, int dataIndex) {
        try {
            WebDriver driver=Driver.getWebDriver();
            new Click().clickByid("show-your-work-label");// Click on "Show your Work"
            Thread.sleep(2000);
            driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains(@id,'whiteBoard_iframe_kedukWBTEACHER__whiteBoard')]")));
            new Click().clickByid("text-btn");//click on text button
            String widthvalue= driver.findElement(By.xpath("//canvas")).getAttribute("width");
            driver.findElement(By.cssSelector("canvas[width='"+widthvalue+"']")).click();
            Thread.sleep(3000);
            new TextSend().textsendbyid(text, "textEditor");
            new Click().clickbylistcssselector("button[type='button']", 2);
            driver.switchTo().defaultContent();

        } catch (Exception e) {
            Assert.fail("Exception in apphelper enterTextInWriteBoard in class WriteBoard.", e);
        }
    }

    public boolean verifyWriteBoardDataIsSaved(String text) {
        boolean writeBoardData = false;
        try {
            WebDriver driver=Driver.getWebDriver();
            new Click().clickByid("show-your-work-label");//Click on "Show your link"
            String str = driver.findElement(By.id("saved_whiteBoard_JSONData")).getAttribute("innerHTML");
            System.out.println(str);
            /*if(str.length() > 50)
                writeBoardData = true;*/
            if (str.contains(text)) {
                writeBoardData = true;
            }
        } catch (Exception e) {
            Assert.fail("Exception in apphelper verifyWriteBoardDataIsSaved in class WriteBoard.", e);
        }
        return writeBoardData;
    }

    public void deleteWriteBoardData() {
        try {
            WebDriver driver=Driver.getWebDriver();
            new Click().clickByid("show-your-work-label");//Click on "Show your link"
            driver.switchTo().frame(driver.findElement(By.id("whiteBoard_iframe_kedukWBSTUDENT__whiteBoard_JSONData__false")));
            new Click().clickByid("clear-btn");//click on delete button

            Alert a = driver.switchTo().alert();
            a.accept();
            driver.switchTo().defaultContent();

        } catch (Exception e) {
            Assert.fail("Exception in apphelper deleteWriteBoardData in class WriteBoard.", e);
        }
    }

    public boolean verifyWriteBoardDataIsDeleted(String text) {
        boolean writeBoardData = false;
        WebDriver driver=Driver.getWebDriver();
        try {
            new Click().clickByid("show-your-work-label");//Click on "Show your link"
            String str = driver.findElement(By.id("saved_whiteBoard_JSONData")).getAttribute("innerHTML");
            /*if(str.length() > 50)
                writeBoardData = true;*/
            if (str.contains(text)) {
                writeBoardData = true;
            }
        } catch (Exception e) {
            Assert.fail("Exception in apphelper verifyWriteBoardDataIsDeleted in class WriteBoard.", e);
        }
        return writeBoardData;
    }

    public boolean verifyInstructorWriteBoardDataIsSavedInStudentSide(String text) {
        boolean writeBoardData = false;
        WebDriver driver=Driver.getWebDriver();
        try {
            new Click().clickByid("show-your-work-label");//Click on "Show your link"
            String str = driver.findElement(By.id("whiteBoard_feedback_JSONData")).getAttribute("innerHTML");
            System.out.println(str);
            /*if(str.length() > 50)
                writeBoardData = true;*/
            if (str.contains(text)) {
                writeBoardData = true;
            }
        } catch (Exception e) {
            Assert.fail("Exception in apphelper verifyInstructorWriteBoardDataIsSavedInStudentSide in class WriteBoard.", e);
        }
        return writeBoardData;
    }

    public boolean verifyWriteBoardDataIsSavedForNumberLine(String text) {
        boolean writeBoardData = false;
        WebDriver driver=Driver.getWebDriver();
        try {
            new Click().clickByid("show-your-work-label");//Click on "Show your link"
            String str = driver.findElement(By.id("whiteBoard_JSONData")).getAttribute("innerHTML");
            System.out.println(str);
            /*if(str.length() > 50)
                writeBoardData = true;*/
            if (str.contains(text)) {
                writeBoardData = true;
            }
        } catch (Exception e) {
            Assert.fail("Exception in apphelper verifyWriteBoardDataIsSaved in class WriteBoard.", e);
        }
        return writeBoardData;
    }

    public boolean verifyWriteBoardDataIsDeletedForNumberLine(String text) {
        boolean writeBoardData = false;
        WebDriver driver=Driver.getWebDriver();
        try {
            new Click().clickByid("show-your-work-label");//Click on "Show your link"
            String str = driver.findElement(By.id("whiteBoard_JSONData")).getAttribute("innerHTML");
            /*if(str.length() > 50)
                writeBoardData = true;*/
            if (str.contains(text)) {
                writeBoardData = true;
            }
        } catch (Exception e) {
            Assert.fail("Exception in apphelper verifyWriteBoardDataIsDeleted in class WriteBoard.", e);
        }
        return writeBoardData;
    }

    public void enterTextInWriteBoardFromCMS(String text, int dataIndex) {
        try {
            WebDriver driver=Driver.getWebDriver();
             String previewPage= ReadTestData.readDataByTagName("","previewPage",String.valueOf(dataIndex));
            if(previewPage==null){
                    new Click().clickbyxpath("//span[@title='Workboard']");//click on the workboard
            }
            driver.switchTo().frame(driver.findElement(By.xpath("//iframe[starts-with(@id,'whiteBoard_iframe_kedukWBSTUDENT')]")));
            new Click().clickByid("text-btn");//click on text button
            String widthvalue= driver.findElement(By.xpath("//canvas")).getAttribute("width");
            driver.findElement(By.cssSelector("canvas[width='"+widthvalue+"']")).click();
            Thread.sleep(3000);
            new TextSend().textsendbyid(text, "textEditor");
            Thread.sleep(2000);
            driver.findElement(By.xpath("(//div[@class='ui-dialog-buttonset'])[2]//button[1]")).click();
            driver.switchTo().defaultContent();
        } catch (Exception e) {
            Assert.fail("Exception in apphelper enterTextInWriteBoardFromCMS in class WriteBoard.", e);
        }
    }

    public void enterTextInWriteBoardInInstructorSide(String text, int dataIndex) {
        try {
            WebDriver driver=Driver.getWebDriver();
            new Click().clickbyxpath("//span[@title=' Workboard feedback']");//click on the workboard
            driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains(@id,'whiteBoard_iframe_kedukWBTEACHER__whiteBoard')]")));
            new Click().clickByid("text-btn");//click on text button
            String widthvalue= driver.findElement(By.xpath("//canvas")).getAttribute("width");
            driver.findElement(By.cssSelector("canvas[width='"+widthvalue+"']")).click();
            Thread.sleep(3000);
            new TextSend().textsendbyid(text, "textEditor");
            new Click().clickbylistcssselector("button[type='button']", 2);//click on update
            driver.switchTo().defaultContent();
        } catch (Exception e) {
            Assert.fail("Exception in apphelper enterTextInWriteBoardInInstructorSide in class WriteBoard.", e);
        }
    }

    public void drawSquareInWriteBoardInstructorSide(int dataIndex) {
        WebDriver driver=Driver.getWebDriver();
            try
            {
                new Click().clickbyxpath("//span[@title=' Workboard feedback']");//click on the workboard
                driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains(@id,'whiteBoard_iframe_kedukWBTEACHER__whiteBoard')]")));
                new Click().clickByid("square-btn");//click inside the board
                Thread.sleep(2000);
                String widthvalue= driver.findElement(By.xpath("//canvas")).getAttribute("width");
                driver.findElement(By.cssSelector("canvas[width='"+widthvalue+"']")).click();
                Thread.sleep(3000);
                driver.switchTo().defaultContent();
                driver.findElement(By.xpath("/html/body")).click();
                Thread.sleep(2000);

        } catch (Exception e) {
            Assert.fail("Exception in apphelper drawSquareInWriteBoardInstructorSide in class WriteBoard.", e);
        }
    }

    public void drawSquareInWriteBoardInStudentSide(int dataIndex) {
        WebDriver driver=Driver.getWebDriver();
        try
        {
            new Click().clickbyxpath("//span[@title='Workboard']");//click on the workboard
            driver.switchTo().frame(driver.findElement(By.xpath("//iframe[starts-with(@id,'whiteBoard_iframe_kedukWBSTUDENT')]")));
            new Click().clickByid("square-btn");//click inside the board
            Thread.sleep(2000);
            String widthvalue= driver.findElement(By.xpath("//canvas")).getAttribute("width");
            driver.findElement(By.cssSelector("canvas[width='"+widthvalue+"']")).click();
            Thread.sleep(3000);
            driver.switchTo().defaultContent();
            driver.findElement(By.xpath("/html/body")).click();
            Thread.sleep(2000);

        } catch (Exception e) {
            Assert.fail("Exception in apphelper drawSquareInWriteBoardInstructorSide in class WriteBoard.", e);
        }
    }
}