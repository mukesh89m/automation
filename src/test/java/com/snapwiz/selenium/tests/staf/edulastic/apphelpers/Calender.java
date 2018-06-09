package com.snapwiz.selenium.tests.staf.edulastic.apphelpers;

import org.testng.Assert;

import java.util.Calendar;

/**
 * Created by root on 2/10/15.
 */
public class Calender {

    public static String getCurrentMonthName(int index){
        String[] monthNames ={"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
        try{
        }catch(Exception e){
            Assert.fail("Exception in the method 'getCurrentMonthName' in the class 'Calender'",e);
        }
        if(index>10)
        {
            index= index-11;
        }
        return monthNames[index];
    }

    public static String getCurrentMonthNameFull(int index){
        String[] monthNames ={"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        try{
        }catch(Exception e){
            Assert.fail("Exception in the method 'getCurrentMonthName' in the class 'Calender'",e);
        }
        return monthNames[index];
    }
}
