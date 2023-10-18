package com.emagia.ach.staples_emagia.repository;

import com.emagia.ach.entity.staples_emagia.CashPaymentTransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CashPaymentTransactionEntityRepository extends JpaRepository<CashPaymentTransactionEntity, Long> {
}