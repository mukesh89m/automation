package com.snapwiz.selenium.tests.staf.orion.apphelper;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.snapwiz.selenium.tests.staf.orion.Config;
import com.snapwiz.selenium.tests.staf.orion.Driver;
import com.snapwiz.selenium.tests.staf.orion.ReadTestData;

public class TextSelectActions {
	public static String noterandomstring;
	public static String discussionstring;
	public void verifyHighlight(String paragraphId,String className,String index)
	{
		try
		{
			WebElement element = (new WebDriverWait(Driver.driver,10)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='"+paragraphId+"']")));
			Actions actions = new Actions(Driver.driver);			
			
			if(Config.browser.equals("chrome"))
			{
			actions.moveToElement(element, 20, 15)
				.doubleClick()		
			    .release()
			    .perform();
			}
			else
			{
				actions.moveToElement(element, 20, 15)
				    .clickAndHold()
				    .moveByOffset(40, 0)
				    .release()
				    .perform();
			}
			String colorhex,colorx,colory,colorz;
			List<String> ptextarray = new ArrayList<String>();
			List<String> rgbhighlight = new ArrayList<String>();
			String yellowcolor = ReadTestData.readDataByTagName(className, "yellow", index);
			String graycolor = ReadTestData.readDataByTagName(className, "blue", index);
			String greencolor = ReadTestData.readDataByTagName(className, "green", index);
			String orangecolor = ReadTestData.readDataByTagName(className, "orange", index);
			
			
			
			List<WebElement> ptexts = Driver.driver.findElements(By.xpath("//*[starts-with(@class, 'ptext')]"));
			for (WebElement elementptext: ptexts) {
				
				ptextarray.add(elementptext.getText());
			}
			
			String [] ptextvalues =  ptextarray.toArray(new String[ptextarray.size()]);
			
			if(!ptextvalues[0].equals("Highlight")) Assert.fail("Highlight not present in text select options");
			
			
			List<WebElement> allElements = Driver.driver.findElements(By.xpath("//*[starts-with(@class, 'pcolor highlightcolor')]"));
			for (WebElement element1: allElements) {	        
		    
		           colorhex = element1.getAttribute("style");
		        
		          if(colorhex.length()>10)
		          {
		             colorx= colorhex.substring(22, 25);
		           colory= colorhex.substring(27,30);
		           colorz = colorhex.substring(32, 35);
		          
		           rgbhighlight.add(String.format("#%02x%02x%02x", Integer.parseInt(colorx), Integer.parseInt(colory), Integer.parseInt(colorz)));
		         
		          			
		          }
		    }
			String [] highlightcolors =  rgbhighlight.toArray(new String[rgbhighlight.size()]);
			
			if(!highlightcolors[0].equals(yellowcolor.toLowerCase()) ) Assert.fail("The first color of Highlight is not yellow");
			if(!highlightcolors[1].equals(graycolor.toLowerCase()) ) Assert.fail("The second color of Highlight is not blue");
			if(!highlightcolors[2].equals(greencolor.toLowerCase())) Assert.fail("The third color of Highlight is not green");
			if(!highlightcolors[3].equals(orangecolor.toLowerCase())) Assert.fail("The fourth color of Highlight is not orange");
		}
		catch(Exception e)
		{
			Assert.fail("Exception in app helper verifyActions in class TextSelectActions",e);
		}
	}
	
	public void verifyAddNote(String paragraphId,String className,String index)
	{
		try
		{
			WebElement element = (new WebDriverWait(Driver.driver,10)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='"+paragraphId+"']")));
			Actions actions = new Actions(Driver.driver);			
			
			if(Config.browser.equals("chrome"))
			{
			actions.moveToElement(element, 20, 15)
				.doubleClick()		
			    .release()
			    .perform();
			}
			else
			{
				actions.moveToElement(element, 20, 15)
				    .clickAndHold()
				    .moveByOffset(40, 0)
				    .release()
				    .perform();
			}
			String colorhex,colorx,colory,colorz;
			List<String> ptextarray = new ArrayList<String>();
			List<String> rgbhighlight = new ArrayList<String>();
			String yellowcolor = ReadTestData.readDataByTagName(className, "yellow", index);
			String graycolor = ReadTestData.readDataByTagName(className, "blue", index);
			String greencolor = ReadTestData.readDataByTagName(className, "green", index);
			String orangecolor = ReadTestData.readDataByTagName(className, "orange", index);
			
			
			
			List<WebElement> ptexts = Driver.driver.findElements(By.xpath("//*[starts-with(@class, 'ptext')]"));
			for (WebElement elementptext: ptexts) {
				
				ptextarray.add(elementptext.getText());
			}
			
			String [] ptextvalues =  ptextarray.toArray(new String[ptextarray.size()]);
			
		
			if(!ptextvalues[1].equals("Add Note")) Assert.fail("Add Note not present in text select options");
			
			
			List<WebElement> allElements = Driver.driver.findElements(By.xpath("//*[starts-with(@class, 'pcolor notecolor')]"));
			for (WebElement element1: allElements) {	        
		    
		           colorhex = element1.getAttribute("style");
		        
		          if(colorhex.length()>10)
		          {
		             colorx= colorhex.substring(22, 25);
		           colory= colorhex.substring(27,30);
		           colorz = colorhex.substring(32, 35);
		          
		           rgbhighlight.add(String.format("#%02x%02x%02x", Integer.parseInt(colorx), Integer.parseInt(colory), Integer.parseInt(colorz)));
		         
		          			
		          }
		    }
			String [] highlightcolors =  rgbhighlight.toArray(new String[rgbhighlight.size()]);
			
			if(!highlightcolors[0].equals(yellowcolor.toLowerCase()) ) Assert.fail("The first color of Add Note is not yellow");
			if(!highlightcolors[1].equals(graycolor.toLowerCase()) ) Assert.fail("The second color of Add Note is not blue");
			if(!highlightcolors[2].equals(greencolor.toLowerCase())) Assert.fail("The third color of Add Note is not green");
			if(!highlightcolors[3].equals(orangecolor.toLowerCase())) Assert.fail("The fourth color of Add Note is not orange");
		}
		catch(Exception e)
		{
			Assert.fail("Exception in app helper verifyActions in class TextSelectActions",e);
		}
	}
	
	
	public void verifyAddDiscussion(String paragraphId,String className,String index)
	{
		try
		{
			WebElement element = (new WebDriverWait(Driver.driver,10)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='"+paragraphId+"']")));
			Actions actions = new Actions(Driver.driver);			
			
			if(Config.browser.equals("chrome"))
			{
			actions.moveToElement(element, 20, 15)
				.doubleClick()		
			    .release()
			    .perform();
			}
			else
			{
				actions.moveToElement(element, 20, 15)
				    .clickAndHold()
				    .moveByOffset(40, 0)
				    .release()
				    .perform();
			}
			String colorhex,colorx,colory,colorz;
			List<String> ptextarray = new ArrayList<String>();
			List<String> rgbhighlight = new ArrayList<String>();
			
			String graycolor = ReadTestData.readDataByTagName(className, "blue", index);
		
			
			
			
			List<WebElement> ptexts = Driver.driver.findElements(By.xpath("//*[starts-with(@class, 'ptext')]"));
			for (WebElement elementptext: ptexts) {
				
				ptextarray.add(elementptext.getText());
			}
			
			String [] ptextvalues =  ptextarray.toArray(new String[ptextarray.size()]);
			
			
			if(!ptextvalues[2].equals("Add Discussion")) Assert.fail("Add Discussion not present in text select options");
			
			List<WebElement> allElements = Driver.driver.findElements(By.xpath("//*[starts-with(@class, 'pcolor discussioncolor')]"));
			for (WebElement element1: allElements) {	        
		    
		           colorhex = element1.getAttribute("style");
		        
		          if(colorhex.length()>10)
		          {
		             colorx= colorhex.substring(22, 25);
		           colory= colorhex.substring(27,30);
		           colorz = colorhex.substring(32, 35);
		          
		           rgbhighlight.add(String.format("#%02x%02x%02x", Integer.parseInt(colorx), Integer.parseInt(colory), Integer.parseInt(colorz)));
		         
		          			
		          }
		    }
			String [] highlightcolors =  rgbhighlight.toArray(new String[rgbhighlight.size()]);
			
			
			if(!highlightcolors[0].equals(graycolor.toLowerCase()) ) Assert.fail("The color of Add Discussion is not blue");
			
		}
		catch(Exception e)
		{
			Assert.fail("Exception in app helper verifyActions in class TextSelectActions",e);
		}
	}
	
	public void highLightText(String color,String paragraphId)
	{
		try
		{
			Boolean defaultyellow = true;
			color = color.toLowerCase();
			boolean colorfound = false;
		WebElement element = (new WebDriverWait(Driver.driver,10)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='"+paragraphId+"']")));
		
		
		Actions actions = new Actions(Driver.driver);			
		
		if(Config.browser.equals("chrome"))
		{
		actions.moveToElement(element, 20, 15)
			.doubleClick()		
		    .release()
		    .perform();
		}
		else
		{
			actions.moveToElement(element, 20, 15)
			    .clickAndHold()
			    .moveByOffset(40, 0)
			    .release()
			    .perform();
		}
		String colorhex,colorx,colory,colorz,rgb,rgbhiglighted=null;
		//String color = ReadTestData.readDataByTagName("AddNoteSelectingTextGreyColor", "colorcode", "962");
		
		List<WebElement> allElements = Driver.driver.findElements(By.xpath("//*[starts-with(@class, 'pcolor highlightcolor')]"));
		for (WebElement element1: allElements) {	        
	    
	           colorhex = element1.getAttribute("style");
	        
	          if(colorhex.length()>10)
	          {
	             colorx= colorhex.substring(22, 25);
	           colory= colorhex.substring(27,30);
	           colorz = colorhex.substring(32, 35);
	          
	          rgb = String.format("#%02x%02x%02x", Integer.parseInt(colorx), Integer.parseInt(colory), Integer.parseInt(colorz));
	          
	         //Validating if default highlight color is yellow or not
	          if(rgb.equals("#fcf2b1"))
	          {
	        	 String defaultcolorclass =  element1.getAttribute("class");
	        	 if(!defaultcolorclass.equals("pcolor highlightcolor selected"))
	        	 {
	        		 defaultyellow = false;
	        	 }
	          }
	          //Clicking on particular color to hightlight text
	          			if(rgb.equals(color)){
	          						Thread.sleep(3000);
	          						element1.click();
	          						colorfound = true;
	          						break;
	          						}
	          }
	    }
		if(colorfound == false) Assert.fail("Highlight text Bullet of required color not found");
		
		//Validating if the text is highlighted or not
		 Thread.sleep(5000);
		List<WebElement> highligtedtext = Driver.driver.findElements(By.xpath("//*[starts-with(@class, 'highlighted annotation')]"));
		
		for (WebElement highligted: highligtedtext) {	        
		   
	           colorhex = highligted.getAttribute("style");
	        System.out.println("style:" +colorhex);
	          if(colorhex.length()>10)
	          {
	             colorx= colorhex.substring(22, 25);
	             colory= colorhex.substring(27,30);
	             colorz = colorhex.substring(32, 35);
	          
	             rgbhiglighted = String.format("#%02x%02x%02x", Integer.parseInt(colorx), Integer.parseInt(colory), Integer.parseInt(colorz));
	             System.out.println("highligted color: "+rgbhiglighted);
	          }
	          if(!rgbhiglighted.equals(color))
	        	  Assert.fail("Selected Text Not highlighted with the desired color");
		}
		
		
		if(defaultyellow == false)  Assert.fail("The default highlight color is not yellow");
	}
		catch(Exception e)
		{
			Assert.fail("Exception while highlighting selecting text",e);
		}
	}
	
	public void addNote(String color,String paragraphId)
	{
		try
		{
			Boolean defaultyellow = true;
			color = color.toLowerCase();
			boolean colorfound = false;
		WebElement element = (new WebDriverWait(Driver.driver,10)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='"+paragraphId+"']")));
		
		
		Actions actions = new Actions(Driver.driver);			
		if(Config.browser.equals("chrome"))
		{
		actions.moveToElement(element, 20, 15)
			.doubleClick()		
		    .release()
		    .perform();
		}
		else
		{
			actions.moveToElement(element, 20, 15)
			    .clickAndHold()
			    .moveByOffset(40, 0)
			    .release()
			    .perform();
		}
		String colorhex,colorx,colory,colorz,rgb;
		//String color = ReadTestData.readDataByTagName("AddNoteSelectingTextGreyColor", "colorcode", "962");
		
		List<WebElement> allElements = Driver.driver.findElements(By.xpath("//*[starts-with(@class, 'pcolor notecolor')]"));
		for (WebElement element1: allElements) {	        
	    
	           colorhex = element1.getAttribute("style");
	        
	          if(colorhex.length()>10)
	          {
	             colorx= colorhex.substring(22, 25);
	           colory= colorhex.substring(27,30);
	           colorz = colorhex.substring(32, 35);
	          
	          rgb = String.format("#%02x%02x%02x", Integer.parseInt(colorx), Integer.parseInt(colory), Integer.parseInt(colorz));
	          System.out.println("rgbvalues: "+ rgb);
	          //Validating if default highlight color is yellow or not
	          if(rgb.equals("#fcf2b1"))
	          {
	        	 String defaultcolorclass =  element1.getAttribute("class");
	        	 if(!defaultcolorclass.equals("pcolor notecolor selected"))
	        	 {
	        		 defaultyellow = false;
	        	 }
	          }          
	          
	          			if(rgb.equals(color)){
	          						Thread.sleep(3000);
	          						element1.click();
	          						colorfound = true;
	          						break;
	          						}
	          }
	    }
		if(colorfound == false) Assert.fail("Add Note Bullet of required color not found");
		
		//validating text pop up, submit and cancel buttons
		Thread.sleep(3000);
		
		WebElement textbox = Driver.driver.findElement(By.className("editnote-text"));
		WebElement submitbutton = Driver.driver.findElement(By.id("editnote-submit-INTERIM"));
		WebElement cancelbutton = Driver.driver.findElement(By.cssSelector("button[class='editnote-button pagecontext-cancel']"));
		if(!submitbutton.isDisplayed()) Assert.fail("Submit Button not present in the add note pop up.");
		if(!cancelbutton.isDisplayed()) Assert.fail("Cancel Button not present in the add note pop up.");
		if(!textbox.isDisplayed()) Assert.fail("Text Box not present in the add note pop up.");
		
		
		//Sending Text
		noterandomstring = new RandomString().randomstring(5);
		Driver.driver.findElement(By.className("editnote-text")).sendKeys(noterandomstring);
		 JavascriptExecutor jsx = (JavascriptExecutor)Driver.driver;
		    jsx.executeScript("window.scrollBy(0,50)", "");
		Thread.sleep(2000);
		WebElement submitnotebutton = Driver.driver.findElement(By.cssSelector("button[class='editnote-button editnote-submit']"));			
		submitnotebutton.click();
		Thread.sleep(2000);
		//new KeysSend().sendKeyBoardKeys(noterandomstring);	
	//	WebElement cancelbutton2 = Driver.driver.findElement(By.xpath("/html/body/div[3]/div/div[2]/div[2]/div/div[2]/div/div[3]/div[2]/div/button[2]"));
	//	cancelbutton2.click();
	//	new KeysSend().sendKeyBoardKeys("`^");
		
		
		//Validating right side panel		
		WebElement note = Driver.driver.findElement(By.className("ls-right-user-post-body"));
		if(!noterandomstring.equals(note.getText().trim()))
		Assert.fail("Note added by user not found on the right side panel");  //Validating Note Text
		
		
		WebElement noteelement = Driver.driver.findElement(By.className("ls-right-user-head"));
		String postedanote = (String) ((JavascriptExecutor)Driver.driver).executeScript("return arguments[0].innerHTML",noteelement);
	    System.out.println(postedanote);
		if(!postedanote.contains(" posted a note"))
		Assert.fail("Text 'Posted a Note' not found on right side panel inside the note block after posting a note"); //Validating posted a note text
		
		String username = Driver.driver.findElement(By.cssSelector("a[class='ls-right-user-name ellipsis']")).getText();
		System.out.println(username);
		String user = Config.givenname+" "+Config.familyname;
		if(!username.equals(user))
			Assert.fail("Username invalid or blank on right side panel inside the note block after posting a note"); //Validating user name
		
		Boolean staricon = Driver.driver.findElement(By.cssSelector("i[class='ls-right-section-sprites ls--right-star-icon']")).isDisplayed();
		if(staricon == false)
			Assert.fail("Star Icon not displayed on right side panel inside the note block after posting a note");
		
		if(defaultyellow == false)  Assert.fail("The default Add Note color is not yellow");
	}
		catch(Exception e)
		{
			Assert.fail("Exception while adding note by selecting text",e);
		}
	}
	
	public void addDiscussion(String color,String paragraphId,int moveToOffset,int moveByOffset)
	{
		try
		{
			Boolean defaultblue = true;
			//JavascriptExecutor jsx = (JavascriptExecutor)Driver.driver;
		    //jsx.executeScript("window.scrollBy(0,250)", "");
			color = color.toLowerCase();
			boolean colorfound = false;
		WebElement element = (new WebDriverWait(Driver.driver,10)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='"+paragraphId+"']")));
		
		
		Actions actions = new Actions(Driver.driver);			
		
		//if(Config.browser.equals("chrome"))
		//{
		actions.moveToElement(element, 20, 15)
			.doubleClick()		
		    .release()
		    .perform();
		/*//}
		else
		{
			actions.moveToElement(element, 20, 15)
			    .clickAndHold()
			    .moveByOffset(40, 0)
			    .release()
			    .perform();
		}*/
		String colorhex,colorx,colory,colorz,rgb;
		//String color = ReadTestData.readDataByTagName("AddNoteSelectingTextGreyColor", "colorcode", "962");
		
		List<WebElement> allElements = Driver.driver.findElements(By.xpath("//*[starts-with(@class, 'pcolor discussioncolor')]"));
		for (WebElement element1: allElements) {	        
	    
	           colorhex = element1.getAttribute("style");
	        
	          if(colorhex.length()>10)
	          {
	             colorx= colorhex.substring(22, 25);
	           colory= colorhex.substring(27,30);
	           colorz = colorhex.substring(32, 35);
	          
	          rgb = String.format("#%02x%02x%02x", Integer.parseInt(colorx), Integer.parseInt(colory), Integer.parseInt(colorz));
	         
	          //Validating if default highlight color is yellow or not
	          if(rgb.equals("#87cefa"))
	          {
	        	 String defaultcolorclass =  element1.getAttribute("class");
	        	 if(!defaultcolorclass.equals("pcolor discussioncolor selected"))
	        	 {
	        		 defaultblue = false;
	        	 }
	          }          
	          
	          			if(rgb.equals(color)){
	          						Thread.sleep(3000);
	          						element1.click();
	          						colorfound = true;
	          						break;
	          						}
	          }
	    }
		
		if(colorfound == false) Assert.fail("Add Discussion Bullet of required color not found");
		
		//validating text pop up, submit and cancel buttons
				Thread.sleep(3000);
				
				WebElement textbox = Driver.driver.findElement(By.className("editdiscussion-text"));
				WebElement submitbutton = Driver.driver.findElement(By.id("editdiscussion-submit-INTERIM"));
				WebElement cancelbutton = Driver.driver.findElement(By.cssSelector("button[class='editdiscussion-button pagecontext-cancel']"));
				if(!submitbutton.isDisplayed()) Assert.fail("Submit Button not present in the add discussion pop up.");
				if(!cancelbutton.isDisplayed()) Assert.fail("Cancel Button not present in the add discussion pop up.");
				if(!textbox.isDisplayed()) Assert.fail("Text Box not present in the add discussion pop up.");
				
		discussionstring = new RandomString().randomstring(5);
		Driver.driver.findElement(By.className("editdiscussion-text")).sendKeys(discussionstring);
		//new KeysSend().sendKeyBoardKeys(discussionstring);
		//new KeysSend().sendKeyBoardKeys("`^");
		JavascriptExecutor jsx = (JavascriptExecutor)Driver.driver;
	    jsx.executeScript("window.scrollBy(0,50)", "");	
		Thread.sleep(2000);
		WebElement submitbutton1 = Driver.driver.findElement(By.cssSelector("button[class='editdiscussion-button editdiscussion-submit']"));			
		submitbutton1.click();
		Thread.sleep(2000);
		//Validating right side panel	
		
				WebElement profilepic =  Driver.driver.findElement(By.className("ls-right-user-img"));
				String imgtag = (String) ((JavascriptExecutor)Driver.driver).executeScript("return arguments[0].innerHTML",profilepic);
				if(!imgtag.contains("<img src=\"/webresources/images/ls/user-default-thumbnail.png\">"))
				Assert.fail("Profile Pic not preset in the add discussion posted shown at the right side of the screen under Stream tab in eTextBook");
		
				WebElement note = Driver.driver.findElement(By.className("ls-right-user-post-body"));
				if(!discussionstring.equals(note.getText().trim()))
				Assert.fail("Discussion added by user not found on the right side panel");  //Validating Note Text
				
				
				WebElement noteelement = Driver.driver.findElement(By.className("ls-right-user-head"));
				String postedanote = (String) ((JavascriptExecutor)Driver.driver).executeScript("return arguments[0].innerHTML",noteelement);
			    
				if(!postedanote.contains(" posted a discussion"))
				Assert.fail("Text 'Posted a Discussion' not found on right side panel inside the note block after posting a Discussion"); //Validating posted a discussion text
				
				String username = Driver.driver.findElement(By.cssSelector("a[class='ls-right-user-name ellipsis']")).getText();
				System.out.println(username);
				String user = Config.givenname+" "+Config.familyname;
				if(!username.equals(user))
					Assert.fail("Username invalid or blank on right side panel inside the note block after posting a Discussion"); //Validating user name
				
				Boolean staricon = Driver.driver.findElement(By.cssSelector("i[class='ls-right-section-sprites ls--right-star-icon']")).isDisplayed();
				if(staricon == false)
					Assert.fail("Star Icon not displayed on right side panel inside the note block after posting a Discussion");
		
		
		if(defaultblue == false)  Assert.fail("The default Add Discussion color is not blue");
	}
		catch(Exception e)
		{
			Assert.fail("Exception while adding discussion by selecting text",e);
		}
	}
}
