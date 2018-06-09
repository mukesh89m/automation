package com.snapwiz.selenium.tests.staf.orion.pagefactory.Dashboard;

import com.snapwiz.selenium.tests.staf.orion.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * Created by mukesh on 10/11/15.
 */
public class ClassSectionDropDown{
    @FindBy(id="active-sections-list-drop-down")
    public WebElement active_tab;

    @FindBy(id="inactive-sections-list-drop-down")
    public WebElement inactive_tab;

    @FindBy(id="finished-sections-list-drop-down")
    public WebElement finished_tab;

   @FindBy(id="drop-down-display-label")
   public WebElement classSection_DropDown;


    @FindBy(css = "div[class='all-section-drop-down-options-selection active']")
    public WebElement byDefaultActive;

    @FindBy(xpath = "//div[@id='all-section-drop-down-options']/div")
    public List<WebElement> classSectionTab;

    @FindBy(id = "display-name")
    public WebElement defaultClassSectionName;

    @FindBy(xpath = "//div[@class='scrollbar disable']")
    public WebElement scrollBarDisable;

    @FindBy(xpath = "//div[@class='scrollbar']")
    public List<WebElement> scrollBarEnable;

    @FindBy(className = "class-section-empty")
    public List<WebElement> emptyClassSectionMsg;

    @FindBy(id = "class-section-one-element")
    public WebElement emptyClassSectionMsg1;

    @FindBy(id = "drop-down-display-label")
    public WebElement defaultClassSection;

    @FindBy(className = "js-sections-list-drop-down")
    public List<WebElement> classSectionName_list;


    @FindBy(className = "al-user-profile-inner-wrapper")
    public WebElement classSectionDropDownStudent;

    @FindBy(id = "myReport")
    public WebElement myReport;

    @FindBy(className = "al-user-profile-name")
    public WebElement profileDropDown;

    @FindBy(className = "js-sections-list-drop-down")
    public List<WebElement> listDropDown;
}
