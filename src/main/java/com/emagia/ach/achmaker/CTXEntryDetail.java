/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package com.emagia.ach.achmaker;

import com.afrunt.jach.annotation.ACHField;
import com.afrunt.jach.annotation.ACHRecordType;
import com.afrunt.jach.domain.NonIATEntryDetail;

import static com.afrunt.jach.annotation.InclusionRequirement.REQUIRED;

/**
 * @author Anil Kadiga
 */
@ACHRecordType(name = "CTX Entry Detail Record")
public class CTXEntryDetail extends NonIATEntryDetail {
    private String identificationNumber;
    private String discretionaryData;
    private String receivingCompanyName;

    @ACHField(start = 39, length = 15, name = NonIATEntryDetail.IDENTIFICATION_NUMBER)
    public String getIdentificationNumber() {
        return identificationNumber;
    }

    public CTXEntryDetail setIdentificationNumber(String identificationNumber) {
        this.identificationNumber = identificationNumber;
        return this;
    }

    @ACHField(start = 54, length = 22, inclusion = REQUIRED, name = NonIATEntryDetail.RECEIVING_COMPANY_NAME)
    public String getReceivingCompanyName() {
        return receivingCompanyName;
    }

    public CTXEntryDetail setReceivingCompanyName(String receivingCompanyName) {
        this.receivingCompanyName = receivingCompanyName;
        return this;
    }

    @ACHField(start = 76, length = 2, name = "Discretionary Data")
    public String getDiscretionaryData() {
        return discretionaryData;
    }

    public CTXEntryDetail setDiscretionaryData(String discretionaryData) {
        this.discretionaryData = discretionaryData;
        return this;
    }
}
