package com.emagia.ach;

import com.afrunt.jach.document.ACHBatch;
import com.afrunt.jach.document.ACHBatchDetail;
import com.afrunt.jach.document.ACHDocument;
import com.afrunt.jach.domain.*;
import com.afrunt.jach.domain.addenda.GeneralAddendaRecord;
import com.emagia.ach.achmaker.ACH;
import com.emagia.ach.achmaker.CTXEntryDetailUpdated;
import com.emagia.ach.entity.Employee;
import com.emagia.ach.repo.EmployeeRepository;
import com.emagia.ach.scheduler.ScheduledTasks;

import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

public class TestMainCron {
    public static void main(String args[]){
        System.out.println("test *****--********");
        ScheduledTasks scheduledTasks = new ScheduledTasks();
        Object employee = scheduledTasks.getCronValue();
        System.out.println(employee.toString());
    }

}
