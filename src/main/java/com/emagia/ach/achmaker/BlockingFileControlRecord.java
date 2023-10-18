package com.emagia.ach.achmaker;

import com.afrunt.jach.annotation.ACHField;
import com.afrunt.jach.annotation.ACHRecordType;
import com.afrunt.jach.annotation.Values;
import com.afrunt.jach.domain.ACHRecord;

import static com.afrunt.jach.domain.RecordTypes.Constants.FILE_HEADER_RECORD_TYPE_CODE;

@ACHRecordType(name = "Blocking File Control Record")
public class BlockingFileControlRecord extends ACHRecord {

    private String filler9s;
    //recordTypeCode

    public BlockingFileControlRecord() {
        super();
        filler9s="9999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999";
        this.setRecord("blocking record");
        this.setLineNumber(10);

    }
    @Override
    @Values(FILE_HEADER_RECORD_TYPE_CODE)
    public String getRecordTypeCode() {
        return FILE_HEADER_RECORD_TYPE_CODE;
    }

    @ACHField(start = 1, length = 94, name = "filler Data")
    public String getFiller9s() {
        return filler9s;
    }

    public void setFiller9s(String filler9s) {
        this.filler9s = filler9s;
    }
}
