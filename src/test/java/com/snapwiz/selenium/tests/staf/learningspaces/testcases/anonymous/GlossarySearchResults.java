package com.snapwiz.selenium.tests.staf.learningspaces.testcases.anonymous;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.util.logging.Level;
import java.util.logging.Logger;



public class GlossarySearchResults extends Driver{


    private static Logger logger=Logger.getLogger("com.snapwiz.selenium.tests.staf.learningspaces.testcases,GlossarySearchResults");
    /*
     * 788=800
     */
    @Test
    public void glossarySearchResults()
    {
        try
        {
            new LoginUsingLTI().ltiLogin("788");
            new Navigator().NavigateTo("eTextbook");
            driver.findElement(By.cssSelector("input[class='toc-sprite search-course-stream ui-autocomplete-input']")).click();
            driver.findElement(By.cssSelector("input[class='toc-sprite search-course-stream ui-autocomplete-input']")).sendKeys("Introduction");
            driver.findElement(By.cssSelector("i[class='search-icon']")).click();
            String glossaryText = driver.findElement(By.cssSelector("span[class='ls-result-view-title']")).getText();
            System.out.println("glossaryText: "+glossaryText);
            boolean startText = glossaryText.startsWith("Glossary");

            boolean descriptionText=driver.findElement(By.cssSelector("a[class='toc-auto-suggest-label']")).isDisplayed();

            boolean searchIcon= driver.findElement(By.cssSelector("i[class='ls-search-results-icons']")).isDisplayed();

            if(startText==true && descriptionText==true && searchIcon == true)
            {
                logger.log(Level.INFO,"Testcase GlossarySearchResults Pass");
            }
            else
            {
                logger.log(Level.INFO,"Testcase GlossarySearchResults Fail");
                Assert.fail("Search results are not displayed in e textbook.");
            }

        }

        catch (Exception e)
        {
            org.testng.Assert.fail("Exception in TC glossarySearchResults of class GlossarySearchResults", e);
        }

    }

}
