package com.snapwiz.selenium.tests.staf.edulastic.pagefactory.profile;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * Created by shashank on 21-07-2015.
 */
public class Profile {

    @FindBy(css = "span.as-profile-edit-icon.pull-right")
    public WebElement editProfile;

    @FindBy(id = "user-email")
    public WebElement email;

    @FindBy(id = "save")
    public WebElement  buttonSave;

    @FindBy(id = "save")
    public List<WebElement>  buttonSaveList;


    @FindBy(css = "span.as-notification-message")
    public WebElement notificationMessageOnSave;

    @FindBy(className = "user-name")
    public WebElement dropdown_username;//Username drop down

    @FindBy(className = "username")
    public WebElement myProfile;// My profile

    @FindBy(id = "upload-user-profile")
    public WebElement uploadProfile;// Upload profile

    @FindBy(id = "user-first-name")
    public WebElement firstName_user;//User first name

    @FindBy(id = "user-last-name")
    public WebElement lastName_user;//User last name

    @FindBy(id = "pickfiles")
    public WebElement selectFile_uploadPopUp;//Select file to upload image

    @FindBy(id = "widget-createimage_start_queue")
    public WebElement uploadNow_uploadPopUp;//Upload now button to upload image

    @FindBy(xpath = "//div[@id='user-profile-img']/img")
    public WebElement profileImage; //Uploaded profile image

    @FindBy(xpath = "//span[contains(@class,'as-profile-change-paswdLink')]")
    public WebElement changePassword;//Change password

    @FindBy(id = "user-password")
    public WebElement textBox_newPassword;//New password text box

    @FindBy(id = "user-confirm-password")
    public WebElement textBox_confirmPassword;//Confirm password text box


}
