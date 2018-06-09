package com.snapwiz.selenium.tests.staf.edulastic.apphelpers;

import com.snapwiz.selenium.tests.staf.framework.controller.Driver;
import com.snapwiz.selenium.tests.staf.edulastic.ReadTestData;
import com.snapwiz.selenium.tests.staf.edulastic.uihelper.Click;
import com.snapwiz.selenium.tests.staf.edulastic.uihelper.SelectDropDown;
import com.snapwiz.selenium.tests.staf.edulastic.uihelper.TextSend;
import com.snapwiz.selenium.tests.staf.framework.utility.ReportUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.List;

/**
 * Created by root on 12/8/14.
 * This app helper covers functionality related to school like creating a new school
 */
public class School extends Driver {

    public String createWithOnlyName(String appendChar, int dataIndex)
    {
        String randomZipCode=null;
        String appendCharacterBuild=System.getProperty("UCHAR");
        WebDriver driver=Driver.getWebDriver();
        if (appendCharacterBuild!=null)
            appendChar=appendChar+appendCharacterBuild;
        try
        {
            boolean districtFound = false;
            String districtName=ReadTestData.readDataByTagName("", "districtName", Integer.toString(dataIndex));
            String  schoolName = new Exception().getStackTrace()[1].getMethodName()+"school"+appendChar;

            WebDriverWait wait=new WebDriverWait(driver,400);
            wait.until(ExpectedConditions.presenceOfElementLocated(By.partialLinkText("Request new school")));

            ((JavascriptExecutor) driver).executeScript("arguments[0].click();",  driver.findElement(By.partialLinkText("Request new school")));
            wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("school-name"))));
            new TextSend().textsendbyid(schoolName,"school-name");//Enter school name in school name field

            if(districtName!=null)
            {
                new TextSend().textsendbycssSelector(districtName, "input[class='maininput reset-district-box']"); //adding first three characters to the District
                Thread.sleep(2000);
                List<WebElement> suggestedNames = driver.findElements(By.xpath("//ul[@id='share-with-add-district_feed']/li"));
                for (WebElement suggestedName : suggestedNames)
                {
                    if (suggestedName.getText().trim().equals(districtName))
                    {
                        suggestedName.click();
                        //  new Click().clickByclassname("auto-focus");
                        districtFound = true;
                        break;
                    }
                }
                if (districtFound == false) {
                    Assert.fail("No school found to select from the suggested schools in the text field to enter name of district");
                }
            }
            else
            {
                String  districtNameDefault = new Exception().getStackTrace()[1].getMethodName()+"district"+appendChar;
                new TextSend().textsendbycssSelector(districtNameDefault, "input[class='maininput reset-district-box']");//Enter District
                new Click().clickBycssselector("input[class='maininput reset-district-box']");
                Thread.sleep(1000);
                new Click().clickByid("share-with-add-district_feed");
                if(driver.findElements(By.cssSelector(".auto-focus")).size()>0) {
                    new Click().clickBycssselector(".auto-focus");
                }
            }


            new TextSend().textsendbyid(new RandomString().randomstring(3), "address");//Enter address

            String  cityNameDefault = new Exception().getStackTrace()[1].getMethodName()+"city"+appendChar;
            new TextSend().textsendbyid(cityNameDefault, "city-name"); //Enter city name

            randomZipCode=new RandomString().randominteger(4);
            new TextSend().textsendbyid(randomZipCode, "zip-code");//Enter zip code

            new TextSend().textsendbyid("State","state-name");//Enter state
/*
            WebElement country = driver.findElement(By.cssSelector("span[class='select2-selection__arrow']"));
            new SelectDropDown().selectByText(country,"United States");//Select the country*/

            driver.findElement(By.cssSelector("span[class='select2-selection__arrow']")).click();
            driver.findElement(By.xpath("//li[text()='United States']")).click();//Select united states

            driver.findElement(By.cssSelector("div[class='as-search-blue-btn as-add-save-btn btn btn-blue pull-right']")).click(); //clicking on save button
            Thread.sleep(3000);

        }
        catch (Exception e)
        {
            Assert.fail("Exception in app helper createWithOnlyName in class School",e);
        }
        return randomZipCode;
    }

    public void enterAndSelectSchool(String zipcode,int dataIndex,boolean removeExistingShare)
    {

        try
        {
            boolean foundzipcode=false;
            WebDriver driver=Driver.getWebDriver();
            if(removeExistingShare == true)
                new Click().clickByclassname("closebutton"); //removing the by default present school if removeExistingShare is passed as true

            new WebDriverWait(driver,60).until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id("share-with_annoninput")));
            new Click().clickByid("share-with_annoninput");
            new TextSend().textsendbycssSelector(zipcode,"input[class='maininput maininput-center-placeholder']"); //adding first three characters to the school name
            Thread.sleep(2000);
            List<WebElement> suggestedNames =  driver.findElements(By.xpath("//*[starts-with(@rel, 'inst_')]"));

            if(suggestedNames.size()>=1)
            {
                for (int i = 0; i < suggestedNames.size(); i++)
                {

                    if (suggestedNames.get(i).getText().trim().contains(zipcode) && suggestedNames.get(i).getText().trim().contains("Ryan International"))
                    {
                        suggestedNames.get(i).click();//Select the school
                        foundzipcode = true;
                        break;
                    }else if(suggestedNames.get(i).getText().trim().contains(zipcode) && suggestedNames.get(i).getText().trim().contains("snapwiz"))
                    {
                        suggestedNames.get(i).click();//Select the school
                        foundzipcode = true;
                        break;
                    }
                }
            }


            if(foundzipcode == false)
                Assert.fail("No school found to select from the suggested schools in the text field to enter the name of school");
            ReportUtil.log("Select existing school", "Existing school is selected", "pass");

        }
        catch (Exception e)
        {
            Assert.fail("Exception in app helper enterAndSelectSchool in class School",e);
        }
    }


}
