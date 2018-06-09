package com.snapwiz.selenium.tests.staf.learningspaces.apphelper;

import org.testng.Assert;

import java.util.Arrays;

/**
 * Created by Mukesh on 4/10/15.
 */
public class Calender {
    public static String getCurrentMonthName(int index){
        String[] monthNames ={"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        try{
        }catch(Exception e){
            Assert.fail("Exception in the method 'getCurrentMonthName' in the class 'Calender'", e);
        }
        if(index>10)
        {
           index= index-11;
        }
        return monthNames[index];
    }



    public static Integer getCurrentMonthIndex(String currentMonthName){
        String[] monthNames ={"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        try{
        }catch(Exception e){
            Assert.fail("Exception in the method 'getCurrentMonthName' in the class 'Calender'", e);
        }
        return(Arrays.asList(monthNames).indexOf(currentMonthName));
    }

}
