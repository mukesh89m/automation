package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT10.R14;

import java.util.List;

import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.Click;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.openqa.selenium.JavascriptExecutor;
import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;

public class SearchTextAreaOnTop extends Driver {

    @Test
    public void searchTextAreaOnTop()
    {
        try
        {
            new LoginUsingLTI().ltiLogin("1928");
            new Navigator().NavigateTo("Resources");
            List<WebElement> allfilter =  driver.findElements(By.id("view-resource-filter-button"));
            new Click().clickbylinkText("All Chapters");
            driver.findElement(By.xpath("//a[starts-with(@title, 'Ch 1:')]")).click(); //selecting first chapter
            //Clicking 'Hide Filter'
            allfilter.get(1).click();
            String str = driver.findElement(By.className("view-filter-text")).getText();
            if(str.contains("+"))
            {
                Assert.fail("Icon before view filters doesnot get updated to +.");
            }
            //Clicking 'View Filter'
            allfilter.get(1).click();
            //Inner html for filter section(Textarea+dropdown)
            WebElement WE = driver.findElement(By.className("ls-assessment-filter-section"));
            String fiterSection= (String) ((JavascriptExecutor)driver).executeScript("return arguments[0].innerHTML",WE);
            if(fiterSection.indexOf("all-resource-search-textarea") > fiterSection.indexOf("resource-dropdowns-wrapper"))
            {
                Assert.fail("Search  textarea is not coming before filter dropdowns.");
            }
            //List the dropdowns
            List<WebElement> allDropdowns = driver.findElements(By.xpath("//*[starts-with(@id, 'sbSelector_')]"));
            for(WebElement dropdown : allDropdowns)
            {
                System.out.println("dropdown: "+dropdown.getText());
            }
            if(!allDropdowns.get(4).getText().contains("Ch 1:"))
            {
                Assert.fail("Chapter selected before minimizing filters didnt remained selected.");
            }
            //clicked on another tab
            driver.findElement(By.cssSelector("span[title='My Resources']")).click();
            //come back to previous tab
            driver.findElement(By.cssSelector("span[title='All Resources']")).click();
            Thread.sleep(2000);
            //List the dropdowns
            List<WebElement> allElements1 = driver.findElements(By.xpath("//*[starts-with(@id, 'sbSelector_')]"));
            if(!allElements1.get(4).getText().contains("Ch 1:"))
            {
                Assert.fail("Chapter selected before going to another tab and coming back to present tab didnt remained selected.");
            }
            new Navigator().NavigateTo("Assignments");
            new Navigator().NavigateTo("Resources");
            // Listing all dropdowns
            List<WebElement> allDropdown = driver.findElements(By.xpath("//*[starts-with(@id, 'sbSelector_')]"));
            for(WebElement dropdown : allDropdown)
            {
                System.out.println("dropdown1: "+dropdown.getText());
            }
            String firstDropdown = allDropdown.get(4).getText();
            String secondDropdown = allDropdown.get(5).getText();
            if(!firstDropdown.equals("All Chapters") || !secondDropdown.equals("All Sections"))
            {
                Assert.fail("After Going to some other page and then coming back to 'Resource' page does not clear the filters.");
            }

        }
        catch(Exception e)
        {
            Assert.fail("Exception in searchTextAreaOnTop in class SearchTextAreaOnTop",e);
        }
    }
    
}
