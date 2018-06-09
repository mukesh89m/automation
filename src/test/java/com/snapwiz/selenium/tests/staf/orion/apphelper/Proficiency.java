package com.snapwiz.selenium.tests.staf.orion.apphelper;



import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.snapwiz.selenium.tests.staf.orion.Driver;

public class Proficiency {

	public List<Integer> getProficiencyOfEachTLO()
	{
		List<Integer> profvalues = new ArrayList<Integer>();
		List<WebElement> profs = Driver.driver.findElements(By.className("al-proficiency-percentage"));
		for(WebElement prof : profs)
		{
			if(!prof.getText().equals(""))
			profvalues.add(Integer.parseInt(prof.getText().replace('%', ' ').trim()));
		}
		return profvalues;
	}
	
	public void isProficiencyImproved(List<Integer> newProficiencies,List<Integer> OldProficiencies)
	{
		for(int i=0;i<newProficiencies.size();i++)
	 		if(newProficiencies.get(i) <= OldProficiencies.get(i))
	 		{
	 			new Screenshot().captureScreenshotFromAppHelper();
	 			Assert.fail("Proficiency of TLO "+(i+1)+" is not improved as compared to the earlier proficiency");
	 		}
	}
	
	public void isProficiencyDeclined(List<Integer> newProficiencies,List<Integer> OldProficiencies)
	{
		for(int i=0;i<newProficiencies.size();i++)
	 		if(newProficiencies.get(i) >= OldProficiencies.get(i))
	 		{
	 			new Screenshot().captureScreenshotFromAppHelper();
	 			Assert.fail("Proficiency of TLO "+(i+1)+" is not declined as compared to the earlier proficiency");
	 		}
	}
	
	public void isProficiencySame(List<Integer> newProficiencies,List<Integer> OldProficiencies)
	{
		for(int i=0;i<newProficiencies.size();i++)
	 		if(newProficiencies.get(i) != OldProficiencies.get(i))
	 		{	
	 			new Screenshot().captureScreenshotFromAppHelper();
	 			Assert.fail("Proficiency of TLO "+(i+1)+" is not equal to the earlier proficiency");
	 		}
	}
}
