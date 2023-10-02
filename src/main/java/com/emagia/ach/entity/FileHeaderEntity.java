package com.emagia.ach.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "FILE_HEADER", schema = "HR")
public class FileHeaderEntity {

    @Basic
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "FILE_HEADER_ID", nullable = true)
    private Long fileHeaderId;

    @Basic
    @Column(name = "RECORDTYPE", nullable = true)
    private String recordtype;
    @Basic
    @Column(name = "PRIORITYCODE", nullable = true)
    private String prioritycode;
    @Basic
    @Column(name = "RT_NUMBER", nullable = true)
    private String rtNumber;
    @Basic
    @Column(name = "FILEID_IMO", nullable = true)
    private String fileidImo;
    @Basic
    @Column(name = "CREATION_DATE", nullable = true)
    private String creationDate;
    @Basic
    @Column(name = "CREATION_TIME", nullable = true)
    private String creationTime;
    @Basic
    @Column(name = "FILEID_MODIFIER", nullable = true)
    private String fileidModifier;
    @Basic
    @Column(name = "RECORDSIZE", nullable = true)
    private String recordsize;
    @Basic
    @Column(name = "BLOCKINGFACTOR", nullable = true)
    private String blockingfactor;
    @Basic
    @Column(name = "FORMATCODE", nullable = true)
    private String formatcode;
    @Basic
    @Column(name = "ORIGINATING_BANK_IMD_DEST_NAME", nullable = true)
    private String originatingBankImdDestName;
    @Basic
    @Column(name = "COMPANY_NAME_IMD_ORIG_NAME", nullable = true)
    private String companyNameImdOrigName;
    @Basic
    @Column(name = "REFERENCECODE", nullable = true)
    private String referencecode;


    public String getRecordtype() {
        return recordtype;
    }

    public void setRecordtype(String recordtype) {
        this.recordtype = recordtype;
    }

    public String getPrioritycode() {
        return prioritycode;
    }

    public void setPrioritycode(String prioritycode) {
        this.prioritycode = prioritycode;
    }

    public String getRtNumber() {
        return rtNumber;
    }

    public void setRtNumber(String rtNumber) {
        this.rtNumber = rtNumber;
    }

    public String getFileidImo() {
        return fileidImo;
    }

    public void setFileidImo(String fileidImo) {
        this.fileidImo = fileidImo;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(String creationTime) {
        this.creationTime = creationTime;
    }

    public String getFileidModifier() {
        return fileidModifier;
    }

    public void setFileidModifier(String fileidModifier) {
        this.fileidModifier = fileidModifier;
    }

    public String getRecordsize() {
        return recordsize;
    }

    public void setRecordsize(String recordsize) {
        this.recordsize = recordsize;
    }

    public String getBlockingfactor() {
        return blockingfactor;
    }

    public void setBlockingfactor(String blockingfactor) {
        this.blockingfactor = blockingfactor;
    }

    public String getFormatcode() {
        return formatcode;
    }

    public void setFormatcode(String formatcode) {
        this.formatcode = formatcode;
    }

    public String getOriginatingBankImdDestName() {
        return originatingBankImdDestName;
    }

    public void setOriginatingBankImdDestName(String originatingBankImdDestName) {
        this.originatingBankImdDestName = originatingBankImdDestName;
    }

    public String getCompanyNameImdOrigName() {
        return companyNameImdOrigName;
    }

    public void setCompanyNameImdOrigName(String companyNameImdOrigName) {
        this.companyNameImdOrigName = companyNameImdOrigName;
    }

    public String getReferencecode() {
        return referencecode;
    }

    public void setReferencecode(String referencecode) {
        this.referencecode = referencecode;
    }

    public Long getFileHeaderId() {
        return fileHeaderId;
    }

    public void setFileHeaderId(Long fileHeaderId) {
        this.fileHeaderId = fileHeaderId;
    }

   /* @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FileHeaderEntity that = (FileHeaderEntity) o;
        return Objects.equals(recordtype, that.recordtype) && Objects.equals(prioritycode, that.prioritycode) && Objects.equals(rtNumber, that.rtNumber) && Objects.equals(fileidImo, that.fileidImo) && Objects.equals(creationDate, that.creationDate) && Objects.equals(creationTime, that.creationTime) && Objects.equals(fileidModifier, that.fileidModifier) && Objects.equals(recordsize, that.recordsize) && Objects.equals(blockingfactor, that.blockingfactor) && Objects.equals(formatcode, that.formatcode) && Objects.equals(originatingBankImdDestName, that.originatingBankImdDestName) && Objects.equals(companyNameImdOrigName, that.companyNameImdOrigName) && Objects.equals(referencecode, that.referencecode) && Objects.equals(fileHeaderId, that.fileHeaderId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(recordtype, prioritycode, rtNumber, fileidImo, creationDate, creationTime, fileidModifier, recordsize, blockingfactor, formatcode, originatingBankImdDestName, companyNameImdOrigName, referencecode, fileHeaderId);
    }*/
}
