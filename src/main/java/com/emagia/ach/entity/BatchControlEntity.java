package com.emagia.ach.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "BATCH_CONTROL", schema = "HR")
public class BatchControlEntity {
    @Basic
    @Id
    @SequenceGenerator(schema = "hr", name = "BATCH_CONTROL_ID_SEQ", sequenceName  = "BATCH_CONTROL_ID_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BATCH_CONTROL_ID_SEQ")
    private Long id;
    @Basic
    @Column(name = "RECORDTYPE", nullable = true)
    private String recordtype;
    @Basic
    @Column(name = "SERVICECLASSCODE", nullable = true)
    private String serviceclasscode;
    @Basic
    @Column(name = "ENTRYADDENDACOUNT", nullable = true)
    private String entryaddendacount;
    @Basic
    @Column(name = "ENTRYHASH", nullable = true)
    private String entryhash;
    @Basic
    @Column(name = "BATCH_DEBIT_ENTRY_TOTAL_AMOUNT", nullable = true)
    private String batchDebitEntryTotalAmount;
    @Basic
    @Column(name = "BATCH_CREDIT_ENTRY_TOTAL_AMOUNT", nullable = true)
    private String batchCreditEntryTotalAmount;
    @Basic
    @Column(name = "COMPANYID", nullable = true)
    private String companyid;
    @Basic
    @Column(name = "MESSAGE_AUTH_CODE", nullable = true)
    private String messageAuthCode;
    @Basic
    @Column(name = "BLANK", nullable = true)
    private String blank;
    @Basic
    @Column(name = "RT_NUMBER_ODFI_ID", nullable = true)
    private String rtNumberOdfiId;
    @Basic
    @Column(name = "BATCHNUMBER", nullable = true)
    private String batchnumber;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRecordtype() {
        return recordtype;
    }

    public void setRecordtype(String recordtype) {
        this.recordtype = recordtype;
    }

    public String getServiceclasscode() {
        return serviceclasscode;
    }

    public void setServiceclasscode(String serviceclasscode) {
        this.serviceclasscode = serviceclasscode;
    }

    public String getEntryaddendacount() {
        return entryaddendacount;
    }

    public void setEntryaddendacount(String entryaddendacount) {
        this.entryaddendacount = entryaddendacount;
    }

    public String getEntryhash() {
        return entryhash;
    }

    public void setEntryhash(String entryhash) {
        this.entryhash = entryhash;
    }

    public String getBatchDebitEntryTotalAmount() {
        return batchDebitEntryTotalAmount;
    }

    public void setBatchDebitEntryTotalAmount(String batchDebitEntryTotalAmount) {
        this.batchDebitEntryTotalAmount = batchDebitEntryTotalAmount;
    }

    public String getBatchCreditEntryTotalAmount() {
        return batchCreditEntryTotalAmount;
    }

    public void setBatchCreditEntryTotalAmount(String batchCreditEntryTotalAmount) {
        this.batchCreditEntryTotalAmount = batchCreditEntryTotalAmount;
    }

    public String getCompanyid() {
        return companyid;
    }

    public void setCompanyid(String companyid) {
        this.companyid = companyid;
    }

    public String getMessageAuthCode() {
        return messageAuthCode;
    }

    public void setMessageAuthCode(String messageAuthCode) {
        this.messageAuthCode = messageAuthCode;
    }

    public String getBlank() {
        return blank;
    }

    public void setBlank(String blank) {
        this.blank = blank;
    }

    public String getRtNumberOdfiId() {
        return rtNumberOdfiId;
    }

    public void setRtNumberOdfiId(String rtNumberOdfiId) {
        this.rtNumberOdfiId = rtNumberOdfiId;
    }

    public String getBatchnumber() {
        return batchnumber;
    }

    public void setBatchnumber(String batchnumber) {
        this.batchnumber = batchnumber;
    }

    /*@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BatchControlEntity that = (BatchControlEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(recordtype, that.recordtype) && Objects.equals(serviceclasscode, that.serviceclasscode) && Objects.equals(entryaddendacount, that.entryaddendacount) && Objects.equals(entryhash, that.entryhash) && Objects.equals(batchDebitEntryTotalAmount, that.batchDebitEntryTotalAmount) && Objects.equals(batchCreditEntryTotalAmount, that.batchCreditEntryTotalAmount) && Objects.equals(companyid, that.companyid) && Objects.equals(messageAuthCode, that.messageAuthCode) && Objects.equals(blank, that.blank) && Objects.equals(rtNumberOdfiId, that.rtNumberOdfiId) && Objects.equals(batchnumber, that.batchnumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, recordtype, serviceclasscode, entryaddendacount, entryhash, batchDebitEntryTotalAmount, batchCreditEntryTotalAmount, companyid, messageAuthCode, blank, rtNumberOdfiId, batchnumber);
    }*/
}
