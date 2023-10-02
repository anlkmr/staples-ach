package com.emagia.ach.controller;

import com.emagia.ach.dto.FileControlDto;

import java.util.Collections;
import java.util.List;

public class FileControlBuilder {
    public static List<String> getIds() {
        return Collections.singletonList("1");
    }

    public static FileControlDto getDto() {
        FileControlDto dto = new FileControlDto();
        dto.setId(1L);
        return dto;
    }
}
