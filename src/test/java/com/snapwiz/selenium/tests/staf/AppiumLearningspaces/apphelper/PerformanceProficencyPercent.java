package com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelper;

import com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelper.DBConnect;
import com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelper.DataFetchFromDataBase;
import org.testng.Assert;

import com.snapwiz.selenium.tests.staf.AppiumLearningspaces.ReadTestData;


public class PerformanceProficencyPercent
{

    public int performanceProficencyPercent(String dataindex )
    {
        double prof=0.0;
        try
        {
            String username=ReadTestData.readDataByTagName("", "user_id", dataindex);
            if(System.getProperty("UCHAR") == null) {
                username = username + com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelper.LoginUsingLTI.appendChar;
            }
            else {
                username = username + System.getProperty("UCHAR");
            }
            System.out.println(username);
            String classsectionnamename=ReadTestData.readDataByTagName("", "context_title", dataindex);
            System.out.println(classsectionnamename);
            String userquesry="select id from t_user where username=\'"+username+"\'";
            String section="SELECT id FROM t_class_section where name=\'"+classsectionnamename+"\'";
            int user=new DataFetchFromDataBase().userverification(1, userquesry);
            int classsection=new DataFetchFromDataBase().userverification(1, section);
            Double profs = DBConnect.getProficienciesFromMongo(user,classsection);
            //double profs1=profs.get(0);
            //double profs2=profs.get(1);
            Double totalecfs = DBConnect.getTotalECFfromMongo(user,classsection);
           // double ecf1=ecfs.get(0);
           // double ecf2=ecfs.get(1);
            System.out.println("Total ECF"+totalecfs); System.out.println("Total Prof "+(profs));
            //System.out.println("1 "+ecf1*profs1); System.out.println("2 "+ecf2*profs2); System.out.println("3 "+ecf1+ecf2);
            //System.out.println("Total ecf "+(ecf1+ecf2));
            prof =((profs)/(totalecfs))*100.0;
            System.out.println("prof db "+prof);
        }
        catch(Exception e)
        {
            Assert.fail("Exception in apphelper method performanceProficencyPercent",e);
        }
        return (int)(prof);
    }

    public double performanceProficencyPercentonetlo(String dataindex )
    {
        double prof=0.0;
        try
        {
          /*  String username=ReadTestData.readDataByTagName("", "user_id", dataindex);
            //System.out.println(username);
            String classsectionnamename=ReadTestData.readDataByTagName("", "context_title", dataindex);
            //System.out.println(classsectionnamename);
            String userquesry="select id from t_user where username=\'"+username+"\'";
            String section="SELECT id FROM t_class_section where name=\'"+classsectionnamename+"\'";
            int user=new DataFetchFromDataBase().userverification(1, userquesry);
            int classsection=new DataFetchFromDataBase().userverification(1, section);
            List<Double> profs = DBConnect.getProficienciesFromMongo(user,classsection);
            double profs1=profs.get(0);
            // double profs2=profs.get(1);
            List<Double> ecfs = DBConnect.getTotalECFfromMongo(user,classsection);
            double ecf1=ecfs.get(0);
            System.out.println("ECF "+ecf1);
            System.out.println("Prof "+profs1);
            //double ecf2=ecfs.get(1);
            double finalecf=(ecf1);
            double finalprof=profs1;
            prof =(finalprof /finalecf)*100;*/
        }
        catch(Exception e)
        {
            Assert.fail("Exception in apphelper method performanceProficencyPercent",e);
        }
        return prof;
    }
}
