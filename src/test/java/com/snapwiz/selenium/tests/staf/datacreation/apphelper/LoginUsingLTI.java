package com.snapwiz.selenium.tests.staf.datacreation.apphelper;

import com.snapwiz.selenium.tests.staf.datacreation.Config;
import com.snapwiz.selenium.tests.staf.datacreation.Driver;
import com.snapwiz.selenium.tests.staf.datacreation.ReadTestData;
import org.openqa.selenium.By;
import org.testng.Assert;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.sql.ResultSet;


public class LoginUsingLTI extends Driver {

	/* Method for LTI login for learning spaces. This method enters LTI login details like end point and keys. The login will fail is the text verified
	   * is not present on the loaded page after clicking the launch button. */
	  public void ltiLogin(int i,int cnt,String dataIndex)
	  {
          String userNo = Integer.toString(i)+Integer.toString(cnt);
          String courseid,domainname,custom_course_number="";
          String contextid = ReadTestData.readDataByTagName("DataCreate","contextid",Integer.toString(i));;
          String contexttitle = ReadTestData.readDataByTagName("DataCreate","contexttitle",Integer.toString(i));
          String domainid = ReadTestData.readDataByTagName("DataCreate","domainid",Integer.toString(i));
          String role = ReadTestData.readDataByTagName("DataCreate","role",Integer.toString(i));
          String firstname = ReadTestData.readDataByTagName("DataCreate","firstname",Integer.toString(i));
          String lastname = ReadTestData.readDataByTagName("DataCreate","lastname",Integer.toString(i));
          String email = ReadTestData.readDataByTagName("DataCreate","email",Integer.toString(i));
          String userid = ReadTestData.readDataByTagName("DataCreate","userid",Integer.toString(i));

       /* try {
            ResultSet rst = DBConnect.st.executeQuery("select firstname,lastname from t_user ORDER BY RAND() LIMIT 1");
            while (rst.next()) {
                firstname = rst.getString("firstname");
                lastname = rst.getString("lastname");
            }

        }
        catch (Exception e) {
            Assert.fail("Exception while fetching username from DB",e);
        }*/

          if(Config.course.equalsIgnoreCase("orion"))
          {

              domainname = "";
              courseid = Config.orionCourseId;
          }
          else if(Config.course.equalsIgnoreCase("lsadp"))
          {
              domainname = Config.custom_domain_name;
              custom_course_number = Config.custom_course_number;
              courseid = Config.courseid;
          }
          else
          {
              userid = firstname.toLowerCase()+"ls"+userNo+"@snapwiz.com";
              contextid = "autocontextidls"+i;
              contexttitle = "Basics";
              domainid = "Visualizing Human Geography";
              domainname = "autodatadomainnamels_"+Config.lsCourseId;
              courseid = Config.lsCourseId;
          }


		  String custom_destination = ReadTestData.readDataByTagName("", "custom_destination", dataIndex);
		  String resource_link_id = ReadTestData.readDataByTagName("", "resource_link_id", dataIndex);
		  String expectederror = ReadTestData.readDataByTagName("", "expectederror", dataIndex);
		  String custom_instructor_classlist = ReadTestData.readDataByTagName("", "custom_instructor_classlist", dataIndex);
		  String custom_lis_result_source_id = ReadTestData.readDataByTagName("", "custom_lis_result_source_id", dataIndex);
		  String lis_person_sourced_id = ReadTestData.readDataByTagName("", "lis_person_sourced_id", dataIndex);
		 
		  try
		  {

            driver.get(Config.baseLTIURL + "/");
		  	driver.findElement(By.name("endpoint")).clear(); //Clear fields
		  	driver.findElement(By.name("endpoint")).sendKeys(Config.launchURL);
		    
		  	driver.findElement(By.name("key")).clear();
		  	driver.findElement(By.name("key")).sendKeys(Config.customerkey);
		    
		  	driver.findElement(By.name("secret")).clear();
		  	driver.findElement(By.name("secret")).sendKeys(Config.secretkey);
		    
		  	driver.findElement(By.name("resource_link_id")).clear();
		  	
		  	if(resource_link_id == null)
		  		driver.findElement(By.name("resource_link_id")).sendKeys(Config.resourselinkid);
		  	else
		  		driver.findElement(By.name("resource_link_id")).sendKeys(resource_link_id);
		    

            driver.findElement(By.name("user_id")).clear();
            driver.findElement(By.name("user_id")).sendKeys(userid);

            driver.findElement(By.name("roles")).clear();
		  	driver.findElement(By.name("roles")).sendKeys(role);
		    
		  	driver.findElement(By.name("lis_person_name_family")).clear();
		  	driver.findElement(By.name("lis_person_name_family")).sendKeys(lastname);
		    
		  	driver.findElement(By.name("lis_person_name_given")).clear();
		    driver.findElement(By.name("lis_person_name_given")).sendKeys(firstname);
		    
		  	driver.findElement(By.name("lis_person_contact_email_primary")).clear();
		  	driver.findElement(By.name("lis_person_contact_email_primary")).sendKeys(email);
		    
		  	driver.findElement(By.name("context_id")).clear();
		   	driver.findElement(By.name("context_id")).sendKeys(contextid); //context id

            driver.findElement(By.name("context_title")).clear();
		    driver.findElement(By.name("context_title")).sendKeys(contexttitle); //context title

            driver.findElement(By.name("tool_consumer_instance_guid")).clear();
		  	driver.findElement(By.name("tool_consumer_instance_guid")).sendKeys(Config.instance_guid);
		    
		  	driver.findElement(By.name("tool_consumer_instance_name")).clear();
		  	driver.findElement(By.name("tool_consumer_instance_name")).sendKeys(Config.instance_name);
		    
		  	driver.findElement(By.name("custom_courseid")).clear();
		    driver.findElement(By.name("custom_courseid")).sendKeys(courseid);

		  	driver.findElement(By.name("custom_domain_name")).sendKeys(domainname); //domain name

		  	driver.findElement(By.name("custom_destination")).clear();
		  	
		  	if(custom_destination == null)
		  		driver.findElement(By.name("custom_destination")).sendKeys(Config.custom_destination); 	
		  	else
		  		driver.findElement(By.name("custom_destination")).sendKeys(custom_destination);

		  	driver.findElement(By.name("custom_domainid")).clear();
	  		driver.findElement(By.name("custom_domainid")).sendKeys(domainid); //domain id

		    
		  	driver.findElement(By.name("custom_course_number")).clear();
		  	driver.findElement(By.name("custom_course_number")).sendKeys(custom_course_number);
		  	
		  	if(custom_instructor_classlist == null)
		  		driver.findElement(By.name("custom_instructor_classlist")).sendKeys(Config.custom_instructor_classlist);
		  	else
		  		driver.findElement(By.name("custom_instructor_classlist")).sendKeys(custom_instructor_classlist);
		    
		  	driver.findElement(By.name("custom_lis_result_source_id")).clear();
		  	driver.findElement(By.name("custom_lis_result_source_id")).sendKeys(custom_lis_result_source_id);
		  	
		  	driver.findElement(By.name("lis_person_sourced_id")).clear();
		  	driver.findElement(By.name("lis_person_sourced_id")).sendKeys(lis_person_sourced_id);
		  	
		  	driver.findElement(By.name("lis_outcome_service_url")).clear();
		  	driver.findElement(By.name("lis_outcome_service_url")).sendKeys(Config.lis_outcome_service_url);
		  	
		  	driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();
		  	driver.findElement(By.cssSelector("form > input[type=\"submit\"]")).click();
		  	Thread.sleep(2000);

              if(Config.course.equals("orion")) {
                  int closewelcomepage = driver.findElements(By.cssSelector("div[class='al-dialog-welcome-close-icon']")).size();
                  if (closewelcomepage >= 1) {
                      driver.findElement(By.cssSelector("div[class='al-dialog-welcome-close-icon']")).click();
                  }
                  if (expectederror == null) {
                      if (Config.course.equals("orion")) {
                          int backtowilyplus1 = driver.findElements(By.cssSelector("img[title='ORION Dashboard']")).size();
                          if (backtowilyplus1 == 0) {

                              Assert.fail("Login to Orion Failed");
                          }
                      }
                  }
              }

              //Writing to file
              File file;
          /*  if(Config.course.equals("orion"))
               file = new File("/home/akansh/users_orion.txt");
            else if (Config.course.equals("lsadp"))
                file = new File("/home/akansh/users_lsadp.txt");
            else
                file = new File("/home/akansh/users_ls.txt");

              // if file doesnt exists, then create it
              if (!file.exists()) {
                  file.createNewFile();
              }

              FileWriter fw = new FileWriter(file.getAbsoluteFile(),true);
              BufferedWriter bw = new BufferedWriter(fw);
                  bw.write("---------------------------------------------------------------------------------------------"); bw.newLine();
                  bw.write("User Id: " + userid); bw.newLine();
                  bw.write("Role " + role); bw.newLine();
                  bw.write("Context Title: "+contexttitle); bw.newLine();
                  bw.write("Context Id: "+contextid); bw.newLine();
                  bw.write("Domain Id: "+domainid); bw.newLine();
                  bw.write("Course Number- autocustno"); bw.newLine();
                  bw.write("Domain Name: "+domainname); bw.newLine();
                  bw.write("Lastname: "+lastname + " Firstname: " + firstname); bw.newLine();


              bw.newLine();
              bw.close();*/

		  }
		  catch(Exception e)
		  {		
			  driver.quit();
			  Assert.fail("Exception in LoginUsingLTI Application Helper",e);
			  
		  }
		   
	  }
	
}
