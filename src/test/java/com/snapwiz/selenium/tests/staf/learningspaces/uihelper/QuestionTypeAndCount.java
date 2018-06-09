package com.snapwiz.selenium.tests.staf.learningspaces.uihelper;

import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.DBConnect;
import org.apache.commons.collections.CollectionUtils;
import org.testng.Assert;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by admin on 14-Oct-14.
 */
public class QuestionTypeAndCount {

    public void questionTypeCount(String query) throws Exception {
        List<String> questionType = new TextFetch().textfetchbylistclasswithoutindex("cms-QuestionsType-name");
        List<String> questionCounts = new TextFetch().textfetchbylistclasswithoutindex("cms-QuestionsTotal-count");
        List<String> allTypeNCount = new ArrayList<>();
        for (int itemCounter = 0; itemCounter < questionType.size(); itemCounter++) {
            String qCount = questionCounts.get(itemCounter);
            // allTypeNCount.add(qType+" "+qCount);
            allTypeNCount.add(qCount);
        }
        System.out.println("UI QuestionCount before sorting:" + allTypeNCount);
        Collections.sort(allTypeNCount);
        System.out.println("UI QuestionCount After sorting:" + allTypeNCount);


        new DBConnect().Connect();
        //String query="SELECT question_type,display_type,count(distinct question_version_id) FROM t_questions where created_by = 14 group by question_type;";
        ResultSet rst = DBConnect.st.executeQuery(query);
        List<String> DBQuestionCount = new ArrayList<>();
        while (rst.next()) {
            // System.out.println("display_type::"+rst.getString("display_type"));
            //System.out.println("display_type::"+rst.getString("count(distinct tq.question_version_id)"));
            DBQuestionCount.add(rst.getString("abc"));


        }
        Thread.sleep(5000);
        System.out.println("DB QuestionCount before sorting:" + DBQuestionCount);
        Collections.sort(DBQuestionCount);
        System.out.println("DB QuestionCount after sorting:" + DBQuestionCount);
        if(!(CollectionUtils.isEqualCollection(allTypeNCount, DBQuestionCount)))
            Assert.fail("the question count for each question type of current logged in user is not shown in DefaultViewOnSummaryPage");
    }
}
