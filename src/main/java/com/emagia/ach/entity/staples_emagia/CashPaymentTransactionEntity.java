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
@Table(name = "CASH_PAYMENT_TRANSACTIONS")
public class CashPaymentTransactionEntity {
    @Id
    @Column(name = "CASH_PAYT_ID")
    private Long cashPaytId;

    @Column(name = "CASH_PAYT_PAYMENT_ID")
    private Long cashPaytPaymentId;

    @Column(name = "CASH_PAYT_TRANSACTION_ID", length = 50)
    private String cashPaytTransactionId;

    @Column(name = "CASH_PAYT_AMOUNT_PAID", precision = 18, scale = 2)
    private BigDecimal cashPaytAmountPaid;

    @Column(name = "CASH_PAYT_HFM", length = 250)
    private String cashPaytHfm;

    @Column(name = "CASH_PAYT_CUSTOMER_ID", length = 250)
    private String cashPaytCustomerId;

    @Column(name = "CASH_PAYT_CURRENCY", length = 250)
    private String cashPaytCurrency;

    @Column(name = "CASH_PAYT_DATE")
    private LocalDate cashPaytDate;

    @Column(name = "CASH_PAYT_COMMENTS", length = 150)
    private String cashPaytComments;

    @Column(name = "CASH_PAYT_CREATED_BY")
    private Long cashPaytCreatedBy;

    @Column(name = "CASH_PAYT_CREATION_DATE", nullable = false)
    private LocalDate cashPaytCreationDate;

    @Column(name = "CASH_PAYT_LAST_UPDATED_BY")
    private Long cashPaytLastUpdatedBy;

    @Column(name = "CASH_PAYT_LAST_UPDATED")
    private LocalDate cashPaytLastUpdated;

    @Column(name = "CASH_PAYT_ATTRIBUTE1", length = 50)
    private String cashPaytAttribute1;

    @Column(name = "CASH_PAYT_ATTRIBUTE2", length = 50)
    private String cashPaytAttribute2;

    @Column(name = "CASH_PAYT_ATTRIBUTE3", length = 50)
    private String cashPaytAttribute3;

    @Column(name = "CASH_PAYT_ATTRIBUTE4", length = 50)
    private String cashPaytAttribute4;

    @Column(name = "CASH_PAYT_ATTRIBUTE5", length = 50)
    private String cashPaytAttribute5;

    @Column(name = "CASH_PAYT_ATTRIBUTE6", length = 50)
    private String cashPaytAttribute6;

    @Column(name = "CASH_PAYT_ATTRIBUTE7", length = 50)
    private String cashPaytAttribute7;

    @Column(name = "CASH_PAYT_ATTRIBUTE8", length = 50)
    private String cashPaytAttribute8;

    @Column(name = "CASH_PAYT_ATTRIBUTE9", length = 50)
    private String cashPaytAttribute9;

    @Column(name = "CASH_PAYT_ATTRIBUTE10", length = 50)
    private String cashPaytAttribute10;

    @Column(name = "CASH_PAYT_DISCOUNT_AMT", precision = 18, scale = 2)
    private BigDecimal cashPaytDiscountAmt;

    @Column(name = "CASH_PAYT_TRAH_ID", length = 30)
    private String cashPaytTrahId;

    @Column(name = "CASH_PAYT_STATUS", length = 25)
    private String cashPaytStatus;

}