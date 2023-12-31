package com.emagia.ach.achmaker;

import com.afrunt.jach.ACH;
import com.afrunt.jach.document.ACHBatch;
import com.afrunt.jach.document.ACHBatchDetail;
import com.afrunt.jach.document.ACHDocument;
import com.afrunt.jach.domain.ACHRecord;
import com.afrunt.jach.domain.AddendaRecord;
import com.afrunt.jach.domain.FileControl;

import com.afrunt.jach.metadata.ACHBeanMetadata;
import com.afrunt.jach.metadata.ACHFieldMetadata;
import com.afrunt.jach.metadata.ACHMetadata;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.Charset;

import static com.afrunt.jach.domain.ACHRecord.ACH_RECORD_LENGTH;
import static com.afrunt.jach.logic.StringUtil.filledWithSpaces;

public class ACHWriterUpdated extends ACHProcessorUpdated {
    private int lines = 0;
    private boolean blockAligning;

    public ACHWriterUpdated(ACHMetadata metadata) {
        super(metadata);
    }

    public String write(ACHDocumentUpdated document) {
        return write(document, com.afrunt.jach.ACH.DEFAULT_CHARSET);
    }

    public String write(ACHDocumentUpdated document, Charset charset) {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            write(document, baos);
            return baos.toString(charset.name());
        } catch (IOException e) {
            throw error("Error writing ACH document", e);
        }
    }


    public void write(ACHDocumentUpdated document, OutputStream outputStream) {
        write(document, outputStream, ACH.DEFAULT_CHARSET);
    }

    public void write(ACHDocumentUpdated document, OutputStream outputStream, Charset charset) {
        try {
            validateDocument(document);
            OutputStreamWriter writer = new OutputStreamWriter(outputStream, charset);
            lines = 0;
            writeLine(writer, writeRecord(document.getFileHeader()));

            document.getBatches().forEach(b -> writeBatch(b, writer));

            FileControl fileControl = document.getFileControl();

            int blockSize = document.getFileHeader().getBlockingFactor() != null ? Integer.parseInt(document.getFileHeader().getBlockingFactor()) : 10;
            int blockCount = (lines + 1) / blockSize + ((lines + 1) % blockSize > 0 ? 1 : 0);

            if (fileControl.getBlockCount() == null) {
                fileControl.setBlockCount(blockCount);
            }

            writeLine(writer, writeRecord(fileControl), false);

            /*if (lines % blockSize != 0 && blockAligning) {
                String emptyLine = new String(new char[ACHRecord.ACH_RECORD_LENGTH]).replace("\0", "9");
                writer.write(LINE_SEPARATOR);
                int trailingLinesLeft = blockSize - (lines % blockSize) - 1;
                for (int i = 0; i < trailingLinesLeft; i++) {
                    writeLine(writer, emptyLine);
                }

                writeLine(writer, emptyLine, true);
            }*/
            if(!document.getBlockingFileControlRecords().isEmpty()) {
                writer.write(LINE_SEPARATOR);
                String emptyLine = new String(new char[ACHRecord.ACH_RECORD_LENGTH]).replace("\0", "9");
                document.getBlockingFileControlRecords().forEach(b -> {
                    try {
                        writeLine(writer, emptyLine);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
                writeLine(writer, emptyLine, false);
            }

            writer.flush();
        } catch (IOException e) {
            throw error("Error writing ACH document", e);
        }
    }

    public String formatFieldValueAsString(Object value, ACHBeanMetadata bm, ACHFieldMetadata fm) {
        return value == null ? filledWithSpaces(fm.getLength()) : (String) fieldToValue(value, String.class, bm, fm);
    }

    private void writeBatch(ACHBatch batch, OutputStreamWriter writer) {
        try {
            validateBatch(batch);

            writeLine(writer, writeRecord(batch.getBatchHeader()));

            batch.getDetails().forEach(d -> writeBatchDetail(d, writer));

            writeLine(writer, writeRecord(batch.getBatchControl()));
        } catch (IOException e) {
            throw error("Error writing ACH batch", e);
        }
    }

    private void writeBlockingFileRecord(BlockingFileControlRecord blockingFileControlRecord, OutputStreamWriter writer) {

        //writeLine(writer, writeRecord(blockingFileControlRecord));
        String emptyLine = new String(new char[ACHRecord.ACH_RECORD_LENGTH]).replace("\0", "9");
        //writeLine(writer, blockingFileControlRecord.getFiller9s(), true);

    }

    private void writeBatchDetail(ACHBatchDetail detail, OutputStreamWriter writer) {
        try {
            writeLine(writer, writeRecord(detail.getDetailRecord()));

            for (AddendaRecord addendaRecord : detail.getAddendaRecords()) {
                writeLine(writer, writeRecord(addendaRecord));
            }

        } catch (IOException e) {
            throw error("Error writing ACH details", e);
        }
    }

    private String writeRecord(ACHRecord record) {
        String recordString = filledWithSpaces(ACH_RECORD_LENGTH);
        ACHBeanMetadata typeMetadata = getMetadata().getBeanMetadata(record.getClass());

        for (ACHFieldMetadata fm : typeMetadata.getACHFieldsMetadata()) {
            Object value = retrieveFieldValue(record, fm);
            String formattedValue = formatFieldValueAsString(value, typeMetadata, fm);

            formattedValue = validateFormattedValue(fm, formattedValue);
            recordString = changeRecordStringWithFieldValue(recordString, fm, formattedValue);
        }

        if (recordString.length() != ACH_RECORD_LENGTH) {
            throwError("Error formatting ACH record. Line length should be 94. Actual size is " + recordString.length());
        }

        record.setRecord(recordString);
        return recordString;
    }

    private String validateFormattedValue(ACHFieldMetadata fm, String formattedValue) {
        if (fm.getLength() < formattedValue.length()) {
            throwError("Field " + fm + " value could not be longer, than " + fm.getLength() + " symbols. Value " + formattedValue + " is not appropriate");
        }
        return formattedValue;
    }

    private String changeRecordStringWithFieldValue(String recordString, ACHFieldMetadata fm, String formattedValue) {
        String headString = recordString.substring(0, fm.getStart());
        String tailString = recordString.substring(fm.getEnd());
        return headString + formattedValue + tailString;
    }

    private Object retrieveFieldValue(ACHRecord record, ACHFieldMetadata fm) {
        try {
            return fm.getGetter().invoke(record);
        } catch (InvocationTargetException | IllegalAccessException e) {
            throw error("Error retrieving value from field " + fm, e);
        }
    }

    private void validateDocument(ACHDocumentUpdated document) {
        if (document == null) {
            throwError("Document cannot be null");
            return;
        }

        if (document.getFileHeader() == null) {
            throwError("File header cannot be null");
        }

        if (document.getFileControl() == null) {
            throwError("File control cannot be null");
        }

        if (document.getBatchCount() == 0) {
            throwError("At least one batch is required");
        }
    }

    private void validateBatch(ACHBatch batch) {
        if (batch.getBatchHeader() == null) {
            throwError("Batch header is required");
        }

        if (batch.getBatchControl() == null) {
            throwError("Batch control is required");
        }

        for (ACHBatchDetail detail : batch.getDetails()) {
            if (detail.getDetailRecord() == null) {
                throwError("Detail record is required");
            }
        }
    }

    private void writeLine(OutputStreamWriter writer, String line, boolean withLineSeparator) throws IOException {
        ++lines;
        writer.write(line);
        if (withLineSeparator) {
            writer.write(LINE_SEPARATOR);
        }
    }

    private void writeLine(OutputStreamWriter writer, String line) throws IOException {
        writeLine(writer, line, true);
    }

    public boolean isBlockAligning() {
        return blockAligning;
    }

    @SuppressWarnings("UnusedReturnValue")
    public ACHWriterUpdated setBlockAligning(boolean blockAligning) {
        this.blockAligning = blockAligning;
        return this;
    }
}