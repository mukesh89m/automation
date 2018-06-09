package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT9.R1;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.TextValidate;

public class LTILoginCheck extends Driver {

    @Test(priority = 1, enabled=true)
    public void loginCustomDestinationBlank()  {
        try
        {
            new LoginUsingLTI().ltiLogin("13");
            String expectederror = ReadTestData.readDataByTagName("", "expectederror", "13");
            Boolean textPresent = new TextValidate().IsTextPresent(expectederror);
            if(textPresent == false)
                Assert.fail("LTI login not failed even after giving custom destination field as blank while login in for course type LS");
        }
        catch(Exception e)
        {
            Assert.fail("Exception in  TestCase loginCustomDestinationBlank in class LTILogoinCheck",e);
        }
    }

    @Test(priority = 2, enabled=true)
    public void loginWithCustomDestinationHomePage()
    {
        try
        {
            new LoginUsingLTI().ltiLogin("14");
        }
        catch(Exception e)
        {
            Assert.fail("Exception in  TestCase loginWithCustomDestinationHomePage in class LTILogoinCheck",e);
        }
    }


    @Test(priority = 3, enabled=true)
    public void loginAdaptiveCustomDestBlank()  {
        try
        {
            new LoginUsingLTI().ltiLogin("16");
            String expectederror = ReadTestData.readDataByTagName("", "expectederror", "16");
            Boolean textPresent = new TextValidate().IsTextPresent(expectederror);
            if(textPresent == false)
                Assert.fail("LTI login not failed even after giving custom destination field as blank while login in course type Adaptive");
        }
        catch(Exception e)
        {
            Assert.fail("Exception in  TestCase LoginAdaptiveCustomDestBlank in class LTILogoinCheck",e);
        }
    }

    @Test(priority = 4, enabled=true)
    public void loginAdaptive()  {
        try
        {
            new LoginUsingLTI().ltiLogin("20");
        }
        catch(Exception e)
        {
            Assert.fail("Exception in  TestCase loginAdaptive in class LTILogoinCheck",e);
        }


    }
    @Test(priority = 6, enabled=true)
    public void resourseLinkAnyValue()
    {
        try
        {
            new LoginUsingLTI().ltiLogin("22");
        }
        catch(Exception e)
        {
            Assert.fail("Exception in  TestCase resourseLinkAnyValue in class LTILogoinCheck",e);
        }
    }

}
