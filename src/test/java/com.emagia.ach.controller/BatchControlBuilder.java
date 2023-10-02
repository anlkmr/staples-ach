package com.emagia.ach.controller;

import com.emagia.ach.dto.BatchControlDto;

import java.util.Collections;
import java.util.List;

public class BatchControlBuilder {
    public static List<String> getIds() {
        return Collections.singletonList("1");
    }

    public static BatchControlDto getDto() {
        BatchControlDto dto = new BatchControlDto();
        dto.setId(1L);
        return dto;
    }
}
