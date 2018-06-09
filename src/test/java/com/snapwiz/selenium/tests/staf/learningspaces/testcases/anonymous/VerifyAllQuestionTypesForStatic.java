package com.snapwiz.selenium.tests.staf.learningspaces.testcases.anonymous;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.TopicOpen;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.VerifyAllQuestionTypes;
import org.junit.Assert;
import org.testng.annotations.Test;

import java.util.logging.Level;
import java.util.logging.Logger;

public class VerifyAllQuestionTypesForStatic extends Driver{

	private static Logger logger=Logger.getLogger("com.snapwiz.selenium.tests.staf.learningspaces.testcases.anonymous.VerifyAllQuestionTypesForStatic");

	@Test
	public void verifyAllQuestionTypesForStatic()
    {
        try
        {
            new LoginUsingLTI().ltiLogin("1236");
            String statictest = ReadTestData.readDataByTagName("tocdata", "statictest", "1");
            new Navigator().NavigateTo("eTextbook");
			new TopicOpen().topicOpen(statictest);
		
            if(new VerifyAllQuestionTypes().startDiagTestFirstQuestionShouldGetDelivered())
            {
                logger.log(Level.INFO,"First question get delivered");
            }
            else
            {
                logger.log(Level.INFO,"First question get not delivered");
                Assert.fail("First question get not delivered");
            }
            if(new VerifyAllQuestionTypes().verifyTrueFalseTypeQuestionForStatic())
            {
                logger.log(Level.SEVERE,"True/False question found");
            }
            else
            {
            	Assert.fail("True/False question not found");
                logger.log(Level.SEVERE,"True/False question not found");
               
            }
        }
        catch(Exception e)
        {
          
            Assert.fail("Exception in TestCase verifyAllQuestionTypesForStatic in class VerifyAllQuestionTypesForStatic");
        }
   
    }

}
