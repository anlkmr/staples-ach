package com.emagia.ach.achmaker;

import com.afrunt.jach.annotation.ACHField;
import com.afrunt.jach.annotation.ACHRecordType;
import com.afrunt.jach.annotation.Values;
import com.afrunt.jach.domain.NonIATEntryDetail;

import static com.afrunt.jach.annotation.InclusionRequirement.*;

/**
 * @author Anil Kadiga
 */
@ACHRecordType(name = "CTX Entry Detail Record")
public class CTXEntryDetailUpdated extends NonIATEntryDetail {
    private String identificationNumber;
     private String discretionaryData;
    private String receivingCompanyName;

    @ACHField(start = 39, length = 15, name = NonIATEntryDetail.IDENTIFICATION_NUMBER)
    public String getIdentificationNumber() {
        return identificationNumber;
    }

    public CTXEntryDetailUpdated setIdentificationNumber(String identificationNumber) {
        this.identificationNumber = identificationNumber;
        return this;
    }

    @ACHField(start = 54, length = 22, inclusion = REQUIRED, name = NonIATEntryDetail.RECEIVING_COMPANY_NAME)
    public String getReceivingCompanyName() {
        return receivingCompanyName;
    }

    public CTXEntryDetailUpdated setReceivingCompanyName(String receivingCompanyName) {
        this.receivingCompanyName = receivingCompanyName;
        return this;
    }

    @ACHField(start = 76, length = 2, name = "Discretionary Data")
    public String getDiscretionaryData() {
        return discretionaryData;
    }

    public CTXEntryDetailUpdated setDiscretionaryData(String discretionaryData) {
        this.discretionaryData = discretionaryData;
        return this;
    }
}

