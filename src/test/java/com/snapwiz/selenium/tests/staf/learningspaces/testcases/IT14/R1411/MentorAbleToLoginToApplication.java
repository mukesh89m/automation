package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT14.R1411;

import java.sql.ResultSet;
import java.util.List;

import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.Click;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.TextFetch;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Config;
import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Assignment;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.AssignmentPolicy;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.DBConnect;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.KeysSend;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.RandomString;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Screenshot;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.ComboBox;

public class MentorAbleToLoginToApplication extends Driver{

	@Test(priority = 1, enabled = true)
	public void mentorAbleToLoginToApplication()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("2");		//login as mentor
			String url = driver.getCurrentUrl();
			//TC row no. 2, 8
			if(!url.contains("learningSpaceInstructorDashboard"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Mentor unable to login for LS+Adaptive course.");
			}
			//TC row no. 9
			if(!url.contains("learningSpaceInstructorDashboard"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Mentor doesnt land on the dashboard which is same as that of the instructor.");
			}
			String classSection = driver.findElement(By.cssSelector("span[class='default-cs-name']")).getText();
			//TC row no. 3
			if(!classSection.contains(Config.context_title))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Mentor doesnt get logged in for the particular class section provided.");
			}
			new Navigator().NavigateTo("Course Stream");
			String csUrl = driver.getCurrentUrl();
			//TC row no. 11
			if(!csUrl.contains("coursestream"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Mentor unable to navigate to Course Stream.");
			}
			new Navigator().NavigateTo("e-Textbook");
			String eTextbookUrl = driver.getCurrentUrl();
			//TC row no. 11
			if(!eTextbookUrl.contains("eTextBook"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Mentor unable to navigate to eTextBook.");
			}
			new Navigator().NavigateTo("Assignments");
			String assignmentsUrl = driver.getCurrentUrl();
			//TC row no. 11
			if(!assignmentsUrl.contains("assignment"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Mentor unable to navigate to assignment.");
			}
			new Navigator().NavigateTo("Assignment Policies");
			String policyUrl = driver.getCurrentUrl();
			//TC row no. 11
			if(!policyUrl.contains("assignmentPolicy"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Mentor unable to navigate to assignment policy.");
			}
			new Navigator().NavigateTo("Proficiency Report");
			String perfReportUrl = driver.getCurrentUrl();
			//TC row no. 11
			if(!perfReportUrl.contains("instProfRepo"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Mentor unable to navigate to Performance Report.");
			}
			new Navigator().NavigateTo("Metacognitive Report");
			String metaCogUrl = driver.getCurrentUrl();
			//TC row no. 11
			if(!metaCogUrl.contains("instMetaCogRepo"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Mentor unable to navigate to Metacognitive Report.");
			}
			new Navigator().NavigateTo("Productivity Report");
			String prodActivityUrl = driver.getCurrentUrl();
			//TC row no. 11
			if(!prodActivityUrl.contains("instProdRepo"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Mentor unable to navigate to Productivity Report.");
			}
			new Navigator().NavigateTo("Most Challenging Activities Report");
			String mcaReportUrl = driver.getCurrentUrl();
			//TC row no. 11
			if(!mcaReportUrl.contains("instMCARepo"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Mentor unable to navigate to Most Challenging Chapters Report.");
			}
			new Navigator().NavigateTo("Engagement Report");
			String engReportUrl = driver.getCurrentUrl();
			//TC row no. 11
			if(!engReportUrl.contains("instEngRepo"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Mentor unable to navigate to Engagement Report.");
			}
			new Navigator().NavigateTo("Resources");
			String resourceUrl = driver.getCurrentUrl();
			//TC row no. 11
			if(!resourceUrl.contains("resources"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Mentor unable to navigate to Instructor Resources.");
			}
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase mentorAbleToLoginToApplication in class MentorAbleToLoginToApplication.",e);
		}
	}
	@Test(priority = 2, enabled = true)
	public void mentorAbleToLoginToApplicationForLSCourse()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("5");		//login as mentor
			String url = driver.getCurrentUrl();
			//TC row no. 5
			if(!url.contains("learningSpaceInstructorDashboard"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Mentor unable to login for LS course.");
			}
			String classSection = driver.findElement(By.cssSelector("span[class='default-cs-name']")).getText();
			//TC row no. 6
			if(!classSection.contains(Config.context_title))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Mentor doesnt get logged in for the particular class section provided for LS course.");
			}
			new Navigator().NavigateTo("Course Stream");
			String csUrl = driver.getCurrentUrl();
			//TC row no. 11
			if(!csUrl.contains("coursestream"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Mentor unable to navigate to Course Stream for LS course.");
			}
			new Navigator().NavigateTo("e-Textbook");
			String eTextbookUrl = driver.getCurrentUrl();
            //TC row no. 11
			if(!eTextbookUrl.contains("eTextBook"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Mentor unable to navigate to eTextBook for LS course.");
			}
			new Navigator().NavigateTo("Assignments");
			String assignmentsUrl = driver.getCurrentUrl();
			//TC row no. 11
			if(!assignmentsUrl.contains("assignment"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Mentor unable to navigate to assignment for LS course.");
			}
			new Navigator().NavigateTo("Assignment Policies");
			String policyUrl = driver.getCurrentUrl();
			//TC row no. 11
			if(!policyUrl.contains("assignmentPolicy"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Mentor unable to navigate to assignment policy for LS course.");
			}
			new Navigator().NavigateTo("Performance Report");
			String perfReportUrl = driver.getCurrentUrl();
			//TC row no. 11
			if(!perfReportUrl.contains("instPerfRepo"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Mentor unable to navigate to Performance Report for LS course.");
			}
			new Navigator().NavigateTo("Most Challenging Chapters Report");
			String mcaReportUrl = driver.getCurrentUrl();
			//TC row no. 11
			if(!mcaReportUrl.contains("instMCARepo"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Mentor unable to navigate to Most Challenging Chapters Report for LS course.");
			}
			new Navigator().NavigateTo("Engagement Report");
			String engReportUrl = driver.getCurrentUrl();
			//TC row no. 11
			if(!engReportUrl.contains("instEngRepo"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Mentor unable to navigate to Engagement Report for LS course.");
			}
			new Navigator().NavigateTo("Resources");
			String resourceUrl = driver.getCurrentUrl();
			//TC row no. 11
			if(!resourceUrl.contains("resources"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Mentor unable to navigate to Instructor Resources for LS course.");
			}
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase mentorAbleToLoginToApplicationForLSCourse in class MentorAbleToLoginToApplication.",e);
		}
	}
	@Test(priority = 3, enabled = true)
	public void mentorActivity()
	{
		try
		{
		    new Assignment().create(13);
			new LoginUsingLTI().ltiLogin("13_1");	//create a student
			new LoginUsingLTI().ltiLogin("13");		//login as mentor
			//if any of the steps fails then the mentor is unable to that particular failed activity
			new Navigator().NavigateTo("Assignment Policies");//navigate to Assignment Policies
			new AssignmentPolicy().createAssignmentPolicy("Policy Name", "13 Policy description", "2", null,false, "1", "", null, "", "", "");
			new Assignment().assignToStudent(13);
			
			new LoginUsingLTI().ltiLogin("13_1");
			new Assignment().submitAssignmentAsStudent(13);

			new LoginUsingLTI().ltiLogin("13");
			new Assignment().provideGRadeToStudent(13);	//tc row no. 15
            new Assignment().releaseGrades(13,"Release Grade for All");
            Assert.assertEquals("Graded",new TextFetch().textfetchbyclass("ls-assignment-status-grades-released"));
            new Navigator().NavigateTo("Summary");
            Assert.assertEquals("Graded",new TextFetch().textfetchbyclass("ls-assignment-status-grades-released"));

		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase mentorActivity in class MentorAbleToLoginToApplication.",e);
		}
	}
	@Test(priority = 4, enabled = true)
	public void mentorInTwoClassSection()
	{
		try
		{
			String context_title = ReadTestData.readDataByTagName("", "context_title", "16");
            String context_title1 = ReadTestData.readDataByTagName("", "context_title", "17");
			new LoginUsingLTI().ltiLogin("16");		//login as mentor
			String classSection = driver.findElement(By.cssSelector("span[class='default-cs-name']")).getText();
			//TC row no. 16
			if(!classSection.contains(context_title))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Mentor unable to login to class section 1.");
			}
			DBConnect.Connect();
			ResultSet count4 = DBConnect.st.executeQuery("SELECT COUNT(*) FROM t_class_section_permission;");
			String count5 = "";
			while(count4.next())
		    {
				count5= count4.getString(1); 
		    }
			int initialCount = Integer.parseInt(count5);
			System.out.println("initialCount :"+initialCount);

			new LoginUsingLTI().ltiLogin("17");		//login as mentor
			String classSection1 = driver.findElement(By.cssSelector("span[class='default-cs-name']")).getText();
			//TC row no. 17
			if(!classSection1.contains(context_title1))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Mentor unable to login to class section 2.");
			}
			
			//get the metor_role from DB 
			DBConnect.Connect();
			ResultSet count = DBConnect.st.executeQuery("SELECT COUNT(*) FROM t_class_section_permission;");
			String count1 = "";
			while(count.next())
		    {
				count1= count.getString(1); 
		    }
			int lastCount = Integer.parseInt(count1);
			System.out.println("lastCount :"+lastCount);

			//TC row no. 21
			if(initialCount+1 != lastCount)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("A row is not added as ROLE_MENTOR for the class section and course.");
			}
			driver.quit();
			startDriver("firefox");
			new LoginUsingLTI().ltiLogin("17");		//login as mentor
			String url = driver.getCurrentUrl();
			//TC row no. 22
			if(!url.contains("learningSpaceInstructorDashboard"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Mentor unable to login after we change the class section dropdown.");
			}
            new ComboBox().selectValueByScrollToView(context_title1,context_title);
            new Click().clickbylinkText(context_title);
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase mentorActivity in class MentorAbleToLoginToApplication.",e);
		}
	}
	@Test(priority = 5, enabled = true)
	public void sharingWithMentor()
	{
		try
		{
			String shareWithInitialString = ReadTestData.readDataByTagName("", "shareWithInitialString", "24");
			String shareName = ReadTestData.readDataByTagName("", "shareName", "24");
            String y[]=shareName.split(" ");
            shareName = y[1] + ", " + y[0];//reverse the name with comma in between
			new LoginUsingLTI().ltiLogin("24");		//login as mentor
			new LoginUsingLTI().ltiLogin("24_1");		//craete a student
			new Navigator().NavigateTo("Course Stream");
			driver.findElement(By.linkText("Post")).click();
			Thread.sleep(2000);
			String randomtext = new RandomString().randomstring(15);
			if(Config.browser.equals("ie"))
			{
				
				Thread.sleep(2000);
				new KeysSend().sendKeyBoardKeys(randomtext);
			}
			else //if(Config.browser.equals("chrome"))
			{
				WebElement t=driver.findElement(By.id("iframe-user-text-input-div"));
				driver.switchTo().frame(t) ;
				driver.findElement(By.xpath("/html/body/font")).click();
				driver.findElement(By.xpath("/html/body")).sendKeys(randomtext);
				driver.switchTo().defaultContent();
			}
			driver.findElement(By.className("closebutton")).click();
			Thread.sleep(2000);
			driver.findElement(By.className("maininput")).sendKeys(shareWithInitialString);
			Thread.sleep(3000);
			//TC row no. 25, 26
			List<WebElement> suggestname = driver.findElements(By.xpath("//*[starts-with(@rel, 'uid_')]"));
			for (WebElement answerchoice: suggestname)
			   {
			    if(!answerchoice.getText().trim().equals(shareName))
			    {
			    	new Screenshot().captureScreenshotFromTestCase();
					Assert.fail("Mentor name is not displayed when student enters the mentor initials.");
			    }
			   }
			
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase sharingWithMentor in class MentorAbleToLoginToApplication.",e);
		}
	}
}
