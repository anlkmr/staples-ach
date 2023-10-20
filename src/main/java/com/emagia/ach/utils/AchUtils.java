package com.emagia.ach.utils;

public class AchUtils {
    public static short calculateCheckDigit(String rtNumber){

        if(rtNumber.length()==8){
        String[] digits = String.valueOf(rtNumber.substring(0,8)).split("");
        int total_sum = Integer.valueOf(digits[0])*3;
        total_sum = total_sum + Integer.valueOf(digits[1])*7;
        total_sum = total_sum + Integer.valueOf(digits[2])*1;
        total_sum = total_sum + Integer.valueOf(digits[3])*3;
        total_sum = total_sum + Integer.valueOf(digits[4])*7;
        total_sum = total_sum + Integer.valueOf(digits[5])*1;
        total_sum = total_sum + Integer.valueOf(digits[6])*3;
        total_sum = total_sum + Integer.valueOf(digits[7])*7;
        System.out.println(round(total_sum));
        return (short) Math.abs(round(total_sum)-total_sum);
        }
        return 0;
    }
    static int round(int n)
    {
        // Smaller multiple
        int a = (n / 10) * 10;

        // Larger multiple
        int b = a + 10;

        // Return of closest of two
        return (n - a > b - n)? b : a;
    }

    public static int roundBy10(int input){
        return ((int) Math.ceil(input / 10) + 1);
    }

    public static int numberOfBlockingFileRecords(int blockCount){
        //return Math.abs(round(blockCount)-blockCount);
        return Math.abs((blockCount/10+1)*10 - blockCount);
    }
}
