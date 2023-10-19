package com.emagia.ach;

import com.afrunt.jach.document.ACHBatch;
import com.afrunt.jach.document.ACHBatchDetail;
import com.afrunt.jach.document.ACHDocument;
import com.afrunt.jach.domain.*;
import com.afrunt.jach.domain.addenda.GeneralAddendaRecord;
import com.emagia.ach.achmaker.ACH;
import com.emagia.ach.achmaker.ACHDocumentUpdated;
import com.emagia.ach.achmaker.CTXEntryDetailUpdated;
import com.emagia.ach.entity.*;
import com.emagia.ach.entity.staples_emagia.PaymentsCaptureBO;
import com.emagia.ach.repository.*;
import com.emagia.ach.service.Achfileservice;
import com.emagia.ach.staples_emagia.repository.StaplesEmagiaMISCRepository;
import com.emagia.ach.utils.AchStringUtil;
import com.emagia.ach.utils.AchUtils;
import com.emagia.ach.utils.BU;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
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
    @Autowired
    private StaplesEmagiaMISCRepository staplesEmagiaMISCRepository;


    private int entrySequenceNumber;

    private final String RT_Number_WellsFargo = "09100001";
    private int totalEntryAddendaCount;
    private int entryHash;
    private BigDecimal entryTotalDebits;
    private String batchHeaderCompanyID;
    private int batchNumber;
    private int blockCount;

    @Override
    public String createOSStringAchCTXDoc() {

        Optional<List<FileHeaderEntity>> fileHeaderEntityOptional = Optional.of(fileHeaderRepository.findAll());
        fileHeaderEntityOptional.ifPresent(fileHeaderEntities -> fileHeaderEntities.forEach(entity -> achFileWriter(entity, entity.getCompanyNameImdOrigName())));
        return "success";
    }


    void achFileWriter(FileHeaderEntity entity, String companyNameImdOrigName) {
        FileWriter myWriter = null;
        try {
            myWriter = new FileWriter("achtest1" + companyNameImdOrigName + ".ach");

            myWriter.write(ach.write(createAchDocument(entity)));
            myWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    private ACHDocumentUpdated createAchDocument(FileHeaderEntity entity) {
        entrySequenceNumber = 0;
        batchNumber = 0;
        blockCount = 0;
        ACHDocumentUpdated achDocument = new ACHDocumentUpdated();
        //callTest();
        achDocument.setFileHeader(CreateFileHeaderRecord(entity.getFileidImo()));
        achDocument.setBatches(getBatchRecordList(entity));
        achDocument.setFileControl(createFileControl());
        //AchUtils.numberOfBlockingFileRecords(blockCount);
        //achDocument.addBlockingFileControlRecords(AchUtils.numberOfBlockingFileRecords(blockCount));
        achDocument.setNumberOfLines(6);
        return achDocument;
    }

    private void callTest() {

        Optional<List<PaymentsCaptureBO>> entryDetailsPayments = staplesEmagiaMISCRepository.getEntryDetails_Payments("023", "ACH");
        System.out.println(entryDetailsPayments);
        //entryDetailsPayments.forEach(obj -> System.out.println(entryDetailsPayments));
    }

    private List<ACHBatch> getBatchRecordList(FileHeaderEntity entity) {
        List<ACHBatch> batchRecordList = new ArrayList<>();
        ++batchNumber;
        //ACHBatchDetail entryBatchDetail = new ACHBatchDetail();
        //List<AddendaRecord> addendaRecords = new ArrayList<>();
        ACHBatch batchRecord = new ACHBatch();
        //set Batch Record
        //set batch header
        GeneralBatchHeader generalBatchHeader = createGeneralBatchHeader(entity.getFileidImo());
        batchRecord.setBatchHeader(generalBatchHeader);
        //set batch detail

        //batchDetailList.add(entryBatchDetail);
        batchRecord.setDetails(createBatchDetailList(String.valueOf(BU.valueOf((generalBatchHeader.getCompanyName()).replaceAll(" ", "")))));
        //set batch control
        BatchControl batchControl = createBatchControl();
        batchRecord.setBatchControl(batchControl);
        batchRecordList.add(batchRecord);//add Batch Record
        //batchRecordList.add(batchRecord);
        return batchRecordList;
    }

    private List<ACHBatchDetail> createBatchDetailList(String BU) {
        entryHash = 0;
        entryTotalDebits = new BigDecimal(0);
        List<ACHBatchDetail> batchDetailList = new ArrayList<>();
        Optional<EntryDetailEntity> entryDetailEntityOptional = entryDetailRepository.findById(3L);
        Optional<List<PaymentsCaptureBO>> entryDetailsPaymentsOptional = staplesEmagiaMISCRepository.getEntryDetails_Payments(BU, "ACH");
        if (entryDetailsPaymentsOptional.isPresent() && entryDetailEntityOptional.isPresent()) {
            EntryDetailEntity entryDetailEntity = entryDetailEntityOptional.get();
            List<PaymentsCaptureBO> paymentsCaptureBOSlist = entryDetailsPaymentsOptional.get();
            paymentsCaptureBOSlist.forEach(paymentsCaptureBOS -> batchDetailList.add(createBatchDetail(entryDetailEntity, paymentsCaptureBOS)));

        }
        //batchDetailList.add(createEntryBatchDetail());
        return batchDetailList;
    }

    private ACHBatchDetail createBatchDetail(EntryDetailEntity entryDetailEntity, PaymentsCaptureBO paymentsCaptureBOS) {
        blockCount++;
        ACHBatchDetail entryBatchDetail = new ACHBatchDetail();
        List<AddendaRecord> addendaRecords = new ArrayList<>();
        int addendaSequenceNumber = 0;

        String entryDetailTraceNumber;
        List<String> tracenumberBuilderList = new ArrayList<>();
        entrySequenceNumber++;
        tracenumberBuilderList.add(RT_Number_WellsFargo);
        String last7TraceNumber = AchStringUtil.leftPad(String.valueOf(entrySequenceNumber), 7, "0");
        tracenumberBuilderList.add(last7TraceNumber);
        entryDetailTraceNumber = AchStringUtil.join(tracenumberBuilderList, "");

        entryBatchDetail.setDetailRecord(createEntryCTXDetailSTAPLES(entryDetailEntity, paymentsCaptureBOS, entryDetailTraceNumber));
        for(int i=0;i<=0;i++){
            addendaRecords.add(createAddendaRecord(last7TraceNumber, ++addendaSequenceNumber));
        }

        entryBatchDetail.setAddendaRecords(addendaRecords);
        return entryBatchDetail;
    }

    private FileControl createFileControl() {
        blockCount++;
        Optional<FileControlEntity> fileControlEntityOptional = fileControlRepository.findById(1L);
        FileControl fileControl = new FileControl();
        fileControl.getRecordTypeCode();
        if (fileControlEntityOptional.isPresent()) {
            FileControlEntity fileControlEntity = fileControlEntityOptional.get();

            //fileControl.setBatchCount(Integer.valueOf(fileControlEntity.getBatchcount()));
            fileControl.setBatchCount(batchNumber);
            //fileControl.setBlockCount(Integer.valueOf(fileControlEntity.getBlockcount()));
            fileControl.setBlockCount(AchUtils.roundBy10(blockCount));
            //fileControl.setEntryAddendaCount(Integer.valueOf(fileControlEntity.getEntryAddendaRecordCount()));
            fileControl.setEntryAddendaCount(totalEntryAddendaCount);
            //fileControl.setEntryHashTotals(Long.valueOf(fileControlEntity.getEntryHashTotal()));
            fileControl.setEntryHashTotals(Long.valueOf(entryHash));
            //fileControl.setTotalDebits(BigDecimal.valueOf(Long.valueOf(fileControlEntity.getTotaldebitEntryAmount())));
            fileControl.setTotalDebits(entryTotalDebits);
            //fileControl.setTotalCredits(BigDecimal.valueOf(Long.valueOf(fileControlEntity.getTotalcreditEntryAmount())));
            fileControl.setTotalCredits(new BigDecimal(0));
        }
        fileControl.setLineNumber(6);
        fileControl.setRecord("file control");
        return fileControl;
    }

    private AddendaRecord createAddendaRecord(String last7TraceNumber, int addendaSequenceNumber) {
        blockCount++;
        Optional<AddendaEntity> addendaEntityOptional = addendaRepository.findById(1L);
        GeneralAddendaRecord addendaRecord = new GeneralAddendaRecord();
        addendaRecord.getRecordTypeCode();
        addendaRecord.getAddendaTypeCode();
        if (addendaEntityOptional.isPresent()) {
            AddendaEntity addendaEntity = addendaEntityOptional.get();
            addendaRecord.setPaymentRelatedInformation(addendaEntity.getPaymentInfo());
            //addendaRecord.setPaymentRelatedInformation("EMAGIA*PMT*INV#0012345678*USD100.00*00001*FP*ABCDEFGHIJKLMNOPQRSTUVWXYZ123456789");
            addendaRecord.setAddendaSequenceNumber(addendaSequenceNumber);
            //addendaRecord.setAddendaSequenceNumber(0001);
            addendaRecord.setEntryDetailSequenceNumber(Long.valueOf(last7TraceNumber));
            //addendaRecord.setEntryDetailSequenceNumber(Long.valueOf(0000001));
        }
        addendaRecord.setLineNumber(4);
        addendaRecord.setRecord("addenda");
        ++totalEntryAddendaCount;
        return addendaRecord;
    }

    private EntryDetail createEntryCTXDetail() {
        Optional<EntryDetailEntity> entryDetailEntityOptional = entryDetailRepository.findById(3L);
        //List<PaymentsCaptureBO[]> entryDetailsPayments = staplesEmagiaMISCRepository.getEntryDetails_Payments("023", "ACH");

        CTXEntryDetailUpdated entryCTXDetail = new CTXEntryDetailUpdated();
        entryCTXDetail.getRecordTypeCode();//1
        if (entryDetailEntityOptional.isPresent()) {
            EntryDetailEntity entryDetailEntity = entryDetailEntityOptional.get();
            entryCTXDetail.setTransactionCode(Integer.valueOf(entryDetailEntity.getTransactioncode()));//2
            //entryCTXDetail.setTransactionCode(24);//2
            entryCTXDetail.setReceivingDfiIdentification(entryDetailEntity.getReceivingDfiRtNumber());//R/T number /8
            //entryCTXDetail.setReceivingDfiIdentification("12100035");//R/T number /8
            entryCTXDetail.setCheckDigit((short) AchUtils.calculateCheckDigit(entryDetailEntity.getReceivingDfiRtNumber()));//1
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

    private EntryDetail createEntryCTXDetailSTAPLES(EntryDetailEntity entryDetail, PaymentsCaptureBO paymentsCaptureBO, String tracenumber) {
        //Optional<EntryDetailEntity> entryDetailEntityOptional = entryDetailRepository.findById(3L);
        //Optional<List<PaymentsCaptureBO[]>> entryDetailsPaymentsOptional = staplesEmagiaMISCRepository.getEntryDetails_Payments("023", "ACH");

        CTXEntryDetailUpdated entryCTXDetail = new CTXEntryDetailUpdated();
        entryCTXDetail.getRecordTypeCode();//1
        //if (entryDetailsPaymentsOptional.isPresent() ) {

        entryCTXDetail.setTransactionCode(Integer.valueOf(entryDetail.getTransactioncode()));//2
        //entryCTXDetail.setTransactionCode(24);//2
        if(paymentsCaptureBO.getCashAbtRoutingNumber().length()==9) {
            entryCTXDetail.setReceivingDfiIdentification(paymentsCaptureBO.getCashAbtRoutingNumber().substring(0, 8));//R/T number /8//TODO
            //entryCTXDetail.setReceivingDfiIdentification("12100035");//R/T number /8
        }else { entryCTXDetail.setReceivingDfiIdentification("12100035");}
        entryCTXDetail.setCheckDigit((short) AchUtils.calculateCheckDigit(paymentsCaptureBO.getCashAbtRoutingNumber()));//1
        //entryCTXDetail.setCheckDigit(Short.valueOf("1"));//1
        entryCTXDetail.setDfiAccountNumber(paymentsCaptureBO.getCashAbtBankAccNumber());//17
        //entryCTXDetail.setDfiAccountNumber("000123456789     ");//17
        entryCTXDetail.setAmount(paymentsCaptureBO.getCashPayTotalAmount());//10
        //entryCTXDetail.setAmount(BigDecimal.valueOf(00000022.2));//10
        entryCTXDetail.setIdentificationNumber(paymentsCaptureBO.getCashCusNumber());//15//Individualid
        //entryCTXDetail.setIdentificationNumber("CUSTID00123    ");//15
        entryCTXDetail.setReceivingCompanyName(paymentsCaptureBO.getCashCusName().substring(0, 22));//Individual Name//22 -6 = 16//TODO use positions 55-58 to indicate the number of addenda
        //entryCTXDetail.setReceivingCompanyName("0001RATNA PRASAD      ");//Individual Name//22 -6 = 16
        entryCTXDetail.setDiscretionaryData(entryDetail.getDiscretionaryData());//2
        //entryCTXDetail.setDiscretionaryData("00");//2
        entryCTXDetail.setAddendaRecordIndicator(Short.valueOf(entryDetail.getAddendaRecordIndicator()));//1//TODO
        //entryCTXDetail.setAddendaRecordIndicator(Short.valueOf("1"));//1


        entryCTXDetail.setTraceNumber(Long.valueOf(tracenumber));//15//TODO
        //entryCTXDetail.setTraceNumber(Long.valueOf("091000010000001"));//15

        //}
        entryCTXDetail.setLineNumber(3);
        entryCTXDetail.setRecord("CTXEntryDetail");
        ++totalEntryAddendaCount;
        entryHash += Integer.valueOf(entryCTXDetail.getReceivingDfiIdentification());
        entryTotalDebits = entryTotalDebits.add(entryCTXDetail.getAmount());
        return entryCTXDetail;
    }

    private BatchControl createBatchControl() {
        blockCount++;
        Optional<BatchControlEntity> batchControlEntityOptional = batchControlRepository.findById(2L);
        BatchControl batchControl = new BatchControl();
        if (batchControlEntityOptional.isPresent()) {
            BatchControlEntity batchControlEntity = batchControlEntityOptional.get();
            batchControl.getRecordTypeCode();
            batchControl.setServiceClassCode(Integer.valueOf(batchControlEntity.getServiceclasscode()));
            //batchControl.setServiceClassCode(220);
            batchControl.setEntryAddendaCount(totalEntryAddendaCount);
            //batchControl.setEntryAddendaCount(000002);

            String entryHashString = String.valueOf(entryHash);
            if(entryHashString.length()>10)
            {
                entryHashString = entryHashString.substring(1,10);
            }
            /*else if (entryHashString.length()>9) {
                entryHashString = entryHashString.substring(2,10);
            }*/
            batchControl.setEntryHash(BigInteger.valueOf(Integer.valueOf(entryHashString)));
            //batchControl.setEntryHash(BigInteger.valueOf(0012100024));
            batchControl.setTotalDebits(entryTotalDebits);
            //batchControl.setTotalDebits(BigDecimal.valueOf(Long.valueOf(000000000000)));
            //batchControl.setTotalCredits(BigDecimal.valueOf(Long.valueOf(batchControlEntity.getBatchCreditEntryTotalAmount())));
            //batchControl.setTotalCredits(BigDecimal.valueOf(Long.valueOf("000038273434")));
            batchControl.setCompanyIdentification(batchHeaderCompanyID);
            //batchControl.setCompanyIdentification("2542049910");
            batchControl.setMessageAuthenticationCode(batchControlEntity.getMessageAuthCode());
            //batchControl.setMessageAuthenticationCode("                   ");
            batchControl.getReserved();
            batchControl.setOriginatingDfiIdentification(RT_Number_WellsFargo);
            //batchControl.setOriginatingDfiIdentification("09100001");
            //batchControl.setBatchNumber(Integer.valueOf(batchControlEntity.getBatchnumber()));
            batchControl.setBatchNumber(batchNumber);
        }
        batchControl.setLineNumber(5);
        batchControl.setRecord("batch control");
        return batchControl;
    }

    @Transactional("exchangeOracleTransactionManager")
    public GeneralBatchHeader createGeneralBatchHeader(String companyId) {
        blockCount++;
        Optional<BatchHeaderEntity> batchHeaderEntityOptional = batchHeaderRepository.findByCompanyid(companyId);
        totalEntryAddendaCount = 0;
        GeneralBatchHeader generalBatchHeader = new GeneralBatchHeader();
        if (batchHeaderEntityOptional.isPresent()) {
            BatchHeaderEntity batchHeaderEntity = batchHeaderEntityOptional.get();
            generalBatchHeader.getRecordTypeCode();
            //generalBatchHeader.setServiceClassCode(batchHeaderEntity.getServiceclasscode());
            generalBatchHeader.setServiceClassCode("225");
            generalBatchHeader.setCompanyName(batchHeaderEntity.getCompanyNamePayeePayor());
            //generalBatchHeader.setCompanyName("STAPLES CONTRACT");
            generalBatchHeader.setCompanyDiscretionaryData(batchHeaderEntity.getCompanyDiscretionaryData());
            //generalBatchHeader.setCompanyDiscretionaryData("00000000000000000000");
            generalBatchHeader.setCompanyID(batchHeaderEntity.getCompanyid());
            batchHeaderCompanyID = batchHeaderEntity.getCompanyid();
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
            //generalBatchHeader.setBatchNumber(Integer.valueOf(batchHeaderEntity.getBatchnumber()));
            generalBatchHeader.setBatchNumber(batchNumber);
        }
        generalBatchHeader.setLineNumber(2);
        generalBatchHeader.setRecord("batch header");
        return generalBatchHeader;
    }

    private FileHeader CreateFileHeaderRecord(String fileID) {
        blockCount++;
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
