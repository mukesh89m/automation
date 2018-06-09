package com.snapwiz.selenium.tests.staf.learningspaces.testcases.anonymous;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.DiagnosticTest;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.VerifyAllQuestionTypes;
import org.junit.Assert;
import org.testng.annotations.Test;

import java.util.logging.Level;
import java.util.logging.Logger;

public class VerifyAllQuestionTypesForDiagnostic extends Driver
{
	private static Logger logger=Logger.getLogger("com.snapwiz.selenium.tests.staf.learningspaces.testcases.VerfyAllQuestionTypesForDiagnostic");
	@Test
	public void verifyAllQuestionTypesForDiagnostic()
    {
        try
        {
            new LoginUsingLTI().ltiLogin("1122");
            new Navigator().NavigateTo("eTextbook");
            new DiagnosticTest().startTest(2);
           
            if(new VerifyAllQuestionTypes().startDiagTestFirstQuestionShouldGetDelivered())
            {
                logger.log(Level.INFO,"First question get delivered");
            }
            else
            {
                logger.log(Level.INFO,"First question get not delivered");
                Assert.fail("First question get not delivered");
            }
            if(new VerifyAllQuestionTypes().verifyTrueFalseTypeQuestion())
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
