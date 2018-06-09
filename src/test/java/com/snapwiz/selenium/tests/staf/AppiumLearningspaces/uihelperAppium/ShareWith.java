package com.snapwiz.selenium.tests.staf.AppiumLearningspaces.uihelperAppium;

import com.snapwiz.selenium.tests.staf.AppiumLearningspaces.Config;
import com.snapwiz.selenium.tests.staf.AppiumLearningspaces.Driver;
import com.snapwiz.selenium.tests.staf.AppiumLearningspaces.uihelper.TextFetch;
import com.snapwiz.selenium.tests.staf.AppiumLearningspaces.uihelper.UIElement;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;


public class ShareWith {
	
	public void share(String shareWithInitialString,String shareName,String shareWithClass,String className,boolean removeExistingShare)
	{
		try
		{
            String y[]=shareName.split(" ");
            if(!shareWithClass.toUpperCase().equals("TRUE")) {//reverse the name only if the shareWithClass parameter is not TRUE, i.e sharing has to be done with a student hence reverse the student name
                shareName = y[1] + ", " + y[0];//reverse the name with comma in between
            }
            String methodName = new Exception().getStackTrace()[1].getMethodName();
            if(!methodName.contains("updateAssignment")) {  //IF updateAssignment is required then student name does not appear with class section in the prompt
                if (!shareWithClass.toUpperCase().equals("TRUE")) {
                    if (className == null || className.equals(""))
                        shareName = shareName + " - " + Config.context_title;
                    else
                        shareName = shareName + " - " + className;
                }
            }
            System.out.println("share name "+shareName);

			boolean sharefound = false;

            new UIElement().waitAndFindElement(By.className("maininput"));
            ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.className("maininput")));
			Thread.sleep(2000);
			if(removeExistingShare == true)
            {
                List<WebElement> allClassSection = Driver.driver.findElements(By.cssSelector("li[class='bit-box']"));
                for(WebElement classSection: allClassSection)
                {
                    Driver.driver.findElement(By.className("closebutton")).click();//click on close symbol
                    String assignToField = new TextFetch().textfetchbyclass("holder");
                    if(assignToField.length() == 0)
                    {
                        System.out.println("Inside if: ");
                        break;
                    }

                }
            }

            //Driver.driver.findElement(By.cssSelector("div[class='ir-ls-assign-dialog-assign-to-content ir-ls-assign-dialog-field']")).clear();
			Driver.driver.findElement(By.className("maininput")).sendKeys(shareWithInitialString);
			Thread.sleep(3000);
			List<WebElement> suggestname;
			if(shareWithClass.toUpperCase().equals("TRUE"))
				 suggestname = Driver.driver.findElements(By.xpath("//*[starts-with(@rel, 'cls_')]")); 
			else
				 suggestname = Driver.driver.findElements(By.xpath("//*[starts-with(@rel, 'uid_')]")); 
			   for (WebElement answerchoice: suggestname)
			   {
				   System.out.println("Names "+answerchoice.getText());
			    if(answerchoice.getText().trim().equals(shareName))
			    {
			    	System.out.println("Inside");
			    	((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", answerchoice);
			        answerchoice.click();	
			     sharefound = true;
			     break;
			    }
			   }
			if(sharefound == false)
				Assert.fail("No value selected from the Assign To field");
		}
		catch(Exception e)
		{
			Assert.fail("Exception in UI helper ShareWith",e);
		}
	}

    public void shareInCS(String shareWithInitialString,String shareName,String shareWithClass,String className,boolean removeExistingShare)
    {
        try
        {
            String y[]=shareName.split(" ");
            if(!shareWithClass.toUpperCase().equals("TRUE")) {//reverse the name only if the shareWithClass parameter is not TRUE, i.e sharing has to be done with a student hence reverse the student name
                shareName = y[1] + ", " + y[0];//reverse the name with comma in between
            }
            System.out.println("share name "+shareName);
            boolean sharefound = false;
            Driver.driver.findElement(By.className("maininput")).click();
            Thread.sleep(2000);
            if(removeExistingShare == true)
                Driver.driver.findElement(By.className("closebutton")).click();
            Thread.sleep(2000);
            Driver.driver.findElement(By.className("maininput")).sendKeys(shareWithInitialString);
            Thread.sleep(3000);
            List<WebElement> suggestname;
            if(shareWithClass.toUpperCase().equals("TRUE"))
                suggestname = Driver.driver.findElements(By.xpath("//*[starts-with(@rel, 'cls_')]"));
            else
                suggestname = Driver.driver.findElements(By.xpath("//*[starts-with(@rel, 'uid_')]"));
            for (WebElement answerchoice: suggestname)
            {
                System.out.println("Names "+answerchoice.getText());
                if(answerchoice.getText().trim().equals(shareName))
                {
                    System.out.println("Inside");
                    ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", answerchoice);
                    answerchoice.click();
                    sharefound = true;
                    break;
                }
            }
            if(sharefound == false)
                Assert.fail("No value selected from the Assign To field");
        }
        catch(Exception e)
        {
            Assert.fail("Exception in UI helper shareInCS",e);
        }
    }

}
