package com.emagia.ach.entity.staples_emagia;

import jakarta.persistence.*;

import java.math.BigInteger;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "CASH_COMPANIES", schema = "STAPLES_EMAGIA")
public class CashCompaniesEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "CASH_COM_ID", nullable = false, length = 100)
    private String cashComId;
    @Basic
    @Column(name = "CASH_COM_NUMBER", nullable = true, length = 100)
    private String cashComNumber;
    @Basic
    @Column(name = "CASH_COM_NAME", nullable = false, length = 50)
    private String cashComName;
    @Basic
    @Column(name = "CASH_COM_FUNCTIONAL_CURRENCY", nullable = false, length = 25)
    private String cashComFunctionalCurrency;
    @Basic
    @Column(name = "CASH_COM_SET_OF_BOOKS", nullable = true, length = 15)
    private String cashComSetOfBooks;
    @Basic
    @Column(name = "CASH_COM_PERIOD_SET_NAME", nullable = true, length = 15)
    private String cashComPeriodSetName;
    @Basic
    @Column(name = "CASH_COM_GOAL_DEADLINE_DAYS", nullable = true, precision = 0)
    private Byte cashComGoalDeadlineDays;
    @Basic
    @Column(name = "CASH_COM_LOCATION", nullable = true, length = 50)
    private String cashComLocation;
    @Basic
    @Column(name = "CASH_COM_REMIT_ADDRESS1", nullable = true, length = 240)
    private String cashComRemitAddress1;
    @Basic
    @Column(name = "CASH_COM_REMIT_ADDRESS2", nullable = true, length = 240)
    private String cashComRemitAddress2;
    @Basic
    @Column(name = "CASH_COM_REMIT_ADDRESS3", nullable = true, length = 240)
    private String cashComRemitAddress3;
    @Basic
    @Column(name = "CASH_COM_REMIT_ADDRESS4", nullable = true, length = 240)
    private String cashComRemitAddress4;
    @Basic
    @Column(name = "CASH_COM_REMIT_CITY", nullable = true, length = 60)
    private String cashComRemitCity;
    @Basic
    @Column(name = "CASH_COM_REMIT_POSTAL_CODE", nullable = true, length = 60)
    private String cashComRemitPostalCode;
    @Basic
    @Column(name = "CASH_COM_REMIT_STATE", nullable = true, length = 50)
    private String cashComRemitState;
    @Basic
    @Column(name = "CASH_COM_REMIT_COUNTRY", nullable = true, length = 50)
    private String cashComRemitCountry;
    @Basic
    @Column(name = "CASH_COM_CLOSING_ACCP_ID", nullable = true, precision = 0)
    private BigInteger cashComClosingAccpId;
    @Basic
    @Column(name = "CASH_COM_EFFECTIVE_START_DATE", nullable = true)
    private Date cashComEffectiveStartDate;
    @Basic
    @Column(name = "CASH_COM_EFFECTIVE_END_DATE", nullable = true)
    private Date cashComEffectiveEndDate;
    @Basic
    @Column(name = "CASH_COM_CREATION_DATE", nullable = false)
    private Date cashComCreationDate;
    @Basic
    @Column(name = "CASH_COM_CREATED_BY", nullable = false, precision = 0)
    private BigInteger cashComCreatedBy;
    @Basic
    @Column(name = "CASH_COM_LAST_UPDATED_DATE", nullable = true)
    private Date cashComLastUpdatedDate;
    @Basic
    @Column(name = "CASH_COM_LAST_UPDATED_BY", nullable = true, precision = 0)
    private BigInteger cashComLastUpdatedBy;
    @Basic
    @Column(name = "CASH_COM_ATTRIBUTE1", nullable = true, length = 50)
    private String cashComAttribute1;
    @Basic
    @Column(name = "CASH_COM_ATTRIBUTE2", nullable = true, length = 50)
    private String cashComAttribute2;
    @Basic
    @Column(name = "CASH_COM_ATTRIBUTE3", nullable = true, length = 50)
    private String cashComAttribute3;
    @Basic
    @Column(name = "CASH_COM_ATTRIBUTE4", nullable = true, length = 50)
    private String cashComAttribute4;
    @Basic
    @Column(name = "CASH_COM_ATTRIBUTE5", nullable = true, length = 50)
    private String cashComAttribute5;
    @Basic
    @Column(name = "CASH_COM_ATTRIBUTE6", nullable = true, length = 50)
    private String cashComAttribute6;
    @Basic
    @Column(name = "CASH_COM_ATTRIBUTE7", nullable = true, length = 50)
    private String cashComAttribute7;
    @Basic
    @Column(name = "CASH_COM_ATTRIBUTE8", nullable = true, length = 50)
    private String cashComAttribute8;
    @Basic
    @Column(name = "CASH_COM_ATTRIBUTE9", nullable = true, length = 50)
    private String cashComAttribute9;
    @Basic
    @Column(name = "CASH_COM_ATTRIBUTE10", nullable = true, length = 50)
    private String cashComAttribute10;

    public String getCashComId() {
        return cashComId;
    }

    public void setCashComId(String cashComId) {
        this.cashComId = cashComId;
    }

    public String getCashComNumber() {
        return cashComNumber;
    }

    public void setCashComNumber(String cashComNumber) {
        this.cashComNumber = cashComNumber;
    }

    public String getCashComName() {
        return cashComName;
    }

    public void setCashComName(String cashComName) {
        this.cashComName = cashComName;
    }

    public String getCashComFunctionalCurrency() {
        return cashComFunctionalCurrency;
    }

    public void setCashComFunctionalCurrency(String cashComFunctionalCurrency) {
        this.cashComFunctionalCurrency = cashComFunctionalCurrency;
    }

    public String getCashComSetOfBooks() {
        return cashComSetOfBooks;
    }

    public void setCashComSetOfBooks(String cashComSetOfBooks) {
        this.cashComSetOfBooks = cashComSetOfBooks;
    }

    public String getCashComPeriodSetName() {
        return cashComPeriodSetName;
    }

    public void setCashComPeriodSetName(String cashComPeriodSetName) {
        this.cashComPeriodSetName = cashComPeriodSetName;
    }

    public Byte getCashComGoalDeadlineDays() {
        return cashComGoalDeadlineDays;
    }

    public void setCashComGoalDeadlineDays(Byte cashComGoalDeadlineDays) {
        this.cashComGoalDeadlineDays = cashComGoalDeadlineDays;
    }

    public String getCashComLocation() {
        return cashComLocation;
    }

    public void setCashComLocation(String cashComLocation) {
        this.cashComLocation = cashComLocation;
    }

    public String getCashComRemitAddress1() {
        return cashComRemitAddress1;
    }

    public void setCashComRemitAddress1(String cashComRemitAddress1) {
        this.cashComRemitAddress1 = cashComRemitAddress1;
    }

    public String getCashComRemitAddress2() {
        return cashComRemitAddress2;
    }

    public void setCashComRemitAddress2(String cashComRemitAddress2) {
        this.cashComRemitAddress2 = cashComRemitAddress2;
    }

    public String getCashComRemitAddress3() {
        return cashComRemitAddress3;
    }

    public void setCashComRemitAddress3(String cashComRemitAddress3) {
        this.cashComRemitAddress3 = cashComRemitAddress3;
    }

    public String getCashComRemitAddress4() {
        return cashComRemitAddress4;
    }

    public void setCashComRemitAddress4(String cashComRemitAddress4) {
        this.cashComRemitAddress4 = cashComRemitAddress4;
    }

    public String getCashComRemitCity() {
        return cashComRemitCity;
    }

    public void setCashComRemitCity(String cashComRemitCity) {
        this.cashComRemitCity = cashComRemitCity;
    }

    public String getCashComRemitPostalCode() {
        return cashComRemitPostalCode;
    }

    public void setCashComRemitPostalCode(String cashComRemitPostalCode) {
        this.cashComRemitPostalCode = cashComRemitPostalCode;
    }

    public String getCashComRemitState() {
        return cashComRemitState;
    }

    public void setCashComRemitState(String cashComRemitState) {
        this.cashComRemitState = cashComRemitState;
    }

    public String getCashComRemitCountry() {
        return cashComRemitCountry;
    }

    public void setCashComRemitCountry(String cashComRemitCountry) {
        this.cashComRemitCountry = cashComRemitCountry;
    }

    public BigInteger getCashComClosingAccpId() {
        return cashComClosingAccpId;
    }

    public void setCashComClosingAccpId(BigInteger cashComClosingAccpId) {
        this.cashComClosingAccpId = cashComClosingAccpId;
    }

    public Date getCashComEffectiveStartDate() {
        return cashComEffectiveStartDate;
    }

    public void setCashComEffectiveStartDate(Date cashComEffectiveStartDate) {
        this.cashComEffectiveStartDate = cashComEffectiveStartDate;
    }

    public Date getCashComEffectiveEndDate() {
        return cashComEffectiveEndDate;
    }

    public void setCashComEffectiveEndDate(Date cashComEffectiveEndDate) {
        this.cashComEffectiveEndDate = cashComEffectiveEndDate;
    }

    public Date getCashComCreationDate() {
        return cashComCreationDate;
    }

    public void setCashComCreationDate(Date cashComCreationDate) {
        this.cashComCreationDate = cashComCreationDate;
    }

    public BigInteger getCashComCreatedBy() {
        return cashComCreatedBy;
    }

    public void setCashComCreatedBy(BigInteger cashComCreatedBy) {
        this.cashComCreatedBy = cashComCreatedBy;
    }

    public Date getCashComLastUpdatedDate() {
        return cashComLastUpdatedDate;
    }

    public void setCashComLastUpdatedDate(Date cashComLastUpdatedDate) {
        this.cashComLastUpdatedDate = cashComLastUpdatedDate;
    }

    public BigInteger getCashComLastUpdatedBy() {
        return cashComLastUpdatedBy;
    }

    public void setCashComLastUpdatedBy(BigInteger cashComLastUpdatedBy) {
        this.cashComLastUpdatedBy = cashComLastUpdatedBy;
    }

    public String getCashComAttribute1() {
        return cashComAttribute1;
    }

    public void setCashComAttribute1(String cashComAttribute1) {
        this.cashComAttribute1 = cashComAttribute1;
    }

    public String getCashComAttribute2() {
        return cashComAttribute2;
    }

    public void setCashComAttribute2(String cashComAttribute2) {
        this.cashComAttribute2 = cashComAttribute2;
    }

    public String getCashComAttribute3() {
        return cashComAttribute3;
    }

    public void setCashComAttribute3(String cashComAttribute3) {
        this.cashComAttribute3 = cashComAttribute3;
    }

    public String getCashComAttribute4() {
        return cashComAttribute4;
    }

    public void setCashComAttribute4(String cashComAttribute4) {
        this.cashComAttribute4 = cashComAttribute4;
    }

    public String getCashComAttribute5() {
        return cashComAttribute5;
    }

    public void setCashComAttribute5(String cashComAttribute5) {
        this.cashComAttribute5 = cashComAttribute5;
    }

    public String getCashComAttribute6() {
        return cashComAttribute6;
    }

    public void setCashComAttribute6(String cashComAttribute6) {
        this.cashComAttribute6 = cashComAttribute6;
    }

    public String getCashComAttribute7() {
        return cashComAttribute7;
    }

    public void setCashComAttribute7(String cashComAttribute7) {
        this.cashComAttribute7 = cashComAttribute7;
    }

    public String getCashComAttribute8() {
        return cashComAttribute8;
    }

    public void setCashComAttribute8(String cashComAttribute8) {
        this.cashComAttribute8 = cashComAttribute8;
    }

    public String getCashComAttribute9() {
        return cashComAttribute9;
    }

    public void setCashComAttribute9(String cashComAttribute9) {
        this.cashComAttribute9 = cashComAttribute9;
    }

    public String getCashComAttribute10() {
        return cashComAttribute10;
    }

    public void setCashComAttribute10(String cashComAttribute10) {
        this.cashComAttribute10 = cashComAttribute10;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CashCompaniesEntity that = (CashCompaniesEntity) o;
        return Objects.equals(cashComId, that.cashComId) && Objects.equals(cashComNumber, that.cashComNumber) && Objects.equals(cashComName, that.cashComName) && Objects.equals(cashComFunctionalCurrency, that.cashComFunctionalCurrency) && Objects.equals(cashComSetOfBooks, that.cashComSetOfBooks) && Objects.equals(cashComPeriodSetName, that.cashComPeriodSetName) && Objects.equals(cashComGoalDeadlineDays, that.cashComGoalDeadlineDays) && Objects.equals(cashComLocation, that.cashComLocation) && Objects.equals(cashComRemitAddress1, that.cashComRemitAddress1) && Objects.equals(cashComRemitAddress2, that.cashComRemitAddress2) && Objects.equals(cashComRemitAddress3, that.cashComRemitAddress3) && Objects.equals(cashComRemitAddress4, that.cashComRemitAddress4) && Objects.equals(cashComRemitCity, that.cashComRemitCity) && Objects.equals(cashComRemitPostalCode, that.cashComRemitPostalCode) && Objects.equals(cashComRemitState, that.cashComRemitState) && Objects.equals(cashComRemitCountry, that.cashComRemitCountry) && Objects.equals(cashComClosingAccpId, that.cashComClosingAccpId) && Objects.equals(cashComEffectiveStartDate, that.cashComEffectiveStartDate) && Objects.equals(cashComEffectiveEndDate, that.cashComEffectiveEndDate) && Objects.equals(cashComCreationDate, that.cashComCreationDate) && Objects.equals(cashComCreatedBy, that.cashComCreatedBy) && Objects.equals(cashComLastUpdatedDate, that.cashComLastUpdatedDate) && Objects.equals(cashComLastUpdatedBy, that.cashComLastUpdatedBy) && Objects.equals(cashComAttribute1, that.cashComAttribute1) && Objects.equals(cashComAttribute2, that.cashComAttribute2) && Objects.equals(cashComAttribute3, that.cashComAttribute3) && Objects.equals(cashComAttribute4, that.cashComAttribute4) && Objects.equals(cashComAttribute5, that.cashComAttribute5) && Objects.equals(cashComAttribute6, that.cashComAttribute6) && Objects.equals(cashComAttribute7, that.cashComAttribute7) && Objects.equals(cashComAttribute8, that.cashComAttribute8) && Objects.equals(cashComAttribute9, that.cashComAttribute9) && Objects.equals(cashComAttribute10, that.cashComAttribute10);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cashComId, cashComNumber, cashComName, cashComFunctionalCurrency, cashComSetOfBooks, cashComPeriodSetName, cashComGoalDeadlineDays, cashComLocation, cashComRemitAddress1, cashComRemitAddress2, cashComRemitAddress3, cashComRemitAddress4, cashComRemitCity, cashComRemitPostalCode, cashComRemitState, cashComRemitCountry, cashComClosingAccpId, cashComEffectiveStartDate, cashComEffectiveEndDate, cashComCreationDate, cashComCreatedBy, cashComLastUpdatedDate, cashComLastUpdatedBy, cashComAttribute1, cashComAttribute2, cashComAttribute3, cashComAttribute4, cashComAttribute5, cashComAttribute6, cashComAttribute7, cashComAttribute8, cashComAttribute9, cashComAttribute10);
    }
}
