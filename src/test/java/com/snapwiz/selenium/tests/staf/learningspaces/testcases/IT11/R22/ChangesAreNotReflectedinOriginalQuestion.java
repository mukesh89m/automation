package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT11.R22;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Assignment;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.DataFetchFromDataBase;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.RandomString;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.SearchQuestionInCustomCourseAssignemnt;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.Click;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.*;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.TextSend;

public class ChangesAreNotReflectedinOriginalQuestion extends Driver
{
	@Test
	public void changesAreNotReflectedInOriginalQuestion()
	{
		try
		{
			new Assignment().create(292);
			String assessmentname=ReadTestData.readDataByTagName("", "assessmentname", "292");
			new Assignment().addQuestions(292, "qtn-type-true-false-img",assessmentname);//add question
			new LoginUsingLTI().ltiLogin("292");//login as instructor
			new Navigator().NavigateTo("Question Banks");//Navigate to resources
			new Click().clickByid("customAssignment");//click on create custom assignment
			Thread.sleep(2000);
			String searchtext=ReadTestData.readDataByTagName("tocdata", "searchtext", "1");
			new SearchQuestionInCustomCourseAssignemnt().searchQuestionInCustomCourseAssignemnt(searchtext);
			new Click().clickbylist("ls-ins-customize-checkbox-small", 0);//checked check box
			String selector="i[class='ls-ins-custom-image ls-ins-edit-assignment-icon']";
			String randomname=new RandomString().randomstring(2);
			String randomndescption=new RandomString().randomstring(2);
			new MouseHover().mouserhoverbyid("ls-ins-assignment-name");//mouse hover on edit name
			new Click().clickbylistcssselector(selector, 0);//click on pen icon	
			new TextSend().textsendbyclasslist(randomname, "ls-ins-edit-assignment",0);//edit name
			new MouseHover().mouserhoverbyid("ls-ins-assignment-desc");//mouse hover on description
			new Click().clickbylistcssselector(selector, 1);//click on pen icon
			new TextSend().textsendbyclasslist(randomndescption, "ls-ins-edit-assignment",1);//edit description
			new Click().clickByclassname("ls-ins-save-assigment-btn");//click on save button
		}
		catch(Exception e)
		{
			Assert.fail("Exception in testcase ChangesAreNotReflectedinOriginalQuestion in method changesAreNotReflectedInOriginalQuestion. ",e);
		}			
	}

}
