package com.emagia.ach.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "ENTRY_DETAIL", schema = "HR")
public class EntryDetailEntity {
    @Basic
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "ID", nullable = true)
    private Long id;
    @Basic
    @Column(name = "RECORDTYPE", nullable = true)
    private String recordtype;
    @Basic
    @Column(name = "TRANSACTIONCODE", nullable = true)
    private String transactioncode;
    @Basic
    @Column(name = "RECEIVING_DFI_RT_NUMBER", nullable = true)
    private String receivingDfiRtNumber;
    @Basic
    @Column(name = "RT_CHECKDIGIT", nullable = true)
    private String rtCheckdigit;
    @Basic
    @Column(name = "RECEIVING_DFI_ACCOUNT_NUMBER", nullable = true)
    private String receivingDfiAccountNumber;
    @Basic
    @Column(name = "AMOUNT", nullable = true)
    private String amount;
    @Basic
    @Column(name = "INDIVIDUALID", nullable = true)
    private String individualid;
    @Basic
    @Column(name = "INDIVIDUALNAME", nullable = true)
    private String individualname;
    @Basic
    @Column(name = "DISCRETIONARY_DATE", nullable = true)
    private String discretionaryDate;
    @Basic
    @Column(name = "ADDENDA_RECORD_INDICATOR", nullable = true)
    private String addendaRecordIndicator;
    @Basic
    @Column(name = "TRACENUMBER", nullable = true)
    private String tracenumber;

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

    public String getTransactioncode() {
        return transactioncode;
    }

    public void setTransactioncode(String transactioncode) {
        this.transactioncode = transactioncode;
    }

    public String getReceivingDfiRtNumber() {
        return receivingDfiRtNumber;
    }

    public void setReceivingDfiRtNumber(String receivingDfiRtNumber) {
        this.receivingDfiRtNumber = receivingDfiRtNumber;
    }

    public String getRtCheckdigit() {
        return rtCheckdigit;
    }

    public void setRtCheckdigit(String rtCheckdigit) {
        this.rtCheckdigit = rtCheckdigit;
    }

    public String getReceivingDfiAccountNumber() {
        return receivingDfiAccountNumber;
    }

    public void setReceivingDfiAccountNumber(String receivingDfiAccountNumber) {
        this.receivingDfiAccountNumber = receivingDfiAccountNumber;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getIndividualid() {
        return individualid;
    }

    public void setIndividualid(String individualid) {
        this.individualid = individualid;
    }

    public String getIndividualname() {
        return individualname;
    }

    public void setIndividualname(String individualname) {
        this.individualname = individualname;
    }

    public String getDiscretionaryDate() {
        return discretionaryDate;
    }

    public void setDiscretionaryDate(String discretionaryDate) {
        this.discretionaryDate = discretionaryDate;
    }

    public String getAddendaRecordIndicator() {
        return addendaRecordIndicator;
    }

    public void setAddendaRecordIndicator(String addendaRecordIndicator) {
        this.addendaRecordIndicator = addendaRecordIndicator;
    }

    public String getTracenumber() {
        return tracenumber;
    }

    public void setTracenumber(String tracenumber) {
        this.tracenumber = tracenumber;
    }

   /* @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EntryDetailEntity that = (EntryDetailEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(recordtype, that.recordtype) && Objects.equals(transactioncode, that.transactioncode) && Objects.equals(receivingDfiRtNumber, that.receivingDfiRtNumber) && Objects.equals(rtCheckdigit, that.rtCheckdigit) && Objects.equals(receivingDfiAccountNumber, that.receivingDfiAccountNumber) && Objects.equals(amount, that.amount) && Objects.equals(individualid, that.individualid) && Objects.equals(individualname, that.individualname) && Objects.equals(discretionaryDate, that.discretionaryDate) && Objects.equals(addendaRecordIndicator, that.addendaRecordIndicator) && Objects.equals(tracenumber, that.tracenumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, recordtype, transactioncode, receivingDfiRtNumber, rtCheckdigit, receivingDfiAccountNumber, amount, individualid, individualname, discretionaryDate, addendaRecordIndicator, tracenumber);
    }*/
}
