package com.emagia.ach.mapper;

import com.emagia.ach.dto.AddendaDto;
import com.emagia.ach.entity.AddendaEntity;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface AddendaMapper extends EntityMapper<AddendaDto, AddendaEntity> {
}