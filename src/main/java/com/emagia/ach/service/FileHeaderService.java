package com.emagia.ach.service;

import com.emagia.ach.dto.FileHeaderDto;
import com.emagia.ach.entity.FileHeaderEntity;
import com.emagia.ach.mapper.FileHeaderMapper;
import com.emagia.ach.repository.FileHeaderRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Transactional
public class FileHeaderService {
    private final FileHeaderRepository repository;
    private final FileHeaderMapper fileHeaderMapper;

    public FileHeaderService(FileHeaderRepository repository, FileHeaderMapper fileHeaderMapper) {
        this.repository = repository;
        this.fileHeaderMapper = fileHeaderMapper;
    }

    public FileHeaderDto save(FileHeaderDto fileHeaderDto) {
        FileHeaderEntity entity = fileHeaderMapper.toEntity(fileHeaderDto);
        return fileHeaderMapper.toDto(repository.save(entity));
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public FileHeaderDto findById(Long id) {
        return fileHeaderMapper.toDto(repository.findById(id).get());
                //.orElseThrow(ResourceNotFoundException::new));
    }

    public Page<FileHeaderDto> findByCondition(FileHeaderDto fileHeaderDto, Pageable pageable) {
        Page<FileHeaderEntity> entityPage = repository.findAll(pageable);
        List<FileHeaderEntity> entities = entityPage.getContent();
        return new PageImpl<>(fileHeaderMapper.toDto(entities), pageable, entityPage.getTotalElements());
    }

    public FileHeaderDto update(FileHeaderDto fileHeaderDto, Long id) {
        FileHeaderDto data = findById(id);
        FileHeaderEntity entity = fileHeaderMapper.toEntity(fileHeaderDto);
        BeanUtils.copyProperties(data, entity);
        return save(fileHeaderMapper.toDto(entity));
    }
}
