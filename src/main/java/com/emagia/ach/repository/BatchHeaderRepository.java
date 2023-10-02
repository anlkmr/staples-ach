package com.emagia.ach.repository;

import com.emagia.ach.entity.BatchHeaderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface BatchHeaderRepository extends JpaRepository<BatchHeaderEntity, Long>, JpaSpecificationExecutor<BatchHeaderEntity> {
}