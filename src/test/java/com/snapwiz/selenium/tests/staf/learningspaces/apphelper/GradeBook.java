package com.snapwiz.selenium.tests.staf.learningspaces.apphelper;

import com.snapwiz.selenium.tests.staf.framework.controller.Driver;
import com.snapwiz.selenium.tests.staf.framework.utility.CustomAssert;
import com.snapwiz.selenium.tests.staf.framework.utility.ReportUtil;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Gradebook.Gradebook;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.ScrollElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by mukesh on 6/7/16.
 */
public class GradeBook extends Driver {

    /**
     * This function will return status value of grade book page
     * @author Mukesh
     * @param dataIndex(testData Index)
     * @param boxName(status box name)
     * @param assignmentName
     */
    public static String gradeBookStatusBoxCount(int dataIndex, String boxName,String assignmentName) {
        WebDriver driver=Driver.getWebDriver();
        String value="";
        int index = 1;
        List<WebElement> assignments = driver.findElements(By.cssSelector("div[class='assignment-label ellipsis gbClassPerformanceByAssignment-xAxisLabel']"));
        for (WebElement element : assignments) {
            new ScrollElement().scrollToViewOfElement(element);
            if (element.getText().trim().equals(assignmentName)) {
                break;
            }
            System.out.println("index:"+index);
            index++;
        }

        if (boxName.equals("DueDate")) {
            List<WebElement> dueDate = driver.findElements(By.cssSelector(".due-date"));
            value = dueDate.get(index).getText().trim();
        }
        if (boxName.equals("DidNotStart")) {
            List<WebElement> didNotStart = driver.findElements(By.cssSelector(".not-start"));
            value = didNotStart.get(index).getText().trim();
        }
        if (boxName.equals("DidNotFinish")) {
            List<WebElement> didNotFinish = driver.findElements(By.cssSelector(".not-finish"));
            value= didNotFinish.get(index).getText().trim();
        }
        if (boxName.equals("Finished")) {
            List<WebElement> finished = driver.findElements(By.cssSelector(".finished"));
            value = finished.get(index).getText().trim();
        }
        if (boxName.equals("AverageTimeSpent")) {
            List<WebElement> averageTimeSpent = driver.findElements(By.cssSelector(".time-spent"));
            value = averageTimeSpent.get(index).getText().trim();
        }
        if (boxName.equals("AverageGrade")) {
            List<WebElement> averageGrade = driver.findElements(By.cssSelector(".grade"));
            value = averageGrade.get(index).getText().trim();
        }
        return value;

    }

    /**
     * This function will return did not start percentage value
     * @author Mukesh
     * @param dataIndex(testData Index)
     * @param assignmentIndex(index of assignment to get the percentage value)
     */

    public static String didNotStartPercentageCalculation(int dataIndex,int assignmentIndex){
        WebDriver driver=Driver.getWebDriver();
        Gradebook gradebook= PageFactory.initElements(driver,Gradebook.class);
        String value="";
        try {
            List<WebElement> didNotStart =gradebook.didNotStart_values;
            value = didNotStart.get(assignmentIndex).getText().trim();
            String digit=value.replaceAll("[^0-9]", "");
            String sss=digit.replace(""," ").trim();
            String [] individualDigit=sss.split(" ");
            ScriptEngineManager mgr = new ScriptEngineManager();
            ScriptEngine engine = mgr.getEngineByName("JavaScript");
            String  formula =""+individualDigit[0]+"/"+individualDigit[1]+"*100";
            String ss = String.valueOf(engine.eval(formula));
            System.out.println("ss:"+ss);
            value =ss.substring(0,ss.indexOf("."));
            System.out.println("percentage:"+value+"%");
        } catch (Exception e) {
            Assert.fail("Exception in method didNotStartPercentageCalculation of class gradeBook",e);
        }
        return value+"%";
    }

    /**
     * This function will return did not finish percentage value
     * @author Mukesh
     * @param dataIndex(testData Index)
     * @param assignmentIndex(index of assignment to get the percentage value)
     */

    public static String didNotFinishPercentageCalculation(int dataIndex,int assignmentIndex){
        WebDriver driver=Driver.getWebDriver();
        Gradebook gradebook= PageFactory.initElements(driver,Gradebook.class);
        String value="";
        try {
            List<WebElement> didNotStart =gradebook.notFinish_values;
            value = didNotStart.get(assignmentIndex).getText().trim();
            String digit=value.replaceAll("[^0-9]", "");
            String sss=digit.replace(""," ").trim();
            String [] individualDigit=sss.split(" ");
            ScriptEngineManager mgr = new ScriptEngineManager();
            ScriptEngine engine = mgr.getEngineByName("JavaScript");
            String  formula =""+individualDigit[0]+"/"+individualDigit[1]+"*100";
            String ss = String.valueOf(engine.eval(formula));
            value =ss.substring(0, ss.indexOf("."));
            System.out.println("percentage:"+value+"%");

        } catch (Exception e) {
            Assert.fail("Exception in method didNotFinishPercentageCalculation of class gradeBook",e);
        }
        return value+"%";
    }

    /**
     * This function will return finished percentage value
     * @author Mukesh
     * @param dataIndex(testData Index)
     * @param assignmentIndex(index of assignment to get the percentage value)
     */
    public static String finishedPercentageCalculation(int dataIndex,int assignmentIndex){
        WebDriver driver=Driver.getWebDriver();
        Gradebook gradebook= PageFactory.initElements(driver,Gradebook.class);
        String value="";
        try {
            List<WebElement> didNotStart =gradebook.finished_values;
            value = didNotStart.get(assignmentIndex).getText().trim();
            String digit=value.replaceAll("[^0-9]", "");
            String sss=digit.replace(""," ").trim();
            String [] individualDigit=sss.split(" ");
            ScriptEngineManager mgr = new ScriptEngineManager();
            ScriptEngine engine = mgr.getEngineByName("JavaScript");
            String  formula =""+individualDigit[0]+"/"+individualDigit[1]+"*100";
            String ss = String.valueOf(engine.eval(formula));
            value =ss.substring(0, ss.indexOf("."));
            System.out.println("percentage:"+value+"%");
        } catch (Exception e) {
            Assert.fail("Exception in method finishedPercentageCalculation of class gradeBook",e);
        }
        return value+"%";
    }

    /**
     * This function will sort percentage value in ascending order
     * @author Mukesh
     * @param dataIndex(testData Index)
     */

    public static void  sortValueInAscendingOrder(int dataIndex,String status){
        WebDriver driver=Driver.getWebDriver();
        Gradebook gradebook= PageFactory.initElements(driver,Gradebook.class);
        String value="";
        List<Integer> values=new ArrayList<>();
        List<WebElement> allStatus=null;
        if (status.equals("DidNotStart")) {
            allStatus =gradebook.didNotStart_values;
        }
        if (status.equals("DidNotFinish")) {
            allStatus =gradebook.notFinish_values;
        }
        if (status.equals("Finished")) {
            allStatus =gradebook.finished_values;
        }
        if (status.equals("AssignmentFinished")) {
            allStatus =gradebook.assignmentFinished;
        }
        try {
            for (WebElement ele:allStatus) {
                new ScrollElement().scrollToViewOfElement(ele);
                value = ele.getText().trim();
                String digit = value.replaceAll("[^0-9]", "");
                String sss = digit.replace("", " ").trim();
                String[] individualDigit = sss.split(" ");
                ScriptEngineManager mgr = new ScriptEngineManager();
                ScriptEngine engine = mgr.getEngineByName("JavaScript");
                String  formula =""+individualDigit[0]+"/"+individualDigit[1]+"*100";
                String ss = String.valueOf(engine.eval(formula));
                value =ss.substring(0, ss.indexOf("."));
                System.out.println("percentage:"+value+"%");
            }
            System.out.println("Ascending value"+values);
            boolean mismatch=false;
            for (int i = 0; i <values.size()-1 ; i++) {
                if (values.get(i) > values.get(i + 1)) {
                    mismatch = true;
                    break;
                }
            }
                if(mismatch==true){
                    CustomAssert.fail("verify order", "Values are not in ascending order");
                }
                else {
                    ReportUtil.log("verify order", "Values are in ascending order", "pass");
                }

        }
        catch (Exception e) {
            Assert.fail("Exception in method sortValueInAscendingOrder of class gradeBook",e);
        }
    }
    /**
     * This function will sort percentage value in descending order
     * @author Mukesh
     * @param dataIndex(testData Index)
     */

    public static void  sortValueInDescendingOrder(int dataIndex,String status) {
        WebDriver driver = Driver.getWebDriver();
        Gradebook gradebook = PageFactory.initElements(driver, Gradebook.class);
        String value = "";
        List<Integer> values = new ArrayList<>();
        List<WebElement> allStatus=null;
        if (status.equals("DidNotStart")) {
            allStatus =gradebook.didNotStart_values;
        }
        if (status.equals("DidNotFinish")) {
            allStatus =gradebook.notFinish_values;
        }
        if (status.equals("Finished")) {
            allStatus =gradebook.finished_values;

        }
        try {
            for (WebElement ele:allStatus) {
                new ScrollElement().scrollToViewOfElement(ele);
                value = ele.getText().trim();
                String digit = value.replaceAll("[^0-9]", "");
                String sss = digit.replace("", " ").trim();
                String[] individualDigit = sss.split(" ");
                ScriptEngineManager mgr = new ScriptEngineManager();
                ScriptEngine engine = mgr.getEngineByName("JavaScript");
                String formula = "" + individualDigit[0] + "/" + individualDigit[1] + "*100";
                String ss = String.valueOf(engine.eval(formula));
                values.add(Integer.parseInt(ss.replace(".0", "")));
            }
            System.out.println("Descending value"+values);

            boolean mismatch = false;
            for (int i = 0; i < values.size() - 1; i++) {
                if (values.get(i) < values.get(i + 1)) {
                    mismatch = true;
                    break;
                }
            }

            if (mismatch == true) {
                CustomAssert.fail("verify order", "Values are not in descending order");
            } else {
                ReportUtil.log("verify order", "Values are in descending order", "pass");

            }

        }
        catch (Exception e) {
            Assert.fail("Exception in method sortValueInDescendingOrder of class gradeBook",e);
        }
    }
}
