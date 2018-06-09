package com.snapwiz.selenium.tests.staf.learningspaces.apphelper;

import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.UIElement;
import org.openqa.selenium.By;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.snapwiz.selenium.tests.staf.learningspaces.Config;
import com.snapwiz.selenium.tests.staf.framework.controller.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;



public class LoginUsingLTI extends Driver {
	public static String appendChar;
	public LoginUsingLTI(){
		Config.readconfiguration();
	}
	/* Method for LTI login for learning spaces. This method enters LTI login details like end point and keys. The login will fail is the text verified
	   * is not present on the loaded page after clicking the launch button. */
	public void ltiLogin(String dataIndex)
	{
		appendChar = System.getProperty("UCHAR");
		String user_id =  ReadTestData.readDataByTagName("", "user_id", dataIndex);
		String role =  ReadTestData.readDataByTagName("", "Role", dataIndex);
		String studentUserName =  ReadTestData.readDataByTagName("", "studentUserName", dataIndex);
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
		String instance_guid = ReadTestData.readDataByTagName("", "instance_guid", dataIndex);  // valid values - 'ls', 'adaptive'.
		String custom_domainid = ReadTestData.readDataByTagName("", "custom_domainid", dataIndex);
		String custom_roster_changes = ReadTestData.readDataByTagName("", "custom_roster_changes", dataIndex);
		String launchURL = ReadTestData.readDataByTagName("", "launchURL", dataIndex);
		String custom_lms_type= ReadTestData.readDataByTagName("","custom_lms_type", dataIndex);
		String lti_message_type=ReadTestData.readDataByTagName("","lti_message_type", dataIndex);
		String content_item_return_url=ReadTestData.readDataByTagName("","content_item_return_url",dataIndex);
		String oauth_callback=ReadTestData.readDataByTagName("","oauth_callback",dataIndex);
		String launch_presentation_document_target=ReadTestData.readDataByTagName("","launch_presentation_document_target",dataIndex);
		WebDriver driver=Driver.getWebDriver();


		try
		{
			if(appendChar == null ) {
				appendChar = "sd1";

			}

			driver.get(Config.baseLTIURL + "/");

			driver.findElement(By.name("endpoint")).clear(); //Clear fields
			new UIElement().waitAndFindElement(By.name("endpoint"));

			if(launchURL == null) {
				driver.findElement(By.name("endpoint")).sendKeys(Config.launchURL);
			}
			else {
				driver.findElement(By.name("endpoint")).sendKeys(launchURL);
			}
			//custom_lms_type

			driver.findElement(By.name("custom_lms_type")).sendKeys(custom_lms_type);
			//lti_message_type
			//driver.findElement(By.name("lti_message_type")).clear();
			new UIElement().waitAndFindElement(By.name("lti_message_type"));
			if(lti_message_type!=null) {
				driver.findElement(By.name("lti_message_type")).clear();
				driver.findElement(By.name("lti_message_type")).sendKeys(lti_message_type);
			}
			//content_item_return_url
			driver.findElement(By.name("content_item_return_url")).sendKeys(content_item_return_url);
			//oauth_callback
			driver.findElement(By.name("oauth_callback")).sendKeys(oauth_callback);
			//launch_presentation_document_target
			new UIElement().waitAndFindElement(By.name("launch_presentation_document_target"));
			if(launch_presentation_document_target!=null) {
				driver.findElement(By.name("launch_presentation_document_target")).clear();
				driver.findElement(By.name("launch_presentation_document_target")).sendKeys(launch_presentation_document_target);
			}
			driver.findElement(By.name("key")).clear();
			new UIElement().waitAndFindElement(By.name("key"));
			driver.findElement(By.name("key")).sendKeys(Config.customerkey);

			driver.findElement(By.name("secret")).clear();
			new UIElement().waitAndFindElement(By.name("secret"));
			driver.findElement(By.name("secret")).sendKeys(Config.secretkey);

			driver.findElement(By.name("resource_link_id")).clear();
			if(resource_link_id == null)
				driver.findElement(By.name("resource_link_id")).sendKeys(Config.resourselinkid);
			else
				driver.findElement(By.name("resource_link_id")).sendKeys(resource_link_id);

			driver.findElement(By.name("user_id")).clear();
			new UIElement().waitAndFindElement(By.name("user_id"));
			driver.findElement(By.name("user_id")).sendKeys(user_id+appendChar);

			driver.findElement(By.name("roles")).clear();
			new UIElement().waitAndFindElement(By.name("roles"));
			driver.findElement(By.name("roles")).sendKeys(role);

			driver.findElement(By.name("lis_person_name_family")).clear();
			if(familyname == null)
				driver.findElement(By.name("lis_person_name_family")).sendKeys(Config.familyname);
			else
				driver.findElement(By.name("lis_person_name_family")).sendKeys(familyname);

			driver.findElement(By.name("lis_person_name_given")).clear();

			if(givenname == null)
				driver.findElement(By.name("lis_person_name_given")).sendKeys(Config.givenname);
			else
				driver.findElement(By.name("lis_person_name_given")).sendKeys(givenname);

			driver.findElement(By.name("lis_person_contact_email_primary")).clear();
			new UIElement().waitAndFindElement(By.name("lis_person_contact_email_primary"));
			driver.findElement(By.name("lis_person_contact_email_primary")).sendKeys(Config.email);

			driver.findElement(By.name("context_id")).clear();
			if(context_id == null)
				driver.findElement(By.name("context_id")).sendKeys(Config.context_id+appendChar);
			else
				driver.findElement(By.name("context_id")).sendKeys(context_id+appendChar);

			driver.findElement(By.name("context_title")).clear();
			if(context_title == null)
				driver.findElement(By.name("context_title")).sendKeys(Config.context_title);
			else
				driver.findElement(By.name("context_title")).sendKeys(context_title);

			driver.findElement(By.name("tool_consumer_instance_guid")).clear();
			if(instance_guid == null)
				driver.findElement(By.name("tool_consumer_instance_guid")).sendKeys(Config.instance_guid);
			else
				driver.findElement(By.name("tool_consumer_instance_guid")).sendKeys(instance_guid);

			driver.findElement(By.name("tool_consumer_instance_name")).clear();
			new UIElement().waitAndFindElement(By.name("tool_consumer_instance_name"));
			driver.findElement(By.name("tool_consumer_instance_name")).sendKeys(Config.instance_name);

			driver.findElement(By.name("custom_courseid")).clear();
			if(custom_courseid == null)
			{
				if(course_type == null)
					driver.findElement(By.name("custom_courseid")).sendKeys(Config.courseid);
				else if(course_type.equalsIgnoreCase("ls"))
					driver.findElement(By.name("custom_courseid")).sendKeys(Config.lsCourseId);
				else if(course_type.equalsIgnoreCase("adaptive"))
					driver.findElement(By.name("custom_courseid")).sendKeys(Config.adaptiveCourseID);
			}
			else
				driver.findElement(By.name("custom_courseid")).sendKeys(custom_courseid);

			driver.findElement(By.name("custom_roster_changes")).clear();
			if(custom_roster_changes != null){
				custom_roster_changes="{\"custom_course_model\":\"LS\",\"custom_domain_id\":\""+Config.custom_domainid+appendChar+"\",\"roster_update\":[{\"classid\":\""+context_id+appendChar+"\",\"userid\":\""+studentUserName+appendChar+"\",\"status\":\"Removed\"}]}";
				driver.findElement(By.name("custom_roster_changes")).sendKeys(custom_roster_changes);
			}

			driver.findElement(By.name("custom_destination")).clear();
			if(custom_destination == null)
				driver.findElement(By.name("custom_destination")).sendKeys(Config.custom_destination);
			else
				driver.findElement(By.name("custom_destination")).sendKeys(custom_destination);

			if(custom_domainid == null){
				driver.findElement(By.name("custom_domainid")).clear();
				new UIElement().waitAndFindElement(By.name("custom_domainid"));
				driver.findElement(By.name("custom_domainid")).sendKeys(Config.custom_domainid+appendChar);
			}else
				driver.findElement(By.name("custom_domainid")).sendKeys(custom_domainid+appendChar);

			driver.findElement(By.name("custom_course_number")).clear();
			if(custom_course_number == null)
				driver.findElement(By.name("custom_course_number")).sendKeys(Config.custom_course_number);
			else
				driver.findElement(By.name("custom_course_number")).sendKeys(custom_course_number);

			if(custom_domain_name == null)
				driver.findElement(By.name("custom_domain_name")).sendKeys(Config.custom_domain_name+appendChar);
			else
				driver.findElement(By.name("custom_domain_name")).sendKeys(custom_domain_name+appendChar);
			if(custom_instructor_classlist == null)
				driver.findElement(By.name("custom_instructor_classlist")).sendKeys(Config.custom_instructor_classlist);
			else
				driver.findElement(By.name("custom_instructor_classlist")).sendKeys(custom_instructor_classlist);

			driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();
			driver.findElement(By.cssSelector("form > input[type=\"submit\"]")).click();

			try {
				String unExpected=driver.switchTo().alert().getText();
				System.out.println("unExpected Error:"+unExpected);
				driver.switchTo().alert().accept();
			} catch (Exception e) {
			}

			//new HelpDisable().helpDisable(user_id+appendChar);
			//Thread.sleep(3000);
			if(expectederror == null)
			{
				boolean textPresent = new TextValidate().IsTextPresent("Something went wrong in processing your request.");
				boolean woopsieText = new TextValidate().IsTextPresent("We encountered a problem while processing your request.");

				if(textPresent == true || woopsieText ==true)
				{
					driver.quit();
					Assert.fail("LTI login with course id "+Config.courseid+" Failed");
				}

			}
			if(Config.browser.equals("ie"))
				driver.navigate().refresh();
			if(!Config.browser.equalsIgnoreCase("firefox")) {
				if(driver.findElements(By.className("swhelp-button-cancel")).size() > 0)
					driver.findElement(By.className("swhelp-button-cancel")).click();
            /*if(driver.findElements(By.className("swhelp-button-cancel-info")).size() > 0)
                driver.findElement(By.className("swhelp-button-cancel-info")).click();*/
				//Thread.sleep(5000);
			}
		}
		catch(Exception e)
		{
			driver.quit();
			Assert.fail("Exception in LoginUsingLTI Application Helper",e);

		}

	}


	public void ltiLogin(String dataIndex,String userId)
	{
		WebDriver driver=Driver.getWebDriver();
		String user_id =  userId;
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
		String instance_guid = ReadTestData.readDataByTagName("", "instance_guid", dataIndex);  // valid values - 'ls', 'adaptive'.
		String custom_domainid = ReadTestData.readDataByTagName("", "custom_domainid", dataIndex);
		String custom_roster_changes = ReadTestData.readDataByTagName("", "custom_roster_changes", dataIndex);
		String launchURL = ReadTestData.readDataByTagName("", "launchURL", dataIndex);


		try
		{
			String appendChar="a";


			driver.get(Config.baseLTIURL + "/");

			driver.findElement(By.name("endpoint")).clear(); //Clear fields
			new UIElement().waitAndFindElement(By.name("endpoint"));

			if(launchURL == null) {
				driver.findElement(By.name("endpoint")).sendKeys(Config.launchURL);
			}
			else {
				driver.findElement(By.name("endpoint")).sendKeys(launchURL);
			}

			driver.findElement(By.name("key")).clear();
			new UIElement().waitAndFindElement(By.name("key"));
			driver.findElement(By.name("key")).sendKeys(Config.customerkey);

			driver.findElement(By.name("secret")).clear();
			new UIElement().waitAndFindElement(By.name("secret"));
			driver.findElement(By.name("secret")).sendKeys(Config.secretkey);

			driver.findElement(By.name("resource_link_id")).clear();
			if(resource_link_id == null)
				driver.findElement(By.name("resource_link_id")).sendKeys(Config.resourselinkid);
			else
				driver.findElement(By.name("resource_link_id")).sendKeys(resource_link_id);

			driver.findElement(By.name("user_id")).clear();
			new UIElement().waitAndFindElement(By.name("user_id"));
			driver.findElement(By.name("user_id")).sendKeys(user_id+appendChar);

			driver.findElement(By.name("roles")).clear();
			new UIElement().waitAndFindElement(By.name("roles"));
			driver.findElement(By.name("roles")).sendKeys(role);

			driver.findElement(By.name("lis_person_name_family")).clear();
			if(familyname == null)
				driver.findElement(By.name("lis_person_name_family")).sendKeys(Config.familyname);
			else
				driver.findElement(By.name("lis_person_name_family")).sendKeys(familyname);

			driver.findElement(By.name("lis_person_name_given")).clear();

			if(givenname == null)
				driver.findElement(By.name("lis_person_name_given")).sendKeys(Config.givenname);
			else
				driver.findElement(By.name("lis_person_name_given")).sendKeys(givenname);

			driver.findElement(By.name("lis_person_contact_email_primary")).clear();
			new UIElement().waitAndFindElement(By.name("lis_person_contact_email_primary"));
			driver.findElement(By.name("lis_person_contact_email_primary")).sendKeys(Config.email);

			driver.findElement(By.name("context_id")).clear();
			if(context_id == null)
				driver.findElement(By.name("context_id")).sendKeys(Config.context_id+appendChar);
			else
				driver.findElement(By.name("context_id")).sendKeys(context_id+appendChar);

			driver.findElement(By.name("context_title")).clear();
			if(context_title == null)
				driver.findElement(By.name("context_title")).sendKeys(Config.context_title);
			else
				driver.findElement(By.name("context_title")).sendKeys(context_title);

			driver.findElement(By.name("tool_consumer_instance_guid")).clear();
			if(instance_guid == null)
				driver.findElement(By.name("tool_consumer_instance_guid")).sendKeys(Config.instance_guid);
			else
				driver.findElement(By.name("tool_consumer_instance_guid")).sendKeys(instance_guid);

			driver.findElement(By.name("tool_consumer_instance_name")).clear();
			new UIElement().waitAndFindElement(By.name("tool_consumer_instance_name"));
			driver.findElement(By.name("tool_consumer_instance_name")).sendKeys(Config.instance_name);

			driver.findElement(By.name("custom_courseid")).clear();
			if(custom_courseid == null)
			{
				if(course_type == null)
					driver.findElement(By.name("custom_courseid")).sendKeys(Config.courseid);
				else if(course_type.equalsIgnoreCase("ls"))
					driver.findElement(By.name("custom_courseid")).sendKeys(Config.lsCourseId);
				else if(course_type.equalsIgnoreCase("adaptive"))
					driver.findElement(By.name("custom_courseid")).sendKeys(Config.adaptiveCourseID);
			}
			else
				driver.findElement(By.name("custom_courseid")).sendKeys(custom_courseid);

			driver.findElement(By.name("custom_roster_changes")).clear();
			if(custom_roster_changes != null){
				driver.findElement(By.name("custom_roster_changes")).sendKeys(custom_roster_changes);

			}

			driver.findElement(By.name("custom_destination")).clear();
			if(custom_destination == null)
				driver.findElement(By.name("custom_destination")).sendKeys(Config.custom_destination);
			else
				driver.findElement(By.name("custom_destination")).sendKeys(custom_destination);

			if(custom_domainid == null){
				driver.findElement(By.name("custom_domainid")).clear();
				new UIElement().waitAndFindElement(By.name("custom_domainid"));
				driver.findElement(By.name("custom_domainid")).sendKeys(Config.custom_domainid+appendChar);
			}else
				driver.findElement(By.name("custom_domainid")).sendKeys(custom_domainid+appendChar);

			driver.findElement(By.name("custom_course_number")).clear();
			if(custom_course_number == null)
				driver.findElement(By.name("custom_course_number")).sendKeys(Config.custom_course_number);
			else
				driver.findElement(By.name("custom_course_number")).sendKeys(custom_course_number);

			if(custom_domain_name == null)
				driver.findElement(By.name("custom_domain_name")).sendKeys(Config.custom_domain_name+appendChar);
			else
				driver.findElement(By.name("custom_domain_name")).sendKeys(custom_domain_name+appendChar);
			if(custom_instructor_classlist == null)
				driver.findElement(By.name("custom_instructor_classlist")).sendKeys(Config.custom_instructor_classlist);
			else
				driver.findElement(By.name("custom_instructor_classlist")).sendKeys(custom_instructor_classlist);

			driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();
			driver.findElement(By.cssSelector("form > input[type=\"submit\"]")).click();
			try {
				String unExpected=driver.switchTo().alert().getText();
				System.out.println("unExpected Error:"+unExpected);
				driver.switchTo().alert().accept();
			} catch (Exception e) {
			}

			if(expectederror == null)
			{
				boolean textPresent = new TextValidate().IsTextPresent("Something went wrong in processing your request.");
				boolean woopsieText = new TextValidate().IsTextPresent("We encountered a problem while processing your request.");
				if(textPresent == true || woopsieText ==true)
				{
					driver.quit();
					Assert.fail("LTI login with course id "+Config.courseid+" Failed");
				}

			}
			if(!Config.browser.equalsIgnoreCase("firefox")) {
				if(driver.findElements(By.className("swhelp-button-cancel")).size() > 0)
					driver.findElement(By.className("swhelp-button-cancel")).click();

			}
		}
		catch(Exception e)
		{
			driver.quit();
			Assert.fail("Exception in LoginUsingLTI Application Helper",e);

		}

	}
}
