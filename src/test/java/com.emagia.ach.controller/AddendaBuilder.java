package com.emagia.ach.controller;

import com.emagia.ach.dto.AddendaDto;

import java.util.Collections;
import java.util.List;

public class AddendaBuilder {
    public static List<String> getIds() {
        return Collections.singletonList("1");
    }

    public static AddendaDto getDto() {
        AddendaDto dto = new AddendaDto();
        dto.setId(1);
        return dto;
    }
}
