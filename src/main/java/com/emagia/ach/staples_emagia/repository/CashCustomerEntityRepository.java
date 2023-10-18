package com.emagia.ach.staples_emagia.repository;

import com.emagia.ach.entity.staples_emagia.CashCustomerEntity;
import com.emagia.ach.entity.staples_emagia.CashCustomerId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CashCustomerEntityRepository extends JpaRepository<CashCustomerEntity, CashCustomerId> {
}