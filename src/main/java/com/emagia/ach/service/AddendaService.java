package com.emagia.ach.service;

import com.emagia.ach.dto.AddendaDto;
import com.emagia.ach.entity.AddendaEntity;
import com.emagia.ach.exception.ResourceNotFoundException;
import com.emagia.ach.mapper.AddendaMapper;
import com.emagia.ach.repository.AddendaRepository;
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
public class AddendaService {
    private final AddendaRepository repository;
    private final AddendaMapper addendaMapper;

     public AddendaService(AddendaRepository repository, AddendaMapper addendaMapper) {
        this.repository = repository;
        this.addendaMapper = addendaMapper;
    }

    public AddendaDto save(AddendaDto addendaDto) {
        AddendaEntity entity = addendaMapper.toEntity(addendaDto);
        return addendaMapper.toDto(repository.save(entity));
    }

    public void deleteById(long id) {
        repository.deleteById(id);
    }

    public AddendaDto findById(long id) {
        return addendaMapper.toDto(repository.findById(id)
                .orElseThrow(ResourceNotFoundException::new));
    }

    public Page<AddendaDto> findByCondition(AddendaDto addendaDto, Pageable pageable) {
        Page<AddendaEntity> entityPage = repository.findAll(pageable);
        List<AddendaEntity> entities = entityPage.getContent();
        return new PageImpl<>(addendaMapper.toDto(entities), pageable, entityPage.getTotalElements());
    }

    public AddendaDto update(AddendaDto addendaDto, long id) {
        AddendaDto data = findById(id);
        AddendaEntity entity = addendaMapper.toEntity(addendaDto);
        BeanUtils.copyProperties(data, entity);
        return save(addendaMapper.toDto(entity));
    }
}
