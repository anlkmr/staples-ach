package com.emagia.ach.entity.staples_emagia;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "CASH_CUSTOMER")
public class CashCustomerEntity {
    @Id
    @Column(name = "CASH_CUS_ID")
    private String cashCusId;


    @Column(name = "CASH_CUS_COMPANY_ID", length = 25)
    private String cashCusCompanyId;

    @Column(name = "CASH_CUS_NUMBER", length = 25)
    private String cashCusNumber;

    @Column(name = "CASH_CUS_NAME", nullable = false, length = 60)
    private String cashCusName;

    @Column(name = "CASH_CUS_COMPANY_URL", length = 100)
    private String cashCusCompanyUrl;

    @Column(name = "CASH_CUS_YEAR_ESTABLISHED")
    private Short cashCusYearEstablished;

    @Column(name = "CASH_CUS_CUSTOMER_SINCE")
    private LocalDate cashCusCustomerSince;

    @Column(name = "CASH_CUS_AUTO_FORECAST_FLAG", length = 1)
    private String cashCusAutoForecastFlag;

    @Column(name = "CASH_CUS_RISK_CODE", length = 50)
    private String cashCusRiskCode;

    @Column(name = "CASH_CUS_INTERNAL_CUST_RATING", length = 50)
    private String cashCusInternalCustRating;

    @Column(name = "CASH_CUS_CREDIT_RATING", length = 50)
    private String cashCusCreditRating;

    @Column(name = "CASH_CUS_CREDIT_LIMIT", precision = 18, scale = 2)
    private BigDecimal cashCusCreditLimit;

    @Column(name = "CASH_CUS_DB_RATING", length = 50)
    private String cashCusDbRating;

    @Column(name = "CASH_CUS_DB_NUMBER", length = 50)
    private String cashCusDbNumber;

    @Column(name = "CASH_CUS_CREDIT_EXPOSURE", precision = 18, scale = 2)
    private BigDecimal cashCusCreditExposure;

    @Column(name = "CASH_CUS_CREDIT_HOLD_FLAG", length = 50)
    private String cashCusCreditHoldFlag;

    @Column(name = "CASH_CUS_TAX_RE_CERTIFICATE", length = 50)
    private String cashCusTaxReCertificate;

    @Column(name = "CASH_CUS_TAX_RE_CERT_FED", length = 50)
    private String cashCusTaxReCertFed;

    @Column(name = "CASH_CUS_DSO_DAYS", precision = 15, scale = 2)
    private BigDecimal cashCusDsoDays;

    @Column(name = "CASH_CUS_PAYMENT_TERM", length = 80)
    private String cashCusPaymentTerm;

    @Column(name = "CASH_CUS_OUTSIDE_SALES_REP_ID", length = 30)
    private String cashCusOutsideSalesRepId;

    @Column(name = "CASH_CUS_SERVICE_REP_ID", length = 30)
    private String cashCusServiceRepId;

    @Column(name = "CASH_CUS_CLAIMS_REP_ID", length = 30)
    private String cashCusClaimsRepId;

    @Column(name = "CASH_CUS_TRADING_PARTNER", length = 15)
    private String cashCusTradingPartner;

    @Column(name = "CASH_CUS_CUSTOMER_CATEGORY", length = 50)
    private String cashCusCustomerCategory;

    @Column(name = "CASH_CUS_CUSTOMER_TYPE", length = 50)
    private String cashCusCustomerType;

    @Column(name = "CASH_CUS_CUSTOMER_CLASS", length = 50)
    private String cashCusCustomerClass;

    @Column(name = "CASH_CUS_CUSTOMER_GROUP", length = 50)
    private String cashCusCustomerGroup;

    @Column(name = "CASH_CUS_INDUSTRY", length = 50)
    private String cashCusIndustry;

    @Column(name = "CASH_CUS_GENERAL_NOTE", length = 250)
    private String cashCusGeneralNote;

    @Column(name = "CASH_CUS_OPERATING_CURRENCY", length = 25)
    private String cashCusOperatingCurrency;

    @Column(name = "CASH_CUS_COLLECTION_CATEGORY", length = 50)
    private String cashCusCollectionCategory;

    @Column(name = "CASH_CUS_LAST_CREDIT_REV_DATE")
    private LocalDate cashCusLastCreditRevDate;

    @Column(name = "CASH_CUS_TIME_ZONE", length = 50)
    private String cashCusTimeZone;

    @Column(name = "CASH_CUS_REGION_CODE", length = 50)
    private String cashCusRegionCode;

    @Column(name = "CASH_CUS_DISCOUNT", precision = 18, scale = 2)
    private BigDecimal cashCusDiscount;

    @Column(name = "CASH_CUS_PARENT_COMPANY_ID", length = 100)
    private String cashCusParentCompanyId;

    @Column(name = "CASH_CUS_PARENT_CUSTOMER_ID", nullable = false, length = 50)
    private String cashCusParentCustomerId;

    @Column(name = "CASH_CUS_STATUS", length = 20)
    private String cashCusStatus;

    @Column(name = "CASH_CUS_IS_ACTIVE", length = 1)
    private String cashCusIsActive;

    @Column(name = "CASH_CUS_PHONE", length = 50)
    private String cashCusPhone;

    @Column(name = "CASH_CUS_FAX", length = 50)
    private String cashCusFax;

    @Column(name = "CASH_CUS_EMAIL", length = 100)
    private String cashCusEmail;

    @Column(name = "CASH_CUS_TYPE", length = 25)
    private String cashCusType;

    @Column(name = "CASH_CUS_CREDIT_CHECK_FLAG", length = 1)
    private String cashCusCreditCheckFlag;

    @Column(name = "CASH_CUS_DUNNING_ALLOWED", length = 1)
    private String cashCusDunningAllowed;

    @Column(name = "CASH_CUS_SALES_CHANNEL_CODE", length = 30)
    private String cashCusSalesChannelCode;

    @Column(name = "CASH_CUS_INV_LOCATION", length = 150)
    private String cashCusInvLocation;

    @Column(name = "CASH_CUS_COLL_REASSIGN_FLAG", length = 1)
    private String cashCusCollReassignFlag;

    @Column(name = "CASH_CUS_CREDIT_CASE_CLASSIF", length = 50)
    private String cashCusCreditCaseClassif;

    @Column(name = "CASH_CUS_CASE_TYPE", length = 30)
    private String cashCusCaseType;

    @Column(name = "CASH_CUS_CASE_STATUS", length = 30)
    private String cashCusCaseStatus;

    @Column(name = "CASH_CUS_ASSIGNED_CREDIT_ANAL", length = 30)
    private String cashCusAssignedCreditAnal;

    @Column(name = "CASH_CUS_LAST_DECISION", length = 50)
    private String cashCusLastDecision;

    @Column(name = "CASH_CUS_LAST_DECISION_DATE")
    private LocalDate cashCusLastDecisionDate;

    @Column(name = "CASH_CUS_LAST_MODIFIED_BY")
    private Long cashCusLastModifiedBy;

    @Column(name = "CASH_CUS_SCORE", precision = 20, scale = 2)
    private BigDecimal cashCusScore;

    @Column(name = "CASH_CUS_SCORECARD_USED", length = 30)
    private String cashCusScorecardUsed;

    @Column(name = "CASH_CUS_SCORED_DATE")
    private LocalDate cashCusScoredDate;

    @Column(name = "CASH_CUS_LAST_APPROVED_AMT", precision = 18, scale = 2)
    private BigDecimal cashCusLastApprovedAmt;

    @Column(name = "CASH_CUS_LAST_APP_TRAN_MAX_AMT", precision = 18, scale = 2)
    private BigDecimal cashCusLastAppTranMaxAmt;

    @Column(name = "CASH_CUS_LAST_APPROVED_CURR", length = 25)
    private String cashCusLastApprovedCurr;

    @Column(name = "CASH_CUS_LAST_APPROVED_TERM")
    private Short cashCusLastApprovedTerm;

    @Column(name = "CASH_CUS_LAST_EXPIRED_DATE")
    private LocalDate cashCusLastExpiredDate;

    @Column(name = "CASH_CUS_TOTAL_AR_BALANCE", precision = 18, scale = 2)
    private BigDecimal cashCusTotalArBalance;

    @Column(name = "CASH_CUS_LAST_DECN_REASON_CODE", length = 50)
    private String cashCusLastDecnReasonCode;

    @Column(name = "CASH_CUS_LAST_SEC_DREASON_CODE", length = 50)
    private String cashCusLastSecDreasonCode;

    @Column(name = "CASH_CUS_CREDIT_REMAINING", precision = 18, scale = 2)
    private BigDecimal cashCusCreditRemaining;

    @Column(name = "CASH_CUS_PERCENT_CREDIT_USAGE")
    private Long cashCusPercentCreditUsage;

    @Column(name = "CASH_CUS_LAST_DECISION_NOTES", length = 250)
    private String cashCusLastDecisionNotes;

    @Column(name = "CASH_CUS_AUTO_MESSAGE_ID")
    private Long cashCusAutoMessageId;

    @Column(name = "CASH_CUS_DELETED_STRATEGY_ID", length = 100)
    private String cashCusDeletedStrategyId;

    @Column(name = "CASH_CUS_EFF_START_DATE")
    private LocalDate cashCusEffStartDate;

    @Column(name = "CASH_CUS_EFF_END_DATE")
    private LocalDate cashCusEffEndDate;

    @Column(name = "CASH_CUS_LANGUAGE", length = 50)
    private String cashCusLanguage;

    @Column(name = "CASH_CUS_DATE_FORMAT", length = 50)
    private String cashCusDateFormat;

    @Column(name = "CASH_CUS_CURRENCY_COUNTRY", length = 50)
    private String cashCusCurrencyCountry;

    @Column(name = "CASH_CUS_LEAD_OPEN_DAYS")
    private Integer cashCusLeadOpenDays;

    @Column(name = "CASH_CUS_COMERCIAL_CREDT_SCORE")
    private Short cashCusComercialCredtScore;

    @Column(name = "CASH_CUS_BANKRUPTCY_PRESENT", length = 5)
    private String cashCusBankruptcyPresent;

    @Column(name = "CASH_CUS_FAILURE_SCORE")
    private Short cashCusFailureScore;

    @Column(name = "CASH_CUS_ORDER_AMOUNT", precision = 18, scale = 2)
    private BigDecimal cashCusOrderAmount;

    @Column(name = "CASH_CUS_LAS_CREDT_DATA_IMPORT")
    private LocalDate cashCusLasCredtDataImport;

    @Column(name = "CASH_CUS_NEXT_REVIEW_DATE")
    private LocalDate cashCusNextReviewDate;

    @Column(name = "CASH_CUS_CREATION_DATE", nullable = false)
    private LocalDate cashCusCreationDate;

    @Column(name = "CASH_CUS_CREATED_BY", nullable = false)
    private Long cashCusCreatedBy;

    @Column(name = "CASH_CUS_LAST_UPDATED_DATE")
    private LocalDate cashCusLastUpdatedDate;

    @Column(name = "CASH_CUS_LAST_UPDATED_BY")
    private Long cashCusLastUpdatedBy;

    @Column(name = "CASH_CUS_ATTRIBUTE1", length = 50)
    private String cashCusAttribute1;

    @Column(name = "CASH_CUS_ATTRIBUTE2", length = 50)
    private String cashCusAttribute2;

    @Column(name = "CASH_CUS_ATTRIBUTE3", length = 50)
    private String cashCusAttribute3;

    @Column(name = "CASH_CUS_ATTRIBUTE4", length = 50)
    private String cashCusAttribute4;

    @Column(name = "CASH_CUS_ATTRIBUTE5", length = 50)
    private String cashCusAttribute5;

    @Column(name = "CASH_CUS_ATTRIBUTE6", length = 50)
    private String cashCusAttribute6;

    @Column(name = "CASH_CUS_ATTRIBUTE7", length = 50)
    private String cashCusAttribute7;

    @Column(name = "CASH_CUS_ATTRIBUTE8", length = 50)
    private String cashCusAttribute8;

    @Column(name = "CASH_CUS_ATTRIBUTE9", length = 50)
    private String cashCusAttribute9;

    @Column(name = "CASH_CUS_ATTRIBUTE10", length = 50)
    private String cashCusAttribute10;

    @Column(name = "CASH_CUS_DUNNED_DATE")
    private LocalDate cashCusDunnedDate;

    @Column(name = "CASH_CUS_OLD_COLLECTOR_ID", length = 30)
    private String cashCusOldCollectorId;

    @Column(name = "CASH_CUS_COLL_REASSIGN_REASON", length = 300)
    private String cashCusCollReassignReason;

    @Column(name = "CASH_CUS_MICR", length = 100)
    private String cashCusMicr;

    @Column(name = "CASH_CUS_DELIQUENT_DSO", precision = 18, scale = 2)
    private BigDecimal cashCusDeliquentDso;

    @Column(name = "CASH_CUS_BP_DSO_DAYS", precision = 18, scale = 2)
    private BigDecimal cashCusBpDsoDays;

    @Column(name = "CASH_CUS_DSO_PERIOD")
    private Long cashCusDsoPeriod;

    @Column(name = "CASH_CUS_COMPANY_LEVEL1_ID", length = 20)
    private String cashCusCompanyLevel1Id;

    @Column(name = "CASH_CUS_LEVEL1_CUSTOMER_ID", length = 20)
    private String cashCusLevel1CustomerId;

    @Column(name = "CASH_CUS_PREDICT_RISK", length = 100)
    private String cashCusPredictRisk;

    @Column(name = "CASH_CUS_PREDICT_RISK_DATE")
    private LocalDate cashCusPredictRiskDate;

    @Column(name = "CASH_CUS_STRATEGY_ID")
    private Long cashCusStrategyId;

    @Column(name = "CASH_CUS_STRATEGY_DATE")
    private LocalDate cashCusStrategyDate;

    @Column(name = "CASH_CUS_CURR_STRTGY_DT")
    private LocalDate cashCusCurrStrtgyDt;

    @Column(name = "CASH_CUS_CURR_STRTGY_ID")
    private Long cashCusCurrStrtgyId;

    @Column(name = "CASH_CUS_PROFILE", length = 500)
    private String cashCusProfile;

    @Column(name = "CASH_CUS_DIVISION", length = 10)
    private String cashCusDivision;

    @Column(name = "CASH_CUS_BILL_FREQUENCY", length = 50)
    private String cashCusBillFrequency;

    @Column(name = "CASH_CUS_BUSINESS_UNIT_CD", length = 50)
    private String cashCusBusinessUnitCd;

    @Column(name = "CASH_CUS_DIVERSITY_CD", length = 50)
    private String cashCusDiversityCd;

    @Column(name = "CASH_CUS_DIVERSITY_NAME", length = 50)
    private String cashCusDiversityName;

    @Column(name = "CASH_CUS_MONTHLY_STATEMENT_FLAG", length = 50)
    private String cashCusMonthlyStatementFlag;

    @Column(name = "CASH_CUS_BILLTO_ONLY", length = 50)
    private String cashCusBilltoOnly;

    @Column(name = "CASH_CUS_MASTER_CUSTOMER", length = 50)
    private String cashCusMasterCustomer;

    @Column(name = "CASH_CUS_MASTER_DIVISION", length = 50)
    private String cashCusMasterDivision;

}