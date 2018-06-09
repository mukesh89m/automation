package com.snapwiz.selenium.tests.staf.orion.apphelper;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.snapwiz.selenium.tests.staf.orion.Driver;

public class Opacity {
	
	public List<Double> opacities()
	{
		List<WebElement> bars = new ArrayList<WebElement>();
		List<Double> values = new ArrayList<Double>();
		try
		{
		    bars = Driver.driver.findElements(By.className("al-proficiency-wrapper"));
			for(WebElement bar : bars)
			{
				String value = bar.getAttribute("style");
				value = value.substring(9);
				value = value.replaceAll(";", " ");
				value = value.trim();
				double val = Double.parseDouble(value);
				if(val != 1.0)
				values.add(val);
			}
		}
		catch(Exception e)
		{
			
		}
		return values;
	}

}
