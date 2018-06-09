package com.snapwiz.selenium.tests.staf.learningspaces.testcases.anonymous;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.DirectLogin;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.KeysSend;

public class FileUpload extends Driver {
	
	  private static Logger logger = Logger.getLogger("com.snapwiz.selenium.tests.staf.learningspaces.testcases.anonymous.FileUpload");
	  
	  
	@Test
	  public void UploadFile() throws Exception
	  {
		  logger.log(Level.INFO,"Starting Execution of Testcase");
		  new DirectLogin().directLogin("0");
		  ((JavascriptExecutor) driver).executeScript("arguments[0].click();",driver.findElement(By.xpath("/html/body/div[3]/div/div/div/div/ul/li[3]/a")));
		  Thread.sleep(5000);
		  ((JavascriptExecutor) driver).executeScript("arguments[0].click();",driver.findElement(By.xpath("//*[@id='file-upload-button']")));
		  
		  Thread.sleep(5000);
		  new KeysSend().sendKeyBoardKeys("/home/akansh/logo.png");
		  Robot robot = new Robot();
		
		  robot.keyPress(KeyEvent.VK_ENTER);
		  robot.keyRelease(KeyEvent.VK_ENTER);
		 
		  Thread.sleep(30000);
		  WebElement btn=(WebElement)((JavascriptExecutor) driver).executeScript("return arguments[0];",driver.findElement(By.xpath("//*[@id='post-submit-button']")));
		  btn.click();		  
		  Thread.sleep(30000);
	  }
	/*
	 public void type(CharSequence characters) throws Exception{
		 Robot robot = new Robot();
	        int length = characters.length();
	        for (int i = 0; i < length; i++) {
	            char character = characters.charAt(i);
	            type(character,robot);
	        }
	    }

	    public void type(char character, Robot robot) {
	        switch (character) {
	        case 'a': robot.keyPress(KeyEvent.VK_A); break;
	        case 'b': robot.keyPress(KeyEvent.VK_B); break;
	        case 'c': robot.keyPress(KeyEvent.VK_C); break;
	        case 'd': robot.keyPress(KeyEvent.VK_D); break;
	        case 'e': robot.keyPress(KeyEvent.VK_E); break;
	        case 'f': robot.keyPress(KeyEvent.VK_F); break;
	        case 'g': robot.keyPress(KeyEvent.VK_G); break;
	        case 'h': robot.keyPress(KeyEvent.VK_H); break;
	        case 'i': robot.keyPress(KeyEvent.VK_I); break;
	        case 'j': robot.keyPress(KeyEvent.VK_J); break;
	        case 'k': robot.keyPress(KeyEvent.VK_K); break;
	        case 'l': robot.keyPress(KeyEvent.VK_L); break;
	        case 'm': robot.keyPress(KeyEvent.VK_M); break;
	        case 'n': robot.keyPress(KeyEvent.VK_N); break;
	        case 'o': robot.keyPress(KeyEvent.VK_O); break;
	        case 'p': robot.keyPress(KeyEvent.VK_P); break;
	        case 'q': robot.keyPress(KeyEvent.VK_Q); break;
	        case 'r': robot.keyPress(KeyEvent.VK_R); break;
	        case 's': robot.keyPress(KeyEvent.VK_S); break;
	        case 't': robot.keyPress(KeyEvent.VK_T); break;
	        case 'u': robot.keyPress(KeyEvent.VK_U); break;
	        case 'v': robot.keyPress(KeyEvent.VK_V); break;
	        case 'w': robot.keyPress(KeyEvent.VK_W); break;
	        case 'x': robot.keyPress(KeyEvent.VK_X); break;
	        case 'y': robot.keyPress(KeyEvent.VK_Y); break;
	        case 'z': robot.keyPress(KeyEvent.VK_Z); break;
	        case 'A': robot.keyPress(KeyEvent.VK_SHIFT, KeyEvent.VK_A); break;
	        case 'B': robot.keyPress(KeyEvent.VK_SHIFT, VK_B); break;
	        case 'C': robot.keyPress(KeyEvent.VK_SHIFT, VK_C); break;
	        case 'D': robot.keyPress(KeyEvent.VK_SHIFT, VK_D); break;
	        case 'E': robot.keyPress(KeyEvent.VK_SHIFT, VK_E); break;
	        case 'F': robot.keyPress(KeyEvent.VK_SHIFT, VK_F); break;
	        case 'G': robot.keyPress(KeyEvent.VK_SHIFT, VK_G); break;
	        case 'H': robot.keyPress(KeyEvent.VK_SHIFT, VK_H); break;
	        case 'I': robot.keyPress(KeyEvent.VK_SHIFT, VK_I); break;
	        case 'J': robot.keyPress(KeyEvent.VK_SHIFT, VK_J); break;
	        case 'K': robot.keyPress(KeyEvent.VK_SHIFT, VK_K); break;
	        case 'L': robot.keyPress(KeyEvent.VK_SHIFT, VK_L); break;
	        case 'M': robot.keyPress(KeyEvent.VK_SHIFT, VK_M); break;
	        case 'N': robot.keyPress(KeyEvent.VK_SHIFT, VK_N); break;
	        case 'O': robot.keyPress(KeyEvent.VK_SHIFT, VK_O); break;
	        case 'P': robot.keyPress(KeyEvent.VK_SHIFT, VK_P); break;
	        case 'Q': robot.keyPress(KeyEvent.VK_SHIFT, VK_Q); break;
	        case 'R': robot.keyPress(KeyEvent.VK_SHIFT, VK_R); break;
	        case 'S': robot.keyPress(KeyEvent.VK_SHIFT, VK_S); break;
	        case 'T': robot.keyPress(KeyEvent.VK_SHIFT, VK_T); break;
	        case 'U': robot.keyPress(KeyEvent.VK_SHIFT, VK_U); break;
	        case 'V': robot.keyPress(KeyEvent.VK_SHIFT, VK_V); break;
	        case 'W': robot.keyPress(KeyEvent.VK_SHIFT, VK_W); break;
	        case 'X': robot.keyPress(KeyEvent.VK_SHIFT, VK_X); break;
	        case 'Y': robot.keyPress(KeyEvent.VK_SHIFT, VK_Y); break;
	        case 'Z': robot.keyPress(KeyEvent.VK_SHIFT, VK_Z); break;
	        case '`': robot.keyPress(KeyEvent.VK_BACK_QUOTE); break;
	        case '0': robot.keyPress(KeyEvent.VK_0); break;
	        case '1': robot.keyPress(KeyEvent.VK_1); break;
	        case '2': robot.keyPress(KeyEvent.VK_2); break;
	        case '3': robot.keyPress(KeyEvent.VK_3); break;
	        case '4': robot.keyPress(KeyEvent.VK_4); break;
	        case '5': robot.keyPress(KeyEvent.VK_5); break;
	        case '6': robot.keyPress(KeyEvent.VK_6); break;
	        case '7': robot.keyPress(KeyEvent.VK_7); break;
	        case '8': robot.keyPress(KeyEvent.VK_8); break;
	        case '9': robot.keyPress(KeyEvent.VK_9); break;
	        case '-': robot.keyPress(KeyEvent.VK_MINUS); break;
	        case '=': robot.keyPress(KeyEvent.VK_EQUALS); break;
	        case '~': robot.keyPress(KeyEvent.VK_SHIFT, VK_BACK_QUOTE); break;
	        case '!': robot.keyPress(KeyEvent.VK_EXCLAMATION_MARK); break;
	        case '@': robot.keyPress(KeyEvent.VK_AT); break;
	        case '#': robot.keyPress(KeyEvent.VK_NUMBER_SIGN); break;
	        case '$': robot.keyPress(KeyEvent.VK_DOLLAR); break;
	        case '%': robot.keyPress(KeyEvent.VK_SHIFT, VK_5); break;
	        case '^': robot.keyPress(KeyEvent.VK_CIRCUMFLEX); break;
	        case '&': robot.keyPress(KeyEvent.VK_AMPERSAND); break;
	        case '*': robot.keyPress(KeyEvent.VK_ASTERISK); break;
	        case '(': robot.keyPress(KeyEvent.VK_LEFT_PARENTHESIS); break;
	        case ')': robot.keyPress(KeyEvent.VK_RIGHT_PARENTHESIS); break;
	        case '_': robot.keyPress(KeyEvent.VK_UNDERSCORE); break;
	        case '+': robot.keyPress(KeyEvent.VK_PLUS); break;
	        case '\t': robot.keyPress(KeyEvent.VK_TAB); break;
	        case '\n': robot.keyPress(KeyEvent.VK_ENTER); break;
	        case '[': robot.keyPress(KeyEvent.VK_OPEN_BRACKET); break;
	        case ']': robot.keyPress(KeyEvent.VK_CLOSE_BRACKET); break;
	        case '\\': robot.keyPress(KeyEvent.VK_BACK_SLASH); break;
	        case '{': robot.keyPress(KeyEvent.VK_SHIFT, VK_OPEN_BRACKET); break;
	        case '}': robot.keyPress(KeyEvent.VK_SHIFT, VK_CLOSE_BRACKET); break;
	        case '|': robot.keyPress(KeyEvent.VK_SHIFT, VK_BACK_SLASH); break;
	        case ';': robot.keyPress(KeyEvent.VK_SEMICOLON); break;
	        case ':': robot.keyPress(KeyEvent.VK_COLON); break;
	        case '\'': robot.keyPress(KeyEvent.VK_QUOTE); break;
	        case '"': robot.keyPress(KeyEvent.VK_QUOTEDBL); break;
	        case ',': robot.keyPress(KeyEvent.VK_COMMA); break;
	        case '<': robot.keyPress(KeyEvent.VK_LESS); break;
	        case '.': robot.keyPress(KeyEvent.VK_PERIOD); break;
	        case '>': robot.keyPress(KeyEvent.VK_GREATER); break;
	        case '/': robot.keyPress(KeyEvent.VK_SLASH); break;
	        case '?': robot.keyPress(KeyEvent.VK_SHIFT, VK_SLASH); break;
	        case ' ': robot.keyPress(KeyEvent.VK_SPACE); break;
	        default:
	            throw new IllegalArgumentException("Cannot type character " + character);
	        } 
	    } */

}
