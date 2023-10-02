package com.emagia.ach.repository;import com.emagia.ach.entity.BatchControlEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface BatchControlRepository extends JpaRepository<BatchControlEntity, Long>, JpaSpecificationExecutor<BatchControlEntity> {
}