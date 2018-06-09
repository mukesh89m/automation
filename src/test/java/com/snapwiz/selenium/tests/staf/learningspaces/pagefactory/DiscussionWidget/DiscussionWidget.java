package com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.DiscussionWidget;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * Created by Mukesh on 7/2/15.
 */
public class DiscussionWidget {

    @FindBy(className = "ir-ls-assign-dialog-dw-gradable-label-check ")
    public WebElement gradable_checkbox;

    @FindBy(css = "a[class='ls-content-post__footer-comment-link js-toggle-comments']")
    public List<WebElement> perspective_comment;
}
