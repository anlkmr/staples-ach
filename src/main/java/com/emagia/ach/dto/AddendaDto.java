package com.emagia.ach.dto;

import io.swagger.annotations.ApiModel;

import javax.validation.constraints.Size;

@ApiModel()
public class AddendaDto extends AbstractDto<Long> {
    private Long id;
    @Size(max = 255)
    private String recordtype;
    @Size(max = 255)
    private String addendatypecode;
    @Size(max = 255)
    private String paymentInfo;
    @Size(max = 255)
    private String addendaSequenceNumber;
    @Size(max = 255)
    private String entrydetailSeqNumber;

    public AddendaDto() {
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return this.id;
    }

    public void setRecordtype(String recordtype) {
        this.recordtype = recordtype;
    }

    public String getRecordtype() {
        return this.recordtype;
    }

    public void setAddendatypecode(String addendatypecode) {
        this.addendatypecode = addendatypecode;
    }

    public String getAddendatypecode() {
        return this.addendatypecode;
    }

    public void setPaymentInfo(String paymentInfo) {
        this.paymentInfo = paymentInfo;
    }

    public String getPaymentInfo() {
        return this.paymentInfo;
    }

    public void setAddendaSequenceNumber(String addendaSequenceNumber) {
        this.addendaSequenceNumber = addendaSequenceNumber;
    }

    public String getAddendaSequenceNumber() {
        return this.addendaSequenceNumber;
    }

    public void setEntrydetailSeqNumber(String entrydetailSeqNumber) {
        this.entrydetailSeqNumber = entrydetailSeqNumber;
    }

    public String getEntrydetailSeqNumber() {
        return this.entrydetailSeqNumber;
    }
}