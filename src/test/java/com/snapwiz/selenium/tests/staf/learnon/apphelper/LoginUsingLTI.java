package com.snapwiz.selenium.tests.staf.learnon.apphelper;

import com.snapwiz.selenium.tests.staf.learnon.uihelper.UIElement;
import com.snapwiz.selenium.tests.staf.learnon.Config;
import com.snapwiz.selenium.tests.staf.learnon.Driver;
import com.snapwiz.selenium.tests.staf.learnon.ReadTestData;
import com.snapwiz.selenium.tests.staf.learnon.apphelper.Screenshot;
import com.snapwiz.selenium.tests.staf.learnon.apphelper.TextValidate;
import org.openqa.selenium.By;
import org.testng.Assert;


public class LoginUsingLTI {
    public static String appendChar;

	/* Method for LTI login for learning spaces. This method enters LTI login details like end point and keys. The login will fail is the text verified
	   * is not present on the loaded page after clicking the launch button. */
	  public void ltiLogin(String dataIndex) 
	  {
          appendChar = System.getProperty("UCHAR");
          String user_id =  ReadTestData.readDataByTagName("", "user_id", dataIndex);
		  String role =  ReadTestData.readDataByTagName("", "Role", dataIndex);
		  String context_id = ReadTestData.readDataByTagName("", "context_id", dataIndex);
		  String context_title = ReadTestData.readDataByTagName("", "context_title", dataIndex);
		  String custom_courseid = ReadTestData.readDataByTagName("", "custom_courseid", dataIndex);
		  String custom_destination = ReadTestData.readDataByTagName("", "custom_destination", dataIndex);
		  String resource_link_id = ReadTestData.readDataByTagName("", "resource_link_id", dataIndex);
		  String expectederror = ReadTestData.readDataByTagName("", "expectederror", dataIndex);
		  String custom_course_number = ReadTestData.readDataByTagName("", "custom_course_number", dataIndex);
		  String familyname =  ReadTestData.readDataByTagName("", "familyname", dataIndex);
		  String givenname = ReadTestData.readDataByTagName("", "givenname", dataIndex);
		  String custom_domain_name = ReadTestData.readDataByTagName("", "custom_domain_name", dataIndex);
		  String custom_instructor_classlist = ReadTestData.readDataByTagName("", "custom_instructor_classlist", dataIndex);
		  String course_type = ReadTestData.readDataByTagName("", "course_type", dataIndex);  // valid values - 'ls', 'adaptive'. 
          String custom_domainid =ReadTestData.readDataByTagName("", "custom_domainid", dataIndex);

          try
          {
              if(appendChar == null ) {
                  appendChar = "a";

              }

              Driver.driver.get(Config.baseLTIURL + "/");
		  		  
		  	Driver.driver.findElement(By.name("endpoint")).clear(); //Clear fields
		  	Thread.sleep(100);
		  	Driver.driver.findElement(By.name("endpoint")).sendKeys(Config.launchURL);
		    
		  	Driver.driver.findElement(By.name("key")).clear();
		  	Thread.sleep(100);
		  	Driver.driver.findElement(By.name("key")).sendKeys(Config.customerkey);
		    
		  	Driver.driver.findElement(By.name("secret")).clear();
		  	Thread.sleep(100);
		  	Driver.driver.findElement(By.name("secret")).sendKeys(Config.secretkey);
		    
		  	Driver.driver.findElement(By.name("resource_link_id")).clear();
		  	Thread.sleep(100);
		  	if(resource_link_id == null)
		  		Driver.driver.findElement(By.name("resource_link_id")).sendKeys(Config.resourselinkid);
		  	else
		  		Driver.driver.findElement(By.name("resource_link_id")).sendKeys(resource_link_id);
		    
		  	Driver.driver.findElement(By.name("user_id")).clear();
		  	Thread.sleep(100);
            Driver.driver.findElement(By.name("user_id")).sendKeys(user_id+appendChar);

              Driver.driver.findElement(By.name("roles")).clear();
		  	Thread.sleep(100);
		  	Driver.driver.findElement(By.name("roles")).sendKeys(role);
		    
		  	Driver.driver.findElement(By.name("lis_person_name_family")).clear();
		  	Thread.sleep(100);
		  	if(familyname == null)
		  		Driver.driver.findElement(By.name("lis_person_name_family")).sendKeys(Config.familyname);
		  	else
		  		Driver.driver.findElement(By.name("lis_person_name_family")).sendKeys(familyname);
		    
		  	Driver.driver.findElement(By.name("lis_person_name_given")).clear();
		  	Thread.sleep(100);
		  	if(givenname == null)
		  		Driver.driver.findElement(By.name("lis_person_name_given")).sendKeys(Config.givenname);
		  	else
		  		Driver.driver.findElement(By.name("lis_person_name_given")).sendKeys(givenname);
		    
		  	Driver.driver.findElement(By.name("lis_person_contact_email_primary")).clear();
		  	Thread.sleep(100);
		  	Driver.driver.findElement(By.name("lis_person_contact_email_primary")).sendKeys(Config.email);
		    
		  	Driver.driver.findElement(By.name("context_id")).clear();
		  	Thread.sleep(100);
		  	if(context_id == null)
		  		Driver.driver.findElement(By.name("context_id")).sendKeys(Config.context_id+appendChar);
		  	else
		  		Driver.driver.findElement(By.name("context_id")).sendKeys(context_id+appendChar);
		  	
		  	Driver.driver.findElement(By.name("context_title")).clear();
		  	Thread.sleep(100);
		  	if(context_title == null)
		  		Driver.driver.findElement(By.name("context_title")).sendKeys(Config.context_title);
		  	else
		  		Driver.driver.findElement(By.name("context_title")).sendKeys(context_title);
		  	
		  	Driver.driver.findElement(By.name("tool_consumer_instance_guid")).clear();
		  	Thread.sleep(100);
		  	Driver.driver.findElement(By.name("tool_consumer_instance_guid")).sendKeys(Config.instance_guid);
		    
		  	Driver.driver.findElement(By.name("tool_consumer_instance_name")).clear();
		  	Thread.sleep(100);
		  	Driver.driver.findElement(By.name("tool_consumer_instance_name")).sendKeys(Config.instance_name);
		    
		  	Driver.driver.findElement(By.name("custom_courseid")).clear();
		  	Thread.sleep(100);
		  	if(custom_courseid == null)
		  	{
		  		if(course_type == null)
		  			Driver.driver.findElement(By.name("custom_courseid")).sendKeys(Config.courseid);
		  		else if(course_type.equalsIgnoreCase("ls"))
		  			Driver.driver.findElement(By.name("custom_courseid")).sendKeys(Config.lsCourseId);
		  		else if(course_type.equalsIgnoreCase("adaptive"))
		  			Driver.driver.findElement(By.name("custom_courseid")).sendKeys(Config.adaptiveCourseID);
		  	}
		  	else
		  		Driver.driver.findElement(By.name("custom_courseid")).sendKeys(custom_courseid);
		  	
		    
		  	Driver.driver.findElement(By.name("custom_destination")).clear();
		  	Thread.sleep(100);
		  	if(custom_destination == null)
		  		Driver.driver.findElement(By.name("custom_destination")).sendKeys(Config.custom_destination); 	
		  	else
		  		Driver.driver.findElement(By.name("custom_destination")).sendKeys(custom_destination);


              if(custom_domainid == null){
                  Driver.driver.findElement(By.name("custom_domainid")).clear();
                  new UIElement().waitAndFindElement(By.name("custom_domainid"));
                  Driver.driver.findElement(By.name("custom_domainid")).sendKeys(Config.custom_domainid+appendChar);
              }else
                  Driver.driver.findElement(By.name("custom_domainid")).sendKeys(custom_domainid+appendChar);


              Driver.driver.findElement(By.name("custom_course_number")).clear();
		  	Thread.sleep(100);
		  	if(custom_course_number == null)
		  		Driver.driver.findElement(By.name("custom_course_number")).sendKeys(Config.custom_course_number);
		  	else
		  		Driver.driver.findElement(By.name("custom_course_number")).sendKeys(custom_course_number);
		    
		  	if(custom_domain_name == null)
		  		Driver.driver.findElement(By.name("custom_domain_name")).sendKeys(Config.custom_domain_name+appendChar);
		  	else
		  		Driver.driver.findElement(By.name("custom_domain_name")).sendKeys(custom_domain_name+appendChar);
		  	if(custom_instructor_classlist == null)
		  		Driver.driver.findElement(By.name("custom_instructor_classlist")).sendKeys(Config.custom_instructor_classlist);
		  	else
		  		Driver.driver.findElement(By.name("custom_instructor_classlist")).sendKeys(custom_instructor_classlist);
		  	
		  	Driver.driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();
		  	Driver.driver.findElement(By.cssSelector("form > input[type=\"submit\"]")).click();
		    if(expectederror == null)
		    {
		    	Boolean textPresent = new TextValidate().IsTextPresent("Something went wrong in processing your request.");
		    	if(textPresent == true)	
		    	{
		    		Driver.driver.quit();
			    	Assert.fail("LTI login with course id "+Config.courseid+" Failed");			    	
		    	}
		    	
		    }
		    if(Config.browser.equals("ie"))
				  Driver.driver.navigate().refresh();
		  }
		  catch(Exception e)
		  {				 
			  new Screenshot().captureScreenshotFromAppHelper();
			  Driver.driver.quit();
			  Assert.fail("Exception in LoginUsingLTI Application Helper",e);
			  
		  }
		   
	  }
	
}
