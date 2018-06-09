package com.snapwiz.selenium.tests.staf.microlearning.stepdefinitions;

import com.galenframework.api.GalenPageDump;
import com.snapwiz.selenium.tests.staf.microlearning.apphelper.GalenHelper;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.testng.Assert;

/**
 * Created by murthi on 25-08-2016.
 */
public class Common {

    @Then("^I validate the layout of \"([^\"]*)\" with spec file \"([^\"]*)\"$")
    public void iValidateTheLayoutOfWithSpecFile(String pageName, String specFile) {
        try {
            new GalenHelper().executeGalenTest(pageName, specFile);
        } catch (Throwable ex) {
            Assert.assertTrue(false, "There is test failure in galen. please check the galen report");
        }
    }

    @When("^I create the dump for \"([^\"]*)\" using spec file \"([^\"]*)\"$")
    public void iCreateTheDumpPageForUsingSpecFile(String pageName, String specFile){
        try{
            new GalenPageDump(pageName).dumpPage(CucumberDriver.getWebDriver(),specFile,"src/test/resources/specs/dump/"+specFile.split("\\.")[0]
                    .split("/")[1]);
        }
        catch (Throwable ex){
            Assert.assertTrue(false, "There is test failure in galen. please check the galen report");
        }
    }

}
