package com.snapwiz.selenium.tests.staf.microlearning.apphelper;

import com.galenframework.reports.GalenTestInfo;
import com.galenframework.reports.HtmlReportBuilder;
import com.galenframework.reports.model.LayoutReport;
import com.snapwiz.selenium.tests.staf.microlearning.stepdefinitions.CucumberDriver;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by murthi on 25-08-2016.
 */
public class GalenHelper {
    public void executeGalenTest(String pageName, String specFile) throws IOException, Throwable {
        WebDriver driver = CucumberDriver.getWebDriver();
        LayoutReport layoutReport = com.galenframework.api.Galen.checkLayout(driver, specFile, Arrays.asList("mobile"));
        List<GalenTestInfo> tests = new LinkedList<GalenTestInfo>();
        GalenTestInfo test = GalenTestInfo.fromString(pageName + " on desktop device test");
        test.getReport().layout(layoutReport, "check layout of " + pageName + "on desktop device using " + specFile);
        tests.add(test);
        new HtmlReportBuilder().build(tests, "target/galen-html-reports");
        CucumberDriver.getScenario().write("<a href=\"../galen-html-reports/report.html\">Click here</a> GalenHelper Report");
        if (test.isFailed())
            throw new Exception("GalenHelper Test failed. please check galen report for details.");
    }
}
