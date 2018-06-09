package com.snapwiz.selenium.tests.staf.orion.apphelper;

import java.util.List;

import org.openqa.selenium.By;

import com.snapwiz.selenium.tests.staf.orion.Driver;

public class TLOInPracticeTestValidate {

	public boolean tloInPracticeTest(List<String> expectedtlos)
	{
		//TLO Names
				
				
		//String tlonamefound = Driver.driver.findElement(By.className("question-association-skill-id")).getAttribute("title");
		String tlonamefound = Driver.driver.findElement(By.className("al-diagtest-skills-evaluted-ul")).getText().substring(2);
		boolean tlofound = false;
		for(String expected : expectedtlos)
		{
			if(expected.trim().equals(tlonamefound.trim()))
			{
				tlofound = true;
				break;
			}			
		}
		if(tlofound == false)
			return false;
		else
			return true;
		
	}
	
	public List<Integer> updateAttemptedCount(List<String> tlonames,List<Integer> tloquestionattemptedcount)
	{
		//String tlonamefound = Driver.driver.findElement(By.className("question-association-skill-id")).getAttribute("title");
		String tlonamefound = Driver.driver.findElement(By.className("al-diagtest-skills-evaluted-ul")).getText().substring(2);
		int tloindex = 0;
		for(String tloinlist : tlonames)
		{
			if(tloinlist.equals(tlonamefound))
				break;
			tloindex++;
		}
		int tlotobeincremented = 0;
		if(tloindex == 0) tlotobeincremented = tloquestionattemptedcount.get(0);
		if(tloindex == 1) tlotobeincremented = tloquestionattemptedcount.get(1);
		if(tloindex == 2) tlotobeincremented = tloquestionattemptedcount.get(2);
		if(tloindex == 3) tlotobeincremented = tloquestionattemptedcount.get(3);
		if(tloindex == 4) tlotobeincremented = tloquestionattemptedcount.get(4);
		if(tloindex == 5) tlotobeincremented = tloquestionattemptedcount.get(5);
		if(tloindex == 6) tlotobeincremented = tloquestionattemptedcount.get(6);
		
		tloquestionattemptedcount.set(tloindex, tlotobeincremented+1);
		return tloquestionattemptedcount;
	}
}
