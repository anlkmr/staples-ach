package com.emagia.ach.staples_emagia.repository;

import com.emagia.ach.entity.staples_emagia.CashAchBankTransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CashAchBankTransactionEntityRepository extends JpaRepository<CashAchBankTransactionEntity, Long> {
}