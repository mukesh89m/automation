package com.snapwiz.selenium.tests.staf.orion.apphelper;




import com.snapwiz.selenium.tests.staf.orion.pagefactory.CMS.Preview;
import com.snapwiz.selenium.tests.staf.orion.uihelper.Click;
import com.snapwiz.selenium.tests.staf.orion.uihelper.UIElement;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.snapwiz.selenium.tests.staf.orion.Driver;

import java.util.List;

public class AttemptTest 
{

	//Attempt Daigonestics Test
	public void Diagonestictest()
	{
		
		int testend=Driver.driver.findElements(By.className("al-diag-test-timer")).size();
		
		while(testend==1)
		{
			try
			{
			//Thread.sleep(3000);
			testend=Driver.driver.findElements(By.className("al-diag-test-timer")).size();
			if(testend == 0)
			break;
			//Driver.driver.findElements(By.className("al-diag-test-timer"));		
			new SelectAnswerAndSubmit().daigonestianswersubmit("A");	
			}
			catch(Exception e)
			{
				Assert.fail("Question or element not found",e);
			}
			
					
		}
		
	}
	//Attempt adaptive test
	public void AdaptiveTest(int numberofquestion)
	{
		for(int i=0;i<=numberofquestion;i++)
		{
			new SelectAnswerAndSubmit().Adaptiveasnswersubmit("B");
		}
		
	}
	public void StaticTest()
	{

		int testend=Driver.driver.findElements(By.className("al-diag-test-timer")).size();
		
		while(testend==1)
		{
			try
			{
			Thread.sleep(3000);
			testend=Driver.driver.findElements(By.className("al-diag-test-timer")).size();
			if(testend == 0)
			break;
			Driver.driver.findElements(By.className("al-diag-test-timer"));		
			new SelectAnswerAndSubmit().staticanswersubmit("A");	
			}
			catch(Exception e)
			{
				Assert.fail("Question or element not found",e);
			}
			
					
		}
	}




	public void attemptTrueFalse(String confidenceLevel)
	{
		try
		{
			System.out.println("Attempting Truer false type");
			String correctChoice = "A";
			List<WebElement> answer = Driver.driver.findElements(By.className("qtn-label"));
			for (WebElement answerChoice : answer) {
				if (answerChoice.getText().trim().contains(correctChoice)) {
					((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", answerChoice);
					System.out.println("Attempted");
					break;
				}
			}
			String classAttribute=null;
			int confidenselevelIndex=Integer.parseInt(confidenceLevel);
			if(confidenceLevel != null && confidenselevelIndex>=1 && confidenselevelIndex<=4 )
			{
				if(confidenceLevel.equals("1"))
					classAttribute="confidence-level-guess";
				if(confidenceLevel.equals("2"))
					classAttribute="confidence-level-somewhat";
				if(confidenceLevel.equals("3"))
					classAttribute="confidence-level-almost";
				if(confidenceLevel.equals("4"))
					classAttribute="confidence-level-i-know-it";

				((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.xpath("//a[@id='"+confidenceLevel+"' and @class='"+classAttribute+"']")));
			}

		}
		catch (Exception e)
		{
			Assert.fail("Exception in apphelper attemptTrueFalse on class AttemptDiagnosticQuestion.", e);
		}
	}

	public void attemptMultipleChoice(String confidenceLevel)
	{
		try
		{
			Thread.sleep(3000);
			System.out.println("Attempting Multiple choice type");
			String correctChoice = "A";
			List<WebElement> answer = Driver.driver.findElements(By.className("qtn-label"));
			for (WebElement answerChoice : answer) {
				if (answerChoice.getText().trim().contains(correctChoice)) {
					answerChoice.click();
					//((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", answerChoice);
					System.out.println("Attempted");
					break;
				}
			}
			String classAttribute=null;
			int confidenselevelIndex=Integer.parseInt(confidenceLevel);
			if(confidenceLevel != null && confidenselevelIndex>=1 && confidenselevelIndex<=4 )
			{
				if(confidenceLevel.equals("1"))
					classAttribute="confidence-level-guess";
				if(confidenceLevel.equals("2"))
					classAttribute="confidence-level-somewhat";
				if(confidenceLevel.equals("3"))
					classAttribute="confidence-level-almost";
				if(confidenceLevel.equals("4"))
					classAttribute="confidence-level-i-know-it";


				((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.xpath("//a[@id='"+confidenceLevel+"' and @class='"+classAttribute+"']")));
			}
		}
		catch (Exception e)
		{
			Assert.fail("Exception in apphelper attemptMultipleChoice on class AttemptDiagnosticQuestion.", e);
		}
	}

	public void attemptMultipleSelection(String confidenceLevel)
	{
		try{
			Thread.sleep(3000);
			System.out.println("Attempting Multiple selection");
			String correctChoice = "A";
			List<WebElement> answer = Driver.driver.findElements(By.className("qtn-label"));
			for (WebElement answerChoice : answer) {
				if (answerChoice.getText().trim().contains(correctChoice)) {
					answerChoice.click();
					//((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", answerChoice);
					System.out.println("Attempted");
					break;
				}
			}
			String correctchoice = "B";
			List<WebElement> answer1 = Driver.driver.findElements(By.className("qtn-label"));
			for (WebElement answerChoice : answer1) {
				if (answerChoice.getText().trim().contains(correctchoice)) {
					answerChoice.click();
					//((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", answerChoice);
					System.out.println("Attempted");
					break;
				}
			}

			String classAttribute=null;
			int confidenselevelIndex=Integer.parseInt(confidenceLevel);
			if(confidenceLevel != null && confidenselevelIndex>=1 && confidenselevelIndex<=4 )
			{
				if(confidenceLevel.equals("1"))
					classAttribute="confidence-level-guess";
				if(confidenceLevel.equals("2"))
					classAttribute="confidence-level-somewhat";
				if(confidenceLevel.equals("3"))
					classAttribute="confidence-level-almost";
				if(confidenceLevel.equals("4"))
					classAttribute="confidence-level-i-know-it";


				((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.xpath("//a[@id='"+confidenceLevel+"' and @class='"+classAttribute+"']")));
			}
		}
		catch (Exception e)
		{
			Assert.fail("Exception in apphelper attemptMultipleSelection on class AttemptDiagnosticQuestion.", e);
		}
	}

	public void attemptTextEntry(String confidenceLevel)
	{
		try {
            Thread.sleep(3000);
			System.out.println("Attempting text entry");
			String corrcttextanswer = "TextEntryQuestion";
			Driver.driver.findElement(By.cssSelector("span[class='input-tag-wrapper question-element-wrapper']>input")).sendKeys(corrcttextanswer);
			String classAttribute=null;
			int confidenselevelIndex=Integer.parseInt(confidenceLevel);
			if(confidenceLevel != null && confidenselevelIndex>=1 && confidenselevelIndex<=4 )
			{
				if(confidenceLevel.equals("1"))
					classAttribute="confidence-level-guess";
				if(confidenceLevel.equals("2"))
					classAttribute="confidence-level-somewhat";
				if(confidenceLevel.equals("3"))
					classAttribute="confidence-level-almost";
				if(confidenceLevel.equals("4"))
					classAttribute="confidence-level-i-know-it";


				((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.xpath("//a[@id='"+confidenceLevel+"' and @class='"+classAttribute+"']")));
			}
		}
		catch (Exception e)
		{
			Assert.fail("Exception in apphelper attemptTextEntry on class AttemptDiagnosticQuestion.", e);
		}
	}
	public void attemptTextDropDown(String confidenceLevel)
	{
		try
		{
			Thread.sleep(3000);
			System.out.println("Attempting text dropdown");
			String corrcttextanswer = "ghi";
			new Select(Driver.driver.findElement(By.className("question-raw-content-dropdown"))).selectByIndex(1);
			String classAttribute=null;
			int confidenselevelIndex=Integer.parseInt(confidenceLevel);
			if(confidenceLevel != null && confidenselevelIndex>=1 && confidenselevelIndex<=4 )
			{
				if(confidenceLevel.equals("1"))
					classAttribute="confidence-level-guess";
				if(confidenceLevel.equals("2"))
					classAttribute="confidence-level-somewhat";
				if(confidenceLevel.equals("3"))
					classAttribute="confidence-level-almost";
				if(confidenceLevel.equals("4"))
					classAttribute="confidence-level-i-know-it";


				((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.xpath("//a[@id='"+confidenceLevel+"' and @class='"+classAttribute+"']")));
			}
		}
		catch (Exception e)
		{
			Assert.fail("Exception in apphelper attemptTextDropDown on class AttemptDiagnosticQuestion.", e);
		}
	}

	public void attemptNumericEntryWithUnits(String confidenceLevel)
	{
		try
		{
			Thread.sleep(3000);
			Preview preview = PageFactory.initElements(Driver.driver, Preview.class);
			String corrcttextanswer = "10";
			String correctUnit = "feet";
			System.out.println("Attempting numeric entry with units");
			preview.textEntry_textBox.sendKeys(corrcttextanswer);
			preview.selectUnits_dropdown.click();
			preview.selectUnits.get(1).click();//select feet
			String classAttribute=null;
			int confidenselevelIndex=Integer.parseInt(confidenceLevel);
			if(confidenceLevel != null && confidenselevelIndex>=1 && confidenselevelIndex<=4 )
			{
				if(confidenceLevel.equals("1"))
					classAttribute="confidence-level-guess";
				if(confidenceLevel.equals("2"))
					classAttribute="confidence-level-somewhat";
				if(confidenceLevel.equals("3"))
					classAttribute="confidence-level-almost";
				if(confidenceLevel.equals("4"))
					classAttribute="confidence-level-i-know-it";


				((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.xpath("//a[@id='"+confidenceLevel+"' and @class='"+classAttribute+"']")));
			}
		}
		catch (Exception e)
		{
			Assert.fail("Exception in apphelper attemptNumericEntryWithUnits on class AttemptDiagnosticQuestion.", e);
		}
	}

	public void attemptAdvancedNumeric(String confidenceLevel) {
		try {
			Thread.sleep(3000);
			System.out.println("Attempting Advanced Numeric");
			String corrcttextanswer = "advancednumeric";
			Driver.driver.findElement(By.cssSelector("span[class='input-tag-wrapper question-element-wrapper cde']>input")).sendKeys(corrcttextanswer);
			String classAttribute=null;
			int confidenselevelIndex=Integer.parseInt(confidenceLevel);
			if(confidenceLevel != null && confidenselevelIndex>=1 && confidenselevelIndex<=4 )
			{
				if(confidenceLevel.equals("1"))
					classAttribute="confidence-level-guess";
				if(confidenceLevel.equals("2"))
					classAttribute="confidence-level-somewhat";
				if(confidenceLevel.equals("3"))
					classAttribute="confidence-level-almost";
				if(confidenceLevel.equals("4"))
					classAttribute="confidence-level-i-know-it";


				((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.xpath("//a[@id='"+confidenceLevel+"' and @class='"+classAttribute+"']")));
			}
		} catch (Exception e) {
			Assert.fail("Exception in apphelper attemptAdvancedNumeric on class AttemptDiagnosticQuestion.", e);
		}
	}

	public void attemptExpressionEvaluator(String confidenceLevel) {
		try {

			Thread.sleep(3000);
			System.out.println("Attempting Expression Evaluator");
			String corrcttextanswer = "5";
			new Click().clickBycssselector("div[class='display-correct-answer-math-editor btn sty-green']");
			Driver.driver.findElement(By.cssSelector("button[title='Square root']")).click();
			Driver.driver.findElement(By.cssSelector("input[class='wrs_focusElement']")).sendKeys(corrcttextanswer);
			Driver.driver.findElement(By.id("wiris-container-save-formaulaeditor")).click();
			String classAttribute=null;
			int confidenselevelIndex=Integer.parseInt(confidenceLevel);
			if(confidenceLevel != null && confidenselevelIndex>=1 && confidenselevelIndex<=4 )
			{
				if(confidenceLevel.equals("1"))
					classAttribute="confidence-level-guess";
				if(confidenceLevel.equals("2"))
					classAttribute="confidence-level-somewhat";
				if(confidenceLevel.equals("3"))
					classAttribute="confidence-level-almost";
				if(confidenceLevel.equals("4"))
					classAttribute="confidence-level-i-know-it";


				((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.xpath("//a[@id='"+confidenceLevel+"' and @class='"+classAttribute+"']")));
			}

		} catch (Exception e) {
			Assert.fail("Exception in apphelper attemptExpressionEvaluator on class AttemptDiagnosticQuestion.", e);
		}
	}
	public void attemptEssayType(String confidenceLevel) {
		try {

			String corrcttextanswer = "5";
			System.out.println("Attempting Essay type");
			Driver.driver.findElement(By.id("html-editor-non-draggable")).click();
			Driver.driver.findElement(By.id("html-editor-non-draggable")).sendKeys(corrcttextanswer);
			String classAttribute=null;
			int confidenselevelIndex=Integer.parseInt(confidenceLevel);
			if(confidenceLevel != null && confidenselevelIndex>=1 && confidenselevelIndex<=4 )
			{
				if(confidenceLevel.equals("1"))
					classAttribute="confidence-level-guess";
				if(confidenceLevel.equals("2"))
					classAttribute="confidence-level-somewhat";
				if(confidenceLevel.equals("3"))
					classAttribute="confidence-level-almost";
				if(confidenceLevel.equals("4"))
					classAttribute="confidence-level-i-know-it";


				((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.xpath("//a[@id='"+confidenceLevel+"' and @class='"+classAttribute+"']")));
			}

		} catch (Exception e) {
			Assert.fail("Exception in apphelper attemptExpressionEvaluator on class AttemptDiagnosticQuestion.", e);
		}
	}

	public void attemptCorrect(int noOfQuestions, String testType,int userNo)
	{
		try {
			int timer=1;
			int questionCount = 0;
			while(timer!=0) {
				boolean attempted = false;

				Thread.sleep(1000);
				String encquestionId = Driver.driver.findElement(By.id("questionId")).getAttribute("value"); // encrypted qId
				new TDESEncryptionUtils(436792765l);
				Long questionId = TDESEncryptionUtils.decryptLong(encquestionId); // decrypted qId
				String qTypeQuery = "select question_type from t_questions where id = '" + questionId + "';";
				String questionType = new DbConnector().databaseConnectWithParam(qTypeQuery);
				if (Driver.driver.findElements(By.className("qtn-label")).size() > 0) {
					List<WebElement> ansChoiceText = Driver.driver.findElements(By.className("answer-choice-content"));
					String query = "SELECT tac.raw_content_data FROM t_answer_choices tac, t_correct_answer tca where (tac.id = tca.choice1_id or tac.id = tca.choice2_id or tac.id = tca.choice3_id or tac.id = tca.choice4_id or tac.id = tca.choice5_id or tac.id = tca.choice6_id) and question_id = '" + questionId + "'";
					List<String> correctAnswer = new DbConnector().databaseConnectReturnMultipleValue(query);
					for (String ele : correctAnswer) {
						if (ele.contains("&nbsp;")) {
							ele.replaceAll("&nbsp;", " ");
						}
					}
					int found = 0;
					for (WebElement ele : ansChoiceText) {
						if (correctAnswer.contains(ele.getText().trim())) {
							Driver.driver.findElements(By.className("qtn-label")).get(found).click();
						}
						found++;
					}

				} else if (questionType.equals("3")) {
					String query = "select substring_index(substring_index(text_answer1,'\"',-2),'\"',1) from t_questions tq, t_correct_answer tca where tq.correct_answer_id = tca.id and tq.id = '" + questionId + "';";
					String textEntryCorrectAnswer = new DbConnector().databaseConnect(query);
					Driver.driver.findElement(By.cssSelector("input[type='textbox']")).sendKeys(textEntryCorrectAnswer);
				} else if (questionType.equals("4")) {
					String query = "select text_answer1 from t_questions tq, t_correct_answer tca where tq.correct_answer_id = tca.id and tq.id = '" + questionId + "';";
					String dropDownCorrectAnswer = new DbConnector().databaseConnect(query);
					Driver.driver.findElement(By.cssSelector("a[class='sbSelector']")).click(); //click on the toggle
					List<WebElement> dropDownValues = Driver.driver.findElements(By.xpath("//ul[@class='sbOptions']/li/a"));
					for (int i = 1; i < dropDownValues.size() - 1; i++) {
						if (dropDownCorrectAnswer.contains(dropDownValues.get(i).getText().trim())) {
							Thread.sleep(4000);
							dropDownValues.get(i).click();
							break;
						}
					}
				} else if (Driver.driver.findElements(By.className("al-diag-test-submit-button")).size() > 0) {
				}
				try {
					if (userNo % 2 == 0) {
						Driver.driver.findElement(By.cssSelector("a[id='3']")).click();
					} else {
						Driver.driver.findElement(By.cssSelector("a[id='4']")).click();
					}
				} catch (Exception e) {

				}
				if (testType.equals("Diagnostic")) {
					Thread.sleep(3000);
					try {
						(new WebDriverWait(Driver.driver, 2))
								.until(ExpectedConditions.presenceOfElementLocated(By.className("al-diag-test-submit-button"))).click();
					} catch (Exception e) {

					}
				} else {
					((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("input[title='Submit']")));
					try {
						Driver.driver.findElement(By.className("al-notification-message-body"));

						String notice = Driver.driver.findElement(By.className("al-notification-message-body")).getText();
						if (notice.equals("You will build your proficiency faster if you say how confident you feel about the question before answering.")) {
							Driver.driver.findElement(By.cssSelector("div[id='close-al-notification-dialog']")).click();
							Driver.driver.findElement(By.className("al-practice-test-submit-button")).click();
							Thread.sleep(1000);
						}


					} catch (Exception e) {

					}
					Thread.sleep(500);
					try {
						((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("input[title='Next']")));
					} catch (Exception e) {

					}

				}
				Thread.sleep(1000);
				questionCount++;
				if (noOfQuestions == 0) {
					try {
						Driver.driver.findElement(By.cssSelector("span[id='timer-label']"));
					} catch (Exception e) {
						timer = 0;
					}
				} else {
					if (noOfQuestions == questionCount) {
						timer = 0;
					}
				}
			}
		}
		catch (Exception e) {
			Assert.fail("Exception while attempting the diagnostic question as correct",e);
		}
	}
}
