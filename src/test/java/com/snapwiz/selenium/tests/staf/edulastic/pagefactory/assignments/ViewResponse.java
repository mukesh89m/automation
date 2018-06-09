package com.snapwiz.selenium.tests.staf.edulastic.pagefactory.assignments;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * Created by shashank on 06-08-2015.
 */
public class ViewResponse {
    @FindBy(css = "button[class='btn gradebook-middle-btn as-live-reassign-wrapper']")
    public WebElement reassign;

    @FindBy(css = "div.icheckbox_square-green")
    public List<WebElement> checkStudentCard;

    @FindBy(css = "div.response-header >h4")
    public List<WebElement> fetchStudentEmailId;
}
