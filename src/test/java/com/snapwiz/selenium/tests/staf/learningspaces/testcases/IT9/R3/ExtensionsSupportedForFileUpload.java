package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT9.R3;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.FileUpload;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.TextFetch;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Created by Sumit on 8/25/2014.
 */
public class ExtensionsSupportedForFileUpload extends Driver{

    @Test(priority = 1, enabled = true)
    public void uploadPptFile()
    {
        try
        {
            new LoginUsingLTI().ltiLogin("254");//login as instructor
            new Navigator().NavigateTo("Course Stream");
            new FileUpload().fileUpload("254", true);//upload a ppt file of size less than 1 MB
            String file;
            file = new TextFetch().textfetchbyclass("ls-stream-post__action");
            System.out.println("file: "+file);
            if(!file.contains("shared a file"))
                Assert.fail("Instructor is Unable to upload a PPT file of size less than 1 MB.");

            new LoginUsingLTI().ltiLogin("254_1");//login as instructor
            new Navigator().NavigateTo("Course Stream");
            new FileUpload().fileUpload("254_1", true);//upload a ppt file of size greater than 1 MB
            Thread.sleep(15000);
            file = new TextFetch().textfetchbyclass("ls-stream-post__action");
            System.out.println("file: "+file);
            if(!file.contains("shared a file"))
                Assert.fail("Instructor is Unable to upload a PPT file of size grater than 1 MB.");

            new LoginUsingLTI().ltiLogin("254_2");//login as instructor
            new Navigator().NavigateTo("Course Stream");
            new FileUpload().fileUpload("254_2", true);//upload a ppt file of size greater than 25 MB
            int entry = driver.findElements(By.className("ls-stream-post__action")).size();
            System.out.println("notice: "+entry);
            if(entry != 0)//there will be no entry if the upload fails
                Assert.fail("Instructor is able to upload a PPT file of size greater than 25 MB.");

            new LoginUsingLTI().ltiLogin("254_3");//login as student
            new Navigator().NavigateTo("Course Stream");
            new FileUpload().fileUpload("254_3", true);//upload a ppt file of size less than 1 MB
            file = new TextFetch().textfetchbyclass("ls-stream-post__action");
            System.out.println("file: "+file);
            if(!file.contains("shared a file"))
                Assert.fail("Student is Unable to upload a PPT file of size less than 1 MB.");

            new LoginUsingLTI().ltiLogin("254_4");//login as student
            new Navigator().NavigateTo("Course Stream");
            new FileUpload().fileUpload("254_4", true);//upload a ppt file of size greater than 1 MB
            Thread.sleep(15000);
            file = new TextFetch().textfetchbyclass("ls-stream-post__action");
            System.out.println("file: "+file);
            if(!file.contains("shared a file"))
                Assert.fail("Student is Unable to upload a PPT file of size grater than 1 MB.");

            new LoginUsingLTI().ltiLogin("254_5");//login as student
            new Navigator().NavigateTo("Course Stream");
            new FileUpload().fileUpload("254_5", true);//upload a ppt file of size greater than 10 MB
            int entry1 = driver.findElements(By.className("ls-stream-post__action")).size();
            System.out.println("entry1: "+entry1);
            if(entry1 != 0)//there will be no entry if the upload fails
                Assert.fail("Student is able to upload a PPT file of size greater than 10 MB.");
        }
        catch (Exception e)
        {
            Assert.fail("Exception TestCase uploadPptFile in class ExtensionsSupportedForFileUpload", e);
        }
    }
    @Test(priority = 2, enabled = true)
    public void uploadPptxFile()
    {
        try
        {
            new LoginUsingLTI().ltiLogin("255");//login as instructor
            new Navigator().NavigateTo("Course Stream");
            new FileUpload().fileUpload("255", true);//upload a PPTX file of size less than 1 MB
            String file;
            file = new TextFetch().textfetchbyclass("ls-stream-post__action");
            System.out.println("file: "+file);
            if(!file.contains("shared a file"))
                Assert.fail("Instructor is Unable to upload a PPTX file of size less than 1 MB.");

            new LoginUsingLTI().ltiLogin("255_1");//login as instructor
            new Navigator().NavigateTo("Course Stream");
            new FileUpload().fileUpload("255_1", true);//upload a PPTX file of size greater than 1 MB
            Thread.sleep(15000);
            file = new TextFetch().textfetchbyclass("ls-stream-post__action");
            System.out.println("file: "+file);
            if(!file.contains("shared a file"))
                Assert.fail("Instructor is Unable to upload a PPTX file of size grater than 1 MB.");

            new LoginUsingLTI().ltiLogin("255_2");//login as instructor
            new Navigator().NavigateTo("Course Stream");
            new FileUpload().fileUpload("255_2", true);//upload a PPTX file of size greater than 25 MB
            int entry = driver.findElements(By.className("ls-stream-post__action")).size();
            System.out.println("notice: "+entry);
            if(entry != 0)//there will be no entry if the upload fails
                Assert.fail("Instructor is able to upload a PPTX file of size greater than 25 MB.");

            new LoginUsingLTI().ltiLogin("255_3");//login as student
            new Navigator().NavigateTo("Course Stream");
            new FileUpload().fileUpload("255_3", true);//upload a PPTX file of size less than 1 MB
            file = new TextFetch().textfetchbyclass("ls-stream-post__action");
            System.out.println("file: "+file);
            if(!file.contains("shared a file"))
                Assert.fail("Student is Unable to upload a PPTX file of size less than 1 MB.");

            new LoginUsingLTI().ltiLogin("255_4");//login as student
            new Navigator().NavigateTo("Course Stream");
            new FileUpload().fileUpload("255_4", true);//upload a PPTX file of size greater than 1 MB
            Thread.sleep(15000);
            file = new TextFetch().textfetchbyclass("ls-stream-post__action");
            System.out.println("file: "+file);
            if(!file.contains("shared a file"))
                Assert.fail("Student is Unable to upload a PPTX file of size grater than 1 MB.");

            new LoginUsingLTI().ltiLogin("255_5");//login as student
            new Navigator().NavigateTo("Course Stream");
            new FileUpload().fileUpload("255_5", true);//upload a PPTX file of size greater than 10 MB
            int entry1 = driver.findElements(By.className("ls-stream-post__action")).size();
            System.out.println("entry1: "+entry1);
            if(entry1 != 0)//there will be no entry if the upload fails
                Assert.fail("Student is able to upload a PPTX file of size greater than 10 MB.");
        }
        catch (Exception e)
        {
            Assert.fail("Exception TestCase uploadPptxFile in class ExtensionsSupportedForFileUpload", e);
        }
    }
    @Test(priority = 3, enabled = true)
    public void uploadDocFile()
    {
        try
        {
            new LoginUsingLTI().ltiLogin("256");//login as instructor
            new Navigator().NavigateTo("Course Stream");
            new FileUpload().fileUpload("256", true);//upload a DOC file of size less than 1 MB
            String file;
            file = new TextFetch().textfetchbyclass("ls-stream-post__action");
            System.out.println("file: "+file);
            if(!file.contains("shared a file"))
                Assert.fail("Instructor is Unable to upload a DOC file of size less than 1 MB.");

            new LoginUsingLTI().ltiLogin("256_1");//login as instructor
            new Navigator().NavigateTo("Course Stream");
            new FileUpload().fileUpload("256_1", true);//upload a DOC file of size greater than 1 MB
            Thread.sleep(20000);
            file = new TextFetch().textfetchbyclass("ls-stream-post__action");
            System.out.println("file: "+file);
            if(!file.contains("shared a file"))
                Assert.fail("Instructor is Unable to upload a DOC file of size grater than 1 MB.");

            new LoginUsingLTI().ltiLogin("256_2");//login as instructor
            new Navigator().NavigateTo("Course Stream");
            new FileUpload().fileUpload("256_2", true);//upload a DOC file of size greater than 25 MB
            int entry = driver.findElements(By.className("ls-stream-post__action")).size();
            System.out.println("notice: "+entry);
            if(entry != 0)//there will be no entry if the upload fails
                Assert.fail("Instructor is able to upload a DOC file of size greater than 25 MB.");

            new LoginUsingLTI().ltiLogin("256_3");//login as student
            new Navigator().NavigateTo("Course Stream");
            new FileUpload().fileUpload("256_3", true);//upload a DOC file of size less than 1 MB
            file = new TextFetch().textfetchbyclass("ls-stream-post__action");
            System.out.println("file: "+file);
            if(!file.contains("shared a file"))
                Assert.fail("Student is Unable to upload a DOC file of size less than 1 MB.");

            new LoginUsingLTI().ltiLogin("256_4");//login as student
            new Navigator().NavigateTo("Course Stream");
            new FileUpload().fileUpload("256_4", true);//upload a DOC file of size greater than 1 MB
            Thread.sleep(15000);
            file = new TextFetch().textfetchbyclass("ls-stream-post__action");
            System.out.println("file: "+file);
            if(!file.contains("shared a file"))
                Assert.fail("Student is Unable to upload a DOC file of size grater than 1 MB.");

            new LoginUsingLTI().ltiLogin("256_5");//login as student
            new Navigator().NavigateTo("Course Stream");
            new FileUpload().fileUpload("256_5", true);//upload a DOC file of size greater than 10 MB
            int entry1 = driver.findElements(By.className("ls-stream-post__action")).size();
            System.out.println("entry1: "+entry1);
            if(entry1 != 0)//there will be no entry if the upload fails
                Assert.fail("Student is able to upload a DOC file of size greater than 10 MB.");


        }
        catch (Exception e)
        {
            Assert.fail("Exception TestCase uploadDocFile in class ExtensionsSupportedForFileUpload", e);
        }
    }
    @Test(priority = 4, enabled = true)
    public void uploadDocxFile()
    {
        try
        {
            new LoginUsingLTI().ltiLogin("257");//login as instructor
            new Navigator().NavigateTo("Course Stream");
            new FileUpload().fileUpload("257", true);//upload a DOCX file of size less than 1 MB
            String file;
            file = new TextFetch().textfetchbyclass("ls-stream-post__action");
            System.out.println("file: "+file);
            if(!file.contains("shared a file"))
                Assert.fail("Instructor is Unable to upload a DOCX file of size less than 1 MB.");

            new LoginUsingLTI().ltiLogin("257_1");//login as instructor
            new Navigator().NavigateTo("Course Stream");
            new FileUpload().fileUpload("257_1", true);//upload a DOCX file of size greater than 1 MB
            Thread.sleep(25000);
            driver.navigate().refresh();
            file = new TextFetch().textfetchbyclass("ls-stream-post__action");
            System.out.println("file: "+file);
            if(!file.contains("shared a file"))
                Assert.fail("Instructor is Unable to upload a DOCX file of size grater than 1 MB.");

            new LoginUsingLTI().ltiLogin("257_2");//login as instructor
            new Navigator().NavigateTo("Course Stream");
            new FileUpload().fileUpload("257_2", true);//upload a DOCX file of size greater than 25 MB
            int entry = driver.findElements(By.className("ls-stream-post__action")).size();
            System.out.println("notice: "+entry);
            if(entry != 0)//there will be no entry if the upload fails
                Assert.fail("Instructor is able to upload a DOCX file of size greater than 25 MB.");

            new LoginUsingLTI().ltiLogin("257_3");//login as student
            new Navigator().NavigateTo("Course Stream");
            new FileUpload().fileUpload("257_3", true);//upload a DOCX file of size less than 1 MB
            file = new TextFetch().textfetchbyclass("ls-stream-post__action");
            System.out.println("file: "+file);
            if(!file.contains("shared a file"))
                Assert.fail("Student is Unable to upload a DOCX file of size less than 1 MB.");

            new LoginUsingLTI().ltiLogin("257_4");//login as student
            new Navigator().NavigateTo("Course Stream");
            new FileUpload().fileUpload("257_4", true);//upload a DOCX file of size greater than 1 MB
            Thread.sleep(25000);
            file = new TextFetch().textfetchbyclass("ls-stream-post__action");
            System.out.println("file: "+file);
            if(!file.contains("shared a file"))
                Assert.fail("Student is Unable to upload a DOCX file of size grater than 1 MB.");

            new LoginUsingLTI().ltiLogin("257_5");//login as student
            new Navigator().NavigateTo("Course Stream");
            new FileUpload().fileUpload("257_5", true);//upload a DOCX file of size greater than 10 MB
            int entry1 = driver.findElements(By.className("ls-stream-post__action")).size();
            System.out.println("entry1: "+entry1);
            if(entry1 != 0)//there will be no entry if the upload fails
                Assert.fail("Student is able to upload a DOCX file of size greater than 10 MB.");

        }
        catch (Exception e)
        {
            Assert.fail("Exception TestCase uploadDocxFile in class ExtensionsSupportedForFileUpload", e);
        }
    }


    @Test(priority = 5, enabled = true)
    public void uploadPdfFile()
    {
        try
        {
            new LoginUsingLTI().ltiLogin("258");//login as instructor
            new Navigator().NavigateTo("Course Stream");
            new FileUpload().fileUpload("258", true);//upload a PDF file of size less than 1 MB
            String file;
            file = new TextFetch().textfetchbyclass("ls-stream-post__action");
            System.out.println("file: "+file);
            if(!file.contains("shared a file"))
                Assert.fail("Instructor is Unable to upload a PDF file of size less than 1 MB.");

            new LoginUsingLTI().ltiLogin("258_1");//login as instructor
            new Navigator().NavigateTo("Course Stream");
            new FileUpload().fileUpload("258_1", true);//upload a PDF file of size greater than 1 MB
            Thread.sleep(25000);
            List<WebElement> all = driver.findElements(By.className("ls-stream-post__action"));
            for(WebElement l: all)
            {
                System.out.println("-->"+l.getText());
            }
            file = new TextFetch().textfetchbyclass("ls-stream-post__action");
            System.out.println("file: "+file);
            if(!file.contains("shared a file"))
                Assert.fail("Instructor is Unable to upload a PDF file of size grater than 1 MB.");

            new LoginUsingLTI().ltiLogin("258_2");//login as instructor
            new Navigator().NavigateTo("Course Stream");
            new FileUpload().fileUpload("258_2", true);//upload a PDF file of size greater than 25 MB
            int entry = driver.findElements(By.className("ls-stream-post__action")).size();
            System.out.println("notice: "+entry);
            if(entry != 0)//there will be no entry if the upload fails
                Assert.fail("Instructor is able to upload a PDF file of size greater than 25 MB.");

            new LoginUsingLTI().ltiLogin("258_3");//login as student
            new Navigator().NavigateTo("Course Stream");
            new FileUpload().fileUpload("258_3", true);//upload a PDF file of size less than 1 MB
            file = new TextFetch().textfetchbyclass("ls-stream-post__action");
            System.out.println("file: "+file);
            if(!file.contains("shared a file"))
                Assert.fail("Student is Unable to upload a PDF file of size less than 1 MB.");

            new LoginUsingLTI().ltiLogin("258_4");//login as student
            new Navigator().NavigateTo("Course Stream");
            new FileUpload().fileUpload("258_4", true);//upload a PDF file of size greater than 1 MB
            Thread.sleep(25000);
            file = new TextFetch().textfetchbyclass("ls-stream-post__action");
            System.out.println("file: "+file);
            if(!file.contains("shared a file"))
                Assert.fail("Student is Unable to upload a PDF file of size grater than 1 MB.");

            new LoginUsingLTI().ltiLogin("258_5");//login as student
            new Navigator().NavigateTo("Course Stream");
            new FileUpload().fileUpload("258_5", true);//upload a PDF file of size greater than 10 MB
            int entry1 = driver.findElements(By.className("ls-stream-post__action")).size();
            System.out.println("entry1: "+entry1);
            if(entry1 != 0)//there will be no entry if the upload fails
                Assert.fail("Student is able to upload a PDF file of size greater than 10 MB.");

        }
        catch (Exception e)
        {
            Assert.fail("Exception TestCase uploadPdfFile in class ExtensionsSupportedForFileUpload", e);
        }
    }
    @Test(priority = 6, enabled = true)
    public void uploadMpFourFile()
    {
        try
        {
            new LoginUsingLTI().ltiLogin("259");//login as instructor
            new Navigator().NavigateTo("Course Stream");
            new FileUpload().fileUpload("259", true);//upload a MP4 file of size less than 1 MB
            String file;
            file = new TextFetch().textfetchbyclass("ls-stream-post__action");
            System.out.println("file: "+file);
            if(!file.contains("shared a file"))
                Assert.fail("Instructor is Unable to upload a MP4 file of size less than 1 MB.");

            new LoginUsingLTI().ltiLogin("259_1");//login as instructor
            new Navigator().NavigateTo("Course Stream");
            new FileUpload().fileUpload("259_1", true);//upload a MP4 file of size greater than 1 MB
            Thread.sleep(25000);
            List<WebElement> all = driver.findElements(By.className("ls-stream-post__action"));
            for(WebElement l: all)
            {
                System.out.println("-->"+l.getText());
            }
            file = new TextFetch().textfetchbyclass("ls-stream-post__action");
            System.out.println("file: "+file);
            if(!file.contains("shared a file"))
                Assert.fail("Instructor is Unable to upload a MP4 file of size grater than 1 MB.");

            new LoginUsingLTI().ltiLogin("259_2");//login as instructor
            new Navigator().NavigateTo("Course Stream");
            new FileUpload().fileUpload("259_2", true);//upload a MP4 file of size greater than 25 MB
            int entry = driver.findElements(By.className("ls-stream-post__action")).size();
            System.out.println("notice: "+entry);
            if(entry != 0)//there will be no entry if the upload fails
                Assert.fail("Instructor is able to upload a MP4 file of size greater than 25 MB.");

            new LoginUsingLTI().ltiLogin("259_3");//login as student
            new Navigator().NavigateTo("Course Stream");
            new FileUpload().fileUpload("259_3", true);//upload a MP4 file of size less than 1 MB
            file = new TextFetch().textfetchbyclass("ls-stream-post__action");
            System.out.println("file: "+file);
            if(!file.contains("shared a file"))
                Assert.fail("Student is Unable to upload a MP4 file of size less than 1 MB.");

            new LoginUsingLTI().ltiLogin("259_4");//login as student
            new Navigator().NavigateTo("Course Stream");
            new FileUpload().fileUpload("259_4", true);//upload a MP4 file of size greater than 1 MB
            Thread.sleep(25000);
            file = new TextFetch().textfetchbyclass("ls-stream-post__action");
            System.out.println("file: "+file);
            if(!file.contains("shared a file"))
                Assert.fail("Student is Unable to upload a MP4 file of size grater than 1 MB.");

            new LoginUsingLTI().ltiLogin("259_5");//login as student
            new Navigator().NavigateTo("Course Stream");
            new FileUpload().fileUpload("259_5", true);//upload a MP4 file of size greater than 10 MB
            int entry1 = driver.findElements(By.className("ls-stream-post__action")).size();
            System.out.println("entry1: "+entry1);
            if(entry1 != 0)//there will be no entry if the upload fails
                Assert.fail("Student is able to upload a MP4 file of size greater than 10 MB.");

        }
        catch (Exception e)
        {
            Assert.fail("Exception TestCase uploadMpFourFile in class ExtensionsSupportedForFileUpload", e);
        }
    }
    @Test(priority = 7, enabled = false)//test data not available 10MB and 25MB files
    public void uploadMpThreeFile()
    {
        try
        {
            new LoginUsingLTI().ltiLogin("260");//login as instructor
            new Navigator().NavigateTo("Course Stream");
            new FileUpload().fileUpload("260", true);//upload a MP3 file of size less than 1 MB
            String file;
            file = new TextFetch().textfetchbyclass("ls-stream-post__action");
            System.out.println("file: "+file);
            if(!file.contains("shared a file"))
                Assert.fail("Instructor is Unable to upload a MP3 file of size less than 1 MB.");

            new LoginUsingLTI().ltiLogin("260_1");//login as instructor
            new Navigator().NavigateTo("Course Stream");
            new FileUpload().fileUpload("260_1", true);//upload a MP3 file of size greater than 1 MB
            Thread.sleep(25000);
            List<WebElement> all = driver.findElements(By.className("ls-stream-post__action"));
            for(WebElement l: all)
            {
                System.out.println("-->"+l.getText());
            }
            file = new TextFetch().textfetchbyclass("ls-stream-post__action");
            System.out.println("file: "+file);
            if(!file.contains("shared a file"))
                Assert.fail("Instructor is Unable to upload a MP3 file of size grater than 1 MB.");

            new LoginUsingLTI().ltiLogin("260_2");//login as instructor
            new Navigator().NavigateTo("Course Stream");
            new FileUpload().fileUpload("260_2", true);//upload a MP3 file of size greater than 25 MB
            int entry = driver.findElements(By.className("ls-stream-post__action")).size();
            System.out.println("notice: "+entry);
            if(entry != 0)//there will be no entry if the upload fails
                Assert.fail("Instructor is able to upload a MP3 file of size greater than 25 MB.");

            new LoginUsingLTI().ltiLogin("260_3");//login as student
            new Navigator().NavigateTo("Course Stream");
            new FileUpload().fileUpload("260_3", true);//upload a MP3 file of size less than 1 MB
            file = new TextFetch().textfetchbyclass("ls-stream-post__action");
            System.out.println("file: "+file);
            if(!file.contains("shared a file"))
                Assert.fail("Student is Unable to upload a MP3 file of size less than 1 MB.");

            new LoginUsingLTI().ltiLogin("260_4");//login as student
            new Navigator().NavigateTo("Course Stream");
            new FileUpload().fileUpload("260_4", true);//upload a MP3 file of size greater than 1 MB
            Thread.sleep(25000);
            file = new TextFetch().textfetchbyclass("ls-stream-post__action");
            System.out.println("file: "+file);
            if(!file.contains("shared a file"))
                Assert.fail("Student is Unable to upload a MP3 file of size grater than 1 MB.");

            new LoginUsingLTI().ltiLogin("260_5");//login as student
            new Navigator().NavigateTo("Course Stream");
            new FileUpload().fileUpload("260_5", true);//upload a MP3 file of size greater than 10 MB
            int entry1 = driver.findElements(By.className("ls-stream-post__action")).size();
            System.out.println("entry1: "+entry1);
            if(entry1 != 0)//there will be no entry if the upload fails
                Assert.fail("Student is able to upload a MP3 file of size greater than 10 MB.");

        }
        catch (Exception e)
        {
            Assert.fail("Exception TestCase uploadMpThreeFile in class ExtensionsSupportedForFileUpload", e);
        }
    }

}

