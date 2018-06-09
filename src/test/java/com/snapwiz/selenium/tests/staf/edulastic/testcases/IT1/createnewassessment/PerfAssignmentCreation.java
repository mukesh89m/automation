package com.snapwiz.selenium.tests.staf.edulastic.testcases.IT1.createnewassessment;

import com.snapwiz.selenium.tests.staf.edulastic.Driver;
import com.snapwiz.selenium.tests.staf.edulastic.apphelpers.Assignment;
import com.snapwiz.selenium.tests.staf.edulastic.apphelpers.Login;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by mahesh kumar on 3/2/2015.
 */
public class PerfAssignmentCreation extends Driver {

    @Test
    public void perfAssignmentCreation() {
        try {
            new Login().directLoginAsInstructor(0,"instructor@snapwiz.com");
            new Assignment().create(1,"multipleselection");
            new Assignment().addQuestion(1,"multiplechoice");
            new Assignment().addQuestion(1,"truefalse");
            new Assignment().addQuestion(1,"textentry");
            new Assignment().addQuestion(1,"textdropdown");
            new Assignment().addQuestion(1,"numericentrywithunits");
            new Assignment().addQuestion(1,"expressionevaluator");
            new Assignment().addQuestion(1,"draganddrop");
        }
        catch (Exception e) {
            Assert.fail("",e);
        }
    }

}
