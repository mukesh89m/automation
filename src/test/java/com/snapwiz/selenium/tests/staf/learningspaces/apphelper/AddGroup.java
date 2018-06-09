package com.snapwiz.selenium.tests.staf.learningspaces.apphelper;

import com.snapwiz.selenium.tests.staf.framework.controller.Driver;
import com.snapwiz.selenium.tests.staf.framework.utility.CustomAssert;
import com.snapwiz.selenium.tests.staf.framework.utility.ReportUtil;
import com.snapwiz.selenium.tests.staf.framework.utility.StringUtil;
import com.snapwiz.selenium.tests.staf.framework.utility.WebDriverUtil;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.AssessmentResponses;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.group.Group;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.List;

/**
 * Created by mukesh on 13/5/16.
 */
public class AddGroup extends Driver {

    /**
     * This function will create group
     * @author Mukesh
     * @param dataIndex
     */
    public static String addGroup(int dataIndex){
        WebDriver driver=Driver.getWebDriver();
        String newGroupName="";
        try {
            Group group= PageFactory.initElements(driver, Group.class);
           // String random=StringUtil.generateRandomString(5, StringUtil.Mode.ALPHA);
            String random="";
            if (System.getProperty("UCHAR") == null) {
                random =LoginUsingLTI.appendChar;
            } else {
                random =System.getProperty("UCHAR");
            }

            String groupName = ReadTestData.readDataByTagName("", "GroupName", Integer.toString(dataIndex));
            String []groupNames=groupName.split(",");
            System.out.println("Length:"+groupNames.length);
            if(groupNames.length==1){
                newGroupName=groupName+random;
                System.out.println("Random String:"+random);
                new Navigator().NavigateTo("Groups");
                ReportUtil.log("Navigate to group", "Navigated to Group page", "info");
                WebDriverUtil.clickOnElementUsingJavascript(group.createNewGroup);//click on the new group link
                //group.createNewGroup.click(); //click on the new group link
                WebDriverUtil.waitTillVisibilityOfElement(group.groupName_textBox, 50);
                group.groupName_textBox.sendKeys(newGroupName);
                WebDriverUtil.clickOnElementUsingJavascript(group.done_button); //click on the done button
            }else {
                for (int i = 0; i < groupNames.length; i++) {
                    newGroupName=groupNames[i]+random;
                    System.out.println("Inside if Random String:"+random);
                    new Navigator().NavigateTo("Groups");
                    ReportUtil.log("Navigate to group", "Navigated to Group page", "info");
                    WebDriverUtil.clickOnElementUsingJavascript(group.createNewGroup); //click on the new group link
                    WebDriverUtil.waitTillVisibilityOfElement(group.groupName_textBox, 50);
                    group.groupName_textBox.sendKeys(groupNames[i]+random);
                    group.done_button.click(); //click on the done button
                    Thread.sleep(2000);
                    String notificationMessage=group.notificationMessage.getText().trim();
                    CustomAssert.assertEquals(notificationMessage, "Group created successfully.", "Verify for Notification Message", "Group created successfully.", "Group not created successfully.");
                }

            }

            Thread.sleep(1000);
            String notificationMessage=group.notificationMessage.getText().trim();
            CustomAssert.assertEquals(notificationMessage, "Group created successfully.", "Verify for Notification Message", "Group created successfully.", "Group not created successfully.");
            return newGroupName;

        } catch (Exception e) {
            Assert.fail("Exception in addGroup appHelper",e);
        }
        return newGroupName;
    }

    /**
     * This function select group based on index number
     * @author Mukesh
     * @param groupIndex(start with 1 for the first group)
     */

  public static void selectGroup(int groupIndex){
      WebDriver driver=Driver.getWebDriver();
      try {
          Group group= PageFactory.initElements(driver, Group.class);
          new Navigator().NavigateTo("Groups");
          group.group_container.get(groupIndex).click();
      } catch (Exception e) {
          Assert.fail("Exception in selectGroup appHelper", e);
      }
  }
    /**
     * This function add student student to particular group
     * @author Mukesh
     * @param groupIndex(start with 1 for the first group)
     * @param startIndex
     * @param noOfStudent (How many student you want to add in group)
     */
    public static void addStudentToParticularGroup(int groupIndex,int startIndex,int noOfStudent){
        WebDriver driver=Driver.getWebDriver();
        Group group= PageFactory.initElements(driver, Group.class);

        try {
            WebDriverUtil.clickOnElementUsingJavascript(group.group_container.get(groupIndex));
            for (int i = startIndex; i <noOfStudent ; i++) {
                group.studentCheckBox.get(i).click();
                Thread.sleep(2000);
            }
        } catch (Exception e) {
            Assert.fail("Exception in addStudentToParticularGroup appHelper", e);
        }
    }

    public static void selectGroupInAssignmentResponsePage(int groupIndex){
        WebDriver driver=Driver.getWebDriver();
        try {
            AssessmentResponses assessmentResponses = PageFactory.initElements(driver, AssessmentResponses.class);
            WebDriverUtil.clickOnElementUsingJavascript(assessmentResponses.group_dropdown.get(groupIndex));
        } catch (Exception e) {
            Assert.fail("Exception in selectGroupInAssignmentResponsePage appHelper", e);
        }
    }

    public static void deleteStudentFromGroup(int groupIndex,String dataIndex) {
        WebDriver driver = Driver.getWebDriver();
        Group group = PageFactory.initElements(driver, Group.class);
        String familyName = ReadTestData.readDataByTagName("", "familyname", dataIndex);
        String givenName = ReadTestData.readDataByTagName("", "givenname", dataIndex);

        String studentName=familyName+", "+givenName;
        System.out.println("studentName:"+studentName);
          int index=0;
        try {
            selectGroup(groupIndex);
            List<WebElement> allStudentOfSelectedGroup=group.selectedStudentName;
            for(WebElement ele:allStudentOfSelectedGroup){
                System.out.println(ele.getText());
                if(ele.getText().trim().equals(studentName)){
                   break;
                }
                index++;
            }
            System.out.println("Index:" + index);
            WebDriverUtil.clickOnElementUsingJavascript(group.crossIconOnStudent.get(index));
            group.delete.sendKeys("DELETE");
            group.yesOnDelete.click();

        } catch (Exception e) {
            Assert.fail("Exception in addStudentToParticularGroup appHelper", e);

        }
    }


    public static void deleteGroup(int groupIndex) {
        WebDriver driver = Driver.getWebDriver();
        Group group = PageFactory.initElements(driver, Group.class);

        try {
            selectGroup(groupIndex);
            group.dotIconHighlightGroup.click();
            group.deleteGroup.click();//click on delete group
            group.delete.sendKeys("DELETE");
            group.yesOnDelete.click();//click on yes

        } catch (Exception e) {
            Assert.fail("Exception in addStudentToParticularGroup appHelper", e);

        }
    }


    public static void deleteStudentFromClassList(int groupIndex) {
        WebDriver driver = Driver.getWebDriver();
        Group group = PageFactory.initElements(driver, Group.class);

        try {
            selectGroup(groupIndex);
            group.studentCheckedBox.get(0).click();//click on 'x' of student in group card
            group.delete.sendKeys("DELETE");
            group.yesOnDelete.click();//click on yes

        } catch (Exception e) {
            Assert.fail("Exception in addStudentToParticularGroup appHelper", e);

        }

    }


}


