package com.emagia.ach.mapper;

import com.emagia.ach.dto.EntryDetailDto;
import com.emagia.ach.entity.EntryDetailEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EntryDetailMapper extends EntityMapper<EntryDetailDto, EntryDetailEntity> {
}