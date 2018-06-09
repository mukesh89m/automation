package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT10.R14;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Assignment;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.ResourseCreate;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.ComboBox;

public class InstructorAbleToFilterAllAssignableResources extends Driver
{
	/*
	 * Testcases R14.1270
	 */
	@Test
	public void instructorAbleToFilterAllAssignableResources()
	{
		try
		{
			String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", "1913");
			String resoursename = ReadTestData.readDataByTagName("", "resoursename", "1913");
			new Assignment().createAssignmentAtTopicLevel(1913);
			new ResourseCreate().resourseCreate(1913,0);
			new LoginUsingLTI().ltiLogin("1913");
			new Navigator().NavigateTo("My Resources");
			List<WebElement> allfilter =  driver.findElements(By.cssSelector("span[class='hide-filter-text']"));
			if(!allfilter.get(0).getText().equals("- Hide Filters"))
			{
				Assert.fail("+ View Filters didn't get updated to \"- Hide Filters\".");
			}
			allfilter.get(0).click();
			List<WebElement> viewfilter =  driver.findElements(By.id("view-resource-filter-button"));
			//Clicking 'View Filter'
			viewfilter.get(0).click();
			//Checking for filter option in 'All Resource' tab

			//Listing the dropdown options
			List<WebElement> allElements = driver.findElements(By.xpath("/*//*[starts-with(@id, 'sbSelector_')]"));

			for(WebElement str:allElements){

				System.out.println(str.getText());
			}
			String secondDropdown = allElements.get(2).getText();
			String thirdDropdown = allElements.get(3).getText();
			if(!secondDropdown.equals("All Chapters") || !thirdDropdown.equals("All Sections"))
			{
				Assert.fail("All The DropDown for filter is not present");
			}
			//clicking on 'All Chapters' Dropdown
			allElements.get(2).click();
			Thread.sleep(3000);
			List<WebElement> allElementsOfallChapterDropDownOptions = driver.findElements(By.xpath("/*//*[starts-with(@class, 'sbOptions')]"));
			String allChapterDropDownOptions = allElementsOfallChapterDropDownOptions.get(2).getText().replaceAll("[\n\r]", "");
			if(!allChapterDropDownOptions.contains("Ch 1:") || !allChapterDropDownOptions.contains("Ch 2:"))
			{
				Assert.fail("All chapter names are not present under 'All Chapter'");
			}


			new Navigator().NavigateTo("All Resources");

			List<WebElement> allElements3 = driver.findElements(By.xpath("//*[starts-with(@id, 'sbSelector_')]"));

			for(WebElement str:allElements3){

				System.out.println(str.getText());
				System.out.println("hello");
			}
			allElements3.get(4).click();
			//Selecting a chapter from 'All Chapters' Dropdown
			driver.findElement(By.xpath("//a[starts-with(@title, 'Ch 1:')]")).click(); //selecting first chapter
		    Thread.sleep(3000);
		    
		    List<String> stringarray = new ArrayList<String>();
		    List<WebElement> allResourcesAfterFilter = driver.findElements(By.xpath("//*[starts-with(@class, 'resource-title')]"));
			for(WebElement element : allResourcesAfterFilter)
			{
				stringarray.add(element.getText());
			}
			String [] assessmentpresent = stringarray.toArray(new String[stringarray.size()]);
			//Finding the assessment name after filtration
			boolean contains2 = false;
			for(int i =0 ; i<assessmentpresent.length; i++)
			{
				String str = assessmentpresent[i];
				if (str.contains(assessmentname)) {
					contains2 = true;
			        break; 
			    }
			}
			//Finding the resource name after filtration which is at chapter level
			boolean contains5 = false;
			for(int i =0 ; i<assessmentpresent.length; i++)
			{
				String str = assessmentpresent[i];
				if (str.contains(resoursename)) {
					contains5 = true;
			        break; 
			    }
			}
			/*if(contains2 == false || contains5 == false)
			{
				Assert.fail("Assignable resources doesn't get filtered based on chapter selected.");
			}*/
		  //clicking on 'All Section' Dropdown
		    List<WebElement> allElements1 = driver.findElements(By.xpath("//*[starts-with(@id, 'sbSelector_')]"));
		    allElements1.get(5).click();
		    Thread.sleep(3000);
		    stringarray.clear();
		    List<WebElement> allElementsOfAllSectionDropdown = driver.findElements(By.xpath("//*[starts-with(@class, 'sbOptions')]"));
			for(WebElement element: allElementsOfAllSectionDropdown)
			{
				stringarray.add(element.getText());
			}
			String [] optionspresent = stringarray.toArray(new String[stringarray.size()]);
			boolean contains = false;
			for(int i =0 ; i<optionspresent.length; i++)
			{
				String str = optionspresent[i];
				if (str.contains("1.1:")) {
			        contains = true;
			        break; 
			    }
			}
			if(contains == false)
			{
				Assert.fail("All topics for the specific chapter didnt get populated in the “All Sections” dropdown. ");
			}
			//Select a sub-topic from 'All Section'
			driver.findElement(By.xpath("//a[starts-with(@title, '1.1:')]")).click(); //selecting first chapter			Thread.sleep(3000);
			stringarray.clear();
			/*List<WebElement> allElements2 = driver.findElements(By.xpath("/*//*[starts-with(@id, 'sbSelector_')]"));
			allElements2.get(8).click();
			Thread.sleep(3000);*/
			//Listing elements in 'All Learning Objectives' Dropdown
			
			//stringarray.clear();
			//Listing all resources after filter has been done
			List<WebElement> allResourcesAfterTopicFilter = driver.findElements(By.xpath("//*[starts-with(@class, 'resource-title')]"));
			for(WebElement element : allResourcesAfterTopicFilter)
			{
				stringarray.add(element.getText());
			}
			String [] assessmentpresent1 = stringarray.toArray(new String[stringarray.size()]);
			//Finding the assessment name after filtration
			boolean contains3 = false;
			for(int i =0 ; i<assessmentpresent1.length; i++)
			{
				
				String str = assessmentpresent1[i];
				if (str.contains(assessmentname)) {
					contains3 = true;
			        break; 
			    }
			}
			//Finding the resource name after filtration which is at chapter level
			boolean contains6 = false;
			for(int i =0 ; i<assessmentpresent1.length; i++)
			{
				String str = assessmentpresent1[i];
				if (str.contains(resoursename)) {
					contains6 = true;
			        break; 
			    }
			}
			/*if(contains3 == false || contains6 == true)
			{
				Assert.fail("Assignable resources doesn't get filtered based on topic selected.");
			}*/
			//Reset all selected filters
			new ComboBox().selectValue(4, "All Chapters");
			stringarray.clear();
			((JavascriptExecutor)driver).executeScript("window.scrollTo(0,5000)");
			Thread.sleep(2000);
			((JavascriptExecutor)driver).executeScript("window.scrollTo(0,5000)");
			//Listing all resources after filter has been done
			List<WebElement> allResourcesAfterFilterReset = driver.findElements(By.xpath("//*[starts-with(@class, 'ls-assessment-item-title')]"));
			for(WebElement element : allResourcesAfterFilterReset)
			{
				stringarray.add(element.getText());
				Thread.sleep(1000);
			}
			System.out.println(stringarray);
			String [] assessmentpresent2 = stringarray.toArray(new String[stringarray.size()]);
			System.out.println("Contains4::"+ Arrays.toString(assessmentpresent2));
			System.out.println("assessmentname:::"+assessmentname);
			//Finding the assessment name after filtration
			boolean contains4 = false;
			for(int i =0 ; i<assessmentpresent2.length; i++)
			{
				
				String str = assessmentpresent2[i];
				if (str.contains(assessmentname)) {
					contains4 = true;
			        break;
			    }
			}
			//Finding the resource name after filtration which is at chapter level
			boolean contains7 = false;
			for(int i =0 ; i<assessmentpresent2.length; i++)
			{
				String str = assessmentpresent2[i];
				if (str.contains(resoursename)) {
					contains7 = true;
			        break; 
			    }
			}
			System.out.println(contains4); System.out.println(contains7);
			if(contains4 == true || contains7 == false)
			{
				Assert.fail("All the resources are not shown after reset of all filters.");
			}
			
		}
		catch(Exception e)
		{
			Assert.fail("Exception in instructorAbleToFilterAllAssignableResources in class InstructorAbleToFilterAllAssignableResources",e);
		}
	}

}
