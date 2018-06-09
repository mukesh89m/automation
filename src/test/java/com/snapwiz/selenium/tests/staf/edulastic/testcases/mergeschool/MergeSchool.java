package com.snapwiz.selenium.tests.staf.edulastic.testcases.mergeschool;

import com.snapwiz.selenium.tests.staf.edulastic.apphelpers.DBConnect;
import com.snapwiz.selenium.tests.staf.framework.controller.Driver;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.FileWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by pragyas on 06-05-2016.
 */
public class MergeSchool{

    static String srcInstitutionID = "66200";
    static String dstInstitutionID = "66200";


    public static void main(String[] arg) throws SQLException
    {
        DBConnect.Connect();
        sourceSchool();
        dstinationSchool();


    }

    public static void sourceSchool(){
        try{
            String sFileName = "C:\\Users\\pragyas\\Desktop\\testdata\\srcSchool.csv";

            ResultSet res = DBConnect.st.executeQuery("SELECT tcs.id,tu.id,tu.username,tur.type FROM t_dn_org_reference tdn,t_class_section_permission tcs,\n" +
                    "t_user tu,t_user_role tur where\n" +
                    "tdn.class_section_id = tcs.class_section_id and\n" +
                    "tcs.user_id = tu.id and\n" +
                    "tdn.institution_id = '"+srcInstitutionID+"' and\n" +
                    "tdn.class_section_id is not null and\n" +
                    "tu.id = tur.user_id\n" +
                    "order by tcs.class_section_id;");

            FileWriter writer = new FileWriter(sFileName);
            writer.append("TCSID"+"     "+"TUID"+"    "+"USERNAME"+"                            "+"TYPE");
            writer.append("\n");

            while (res.next()) {
                int tcsId = res.getInt(1);
                int tuId = res.getInt(2);
                String userName = res.getString(3);
                String type = res.getString(4);

                writer.append(tcsId+ "  " +tuId + "  " +userName + "                              " +type);
                writer.append("\n");
                writer.flush();
            }


            writer.append("\n");
            writer.append("\n");
            ResultSet zipRes = DBConnect.st.executeQuery("SELECT zip FROM t_address where id in(Select shipping_address_id from t_institution where id='"+srcInstitutionID+"') ;");
            writer.append("ZIPCODE");
            writer.append("\n");

            while (zipRes.next()) {
                int zipCode = zipRes.getInt(1);
                writer.append(zipCode+" ");
                writer.flush();
            }


            writer.append("\n");
            writer.append("\n");
            ResultSet assessemntRes = DBConnect.st.executeQuery("Select id ,name,created_by ,sharedWithIds from t_assessment where created_by in (SELECT distinct tu.id user_id FROM t_institution ti,t_user tu,t_user_role tur,t_user_org_reference tuorg,t_dn_org_reference tdn,t_class tc,t_class_section tcs,t_class_section_permission tcsp\n" +
                    "where ti.id=tdn.institution_id\n" +
                    "and tuorg.org_reference_id=tdn.id\n" +
                    "and tcs.id=tdn.class_section_id\n" +
                    "and tcs.id=tcsp.class_section_id\n" +
                    "and tu.id=tur.user_id\n" +
                    "and tc.id=tcs.class_id\n" +
                    "and tc.id=tdn.class_id\n" +
                    "and tdn.class_section_id is not null\n" +
                    "and ti.id in ('"+srcInstitutionID+"')) ;and tu.id = tuorg.user_id;");
            writer.append("ID"+" " +"Name"+ " "+ "Created_By"+ " "+ "SharedWithIds");
            writer.append("\n");

            while (assessemntRes.next()) {
                int id = assessemntRes.getInt(1);
                String name = assessemntRes.getString(2);
                String createdBy = assessemntRes.getString(3);
                String sharedWithIds = assessemntRes.getString(4);
                writer.append(id+ "  " +name + "  " +createdBy + "      " +sharedWithIds);
                writer.append("\n");
                writer.flush();
            }

        }catch (Exception e){
            Assert.fail("Exception while creating srcSchool.csv",e);
        }
    }


    public static void dstinationSchool(){
        try{
            String sFileName = "C:\\Users\\pragyas\\Desktop\\testdata\\dstSchool.csv";

            ResultSet res = DBConnect.st.executeQuery("SELECT tcs.id,tu.id,tu.username,tur.type FROM t_dn_org_reference tdn,t_class_section_permission tcs,\n" +
                    "t_user tu,t_user_role tur where\n" +
                    "tdn.class_section_id = tcs.class_section_id and\n" +
                    "tcs.user_id = tu.id and\n" +
                    "tdn.institution_id = '"+dstInstitutionID+"' and\n" +
                    "tdn.class_section_id is not null and\n" +
                    "tu.id = tur.user_id\n" +
                    "order by tcs.class_section_id;");

            FileWriter writer = new FileWriter(sFileName);
            writer.append("TCSID"+"     "+"TUID"+"    "+"USERNAME"+"                            "+"TYPE");
            writer.append("\n");

            while (res.next()) {
                int tcsId = res.getInt(1);
                int tuId = res.getInt(2);
                String userName = res.getString(3);
                String type = res.getString(4);

                writer.append(tcsId+ "  " +tuId + "  " +userName + "                              " +type);
                writer.append("\n");
                writer.flush();
            }
            writer.close();

        }catch (Exception e){
            Assert.fail("Exception while creating dstSchool.csv",e);

        }
    }

    }








