package com.emagia.ach.repository;

import com.emagia.ach.entity.BatchHeaderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BatchHeaderRepository extends JpaRepository<BatchHeaderEntity, Long>, JpaSpecificationExecutor<BatchHeaderEntity> {
    Optional<BatchHeaderEntity> findByCompanyid(String i);
}