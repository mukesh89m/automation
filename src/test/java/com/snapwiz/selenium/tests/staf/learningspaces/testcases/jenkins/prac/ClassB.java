package com.snapwiz.selenium.tests.staf.learningspaces.testcases.jenkins.prac;

import org.openqa.selenium.By;
import org.testng.annotations.*;

import java.net.MalformedURLException;


/**
 * Created by mukesh on 22/7/16.
 */
public class ClassB extends ConfigureDriver {

    @Test
    public void test_01() throws InterruptedException, MalformedURLException {
        try{

            getDriver().get("http://www.w3schools.com/");

            getDriver().findElement(By.xpath("html/body/div[2]/div/a[4]")).click();
            Thread.sleep(10000);
            getDriver().findElement(By.xpath("//*[@id='gsc-i-id1']")).sendKeys("test");
            Thread.sleep(5000);

        }
        catch(Exception e){
            System.out.println(e);
        }
    }

    @Test
    public void test_02() throws InterruptedException, MalformedURLException {
        try{
            getDriver().get("http://www.seleniumhq.org/download/");
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
}
