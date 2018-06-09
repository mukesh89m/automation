package com.snapwiz.selenium.tests.staf.AppiumLearningspaces.pageFactoryAppium;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by Dharaneesha on 04/08/15.
 */
public class HomePage_appium {
    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIAScrollView[1]/UIAWebView[1]/UIAImage[1]")
    public WebElement link_visualizingHumanGeography;

    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIAScrollView[1]/UIAWebView[1]/UIAImage[2]")
    public WebElement link_geography;

    /*@FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIAScrollView[1]/UIAWebView[1]/UIAImage[1]")
    public WebElement link_biology;*/

    @FindBy(xpath = "//UIAStaticText[@name = 'Biology']")
    public WebElement link_biology;


    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIAScrollView[1]/UIAWebView[1]/UIAImage[4]")
    public WebElement link_hrManagement;

    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIAScrollView[1]/UIAWebView[1]/UIALink[1]")
    public WebElement title_homePage;

    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIAScrollView[1]/UIAWebView[1]/UIAImage[1]")
    public WebElement link_biologyThroughRandomLogin;



}
