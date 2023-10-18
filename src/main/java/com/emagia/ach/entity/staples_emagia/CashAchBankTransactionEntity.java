package com.emagia.ach.entity.staples_emagia;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "CASH_ACH_BANK_TRANSACTIONS")
public class CashAchBankTransactionEntity {
    @Id
    @Column(name = "CASH_ABT_ID")
    private Long cashAbtId;

    @Column(name = "CASH_ABT_USER_ID")
    private Long cashAbtUserId;

    @Column(name = "CASH_ABT_COMPANY_ID", length = 10)
    private String cashAbtCompanyId;

    @Column(name = "CASH_ABT_BANK_NAME", length = 100)
    private String cashAbtBankName;

    @Column(name = "CASH_ABT_BANK_ACC_NUMBER", length = 30)
    private String cashAbtBankAccNumber;

    @Column(name = "CASH_ABT_CNF_ACC_NUMBER", length = 30)
    private String cashAbtCnfAccNumber;

    @Column(name = "CASH_ABT_ROUTING_NUMBER", length = 30)
    private String cashAbtRoutingNumber;

    @Column(name = "CASH_ABT_TOKEN_NUMBER", length = 100)
    private String cashAbtTokenNumber;

    @Column(name = "CASH_ABT_CREATION_DATE")
    private LocalDate cashAbtCreationDate;

    @Column(name = "CASH_ABT_CREATED_BY")
    private Long cashAbtCreatedBy;

    @Column(name = "CASH_ABT_LAST_UPDATED_DATE")
    private LocalDate cashAbtLastUpdatedDate;

    @Column(name = "CASH_ABT_LAST_UPDATED_BY")
    private Long cashAbtLastUpdatedBy;

    @Column(name = "CASH_ABT_ATTRIBUTE1", length = 50)
    private String cashAbtAttribute1;

    @Column(name = "CASH_ABT_ATTRIBUTE2", length = 50)
    private String cashAbtAttribute2;

    @Column(name = "CASH_ABT_ATTRIBUTE3", length = 50)
    private String cashAbtAttribute3;

    @Column(name = "CASH_ABT_ATTRIBUTE4", length = 50)
    private String cashAbtAttribute4;

    @Column(name = "CASH_ABT_ATTRIBUTE5", length = 50)
    private String cashAbtAttribute5;

}