package com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelperAppium;


import com.snapwiz.selenium.tests.staf.AppiumLearningspaces.Driver;
import com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelper.Tap;
import com.snapwiz.selenium.tests.staf.AppiumLearningspaces.uihelperAppium.Wait;
import org.testng.Assert;

/**
 * Created by Snapwiz on 12/08/15.
 */
public class Navigator_appium extends Driver{
    public void navigateTo(String navigateTo){
        try{
            Thread.sleep(15000);
            new Tap().tapMainNavigator();
            Thread.sleep(2000);
            if(!dashBoard.link_dashBoard.isDisplayed()&&dashBoard.link_eTextBook.isDisplayed()&&dashBoard.link_assignments.isDisplayed()&&dashBoard.link_courseStream.isDisplayed()&&dashBoard.link_myNotes.isDisplayed()){
                Assert.fail("The Navigation List should be displayed to the User");
            }

            if(navigateTo.equalsIgnoreCase("Dashboard")){
                dashBoard.link_dashBoard.click();
                new Wait().visibilityOf(dashBoard.link_study);
                if(!dashBoard.link_study.isDisplayed()){
                  Assert.fail("Failure in Dashboard Navigation");
                }
            }else if(navigateTo.equalsIgnoreCase("e-TextBook")||navigateTo.equalsIgnoreCase("e_TextBook")||navigateTo.equalsIgnoreCase("eTextBook")){
                Thread.sleep(5000);
                dashBoard.link_eTextBook.click();
                Thread.sleep(2000);
                new Wait().visibilityOf(dashBoard.textField_searchETextBook);

                if(!dashBoard.textField_searchETextBook.isDisplayed()){
                    Assert.fail("Failure in e-TextBook Navigation");
                }
            } else if(navigateTo.equalsIgnoreCase("Assignments")){
                dashBoard.link_assignments.click();
                new Wait().visibilityOf(dashBoard.link_allAsignments);
                if(!dashBoard.link_allAsignments.isDisplayed()){
                    Assert.fail("Failure in Assignemnts Navigation");
                }
            }else if(navigateTo.equalsIgnoreCase("Course Stream")||navigateTo.equalsIgnoreCase("CourseStream")){
                dashBoard.link_courseStream.click();
                Thread.sleep(5000);
                /*if(!dashBoard.link_ShareANew.isDisplayed()){
                    Assert.fail("Failure in Course Stream Navigation");
                }*/
            }else if(navigateTo.equalsIgnoreCase("My Notes")||navigateTo.equalsIgnoreCase("MyNotes")){
                dashBoard.link_myNotes.click();
                new Wait().visibilityOf(dashBoard.text_Note);
                if(!dashBoard.text_Note.isDisplayed()){
                    Assert.fail("Failure in My Notes Navigation");
                }
            }
            Thread.sleep(1000);
        }catch(Exception e){
            Assert.fail("Exception in the method 'navigateTo' in the class 'Navigator_appium'",e);
        }
    }
}
