package com.emagia.ach.mapper;

import com.emagia.ach.dto.BatchHeaderDto;
import com.emagia.ach.entity.BatchHeaderEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BatchHeaderMapper extends EntityMapper<BatchHeaderDto, BatchHeaderEntity> {
}