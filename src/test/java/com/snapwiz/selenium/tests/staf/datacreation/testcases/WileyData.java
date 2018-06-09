package com.snapwiz.selenium.tests.staf.datacreation.testcases;

import com.mongodb.DBCollection;
import com.snapwiz.selenium.tests.staf.datacreation.Config;
import com.snapwiz.selenium.tests.staf.datacreation.Driver;
import com.snapwiz.selenium.tests.staf.datacreation.ReadTestData;
import com.snapwiz.selenium.tests.staf.datacreation.apphelper.DBConnect;
import com.snapwiz.selenium.tests.staf.datacreation.apphelper.TDESEncryptionUtils;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.util.*;


public class WileyData extends Driver {
    int failureCount = 0;
    int totalCount = 0;
    String getStringOrEmpty(Object o)
    {
        if (o!=null){
            if(o instanceof Double)
               return (Math.round((Double)o)) + "";
            else
             return o.toString();
        }
        return "";
    }

    @Test
    public void run() {

        new TDESEncryptionUtils(436792765l);


        try {
            DBCollection collection = DBConnect.mongoConnect("DeltaAssessmentDetails");
            List<Map> mongoData = DBConnect.getDataFromMongo(collection);
            UserData userData = null;
            UserData userData_prev = new UserData();
            int progressCount = 0;
            totalCount = mongoData.size();

            for (Map map : mongoData) {

                    try {
                        progressCount++;
                        userData = new UserData();
                        userData.user_external_id = getStringOrEmpty(map.get("user_external_id"));
                        userData.user_first_name = getStringOrEmpty(map.get("user_first_name"));
                        userData.user_last_name = getStringOrEmpty(map.get("user_last_name"));
                        userData.user_id = getStringOrEmpty(map.get("user_id"));
                        userData.domain_external_id = getStringOrEmpty(map.get("domain_external_id"));
                        userData.domain_id = getStringOrEmpty(map.get("domain_id"));
                        userData.class_sec_external_id = getStringOrEmpty(map.get("class_sec_external_id"));
                        userData.class_sec_id = getStringOrEmpty(map.get("class_sec_id"));
                        userData.class_sec_name = getStringOrEmpty(map.get("class_sec_name"));
                        userData.user_role = getStringOrEmpty(map.get("user_role"));
                        userData.uaId = getStringOrEmpty(((Map) map.get("uaId")).get("$oid"));
                        userData.UserId = getStringOrEmpty(map.get("UserId"));
                        userData.CourseId = getStringOrEmpty(map.get("CourseId"));
                        userData.ClassSectionId = getStringOrEmpty(map.get("ClassSectionId"));
                        userData.ChapterId = getStringOrEmpty(map.get("ChapterId"));
                        userData.TLOId = getStringOrEmpty(map.get("TLOId"));
                        userData.AssessmentType = getStringOrEmpty(map.get("AssessmentType"));
                        userData.Progress_Status = getStringOrEmpty(map.get("ProgressStatus"));
                        userData.Assessment_Id = getStringOrEmpty(map.get("AssessmentId"));
                        userData.TotalQs = getStringOrEmpty(map.get("TotalQs"));
                        userData.TotalCorrectQs = getStringOrEmpty(map.get("TotalCorrectQs"));
                        userData.TotalPartiallyCorrectQs = getStringOrEmpty(map.get("TotalPartiallyCorrectQs"));
                        userData.TotalIncorrectQs = getStringOrEmpty(map.get("TotalIncorrectQs"));
                        userData.TotalSkippedQs = getStringOrEmpty(map.get("TotalSkippedQs"));
                        userData.QuestionId = getStringOrEmpty(map.get("QuestionId"));
                        userData.qSetId = getStringOrEmpty(map.get("qSetId"));
                        userData.algovar = getStringOrEmpty(map.get("algovar"));
                        userData.acIds = getStringOrEmpty(map.get("acIds"));
                        userData.ac1 = getStringOrEmpty(map.get("ac1"));
                        userData.ac2 = getStringOrEmpty(map.get("ac2"));
                        userData.ac3 = getStringOrEmpty(map.get("ac3"));
                        userData.ac4 = getStringOrEmpty(map.get("ac4"));
                        userData.ac5 = getStringOrEmpty(map.get("ac5"));
                        userData.ac6 = getStringOrEmpty(map.get("ac6"));
                        userData.ac7 = getStringOrEmpty(map.get("ac7"));
                        userData.ac8 = getStringOrEmpty(map.get("ac8"));
                        userData.ac9 = getStringOrEmpty(map.get("ac9"));
                        userData.ac10 = getStringOrEmpty(map.get("ac10"));
                        userData.ac11 = getStringOrEmpty(map.get("ac11"));
                        userData.ac12 = getStringOrEmpty(map.get("ac12"));
                        userData.cf = getStringOrEmpty(map.get("cf"));
                        userData.ta1 = getStringOrEmpty(map.get("ta1"));
                        userData.ta2= getStringOrEmpty(map.get("ta2"));
                        userData.ta3 = getStringOrEmpty(map.get("ta3"));
                        userData.ta4 = getStringOrEmpty(map.get("ta4"));
                        userData.ta5 = getStringOrEmpty(map.get("ta5"));
                        userData.ta6 = getStringOrEmpty(map.get("ta6"));
                        userData.ta7 = getStringOrEmpty(map.get("ta7"));
                        userData.pos = getStringOrEmpty(map.get("pos"));
                        userData.dl = getStringOrEmpty(map.get("dl"));
                        userData.qp = getStringOrEmpty(map.get("qp"));
                        userData.email = getStringOrEmpty(map.get("user_email"));
                        userData.external_course_id = getStringOrEmpty(map.get("course_external_id"));
                        userData.isHintViewed = getStringOrEmpty(map.get("isHintViewed"));
                        userData.timeTaken = getStringOrEmpty(map.get("timeTaken"));
                        userData.diagCf = getStringOrEmpty(map.get("diaf_cf"));
                        userData.qDl = getStringOrEmpty(map.get("q_diff_level"));
                        userData.mfr = getStringOrEmpty(map.get("markForReview"));
                        userData.custom_lis_result_source_id = getStringOrEmpty(map.get("custom_lis_result_source_id"));
                        userData.lis_outcome_service_url =   Config.lis_outcome_service_url+"class_sec_external_id="+getStringOrEmpty(map.get("class_sec_external_id"))+"&user_external_id="+getStringOrEmpty(map.get("user_external_id"));     //getStringOrEmpty(map.get("lis_outcome_service_url"));
                        //userData.lis_outcome_service_url =  Config.lis_outcome_service_url;
                        userData.mongoObjectId = getStringOrEmpty(((Map) map.get("_id")).get("$oid"));

                }
                catch (Exception e)
                {
                    System.out.println("IndexoutofBoundsException "+(userData));
                }

                //Handle marking complete or skipped for both diag and practice - for prev record.
                if (!userData.uaId.equals(userData_prev.uaId)) {
                    endOrCompleteAssessmentSession(userData_prev);
                }

                //System.out.println("Data from mongo "+userData);
                //=========================
                if((!userData.user_external_id.equals(userData_prev.user_external_id)) || (!userData.external_course_id.equals(userData_prev.external_course_id)) ) {
                    driver.get(Config.baseURL + "/logout");
                  boolean loginSuccess =  login(userData.user_first_name, userData.user_last_name, userData.user_external_id, userData.class_sec_external_id, userData.class_sec_name, "1", userData.domain_external_id, userData.email, userData.external_course_id,userData.custom_lis_result_source_id,userData.lis_outcome_service_url);
                    if(!loginSuccess) {
                        System.out.println("Error while logging in for userdata "+userData);
                        userData_prev = userData;
                        failureCount++;
                        continue;
                    }
                }


                if (userData.AssessmentType.equals("1")) {

                    if (!userData.uaId.equals(userData_prev.uaId)) {
                        String uahId = beginOrContinueDiagTest(userData.ChapterId, userData.diagCf);
                        if(uahId == null) {
                            System.out.println("Error UahId is null for diagnostic test for user "+userData);
                            userData_prev = userData;
                            failureCount++;
                            continue;
                        }
                        userData.currentuahId = uahId;
                    } else if(userData_prev.currentuahId !=null){

                        userData.currentuahId = userData_prev.currentuahId;
                    }
                    if(userData.currentuahId != null) {
                        processDiagTestQuestions(userData.Assessment_Id, userData.Progress_Status, userData.ChapterId, userData.currentuahId, userData.algovar,
                                userData.acIds, userData.cf, userData.QuestionId, userData.qDl, userData.dl, userData.isHintViewed, userData.pos, userData.qp, userData.timeTaken,
                                userData.TotalQs, userData.getUserAnswers(), userData.qSetId, userData.isTextAns(), Boolean.parseBoolean(userData.mfr),userData.user_id,userData.class_sec_id);
                    }


                } else if (userData.AssessmentType.equals("2")) {

                    if (!userData.uaId.equals(userData_prev.uaId)) {
                        String uahId =  beginOrContinuePracticeTest(userData.ChapterId, userData.TLOId);
                        if(uahId == null) {
                            System.out.println("Error UahId is null for practice test for user " + userData);
                            failureCount++;
                        }
                        userData.currentuahId = uahId;
                    }else if(userData_prev.currentuahId !=null) {

                        userData.currentuahId = userData_prev.currentuahId;
                    }
                    if(userData.currentuahId != null) {
                        processPracTestQuestions(userData.Assessment_Id, userData.Progress_Status, userData.ChapterId, userData.currentuahId, userData.algovar,
                                userData.acIds, userData.cf, userData.QuestionId, userData.qDl, userData.dl, userData.isHintViewed, userData.pos, userData.qp, userData.timeTaken,
                                userData.TotalQs, userData.getUserAnswers(), userData.TLOId, userData.qSetId, userData.isTextAns(), Boolean.parseBoolean(userData.mfr),userData.user_id,userData.class_sec_id);
                    }


                }
                userData_prev = userData;
                if((progressCount % 100) == 0)
                    System.out.println("Completed "+progressCount+ " of "+ totalCount);
                //===========================
            }
            endOrCompleteAssessmentSession(userData_prev);
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Total Records to be processed "+totalCount);
        System.out.println("Failed Recods "+failureCount);
    }
    public void endOrCompleteAssessmentSession(UserData userData){
        if(userData.currentuahId == null){
            return;
        }
        if ("1".equals(userData.AssessmentType) && "SKIPPED".equals(userData.Progress_Status)) {
            quitDiagTest(userData.Assessment_Id, userData.ChapterId, userData.currentuahId);
        } else if ("2".equals(userData.AssessmentType) && "COMPLETED".equals(userData.Progress_Status)) {
            endPracTest(userData.ChapterId, userData.TLOId, userData.currentuahId);
        } else if ("2".equals(userData.AssessmentType) && "STARTED".equals(userData.Progress_Status)) {
            forceSyncGradeBookForPractice(userData.ChapterId, userData.TLOId);
        }
    }
    public boolean login(String firstname,String lastname,String userid,String contextId, String contextTitle,String dataIndex, String custom_domainid,String email,String external_course_id, String custom_lis_result_source_id, String lis_outcome_service_url)
    {
        String custom_destination = ReadTestData.readDataByTagName("", "custom_destination", dataIndex);
        String resource_link_id = ReadTestData.readDataByTagName("", "resource_link_id", dataIndex);
        try
        {
            driver.get(Config.baseLTIURL + "/");
            driver.findElement(By.name("endpoint")).clear(); //Clear fields
            driver.findElement(By.name("endpoint")).sendKeys(Config.launchURL);

            driver.findElement(By.name("key")).clear();
            driver.findElement(By.name("key")).sendKeys(Config.customerkey);

            driver.findElement(By.name("secret")).clear();
            driver.findElement(By.name("secret")).sendKeys(Config.secretkey);

            driver.findElement(By.name("resource_link_id")).clear();

            if(resource_link_id == null)
                driver.findElement(By.name("resource_link_id")).sendKeys(Config.resourselinkid);
            else
                driver.findElement(By.name("resource_link_id")).sendKeys(resource_link_id);


            driver.findElement(By.name("user_id")).clear();
            driver.findElement(By.name("user_id")).sendKeys(userid);

            driver.findElement(By.name("roles")).clear();
            driver.findElement(By.name("roles")).sendKeys("student");

            driver.findElement(By.name("lis_person_name_family")).clear();
            driver.findElement(By.name("lis_person_name_family")).sendKeys(lastname);

            driver.findElement(By.name("lis_person_name_given")).clear();
            driver.findElement(By.name("lis_person_name_given")).sendKeys(firstname);

            driver.findElement(By.name("lis_person_contact_email_primary")).clear();
            driver.findElement(By.name("lis_person_contact_email_primary")).sendKeys(email);

            driver.findElement(By.name("context_id")).clear();
            driver.findElement(By.name("context_id")).sendKeys(contextId); //context id

            driver.findElement(By.name("context_title")).clear();
            driver.findElement(By.name("context_title")).sendKeys(contextTitle.replace("\t"," ")); //context title

            driver.findElement(By.name("tool_consumer_instance_guid")).clear();
            driver.findElement(By.name("tool_consumer_instance_guid")).sendKeys(Config.instance_guid);

            driver.findElement(By.name("tool_consumer_instance_name")).clear();
            driver.findElement(By.name("tool_consumer_instance_name")).sendKeys(Config.instance_name);

            driver.findElement(By.name("custom_courseid")).clear();
            driver.findElement(By.name("custom_courseid")).sendKeys(external_course_id);

            //driver.findElement(By.name("custom_domain_name")).sendKeys(Config.custom_domain_name); //domain name

            driver.findElement(By.name("custom_destination")).clear();

            if(custom_destination == null)
                driver.findElement(By.name("custom_destination")).sendKeys(Config.custom_destination);
            else
                driver.findElement(By.name("custom_destination")).sendKeys(custom_destination);

            driver.findElement(By.name("custom_domainid")).clear();
            driver.findElement(By.name("custom_domainid")).sendKeys(custom_domainid); //domain id

            driver.findElement(By.name("custom_lis_result_source_id")).clear();
            driver.findElement(By.name("custom_lis_result_source_id")).sendKeys(custom_lis_result_source_id);

            driver.findElement(By.name("lis_outcome_service_url")).clear();
            driver.findElement(By.name("lis_outcome_service_url")).sendKeys(lis_outcome_service_url);


            driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();
            driver.findElement(By.cssSelector("form > input[type=\"submit\"]")).click();
            String courseId = "";
            String userType = "";
            try {
                courseId = driver.findElement(By.id("cid")).getAttribute("value");
                userType = driver.findElement(By.id("userType")).getAttribute("value");
            }
            catch (Exception e)
            {

            }
            if(StringUtils.isEmpty(courseId) || StringUtils.isEmpty(userType))
                return false;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return true;

    }

    public String beginOrContinueDiagTest(String chapterId, String confidenceLevel)
    {
        if(confidenceLevel == null) {
            confidenceLevel = "";
        }


        String encChapterId = TDESEncryptionUtils.encryptLong(new Long(chapterId));
        String uahId = null;
        String no_data_found = null;
        try {

            driver.get(Config.baseURL + "/secure/diagnosticTest?ajax=true&chapterId="+encChapterId+"&confidenceLevel="+confidenceLevel); //continue diagnostic test
            try {
                no_data_found = driver.findElement(By.xpath("/html/body")).getText();
            }
            catch (Exception e)
            {

            }

            if("no_questions_found".equals(no_data_found)) {
                System.out.println("Error No Data Found for Diag Test for chapter id - "+chapterId);
            }
            else {
                try {
                    WebElement assessmentIdElement = (new WebDriverWait(driver, 5))
                            .until(ExpectedConditions.presenceOfElementLocated(By.id("userAssessmentId")));
                    uahId = assessmentIdElement.getAttribute("value");
                }
                catch (Exception e) {
                    System.out.println("Error - assessmentIdElement not found by selenium  for chapter id - "+chapterId);
                }
            }

        }
        catch (Exception e)
        {
            System.out.println("Error in beginOrContinueDiagTest chapterId "+chapterId);
        }
        return uahId;
    }

    public void quitDiagTest(String assessmentId, String chapterId,String userAssessmentHistoryId)
    {
        try
        {
            String encAssessmentId = TDESEncryptionUtils.encryptLong(new Long(assessmentId));
            String encChapterId = TDESEncryptionUtils.encryptLong(new Long(chapterId));
            driver.get(Config.baseURL+"/secure/quitALDiagTest?aid="+encAssessmentId+"&ajax=true&chapterId="+encChapterId+"&userAssessmentId=" + userAssessmentHistoryId); //quit diagnostic test
        }
        catch (Exception e)
        {
            System.out.println("Error in quitting diag test Assessment id "+assessmentId+" Chapter Id "+chapterId+"userAssessmentHistoryId "+userAssessmentHistoryId);
        }
    }


    public void processDiagTestQuestions(String assessmentId, String assessmentStatus, String chapterId,String userAssessmentHistoryId,String algoVariables
            ,String answerChoiceIds,String confidenceFactor,String currentQId,String diffculty, String displayLabel, String isHintViewed, String position, String questionPoints,
             String timeTaken, String totalQs, String [] userAnswers, String qSetId, Boolean isTextAns, Boolean mfr, String userId, String classSectionId)
    {
        StringBuffer encUserAnswers = new StringBuffer();
        if(!isTextAns) {
            for (String uAnswer : userAnswers) {
                if (!StringUtils.isEmpty(uAnswer))
                    encUserAnswers.append(TDESEncryptionUtils.encryptLong(new Long(uAnswer)) + "_");
            }
            if(!StringUtils.isEmpty(encUserAnswers)) {
                encUserAnswers.deleteCharAt(encUserAnswers.length() - 1);
            }
        }else{
            encUserAnswers.append(userAnswers[0]);
        }


        String enctotalQs = TDESEncryptionUtils.encryptInteger(new Integer(totalQs));
        String encPosition = TDESEncryptionUtils.encryptInteger(new Integer(position));
        String encQSetId = TDESEncryptionUtils.encryptLong(new Long(qSetId));
        String encCurrentQId = TDESEncryptionUtils.encryptLong(new Long(currentQId));
        String encChapterId = TDESEncryptionUtils.encryptLong(new Long(chapterId));
        String encAssessmentId = TDESEncryptionUtils.encryptLong(new Long(assessmentId));
        StringBuffer encAnswerChoiceIds = new StringBuffer();

        answerChoiceIds = answerChoiceIds.replaceAll("\"", "");
        answerChoiceIds = answerChoiceIds.replaceAll("\\|", ",");
        if(!StringUtils.isEmpty(answerChoiceIds)) {
            answerChoiceIds = answerChoiceIds.substring(0, answerChoiceIds.length() - 1);
        }

        if(answerChoiceIds != null && answerChoiceIds.contains(",")) {
        String [] listAnswerChoiceIds = answerChoiceIds.split(",");

            for(String answerChoiceId : listAnswerChoiceIds) {
                if(!StringUtils.isEmpty(answerChoiceId))
                    encAnswerChoiceIds.append(TDESEncryptionUtils.encryptLong(new Long(answerChoiceId)) + ",");
            }
            if(!StringUtils.isEmpty(encAnswerChoiceIds)) {
                encAnswerChoiceIds.deleteCharAt(encAnswerChoiceIds.length() - 1);
            }
        }
        try
        {
    driver.get(Config.baseURL+"/secure/getNextDiagQuestion?" +
            "aid="+encAssessmentId+"" +
            "&ajax=true" +
            "&algoVariables=" +algoVariables+
            "&answerChoiceIds="+encAnswerChoiceIds+"" +
            "&autoGrade=true" +
            "&chapterId="+encChapterId+"" +
            "&markForReview="+mfr+"" +
            "&qSetId="+encQSetId+"" +
            "&confidenceFactor="+confidenceFactor+"" +
            "&currentQId="+encCurrentQId+"" +
            "&diagnosticTestSideBarStatus=SHOW" +
            "&difficulty="+diffculty+"" +
            "&displayLabel="+displayLabel+"" +
            "&isHintViewed="+isHintViewed+"" +
            "&position="+encPosition+"" +
            "&questionPoints="+questionPoints+"" +
            "&timeTaken="+timeTaken+"" +
            "&totalQs="+enctotalQs+"" +
            "&userAnswer="+encUserAnswers+"" +
            "&userAssessmentId=" + userAssessmentHistoryId);


            try {
                if (driver.findElement(By.tagName("img")).getAttribute("src").equals("/webresources/images/app/whoopsie.png")) {
                    System.out.println("Error Woopsie on chapterId " + chapterId + " currentQId " + currentQId+ "userid "+userId + "class_section_id "+classSectionId + "Assessmeent id "+assessmentId);
                    failureCount++;
                }
            }
            catch (Exception e)
            {

            }

           /* (new WebDriverWait(driver, 5))
                    .until(ExpectedConditions.presenceOfElementLocated(By.className("al-about-this-question-title")));*/

        }
        catch (Exception e)
        {
            System.out.println("Error while attempting diagnotic test question on chapterId " + chapterId + " currentQId " + currentQId + "userid "+userId + "class_section_id "+classSectionId + "Assessmeent id "+assessmentId);
        }
    }

    public String beginOrContinuePracticeTest(String chapterId,String tloId)
    {
        String uahId = null;

        try {
            String encChapterId = TDESEncryptionUtils.encryptLong(new Long(chapterId));
            String encTloId = "";
            if(!StringUtils.isEmpty(tloId))
            encTloId = TDESEncryptionUtils.encryptLong(new Long(tloId));
            driver.get(Config.baseURL + "/secure/practiceTest?ajax=true&chapterId="+encChapterId+"&tloId="+encTloId);
            uahId = driver.findElement(By.id("userAssessmentId")).getAttribute("value");

        }
        catch (Exception e)
        {
            System.out.println("Error - userAssessmentId not fetched by selenium in beginOrContinuePracticeTest for chapter id - "+chapterId);
        }
        return uahId;
    }

    public void processPracTestQuestions(String assessmentId, String assessmentStatus, String chapterId,String userAssessmentHistoryId,String algoVariables
            ,String answerChoiceIds,String confidenceFactor,String currentQId,String diffculty, String displayLabel, String isHintViewed, String position, String questionPoints,
                                         String timeTaken, String totalQs, String [] userAnswers,String tloId, String qSetId,Boolean isTextAns, Boolean mfr, String userId, String classSectionId)
    {
        StringBuffer encUserAnswers = new StringBuffer();
        if(!isTextAns) {
            for (String uAnswer : userAnswers) {
                if (!StringUtils.isEmpty(uAnswer))
                    encUserAnswers.append(TDESEncryptionUtils.encryptLong(new Long(uAnswer)) + "_");
            }
            if(!StringUtils.isEmpty(encUserAnswers)) {
                encUserAnswers.deleteCharAt(encUserAnswers.length() - 1);
            }
        }else{
            encUserAnswers.append(userAnswers[0]);
        }

        String enctotalQs = TDESEncryptionUtils.encryptInteger(new Integer(totalQs));
        String encPosition = "";
        if(!StringUtils.isEmpty(position)) {
            encPosition = TDESEncryptionUtils.encryptInteger(new Integer(position));
        }
        String encCurrentQId = TDESEncryptionUtils.encryptLong(new Long(currentQId));
        String encChapterId = TDESEncryptionUtils.encryptLong(new Long(chapterId));
        String encTloId = "";
        if(!StringUtils.isEmpty(tloId)) {
            encTloId = TDESEncryptionUtils.encryptLong(new Long(tloId));
        }
        String encQSetId = TDESEncryptionUtils.encryptLong(new Long(qSetId));
        String encAssessmentId = TDESEncryptionUtils.encryptLong(new Long(assessmentId));
        StringBuffer encAnswerChoiceIds = new StringBuffer();

        answerChoiceIds = answerChoiceIds.replaceAll("\"", "");
        answerChoiceIds = answerChoiceIds.replaceAll("\\|", ",");
        if(!StringUtils.isEmpty(answerChoiceIds)) {
            answerChoiceIds = answerChoiceIds.substring(0, answerChoiceIds.length() - 1);
        }

        if(answerChoiceIds != null && answerChoiceIds.contains(",")) {
            String [] listAnswerChoiceIds = answerChoiceIds.split(",");

            for(String answerChoiceId : listAnswerChoiceIds) {
                if(!StringUtils.isEmpty(answerChoiceId))
                    encAnswerChoiceIds.append(TDESEncryptionUtils.encryptLong(new Long(answerChoiceId)) + ",");
            }
            if(!StringUtils.isEmpty(encAnswerChoiceIds)) {
                encAnswerChoiceIds.deleteCharAt(encAnswerChoiceIds.length() - 1);
            }
        }
        try
        {
            driver.get(Config.baseURL+"/secure/getUserResponseView?" +
                    "aid="+encAssessmentId+"" +
                    "&ajax=true" +
                    "&algoVariables=" +algoVariables+
                    "&answerChoiceIds="+encAnswerChoiceIds+"" +
                    "&autoGrade=true" +
                    "&chapterId="+encChapterId+"" +
                    "&markForReview="+mfr+"" +
                    "&confidenceFactor="+confidenceFactor+"" +
                    "&currentQId="+encCurrentQId+"" +
                    "&difficulty="+diffculty+"" +
                    "&displayLabel="+displayLabel+"" +
                    "&isHintViewed="+isHintViewed+"" +
                    "&position="+encPosition+"" +
                    "&pqIndex=1" +
                    "&practiceTestSideBarStatus=SHOW" +
                    "&qSetId="+encQSetId+"" +
                    "&questionPoints="+questionPoints+"" +
                    "&timeTaken="+timeTaken+"" +
                    "&tloId="+encTloId+"" +
                    "&totalQs="+enctotalQs+"" +
                    "&userAnswer="+encUserAnswers+"" +
                    "&userAssessmentId=" + userAssessmentHistoryId);

            try {
                if (driver.findElement(By.tagName("img")).getAttribute("src").equals("/webresources/images/app/whoopsie.png")) {
                    System.out.println("Error Woopsie on chapterId " + chapterId + " currentQId " + currentQId+ "userid "+userId + "class_section_id "+classSectionId + "Assessmeent id "+assessmentId);
                    failureCount++;
                }
            }
            catch (Exception e)  {

            }

        }
        catch (Exception e) {
            System.out.println("Error in processPracTestQuestions " + chapterId + " currentQId " + currentQId+ "userid "+userId + "class_section_id "+classSectionId + "Assessmeent id "+assessmentId);
        }
    }

    public void endPracTest(String chapterId, String tloId, String userAssessmentHistoryId)
    {
        try
        {
            String encTloId = "";
            if(!StringUtils.isEmpty(tloId)) {
                encTloId = TDESEncryptionUtils.encryptLong(new Long(tloId));
            }
            String encChapterId = TDESEncryptionUtils.encryptLong(new Long(chapterId));
            driver.get(Config.baseURL+"/secure/endPractice?encTloId="+encTloId+"&ajax=true&chapterId="+encChapterId+"&userAssessmentId=" + userAssessmentHistoryId); //end practice test
        }
        catch (Exception e)
        {
            System.out.println("Error in endPracTest chapter id - " + chapterId +"tloId "+tloId+"userAssessmentHistoryId "+userAssessmentHistoryId);
        }
    }
    public void forceSyncGradeBookForPractice(String chapterId, String tloId)
    {

        try
        {
            String encTloId = "";
            if(!StringUtils.isEmpty(tloId)) {
                encTloId = TDESEncryptionUtils.encryptLong(new Long(tloId));
            }
            String encChapterId = TDESEncryptionUtils.encryptLong(new Long(chapterId));
            driver.get(Config.baseURL+"/secure/practiceTestReportView?tloId="+encTloId+"&ajax=true&chapterId="+encChapterId); //viewing report for practice, will force gradebook sync
        }
        catch (Exception e){
            System.out.println("Error in forceSyncGradeBookForPractice method for chapterId "+chapterId+" tloId "+tloId);
        }
    }


}