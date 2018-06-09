package com.snapwiz.selenium.tests.staf.microlearning.apphelper;

import com.snapwiz.selenium.tests.staf.microlearning.stepdefinitions.CucumberDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

/**
 * Created by root on 9/8/16.
 */
public class FilterHelper{
    String filterName;
    public int getFilterCount(String filterName){
        int filterCount = 0;
        this. filterName = filterName.substring(0, 1).toLowerCase() + filterName.substring(1);
        try{
            WebDriver driver = CucumberDriver.getWebDriver();
            filterCount = Integer.parseInt(driver.findElement(By.xpath("//li[starts-with(normalize-space(@class),'ml-lesson-filter-btn dl-filter-"+this.filterName+" js-lesson-filter-btn white-bg')]//span[@class='ml-filter-count']")).getText());
        }catch(Exception e){
            Assert.fail("Exception in the test method in the class SignUp in the apphelper 'registerUser'", e);
        }
        return filterCount;
    }
}
