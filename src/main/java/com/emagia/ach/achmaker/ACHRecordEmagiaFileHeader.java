/*
 * Created on Apr 10, 2007
 *
 */
package com.emagia.ach.achmaker;

import com.afrunt.jach.domain.FileHeader;

import java.text.DecimalFormat;

public class ACHRecordEmagiaFileHeader extends FileHeader {

	public ACHRecordEmagiaFileHeader() {
		super();
		setPriorityCode("01");
		setImmediateDestination(" 091000019");
		//setRecordSize("94");
		setBlockingFactor("10");
		setFormatCode("1");
		setImmediateDestinationName("WELLS FARGO            ");
	}

	public ACHRecordEmagiaFileHeader(String record) {
		/*super(record);
		if (isFileHeaderType()) {
			parseRecord(record);
		}*/
	}

		public String toString() {

		return super.toString()+ getRecordTypeCode() + getPriorityCode() + getImmediateDestination()
				+ getImmediateOrigin() + getFileCreationDate() + getFileCreationTime()
				+ getFileIdModifier() + getRecordSize() + getBlockingFactor() + getFormatCode()
				+ getImmediateDestinationName() + getImmediateOriginName()
				+ getReferenceCode();
	}

	public static String formatACHDecimal(String inputString, String formatString) {
		return formatACHDecimal(inputString, formatString,0);
	}

	public static String formatACHDecimal(String inputString, String formatString,
										  long defaultValue) {

		String returnValue = "";
		DecimalFormat format = new DecimalFormat(formatString);
		int length = formatString.length();
		try {
			returnValue = format.format(Long.parseLong(inputString));
		} catch (Exception ex) {
			returnValue = format.format(defaultValue);
		}
		// Truncate -- if larger than format, only return the last length digits
		if (returnValue.length() > length) {
			returnValue = returnValue.substring(returnValue.length() - length);
		}
		return returnValue;
	}
}
