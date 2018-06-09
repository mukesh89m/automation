package com.snapwiz.selenium.tests.staf.microlearning.apphelper;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.lang.reflect.Field;

/**
 * Created by mukesh on 14/9/16.
 */
public class RobotHelper {

    public void sendKeyBoardKeys(String stringToSend) throws Exception
    {
        java.awt.Robot robot = new java.awt.Robot();

        // String texttoSend = "img.jpg";
        stringToSend = stringToSend.toUpperCase();
        char [] charArray = stringToSend.toCharArray();
        for(int i=0;i<charArray.length;i++)
        {

            if(charArray[i] == '.')
            {
                robot.keyPress(KeyEvent.VK_DECIMAL);
                robot.keyRelease(KeyEvent.VK_DECIMAL);
            }
            else if(charArray[i] == ':')
            {
                robot.keyPress(KeyEvent.VK_SHIFT);
                robot.keyPress(KeyEvent.VK_SEMICOLON);
                robot.keyRelease(KeyEvent.VK_SEMICOLON);
                robot.keyRelease(KeyEvent.VK_SHIFT);
            }
            else if(charArray[i] == '^')
            {
                robot.keyPress(KeyEvent.VK_ENTER);
                robot.keyRelease(KeyEvent.VK_ENTER);
            }
            else if(charArray[i] == ' ')
            {
                robot.keyPress(KeyEvent.VK_SPACE);
                robot.keyRelease(KeyEvent.VK_SPACE);
            }
            else if(charArray[i] == '\\')
            {
                robot.keyPress(KeyEvent.VK_BACK_SLASH);
                robot.keyRelease(KeyEvent.VK_BACK_SLASH);
            }
            else if(charArray[i] == '`')
            {
                robot.keyPress(KeyEvent.VK_TAB);
                robot.keyRelease(KeyEvent.VK_TAB);
            }
            else if(charArray[i] == '/')
            {
                robot.keyPress(KeyEvent.VK_SLASH);
                robot.keyRelease(KeyEvent.VK_SLASH);
            }
            else if(charArray[i] == '-')
            {
                robot.keyPress(KeyEvent.VK_MINUS);
                robot.keyRelease(KeyEvent.VK_MINUS);
            }
            else if(charArray[i] == '$')
            {
                robot.keyPress(KeyEvent.VK_CONTROL);
                robot.keyPress(KeyEvent.VK_A);
                robot.keyRelease(KeyEvent.VK_CONTROL);
                robot.keyRelease(KeyEvent.VK_A);
                robot.keyPress(KeyEvent.VK_CLEAR);
                robot.keyRelease(KeyEvent.VK_CLEAR);
            }
            else
            {
                String variableName = "VK_" + charArray[i];
                Class clazz = KeyEvent.class;
                Field field = clazz.getField( variableName );
                int keyCode = field.getInt(null);
                robot.keyPress( keyCode );
                robot.keyRelease( keyCode );
            }
            robot.delay(50);
        }
    }

}
