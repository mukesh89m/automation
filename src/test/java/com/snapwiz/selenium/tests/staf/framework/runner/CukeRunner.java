package com.snapwiz.selenium.tests.staf.framework.runner;

/**
 * Created by murthi on 05-08-2016.
 */

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(strict = true,
        features = {"classpath:features/Murthi.feature"},
        plugin = {"json:target/cucumber-parallel/cucumber.json", "html:target/cucumber","rerun:target/cucumber/rerun.txt"},
        monochrome = true,
        glue = { "com.snapwiz.selenium.tests.staf.microlearning.stepdefinitions","com.snapwiz.selenium.tests.staf.microlearning.apphelper" })
public class CukeRunner {
}
