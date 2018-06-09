package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT15.R1594;

/*
 *@ Brajesh Kumar
 */
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.AssignLesson;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Assignment;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.AssignmentPolicy;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.HelpText;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.RandomString;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.BooleanValue;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.Click;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.MouseHover;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.TextFetch;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.TextSend;

public class PopUpToCreatePolicyOnFly extends Driver
{
	@Test(priority=1,enabled=true)
	public void popUpToCreatePolicyOnFly()
	{
		try
		{

			String nameofpolicy=new RandomString().randomstring(3);
			String assignemntname=ReadTestData.readDataByTagName("", "assessmentname", "1");
			new Assignment().create(1);//create assignment
			new LoginUsingLTI().ltiLogin("1");//login as instructor
			new AssignmentPolicy().openAssignmentPolicy(assignemntname);//open assignment policy form instructor resources page
			//3
			String popupheader=new TextFetch().textfetchbyclass("policy-dialog-header-text");//pop -up header
			if(!popupheader.contains("New Assignment policy"))
				Assert.fail("header is not 'New Assignment policy '");
			//7
			boolean cancleButton=new BooleanValue().booleanbyclass("ls-cancel-policy-btn");//verify cancle button
			if(cancleButton==false)
				Assert.fail("cancle link not present");
			boolean useThisPolicy=new BooleanValue().booleanbycssselector("div[title='Use this Policy']");//verify use this policy button
			if(useThisPolicy==false)
				Assert.fail("use this policy button not display");
			//8
			String defaultTextOfPolicyName=new TextFetch().textfetchbyid("ls-ins-assignment-policy-name");//default text of policy name
			if(!defaultTextOfPolicyName.contains("Click to enter a name for this policy"))
				Assert.fail("default name not shown 'Click to enter a name for this policy...'");
			//13
			String defaultTextOfPolicyDescription=new TextFetch().textfetchbyid("ls-ins-assignment-policy-desc");//default text of policy description
			if(!defaultTextOfPolicyDescription.contains("Click to enter a description for this policy"))
				Assert.fail("default descrption not shown 'Click to enter a description for this policy'");			
			//9-11
			new MouseHover().mouserhoverbyid("ls-ins-assignment-policy-name");
			new Click().clickBycssselector("i[class='ls-policy-ls-assignment-image ls-ins-edit-assignment-policy-icon']");//click on policy name edit icon
			new TextSend().textsendbyid(nameofpolicy, "ls-ins-edit-assignment-policy");//name of policy editable
			//14 -16
			new MouseHover().mouserhoverbyid("ls-ins-assignment-policy-desc");
			new Click().clickbylistcssselector("i[class='ls-policy-ls-assignment-image ls-ins-edit-assignment-policy-icon']",1);//click on policy description edit icon
			new TextSend().textsendbyid(nameofpolicy, "ls-ins-enter-assignment-policy-desc");	//descrption editablle		
			String popupheader1=new TextFetch().textfetchbyclass("policy-dialog-header-text");//pop -up header after change policy name
			if(!popupheader1.contains(nameofpolicy))
				Assert.fail("header not change as policy name ");
			//17-18--text editable
			new TextSend().textsendbyid("3333", "score");//point per question editable
			//21-22--helptext
			String helpTextforPointPerQuestion=new HelpText().helpTextForGradingPolicy(0);//help text for point per questions
			if(!helpTextforPointPerQuestion.contains("Enter the points available for each question in the assignment"))
				Assert.fail("helptext not shown");
			//23-28
			String orderingOptions=new TextFetch().textfetchbyclass("ls-assignment-policy-answer-options");//fetch ordring option
			if(!orderingOptions.contains("Keep in order"))
				Assert.fail("Keep in order ption not present");
			if(!orderingOptions.contains("Random"))
				Assert.fail("Random ption not present");
			driver.findElement(By.xpath("//*[@id='assignment-policy-scroll-wrapper']/div/div/div/div[4]/div/div/div[2]/div[2]/label[1]/input")).click();//click on random option
			String ordringHelptext=new HelpText().helpTextForGradingPolicy(1);
			if(!ordringHelptext.contains("Select random if you would like the order of questions to be random for each student."))
				Assert.fail("ordering help text not shown");
			//29-34--about immidiate feedback
			String immediateFeedbackOption=new TextFetch().textfetchbylistclass("ls-assignment-policy-answer-options",1);	//fetch immdiate feedback options avilable		
			if(!immediateFeedbackOption.contains("Enable"))
				Assert.fail("enable option not shown");
			if(!immediateFeedbackOption.contains("Disable"))
				Assert.fail("Disable option not shown");
			new Click().clickbylist("immediateFeedback", 0);//select enable for immidiate option
			String immediateFeedbackHelpText=new HelpText().helpTextForGradingPolicy(2);//immdiate option help text
			if(!immediateFeedbackHelpText.contains("Enable immediate feedback if you want students to see if their answer"))
				Assert.fail("immediate help text not shown");
			//35-42
			int gradeReleaseOption=driver.findElements(By.className("gradeReleaseOptions")).size();//count grade release option
			if(gradeReleaseOption!=4)
				Assert.fail("grade release option not display 4");
			new Click().clickbylist("gradeReleaseOptions", 2);//select option 3
			String gradeReleaseOptionHelpText=new HelpText().helpTextForGradingPolicy(3);//grade release help text
			if(!gradeReleaseOptionHelpText.contains("You can control when students will see their grade on an assignment."))
					Assert.fail("grade release option help text not shown");
			//44-52 number of attempt of questions
												
			//driver.findElement(By.xpath("html/body/div[11]/div/div/div[2]/div/div/div/div/div[4]/div/div/div[5]/div[1]/div[2]/div/a[2]")).click();//number of attempt question drop down
            driver.findElement(By.xpath("//div[@id='policy-number-of-attempts-wrapper']/div/a[2]")).click();//number of attempt question drop down
            Thread.sleep(2000);
			driver.findElement(By.linkText("4")).click();//select numbet of attempt the question
			String numberOfAttempt=new HelpText().helpTextForGradingPolicy(4);//number of attepmt help text
			if(!numberOfAttempt.contains("Enter the number of attempts available for students on each question."))
					Assert.fail("for number Of Attempt option help text not shown");
			new Click().clickbylist("immediateFeedback", 1);//select immediate feedback disable
			String defaultAttemptquestion = new TextFetch().textfetchbyid("policy-number-of-attempts-wrapper");//fetch default text under attempt question after select disable option
			if(!defaultAttemptquestion.contains("1"))
				Assert.fail("default question not became 1 after select immediate feedback disable");
			//fetch alorithm question delivery option
			String AlgorithmQuestionDelivery=new TextFetch().textfetchbylistclass("ls-assignment-policy-answer-options", 4);//fetch algorithm question delivery options
			if(!AlgorithmQuestionDelivery.contains("Use same values for subsequent attempts"))
				Assert.fail("Use same values for subsequent attempts option not present");
			if(!AlgorithmQuestionDelivery.contains("Use new values for subsequent attempts"))
				Assert.fail("Use new values for subsequent attempts option not present");
			//driver.findElement(By.xpath("//*[@id='assignment-policy-scroll-wrapper']/div/div/div/div[4]/div/div/div[5]/div[2]/div[2]/label[2]/input")).click();//select
            driver.findElement(By.xpath("//div[@id='policy-number-of-attempts-wrapper']/div/a[2]")).click();
            String AlgorithmHelpText=new HelpText().helpTextForGradingPolicy(5);//algorithm help text
			if(!AlgorithmHelpText.contains("use same values for subsequent attempts"))
					Assert.fail("for algorithm  help text not shown");
			
			new Click().clickbylist("immediateFeedback", 0);//select enable for immediate feedback
			//driver.findElement(By.xpath("html/body/div[11]/div/div/div[2]/div/div/div/div/div[4]/div/div/div[5]/div[1]/div[2]/div/a[2]")).click();//number of attempt question drop down
            driver.findElement(By.xpath("//div[@id='policy-number-of-attempts-wrapper']/div/a[2]")).click();
            Thread.sleep(2000);
			driver.findElement(By.linkText("5")).click();//select number of attempt option 5
			new Click().clickbylist("showHints", 0);//selecy yes option for hint			
			String showHintTextAfterSelectYes=new TextFetch().textfetchbycssselector("div[class='show-hints-policy-attempts-wrapper attempt-shaded-bg']");//show hint show number of question per attempt after select yes option
			if(!showHintTextAfterSelectYes.contains("5"))
				Assert.fail("after select 'yes' option the 'at attempt number div not shown'");			
			new Click().clickbylist("showReadContentLink", 0);//select Show reading content Link 'yes'
			String showReadingContentList=new TextFetch().textfetchbycssselector("div[class='show-content-link-policy-attempts-wrapper attempt-shaded-bg']");//reading content link  show number of question per attempt after select yes option
			if(!showHintTextAfterSelectYes.contains("5"))
			if(!showReadingContentList.contains("5"))
				Assert.fail("number of reading content list not change to 5 after select number of question attempt 5");
			new Click().clickbylist("showSolution", 0);//select Show reading content Link 'yes'
			String showSoltuionText=new TextFetch().textfetchbycssselector("div[class='show-solution-policy-attempts-wrapper attempt-shaded-bg']");
			if(!showSoltuionText.contains("5"))
				Assert.fail("show solution text not change to 5 after select number of question attempt 5");
			new Click().clickbylist("showAnswer", 0);//select Show reading content Link 'yes'
			String showAnswerText=new TextFetch().textfetchbycssselector("div[class='show-answer-policy-attempts-wrapper attempt-shaded-bg']");
			if(!showAnswerText.contains("5"))
				Assert.fail("show Answer text not change to 5 after select number of question attempt 5");
			//select a attempt question unlimted
			//driver.findElement(By.xpath("html/body/div[11]/div/div/div[2]/div/div/div/div/div[4]/div/div/div[5]/div[1]/div[2]/div/a[2]")).click();//number of attempt question drop down
            driver.findElement(By.xpath("//div[@id='policy-number-of-attempts-wrapper']/div/a[2]")).click();
            Thread.sleep(2000);
			new Click().clickBycssselector("a[title='Unlimited']");//select unlimted option
			String showHintTextAfterSelectYes1=new TextFetch().textfetchbycssselector("div[class='show-hints-policy-attempts-wrapper attempt-shaded-bg']");
			if(!showHintTextAfterSelectYes1.contains("15"))
				Assert.fail("after select 'yes' option the 'at attempt number div not shown'");
			String showhintHelpText=new HelpText().helpTextForGradingPolicy(6);
			if(!showhintHelpText.contains("Some questions have a hint available. You can decide at which attempt to display hints to students."))
					Assert.fail("for show hint help text not shown");
			String showReadingContentList1=new TextFetch().textfetchbycssselector("div[class='show-content-link-policy-attempts-wrapper attempt-shaded-bg']");
			if(!showReadingContentList1.contains("15"))
				Assert.fail("number of reading content list not change to 15 after select number of question attempt unlimted");			
			String showSoltuionText1=new TextFetch().textfetchbycssselector("div[class='show-solution-policy-attempts-wrapper attempt-shaded-bg']");
			if(!showSoltuionText1.contains("15"))
				Assert.fail("show solution text not change to 15 after select number of question attempt unlimted");
			String showAnswerText1=new TextFetch().textfetchbycssselector("div[class='show-answer-policy-attempts-wrapper attempt-shaded-bg']");
			if(!showAnswerText1.contains("15"))
				Assert.fail("show Answer text not change to 15 after select number of question attempt umlimted");
			new Click().clickbylist("immediateFeedback", 1);//select disable for immediate feedback
			String showHintAfterDisable = new TextFetch().textfetchbycssselector("div[class='show-hints-policy-attempts-wrapper attempt-shaded-bg']");
			//79-80
			if(!showHintAfterDisable.contains("1"))
				Assert.fail("after select disable option of immediate feedback the question attempt not change to 1 for show hint ");
			new Click().clickbylist("showReadContentLink", 0);//select yes option for reading content link
			String showReadingEnableContentAfterDisable = new TextFetch().textfetchbycssselector("div[class='show-content-link-policy-attempts-wrapper attempt-shaded-bg']");
			//94-97
			if(!showReadingEnableContentAfterDisable.contains("1"))
				Assert.fail("after select disable option of immediate feedback the question attempt not change to 1 for enable content link ");
			Thread.sleep(2000);
			//79-80
			boolean showSolutionRButtonDisable=driver.findElement(By.className("showSolution")).isEnabled();	//show solution 'yes' option disable after select disable option for immdiate feedback
			if(showSolutionRButtonDisable==true)
				Assert.fail("instructor able to select show solution option even immediate feedback disable");
			//91-94
			boolean showAnswerRButtonDisable=driver.findElement(By.className("showAnswer")).isEnabled();//show Answer 'yes' option disable after select disable option for immdiate feedback
			if(showAnswerRButtonDisable==true)
				Assert.fail("instructor able to select show Answer option even immediate feedback disable");
			//87-89
			String ReadingContentLinkHelpText=new HelpText().helpTextForGradingPolicy(7);//reading content link help text
			if(!ReadingContentLinkHelpText.contains("Every question has a link to the relevant section of the eTextbook."))
					Assert.fail("for Reading Content Link Help Text not shown");
			//101-103
			String ShowSolutionHelpText=new HelpText().helpTextForGradingPolicy(8);//show solution help test
			if(!ShowSolutionHelpText.contains("Some questions have a full solution available."))
					Assert.fail("for Show Solution Help Text not shown");
			//117-118
			String ShowAnswerHelpText=new HelpText().helpTextForGradingPolicy(9);//help text for show answer
			if(!ShowAnswerHelpText.contains("You can decide after which attempt a student should see the correct answer to a question."))
					Assert.fail("for Show Answer Help Text not shown");
			//122-124
			String extendDueDateHelpText=new HelpText().helpTextForGradingPolicy(10);//help text forextend due date
			if(!extendDueDateHelpText.contains("Select Enable if you want students to discuss assignment questions after each attempt."))
					Assert.fail("for collboration Help Text not shown");
			//128-130----requirment change
			
			/*String ExtendTimeLimitHelpText=new HelpText().helpTextForGradingPolicy(11);//help text for extend  time limit
			if(!ExtendTimeLimitHelpText.contains("You can choose whether you want to extend the time limit"))
					Assert.fail("for Extend Time limit  Help Text not shown");
			//119-121
			driver.findElement(By.xpath("//*[@id='assignment-policy-scroll-wrapper']/div/div/div/div[4]/div/div/div[6]/div[1]/div[2]/label[1]/input")).click();//click on enable for student due date
			//125-127
			driver.findElement(By.xpath("//*[@id='assignment-policy-scroll-wrapper']/div/div/div/div[4]/div/div/div[6]/div[2]/div[2]/label[1]/input")).click();//click on enable for student time limit
			//137-147
*/			new Click().clickbylist("ls-save-policy-btn",1);//click on use this policy
			String gradingpolicyAftersave=new TextFetch().textfetchbycssselector("div[class='ir-ls-assignment-policy-wrapper ir-ls-assignment-policy-content-item']");
			if(!gradingpolicyAftersave.contains(nameofpolicy))
				Assert.fail("name of policy not shown after click on use this policy");
			new Click().clickByid("assignment-policy-icons");
			new Click().clickByid("dialog-close");//close the pop-up after click on cross button
			new AssignLesson().assignResourcesFormrightTab(1);
			new Click().clickBycssselector("span[title='Update Assignment']");//click on update assignment
			new Navigator().NavigateTo("Assignment Policies");//navigate to assignment policy
			String assignmentPolicyHeadingOnAPPage=new TextFetch().textfetchbylistclass("assignment-policy-heading", 0);//verify assignment policy name
			if(!assignmentPolicyHeadingOnAPPage.contains(nameofpolicy))
				Assert.fail("Assignment policy not present on assignment policy page");
			
		}
		catch(Exception e)
		{
			Assert.fail("Exception in testcase PopUpToCreatePolicyOnFly in test method popUpToCreatePolicyOnFly",e);
		}
	}
	
	@Test(priority=2,enabled=true)
	public void CancleFunctionalityOfgradingPolicy()
	{
		try
		{

			String assignemntname=ReadTestData.readDataByTagName("", "assessmentname", "131");
			new Assignment().create(131);//create assignment
			new LoginUsingLTI().ltiLogin("131");//instructor login
			new AssignmentPolicy().openAssignmentPolicy(assignemntname);//open assignment policy form instructor resources page
			new Click().clickByid("dialog-close");//close the pop-up after click on cross button
			int popUpClose=driver.findElements(By.className("policy-dialog-header")).size();//check pop up close or not af
			if(popUpClose!=0)
				Assert.fail("after click on cross button pop up not close");
			new Click().clickByid("assignment-policy-icons");//click on + icon for grading policy
			new Click().clickByclassname("ls-cancel-policy-btn");//click on cancle link of pop up
			int popUpClose1=driver.findElements(By.className("policy-dialog-header")).size();
			if(popUpClose1!=0)
				Assert.fail("after click on cancle link pop up not close");
			
		}
		catch(Exception e)
		{
			Assert.fail("Exception in testcase PopUpToCreatePolicyOnFly in test method CancleFunctionalityOfgradingPolicy",e);
		}
	}

}
