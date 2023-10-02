package com.emagia.ach.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "FILE_CONTROL", schema = "HR")
public class FileControlEntity {
    @Basic
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "ID", nullable = true)
    private Long id;
    @Basic
    @Column(name = "RECORDTYPE", nullable = true)
    private String recordtype;
    @Basic
    @Column(name = "BATCHCOUNT", nullable = true)
    private String batchcount;
    @Basic
    @Column(name = "BLOCKCOUNT", nullable = true)
    private String blockcount;
    @Basic
    @Column(name = "ENTRY_ADDENDA_RECORD_COUNT", nullable = true)
    private String entryAddendaRecordCount;
    @Basic
    @Column(name = "ENTRY_HASH_TOTAL", nullable = true)
    private String entryHashTotal;
    @Basic
    @Column(name = "TOTALDEBIT_ENTRY_AMOUNT", nullable = true)
    private String totaldebitEntryAmount;
    @Basic
    @Column(name = "TOTALCREDIT_ENTRY_AMOUNT", nullable = true)
    private String totalcreditEntryAmount;
    @Basic
    @Column(name = "FILLER", nullable = true)
    private String filler;

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

    public String getBatchcount() {
        return batchcount;
    }

    public void setBatchcount(String batchcount) {
        this.batchcount = batchcount;
    }

    public String getBlockcount() {
        return blockcount;
    }

    public void setBlockcount(String blockcount) {
        this.blockcount = blockcount;
    }

    public String getEntryAddendaRecordCount() {
        return entryAddendaRecordCount;
    }

    public void setEntryAddendaRecordCount(String entryAddendaRecordCount) {
        this.entryAddendaRecordCount = entryAddendaRecordCount;
    }

    public String getEntryHashTotal() {
        return entryHashTotal;
    }

    public void setEntryHashTotal(String entryHashTotal) {
        this.entryHashTotal = entryHashTotal;
    }

    public String getTotaldebitEntryAmount() {
        return totaldebitEntryAmount;
    }

    public void setTotaldebitEntryAmount(String totaldebitEntryAmount) {
        this.totaldebitEntryAmount = totaldebitEntryAmount;
    }

    public String getTotalcreditEntryAmount() {
        return totalcreditEntryAmount;
    }

    public void setTotalcreditEntryAmount(String totalcreditEntryAmount) {
        this.totalcreditEntryAmount = totalcreditEntryAmount;
    }

    public String getFiller() {
        return filler;
    }

    public void setFiller(String filler) {
        this.filler = filler;
    }

   /* @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FileControlEntity that = (FileControlEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(recordtype, that.recordtype) && Objects.equals(batchcount, that.batchcount) && Objects.equals(blockcount, that.blockcount) && Objects.equals(entryAddendaRecordCount, that.entryAddendaRecordCount) && Objects.equals(entryHashTotal, that.entryHashTotal) && Objects.equals(totaldebitEntryAmount, that.totaldebitEntryAmount) && Objects.equals(totalcreditEntryAmount, that.totalcreditEntryAmount) && Objects.equals(filler, that.filler);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, recordtype, batchcount, blockcount, entryAddendaRecordCount, entryHashTotal, totaldebitEntryAmount, totalcreditEntryAmount, filler);
    }*/
}
