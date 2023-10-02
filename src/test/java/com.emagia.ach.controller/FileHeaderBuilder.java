package com.emagia.ach.controller;

import com.emagia.ach.dto.FileHeaderDto;

import java.util.Collections;
import java.util.List;

public class FileHeaderBuilder {
    public static List<String> getIds() {
        return Collections.singletonList("1");
    }

    public static FileHeaderDto getDto() {
        FileHeaderDto dto = new FileHeaderDto();
        dto.setFileHeaderId(1L);
        return dto;
    }
}
