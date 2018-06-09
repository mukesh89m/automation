package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT14.R142;

import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.BooleanValue;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.Click;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.TextFetch;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.TextSend;

public class InstructorAbleToAssociateGradingPolicyWithAssessment extends Driver
{
	@Test(priority=1,enabled=true)
	public void instructorAbleToAssociateGradingPolicyWithAssessment()
	{
		try
		{
			String Assessment=ReadTestData.readDataByTagName("", "assessmentname", "90");
			String policyname=ReadTestData.readDataByTagName("", "resourcesname", "90");
			new Assignment().create(90);//create assignment
			new LoginUsingLTI().ltiLogin("90");
			if(System.getProperty("UCHAR") == null) {
				policyname = policyname + LoginUsingLTI.appendChar;
			}
			else {
				policyname = policyname + System.getProperty("UCHAR");
			}
			new UploadResources().uploadResources("90", false, true, false);
			new Navigator().NavigateTo("Question Banks");//navigate to question bank
			new Click().clickByid("all-resource-search-textarea");
			new TextSend().textsendbyid("\""+Assessment+"\"", "all-resource-search-textarea");
			new Click().clickByid("all-resource-search-button");
			new Click().clickbylist("assign-this", 0);
			//91-94--grading policy in drop down
			driver.findElement(By.linkText("Select your Assignment Reference")).click();//click on drop down for choose grading policy
			Thread.sleep(2000);
			driver.findElement(By.linkText(policyname)).click();//select name of grading policy
			new Click().clickByid("assign-cancel");
			int assignpopupaftercancle=driver.findElements(By.className("ir-ls-assign-dialog-wrapper")).size();//check pop-up close or not
			if(assignpopupaftercancle!=0)
				Assert.fail("after click on cancle assign this pop up not close");			
		}
		catch(Exception e)
		{
			Assert.fail("Exception in class InstructorAbleToAssociateGradingPolicyWithAssessment in tescase method instructorAbleToAssociateGradingPolicyWithAssessment",e);
		}
	}
	
	@Test(priority=2,enabled=true,dependsOnMethods = {"instructorAbleToAssociateGradingPolicyWithAssessment"})
	public void Assessmentassignwithgradingpolicy()
	{
		try
		{
			String Assessment=ReadTestData.readDataByTagName("", "assessmentname", "99");
			String description=ReadTestData.readDataByTagName("", "description", "99");
			new Assignment().create(99);//create assignment
			new LoginUsingLTI().ltiLogin("991");//login as student
			new LoginUsingLTI().ltiLogin("99");//login as instructor
			new UploadResources().uploadResources("99", false, true, false);//upload grading policy
			//90,99-101,107
			new Assignment().AssignAssessmentwithgradingPolicy(99,true);//assign to the student with garding policy
			//111,105
			int assignpopupaftercancle=driver.findElements(By.className("ir-ls-assign-dialog-wrapper")).size();//verify assign this pop-up close or not after assigned
			if(assignpopupaftercancle!=0)
				Assert.fail("after click on cancle assign this pop up not close");
			//112
			new Navigator().NavigateTo("Course Stream");
			String assigmentoncoursestream=new TextFetch().textfetchbyclass("ls-media__body");//fetch string form course stream
			if(!assigmentoncoursestream.contains(Assessment))
				Assert.fail("assign assignment not present on course stream page");
			new Navigator().NavigateTo("eTextBook");
			new TopicOpen().lessonOpen(0, 0);//open chapter 1 lesson 1			
			new Navigator().navigateToTab("Assignments");//go to assignment tab
			String assigmentonetextbook=new TextFetch().textfetchbyclass("assignment-content-posts");
			if(!assigmentonetextbook.contains(Assessment))
				Assert.fail("assign assignment not present on etextbook");
			//129
			if(assigmentonetextbook.contains(description))//verify at etextbook right side assignment tab policy description is present or not
				Assert.fail("grading policy descrption present on etextbook");
			new Navigator().NavigateTo("Dashboard");
			String assigmentondashboard=new TextFetch().textfetchbyclass("content-stream");//fetch text from 4th tile of dashboard
			if(!assigmentondashboard.contains(Assessment))
				Assert.fail("assign assignment not present on dashboard");
			new Navigator().NavigateTo("Assignments");
			String assigmentonassignemntpage=new TextFetch().textfetchbyclass("ls-assignment-item-detail-section");//fetch text from assignment page
			if(!assigmentonassignemntpage.contains(Assessment))
				Assert.fail("assign assignment not present on assignemnt page");
			//125
			boolean gradingmedia=new BooleanValue().booleanbyclass("grading-policy-media-file-for-instructor");//veify grading policy link text
			if(gradingmedia==false)
				Assert.fail("uploaded media file not display");
			//126
			boolean policyicon=new BooleanValue().booleanbycssselector("div[class='ls-resource-doctypes ls-resource-image']");
			if(policyicon==false)
				Assert.fail("media icon not shown");
			String gradingpolicydescription=new TextFetch().textfetchbyclass("ls-assignment-grading-desc");
			if(!gradingpolicydescription.contains(description))
				Assert.fail("grading policy descrption not display on assignment page");
			//131--delete this link not present for associated grading policy
			new Navigator().NavigateTo("Resources");
			new Navigator().navigateToTab("My Resources");
			String gradingpolicytext=new TextFetch().textfetchbyclass("ls-assessment-item-sectn");
			if(gradingpolicytext.contains("delete this"))
				Assert.fail("delete this link present for the garding policy with is attached with assignment.");
			//login as student--130
			new LoginUsingLTI().ltiLogin("991");
			new Navigator().NavigateTo("Assignments");
			boolean gradingmedia1=new BooleanValue().booleanbyclass("grading-policy-media-file");//verify grading policy link
			if(gradingmedia1==false)
				Assert.fail("uploaded media file not display for student");
			String gradingpolicydescription1=new TextFetch().textfetchbyclass("ls-assignment-grading-desc");//verify policy descrption present or not
			if(!gradingpolicydescription1.contains(description))
				Assert.fail("grading policy descrption not display on assignment page");
			boolean policyicon1=new BooleanValue().booleanbycssselector("div[class='ls-resource-doctypes ls-resource-image']");//verify upload media type icon
			if(policyicon1==false)
				Assert.fail("media icon not shown");
			new Navigator().NavigateTo("eTextBook");
			new TopicOpen().lessonOpen(0, 0);			
			new Navigator().navigateToTab("Assignments");//go to assignment tab
			String assigmentonetextbook1=new TextFetch().textfetchbyclass("assignment-content-posts");//fetch all text of assignment at right side tab
			if(assigmentonetextbook1.contains(description))
				Assert.fail("grading policy descrption present on etextbook");
		}
		catch(Exception e)
		{
			Assert.fail("Exception in class InstructorAbleToAssociateGradingPolicyWithAssessment in tescase method Assessmentassignwithgradingpolicy",e);
		}
	}
	@Test(priority=3,enabled=true)
	public void instructorlinkedtodifferentclasssection()
	{
		try
		{
			String Assessment=ReadTestData.readDataByTagName("", "assessmentname", "115");
            new Assignment().create(115);//create assignment
            new LoginUsingLTI().ltiLogin("991");//login as student
			new LoginUsingLTI().ltiLogin("115");//login as instructor
            new UploadResources().uploadResources("115", false, true, false);//upload grading policy
			//117-119
			new Assignment().AssignAssessmentwithgradingPolicy(115,false);//assign to the student with garding policy
			//120
			new Navigator().NavigateTo("Course Stream");
			String assigmentoncoursestream=new TextFetch().textfetchbyclass("ls-media__body");//fetch string form course stream
			if(!assigmentoncoursestream.contains(Assessment))
				Assert.fail("assign assignment not present on course stream page");
			new Navigator().NavigateTo("eTextBook");
			new TOCShow().tocHide();
			new Navigator().navigateToTab("Assignments");//go to assignment tab
			String assigmentonetextbook=new TextFetch().textfetchbyclass("assignment-content-posts");
			if(!assigmentonetextbook.contains(Assessment))
				Assert.fail("assign assignment not present on etextbook");
			new Navigator().NavigateTo("Assignments");
			String assigmentonassignemntpage=new TextFetch().textfetchbyclass("ls-assignment-item-detail-section");//fetch text from assignment page
			if(!assigmentonassignemntpage.contains(Assessment))
				Assert.fail("assign assignment not present on assignemnt page");
			new Navigator().NavigateTo("Dashboard");
			String assigmentondashboard=new TextFetch().textfetchbyclass("content-stream");//fetch text from 4th tile of dashboard
			if(!assigmentondashboard.contains(Assessment))
				Assert.fail("assign assignment not present on dashboard");
			
		}
		catch(Exception e)
		{
			Assert.fail("Exception in class InstructorAbleToAssociateGradingPolicyWithAssessment in tescase method instructorlinkedtodifferentclasssection",e);
		}
	}
	

}
