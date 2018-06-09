package com.snapwiz.selenium.tests.staf.datacreation.testcases;

import com.snapwiz.selenium.tests.staf.datacreation.Config;
import com.snapwiz.selenium.tests.staf.datacreation.Driver;
import com.snapwiz.selenium.tests.staf.datacreation.apphelper.DirectLogin;
import com.snapwiz.selenium.tests.staf.datacreation.uihelper.ComboBox;
import org.openqa.selenium.By;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by akansh on 4/11/14.
 */
public class QuestionMigration extends Driver {

    @Test
    public void questionMigrate()
    {
       // BufferedWriter bw = fileWrite("/home/akansh/coursemigration1.txt");

        List<String> courses = new ArrayList<>();
        int i=0;
        try
        {
            new DirectLogin().CMSLogin();
            driver.get(Config.baseURL+"/secure/migrateQuestionContent");

            courses.add("211");courses.add("212");courses.add("213");courses.add("217");courses.add("219");courses.add("220");
            courses.add("221");courses.add("222");courses.add("241");courses.add("242");courses.add("243");courses.add("244");
            courses.add("245");courses.add("246");courses.add("251");courses.add("254");courses.add("255");courses.add("256");
            courses.add("257");courses.add("258");courses.add("261");courses.add("262");courses.add("263");courses.add("264");
            courses.add("265");courses.add("266");courses.add("267");courses.add("268");courses.add("269");courses.add("270");
            courses.add("271");courses.add("272");courses.add("277");courses.add("281");courses.add("282");courses.add("284");
            courses.add("291");courses.add("292");courses.add("293");courses.add("294");courses.add("295");courses.add("297");
            courses.add("301");courses.add("302");courses.add("313");courses.add("314");courses.add("317");courses.add("318");
            courses.add("319");courses.add("320");courses.add("322");courses.add("323");courses.add("325");courses.add("329");
            courses.add("337");courses.add("340");courses.add("342");courses.add("343");courses.add("344");

            for(i = 0;i<courses.size();i++) {


                File file = new File("/home/akansh/tito.txt");

                // if file doesnt exists, then create it
                if (!file.exists()) {
                    file.createNewFile();
                }

                FileWriter fw = new FileWriter(file.getAbsoluteFile(),true);
                BufferedWriter bw = bw = new BufferedWriter(fw);

                new ComboBox().selectValue("course-drop-down", courses.get(i));
                driver.findElement(By.id("migrate-chapters")).click();

                long startTime = System.currentTimeMillis();
                bw.write("---------------------------------------------------------------------------------------------------");
                bw.newLine();
                bw.write("Course Id: "+courses.get(i)); bw.newLine();

                while(driver.findElements(By.id("loading-indicator")).size() > 0) {
                }
                bw.write(driver.findElement(By.id("question-migration-total-number-questions-toprocess")).getText());
                bw.newLine();
                bw.write(driver.findElement(By.id("question-migration-number-pending-questions")).getText());
                bw.newLine();
                //Thread.sleep(3000);
                try {
                    bw.write("Failed Questions "+driver.findElement(By.id("question-migration-failed-questionids")).getText());
                    System.out.println("Course id "+courses.get(i)+" Failed Questions "+driver.findElement(By.id("question-migration-failed-questionids")).getText());
                }
                catch (Exception e) {
                    bw.write("No question failed");
                    System.out.println("No question failed for course id " + courses.get(i));
                }
                long endTime = System.currentTimeMillis() - startTime;
                int seconds = (int) ((endTime / 1000) % 60);
                int minute = (int)((endTime / 1000) / 60);
                bw.newLine();


                bw.newLine();
                bw.write("Total time Elapsed: "+minute+ " Minutes and "+seconds+" Seconds");
                bw.newLine();
                bw.close();
            }
        }
        catch (Exception e)
        {

            System.out.println("Exception while migrating course "+e+ " course id "+courses.get(i));
        }

    }

}
