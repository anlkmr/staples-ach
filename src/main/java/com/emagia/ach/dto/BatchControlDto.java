package com.emagia.ach.dto;

import io.swagger.annotations.ApiModel;

@ApiModel()
public class BatchControlDto extends AbstractDto<String> {
    private Long id;
    private String recordtype;
    private String serviceclasscode;
    private String entryaddendacount;
    private String entryhash;
    private String batchDebitEntryTotalAmount;
    private String batchCreditEntryTotalAmount;
    private String companyid;
    private String messageAuthCode;
    private String blank;
    private String rtNumberOdfiId;
    private String batchnumber;

    public BatchControlDto() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return this.id;
    }

    public void setRecordtype(String recordtype) {
        this.recordtype = recordtype;
    }

    public String getRecordtype() {
        return this.recordtype;
    }

    public void setServiceclasscode(String serviceclasscode) {
        this.serviceclasscode = serviceclasscode;
    }

    public String getServiceclasscode() {
        return this.serviceclasscode;
    }

    public void setEntryaddendacount(String entryaddendacount) {
        this.entryaddendacount = entryaddendacount;
    }

    public String getEntryaddendacount() {
        return this.entryaddendacount;
    }

    public void setEntryhash(String entryhash) {
        this.entryhash = entryhash;
    }

    public String getEntryhash() {
        return this.entryhash;
    }

    public void setBatchDebitEntryTotalAmount(String batchDebitEntryTotalAmount) {
        this.batchDebitEntryTotalAmount = batchDebitEntryTotalAmount;
    }

    public String getBatchDebitEntryTotalAmount() {
        return this.batchDebitEntryTotalAmount;
    }

    public void setBatchCreditEntryTotalAmount(String batchCreditEntryTotalAmount) {
        this.batchCreditEntryTotalAmount = batchCreditEntryTotalAmount;
    }

    public String getBatchCreditEntryTotalAmount() {
        return this.batchCreditEntryTotalAmount;
    }

    public void setCompanyid(String companyid) {
        this.companyid = companyid;
    }

    public String getCompanyid() {
        return this.companyid;
    }

    public void setMessageAuthCode(String messageAuthCode) {
        this.messageAuthCode = messageAuthCode;
    }

    public String getMessageAuthCode() {
        return this.messageAuthCode;
    }

    public void setBlank(String blank) {
        this.blank = blank;
    }

    public String getBlank() {
        return this.blank;
    }

    public void setRtNumberOdfiId(String rtNumberOdfiId) {
        this.rtNumberOdfiId = rtNumberOdfiId;
    }

    public String getRtNumberOdfiId() {
        return this.rtNumberOdfiId;
    }

    public void setBatchnumber(String batchnumber) {
        this.batchnumber = batchnumber;
    }

    public String getBatchnumber() {
        return this.batchnumber;
    }
}