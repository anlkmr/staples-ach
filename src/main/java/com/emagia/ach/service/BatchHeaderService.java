package com.emagia.ach.service;

import com.emagia.ach.dto.BatchHeaderDto;
import com.emagia.ach.entity.BatchHeaderEntity;
import com.emagia.ach.mapper.BatchHeaderMapper;
import com.emagia.ach.repository.BatchHeaderRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@Slf4j
@Service
@Transactional
public class BatchHeaderService {
    private final BatchHeaderRepository repository;
    private final BatchHeaderMapper batchHeaderMapper;

    public BatchHeaderService(BatchHeaderRepository repository, BatchHeaderMapper batchHeaderMapper) {
        this.repository = repository;
        this.batchHeaderMapper = batchHeaderMapper;
    }

    public BatchHeaderDto save(BatchHeaderDto batchHeaderDto) {
        BatchHeaderEntity entity = batchHeaderMapper.toEntity(batchHeaderDto);
        return batchHeaderMapper.toDto(repository.save(entity));
    }

    public BatchHeaderEntity toEntity(BatchHeaderDto dto) {
        if ( dto == null ) {
            return null;
        }

        BatchHeaderEntity batchHeaderEntity = new BatchHeaderEntity();

        batchHeaderEntity.setRecordtype( dto.getRecordtype() );
        batchHeaderEntity.setServiceclasscode( dto.getServiceclasscode() );
        batchHeaderEntity.setCompanyNamePayeePayor( dto.getCompanyNamePayeePayor() );
        batchHeaderEntity.setCompanyDiscretionaryData( dto.getCompanyDiscretionaryData() );
        batchHeaderEntity.setCompanyid( dto.getCompanyid() );
        batchHeaderEntity.setSeccode( dto.getSeccode() );
        batchHeaderEntity.setCompanyEntryDesc( dto.getCompanyEntryDesc() );
        batchHeaderEntity.setCompanyDescDate( dto.getCompanyDescDate() );
        try {
            if ( dto.getEffectiveEntryDate() != null ) {
                batchHeaderEntity.setEffectiveEntryDate( new SimpleDateFormat("yyMMdd").parse( dto.getEffectiveEntryDate() ) );
            }
        }
        catch ( ParseException e ) {
            throw new RuntimeException( e );
        }
        batchHeaderEntity.setSettlementDate( dto.getSettlementDate() );
        batchHeaderEntity.setOriginatorStatusCode( dto.getOriginatorStatusCode() );
        batchHeaderEntity.setRtNumberOdfiId( dto.getRtNumberOdfiId() );
        batchHeaderEntity.setBatchnumber( dto.getBatchnumber() );
        if ( dto.getBatchHeaderId() != null ) {
            batchHeaderEntity.setBatchHeaderId( dto.getBatchHeaderId() );
        }

        return batchHeaderEntity;
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public BatchHeaderDto findById(Long id) {
        return batchHeaderMapper.toDto(repository.findById(id).get());
                //.orElseThrow(ResourceNotFoundException::new));
    }

    public Page<BatchHeaderDto> findByCondition(BatchHeaderDto batchHeaderDto, Pageable pageable) {
        Page<BatchHeaderEntity> entityPage = repository.findAll(pageable);
        List<BatchHeaderEntity> entities = entityPage.getContent();
        return new PageImpl<>(batchHeaderMapper.toDto(entities), pageable, entityPage.getTotalElements());
    }

    public BatchHeaderDto update(BatchHeaderDto batchHeaderDto, Long id) {
        BatchHeaderDto data = findById(id);
        BatchHeaderEntity entity = batchHeaderMapper.toEntity(batchHeaderDto);
        BeanUtils.copyProperties(data, entity);
        return save(batchHeaderMapper.toDto(entity));
    }
}
