package com.snapwiz.selenium.tests.staf.edulastic.testcases.IT1.performance;

import java.io.FileWriter;

/**
 * Created by pragya on 07-08-2015.
 */
public class CreteCSVFie {
    public static void main(String [] args)
    {

        generateCsvFile("C:\\Users\\pragya\\Desktop\\Perf Test_25QuestionsTrueFalse\\testdata.csv");
    }

    private static void generateCsvFile(String sFileName)
        {
            try
            {   int count = 1;
                FileWriter writer = new FileWriter(sFileName);

                String[] clsCodes  = {"7B73F","C19F8","36BD3","F9AD0","B3B4D","2525E","53E96","66EF8","CB1EE","22A61","A0C4F","0D78F","67F75","736E7","2DA09","1D9E1","1E71F","A5C55","EE78D","48FA5"};
                String[] id = {"MjQ3ODk2MDA2MQ%3D%3D,MjQ3OTAyNTU5Nw%3D%3D,MjQ3OTA5MTEzMw%3D%3D,MjQ3OTE1NjY2OQ%3D%3D,MjQ3OTIyMjIwNQ%3D%3D,MjQ3OTI4Nzc0MQ%3D%3D,MjQ3OTM1MzI3Nw%3D%3D,MjQ4MDQ2NzM4OQ%3D%3D,MjQ4MDUzMjkyNQ%3D%3D,MjQ4MDU5ODQ2MQ%3D%3D,MjQ4MDY2Mzk5Nw%3D%3D,MjQ4MDcyOTUzMw%3D%3D,MjQ4MDc5NTA2OQ%3D%3D,MjQ4MDg2MDYwNQ%3D%3D,MjQ4MDkyNjE0MQ%3D%3D,MjQ3OTk0MzEwMQ%3D%3D,MjQ4MDAwODYzNw%3D%3D,MjQ4MDA3NDE3Mw%3D%3D,MjQ4MDEzOTcwOQ%3D%3D,MjQ4MDIwNTI0NQ%3D%3D,MjQ4MDI3MDc4MQ%3D%3D,MjQ4MDMzNjMxNw%3D%3D,MjQ4MDQwMTg1Mw%3D%3D,MjQ4MTUxNTk2NQ%3D%3D,MjQ4MTU4MTUwMQ%3D%3D",
                            "MjQ4MTY0NzAzNw%3D%3D,MjQ4MTcxMjU3Mw%3D%3D,MjQ4MTc3ODEwOQ%3D%3D,MjQ4MTg0MzY0NQ%3D%3D,MjQ4MTkwOTE4MQ%3D%3D,MjQ4MTk3NDcxNw%3D%3D,MjQ4MDk5MTY3Nw%3D%3D,MjQ4MTA1NzIxMw%3D%3D,MjQ4MTEyMjc0OQ%3D%3D,MjQ4MTE4ODI4NQ%3D%3D,MjQ4MTI1MzgyMQ%3D%3D,MjQ4MTMxOTM1Nw%3D%3D,MjQ4MTM4NDg5Mw%3D%3D,MjQ4MTQ1MDQyOQ%3D%3D,MjQ4MjU2NDU0MQ%3D%3D,MjQ4MjYzMDA3Nw%3D%3D,MjQ4MjY5NTYxMw%3D%3D,MjQ4Mjc2MTE0OQ%3D%3D,MjQ4MjgyNjY4NQ%3D%3D,MjQ4Mjg5MjIyMQ%3D%3D,MjQ4Mjk1Nzc1Nw%3D%3D,MjQ4MzAyMzI5Mw%3D%3D,MjQ4MjA0MDI1Mw%3D%3D,MjQ4MjEwNTc4OQ%3D%3D,MjQ4MjE3MTMyNQ%3D%3D",
                            "MjQ4MjIzNjg2MQ%3D%3D,MjQ4MjMwMjM5Nw%3D%3D,MjQ4MjM2NzkzMw%3D%3D,MjQ4MjQzMzQ2OQ%3D%3D,MjQ4MjQ5OTAwNQ%3D%3D,MjQxNjUwNDI1Mw%3D%3D,MjQxNjU2OTc4OQ%3D%3D,MjQxNjYzNTMyNQ%3D%3D,MjQxNjcwMDg2MQ%3D%3D,MjQxNjc2NjM5Nw%3D%3D,MjQxNjgzMTkzMw%3D%3D,MjQxNjg5NzQ2OQ%3D%3D,MjQxNjk2MzAwNQ%3D%3D,MjQxNTk3OTk2NQ%3D%3D,MjQxNjA0NTUwMQ%3D%3D,MjQxNjExMTAzNw%3D%3D,MjQxNjE3NjU3Mw%3D%3D,MjQxNjI0MjEwOQ%3D%3D,MjQxNjMwNzY0NQ%3D%3D,MjQxNjM3MzE4MQ%3D%3D,MjQxNjQzODcxNw%3D%3D,MjQxNzU1MjgyOQ%3D%3D,MjQxNzYxODM2NQ%3D%3D,MjQxNzY4MzkwMQ%3D%3D,MjQxNzc0OTQzNw%3D%3D",
                            "MjQxNzg4MDUwOQ%3D%3D,MjQxNzk0NjA0NQ%3D%3D,MjQxODAxMTU4MQ%3D%3D,MjQxNzAyODU0MQ%3D%3D,MjQxNzA5NDA3Nw%3D%3D,MjQxNzE1OTYxMw%3D%3D,MjQxNzIyNTE0OQ%3D%3D,MjQxNzI5MDY4NQ%3D%3D,MjQxNzM1NjIyMQ%3D%3D,MjQxNzQyMTc1Nw%3D%3D,MjQxNzQ4NzI5Mw%3D%3D,MjQxODYwMTQwNQ%3D%3D,MjQxODY2Njk0MQ%3D%3D,MjQxODczMjQ3Nw%3D%3D,MjQxODc5ODAxMw%3D%3D,MjQxODg2MzU0OQ%3D%3D,MjQxODkyOTA4NQ%3D%3D,MjQxODk5NDYyMQ%3D%3D,MjQxOTA2MDE1Nw%3D%3D,MjQxODA3NzExNw%3D%3D,MjQxODE0MjY1Mw%3D%3D,MjQxODIwODE4OQ%3D%3D,MjQxODI3MzcyNQ%3D%3D,MjQxODMzOTI2MQ%3D%3D,MjQxODQwNDc5Nw%3D%3D",
                            "MjQyMjc5NTcwOQ%3D%3D,MjQyMjg2MTI0NQ%3D%3D,MjQyMjkyNjc4MQ%3D%3D,MjQyMjk5MjMxNw%3D%3D,MjQyMzA1Nzg1Mw%3D%3D,MjQyMzEyMzM4OQ%3D%3D,MjQyMzE4ODkyNQ%3D%3D,MjQyMzI1NDQ2MQ%3D%3D,MjQyMjI3MTQyMQ%3D%3D,MjQyMjMzNjk1Nw%3D%3D,MjQyMjQwMjQ5Mw%3D%3D,MjQyMjQ2ODAyOQ%3D%3D,MjQyMjUzMzU2NQ%3D%3D,MjQyMjU5OTEwMQ%3D%3D,MjQyMjY2NDYzNw%3D%3D,MjQyMjczMDE3Mw%3D%3D,MjQyMzg0NDI4NQ%3D%3D,MjQyMzkwOTgyMQ%3D%3D,MjQyMzk3NTM1Nw%3D%3D,MjQyNDA0MDg5Mw%3D%3D,MjQyNDEwNjQyOQ%3D%3D,MjQyNDE3MTk2NQ%3D%3D,MjQyNDIzNzUwMQ%3D%3D,MjQyNDMwMzAzNw%3D%3D,MjQyMzMxOTk5Nw%3D%3D",
                            "MjQyMzM4NTUzMw%3D%3D,MjQyMzQ1MTA2OQ%3D%3D,MjQyMzUxNjYwNQ%3D%3D,MjQyMzU4MjE0MQ%3D%3D,MjQyMzY0NzY3Nw%3D%3D,MjQyMzcxMzIxMw%3D%3D,MjQyMzc3ODc0OQ%3D%3D,MjQyNDg5Mjg2MQ%3D%3D,MjQyNDk1ODM5Nw%3D%3D,MjQyNTAyMzkzMw%3D%3D,MjQyNTA4OTQ2OQ%3D%3D,MjQyNTE1NTAwNQ%3D%3D,MjQyNTIyMDU0MQ%3D%3D,MjQyNTI4NjA3Nw%3D%3D,MjQyNTM1MTYxMw%3D%3D,MjQyNDM2ODU3Mw%3D%3D,MjQyNDQzNDEwOQ%3D%3D,MjQyNDQ5OTY0NQ%3D%3D,MjQyNDU2NTE4MQ%3D%3D,MjQyNDYzMDcxNw%3D%3D,MjQyNDY5NjI1Mw%3D%3D,MjQyNDc2MTc4OQ%3D%3D,MjQyNDgyNzMyNQ%3D%3D,MjQyNTk0MTQzNw%3D%3D,MjQyNjAwNjk3Mw%3D%3D",
                            "MjQyNjA3MjUwOQ%3D%3D,MjQyNjEzODA0NQ%3D%3D,MjQyNjIwMzU4MQ%3D%3D,MjQyNjI2OTExNw%3D%3D,MjQyNjMzNDY1Mw%3D%3D,MjQyNjQwMDE4OQ%3D%3D,MjQyNTQxNzE0OQ%3D%3D,MjQyNTQ4MjY4NQ%3D%3D,MjQyNTU0ODIyMQ%3D%3D,MjQyNTYxMzc1Nw%3D%3D,MjQyNTY3OTI5Mw%3D%3D,MjQyNTc0NDgyOQ%3D%3D,MjQyNTgxMDM2NQ%3D%3D,MjQyNTg3NTkwMQ%3D%3D,MjQyNjk5MDAxMw%3D%3D,MjQyNzA1NTU0OQ%3D%3D,MjQyNzEyMTA4NQ%3D%3D,MjQyNzE4NjYyMQ%3D%3D,MjQyNzI1MjE1Nw%3D%3D,MjQyNzMxNzY5Mw%3D%3D,MjQyNzM4MzIyOQ%3D%3D,MjQyNzQ0ODc2NQ%3D%3D,MjQyNjQ2NTcyNQ%3D%3D,MjQyNjUzMTI2MQ%3D%3D,MjQyNjU5Njc5Nw%3D%3D",
                            "MjQyNjY2MjMzMw%3D%3D,MjQyNjcyNzg2OQ%3D%3D,MjQyNjc5MzQwNQ%3D%3D,MjQyNjg1ODk0MQ%3D%3D,MjQyNjkyNDQ3Nw%3D%3D,MjQyODAzODU4OQ%3D%3D,MjQyODEwNDEyNQ%3D%3D,MjQyODE2OTY2MQ%3D%3D,MjQyODIzNTE5Nw%3D%3D,MjQyODMwMDczMw%3D%3D,MjQyODM2NjI2OQ%3D%3D,MjQyODQzMTgwNQ%3D%3D,MjQyODQ5NzM0MQ%3D%3D,MjQyNzUxNDMwMQ%3D%3D,MjQyNzU3OTgzNw%3D%3D,MjQyNzY0NTM3Mw%3D%3D,MjQyNzcxMDkwOQ%3D%3D,MjQyNzc3NjQ0NQ%3D%3D,MjQyNzg0MTk4MQ%3D%3D,MjQyNzkwNzUxNw%3D%3D,MjQyNzk3MzA1Mw%3D%3D,MjQyOTA4NzE2NQ%3D%3D,MjQyOTE1MjcwMQ%3D%3D,MjQyOTIxODIzNw%3D%3D,MjQyOTI4Mzc3Mw%3D%3D",
                            "MjQyOTM0OTMwOQ%3D%3D,MjQyOTU0NTkxNw%3D%3D,MjQyODU2Mjg3Nw%3D%3D,MjQyODYyODQxMw%3D%3D,MjQyODY5Mzk0OQ%3D%3D,MjQyODc1OTQ4NQ%3D%3D,MjQyODg5MDU1Nw%3D%3D,MjQyODk1NjA5Mw%3D%3D,MjQyOTAyMTYyOQ%3D%3D,MjQzMDEzNTc0MQ%3D%3D,MjQzMDIwMTI3Nw%3D%3D,MjQzMDMzMjM0OQ%3D%3D,MjQzMDM5Nzg4NQ%3D%3D,MjQzMDQ2MzQyMQ%3D%3D,MjQzMDUyODk1Nw%3D%3D,MjQyOTYxMTQ1Mw%3D%3D,MjQyOTY3Njk4OQ%3D%3D,MjQyOTc0MjUyNQ%3D%3D,MjQyOTgwODA2MQ%3D%3D,MjQyOTg3MzU5Nw%3D%3D,MjQyOTkzOTEzMw%3D%3D,MjQzMDAwNDY2OQ%3D%3D,MjQzMDA3MDIwNQ%3D%3D,MjQzMTE4NDMxNw%3D%3D,MjQzMTI0OTg1Mw%3D%3D",
                            "MjQzMTMxNTM4OQ%3D%3D,MjQzMTM4MDkyNQ%3D%3D,MjQzMTQ0NjQ2MQ%3D%3D,MjQzMTUxMTk5Nw%3D%3D,MjQzMTU3NzUzMw%3D%3D,MjQzMTY0MzA2OQ%3D%3D,MjQzMDY2MDAyOQ%3D%3D,MjQzMDcyNTU2NQ%3D%3D,MjQzMDc5MTEwMQ%3D%3D,MjQzMDg1NjYzNw%3D%3D,MjQzMDkyMjE3Mw%3D%3D,MjQzMDk4NzcwOQ%3D%3D,MjQzMTA1MzI0NQ%3D%3D,MjQzMTExODc4MQ%3D%3D,MjQzMjIzMjg5Mw%3D%3D,MjQzMjI5ODQyOQ%3D%3D,MjQzMjM2Mzk2NQ%3D%3D,MjQzMjQ5NTAzNw%3D%3D,MjQzMjU2MDU3Mw%3D%3D,MjQzMjYyNjEwOQ%3D%3D,MjQzMjY5MTY0NQ%3D%3D,MjQzMTcwODYwNQ%3D%3D,MjQzMTc3NDE0MQ%3D%3D,MjQzMTgzOTY3Nw%3D%3D,MjQzMTkwNTIxMw%3D%3D",
                            "MjQzNDcyMzI2MQ%3D%3D,MjQzMzg3MTI5Mw%3D%3D,MjQzNDA2NzkwMQ%3D%3D,MjQzNDE5ODk3Mw%3D%3D,MjQzNTQ0NDE1Nw%3D%3D,MjQzNTY0MDc2NQ%3D%3D,MjQzNTc3MTgzNw%3D%3D,MjQzNDkxOTg2OQ%3D%3D,MjQzNTA1MDk0MQ%3D%3D,MjQzNTE4MjAxMw%3D%3D,MjQzNjQyNzE5Nw%3D%3D,MjQzNjU1ODI2OQ%3D%3D,MjQzNjYyMzgwNQ%3D%3D,MjQzNjY4OTM0MQ%3D%3D,MjQzNjgyMDQxMw%3D%3D,MjQzNTk2ODQ0NQ%3D%3D,MjQzNjE2NTA1Mw%3D%3D,MjQzNjI5NjEyNQ%3D%3D,MjQzNzQ3NTc3Mw%3D%3D,MjQzNzYwNjg0NQ%3D%3D,MjQzNzgwMzQ1Mw%3D%3D,MjQzNjk1MTQ4NQ%3D%3D,MjQzNzA4MjU1Nw%3D%3D,MjQzNzIxMzYyOQ%3D%3D,MjQzNzQxMDIzNw%3D%3D",
                            "MjQzMTk3MDc0OQ%3D%3D,MjQzMjEwMTgyMQ%3D%3D,MjQzMjE2NzM1Nw%3D%3D,MjQzMzM0NzAwNQ%3D%3D,MjQzMzU0MzYxMw%3D%3D,MjQzMzY3NDY4NQ%3D%3D,MjQzMjgyMjcxNw%3D%3D,MjQzMjk1Mzc4OQ%3D%3D,MjQzMzE1MDM5Nw%3D%3D,MjQzNDM5NTU4MQ%3D%3D,MjQzNDUyNjY1Mw%3D%3D,MjQzNDc4ODc5Nw%3D%3D,MjQzNDAwMjM2NQ%3D%3D,MjQzNDI2NDUwOQ%3D%3D,MjQzNTU3NTIyOQ%3D%3D,MjQzNDg1NDMzMw%3D%3D,MjQzNTI0NzU0OQ%3D%3D,MjQzNTkwMjkwOQ%3D%3D,MjQzNzY3MjM4MQ%3D%3D,MjQzNzg2ODk4OQ%3D%3D,MjQzNzE0ODA5Mw%3D%3D,MjQzODcyMDk1Nw%3D%3D,MjQzODI2MjIwNQ%3D%3D,MjQzOTU3MjkyNQ%3D%3D,MjQzOTgzNTA2OQ%3D%3D",
                            "MjQzODY1NTQyMQ%3D%3D,MjQzODc4NjQ5Mw%3D%3D,MjQzODkxNzU2NQ%3D%3D,MjQzODAwMDA2MQ%3D%3D,MjQzODEzMTEzMw%3D%3D,MjQzODMyNzc0MQ%3D%3D,MjQzODQ1ODgxMw%3D%3D,MjQzOTcwMzk5Nw%3D%3D,MjQzOTkwMDYwNQ%3D%3D,MjQ0MDAzMTY3Nw%3D%3D,MjQzOTExNDE3Mw%3D%3D,MjQzOTI0NTI0NQ%3D%3D,MjQzOTM3NjMxNw%3D%3D,MjQzOTQ0MTg1Mw%3D%3D,MjQ0MDYyMTUwMQ%3D%3D,MjQ0MDc1MjU3Mw%3D%3D,MjQ0MDg4MzY0NQ%3D%3D,MjQ0MTAxNDcxNw%3D%3D,MjQ0MDA5NzIxMw%3D%3D,MjQ0MDIyODI4NQ%3D%3D,MjQ0MDM1OTM1Nw%3D%3D,MjQ0MDQ5MDQyOQ%3D%3D,MjQ0MTY3MDA3Nw%3D%3D,MjQ0MTgwMTE0OQ%3D%3D,MjQ0MTkzMjIyMQ%3D%3D",
                            "MjQzNjc1NDg3Nw%3D%3D,MjQzNjg4NTk0OQ%3D%3D,MjQzNjAzMzk4MQ%3D%3D,MjQzNjA5OTUxNw%3D%3D,MjQzNjIzMDU4OQ%3D%3D,MjQzNjM2MTY2MQ%3D%3D,MjQzNzU0MTMwOQ%3D%3D,MjQzNzczNzkxNw%3D%3D,MjQzNzkzNDUyNQ%3D%3D,MjQzNzAxNzAyMQ%3D%3D,MjQzNzI3OTE2NQ%3D%3D,MjQzNzM0NDcwMQ%3D%3D,MjQzODUyNDM0OQ%3D%3D,MjQzODU4OTg4NQ%3D%3D,MjQzODg1MjAyOQ%3D%3D,MjQzODk4MzEwMQ%3D%3D,MjQzODA2NTU5Nw%3D%3D,MjQzODE5NjY2OQ%3D%3D,MjQzODM5MzI3Nw%3D%3D,MjQzOTYzODQ2MQ%3D%3D,MjQzOTc2OTUzMw%3D%3D,MjQzOTk2NjE0MQ%3D%3D,MjQzOTA0ODYzNw%3D%3D,MjQzOTE3OTcwOQ%3D%3D,MjQzOTMxMDc4MQ%3D%3D",
                            "MjQzOTUwNzM4OQ%3D%3D,MjQ0MDY4NzAzNw%3D%3D,MjQ0MDgxODEwOQ%3D%3D,MjQ0MDk0OTE4MQ%3D%3D,MjQ0MTA4MDI1Mw%3D%3D,MjQ0MDE2Mjc0OQ%3D%3D,MjQ0MDI5MzgyMQ%3D%3D,MjQ0MDQyNDg5Mw%3D%3D,MjQ0MDU1NTk2NQ%3D%3D,MjQ0MTczNTYxMw%3D%3D,MjQ0MTg2NjY4NQ%3D%3D,MjQ0MTk5Nzc1Nw%3D%3D,MjQ0MjA2MzI5Mw%3D%3D,MjQ0MjEyODgyOQ%3D%3D,MjQ0MTE0NTc4OQ%3D%3D,MjQ0MTIxMTMyNQ%3D%3D,MjQ0MTI3Njg2MQ%3D%3D,MjQ0MTM0MjM5Nw%3D%3D,MjQ0MTQwNzkzMw%3D%3D,MjQ0MTQ3MzQ2OQ%3D%3D,MjQ0MTUzOTAwNQ%3D%3D,MjQ0MTYwNDU0MQ%3D%3D,MjQ0MjcxODY1Mw%3D%3D,MjQ0Mjc4NDE4OQ%3D%3D,MjQ0Mjg0OTcyNQ%3D%3D",
                            "MjQzMzI4MTQ2OQ%3D%3D,MjQzMzQxMjU0MQ%3D%3D,MjQzMzQ3ODA3Nw%3D%3D,MjQzMzYwOTE0OQ%3D%3D,MjQzMzc0MDIyMQ%3D%3D,MjQzMjc1NzE4MQ%3D%3D,MjQzMjg4ODI1Mw%3D%3D,MjQzMzAxOTMyNQ%3D%3D,MjQzMzA4NDg2MQ%3D%3D,MjQzMzIxNTkzMw%3D%3D,MjQzNDMzMDA0NQ%3D%3D,MjQzNDQ2MTExNw%3D%3D,MjQzNDU5MjE4OQ%3D%3D,MjQzNDY1NzcyNQ%3D%3D,MjQzMzgwNTc1Nw%3D%3D,MjQzMzkzNjgyOQ%3D%3D,MjQzNDEzMzQzNw%3D%3D,MjQzNTM3ODYyMQ%3D%3D,MjQzNTUwOTY5Mw%3D%3D,MjQzNTcwNjMwMQ%3D%3D,MjQzNTgzNzM3Mw%3D%3D,MjQzNDk4NTQwNQ%3D%3D,MjQzNTExNjQ3Nw%3D%3D,MjQzNTMxMzA4NQ%3D%3D,MjQzNjQ5MjczMw%3D%3D",
                            "MjQ3NTU1MjE4OQ%3D%3D,MjQ3NTYxNzcyNQ%3D%3D,MjQ3NTY4MzI2MQ%3D%3D,MjQ3NDcwMDIyMQ%3D%3D,MjQ3NDc2NTc1Nw%3D%3D,MjQ3NDgzMTI5Mw%3D%3D,MjQ3NDg5NjgyOQ%3D%3D,MjQ3NDk2MjM2NQ%3D%3D,MjQ3NTAyNzkwMQ%3D%3D,MjQ3NTA5MzQzNw%3D%3D,MjQ3NTE1ODk3Mw%3D%3D,MjQ3NjI3MzA4NQ%3D%3D,MjQ3NjMzODYyMQ%3D%3D,MjQ3NjQwNDE1Nw%3D%3D,MjQ3NjQ2OTY5Mw%3D%3D,MjQ3NjUzNTIyOQ%3D%3D,MjQ3NjYwMDc2NQ%3D%3D,MjQ3NjY2NjMwMQ%3D%3D,MjQ3NjczMTgzNw%3D%3D,MjQ3NTc0ODc5Nw%3D%3D,MjQ3NTgxNDMzMw%3D%3D,MjQ3NTg3OTg2OQ%3D%3D,MjQ3NTk0NTQwNQ%3D%3D,MjQ3NjAxMDk0MQ%3D%3D,MjQ3NjA3NjQ3Nw%3D%3D",
                            "MjQ3Mjc5OTY3Nw%3D%3D,MjQ3Mjg2NTIxMw%3D%3D,MjQ3MjkzMDc0OQ%3D%3D,MjQ3Mjk5NjI4NQ%3D%3D,MjQ3MzA2MTgyMQ%3D%3D,MjQ3NDE3NTkzMw%3D%3D,MjQ3NDI0MTQ2OQ%3D%3D,MjQ3NDMwNzAwNQ%3D%3D,MjQ3NDM3MjU0MQ%3D%3D,MjQ3NDQzODA3Nw%3D%3D,MjQ3NDUwMzYxMw%3D%3D,MjQ3NDU2OTE0OQ%3D%3D,MjQ3NDYzNDY4NQ%3D%3D,MjQ3MzY1MTY0NQ%3D%3D,MjQ3MzcxNzE4MQ%3D%3D,MjQ3Mzc4MjcxNw%3D%3D,MjQ3Mzg0ODI1Mw%3D%3D,MjQ3MzkxMzc4OQ%3D%3D,MjQ3Mzk3OTMyNQ%3D%3D,MjQ3NDA0NDg2MQ%3D%3D,MjQ3NDExMDM5Nw%3D%3D,MjQ3NTIyNDUwOQ%3D%3D,MjQ3NTI5MDA0NQ%3D%3D,MjQ3NTM1NTU4MQ%3D%3D,MjQ3NTQyMTExNw%3D%3D",
                            "MjQyMTE1NzMwOQ%3D%3D,MjQyMDE3NDI2OQ%3D%3D,MjQyMDIzOTgwNQ%3D%3D,MjQyMDMwNTM0MQ%3D%3D,MjQyMDM3MDg3Nw%3D%3D,MjQyMDQzNjQxMw%3D%3D,MjQyMDUwMTk0OQ%3D%3D,MjQyMDU2NzQ4NQ%3D%3D,MjQyMDYzMzAyMQ%3D%3D,MjQyMTc0NzEzMw%3D%3D,MjQyMTgxMjY2OQ%3D%3D,MjQyMTg3ODIwNQ%3D%3D,MjQyMTk0Mzc0MQ%3D%3D,MjQyMjAwOTI3Nw%3D%3D,MjQyMjA3NDgxMw%3D%3D,MjQyMjE0MDM0OQ%3D%3D,MjQyMjIwNTg4NQ%3D%3D,MjQyMTIyMjg0NQ%3D%3D,MjQyMTI4ODM4MQ%3D%3D,MjQyMTM1MzkxNw%3D%3D,MjQyMTQxOTQ1Mw%3D%3D,MjQyMTQ4NDk4OQ%3D%3D,MjQyMTU1MDUyNQ%3D%3D,MjQyMTYxNjA2MQ%3D%3D,MjQyMTY4MTU5Nw%3D%3D",
                            "MjQxODQ3MDMzMw%3D%3D,MjQxODUzNTg2OQ%3D%3D,MjQxOTY0OTk4MQ%3D%3D,MjQxOTcxNTUxNw%3D%3D,MjQxOTc4MTA1Mw%3D%3D,MjQxOTg0NjU4OQ%3D%3D,MjQxOTkxMjEyNQ%3D%3D,MjQxOTk3NzY2MQ%3D%3D,MjQyMDA0MzE5Nw%3D%3D,MjQyMDEwODczMw%3D%3D,MjQxOTEyNTY5Mw%3D%3D,MjQxOTE5MTIyOQ%3D%3D,MjQxOTI1Njc2NQ%3D%3D,MjQxOTMyMjMwMQ%3D%3D,MjQxOTM4NzgzNw%3D%3D,MjQxOTQ1MzM3Mw%3D%3D,MjQxOTUxODkwOQ%3D%3D,MjQxOTU4NDQ0NQ%3D%3D,MjQyMDY5ODU1Nw%3D%3D,MjQyMDc2NDA5Mw%3D%3D,MjQyMDgyOTYyOQ%3D%3D,MjQyMDg5NTE2NQ%3D%3D,MjQyMDk2MDcwMQ%3D%3D,MjQyMTAyNjIzNw%3D%3D,MjQyMTA5MTc3Mw%3D%3D",

                };
              /*  for (int i = 1; i <=1000 ; i ++){
                    writer.append("Perfc3vstud"+i);
                    writer.append(',');
                    writer.append("Perfc3vstud"+i+"@snapwiz.com");
                    writer.append(',');
                    if(i == (count*50)+1){
                        count++;
                    }
                    writer.append(clsCodes[count-1]);
                    writer.append('\n');
                }
*/


                for (int i = 1; i <=500 ; i ++){
                    writer.append("PerfTrueFalseNew"+i);
                    writer.append(',');
                    writer.append("PerfTrueFalseNew"+i+"@snapwiz.com");
                    writer.append(',');
                    if(i == (count*25)+1){
                        count++;
                    }
                    writer.append(clsCodes[count-1]);
                    writer.append(',');
                    writer.append(id[count-1]);
                    writer.append('\n');
                }




           /* for (int i = 0; i < 500 ; i ++){
                writer.append("Perc1fvstud"+count);
                writer.append(',');
                writer.append("Perfc1vstud"+count+"@snapwiz.com");
                writer.append(',');
                if(i<=49){
                    writer.append("7B73F");
                }
                if(i>49 && i<100){
                    writer.append("C19F8");
                }

                if(i>99 && i<150){
                    writer.append("36BD3");
                }

                if(i>149 && i<200){
                    writer.append("F9AD0");
                }

                if(i>199 && i<250){
                    writer.append("B3B4D");
                }

                if(i>249 && i<300){
                    writer.append("2525E");
                }

                if(i>299 && i<350){
                    writer.append("53E96");
                }

                if(i>349 && i<400){
                    writer.append("66EF8");
                }

                if(i>399 && i<450){
                    writer.append("CB1EE");
                }

                if(i>449 && i<500){
                    writer.append("22A61");
                }
                writer.append('\n');
                count ++;
            }*/
            writer.flush();
            writer.close();
        }catch (Exception e){
            e.printStackTrace();

        }
        }
    }
