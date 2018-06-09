package com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.dashboard;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * Created by mukesh on 5/11/15.
 */
public class ClassSectionDropDown {

    @FindBy(id="active-cs")
    public WebElement active_tab;

    @FindBy(id="in-active-cs")
    public WebElement inactive_tab;

    @FindBy(id="finished-cs")
    public WebElement finished_tab;

    @FindBy(css = "span[class='drop-down-arrow']")
    public WebElement classSection_DropDown;

    @FindBy(css = "div[class='class-section-status  active ']")
    public WebElement byDefaultActive;

    @FindBy(xpath = "//div[@class='class-section-tab-footer-wrapper']/div")
    public List<WebElement> classSectionTab;

    @FindBy(className = "default-cs-name")
    public WebElement defaultClassSectionName;

    @FindBy(xpath = "//div[@class='scrollbar disable']")
    public WebElement scrollBarDisable;

    @FindBy(xpath = "//div[@class='scrollbar']")
    public List<WebElement> scrollBarEnable;

    @FindBy(className = "class-section-empty")
    public List<WebElement> emptyClassSectionMsg;

    @FindBy(className = "default-cs")
    public WebElement defaultClassSection;

    @FindBy(className = "class-section")
    public List<WebElement> classSectionName_list;

}