package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT14.R142;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import com.snapwiz.selenium.tests.staf.learningspaces.Config;
import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.DataFetchFromDataBase;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.KeysSend;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.UploadResources;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.Click;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.TextFetch;

public class UploadResourcesVerifyInDatabase extends Driver
{
    @Test
    public void uploadResourcesVerifyInDatabase()
    {
        try
        {
            String filename =  ReadTestData.readDataByTagName("", "filename", "53");
            String resourcename=ReadTestData.readDataByTagName("", "resourcesname", "53");
            new LoginUsingLTI().ltiLogin("53");
            if(System.getProperty("UCHAR") == null) {
                resourcename = resourcename + LoginUsingLTI.appendChar;
            }
            else {
                resourcename = resourcename + System.getProperty("UCHAR");
            }
            new UploadResources().openUploadResource();
            //upload file
            new Click().clickByid("uploadFile");
            new KeysSend().sendKeyBoardKeys("$"+Config.fileUploadPath+filename+"^");
            Thread.sleep(4000);
            //56 grading help text
            new Click().clickByclassname("ins-resourceUpload-help-img-grading-policy");
            String helptext=new TextFetch().textfetchbyclass("help-data-container");
            if(!helptext.contains("If you select this option, the assignment reference resource will be available for the instructor in the \"My Resources\" page under Resources. This assignment reference should be used while creating gradable assignments for students."))
                Assert.fail("help text not shown");
            new Click().clickByclassname("ins-dialogClose");//close the pop up
            Thread.sleep(2000);
            //54--resources not sshown on my resources tab
            int nothingAvailableMessage=driver.findElements(By.cssSelector("span[class='al-performace-report-sidebar-notification-text ls-ins-no-resources-found-message']")).size();
            if(!driver.findElement(By.cssSelector("span[class='al-performace-report-sidebar-notification-text ls-ins-no-resources-found-message']")).getText().contains("Nothing"))
                Assert.fail("The default message shown on 'My Resources Page' if no resource is present is not coming");
            if(nothingAvailableMessage!=1)
                Assert.fail("resources image  shown without uploading.");
            String sql="select id from t_resources where content_name = '"+resourcename+"'";
            //55
            int entryforquestion=new DataFetchFromDataBase().userverification(1, sql);
            if(entryforquestion!=0)
                Assert.fail("in database table t_resources one row added ");
            new UploadResources().uploadResources("53", false, true, false);
            if(!(new TextFetch().textfetchbyclass("ls-grading-policy-icon").equals("Assignment Reference")))
            Assert.fail("The label 'Grading Policy' is not displayed in front of the name of resource uploaded as 'Reserved for grading policy' in My Library Page");
            //33,34,44,45,
            int entryforquestion1=new DataFetchFromDataBase().userverification(1, sql);//fetch data from t_resources after
            if(entryforquestion1==0)
                Assert.fail("in database table t_resources one row not added ");
        }
        catch(Exception e)
        {
            Assert.fail("Exception in testcase method uploadResourcesVerifyInDatabase",e);
        }
    }
}
