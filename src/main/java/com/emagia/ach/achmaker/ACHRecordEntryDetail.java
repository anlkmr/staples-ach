/*
 * Created on Apr 10, 2007
 *
 */
package com.emagia.ach.achmaker;

import com.afrunt.jach.document.ACHBatchDetail;

public class ACHRecordEntryDetail extends ACHBatchDetail {


	private String RecordType;


	public ACHRecordEntryDetail() {
		setRecordType("6");
	}

	public String getRecordType() {
		return RecordType;
	}

	public void setRecordType(String recordType) {
		RecordType = recordType;
	}




}
