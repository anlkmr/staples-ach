package com.emagia.ach.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "ADDENDA", schema = "HR")
public class AddendaEntity {

    @Basic
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "ID", nullable = true)
    private long id;
    @Basic
    @Column(name = "RECORDTYPE", nullable = true)
    private String recordtype;
    @Basic
    @Column(name = "ADDENDATYPECODE", nullable = true)
    private String addendatypecode;
    @Basic
    @Column(name = "PAYMENT_INFO", nullable = true)
    private String paymentInfo;
    @Basic
    @Column(name = "ADDENDA_SEQUENCE_NUMBER", nullable = true)
    private String addendaSequenceNumber;
    @Basic
    @Column(name = "ENTRYDETAIL_SEQ_NUMBER", nullable = true)
    private String entrydetailSeqNumber;

    public String getRecordtype() {
        return recordtype;
    }

    public void setRecordtype(String recordtype) {
        this.recordtype = recordtype;
    }

    public String getAddendatypecode() {
        return addendatypecode;
    }

    public void setAddendatypecode(String addendatypecode) {
        this.addendatypecode = addendatypecode;
    }

    public String getPaymentInfo() {
        return paymentInfo;
    }

    public void setPaymentInfo(String paymentInfo) {
        this.paymentInfo = paymentInfo;
    }

    public String getAddendaSequenceNumber() {
        return addendaSequenceNumber;
    }

    public void setAddendaSequenceNumber(String addendaSequenceNumber) {
        this.addendaSequenceNumber = addendaSequenceNumber;
    }

    public String getEntrydetailSeqNumber() {
        return entrydetailSeqNumber;
    }

    public void setEntrydetailSeqNumber(String entrydetailSeqNumber) {
        this.entrydetailSeqNumber = entrydetailSeqNumber;
    }

    /*@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddendaEntity that = (AddendaEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(recordtype, that.recordtype) && Objects.equals(addendatypecode, that.addendatypecode) && Objects.equals(paymentInfo, that.paymentInfo) && Objects.equals(addendaSequenceNumber, that.addendaSequenceNumber) && Objects.equals(entrydetailSeqNumber, that.entrydetailSeqNumber);
    }*/

   /* @Override
    public int hashCode() {
        return Objects.hash(id, recordtype, addendatypecode, paymentInfo, addendaSequenceNumber, entrydetailSeqNumber);
    }*/

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
