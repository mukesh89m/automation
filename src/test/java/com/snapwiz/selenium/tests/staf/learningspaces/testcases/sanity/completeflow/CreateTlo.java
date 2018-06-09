package com.snapwiz.selenium.tests.staf.learningspaces.testcases.sanity.completeflow;

import com.snapwiz.selenium.tests.staf.framework.controller.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Assignment;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.DirectLogin;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.SelectCourse;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.ScrollElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.TestNG;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import org.testng.xml.XmlSuite;

import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

/**
 * Created by mukesh on 25/2/16.
 */
public class CreateTlo extends Driver {

    @Test
    public void createTlo(){
        try {
            WebDriver driver=Driver.getWebDriver();
            new DirectLogin().CMSLogin();
            driver.findElement(By.xpath("//*[@class='content-dropdown']/div/a")).click(); //click on the content dropdown
            driver.findElement(By.xpath("//a[@title='Taxonomy Management']")).click(); //click on the Taxonomy Management
            new SelectCourse().selectcourse();
            new ScrollElement().scrollBottomOfPage();
            driver.findElement(By.xpath("//div[@type='terminal-learning-objective']")).click(); //click on the terminal learning objective
            driver.findElement(By.xpath("//input[@class='terminal-skill-new']")).sendKeys("add tlo");
            driver.findElement(By.xpath("/html/body")).click();
            driver.findElement(By.xpath("//*[@class='content-dropdown']/div/a")).click(); //click on the content dropdown
            driver.findElement(By.xpath("//a[@title='Course Management']")).click(); //click on the Taxonomy Management



        } catch (Exception e) {
            Assert.fail("Exception in TC createTlo of class  CreateTlo",e);
        }

    }
    @Test
    public void createTlos() {
        try {
            WebDriver driver=Driver.getWebDriver();
            new DirectLogin().CMSLogin();
            driver.findElement(By.xpath("//*[@class='content-dropdown']/div/a")).click(); //click on the content dropdown
            driver.findElement(By.xpath("//a[@title='Taxonomy Management']")).click(); //click on the Taxonomy Management
            new SelectCourse().selectcourse();
            new ScrollElement().scrollBottomOfPage();
            driver.findElement(By.xpath("//div[@type='terminal-learning-objective']")).click(); //click on the terminal learning objective
            driver.findElement(By.xpath("//input[@class='terminal-skill-new']")).sendKeys("dadadadsf");
            driver.findElement(By.xpath("/html/body")).click();
            driver.findElement(By.xpath("//*[@class='content-dropdown']/div/a")).click(); //click on the content dropdown
            driver.findElement(By.xpath("//a[@title='Course Management']")).click(); //click on the Taxonomy Management
            new Assignment().create(1);


        } catch (Exception e) {
            Assert.fail("Exception in TC createTlo of class  CreateTlo", e);
        }
    }
    @Test
    public void getJsonData() {
        try {
            WebDriver driver=Driver.getWebDriver();
            URL obj = new URL("http://ec2-54-198-190-207.compute-1.amazonaws.com/");
            URLConnection conn = obj.openConnection();

            //get all headers
            Map<String, List<String>> map = conn.getHeaderFields();
            for (Map.Entry<String, List<String>> entry : map.entrySet()) {
                System.out.println("Key : " + entry.getKey() +
                        " ,Value : " + entry.getValue());
            }

            //get header by 'key'
            String server = conn.getHeaderField("Response");
            System.out.println(server);

        } catch (Exception e) {
            Assert.fail("Exception in TC createTlo of class  CreateTlo", e);
        }
    }
}
