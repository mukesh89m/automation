package com.snapwiz.selenium.tests.staf.datacreation.apphelper;

import org.testng.Assert;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

/**
 * Created by root on 17/10/14.
 */
public class                                                                                                                                                                                                                                                                                                    FileOperations {

    public static void main (String [] args)
    {
        try
        {
            File file = new File("/home/akansh/user.txt");

            // if file doesnt exists, then create it
            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter fw = new FileWriter(file.getAbsoluteFile(),true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write("hello");
            bw.close();
        }
        catch (Exception e)
        {
            Assert.fail("Exception while writing data to file",e);
        }
    }
}
