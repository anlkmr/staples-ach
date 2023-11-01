package com.emagia.ach;

import com.afrunt.jach.document.ACHBatch;
import com.afrunt.jach.document.ACHBatchDetail;
import com.afrunt.jach.domain.*;
import com.afrunt.jach.domain.addenda.GeneralAddendaRecord;
import com.emagia.ach.achmaker.ACH;
import com.emagia.ach.achmaker.ACHDocumentUpdated;
import com.emagia.ach.achmaker.CTXEntryDetailUpdated;
import com.emagia.ach.entity.*;
import com.emagia.ach.entity.staples_emagia.PaymentsCaptureBO;
import com.emagia.ach.exception.AnotherCustomException;
import com.emagia.ach.pgp.PgpDecryptionUtil;
import com.emagia.ach.pgp.PgpEncryptionUtil;
import com.emagia.ach.repository.*;
import com.emagia.ach.service.Achfileservice;
import com.emagia.ach.service.FileUploadSFTPService;
import com.emagia.ach.staples_emagia.repository.StaplesEmagiaMISCRepository;
import com.emagia.ach.utils.AchStringUtil;
import com.emagia.ach.utils.AchUtils;
import com.emagia.ach.utils.BU;
import lombok.extern.log4j.Log4j2;
import org.bouncycastle.bcpg.CompressionAlgorithmTags;
import org.bouncycastle.bcpg.SymmetricKeyAlgorithmTags;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URL;
import java.nio.file.Files;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Log4j2
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
    @Autowired
    private FileUploadSFTPService fileUploadSFTPService;

    PgpEncryptionUtil pgpEncryptionUtil;
    PgpDecryptionUtil pgpDecryptionUtil;
    private int entrySequenceNumber;
    private final String RT_Number_WellsFargo = "09100001";
    private Integer totalEntryAddendaCount;
    private int entryHash;
    private BigDecimal entryTotalDebits;
    private String batchHeaderCompanyID;
    private Integer batchNumber;
    private int blockCount;
    private Integer addendaSequenceNumber;


    private static URL loadResource(String resourcePath) {
        return Optional.ofNullable(AchfileserviceImpl.class.getResource(resourcePath))
                .orElseThrow(() -> new IllegalArgumentException("Resource not found"));
    }
    private final URL publicKey = loadResource("/public.pgp");
    private final URL privateKey = loadResource("/private.pgp");

    private static final String passkey = "dummy";

    @Override
    public String createOSStringAchCTXDoc() {
        log.info("Entered - "+getClass());
        log.info("Fetching file Header configuration.");
        Optional<List<FileHeaderEntity>> fileHeaderEntityOptional = Optional.of(fileHeaderRepository.findAll());
        log.info("Fetching file Header configuration completed");
        fileHeaderEntityOptional.ifPresent(fileHeaderEntities -> fileHeaderEntities.forEach(entity -> achFileWriter(entity, entity.getCompanyNameImdOrigName().replaceAll(" ", ""))));
        log.info("Exiting - "+getClass());
        return "success";
    }

    void achFileWriter(FileHeaderEntity entity, String companyNameImdOrigName) {
        log.info("file header configuration: {}",entity);
        FileWriter myWriterEnc = null;
        FileWriter myWriterDec = null;
        FileWriter myWriter = null;
        try {

            var fileNameToCreateACHFile = "achtest1" + companyNameImdOrigName + ".ach";
            var fileNameToOriginalACHFile = "achtest1" + companyNameImdOrigName + ".ach";
            var fileNameToCreateEncryptFile = "achtest1" + companyNameImdOrigName + "EncryptedCAST5";
            myWriterEnc = new FileWriter(fileNameToCreateEncryptFile);
            myWriterDec = new FileWriter(fileNameToCreateACHFile);
            myWriter = new FileWriter(fileNameToOriginalACHFile);
            log.info("created file writer for : {}",companyNameImdOrigName);
            myWriter.write(ach.write(createAchDocument(entity)));
            InputStream originalFile = new ByteArrayInputStream(ach.write(createAchDocument(entity)).getBytes());

            pgpEncryptionUtil = PgpEncryptionUtil.builder()
                    .armor(true)
                    .compressionAlgorithm(CompressionAlgorithmTags.ZIP)
                    .symmetricKeyAlgorithm(SymmetricKeyAlgorithmTags.CAST5)
                    .withIntegrityCheck(true)
                    .build();

            byte[] encryptedIn = pgpEncryptionUtil.encrypt(ach.write(createAchDocument(entity)).getBytes(), publicKey.openStream());
            myWriterEnc.write(Arrays.toString(encryptedIn));
            //fileUploadSFTPService.uplodaToEmagiaSftp(targetStream, fileNameToCreateEncryptFile);
            pgpDecryptionUtil = new PgpDecryptionUtil(privateKey.openStream(), passkey);
            byte[] decryptedBytes = pgpDecryptionUtil.decrypt(encryptedIn);
            myWriterDec.write(Arrays.toString(decryptedBytes));
            log.info("closing writer: {}",myWriterEnc);
            log.info("closing writer: {}",myWriterDec);
            myWriterDec.close();
            myWriterEnc.close();
        } catch (Exception e) {
            log.error("caught exception : {}",e.getMessage());
            throw new AnotherCustomException(e.getMessage());
        }
    }

    private ACHDocumentUpdated createAchDocument(FileHeaderEntity entity) {
        log.info("Entered createAchDocument method");
        entrySequenceNumber = 0;
        batchNumber = (Integer) 0;
        blockCount = 0;

        ACHDocumentUpdated achDocument = new ACHDocumentUpdated();
        //callTest();
        achDocument.setFileHeader(CreateFileHeaderRecord(entity.getFileidImo()));
        achDocument.setBatches(getBatchRecordList(entity.getFileidImo()));
        achDocument.setFileControl(createFileControl());
        //AchUtils.numberOfBlockingFileRecords(blockCount);
        //achDocument.addBlockingFileControlRecords(AchUtils.numberOfBlockingFileRecords(blockCount));
        achDocument.setNumberOfLines(6);
        log.info("created AchDocument");
        return achDocument;
    }

    private List<ACHBatch> getBatchRecordList(String fileidImo) {
        log.info("Enter getBatchRecordList method ");
        List<ACHBatch> batchRecordList = new ArrayList<>();
        ++batchNumber;
        ACHBatch batchRecord = new ACHBatch();
        GeneralBatchHeader generalBatchHeader = createGeneralBatchHeader(fileidImo);
        batchRecord.setBatchHeader(generalBatchHeader);
        batchRecord.setDetails(createBatchDetailList(String.valueOf(BU.valueOf((generalBatchHeader.getCompanyName()).replaceAll(" ", "")))));
        BatchControl batchControl = createBatchControl();
        batchRecord.setBatchControl(batchControl);
        batchRecordList.add(batchRecord);//add Batch Record
        return batchRecordList;
    }

    private List<ACHBatchDetail> createBatchDetailList(String BU) {
        log.info("fetching payment and payment trasanctions for business unit: {}",BU);
        entryHash = 0;
        entryTotalDebits = new BigDecimal(0);
        List<ACHBatchDetail> batchDetailList = new ArrayList<>();
        log.info("fetching entry detail configuration.");
        Optional<EntryDetailEntity> entryDetailEntityOptional = entryDetailRepository.findById(Long.valueOf(3L));
        Optional<List<PaymentsCaptureBO>> entryDetailsPaymentsOptional = staplesEmagiaMISCRepository.getEntryDetails_Payments(BU, "ACH");
        if (entryDetailsPaymentsOptional.isPresent() && entryDetailEntityOptional.isPresent()) {
            EntryDetailEntity entryDetailEntity = entryDetailEntityOptional.get();
            List<PaymentsCaptureBO> paymentsCaptureBOSlist = entryDetailsPaymentsOptional.get();
            Collection<List<PaymentsCaptureBO>> paymentsCaptureBOSUniquelist = paymentsCaptureBOSlist.stream().collect(Collectors.groupingBy(p -> {
                return p.getCashPayId();
            })).values();
            paymentsCaptureBOSUniquelist.forEach(uniqueList -> {
                ACHBatchDetail batchDetail = processForEntry_AddendaRecords(uniqueList, entryDetailEntity);
                if (null != batchDetail.getDetailRecord()) {
                    batchDetailList.add(batchDetail);
                }
            });
        }
        return batchDetailList;
    }

    private ACHBatchDetail processForEntry_AddendaRecords(List<PaymentsCaptureBO> uniqueList, EntryDetailEntity entryDetailEntity) {
        entrySequenceNumber++;
        ACHBatchDetail batchDetail = new ACHBatchDetail();
        createEntry_AddendaRecords(uniqueList, batchDetail, entryDetailEntity);
        return batchDetail;
    }

    private void createEntry_AddendaRecords(List<PaymentsCaptureBO> uniqueList, ACHBatchDetail batchDetail, EntryDetailEntity entryDetailEntity) {
        blockCount++;
        CTXEntryDetailUpdated entryCTXDetail = new CTXEntryDetailUpdated();
        List<AddendaRecord> addendaRecords = new ArrayList<>();
        addendaSequenceNumber = (Integer) 0;
        String entryDetailTraceNumber;
        List<String> tracenumberBuilderList = new ArrayList<>();
        tracenumberBuilderList.add(RT_Number_WellsFargo);
        String last7TraceNumber = AchStringUtil.leftPad(String.valueOf(entrySequenceNumber), 7, "0");
        tracenumberBuilderList.add(last7TraceNumber);
        entryDetailTraceNumber = AchStringUtil.join(tracenumberBuilderList, "");
        PaymentsCaptureBO paymentsCaptureBO = uniqueList.get(0);// As we need one to many mapping entry -> addenda
        if (uniqueList.get(0).getCashAbtRoutingNumber().length() == 9) {
            entryCTXDetail.setReceivingDfiIdentification(paymentsCaptureBO.getCashAbtRoutingNumber().substring(0, 8));//R/T number /8//TODO
        } else {
            entryCTXDetail.setReceivingDfiIdentification("12100035");
        }//TODO
        entryCTXDetail.setCheckDigit(AchUtils.calculateCheckDigit(paymentsCaptureBO.getCashAbtRoutingNumber()));//1
        entryCTXDetail.setDfiAccountNumber(paymentsCaptureBO.getCashAbtBankAccNumber());//17
        entryCTXDetail.setAmount(paymentsCaptureBO.getCashPayTotalAmount());//10
        entryCTXDetail.setIdentificationNumber(paymentsCaptureBO.getCashCusNumber());//15//Individualid
        entryCTXDetail.setReceivingCompanyName(paymentsCaptureBO.getCashCusName().substring(0, 22));//Individual Name//22 -6 = 16//TODO use positions 55-58 to indicate the number of addenda
        batchDetail.setDetailRecord(createEntryCTXDetailSTAPLES(entryDetailEntity, entryCTXDetail, entryDetailTraceNumber));
        log.info("Entry detail record created with trace number: {}",last7TraceNumber);
        entryHash += Integer.valueOf(entryCTXDetail.getReceivingDfiIdentification());
        totalEntryAddendaCount++;
        uniqueList.forEach(captureBO -> {
            addendaRecords.add(createAddendaRecord(last7TraceNumber, captureBO));
        });
        batchDetail.setAddendaRecords(addendaRecords);
    }

    private AddendaRecord createAddendaRecord(String last7TraceNumber, PaymentsCaptureBO captureBO) {
        ++addendaSequenceNumber;
        blockCount++;
        totalEntryAddendaCount++;
        log.info("creating addenda record");
        GeneralAddendaRecord addendaRecord = new GeneralAddendaRecord();
        addendaRecord.getRecordTypeCode();
        addendaRecord.getAddendaTypeCode();
        addendaRecord.setPaymentRelatedInformation("EMAGIA*PMT*INV#" + captureBO.getCashPaytTransactionId() + "*USD" + captureBO.getCashPaytAmountPaid() + "*" + AchStringUtil.leftPad(String.valueOf(addendaSequenceNumber), 5, "0") + "*FP*ABCDEFGHIJKLMNOPQRSTUVWXYZ123456789");
        addendaRecord.setAddendaSequenceNumber(addendaSequenceNumber);
        addendaRecord.setEntryDetailSequenceNumber(Long.valueOf(last7TraceNumber));
        addendaRecord.setLineNumber(4);
        addendaRecord.setRecord("addenda");
        entryTotalDebits = entryTotalDebits.add(captureBO.getCashPaytAmountPaid());
        log.info("created addenda with sequence number: {}", addendaSequenceNumber);
        return addendaRecord;
    }

    private EntryDetail createEntryCTXDetail() {
        Optional<EntryDetailEntity> entryDetailEntityOptional = entryDetailRepository.findById(Long.valueOf(3));
        CTXEntryDetailUpdated entryCTXDetail = new CTXEntryDetailUpdated();
        entryCTXDetail.getRecordTypeCode();//1
        if (entryDetailEntityOptional.isPresent()) {
            EntryDetailEntity entryDetailEntity = entryDetailEntityOptional.get();
            entryCTXDetail.setTransactionCode(Integer.valueOf(entryDetailEntity.getTransactioncode()));//2
            entryCTXDetail.setReceivingDfiIdentification(entryDetailEntity.getReceivingDfiRtNumber());//R/T number /8
            entryCTXDetail.setCheckDigit(AchUtils.calculateCheckDigit(entryDetailEntity.getReceivingDfiRtNumber()));//1
            entryCTXDetail.setDfiAccountNumber(entryDetailEntity.getReceivingDfiAccountNumber());//17
            entryCTXDetail.setAmount(entryDetailEntity.getAmount());//10
            entryCTXDetail.setIdentificationNumber(entryDetailEntity.getIndividualid());//15
            entryCTXDetail.setReceivingCompanyName(entryDetailEntity.getIndividualname());//Individual Name//22 -6 = 16
            entryCTXDetail.setDiscretionaryData(entryDetailEntity.getDiscretionaryData());//2
            entryCTXDetail.setAddendaRecordIndicator(Short.valueOf(entryDetailEntity.getAddendaRecordIndicator()));//1
            entryCTXDetail.setTraceNumber(Long.valueOf(entryDetailEntity.getTracenumber()));//15
        }
        entryCTXDetail.setLineNumber(3);
        entryCTXDetail.setRecord("CTXEntryDetail");
        return entryCTXDetail;
    }

    private CTXEntryDetailUpdated createEntryCTXDetailSTAPLES(EntryDetailEntity entryDetailEntity, CTXEntryDetailUpdated entryCTXDetail, String tracenumber) {
          entryCTXDetail.getRecordTypeCode();//1
        entryCTXDetail.setTransactionCode(Integer.valueOf(entryDetailEntity.getTransactioncode()));//2
        entryCTXDetail.setDiscretionaryData(entryDetailEntity.getDiscretionaryData());//2
        entryCTXDetail.setAddendaRecordIndicator(Short.valueOf(entryDetailEntity.getAddendaRecordIndicator()));//1//TODO
        entryCTXDetail.setTraceNumber(Long.valueOf(tracenumber));//15//TODO
        entryCTXDetail.setLineNumber(3);
        entryCTXDetail.setRecord("CTXEntryDetail");
        entryHash += Integer.valueOf(entryCTXDetail.getReceivingDfiIdentification());
        return entryCTXDetail;
    }

    private BatchControl createBatchControl() {
        blockCount++;
        Optional<BatchControlEntity> batchControlEntityOptional = batchControlRepository.findById(Long.valueOf(2L));
        BatchControl batchControl = new BatchControl();
        if (batchControlEntityOptional.isPresent()) {
            BatchControlEntity batchControlEntity = batchControlEntityOptional.get();
            batchControl.getRecordTypeCode();
            batchControl.setServiceClassCode(Integer.valueOf(batchControlEntity.getServiceclasscode()));
            batchControl.setEntryAddendaCount(totalEntryAddendaCount);
            String entryHashString = String.valueOf(entryHash);
            if (entryHashString.length() > 10) {
                entryHashString = entryHashString.substring(1, 10);
            }
            batchControl.setEntryHash(BigInteger.valueOf(Integer.valueOf(entryHashString)));
            batchControl.setTotalDebits(entryTotalDebits);
            batchControl.setCompanyIdentification(batchHeaderCompanyID);
            batchControl.setMessageAuthenticationCode(batchControlEntity.getMessageAuthCode());
            batchControl.getReserved();
            batchControl.setOriginatingDfiIdentification(RT_Number_WellsFargo);
            batchControl.setBatchNumber(batchNumber);
        }
        batchControl.setLineNumber(5);
        batchControl.setRecord("batch control");
        return batchControl;
    }

    @Transactional("exchangeOracleTransactionManager")
    public GeneralBatchHeader createGeneralBatchHeader(String companyId) {
        log.info("creating batch header with companyID: {}",companyId);
        blockCount++;
        log.info("fetching batch header configuration.");
        Optional<BatchHeaderEntity> batchHeaderEntityOptional = batchHeaderRepository.findByCompanyid(companyId);
        totalEntryAddendaCount = (Integer) 0;
        GeneralBatchHeader generalBatchHeader = new GeneralBatchHeader();
        if (batchHeaderEntityOptional.isPresent()) {
            BatchHeaderEntity batchHeaderEntity = batchHeaderEntityOptional.get();
            generalBatchHeader.getRecordTypeCode();
            generalBatchHeader.setServiceClassCode("225");
            generalBatchHeader.setCompanyName(batchHeaderEntity.getCompanyNamePayeePayor());
            generalBatchHeader.setCompanyDiscretionaryData(batchHeaderEntity.getCompanyDiscretionaryData());
            generalBatchHeader.setCompanyID(batchHeaderEntity.getCompanyid());
            batchHeaderCompanyID = batchHeaderEntity.getCompanyid();
            generalBatchHeader.setStandardEntryClassCode(batchHeaderEntity.getSeccode());
            generalBatchHeader.setCompanyEntryDescription(batchHeaderEntity.getCompanyEntryDesc());
            Calendar cal = Calendar.getInstance();
            generalBatchHeader.setCompanyDescriptiveDate(batchHeaderEntity.getCompanyDescDate());
            generalBatchHeader.setEffectiveEntryDate(batchHeaderEntity.getEffectiveEntryDate());
            generalBatchHeader.getSettlementDate();
            generalBatchHeader.setOriginatorStatusCode(batchHeaderEntity.getOriginatorStatusCode());
            generalBatchHeader.setOriginatorDFIIdentifier(batchHeaderEntity.getRtNumberOdfiId());
            generalBatchHeader.setBatchNumber(batchNumber);
        }
        generalBatchHeader.setLineNumber(2);
        generalBatchHeader.setRecord("batch header");
        log.info("created batch header");
        return generalBatchHeader;
    }

    private FileHeader CreateFileHeaderRecord(String fileID) {
        log.info("Creating file header record with fileID: {}",fileID);
        blockCount++;
        Optional<FileHeaderEntity> fileHeaderEntityOptional = fileHeaderRepository.findByFileidImo(fileID);
        log.info("fetched file header configuration. ");
        FileHeader fileHeader = new FileHeader();
        if (fileHeaderEntityOptional.isPresent()) {
            FileHeaderEntity fileHeaderEntity = fileHeaderEntityOptional.get();
            fileHeader.setPriorityCode(fileHeaderEntity.getPrioritycode());
            fileHeader.setImmediateDestination(fileHeaderEntity.getRtNumber());
            fileHeader.setImmediateOrigin(fileHeaderEntity.getFileidImo());
            Calendar cal = Calendar.getInstance();
            fileHeader.setFileCreationDate(cal.getTime());//"230828"
            fileHeader.setFileCreationTime(String.valueOf(cal.get(Calendar.HOUR_OF_DAY)) + String.valueOf(cal.get(Calendar.MINUTE)));
            fileHeader.setFileIdModifier(fileHeaderEntity.getFileidModifier());
            fileHeader.getRecordSize();
            fileHeader.setBlockingFactor(fileHeaderEntity.getBlockingfactor());
            fileHeader.setFormatCode(fileHeaderEntity.getFormatcode());
            fileHeader.setImmediateDestinationName(fileHeaderEntity.getOriginatingBankImdDestName());
            fileHeader.setImmediateOriginName(fileHeaderEntity.getCompanyNameImdOrigName());
            fileHeader.setReferenceCode(fileHeaderEntity.getReferencecode());
        }
        fileHeader.setLineNumber(1);
        String record = "file header";
        fileHeader.setRecord(record);
        log.info("file header created");
        return fileHeader;
    }

    private FileControl createFileControl() {
        log.info("creating file control record");
        blockCount++;
        Optional<FileControlEntity> fileControlEntityOptional = fileControlRepository.findById(Long.valueOf(1L));
        FileControl fileControl = new FileControl();
        fileControl.getRecordTypeCode();
        if (fileControlEntityOptional.isPresent()) {
            FileControlEntity fileControlEntity = fileControlEntityOptional.get();
            fileControl.setBatchCount(batchNumber);
            fileControl.setBlockCount(Integer.valueOf(AchUtils.roundBy10(blockCount)));
            fileControl.setEntryAddendaCount(totalEntryAddendaCount);
            fileControl.setEntryHashTotals(Long.valueOf(entryHash));
            fileControl.setTotalDebits(entryTotalDebits);
            fileControl.setTotalCredits(new BigDecimal(0));
        }
        fileControl.setLineNumber(6);
        fileControl.setRecord("file control");
        log.info("created file control record");
        return fileControl;
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
