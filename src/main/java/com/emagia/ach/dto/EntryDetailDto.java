package com.emagia.ach.dto;

import io.swagger.annotations.ApiModel;

import javax.validation.constraints.Size;
import java.math.BigDecimal;

@ApiModel()
public class EntryDetailDto extends AbstractDto<Long> {
    private Long id;
    @Size(max = 255)
    private String recordtype;
    @Size(max = 255)
    private String transactioncode;
    @Size(max = 255)
    private String receivingDfiRtNumber;
    @Size(max = 255)
    private String rtCheckdigit;
    @Size(max = 255)
    private String receivingDfiAccountNumber;
    @Size(max = 255)
    private BigDecimal amount;
    @Size(max = 255)
    private String individualid;
    @Size(max = 255)
    private String individualname;
    @Size(max = 255)
    private String discretionaryData;
    @Size(max = 255)
    private String addendaRecordIndicator;
    @Size(max = 255)
    private String tracenumber;

    public EntryDetailDto() {
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

    public void setTransactioncode(String transactioncode) {
        this.transactioncode = transactioncode;
    }

    public String getTransactioncode() {
        return this.transactioncode;
    }

    public void setReceivingDfiRtNumber(String receivingDfiRtNumber) {
        this.receivingDfiRtNumber = receivingDfiRtNumber;
    }

    public String getReceivingDfiRtNumber() {
        return this.receivingDfiRtNumber;
    }

    public void setRtCheckdigit(String rtCheckdigit) {
        this.rtCheckdigit = rtCheckdigit;
    }

    public String getRtCheckdigit() {
        return this.rtCheckdigit;
    }

    public void setReceivingDfiAccountNumber(String receivingDfiAccountNumber) {
        this.receivingDfiAccountNumber = receivingDfiAccountNumber;
    }

    public String getReceivingDfiAccountNumber() {
        return this.receivingDfiAccountNumber;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getAmount() {
        return this.amount;
    }

    public void setIndividualid(String individualid) {
        this.individualid = individualid;
    }

    public String getIndividualid() {
        return this.individualid;
    }

    public void setIndividualname(String individualname) {
        this.individualname = individualname;
    }

    public String getIndividualname() {
        return this.individualname;
    }

    public void setDiscretionaryData(String discretionaryData) {
        this.discretionaryData = discretionaryData;
    }

    public String getDiscretionaryData() {
        return this.discretionaryData;
    }

    public void setAddendaRecordIndicator(String addendaRecordIndicator) {
        this.addendaRecordIndicator = addendaRecordIndicator;
    }

    public String getAddendaRecordIndicator() {
        return this.addendaRecordIndicator;
    }

    public void setTracenumber(String tracenumber) {
        this.tracenumber = tracenumber;
    }

    public String getTracenumber() {
        return this.tracenumber;
    }
}