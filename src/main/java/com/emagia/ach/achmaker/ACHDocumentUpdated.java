package com.emagia.ach.achmaker;


import com.afrunt.jach.document.ACHBatch;
import com.afrunt.jach.domain.FileControl;
import com.afrunt.jach.domain.FileHeader;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Anil Kadiga
 */
@SuppressWarnings("UnusedReturnValue")
public class ACHDocumentUpdated {
    private FileHeader fileHeader;
    private List<ACHBatch> batches = new ArrayList<>();
    private FileControl fileControl;
    private List<BlockingFileControlRecord> blockingFileControlRecords = new ArrayList<>();

    public List<BlockingFileControlRecord> getBlockingFileControlRecords() {
        return blockingFileControlRecords;
    }
/*public BlockingFileControlRecord getBlockingFileControlRecord() {
        return blockingFileControlRecord;
    }

    public ACHDocumentUpdated setBlockingFileControlRecord(BlockingFileControlRecord blockingFileControlRecord) {
        this.blockingFileControlRecord = blockingFileControlRecord;
        return this;
    }*/

    private int numberOfLines;

    public ACHDocumentUpdated addBatch(ACHBatch batch) {
        batches = batches == null ? new ArrayList<>() : batches;
        batches.add(batch);
        return this;
    }

    public ACHDocumentUpdated addBlockingFileControlRecords(int count) {
        blockingFileControlRecords = blockingFileControlRecords == null ? new ArrayList<>() : blockingFileControlRecords;
        for (int i = 0; i < count; i++) {
            blockingFileControlRecords.add(new BlockingFileControlRecord());
        }

        return this;
    }

    public FileHeader getFileHeader() {
        return fileHeader;
    }

    public ACHDocumentUpdated setFileHeader(FileHeader fileHeader) {
        this.fileHeader = fileHeader;
        return this;
    }

    public List<ACHBatch> getBatches() {
        return batches;
    }

    public ACHDocumentUpdated setBatches(List<ACHBatch> batches) {
        this.batches = batches;
        return this;
    }

    public FileControl getFileControl() {
        return fileControl;
    }

    public ACHDocumentUpdated setFileControl(FileControl fileControl) {
        this.fileControl = fileControl;
        return this;
    }

    public int getBatchCount() {
        return Optional.ofNullable(batches)
                .map(List::size)
                .orElse(0);
    }

    public int getNumberOfLines() {
        return numberOfLines;
    }

    public ACHDocumentUpdated setNumberOfLines(int numberOfLines) {
        this.numberOfLines = numberOfLines;
        return this;
    }
}
