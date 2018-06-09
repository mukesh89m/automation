package com.snapwiz.selenium.tests.staf.learningspaces.apphelper;

import org.testng.Assert;

import java.sql.ResultSet;

/*
 * Created by Sumit on 8/5/2014.
 */
public class ValidateContentErrorInDB {

    public int rowCountBeforeReportingContentError()
    {
        int errorRowCount = 0;
        try
        {
            ResultSet rst = DBConnect.st.executeQuery("SELECT COUNT(*) as cnt FROM t_content_error;");
            rst.next();
            errorRowCount = rst.getInt("cnt");
            rst.close();
            System.out.print("From DB "+errorRowCount);
        }
        catch (Exception e)
        {
            Assert.fail("Exception in apphelper method rowCountBeforeReportingContentError in class ValidateContentErrorInDB.", e);
        }
        return errorRowCount;
    }
}
