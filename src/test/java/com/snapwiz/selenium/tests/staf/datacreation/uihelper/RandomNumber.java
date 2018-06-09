package com.snapwiz.selenium.tests.staf.datacreation.uihelper;

public class RandomNumber {

	public int generateRandomNumber(int lowerLimit, int upperLimit)
	{
		return  (int)(Math.random() * (upperLimit - lowerLimit)) + lowerLimit;
	}
}
