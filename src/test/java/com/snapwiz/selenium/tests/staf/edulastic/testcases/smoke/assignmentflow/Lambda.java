package com.snapwiz.selenium.tests.staf.edulastic.testcases.smoke.assignmentflow;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.lambda.AWSLambdaClient;
import com.amazonaws.services.lambda.model.InvokeRequest;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Stack;

/**
 * Created by Murthi on 11/2/2016.
 */
public class Lambda {


    private static final Log logger = LogFactory.getLog(Lambda.class);
    private static final String awsAccessKeyId = "AKIAIEC3OLCMC5I5NDZQ";
    private static final String awsSecretAccessKey = "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";
    private static final String regionName = "us-east-1";
    private static final String functionName = "MyFunction";
    private static Region region;
    private static AWSCredentials credentials;
    private static AWSLambdaClient lambdaClient;
    Stack stack=new Stack();

    @BeforeClass
    public void initCount(){
        for(int i=0;i<50;i++){
            stack.push(new Integer(i));
        }
    }

    @Test(enabled = true,threadPoolSize = 50, invocationCount = 50)
    public void invokeAWSLambdaFunction(){

        String appendChar="pp";

        HashMap<java.lang.String, java.lang.String> input=new HashMap<>();

        String email_id="createQuestionst"+appendChar+stack.pop()+"@snapwiz.com";
        System.out.println(email_id);
        input.put("email",email_id);

        credentials = new BasicAWSCredentials(awsAccessKeyId,
                awsSecretAccessKey);

        lambdaClient = (credentials == null) ? new AWSLambdaClient() : new AWSLambdaClient(credentials);
        //lambdaClient.configureRegion(Regions.US_WEST_2);
        region = Region.getRegion(Regions.fromName(regionName));
        lambdaClient.setRegion(region);




        try {
            InvokeRequest invokeRequest = new InvokeRequest();
            invokeRequest.setFunctionName(functionName);
            //invokeRequest.setPayload("{ \"email\":\""+email_id +"\"}");
            invokeRequest.setPayload("\""+email_id+"\"");
            System.out.println(byteBufferToString(
                    lambdaClient.invoke(invokeRequest).getPayload(),
                    Charset.forName("UTF-8")));
        } catch (Exception e) {
            logger.error(e.getMessage());
            System.out.println(e.getMessage());

        }
    }

    public static String byteBufferToString(ByteBuffer buffer, Charset charset) {
        byte[] bytes;
        if (buffer.hasArray()) {
            bytes = buffer.array();
        } else {
            bytes = new byte[buffer.remaining()];
            buffer.get(bytes);
        }
        return new String(bytes, charset);
    }



}