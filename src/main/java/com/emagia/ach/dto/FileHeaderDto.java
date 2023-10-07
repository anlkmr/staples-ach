package com.emagia.ach.dto;

import io.swagger.annotations.ApiModel;

@ApiModel()
public class FileHeaderDto extends AbstractDto<Long> {
    private Long fileHeaderId;
    private String recordtype;
    private String prioritycode;
    private String rtNumber;
    private String fileidImo;
    private String creationDate;
    private String creationTime;
    private String fileidModifier;
    private String recordsize;
    private String blockingfactor;
    private String formatcode;
    private String originatingBankImdDestName;
    private String companyNameImdOrigName;
    private String referencecode;

    public FileHeaderDto() {
    }

    public void setFileHeaderId(Long fileHeaderId) {
        fileHeaderId = fileHeaderId;
    }

    public Long getFileHeaderId() {
        return fileHeaderId;
    }

    public void setRecordtype(String recordtype) {
        this.recordtype = recordtype;
    }

    public String getRecordtype() {
        return this.recordtype;
    }

    public void setPrioritycode(String prioritycode) {
        this.prioritycode = prioritycode;
    }

    public String getPrioritycode() {
        return this.prioritycode;
    }

    public void setRtNumber(String rtNumber) {
        this.rtNumber = rtNumber;
    }

    public String getRtNumber() {
        return this.rtNumber;
    }

    public void setFileidImo(String fileidImo) {
        this.fileidImo = fileidImo;
    }

    public String getFileidImo() {
        return this.fileidImo;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String getCreationDate() {
        return this.creationDate;
    }

    public void setCreationTime(String creationTime) {
        this.creationTime = creationTime;
    }

    public String getCreationTime() {
        return this.creationTime;
    }

    public void setFileidModifier(String fileidModifier) {
        this.fileidModifier = fileidModifier;
    }

    public String getFileidModifier() {
        return this.fileidModifier;
    }

    public void setRecordsize(String recordsize) {
        this.recordsize = recordsize;
    }

    public String getRecordsize() {
        return this.recordsize;
    }

    public void setBlockingfactor(String blockingfactor) {
        this.blockingfactor = blockingfactor;
    }

    public String getBlockingfactor() {
        return this.blockingfactor;
    }

    public void setFormatcode(String formatcode) {
        this.formatcode = formatcode;
    }

    public String getFormatcode() {
        return this.formatcode;
    }

    public void setOriginatingBankImdDestName(String originatingBankImdDestName) {
        this.originatingBankImdDestName = originatingBankImdDestName;
    }

    public String getOriginatingBankImdDestName() {
        return this.originatingBankImdDestName;
    }

    public void setCompanyNameImdOrigName(String companyNameImdOrigName) {
        this.companyNameImdOrigName = companyNameImdOrigName;
    }

    public String getCompanyNameImdOrigName() {
        return this.companyNameImdOrigName;
    }

    public void setReferencecode(String referencecode) {
        this.referencecode = referencecode;
    }

    public String getReferencecode() {
        return this.referencecode;
    }
}