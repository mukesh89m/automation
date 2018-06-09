package com.snapwiz.selenium.tests.staf.learnon.apphelper;

import com.snapwiz.selenium.tests.staf.learnon.Driver;
import com.snapwiz.selenium.tests.staf.learnon.ReadTestData;
import com.snapwiz.selenium.tests.staf.learnon.uihelper.Click;
import com.snapwiz.selenium.tests.staf.learnon.uihelper.MouseHover;
import com.snapwiz.selenium.tests.staf.learnon.uihelper.UIElement;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.List;

public class Navigator 
{

	public void NavigateTo(String navigateTo)
	{
        try
        {
            new UIElement().waitAndFindElement(By.xpath(".//*[@class='ls-site-nav-drop-down']/a"));
            new Click().clickbyxpath(".//*[@class='ls-site-nav-drop-down']/a");//click on Navigator icon

            if(navigateTo.equals("My Journal")) navigateTo = "My Notes";
            if(navigateTo.equals("Learning Content")) navigateTo = "e-Textbook";
            if(navigateTo.equals("eTextbook")) navigateTo = "e-Textbook";
            if(navigateTo.equals("eTextBook")) navigateTo = "e-Textbook";
            if(navigateTo.equals("e-Textbook")) navigateTo = "Course";


            if(navigateTo.equals("Current Assignments") || navigateTo.equals("My Question Bank") || navigateTo.equals("Policies") || navigateTo.equals("Summary") || navigateTo.equals("New Assignment")) {
                new UIElement().waitAndFindElement(By.cssSelector("span[data-localize= 'Assignments']"));
                Driver.driver.findElement(By.linkText("Assignments")).click();//click on Assignments
                Thread.sleep(2000);
                if(navigateTo.equals("Summary")){
                    navigateTo = "Current Assignments";
                }
            }
            boolean questionBank = false;
            if(navigateTo.equals("Question Banks")){
                Driver.driver.findElement(By.linkText("Assignments")).click();//click on Assignments
                navigateTo = "My Question Bank";
                questionBank = true;

            }

            if(navigateTo.equals("Assignment Policies"))
            {
                Driver.driver.findElement(By.linkText("Assignments")).click();//click on Assignments
                navigateTo = "Policies";
            }

            if(navigateTo.equals("Assignments")) {
                if (Driver.driver.getCurrentUrl().contains("learningSpaceInstructorDashboard")) {
                    Driver.driver.findElement(By.linkText("Assignments")).click();//click on Assignments
                    navigateTo = "Current Assignments";
                }
            }

            if(navigateTo.equals("Proficiency Report") || navigateTo.equals("Metacognitive Report")
                    || navigateTo.equals("Productivity Report") || navigateTo.equals("Most Challenging Activities Report") || navigateTo.equals("Engagement Report")
                    || navigateTo.equals("Performance Report") || navigateTo.equals("Most Challenging Chapters Report")){
                Driver.driver.findElement(By.linkText("My Reports")).click();//click on My Reports
            }

            if(navigateTo.equals("All Resources") || navigateTo.equals("My Resources")){
                Driver.driver.findElement(By.linkText("Resources")).click();//click on Resources
            }
            if(navigateTo.equals("Resources")){
                Driver.driver.findElement(By.linkText("Resources")).click();//click on Resources
                navigateTo = "All Resources";
            }


            ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.linkText(navigateTo)));

            Thread.sleep(3000);
            if(navigateTo.equals("e-Textbook"))
            {
                new WebDriverWait(Driver.driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.className("chapter-heading-label")));

            }
            if(questionBank){
                new Click().clickBycssselector("div[data-id='all-resources']");//click on Question Banks tab
            }
            if(navigateTo.equals("Learning Content"))
            {
                int helppage = Driver.driver.findElements(By.className("close-help-page")).size();
                if(helppage == 1)
                    Driver.driver.findElement(By.className("close-help-page")).click();
            }
            if(navigateTo.equals("Instructor Resources"))
            {
                if(Driver.driver.findElement(By.cssSelector("div[class='tab active']")).getText().equals("My Resources"))
                {//Opening All Resources tab if not opened after clicking on New Assignment button
                    Driver.driver.findElement(By.cssSelector("span[title='All Resources']")).click();
                }
            }


        }
		catch(Exception e)
		{
			Assert.fail("Exception in NavigateTo in AppHelper Navigator",e);
		}
	}
	
	public void InstructorNavigateTo(String navigateTo)
	{
		try
		{

			 String streamvalue=Driver.driver.findElement(By.xpath("/html/body/header/div/div/div/ul/li/a")).getText();
		     
			 if(!streamvalue.trim().equals(navigateTo))
		     {
		    	
			     Actions action = new Actions(Driver.driver);
			     WebElement we = Driver.driver.findElement(By.xpath("/html/body/header/div/div/div/ul/li/a"));
			     action.moveToElement(we).build().perform();
			     Driver.driver.findElement(By.linkText(navigateTo)).click();
			     if(navigateTo.equals("eTextbook"))
					{
					int helppage = Driver.driver.findElements(By.className("close-help-page")).size(); 
				     if(helppage == 1)
				    	 Driver.driver.findElement(By.className("close-help-page")).click();
					}
			     Thread.sleep(5000);
			    
		     }
		     else
		     {		    	
		    	 
		     }
		}
		catch(Exception e)
		{
			Assert.fail("Exception in InstructorNavigateTo in AppHelper class Navigator", e);
		}
	}
	public void navigateToResourceTab()
	{
		try
		{
			((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("span[title='Resources']")));
			Thread.sleep(2000);
		}
		catch(Exception e)
		{
			Assert.fail("Exception in navigateToResourceTab in AppHelper class Navigator", e);
		}
	}
	public void openResourceFromResourceTab(int dataIndex)
	{
		try
		{
            String resoursename = ReadTestData.readDataByTagName("", "resoursename", Integer.toString(dataIndex));
            System.out.println("resoursename: "+resoursename);
            navigateToResourceTab();
            int resindex = 0;
            List<WebElement> resources = Driver.driver.findElements(By.cssSelector("div[class='ls-right-section-status ls-right-section-resource']"));
            for(WebElement res : resources)
            {
                System.out.println(res.getText());
                if(res.getText().contains(resoursename))
                {
                    break;
                }
                resindex++;
            }
          //click on resource
			List<WebElement> allArrowIcon = Driver.driver.findElements(By.className("ls-resource-show-assign-this-block"));
			((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", allArrowIcon.get(resindex));	//click on arrow icon
			Thread.sleep(2000);
			//click on resource
			List<WebElement> allOpenLink = Driver.driver.findElements(By.xpath("//*[@class='resource-content-posts-list']/div[3]/a[2]/span"));
			((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", allOpenLink.get(resindex));
			Thread.sleep(2000);
			Driver.driver.findElement(By.cssSelector("div.ls-main_aside > div.tabs > div.tab.active")).click();
           /* zerobasedindex+=1;
            Driver.driver.findElement(By.xpath("//*[@id='resource-content-posts-scrollbar']/div[2]/div/ul/li["+Integer.toString(zerobasedindex)+"]/div[3]/a[2]/span")).click();
			Thread.sleep(2000);*/
		}
		catch(Exception e)
		{
			Assert.fail("Exception in openResourceFromResourceTab in AppHelper class Navigator", e);
		}
	}
	
	public void clickOnJumpOutIcon()
	{
		try
		{
			MouseHover.mouserhover("my-journal-activity-details");
			Thread.sleep(2000);
			((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("span[class='my-journal-media-popout-icon card-icons']")));//click on jump out icon
			Thread.sleep(2000);
		}
		catch(Exception e)
		{
			Assert.fail("Exception in clickOnJumpOutIcon in AppHelper class Navigator", e);
		}
	}
	//@Author Sumit
	//to navigate to tabs present in RHS, pass the tab name as a string to navigate
	public void navigateToTab(String tabName)
	{
		try
		{
			if(tabName.equals("Fav") || tabName.equals("Favorite"))
            ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("div[data-id='fav']")));
            else
			((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("span[title='"+tabName+"']")));
			Thread.sleep(2000);
		}
		catch(Exception e)
		{
			Assert.fail("Exception in navigateToResourceTab in AppHelper class Navigator", e);
		}
	}
	//@Author Sumit
	//to navigate to a page from profile drop down present on top right corner for LS+Adaptive course
	public void navigateFromProfileDropDown(String pageName)
	{
		try
		{
			Driver.driver.findElement(By.className("ls-user-nav__username")).click();	//click on profile dropdown
			Thread.sleep(2000);
			Driver.driver.findElement(By.linkText(pageName)).click();//click on page that you want to navigate 
			Thread.sleep(2000);
		}
		catch(Exception e)
		{
			Assert.fail("Exception in navigateFromProfileDropDown in AppHelper class Navigator", e);
		}
	}
	//@Author Sumit
		//to navigate to a page from profile drop down present on top right corner
		public void navigatorElements(String pageName)
		{
			try
			{
				Driver.driver.findElement(By.className("ls-user-nav__username")).click();	//click on profile dropdown
				Thread.sleep(2000);
				Driver.driver.findElement(By.linkText(pageName)).click();//click on page that you want to navigate 
				Thread.sleep(2000);
			}
			catch(Exception e)
			{
				Assert.fail("Exception in navigatorElements in AppHelper class Navigator", e);
			}
		}
    //@Author Sumit--modify brajesh
    //to navigate to a page from profile drop down present on top right corner for Orion course
    public void navigateFromProfileDropDownForOrion(String pageName)
    {
        try
        {
          /* new Click().clickByclassname("idb-user-profile-click-wrapper");	//click on profile dropdown
            Thread.sleep(2000);
            Driver.driver.findElement(By.cssSelector("li[title='"+pageName+"']")).click();//click on page that you want to navigate
            Thread.sleep(2000);*/
            new Click().clickBycssselector("li[title='"+pageName+"']");
        }
        catch(Exception e)
        {
            Assert.fail("Exception in navigateFromProfileDropDownForOrion in AppHelper class Navigator", e);
        }
    }
  /*
   *   @Brajesh
   *   navigate to dashboard orion
   */
    public void orionDashboard()
	{
		try
		{
		
		Driver.driver.findElement(By.cssSelector("img[title='ORION Dashboard']")).click();
		Thread.sleep(3000);
		}
		catch(Exception e)
		{
			//new Screenshot().captureScreenshotFromAppHelper();
			Assert.fail("Exception in orionDashboard in AppHelper Navigator",e);
		}
	}

    /*
       @Author: Sumit on 18/08/2014
       This apphelper will open the 1st resource from the resource tab
     */
    public void openFirstResourceFromResourceTab(int dataIndex)
    {
        try
        {
            String resoursename = ReadTestData.readDataByTagName("", "resoursename", Integer.toString(dataIndex));
            navigateToResourceTab();
            ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.className("ls-resource-show-assign-this-block")));	//click on arrow icon for first resource
            Thread.sleep(2000);
            ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.xpath("//*[@class='resource-content-posts-list']/div[3]/a[2]/span")));//click on open link
            Thread.sleep(2000);
        }
        catch(Exception e)
        {
            Assert.fail("Exception in openFirstResourceFromResourceTab in AppHelper class Navigator", e);
        }
    }

    public void myLibraryTab()
    {
        try {

            new Navigator().NavigateTo("Question Banks");
            new Click().clickBycssselector("div[data-id='my-resources']");

        }

        catch(Exception e)
        {
            Assert.fail("Exception in myLibraryTab in AppHelper class Navigator", e);
        }

    }
}
