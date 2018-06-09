package com.snapwiz.selenium.tests.staf.learningspaces.testcases.anonymous;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;

public class FiltersUnderPerformanceFrame extends Driver
{
    private static Logger logger = Logger.getLogger("com.snapwiz.selenium.tests.staf.learningspaces.testcases.anonymous.FiltersUnderPerformanceFrame");

    @Test
    public void filtersUnderPerformanceFrame()
    {
        try
        {
            String[] options = {"Correct","Partially Correct","Incorrect", "Skipped", "Marked For Review"};
            String[] optionsInSecondDropDown = {};
            String[] optionsInThirdDropDown = {"Earliest First"};
            new LoginUsingLTI().ltiLogin("1275");
            new Navigator().NavigateTo("eTextbook");//navigate to etextbook
            new DiagnosticTest().startTest(2);
            new AttemptTest().Diagonestictest();
            List<WebElement> allElements = driver.findElements(By.xpath("//*[starts-with(@class, 'sbSelector')]"));

            for (WebElement element: allElements)
            {
                System.out.println(element.getText());

            }

            allElements.get(0).click();
            String dropvalues=null;
            Thread.sleep(5000);
            List<WebElement> allElementsfor1StDropDown = driver.findElements(By.xpath("//*[starts-with(@class, 'sbOptions')]"));
            for (WebElement element: allElementsfor1StDropDown) {
                if(element.getText().equals(""))
                    break;
                dropvalues = element.getText();

            }
            String [] valuesinarray = dropvalues.split("\n");


            if (options.length == valuesinarray.length)
            {
                for (int i = 0; i < options.length; i++)
                {
                    if (options[i].equals(valuesinarray[i]))
                    {
                        logger.log(Level.INFO,"1st Dropdown options are correct");
                    }
                    else
                    {
                        Assert.fail("3rd Dropdown options are incorrect");
                    }

                }
            }

            allElements.get(1).click();
            Thread.sleep(5000);
            String dropvalues1=null;
            List<WebElement> allElementsfor2ndDropDown = driver.findElements(By.xpath("//*[starts-with(@class, 'sbOptions')]"));
            int index = 0;
            for (WebElement element: allElementsfor2ndDropDown)
            {

                if(index == 1)
                {
                    dropvalues1 = element.getText();

                    break;

                }
                index++;

            }

            String [] valuesinarray1 = dropvalues1.split("\n");

            if (optionsInSecondDropDown.length == valuesinarray1.length)
            {
                for (int i = 0; i < optionsInSecondDropDown.length; i++)
                {
                    if (optionsInSecondDropDown[i].equals(valuesinarray1[i]))
                    {
                        logger.log(Level.INFO,"2nd Dropdown options are correct");
                    }
                    else
                    {
                        Assert.fail("2nd Dropdown options are incorrect");
                    }

                }
            }

            Thread.sleep(5000);
            allElements.get(2).click();
            String dropvalues2=null;
            Thread.sleep(5000);

            List<WebElement> allElementsfor3rdDropDown = driver.findElements(By.xpath("//*[starts-with(@class, 'sbOptions')]"));
            for (WebElement element: allElementsfor3rdDropDown) {

                dropvalues2 = element.getText();

            }
            String [] valuesinarray2 = dropvalues2.split("\n");

            if (optionsInThirdDropDown.length == valuesinarray2.length)
            {
                for (int i = 0; i < optionsInThirdDropDown.length; i++)
                {
                    if (optionsInThirdDropDown[i].equals(valuesinarray2[i]))
                    {
                        logger.log(Level.INFO,"3rd Dropdown options are correct");
                    }
                    else
                    {
                        Assert.fail("3rd Dropdown options are incorrect");
                    }

                }
            }


        }
        catch(Exception e)
        {
            Assert.fail("Exception in TestCase filtersUnderPerformanceFrame in class FiltersUnderPerformanceFrame",e);
        }
    }

}
