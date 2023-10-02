package com.emagia.ach.service;import com.emagia.ach.dto.BatchControlDto;import com.emagia.ach.entity.BatchControlEntity;
import com.emagia.ach.mapper.BatchControlMapper;import com.emagia.ach.repository.BatchControlRepository;import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.emagia.ach.exception.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Transactional
public class BatchControlService {
    private final BatchControlRepository repository;
    private final BatchControlMapper batchControlMapper;

    public BatchControlService(BatchControlRepository repository, BatchControlMapper batchControlMapper) {
        this.repository = repository;
        this.batchControlMapper = batchControlMapper;
    }

    public BatchControlDto save(BatchControlDto batchControlDto) {
        BatchControlEntity entity = batchControlMapper.toEntity(batchControlDto);
        return batchControlMapper.toDto(repository.save(entity));
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public BatchControlDto findById(Long id) {
        return batchControlMapper.toDto(repository.findById(id)
                .orElseThrow(ResourceNotFoundException::new));
        //return new BatchControlDto();
    }

    public Page<BatchControlDto> findByCondition(BatchControlDto batchControlDto, Pageable pageable) {
        Page<BatchControlEntity> entityPage = repository.findAll(pageable);
        List<BatchControlEntity> entities = entityPage.getContent();
        return new PageImpl<>(batchControlMapper.toDto(entities), pageable, entityPage.getTotalElements());
    }

    public BatchControlDto update(BatchControlDto batchControlDto, Long id) {
        BatchControlDto data = findById(id);
        BatchControlEntity entity = batchControlMapper.toEntity(batchControlDto);
        BeanUtils.copyProperties(data, entity);
        return save(batchControlMapper.toDto(entity));
    }
}
