package com.emagia.ach.service;

import com.emagia.ach.dto.EntryDetailDto;
import com.emagia.ach.entity.EntryDetailEntity;
import com.emagia.ach.mapper.EntryDetailMapper;
import com.emagia.ach.repository.EntryDetailRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@Transactional
public class EntryDetailService {
    private final EntryDetailRepository repository;
    private final EntryDetailMapper entryDetailMapper;

    public EntryDetailService(EntryDetailRepository repository, EntryDetailMapper entryDetailMapper) {
        this.repository = repository;
        this.entryDetailMapper = entryDetailMapper;
    }

    public EntryDetailDto save(EntryDetailDto entryDetailDto) {
        EntryDetailEntity entity = entryDetailMapper.toEntity(entryDetailDto);
        return entryDetailMapper.toDto(repository.save(entity));
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public EntryDetailDto findById(Long id) {
        return entryDetailMapper.toDto(repository.findById(id).get());
                //.orElseThrow(ResourceNotFoundException::new));
    }

    public Page<EntryDetailDto> findByCondition(EntryDetailDto entryDetailDto, Pageable pageable) {
        Page<EntryDetailEntity> entityPage = repository.findAll(pageable);
        List<EntryDetailEntity> entities = entityPage.getContent();
        return new PageImpl<>(entryDetailMapper.toDto(entities), pageable, entityPage.getTotalElements());
    }

    public EntryDetailDto update(EntryDetailDto entryDetailDto, Long id) {
        EntryDetailDto data = findById(id);
        EntryDetailEntity entity = entryDetailMapper.toEntity(entryDetailDto);
        BeanUtils.copyProperties(data, entity);
        return save(entryDetailMapper.toDto(entity));
    }
}
