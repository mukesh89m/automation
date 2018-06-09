package com.snapwiz.selenium.tests.staf.learningspaces.apphelper;

import com.snapwiz.selenium.tests.staf.framework.controller.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class QuestionDifficulty extends Driver {
    //private static Logger logger=Logger.getLogger("com.snapwiz.selenium.tests.staf.learningspaces.testcases.DaigonesticTestNevigateToQuestion");
    //Its shows difficulty Level and % bar
    public boolean questiondiffculty() {
        try {
            WebDriver driver = Driver.getWebDriver();
            boolean diffcultybar = driver.findElement(By.className("al-difficulty-level-status")).isDisplayed();
            boolean diffcultypercent = driver.findElement(By.className("al-diagtest-percentage-score")).isDisplayed();
            boolean difficltylevel = driver.findElement(By.className("al-diagtest-percentage-score-text")).isDisplayed();
            if (diffcultybar == true && diffcultypercent == true && difficltylevel == true) {
                return true;
            } else {

                return false;
            }

        } catch (Exception e) {


            return false;
        }


    }

}
