package com.emagia.ach.repository;

import com.emagia.ach.entity.AddendaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface AddendaRepository extends JpaRepository<AddendaEntity, Long>, JpaSpecificationExecutor<AddendaEntity> {
}