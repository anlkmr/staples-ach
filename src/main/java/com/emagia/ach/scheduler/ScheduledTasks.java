package com.emagia.ach.scheduler;

import com.emagia.ach.entity.Employee;
import com.emagia.ach.repo.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;

@Component
public class ScheduledTasks {

    @Autowired
    private EmployeeRepository cronRepo;
    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");



   // @Scheduled(fixedRate = 5000)
    public void reportCurrentTime() {
        log.info("The time is now {}", dateFormat.format(new Date()));
    }

    //@Scheduled(cron = "0 */1 * ? * *")


    @Bean
    public String getCronValue()
    {
        return cronRepo.findById(1L).get().getRole();
    }
}