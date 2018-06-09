package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT10.R14;

import java.util.ArrayList;
import java.util.List;

import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.Click;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.RandomString;

public class ResourceAssignmentByInstructor extends Driver
{
    @Test
    public void resourceAssignmentByInstructor()
    {
        try
        {
            new LoginUsingLTI().ltiLogin("1895");
            new Navigator().NavigateTo("Resources");
            String tab1 = driver.findElement(By.cssSelector("div[class='tab active']")).getText();
            if(!tab1.equals("All Resources"))
            {
                Assert.fail("On selecting 'Resources' all resource page is not displayed ");
            }
            List<String> stringarray = new ArrayList<String>();
            //Listing  the all Resources element
            List<WebElement> allResources =  driver.findElements(By.cssSelector("span[class='resource-title']"));
            for(WebElement elements : allResources)
            {
                stringarray.add(elements.getAttribute("type"));
            }
            String [] resourceArray = stringarray.toArray(new String[stringarray.size()]);
            for (int i = 0; i < resourceArray.length; i++)
            {

                if (resourceArray[i].equals("ASSESSMENT") || resourceArray[i].equals("RESOURCE"))
                {

                }
                else
                {
                    Assert.fail("Resource type is neither ASSESSMENT nor RESOURCE");
                }
            }
            String tab = driver.findElement(By.cssSelector("div[class='tab active']")).getText();
            if(!tab.equals("All Resources"))
            {
                Assert.fail("On selecting 'Resources' all resource page is not displayed ");
            }
            //Checking for textbox in 'All Resource' tab
            int textbox = driver.findElements(By.cssSelector("textarea[id='all-resource-search-textarea']")).size();
            if(textbox != 1)
            {
                Assert.fail("Textbox for search is absent");
            }
            //Checking for search Icon in 'All Resource' tab
            int searchIcon = driver.findElements(By.cssSelector("div[id='all-resource-search-button']")).size();
            if(searchIcon != 1)
            {
                Assert.fail("Search Icon for search is absent");
            }
            //Checking for filter option in 'All Resource' tab
            List<WebElement> allfilter =  driver.findElements(By.cssSelector("span[class='hide-filter-text']"));
            if(!allfilter.get(1).getText().equals("- Hide Filters"))
            {
                Assert.fail("Filter option is absent");
            }
            stringarray.clear();

            //checking if 'Close' button is present or not in 'All Resource' tab
            boolean closetab = driver.findElement(By.className("close_tab")).isDisplayed();
            if(closetab)
            {
                Assert.fail("For 'All Resource' has option to close the tab");
            }
            //Going to Assignments and again selecting Resources
            new Navigator().NavigateTo("Assignments");
            driver.findElement(By.cssSelector("span.ins-assignment-button-sprite.instructor-assignment-new")).click();
            new Click().clickBycssselector("div[class='ls-inst-dashboard-assignment-popup-button ls--question-banks-view']");

            //Find the deafult text in the text box
            String str = driver.findElement(By.id("all-resource-search-textarea")).getAttribute("placeholder");
            if(!str.equals("Search Question Banks..."))
            {
                Assert.fail("Default text present is not correct");
            }
            String randomStr = new RandomString().randomstring(5);
            driver.findElement(By.id("all-resource-search-textarea")).clear();
            Thread.sleep(3000);
            driver.findElement(By.id("all-resource-search-textarea")).sendKeys("\""+randomStr+"\"");
            driver.findElement(By.cssSelector("#all-resource-search-button > span.ins-resource-search-sprite.instructor-resource-search-img")).click();
            Thread.sleep(3000);
            //Finding  the error message element
            List<WebElement> allErrorMessage =  driver.findElements(By.cssSelector("span[class='al-performace-report-sidebar-notification-text ls-ins-no-resources-found-message']"));
            String errormessage = allErrorMessage.get(1).getText().replaceAll("\n", " ");
            System.out.println("errormessage: "+errormessage);
            if(!errormessage.contains("Unfortunately, there are no resources available. You should either change the"))
            {
                Assert.fail("Error message doesnt come on searching a random text");
            }
        }
        catch(Exception e)
        {
            Assert.fail("Exception in resourceAssignmentByInstructor in class ResourceAssignmentByInstructor",e);
        }
    }

}
