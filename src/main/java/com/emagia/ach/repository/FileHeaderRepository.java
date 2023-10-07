package com.emagia.ach.repository;

import com.emagia.ach.entity.FileHeaderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FileHeaderRepository extends JpaRepository<FileHeaderEntity, Long>, JpaSpecificationExecutor<FileHeaderEntity> {
    Optional<FileHeaderEntity> findByFileidImo(String number);
}