package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT14.R1410;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Assignment;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.SortingOrderOfAssignmentOnStudyPlan;

public class SortingOrderOfChapterLevelAssignmentbasedOnStatus extends Driver
{
	@Test(priority=1,enabled=true)
	public void sortingOrderOfChapterLevelAssignmentbasedOnStatus()
	{
		try
		{
			String assignment1=ReadTestData.readDataByTagName("", "assessmentname", "18");
			String assignment2=ReadTestData.readDataByTagName("", "assessmentname", "181");
			String assignment3=ReadTestData.readDataByTagName("", "assessmentname", "182");
			new Assignment().create(18);
			new Assignment().create(181);
			new Assignment().create(182);
			new LoginUsingLTI().ltiLogin("18_1");//create student
			new LoginUsingLTI().ltiLogin("18");//login as instructor
			new Assignment().assignToStudent(18);
			new Assignment().assignToStudent(181);
			new Assignment().assignToStudent(182);
			new LoginUsingLTI().ltiLogin("18_1");//login as student
			//18-28
			new SortingOrderOfAssignmentOnStudyPlan().sortingOrderOfAssignmentOnStudyplan(assignment1, assignment2, assignment3);//sorting oder of assignment of status(not started,in progress,submitted
		}
		catch(Exception e)
		{
			Assert.fail("Exception in testcase SortingOrderOfChapterLevelAssignmentbasedOnStatus in test method sortingOrderOfChapterLevelAssignmentbasedOnStatus",e);
		}
	}
	
	@Test(priority=2,enabled=true)
	public void sortingOrderOftopicLevelAssignmentbasedOnStatus()
	{
		try
		{
			String assignment1=ReadTestData.readDataByTagName("", "assessmentname", "35");
			String assignment2=ReadTestData.readDataByTagName("", "assessmentname", "351");
			String assignment3=ReadTestData.readDataByTagName("", "assessmentname", "352");
			new Assignment().createAssignmentAtTopicLevel(35);
			new Assignment().createAssignmentAtTopicLevel(351);
			new Assignment().createAssignmentAtTopicLevel(352);
			new LoginUsingLTI().ltiLogin("35_1");//create student
			new LoginUsingLTI().ltiLogin("35");//login as instructor
			new Assignment().assignToStudent(35);
			new Assignment().assignToStudent(351);
			new Assignment().assignToStudent(352);
			new LoginUsingLTI().ltiLogin("35_1");//login as student
			//35-45
			new SortingOrderOfAssignmentOnStudyPlan().sortingOrderOfAssignmentOnStudyplan(assignment1, assignment2, assignment3);//sorting oder of assignment of status(not started,in progress,submitted
			
		}
		catch(Exception e)
		{
			Assert.fail("Exception in testcase SortingOrderOfChapterLevelAssignmentbasedOnStatus in test method sortingOrderOftopicLevelAssignmentbasedOnStatus",e);
		}
	}
}
