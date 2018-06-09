package com.snapwiz.selenium.tests.staf.learningspaces.testcases.anonymous;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.TabClose;
import org.junit.Assert;
import org.testng.annotations.Test;

import java.util.logging.Level;
import java.util.logging.Logger;

public class VerifyAllQuestionTypesForAdaptive extends Driver
{
	private static Logger logger=Logger.getLogger("com.snapwiz.selenium.tests.staf.learningspaces.testcases.anonymous.VerifyAllQuestionTypesForAdaptive");
	@Test
	public void verifyAllQuestionTypesForAdaptive()
    {
        try
        {
            new LoginUsingLTI().ltiLogin("1177");
            String adaptivetest = ReadTestData.readDataByTagName("tocdata", "adaptivetest", "1");
            new Navigator().NavigateTo("eTextbook");
            new DiagnosticTest().startTest(2);
			new AttemptTest().Diagonestictest();
			new TabClose().tabClose(1);
			Thread.sleep(5000);
			new TOCShow().tocShow();
			new TopicOpen().topicOpen(adaptivetest);
            if(new VerifyAllQuestionTypes().firstQuestionShouldGetDeliveredForAdaptive())
            {
                logger.log(Level.INFO,"First question get delivered");
            }
            else
            {
                logger.log(Level.INFO,"First question doesnt get not delivered");
                Assert.fail("First question doesnt get not delivered");
            }
           
            if(new VerifyAllQuestionTypes().verifyTrueFalseTypeQuestionForAdaptive())
            {
                logger.log(Level.SEVERE,"True/False question found");
            }
            else
            {
              Assert.fail("True/False question type not found");
               
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
            logger.log(Level.SEVERE,"Exception in VerifyAllQuestionTypesForDiagnostic",e);
            Assert.fail("Exception in TestCase verifyAllQuestionTypesForDiagnostic in class VerifyAllQuestionTypesForDiagnostic");
        }
   
    }
	
	}


