package com.snapwiz.selenium.tests.staf.microlearning.stepdefinitions;

import com.snapwiz.selenium.tests.staf.framework.utility.Properties;
import com.snapwiz.selenium.tests.staf.framework.utility.WebDriverFactory;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;

import java.util.HashMap;
import java.util.Map;

import static com.snapwiz.selenium.tests.staf.framework.controller.Driver.webDriverMap;

/**
 * Created by murthi on 22-01-2016.
 */
public class CucumberDriver {
    public static Map<Long, WebDriver> webDriverMap = new HashMap<>();
    public static Map<String, Object> stringObjectMap = new HashMap<>();




    public static Scenario getScenario() {
        return (Scenario) stringObjectMap.get(Thread.currentThread().getName());
    }

    @After
    public void afterScenario(Scenario scenarioResult) {
        if (scenarioResult.isFailed()) {
            System.out.println("Scenario " + scenarioResult.getName() + " is failed");
            try {
                byte[] screenshot = ((TakesScreenshot) getWebDriver()).getScreenshotAs(OutputType.BYTES);
                scenarioResult.embed(screenshot, "image/png");
                scenarioResult.write("URL at failure: " + getWebDriver().getCurrentUrl());
            } catch (WebDriverException wde) {
                scenarioResult.write("Embed Failed " + wde.getMessage());
            } catch (ClassCastException cce) {
                cce.printStackTrace();
            }
        } else
            System.out.println("Scenario " + scenarioResult.getName() + " is passed");
        if(getWebDriver()!=null)
            getWebDriver().quit();
    }

    @Before
    public void beforeScenario(Scenario scenarioResult) throws Exception {
        //declare local variables
        String baseURL = null;
        String browser = null;
        boolean isLocal = true;
        WebDriver driver = null;
        String configFile = System.getProperty("env.file");
        stringObjectMap.put(Thread.currentThread().getName(), scenarioResult);
        System.out.println("Scenario " + scenarioResult.getName() + " is started");

        if (configFile == null) {
            configFile = "ML_Config.properties";
        }
        //load global properties file from resource
        Properties.loadPropertiesFile(configFile);
        browser = Properties.getPropertyValue("Browser");
        if (System.getProperty("IsLocal") != null)
            isLocal = Boolean.parseBoolean(System.getProperty("IsLocal"));
        else
            isLocal = Boolean.parseBoolean(Properties.getPropertyValue("IsLocal"));
        baseURL = Properties.getPropertyValue("BaseURL");

        //initializing the driver

        driver = WebDriverFactory.startDriver(browser, isLocal);
        webDriverMap.put(Thread.currentThread().getId(), driver);

    }
    /**
     * This will get the current driver instance
     *
     * @return web driver instance
     */
    public static WebDriver getWebDriver() {
        return webDriverMap.get(Thread.currentThread().getId());
    }
}
