package com.emagia.ach;

import com.emagia.ach.scheduler.ScheduledTasks;

public class TestMainCron {
    public static void main(String args[]){
        System.out.println("test *****--********");
        ScheduledTasks scheduledTasks = new ScheduledTasks();
        Object employee = scheduledTasks.getCronValue();
        System.out.println(employee.toString());
    }

}
