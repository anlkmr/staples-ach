package com.emagia.ach.staples_emagia.repository;

import com.emagia.ach.entity.staples_emagia.CashCompaniesEntity;
import com.emagia.ach.entity.staples_emagia.PaymentsCaptureBO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StaplesEmagiaMISCRepository extends JpaRepository<CashCompaniesEntity, String>, JpaSpecificationExecutor<CashCompaniesEntity> {

    /*@Query(
            nativeQuery = true,
            value
                    = "SELECT ea.id, ea.city, ea.state FROM gfgmicroservicesdemo.address ea join gfgmicroservicesdemo.employee e on e.id = ea.employee_id where ea.employee_id=:employeeId")
    Optional<Address> findAddressByEmployeeId(@Param("employeeId") int employeeId);*/
//    @Query("SELECT new com.baeldung.aggregation.model.custom.CommentCount(c.year, COUNT(c.year)) "
//            + "FROM Comment AS c GROUP BY c.year ORDER BY c.year DESC")

    //nativeQuery = true,
    //value
                    /*= "SELECT  CP.CASH_PAY_TYPE, CP.CASH_PAY_TOTAL_AMOUNT, CP.CASH_PAY_DATE, CP.CASH_PAY_CREATION_DATE, CP.CASH_PAY_ATTRIBUTE4, \n" +
                    "CPT.CASH_PAYT_AMOUNT_PAID, CPT.CASH_PAYT_HFM, CPT.CASH_PAYT_CUSTOMER_ID, CPT.CASH_PAYT_DATE, CPT.CASH_PAYT_CREATION_DATE, \n" +
                    "CABT.CASH_ABT_BANK_NAME, CABT.CASH_ABT_BANK_ACC_NUMBER, CABT.CASH_ABT_ROUTING_NUMBER, \n" +
                    "CC.CASH_CUS_ID, CC.CASH_CUS_COMPANY_ID, CC.CASH_CUS_NUMBER, CC.CASH_CUS_NAME, CC.CASH_CUS_CREDIT_LIMIT, CC.CASH_CUS_PARENT_CUSTOMER_ID " +
                    "FROM CASH_PAYMENTS cp JOIN CASH_PAYMENT_TRANSACTIONS cpt ON cp.CASH_PAY_ID = cpt.CASH_PAYT_PAYMENT_ID " +
                    "JOIN CASH_ACH_BANK_TRANSACTIONS cabt ON cabt.CASH_ABT_TOKEN_NUMBER = cp.CASH_PAY_ATTRIBUTE4 " +
                    "JOIN CASH_CUSTOMER cc ON cc.CASH_CUS_ID = cpt.CASH_PAYT_CUSTOMER_ID  " +
                    "AND cp.CASH_PAY_TYPE=:ach WHERE cpt.CASH_PAYT_HFM=:BU")*/

/*

    @Query("SELECT new com.emagia.ach.entity.staples_emagia.PaymentsCaptureBO(e.cashPayType, cast(e.cashPayTotalAmount as BigDecimal ), cast(e.cashPayDate as Date), cast(e.cashPayCreationDate as Date), e.cashPayAttribute4, " +
            "cast(e.cashPaytAmountPaid as BigDecimal), e.cashPaytHfm, e.cashPaytCustomerId, cast(e.cashPaytDate as Date), cast(e.cashPaytCreationDate as Date), " +
            "e.cashAbtBankName, e.cashAbtBankAccNumber, e.cashAbtRoutingNumber, " +
            "e.cashCusId, e.cashCusCompanyId, e.cashCusNumber, e.cashCusName, cast(e.cashCusCreditLimit as BigDecimal), e.cashCusParentCustomerId) " +
   "FROM (SELECT cp.cashPayType as cashPayType, cp.cashPayTotalAmount as cashPayTotalAmount, cp.cashPayDate as cashPayDate, cp.cashPayCreationDate as cashPayCreationDate, cp.cashPayAttribute4 as cashPayAttribute4, cpt.cashPaytAmountPaid as cashPaytAmountPaid, cpt.cashPaytHfm as cashPaytHfm, cpt.cashPaytCustomerId as cashPaytCustomerId, cpt.cashPaytDate as cashPaytDate, cpt.cashPaytCreationDate as cashPaytCreationDate, cabt.cashAbtBankName as cashAbtBankName, cabt.cashAbtBankAccNumber as cashAbtBankAccNumber, cabt.cashAbtRoutingNumber as cashAbtRoutingNumber, cc.cashCusId as cashCusId, cc.cashCusCompanyId as cashCusCompanyId, cc.cashCusNumber as cashCusNumber, cc.cashCusName as cashCusName, cc.cashCusCreditLimit as cashCusCreditLimit, cc.cashCusParentCustomerId as cashCusParentCustomerId FROM CashPaymentsEntity cp JOIN CashPaymentTransactionEntity cpt ON cp.cashPayId = cpt.cashPaytPaymentId JOIN CashAchBankTransactionEntity cabt ON cabt.cashAbtTokenNumber = cp.cashPayAttribute4 JOIN CashCustomerEntity cc ON cc.cashCusId = cpt.cashPaytCustomerId AND cp.cashPayType=:ach WHERE cpt.cashPaytHfm=:BU)e")
*/


    @Query("SELECT new com.emagia.ach.entity.staples_emagia.PaymentsCaptureBO(cast(e.cashPayId as BigInteger), cast(e.cashPaytPaymentId as BigInteger), e.cashPayType, cast(e.cashPayTotalAmount as BigDecimal ), cast(e.cashPayDate as Date), cast(e.cashPayCreationDate as Date), e.cashPayAttribute4, " +
            "cast(e.cashPaytAmountPaid as BigDecimal), e.cashPaytHfm, e.cashPaytCustomerId, cast(e.cashPaytDate as Date), cast(e.cashPaytCreationDate as Date), e.cashPaytTransactionId, " +
            "e.cashAbtBankName, e.cashAbtBankAccNumber, e.cashAbtRoutingNumber, " +
            "e.cashCusId, e.cashCusCompanyId, e.cashCusNumber, e.cashCusName, cast(e.cashCusCreditLimit as BigDecimal), e.cashCusParentCustomerId) " +
            "FROM (SELECT cp.cashPayId as cashPayId, cp.cashPayType as cashPayType, cp.cashPayTotalAmount as cashPayTotalAmount, cp.cashPayDate as cashPayDate, cp.cashPayCreationDate as cashPayCreationDate, cp.cashPayAttribute4 as cashPayAttribute4, cpt.cashPaytPaymentId as cashPaytPaymentId, cpt.cashPaytAmountPaid as cashPaytAmountPaid, cpt.cashPaytHfm as cashPaytHfm, cpt.cashPaytCustomerId as cashPaytCustomerId, cpt.cashPaytDate as cashPaytDate, cpt.cashPaytCreationDate as cashPaytCreationDate, cpt.cashPaytTransactionId as cashPaytTransactionId, cabt.cashAbtBankName as cashAbtBankName, cabt.cashAbtBankAccNumber as cashAbtBankAccNumber, cabt.cashAbtRoutingNumber as cashAbtRoutingNumber, cc.cashCusId as cashCusId, cc.cashCusCompanyId as cashCusCompanyId, cc.cashCusNumber as cashCusNumber, cc.cashCusName as cashCusName, cc.cashCusCreditLimit as cashCusCreditLimit, cc.cashCusParentCustomerId as cashCusParentCustomerId FROM CashPaymentsEntity cp JOIN CashPaymentTransactionEntity cpt ON cp.cashPayId = cpt.cashPaytPaymentId JOIN CashAchBankTransactionEntity cabt ON cabt.cashAbtTokenNumber = cp.cashPayAttribute4 JOIN CashCustomerEntity cc ON cc.cashCusId = cpt.cashPaytCustomerId    JOIN CashCompaniesEntity ccmp ON cpt.cashPaytHfm  = ccmp.cashComId  AND cp.cashPayType=:ach  WHERE ccmp.cashComName=:BU)e")


   /* @Query("select new com.emagia.ach.entity.staples_emagia.PaymentsCaptureBO(e.cashPayType)from (SELECT cp.cashPayType as cashPayType, cp.cashPayTotalAmount, cp.cashPayDate, cp.cashPayCreationDate, cp.cashPayAttribute4, " +
            "cpt.cashPaytAmountPaid, cpt.cashPaytHfm, cpt.cashPaytCustomerId, cpt.cashPaytDate, cpt.cashPaytCreationDate, " +
            "cabt.cashAbtBankName, cabt.cashAbtBankAccNumber, cabt.cashAbtRoutingNumber, " +
            "cc.cashCusId, cc.cashCusCompanyId, cc.cashCusNumber, cc.cashCusName, cc.cashCusCreditLimit, cc.cashCusParentCustomerId " +
            "FROM CashPaymentsEntity cp join CashPaymentTransactionEntity cpt on cp.cashPayId = cpt.cashPaytPaymentId " +
            "join CashAchBankTransactionEntity cabt ON cabt.cashAbtTokenNumber = cp.cashPayAttribute4 " +
            "join CashCustomerEntity cc on cc.cashCusId = cpt.cashPaytCustomerId "+
            "AND cp.cashPayType=:ach WHERE cpt.cashPaytHfm=:BU) e")*/
    Optional<List<PaymentsCaptureBO>> getEntryDetails_Payments(@Param("BU") String BU, @Param("ach") String ach);
}