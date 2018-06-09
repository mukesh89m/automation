package com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.MyNotes;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by Snapwiz on 23/09/15.
 */
public class MyNote{
    @FindBy(className="auto-suggest-element")
    public WebElement autoSuggest;

    @FindBy(className="my-journal-header")
    public WebElement label_myNotes;

    @FindBy(className = "add-note-button")
    public WebElement NoteButton;

    @FindBy(className = "journal-card-bottom-description")
    public WebElement note_description;

}
