package com.snapwiz.selenium.tests.staf.microlearning.runner;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

/**
 * Created by mukesh on 19/8/16.
 */




@RunWith(Cucumber.class)
@CucumberOptions(plugin = {"json:target/cucumber.json","html:target/cucumber"},
        features = {"src/test/resources/features/signup.feature"},
        glue={"com.snapwiz.selenium.tests.staf.microlearning.stepdefinitions","com.snapwiz.selenium.tests.staf.microlearning.apphelper"},
        monochrome = true,
        dryRun=false
)
public class TestRunner {
}
