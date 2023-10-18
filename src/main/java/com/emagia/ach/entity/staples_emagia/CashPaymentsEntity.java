package com.emagia.ach.entity.staples_emagia;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "CASH_PAYMENTS")
public class CashPaymentsEntity {
    @Id
    @Column(name = "CASH_PAY_ID")
    private Long cashPayId;

    @Column(name = "CASH_PAY_CUSTOMER_USER_ID")
    private Long cashPayCustomerUserId;

    @Column(name = "CASH_PAY_TYPE", length = 15)
    private String cashPayType;

    @Column(name = "CASH_PAY_TOTAL_AMOUNT", precision = 18, scale = 2)
    private BigDecimal cashPayTotalAmount;

    @Column(name = "CASH_PAY_DATE")
    private LocalDate cashPayDate;

    @Column(name = "CASH_PAY_COMMENTS", length = 150)
    private String cashPayComments;

    @Column(name = "CASH_PAY_REFERENCE_NUMBER", length = 100)
    private String cashPayReferenceNumber;

    @Column(name = "CASH_PAY_BILL_TO_ADDRESS1", length = 240)
    private String cashPayBillToAddress1;

    @Column(name = "CASH_PAY_BILL_TO_ADDRESS2", length = 240)
    private String cashPayBillToAddress2;

    @Column(name = "CASH_PAY_BILL_TO_CITY", length = 50)
    private String cashPayBillToCity;

    @Column(name = "CASH_PAY_BILL_TO_STATE", length = 50)
    private String cashPayBillToState;

    @Column(name = "CASH_PAY_BILL_TO_ZIP", length = 50)
    private String cashPayBillToZip;

    @Column(name = "CASH_PAY_BILL_TO_COUNTRY", length = 50)
    private String cashPayBillToCountry;

    @Column(name = "CASH_PAY_ORDER_ID", length = 250)
    private String cashPayOrderId;

    @Column(name = "CASH_PAY_MESSAGE", length = 250)
    private String cashPayMessage;

    @Column(name = "CASH_PAY_CURRENCY", length = 250)
    private String cashPayCurrency;

    @Column(name = "CASH_PAY_CREATED_BY")
    private Long cashPayCreatedBy;

    @Column(name = "CASH_PAY_CREATION_DATE", nullable = false)
    private LocalDate cashPayCreationDate;

    @Column(name = "CASH_PAY_LAST_UPDATED_BY")
    private Long cashPayLastUpdatedBy;

    @Column(name = "CASH_PAY_LAST_UPDATED")
    private LocalDate cashPayLastUpdated;

    @Column(name = "CASH_PAY_ATTRIBUTE1", length = 50)
    private String cashPayAttribute1;

    @Column(name = "CASH_PAY_ATTRIBUTE2", length = 50)
    private String cashPayAttribute2;

    @Column(name = "CASH_PAY_ATTRIBUTE3", length = 50)
    private String cashPayAttribute3;

    @Column(name = "CASH_PAY_ATTRIBUTE4", length = 50)
    private String cashPayAttribute4;

    @Column(name = "CASH_PAY_ATTRIBUTE5", length = 50)
    private String cashPayAttribute5;

    @Column(name = "CASH_PAY_ATTRIBUTE6", length = 50)
    private String cashPayAttribute6;

    @Column(name = "CASH_PAY_ATTRIBUTE7", length = 50)
    private String cashPayAttribute7;

    @Column(name = "CASH_PAY_ATTRIBUTE8", length = 50)
    private String cashPayAttribute8;

    @Column(name = "CASH_PAY_ATTRIBUTE9", length = 50)
    private String cashPayAttribute9;

    @Column(name = "CASH_PAY_ATTRIBUTE10", length = 50)
    private String cashPayAttribute10;

    @Column(name = "CASH_PAY_DISCOUNT_AMT", precision = 18, scale = 2)
    private BigDecimal cashPayDiscountAmt;

    @Column(name = "CASH_CREDITCARD_CHARGES", precision = 18, scale = 2)
    private BigDecimal cashCreditcardCharges;

    @Column(name = "CARDX_RESPONSE", length = 1000)
    private String cardxResponse;

}