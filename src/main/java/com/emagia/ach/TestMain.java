package com.emagia.ach;

import com.afrunt.jach.document.ACHBatch;
import com.afrunt.jach.document.ACHBatchDetail;
import com.afrunt.jach.document.ACHDocument;
import com.afrunt.jach.domain.*;
import com.afrunt.jach.domain.addenda.GeneralAddendaRecord;
import com.afrunt.jach.logic.ACHWriter;
import com.afrunt.jach.metadata.ACHBeanMetadata;
import com.emagia.ach.achmaker.ACH;
import com.emagia.ach.achmaker.ACHRecordEmagiaFileHeader;
import com.emagia.ach.achmaker.CTXEntryDetail;
import com.emagia.ach.achmaker.CTXEntryDetailUpdated;


import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.time.LocalDate;
import java.util.*;

public class TestMain {
    public static void main(String args[]){
        System.out.println("test *****--********");
        //UTF-8
        ACH ach = new ACH();
        ACHDocument achDocument = new ACHDocument();
        achDocument.setFileHeader(CreateFileHeaderRecord());
        achDocument.setBatches(getBatchRecordList());
        achDocument.setFileControl(createFileControl());
        achDocument.setNumberOfLines(6);
        String result = ach.write(achDocument);

        try {
            FileWriter myWriter = new FileWriter("achtest1.ach");
            myWriter.write(result);
            myWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println(result);//, Charset.forName("UTF-8")
        //ACHDocument achDocument1 = ach.read(result);
    }

    private static List<ACHBatch> getBatchRecordList() {
        List<ACHBatch> batchRecordList = new ArrayList<>();
        List<ACHBatchDetail> batchDetailList = new ArrayList<>();
        ACHBatchDetail entryBatchDetail = new ACHBatchDetail();
        List<AddendaRecord> addendaRecords = new ArrayList<>();
        ACHBatch batchRecord = new ACHBatch();
        //set Batch Record
        //set batch header
        GeneralBatchHeader generalBatchHeader = createGeneralBatchHeader();
        batchRecord.setBatchHeader(generalBatchHeader);
        //set batch detail
        entryBatchDetail.setDetailRecord(createEntryCTXDetail());
        addendaRecords.add(createAddendaRecord());
        entryBatchDetail.setAddendaRecords(addendaRecords);
        batchDetailList.add(entryBatchDetail);
        batchRecord.setDetails(batchDetailList);
        //set batch control
        BatchControl batchControl = createBatchControl();
        batchRecord.setBatchControl(batchControl);
        batchRecordList.add(batchRecord);//add Batch Record
        return batchRecordList;
    }

    private static FileControl createFileControl() {
        FileControl fileControl = new FileControl();
        fileControl.getRecordTypeCode();
        fileControl.setBatchCount(000001);
        fileControl.setBlockCount(000001);
        fileControl.setEntryAddendaCount(00000002);
        fileControl.setEntryHashTotals(Long.valueOf(0012100024));
        fileControl.setTotalDebits(BigDecimal.valueOf(Long.valueOf("000038273434")));
        fileControl.setTotalCredits(BigDecimal.valueOf(Long.valueOf(000000000000)));
        fileControl.setLineNumber(6);
        fileControl.setRecord("file control");
        return fileControl;
    }

    private static AddendaRecord createAddendaRecord() {
        GeneralAddendaRecord addendaRecord = new GeneralAddendaRecord();
        addendaRecord.getRecordTypeCode();
        addendaRecord.getAddendaTypeCode();
        addendaRecord.setPaymentRelatedInformation("EMAGIA*PMT*INV#0012345678*USD100.00*00001*FP*ABCDEFGHIJKLMNOPQRSTUVWXYZ123456789");
        addendaRecord.setAddendaSequenceNumber(0001);
        addendaRecord.setEntryDetailSequenceNumber(Long.valueOf(0000001));
        addendaRecord.setLineNumber(4);
        addendaRecord.setRecord("addenda");
        return addendaRecord;
    }

    private static EntryDetail createEntryCTXDetail() {
        CTXEntryDetailUpdated entryCTXDetail = new CTXEntryDetailUpdated();
        entryCTXDetail.getRecordTypeCode();//1
        entryCTXDetail.setTransactionCode(24);//2
        entryCTXDetail.setReceivingDfiIdentification("12100035");//R/T number /8
        entryCTXDetail.setCheckDigit(Short.valueOf("1"));//1
        entryCTXDetail.setDfiAccountNumber("000123456789     ");//17
        entryCTXDetail.setAmount(BigDecimal.valueOf(00000022.2));//10
        entryCTXDetail.setIdentificationNumber("CUSTID00123    ");//15
        entryCTXDetail.setReceivingCompanyName("0001RATNA PRASAD      ");//Individual Name//22 -6 = 16
        entryCTXDetail.setDiscretionaryData("00");//2
        entryCTXDetail.setAddendaRecordIndicator(Short.valueOf("1"));//1
        entryCTXDetail.setTraceNumber(Long.valueOf("091000010000001"));//15
        entryCTXDetail.setLineNumber(3);
        entryCTXDetail.setRecord("CTXEntryDetail");
        return entryCTXDetail;
    }

    private static BatchControl createBatchControl() {
        BatchControl batchControl = new BatchControl();
        batchControl.getRecordTypeCode();
        batchControl.setServiceClassCode(220);
        batchControl.setEntryAddendaCount(000002);
        batchControl.setEntryHash(BigInteger.valueOf(0012100024));
        batchControl.setTotalDebits(BigDecimal.valueOf(Long.valueOf(000000000000)));
        batchControl.setTotalCredits(BigDecimal.valueOf(Long.valueOf("000038273434")));
        batchControl.setCompanyIdentification("2542049910");
        batchControl.setMessageAuthenticationCode("                   ");
        batchControl.getReserved();
        batchControl.setOriginatingDfiIdentification("09100001");
        batchControl.setBatchNumber(Integer.valueOf(00000001));
        batchControl.setLineNumber(5);
        batchControl.setRecord("batch control");
        return batchControl;

    }

    private static GeneralBatchHeader createGeneralBatchHeader() {
        GeneralBatchHeader generalBatchHeader = new GeneralBatchHeader();
        generalBatchHeader.getRecordTypeCode();
        generalBatchHeader.setServiceClassCode("220");
        generalBatchHeader.setCompanyName("STAPLES CONTRACT");
        generalBatchHeader.setCompanyDiscretionaryData("00000000000000000000");
        generalBatchHeader.setCompanyID("2042896127");
        generalBatchHeader.setStandardEntryClassCode("CTX");
        generalBatchHeader.setCompanyEntryDescription("EMAGIAPMT ");
        Calendar cal = Calendar.getInstance();
        cal.set(2023,8,28,15,30);

        generalBatchHeader.setCompanyDescriptiveDate("SEP 06");
        generalBatchHeader.setEffectiveEntryDate(cal.getTime());
        generalBatchHeader.getSettlementDate();
        generalBatchHeader.getOriginatorStatusCode();
        generalBatchHeader.getOriginatorDFIIdentifier();
        generalBatchHeader.setBatchNumber(00000001);
        generalBatchHeader.setLineNumber(2);
        generalBatchHeader.setRecord("batch header");
        return generalBatchHeader;
    }
    static FileHeader CreateFileHeaderRecord() {
        //ACHRecordEmagiaFileHeader fileHeader = new ACHRecordEmagiaFileHeader();
        FileHeader fileHeader = new FileHeader();
        fileHeader.setPriorityCode("01");
        fileHeader.setImmediateOrigin("2042896127");
        fileHeader.setImmediateDestination(" 091000019");
        Calendar cal = Calendar.getInstance();
        cal.set(2023,8,28,15,30);

        fileHeader.setFileCreationDate(cal.getTime());//"230828"
        fileHeader.setFileCreationTime("1530");
        fileHeader.setFileIdModifier("A");
        fileHeader.getRecordSize();
        fileHeader.setBlockingFactor("10");
        fileHeader.setFormatCode("1");
        fileHeader.setImmediateDestinationName("WELLS FARGO            ");
        fileHeader.setImmediateOriginName("Staples & CommercialLLC");
        fileHeader.setReferenceCode("        ");
        //fileHeader.setFileCreationDate(LocalDate.of(2023,8,28));//"230828"
        //localDateTime.format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        fileHeader.setLineNumber(1);
        String record = "file header";
        fileHeader.setRecord(record);
        return fileHeader;
    }

}
