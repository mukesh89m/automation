package com.snapwiz.selenium.tests.staf.edulastic.testcases.IT1.classManagement;

import com.snapwiz.selenium.tests.staf.edulastic.Driver;
import com.snapwiz.selenium.tests.staf.edulastic.apphelpers.Login;
import com.snapwiz.selenium.tests.staf.edulastic.apphelpers.Navigator;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.manageClass.ManageClass;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by shashank on 02-03-2015.
 *
 */
public class
        CleverSync extends Driver{

    @Test(priority = 0,enabled=false)
    public void cleverSync()
    {
     try {


         //driver.get("10.0.0.52");
         ManageClass manageClass = PageFactory.initElements(driver, ManageClass.class);
         new Login().directLoginAsDATeacher(0, "testfeb9-2@test.com");
         new Navigator().navigateTo("manageClass");
         Assert.assertEquals(driver.findElements(By.cssSelector("div.as-manage-class-settings")).size(),0,"Exception in Cleversync Class in method cleversync");
         new Login().directLoginAsDAStudent(0, "edutestfeb9-5@gmail.com");
         new Navigator().navigateTo("manageClass");
         Assert.assertEquals(driver.findElements(By.cssSelector("div.as-manage-class-settings")).size(),0,"Exception in Cleversync Class in method cleversync");
     }
     catch (Exception e)
     {
         Assert.fail("Exception in Cleversync Class in method cleversync",e);
     }

    }

}
