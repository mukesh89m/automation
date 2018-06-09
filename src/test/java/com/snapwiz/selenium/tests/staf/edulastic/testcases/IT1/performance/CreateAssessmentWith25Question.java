package com.snapwiz.selenium.tests.staf.edulastic.testcases.IT1.performance;

import com.snapwiz.selenium.tests.staf.edulastic.Driver;
import com.snapwiz.selenium.tests.staf.edulastic.apphelpers.*;
import org.testng.annotations.Test;

/**
 * Created by shashank on 05-10-2015.
 */
public class CreateAssessmentWith25Question extends Driver {

    @Test(enabled = true,priority = 1)
    public void createAssessmentSharedPublic()
    {
        int dataIndex = 1;
        String appendChar = "a";
            new SignUp().teacher(appendChar,dataIndex);//Sign up as teacher
            new School().createWithOnlyName(appendChar,dataIndex);//Create school
            new Classes().createClass(appendChar,dataIndex);//Create class

        for(int i=1;i<=20;i++)
        {
            new Assignment().create(dataIndex,"truefalse","perf"+i);//Create an assignment with multipleselection question type
            for(int j=1;j<25;j++)
            {
                new Assignment().addQuestion(dataIndex,"truefalse","perf"+i);
            }
           // new Assignment().assignToStudent(dataIndex,"perf"+i,1);

        }


    }

}
