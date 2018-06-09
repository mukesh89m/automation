package com.snapwiz.selenium.tests.staf.framework.utility;

/**
 * Created by murthi on 16-02-2016.
 */
public class StringUtil {

        public static enum Mode {
            ALPHA, ALPHANUMERIC, NUMERIC
        }

        public static String generateRandomString(int length, Mode mode) {

            StringBuffer buffer = new StringBuffer();
            String characters = "";

            switch(mode){

                case ALPHA:
                    characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
                    break;

                case ALPHANUMERIC:
                    characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
                    break;

                case NUMERIC:
                    characters = "1234567890";
                    break;
            }

            int charactersLength = characters.length();

            for (int i = 0; i < length; i++) {
                double index = Math.random() * charactersLength;
                buffer.append(characters.charAt((int) index));
            }
            return buffer.toString();
        }

        public static String extractNumberFromString(String string){
            return string.replaceAll("^[0-9]","");
        }
}
