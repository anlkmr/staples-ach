package com.emagia.ach.service;

import com.emagia.ach.staples_emagia.repository.StaplesEmagiaMISCRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CashCompaniesService {
    
    
    @Autowired
    StaplesEmagiaMISCRepository staplesEmagiaMISCRepository;

    @Transactional("staplesEmagiaOracleTransactionManager")
    public String getIDresult(String id){
        var byId = staplesEmagiaMISCRepository.findById(id);
        System.out.println("done");
        return id;
    }
    
}
