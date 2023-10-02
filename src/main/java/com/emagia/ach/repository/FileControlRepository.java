package com.emagia.ach.repository;

import com.emagia.ach.entity.FileControlEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface FileControlRepository extends JpaRepository<FileControlEntity, Long>, JpaSpecificationExecutor<FileControlEntity> {
}