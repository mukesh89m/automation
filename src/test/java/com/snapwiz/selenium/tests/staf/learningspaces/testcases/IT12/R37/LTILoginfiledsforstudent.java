package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT12.R37;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Logout;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Screenshot;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.MouseHover;

public class LTILoginfiledsforstudent extends Driver
{
	@Test(priority = 1, enabled = true)
	public void  newStudentClassSection()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("32");
			String classname = new UserVerification().databaseverificationstring(1, "SELECT display_name FROM t_class_section where id in (SELECT class_section_id FROM t_class_section_permission where user_id in (SELECT id FROM t_user where username = 'student32'))");
			System.out.println(classname);
			if(!classname.equals("TestnewClssection"))
			{
				Assert.fail("Student is not log in to class section mentioned under context_id");
			} 			
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase newstudentclasssection in class LTILoginfiledsforstudent",e);
		}
	}

	@Test(priority = 2, enabled = true)
	public void  ignoreCustomInstructorClassListFiledForStudent()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("33");
			String classname = new UserVerification().databaseverificationstring(1, "SELECT display_name FROM t_class_section where id in (SELECT class_section_id FROM t_class_section_permission where user_id in (SELECT id FROM t_user where username = 'student33'))");
			System.out.println(classname);
			if(classname.equals("Kimmel_ClassA"))
			{
				Assert.fail("Class(Kimmel_ClassA) mentioned in custom_instructor_classlist field json string is not ignored for the student role");
			} 	
			String lastclassname = new UserVerification().databaseverificationstring(1, "SELECT display_name FROM t_class_section where id in (SELECT class_section_id FROM t_class_section_permission where user_id in (SELECT id FROM t_user where username = 'student33'))");
			System.out.println(lastclassname);
			if(lastclassname.equals("Kimmel_ClassB"))
			{
				Assert.fail("Class(Kimmel_ClassB) mentioned in custom_instructor_classlist field json string is not ignored for the student role");
			} 
			
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase ignorecustominstructorclasslistfiledforstudent in class LTILoginfiledsforstudent",e);
		}
	}

	@Test(priority = 3, enabled = true)
	public void  lastValueFromCustomInstructorClassListFiledForStudent()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("34");
			String lastclassname = new UserVerification().databaseverificationstring(1, "SELECT name FROM t_class_section where id in (SELECT class_section_id FROM t_class_section_permission where user_id in (SELECT id FROM t_user where username = 'student34'))");
			System.out.println(lastclassname);
			if(!lastclassname.equals("Kimmel_ClassB"))
			{
				Assert.fail("Class(Kimmel_ClassB) mentioned in custom_instructor_classlist field json string is not ignored for the student role");
			} 
			
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase lastvaluefromcustominstructorclasslistfiledforstudent in class LTILoginfiledsforstudent",e);
		}
	}
	
	@Test(priority = 4, enabled = true)
	public void  ignoreValueFromCustomInstructorClassListFiledForStudent()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("35");
			String classsectionname = new UserVerification().databaseverificationstring(1, "SELECT name FROM t_class_section where id in (SELECT class_section_id FROM t_class_section_permission where user_id in (SELECT id FROM t_user where username = 'student35'))");
			System.out.println(classsectionname);
			if(classsectionname.equals("Kimmel_ClassA"))
			{
				Assert.fail("Class(Kimmel_ClassA) mentioned in custom_instructor_classlist field json string is not ignored for the student role");
			} 
			
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase ignorevaluefromcustominstructorclasslistfiledforstudent in class LTILoginfiledsforstudent",e);
		}
	}
	
	@Test(priority = 5, enabled = true)
	public void  logoutURLforLScourse()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("36");
			Thread.sleep(2000);
			new Logout().logout();
			Thread.sleep(1000);
			String logoutURL = driver.getCurrentUrl();
			System.out.println("CurrentURL"+logoutURL);
			if(!logoutURL.equals("http://localhost:8090/OauthClient/oAuthTest/action:logout"))
			{
				Assert.fail("After logout student is not navigate to url as mentioned under custom_logout_url field on LTI page");
			}					    
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase logoutURLforLScourse in class LTILoginfiledsforstudent(LS Course)",e);
		}
	}
	
	@Test(priority = 6, enabled = true)
	public void  logoutURLforLSadaptivecourse()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("37");
			Thread.sleep(2000);
			new Logout().logout();
			Thread.sleep(1000);
			String logoutURL = driver.getCurrentUrl();
			System.out.println("CurrentURL"+logoutURL);
			if(!logoutURL.equals("http://localhost:8090/OauthClient/oAuthTest/action:logout"))
			{
				Assert.fail("Click logout is not navigate to url as mentioned under custom_logout_url field on LTI page(LSadaptive Course)");
			}					    
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase logoutURLforLSadaptivecourse in class LTILoginfiledsforstudent",e);
		}
	}
	
	@Test(priority = 7, enabled = true)
	public void  clickmyWileyPLUSlinkforLScourse()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("38");
			Thread.sleep(2000);
			new MouseHover().mouserhover("ls-user-nav__username");
			driver.findElement(By.linkText("My WileyPLUS")).click();
			String wileyplusurl = driver.getCurrentUrl();
			System.out.println("WileyplusURL:"+wileyplusurl);
			if(!wileyplusurl.equals("http://localhost:8090/OauthClient/oAuthTest/action:mywileyPlus"))
			{
				Assert.fail("Click My wileyplus is navigate to url as mentioned under custom_my_wileyplus_url field on LTI page(LS Course)");
			}					    
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase clickmyWileyPLUSlinkforLScourse in class LTILoginfiledsforstudent",e);
		}
	}
	
	@Test(priority = 8, enabled = true)
	public void  clickmyWileyPLUSlinkforLSadaptivecourse()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("39");
			Thread.sleep(2000);
			new MouseHover().mouserhover("ls-user-nav__username");
			driver.findElement(By.linkText("My WileyPLUS")).click();
			String wileyplusurl = driver.getCurrentUrl();
			System.out.println("WileyplusURL:"+wileyplusurl);
			if(!wileyplusurl.equals("http://localhost:8090/OauthClient/oAuthTest/action:mywileyPlus"))
			{
				Assert.fail("Click My wileyplus is navigate to url as mentioned under custom_my_wileyplus_url field on LTI page(LSadaptive Course)");
			}					    
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase clickmyWileyPLUSlinkforLSadaptivecourse in class LTILoginfiledsforstudent",e);
		}
	}
	
	@Test(priority = 9, enabled = true)
	public void  clickcontactusforLScourse()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("40");
			Thread.sleep(2000);
			String parentHandle = driver.getWindowHandle();
			new MouseHover().mouserhover("ls-user-nav__username");			
			driver.findElement(By.linkText("Contact Us")).click();
			Thread.sleep(9000);
			for (String winHandle : driver.getWindowHandles())
		    {
		     driver.switchTo().window(winHandle); // switch focus of WebDriver to the next found window handle (that's your newly opened window)
		    }
			String ContactUs = driver.getCurrentUrl();
			System.out.println("WileyplusURL:"+ContactUs);
			if(!ContactUs.equals("http://localhost:8090/OauthClient/oAuthTest/action:contactUs"))
			{
				Assert.fail("Click Contact Us is navigate to url as mentioned under custom_contactus_url field on LTI page(LS Course)");
			}			
			driver.close();
			driver.switchTo().window(parentHandle);
		}
		
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase clickcontactusforLScourse in class LTILoginfiledsforstudent",e);
		}
	}
	
	@Test(priority = 10, enabled = true)
	public void  clickcontactusforLSadapativecourse()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("41");
			Thread.sleep(2000);
			String parentHandle = driver.getWindowHandle();
			new MouseHover().mouserhover("ls-user-nav__username");			
			driver.findElement(By.linkText("Contact Us")).click();
			Thread.sleep(9000);
			for (String winHandle : driver.getWindowHandles())
		    {
		     driver.switchTo().window(winHandle); // switch focus of WebDriver to the next found window handle (that's your newly opened window)
		    }
			String ContactUs = driver.getCurrentUrl();
			System.out.println("WileyplusURL:"+ContactUs);
			if(!ContactUs.equals("http://localhost:8090/OauthClient/oAuthTest/action:contactUs"))
			{
				Assert.fail("Click Contact Us is navigate to url as mentioned under custom_contactus_url field on LTI page(LSadaptive Course)");
			}			
			driver.close();
			driver.switchTo().window(parentHandle);
		}
		
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase clickcontactusforLSadapativecourse in class LTILoginfiledsforstudent",e);
		}
	}
	
	@Test(priority = 11, enabled = true)
	public void  clickCourseSettingsforLScourse()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("42");
			Thread.sleep(2000);
			new MouseHover().mouserhover("ls-user-nav__username");
			driver.findElement(By.linkText("Course Settings")).click();
			Thread.sleep(2000);
			String CourseSettingsURL = driver.getCurrentUrl();
			System.out.println("CourseSettingsURL:"+CourseSettingsURL);
			if(!CourseSettingsURL.equals("http://localhost:8090/OauthClient/oAuthTest/action:courseSettings"))
			{
				Assert.fail("Click My wileyplus is navigate to url as mentioned under custom_course_settings_url field on LTI page(LS Course)");
			}					    
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase clickCourseSettingsforLScourse in class LTILoginfiledsforstudent",e);
		}
	}
	
	@Test(priority = 12, enabled = true)
	public void  clickCourseSettingsforLSadaptivecourse()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("43");
			Thread.sleep(2000);
			new MouseHover().mouserhover("ls-user-nav__username");
			driver.findElement(By.linkText("Course Settings")).click();
			Thread.sleep(2000);
			String CourseSettingsURL = driver.getCurrentUrl();
			System.out.println("CourseSettingsURL:"+CourseSettingsURL);
			if(!CourseSettingsURL.equals("http://localhost:8090/OauthClient/oAuthTest/action:courseSettings"))
			{
				Assert.fail("Click My wileyplus is navigate to url as mentioned under custom_course_settings_url field on LTI page(LSadaptive Course)");
			}					    
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase clickCourseSettingsforLSadaptivecourse in class LTILoginfiledsforstudent",e);
		}
	}
	
	@Test(priority = 13, enabled = true)
	public void  orionstudentprofledropdown()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("44");
			Thread.sleep(2000);
			int closewelcomepage=driver.findElements(By.cssSelector("div[class='al-dialog-welcome-close-icon']")).size();
		    if(closewelcomepage>=1)
		    {
		    	driver.findElement(By.cssSelector("div[class='al-dialog-welcome-close-icon']")).click();
		    	Thread.sleep(2000);
		    }
			Thread.sleep(1000);
			driver.findElement(By.className("al-user-profile-name")).click();
			Thread.sleep(1000);
			boolean allactivity = driver.findElement(By.id("ls-my-Activity")).isDisplayed();
			System.out.println("Output:"+allactivity);
			if(allactivity==false)
			{
				Assert.fail("All Activity option is not present in orion student profile dropdown");
			}
			boolean myReport = driver.findElement(By.id("myReport")).isDisplayed();
			System.out.println("Output:"+myReport);
			if(myReport==false)
			{
				Assert.fail("My Reports option is not present in orion student profile dropdown");
			}
			boolean myProfile = driver.findElement(By.id("myProfile")).isDisplayed();
			System.out.println("Output:"+myProfile);
			if(myProfile==false)
			{
				Assert.fail("My Profile option is not present in orion student profile dropdown");
			}
			boolean help = driver.findElement(By.id("help")).isDisplayed();
			System.out.println("Output:"+help);
			if(help==false)
			{
				Assert.fail("Help option is not present in orion student profile dropdown");
			}
			
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase orionstudentprofledropdown in class LTILoginfiledsforstudent",e);
		}
	}
	
	@Test(priority = 14, enabled = true)
	public void  optionsnotexistedinorionstudentprofledropdown()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("45");
			Thread.sleep(2000);
			int closewelcomepage=driver.findElements(By.cssSelector("div[class='al-dialog-welcome-close-icon']")).size();
		    if(closewelcomepage>=1)
		    {
		    	driver.findElement(By.cssSelector("div[class='al-dialog-welcome-close-icon']")).click();
		    	Thread.sleep(2000);
		    }
			Thread.sleep(1000);
			driver.findElement(By.className("al-user-profile-name")).click();
			Thread.sleep(1000);
			int ContactUs = driver.findElements(By.linkText("Contact Us")).size();
			if(ContactUs==1)
			{
				Assert.fail("Contact Us option is present in orion student profile dropdown");
			}
			int mywileyplus = driver.findElements(By.linkText("My WileyPLUS")).size();
			if(mywileyplus==1)
			{
				Assert.fail("My Wileyplus option is present in orion student profile dropdown");
			}
			int coursesettings = driver.findElements(By.linkText("Course Settings")).size();
			if(coursesettings==1)
			{
				Assert.fail("Sourse Settings option is present in orion student profile dropdown");
			}
			int Logout = driver.findElements(By.linkText("Logout")).size();
			if(Logout==1)
			{
				Assert.fail("Logout option is present in orion student profile dropdown");
			}
			
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase optionsnotexistedinorionstudentprofledropdown in class LTILoginfiledsforstudent",e);
		}
	}
	
	@Test(priority = 15, enabled = true)
	public void  instructorprofledropdownoptions()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("46");
			Thread.sleep(2000);
			new MouseHover().mouserhover("ls-user-nav__username");
			int ContactUs = driver.findElements(By.linkText("Contact Us")).size();
			if(ContactUs==0)
			{
				Assert.fail("Contact Us option is not present in instructor profile dropdown");
			}
			int mywileyplus = driver.findElements(By.linkText("My WileyPLUS")).size();
			if(mywileyplus==0)
			{
				Assert.fail("My Wileyplus option is not present in instructor profile dropdown");
			}
			int coursesettings = driver.findElements(By.linkText("Course Settings")).size();
			if(coursesettings==0)
			{
				Assert.fail("Sourse Settings option is not present in instructor profile dropdown");
			}
			int Logout = driver.findElements(By.linkText("Logout")).size();
			if(Logout==0)
			{
				Assert.fail("Logout option is not present in instructor dropdown");
			}
			
			
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase instructorprofledropdownoptions in class LTILoginfiledsforstudent",e);
		}
	}
	
	@Test(priority = 16, enabled = true)
	public void  mentorprofledropdownoptions()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("46_1");
			Thread.sleep(2000);
			new MouseHover().mouserhover("ls-user-nav__username");
			Thread.sleep(1000);
			int ContactUs = driver.findElements(By.linkText("Contact Us")).size();
			if(ContactUs==0)
			{
				Assert.fail("Contact Us option is not present in mentor profile dropdown");
			}
			int mywileyplus = driver.findElements(By.linkText("My WileyPLUS")).size();
			if(mywileyplus==0)
			{
				Assert.fail("My Wileyplus option is not present in mentor profile dropdown");
			}
			int coursesettings = driver.findElements(By.linkText("Course Settings")).size();
			if(coursesettings==0)
			{
				Assert.fail("Sourse Settings option is not present in mentor profile dropdown");
			}
			int Logout = driver.findElements(By.linkText("Logout")).size();
			if(Logout==0)
			{
				Assert.fail("Logout option is not present in mentor dropdown");
			}
			
			
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase mentorprofledropdownoptions in class LTILoginfiledsforstudent",e);
		}
	}

}
