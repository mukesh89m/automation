package com.snapwiz.selenium.tests.staf.dummies.testcases.IT14.R32;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import com.snapwiz.selenium.tests.staf.dummies.Driver;
import com.snapwiz.selenium.tests.staf.dummies.apphelper.CreateOwnQuizByStudent;
import com.snapwiz.selenium.tests.staf.dummies.apphelper.DirectLogin;
import com.snapwiz.selenium.tests.staf.dummies.apphelper.UserCreate;
import com.snapwiz.selenium.tests.staf.dummies.uihelper.Click;
import com.snapwiz.selenium.tests.staf.dummies.uihelper.ComboBox;
import com.snapwiz.selenium.tests.staf.dummies.uihelper.TextFetch;

public class StudentAbleToCreateOwnCustomQuiz
{
	@Test(priority=1,enabled=true)
	public void studentAbleToCreateOwnCustomQuiz()
	{
		try
		{
			Driver.startDriver();
			new UserCreate().CreateStudent("a", "");//create student
			new DirectLogin().studentLogin("a");
			Thread.sleep(2000);
			new CreateOwnQuizByStudent().OpenCustomQuizPage();//open custom quiz page
			Thread.sleep(2000);
			//81
			String pageheader=new TextFetch().textfetchbyclass("custom-quiz-description");//fetch page header text
			if(!pageheader.contains("Create your own quiz by selecting question types and learning objectives:"))
				Assert.fail("page header is not 'Create your own quiz by selecting question types and learning objectives:'");
			//82
			String quiztypeoption=new TextFetch().textfetchbyclass("quiz-mode-options");
			if(!quiztypeoption.contains("Practice"))
				Assert.fail("practice type quiz not shown");
			if(!quiztypeoption.contains("Test"))
				Assert.fail("Test type quiz not shown");
			/*if(!quiztypeoption.contains("Simulation"))
				Assert.fail("Simulation type quiz not shown");*/
			//83,84
			String practiceTestExplantion=new TextFetch().textfetchbycssselector("div[class='quiz-mode-explanation practice-explanation']");
			if(!practiceTestExplantion.contains("Practice mode reveals answer and explanation after each question is answered."))
				Assert.fail("practice test explanation not shown");
			//85
			new Click().clickbylist("quiz-mode ", 1	);//select test quiz type
			String TestExplantion=new TextFetch().textfetchbycssselector("div[class='quiz-mode-explanation test-explanation']");
			if(!TestExplantion.contains("Test mode withholds all answers and explanations until the quiz is finished."))
				Assert.fail("practice test explanation not shown");
			//86
			/*new Click().clickbylist("quiz-mode ", 2	);//select simulation quiz type
			String simulationTestExplantion=new TextFetch().textfetchbycssselector("div[class='quiz-mode-explanation expanded simulation-explanation']");
			if(!simulationTestExplantion.contains("Timed test in a test like interface where solution is visible only at the end of practice."))
				Assert.fail("practice test explanation not shown");*/
			
			//87,88
			Driver.driver.findElement(By.xpath("html/body/div[5]/div/div[3]/div/div/div[3]/div/div/a[2]")).click();//click on question type dropdown
			Thread.sleep(2000);
			String questiontype=Driver.driver.findElement(By.xpath("html/body/div[5]/div/div[3]/div/div/div[3]/div/div/ul")).getText();//fetch all type question option
			if(!questiontype.contains("All"))
				Assert.fail("All question type option not present");
			if(!questiontype.contains("Incorrect"))
				Assert.fail("Incorrect question type option not present");
			if(!questiontype.contains("Marked"))
				Assert.fail("Marked question type option not present");
			if(!questiontype.contains("Skipped"))
				Assert.fail("Skipped question type option not present");
			//89,90
			String questionCountUnanswred=new TextFetch().textfetchbyclass("quiz-data-question-count");
			Driver.driver.findElement(By.xpath("html/body/div[5]/div/div[3]/div/div/div[3]/div/div/a[2]")).click();//click on question type dropn down for close the open question type option div
			//91
			new ComboBox().selectValue(0, "All");
			String questionCountAll=new TextFetch().textfetchbyclass("quiz-data-question-count");
			if(!questionCountAll.contains(questionCountUnanswred))//verify unanswedd question type nad all question type
				Assert.fail("number of all and unanswered questions not same");
			//92
			new ComboBox().selectValue(0, "Incorrect");
			String questionCountIncorrect=new TextFetch().textfetchbyclass("quiz-data-question-count");
			//93
			new ComboBox().selectValue(0, "Marked");
			String questionCountMarked=new TextFetch().textfetchbyclass("quiz-data-question-count");			
			//94
			new ComboBox().selectValue(0, "Skipped");
			String questionCountSkipped=new TextFetch().textfetchbyclass("quiz-data-question-count");
			if(!questionCountIncorrect.contains("0"))//verify question type incorrect
				Assert.fail("incorrect question not 0 even non of question attempted");
			if(!questionCountMarked.contains("0"))//verify question type marked
				Assert.fail("marked question not 0 even non of question attempted");
			if(!questionCountSkipped.contains("0"))//verify question type skipped
				Assert.fail("skipped question not 0 even non of question attempted");
		}
		catch(Exception e)
		{
			Assert.fail("Exception uin testcase StudentAbleToCreateOwnCustomQuiz in test method studentAbleToCreateOwnCustomQuiz",e);
		}
	}
	
	@Test(priority=2,enabled=true)
	public void DifficultyLevelFunctionalityofCustomQuiz()
	{
		try
		{
			Driver.startDriver();
			new UserCreate().CreateStudent("a", "");//create student
			new DirectLogin().studentLogin("a");
			Thread.sleep(5000);
			new CreateOwnQuizByStudent().OpenCustomQuizPage();//open custom quiz page
			Thread.sleep(2000);
			String difficultylevel=new TextFetch().textfetchbyclass("difficulty-level-options");//fetch difficulty options
			if(!difficultylevel.contains("All"))
				Assert.fail("difficulty level option not conation 'All'");
			if(!difficultylevel.contains("Easy"))
				Assert.fail("difficulty level option not conation 'Easy'");
			if(!difficultylevel.contains("Medium"))
				Assert.fail("difficulty level option not conation 'Medium'");
			if(!difficultylevel.contains("Hard"))
				Assert.fail("difficulty level option not conation 'Hard'");
			int checkeddifficultylevel=Driver.driver.findElements(By.cssSelector("span[class='difficulty-level checked']")).size();
			if(checkeddifficultylevel!=4)
				Assert.fail("if all option select then all avilable optiion not selected");
			//107-130--select one by one difficulty option
			for(int i=1;i<=3;i++)
			{
				int totalanswerdquestion=Integer.parseInt(new TextFetch().textfetchbyclass("quiz-data-question-count"));//total question count before slected any one difficulty
				new Click().clickbylistcssselector("span[class='difficulty-level checked']", i);
				int checkedDifficultylevelAfterdeselectoneoption=Driver.driver.findElements(By.cssSelector("span[class='difficulty-level checked']")).size();//check number of
				if(checkedDifficultylevelAfterdeselectoneoption!=2)
					Assert.fail("after delect one option all option is not automatically deselect");
				int totalquestionafterdeselectoneoption=Integer.parseInt(new TextFetch().textfetchbyclass("quiz-data-question-count"));//total question after select one difficulty
				if(totalanswerdquestion<totalquestionafterdeselectoneoption)
					Assert.fail("answer count not changed");
				new Click().clickByclassname("difficulty-level");
			}
			//131-151 select one by two  difficulty option at a time
			for(int i=1;i<=3;i++)
			{
				int totalanswerdquestion=Integer.parseInt(new TextFetch().textfetchbyclass("quiz-data-question-count"));
				new Click().clickbylistcssselector("span[class='difficulty-level checked']", i);
				new Click().clickbylistcssselector("span[class='difficulty-level checked']", 1);
				int checkedDifficultylevelAfterdeselectoneoption=Driver.driver.findElements(By.cssSelector("span[class='difficulty-level checked']")).size();
				if(checkedDifficultylevelAfterdeselectoneoption!=1)
					Assert.fail("after delect one option all option is not automatically deselect");
				int totalquestionafterdeselectoneoption=Integer.parseInt(new TextFetch().textfetchbyclass("quiz-data-question-count"));
				if(totalanswerdquestion<totalquestionafterdeselectoneoption)
					Assert.fail("answer count not changed");
				new Click().clickByclassname("difficulty-level");
			}
			//102-106
			new Click().clickbylistcssselector("span[class='difficulty-level checked']", 0);//deselect all option for difficulty
			int checkedDifficultyoptionafterdeselctAll=Driver.driver.findElements(By.cssSelector("span[class='difficulty-level checked']")).size();
			if(checkedDifficultyoptionafterdeselctAll!=0)
				Assert.fail("after deselect All option other option not deselect ");
			new Click().clickByclassname("difficulty-level");//select all option for difficulty
			//158-160,161
			String learningObjectiveType=new TextFetch().textfetchbyclass("select-lo-options");//fetch learing objective options
			if(!learningObjectiveType.contains("All"))
				Assert.fail("'All' option not present for chossing LO");
			if(!learningObjectiveType.contains("Select Learning Objectives"))
				Assert.fail("'Select Learning Objectives' option not present for chossing LO");
			int LOchecked=Driver.driver.findElements(By.cssSelector("span[class='lo-checkbox checked']")).size();//count noumber of LO selected after click on 'select learing objective redio button"
			if(LOchecked==0)
				Assert.fail("all LO not checked if 'All' option selected");
			//171,175
			//Driver.driver.findElement(By.xpath("html/body/div[5]/div/div[3]/div/div/div[5]/div/div[2]/span")).click();//select 'select learing objective'option
            Driver.driver.findElement(By.cssSelector("span[class='select-lo ']")).click();
			int LOcheckedAfterSelectOtheroption=Driver.driver.findElements(By.cssSelector("span[class='lo-checkbox checked']")).size();
			if(LOcheckedAfterSelectOtheroption!=0)
				Assert.fail("all LO not unchecked if 'Select Learning Objectives' option selected");
			int totalquestionaAfterDeselectLO=Integer.parseInt(new TextFetch().textfetchbyclass("quiz-data-question-count"));
			if(totalquestionaAfterDeselectLO!=0)
				Assert.fail("questions count not updated after deselect all TLO");
			//178-182
			new Click().clickbylist("lo-checkbox", 0);//checked 1st LO
			int totalquestionaAftereselectoneLO=Integer.parseInt(new TextFetch().textfetchbyclass("quiz-data-question-count"));
			new Click().clickbylist("lo-checkbox", 1);//checked 2nd LO
			int totalquestionaAfterSelectTwoLO=Integer.parseInt(new TextFetch().textfetchbyclass("quiz-data-question-count"));
			if(totalquestionaAfterSelectTwoLO<totalquestionaAftereselectoneLO)//verify question increase or not
				Assert.fail("after select LO question count not update as LO increase");		
		}
		catch(Exception e)
		{
			Assert.fail("Exception uin testcase StudentAbleToCreateOwnCustomQuiz in test method DifficultyLevelFunctionalityofCustomQuiz",e);
		}
	}
	
	@AfterMethod
	public void TearDown() throws Exception
	{
		Driver.driver.quit();
	}	
}
