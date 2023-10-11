package com.emagia.ach;

import com.afrunt.jach.document.ACHBatch;
import com.afrunt.jach.document.ACHBatchDetail;
import com.afrunt.jach.document.ACHDocument;
import com.afrunt.jach.domain.*;
import com.afrunt.jach.domain.addenda.GeneralAddendaRecord;
import com.emagia.ach.achmaker.ACH;
import com.emagia.ach.achmaker.CTXEntryDetailUpdated;
import com.emagia.ach.entity.*;
import com.emagia.ach.repository.*;
import com.emagia.ach.service.Achfileservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@Service
public class AchfileserviceImpl implements Achfileservice {
    @Autowired
    private ACH ach;
    @Autowired
    private FileHeaderRepository fileHeaderRepository;
    @Autowired
    private BatchHeaderRepository batchHeaderRepository;
    @Autowired
    private EntryDetailRepository entryDetailRepository;
    @Autowired
    private AddendaRepository addendaRepository;
    @Autowired
    private BatchControlRepository batchControlRepository;
    @Autowired
    private FileControlRepository fileControlRepository;
    @Override
    public String createOSStringAchCTXDoc() {
        return ach.write(createAchDocument());
    }
    private ACHDocument createAchDocument() {
        ACHDocument achDocument = new ACHDocument();
        achDocument.setFileHeader(CreateFileHeaderRecord("2042896127"));
        achDocument.setBatches(getBatchRecordList());
        achDocument.setFileControl(createFileControl());
        achDocument.setNumberOfLines(6);
        return achDocument;
    }
    private List<ACHBatch> getBatchRecordList() {
        List<ACHBatch> batchRecordList = new ArrayList<>();
        List<ACHBatchDetail> batchDetailList = new ArrayList<>();
        ACHBatchDetail entryBatchDetail = new ACHBatchDetail();
        List<AddendaRecord> addendaRecords = new ArrayList<>();
        ACHBatch batchRecord = new ACHBatch();
        //set Batch Record
        //set batch header
        GeneralBatchHeader generalBatchHeader = createGeneralBatchHeader("2042896127");
        batchRecord.setBatchHeader(generalBatchHeader);
        //set batch detail
        entryBatchDetail.setDetailRecord(createEntryCTXDetail());
        addendaRecords.add(createAddendaRecord());
        entryBatchDetail.setAddendaRecords(addendaRecords);
        batchDetailList.add(entryBatchDetail);
        //batchDetailList.add(entryBatchDetail);
        batchRecord.setDetails(batchDetailList);
        //set batch control
        BatchControl batchControl = createBatchControl();
        batchRecord.setBatchControl(batchControl);
        batchRecordList.add(batchRecord);//add Batch Record
        //batchRecordList.add(batchRecord);
        return batchRecordList;
    }
    private FileControl createFileControl() {
        Optional<FileControlEntity> fileControlEntityOptional = fileControlRepository.findById(1L);
        FileControl fileControl = new FileControl();
        fileControl.getRecordTypeCode();
        if(fileControlEntityOptional.isPresent()) {
            FileControlEntity fileControlEntity = fileControlEntityOptional.get();

            fileControl.setBatchCount(Integer.valueOf(fileControlEntity.getBatchcount()));
            //fileControl.setBatchCount(000001);
            fileControl.setBlockCount(Integer.valueOf(fileControlEntity.getBlockcount()));
            //fileControl.setBlockCount(000001);
            fileControl.setEntryAddendaCount(Integer.valueOf(fileControlEntity.getEntryAddendaRecordCount()));
            //fileControl.setEntryAddendaCount(00000002);
            fileControl.setEntryHashTotals(Long.valueOf(fileControlEntity.getEntryHashTotal()));
            //fileControl.setEntryHashTotals(Long.valueOf(0012100024));
            fileControl.setTotalDebits(BigDecimal.valueOf(Long.valueOf(fileControlEntity.getTotaldebitEntryAmount())));
            //fileControl.setTotalDebits(BigDecimal.valueOf(Long.valueOf("000038273434")));
            fileControl.setTotalCredits(BigDecimal.valueOf(Long.valueOf(fileControlEntity.getTotalcreditEntryAmount())));
            //fileControl.setTotalCredits(BigDecimal.valueOf(Long.valueOf(000000000000)));
        }
        fileControl.setLineNumber(6);
        fileControl.setRecord("file control");
        return fileControl;
    }
    private AddendaRecord createAddendaRecord() {
        Optional<AddendaEntity> addendaEntityOptional = addendaRepository.findById(1L);
        GeneralAddendaRecord addendaRecord = new GeneralAddendaRecord();
        addendaRecord.getRecordTypeCode();
        addendaRecord.getAddendaTypeCode();
        if (addendaEntityOptional.isPresent()) {
            AddendaEntity addendaEntity = addendaEntityOptional.get();
            addendaRecord.setPaymentRelatedInformation(addendaEntity.getPaymentInfo());
            //addendaRecord.setPaymentRelatedInformation("EMAGIA*PMT*INV#0012345678*USD100.00*00001*FP*ABCDEFGHIJKLMNOPQRSTUVWXYZ123456789");
            addendaRecord.setAddendaSequenceNumber(Integer.valueOf(addendaEntity.getAddendaSequenceNumber()));
            //addendaRecord.setAddendaSequenceNumber(0001);
            addendaRecord.setEntryDetailSequenceNumber(Long.valueOf(addendaEntity.getEntrydetailSeqNumber()));
            //addendaRecord.setEntryDetailSequenceNumber(Long.valueOf(0000001));
        }
        addendaRecord.setLineNumber(4);
        addendaRecord.setRecord("addenda");
        return addendaRecord;
    }
    private EntryDetail createEntryCTXDetail() {
        Optional<EntryDetailEntity> entryDetailEntityOptional = entryDetailRepository.findById(3L);
        CTXEntryDetailUpdated entryCTXDetail = new CTXEntryDetailUpdated();
        entryCTXDetail.getRecordTypeCode();//1
        if (entryDetailEntityOptional.isPresent()) {
            EntryDetailEntity entryDetailEntity = entryDetailEntityOptional.get();
            entryCTXDetail.setTransactionCode(Integer.valueOf(entryDetailEntity.getTransactioncode()));//2
            //entryCTXDetail.setTransactionCode(24);//2
            entryCTXDetail.setReceivingDfiIdentification(entryDetailEntity.getReceivingDfiRtNumber());//R/T number /8
            //entryCTXDetail.setReceivingDfiIdentification("12100035");//R/T number /8
            entryCTXDetail.setCheckDigit(Short.valueOf(entryDetailEntity.getRtCheckdigit()));//1
            //entryCTXDetail.setCheckDigit(Short.valueOf("1"));//1
            entryCTXDetail.setDfiAccountNumber(entryDetailEntity.getReceivingDfiAccountNumber());//17
            //entryCTXDetail.setDfiAccountNumber("000123456789     ");//17
            entryCTXDetail.setAmount(entryDetailEntity.getAmount());//10
            //entryCTXDetail.setAmount(BigDecimal.valueOf(00000022.2));//10
            entryCTXDetail.setIdentificationNumber(entryDetailEntity.getIndividualid());//15
            //entryCTXDetail.setIdentificationNumber("CUSTID00123    ");//15
            entryCTXDetail.setReceivingCompanyName(entryDetailEntity.getIndividualname());//Individual Name//22 -6 = 16
            //entryCTXDetail.setReceivingCompanyName("0001RATNA PRASAD      ");//Individual Name//22 -6 = 16
            entryCTXDetail.setDiscretionaryData(entryDetailEntity.getDiscretionaryData());//2
            //entryCTXDetail.setDiscretionaryData("00");//2
            entryCTXDetail.setAddendaRecordIndicator(Short.valueOf(entryDetailEntity.getAddendaRecordIndicator()));//1
            //entryCTXDetail.setAddendaRecordIndicator(Short.valueOf("1"));//1
            entryCTXDetail.setTraceNumber(Long.valueOf(entryDetailEntity.getTracenumber()));//15
            //entryCTXDetail.setTraceNumber(Long.valueOf("091000010000001"));//15
        }
        entryCTXDetail.setLineNumber(3);
        entryCTXDetail.setRecord("CTXEntryDetail");
        return entryCTXDetail;
    }
    private BatchControl createBatchControl() {
        Optional<BatchControlEntity> batchControlEntityOptional = batchControlRepository.findById(2L);
        BatchControl batchControl = new BatchControl();
        if (batchControlEntityOptional.isPresent()) {
            BatchControlEntity batchControlEntity = batchControlEntityOptional.get();
            batchControl.getRecordTypeCode();
            batchControl.setServiceClassCode(Integer.valueOf(batchControlEntity.getServiceclasscode()));
            //batchControl.setServiceClassCode(220);
            batchControl.setEntryAddendaCount(Integer.valueOf(batchControlEntity.getEntryaddendacount()));
            //batchControl.setEntryAddendaCount(000002);
            batchControl.setEntryHash(BigInteger.valueOf(Long.parseLong(batchControlEntity.getEntryhash())));
            //batchControl.setEntryHash(BigInteger.valueOf(0012100024));
            batchControl.setTotalDebits(BigDecimal.valueOf(Long.valueOf(batchControlEntity.getBatchDebitEntryTotalAmount())));
            //batchControl.setTotalDebits(BigDecimal.valueOf(Long.valueOf(000000000000)));
            batchControl.setTotalCredits(BigDecimal.valueOf(Long.valueOf(batchControlEntity.getBatchCreditEntryTotalAmount())));
            //batchControl.setTotalCredits(BigDecimal.valueOf(Long.valueOf("000038273434")));
            batchControl.setCompanyIdentification(batchControlEntity.getCompanyid());
            //batchControl.setCompanyIdentification("2542049910");
            batchControl.setMessageAuthenticationCode(batchControlEntity.getMessageAuthCode());
            //batchControl.setMessageAuthenticationCode("                   ");
            batchControl.getReserved();
            batchControl.setOriginatingDfiIdentification(batchControlEntity.getRtNumberOdfiId());
            //batchControl.setOriginatingDfiIdentification("09100001");
            batchControl.setBatchNumber(Integer.valueOf(batchControlEntity.getBatchnumber()));
            //batchControl.setBatchNumber(Integer.valueOf(0000001));
        }
        batchControl.setLineNumber(5);
        batchControl.setRecord("batch control");
        return batchControl;
    }
    private GeneralBatchHeader createGeneralBatchHeader(String companyId) {
        Optional<BatchHeaderEntity> batchHeaderEntityOptional = batchHeaderRepository.findByCompanyid(companyId);
        GeneralBatchHeader generalBatchHeader = new GeneralBatchHeader();
        if (batchHeaderEntityOptional.isPresent()) {
            BatchHeaderEntity batchHeaderEntity = batchHeaderEntityOptional.get();
            generalBatchHeader.getRecordTypeCode();
            generalBatchHeader.setServiceClassCode(batchHeaderEntity.getServiceclasscode());
            //generalBatchHeader.setServiceClassCode("220");
            generalBatchHeader.setCompanyName(batchHeaderEntity.getCompanyNamePayeePayor());
            //generalBatchHeader.setCompanyName("STAPLES CONTRACT");
            generalBatchHeader.setCompanyDiscretionaryData(batchHeaderEntity.getCompanyDiscretionaryData());
            //generalBatchHeader.setCompanyDiscretionaryData("00000000000000000000");
            generalBatchHeader.setCompanyID(batchHeaderEntity.getCompanyid());
            //generalBatchHeader.setCompanyID("2042896127");
            generalBatchHeader.setStandardEntryClassCode(batchHeaderEntity.getSeccode());
            //generalBatchHeader.setStandardEntryClassCode("CTX");
            generalBatchHeader.setCompanyEntryDescription(batchHeaderEntity.getCompanyEntryDesc());
            //generalBatchHeader.setCompanyEntryDescription("EMAGIAPMT ");
            Calendar cal = Calendar.getInstance();
            //cal.set(2023,8,28,15,30);

            //generalBatchHeader.setCompanyDescriptiveDate(String.valueOf(cal.get(Calendar.MONTH)) + String.valueOf(cal.get(Calendar.DAY_OF_MONTH)) + String.valueOf(cal.get(Calendar.YEAR)));
            generalBatchHeader.setCompanyDescriptiveDate(batchHeaderEntity.getCompanyDescDate());
            generalBatchHeader.setEffectiveEntryDate(batchHeaderEntity.getEffectiveEntryDate());
            //generalBatchHeader.setEffectiveEntryDate(cal.getTime());
            generalBatchHeader.getSettlementDate();
            generalBatchHeader.setOriginatorStatusCode(batchHeaderEntity.getOriginatorStatusCode());
            //generalBatchHeader.setOriginatorStatusCode("1");
            generalBatchHeader.setOriginatorDFIIdentifier(batchHeaderEntity.getRtNumberOdfiId());
            //generalBatchHeader.setOriginatorDFIIdentifier("09100001");
            generalBatchHeader.setBatchNumber(Integer.valueOf(batchHeaderEntity.getBatchnumber()));
            //generalBatchHeader.setBatchNumber(00000001);
        }
        generalBatchHeader.setLineNumber(2);
        generalBatchHeader.setRecord("batch header");
        return generalBatchHeader;
    }
    private FileHeader CreateFileHeaderRecord(String fileID) {
        //ACHRecordEmagiaFileHeader fileHeader = new ACHRecordEmagiaFileHeader();
        Optional<FileHeaderEntity> fileHeaderEntityOptional = fileHeaderRepository.findByFileidImo(fileID);
        FileHeader fileHeader = new FileHeader();
        if (fileHeaderEntityOptional.isPresent()) {
            FileHeaderEntity fileHeaderEntity = fileHeaderEntityOptional.get();
            fileHeader.setPriorityCode(fileHeaderEntity.getPrioritycode());
            //fileHeader.setPriorityCode("01");
            fileHeader.setImmediateDestination(fileHeaderEntity.getRtNumber());
            //fileHeader.setImmediateDestination(" 091000019");
            fileHeader.setImmediateOrigin(fileHeaderEntity.getFileidImo());
            //fileHeader.setImmediateOrigin("2042896127");
            Calendar cal = Calendar.getInstance();
            fileHeader.setFileCreationDate(cal.getTime());//"230828"
            fileHeader.setFileCreationTime(String.valueOf(cal.get(Calendar.HOUR_OF_DAY)) + String.valueOf(cal.get(Calendar.MINUTE)));
            fileHeader.setFileIdModifier(fileHeaderEntity.getFileidModifier());
            //fileHeader.setFileIdModifier("A");
            fileHeader.getRecordSize();
            //fileHeader.getRecordSize();
            fileHeader.setBlockingFactor(fileHeaderEntity.getBlockingfactor());
            //fileHeader.setBlockingFactor("10");
            fileHeader.setFormatCode(fileHeaderEntity.getFormatcode());
            //fileHeader.setFormatCode("1");
            fileHeader.setImmediateDestinationName(fileHeaderEntity.getOriginatingBankImdDestName());
            //fileHeader.setImmediateDestinationName("WELLS FARGO            ");
            fileHeader.setImmediateOriginName(fileHeaderEntity.getCompanyNameImdOrigName());
            //fileHeader.setImmediateOriginName("Staples & CommercialLLC");
            fileHeader.setReferenceCode(fileHeaderEntity.getReferencecode());
            //fileHeader.setReferenceCode("        ");
        }
        //fileHeader.setFileCreationDate(LocalDate.of(2023,8,28));//"230828"
        //localDateTime.format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        fileHeader.setLineNumber(1);
        String record = "file header";
        fileHeader.setRecord(record);
        return fileHeader;
    }

    //@Scheduled(cron="#{@getCronValue}")
    public void runEvey5Minutes() {
        System.out.println("#############################Current time is :: " + LocalDate.now());
        //createOSStringAchCTXDoc();
        try {
            FileWriter myWriter = new FileWriter("achtest1.ach");
            myWriter.write(createOSStringAchCTXDoc());
            myWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
