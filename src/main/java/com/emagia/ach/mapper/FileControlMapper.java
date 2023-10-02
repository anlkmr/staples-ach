package com.emagia.ach.mapper;

import com.emagia.ach.dto.FileControlDto;
import com.emagia.ach.entity.FileControlEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FileControlMapper extends EntityMapper<FileControlDto, FileControlEntity> {
}