package com.snapwiz.selenium.tests.staf.datacreation.testcases;

import lombok.Data;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;

import java.net.URLEncoder;

/**
* Created by root on 27/10/14.Strin
*/
@ToString
@Data
class UserData {


   String user_external_id, user_first_name, user_last_name, user_id, domain_external_id, domain_id, class_sec_external_id, class_sec_id, class_sec_name, user_role,  uaId, UserId, CourseId, ClassSectionId,ChapterId, TLOId, AssessmentType, Progress_Status,
           Assessment_Id, TotalQs, TotalCorrectQs, TotalPartiallyCorrectQs, TotalIncorrectQs, TotalSkippedQs, QuestionId, qSetId, algovar, acIds, ac1, ac2, ac3, ac4, ac5, ac6,ac7, ac8, ac9, ac10,ac11,ac12,cf
            ,ta1,ta2,ta3,ta4,ta5,ta6,ta7,pos,dl,qp,email,external_course_id,isHintViewed,timeTaken,diagCf,qDl,mfr,custom_lis_result_source_id,lis_outcome_service_url,mongoObjectId;

    String currentuahId;


    public String [] getUserAnswers()
    {
        String [] userAnswers = new String[10];
        try {
            if(!StringUtils.isEmpty(this.ac1) || !StringUtils.isEmpty(this.ac2) || !StringUtils.isEmpty(this.ac3) || !StringUtils.isEmpty(this.ac4)
                    || !StringUtils.isEmpty(this.ac5) || !StringUtils.isEmpty(this.ac6) || !StringUtils.isEmpty(this.ac7)
                    || !StringUtils.isEmpty(this.ac8)  || !StringUtils.isEmpty(this.ac9) || !StringUtils.isEmpty(this.ac10)
                    || !StringUtils.isEmpty(this.ac11) || !StringUtils.isEmpty(this.ac12)) {


                if (!StringUtils.isEmpty(this.ac1)) {
                    userAnswers[0] = this.ac1;
                }
                if (!StringUtils.isEmpty(this.ac2)) {
                    userAnswers[1] = this.ac2;
                }
                if (!StringUtils.isEmpty(this.ac3)) {
                    userAnswers[2] = this.ac3;
                }
                if (!StringUtils.isEmpty(this.ac4)) {
                    userAnswers[3] = this.ac4;
                }
                if (!StringUtils.isEmpty(this.ac5)) {
                    userAnswers[4] = this.ac5;
                }
                if (!StringUtils.isEmpty(this.ac6)) {
                    userAnswers[5] = this.ac6;
                }
                if (!StringUtils.isEmpty(this.ac7)) {
                    userAnswers[6] = this.ac7;
                }
                if (!StringUtils.isEmpty(this.ac8)) {
                    userAnswers[7] = this.ac8;
                }
                if (!StringUtils.isEmpty(this.ac9)) {
                    userAnswers[8] = this.ac9;
                }
                if (!StringUtils.isEmpty(this.ac10)) {
                    userAnswers[9] = this.ac10;
                }
                if (!StringUtils.isEmpty(this.ac11)) {
                    userAnswers[10] = this.ac11;
                }
                if (!StringUtils.isEmpty(this.ac12)) {
                    userAnswers[11] = this.ac12;
                }
            }
            else  if(!StringUtils.isEmpty(this.ta1) || !StringUtils.isEmpty(this.ta2) || !StringUtils.isEmpty(this.ta3)
                    || !StringUtils.isEmpty(this.ta4) || !StringUtils.isEmpty(this.ta5) || !StringUtils.isEmpty(this.ta6) || !StringUtils.isEmpty(this.ta7)){
                StringBuffer taAns = new StringBuffer();

                if(!StringUtils.isEmpty(this.ta1)){
                    if (this.ta1.contains("<math")) {
                        taAns.append(this.ta1);
                    }else {
                        taAns.append("{");
                        taAns.append("\"textAnswer1\":\"" + this.ta1 + "\"");
                    }
                }
                if(!StringUtils.isEmpty(this.ta2)){
                    if (taAns.length()>0) {
                        taAns.append(",");
                    }
                    if (this.ta2.contains("<math")) {
                        taAns.append( this.ta2);
                    } else {
                        if (taAns.length()==0) {
                            taAns.append("{");
                        }
                        taAns.append("\"textAnswer2\":\"" + this.ta2 + "\"");
                    }
                }
                if(!StringUtils.isEmpty(this.ta3)){
                    if (taAns.length()>0) {
                        taAns.append(",");
                    }
                    if (this.ta3.contains("<math")) {
                        taAns.append( this.ta3);
                    } else {
                        if (taAns.length()==0) {
                            taAns.append("{");
                        }
                        taAns.append("\"textAnswer3\":\"" + this.ta3 + "\"");
                    }
                }
                if(!StringUtils.isEmpty(this.ta4)){
                    if (taAns.length()>0) {
                        taAns.append(",");
                    }
                    if (this.ta4.contains("<math")) {
                        taAns.append( this.ta4);
                    } else {
                        if (taAns.length()==0) {
                            taAns.append("{");
                        }
                        taAns.append("\"textAnswer4\":\"" + this.ta4 + "\"");
                    }
                }
                if(!StringUtils.isEmpty(this.ta5)){
                    if (taAns.length()>0) {
                        taAns.append(",");
                    }
                    if (this.ta5.contains("<math")) {
                        taAns.append( this.ta5);
                    } else {
                        if (taAns.length()==0) {
                            taAns.append("{");
                        }
                        taAns.append("\"textAnswer5\":\"" + this.ta5 + "\"");
                    }
                }
                if(!StringUtils.isEmpty(this.ta6)){
                    if (taAns.length()>0) {
                        taAns.append(",");
                    }
                    if (this.ta6.contains("<math")) {
                        taAns.append( this.ta6);
                    } else {
                        if (taAns.length()==0) {
                            taAns.append("{");
                        }
                        taAns.append("\"textAnswer6\":\"" + this.ta6 + "\"");
                    }
                }
                if(!StringUtils.isEmpty(this.ta7)){
                    if (taAns.length()>0) {
                        taAns.append(",");
                    }
                    if (this.ta7.contains("<math")) {
                        taAns.append( this.ta7);
                    } else {
                        if (taAns.length()==0) {
                            taAns.append("{");
                        }
                        taAns.append("\"textAnswer7\":\"" + this.ta7 + "\"");
                    }
                }
                if (taAns.indexOf("{")==0) {
                    taAns.append("}");
                }
                userAnswers[0] = URLEncoder.encode(taAns.toString(),"UTF-8");
            }
        }
        catch (Exception e)
        {

        }
        return userAnswers;
    }
    public Boolean isTextAns()
    {
        String [] userAnswers = new String[10];
        try {
             if(!StringUtils.isEmpty(this.ta1) || !StringUtils.isEmpty(this.ta2) || !StringUtils.isEmpty(this.ta3)
                    || !StringUtils.isEmpty(this.ta4) || !StringUtils.isEmpty(this.ta5) || !StringUtils.isEmpty(this.ta6) || !StringUtils.isEmpty(this.ta7)){
                return Boolean.TRUE;
            }
        }
        catch (Exception e)
        {

        }
      return Boolean.FALSE;
    }
}

