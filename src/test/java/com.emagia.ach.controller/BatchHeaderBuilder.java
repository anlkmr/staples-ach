package com.emagia.ach.controller;

import com.emagia.ach.dto.BatchHeaderDto;

import java.util.Collections;
import java.util.List;

public class BatchHeaderBuilder {
    public static List<String> getIds() {
        return Collections.singletonList("1");
    }

    public static BatchHeaderDto getDto() {
        BatchHeaderDto dto = new BatchHeaderDto();
        dto.setBatchHeaderId(1L);
        return dto;
    }
}
