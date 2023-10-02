package com.emagia.ach.controller;

import com.emagia.ach.dto.EntryDetailDto;

import java.util.Collections;
import java.util.List;

public class EntryDetailBuilder {
    public static List<String> getIds() {
        return Collections.singletonList("1");
    }

    public static EntryDetailDto getDto() {
        EntryDetailDto dto = new EntryDetailDto();
        dto.setId(1L);
        return dto;
    }
}
