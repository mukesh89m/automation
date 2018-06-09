package com.snapwiz.selenium.tests.staf.dummies.testcases.IT14.R32;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import com.snapwiz.selenium.tests.staf.dummies.Driver;
import com.snapwiz.selenium.tests.staf.dummies.apphelper.CreateOwnQuizByStudent;
import com.snapwiz.selenium.tests.staf.dummies.apphelper.DirectLogin;
import com.snapwiz.selenium.tests.staf.dummies.apphelper.PracticeTest;
import com.snapwiz.selenium.tests.staf.dummies.apphelper.UserCreate;
import com.snapwiz.selenium.tests.staf.dummies.uihelper.BooleanValue;
import com.snapwiz.selenium.tests.staf.dummies.uihelper.Click;
import com.snapwiz.selenium.tests.staf.dummies.uihelper.TextFetch;

public class PerformanceReportOfQuiz 
{
	@Test
	public void performanceReportOfQuiz()
	{
		try
		{
			Driver.startDriver();
			new UserCreate().CreateStudent("aa", "");//create student
			new DirectLogin().studentLogin("aa");
			new CreateOwnQuizByStudent().createOwnQuizByStudentFromAllTLO("1");
			for(int i=1; i<=4;i++)
			{
				new PracticeTest().attemptQuestion("correct", 0, false, false);//attempt 10 questions correctly
			}
			//299
			new PracticeTest().quitTestAndGoToDashboard();
			Thread.sleep(2000);
			//300-301
			boolean quizno=new BooleanValue().booleanbyid("al-peformance-title-id");
			if(quizno==false)
				Assert.fail("quiz number not shown");
			String pageheader=new TextFetch().textfetchbyclass("al-performance-chart-title");
			if(!pageheader.contains("Quiz Performance Summary"))
				Assert.fail("page header is not 'Quiz Performance Summary'");
			boolean performancereportfilter=new BooleanValue().booleanbyclass("performance-report-sidebar-title");
			if(performancereportfilter==false)
				Assert.fail("performance filter not shown on right side");
			boolean questioncard=new BooleanValue().booleanbyclass("al-performance-report-sidebar-content");
			if(questioncard==false)
				Assert.fail("question card not shown");
			boolean donatgraph=new BooleanValue().booleanbyclass("al-performance-chart-label");
			if(donatgraph==false)
				Assert.fail("donat graph not shown");
			String questiongraphtitle=new TextFetch().textfetchbyclass("al-chapter-performance-title");
			if(!questiongraphtitle.contains("Quiz Performance by Questions"))
				Assert.fail("question graph tilte is not 'Quiz Performance by Questions'");
			//Driver.driver.findElement(By.xpath("//*[@id='highcharts-7']/svg/g[5]/g[1]/rect[1]")).click();
			new Click().clickongraphbar();
			Thread.sleep(2000);
			//302-305
			boolean questionnumber=new BooleanValue().booleanbyclass("al-diag-test-question-label");//verify question number
			if(questionnumber==false)
				Assert.fail("question count nor shown");
			//308
			 boolean filteroption=new BooleanValue().booleanbyclass("al-performance-report-sidebar-dropown-sections");
			 if(filteroption==false)
				 Assert.fail("filter option on roght side not shown");
			 boolean questioncard1=new BooleanValue().booleanbyclass("al-performance-report-sidebar-content");//vrify question card after click on graph
			 if(questioncard1==false)
				Assert.fail("question card not shown after click on bar");
			 //307
			 new Click().clickByid("al-diagtest-markForReview");//clikc on mark as review
			 //313
			 new Click().clickByid("al-chapter-performance-view");//click on graph icon
			 //309
			 boolean questionDifficultyOnQuestionCard=new BooleanValue().booleanbyclass("question-card-difficulty-level");//verify difficulty level
			 if(questionDifficultyOnQuestionCard==false)
				 Assert.fail("assert question difficulty not shown on question card");
			 boolean timetakenonquestiononqcard=new BooleanValue().booleanbyclass("question-card-time-taken");//verify time spent
			 if(timetakenonquestiononqcard==false)
				 Assert.fail("time spent not shown on question card");
			 new Click().clickbylist("question-card-question-no", 1);//click on question card
			 boolean questionnumber1=new BooleanValue().booleanbyclass("al-diag-test-question-label");
				if(questionnumber1==false)
					Assert.fail("question count nor shown");
		}
		catch(Exception e)
		{
			Assert.fail("Exception in testcase PerformanceReportOfQuiz in test method performanceReportOfQuiz",e);
		}
	}
	@AfterMethod
	public void TearDown() throws Exception
	{
		Driver.driver.quit();
	}	

}
