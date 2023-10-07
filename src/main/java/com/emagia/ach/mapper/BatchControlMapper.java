package com.emagia.ach.mapper;

import com.emagia.ach.dto.BatchControlDto;
import com.emagia.ach.entity.BatchControlEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BatchControlMapper extends EntityMapper<BatchControlDto, BatchControlEntity> {

}