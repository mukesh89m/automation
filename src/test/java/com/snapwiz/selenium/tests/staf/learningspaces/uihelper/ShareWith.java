package com.snapwiz.selenium.tests.staf.learningspaces.uihelper;

import java.security.Key;
import java.util.List;

import com.snapwiz.selenium.tests.staf.framework.utility.WebDriverUtil;
import com.snapwiz.selenium.tests.staf.learningspaces.Config;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.KeysSend;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import com.snapwiz.selenium.tests.staf.framework.controller.Driver;


public class ShareWith extends Driver {

    public void share(String shareWithInitialString,String shareName,String shareWithClass,String className,boolean removeExistingShare)
    {
        WebDriver driver=Driver.getWebDriver();
        try
        {
            String y[]=shareName.split(" ");
            if(!shareWithClass.toUpperCase().equals("TRUE")) {//reverse the name only if the shareWithClass parameter is not TRUE, i.e sharing has to be done with a student hence reverse the student name
                shareName = y[1] + ", " + y[0];//reverse the name with comma in between
            }
            String methodName = new Exception().getStackTrace()[1].getMethodName();
            if(!methodName.contains("updateAssignment")) {  //IF updateAssignment is required then student name does not appear with class section in the prompt
                if (!shareWithClass.toUpperCase().equals("TRUE")) {
                    if (className == null || className.equals(""))
                        shareName = shareName + " - " + Config.context_title;
                    else
                        shareName = shareName + " - " + className;
                }
            }
            System.out.println("share name "+shareName);

            boolean sharefound = false;

            new UIElement().waitAndFindElement(By.className("maininput"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("maininput")));
            new UIElement().waitAndFindElement(By.className("bit-box"));
            if(removeExistingShare == true)
            {
                List<WebElement> allClassSection = driver.findElements(By.cssSelector("li[class='bit-box']"));
                for(WebElement classSection: allClassSection)
                {
                    WebDriverUtil.clickOnElementUsingJavascript(driver.findElements(By.className("closebutton")).get(driver.findElements(By.className("closebutton")).size()-1));//click on close symbol
                    String assignToField = new TextFetch().textfetchbyclass("holder");
                    if(assignToField.length() == 0)
                    {
                        System.out.println("Inside if: ");
                        break;
                    }

                }
            }
            //driver.findElement(By.cssSelector("div[class='ir-ls-assign-dialog-assign-to-content ir-ls-assign-dialog-field']")).clear();
            driver.findElement(By.className("maininput")).sendKeys(shareWithInitialString);
            Thread.sleep(3000);
            List<WebElement> suggestname;
            if(shareWithClass.toUpperCase().equals("TRUE"))
                suggestname = driver.findElements(By.xpath("//*[starts-with(@rel, 'cls_')]"));
            else
                suggestname = driver.findElements(By.xpath("//*[starts-with(@rel, 'uid_')]"));
            for (WebElement answerchoice: suggestname)
            {
                System.out.println("Names "+answerchoice.getText());
                if(answerchoice.getText().trim().equals(shareName))
                {
                    System.out.println("Inside");
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", answerchoice);
                    answerchoice.click();
                    sharefound = true;
                    break;
                }
            }
            if(sharefound == false)
                Assert.fail("No value selected from the Assign To field");
        }
        catch(Exception e)
        {
            Assert.fail("Exception in UI helper ShareWith",e);
        }
    }

    public void shareInCS(String shareWithInitialString,String shareName,String shareWithClass,String className,boolean removeExistingShare)
    {
        WebDriver driver=Driver.getWebDriver();
        try
        {
            new Navigator().closeHelp();
            String y[]=shareName.split(" ");
            if(!shareWithClass.toUpperCase().equals("TRUE")) {//reverse the name only if the shareWithClass parameter is not TRUE, i.e sharing has to be done with a student hence reverse the student name
                shareName = y[1] + ", " + y[0]+" - "+className;//reverse the name with comma in between
            }
            System.out.println("share name "+shareName);
            boolean sharefound = false;
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("maininput")));
            Thread.sleep(5000);
            if(removeExistingShare == true)
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("closebutton")));
            Thread.sleep(5000);
            List<WebElement> classSectionName=driver.findElements(By.className("maininput"));
            for(WebElement ele:classSectionName){
                if(ele.isDisplayed()){
                    // ((JavascriptExecutor) driver).executeAsyncScript("arguments[0].value='"+shareWithInitialString+"'",ele);
                    ele.sendKeys(shareWithInitialString);
                    ele.sendKeys(Keys.ENTER);
                    Thread.sleep(2000);
                    break;

                }
            }
            List<WebElement> suggestname;
            if(shareWithClass.toUpperCase().equals("TRUE"))
                suggestname = driver.findElements(By.xpath("//*[starts-with(@rel, 'cls_')]"));
            else
                suggestname = driver.findElements(By.xpath("//*[starts-with(@rel, 'uid_')]"));
            for (WebElement answerchoice: suggestname)
            {
                System.out.println("Names "+answerchoice.getText());
                System.out.println("shareName "+shareName);

                if(answerchoice.getText().trim().equals(shareName))
                {
                    System.out.println("Inside");
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", answerchoice);
                    answerchoice.click();
                    sharefound = true;
                    break;
                }
            }
            if(sharefound == false)
                Assert.fail("No value selected from the Assign To field");
        }
        catch(Exception e)
        {
            Assert.fail("Exception in UI helper shareInCS",e);
        }
    }

    public void shareIn(String shareWithInitialString,String shareName,String shareWithClass,String className,boolean removeExistingShare)
    {
        WebDriver driver=Driver.getWebDriver();
        try
        {
            new Navigator().closeHelp();
            String y[]=shareName.split(" ");
            if(!shareWithClass.toUpperCase().equals("TRUE")) {//reverse the name only if the shareWithClass parameter is not TRUE, i.e sharing has to be done with a student hence reverse the student name
                shareName = y[1] + ", " + y[0];//reverse the name with comma in between
            }
            System.out.println("share name "+shareName);
            boolean sharefound = false;
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("maininput")));
            Thread.sleep(5000);
            if(removeExistingShare == true)
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("closebutton")));
            Thread.sleep(5000);
            List<WebElement> classSectionName=driver.findElements(By.className("maininput"));
            for(WebElement ele:classSectionName){
                if(ele.isDisplayed()){
                    ele.sendKeys(shareWithInitialString);
                    ele.sendKeys(Keys.ENTER);
                    Thread.sleep(2000);
                    break;

                }
            }
            List<WebElement> suggestname;
            if(shareWithClass.toUpperCase().equals("TRUE"))
                suggestname = driver.findElements(By.xpath("//*[starts-with(@rel, 'cls_')]"));
            else
                suggestname = driver.findElements(By.xpath("//*[starts-with(@rel, 'uid_')]"));
            for (WebElement answerchoice: suggestname)
            {
                System.out.println("Names "+answerchoice.getText());
                System.out.println("shareName "+shareName);

                if(answerchoice.getText().trim().equals(shareName))
                {
                    System.out.println("Inside");
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", answerchoice);
                    sharefound = true;
                    break;
                }
            }
            if(sharefound == false)
                Assert.fail("No value selected from the Assign To field");
        }
        catch(Exception e)
        {
            Assert.fail("Exception in UI helper shareInCS",e);
        }
    }


    public void shareWithGroup(String shareWithInitialString,String shareName,String shareWithClass,String className,boolean removeExistingShare) throws InterruptedException {
        WebDriver driver=Driver.getWebDriver();
        try
        {
            /*String y[]=shareName.split(" ");
            if(!shareWithClass.toUpperCase().equals("TRUE")) {//reverse the name only if the shareWithClass parameter is not TRUE, i.e sharing has to be done with a student hence reverse the student name
                shareName = y[1] + ", " + y[0];//reverse the name with comma in between
            }*/

            boolean shareFound = false;
            new UIElement().waitAndFindElement(By.className("maininput"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("maininput")));
            new UIElement().waitAndFindElement(By.className("bit-box"));
            if(removeExistingShare == true)
            {
                List<WebElement> allClassSection = driver.findElements(By.cssSelector("li[class='bit-box']"));
                for(WebElement classSection: allClassSection)
                {
                    WebDriverUtil.clickOnElementUsingJavascript(driver.findElements(By.className("closebutton")).get(driver.findElements(By.className("closebutton")).size()-1));//click on close symbol
                    String assignToField = new TextFetch().textfetchbyclass("holder");
                    if(assignToField.length() == 0) {
                        break;
                    }
                }
            }

            driver.findElement(By.className("maininput")).sendKeys(shareWithInitialString);
            Thread.sleep(2000);
            driver.findElement(By.xpath("//ul[@id = 'share-with_feed']//li")).click();;

        }

        catch(Exception e)
        {
            Assert.fail("Exception in UI helper ShareWith",e);
        }

    }

}
