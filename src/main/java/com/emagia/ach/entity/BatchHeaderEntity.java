package com.emagia.ach.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "BATCH_HEADER", schema = "HR")
public class BatchHeaderEntity {

    @Basic
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "BATCH_HEADER_ID", nullable = true)
    private Long batchHeaderId;
    @Basic
    @Column(name = "RECORDTYPE", nullable = true)
    private String recordtype;
    @Basic
    @Column(name = "SERVICECLASSCODE", nullable = true)
    private String serviceclasscode;
    @Basic
    @Column(name = "COMPANY_NAME_PAYEE_PAYOR", nullable = true)
    private String companyNamePayeePayor;
    @Basic
    @Column(name = "COMPANY_DISCRETIONARY_DATA", nullable = true)
    private String companyDiscretionaryData;
    @Basic
    @Column(name = "COMPANYID", nullable = true)
    private String companyid;
    @Basic
    @Column(name = "SECCODE", nullable = true)
    private String seccode;
    @Basic
    @Column(name = "COMPANY_ENTRY_DESC", nullable = true)
    private String companyEntryDesc;
    @Basic
    @Column(name = "COMPANY_DESC_DATE", nullable = true)
    private String companyDescDate;
    @Basic
    @Column(name = "EFFECTIVE_ENTRY_DATE", nullable = true)
    private String effectiveEntryDate;
    @Basic
    @Column(name = "SETTLEMENT_DATE", nullable = true)
    private String settlementDate;
    @Basic
    @Column(name = "ORIGINATOR_STATUS_CODE", nullable = true)
    private String originatorStatusCode;
    @Basic
    @Column(name = "RT_NUMBER_ODFI_ID", nullable = true)
    private String rtNumberOdfiId;
    @Basic
    @Column(name = "BATCHNUMBER", nullable = true)
    private String batchnumber;




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

    public String getCompanyNamePayeePayor() {
        return companyNamePayeePayor;
    }

    public void setCompanyNamePayeePayor(String companyNamePayeePayor) {
        this.companyNamePayeePayor = companyNamePayeePayor;
    }

    public String getCompanyDiscretionaryData() {
        return companyDiscretionaryData;
    }

    public void setCompanyDiscretionaryData(String companyDiscretionaryData) {
        this.companyDiscretionaryData = companyDiscretionaryData;
    }

    public String getCompanyid() {
        return companyid;
    }

    public void setCompanyid(String companyid) {
        this.companyid = companyid;
    }

    public String getSeccode() {
        return seccode;
    }

    public void setSeccode(String seccode) {
        this.seccode = seccode;
    }

    public String getCompanyEntryDesc() {
        return companyEntryDesc;
    }

    public void setCompanyEntryDesc(String companyEntryDesc) {
        this.companyEntryDesc = companyEntryDesc;
    }

    public String getCompanyDescDate() {
        return companyDescDate;
    }

    public void setCompanyDescDate(String companyDescDate) {
        this.companyDescDate = companyDescDate;
    }

    public String getEffectiveEntryDate() {
        return effectiveEntryDate;
    }

    public void setEffectiveEntryDate(String effectiveEntryDate) {
        this.effectiveEntryDate = effectiveEntryDate;
    }

    public String getSettlementDate() {
        return settlementDate;
    }

    public void setSettlementDate(String settlementDate) {
        this.settlementDate = settlementDate;
    }

    public String getOriginatorStatusCode() {
        return originatorStatusCode;
    }

    public void setOriginatorStatusCode(String originatorStatusCode) {
        this.originatorStatusCode = originatorStatusCode;
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

    public Long getBatchHeaderId() {
        return batchHeaderId;
    }

    public void setBatchHeaderId(long batchHeaderId) {
        this.batchHeaderId = batchHeaderId;
    }

   /* @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BatchHeaderEntity that = (BatchHeaderEntity) o;
        return Objects.equals(recordtype, that.recordtype) && Objects.equals(serviceclasscode, that.serviceclasscode) && Objects.equals(companyNamePayeePayor, that.companyNamePayeePayor) && Objects.equals(companyDiscretionaryData, that.companyDiscretionaryData) && Objects.equals(companyid, that.companyid) && Objects.equals(seccode, that.seccode) && Objects.equals(companyEntryDesc, that.companyEntryDesc) && Objects.equals(companyDescDate, that.companyDescDate) && Objects.equals(effectiveEntryDate, that.effectiveEntryDate) && Objects.equals(settlementDate, that.settlementDate) && Objects.equals(originatorStatusCode, that.originatorStatusCode) && Objects.equals(rtNumberOdfiId, that.rtNumberOdfiId) && Objects.equals(batchnumber, that.batchnumber) && Objects.equals(batchHeaderId, that.batchHeaderId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(recordtype, serviceclasscode, companyNamePayeePayor, companyDiscretionaryData, companyid, seccode, companyEntryDesc, companyDescDate, effectiveEntryDate, settlementDate, originatorStatusCode, rtNumberOdfiId, batchnumber, batchHeaderId);
    }*/
}
