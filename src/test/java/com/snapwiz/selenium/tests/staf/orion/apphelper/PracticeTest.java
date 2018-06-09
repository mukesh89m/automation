package com.snapwiz.selenium.tests.staf.orion.apphelper;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.snapwiz.selenium.tests.staf.orion.Config;
import com.snapwiz.selenium.tests.staf.orion.uihelper.Click;
import com.snapwiz.selenium.tests.staf.orion.uihelper.RandomNumber;
import com.snapwiz.selenium.tests.staf.orion.uihelper.UIElement;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import com.mongodb.DBCollection;
import com.snapwiz.selenium.tests.staf.framework.controller.Driver;
import com.snapwiz.selenium.tests.staf.orion.ReadTestData;

public class PracticeTest extends Driver {

	public void startTest()
	{
		WebDriver driver=Driver.getWebDriver();
		try
		{
			//driver.findElement(By.cssSelector("span[title='Practice']")).click();
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("a[title='Practice']")));
			Thread.sleep(3000);
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromAppHelper();
			Assert.fail("Exception in starting the practice test",e);
		}
	}

	public void startPracticeTest()
	{
		WebDriver driver=Driver.getWebDriver();
		try
		{
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.xpath("//div[@class='al-content-header-row']//a")));
			Thread.sleep(3000);
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromAppHelper();
			Assert.fail("Exception in starting the practice test",e);
		}
	}
	public void startTLOLevelPracticeTest(int tloIndex)
	 {
		 WebDriver driver=Driver.getWebDriver();
	  try
	  {		  		
		  		List<String> tlonames = new PracticeTest().tloNames();
		  		System.out.println(tlonames.get(tloIndex));
		  		//List<WebElement> practicebuttons = driver.findElements(By.cssSelector("div[tloname='"+tlonames.get(tloIndex)+"']"));
		  		List<WebElement> tlolist = driver.findElements(By.className("al-terminal-objective-title"));
		  		for(WebElement tlo : tlolist)
		  		{
		  			if(tlo.getText().equals(tlonames.get(tloIndex)))
		  			{
		  				Actions action = new Actions(driver);
		  				action.moveToElement(tlo).build().perform();
		  				break;
		  			}
		  		}
		  		
				/*WebElement we = driver.findElement(By.className(classname));

		  	    MouseHover.mouserhover("al-terminal-objective-title");*/
		  	    driver.findElement(By.cssSelector("span[title='"+tlonames.get(tloIndex)+"']")).click();
		  		//driver.findElement(By.className("al-terminal-objective-title"))
		  		//((JavascriptExecutor) driver).executeScript("arguments[0].click();",practicebuttons.get(practicebuttons.size()-1));
		  		Thread.sleep(3000);
		  		//((JavascriptExecutor) driver).executeScript("arguments[0].click();",practicebuttons.get(practicebuttons.size()-1));

	   }
	  catch(Exception e)
	  {
	   Assert.fail("Exception in app helper method startTLOLevelPracticeTest in class SelectChapterForTest",e);
	  }
	 }
	public void quitTestAndGoToDashboard()
	{
		WebDriver driver=Driver.getWebDriver();
		try
		{
			driver.findElement(By.className("al-quit-diag-test-icon")).click();
			Thread.sleep(1000);
			driver.findElement(By.className("al-diag-test-continue-later")).click();
			Thread.sleep(2000);
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromAppHelper();
			Assert.fail("Exception in quitting the practice test",e);
		}
	}
	public void quitTestAndGoToReport()
	{
		WebDriver driver=Driver.getWebDriver();
		try
		{
			driver.findElement(By.className("al-quit-diag-test-icon")).click();
			Thread.sleep(5000);
			new UIElement().waitAndFindElement(By.className("al-view-practice-test-report"));
			driver.findElement(By.className("al-view-practice-test-report")).click();
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromAppHelper();
			Assert.fail("Exception in quitting the practice test",e);
		}
	}
	
	public List<Integer> attemptedCount()
	{
		WebDriver driver=Driver.getWebDriver();
		List<Integer> tloquestionattemptedcount = new ArrayList<Integer>();
		List<WebElement> noofattemptedquestions =  driver.findElements(By.className("al-preformance-text"));
		try
		{			
			for(WebElement noattempted : noofattemptedquestions)
			{
				if(noattempted.getText().contains("/"))
				{
					int startIndex = noattempted.getText().indexOf("/");
					tloquestionattemptedcount.add(Integer.parseInt(noattempted.getText().substring(startIndex+1)));
				}
			}			
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromAppHelper();
			Assert.fail("Exception in fetching the no of quetions attempted",e);
		}
		
		return tloquestionattemptedcount;
	}
	
	public List<String> tloNames()
	{
		List<String> tlonames = new ArrayList<String>();
		try
		{
		String tlo1 = ReadTestData.readDataByTagName("tlos","tlo1" , "0");
		String tlo2 = ReadTestData.readDataByTagName("tlos","tlo2" , "0");
		String tlo3 = ReadTestData.readDataByTagName("tlos","tlo3" , "0");
		String tlo4 = ReadTestData.readDataByTagName("tlos","tlo4" , "0");
		String tlo5 = ReadTestData.readDataByTagName("tlos","tlo5" , "0");
		String tlo6 = ReadTestData.readDataByTagName("tlos","tlo6" , "0");
		String tlo7 = ReadTestData.readDataByTagName("tlos","tlo7" , "0");		
		tlonames.add(tlo1); tlonames.add(tlo2); tlonames.add(tlo3); tlonames.add(tlo4); tlonames.add(tlo5); tlonames.add(tlo6);tlonames.add(tlo7);
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromAppHelper();
			Assert.fail("Exception in fetching TLO names from test data",e);
		}
		return tlonames;
	}

	
	public List<String> tloIds()
	{
		List<String> tloids = new ArrayList<String>();
		try
		{
		String tlo1 = ReadTestData.readDataByTagName("tloids","tlo1" , "0");
		String tlo2 = ReadTestData.readDataByTagName("tloids","tlo2" , "0");
		String tlo3 = ReadTestData.readDataByTagName("tloids","tlo3" , "0");
		String tlo4 = ReadTestData.readDataByTagName("tloids","tlo4" , "0");
		String tlo5 = ReadTestData.readDataByTagName("tloids","tlo5" , "0");
		String tlo6 = ReadTestData.readDataByTagName("tloids","tlo6" , "0");
		String tlo7 = ReadTestData.readDataByTagName("tloids","tlo7" , "0");		
		tloids.add(tlo1); tloids.add(tlo2); tloids.add(tlo3); tloids.add(tlo4); tloids.add(tlo5); tloids.add(tlo6);tloids.add(tlo7);
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromAppHelper();
			Assert.fail("Exception in fetching TLO names from test data",e);
		}
		return tloids;
	}

    public void AttemptCorrectAnswer(int confidencelevel) {
        try {
			WebDriver driver=Driver.getWebDriver();
            String corranswer = driver.findElement(By.id("show-debug-correct-answer-block")).getText();
            char correctanswer = corranswer.charAt(17);
            String correctchoice = Character.toString(correctanswer);
            String corranswertext = driver.findElement(By.id("show-debug-correct-answer-block")).getText();
            int lastindex = corranswertext.length();
            String correcttextanswer = corranswertext.substring(16, lastindex);

            if (driver.findElements(By.className("multiple-selection-answer-choice-label-wrapper")).size() > 0) {
                String corranswer1 = driver.findElement(By.id("show-debug-correct-answer-block")).getText();
                if (corranswer1.length() > 21) {
                    char correctanswer1 = corranswer1.charAt(22);
                    String correctchoice1 = Character.toString(correctanswer1);
                    List<WebElement> answer1 = driver.findElements(By.className("qtn-label"));
                    for (WebElement answerchoice1 : answer1) {

                        if (answerchoice1.getText().trim().equals(correctchoice1)) {
                            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", answerchoice1);
                            break;
                        }
                    }
                }
                List<WebElement> answer = driver.findElements(By.className("qtn-label"));
                for (WebElement answerchoice : answer) {

                    if (answerchoice.getText().trim().equals(correctchoice)) {
                        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", answerchoice);
                        break;
                    }
                }


            } else if (driver.findElements(By.className("answer-choice-label-wrapper")).size() > 0) //single select and true/false question type
            {
                List<WebElement> answer = driver.findElements(By.className("qtn-label"));
                for (WebElement answerchoice : answer) {
                    if (answerchoice.getText().trim().equals(correctchoice)) {
                        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", answerchoice);
                        break;
                    }
                }
            } else if (driver.findElements(By.cssSelector("span[class='input-tag-wrapper question-element-wrapper']")).size() > 0) {
                driver.findElement(By.cssSelector("input[id='1']")).sendKeys(correcttextanswer);
            } else if (driver.findElements(By.className("sbHolder")).size() > 0) {
                try {
                    driver.findElement(By.className("sbToggle")).click();
                    //Thread.sleep(2000);
                    driver.findElement(By.linkText(correcttextanswer)).click();
                    //Thread.sleep(5000);
                } catch (Exception e) {
                    Assert.fail("Exception in App helper attemptQuestion in class PracticeTest", e);
                }
            }
            if (confidencelevel != 0)
                driver.findElement(By.cssSelector("a[id='" + confidencelevel + "']")).click();//click on confidence level

            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("div[class='al-practice-test-submit-button']>input")));

            int noticesize = driver.findElements(By.className("al-notification-message-body")).size();
            if (noticesize == 1)

            {
                String notice = driver.findElement(By.className("al-notification-message-body")).getText();
                if (notice.equals("You will build your proficiency faster if you say how confident you feel about the question before answering.")) {
                    Thread.sleep(3000);
                    driver.findElement(By.cssSelector("div[id='close-al-notification-dialog']")).click();
                    driver.findElement(By.className("al-practice-test-submit-button")).click();
                    Thread.sleep(2000);
                }

            }
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("div[id='al-next-practice-question-button']>input")));
            Thread.sleep(2000);

        } catch (Exception e) {
            new Screenshot().captureScreenshotFromAppHelper();
            Assert.fail("Exception in App helper AttemptCorrectAnswer in class PracticeTest", e);
        }
    }
	public int questionDifficulty()
		{
		String diff = null;
		try
		{
			WebDriver driver=Driver.getWebDriver();
		List<WebElement> debugvalues = driver.findElements(By.id("show-debug-question-id-label"));
		diff = debugvalues.get(1).getText();
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromAppHelper();
			Assert.fail("Exception in app helper questionDifficulty in class PracticeTest",e);
		}
		return Integer.parseInt(diff.substring(13));
		}
	
	public void questionPresentCheck(String tcIndex)
	{
		try
		{
			DBCollection collection = DBConnect.mongoConnect("UserQuestionHistory");
			 int userid=0,classid=0;
			 String username = ReadTestData.readDataByTagName("", "user_id", tcIndex);
			
			 ResultSet userids = DBConnect.st.executeQuery("SELECT id as userid from t_user where username like '"+username+"';");
			 while(userids.next())
			 	{
				 userid = userids.getInt("userid");
			 	}
			 
			 ResultSet classsections = DBConnect.st.executeQuery("SELECT id as classid FROM t_class_section  where name like 'studtitle';");
			 while(classsections.next())
			 	{
				 classid = classsections.getInt("classid");
			 	}

			WebDriver driver=Driver.getWebDriver();
		String tlonamefound = driver.findElement(By.className("al-diagtest-skills-evaluted-ul")).getText().substring(2);	
		
		List<String> tlonames = new PracticeTest().tloNames();		//Get TLO names	
		int index = 0; //Get index at which the TLO is present
		for (String tlo : tlonames)
		{
			if(tlo.equals(tlonamefound))
				break;
			index++;
		}		
		List<Double> profs = DBConnect.getProficienciesFromMongo(userid,classid); //Get the proficiency of the TLO found
		Double tloprof = profs.get(index);
		int proficiency = (int)(tloprof*100);
		
		tlonamefound = tlonamefound.replaceAll("'", "''");		
		
		List<Integer> quesids = new ArrayList<Integer>();
		//List<Integer> qidsgrcq = new ArrayList<Integer>();	
		
		ResultSet rstidltcq = DBConnect.st.executeQuery("select tq.id as qids from t_questions tq, t_assessment ta, t_question_set tqs, t_question_set_item tqsi,t_master_skill tms, t_question_skill_map tqsm where ta.id = tqs.assessment_id and"+
			        " tq.id = tqsi.question_id and tqs.id = tqsi.question_set_id and tms.id = tqsm.master_skill_id and tq.id = tqsm.question_id and"+
			        " ta.id = 11052 and tms.name like '"+tlonamefound+"' and tq.status = 80 order by tq.computed_difficulty;");
					while(rstidltcq.next())
					{
						quesids.add(rstidltcq.getInt("qids"));
					}
					
		List<Integer> notattemptedquestions = new ArrayList<Integer>();
					for(Integer qids : quesids)
					{
					int value = DBConnect.getAttemptedQuestions(qids,collection,userid,classid);
					if(value == 0)
						{
						notattemptedquestions.add(qids);						
						}
					}
		Map<Integer,Integer> qid_diff = new HashMap<Integer,Integer>();
		for(Integer qid : notattemptedquestions)	
		{
		ResultSet comp_diff = DBConnect.st.executeQuery("select tq.computed_difficulty as comp_difficulty from t_questions tq, t_assessment ta, t_question_set tqs, t_question_set_item tqsi,t_master_skill tms, t_question_skill_map tqsm where ta.id = tqs.assessment_id and"+
				" tq.id = tqsi.question_id and tqs.id = tqsi.question_set_id and tms.id = tqsm.master_skill_id and tq.id = tqsm.question_id and"+
				" ta.id = 11052 and tms.name like '"+tlonamefound+"' and tq.status = 80 and tq.id = "+(qid)+";");
		while(comp_diff.next())
			{
			qid_diff.put(qid, comp_diff.getInt("comp_difficulty"));
			}
		}
		Set set = qid_diff.entrySet();
		Iterator i = set.iterator();
		Iterator i2 = set.iterator();
		while(i2.hasNext()) {
			 Map.Entry me2 = (Map.Entry)i2.next();
	         System.out.print("QID: "+me2.getKey() + " ");
	         System.out.print("CD :"+me2.getValue()+ " ");
	         System.out.println("Distance :"+Math.abs((proficiency  - (Integer)me2.getValue())));
	      }
		System.out.println();
		System.out.println();
		while(i.hasNext()) {
	         Map.Entry me = (Map.Entry)i.next();
	         qid_diff.put((Integer)me.getKey(), Math.abs((proficiency  - (Integer)me.getValue())));
	      }
		Integer min = Collections.min(qid_diff.values());
		System.out.println("Minimum Value of Distance "+min);
		List<Integer> expectedqid = new ArrayList<Integer>();
		Iterator i_min = set.iterator();
		while(i_min.hasNext()) {
		
			 Map.Entry me_min = (Map.Entry)i_min.next();
			 if((Integer)me_min.getValue() == min)
			 {
				 expectedqid.add((Integer)me_min.getKey());
			 }
		}
		for(Integer qid : expectedqid) System.out.println("Expected questions "+qid);
		List<Integer> expected_comp_diff = new ArrayList<Integer>();
		for(Integer qid : expectedqid)	
		{
		ResultSet comp_diff = DBConnect.st.executeQuery("select tq.computed_difficulty as comp_difficulty from t_questions tq, t_assessment ta, t_question_set tqs, t_question_set_item tqsi,t_master_skill tms, t_question_skill_map tqsm where ta.id = tqs.assessment_id and"+
				" tq.id = tqsi.question_id and tqs.id = tqsi.question_set_id and tms.id = tqsm.master_skill_id and tq.id = tqsm.question_id and"+
				" ta.id = 11052 and tms.name like '"+tlonamefound+"' and tq.status = 80 and tq.id = "+(qid)+";");
		while(comp_diff.next())
			{
			expected_comp_diff.add(comp_diff.getInt("comp_difficulty"));
			}
		}
		int rangeLower=0,rangeUpper=0;
		for(Integer compdiff : expected_comp_diff) 
		{
			rangeLower = compdiff-15; rangeUpper = compdiff+15;
			System.out.println("Expected Computed Difficulty range "+(compdiff-15) +"-"+(compdiff+15));
		}
		
		System.out.println();
		int questiondifficulty = questionDifficulty();
		System.out.println("computed difficulty found "+questiondifficulty);
		boolean inRange=false;
		if(questiondifficulty >= rangeLower && questiondifficulty <= rangeUpper)
			inRange = true;
		if(inRange == false)
		{
			new Screenshot().captureScreenshotFromAppHelper();
			Assert.fail("Question not as Expected. "+"Difficulty Found "+questiondifficulty+". Expected in Range "+rangeLower+"-"+rangeUpper+" from TLO "+tlonamefound+" with Proficiency "+proficiency);
		}
		
		/*int suggesstedidfromlowerside =0;
		int suggestedidfromupperside =0;
		if(qidsltcq.size() > 0)			
		suggesstedidfromlowerside = qidsltcq.get(0);
		if(qidsgrcq.size()>0)
		suggestedidfromupperside =  qidsgrcq.get(0);
		
		int lowercomputeddifficulty=0;
		ResultSet computeddifffromlowerside = DBConnect.st.executeQuery("SELECT computed_difficulty as compdifflower from t_questions where id= '"+suggesstedidfromlowerside+"'");
		while(computeddifffromlowerside.next())
		{
			lowercomputeddifficulty = computeddifffromlowerside.getInt("compdifflower");
		}
		
		int uppercomputeddifficulty=0;
		ResultSet computeddifffromupperside = DBConnect.st.executeQuery("SELECT computed_difficulty as compdiffupper from t_questions where id= '"+suggestedidfromupperside+"'");
		while(computeddifffromupperside.next())
		{
			uppercomputeddifficulty = computeddifffromupperside.getInt("compdiffupper");
		}
		
		//String question_id = driver.findElement(By.id("show-debug-question-id-label")).getText().substring(13);
		//System.out.println("Qeustion ID found "+question_id);
		System.out.println("lower comp diff "+lowercomputeddifficulty);
		System.out.println("upper comp diff "+uppercomputeddifficulty);
		int rangelower = 0; int rangeupper = 0;
		if(lowercomputeddifficulty == 0 && uppercomputeddifficulty !=0)
		{
			rangelower = uppercomputeddifficulty-10;
			rangeupper = uppercomputeddifficulty+10;
		}
		else if (uppercomputeddifficulty == 0 && lowercomputeddifficulty !=0)
		{
			rangelower = lowercomputeddifficulty-10;
			rangeupper = lowercomputeddifficulty+10;
		}
		else if(uppercomputeddifficulty - cqtloprof < cqtloprof - lowercomputeddifficulty)
		{
			rangelower = uppercomputeddifficulty-10;
			rangeupper = uppercomputeddifficulty+10;
		}
		else if(uppercomputeddifficulty - cqtloprof > cqtloprof - lowercomputeddifficulty)
		{
			rangelower = lowercomputeddifficulty-10;
			rangeupper = lowercomputeddifficulty+10;
		}
		else
		{
			rangelower = lowercomputeddifficulty-10;
			rangeupper = uppercomputeddifficulty+10;
		}
		System.out.println("Lower Range "+rangelower);
		System.out.println("Upper Range "+rangeupper);
		
		
		String nextQuesTloName = driver.findElement(By.className("al-diagtest-skills-evaluted-ul")).getText().substring(2).replaceAll("'", "''");
		System.out.println("Next Ques TLO "+nextQuesTloName);
		boolean inRange = false;
		int questiondifficulty = new PracticeTest().questionDifficulty();
		System.out.println("Difficulty found "+questiondifficulty);
		if(questiondifficulty >=rangelower && questiondifficulty <=rangeupper )
			inRange = true;
		*/
		/*List<Integer> notatteptedltquest_notinrange = new ArrayList<Integer>();
		List<Integer> notatteptedgrquest_notinrange = new ArrayList<Integer>();
		List<Integer> qidsltcq_notinrange = new ArrayList<Integer>();
		List<Integer> qidsgrcq_notinrange = new ArrayList<Integer>();
		
		List<Integer> inrangeqids = new ArrayList<Integer>();
		List<Integer> notattemptedquestinrange = new ArrayList<Integer>();
		if(inRange == false) //checking if there are non-attempted questions in the TLO which are in Range
		{
			ResultSet inrangequestions = DBConnect.st.executeQuery("select tq.id as qids from t_questions tq, t_assessment ta, t_question_set tqs, t_question_set_item tqsi,t_master_skill tms, t_question_skill_map tqsm where ta.id = tqs.assessment_id and"+
			        " tq.id = tqsi.question_id and tqs.id = tqsi.question_set_id and tms.id = tqsm.master_skill_id and tq.id = tqsm.question_id and"+
			        " ta.id = 11052 and tms.name like '"+nextQuesTloName+"' and tq.status = 80 and tq.computed_difficulty >= "+(rangelower)+" and  tq.computed_difficulty <= "+(rangeupper)+";");
					while(inrangequestions.next())
					{
						inrangeqids.add(inrangequestions.getInt("qids"));
					}
			for(Integer qids : inrangeqids)
					{
					int value = DBConnect.getAttemptedQuestions(qids,collection,userid,classid);
					if(value == 0)
						{
						notattemptedquestinrange.add(qids);						
						}
					}
			for(Integer ques : inrangeqids) System.out.println("In range questions "+ques);
			for(Integer ques : notattemptedquestinrange) System.out.println("Not attempted questions "+ques);
			if(notattemptedquestinrange.size() > 0)
				Assert.fail("There are questions in this TLO which has computed difficulty in the suggested range and are not attempted but are still not presented as the next question");
		}
		
		if(inRange == false)
		{		
			//int currentquestdiff = questionDifficulty();
			ResultSet rstidltcq_notinrange = DBConnect.st.executeQuery("select tq.id as qids from t_questions tq, t_assessment ta, t_question_set tqs, t_question_set_item tqsi,t_master_skill tms, t_question_skill_map tqsm where ta.id = tqs.assessment_id and"+
			        " tq.id = tqsi.question_id and tqs.id = tqsi.question_set_id and tms.id = tqsm.master_skill_id and tq.id = tqsm.question_id and"+
			        " ta.id = 11052 and tms.name like '"+nextQuesTloName+"' and tq.status = 80 and tq.computed_difficulty < "+(rangelower)+" order by tq.computed_difficulty desc;");
					while(rstidltcq_notinrange.next())
					{
						qidsltcq_notinrange.add(rstidltcq_notinrange.getInt("qids"));
					}
					
					
		ResultSet rstidgrcq_notinrange = DBConnect.st.executeQuery("select tq.id as qids from t_questions tq, t_assessment ta, t_question_set tqs, t_question_set_item tqsi,t_master_skill tms, t_question_skill_map tqsm where ta.id = tqs.assessment_id and"+
					" tq.id = tqsi.question_id and tqs.id = tqsi.question_set_id and tms.id = tqsm.master_skill_id and tq.id = tqsm.question_id and"+
					" ta.id = 11052 and tms.name like '"+nextQuesTloName+"' and tq.status = 80 and tq.computed_difficulty > "+(rangeupper)+" order by tq.computed_difficulty;");
					while(rstidgrcq_notinrange.next())
					{
						qidsgrcq_notinrange.add(rstidgrcq_notinrange.getInt("qids"));
						
					}			
					
					
					int suggesstedidfromlowerside_notinrange =0;
					int suggestedidfromupperside_notinrange =0;
		for(Integer qids : qidsltcq_notinrange)
				{
				int value = DBConnect.getAttemptedQuestions(qids,collection,userid,classid);
				if(value == 0)
				{
					notatteptedltquest_notinrange.add(qids);
					suggesstedidfromlowerside_notinrange = notatteptedltquest_notinrange.get(0);
					break;
				}
				}
					
		for(Integer qids : qidsgrcq_notinrange)
				{
					int value = DBConnect.getAttemptedQuestions(qids,collection,userid,classid);
					if(value == 0)
					{
							notatteptedgrquest_notinrange.add(qids);
							suggestedidfromupperside_notinrange =  notatteptedgrquest_notinrange.get(0);
							break;
					}
				}					
		
		int lowercomputeddifficulty_notinrange=0;
		ResultSet computeddifffromlowerside_notinrange = DBConnect.st.executeQuery("SELECT computed_difficulty as compdifflower from t_questions where id= '"+suggesstedidfromlowerside_notinrange+"'");
		while(computeddifffromlowerside_notinrange.next())
		{
			lowercomputeddifficulty_notinrange = computeddifffromlowerside_notinrange.getInt("compdifflower");
		}
		
		int uppercomputeddifficulty_notinrange=0;
		ResultSet computeddifffromupperside_notinrange = DBConnect.st.executeQuery("SELECT computed_difficulty as compdiffupper from t_questions where id= '"+suggestedidfromupperside_notinrange+"'");
		while(computeddifffromupperside_notinrange.next())
		{
			uppercomputeddifficulty_notinrange = computeddifffromupperside_notinrange.getInt("compdiffupper");
		}
		System.out.println("suggested from lower side not in range "+lowercomputeddifficulty_notinrange);
		System.out.println("suggested from upper side no in range "+uppercomputeddifficulty_notinrange);
		//int questiondiff = questionDifficulty();
		//System.out.println("Question Difficulty found "+questiondiff);
		if(questiondifficulty == lowercomputeddifficulty_notinrange || questiondifficulty == uppercomputeddifficulty_notinrange)
			inRange=true;
		
		}
		if(inRange == false)
		{
			new Screenshot().captureScreenshotFromAppHelper();
			Assert.fail("Next question difficulty not in range. Found Difficulty");
		}*/
		//int lastidofltcq = qidsltcq.get(qidsltcq.size()-1);
		
	}
	catch(Exception e)
	{
		new Screenshot().captureScreenshotFromAppHelper();
		Assert.fail("Exception in App helper questionPresentCheck in class PracticeTest",e);
	}
	}
	
	public void questionPresentCheckRepeatFlow(String tcIndex)
	{
		try
		{
            /*
            select id,name from t_assessment where name like 'Practice - The Changing Face of Business';
             */
			int assessmentid = 11052;
			WebDriver driver=Driver.getWebDriver();
			
		// System.out.println("Assessment ID "+assessmentid);
			DBCollection collection = DBConnect.mongoConnect("UserQuestionHistory");
			 int userid=0,classid=0;
			 String username = ReadTestData.readDataByTagName("", "user_id", tcIndex);
			
			 ResultSet userids = DBConnect.st.executeQuery("SELECT id as userid from t_user where username like '"+username+"';");
			 while(userids.next())
			 	{
				 userid = userids.getInt("userid");
			 	}
			 
			 ResultSet classsections = DBConnect.st.executeQuery("SELECT id as classid FROM t_class_section  where name like 'studrepeattitle179';");
			 while(classsections.next())
			 	{
				 classid = classsections.getInt("classid");
			 	}
			
			System.out.println(userid); System.out.println(classid);
		String tlonamefound = driver.findElement(By.className("al-diagtest-skills-evaluted-ul")).getText().substring(2);	
		
		List<String> tlonames = new PracticeTest().tloNames();		//Get TLO names	
		int index = 0; //Get index at which the TLO is present
		for (String tlo : tlonames)
		{
			if(tlo.equals(tlonamefound))
				break;
			index++;
		}		
		List<Double> profs = DBConnect.getProficienciesFromMongo(userid,classid); //Get the proficiency of the TLO found
		Double tloprof = profs.get(index);
		int proficiency = (int)(tloprof*100);
		System.out.println("TLO proficiency "+proficiency);
		tlonamefound = tlonamefound.replaceAll("'", "''");		
		
		List<Integer> quesids = new ArrayList<Integer>();
		//List<Integer> qidsgrcq = new ArrayList<Integer>();	
		
		ResultSet rstidltcq = DBConnect.st.executeQuery("select tq.id as qids from t_questions tq, t_assessment ta, t_question_set tqs, t_question_set_item tqsi,t_master_skill tms, t_question_skill_map tqsm where ta.id = tqs.assessment_id and"+
			        " tq.id = tqsi.question_id and tqs.id = tqsi.question_set_id and tms.id = tqsm.master_skill_id and tq.id = tqsm.question_id and"+
			        " ta.id = "+assessmentid+" and tms.name like '"+tlonamefound+"' and tq.status = 80 order by tq.computed_difficulty;");
					while(rstidltcq.next())
					{
						quesids.add(rstidltcq.getInt("qids"));
					}
					
		List<Integer> questions = new ArrayList<Integer>();
					for(Integer qids : quesids)
					{
					int value = DBConnect.getAttemptedQuestions(qids,collection,userid,classid);
					if(value == 0)
						{
						questions.add(qids);
						System.out.println("Unattempted question with id "+qids+" expected. TLO "+tlonamefound);
						}
					}
					System.out.println("Size initially "+questions.size());
		if(questions.size() == 0) //If all questions are attempted
		{
			for(Integer qids : quesids)
			{
			int value = DBConnect.getQuestions(qids,collection,userid,classid,"skipped"); //checking for skipped questions
			if(value != 0)
				{
				questions.add(qids);
				System.out.println("Skipped question with id "+qids+" expected. Questions Exhaused TLO "+tlonamefound);
				}
			}
			
		}
		
		if(questions.size() == 0) //If there are no skipped questions, checking for incorrectly answered questions
		{
			for(Integer qids : quesids)
			{
			int value = DBConnect.getQuestions(qids,collection,userid,classid,"incorrect"); //checking for incorrectly answered questions
			if(value != 0)
				{
				questions.add(qids);	
				System.out.println("Incorrect question with id "+qids+" expected. Questions Exhaused TLO "+tlonamefound);
				}
			}
			
		}
		
		if(questions.size() == 0) //If there are no skipped and incorrect questions, checking for correctly answered questions
		{
			for(Integer qids : quesids)
			{
			int value = DBConnect.getQuestions(qids,collection,userid,classid,"correct"); //checking for correctly answered questions
			if(value != 0)
				{
				questions.add(qids);	
				System.out.println("Correct question with id "+qids+" expected. Questions Exhausted TLO: "+tlonamefound);
				}
			}	
			
		}
		
		String question_id_found = driver.findElement(By.id("show-debug-question-id-label")).getText().substring(13);
		System.out.println("Question Found "+question_id_found);
		//if(!tlonamefound.equals("Outline the characteristics that make a company admired."))
		//{
		if(!questions.contains(Integer.parseInt(question_id_found)))
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Question Found "+question_id_found+". Expected "+questions+" from TLO "+tlonamefound);
		}
		//}
		Map<Integer,Integer> qid_diff = new HashMap<Integer,Integer>();
		for(Integer qid : questions)	
		{
		ResultSet comp_diff = DBConnect.st.executeQuery("select tq.computed_difficulty as comp_difficulty from t_questions tq, t_assessment ta, t_question_set tqs, t_question_set_item tqsi,t_master_skill tms, t_question_skill_map tqsm where ta.id = tqs.assessment_id and"+
				" tq.id = tqsi.question_id and tqs.id = tqsi.question_set_id and tms.id = tqsm.master_skill_id and tq.id = tqsm.question_id and"+
				" ta.id = "+assessmentid+" and tms.name like '"+tlonamefound+"' and tq.status = 80 and tq.id = "+(qid)+";");
		while(comp_diff.next())
			{
			qid_diff.put(qid, comp_diff.getInt("comp_difficulty"));
			}
		}
		Set set = qid_diff.entrySet();
		Iterator i = set.iterator();
		Iterator i2 = set.iterator();
		while(i2.hasNext()) {
			 Map.Entry me2 = (Map.Entry)i2.next();
	         System.out.print("QID: "+me2.getKey() + " ");
	         System.out.print("CD :"+me2.getValue()+ " ");
	         System.out.println("Distance :"+Math.abs((proficiency  - (Integer)me2.getValue())));
	      }
		System.out.println();
		System.out.println();
		while(i.hasNext()) {
	         Map.Entry me = (Map.Entry)i.next();
	         qid_diff.put((Integer)me.getKey(), Math.abs((proficiency  - (Integer)me.getValue())));
	      }
		Integer min = Collections.min(qid_diff.values());
		System.out.println("Minimum Value of Distance "+min);
		List<Integer> expectedqid = new ArrayList<Integer>();
		Iterator i_min = set.iterator();
		while(i_min.hasNext()) {
		
			 Map.Entry me_min = (Map.Entry)i_min.next();
			 if((Integer)me_min.getValue() == min)
			 {
				 expectedqid.add((Integer)me_min.getKey());
			 }
		}
		//for(Integer qid : expectedqid) System.out.println("Expected questions "+qid);
		List<Integer> expected_comp_diff = new ArrayList<Integer>();
		for(Integer qid : expectedqid)	
		{
		ResultSet comp_diff = DBConnect.st.executeQuery("select tq.computed_difficulty as comp_difficulty from t_questions tq, t_assessment ta, t_question_set tqs, t_question_set_item tqsi,t_master_skill tms, t_question_skill_map tqsm where ta.id = tqs.assessment_id and"+
				" tq.id = tqsi.question_id and tqs.id = tqsi.question_set_id and tms.id = tqsm.master_skill_id and tq.id = tqsm.question_id and"+
				" ta.id = "+assessmentid+" and tms.name like '"+tlonamefound+"' and tq.status = 80 and tq.id = "+(qid)+";");
		while(comp_diff.next())
			{
			expected_comp_diff.add(comp_diff.getInt("comp_difficulty"));
			}
		}
		int rangeLower=0,rangeUpper=0;
		for(Integer compdiff : expected_comp_diff) 
		{
			rangeLower = compdiff-15; rangeUpper = compdiff+15;
			System.out.println("Expected Computed Difficulty range "+(compdiff-15) +"-"+(compdiff+15));
		}
		
		System.out.println();
		int questiondifficulty = questionDifficulty();
		System.out.println("computed difficulty found "+questiondifficulty);
		boolean inRange=false;
		if(questiondifficulty >= rangeLower && questiondifficulty <= rangeUpper)
			inRange = true;
		if(inRange == false)
		{
			new Screenshot().captureScreenshotFromAppHelper();
			Assert.fail("Question not as Expected. "+"Difficulty Found "+questiondifficulty+". Expected in Range "+rangeLower+"-"+rangeUpper+" from TLO "+tlonamefound+" with Proficiency "+proficiency);
		}
		}
		catch(Exception e)
		{
			Assert.fail("",e);
		}
	}
	
	public void openLastPracticeTest()
	{
		try
		{
			WebDriver driver=Driver.getWebDriver();
			List<WebElement> allPractice=driver.findElements(By.cssSelector("a[title='Practice']"));
			((JavascriptExecutor)driver).executeScript("arguments[0].click();",allPractice.get(allPractice.size()-1));//click on latest created practice test 
			Thread.sleep(2000);
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromAppHelper();
			Assert.fail("Exception in App helper openLastPracticeTest in class PracticeTest",e);
		}
	}
	
	
	  public void attemptPracticeQuesFromEachTLO(int questionsFromEachTLO,String answerChoice,int confidence, boolean useHints, boolean useSolutionText)
	   {
		   WebDriver driver=Driver.getWebDriver();
	    try
	    {
	    List<String> tlonames = new PracticeTest().tloNames();		//Get TLO names	
	    //List<String> tlosattempted = new ArrayList<String>();	    
	    int tlo1cnt = 0; int tlo2cnt = 0; int tlo3cnt = 0; int tlo4cnt = 0; int tlo5cnt = 0;int tlo6cnt = 0;int tlo7cnt = 0;
	    
	    boolean done = false;
	    while(done == false)
	    {
	     String tlonamefound = driver.findElement(By.className("al-diagtest-skills-evaluted-ul")).getText().substring(2); //TLO from Question Page
	     System.out.println(tlonamefound);
	     System.out.println(tlonames.get(4));
	    	 if(tlonamefound.equals(tlonames.get(0)))
	    		 tlo1cnt++;
	    	 if(tlonamefound.equals(tlonames.get(1)))
	    		 tlo2cnt++;
	    	 if(tlonamefound.equals(tlonames.get(2)))
	    		 tlo3cnt++;	    	 
	    	 if(tlonamefound.equals(tlonames.get(3)))
	    		 tlo4cnt++;
	    	 if(tlonamefound.equals(tlonames.get(4)))
	    		 tlo5cnt++;
	    	 if(tlonamefound.equals(tlonames.get(5)))
	    		 tlo6cnt++;
	    	 if(tlonamefound.equals(tlonames.get(6)))
	    		 tlo7cnt++;
	     		//for loop ends
	     System.out.println(tlo1cnt+" "+tlo2cnt+" "+tlo3cnt+" "+tlo4cnt+" "+tlo5cnt+" "+tlo6cnt+" "+tlo7cnt);
	   if(tlo1cnt >= questionsFromEachTLO && tlo2cnt >= questionsFromEachTLO && tlo3cnt  >= questionsFromEachTLO && tlo4cnt >= questionsFromEachTLO && tlo5cnt >= questionsFromEachTLO && tlo6cnt >= questionsFromEachTLO && tlo7cnt >= questionsFromEachTLO)
	   done =true;
	     attemptQuestion(answerChoice,confidence,useHints,useSolutionText);
	    }  //while loop ends    	
	   }
	    catch(Exception e)
	    {
	     new Screenshot().captureScreenshotFromAppHelper();
	     Assert.fail("Exception in App helper attemptPracticeQuesFromEachTLO in class PracticeTest",e);
	    }
	   }
	   
	   public void attemptQuestion(String answeroption,int confidencelevel, boolean useHints,boolean useSolutionText)
	   {
		   WebDriver driver=Driver.getWebDriver();
	    try
	    {
	    	if(answeroption.equalsIgnoreCase("skip"))
			{				
					
				if(useHints == true)					
					driver.findElement(By.cssSelector("img[title=\"Hint\"]")).click();
					
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("img[title=\"Submit\"]")));
			    Thread.sleep(2000);
				int noticesize = driver.findElements(By.className("al-notification-message-body")).size();
				if(noticesize==1)
				
				{
					String notice =  driver.findElement(By.className("al-notification-message-body")).getText();
					if(notice.equals("You will build your proficiency faster if you say how confident you feel about the question before answering."))
					{
						driver.findElement(By.cssSelector("div[id='close-al-notification-dialog']")).click();
						((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("img[title=\"Submit\"]")));
						Thread.sleep(2000);
						
					}
					
				}
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("img[title=\"Next\"]")));
				Thread.sleep(2000);
				
			} //logic for skipping questions ends
			
			else if(answeroption.equalsIgnoreCase("correct"))
			{					
			String confidence=Integer.toString(confidencelevel);
			
			if(useHints == true)					
			driver.findElement(By.cssSelector("img[title=\"Hint\"]")).click();
				new DiagnosticTest().attemptCorrect(confidencelevel);
			
//			if(driver.findElements(By.className("multiple-selection-answer-choice-label-wrapper")).size()>0)
//			{
//				String corranswer=driver.findElement(By.id("show-debug-correct-answer-block")).getText();
//				char correctanswer;
//				  if(corranswer.charAt(17) == '(')
//					  correctanswer=corranswer.charAt(18);
//				  else
//					  correctanswer=corranswer.charAt(17);
//				String correctchoice=Character.toString(correctanswer);
//
//				String corranswer1=corranswer;
//				char correctanswer1=corranswer1.charAt(22);
//				String correctchoice1=Character.toString(correctanswer1);
//				List<WebElement> answer = driver.findElements(By.className("qtn-label"));
//				for (WebElement answerchoice: answer)
//				{
//
//					if(answerchoice.getText().trim().equals(correctchoice))
//					{
//						((JavascriptExecutor) driver).executeScript("arguments[0].click();",answerchoice);
//						break;
//					}
//				}
//				List<WebElement> answer1 = driver.findElements(By.className("qtn-label"));
//				for (WebElement answerchoice1: answer1)
//				{
//
//					if(answerchoice1.getText().trim().equals(correctchoice1))
//					{
//						((JavascriptExecutor) driver).executeScript("arguments[0].click();",answerchoice1);
//						break;
//					}
//				}
//
//			}
//
//			else if(driver.findElements(By.className("answer-choice-label-wrapper")).size() > 0)
//			{
//				String corranswer=driver.findElement(By.id("show-debug-correct-answer-block")).getText();
//				char correctanswer=corranswer.charAt(17);
//				String correctchoice=Character.toString(correctanswer);
//			List<WebElement> answer = driver.findElements(By.className("qtn-label"));
//			for (WebElement answerchoice: answer)
//			{
//
//				if(answerchoice.getText().trim().equals(correctchoice))
//				{
//					((JavascriptExecutor) driver).executeScript("arguments[0].click();",answerchoice);
//					break;
//				}
//			}
//				}
//
//
//			else if(driver.findElements(By.cssSelector("span[class='input-tag-wrapper question-element-wrapper']")).size() > 0)
//			{
//				String corranswertext=driver.findElement(By.id("show-debug-correct-answer-block")).getText();
//				int lastindex=corranswertext.length();
//				String corrcttextanswer=corranswertext.substring(16, lastindex);
//			driver.findElement(By.cssSelector("input[id='1']")).sendKeys(corrcttextanswer);
//			}
//			else if(driver.findElements(By.className("sbHolder")).size() > 0)
//			{
//				String corranswertext=driver.findElement(By.id("show-debug-correct-answer-block")).getText();
//				int lastindex=corranswertext.length();
//				String corrcttextanswer=corranswertext.substring(16, lastindex);
//				driver.findElement(By.className("sbToggle")).click();
//				Thread.sleep(2000);
//				driver.findElement(By.linkText(corrcttextanswer)).click();
//				Thread.sleep(5000);
//			}
//
//			Thread.sleep(2000);
//			if(confidencelevel!=0)
//			{
//				driver.findElement(By.cssSelector("a[id='"+confidence+"']")).click();//click on confidence level
//				Thread.sleep(2000);
//			}
//			 ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("input[title=\"Submit\"]")));
//		    Thread.sleep(2000);
//			int noticesize = driver.findElements(By.className("al-notification-message-body")).size();
//			if(noticesize==1)
//
//			{
//				String notice =  driver.findElement(By.className("al-notification-message-body")).getText();
//				if(notice.equals("You will build your proficiency faster if you say how confident you feel about the question before answering."))
//				{
//					driver.findElement(By.cssSelector("div[id='close-al-notification-dialog']")).click();
//					((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("img[title=\"Submit\"]")));
//					Thread.sleep(2000);
//				}
//			}
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("input[title=\"Next\"]")));
			Thread.sleep(2000);
		
			
			
		} //attepting correct answer ends
			else
			{
				int recommendation = driver.findElements(By.className("al-notification-message-wrapper")).size();
				if(recommendation > 0)
				{
					String notice =  driver.findElement(By.className("al-notification-message-body")).getText();
					if(notice.contains("Looks like you might want to study this objective in WileyPLUS. Go to WileyPLUS for"))
					{	
						Thread.sleep(3000);
						((JavascriptExecutor) driver).executeScript("arguments[0].click();",driver.findElement(By.cssSelector("span[id='exit-practice-test-block']")));
						//driver.findElement(By.cssSelector("span[id='exit-practice-test-block']")).click();
						
					}							
				}
					if(useHints == true)
					{
						
						driver.findElement(By.cssSelector("img[title=\"Hint\"]")).click();
					Thread.sleep(2000);
					int hintpopup=driver.findElements(By.className("al-notification-message-header")).size();
					if(hintpopup>0)
					{
						driver.findElement(By.xpath("/html/body")).click();
						Thread.sleep(2000);
					}
					}
					String confidence=Integer.toString(confidencelevel);
									
					String corranswertext=driver.findElement(By.id("show-debug-correct-answer-block")).getText();
					int lastindex=corranswertext.length();
					String corrcttextanswer=corranswertext.substring(16, lastindex);
					
					if(driver.findElements(By.className("multiple-selection-answer-choice-label-wrapper")).size()>0)
					{
						String corranswer=driver.findElement(By.id("show-debug-correct-answer-block")).getText();					
						char correctanswer;							  
						  if(corranswer.charAt(17) == '(')
							  correctanswer=corranswer.charAt(18);
						  else
							  correctanswer=corranswer.charAt(17);
						  
						 String  correctchoice=Character.toString(correctanswer); //first correct choice
						  
						  char correctanswer1=corranswer.charAt(22);		
							String correctchoice1=Character.toString(correctanswer1); //second correct choice						  
						  		  
							if((correctchoice.equals("A") && correctchoice1.equals("B")) || (correctchoice.equals("B") && correctchoice1.equals("A")) )
							{	correctchoice="C"; correctchoice1 = "D";} 
							else if(correctchoice.equals("A") && correctchoice1.equals("C") || (correctchoice.equals("C") && correctchoice1.equals("A")))
							{	correctchoice="B"; correctchoice1 = "D"; }
			 				else if(correctchoice.equals("A") && correctchoice1.equals("D") || (correctchoice.equals("D") && correctchoice1.equals("A")))
								{ correctchoice="B"; correctchoice1 = "C";}
			 				else if(correctchoice.equals("A") && correctchoice1.equals("E") || (correctchoice.equals("E") && correctchoice1.equals("A")))
							{ correctchoice="B"; correctchoice1 = "C";}
			 				else if(correctchoice.equals("A") && correctchoice1.equals("F") || (correctchoice.equals("F") && correctchoice1.equals("A")))
							{ correctchoice="B"; correctchoice1 = "C";}
							
							
			 				else if((correctchoice.equals("B") && correctchoice1.equals("C")) || (correctchoice.equals("C") && correctchoice1.equals("B")) )
							{	correctchoice="A"; correctchoice1 = "D";} 
							else if(correctchoice.equals("B") && correctchoice1.equals("D") || (correctchoice.equals("D") && correctchoice1.equals("B")))
							{	correctchoice="A"; correctchoice1 = "C"; }
			 				else if(correctchoice.equals("B") && correctchoice1.equals("E") || (correctchoice.equals("E") && correctchoice1.equals("B")))
								{ correctchoice="A"; correctchoice1 = "C";}
			 				else if(correctchoice.equals("B") && correctchoice1.equals("F") || (correctchoice.equals("F") && correctchoice1.equals("B")))
							{ correctchoice="A"; correctchoice1 = "C";}
			 				
			 				else if((correctchoice.equals("C") && correctchoice1.equals("D")) || (correctchoice.equals("D") && correctchoice1.equals("C")) )
							{	correctchoice="A"; correctchoice1 = "B";} 
							else if(correctchoice.equals("C") && correctchoice1.equals("E") || (correctchoice.equals("E") && correctchoice1.equals("C")))
							{	correctchoice="A"; correctchoice1 = "B"; }
			 				else if(correctchoice.equals("C") && correctchoice1.equals("F") || (correctchoice.equals("F") && correctchoice1.equals("C")))
								{ correctchoice="A"; correctchoice1 = "B";}
							
							
			 				else if((correctchoice.equals("D") && correctchoice1.equals("E")) || (correctchoice.equals("E") && correctchoice1.equals("D")) )
							{	correctchoice="A"; correctchoice1 = "B";} 
							else if(correctchoice.equals("D") && correctchoice1.equals("F") || (correctchoice.equals("F") && correctchoice1.equals("D")))
							{	correctchoice="A"; correctchoice1 = "B"; }
							
							else if((correctchoice.equals("E") && correctchoice1.equals("F")) || (correctchoice.equals("F") && correctchoice1.equals("E")) )
							{	correctchoice="A"; correctchoice1 = "B";}
						List<WebElement> answer = driver.findElements(By.className("qtn-label"));
						for (WebElement answerchoice: answer)
						{
							
							if(answerchoice.getText().trim().equals(correctchoice))
							{
								((JavascriptExecutor) driver).executeScript("arguments[0].click();",answerchoice);					
								break;
							}
						}
						List<WebElement> answer1 = driver.findElements(By.className("qtn-label"));
						for (WebElement answerchoice1: answer1)
						{
							
							if(answerchoice1.getText().trim().equals(correctchoice1))
							{
								((JavascriptExecutor) driver).executeScript("arguments[0].click();",answerchoice1);					
								break;
							}
						}
						
					}
					
					else if(driver.findElements(By.className("answer-choice-label-wrapper")).size() > 0)
						{
						String corranswer=driver.findElement(By.id("show-debug-correct-answer-block")).getText();					
						char correctanswer=corranswer.charAt(17);
						String correctchoice=Character.toString(correctanswer);
						if(correctchoice.equals("A"))
							correctchoice="B";
						else if(correctchoice.equals("B"))
							correctchoice="A";
						else if(correctchoice.equals("C"))
							correctchoice="D";
						else if(correctchoice.equals("D"))
							correctchoice="C";
						List<WebElement> answer = driver.findElements(By.className("qtn-label"));
						for (WebElement answerchoice: answer)
						{
							
							if(answerchoice.getText().trim().equals(correctchoice))
							{
								((JavascriptExecutor) driver).executeScript("arguments[0].click();",answerchoice);					
								break;
							}
						}
						
						}
					
					else if(driver.findElements(By.cssSelector("span[class='input-tag-wrapper question-element-wrapper']")).size() > 0)
					{
						
					driver.findElement(By.cssSelector("input[id='1']")).sendKeys(corrcttextanswer+"bh");
					}
					else if(driver.findElements(By.className("sbHolder")).size() > 0)
					{
						driver.findElement(By.className("sbToggle")).click();
						Thread.sleep(2000);
						
						String values = driver.findElement(By.cssSelector("ul[class='sbOptions']")).getText();
						//values = values.replaceAll("\n", " ");
						String [] val = values.split("\n"); 
						
						
						for(String element:val)
						{
							if(!element.equals(corrcttextanswer))
							{
								driver.findElement(By.linkText(element)).click();
								break;
							}
						}
					}
				//	((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.xpath("/html/body/div[3]/div/div[2]/div[2]/div[2]/div/div/div[2]/div[2]/div/div/label")));
				//	Thread.sleep(2000);
					Thread.sleep(2000);				
					
					if(confidencelevel!=0)
					{						
						driver.findElement(By.cssSelector("a[id='"+confidence+"']")).click();//click on confidence level
						Thread.sleep(2000);
					}
					((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("img[title=\"Submit\"]")));
				    Thread.sleep(2000);

				try {
					(new WebDriverWait(driver, 2))
                            .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("span[class='al-summary-performance-link']"))).click();
				} catch (Exception e) {
				}

				int noticesize = driver.findElements(By.className("al-notification-message-body")).size();
					if(noticesize==1)
					
					{
						String notice =  driver.findElement(By.className("al-notification-message-body")).getText();
						if(notice.equals("You will build your proficiency faster if you say how confident you feel about the question before answering."))
						{
							driver.findElement(By.cssSelector("div[id='close-al-notification-dialog']")).click();
							((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("img[title=\"Submit\"]")));
							Thread.sleep(2000);
							try {
								(new WebDriverWait(driver, 2))
										.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("span[class='al-summary-performance-link']"))).click();
							} catch (Exception e) {
							}
						}
						
					}
					((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("img[title=\"Next\"]")));
					Thread.sleep(2000);
				
			
			}
	    }
	    catch(Exception e)
	    {
	    	new Screenshot().captureScreenshotFromAppHelper();
	    	Assert.fail("Exception in App helper attemptQuestion in class PracticeTest",e);
	    }
	   }
	   
	   public void submitOnlyQuestion()
	   {
		   WebDriver driver=Driver.getWebDriver();
		   try
		   {
			   ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("img[title=\"Submit\"]")));
			    Thread.sleep(2000);
				int noticesize = driver.findElements(By.className("al-notification-message-body")).size();
				if(noticesize==1)
				
				{
					String notice =  driver.findElement(By.className("al-notification-message-body")).getText();
					if(notice.equals("You will build your proficiency faster if you say how confident you feel about the question before answering."))
					{
						driver.findElement(By.cssSelector("div[id='close-al-notification-dialog']")).click();
						((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("img[title=\"Submit\"]")));
						Thread.sleep(2000);
						
					}
					
				}
		   }
		   catch(Exception e)
		    {
		    	new Screenshot().captureScreenshotFromAppHelper();
		    	Assert.fail("Exception in App helper submitOnlyQuestion in class PracticeTest",e);
		    }
	   }
	   
	   public void practiceTestAttempt(int confidencelevel,int numberofquestionattempt,String answeroption,boolean useHints,boolean showSolutionText)
		{
			WebDriver driver=Driver.getWebDriver();
			try
			{				
				if(answeroption.equalsIgnoreCase("skip"))
				{				
				for(int i=0;i<numberofquestionattempt;i++)
					{			
					if(useHints == true)					
						driver.findElement(By.cssSelector("img[title=\"Hint\"]")).click();
						
					((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("img[title=\"Submit\"]")));
				    Thread.sleep(2000);
					int noticesize = driver.findElements(By.className("al-notification-message-body")).size();
					if(noticesize==1)
					
					{
						String notice =  driver.findElement(By.className("al-notification-message-body")).getText();
						if(notice.equals("You will build your proficiency faster if you say how confident you feel about the question before answering."))
						{
							driver.findElement(By.cssSelector("div[id='close-al-notification-dialog']")).click();
							((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("img[title=\"Submit\"]")));
							Thread.sleep(2000);
							
						}
						
					}
					((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("img[title=\"Next\"]")));
					Thread.sleep(2000);
					}
				} //logic for skipping questions ends
				
				else if(answeroption.equalsIgnoreCase("correct"))
				{				
				for(int i=0;i<numberofquestionattempt;i++)
				{
				String confidence=Integer.toString(confidencelevel);
				
				if(useHints == true)					
				driver.findElement(By.cssSelector("img[title=\"Hint\"]")).click();
				
				if(driver.findElements(By.className("multiple-selection-answer-choice-label-wrapper")).size()>0)
				{
					String corranswer=driver.findElement(By.id("show-debug-correct-answer-block")).getText();			
					char correctanswer;							  
					  if(corranswer.charAt(17) == '(')
						  correctanswer=corranswer.charAt(18);
					  else
						  correctanswer=corranswer.charAt(17);
					String correctchoice=Character.toString(correctanswer);
					
					String corranswer1=corranswer;
					char correctanswer1=corranswer1.charAt(22);
					String correctchoice1=Character.toString(correctanswer1);
					List<WebElement> answer = driver.findElements(By.className("qtn-label"));
					for (WebElement answerchoice: answer)
					{
						
						if(answerchoice.getText().trim().equals(correctchoice))
						{
							((JavascriptExecutor) driver).executeScript("arguments[0].click();",answerchoice);					
							break;
						}
					}
					List<WebElement> answer1 = driver.findElements(By.className("qtn-label"));
					for (WebElement answerchoice1: answer1)
					{
						
						if(answerchoice1.getText().trim().equals(correctchoice1))
						{
							((JavascriptExecutor) driver).executeScript("arguments[0].click();",answerchoice1);					
							break;
						}
					}
					
				}
				
				else if(driver.findElements(By.className("answer-choice-label-wrapper")).size() > 0)
				{
					String corranswer=driver.findElement(By.id("show-debug-correct-answer-block")).getText();			
					char correctanswer=corranswer.charAt(17);
					String correctchoice=Character.toString(correctanswer);	
				List<WebElement> answer = driver.findElements(By.className("qtn-label"));
				for (WebElement answerchoice: answer)
				{
					
					if(answerchoice.getText().trim().equals(correctchoice))
					{
						((JavascriptExecutor) driver).executeScript("arguments[0].click();",answerchoice);					
						break;
					}
				}				
					}
				
				
				else if(driver.findElements(By.cssSelector("span[class='input-tag-wrapper question-element-wrapper']")).size() > 0)
				{
					String corranswertext=driver.findElement(By.id("show-debug-correct-answer-block")).getText();
					int lastindex=corranswertext.length();
					String corrcttextanswer=corranswertext.substring(16, lastindex);
				driver.findElement(By.cssSelector("input[id='1']")).sendKeys(corrcttextanswer);
				}
				else if(driver.findElements(By.className("sbHolder")).size() > 0)
				{
					String corranswertext=driver.findElement(By.id("show-debug-correct-answer-block")).getText();
					int lastindex=corranswertext.length();
					String corrcttextanswer=corranswertext.substring(16, lastindex);
					driver.findElement(By.className("sbToggle")).click();
					Thread.sleep(2000);
					driver.findElement(By.linkText(corrcttextanswer)).click();
					Thread.sleep(5000);
				}
			
				Thread.sleep(2000);
				if(confidencelevel!=0)
				{
					driver.findElement(By.cssSelector("a[id='"+confidence+"']")).click();//click on confidence level
					Thread.sleep(2000);
				}
				 ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("img[title=\"Submit\"]")));
			    Thread.sleep(2000);
				int noticesize = driver.findElements(By.className("al-notification-message-body")).size();
				if(noticesize==1)
				
				{
					String notice =  driver.findElement(By.className("al-notification-message-body")).getText();
					if(notice.equals("You will build your proficiency faster if you say how confident you feel about the question before answering."))
					{
						driver.findElement(By.cssSelector("div[id='close-al-notification-dialog']")).click();
						((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("img[title=\"Submit\"]")));
						Thread.sleep(2000);						
					}
					}
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("img[title=\"Next\"]")));
				Thread.sleep(2000);
			
				
				}
			} //attepting correct answer ends
				else
				{
					for(int j=0;j<numberofquestionattempt;j++)
					{
						if(useHints == true)					
							driver.findElement(By.cssSelector("img[title=\"Hint\"]")).click();
						Thread.sleep(2000);
						int hintpopup=driver.findElements(By.className("al-notification-message-header")).size();
						if(hintpopup>0)
						{
							driver.findElement(By.xpath("/html/body")).click();
							Thread.sleep(2000);
						}
						String confidence=Integer.toString(confidencelevel);
										
						String corranswertext=driver.findElement(By.id("show-debug-correct-answer-block")).getText();
						int lastindex=corranswertext.length();
						String corrcttextanswer=corranswertext.substring(16, lastindex);
						
						if(driver.findElements(By.className("multiple-selection-answer-choice-label-wrapper")).size()>0)
						{
							String corranswer=driver.findElement(By.id("show-debug-correct-answer-block")).getText();					
							char correctanswer;							  
							  if(corranswer.charAt(17) == '(')
								  correctanswer=corranswer.charAt(18);
							  else
								  correctanswer=corranswer.charAt(17);
							  
							 String  correctchoice=Character.toString(correctanswer); //first correct choice
							  
							  char correctanswer1=corranswer.charAt(22);		
								String correctchoice1=Character.toString(correctanswer1); //second correct choice
							  System.out.println("Correct answer 1 in attemptincorrect in muliple chocce "+correctchoice);
							  System.out.println("Correct answer 2 in attemptincorrect in muliple chocce "+correctchoice1);
							  
							  		  
								if((correctchoice.equals("A") && correctchoice1.equals("B")) || (correctchoice.equals("B") && correctchoice1.equals("A")) )
								{	correctchoice="C"; correctchoice1 = "D";} 
								else if(correctchoice.equals("A") && correctchoice1.equals("C") || (correctchoice.equals("C") && correctchoice1.equals("A")))
								{	correctchoice="B"; correctchoice1 = "D"; }
				 				else if(correctchoice.equals("A") && correctchoice1.equals("D") || (correctchoice.equals("D") && correctchoice1.equals("A")))
									{ correctchoice="B"; correctchoice1 = "C";}
				 				else if(correctchoice.equals("A") && correctchoice1.equals("E") || (correctchoice.equals("E") && correctchoice1.equals("A")))
								{ correctchoice="B"; correctchoice1 = "C";}
				 				else if(correctchoice.equals("A") && correctchoice1.equals("F") || (correctchoice.equals("F") && correctchoice1.equals("A")))
								{ correctchoice="B"; correctchoice1 = "C";}
								
								
				 				else if((correctchoice.equals("B") && correctchoice1.equals("C")) || (correctchoice.equals("C") && correctchoice1.equals("B")) )
								{	correctchoice="A"; correctchoice1 = "D";} 
								else if(correctchoice.equals("B") && correctchoice1.equals("D") || (correctchoice.equals("D") && correctchoice1.equals("B")))
								{	correctchoice="A"; correctchoice1 = "C"; }
				 				else if(correctchoice.equals("B") && correctchoice1.equals("E") || (correctchoice.equals("E") && correctchoice1.equals("B")))
									{ correctchoice="A"; correctchoice1 = "C";}
				 				else if(correctchoice.equals("B") && correctchoice1.equals("F") || (correctchoice.equals("F") && correctchoice1.equals("B")))
								{ correctchoice="A"; correctchoice1 = "C";}
				 				
				 				else if((correctchoice.equals("C") && correctchoice1.equals("D")) || (correctchoice.equals("D") && correctchoice1.equals("C")) )
								{	correctchoice="A"; correctchoice1 = "B";} 
								else if(correctchoice.equals("C") && correctchoice1.equals("E") || (correctchoice.equals("E") && correctchoice1.equals("C")))
								{	correctchoice="A"; correctchoice1 = "B"; }
				 				else if(correctchoice.equals("C") && correctchoice1.equals("F") || (correctchoice.equals("F") && correctchoice1.equals("C")))
									{ correctchoice="A"; correctchoice1 = "B";}
								
								
				 				else if((correctchoice.equals("D") && correctchoice1.equals("E")) || (correctchoice.equals("E") && correctchoice1.equals("D")) )
								{	correctchoice="A"; correctchoice1 = "B";} 
								else if(correctchoice.equals("D") && correctchoice1.equals("F") || (correctchoice.equals("F") && correctchoice1.equals("D")))
								{	correctchoice="A"; correctchoice1 = "B"; }
								
								else if((correctchoice.equals("E") && correctchoice1.equals("F")) || (correctchoice.equals("F") && correctchoice1.equals("E")) )
								{	correctchoice="A"; correctchoice1 = "B";}
							List<WebElement> answer = driver.findElements(By.className("qtn-label"));
							for (WebElement answerchoice: answer)
							{
								
								if(answerchoice.getText().trim().equals(correctchoice))
								{
									((JavascriptExecutor) driver).executeScript("arguments[0].click();",answerchoice);					
									break;
								}
							}
							List<WebElement> answer1 = driver.findElements(By.className("qtn-label"));
							for (WebElement answerchoice1: answer1)
							{
								
								if(answerchoice1.getText().trim().equals(correctchoice1))
								{
									((JavascriptExecutor) driver).executeScript("arguments[0].click();",answerchoice1);					
									break;
								}
							}
							
						}
						
						else if(driver.findElements(By.className("answer-choice-label-wrapper")).size() > 0)
							{
							String corranswer=driver.findElement(By.id("show-debug-correct-answer-block")).getText();					
							char correctanswer=corranswer.charAt(17);
							String correctchoice=Character.toString(correctanswer);
							if(correctchoice.equals("A"))
								correctchoice="B";
							else if(correctchoice.equals("B"))
								correctchoice="A";
							else if(correctchoice.equals("C"))
								correctchoice="D";
							else if(correctchoice.equals("D"))
								correctchoice="C";
							List<WebElement> answer = driver.findElements(By.className("qtn-label"));
							for (WebElement answerchoice: answer)
							{
								
								if(answerchoice.getText().trim().equals(correctchoice))
								{
									((JavascriptExecutor) driver).executeScript("arguments[0].click();",answerchoice);					
									break;
								}
							}
							
							}
						
						else if(driver.findElements(By.cssSelector("span[class='input-tag-wrapper question-element-wrapper']")).size() > 0)
						{
							
						driver.findElement(By.cssSelector("input[id='1']")).sendKeys(corrcttextanswer+"bh");
						}
						else if(driver.findElements(By.className("sbHolder")).size() > 0)
						{
							driver.findElement(By.className("sbToggle")).click();
							Thread.sleep(2000);
							
							String values = driver.findElement(By.cssSelector("ul[class='sbOptions']")).getText();
							//values = values.replaceAll("\n", " ");
							String [] val = values.split("\n"); 
							
							
							for(String element:val)
							{
								if(!element.equals(corrcttextanswer))
								{
									driver.findElement(By.linkText(element)).click();
									break;
								}
							}
						}
					//	((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.xpath("/html/body/div[3]/div/div[2]/div[2]/div[2]/div/div/div[2]/div[2]/div/div/label")));
					//	Thread.sleep(2000);
						Thread.sleep(2000);
						if(confidencelevel!=0)
						{
							driver.findElement(By.cssSelector("a[id='"+confidence+"']")).click();//click on confidence level
							Thread.sleep(2000);
						}
						((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("img[title=\"Submit\"]")));
					    Thread.sleep(2000);
						int noticesize = driver.findElements(By.className("al-notification-message-body")).size();
						if(noticesize==1)
						
						{
							String notice =  driver.findElement(By.className("al-notification-message-body")).getText();
							if(notice.equals("You will build your proficiency faster if you say how confident you feel about the question before answering."))
							{
								driver.findElement(By.cssSelector("div[id='close-al-notification-dialog']")).click();
								((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("img[title=\"Submit\"]")));
								Thread.sleep(2000);
								
							}
							
						}
						((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("img[title=\"Next\"]")));
						Thread.sleep(2000);
					
				}
				}
				driver.findElement(By.className("al-quit-diag-test-icon")).click();
				Thread.sleep(1000);
				driver.findElement(By.className("al-diag-test-continue-later")).click();
				Thread.sleep(2000);
			}
			catch(Exception e)
			{
				
				Assert.fail("Exception in App helper method  AdaptiveTestInBetween in class practicetest",e);
			}
		}

    //@author Sumit
    //open a TLO level practice test based on Index(just passed the index of TLO needs to be opened
    public void openTLOLevelPracticeTestBasedOnIndex(int tloIndex)
    {
		WebDriver driver=Driver.getWebDriver();
        try
        {
            List<WebElement> tloList = driver.findElements(By.className("al-terminal-objective-title"));
            Thread.sleep(1000);
            new Actions(driver).moveToElement(tloList.get(tloIndex)).build().perform();
            List<WebElement> allPractice = driver.findElements(By.cssSelector("div[title='Practice']"));
            for(WebElement practice : allPractice)
            {
                if(practice.isDisplayed() == true)
                {
                    practice.click();
                    Thread.sleep(2000);
                    break;
                }
            }
        }
        catch (Exception e)
        {
            Assert.fail("Exception in App helper method  openTLOLevelPracticeTestBasedOnIndex in class PracticeTest",e);
        }
    }

	public void attemptPracticeTestRandomly(int noOfQsToAttempt)
	{
		WebDriver driver=Driver.getWebDriver();
		try
		{
			int practiceQuit = driver.findElements(By.className("al-quit-diag-tag")).size();
			if (practiceQuit > 0)
			{
				for(int i = 0; i < noOfQsToAttempt; i++ )
				{
					List<WebElement> multipleChoice = driver.findElements(By.className("qtn-label"));
					if(multipleChoice.size() > 2)
					{
						int randNumber = new RandomNumber().generateRandomNumber(0,3);
						multipleChoice.get(randNumber).click();
					}
					else if(multipleChoice.size() > 0 && multipleChoice.size() <= 2)
					{
						int randNumber = new RandomNumber().generateRandomNumber(0,1);
						multipleChoice.get(randNumber).click();
					}
					int submit = driver.findElements(By.className("al-practice-test-submit-button")).size();
					while (submit > 0)
					{
						new Click().clickByclassname("al-practice-test-submit-button");
						submit = driver.findElements(By.className("al-practice-test-submit-button")).size();
					}
					int next = driver.findElements(By.id("al-next-practice-question-button")).size();
					while (next > 0)
					{
						new Click().clickByid("al-next-practice-question-button");
						next = driver.findElements(By.id("al-next-practice-question-button")).size();
					}
				}
			}
		}
		catch (Exception e)
		{
			Assert.fail("Exception in App helper method  attemptPracticeTestRandomly",e);
		}
	}
	
	}




