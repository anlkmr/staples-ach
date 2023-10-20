package com.emagia.ach.entity.staples_emagia;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Date;

public class PaymentsCaptureBO implements Serializable {

    private BigInteger cashPayId;
    private BigInteger cashPaytPaymentId;
    private String cashPayType;
    private BigDecimal cashPayTotalAmount;
    private Date cashPayDate;
    private Date cashPayCreationDate;
    private String cashPayAttribute4;
    private BigDecimal cashPaytAmountPaid;
    private String cashPaytHfm;
    private String cashPaytCustomerId;
    private Date cashPaytDate;
    private Date cashPaytCreationDate;
    private String cashPaytTransactionId;
    private String cashAbtBankName;
    private String cashAbtBankAccNumber;
    private String cashAbtRoutingNumber;
    private String cashCusId;
    private String cashCusCompanyId;
    private String cashCusNumber;
    private String cashCusName;
    private BigDecimal cashCusCreditLimit;
    private String cashCusParentCustomerId;

    public PaymentsCaptureBO(BigInteger cashPayId, BigInteger cashPaytPaymentId, String cashPayType, BigDecimal cashPayTotalAmount, Date cashPayDate, Date cashPayCreationDate, String cashPayAttribute4, BigDecimal cashPaytAmountPaid, String cashPaytHfm, String cashPaytCustomerId, Date cashPaytDate, Date cashPaytCreationDate, String cashPaytTransactionId, String cashAbtBankName, String cashAbtBankAccNumber, String cashAbtRoutingNumber, String cashCusId, String cashCusCompanyId, String cashCusNumber, String cashCusName, BigDecimal cashCusCreditLimit, String cashCusParentCustomerId) {
        this.cashPayId = cashPayId;
        this.cashPayType = cashPayType;
        this.cashPayTotalAmount = cashPayTotalAmount;
        this.cashPayDate = cashPayDate;
        this.cashPayCreationDate = cashPayCreationDate;
        this.cashPayAttribute4 = cashPayAttribute4;
        this.cashPaytAmountPaid = cashPaytAmountPaid;
        this.cashPaytHfm = cashPaytHfm;
        this.cashPaytCustomerId = cashPaytCustomerId;
        this.cashPaytDate = cashPaytDate;
        this.cashPaytCreationDate = cashPaytCreationDate;
        this.cashPaytTransactionId = cashPaytTransactionId;
        this.cashAbtBankName = cashAbtBankName;
        this.cashAbtBankAccNumber = cashAbtBankAccNumber;
        this.cashAbtRoutingNumber = cashAbtRoutingNumber;
        this.cashCusId = cashCusId;
        this.cashCusCompanyId = cashCusCompanyId;
        this.cashCusNumber = cashCusNumber;
        this.cashCusName = cashCusName;
        this.cashCusCreditLimit = cashCusCreditLimit;
        this.cashCusParentCustomerId = cashCusParentCustomerId;
    }


    public BigInteger getCashPayId() {
        return cashPayId;
    }

    public void setCashPayId(BigInteger cashPayId) {
        this.cashPayId = cashPayId;
    }

    public BigInteger getCashPaytPaymentId() {
        return cashPaytPaymentId;
    }

    public String getCashPaytTransactionId() {
        return cashPaytTransactionId;
    }

    public void setCashPaytTransactionId(String cashPaytTransactionId) {
        this.cashPaytTransactionId = cashPaytTransactionId;
    }

    public void setCashPaytPaymentId(BigInteger cashPaytPaymentId) {
        this.cashPaytPaymentId = cashPaytPaymentId;
    }

    public String getCashPayType() {
        return cashPayType;
    }

    public void setCashPayType(String cashPayType) {
        this.cashPayType = cashPayType;
    }

    public BigDecimal getCashPayTotalAmount() {
        return cashPayTotalAmount;
    }

    public void setCashPayTotalAmount(BigDecimal cashPayTotalAmount) {
        this.cashPayTotalAmount = cashPayTotalAmount;
    }

    public Date getCashPayDate() {
        return cashPayDate;
    }

    public void setCashPayDate(Date cashPayDate) {
        this.cashPayDate = cashPayDate;
    }

    public Date getCashPayCreationDate() {
        return cashPayCreationDate;
    }

    public void setCashPayCreationDate(Timestamp cashPayCreationDate) {
        this.cashPayCreationDate = cashPayCreationDate;
    }

    public String getCashPayAttribute4() {
        return cashPayAttribute4;
    }

    public void setCashPayAttribute4(String cashPayAttribute4) {
        this.cashPayAttribute4 = cashPayAttribute4;
    }

    public BigDecimal getCashPaytAmountPaid() {
        return cashPaytAmountPaid;
    }

    public void setCashPaytAmountPaid(BigDecimal cashPaytAmountPaid) {
        this.cashPaytAmountPaid = cashPaytAmountPaid;
    }

    public String getCashPaytHfm() {
        return cashPaytHfm;
    }

    public void setCashPaytHfm(String cashPaytHfm) {
        this.cashPaytHfm = cashPaytHfm;
    }

    public String getCashPaytCustomerId() {
        return cashPaytCustomerId;
    }

    public void setCashPaytCustomerId(String cashPaytCustomerId) {
        this.cashPaytCustomerId = cashPaytCustomerId;
    }

    public Date getCashPaytDate() {
        return cashPaytDate;
    }

    public void setCashPaytDate(Date cashPaytDate) {
        this.cashPaytDate = cashPaytDate;
    }

    public Date getCashPaytCreationDate() {
        return cashPaytCreationDate;
    }

    public void setCashPaytCreationDate(Date cashPaytCreationDate) {
        this.cashPaytCreationDate = cashPaytCreationDate;
    }

    public String getCashAbtBankName() {
        return cashAbtBankName;
    }

    public void setCashAbtBankName(String cashAbtBankName) {
        this.cashAbtBankName = cashAbtBankName;
    }

    public String getCashAbtBankAccNumber() {
        return cashAbtBankAccNumber;
    }

    public void setCashAbtBankAccNumber(String cashAbtBankAccNumber) {
        this.cashAbtBankAccNumber = cashAbtBankAccNumber;
    }

    public String getCashAbtRoutingNumber() {
        return cashAbtRoutingNumber;
    }

    public void setCashAbtRoutingNumber(String cashAbtRoutingNumber) {
        this.cashAbtRoutingNumber = cashAbtRoutingNumber;
    }

    public String getCashCusId() {
        return cashCusId;
    }

    public void setCashCusId(String cashCusId) {
        this.cashCusId = cashCusId;
    }

    public String getCashCusCompanyId() {
        return cashCusCompanyId;
    }

    public void setCashCusCompanyId(String cashCusCompanyId) {
        this.cashCusCompanyId = cashCusCompanyId;
    }

    public String getCashCusNumber() {
        return cashCusNumber;
    }

    public void setCashCusNumber(String cashCusNumber) {
        this.cashCusNumber = cashCusNumber;
    }

    public String getCashCusName() {
        return cashCusName;
    }

    public void setCashCusName(String cashCusName) {
        this.cashCusName = cashCusName;
    }

    public BigDecimal getCashCusCreditLimit() {
        return cashCusCreditLimit;
    }

    public void setCashCusCreditLimit(BigDecimal cashCusCreditLimit) {
        this.cashCusCreditLimit = cashCusCreditLimit;
    }

    public String getCashCusParentCustomerId() {
        return cashCusParentCustomerId;
    }

    public void setCashCusParentCustomerId(String cashCusParentCustomerId) {
        this.cashCusParentCustomerId = cashCusParentCustomerId;
    }
}
