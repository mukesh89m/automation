package com.snapwiz.selenium.tests.staf.datacreation.testcases;

import com.snapwiz.selenium.tests.staf.datacreation.Driver;

import com.snapwiz.selenium.tests.staf.datacreation.apphelper.DiagnosticTest;
import com.snapwiz.selenium.tests.staf.datacreation.apphelper.DirectLogin;
import com.snapwiz.selenium.tests.staf.datacreation.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.datacreation.uihelper.RandomNumber;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by root on 30/12/14.
 */
public class MaheshData extends Driver {

    @Test
    public void orionData() {

        try {
for(int i = 1;i<=2;i++) {
          Runtime.getRuntime().exec("./set_date" + Integer.toString(i) + ".sh");

          new DirectLogin().CMSLogin();

          new DiagnosticTest().startTest(0, new RandomNumber().generateRandomNumber(1, 5));
          new DiagnosticTest().attemptProfile(1, "Diagnostic", "orion", i);
          new DiagnosticTest().quitTestAndGoToDashboard();
    // new Navigator().orionDashboard();
}

        }
        catch (Exception e) {
            Assert.fail("Exception ", e);
        }

    }
}
