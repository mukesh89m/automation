package com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelperAppium;

import com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelper.Calender;
import org.testng.Assert;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Dharaneesha on 07/08/15.
 */
public class Calender_appium {
    public String getTodaysDateAsperFormat() {
        String todaysDate = null;
        try {
            String month =  new SimpleDateFormat("MMMM").format(new Date());
            String currentDate =  new SimpleDateFormat("dd").format(new Date());
            String currentYear=  new SimpleDateFormat("yyyy").format(new Date());
            todaysDate = currentDate+getCurrentMonthName(month)+currentYear;


        } catch (Exception e) {
            Assert.fail("Exception in Method getTodaysDateAsperFormat in class Calender_appium.", e);
        }
        return todaysDate;
    }

    public String getCurrentMonthName(String monthName){
        String[] monthNames ={"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        int pos = 0;
        String currentMonth = null;
        try{
            for(int a=0;a<monthNames.length;a++){
                if(monthNames[a].equals(monthName)){
                    pos = a+1;
                    break;
                }
            }
            currentMonth= 0+""+pos;
        }catch(Exception e){
            Assert.fail("Exception in the method 'getCurrentMonthName' in the class 'Calender'", e);
        }
        return currentMonth;
    }
}
