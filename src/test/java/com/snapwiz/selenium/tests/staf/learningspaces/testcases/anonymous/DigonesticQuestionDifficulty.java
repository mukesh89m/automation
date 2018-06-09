package com.snapwiz.selenium.tests.staf.learningspaces.testcases.anonymous;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;


public class DigonesticQuestionDifficulty extends Driver
{
    private static Logger logger=Logger.getLogger("com.snapwiz.selenium.tests.staf.learningspaces.testcases.anonymous.DigonesticQuestionDifficulty");
    /*1099-1100
     */
    @Test
    public void digonesticquestiondifficulty ()
    {
        try
        {
            new LoginUsingLTI().ltiLogin("1099");
            new Navigator().NavigateTo("eTextbook");
            new DiagnosticTest().startTest(2);
            boolean questiondifficulty=new QuestionDifficulty().questiondiffculty();

            if(questiondifficulty==true)
            {
                logger.log(Level.INFO,"TestCase DigonesticQuestionDifficulty Pass ");
            }
            else
            {
                logger.log(Level.INFO,"TestCase DigonesticQuestionDifficulty fail ");
                Assert.fail("Question difficulty not shown all the level bar and %.");
            }

        }
        catch(Exception e)
        {
            logger.log(Level.SEVERE,"Exception in LoginUsingLTI Application Helper",e);
            Assert.fail("Exception in LoginUsingLTI Application Helper",e);

        }
    }
    @AfterMethod
    public void TearDown()throws Exception
    {
        driver.quit();
    }


}
