package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT13.R51;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Assignment;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.SortingOrderOfStudentInEngamentReport;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.StaticAssignmentSubmit;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.TopicOpen;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.BooleanValue;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.Click;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.TextFetch;

public class AchievementSummary extends Driver
{
	@Test
	public void achievementSummary()
	{
		try
		{
			new Assignment().create(561);
			new Assignment().create(562);
			new Assignment().create(563);
			new Assignment().create(564);
			new Assignment().create(565);
			new LoginUsingLTI().ltiLogin("561");//login as student
			new LoginUsingLTI().ltiLogin("562");//login as student
			new LoginUsingLTI().ltiLogin("563");//login as student
			new LoginUsingLTI().ltiLogin("564");//login as student
			new LoginUsingLTI().ltiLogin("565");//login as student
			new LoginUsingLTI().ltiLogin("56");//Login as instructor
			new Assignment().assignToStudent(561);//assign assignment to student
			new LoginUsingLTI().ltiLogin("56");//Login as instructor
			new Assignment().assignToStudent(562);//assign assignment to student\
			new LoginUsingLTI().ltiLogin("56");//Login as instructor
			new Assignment().assignToStudent(563);//assign assignment to student
			new LoginUsingLTI().ltiLogin("56");//Login as instructor
			new Assignment().assignToStudent(564);//assign assignment to student
			new LoginUsingLTI().ltiLogin("56");//Login as instructor
			new Assignment().assignToStudent(565);//assign assignment to student
			new LoginUsingLTI().ltiLogin("561");
			new StaticAssignmentSubmit().staticAssignmentSubmit(0);//submit assignment
			new LoginUsingLTI().ltiLogin("562");
			new StaticAssignmentSubmit().clickonassignment(0);//click on assignment
			new LoginUsingLTI().ltiLogin("564");
			new StaticAssignmentSubmit().staticAssignmentSubmit(0);////submit assignment
			new LoginUsingLTI().ltiLogin("56");
			new Assignment().provideGRadeToStudent(564);
			new Assignment().releaseGrades(564,"Release Grade for All");
			new LoginUsingLTI().ltiLogin("565");
			new StaticAssignmentSubmit().staticAssignmentSubmit(0);////submit assignment
			new LoginUsingLTI().ltiLogin("56");
			new Assignment().provideGRadeToStudent(565);
            new Assignment().releaseGrades(565,"Release Grade for All");
			new LoginUsingLTI().ltiLogin("56");
			new Navigator().NavigateTo("Engagement Report");//4-navigate to engaged report 
			//56
			String achivmentsummary=new TextFetch().textfetchbyclass("student-achievement-summary");
			//65
			if(!achivmentsummary.contains("100"))
			{
				Assert.fail("proficency not shown");
			}
			//58
			if(!achivmentsummary.contains("Skipped"))
			{
				Assert.fail("status not shown for question");
			}
			//61,59,66
			if(!achivmentsummary.contains("3"))
			{
				Assert.fail("total number of question not shown");
			}
			//57
			if(!achivmentsummary.contains("Question Performance Summary"))
			{
				Assert.fail("Title of the third chart in Engagement report is not 'Achievement Summary'");
			}
			//75,76
			int numberofstudent=driver.findElements(By.className("students-performance-checkbox")).size();
			if(numberofstudent!=5)
			{
				Assert.fail("student table not break as per student");
			}
			new SortingOrderOfStudentInEngamentReport().sortingOrderOfStudentInEngamentReport(0);
			//87
			boolean questionicon=new BooleanValue().booleanbylistclass("performance-question-help-img", 0);
			if(questionicon==false)
			{
				Assert.fail("question icon not shown before assigned");
			}
			//88
			new Click().clickByclassname("performance-question-help-img");
			boolean helppop=new BooleanValue().booleanbyclass("help-data-container");//---88
			if(helppop==false)
			{
				Assert.fail("help pop not shown after click on ?");
			}
			//89
			String assigntext=new TextFetch().textfetchbylistclass("students-report-content-body-second", 0);
			if(!assigntext.contains("Not Started"))
			{
				Assert.fail("not started status not shown");
			}
			if(!assigntext.contains("In Progress"))
			{
				Assert.fail("In Progress status not shown");
			}
			if(!assigntext.contains("Completed"))
			{
				Assert.fail("Completed status not shown");
			}
			
			//93,94--soring of all element
			new SortingOrderOfStudentInEngamentReport().sortingOrderOfStudentInEngamentReport(1);
			//95,96
			new SortingOrderOfStudentInEngamentReport().sortingOrderOfStudentInEngamentReport(2);
			//97,98
			new SortingOrderOfStudentInEngamentReport().sortingOrderOfStudentInEngamentReport(3);
			
			String rowtext=new TextFetch().textfetchbylistcssselector("div[class='students-performance-report-content-row student-performance-content-odd-row']", 1);
            String[] rowtextarr=rowtext.split("\n");
			for(int i=0;i<rowtextarr.length;i++)
			{
				System.out.println("rowtextarr:"+rowtextarr[i]);
				
			}
			if(!rowtextarr[1].contains("1"))
			{
				Assert.fail("question complted is not shown");
			}
			//116
            if(!rowtextarr[2].contains("sec"))
			{
				Assert.fail("time spent  not shown");
			}
			//126
			if(!rowtextarr[2].contains("1"))
			{
				Assert.fail("attemped question  is not shown");
			}
			//135
			if(!rowtextarr[2].contains("60.0%"))
			{
				Assert.fail("assignment mark  is not shown");
			}
			//130,131--sorting time spent 
			new SortingOrderOfStudentInEngamentReport().sortingOrderOfStudentInEngamentReport(8);
			//139,140--sorting assignment mark
			new SortingOrderOfStudentInEngamentReport().sortingOrderOfStudentInEngamentReport(9);
            //121,122--sorting  assignment mark
            new SortingOrderOfStudentInEngamentReport().sortingOrderOfStudentInEngamentReport(7);
			//question attempted
			//new SortingOrderOfStudentInEngamentReport().sortingOrderOfStudentInEngamentReport(7);
			//124,125
			new Click().clickbylist("performance-question-help-img", 2);//click on question attempted question icon
			boolean helppop1=new BooleanValue().booleanbyclass("help-data-container");//---88
			if(helppop1==false)
			{
				Assert.fail("help pop not shown after click on ?");
			}
			//133,134
			new Click().clickbylist("performance-question-help-img", 3);//click on mark assignment question icon
			boolean helppop2=new BooleanValue().booleanbyclass("help-data-container");//---88
			if(helppop2==false)
			{
				Assert.fail("help pop not shown after click on ?");
			}
			//143
			new Click().clickBycssselector("div[class='student-performance-report-email-section ins-post-message-button-wrapper']");//click on email
			int sendmaildialog=driver.findElements(By.id("al-dialog-sendMailDialog")).size();
			//142,145
			new Click().clickByid("students-performance-select-all-checkbox");//click on check box
			new Click().clickBycssselector("div[class='student-performance-report-email-section ins-post-message-button-wrapper email-button-active']");
			//146
			int sendmaildialog1=driver.findElements(By.id("al-dialog-sendMailDialog")).size();
			if(sendmaildialog>sendmaildialog1)
			{
				Assert.fail("email is enable without selecting any student");
			}
			String emailheader=new TextFetch().textfetchbyclass("ls-ins-send-mail-header-label");
			//153
			if(!emailheader.contains("Post a message"))
			{
				Assert.fail("header of email dialog box in engagement report is not 'Post a message'");
			}
			//154
			boolean shraefield=new BooleanValue().booleanbyid("user-names-block");
			if(shraefield==false)
			{
				Assert.fail("share-with field not shown");
			}
			boolean emailmessage=new BooleanValue().booleanbyid("email-message-content");
			if(emailmessage==false)
			{
				Assert.fail("email message not shown");
			}
			//155--student count
			int studentcount=driver.findElements(By.className("bit-box")).size();
			if(studentcount!=5)
			{
				Assert.fail("student name not shown in share with filed");
			}
			
			//156
			new Click().clickByid("dialog-close");//cancle email sending dialog
			//147
			new Click().clickByid("students-performance-select-all-checkbox");//deselect all student
			//144
			boolean exporttocsv=driver.findElement(By.cssSelector("img[title='Export to CSV']")).isEnabled();
			if(exporttocsv==false)
			{
				Assert.fail("export to csv not enable");
			}
			//click on student 1st student 1st column--159,160
			driver.findElement(By.xpath("//*[@id='idb-body-content']/div/div/div[5]/div[2]/div[2]/div[2]/div/div[1]")).click();
			Thread.sleep(2000);
			boolean blockUI=new BooleanValue().booleanbycssselector("div[class='blockUI blockOverlay']");
			if(blockUI==false)
			{
				Assert.fail("bolck Ui not shown on assignwmnt page");
			}
			//dropdown functionality--162
			new Click().clickBycssselector("a[title='All Assignments']");
			new Click().clickBycssselector("a[title='Question Assignment']");
			//163
			new Click().clickByclassname("student-assignment-view-back-btn");//161--click on back button
			boolean assignquizzed=new BooleanValue().booleanbyclass("assigned-quizzes-progress");
			if(assignquizzed==false)
			{
				Assert.fail("afetr click on back button in assignment page its not come back to engagement report.");
			}
			//164--click on non assigned reading table
			driver.findElement(By.xpath("//*[@id='idb-body-content']/div/div/div[5]/div[2]/div[2]/div[3]/div/div[1]")).click();//click on reading non assigned
			Thread.sleep(3000);
			//165,166,167
			boolean readingblockUI=new BooleanValue().booleanbycssselector("div[class='blockUI blockOverlay']");
			if(readingblockUI==false)
			{
				Assert.fail("readingblockUI  not shown on learning content");
			}
			//168,169
			new TopicOpen().chapterOpen(1);//click on chapter 2
			//170
			new Click().clickByclassname("study-plan-back-btn");//click on back button
			boolean assignquizzed1=new BooleanValue().booleanbyclass("assigned-quizzes-progress");
			if(assignquizzed1==false)
			{
				Assert.fail("After clicking on back button in assignment page its not come back to engagement report.");
			}
		}
		catch(Exception e)
		{
			Assert.fail("Exception in terstcase AchievementSummary in method achievementSummary",e);
		}		
	}

}
