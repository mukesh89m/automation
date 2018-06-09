package com.snapwiz.selenium.tests.staf.edulastic.pagefactory.googleclassroom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * Created by pragyas on 25-08-2016.
 */
public class GoogleclassRoom {

    @FindBy(css = "div[class='umGuKf gMIble PCK8he']")
    public WebElement navigator;//Navigator in left pane

    @FindBy(css = "div[class='QhQ9Dc nk37z']")
    public List<WebElement> className_onClassCard;//Class name on class card

    @FindBy(xpath = "//a[@guidedhelpid='studentTab']")
    public List<WebElement> studentTab;//Student tab

    @FindBy(css = ".QRiHXd>span")
    public List<WebElement> classCode;//Class code


    @FindBy(className = "gb_cb")
    public WebElement dropdown_signout;//Sign out drop down

    @FindBy(linkText = "Sign out")
    public WebElement signOut;//Sign out

    @FindBy(xpath = "//div[text()='Classes']")
    public WebElement classes;//Classes

    @FindBy(xpath = "//span[text()='Classroom']")
    public WebElement text_classroom;//Classroom

    @FindBy(xpath = "//div[@class='F7l5Ob-FO6che']")
    public WebElement resetClassCode;//Reset class code

    @FindBy(xpath = "//div[text()='Disable']")
    public WebElement disableClassCode;//Disable class code

    @FindBy(css = "div[class='F7l5Ob-rbyRf gMIble LnjKCd']")
    public List<WebElement> classCodeDropdown;//Class code drop down

    @FindBy(xpath = "//div[text()='Enable']")
    public WebElement enableClassCode;//Enable class code

    @FindBy(xpath = "//div[@class='ry3kXd']")
    public List<WebElement> chooseClass;

    @FindBy(xpath = "//content[text()='Create assignment']")
    public List<WebElement> createAssignment;

    @FindBy(xpath = "//div[text()='Go']")
    public WebElement goButton;

    @FindBy(xpath = "//div[text()='Assign']")
    public WebElement assignButton;









}
