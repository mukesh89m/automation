package com.snapwiz.selenium.tests.staf.orion.apphelper;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Date;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//import sun.misc.BASE64Decoder;
//import sun.misc.BASE64Encoder;

import javax.xml.bind.DatatypeConverter;

@SuppressWarnings("restriction")
public class TDESEncryptionUtils {

    private static final Logger logger = LoggerFactory.getLogger(TDESEncryptionUtils.class);
    public static final String CHARACTER_SET = "UTF-8";
    private static long salt;
    private static Random rand = new Random((new Date()).getTime());

    public TDESEncryptionUtils(long salt) {
        TDESEncryptionUtils.salt = salt;
    }

    private static Long encrypt(long l){
        return ( (((0x0000FFFF & l)<<16)|((0xFFFF0000 &l)>>16) ) ^ salt );
    }

    private static Long decrypt(long l){
        long a=( l ^ salt );
        return (((0x0000FFFF & a)<<16)|((0xFFFF0000 &a)>>16) );
    }

    private static String encryptAndEncode(Long unencrypted){

        Long encVal = unencrypted;
        String encrypted = null;

        try {

            if(encVal != null){

                encVal = encrypt(encVal);
                byte[] plainText = encVal.toString().getBytes(CHARACTER_SET);
//                BASE64Encoder base64encoder = new BASE64Encoder();
//                encrypted = base64encoder.encode(plainText);
                encrypted= DatatypeConverter.printBase64Binary(plainText);
                encrypted = URLEncoder.encode(encrypted, CHARACTER_SET);

            }

        } catch (Exception e) {
            if (logger.isErrorEnabled())
                logger.error("Exception ", e);
        }

        return encrypted;

    }


    private static Long decodeAndDecrypt(String encryptedString){

        String decVal = encryptedString;
        Long decrypted = null;

        try {

            if(decVal != null && !decVal.equals("")){

                decVal = URLDecoder.decode(decVal, CHARACTER_SET);
//                BASE64Decoder base64decoder = new BASE64Decoder();
//                byte[] decryptedText = base64decoder.decodeBuffer(decVal);
                byte[] decryptedText=DatatypeConverter.parseBase64Binary(decVal);
                decVal = new String(decryptedText, CHARACTER_SET);
                decrypted = Long.parseLong(decVal);
                decrypted = decrypt(decrypted);

            }

        } catch (Exception e) {

            logger.error("Value passed for decryption *" + encryptedString + "*");

            if (logger.isErrorEnabled())
                logger.error("Exception ", e);

            throw new RuntimeException();
        }

        return decrypted;

    }

    public static String encryptLong(Long valToEnc){

        return encryptAndEncode(valToEnc);

    }

    public static Long decryptLong(String valToDec){

        valToDec = checkIfMultipleVauesExists(valToDec);
        return decodeAndDecrypt(valToDec);

    }

    public static String encryptInteger(Integer valToEnc){

        String value = null;

        if (valToEnc != null) {
            value = encryptAndEncode(valToEnc.longValue());
        }

        return value;

    }

    public static Integer decryptInteger(String valToDec){

        Integer value = null;

        valToDec = checkIfMultipleVauesExists(valToDec);
        Long decrypted = decodeAndDecrypt(valToDec);

        if(decrypted != null)
            value = decrypted.intValue();

        return value;

    }

    public static String decryptString(String valToDec){

        String value = valToDec;

        if(value != null && !value.equals("")){

            value = checkIfMultipleVauesExists(value);
            Long decrypted = decodeAndDecrypt(value);

            if(decrypted != null)
                value = decrypted.toString();
        }

        return value;

    }

    /**
     * This method takes the encrypted answerChoiceIds and the encrypted userAnswer as input, iterates over the answerChoiceIds and decrypts it.
     * @param encAnswerChoiceIds
     * @param encUserAnswer
     * @param answerChoiceIds
     */
    public static String decryptAnswerChoiceIds(String encAnswerChoiceIds, String encUserAnswer, StringBuffer answerChoiceIds){

        String userAnswer = encUserAnswer;

        if (null != encAnswerChoiceIds && !"".equals(encAnswerChoiceIds)) {

            String[] tempArr = encAnswerChoiceIds.split(",");

            for (String s : tempArr)
                answerChoiceIds.append(TDESEncryptionUtils.decryptString(s)).append(",");

            if(encUserAnswer.contains("_")){

                tempArr = encUserAnswer.split("_");
                userAnswer = "";
                for (String s : tempArr)
                    userAnswer = userAnswer + TDESEncryptionUtils.decryptString(s) + "_";

            } else {
                userAnswer = TDESEncryptionUtils.decryptString(encUserAnswer);
            }

        }

        return userAnswer;

    }

    /**
     * Testing The DESede Encryption And Decryption Technique
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {

        new TDESEncryptionUtils(436792765l);
		/*String encVal1 = TDESEncryptionUtils.encryptAndEncode(4821l);
		System.out.println("Encrypted string :: " + encVal1);
		String decVal1 = TDESEncryptionUtils.decryptString(encVal1);
		System.out.println("Decrypted string ::" + decVal1);
		*/
        String chid = "2473";
        Long lon = new Long(chid);
        String enc = TDESEncryptionUtils.encryptLong(lon);
        System.out.println("Encrypted Long :: " + enc);
        Long dec = TDESEncryptionUtils.decryptLong("ODc0NTczMjM5");
        System.out.println("Decrypted Long ::" + dec);

        //System.out.println(Long.parseLong("297013313"));

		/*Integer in = new Integer(4821);
		String enci = TDESEncryptionUtils.encryptInteger(in);
		System.out.println("Encrypted Long :: " + enci);
		Integer deci = TDESEncryptionUtils.decryptInteger(enci);
		System.out.println("Decrypted Long ::" + deci);*/

		/*
		int iterations = 500;
		long start = System.nanoTime();
		for(int i = 0; i<10; i++){
			TDESEncryptionUtils.encryptLong(lon);
			lon++;
		}
		long end = System.nanoTime();

		System.out.println("Time taken for " + iterations + " :: " + (end - start));*/

    }

    /**
     * method to decrypt answerChoiceIds
     * @param answerChoiceIds
     * @return
     */
    public static String decryptAnswerChoiceIds(String answerChoiceIds) {
        String decryptedAnswerChoiceIds = "";
        String[] arrayOfAnswerChoiceIds = answerChoiceIds.split(",");
        for(int i=0; i<arrayOfAnswerChoiceIds.length; i++) {
            if(!arrayOfAnswerChoiceIds[i].trim().equals("")) {
                String temp = TDESEncryptionUtils.decryptString(arrayOfAnswerChoiceIds[i].trim())+",";
                decryptedAnswerChoiceIds = decryptedAnswerChoiceIds+temp;
            }
        }
        return decryptedAnswerChoiceIds;
    }

    /**
     * This method is added as a temporary fix for the defect 3286
     * @param valToDec
     * @return
     */
    private static String checkIfMultipleVauesExists(String valToDec) {
        if(valToDec!=null && valToDec.contains(",")){
            logger.error("Multiple values passed for decryption !!! - " + valToDec);
            return valToDec.split(",")[0];
        }

        return valToDec;
    }

    public static String encrypt(String str) {

//        BASE64Encoder encoder = new BASE64Encoder();


        byte[] salt = new byte[8];

        rand.nextBytes(salt);

        //return encoder.encode(salt) + encoder.encode(str.getBytes());
        return DatatypeConverter.printBase64Binary(salt)+DatatypeConverter.printBase64Binary(str.getBytes());
    }

    public static String decrypt(String encstr) {

        if (encstr.length() > 12) {

            String cipher = encstr.substring(12);

//            BASE64Decoder decoder = new BASE64Decoder();

            try {

//                return new String(decoder.decodeBuffer(cipher));
                return  new String(DatatypeConverter.parseBase64Binary(cipher));

            } catch (Exception e) {

                if (logger.isErrorEnabled())
                    logger.error("Exception ", e);
            }
        }
        return null;
    }

}

