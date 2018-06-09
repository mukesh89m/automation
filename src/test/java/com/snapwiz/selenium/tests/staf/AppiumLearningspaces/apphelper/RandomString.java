package com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelper;

import java.util.Random;

public class RandomString {
	/*
	 * create random string
	 */
	 String dat1 = "",dat2 = "",dat3 = "",dat4 = "",dat5 = "",dat6 = "",dat7 = "",dat8 = "";
	public String randomstring(int length)
	{
		Random ran=new Random();
		char data = ' ';
		
		

		for (int i=0; i<=length; i++) {
			data = (char)(ran.nextInt(25)+97);
		
			dat1 = data + dat1;
	}
		
		for (int i=0; i<=length; i++) {
			data = (char)(ran.nextInt(25)+97);
		
			dat2 = data + dat2;
	}
		
		for (int i=0; i<=length; i++) {
			data = (char)(ran.nextInt(25)+97);
		
			dat3 = data + dat3;
	}
		
		for (int i=0; i<=length; i++) {
			data = (char)(ran.nextInt(25)+97);
		
			dat4 = data + dat4;
	}
		
		for (int i=0; i<=length; i++) {
			data = (char)(ran.nextInt(25)+97);
		
			dat5 = data + dat5;
	}
		
		for (int i=0; i<=length; i++) {
			data = (char)(ran.nextInt(25)+97);
		
			dat6 = data + dat6;			
			
	}
		
		for (int i=0; i<=length; i++) {
			data = (char)(ran.nextInt(25)+97);
		
			dat7 = data + dat7;			
			
	}
		for (int i=0; i<=length; i++) {
			data = (char)(ran.nextInt(25)+97);
		
			dat8 = data + dat8;			
			
	}
		return dat1+" "+dat2+" "+dat3+" "+dat4+" "+dat5+" "+dat6+" "+dat7+" "+dat8;
	
	}
	
	
}

