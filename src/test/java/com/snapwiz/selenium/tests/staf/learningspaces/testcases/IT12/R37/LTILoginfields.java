package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT12.R37;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Config;
import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Screenshot;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.ComboBox;
public class LTILoginfields extends Driver
{
	@Test(priority = 1, enabled = true)
	public void LTIfiledslist()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("8");
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase LTIfiledslist in class LTILoginfields",e);
		}
	}
	
	@Test(priority = 2, enabled = true)
	public void  Verifycustomdomainnamefield()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("9");
			WebElement pagetext = driver.findElement(By.tagName("body"));
			if(!pagetext.getText().contains("Something went wrong in processing your request."))
			{
				Assert.fail("custom_domain_name is not mandatary field.");
			}
			  
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase Verifycustomdomainnamefield in class LTILoginfields",e);
		}
	}
	
	@Test(priority = 3, enabled = true)
	public void  Verifycustomdomainnamendatabase()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("10");
			String classname = new UserVerification().databaseverificationstring(1, "SELECT display_name FROM t_class where id = (SELECT class_id FROM t_class_section where id = (SELECT class_section_id FROM t_class_section_permission where user_id = (SELECT id FROM t_user where username = 'student10')))");
			System.out.println(classname);
			if(!classname.equals("studtitle"))
			{
				Assert.fail("custom_domain_name field is not saved in t_class.display_name column");
			}
			  
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase Verifycustomdomainnamendatabase in class LTILoginfields",e);
		}
	}
	
	@Test(priority = 4, enabled = true)
	public void  customdomainnameblankbydefault()
	{
		try
		{
			driver.get(Config.baseLTIURL + "/");
			String customdomainnameblank = driver.findElement(By.cssSelector("input[name='custom_domain_name']")).getAttribute("value");
			if(!customdomainnameblank.contains(""))
			{
				Assert.fail("custom_domain_name filed is not blank");
			}
			/*if(!driver.findElement(By.cssSelector("input[name='custom_domain_name']")).getText().equals(""))
				Assert.fail("custom_domain_name filed is not blank");*/		
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase customdomainnameblankbydefault in class LTILoginfields",e);
		}
	}
	
	@Test(priority = 5, enabled = true)
	public void  custominstructorclasslistjsonstring()
	{
		try
		{
			driver.get(Config.baseLTIURL + "/");
			String customdomainnameblank = driver.findElement(By.cssSelector("input[name='custom_instructor_classlist']")).getAttribute("value");
			if(!customdomainnameblank.equals("[{'classid': 'cls16760','title': 'Kimmel_ClassA','classcode': 'BIO-101','state': 'Active'}, {'classid': 'cls16761','title': 'Kimmel_ClassB','classcode': 'BIO-101','state': 'Active'}]"))
			{
				Assert.fail("customin_structor_classlist fieled not contains any default json string");
			}
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase custominstructorclasslistjsonstring in class LTILoginfields",e);
		}
	}
	
	@Test(priority = 6, enabled = true)
	public void  mandatorycustominstructorclasslist()
	{
		 try
		{
			String user_id =  ReadTestData.readDataByTagName("", "user_id", "13");
			String role =  ReadTestData.readDataByTagName("", "Role", "13");
			driver.get(Config.baseLTIURL + "/");
			  		  
			driver.findElement(By.name("endpoint")).clear(); //Clear fields
			Thread.sleep(500);
			driver.findElement(By.name("endpoint")).sendKeys(Config.launchURL);
			driver.findElement(By.name("key")).clear();
			Thread.sleep(500);
			driver.findElement(By.name("key")).sendKeys(Config.customerkey);
			driver.findElement(By.name("secret")).clear();
			Thread.sleep(500);
			driver.findElement(By.name("secret")).sendKeys(Config.secretkey);
			driver.findElement(By.name("resource_link_id")).clear();
			Thread.sleep(500);
			driver.findElement(By.name("resource_link_id")).sendKeys(Config.resourselinkid);
			driver.findElement(By.name("user_id")).clear();
			Thread.sleep(500);
			driver.findElement(By.name("user_id")).sendKeys(user_id);
			driver.findElement(By.name("roles")).clear();
			Thread.sleep(500);
			driver.findElement(By.name("roles")).sendKeys(role);
			driver.findElement(By.name("lis_person_name_family")).clear();
			Thread.sleep(500);
			driver.findElement(By.name("lis_person_name_family")).sendKeys(Config.familyname);
			driver.findElement(By.name("lis_person_name_given")).clear();
			Thread.sleep(500);
			driver.findElement(By.name("lis_person_name_given")).sendKeys(Config.givenname);
			driver.findElement(By.name("lis_person_contact_email_primary")).clear();
			Thread.sleep(500);
			driver.findElement(By.name("lis_person_contact_email_primary")).sendKeys(Config.email);
			driver.findElement(By.name("context_id")).clear();
			Thread.sleep(500);
			driver.findElement(By.name("context_id")).sendKeys(Config.context_id);
			driver.findElement(By.name("context_title")).clear();
			Thread.sleep(500);
			driver.findElement(By.name("context_title")).sendKeys(Config.context_title);
			driver.findElement(By.name("tool_consumer_instance_guid")).clear();
			Thread.sleep(500);
			driver.findElement(By.name("tool_consumer_instance_guid")).sendKeys(Config.instance_guid);
			driver.findElement(By.name("tool_consumer_instance_name")).clear();
			Thread.sleep(500);
	    	driver.findElement(By.name("tool_consumer_instance_name")).sendKeys(Config.instance_name);
			driver.findElement(By.name("custom_courseid")).clear();
			Thread.sleep(500);
			driver.findElement(By.name("custom_courseid")).sendKeys(Config.courseid);
			driver.findElement(By.name("custom_destination")).clear();
			Thread.sleep(500);
			driver.findElement(By.name("custom_destination")).sendKeys(Config.custom_destination);
			driver.findElement(By.name("custom_domainid")).clear();
			Thread.sleep(500);
			driver.findElement(By.name("custom_domainid")).sendKeys(Config.custom_domainid);
			driver.findElement(By.name("custom_course_number")).clear();
			Thread.sleep(500);
			driver.findElement(By.name("custom_course_number")).sendKeys(Config.custom_course_number);
			driver.findElement(By.name("custom_domain_name")).sendKeys(Config.custom_domain_name);
			driver.findElement(By.cssSelector("input[name='custom_instructor_classlist']")).clear();
			driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();
			driver.findElement(By.cssSelector("form > input[type=\"submit\"]")).click();
			Thread.sleep(5000);

		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase mandatorycustominstructorclasslist in class LTILoginfields",e);
		}
		
	}
	
	@Test(priority = 7 , enabled = true)
	public void  verifymandatoryofcustominstructorclasslist()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("14");
			Thread.sleep(3000);
			new ComboBox().selectValue(0,"Kimmel_ClassB");
			Thread.sleep(3000);
			new ComboBox().selectValue(0,"studtitle");
			Thread.sleep(3000);
			new ComboBox().selectValue(0,"Kimmel_ClassA");
			Thread.sleep(3000);  
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase verifymandatoryofcustominstructorclasslist in class LTILoginfields",e);
		}
	}
	
	@Test(priority = 8, enabled = true)
	public void  igonrecustominstructorclasslistforstudent()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("15");
			String classname = new UserVerification().databaseverificationstring(1, "SELECT display_name FROM t_class where id = (SELECT class_id FROM t_class_section where id = (SELECT class_section_id FROM t_class_section_permission where user_id = (SELECT id FROM t_user where username = 'student15')))");
			System.out.println(classname);
			if(classname.equals("Kimmel_ClassA"))
			{
				Assert.fail("custom_instructor_classlist field is not ignored for student role");
			} 
			String classnamelast = new UserVerification().databaseverificationstring(1, "SELECT display_name FROM t_class where id = (SELECT class_id FROM t_class_section where id = (SELECT class_section_id FROM t_class_section_permission where user_id = (SELECT id FROM t_user where username = 'student15')))");
			System.out.println(classnamelast);
			if(classnamelast.equals("Kimmel_ClassB"))
			{
				Assert.fail("custom_instructor_classlist field is not ignored for student role(Second class section)");
			}
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase igonrecustominstructorclasslistforstudent in class LTILoginfields",e);
		}
	}
	
	@Test(priority = 9, enabled = true)
	public void  createnewclassscetionifnotexists()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("16");
			String classname = new UserVerification().databaseverificationstring(1, "SELECT display_name FROM t_class where id in (SELECT class_id FROM t_class_section where id in (SELECT class_section_id FROM t_class_section_permission where user_id in (SELECT id FROM t_user where username = 'instructor16')))");
			System.out.println(classname);
			if(!classname.equals("studtitle"))
			{
				Assert.fail("Not created a new class section if value entered in the field is not existed");
			} 			
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase createnewclassscetionifnotexists in class LTILoginfields",e);
		}
	}
	
	@Test(priority = 10, enabled = true)
	public void  customclassstateblank()
	{
		try
		{
			String user_id =  ReadTestData.readDataByTagName("", "user_id", "17");
			String role =  ReadTestData.readDataByTagName("", "Role", "17");
			driver.get(Config.baseLTIURL + "/");
			  		  
			driver.findElement(By.name("endpoint")).clear(); //Clear fields
			Thread.sleep(500);
			driver.findElement(By.name("endpoint")).sendKeys(Config.launchURL);
			driver.findElement(By.name("key")).clear();
			Thread.sleep(500);
			driver.findElement(By.name("key")).sendKeys(Config.customerkey);
			driver.findElement(By.name("secret")).clear();
			Thread.sleep(500);
			driver.findElement(By.name("secret")).sendKeys(Config.secretkey);
			driver.findElement(By.name("resource_link_id")).clear();
			Thread.sleep(500);
			driver.findElement(By.name("resource_link_id")).sendKeys(Config.resourselinkid);
			driver.findElement(By.name("user_id")).clear();
			Thread.sleep(500);
			driver.findElement(By.name("user_id")).sendKeys(user_id);
			driver.findElement(By.name("roles")).clear();
			Thread.sleep(500);
			driver.findElement(By.name("roles")).sendKeys(role);
			driver.findElement(By.name("lis_person_name_family")).clear();
			Thread.sleep(500);
			driver.findElement(By.name("lis_person_name_family")).sendKeys(Config.familyname);
			driver.findElement(By.name("lis_person_name_given")).clear();
			Thread.sleep(500);
			driver.findElement(By.name("lis_person_name_given")).sendKeys(Config.givenname);
			driver.findElement(By.name("lis_person_contact_email_primary")).clear();
			Thread.sleep(500);
			driver.findElement(By.name("lis_person_contact_email_primary")).sendKeys(Config.email);
			driver.findElement(By.name("context_id")).clear();
			Thread.sleep(500);
			driver.findElement(By.name("context_id")).sendKeys(Config.context_id);
			driver.findElement(By.name("context_title")).clear();
			Thread.sleep(500);
			driver.findElement(By.name("context_title")).sendKeys(Config.context_title);
			driver.findElement(By.name("tool_consumer_instance_guid")).clear();
			Thread.sleep(500);
			driver.findElement(By.name("tool_consumer_instance_guid")).sendKeys(Config.instance_guid);
			driver.findElement(By.name("tool_consumer_instance_name")).clear();
			Thread.sleep(500);
	    	driver.findElement(By.name("tool_consumer_instance_name")).sendKeys(Config.instance_name);
			driver.findElement(By.name("custom_courseid")).clear();
			Thread.sleep(500);
			driver.findElement(By.name("custom_courseid")).sendKeys(Config.courseid);
			driver.findElement(By.name("custom_destination")).clear();
			Thread.sleep(500);
			driver.findElement(By.name("custom_destination")).sendKeys(Config.custom_destination);
			driver.findElement(By.name("custom_domainid")).clear();
			Thread.sleep(500);
			driver.findElement(By.name("custom_domainid")).sendKeys(Config.custom_domainid);
			driver.findElement(By.name("custom_course_number")).clear();
			Thread.sleep(500);
			driver.findElement(By.name("custom_course_number")).sendKeys(Config.custom_course_number);
			driver.findElement(By.name("custom_domain_name")).sendKeys(Config.custom_domain_name);
			driver.findElement(By.cssSelector("input[name='custom_class_state']")).clear();
			driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();
			driver.findElement(By.cssSelector("form > input[type=\"submit\"]")).click();
			Thread.sleep(5000);

		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase customclassstateblank in class LTILoginfields",e);
		}
	}
	
	@Test(priority = 11, enabled = true)
	public void  customclassstatedefalutvalue()
	{
		try
		{
			driver.get(Config.baseLTIURL + "/");
			String customdomainnameblank = driver.findElement(By.cssSelector("input[name='custom_class_state']")).getAttribute("value");
			if(!customdomainnameblank.equals("Active"))
			{
				Assert.fail("customin_structor_classlist fieled not contains any default value");
			}
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase customclassstatedefalutvalue in class LTILoginfields",e);
		}
	}	
	
	@Test(priority = 12, enabled = true)
	public void  customlogouturlnotmandatory()
	{
		try
		{
			String user_id =  ReadTestData.readDataByTagName("", "user_id", "20");
			String role =  ReadTestData.readDataByTagName("", "Role", "20");
			driver.get(Config.baseLTIURL + "/");
			  		  
			driver.findElement(By.name("endpoint")).clear(); //Clear fields
			Thread.sleep(500);
			driver.findElement(By.name("endpoint")).sendKeys(Config.launchURL);
			driver.findElement(By.name("key")).clear();
			Thread.sleep(500);
			driver.findElement(By.name("key")).sendKeys(Config.customerkey);
			driver.findElement(By.name("secret")).clear();
			Thread.sleep(500);
			driver.findElement(By.name("secret")).sendKeys(Config.secretkey);
			driver.findElement(By.name("resource_link_id")).clear();
			Thread.sleep(500);
			driver.findElement(By.name("resource_link_id")).sendKeys(Config.resourselinkid);
			driver.findElement(By.name("user_id")).clear();
			Thread.sleep(500);
			driver.findElement(By.name("user_id")).sendKeys(user_id);
			driver.findElement(By.name("roles")).clear();
			Thread.sleep(500);
			driver.findElement(By.name("roles")).sendKeys(role);
			driver.findElement(By.name("lis_person_name_family")).clear();
			Thread.sleep(500);
			driver.findElement(By.name("lis_person_name_family")).sendKeys(Config.familyname);
			driver.findElement(By.name("lis_person_name_given")).clear();
			Thread.sleep(500);
			driver.findElement(By.name("lis_person_name_given")).sendKeys(Config.givenname);
			driver.findElement(By.name("lis_person_contact_email_primary")).clear();
			Thread.sleep(500);
			driver.findElement(By.name("lis_person_contact_email_primary")).sendKeys(Config.email);
			driver.findElement(By.name("context_id")).clear();
			Thread.sleep(500);
			driver.findElement(By.name("context_id")).sendKeys(Config.context_id);
			driver.findElement(By.name("context_title")).clear();
			Thread.sleep(500);
			driver.findElement(By.name("context_title")).sendKeys(Config.context_title);
			driver.findElement(By.name("tool_consumer_instance_guid")).clear();
			Thread.sleep(500);
			driver.findElement(By.name("tool_consumer_instance_guid")).sendKeys(Config.instance_guid);
			driver.findElement(By.name("tool_consumer_instance_name")).clear();
			Thread.sleep(500);
	    	driver.findElement(By.name("tool_consumer_instance_name")).sendKeys(Config.instance_name);
			driver.findElement(By.name("custom_courseid")).clear();
			Thread.sleep(500);
			driver.findElement(By.name("custom_courseid")).sendKeys(Config.courseid);
			driver.findElement(By.name("custom_destination")).clear();
			Thread.sleep(500);
			driver.findElement(By.name("custom_destination")).sendKeys(Config.custom_destination);
			driver.findElement(By.name("custom_domainid")).clear();
			Thread.sleep(500);
			driver.findElement(By.name("custom_domainid")).sendKeys(Config.custom_domainid);
			driver.findElement(By.name("custom_course_number")).clear();
			Thread.sleep(500);
			driver.findElement(By.name("custom_course_number")).sendKeys(Config.custom_course_number);
			driver.findElement(By.name("custom_domain_name")).sendKeys(Config.custom_domain_name);
			driver.findElement(By.cssSelector("input[name='custom_logout_url']")).clear();
			driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();
			driver.findElement(By.cssSelector("form > input[type=\"submit\"]")).click();
			Thread.sleep(5000);
			WebElement pagetext = driver.findElement(By.tagName("body"));
			if(!pagetext.getText().contains("Something went wrong in processing your request."))
			{
				Assert.fail("custom_logout_url is mandatary field.");
			}

		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase customlogouturlnotmandatory in class LTILoginfields",e);
		}
	}
	
	@Test(priority = 13, enabled = true)
	public void  customlogouturllogoutURL()
	{
		try
		{
			driver.get(Config.baseLTIURL + "/");
			String customdomainnameblank = driver.findElement(By.cssSelector("input[name='custom_logout_url']")).getAttribute("value");
			if(!customdomainnameblank.equals("http://localhost:8090/OauthClient/oAuthTest/action:logout"))
			{
				Assert.fail("custom_logout_url fieled not contains any default value");
			}
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase customlogouturllogoutURL in class LTILoginfields",e);
		}
	}
	
	@Test(priority = 14, enabled = true)
	public void  customcoursesettingsurlmandatory()
	{
		try
		{
			String user_id =  ReadTestData.readDataByTagName("", "user_id", "22");
			String role =  ReadTestData.readDataByTagName("", "Role", "22");
			driver.get(Config.baseLTIURL + "/");
			  		  
			driver.findElement(By.name("endpoint")).clear(); //Clear fields
			Thread.sleep(500);
			driver.findElement(By.name("endpoint")).sendKeys(Config.launchURL);
			driver.findElement(By.name("key")).clear();
			Thread.sleep(500);
			driver.findElement(By.name("key")).sendKeys(Config.customerkey);
			driver.findElement(By.name("secret")).clear();
			Thread.sleep(500);
			driver.findElement(By.name("secret")).sendKeys(Config.secretkey);
			driver.findElement(By.name("resource_link_id")).clear();
			Thread.sleep(500);
			driver.findElement(By.name("resource_link_id")).sendKeys(Config.resourselinkid);
			driver.findElement(By.name("user_id")).clear();
			Thread.sleep(500);
			driver.findElement(By.name("user_id")).sendKeys(user_id);
			driver.findElement(By.name("roles")).clear();
			Thread.sleep(500);
			driver.findElement(By.name("roles")).sendKeys(role);
			driver.findElement(By.name("lis_person_name_family")).clear();
			Thread.sleep(500);
			driver.findElement(By.name("lis_person_name_family")).sendKeys(Config.familyname);
			driver.findElement(By.name("lis_person_name_given")).clear();
			Thread.sleep(500);
			driver.findElement(By.name("lis_person_name_given")).sendKeys(Config.givenname);
			driver.findElement(By.name("lis_person_contact_email_primary")).clear();
			Thread.sleep(500);
			driver.findElement(By.name("lis_person_contact_email_primary")).sendKeys(Config.email);
			driver.findElement(By.name("context_id")).clear();
			Thread.sleep(500);
			driver.findElement(By.name("context_id")).sendKeys(Config.context_id);
			driver.findElement(By.name("context_title")).clear();
			Thread.sleep(500);
			driver.findElement(By.name("context_title")).sendKeys(Config.context_title);
			driver.findElement(By.name("tool_consumer_instance_guid")).clear();
			Thread.sleep(500);
			driver.findElement(By.name("tool_consumer_instance_guid")).sendKeys(Config.instance_guid);
			driver.findElement(By.name("tool_consumer_instance_name")).clear();
			Thread.sleep(500);
	    	driver.findElement(By.name("tool_consumer_instance_name")).sendKeys(Config.instance_name);
			driver.findElement(By.name("custom_courseid")).clear();
			Thread.sleep(500);
			driver.findElement(By.name("custom_courseid")).sendKeys(Config.courseid);
			driver.findElement(By.name("custom_destination")).clear();
			Thread.sleep(500);
			driver.findElement(By.name("custom_destination")).sendKeys(Config.custom_destination);
			driver.findElement(By.name("custom_domainid")).clear();
			Thread.sleep(500);
			driver.findElement(By.name("custom_domainid")).sendKeys(Config.custom_domainid);
			driver.findElement(By.name("custom_course_number")).clear();
			Thread.sleep(500);
			driver.findElement(By.name("custom_course_number")).sendKeys(Config.custom_course_number);
			driver.findElement(By.name("custom_domain_name")).sendKeys(Config.custom_domain_name);
			driver.findElement(By.cssSelector("input[name='custom_course_settings_url']")).clear();
			driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();
			driver.findElement(By.cssSelector("form > input[type=\"submit\"]")).click();
			Thread.sleep(5000);
			WebElement pagetext = driver.findElement(By.tagName("body"));
			if(!pagetext.getText().contains("Something went wrong in processing your request."))
			{
				Assert.fail("custom_course_settings_url is mandatary field.");
			}

		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase customcoursesettingsurlmandatory in class LTILoginfields",e);
		}
	}
	
	@Test(priority = 15, enabled = true)
	public void  customcoursesettingsurldefaultURL()
	{
		try
		{
			driver.get(Config.baseLTIURL + "/");
			String customdomainnameblank = driver.findElement(By.cssSelector("input[name='custom_course_settings_url']")).getAttribute("value");
			if(!customdomainnameblank.equals("http://localhost:8090/OauthClient/oAuthTest/action:courseSettings"))
			{
				Assert.fail("custom_course_settings_url fieled not contains any default value");
			}
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase customcoursesettingsurldefaultURL in class LTILoginfields",e);
		}
	}
	
	@Test(priority = 16, enabled = true)
	public void  customcontactusurlmandatory()
	{
		try
		{
			String user_id =  ReadTestData.readDataByTagName("", "user_id", "24");
			String role =  ReadTestData.readDataByTagName("", "Role", "24");
			driver.get(Config.baseLTIURL + "/");
			driver.findElement(By.name("endpoint")).clear(); //Clear fields
			Thread.sleep(500);
			driver.findElement(By.name("endpoint")).sendKeys(Config.launchURL);
			driver.findElement(By.name("key")).clear();
			Thread.sleep(500);
			driver.findElement(By.name("key")).sendKeys(Config.customerkey);
			driver.findElement(By.name("secret")).clear();
			Thread.sleep(500);
			driver.findElement(By.name("secret")).sendKeys(Config.secretkey);
			driver.findElement(By.name("resource_link_id")).clear();
			Thread.sleep(500);
			driver.findElement(By.name("resource_link_id")).sendKeys(Config.resourselinkid);
			driver.findElement(By.name("user_id")).clear();
			Thread.sleep(500);
			driver.findElement(By.name("user_id")).sendKeys(user_id);
			driver.findElement(By.name("roles")).clear();
			Thread.sleep(500);
			driver.findElement(By.name("roles")).sendKeys(role);
			driver.findElement(By.name("lis_person_name_family")).clear();
			Thread.sleep(500);
			driver.findElement(By.name("lis_person_name_family")).sendKeys(Config.familyname);
			driver.findElement(By.name("lis_person_name_given")).clear();
			Thread.sleep(500);
			driver.findElement(By.name("lis_person_name_given")).sendKeys(Config.givenname);
			driver.findElement(By.name("lis_person_contact_email_primary")).clear();
			Thread.sleep(500);
			driver.findElement(By.name("lis_person_contact_email_primary")).sendKeys(Config.email);
			driver.findElement(By.name("context_id")).clear();
			Thread.sleep(500);
			driver.findElement(By.name("context_id")).sendKeys(Config.context_id);
			driver.findElement(By.name("context_title")).clear();
			Thread.sleep(500);
			driver.findElement(By.name("context_title")).sendKeys(Config.context_title);
			driver.findElement(By.name("tool_consumer_instance_guid")).clear();
			Thread.sleep(500);
			driver.findElement(By.name("tool_consumer_instance_guid")).sendKeys(Config.instance_guid);
			driver.findElement(By.name("tool_consumer_instance_name")).clear();
			Thread.sleep(500);
	    	driver.findElement(By.name("tool_consumer_instance_name")).sendKeys(Config.instance_name);
			driver.findElement(By.name("custom_courseid")).clear();
			Thread.sleep(500);
			driver.findElement(By.name("custom_courseid")).sendKeys(Config.courseid);
			driver.findElement(By.name("custom_destination")).clear();
			Thread.sleep(500);
			driver.findElement(By.name("custom_destination")).sendKeys(Config.custom_destination);
			driver.findElement(By.name("custom_domainid")).clear();
			Thread.sleep(500);
			driver.findElement(By.name("custom_domainid")).sendKeys(Config.custom_domainid);
			driver.findElement(By.name("custom_course_number")).clear();
			Thread.sleep(500);
			driver.findElement(By.name("custom_course_number")).sendKeys(Config.custom_course_number);
			driver.findElement(By.name("custom_domain_name")).sendKeys(Config.custom_domain_name);
			driver.findElement(By.cssSelector("input[name='custom_contactus_url']")).clear();
			driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();
			driver.findElement(By.cssSelector("form > input[type=\"submit\"]")).click();
			Thread.sleep(5000);
			WebElement pagetext = driver.findElement(By.tagName("body"));
			if(!pagetext.getText().contains("Something went wrong in processing your request."))
			{
				Assert.fail("custom_contactus_url is mandatary field.");
			}

		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase customcontactusurlmandatory in class LTILoginfields",e);
		}
	}
	
	@Test(priority = 17, enabled = true)
	public void  customcontactusurldefaultURL()
	{
		try
		{
			driver.get(Config.baseLTIURL + "/");
			String customdomainnameblank = driver.findElement(By.cssSelector("input[name='custom_contactus_url']")).getAttribute("value");
			if(!customdomainnameblank.equals("http://localhost:8090/OauthClient/oAuthTest/action:contactUs"))
			{
				Assert.fail("custom_contactus_url fieled not contains any default value");
			}
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase customcontactusurldefaultURL in class LTILoginfields",e);
		}
	}
	
	@Test(priority = 18, enabled = true)
	public void  custommywileyplusurlmandatory()
	{
		try
		{
			String user_id =  ReadTestData.readDataByTagName("", "user_id", "26");
			String role =  ReadTestData.readDataByTagName("", "Role", "26");
			driver.get(Config.baseLTIURL + "/");
			driver.findElement(By.name("endpoint")).clear(); //Clear fields
			Thread.sleep(500);
			driver.findElement(By.name("endpoint")).sendKeys(Config.launchURL);
			driver.findElement(By.name("key")).clear();
			Thread.sleep(500);
			driver.findElement(By.name("key")).sendKeys(Config.customerkey);
			driver.findElement(By.name("secret")).clear();
			Thread.sleep(500);
			driver.findElement(By.name("secret")).sendKeys(Config.secretkey);
			driver.findElement(By.name("resource_link_id")).clear();
			Thread.sleep(500);
			driver.findElement(By.name("resource_link_id")).sendKeys(Config.resourselinkid);
			driver.findElement(By.name("user_id")).clear();
			Thread.sleep(500);
			driver.findElement(By.name("user_id")).sendKeys(user_id);
			driver.findElement(By.name("roles")).clear();
			Thread.sleep(500);
			driver.findElement(By.name("roles")).sendKeys(role);
			driver.findElement(By.name("lis_person_name_family")).clear();
			Thread.sleep(500);
			driver.findElement(By.name("lis_person_name_family")).sendKeys(Config.familyname);
			driver.findElement(By.name("lis_person_name_given")).clear();
			Thread.sleep(500);
			driver.findElement(By.name("lis_person_name_given")).sendKeys(Config.givenname);
			driver.findElement(By.name("lis_person_contact_email_primary")).clear();
			Thread.sleep(500);
			driver.findElement(By.name("lis_person_contact_email_primary")).sendKeys(Config.email);
			driver.findElement(By.name("context_id")).clear();
			Thread.sleep(500);
			driver.findElement(By.name("context_id")).sendKeys(Config.context_id);
			driver.findElement(By.name("context_title")).clear();
			Thread.sleep(500);
			driver.findElement(By.name("context_title")).sendKeys(Config.context_title);
			driver.findElement(By.name("tool_consumer_instance_guid")).clear();
			Thread.sleep(500);
			driver.findElement(By.name("tool_consumer_instance_guid")).sendKeys(Config.instance_guid);
			driver.findElement(By.name("tool_consumer_instance_name")).clear();
			Thread.sleep(500);
	    	driver.findElement(By.name("tool_consumer_instance_name")).sendKeys(Config.instance_name);
			driver.findElement(By.name("custom_courseid")).clear();
			Thread.sleep(500);
			driver.findElement(By.name("custom_courseid")).sendKeys(Config.courseid);
			driver.findElement(By.name("custom_destination")).clear();
			Thread.sleep(500);
			driver.findElement(By.name("custom_destination")).sendKeys(Config.custom_destination);
			driver.findElement(By.name("custom_domainid")).clear();
			Thread.sleep(500);
			driver.findElement(By.name("custom_domainid")).sendKeys(Config.custom_domainid);
			driver.findElement(By.name("custom_course_number")).clear();
			Thread.sleep(500);
			driver.findElement(By.name("custom_course_number")).sendKeys(Config.custom_course_number);
			driver.findElement(By.name("custom_domain_name")).sendKeys(Config.custom_domain_name);
			driver.findElement(By.cssSelector("input[name='custom_my_wileyplus_url']")).clear();
			driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();
			driver.findElement(By.cssSelector("form > input[type=\"submit\"]")).click();
			Thread.sleep(5000);
			WebElement pagetext = driver.findElement(By.tagName("body"));
			if(!pagetext.getText().contains("Something went wrong in processing your request."))
			{
				Assert.fail("custom_my_wileyplus_url is mandatary field.");
			}

		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase custommywileyplusurlmandatory in class LTILoginfields",e);
		}
	}
	
	@Test(priority = 19, enabled = true)
	public void  custommywileypludefaulttURL()
	{
		try
		{
			driver.get(Config.baseLTIURL + "/");
			String customdomainnameblank = driver.findElement(By.cssSelector("input[name='custom_my_wileyplus_url']")).getAttribute("value");
			if(!customdomainnameblank.equals("http://localhost:8090/OauthClient/oAuthTest/action:mywileyPlus"))
			{
				Assert.fail("custom_my_wileyplus_url fieled not contains any default value");
			}
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase custommywileypludefaulttURL in class LTILoginfields",e);
		}
	}

}
