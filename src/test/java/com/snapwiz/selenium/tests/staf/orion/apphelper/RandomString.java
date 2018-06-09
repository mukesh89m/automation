package com.snapwiz.selenium.tests.staf.orion.apphelper;

import com.snapwiz.selenium.tests.staf.framework.utility.ReportUtil;

import java.util.Random;

public class RandomString {
	/*
	 * create random string
	 */
	 String dat1 = "";
	 String dat2 = "";
	 String dat3 = "";
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
        ReportUtil.log("Generating Random Text", "Random Text is generated", "info");
        return dat1+" "+dat2+" "+dat3;

	}


}

