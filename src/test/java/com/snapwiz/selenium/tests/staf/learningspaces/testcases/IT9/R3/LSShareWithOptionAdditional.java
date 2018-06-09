package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT9.R3;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.CheckShareWithOptionWithTextBoxSubmitCancelButton;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.PostLinkFileTextPresent;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.PostMessage;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.PostMessageValidate;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.RandomString;
import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;


public class LSShareWithOptionAdditional extends Driver{
	
private static Logger logger = Logger.getLogger("com.snapwiz.selenium.tests.staf.learningspaces.testcases.LSShareWithOptionAdditional");

@Test(priority=1, enabled=true)
public void CheckShareBoxPostLinkFileTabs()
{
	  try
	  {

	  new LoginUsingLTI().ltiLogin("97");
	  new Navigator().NavigateTo("Course Stream");
	  driver.findElement(By.linkText("Post")).click();
	  new CheckShareWithOptionWithTextBoxSubmitCancelButton().ShareWithOptionWithTextBoxSubmitCancelButton("97");
	  new PostLinkFileTextPresent().postLinkFileTextPresent();
	  }
	  catch(Exception e)
	  {
		  Assert.fail("Exception TestCase CheckShareBoxPostLinkFileTabs in class LSShareWithOptionAdditional",e);
	  }
	  
}

@Test(priority=2, enabled=true)
public void ShareBoxClassSectionNameByDefault() 
{
  try
  { 

	  new LoginUsingLTI().ltiLogin("100");
	  new Navigator().NavigateTo("Course Stream");
	  driver.findElement(By.linkText("Post")).click();
	  String defaultClassSection = ReadTestData.readDataByTagName("", "defaultClassSection", "100");
	  	if(!driver.findElement(By.className("item-text")).getText().trim().equals(defaultClassSection))
	  			Assert.fail("Class Section Name not found by default in Share TextBox");		
	  
  	}
  catch(Exception e)
  {
	  Assert.fail("Exception TestCase ShareBoxClassSectionNameByDefault in class LSShareWithOptionAdditional",e);	
  }
}
@Test(priority=3, enabled=true)
public void AutoSuggestShareBox() 
{
	  try
	  {		  

		  new LoginUsingLTI().ltiLogin("105");
		  new LoginUsingLTI().ltiLogin("110");
		  new Navigator().NavigateTo("Course Stream");
		  String shareWithInitialString = ReadTestData.readDataByTagName("", "shareWithInitialString", "110");
		  String randomtext= new RandomString().randomstring(10);
		  if(!new PostMessage().postMessageAndShare(randomtext, shareWithInitialString, "studentnametag", "110",""))
			  Assert.fail("No Results Found. Auto Suggest feature of share box not working, we are unable to select the student.");
	  	
	  }
	  catch(Exception e)
	  {
		  Assert.fail("Exception in TestCase AutoSuggestShareBox in class LSShareWithOptionAdditional", e);
	  }
} 
	@Test(priority=4, enabled=true)
	public void LSAutoSuggestNotDisplaySelectedName() 
	  {
		try{
		

			new LoginUsingLTI().ltiLogin("105");
			new LoginUsingLTI().ltiLogin("113");
			new Navigator().NavigateTo("Course Stream");
			driver.findElement(By.linkText("Post")).click();
			String shareWithInitialString = ReadTestData.readDataByTagName("", "shareWithInitialString", "113");
			String studentnametag1 = ReadTestData.readDataByTagName("", "studentnametag1", "113");
			new PostMessage().postMessageAndShare(new RandomString().randomstring(20), shareWithInitialString, "studentnametag", "113","");
			driver.findElement(By.linkText("Post")).click();
			driver.findElement(By.className("maininput")).click();
			Thread.sleep(2000);
			driver.findElement(By.className("maininput")).sendKeys(shareWithInitialString);
			Thread.sleep(5000);
			List<WebElement> suggestname1 = driver.findElements(By.xpath("//*[starts-with(@rel, 'uid_')]"));
			   for (WebElement answerchoice1: suggestname1.subList(1, suggestname1.size()))
			   {    
			    
			    if(answerchoice1.getText().trim().equals(studentnametag1))
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
			e.printStackTrace();
			Assert.fail("Exception in TestCase LSAutoSuggestNotDisplaySelectedName in class LSShareWithOptionAdditional", e);
		}
	  } 
	 
	
@Test(priority=5, enabled=true)
public void RemoveDefaultClassSectionName()
{
	try{
		
		

		new LoginUsingLTI().ltiLogin("136");
		new Navigator().NavigateTo("Course Stream");
		driver.findElement(By.linkText("Post")).click();
		String defaultClasssection = ReadTestData.readDataByTagName("", "defaultClasssection", "136");
		driver.findElement(By.className("closebutton")).click();
		Thread.sleep(5000);
		String classsection = driver.findElement(By.className("holder")).getText();
	  	if(classsection.trim().equals(defaultClasssection))	  		
	  			Assert.fail("Default ClassSection is Present after removing it ");  		
	  
	}
	catch(Exception e)
	{
		e.printStackTrace();
		Assert.fail("Exception in TestCase RemoveDefaultClassSectionName in class LSShareWithOptionAdditional", e);
	}
	  
} 


@Test(priority=6, enabled=true)
public void LSAutoSuggestStartsWithThreeAlphabets()
{ 
	try
	{
		logger.log(Level.INFO,"Starting Execution of Testcase LSAutoSuggestStartsWithThreeAlphabets");

		new LoginUsingLTI().ltiLogin("105");
		new LoginUsingLTI().ltiLogin("137");
		String shareWithInitialStringSingle = ReadTestData.readDataByTagName("", "shareWithInitialStringSingle", "137");
		String shareWithInitialStringDouble = ReadTestData.readDataByTagName("", "shareWithInitialStringDouble", "137");
		String shareWithInitialStringTriple = ReadTestData.readDataByTagName("", "shareWithInitialStringTriple", "137");
        String enterFullString = ReadTestData.readDataByTagName("", "shareWithEnterFullString", "137");
		String shareWithEnterFullString = ReadTestData.readDataByTagName("", "shareWithEnterFullString", "137");
        String y[]=shareWithEnterFullString.split(" ");
        shareWithEnterFullString = y[1] + ", " + y[0];//reverse the name with comma in between
        new Navigator().NavigateTo("Course Stream");
		driver.findElement(By.linkText("Post")).click();
		driver.findElement(By.className("maininput")).click();
		Thread.sleep(2000);
		String  defaultPrompt= driver.findElement(By.className("default")).getText();
		if(!defaultPrompt.trim().equals("(Enter Group or User Name)"))
			Assert.fail("Default Prompt '(Enter Group or User Name)' OnClick at ShareWith box is absent");
		
		driver.findElement(By.className("maininput")).sendKeys(shareWithInitialStringSingle);
		Thread.sleep(5000);
        //driver.findElement(By.className("closebutton")).click();
        Thread.sleep(1000);
		String oneLetter = driver.findElement(By.className("error-message")).getText();
		
		if(!oneLetter.trim().equals("Enter at least 3 characters"))
			Assert.fail("Autosuggestion comes on entering one letter");
		
		driver.findElement(By.className("maininput")).sendKeys(shareWithInitialStringDouble);
		Thread.sleep(5000);
		String twoLetter = driver.findElement(By.className("error-message")).getText();
		
		if(twoLetter.trim().equals("Enter at least 3 characters"))
  		{
			logger.log(Level.INFO,"Autosuggestion doesnt not come on entering two letter");
  		}
		else 
  		{
			Assert.fail("Autosuggestion comes on entering two letter");
  		}
		driver.findElement(By.className("maininput")).sendKeys(shareWithInitialStringTriple);
		Thread.sleep(5000);
		WebElement moreLetter = driver.findElement(By.id("share-with_feed"));
		if(!moreLetter.getText().trim().equals("Enter at least 3 characters") || !moreLetter.getText().trim().equals("(Enter Group or User Name)"))
  		{
			logger.log(Level.INFO,"Autosuggestion comes after 3 Letter input: Its OK");
  		}
		else 
  		{
			Assert.fail("Autosuggestion doesnt not come on entering three letter");
  		}
		Thread.sleep(5000);
		driver.findElement(By.className("maininput")).clear();
		
		driver.findElement(By.className("maininput")).sendKeys(enterFullString);
		Thread.sleep(5000);
		String fullName = driver.findElement(By.id("share-with_feed")).getText();
        System.out.println("Share:"+shareWithEnterFullString);
        System.out.println("Fullname:"+fullName);

        if(fullName.trim().equals(shareWithEnterFullString.trim()))
  		{
		 
		 logger.log(Level.INFO,"Auto suggest should display the name entered: Its OK");
  		}

	}
	catch(Exception e)
	{
		e.printStackTrace();
		Assert.fail("Exception in TestCase LSAutoSuggestStartsWithThreeAlphabets in class LSShareWithOptionAdditional", e);
	}
		
		
}


@Test(priority=7, enabled=true)
public void AutoSuggestConsideringCurrentClassSection()
{
	try{
		

		String shareWithClassSectionString = ReadTestData.readDataByTagName("", "shareWithClassSectionString", "161");
		String shareWithInitialString = ReadTestData.readDataByTagName("", "shareWithInitialString", "161");
		String FirstNameInClassSection = ReadTestData.readDataByTagName("", "FirstNameInClassSection", "161");
        String y[]=FirstNameInClassSection.split(" ");
        FirstNameInClassSection = y[1] + ", " + y[0];//reverse the name with comma in between
        String SeccondNameInClassSection = ReadTestData.readDataByTagName("", "SeccondNameInClassSection", "161");
        String a[]=SeccondNameInClassSection.split(" ");
        SeccondNameInClassSection = a[1] + ", " + a[0];//reverse the name with comma in between
		String ThirdNameInClassSection = ReadTestData.readDataByTagName("", "ThirdNameInClassSection", "161");
        String b[]=ThirdNameInClassSection.split(" ");
        ThirdNameInClassSection = b[1] + ", " + b[0];//reverse the name with comma in between
		String defaultClassSectionName = ReadTestData.readDataByTagName("", "defaultClassSectionName", "161");
		new LoginUsingLTI().ltiLogin("103");	
		new LoginUsingLTI().ltiLogin("104");
		new LoginUsingLTI().ltiLogin("105");
		new LoginUsingLTI().ltiLogin("161");
		new Navigator().NavigateTo("Course Stream");
		driver.findElement(By.linkText("Post")).click();
		
		driver.findElement(By.className("closebutton")).click();
		driver.findElement(By.className("maininput")).sendKeys(shareWithClassSectionString);
		Thread.sleep(3000);
	  	String classSection = driver.findElement(By.id("share-with_feed")).getText();
	  	System.out.println(classSection);
		driver.findElement(By.className("maininput")).clear();
		driver.findElement(By.className("maininput")).sendKeys(shareWithInitialString);
	  	List<String> stringarray = new ArrayList<>();
	  	List<WebElement> suggestname = driver.findElements(By.xpath("//*[starts-with(@rel, 'uid_')]"));
		   
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
		e.printStackTrace();
		Assert.fail("Exception in TestCase AutoSuggestConsideringCurrentClassSection in class LSShareWithOptionAdditional", e);
	}
}


@Test(priority=8, enabled=true)
public void SharePostWithStudentOrInstructorOrClassSection()
{
	try{
		

		new LoginUsingLTI().ltiLogin("105");
		new LoginUsingLTI().ltiLogin("110");
		new Navigator().NavigateTo("Course Stream");
		String shareWithInitialString = ReadTestData.readDataByTagName("", "shareWithInitialString", "110");
		String str = new RandomString().randomstring(5);
	  	if(!new PostMessage().postMessageAndShare(str, shareWithInitialString, "studentnametag", "110",""))
  		{
	  		Assert.fail("Post Not Posted");
  		
  		}
	  	if(!new PostMessageValidate().postMessageValidate(str))
	  		Assert.fail("Post not found");
  	
	  	
	}
	catch(Exception e)
	{
		Assert.fail("Exception in TestCase SharePostWithStudentOrInstructorOrClassSection in class LSShareWithOptionAdditional", e);
	}
}

}
