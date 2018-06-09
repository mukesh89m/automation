package com.snapwiz.selenium.tests.staf.edulastic.testcases.IT1.allquestions;

import com.snapwiz.selenium.tests.staf.edulastic.apphelpers.*;
import com.snapwiz.selenium.tests.staf.framework.controller.Driver;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by snapwiz on 01-03-2016.
 */
public class AllQuestions extends Driver {

    @Test(priority = 1,enabled = true)
    public void allQuestions(){
        {
            try{
                int dataIndex = 1;
                String appendChar = "a";

                new SignUp().teacher(appendChar,dataIndex);
                new School().createWithOnlyName(appendChar,dataIndex);
                new Classes().createClass(appendChar,dataIndex);

                new Assignment().create(dataIndex,"all");
                new Assignment().assignToStudent(dataIndex,appendChar);


            }catch (Exception e){
                Assert.fail("Exception in 'AllQuestions' Class in method 'allQuestions'", e);
            }

        }
    }
}


