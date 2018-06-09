package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT9.R3;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;


/*
 * R3.1020
 * 141-
 * Verify the auto-suggest while typing in the "share with" box
 */
public class LSAutoSuggestShareBox extends Driver {
	
		  private static Logger logger = Logger.getLogger("com.snapwiz.selenium.tests.staf.learningspaces.testcases.LSAutoSuggestShareBox");
	 
	  @Test(priority = 1,enabled = true)
	  public void AutoSuggestShareBox() 
	  {
		  try
		  {
			  String studentnametext =  ReadTestData.readDataByTagName("LSAutoSuggestShareBox", "studentname", "141");
			  String instructor =  ReadTestData.readDataByTagName("LSAutoSuggestShareBox", "instructor", "141");
			  logger.log(Level.INFO,"Starting Execution of Testcase");	

              new LoginUsingLTI().ltiLogin("141instructor");
              new LoginUsingLTI().ltiLogin("141student");
			  new LoginUsingLTI().ltiLogin("141");
			  new Navigator().NavigateTo("Course Stream");
			  driver.findElement(By.linkText("Post")).click();
			  driver.findElement(By.cssSelector("a[class='closebutton']")).click();
			  WebElement textbox = driver.findElement(By.className("maininput"));
			  textbox.sendKeys(studentnametext);
              Thread.sleep(5000);
			  WebElement studentname = driver.findElement(By.cssSelector("ul[id='share-with_feed']"));
			  System.out.println("--"+studentname.getText());

			  driver.findElement(By.className("maininput")).clear();
			  driver.findElement(By.className("maininput")).sendKeys(instructor);
              Thread.sleep(5000);
			  WebElement instructorname = driver.findElement(By.cssSelector("ul[id='share-with_feed']"));
			  System.out.println("----"+instructorname.getText());
			  if(studentname.getText().trim().equals("") || instructorname.getText().trim().equals("") )			  {
				  logger.log(Level.INFO,"share with box not suggest student or group and instructor name");
				  Assert.fail("share with box does not suggest student or group or instructor name");
			  }
			  else
			  {
				  logger.log(Level.INFO,"share with box  suggest student or instructor name");
				  
			  }
		  }
		  catch(Exception e)
		  {
			  	logger.log(Level.SEVERE,"Exception  in testcase LSAutoSuggestShareBox");
			  	Assert.fail("Exception  in testcase LSAutoSuggestShareBox",e);
		  }
	  }
    @Test(priority = 2,enabled = true)
    public void studentSharePostWithMentor()
    {
        try
        {

            new LoginUsingLTI().ltiLogin("141mentor");
            new LoginUsingLTI().ltiLogin("141");
            new Navigator().NavigateTo("Course Stream");
            String postMessage = new RandomString().randomstring(10);
            new PostMessage().postMessageAndShare(postMessage,"141mentorfirst","studentnametag","141mentor","false");
            new LoginUsingLTI().ltiLogin("141mentor");
            new Navigator().NavigateTo("Course Stream");
            if(new PostMessageValidate().postMessageValidate(postMessage) == false)
                Assert.fail("The message posted by student which is shared to mentor is not visible in mentor CS");

        }
        catch (Exception e)
        {
            Assert.fail("Exception in testcase studentSharePostWithMentor in class LSAutoSuggestShareBox",e);
        }
    }

    @Test(priority = 3,enabled = true)
    public void studentShareLinkWithMentor()
    {
        try
        {

            new LoginUsingLTI().ltiLogin("141mentor");
            new LoginUsingLTI().ltiLogin("141");
            new Navigator().NavigateTo("Course Stream");
            new PostMessage().postLinkAndShare("http://www.google.com", "141mentorfirst", "studentnametag", "141mentor");
            new LoginUsingLTI().ltiLogin("141mentor");
            new Navigator().NavigateTo("Course Stream");
            List<WebElement> allSharedElements = driver.findElements(By.className("ls-stream-share__title"));
            if(!(allSharedElements.get(0).getText().contains("http://www.google.com")))
                Assert.fail("The link posted by student which is shared to mentor is not visible in mentor CS");

        }
        catch (Exception e)
        {
            Assert.fail("Exeception in testcase studentSharePostWithMentor in class LSAutoSuggestShareBox",e);
        }
    }

    @Test(priority = 4,enabled = true)
    public void studentShareFileWithMentor()
    {
        try
        {

            new LoginUsingLTI().ltiLogin("141mentor");
            new LoginUsingLTI().ltiLogin("141");
            new Navigator().NavigateTo("Course Stream");
            new FileUpload().fileUploadAndShare("141mentorfirst", "studentnametag", "false","141mentor");
            new LoginUsingLTI().ltiLogin("141mentor");
            new Navigator().NavigateTo("Course Stream");
            if((new FileUpload().validateFilePosted("img.png")) == false)
                Assert.fail("The file posted by student which is shared to mentor is not visible in mentor CS");
        }
        catch (Exception e)
        {
            Assert.fail("Exeception in testcase studentSharePostWithMentor in class LSAutoSuggestShareBox",e);
        }
    }

    @Test(priority = 5,enabled = true)
    public void classSectionOtherThanCurrentClassSection()
    {
        try {

            new LoginUsingLTI().ltiLogin("141newclasssection");
            new LoginUsingLTI().ltiLogin("141");
            new Navigator().NavigateTo("Course Stream");
            driver.findElement(By.linkText("Post")).click();
            driver.findElement(By.cssSelector("a[class='closebutton']")).click();
            driver.findElement(By.className("maininput")).sendKeys("141new");
            Thread.sleep(5000);
            System.out.println("---"+driver.findElement(By.className("no-results-message")).getText());
            if(!(driver.findElement(By.className("no-results-message")).getText().equals("No results found")))
                Assert.fail("Student is able to share the post with another class section of which he is not a part of");


            driver.findElement(By.linkText("Link")).click();
            driver.findElement(By.cssSelector("a[class='closebutton']")).click();
            driver.findElement(By.className("maininput")).sendKeys("141new");
            Thread.sleep(5000);
            System.out.println("---"+driver.findElement(By.className("no-results-message")).getText());
            if(!(driver.findElement(By.className("no-results-message")).getText().equals("No results found")))
                Assert.fail("Student is able to share the link with another class section of which he is not a part of");

            driver.findElement(By.linkText("File")).click();
            driver.findElement(By.cssSelector("a[class='closebutton']")).click();
            driver.findElement(By.className("maininput")).sendKeys("141new");
            Thread.sleep(5000);
            System.out.println("---"+driver.findElement(By.className("no-results-message")).getText());
            if(!(driver.findElement(By.className("no-results-message")).getText().equals("No results found")))
                Assert.fail("Student is able to share the link with another class section of which he is not a part of");
        }
        catch (Exception e)
        {
            Assert.fail("Exeception in testcase classSectionOtherThanCurrentClassSection in class LSAutoSuggestShareBox",e);
        }

    }


	  


}
