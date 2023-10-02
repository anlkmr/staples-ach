package com.emagia.ach.mapper;

import com.emagia.ach.dto.FileHeaderDto;
import com.emagia.ach.entity.FileHeaderEntity;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
public interface FileHeaderMapper extends EntityMapper<FileHeaderDto, FileHeaderEntity> {
}