package com.snapwiz.selenium.tests.staf.learnon.apphelper;

import com.snapwiz.selenium.tests.staf.learnon.uihelper.Click;
import org.testng.Assert;

/**
 * Created by root on 12/29/15.
 */
public class Settings{
    public void disableTopic(int topicIndex){
        try{
            new Click().clickByclassname("ls-user-nav__username");
            new Click().clickbylinkText("My course settings");
            new Click().clickBycssselector("label[for='studyPlanEnable']");
            new Click().clickbylinkText("Customize Now >");
            new Click().clickbylistcssselector("label[class='customizeChapterLevel settingsOnOff settings-on']",topicIndex);



        }catch(Exception e){
            Assert.fail("Exception in class 'Settings.jav' in the method 'disableTopic'",e);
        }
    }

}
