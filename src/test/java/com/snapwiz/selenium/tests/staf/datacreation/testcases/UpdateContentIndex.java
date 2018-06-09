package com.snapwiz.selenium.tests.staf.datacreation.testcases;

import com.snapwiz.selenium.tests.staf.datacreation.Config;
import com.snapwiz.selenium.tests.staf.datacreation.Driver;
import com.snapwiz.selenium.tests.staf.datacreation.apphelper.DirectLogin;
import com.snapwiz.selenium.tests.staf.datacreation.uihelper.ComboBox;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UpdateContentIndex extends Driver {
	private static Logger logger=Logger.getLogger("com.snapwiz.selenium.tests.staf.datacreation.apphelper.UpdateContentIndex");
	//Its update the course update Content index
    @Test
	public void updatecontentindex()
	{
		try
		{
            String msg1,msg2,msg3,msg4;
            List<String> courses = new ArrayList<>();

            //LS
            courses.add("247");courses.add("249");courses.add("250");courses.add("252");courses.add("253");courses.add("273");
            courses.add("275");courses.add("276");courses.add("277");courses.add("279");courses.add("280");courses.add("283");
            courses.add("285");courses.add("287");courses.add("288");courses.add("289");courses.add("290");courses.add("296");
            courses.add("298");courses.add("299");courses.add("300");courses.add("303");courses.add("305");courses.add("306");
            courses.add("307");courses.add("308");courses.add("309");courses.add("310");courses.add("311");courses.add("312");
            courses.add("315");courses.add("316");courses.add("321");courses.add("324");courses.add("327");courses.add("328");
            courses.add("330");courses.add("331");courses.add("334");courses.add("335");courses.add("336");courses.add("338");
            courses.add("339");courses.add("341");courses.add("345");courses.add("348");courses.add("349");courses.add("353");
            courses.add("355");courses.add("356");courses.add("357");courses.add("358");courses.add("359");


			new DirectLogin().CMSLogin();
			String title=Driver.driver.getTitle();
			if(title.equals("Course Content Details"))
			{
				logger.log(Level.INFO,"CMSHome  open");

				Driver.driver.get(Config.baseURL+"/secure/updateContentIndex");
                for(String course : courses) {

                    new ComboBox().selectValue(1, course);


                    Driver.driver.findElement(By.id("content-type-radio-button")).click();
                    /*Driver.driver.findElement(By.id("remove-from-solr-index")).click();
                    WebElement myDynamicElement = (new WebDriverWait(Driver.driver, 3600000))
                            .until(ExpectedConditions.presenceOfElementLocated(By.className("cms-notification-message-wrapper")));
                    Thread.sleep(500);
                    msg1 = (myDynamicElement.getText());*/
                    /*if (!myDynamicElement.getText().equals("Successfully removed from Solr index."))
                        Assert.fail("Solr index not removed successfully");*/
                    Driver.driver.findElement(By.id("add-to-solr-index")).click();
                    WebElement myDynamicElement = (new WebDriverWait(Driver.driver, 3600000))
                            .until(ExpectedConditions.presenceOfElementLocated(By.className("cms-notification-message-wrapper")));
                    Thread.sleep(500);
                    msg2 = (myDynamicElement.getText());
                   /* if (!myDynamicElement.getText().equals("Successfully added to Solr index."))
                        Assert.fail("Solr index not added successfully");*/
                    Driver.driver.findElement(By.id("entity-radio-button")).click();
                    /*Driver.driver.findElement(By.id("remove-from-solr-index")).click();
                    myDynamicElement = (new WebDriverWait(Driver.driver, 3600000))
                            .until(ExpectedConditions.presenceOfElementLocated(By.className("cms-notification-message-wrapper")));
                    Thread.sleep(500);
                    msg3 = (myDynamicElement.getText());*/
                    /*if (!myDynamicElement.getText().equals("Successfully removed from Solr index."))
                        Assert.fail("Solr index not removed successfully");*/
                    Driver.driver.findElement(By.id("add-to-solr-index")).click();
                    myDynamicElement = (new WebDriverWait(Driver.driver, 3600000))
                            .until(ExpectedConditions.presenceOfElementLocated(By.className("cms-notification-message-wrapper")));
                    Thread.sleep(500);
                    msg4 = (myDynamicElement.getText());
                    writeToFile(msg2,msg4,course);
                    /*if (!myDynamicElement.getText().equals("Successfully added to Solr index."))
                        Assert.fail("Solr index not added successfully");*/
                }
			}
			else
			{
				logger.log(Level.INFO,"CMSHome  NOT open");
				Assert.fail("CMS home not opened");
			}					
		}
		catch(Exception e)
		{
			System.out.println("Exception in App Helper UpdateContentIndex "+e);
		}
	}

    public void writeToFile(String msg2,String msg3,String course) {
        try {
           File file = new File("updatecontentindex.txt");

            // if file doesnt exists, then create it
            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter fw = new FileWriter(file.getAbsoluteFile(),true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write("---------------------------------------------------------------------------------------------"); bw.newLine();
            bw.write("Course: "+course); bw.newLine();
            bw.write("Content Type " + msg2); bw.newLine();
            bw.write("Entities "+msg3); bw.newLine();

            bw.newLine();
            bw.close();

        }
        catch (Exception e) {

        }
    }
}
	
