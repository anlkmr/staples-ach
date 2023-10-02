package com.emagia.ach.dto;

import io.swagger.annotations.ApiModel;

import javax.validation.constraints.Size;

@ApiModel()
public class FileControlDto extends AbstractDto<Long> {
    private Long id;
    @Size(max = 255)
    private String recordtype;
    @Size(max = 255)
    private String batchcount;
    @Size(max = 255)
    private String blockcount;
    @Size(max = 255)
    private String entryAddendaRecordCount;
    @Size(max = 255)
    private String entryHashTotal;
    @Size(max = 255)
    private String totaldebitEntryAmount;
    @Size(max = 255)
    private String totalcreditEntryAmount;
    @Size(max = 255)
    private String filler;

    public FileControlDto() {
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

    public void setBatchcount(String batchcount) {
        this.batchcount = batchcount;
    }

    public String getBatchcount() {
        return this.batchcount;
    }

    public void setBlockcount(String blockcount) {
        this.blockcount = blockcount;
    }

    public String getBlockcount() {
        return this.blockcount;
    }

    public void setEntryAddendaRecordCount(String entryAddendaRecordCount) {
        this.entryAddendaRecordCount = entryAddendaRecordCount;
    }

    public String getEntryAddendaRecordCount() {
        return this.entryAddendaRecordCount;
    }

    public void setEntryHashTotal(String entryHashTotal) {
        this.entryHashTotal = entryHashTotal;
    }

    public String getEntryHashTotal() {
        return this.entryHashTotal;
    }

    public void setTotaldebitEntryAmount(String totaldebitEntryAmount) {
        this.totaldebitEntryAmount = totaldebitEntryAmount;
    }

    public String getTotaldebitEntryAmount() {
        return this.totaldebitEntryAmount;
    }

    public void setTotalcreditEntryAmount(String totalcreditEntryAmount) {
        this.totalcreditEntryAmount = totalcreditEntryAmount;
    }

    public String getTotalcreditEntryAmount() {
        return this.totalcreditEntryAmount;
    }

    public void setFiller(String filler) {
        this.filler = filler;
    }

    public String getFiller() {
        return this.filler;
    }
}