package com.emagia.ach.mapper;import com.emagia.ach.dto.BatchControlDto;
import com.emagia.ach.entity.BatchControlEntity;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Mapper(componentModel = "spring")
public interface BatchControlMapper extends EntityMapper<BatchControlDto, BatchControlEntity> {

}