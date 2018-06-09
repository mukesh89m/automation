package com.snapwiz.selenium.tests.staf.dummies.testcases.IT9.R3;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.dummies.apphelper.CheckShareWithOptionWithTextBoxSubmitCancelButton;
import com.snapwiz.selenium.tests.staf.dummies.apphelper.DirectLogin;
import com.snapwiz.selenium.tests.staf.dummies.apphelper.Logout;
import com.snapwiz.selenium.tests.staf.dummies.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.dummies.apphelper.PostLinkFileTextPresent;
import com.snapwiz.selenium.tests.staf.dummies.apphelper.PostMessage;
import com.snapwiz.selenium.tests.staf.dummies.apphelper.PostMessageValidate;
import com.snapwiz.selenium.tests.staf.dummies.apphelper.RandomString;
import com.snapwiz.selenium.tests.staf.dummies.apphelper.UserCreate;
import com.snapwiz.selenium.tests.staf.dummies.Driver;
import com.snapwiz.selenium.tests.staf.dummies.ReadTestData;


public class LSShareWithOptionAdditional {

	private static Logger logger = Logger.getLogger("com.snapwiz.selenium.tests.staf.dummies.testcases.LSShareWithOptionAdditional");

	@Test(priority = 1, enabled = true)
	public void CheckShareBoxPostLinkFileTabs()
	{
		try
		{
			Driver.startDriver();
			new UserCreate().CreateStudent("a", "");//create student
			new DirectLogin().studentLogin("a");
			new Navigator().NavigateTo("Course Stream");
			Driver.driver.findElement(By.linkText("Post")).click();
			new CheckShareWithOptionWithTextBoxSubmitCancelButton().ShareWithOptionWithTextBoxSubmitCancelButton("97");
			new PostLinkFileTextPresent().postLinkFileTextPresent();
		}
		catch(Exception e)
		{
			Assert.fail("Exception TestCase CheckShareBoxPostLinkFileTabs in class LSShareWithOptionAdditional",e);
		}

	}

	@Test(priority = 2, enabled = true)
	public void ShareBoxClassSectionNameByDefault() 
	{
		try
		{ 
			Driver.startDriver();
			new UserCreate().CreateStudent("a", "");//create student
			new DirectLogin().studentLogin("a");
			new Navigator().NavigateTo("Course Stream");
			Driver.driver.findElement(By.linkText("Post")).click();
			if(Driver.driver.findElement(By.className("item-text")).getText() == null)  			
				Assert.fail("Class Section Name not found by default in Share TextBox");		

		}
		catch(Exception e)
		{
			Assert.fail("Exception TestCase ShareBoxClassSectionNameByDefault in class LSShareWithOptionAdditional",e);	
		}
	}
	@Test(priority = 3, enabled = true)
	public void AutoSuggestShareBox() 
	{
		try
		{		  
			Driver.startDriver();
			new UserCreate().CreateStudent("c", "");//create student
			new DirectLogin().studentLogin("c");
			new Logout().logout();
			
			new UserCreate().CreateStudent("d", "");//create student
			new DirectLogin().studentLogin("d");
			new Navigator().NavigateTo("Course Stream");
			String randomtext= new RandomString().randomstring(10);
			if(!new PostMessage().postMessageAndShare(randomtext, "", "c", "",""))
				Assert.fail("No Results Found. Auto Suggest feature of share box not working, we are unable to select the student.");

		}
		catch(Exception e)
		{
			Assert.fail("Exception in TestCase AutoSuggestShareBox in class LSShareWithOptionAdditional", e);
		}
	} 
	@Test(priority = 4, enabled = true)
	public void LSAutoSuggestNotDisplaySelectedName() 
	{
		try{

			Driver.startDriver();	
			new UserCreate().CreateStudent("a", "");//create student
			new DirectLogin().studentLogin("a");
			new Logout().logout();
            String methodName = new Exception().getStackTrace()[0].getMethodName();
			new UserCreate().CreateStudent("b", "");//create student
			new DirectLogin().studentLogin("b");
			new Navigator().NavigateTo("Course Stream");
			Driver.driver.findElement(By.linkText("Post")).click();	
			new PostMessage().postMessageWithshare(new RandomString().randomstring(20), "", "a", "","");
			Driver.driver.findElement(By.className("maininput")).click();
			Thread.sleep(2000);
			String shareName = methodName+"a";
			System.out.println("shareName: "+shareName);
			Driver.driver.findElement(By.className("maininput")).sendKeys(methodName);
			Thread.sleep(5000);
			List<WebElement> suggestname1 = Driver.driver.findElements(By.xpath("//*[starts-with(@id, 'share-with_feed')]/li"));
			for (WebElement answerchoice1: suggestname1)
			{    

				if(answerchoice1.getText().trim().equals(shareName))
				{
					Assert.fail("Already selected name is appearing in the autosuggestion");
				}
				else
				{
					logger.log(Level.INFO,"Already selected name is not appearing in the autosuggestion");
				}
			}

		}
		catch(Exception e)
		{
			Assert.fail("Exception in TestCase LSAutoSuggestNotDisplaySelectedName in class LSShareWithOptionAdditional", e);
		}
	} 


	@Test(priority = 5, enabled = true)
	public void RemoveDefaultClassSectionName()
	{
		try{


			Driver.startDriver();
			new UserCreate().CreateStudent("a", "");//create student
			new DirectLogin().studentLogin("a");
			new Navigator().NavigateTo("Course Stream");
			Driver.driver.findElement(By.linkText("Post")).click();
			Driver.driver.findElement(By.className("closebutton")).click();
			Thread.sleep(5000);
			String classsection = Driver.driver.findElement(By.className("holder")).getText();
			System.out.println("-->"+classsection);
			if(classsection.length() != 0)	  		
				Assert.fail("Default ClassSection is Present after removing it ");  		
		}
		catch(Exception e)
		{
			Assert.fail("Exception in TestCase RemoveDefaultClassSectionName in class LSShareWithOptionAdditional", e);
		}
	} 


	@Test(priority = 6, enabled = true)
	public void LSAutoSuggestStartsWithThreeAlphabets()
	{ 
		try
		{
			logger.log(Level.INFO,"Starting Execution of Testcase LSAutoSuggestStartsWithThreeAlphabets");
			Driver.startDriver();	
			new UserCreate().CreateStudent("a", "");//create student
			new DirectLogin().studentLogin("a");
			new Logout().logout();
			
			new UserCreate().CreateStudent("b", "");//create student
			new DirectLogin().studentLogin("b");
			String methodName = new Exception().getStackTrace()[0].getMethodName();
			String shareWithInitialStringSingle = methodName.substring(0, 1);
			String shareWithInitialStringDouble = methodName.substring(1, 2);
			String shareWithInitialStringTriple = methodName.substring(2, 3);

			String shareWithEnterFullString = methodName+"a";
			new Navigator().NavigateTo("Course Stream");
			Driver.driver.findElement(By.linkText("Post")).click();
			Driver.driver.findElement(By.className("maininput")).click();
			Thread.sleep(2000);
			String  defaultPrompt= Driver.driver.findElement(By.className("default")).getText();
			if(!defaultPrompt.trim().equals("(Enter Group or User Name)"))
				Assert.fail("Default Prompt '(Enter Group or User Name)' OnClick at ShareWith box is absent");

			Driver.driver.findElement(By.className("maininput")).sendKeys(shareWithInitialStringSingle);
			Thread.sleep(5000);
			String oneLetter = Driver.driver.findElement(By.className("error-message")).getText();

			if(!oneLetter.trim().equals("Enter at least 3 characters"))
				Assert.fail("Autosuggestion comes on entering one letter");

			Driver.driver.findElement(By.className("maininput")).sendKeys(shareWithInitialStringDouble);
			Thread.sleep(5000);
			String twoLetter = Driver.driver.findElement(By.className("error-message")).getText();

			if(twoLetter.trim().equals("Enter at least 3 characters"))
			{
				logger.log(Level.INFO,"Autosuggestion doesnt not come on entering two letter");
			}
			else 
			{
				Assert.fail("Autosuggestion comes on entering two letter");
			}
			Driver.driver.findElement(By.className("maininput")).sendKeys(shareWithInitialStringTriple);
			Thread.sleep(5000);
			WebElement moreLetter = Driver.driver.findElement(By.id("share-with_feed"));
			if(!moreLetter.getText().trim().equals("Enter at least 3 characters") || !moreLetter.getText().trim().equals("(Enter Group or User Name)"))
			{
				logger.log(Level.INFO,"Autosuggestion comes after 3 Letter input: Its OK");
			}
			else 
			{
				Assert.fail("Autosuggestion doesnt not come on entering three letter");
			}
			Thread.sleep(5000);
			Driver.driver.findElement(By.className("maininput")).clear();

			Driver.driver.findElement(By.className("maininput")).sendKeys(shareWithEnterFullString);
			Thread.sleep(5000);
			String fullName = Driver.driver.findElement(By.id("share-with_feed")).getText();
			if(fullName.trim().equals(shareWithEnterFullString.trim()))
			{

				logger.log(Level.INFO,"Auto suggest should display the name entered: Its OK");
			}
			else 
			{
				Assert.fail("Testcase LSAutoSuggestStartsWithThreeAlphabets Fail");
			}
		}
		catch(Exception e)
		{
			Assert.fail("Exception in TestCase LSAutoSuggestStartsWithThreeAlphabets in class LSShareWithOptionAdditional", e);
		}
	}

//marking this method as false there is no provision to create to a new class section, so unable to check this method
	@Test(priority = 7, enabled = false)
	public void AutoSuggestConsideringCurrentClassSection()
	{
		try{

			Driver.startDriver();
			String shareWithClassSectionString = ReadTestData.readDataByTagName("", "shareWithClassSectionString", "161");
			String shareWithInitialString = ReadTestData.readDataByTagName("", "shareWithInitialString", "161");
			String FirstNameInClassSection = ReadTestData.readDataByTagName("", "FirstNameInClassSection", "161");
			String SeccondNameInClassSection = ReadTestData.readDataByTagName("", "SeccondNameInClassSection", "161");
			String ThirdNameInClassSection = ReadTestData.readDataByTagName("", "ThirdNameInClassSection", "161");
			String defaultClassSectionName = ReadTestData.readDataByTagName("", "defaultClassSectionName", "161");
            new UserCreate().CreateStudent("bb", "");//create student
            new DirectLogin().studentLogin("bb");
			/*new LoginUsingLTI().ltiLogin("103");
			new LoginUsingLTI().ltiLogin("104");
			new LoginUsingLTI().ltiLogin("105");
			new LoginUsingLTI().ltiLogin("161");*/
			new Navigator().NavigateTo("Course Stream");
			Driver.driver.findElement(By.linkText("Post")).click();

			Driver.driver.findElement(By.className("closebutton")).click();
			Driver.driver.findElement(By.className("maininput")).sendKeys(shareWithClassSectionString);
			Thread.sleep(3000);
			String classSection = Driver.driver.findElement(By.id("share-with_feed")).getText();
			System.out.println(classSection);
			Driver.driver.findElement(By.className("maininput")).clear();
			Driver.driver.findElement(By.className("maininput")).sendKeys(shareWithInitialString);	
			List<String> stringarray = new ArrayList<String>();
			List<WebElement> suggestname = Driver.driver.findElements(By.xpath("//*[starts-with(@rel, 'uid_')]")); 

			for (WebElement answerchoice: suggestname)
			{   
				stringarray.add(answerchoice.getText());
			}
			System.out.println(stringarray);
			if(classSection.trim().equals(defaultClassSectionName.trim()) && stringarray.get(0).equals(FirstNameInClassSection.trim()) && stringarray.get(1).equals(SeccondNameInClassSection.trim()) && stringarray.get(2).equals(ThirdNameInClassSection.trim()))
			{
				logger.log(Level.INFO,"Autosuggest comes considering the current class section");
			}
			else
			{
				Assert.fail("Autosuggest doesnt come considering the current class section");
			}

		}
		catch(Exception e)
		{
			Assert.fail("Exception in TestCase AutoSuggestConsideringCurrentClassSection in class LSShareWithOptionAdditional", e);
		}
	}


	@Test(priority = 8, enabled = true)
	public void SharePostWithStudentOrInstructorOrClassSection()
	{
		try{

			Driver.startDriver();
			new UserCreate().CreateStudent("a", "");//create student
			new DirectLogin().studentLogin("a");
			new Logout().logout();
			
			new UserCreate().CreateStudent("b", "");//create student
			new DirectLogin().studentLogin("b");
			new Navigator().NavigateTo("Course Stream");
			String str = new RandomString().randomstring(5);
			new PostMessage().postmessage(str);
			if(!new PostMessageValidate().postMessageValidate(str))
				Assert.fail("Post not found");
		}
		catch(Exception e)
		{
			Assert.fail("Exception in TestCase SharePostWithStudentOrInstructorOrClassSection in class LSShareWithOptionAdditional", e);
		}
	}
	@AfterMethod
	public void tearDown() throws Exception
    {
		Driver.driver.quit();
	}
}
