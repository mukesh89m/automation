package com.snapwiz.selenium.tests.staf.learningspaces.testcases.anonymous;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;

public class LTICustomCourseNumberAssociatedWithDomain extends Driver{
    private static Logger logger = Logger.getLogger("com.snapwiz.selenium.tests.staf.learningspaces.testcases.anonymous.LTICustomCourseNumberAssociatedWithDomain");

    @Test
    public void ltiCustomCourseNumberAssociatedWithDomain()
    {
        String textVerify=ReadTestData.readDataByTagName("", "text1", "9");
        for(int i=1;i<3;i++)
        {
            switch(i)
            {
                case 1:
                    try
                    {
                        startDriver("firefox");
                        new LoginUsingLTI().ltiLogin("9");
                        String text = driver.findElement(By.className("al-header-description")).getAttribute("title");
                        Assert.assertEquals(text, textVerify, "The course number (LTI Param name:custom_course_number) should NOT be associated to the domain and created during domain creation.");

                    }
                    catch(Exception e)
                    {
                        Assert.fail("Exception  in testcase ltiCustomCourseNumberAssociatedWithDomain in class LTICustomCourseNumberAssociatedWithDomain.",e);
                    }

                    driver.quit();
                    break;
                case 2:
                    try
                    {
                        startDriver("firefox");
                        logger.log(Level.INFO,"Starting TestCase LTICustomCourseNumberAssociatedWithDomain");
                        new LoginUsingLTI().ltiLogin("10");
                        String text = driver.findElement(By.className("al-header-description")).getAttribute("title");
                        System.out.println(text);

                        if(text.equals(textVerify))
                        {
                            logger.log(Level.INFO,"Testcase LTICustomCourseNumberAssociatedWithDomain Pass");
                        }
                        else
                        {
                            logger.log(Level.INFO,"Testcase LTICustomCourseNumberAssociatedWithDomain fail");
                            Assert.fail("If the course number is updated due to any reasons, the product will ignore the new course number during the LTI call i.e. course number once created will  be changed.");
                        }
                    }
                    catch(Exception e)
                    {
                        logger.log(Level.SEVERE,"Exception in  TestCase LTICustomCourseNumberAssociatedWithDomain",e);
                        Assert.fail("Exception  in testcase ltiCustomCourseNumberAssociatedWithDomain in class LTICustomCourseNumberAssociatedWithDomain.",e);
                    }
                    break;
                default: break;
            }

        }
    }





}
