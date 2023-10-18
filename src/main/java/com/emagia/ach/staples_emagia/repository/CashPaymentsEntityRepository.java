package com.emagia.ach.staples_emagia.repository;

import com.emagia.ach.entity.staples_emagia.CashPaymentsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CashPaymentsEntityRepository extends JpaRepository<CashPaymentsEntity, Long> {
}