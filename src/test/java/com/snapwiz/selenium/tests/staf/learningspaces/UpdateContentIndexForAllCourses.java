package com.snapwiz.selenium.tests.staf.learningspaces;

import com.snapwiz.selenium.tests.staf.framework.controller.Driver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.util.Arrays;

/**
 * Created by shashank on 17-07-2015.
 */
public class UpdateContentIndexForAllCourses extends Driver {

    @Test
    public void updateIndexForAllCourses()
    {
        String courseID = System.getProperty("CourseID");
        String courses[]=courseID.split(",");
        System.out.println("Courses are:"+Arrays.toString(courses));
       // String arr[]={courseID/*, "250", "252", "253", "273", "283", "296", "298", "303", "305","307", "308", "309", "310", "312", "316"*/};

        try {
            new com.snapwiz.selenium.tests.staf.learningspaces.apphelper.UpdateContentIndex().updateContentIndexForAllCourses("222");
        }
        catch (Exception e)
        {
            Assert.fail("Exception while updating content index of solr", e);
        }
    }

    /**
     * This method will remove and add index for both content type and Entity type
     * @param /['RESOURCE','ASSESSMENT','LESSON','WIDGET','GLOSSARY','QUESTION']
     *
     */
    @Test
    public void updateIndexForParticularCourses()
    {
        String courseID = System.getProperty("CourseID");
        String courses[]=courseID.split(",");
        System.out.println("Courses are:"+Arrays.toString(courses));
        try {
            new com.snapwiz.selenium.tests.staf.learningspaces.apphelper.UpdateContentIndex().updateContentIndexForCoursesBothContentAndEntityType("222",courses);
        }
        catch (Exception e)
        {
            Assert.fail("Exception while updating content index of updateIndexForParticularCourses", e);
        }
    }

    /**
     * This method will remove and add index for specific content type
     * @param /['RESOURCE','ASSESSMENT','LESSON','WIDGET','GLOSSARY','QUESTION']
     *
     */

    @Test
    public void updateContentIndexForCoursesForContentType()
    {
        String courseID = System.getProperty("CourseID");
        String courses[]=courseID.split(",");
        System.out.println("Courses are:"+Arrays.toString(courses));
        try {
            new com.snapwiz.selenium.tests.staf.learningspaces.apphelper.UpdateContentIndex().updateContentIndexForCoursesForContentType("222",courses);
        }
        catch (Exception e)
        {
            Assert.fail("Exception while updating content index of updateContentIndexForCoursesForContentType", e);
        }
    }
    /**
     * This method will remove and add index for all content and entity type
     * @param -null
     *
     */
    @Test
    public void updateContentIndexForCoursesForAllContentAndEntityType()
    {
        String courseID = System.getProperty("CourseID");
        String courses[]=courseID.split(",");
        System.out.println("Courses are:"+Arrays.toString(courses));
        try {
            new com.snapwiz.selenium.tests.staf.learningspaces.apphelper.UpdateContentIndex().updateContentIndexForCoursesForAllContentAndEntityType("222",courses);
        }
        catch (Exception e)
        {
            Assert.fail("Exception while updating content index of updateContentIndexForCoursesForAllContentAndEntityType", e);
        }
    }
}
