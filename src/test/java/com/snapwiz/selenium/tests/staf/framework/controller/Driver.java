package com.snapwiz.selenium.tests.staf.framework.controller;

import com.snapwiz.selenium.tests.staf.framework.utility.Properties;
import com.snapwiz.selenium.tests.staf.framework.utility.ReportUtil;
import com.snapwiz.selenium.tests.staf.framework.utility.WebDriverFactory;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;


public class Driver {

    public static Map<Long, WebDriver> webDriverMap = new HashMap<>();
    public static String workingDir = System.getProperty("user.dir");
    private static Logger logger = Logger.getLogger(Driver.class.getName());


    public static Logger getLogger() {
        return logger;
    }

    @BeforeSuite
    public void beforeSuite() {
        ReportUtil.initReports();
        DOMConfigurator.configure("log4j.xml");
    }

    @BeforeMethod
    @Parameters("env.file")
    public void setUp(@Optional("Env_Common.properties") String configFile, Method method) throws Exception {

        //declare local variables
        String browser = null;
        boolean isLocal = true;
        WebDriver driver=null;

        //start report
        ReportUtil.startTest(method.getName());

        //load global properties file from resource
        Properties.loadPropertiesFile(configFile);
        browser = Properties.getPropertyValue("Browser");
        if(System.getProperty("IsLocal")!=null)
            isLocal=Boolean.parseBoolean(System.getProperty("IsLocal"));
        else
            isLocal = Boolean.parseBoolean(Properties.getPropertyValue("IsLocal"));
        //baseURL=Properties.getPropertyValue("BaseURL");

        //initializing the driver
        System.out.println(isLocal);
        driver = WebDriverFactory.startDriver(browser, isLocal);
        webDriverMap.put(Thread.currentThread().getId(), driver);
        
    }

    @AfterMethod
    public void tearDown() {

        //close browser
        if(webDriverMap.get(Thread.currentThread().getId())!=null)
          //webDriverMap.get(Thread.currentThread().getId()).quit();

        //delete driver exe file
        WebDriverFactory.deleteDriverExe();

        //end test report
        ReportUtil.endTest();
    }

    @AfterSuite
    public void afterSuite() {
        ReportUtil.endReports();
    }

    /**
     * This will get the current driver instance
     * @return web driver instance
     */
    public static WebDriver getWebDriver(){
        return webDriverMap.get(Thread.currentThread().getId());
    }



}
