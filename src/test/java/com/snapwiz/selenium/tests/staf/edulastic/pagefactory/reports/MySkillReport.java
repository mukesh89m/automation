package com.snapwiz.selenium.tests.staf.edulastic.pagefactory.reports;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * Created by shashank on 03-03-2016.
 */
public class MySkillReport {

    @FindBy(xpath = "//div[starts-with(@class,'common-core-std-grade')]")
    public List<WebElement> percentUnderElo;

    @FindBy(css = "div[class='lsm-report-progress-fill lsm-proficiency-yellowfill']")
    public List<WebElement> percentScorePerElo;

    @FindBy(css = "div[class='lsm-report-progress-fill lsm-proficiency-greenfill']")
    public List<WebElement> percentScorePerEloGreenColor;

}
