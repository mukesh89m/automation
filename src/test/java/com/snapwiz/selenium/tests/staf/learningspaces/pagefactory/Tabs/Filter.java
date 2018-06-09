package com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Tabs;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * Created by mukesh on 11/12/15.
 */
public class Filter {

    @FindBy(xpath = "//a[@title='Show All Chapters']")
    public WebElement showChapter_link;

    @FindBy(xpath = "//div[@id='report-sidebar-chapter-dropDown-wrapper']//li")
    public List<WebElement> selectChapter;
}
