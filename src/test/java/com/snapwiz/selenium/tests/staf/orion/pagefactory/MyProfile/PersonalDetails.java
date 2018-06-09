package com.snapwiz.selenium.tests.staf.orion.pagefactory.MyProfile;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by root on 2/3/16.
 */
public class PersonalDetails {
    @FindBy(xpath = "//div[@class='my-profile-wrapper']//h4")
    public WebElement label_personalDetails;

    @FindBy(xpath = "//div[@class='idb-user-profile-settings-wrapper']//img")
    public WebElement profileImage;

    @FindBy(id = "upload-profile-thumbnail")
    public WebElement ProfileUploadIcon;

    @FindBy(id = "start_queue")
    public WebElement button_uploadNow;

    @FindBy(id = "user-thumbnail-img")
    public WebElement thumbnailImage;




}
