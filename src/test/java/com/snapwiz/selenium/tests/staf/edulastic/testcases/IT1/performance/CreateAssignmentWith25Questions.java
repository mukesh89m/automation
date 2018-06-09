package com.snapwiz.selenium.tests.staf.edulastic.testcases.IT1.performance;

import com.snapwiz.selenium.tests.staf.edulastic.Driver;
import com.snapwiz.selenium.tests.staf.edulastic.apphelpers.*;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by pragya on 21-08-2015.
 */
public class CreateAssignmentWith25Questions extends Driver{

    @Test(priority = 1,enabled = true)
    public void performance3(){
        try{
            int dataIndex = 1;
            String appendChar = "a";
           /* new SignUp().teacher(appendChar,dataIndex);//Sign up as teacher
            new School().createWithOnlyName(appendChar,dataIndex);//Create school
            new Classes().createClass(appendChar,dataIndex);//Create class
*/
            new Login().loginAsInstructor(appendChar,dataIndex);

            new Assignment().create(dataIndex,"multipleselection");//Create an assignment with multipleselection question type
            //Add questions
            for(int i=0; i<3; i++){
                new Assignment().addQuestion(dataIndex,"multiplechoice");
                new Assignment().addQuestion(dataIndex,"truefalse");
                new Assignment().addQuestion(dataIndex,"textentry");
                new Assignment().addQuestion(dataIndex,"textdropdown");
                new Assignment().addQuestion(dataIndex,"numericentrywithunits");
                new Assignment().addQuestion(dataIndex,"expressionevaluator");
                new Assignment().addQuestion(dataIndex,"draganddrop");
                new Assignment().addQuestion(dataIndex,"multipleselection");
                if(i>1){
                    new Assignment().addQuestion(dataIndex,"multiplechoice");
                    new Assignment().addQuestion(dataIndex,"truefalse");
                    new Assignment().addQuestion(dataIndex,"textentry");
                    new Assignment().addQuestion(dataIndex,"textdropdown");
                    new Assignment().addQuestion(dataIndex,"numericentrywithunits");
                    new Assignment().addQuestion(dataIndex,"expressionevaluator");
                    new Assignment().addQuestion(dataIndex,"advancednumeric");
                    new Assignment().addQuestion(dataIndex,"graphing");
                }
            }

        }catch (Exception e){
            Assert.fail("Exception in 'CreateAssignmentWith25Questions' in method 'createAssignment'", e);

        }
    }



    @Test(priority = 2)
    public void PerformanceUpload(){
        try{
            int dataIndex = 1;
            String appendChar = "a1";
            /*new SignUp().teacher(appendChar,dataIndex);//Sign up as teacher
            new School().createWithOnlyName(appendChar,dataIndex);//Create school
            new Classes().createClass(appendChar,dataIndex);//Create class
            new Assignment().create(dataIndex,"truefalse");*/


            new Login().loginAsInstructor(appendChar,dataIndex);


            for(int i=0; i<24; i++){
                new Assignment().addQuestion(dataIndex,"truefalse");
            }

        }catch (Exception e){
            Assert.fail("Exception in 'CreateAssignmentWith25Questions' in method 'PerformanceUpload'", e);

        }
    }




}
