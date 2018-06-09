package com.snapwiz.selenium.tests.staf.orion.pagefactory.allActivity;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * Created by priyanka on 30-11-2015.
 */
public class AllActivity {

   @FindBy(id = "ls-my-Activity")
    public WebElement allActivity;

    @FindBy(xpath = "//div[@class='al-notification-message-body']")
    public WebElement notificationStudent;

    @FindBy(linkText = "All Activity")
    public WebElement allActivityTitle;

    @FindBy(id = "my-journal-tab-button")
    public WebElement myJournal;

    @FindBy(linkText = "My Journal")
    public WebElement myJournalTitle;

    @FindBy(xpath = "//header[@class='my-journal-activity-event-header my-journal-activity-media']")
    public List<WebElement> activityHeader;

    @FindBy(xpath = "//a[@title='All Chapters']")
    public List<WebElement> allChaptersFilter;


}
