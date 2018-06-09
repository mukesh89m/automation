package com.snapwiz.selenium.tests.staf.microlearning.pagefactory;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by navin on 9/23/2016.
 */
public class LessonSubscribePage {

    @FindBy (css = ".ml-rating-block")
    public WebElement completeRatingBlock;

    @FindBy(css = ".ml-ratings")
    public WebElement actualRatingBlock;

    @FindBy(css = ".ml-ratings-score.m-l-xs")
    public WebElement CompleteRatingLabel;

    @FindBy(css = ".btn.btn-sm.btn-rounded.btn-green.ml-preview-add-btn.js-add2my-library.pull-right")
    public WebElement addToMyLibraryButton;



}
