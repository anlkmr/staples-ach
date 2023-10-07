package com.emagia.ach.dto;

import io.swagger.annotations.ApiModel;

import javax.validation.constraints.Size;
import java.text.ParseException;

@ApiModel()
public class BatchHeaderDto extends AbstractDto<Long> {
    private Long batchHeaderId;
    @Size(max = 255)
    private String recordtype;
    @Size(max = 255)
    private String serviceclasscode;
    @Size(max = 255)
    private String companyNamePayeePayor;
    @Size(max = 255)
    private String companyDiscretionaryData;
    @Size(max = 255)
    private String companyid;
    @Size(max = 255)
    private String seccode;
    @Size(max = 255)
    private String companyEntryDesc;
    @Size(max = 255)
    private String companyDescDate;
    @Size(max = 255)
    private String effectiveEntryDate;
    @Size(max = 255)
    private String settlementDate;
    @Size(max = 255)
    private String originatorStatusCode;
    @Size(max = 255)
    private String rtNumberOdfiId;
    @Size(max = 255)
    private String batchnumber;

    public BatchHeaderDto() {
    }

    public void setBatchHeaderId(Long batchHeaderId) {
        batchHeaderId = batchHeaderId;
    }

    public Long getBatchHeaderId() {
        return batchHeaderId;
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

    public void setCompanyNamePayeePayor(String companyNamePayeePayor) {
        this.companyNamePayeePayor = companyNamePayeePayor;
    }

    public String getCompanyNamePayeePayor() {
        return this.companyNamePayeePayor;
    }

    public void setCompanyDiscretionaryData(String companyDiscretionaryData) {
        this.companyDiscretionaryData = companyDiscretionaryData;
    }

    public String getCompanyDiscretionaryData() {
        return this.companyDiscretionaryData;
    }

    public void setCompanyid(String companyid) {
        this.companyid = companyid;
    }

    public String getCompanyid() {
        return this.companyid;
    }

    public void setSeccode(String seccode) {
        this.seccode = seccode;
    }

    public String getSeccode() {
        return this.seccode;
    }

    public void setCompanyEntryDesc(String companyEntryDesc) {
        this.companyEntryDesc = companyEntryDesc;
    }

    public String getCompanyEntryDesc() {
        return this.companyEntryDesc;
    }

    public void setCompanyDescDate(String companyDescDate) {
        this.companyDescDate = companyDescDate;
    }

    public String getCompanyDescDate() {
        return this.companyDescDate;
    }

    public void setEffectiveEntryDate(String effectiveEntryDate) throws ParseException {
        this.effectiveEntryDate = effectiveEntryDate;

    }

    public String getEffectiveEntryDate() {
        return this.effectiveEntryDate;
    }

    public void setSettlementDate(String settlementDate) {
        this.settlementDate = settlementDate;
    }

    public String getSettlementDate() {
        return this.settlementDate;
    }

    public void setOriginatorStatusCode(String originatorStatusCode) {
        this.originatorStatusCode = originatorStatusCode;
    }

    public String getOriginatorStatusCode() {
        return this.originatorStatusCode;
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