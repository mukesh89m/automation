package com.snapwiz.selenium.tests.staf.edulastic.pagefactory.reports;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * Created by shashank on 02-03-2016.
 */
public class DetailedMasteryreport {

    @FindBy(xpath = "//td[contains(@class,'skillReportIns-orangeGrade')]")
    public List<WebElement> detailedElo;

    @FindBy(xpath = "//td[contains(@class,'skillReportIns-redGrade')]")
    public List<WebElement> detailedEloNotMastered;

    @FindBy(xpath = "//*[starts-with(@class,'question-link-label')]")
    public List<WebElement> questionBubble;

    @FindBy(css = "#view-user-question-performance-score-box")
    public List<WebElement> scorePerQuestion;
}
