package com.snapwiz.selenium.tests.staf.learningspaces.testcases.anonymous;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Assignment;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.AssignmentTabDetails;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.ExpandCollapseChapter;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.ExpandFirstChapter;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.TOCShow;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.TopicOpen;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.UpdateContentIndex;

public class StudentAbleToViewAllAssignments extends Driver
{
	@Test(priority=1,enabled=true)
	public void studentabletoviewallassignments()
	{
		try
		{
			String topic1=ReadTestData.readDataByTagName("tocdata", "card2topic1", "1");//fetch topic 1
			String subtopic1=ReadTestData.readDataByTagName("tocdata", "subtopic1", "1");//fetch sub topic 1
			String topic3=ReadTestData.readDataByTagName("tocdata", "card4topic1", "1");//fetch topic 3
			String topic1chapter2=ReadTestData.readDataByTagName("tocdata", "topic1chapter2", "1");//fetch topic1 chapter 2
			String topic2chapter2=ReadTestData.readDataByTagName("tocdata", "topic2chapter2", "1");//fetch topic2 chapter2
			String assignments1=ReadTestData.readDataByTagName("StudentAbleToViewAllAssignments", "assessmentname", "3238");//fetch assignment name
			String assignments2=ReadTestData.readDataByTagName("StudentAbleToViewAllAssignments", "assessmentname", "32381");//fetch assignment name
			String assignments3=ReadTestData.readDataByTagName("StudentAbleToViewAllAssignments", "assessmentname", "32382");//fetch assignment name
			String assignments4=ReadTestData.readDataByTagName("StudentAbleToViewAllAssignments", "assessmentname", "32383");//fetch assignment name
			String assignments5=ReadTestData.readDataByTagName("StudentAbleToViewAllAssignments", "assessmentname", "32384");//fetch assignment name
			driver.quit();
			new Assignment().create(3238);//add assignment at chapter 1
			new Assignment().createassignmentontopiclevel(32381, 0, 0);//add assignment at topic 1 of chapter 1
			new Assignment().createassignmentontopiclevel(32382, 1, 0);//add assignment at topic 2 of chapter 1
			new Assignment().createresourcesatsubtopiclevel(32383, 0, 0, 0);//add assignment at subtopic 1 of chapter 1 of topic1
			new Assignment().createassignmentontopiclevel(32384, 6, 1);//add assignment at topic 1 of chapter 2
			new LoginUsingLTI().ltiLogin("32385");//login as student
			new UpdateContentIndex().updatecontentindex("3238");
			startDriver("firefox");
			new LoginUsingLTI().ltiLogin("3238");//login as instructor
			new Assignment().assignToStudent(3238);//Assignment assign to student
			new LoginUsingLTI().ltiLogin("32381");//login as instructor
			new Assignment().assignToStudent(32381);//Assignment assign to student
			new LoginUsingLTI().ltiLogin("32382");//login as instructor
			new Assignment().assignToStudent(32382);//Assignment assign to student
			new LoginUsingLTI().ltiLogin("32383");//login as instructor
			new Assignment().assignToStudent(32383);//Assignment assign to student
			new LoginUsingLTI().ltiLogin("32384");//login as instructor
			new Assignment().assignToStudent(32384);//Assignment assign to student		
     		new LoginUsingLTI().ltiLogin("32385");//login as student
			new Navigator().NavigateTo("eTextbook");//navigate to etextbook
			new ExpandFirstChapter().expandFirstChapter();//expand 1st chapter
			new TopicOpen().topicOpen(topic1);//open topic1 chapter1
			Thread.sleep(2000);
			driver.findElement(By.cssSelector("span[title='Assignments']")).click();//click on assignment tab
			Thread.sleep(3000);
			String[] assignment={assignments1,assignments2,assignments3,assignments4,assignments5};
			String[] assignmentnametopic1=new String[2];
			int i=0;
			List<WebElement> allassignment=driver.findElements(By.cssSelector("a[class='ls_assessment_link']"));//fetch all assignment name under assignement tab
			for(WebElement assignemntname:allassignment)
			{
				assignmentnametopic1[i]=assignemntname.getText();
				i++;
			}
			if(!assignmentnametopic1[0].equals(assignment[0]))
				Assert.fail("chpater level assignment not at top");
			if(!assignmentnametopic1[1].equals(assignment[1]))
				Assert.fail(" topic level assignment not and 2nd");
			new TOCShow().tocShow();//show toc
			Thread.sleep(3000);
			new TopicOpen().topicOpen(subtopic1);//open subtopic1 chapter 1 topic 1
			driver.findElement(By.cssSelector("span[title='Assignments']")).click();//click on assignment tab
			Thread.sleep(3000);
			String[] assignmentnamesubtopic1=new String[3];
			int j=0;
			List<WebElement> allassignmentsubtopic=(List<WebElement>) ((JavascriptExecutor)driver).executeScript("return arguments[0]",driver.findElements(By.cssSelector("a[class='ls_assessment_link']")));//fetch all assignment name under assignement tab
			for(WebElement subtopicassignemntname:allassignmentsubtopic)
			{
				assignmentnamesubtopic1[j]=subtopicassignemntname.getText();
				j++;
			}
			if(!assignmentnamesubtopic1[0].equals(assignment[0]))
				Assert.fail("subtopic level assignemnet not shown");
			if(!assignmentnamesubtopic1[1].equals(assignment[1]))
				Assert.fail("chapter  level assignemnet not shown");
			new TOCShow().tocShow();//show toc
			Thread.sleep(3000);
			new ExpandCollapseChapter().expandChapter(2);//expand chapter 2
			Thread.sleep(3000);
			new TopicOpen().topicOpen(topic2chapter2);//open topic 2 chapter 2
			driver.findElement(By.cssSelector("span[title='Assignments']")).click();//click on assignment tab
			Thread.sleep(3000);
			String[] assignmentnamesubtopic2chapter2=new String[1];
			int k=0;
			List<WebElement> allassignmenttopic2chapter2=(List<WebElement>) ((JavascriptExecutor)driver).executeScript("return arguments[0]",driver.findElements(By.cssSelector("a[class='ls_assessment_link']")));//fetch all assignment name under assignement tab
			for(WebElement topic1chapter2assignemntname:allassignmenttopic2chapter2)
			{
				assignmentnamesubtopic2chapter2[k]=topic1chapter2assignemntname.getText();
				k++;
			}
			if(!assignmentnamesubtopic2chapter2[0].equals(assignment[4]))
				Assert.fail("topic level assignment not shown in assignment tab for topic 2 chapter 2 ");
			
			new TOCShow().tocShow();//show toc
			Thread.sleep(3000);
			new TopicOpen().topicOpen(topic1chapter2);//open topic 1 chapter 2
			boolean assignmenttab=driver.findElement(By.cssSelector("span[title='Assignments']")).isDisplayed();
			if(assignmenttab==true)
				Assert.fail("assignment tab shown ,but there are no assignment at topic subtopic and chapter level");
			new TOCShow().tocShow();//show toc
			Thread.sleep(3000);
			new ExpandCollapseChapter().expandChapter(1);//expand 1st chapter
			Thread.sleep(3000);
			new TopicOpen().topicOpen(topic3);//open topic 3 chapter 1
			Thread.sleep(2000);
			driver.findElement(By.cssSelector("span[title='Assignments']")).click();//click on assignment tab
			Thread.sleep(2000);
			String[] assignmentnamesubtopic3chapter1=new String[1];
			int l=0;
			List<WebElement> allassignmenttopic3chapter1=(List<WebElement>) ((JavascriptExecutor)driver).executeScript("return arguments[0]",driver.findElements(By.cssSelector("a[class='ls_assessment_link']")));//fetch all assignment name under assignement tab
			for(WebElement topic3chapter1assignemntname:allassignmenttopic3chapter1)
			{
				assignmentnamesubtopic3chapter1[l]=topic3chapter1assignemntname.getText();
				l++;
			}
			if(!assignmentnamesubtopic3chapter1[0].equals(assignment[0]))
				Assert.fail("only chapter level assignment not shown");
			
			new TOCShow().tocShow();//show toc
			new ExpandCollapseChapter().collapseChapter(1);//collapse chapter 1
			new ExpandCollapseChapter().expandChapter(1);//expand chapter 1
			Thread.sleep(3000);
			new TopicOpen().topicopeninnewtab(topic1,2);//open topic 1 of chapter 1 in new tab
			Thread.sleep(2000);
			driver.findElement(By.cssSelector("span[title='Assignments']")).click();//click on assignment tab
			Thread.sleep(2000);
			String[] assignmentnametopic11=new String[2];
			int i1=0;
			List<WebElement> allassignment1=driver.findElements(By.cssSelector("a[class='ls_assessment_link']"));
			for(WebElement assignemntname1:allassignment1)
			{
				assignmentnametopic11[i1]=assignemntname1.getText();
				i1++;
			}
			if(!assignmentnametopic11[0].equals(assignment[0]))
				Assert.fail("chpater level assignment not at top");
			if(!assignmentnametopic11[1].equals(assignment[1]))
				Assert.fail(" topic level assignment not and 2nd");
			new TOCShow().tocShow();//show toc
			Thread.sleep(2000);
			new TopicOpen().topicOpen(topic3);//open topic 3 chapter 1
			Thread.sleep(2000);
			driver.findElement(By.cssSelector("span[title='Assignments']")).click();//click on assignment tab
			Thread.sleep(2000);
			new AssignmentTabDetails().assignmentdetailsatrightsideframe(assignments1);//verify all text of  assignment  under assignments tab
			
			
			
		}
		catch(Exception e)
		{
			Assert.fail("Exception in apphelpper studentabletoviewallassignments in class StudentAbleToViewAllAssignments.",e);
		}
	}


}
