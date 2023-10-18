package com.emagia.ach.staples_emagia.repository;

import com.emagia.ach.entity.staples_emagia.CashCompaniesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CashCompaniesEntityRepository extends JpaRepository<CashCompaniesEntity, String> {
}