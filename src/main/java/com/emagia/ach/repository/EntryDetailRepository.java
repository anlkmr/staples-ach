package com.emagia.ach.repository;

import com.emagia.ach.entity.EntryDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface EntryDetailRepository extends JpaRepository<EntryDetailEntity, Long>, JpaSpecificationExecutor<EntryDetailEntity> {
}