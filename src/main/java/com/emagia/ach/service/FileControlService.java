package com.emagia.ach.service;

import com.emagia.ach.dto.FileControlDto;
import com.emagia.ach.entity.FileControlEntity;
import com.emagia.ach.mapper.FileControlMapper;
import com.emagia.ach.repository.FileControlRepository;
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
public class FileControlService {
    private final FileControlRepository repository;
    private final FileControlMapper fileControlMapper;

    public FileControlService(FileControlRepository repository, FileControlMapper fileControlMapper) {
        this.repository = repository;
        this.fileControlMapper = fileControlMapper;
    }

    public FileControlDto save(FileControlDto fileControlDto) {
        FileControlEntity entity = fileControlMapper.toEntity(fileControlDto);
        return fileControlMapper.toDto(repository.save(entity));
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public FileControlDto findById(Long id) {
        return fileControlMapper.toDto(repository.findById(id).get());
                //.orElseThrow(ResourceNotFoundException::new));
    }

    public Page<FileControlDto> findByCondition(FileControlDto fileControlDto, Pageable pageable) {
        Page<FileControlEntity> entityPage = repository.findAll(pageable);
        List<FileControlEntity> entities = entityPage.getContent();
        return new PageImpl<>(fileControlMapper.toDto(entities), pageable, entityPage.getTotalElements());
    }

    public FileControlDto update(FileControlDto fileControlDto, Long id) {
        FileControlDto data = findById(id);
        FileControlEntity entity = fileControlMapper.toEntity(fileControlDto);
        BeanUtils.copyProperties(data, entity);
        return save(fileControlMapper.toDto(entity));
    }
}
