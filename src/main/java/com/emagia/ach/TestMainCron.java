package com.emagia.ach;

import com.emagia.ach.scheduler.ScheduledTasks;
import com.emagia.ach.utils.AchStringUtil;
import org.apache.juli.logging.Log;
import org.bouncycastle.oer.its.ieee1609dot2.basetypes.Signature;
import org.bouncycastle.openpgp.examples.KeyBasedFileProcessor;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class TestMainCron {
    private static int trace = 1;
    public static void main(String args[]){
       /* System.out.println("test *****--********");
        ScheduledTasks scheduledTasks = new ScheduledTasks();
        Object employee = scheduledTasks.getCronValue();
        System.out.println(employee.toString());*/

        //System.out.println("The sum = " + checkDigit("121000358"));
        //12100035
        //37137137
        //3 14 1 0 0 0 9 35 = 62
        //trace++;
        //AchStringUtil.letter(trace)
        //String rt = "09100035";
        //String s = AchStringUtil.leftPad("11", 4,"0");
        //String s = AchStringUtil.rightPad("11", 22);
        //System.out.println(s);
        /*List<String> stlist = new ArrayList<>();
        stlist.add(rt);
        stlist.add(s);
        //Iterable<?> stlistww = null;
        String join = AchStringUtil.join(stlist, "");

        System.out.println(join);*/

       // var ceil = (int) Math.ceil(155);

       // System.out.println(numberOfBlockingFileRecords(158));

        //System.out.println(round(155));
        Signature signature = Signature.getInstance("SHA256withRSA");
        KeyBasedFileProcessor keyBasedFileProcessor = new KeyBasedFileProcessor();
        //keyBasedFileProcessor.
       // signature


    }

    static int numberOfBlockingFileRecords(int blockCount){
       //return Math.abs(round(blockCount)-blockCount);
        return Math.abs((blockCount/10+1)*10 - blockCount);
    }

    static int checkDigit(String rtNumber)
    {

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
            return Math.abs(round(total_sum)-total_sum);
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
}
